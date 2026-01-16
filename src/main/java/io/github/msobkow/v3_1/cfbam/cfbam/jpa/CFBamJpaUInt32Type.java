// Description: Java 25 JPA implementation of a UInt32Type entity definition object.

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
	name = "uint32typ", schema = "CFBam31",
	indexes = {
		@Index(name = "UInt32TypeIdIdx", columnList = "Id", unique = true),
		@Index(name = "UInt32TypeSchemaDefIdx", columnList = "SchemaDefId", unique = false)
	}
)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("43108")
@Transactional(Transactional.TxType.SUPPORTS)
@PersistenceContext(unitName = "CFBamPU")
public class CFBamJpaUInt32Type extends CFBamJpaUInt32Def
	implements ICFBamUInt32Type
{

	@AttributeOverrides({
		@AttributeOverride(name="bytes", column = @Column( name="SchemaDefId", nullable=false, length=CFLibDbKeyHash256.HASH_LENGTH ) )
	})
	protected CFLibDbKeyHash256 requiredSchemaDefId;

	public CFBamJpaUInt32Type() {
		super();
		requiredSchemaDefId = CFLibDbKeyHash256.fromHex( ICFBamUInt32Type.SCHEMADEFID_INIT_VALUE.toString() );
	}

	@Override
	public int getClassCode() {
		return( ICFBamUInt32Type.CLASS_CODE );
	}

	@Override
	public ICFBamSchemaDef getRequiredContainerSchemaDef() {
		ICFBamSchema targetBackingSchema = ICFBamSchema.getBackingCFBam();
		if (targetBackingSchema == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerSchemaDef", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamSchemaDefTable targetTable = targetBackingSchema.getTableSchemaDef();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerSchemaDef", 0, "ICFBamSchema.getBackingCFBam().getTableSchemaDef()");
		}
		ICFBamSchemaDef targetRec = targetTable.readDerivedByIdIdx(null, getRequiredSchemaDefId());
		return(targetRec);
	}
	@Override
	public void setRequiredContainerSchemaDef(ICFBamSchemaDef argObj) {
		if(argObj == null) {
			throw new CFLibNullArgumentException(getClass(), "setContainerSchemaDef", 1, "argObj");
		}
		else {
			requiredSchemaDefId = argObj.getRequiredId();
		}
	}

	@Override
	public void setRequiredContainerSchemaDef(CFLibDbKeyHash256 argSchemaDefId) {
		requiredSchemaDefId = argSchemaDefId;
	}

	@Override
	public CFLibDbKeyHash256 getRequiredSchemaDefId() {
		return( requiredSchemaDefId );
	}

	@Override
	public boolean equals( Object obj ) {
		if (obj == null) {
			return( false );
		}
		else if (obj instanceof ICFBamUInt32Type) {
			if (!super.equals(obj)) {
				return( false );
			}
			ICFBamUInt32Type rhs = (ICFBamUInt32Type)obj;
			if( getRequiredSchemaDefId() != null && !getRequiredSchemaDefId().isNull() ) {
				if( rhs.getRequiredSchemaDefId() != null && !rhs.getRequiredSchemaDefId().isNull() ) {
					if( ! getRequiredSchemaDefId().equals( rhs.getRequiredSchemaDefId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSchemaDefId() != null && !getRequiredSchemaDefId().isNull()) {
					return( false );
				}
			}
			return( true );
		}
		else if (obj instanceof ICFBamUInt32TypeH) {
			if (!super.equals(obj)) {
				return( false );
			}
			ICFBamUInt32TypeH rhs = (ICFBamUInt32TypeH)obj;
			if( getRequiredSchemaDefId() != null && !getRequiredSchemaDefId().isNull() ) {
				if( rhs.getRequiredSchemaDefId() != null && !rhs.getRequiredSchemaDefId().isNull() ) {
					if( ! getRequiredSchemaDefId().equals( rhs.getRequiredSchemaDefId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSchemaDefId() != null && !getRequiredSchemaDefId().isNull()) {
					return( false );
				}
			}
			return( true );
		}
		else if (obj instanceof ICFBamValueHPKey) {
			return( super.equals(obj) );
		}
		else if (obj instanceof ICFBamUInt32TypeBySchemaIdxKey) {
			ICFBamUInt32TypeBySchemaIdxKey rhs = (ICFBamUInt32TypeBySchemaIdxKey)obj;
			if( getRequiredSchemaDefId() != null && !getRequiredSchemaDefId().isNull() ) {
				if( rhs.getRequiredSchemaDefId() != null && !rhs.getRequiredSchemaDefId().isNull() ) {
					if( ! getRequiredSchemaDefId().equals( rhs.getRequiredSchemaDefId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSchemaDefId() != null && !getRequiredSchemaDefId().isNull()) {
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
		hashCode = hashCode + getRequiredSchemaDefId().hashCode();
		return( hashCode & 0x7fffffff );
	}

	@Override
	public int compareTo( Object obj ) {
		int cmp;
		if (obj == null) {
			return( 1 );
		}
		else if (obj instanceof ICFBamUInt32Type) {
			cmp = super.compareTo(obj);
			if (cmp != 0) {
				return( cmp );
			}
			ICFBamUInt32Type rhs = (ICFBamUInt32Type)obj;
			if (getRequiredSchemaDefId() != null) {
				if (rhs.getRequiredSchemaDefId() != null) {
					cmp = getRequiredSchemaDefId().compareTo( rhs.getRequiredSchemaDefId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSchemaDefId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFBamUInt32TypeH ) {
			cmp = super.compareTo(obj);
			if (cmp != 0) {
				return( cmp );
			}
			ICFBamUInt32TypeH rhs = (ICFBamUInt32TypeH)obj;
			if (getRequiredSchemaDefId() != null) {
				if (rhs.getRequiredSchemaDefId() != null) {
					cmp = getRequiredSchemaDefId().compareTo( rhs.getRequiredSchemaDefId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSchemaDefId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if (obj instanceof ICFBamUInt32TypeBySchemaIdxKey) {
			ICFBamUInt32TypeBySchemaIdxKey rhs = (ICFBamUInt32TypeBySchemaIdxKey)obj;
			if (getRequiredSchemaDefId() != null) {
				if (rhs.getRequiredSchemaDefId() != null) {
					cmp = getRequiredSchemaDefId().compareTo( rhs.getRequiredSchemaDefId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSchemaDefId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else {
			cmp = super.compareTo(obj);
			return( cmp );
		}
	}

	@Override
	public void set( ICFBamValue src ) {
		if( src instanceof ICFBamUInt32Type ) {
			setUInt32Type( (ICFBamUInt32Type)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamJpaUInt32Type" );
		}
	}

	@Override
	public void setUInt32Type( ICFBamUInt32Type src ) {
		super.setUInt32Def( src );
		setRequiredContainerSchemaDef(src.getRequiredContainerSchemaDef());
	}

	@Override
	public void set( ICFBamValueH src ) {
		if( src instanceof ICFBamUInt32TypeH ) {
			setUInt32Type( (ICFBamUInt32TypeH)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamUInt32TypeH" );
		}
	}

	@Override
	public void setUInt32Type( ICFBamUInt32TypeH src ) {
		super.setUInt32Def( src );
		setRequiredContainerSchemaDef(src.getRequiredSchemaDefId());
	}

	@Override
	public String getXmlAttrFragment() {
		String ret = super.getXmlAttrFragment() 
			+ " RequiredId=" + "\"" + getRequiredId().toString() + "\""
			+ " RequiredSchemaDefId=" + "\"" + getRequiredSchemaDefId().toString() + "\"";
		return( ret );
	}

	@Override
	public String toString() {
		String ret = "<CFBamJpaUInt32Type" + getXmlAttrFragment() + "/>";
		return( ret );
	}
}
