// Description: Java 25 JPA implementation of a PopSubDep3 entity definition object.

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
	name = "popsubdep3", schema = "CFBam31",
	indexes = {
		@Index(name = "PopSubDep3IdIdx", columnList = "Id", unique = true),
		@Index(name = "PopSubDep3PopSubDep2Idx", columnList = "contpopdep2id", unique = false),
		@Index(name = "PopSubDep3UNameIdx", columnList = "contpopdep2id, safe_name", unique = true)
	}
)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("43059")
@Transactional(Transactional.TxType.SUPPORTS)
@PersistenceContext(unitName = "CFBamPU")
public class CFBamJpaPopSubDep3 extends CFBamJpaPopDep
	implements ICFBamPopSubDep3
{
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn( name="contpopdep2id" )
	protected CFBamJpaPopSubDep2 requiredContainerPopSubDep2;

	@Column( name="safe_name", nullable=false, length=192 )
	protected String requiredName;

	public CFBamJpaPopSubDep3() {
		super();
		requiredName = ICFBamPopSubDep3.NAME_INIT_VALUE;
	}

	@Override
	public int getClassCode() {
		return( ICFBamPopSubDep3.CLASS_CODE );
	}

	@Override
	public ICFBamPopSubDep2 getRequiredContainerPopSubDep2() {
		return( requiredContainerPopSubDep2 );
	}
	@Override
	public void setRequiredContainerPopSubDep2(ICFBamPopSubDep2 argObj) {
		if(argObj == null) {
			throw new CFLibNullArgumentException(getClass(), "setContainerPopSubDep2", 1, "argObj");
		}
		else if (argObj instanceof CFBamJpaPopSubDep2) {
			requiredContainerPopSubDep2 = (CFBamJpaPopSubDep2)argObj;
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "setContainerPopSubDep2", "argObj", argObj, "CFBamJpaPopSubDep2");
		}
	}

	@Override
	public void setRequiredContainerPopSubDep2(CFLibDbKeyHash256 argPopSubDep2Id) {
		ICFBamSchema targetBackingSchema = ICFBamSchema.getBackingCFBam();
		if (targetBackingSchema == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerPopSubDep2", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamPopSubDep2Table targetTable = targetBackingSchema.getTablePopSubDep2();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerPopSubDep2", 0, "ICFBamSchema.getBackingCFBam().getTablePopSubDep2()");
		}
		ICFBamPopSubDep2 targetRec = targetTable.readDerived(null, argPopSubDep2Id);
		setRequiredContainerPopSubDep2(targetRec);
	}

	@Override
	public CFLibDbKeyHash256 getRequiredPopSubDep2Id() {
		ICFBamPopSubDep2 result = getRequiredContainerPopSubDep2();
		if (result != null) {
			return result.getRequiredId();
		}
		else {
			return( ICFBamPopSubDep2.ID_INIT_VALUE );
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
		else if (obj instanceof ICFBamPopSubDep3) {
			if (!super.equals(obj)) {
				return( false );
			}
			ICFBamPopSubDep3 rhs = (ICFBamPopSubDep3)obj;
			if( getRequiredPopSubDep2Id() != null ) {
				if( rhs.getRequiredPopSubDep2Id() != null ) {
					if( ! getRequiredPopSubDep2Id().equals( rhs.getRequiredPopSubDep2Id() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredPopSubDep2Id() != null ) {
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
		else if (obj instanceof ICFBamPopSubDep3H) {
			if (!super.equals(obj)) {
				return( false );
			}
			ICFBamPopSubDep3H rhs = (ICFBamPopSubDep3H)obj;
			if( getRequiredPopSubDep2Id() != null ) {
				if( rhs.getRequiredPopSubDep2Id() != null ) {
					if( ! getRequiredPopSubDep2Id().equals( rhs.getRequiredPopSubDep2Id() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredPopSubDep2Id() != null ) {
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
		else if (obj instanceof ICFBamPopSubDep3ByPopSubDep2IdxKey) {
			ICFBamPopSubDep3ByPopSubDep2IdxKey rhs = (ICFBamPopSubDep3ByPopSubDep2IdxKey)obj;
			if( getRequiredPopSubDep2Id() != null ) {
				if( rhs.getRequiredPopSubDep2Id() != null ) {
					if( ! getRequiredPopSubDep2Id().equals( rhs.getRequiredPopSubDep2Id() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredPopSubDep2Id() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if (obj instanceof ICFBamPopSubDep3ByUNameIdxKey) {
			ICFBamPopSubDep3ByUNameIdxKey rhs = (ICFBamPopSubDep3ByUNameIdxKey)obj;
			if( getRequiredPopSubDep2Id() != null ) {
				if( rhs.getRequiredPopSubDep2Id() != null ) {
					if( ! getRequiredPopSubDep2Id().equals( rhs.getRequiredPopSubDep2Id() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredPopSubDep2Id() != null ) {
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
		hashCode = hashCode + getRequiredPopSubDep2Id().hashCode();
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
		else if (obj instanceof ICFBamPopSubDep3) {
			cmp = super.compareTo(obj);
			if (cmp != 0) {
				return( cmp );
			}
			ICFBamPopSubDep3 rhs = (ICFBamPopSubDep3)obj;
			if (getRequiredPopSubDep2Id() != null) {
				if (rhs.getRequiredPopSubDep2Id() != null) {
					cmp = getRequiredPopSubDep2Id().compareTo( rhs.getRequiredPopSubDep2Id() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredPopSubDep2Id() != null) {
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
		else if( obj instanceof ICFBamPopSubDep3H ) {
			cmp = super.compareTo(obj);
			if (cmp != 0) {
				return( cmp );
			}
			ICFBamPopSubDep3H rhs = (ICFBamPopSubDep3H)obj;
			if (getRequiredPopSubDep2Id() != null) {
				if (rhs.getRequiredPopSubDep2Id() != null) {
					cmp = getRequiredPopSubDep2Id().compareTo( rhs.getRequiredPopSubDep2Id() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredPopSubDep2Id() != null) {
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
		else if (obj instanceof ICFBamPopSubDep3ByPopSubDep2IdxKey) {
			ICFBamPopSubDep3ByPopSubDep2IdxKey rhs = (ICFBamPopSubDep3ByPopSubDep2IdxKey)obj;
			if (getRequiredPopSubDep2Id() != null) {
				if (rhs.getRequiredPopSubDep2Id() != null) {
					cmp = getRequiredPopSubDep2Id().compareTo( rhs.getRequiredPopSubDep2Id() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredPopSubDep2Id() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if (obj instanceof ICFBamPopSubDep3ByUNameIdxKey) {
			ICFBamPopSubDep3ByUNameIdxKey rhs = (ICFBamPopSubDep3ByUNameIdxKey)obj;
			if (getRequiredPopSubDep2Id() != null) {
				if (rhs.getRequiredPopSubDep2Id() != null) {
					cmp = getRequiredPopSubDep2Id().compareTo( rhs.getRequiredPopSubDep2Id() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredPopSubDep2Id() != null) {
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
		if( src instanceof ICFBamPopSubDep3 ) {
			setPopSubDep3( (ICFBamPopSubDep3)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamJpaPopSubDep3" );
		}
	}

	@Override
	public void setPopSubDep3( ICFBamPopSubDep3 src ) {
		super.setPopDep( src );
		setRequiredContainerPopSubDep2(src.getRequiredContainerPopSubDep2());
		setRequiredName(src.getRequiredName());
	}

	@Override
	public void set( ICFBamScopeH src ) {
		if( src instanceof ICFBamPopSubDep3H ) {
			setPopSubDep3( (ICFBamPopSubDep3H)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamPopSubDep3H" );
		}
	}

	@Override
	public void setPopSubDep3( ICFBamPopSubDep3H src ) {
		super.setPopDep( src );
		setRequiredContainerPopSubDep2(src.getRequiredPopSubDep2Id());
		setRequiredName(src.getRequiredName());
	}

	@Override
	public String getXmlAttrFragment() {
		String ret = super.getXmlAttrFragment() 
			+ " RequiredId=" + "\"" + getRequiredId().toString() + "\""
			+ " RequiredPopSubDep2Id=" + "\"" + getRequiredPopSubDep2Id().toString() + "\""
			+ " RequiredName=" + "\"" + StringEscapeUtils.escapeXml11( getRequiredName() ) + "\"";
		return( ret );
	}

	@Override
	public String toString() {
		String ret = "<CFBamJpaPopSubDep3" + getXmlAttrFragment() + "/>";
		return( ret );
	}
}
