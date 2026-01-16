// Description: Java 25 JPA implementation of a SchemaRef entity definition object.

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
	name = "schema_ref", schema = "CFBam31",
	indexes = {
		@Index(name = "SchemaRefIdIdx", columnList = "Id", unique = true),
		@Index(name = "SchemaRefSchemaIdx", columnList = "SchemaId", unique = false),
		@Index(name = "SchemaRefUNameIdx", columnList = "SchemaId, safe_name", unique = true),
		@Index(name = "SchemaRefRefSchemaIdx", columnList = "RefSchId", unique = false),
		@Index(name = "SchemaRefPrevIdx", columnList = "PrevId", unique = false),
		@Index(name = "SchemaRefNextIdx", columnList = "NextId", unique = false)
	}
)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("43012")
@Transactional(Transactional.TxType.SUPPORTS)
@PersistenceContext(unitName = "CFBamPU")
public class CFBamJpaSchemaRef extends CFBamJpaScope
	implements ICFBamSchemaRef
{
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn( name="SchemaId" )
	protected CFBamJpaSchemaDef requiredContainerSchema;
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn( name="RefSchId" )
	protected CFBamJpaSchemaDef optionalLookupRefSchema;
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn( name="PrevId" )
	protected CFBamJpaSchemaRef optionalLookupPrev;
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn( name="NextId" )
	protected CFBamJpaSchemaRef optionalLookupNext;

	@Column( name="safe_name", nullable=false, length=192 )
	protected String requiredName;
	@Column( name="RefModelName", nullable=false, length=1024 )
	protected String requiredRefModelName;
	@Column( name="IncludeRoot", nullable=false, length=1024 )
	protected String requiredIncludeRoot;

	public CFBamJpaSchemaRef() {
		super();
		requiredName = ICFBamSchemaRef.NAME_INIT_VALUE;
		requiredRefModelName = ICFBamSchemaRef.REFMODELNAME_INIT_VALUE;
		requiredIncludeRoot = ICFBamSchemaRef.INCLUDEROOT_INIT_VALUE;
	}

	@Override
	public int getClassCode() {
		return( ICFBamSchemaRef.CLASS_CODE );
	}

	@Override
	public ICFBamSchemaDef getRequiredContainerSchema() {
		return( requiredContainerSchema );
	}
	@Override
	public void setRequiredContainerSchema(ICFBamSchemaDef argObj) {
		if(argObj == null) {
			throw new CFLibNullArgumentException(getClass(), "setContainerSchema", 1, "argObj");
		}
		else if (argObj instanceof CFBamJpaSchemaDef) {
			requiredContainerSchema = (CFBamJpaSchemaDef)argObj;
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "setContainerSchema", "argObj", argObj, "CFBamJpaSchemaDef");
		}
	}

	@Override
	public void setRequiredContainerSchema(CFLibDbKeyHash256 argSchemaId) {
		ICFBamSchema targetBackingSchema = ICFBamSchema.getBackingCFBam();
		if (targetBackingSchema == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerSchema", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamSchemaDefTable targetTable = targetBackingSchema.getTableSchemaDef();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerSchema", 0, "ICFBamSchema.getBackingCFBam().getTableSchemaDef()");
		}
		ICFBamSchemaDef targetRec = targetTable.readDerived(null, argSchemaId);
		setRequiredContainerSchema(targetRec);
	}

	@Override
	public ICFBamSchemaDef getOptionalLookupRefSchema() {
		return( optionalLookupRefSchema );
	}
	@Override
	public void setOptionalLookupRefSchema(ICFBamSchemaDef argObj) {
		if(argObj == null) {
			optionalLookupRefSchema = null;
		}
		else if (argObj instanceof CFBamJpaSchemaDef) {
			optionalLookupRefSchema = (CFBamJpaSchemaDef)argObj;
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "setLookupRefSchema", "argObj", argObj, "CFBamJpaSchemaDef");
		}
	}

	@Override
	public void setOptionalLookupRefSchema(CFLibDbKeyHash256 argRefSchemaId) {
		ICFBamSchema targetBackingSchema = ICFBamSchema.getBackingCFBam();
		if (targetBackingSchema == null) {
			throw new CFLibNullArgumentException(getClass(), "setOptionalLookupRefSchema", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamSchemaDefTable targetTable = targetBackingSchema.getTableSchemaDef();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setOptionalLookupRefSchema", 0, "ICFBamSchema.getBackingCFBam().getTableSchemaDef()");
		}
		ICFBamSchemaDef targetRec = targetTable.readDerived(null, argRefSchemaId);
		setOptionalLookupRefSchema(targetRec);
	}

	@Override
	public ICFBamSchemaRef getOptionalLookupPrev() {
		return( optionalLookupPrev );
	}
	@Override
	public void setOptionalLookupPrev(ICFBamSchemaRef argObj) {
		if(argObj == null) {
			optionalLookupPrev = null;
		}
		else if (argObj instanceof CFBamJpaSchemaRef) {
			optionalLookupPrev = (CFBamJpaSchemaRef)argObj;
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "setLookupPrev", "argObj", argObj, "CFBamJpaSchemaRef");
		}
	}

	@Override
	public void setOptionalLookupPrev(CFLibDbKeyHash256 argPrevId) {
		ICFBamSchema targetBackingSchema = ICFBamSchema.getBackingCFBam();
		if (targetBackingSchema == null) {
			throw new CFLibNullArgumentException(getClass(), "setOptionalLookupPrev", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamSchemaRefTable targetTable = targetBackingSchema.getTableSchemaRef();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setOptionalLookupPrev", 0, "ICFBamSchema.getBackingCFBam().getTableSchemaRef()");
		}
		ICFBamSchemaRef targetRec = targetTable.readDerived(null, argPrevId);
		setOptionalLookupPrev(targetRec);
	}

	@Override
	public ICFBamSchemaRef getOptionalLookupNext() {
		return( optionalLookupNext );
	}
	@Override
	public void setOptionalLookupNext(ICFBamSchemaRef argObj) {
		if(argObj == null) {
			optionalLookupNext = null;
		}
		else if (argObj instanceof CFBamJpaSchemaRef) {
			optionalLookupNext = (CFBamJpaSchemaRef)argObj;
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "setLookupNext", "argObj", argObj, "CFBamJpaSchemaRef");
		}
	}

	@Override
	public void setOptionalLookupNext(CFLibDbKeyHash256 argNextId) {
		ICFBamSchema targetBackingSchema = ICFBamSchema.getBackingCFBam();
		if (targetBackingSchema == null) {
			throw new CFLibNullArgumentException(getClass(), "setOptionalLookupNext", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamSchemaRefTable targetTable = targetBackingSchema.getTableSchemaRef();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setOptionalLookupNext", 0, "ICFBamSchema.getBackingCFBam().getTableSchemaRef()");
		}
		ICFBamSchemaRef targetRec = targetTable.readDerived(null, argNextId);
		setOptionalLookupNext(targetRec);
	}

	@Override
	public CFLibDbKeyHash256 getRequiredSchemaId() {
		ICFBamSchemaDef result = getRequiredContainerSchema();
		if (result != null) {
			return result.getRequiredId();
		}
		else {
			return( ICFBamSchemaDef.ID_INIT_VALUE );
		}
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
		else if( value.length() > 192 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setRequiredName",
				1,
				"value.length()",
				value.length(),
				192 );
		}
		requiredName = value;
	}

	@Override
	public String getRequiredRefModelName() {
		return( requiredRefModelName );
	}

	@Override
	public void setRequiredRefModelName( String value ) {
		if( value == null ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredRefModelName",
				1,
				"value" );
		}
		else if( value.length() > 1024 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setRequiredRefModelName",
				1,
				"value.length()",
				value.length(),
				1024 );
		}
		requiredRefModelName = value;
	}

	@Override
	public String getRequiredIncludeRoot() {
		return( requiredIncludeRoot );
	}

	@Override
	public void setRequiredIncludeRoot( String value ) {
		if( value == null ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredIncludeRoot",
				1,
				"value" );
		}
		else if( value.length() > 1024 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setRequiredIncludeRoot",
				1,
				"value.length()",
				value.length(),
				1024 );
		}
		requiredIncludeRoot = value;
	}

	@Override
	public CFLibDbKeyHash256 getOptionalRefSchemaId() {
		ICFBamSchemaDef result = getOptionalLookupRefSchema();
		if (result != null) {
			return result.getRequiredId();
		}
		else {
			return null;
		}
	}

	@Override
	public CFLibDbKeyHash256 getOptionalPrevId() {
		ICFBamSchemaRef result = getOptionalLookupPrev();
		if (result != null) {
			return result.getRequiredId();
		}
		else {
			return null;
		}
	}

	@Override
	public CFLibDbKeyHash256 getOptionalNextId() {
		ICFBamSchemaRef result = getOptionalLookupNext();
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
		else if (obj instanceof ICFBamSchemaRef) {
			if (!super.equals(obj)) {
				return( false );
			}
			ICFBamSchemaRef rhs = (ICFBamSchemaRef)obj;
			if( getRequiredSchemaId() != null && !getRequiredSchemaId().isNull() ) {
				if( rhs.getRequiredSchemaId() != null && !rhs.getRequiredSchemaId().isNull() ) {
					if( ! getRequiredSchemaId().equals( rhs.getRequiredSchemaId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSchemaId() != null && !getRequiredSchemaId().isNull()) {
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
			if( getRequiredRefModelName() != null ) {
				if( rhs.getRequiredRefModelName() != null ) {
					if( ! getRequiredRefModelName().equals( rhs.getRequiredRefModelName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredRefModelName() != null ) {
					return( false );
				}
			}
			if( getRequiredIncludeRoot() != null ) {
				if( rhs.getRequiredIncludeRoot() != null ) {
					if( ! getRequiredIncludeRoot().equals( rhs.getRequiredIncludeRoot() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredIncludeRoot() != null ) {
					return( false );
				}
			}
			if( getOptionalRefSchemaId() != null && !getOptionalRefSchemaId().isNull() ) {
				if( rhs.getOptionalRefSchemaId() != null && !rhs.getOptionalRefSchemaId().isNull() ) {
					if( ! getOptionalRefSchemaId().equals( rhs.getOptionalRefSchemaId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalRefSchemaId() != null && !getOptionalRefSchemaId().isNull()) {
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
		else if (obj instanceof ICFBamSchemaRefH) {
			if (!super.equals(obj)) {
				return( false );
			}
			ICFBamSchemaRefH rhs = (ICFBamSchemaRefH)obj;
			if( getRequiredSchemaId() != null && !getRequiredSchemaId().isNull() ) {
				if( rhs.getRequiredSchemaId() != null && !rhs.getRequiredSchemaId().isNull() ) {
					if( ! getRequiredSchemaId().equals( rhs.getRequiredSchemaId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSchemaId() != null && !getRequiredSchemaId().isNull()) {
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
			if( getRequiredRefModelName() != null ) {
				if( rhs.getRequiredRefModelName() != null ) {
					if( ! getRequiredRefModelName().equals( rhs.getRequiredRefModelName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredRefModelName() != null ) {
					return( false );
				}
			}
			if( getRequiredIncludeRoot() != null ) {
				if( rhs.getRequiredIncludeRoot() != null ) {
					if( ! getRequiredIncludeRoot().equals( rhs.getRequiredIncludeRoot() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredIncludeRoot() != null ) {
					return( false );
				}
			}
			if( getOptionalRefSchemaId() != null && !getOptionalRefSchemaId().isNull() ) {
				if( rhs.getOptionalRefSchemaId() != null && !rhs.getOptionalRefSchemaId().isNull() ) {
					if( ! getOptionalRefSchemaId().equals( rhs.getOptionalRefSchemaId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalRefSchemaId() != null && !getOptionalRefSchemaId().isNull()) {
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
		else if (obj instanceof ICFBamScopeHPKey) {
			return( super.equals(obj) );
		}
		else if (obj instanceof ICFBamSchemaRefBySchemaIdxKey) {
			ICFBamSchemaRefBySchemaIdxKey rhs = (ICFBamSchemaRefBySchemaIdxKey)obj;
			if( getRequiredSchemaId() != null && !getRequiredSchemaId().isNull() ) {
				if( rhs.getRequiredSchemaId() != null && !rhs.getRequiredSchemaId().isNull() ) {
					if( ! getRequiredSchemaId().equals( rhs.getRequiredSchemaId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSchemaId() != null && !getRequiredSchemaId().isNull()) {
					return( false );
				}
			}
			return( true );
		}
		else if (obj instanceof ICFBamSchemaRefByUNameIdxKey) {
			ICFBamSchemaRefByUNameIdxKey rhs = (ICFBamSchemaRefByUNameIdxKey)obj;
			if( getRequiredSchemaId() != null && !getRequiredSchemaId().isNull() ) {
				if( rhs.getRequiredSchemaId() != null && !rhs.getRequiredSchemaId().isNull() ) {
					if( ! getRequiredSchemaId().equals( rhs.getRequiredSchemaId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSchemaId() != null && !getRequiredSchemaId().isNull()) {
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
		else if (obj instanceof ICFBamSchemaRefByRefSchemaIdxKey) {
			ICFBamSchemaRefByRefSchemaIdxKey rhs = (ICFBamSchemaRefByRefSchemaIdxKey)obj;
			if( getOptionalRefSchemaId() != null && !getOptionalRefSchemaId().isNull() ) {
				if( rhs.getOptionalRefSchemaId() != null && !rhs.getOptionalRefSchemaId().isNull() ) {
					if( ! getOptionalRefSchemaId().equals( rhs.getOptionalRefSchemaId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalRefSchemaId() != null && !getOptionalRefSchemaId().isNull()) {
					return( false );
				}
			}
			return( true );
		}
		else if (obj instanceof ICFBamSchemaRefByPrevIdxKey) {
			ICFBamSchemaRefByPrevIdxKey rhs = (ICFBamSchemaRefByPrevIdxKey)obj;
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
		else if (obj instanceof ICFBamSchemaRefByNextIdxKey) {
			ICFBamSchemaRefByNextIdxKey rhs = (ICFBamSchemaRefByNextIdxKey)obj;
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
			return( super.equals(obj) );
		}
	}
	
	@Override
	public int hashCode() {
		int hashCode = super.hashCode();
		hashCode = hashCode + getRequiredSchemaId().hashCode();
		if( getRequiredName() != null ) {
			hashCode = hashCode + getRequiredName().hashCode();
		}
		if( getRequiredRefModelName() != null ) {
			hashCode = hashCode + getRequiredRefModelName().hashCode();
		}
		if( getRequiredIncludeRoot() != null ) {
			hashCode = hashCode + getRequiredIncludeRoot().hashCode();
		}
		if( getOptionalRefSchemaId() != null ) {
			hashCode = hashCode + getOptionalRefSchemaId().hashCode();
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
		else if (obj instanceof ICFBamSchemaRef) {
			cmp = super.compareTo(obj);
			if (cmp != 0) {
				return( cmp );
			}
			ICFBamSchemaRef rhs = (ICFBamSchemaRef)obj;
			if (getRequiredSchemaId() != null) {
				if (rhs.getRequiredSchemaId() != null) {
					cmp = getRequiredSchemaId().compareTo( rhs.getRequiredSchemaId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSchemaId() != null) {
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
			if (getRequiredRefModelName() != null) {
				if (rhs.getRequiredRefModelName() != null) {
					cmp = getRequiredRefModelName().compareTo( rhs.getRequiredRefModelName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredRefModelName() != null) {
				return( -1 );
			}
			if (getRequiredIncludeRoot() != null) {
				if (rhs.getRequiredIncludeRoot() != null) {
					cmp = getRequiredIncludeRoot().compareTo( rhs.getRequiredIncludeRoot() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredIncludeRoot() != null) {
				return( -1 );
			}
			if( getOptionalRefSchemaId() != null ) {
				if( rhs.getOptionalRefSchemaId() != null ) {
					cmp = getOptionalRefSchemaId().compareTo( rhs.getOptionalRefSchemaId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalRefSchemaId() != null ) {
					return( -1 );
				}
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
		else if( obj instanceof ICFBamSchemaRefH ) {
			cmp = super.compareTo(obj);
			if (cmp != 0) {
				return( cmp );
			}
			ICFBamSchemaRefH rhs = (ICFBamSchemaRefH)obj;
			if (getRequiredSchemaId() != null) {
				if (rhs.getRequiredSchemaId() != null) {
					cmp = getRequiredSchemaId().compareTo( rhs.getRequiredSchemaId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSchemaId() != null) {
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
			if (getRequiredRefModelName() != null) {
				if (rhs.getRequiredRefModelName() != null) {
					cmp = getRequiredRefModelName().compareTo( rhs.getRequiredRefModelName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredRefModelName() != null) {
				return( -1 );
			}
			if (getRequiredIncludeRoot() != null) {
				if (rhs.getRequiredIncludeRoot() != null) {
					cmp = getRequiredIncludeRoot().compareTo( rhs.getRequiredIncludeRoot() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredIncludeRoot() != null) {
				return( -1 );
			}
			if( getOptionalRefSchemaId() != null ) {
				if( rhs.getOptionalRefSchemaId() != null ) {
					cmp = getOptionalRefSchemaId().compareTo( rhs.getOptionalRefSchemaId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalRefSchemaId() != null ) {
					return( -1 );
				}
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
		else if (obj instanceof ICFBamSchemaRefBySchemaIdxKey) {
			ICFBamSchemaRefBySchemaIdxKey rhs = (ICFBamSchemaRefBySchemaIdxKey)obj;
			if (getRequiredSchemaId() != null) {
				if (rhs.getRequiredSchemaId() != null) {
					cmp = getRequiredSchemaId().compareTo( rhs.getRequiredSchemaId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSchemaId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if (obj instanceof ICFBamSchemaRefByUNameIdxKey) {
			ICFBamSchemaRefByUNameIdxKey rhs = (ICFBamSchemaRefByUNameIdxKey)obj;
			if (getRequiredSchemaId() != null) {
				if (rhs.getRequiredSchemaId() != null) {
					cmp = getRequiredSchemaId().compareTo( rhs.getRequiredSchemaId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSchemaId() != null) {
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
		else if (obj instanceof ICFBamSchemaRefByRefSchemaIdxKey) {
			ICFBamSchemaRefByRefSchemaIdxKey rhs = (ICFBamSchemaRefByRefSchemaIdxKey)obj;
			if( getOptionalRefSchemaId() != null ) {
				if( rhs.getOptionalRefSchemaId() != null ) {
					cmp = getOptionalRefSchemaId().compareTo( rhs.getOptionalRefSchemaId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalRefSchemaId() != null ) {
					return( -1 );
				}
			}
			return( 0 );
		}
		else if (obj instanceof ICFBamSchemaRefByPrevIdxKey) {
			ICFBamSchemaRefByPrevIdxKey rhs = (ICFBamSchemaRefByPrevIdxKey)obj;
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
		else if (obj instanceof ICFBamSchemaRefByNextIdxKey) {
			ICFBamSchemaRefByNextIdxKey rhs = (ICFBamSchemaRefByNextIdxKey)obj;
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
			cmp = super.compareTo(obj);
			return( cmp );
		}
	}

	@Override
	public void set( ICFBamScope src ) {
		if( src instanceof ICFBamSchemaRef ) {
			setSchemaRef( (ICFBamSchemaRef)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamJpaSchemaRef" );
		}
	}

	@Override
	public void setSchemaRef( ICFBamSchemaRef src ) {
		super.setScope( src );
		setRequiredContainerSchema(src.getRequiredContainerSchema());
		setOptionalLookupRefSchema(src.getOptionalLookupRefSchema());
		setOptionalLookupPrev(src.getOptionalLookupPrev());
		setOptionalLookupNext(src.getOptionalLookupNext());
		setRequiredName(src.getRequiredName());
		setRequiredRefModelName(src.getRequiredRefModelName());
		setRequiredIncludeRoot(src.getRequiredIncludeRoot());
	}

	@Override
	public void set( ICFBamScopeH src ) {
		if( src instanceof ICFBamSchemaRefH ) {
			setSchemaRef( (ICFBamSchemaRefH)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamSchemaRefH" );
		}
	}

	@Override
	public void setSchemaRef( ICFBamSchemaRefH src ) {
		super.setScope( src );
		setRequiredContainerSchema(src.getRequiredSchemaId());
		setOptionalLookupRefSchema(src.getOptionalRefSchemaId());
		setOptionalLookupPrev(src.getOptionalPrevId());
		setOptionalLookupNext(src.getOptionalNextId());
		setRequiredName(src.getRequiredName());
		setRequiredRefModelName(src.getRequiredRefModelName());
		setRequiredIncludeRoot(src.getRequiredIncludeRoot());
	}

	@Override
	public String getXmlAttrFragment() {
		String ret = super.getXmlAttrFragment() 
			+ " RequiredId=" + "\"" + getRequiredId().toString() + "\""
			+ " RequiredSchemaId=" + "\"" + getRequiredSchemaId().toString() + "\""
			+ " RequiredName=" + "\"" + StringEscapeUtils.escapeXml11( getRequiredName() ) + "\""
			+ " RequiredRefModelName=" + "\"" + StringEscapeUtils.escapeXml11( getRequiredRefModelName() ) + "\""
			+ " RequiredIncludeRoot=" + "\"" + StringEscapeUtils.escapeXml11( getRequiredIncludeRoot() ) + "\""
			+ " OptionalRefSchemaId=" + ( ( getOptionalRefSchemaId() == null ) ? "null" : "\"" + getOptionalRefSchemaId().toString() + "\"" )
			+ " OptionalPrevId=" + ( ( getOptionalPrevId() == null ) ? "null" : "\"" + getOptionalPrevId().toString() + "\"" )
			+ " OptionalNextId=" + ( ( getOptionalNextId() == null ) ? "null" : "\"" + getOptionalNextId().toString() + "\"" );
		return( ret );
	}

	@Override
	public String toString() {
		String ret = "<CFBamJpaSchemaRef" + getXmlAttrFragment() + "/>";
		return( ret );
	}
}
