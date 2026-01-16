// Description: Java 25 JPA implementation of a ClearDep entity definition object.

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
	name = "clr_dep", schema = "CFBam31",
	indexes = {
		@Index(name = "ClearDepIdIdx", columnList = "Id", unique = true),
		@Index(name = "ClearDepRelationIdx", columnList = "RelationId", unique = false),
		@Index(name = "ClearDepDefSchemaDefIdx", columnList = "defschid", unique = false)
	}
)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("43024")
@Transactional(Transactional.TxType.SUPPORTS)
@PersistenceContext(unitName = "CFBamPU")
public class CFBamJpaClearDep extends CFBamJpaScope
	implements ICFBamClearDep
{
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn( name="RelationId" )
	protected CFBamJpaRelation requiredLookupRelation;
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn( name="defschid" )
	protected CFBamJpaSchemaDef optionalLookupDefSchema;


	public CFBamJpaClearDep() {
		super();
	}

	@Override
	public int getClassCode() {
		return( ICFBamClearDep.CLASS_CODE );
	}

	@Override
	public ICFBamRelation getRequiredLookupRelation() {
		return( requiredLookupRelation );
	}
	@Override
	public void setRequiredLookupRelation(ICFBamRelation argObj) {
		if(argObj == null) {
			throw new CFLibNullArgumentException(getClass(), "setLookupRelation", 1, "argObj");
		}
		else if (argObj instanceof CFBamJpaRelation) {
			requiredLookupRelation = (CFBamJpaRelation)argObj;
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "setLookupRelation", "argObj", argObj, "CFBamJpaRelation");
		}
	}

	@Override
	public void setRequiredLookupRelation(CFLibDbKeyHash256 argRelationId) {
		ICFBamSchema targetBackingSchema = ICFBamSchema.getBackingCFBam();
		if (targetBackingSchema == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredLookupRelation", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamRelationTable targetTable = targetBackingSchema.getTableRelation();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredLookupRelation", 0, "ICFBamSchema.getBackingCFBam().getTableRelation()");
		}
		ICFBamRelation targetRec = targetTable.readDerived(null, argRelationId);
		setRequiredLookupRelation(targetRec);
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
	public CFLibDbKeyHash256 getRequiredRelationId() {
		ICFBamRelation result = getRequiredLookupRelation();
		if (result != null) {
			return result.getRequiredId();
		}
		else {
			return( ICFBamRelation.ID_INIT_VALUE );
		}
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
	public boolean equals( Object obj ) {
		if (obj == null) {
			return( false );
		}
		else if (obj instanceof ICFBamClearDep) {
			if (!super.equals(obj)) {
				return( false );
			}
			ICFBamClearDep rhs = (ICFBamClearDep)obj;
			if( getRequiredRelationId() != null && !getRequiredRelationId().isNull() ) {
				if( rhs.getRequiredRelationId() != null && !rhs.getRequiredRelationId().isNull() ) {
					if( ! getRequiredRelationId().equals( rhs.getRequiredRelationId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredRelationId() != null && !getRequiredRelationId().isNull()) {
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
			return( true );
		}
		else if (obj instanceof ICFBamClearDepH) {
			if (!super.equals(obj)) {
				return( false );
			}
			ICFBamClearDepH rhs = (ICFBamClearDepH)obj;
			if( getRequiredRelationId() != null && !getRequiredRelationId().isNull() ) {
				if( rhs.getRequiredRelationId() != null && !rhs.getRequiredRelationId().isNull() ) {
					if( ! getRequiredRelationId().equals( rhs.getRequiredRelationId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredRelationId() != null && !getRequiredRelationId().isNull()) {
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
			return( true );
		}
		else if (obj instanceof ICFBamScopeHPKey) {
			return( super.equals(obj) );
		}
		else if (obj instanceof ICFBamClearDepByClearDepIdxKey) {
			ICFBamClearDepByClearDepIdxKey rhs = (ICFBamClearDepByClearDepIdxKey)obj;
			if( getRequiredRelationId() != null && !getRequiredRelationId().isNull() ) {
				if( rhs.getRequiredRelationId() != null && !rhs.getRequiredRelationId().isNull() ) {
					if( ! getRequiredRelationId().equals( rhs.getRequiredRelationId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredRelationId() != null && !getRequiredRelationId().isNull()) {
					return( false );
				}
			}
			return( true );
		}
		else if (obj instanceof ICFBamClearDepByDefSchemaIdxKey) {
			ICFBamClearDepByDefSchemaIdxKey rhs = (ICFBamClearDepByDefSchemaIdxKey)obj;
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
		else {
			return( super.equals(obj) );
		}
	}
	
	@Override
	public int hashCode() {
		int hashCode = super.hashCode();
		hashCode = hashCode + getRequiredRelationId().hashCode();
		if( getOptionalDefSchemaId() != null ) {
			hashCode = hashCode + getOptionalDefSchemaId().hashCode();
		}
		return( hashCode & 0x7fffffff );
	}

	@Override
	public int compareTo( Object obj ) {
		int cmp;
		if (obj == null) {
			return( 1 );
		}
		else if (obj instanceof ICFBamClearDep) {
			cmp = super.compareTo(obj);
			if (cmp != 0) {
				return( cmp );
			}
			ICFBamClearDep rhs = (ICFBamClearDep)obj;
			if (getRequiredRelationId() != null) {
				if (rhs.getRequiredRelationId() != null) {
					cmp = getRequiredRelationId().compareTo( rhs.getRequiredRelationId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredRelationId() != null) {
				return( -1 );
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
			return( 0 );
		}
		else if( obj instanceof ICFBamClearDepH ) {
			cmp = super.compareTo(obj);
			if (cmp != 0) {
				return( cmp );
			}
			ICFBamClearDepH rhs = (ICFBamClearDepH)obj;
			if (getRequiredRelationId() != null) {
				if (rhs.getRequiredRelationId() != null) {
					cmp = getRequiredRelationId().compareTo( rhs.getRequiredRelationId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredRelationId() != null) {
				return( -1 );
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
			return( 0 );
		}
		else if (obj instanceof ICFBamClearDepByClearDepIdxKey) {
			ICFBamClearDepByClearDepIdxKey rhs = (ICFBamClearDepByClearDepIdxKey)obj;
			if (getRequiredRelationId() != null) {
				if (rhs.getRequiredRelationId() != null) {
					cmp = getRequiredRelationId().compareTo( rhs.getRequiredRelationId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredRelationId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if (obj instanceof ICFBamClearDepByDefSchemaIdxKey) {
			ICFBamClearDepByDefSchemaIdxKey rhs = (ICFBamClearDepByDefSchemaIdxKey)obj;
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
		else {
			cmp = super.compareTo(obj);
			return( cmp );
		}
	}

	@Override
	public void set( ICFBamScope src ) {
		if( src instanceof ICFBamClearDep ) {
			setClearDep( (ICFBamClearDep)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamJpaClearDep" );
		}
	}

	@Override
	public void setClearDep( ICFBamClearDep src ) {
		super.setScope( src );
		setRequiredLookupRelation(src.getRequiredLookupRelation());
		setOptionalLookupDefSchema(src.getOptionalLookupDefSchema());
	}

	@Override
	public void set( ICFBamScopeH src ) {
		if( src instanceof ICFBamClearDepH ) {
			setClearDep( (ICFBamClearDepH)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamClearDepH" );
		}
	}

	@Override
	public void setClearDep( ICFBamClearDepH src ) {
		super.setScope( src );
		setRequiredLookupRelation(src.getRequiredRelationId());
		setOptionalLookupDefSchema(src.getOptionalDefSchemaId());
	}

	@Override
	public String getXmlAttrFragment() {
		String ret = super.getXmlAttrFragment() 
			+ " RequiredId=" + "\"" + getRequiredId().toString() + "\""
			+ " RequiredRelationId=" + "\"" + getRequiredRelationId().toString() + "\""
			+ " OptionalDefSchemaId=" + ( ( getOptionalDefSchemaId() == null ) ? "null" : "\"" + getOptionalDefSchemaId().toString() + "\"" );
		return( ret );
	}

	@Override
	public String toString() {
		String ret = "<CFBamJpaClearDep" + getXmlAttrFragment() + "/>";
		return( ret );
	}
}
