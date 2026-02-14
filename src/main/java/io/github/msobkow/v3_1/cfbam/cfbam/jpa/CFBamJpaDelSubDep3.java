// Description: Java 25 JPA implementation of a DelSubDep3 entity definition object.

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
	name = "delsubdep3", schema = "CFBam31",
	indexes = {
		@Index(name = "DelSubDep3IdIdx", columnList = "Id", unique = true),
		@Index(name = "DelSubDep3DelSubDep2Idx", columnList = "contdeldep2id", unique = false),
		@Index(name = "DelSubDep3UNameIdx", columnList = "contdeldep2id, safe_name", unique = true)
	}
)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("43034")
@Transactional(Transactional.TxType.SUPPORTS)
@PersistenceContext(unitName = "CFBamPU")
public class CFBamJpaDelSubDep3 extends CFBamJpaDelDep
	implements ICFBamDelSubDep3
{
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn( name="contdeldep2id" )
	protected CFBamJpaDelSubDep2 requiredContainerDelSubDep2;

	@Column( name="safe_name", nullable=false, length=192 )
	protected String requiredName;

	public CFBamJpaDelSubDep3() {
		super();
		requiredName = ICFBamDelSubDep3.NAME_INIT_VALUE;
	}

	@Override
	public int getClassCode() {
		return( ICFBamDelSubDep3.CLASS_CODE );
	}

	@Override
	public ICFBamDelSubDep2 getRequiredContainerDelSubDep2() {
		return( requiredContainerDelSubDep2 );
	}
	@Override
	public void setRequiredContainerDelSubDep2(ICFBamDelSubDep2 argObj) {
		if(argObj == null) {
			throw new CFLibNullArgumentException(getClass(), "setContainerDelSubDep2", 1, "argObj");
		}
		else if (argObj instanceof CFBamJpaDelSubDep2) {
			requiredContainerDelSubDep2 = (CFBamJpaDelSubDep2)argObj;
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "setContainerDelSubDep2", "argObj", argObj, "CFBamJpaDelSubDep2");
		}
	}

	@Override
	public void setRequiredContainerDelSubDep2(CFLibDbKeyHash256 argDelSubDep2Id) {
		ICFBamSchema targetBackingSchema = ICFBamSchema.getBackingCFBam();
		if (targetBackingSchema == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerDelSubDep2", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamDelSubDep2Table targetTable = targetBackingSchema.getTableDelSubDep2();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerDelSubDep2", 0, "ICFBamSchema.getBackingCFBam().getTableDelSubDep2()");
		}
		ICFBamDelSubDep2 targetRec = targetTable.readDerived(null, argDelSubDep2Id);
		setRequiredContainerDelSubDep2(targetRec);
	}

	@Override
	public CFLibDbKeyHash256 getRequiredDelSubDep2Id() {
		ICFBamDelSubDep2 result = getRequiredContainerDelSubDep2();
		if (result != null) {
			return result.getRequiredId();
		}
		else {
			return( ICFBamDelSubDep2.ID_INIT_VALUE );
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
	public boolean equals( Object obj ) {
		if (obj == null) {
			return( false );
		}
		else if (obj instanceof ICFBamDelSubDep3) {
			if (!super.equals(obj)) {
				return( false );
			}
			ICFBamDelSubDep3 rhs = (ICFBamDelSubDep3)obj;
			if( getRequiredDelSubDep2Id() != null ) {
				if( rhs.getRequiredDelSubDep2Id() != null ) {
					if( ! getRequiredDelSubDep2Id().equals( rhs.getRequiredDelSubDep2Id() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredDelSubDep2Id() != null ) {
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
		else if (obj instanceof ICFBamDelSubDep3H) {
			if (!super.equals(obj)) {
				return( false );
			}
			ICFBamDelSubDep3H rhs = (ICFBamDelSubDep3H)obj;
			if( getRequiredDelSubDep2Id() != null ) {
				if( rhs.getRequiredDelSubDep2Id() != null ) {
					if( ! getRequiredDelSubDep2Id().equals( rhs.getRequiredDelSubDep2Id() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredDelSubDep2Id() != null ) {
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
		else if (obj instanceof ICFBamScopeHPKey) {
			return( super.equals(obj) );
		}
		else if (obj instanceof ICFBamDelSubDep3ByDelSubDep2IdxKey) {
			ICFBamDelSubDep3ByDelSubDep2IdxKey rhs = (ICFBamDelSubDep3ByDelSubDep2IdxKey)obj;
			if( getRequiredDelSubDep2Id() != null ) {
				if( rhs.getRequiredDelSubDep2Id() != null ) {
					if( ! getRequiredDelSubDep2Id().equals( rhs.getRequiredDelSubDep2Id() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredDelSubDep2Id() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if (obj instanceof ICFBamDelSubDep3ByUNameIdxKey) {
			ICFBamDelSubDep3ByUNameIdxKey rhs = (ICFBamDelSubDep3ByUNameIdxKey)obj;
			if( getRequiredDelSubDep2Id() != null ) {
				if( rhs.getRequiredDelSubDep2Id() != null ) {
					if( ! getRequiredDelSubDep2Id().equals( rhs.getRequiredDelSubDep2Id() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredDelSubDep2Id() != null ) {
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
		else {
			return( super.equals(obj) );
		}
	}
	
	@Override
	public int hashCode() {
		int hashCode = super.hashCode();
		hashCode = hashCode + getRequiredDelSubDep2Id().hashCode();
		if( getRequiredName() != null ) {
			hashCode = hashCode + getRequiredName().hashCode();
		}
		return( hashCode & 0x7fffffff );
	}

	@Override
	public int compareTo( Object obj ) {
		int cmp;
		if (obj == null) {
			return( 1 );
		}
		else if (obj instanceof ICFBamDelSubDep3) {
			cmp = super.compareTo(obj);
			if (cmp != 0) {
				return( cmp );
			}
			ICFBamDelSubDep3 rhs = (ICFBamDelSubDep3)obj;
			if (getRequiredDelSubDep2Id() != null) {
				if (rhs.getRequiredDelSubDep2Id() != null) {
					cmp = getRequiredDelSubDep2Id().compareTo( rhs.getRequiredDelSubDep2Id() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredDelSubDep2Id() != null) {
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
		else if( obj instanceof ICFBamDelSubDep3H ) {
			cmp = super.compareTo(obj);
			if (cmp != 0) {
				return( cmp );
			}
			ICFBamDelSubDep3H rhs = (ICFBamDelSubDep3H)obj;
			if (getRequiredDelSubDep2Id() != null) {
				if (rhs.getRequiredDelSubDep2Id() != null) {
					cmp = getRequiredDelSubDep2Id().compareTo( rhs.getRequiredDelSubDep2Id() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredDelSubDep2Id() != null) {
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
		else if (obj instanceof ICFBamDelSubDep3ByDelSubDep2IdxKey) {
			ICFBamDelSubDep3ByDelSubDep2IdxKey rhs = (ICFBamDelSubDep3ByDelSubDep2IdxKey)obj;
			if (getRequiredDelSubDep2Id() != null) {
				if (rhs.getRequiredDelSubDep2Id() != null) {
					cmp = getRequiredDelSubDep2Id().compareTo( rhs.getRequiredDelSubDep2Id() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredDelSubDep2Id() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if (obj instanceof ICFBamDelSubDep3ByUNameIdxKey) {
			ICFBamDelSubDep3ByUNameIdxKey rhs = (ICFBamDelSubDep3ByUNameIdxKey)obj;
			if (getRequiredDelSubDep2Id() != null) {
				if (rhs.getRequiredDelSubDep2Id() != null) {
					cmp = getRequiredDelSubDep2Id().compareTo( rhs.getRequiredDelSubDep2Id() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredDelSubDep2Id() != null) {
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
		else {
			cmp = super.compareTo(obj);
			return( cmp );
		}
	}

	@Override
	public void set( ICFBamScope src ) {
		if( src instanceof ICFBamDelSubDep3 ) {
			setDelSubDep3( (ICFBamDelSubDep3)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamJpaDelSubDep3" );
		}
	}

	@Override
	public void setDelSubDep3( ICFBamDelSubDep3 src ) {
		super.setDelDep( src );
		setRequiredContainerDelSubDep2(src.getRequiredContainerDelSubDep2());
		setRequiredName(src.getRequiredName());
	}

	@Override
	public void set( ICFBamScopeH src ) {
		if( src instanceof ICFBamDelSubDep3H ) {
			setDelSubDep3( (ICFBamDelSubDep3H)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamDelSubDep3H" );
		}
	}

	@Override
	public void setDelSubDep3( ICFBamDelSubDep3H src ) {
		super.setDelDep( src );
		setRequiredContainerDelSubDep2(src.getRequiredDelSubDep2Id());
		setRequiredName(src.getRequiredName());
	}

	@Override
	public String getXmlAttrFragment() {
		String ret = super.getXmlAttrFragment() 
			+ " RequiredId=" + "\"" + getRequiredId().toString() + "\""
			+ " RequiredDelSubDep2Id=" + "\"" + getRequiredDelSubDep2Id().toString() + "\""
			+ " RequiredName=" + "\"" + StringEscapeUtils.escapeXml11( getRequiredName() ) + "\"";
		return( ret );
	}

	@Override
	public String toString() {
		String ret = "<CFBamJpaDelSubDep3" + getXmlAttrFragment() + "/>";
		return( ret );
	}
}
