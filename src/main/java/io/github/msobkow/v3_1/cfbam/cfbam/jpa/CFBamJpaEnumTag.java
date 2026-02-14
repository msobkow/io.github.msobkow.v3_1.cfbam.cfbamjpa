// Description: Java 25 JPA implementation of a EnumTag entity definition object.

/*
 *	io.github.msobkow.CFBam
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal 3.1 CFBam - Business Application Model
 *	
 *	This file is part of Mark's Code Fractal CFBam.
 *	
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later.
 *	
 *	Mark's Code Fractal CFBam is free software: you can redistribute it and/or
 *	modify it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *	
 *	Mark's Code Fractal CFBam is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *	
 *	You should have received a copy of the GNU General Public License
 *	along with Mark's Code Fractal CFBam.  If not, see <https://www.gnu.org/licenses/>.
 *	
 *	If you wish to modify and use this code without publishing your changes,
 *	or integrate it with proprietary code, please contact Mark Stephen Sobkow
 *	for a commercial license at mark.sobkow@gmail.com
 *	
 */

package io.github.msobkow.v3_1.cfbam.cfbam.jpa;

import java.io.Serializable;
import java.math.*;
import java.time.*;
import java.util.*;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import io.github.msobkow.v3_1.cflib.*;
import io.github.msobkow.v3_1.cflib.dbutil.*;
import io.github.msobkow.v3_1.cflib.xml.CFLibXmlUtil;
import io.github.msobkow.v3_1.cfsec.cfsec.*;
import io.github.msobkow.v3_1.cfint.cfint.*;
import io.github.msobkow.v3_1.cfbam.cfbam.*;
import io.github.msobkow.v3_1.cfsec.cfsec.jpa.*;
import io.github.msobkow.v3_1.cfint.cfint.jpa.*;

@Entity
@Table(
	name = "enum_tag", schema = "CFBam31",
	indexes = {
		@Index(name = "EnumTagIdIdx", columnList = "Id", unique = true),
		@Index(name = "EnumTagEnumIdx", columnList = "EnumId", unique = false),
		@Index(name = "EnumTagDefSchemaDefIdx", columnList = "defschid", unique = false),
		@Index(name = "EnumTagEnumNameIdx", columnList = "EnumId, safe_name", unique = true),
		@Index(name = "EnumTagPrevIdx", columnList = "PrevId", unique = false),
		@Index(name = "EnumTagNextIdx", columnList = "NextId", unique = false)
	}
)
@Transactional(Transactional.TxType.SUPPORTS)
@PersistenceContext(unitName = "CFBamPU")
public class CFBamJpaEnumTag
	implements Comparable<Object>,
		ICFBamEnumTag,
		Serializable
{
	@Id
	@AttributeOverrides({
		@AttributeOverride(name="bytes", column = @Column( name="Id", nullable=false, length=CFLibDbKeyHash256.HASH_LENGTH ) )
	})
	protected CFLibDbKeyHash256 requiredId;
	protected int requiredRevision;

	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn( name="EnumId" )
	protected CFBamJpaEnumDef requiredContainerEnumDef;
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn( name="defschid" )
	protected CFBamJpaSchemaDef optionalLookupDefSchema;
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn( name="PrevId" )
	protected CFBamJpaEnumTag optionalLookupPrev;
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn( name="NextId" )
	protected CFBamJpaEnumTag optionalLookupNext;

	@AttributeOverrides({
		@AttributeOverride( name="bytes", column = @Column( name="CreatedByUserId", nullable=false, length=CFLibDbKeyHash256.HASH_LENGTH ) )
	})
	protected CFLibDbKeyHash256 createdByUserId = CFLibDbKeyHash256.fromHex(ICFBamEnumTag.S_INIT_CREATED_BY);

	@Column(name="CreatedAt", nullable=false)
	protected LocalDateTime createdAt = LocalDateTime.now();

	@AttributeOverrides({
		@AttributeOverride( name="bytes", column= @Column( name="UpdatedByUserId", nullable=false, length=CFLibDbKeyHash256.HASH_LENGTH ) )
	})
	protected CFLibDbKeyHash256 updatedByUserId = CFLibDbKeyHash256.fromHex(ICFBamEnumTag.S_INIT_UPDATED_BY);

	@Column(name="UpdatedAt", nullable=false)
	protected LocalDateTime updatedAt = LocalDateTime.now();
	@Column( name="EnumCode", nullable=true )
	protected Short optionalEnumCode;
	@Column( name="safe_name", nullable=false, length=64 )
	protected String requiredName;

	public CFBamJpaEnumTag() {
		requiredId = CFLibDbKeyHash256.fromHex( ICFBamEnumTag.ID_INIT_VALUE.toString() );
		optionalEnumCode = null;
		requiredName = ICFBamEnumTag.NAME_INIT_VALUE;
	}

	@Override
	public int getClassCode() {
		return( ICFBamEnumTag.CLASS_CODE );
	}

	@Override
	public ICFBamEnumDef getRequiredContainerEnumDef() {
		return( requiredContainerEnumDef );
	}
	@Override
	public void setRequiredContainerEnumDef(ICFBamEnumDef argObj) {
		if(argObj == null) {
			throw new CFLibNullArgumentException(getClass(), "setContainerEnumDef", 1, "argObj");
		}
		else if (argObj instanceof CFBamJpaEnumDef) {
			requiredContainerEnumDef = (CFBamJpaEnumDef)argObj;
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "setContainerEnumDef", "argObj", argObj, "CFBamJpaEnumDef");
		}
	}

	@Override
	public void setRequiredContainerEnumDef(CFLibDbKeyHash256 argEnumId) {
		ICFBamSchema targetBackingSchema = ICFBamSchema.getBackingCFBam();
		if (targetBackingSchema == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerEnumDef", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamEnumDefTable targetTable = targetBackingSchema.getTableEnumDef();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerEnumDef", 0, "ICFBamSchema.getBackingCFBam().getTableEnumDef()");
		}
		ICFBamEnumDef targetRec = targetTable.readDerived(null, argEnumId);
		setRequiredContainerEnumDef(targetRec);
	}

	@Override
	public ICFBamSchemaDef getOptionalLookupDefSchema() {
		return( optionalLookupDefSchema );
	}
	@Override
	public void setOptionalLookupDefSchema(ICFBamSchemaDef argObj) {
		if(argObj == null) {
			optionalLookupDefSchema = null;
		}
		else if (argObj instanceof CFBamJpaSchemaDef) {
			optionalLookupDefSchema = (CFBamJpaSchemaDef)argObj;
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "setLookupDefSchema", "argObj", argObj, "CFBamJpaSchemaDef");
		}
	}

	@Override
	public void setOptionalLookupDefSchema(CFLibDbKeyHash256 argDefSchemaId) {
		ICFBamSchema targetBackingSchema = ICFBamSchema.getBackingCFBam();
		if (targetBackingSchema == null) {
			throw new CFLibNullArgumentException(getClass(), "setOptionalLookupDefSchema", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamSchemaDefTable targetTable = targetBackingSchema.getTableSchemaDef();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setOptionalLookupDefSchema", 0, "ICFBamSchema.getBackingCFBam().getTableSchemaDef()");
		}
		ICFBamSchemaDef targetRec = targetTable.readDerived(null, argDefSchemaId);
		setOptionalLookupDefSchema(targetRec);
	}

	@Override
	public ICFBamEnumTag getOptionalLookupPrev() {
		return( optionalLookupPrev );
	}
	@Override
	public void setOptionalLookupPrev(ICFBamEnumTag argObj) {
		if(argObj == null) {
			optionalLookupPrev = null;
		}
		else if (argObj instanceof CFBamJpaEnumTag) {
			optionalLookupPrev = (CFBamJpaEnumTag)argObj;
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "setLookupPrev", "argObj", argObj, "CFBamJpaEnumTag");
		}
	}

	@Override
	public void setOptionalLookupPrev(CFLibDbKeyHash256 argPrevId) {
		ICFBamSchema targetBackingSchema = ICFBamSchema.getBackingCFBam();
		if (targetBackingSchema == null) {
			throw new CFLibNullArgumentException(getClass(), "setOptionalLookupPrev", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamEnumTagTable targetTable = targetBackingSchema.getTableEnumTag();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setOptionalLookupPrev", 0, "ICFBamSchema.getBackingCFBam().getTableEnumTag()");
		}
		ICFBamEnumTag targetRec = targetTable.readDerived(null, argPrevId);
		setOptionalLookupPrev(targetRec);
	}

	@Override
	public ICFBamEnumTag getOptionalLookupNext() {
		return( optionalLookupNext );
	}
	@Override
	public void setOptionalLookupNext(ICFBamEnumTag argObj) {
		if(argObj == null) {
			optionalLookupNext = null;
		}
		else if (argObj instanceof CFBamJpaEnumTag) {
			optionalLookupNext = (CFBamJpaEnumTag)argObj;
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "setLookupNext", "argObj", argObj, "CFBamJpaEnumTag");
		}
	}

	@Override
	public void setOptionalLookupNext(CFLibDbKeyHash256 argNextId) {
		ICFBamSchema targetBackingSchema = ICFBamSchema.getBackingCFBam();
		if (targetBackingSchema == null) {
			throw new CFLibNullArgumentException(getClass(), "setOptionalLookupNext", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamEnumTagTable targetTable = targetBackingSchema.getTableEnumTag();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setOptionalLookupNext", 0, "ICFBamSchema.getBackingCFBam().getTableEnumTag()");
		}
		ICFBamEnumTag targetRec = targetTable.readDerived(null, argNextId);
		setOptionalLookupNext(targetRec);
	}

	@Override
	public CFLibDbKeyHash256 getCreatedByUserId() {
		return( createdByUserId );
	}

	@Override
	public void setCreatedByUserId( CFLibDbKeyHash256 value ) {
		if (value == null || value.isNull()) {
			throw new CFLibNullArgumentException(getClass(), "setCreatedByUserId", 1, "value");
		}
		createdByUserId = value;
	}

	@Override
	public LocalDateTime getCreatedAt() {
		return( createdAt );
	}

	@Override
	public void setCreatedAt( LocalDateTime value ) {
		if (value == null) {
			throw new CFLibNullArgumentException(getClass(), "setCreatedAt", 1, "value");
		}
		createdAt = value;
	}

	@Override
	public CFLibDbKeyHash256 getUpdatedByUserId() {
		return( updatedByUserId );
	}

	@Override
	public void setUpdatedByUserId( CFLibDbKeyHash256 value ) {
		if (value == null || value.isNull()) {
			throw new CFLibNullArgumentException(getClass(), "setUpdatedByUserId", 1, "value");
		}
		updatedByUserId = value;
	}

	@Override
	public LocalDateTime getUpdatedAt() {
		return( updatedAt );
	}

	@Override
	public void setUpdatedAt( LocalDateTime value ) {
		if (value == null) {
			throw new CFLibNullArgumentException(getClass(), "setUpdatedAt", 1, "value");
		}
		updatedAt = value;
	}

	@Override
	public CFLibDbKeyHash256 getPKey() {
		return getRequiredId();
	}

	@Override
	public void setPKey(CFLibDbKeyHash256 requiredId) {
		if (requiredId != null) {
			setRequiredId(requiredId);
		}
	}
	
	@Override
	public CFLibDbKeyHash256 getRequiredId() {
		return( requiredId );
	}

	@Override
	public void setRequiredId( CFLibDbKeyHash256 value ) {
		if( value == null || value.isNull() ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredId",
				1,
				"value" );
		}
		requiredId = value;
	}

	
	@Override
	public int getRequiredRevision() {
		return( requiredRevision );
	}

	@Override
	public void setRequiredRevision( int value ) {
		requiredRevision = value;
	}

	@Override
	public CFLibDbKeyHash256 getOptionalDefSchemaId() {
		ICFBamSchemaDef result = getOptionalLookupDefSchema();
		if (result != null) {
			return result.getRequiredId();
		}
		else {
			return null;
		}
	}

	@Override
	public CFLibDbKeyHash256 getRequiredEnumId() {
		ICFBamEnumDef result = getRequiredContainerEnumDef();
		if (result != null) {
			return result.getRequiredId();
		}
		else {
			return( ICFBamEnumDef.ID_INIT_VALUE );
		}
	}

	@Override
	public Short getOptionalEnumCode() {
		return( optionalEnumCode );
	}

	@Override
	public void setOptionalEnumCode( Short value ) {
		if( value < ICFBamEnumTag.ENUMCODE_MIN_VALUE ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				"setOptionalEnumCode",
				1,
				"value",
				value,
				ICFBamEnumTag.ENUMCODE_MIN_VALUE );
		}
		else if( value > ICFBamEnumTag.ENUMCODE_MAX_VALUE ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setOptionalEnumCode",
				1,
				"value",
				value,
				ICFBamEnumTag.ENUMCODE_MAX_VALUE );
		}
		optionalEnumCode = value;
	}

	@Override
	public String getRequiredName() {
		return( requiredName );
	}

	@Override
	public void setRequiredName( String value ) {
		if( value == null ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredName",
				1,
				"value" );
		}
		else if( value.length() > 64 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setRequiredName",
				1,
				"value.length()",
				value.length(),
				64 );
		}
		requiredName = value;
	}

	@Override
	public CFLibDbKeyHash256 getOptionalPrevId() {
		ICFBamEnumTag result = getOptionalLookupPrev();
		if (result != null) {
			return result.getRequiredId();
		}
		else {
			return null;
		}
	}

	@Override
	public CFLibDbKeyHash256 getOptionalNextId() {
		ICFBamEnumTag result = getOptionalLookupNext();
		if (result != null) {
			return result.getRequiredId();
		}
		else {
			return null;
		}
	}

	@Override
	public boolean equals( Object obj ) {
		if (obj == null) {
			return( false );
		}
		else if (obj instanceof ICFBamEnumTag) {
			ICFBamEnumTag rhs = (ICFBamEnumTag)obj;
			if( ! getCreatedByUserId().equals( rhs.getCreatedByUserId() ) ) {
				return( false );
			}
			if( ! getCreatedAt().equals( rhs.getCreatedAt() ) ) {
				return( false );
			}
			if( ! getUpdatedByUserId().equals( rhs.getUpdatedByUserId() ) ) {
				return( false );
			}
			if( ! getUpdatedAt().equals( rhs.getUpdatedAt() ) ) {
				return( false );
			}
			if( getRequiredId() != null && !getRequiredId().isNull() ) {
				if( rhs.getRequiredId() != null && !rhs.getRequiredId().isNull() ) {
					if( ! getRequiredId().equals( rhs.getRequiredId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredId() != null && !getRequiredId().isNull()) {
					return( false );
				}
			}
			if( getOptionalDefSchemaId() != null && !getOptionalDefSchemaId().isNull() ) {
				if( rhs.getOptionalDefSchemaId() != null && !rhs.getOptionalDefSchemaId().isNull() ) {
					if( ! getOptionalDefSchemaId().equals( rhs.getOptionalDefSchemaId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDefSchemaId() != null && !getOptionalDefSchemaId().isNull()) {
					return( false );
				}
			}
			if( getRequiredEnumId() != null && !getRequiredEnumId().isNull() ) {
				if( rhs.getRequiredEnumId() != null && !rhs.getRequiredEnumId().isNull() ) {
					if( ! getRequiredEnumId().equals( rhs.getRequiredEnumId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredEnumId() != null && !getRequiredEnumId().isNull()) {
					return( false );
				}
			}
			if( getOptionalEnumCode() != null ) {
				if( rhs.getOptionalEnumCode() != null ) {
					if( ! getOptionalEnumCode().equals( rhs.getOptionalEnumCode() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalEnumCode() != null ) {
					return( false );
				}
			}
			if( getRequiredName() != null ) {
				if( rhs.getRequiredName() != null ) {
					if( ! getRequiredName().equals( rhs.getRequiredName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredName() != null ) {
					return( false );
				}
			}
			if( getOptionalPrevId() != null && !getOptionalPrevId().isNull() ) {
				if( rhs.getOptionalPrevId() != null && !rhs.getOptionalPrevId().isNull() ) {
					if( ! getOptionalPrevId().equals( rhs.getOptionalPrevId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalPrevId() != null && !getOptionalPrevId().isNull()) {
					return( false );
				}
			}
			if( getOptionalNextId() != null && !getOptionalNextId().isNull() ) {
				if( rhs.getOptionalNextId() != null && !rhs.getOptionalNextId().isNull() ) {
					if( ! getOptionalNextId().equals( rhs.getOptionalNextId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalNextId() != null && !getOptionalNextId().isNull()) {
					return( false );
				}
			}
			return( true );
		}
		else if (obj instanceof ICFBamEnumTagH) {
			ICFBamEnumTagH rhs = (ICFBamEnumTagH)obj;
			if( ! getCreatedByUserId().equals( rhs.getCreatedByUserId() ) ) {
				return( false );
			}
			if( ! getCreatedAt().equals( rhs.getCreatedAt() ) ) {
				return( false );
			}
			if( ! getUpdatedByUserId().equals( rhs.getUpdatedByUserId() ) ) {
				return( false );
			}
			if( ! getUpdatedAt().equals( rhs.getUpdatedAt() ) ) {
				return( false );
			}
			if( getRequiredId() != null && !getRequiredId().isNull() ) {
				if( rhs.getRequiredId() != null && !rhs.getRequiredId().isNull() ) {
					if( ! getRequiredId().equals( rhs.getRequiredId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredId() != null && !getRequiredId().isNull()) {
					return( false );
				}
			}
			if( getOptionalDefSchemaId() != null && !getOptionalDefSchemaId().isNull() ) {
				if( rhs.getOptionalDefSchemaId() != null && !rhs.getOptionalDefSchemaId().isNull() ) {
					if( ! getOptionalDefSchemaId().equals( rhs.getOptionalDefSchemaId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDefSchemaId() != null && !getOptionalDefSchemaId().isNull()) {
					return( false );
				}
			}
			if( getRequiredEnumId() != null && !getRequiredEnumId().isNull() ) {
				if( rhs.getRequiredEnumId() != null && !rhs.getRequiredEnumId().isNull() ) {
					if( ! getRequiredEnumId().equals( rhs.getRequiredEnumId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredEnumId() != null && !getRequiredEnumId().isNull()) {
					return( false );
				}
			}
			if( getOptionalEnumCode() != null ) {
				if( rhs.getOptionalEnumCode() != null ) {
					if( ! getOptionalEnumCode().equals( rhs.getOptionalEnumCode() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalEnumCode() != null ) {
					return( false );
				}
			}
			if( getRequiredName() != null ) {
				if( rhs.getRequiredName() != null ) {
					if( ! getRequiredName().equals( rhs.getRequiredName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredName() != null ) {
					return( false );
				}
			}
			if( getOptionalPrevId() != null && !getOptionalPrevId().isNull() ) {
				if( rhs.getOptionalPrevId() != null && !rhs.getOptionalPrevId().isNull() ) {
					if( ! getOptionalPrevId().equals( rhs.getOptionalPrevId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalPrevId() != null && !getOptionalPrevId().isNull()) {
					return( false );
				}
			}
			if( getOptionalNextId() != null && !getOptionalNextId().isNull() ) {
				if( rhs.getOptionalNextId() != null && !rhs.getOptionalNextId().isNull() ) {
					if( ! getOptionalNextId().equals( rhs.getOptionalNextId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalNextId() != null && !getOptionalNextId().isNull()) {
					return( false );
				}
			}
			return( true );
		}
		else if (obj instanceof ICFBamEnumTagHPKey) {
			ICFBamEnumTagHPKey rhs = (ICFBamEnumTagHPKey)obj;
			if( getRequiredId() != null && !getRequiredId().isNull() ) {
				if( rhs.getRequiredId() != null && !rhs.getRequiredId().isNull() ) {
					if( ! getRequiredId().equals( rhs.getRequiredId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredId() != null && !getRequiredId().isNull()) {
					return( false );
				}
			}
			return( true );
		}
		else if (obj instanceof ICFBamEnumTagByEnumIdxKey) {
			ICFBamEnumTagByEnumIdxKey rhs = (ICFBamEnumTagByEnumIdxKey)obj;
			if( getRequiredEnumId() != null && !getRequiredEnumId().isNull() ) {
				if( rhs.getRequiredEnumId() != null && !rhs.getRequiredEnumId().isNull() ) {
					if( ! getRequiredEnumId().equals( rhs.getRequiredEnumId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredEnumId() != null && !getRequiredEnumId().isNull()) {
					return( false );
				}
			}
			return( true );
		}
		else if (obj instanceof ICFBamEnumTagByDefSchemaIdxKey) {
			ICFBamEnumTagByDefSchemaIdxKey rhs = (ICFBamEnumTagByDefSchemaIdxKey)obj;
			if( getOptionalDefSchemaId() != null && !getOptionalDefSchemaId().isNull() ) {
				if( rhs.getOptionalDefSchemaId() != null && !rhs.getOptionalDefSchemaId().isNull() ) {
					if( ! getOptionalDefSchemaId().equals( rhs.getOptionalDefSchemaId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDefSchemaId() != null && !getOptionalDefSchemaId().isNull()) {
					return( false );
				}
			}
			return( true );
		}
		else if (obj instanceof ICFBamEnumTagByEnumNameIdxKey) {
			ICFBamEnumTagByEnumNameIdxKey rhs = (ICFBamEnumTagByEnumNameIdxKey)obj;
			if( getRequiredEnumId() != null && !getRequiredEnumId().isNull() ) {
				if( rhs.getRequiredEnumId() != null && !rhs.getRequiredEnumId().isNull() ) {
					if( ! getRequiredEnumId().equals( rhs.getRequiredEnumId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredEnumId() != null && !getRequiredEnumId().isNull()) {
					return( false );
				}
			}
			if( getRequiredName() != null ) {
				if( rhs.getRequiredName() != null ) {
					if( ! getRequiredName().equals( rhs.getRequiredName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredName() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if (obj instanceof ICFBamEnumTagByPrevIdxKey) {
			ICFBamEnumTagByPrevIdxKey rhs = (ICFBamEnumTagByPrevIdxKey)obj;
			if( getOptionalPrevId() != null && !getOptionalPrevId().isNull() ) {
				if( rhs.getOptionalPrevId() != null && !rhs.getOptionalPrevId().isNull() ) {
					if( ! getOptionalPrevId().equals( rhs.getOptionalPrevId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalPrevId() != null && !getOptionalPrevId().isNull()) {
					return( false );
				}
			}
			return( true );
		}
		else if (obj instanceof ICFBamEnumTagByNextIdxKey) {
			ICFBamEnumTagByNextIdxKey rhs = (ICFBamEnumTagByNextIdxKey)obj;
			if( getOptionalNextId() != null && !getOptionalNextId().isNull() ) {
				if( rhs.getOptionalNextId() != null && !rhs.getOptionalNextId().isNull() ) {
					if( ! getOptionalNextId().equals( rhs.getOptionalNextId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalNextId() != null && !getOptionalNextId().isNull()) {
					return( false );
				}
			}
			return( true );
		}
		else {
			return( false );
		}
	}
	
	@Override
	public int hashCode() {
		int hashCode = getPKey().hashCode();
		hashCode = hashCode + getCreatedByUserId().hashCode();
		hashCode = hashCode + getCreatedAt().hashCode();
		hashCode = hashCode + getUpdatedByUserId().hashCode();
		hashCode = hashCode + getUpdatedAt().hashCode();
		hashCode = hashCode + getRequiredId().hashCode();
		if( getOptionalDefSchemaId() != null ) {
			hashCode = hashCode + getOptionalDefSchemaId().hashCode();
		}
		hashCode = hashCode + getRequiredEnumId().hashCode();
		if( getOptionalEnumCode() != null ) {
			hashCode = ( hashCode * 0x10000 ) + getOptionalEnumCode();
		}
		else {
			hashCode = (hashCode * 0x10000 );
		}
		if( getRequiredName() != null ) {
			hashCode = hashCode + getRequiredName().hashCode();
		}
		if( getOptionalPrevId() != null ) {
			hashCode = hashCode + getOptionalPrevId().hashCode();
		}
		if( getOptionalNextId() != null ) {
			hashCode = hashCode + getOptionalNextId().hashCode();
		}
		return( hashCode & 0x7fffffff );
	}

	@Override
	public int compareTo( Object obj ) {
		int cmp;
		if (obj == null) {
			return( 1 );
		}
		else if (obj instanceof ICFBamEnumTag) {
			ICFBamEnumTag rhs = (ICFBamEnumTag)obj;
			if (getPKey() == null) {
				if (rhs.getPKey() != null) {
					return( -1 );
				}
			}
			else {
				if (rhs.getPKey() == null) {
					return( 1 );
				}
				else {
					cmp = getPKey().compareTo(rhs.getPKey());
					if (cmp != 0) {
						return( cmp );
					}
				}
			}
			cmp = getCreatedByUserId().compareTo( rhs.getCreatedByUserId() );
			if( cmp != 0 ) {
				return( cmp );
			}
			cmp = getCreatedAt().compareTo( rhs.getCreatedAt() );
			if( cmp != 0 ) {
				return( cmp );
			}
			cmp = getUpdatedByUserId().compareTo( rhs.getUpdatedByUserId() );
			if( cmp != 0 ) {
				return( cmp );
			}
			cmp = getUpdatedAt().compareTo( rhs.getUpdatedAt() );
			if( cmp != 0 ) {
				return( cmp );
			}
			if( getOptionalDefSchemaId() != null ) {
				if( rhs.getOptionalDefSchemaId() != null ) {
					cmp = getOptionalDefSchemaId().compareTo( rhs.getOptionalDefSchemaId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDefSchemaId() != null ) {
					return( -1 );
				}
			}
			if (getRequiredEnumId() != null) {
				if (rhs.getRequiredEnumId() != null) {
					cmp = getRequiredEnumId().compareTo( rhs.getRequiredEnumId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredEnumId() != null) {
				return( -1 );
			}
			if( getOptionalEnumCode() != null ) {
				Short lhsEnumCode = getOptionalEnumCode();
				if( rhs.getOptionalEnumCode() != null ) {
					Short rhsEnumCode = rhs.getOptionalEnumCode();
					cmp = lhsEnumCode.compareTo( rhsEnumCode );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalEnumCode() != null ) {
					return( -1 );
				}
			}
			if (getRequiredName() != null) {
				if (rhs.getRequiredName() != null) {
					cmp = getRequiredName().compareTo( rhs.getRequiredName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredName() != null) {
				return( -1 );
			}
			if( getOptionalPrevId() != null ) {
				if( rhs.getOptionalPrevId() != null ) {
					cmp = getOptionalPrevId().compareTo( rhs.getOptionalPrevId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalPrevId() != null ) {
					return( -1 );
				}
			}
			if( getOptionalNextId() != null ) {
				if( rhs.getOptionalNextId() != null ) {
					cmp = getOptionalNextId().compareTo( rhs.getOptionalNextId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalNextId() != null ) {
					return( -1 );
				}
			}
			return( 0 );
		}
		else if (obj instanceof ICFBamEnumTagHPKey) {
			ICFBamEnumTagHPKey rhs = (ICFBamEnumTagHPKey)obj;
			if (getRequiredId() != null) {
				if (rhs.getRequiredId() != null) {
					cmp = getRequiredId().compareTo( rhs.getRequiredId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFBamEnumTagH ) {
			ICFBamEnumTagH rhs = (ICFBamEnumTagH)obj;
			if (getRequiredId() != null) {
				if (rhs.getRequiredId() != null) {
					cmp = getRequiredId().compareTo( rhs.getRequiredId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredId() != null) {
				return( -1 );
			}
			cmp = getCreatedByUserId().compareTo( rhs.getCreatedByUserId() );
			if( cmp != 0 ) {
				return( cmp );
			}
			cmp = getCreatedAt().compareTo( rhs.getCreatedAt() );
			if( cmp != 0 ) {
				return( cmp );
			}
			cmp = getUpdatedByUserId().compareTo( rhs.getUpdatedByUserId() );
			if( cmp != 0 ) {
				return( cmp );
			}
			cmp = getUpdatedAt().compareTo( rhs.getUpdatedAt() );
			if( cmp != 0 ) {
				return( cmp );
			}
			if( getOptionalDefSchemaId() != null ) {
				if( rhs.getOptionalDefSchemaId() != null ) {
					cmp = getOptionalDefSchemaId().compareTo( rhs.getOptionalDefSchemaId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDefSchemaId() != null ) {
					return( -1 );
				}
			}
			if (getRequiredEnumId() != null) {
				if (rhs.getRequiredEnumId() != null) {
					cmp = getRequiredEnumId().compareTo( rhs.getRequiredEnumId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredEnumId() != null) {
				return( -1 );
			}
			if( getOptionalEnumCode() != null ) {
				Short lhsEnumCode = getOptionalEnumCode();
				if( rhs.getOptionalEnumCode() != null ) {
					Short rhsEnumCode = rhs.getOptionalEnumCode();
					cmp = lhsEnumCode.compareTo( rhsEnumCode );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalEnumCode() != null ) {
					return( -1 );
				}
			}
			if (getRequiredName() != null) {
				if (rhs.getRequiredName() != null) {
					cmp = getRequiredName().compareTo( rhs.getRequiredName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredName() != null) {
				return( -1 );
			}
			if( getOptionalPrevId() != null ) {
				if( rhs.getOptionalPrevId() != null ) {
					cmp = getOptionalPrevId().compareTo( rhs.getOptionalPrevId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalPrevId() != null ) {
					return( -1 );
				}
			}
			if( getOptionalNextId() != null ) {
				if( rhs.getOptionalNextId() != null ) {
					cmp = getOptionalNextId().compareTo( rhs.getOptionalNextId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalNextId() != null ) {
					return( -1 );
				}
			}
			return( 0 );
		}
		else if (obj instanceof ICFBamEnumTagByEnumIdxKey) {
			ICFBamEnumTagByEnumIdxKey rhs = (ICFBamEnumTagByEnumIdxKey)obj;
			if (getRequiredEnumId() != null) {
				if (rhs.getRequiredEnumId() != null) {
					cmp = getRequiredEnumId().compareTo( rhs.getRequiredEnumId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredEnumId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if (obj instanceof ICFBamEnumTagByDefSchemaIdxKey) {
			ICFBamEnumTagByDefSchemaIdxKey rhs = (ICFBamEnumTagByDefSchemaIdxKey)obj;
			if( getOptionalDefSchemaId() != null ) {
				if( rhs.getOptionalDefSchemaId() != null ) {
					cmp = getOptionalDefSchemaId().compareTo( rhs.getOptionalDefSchemaId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDefSchemaId() != null ) {
					return( -1 );
				}
			}
			return( 0 );
		}
		else if (obj instanceof ICFBamEnumTagByEnumNameIdxKey) {
			ICFBamEnumTagByEnumNameIdxKey rhs = (ICFBamEnumTagByEnumNameIdxKey)obj;
			if (getRequiredEnumId() != null) {
				if (rhs.getRequiredEnumId() != null) {
					cmp = getRequiredEnumId().compareTo( rhs.getRequiredEnumId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredEnumId() != null) {
				return( -1 );
			}
			if (getRequiredName() != null) {
				if (rhs.getRequiredName() != null) {
					cmp = getRequiredName().compareTo( rhs.getRequiredName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredName() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if (obj instanceof ICFBamEnumTagByPrevIdxKey) {
			ICFBamEnumTagByPrevIdxKey rhs = (ICFBamEnumTagByPrevIdxKey)obj;
			if( getOptionalPrevId() != null ) {
				if( rhs.getOptionalPrevId() != null ) {
					cmp = getOptionalPrevId().compareTo( rhs.getOptionalPrevId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalPrevId() != null ) {
					return( -1 );
				}
			}
			return( 0 );
		}
		else if (obj instanceof ICFBamEnumTagByNextIdxKey) {
			ICFBamEnumTagByNextIdxKey rhs = (ICFBamEnumTagByNextIdxKey)obj;
			if( getOptionalNextId() != null ) {
				if( rhs.getOptionalNextId() != null ) {
					cmp = getOptionalNextId().compareTo( rhs.getOptionalNextId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalNextId() != null ) {
					return( -1 );
				}
			}
			return( 0 );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"obj",
				obj,
				null );
		}
	}

	@Override
	public void set( ICFBamEnumTag src ) {
		setEnumTag( src );
	}

	@Override
	public void setEnumTag( ICFBamEnumTag src ) {
		setRequiredId(src.getRequiredId());
		setRequiredRevision( src.getRequiredRevision() );
		setCreatedByUserId( src.getCreatedByUserId() );
		setCreatedAt( src.getCreatedAt() );
		setUpdatedByUserId( src.getUpdatedByUserId() );
		setUpdatedAt( src.getUpdatedAt() );
		setRequiredContainerEnumDef(src.getRequiredContainerEnumDef());
		setOptionalLookupDefSchema(src.getOptionalLookupDefSchema());
		setOptionalLookupPrev(src.getOptionalLookupPrev());
		setOptionalLookupNext(src.getOptionalLookupNext());
		setOptionalEnumCode(src.getOptionalEnumCode());
		setRequiredName(src.getRequiredName());
	}

	@Override
	public void set( ICFBamEnumTagH src ) {
		setEnumTag( src );
	}

	@Override
	public void setEnumTag( ICFBamEnumTagH src ) {
		setRequiredId(src.getRequiredId());
		setRequiredContainerEnumDef(src.getRequiredEnumId());
		setOptionalLookupDefSchema(src.getOptionalDefSchemaId());
		setOptionalLookupPrev(src.getOptionalPrevId());
		setOptionalLookupNext(src.getOptionalNextId());
		setOptionalEnumCode(src.getOptionalEnumCode());
		setRequiredName(src.getRequiredName());
	}

	@Override
	public String getXmlAttrFragment() {
		String ret = ""
			+ " RequiredId=" + "\"" + getRequiredId().toString() + "\""
			+ " RequiredRevision=\"" + Integer.toString( getRequiredRevision() ) + "\""
			+ " RequiredId=" + "\"" + getRequiredId().toString() + "\""
			+ " OptionalDefSchemaId=" + ( ( getOptionalDefSchemaId() == null ) ? "null" : "\"" + getOptionalDefSchemaId().toString() + "\"" )
			+ " RequiredEnumId=" + "\"" + getRequiredEnumId().toString() + "\""
			+ " OptionalEnumCode=" + ( ( getOptionalEnumCode() == null ) ? "null" : "\"" + getOptionalEnumCode().toString() + "\"" )
			+ " RequiredName=" + "\"" + StringEscapeUtils.escapeXml11( getRequiredName() ) + "\""
			+ " OptionalPrevId=" + ( ( getOptionalPrevId() == null ) ? "null" : "\"" + getOptionalPrevId().toString() + "\"" )
			+ " OptionalNextId=" + ( ( getOptionalNextId() == null ) ? "null" : "\"" + getOptionalNextId().toString() + "\"" );
		return( ret );
	}

	@Override
	public String toString() {
		String ret = "<CFBamJpaEnumTag" + getXmlAttrFragment() + "/>";
		return( ret );
	}
}
