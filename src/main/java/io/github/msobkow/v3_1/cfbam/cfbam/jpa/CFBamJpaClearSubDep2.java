// Description: Java 25 JPA implementation of a ClearSubDep2 entity definition object.

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
	name = "clrsubdep2", schema = "CFBam31",
	indexes = {
		@Index(name = "ClearSubDep2IdIdx", columnList = "Id", unique = true),
		@Index(name = "ClearSubDep2ClearSubDep1Idx", columnList = "contclrdep1id", unique = false),
		@Index(name = "ClearSubDep2UNameIdx", columnList = "contclrdep1id, safe_name", unique = true)
	}
)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("43026")
@Transactional(Transactional.TxType.SUPPORTS)
@PersistenceContext(unitName = "CFBamPU")
public class CFBamJpaClearSubDep2 extends CFBamJpaClearDep
	implements ICFBamClearSubDep2
{
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn( name="contclrdep1id" )
	protected CFBamJpaClearSubDep1 requiredContainerClearSubDep1;
	@OneToMany(fetch=FetchType.LAZY, mappedBy="requiredContainerClearSubDep2")
	protected Set<CFBamJpaClearSubDep3> optionalComponentsClearDep;

	@Column( name="safe_name", nullable=false, length=192 )
	protected String requiredName;

	public CFBamJpaClearSubDep2() {
		super();
		requiredName = ICFBamClearSubDep2.NAME_INIT_VALUE;
	}

	@Override
	public int getClassCode() {
		return( ICFBamClearSubDep2.CLASS_CODE );
	}

	@Override
	public ICFBamClearSubDep1 getRequiredContainerClearSubDep1() {
		return( requiredContainerClearSubDep1 );
	}
	@Override
	public void setRequiredContainerClearSubDep1(ICFBamClearSubDep1 argObj) {
		if(argObj == null) {
			throw new CFLibNullArgumentException(getClass(), "setContainerClearSubDep1", 1, "argObj");
		}
		else if (argObj instanceof CFBamJpaClearSubDep1) {
			requiredContainerClearSubDep1 = (CFBamJpaClearSubDep1)argObj;
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "setContainerClearSubDep1", "argObj", argObj, "CFBamJpaClearSubDep1");
		}
	}

	@Override
	public void setRequiredContainerClearSubDep1(CFLibDbKeyHash256 argClearSubDep1Id) {
		ICFBamSchema targetBackingSchema = ICFBamSchema.getBackingCFBam();
		if (targetBackingSchema == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerClearSubDep1", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamClearSubDep1Table targetTable = targetBackingSchema.getTableClearSubDep1();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerClearSubDep1", 0, "ICFBamSchema.getBackingCFBam().getTableClearSubDep1()");
		}
		ICFBamClearSubDep1 targetRec = targetTable.readDerived(null, argClearSubDep1Id);
		setRequiredContainerClearSubDep1(targetRec);
	}

	@Override
	public List<ICFBamClearSubDep3> getOptionalComponentsClearDep() {
		List<ICFBamClearSubDep3> retlist = new ArrayList<>(optionalComponentsClearDep.size());
		for (CFBamJpaClearSubDep3 cur: optionalComponentsClearDep) {
			retlist.add(cur);
		}
		return( retlist );
	}
	@Override
	public CFLibDbKeyHash256 getRequiredClearSubDep1Id() {
		ICFBamClearSubDep1 result = getRequiredContainerClearSubDep1();
		if (result != null) {
			return result.getRequiredId();
		}
		else {
			return( ICFBamClearSubDep1.ID_INIT_VALUE );
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
		else if (obj instanceof ICFBamClearSubDep2) {
			if (!super.equals(obj)) {
				return( false );
			}
			ICFBamClearSubDep2 rhs = (ICFBamClearSubDep2)obj;
			if( getRequiredClearSubDep1Id() != null ) {
				if( rhs.getRequiredClearSubDep1Id() != null ) {
					if( ! getRequiredClearSubDep1Id().equals( rhs.getRequiredClearSubDep1Id() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredClearSubDep1Id() != null ) {
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
		else if (obj instanceof ICFBamClearSubDep2H) {
			if (!super.equals(obj)) {
				return( false );
			}
			ICFBamClearSubDep2H rhs = (ICFBamClearSubDep2H)obj;
			if( getRequiredClearSubDep1Id() != null ) {
				if( rhs.getRequiredClearSubDep1Id() != null ) {
					if( ! getRequiredClearSubDep1Id().equals( rhs.getRequiredClearSubDep1Id() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredClearSubDep1Id() != null ) {
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
		else if (obj instanceof ICFBamClearSubDep2ByClearSubDep1IdxKey) {
			ICFBamClearSubDep2ByClearSubDep1IdxKey rhs = (ICFBamClearSubDep2ByClearSubDep1IdxKey)obj;
			if( getRequiredClearSubDep1Id() != null ) {
				if( rhs.getRequiredClearSubDep1Id() != null ) {
					if( ! getRequiredClearSubDep1Id().equals( rhs.getRequiredClearSubDep1Id() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredClearSubDep1Id() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if (obj instanceof ICFBamClearSubDep2ByUNameIdxKey) {
			ICFBamClearSubDep2ByUNameIdxKey rhs = (ICFBamClearSubDep2ByUNameIdxKey)obj;
			if( getRequiredClearSubDep1Id() != null ) {
				if( rhs.getRequiredClearSubDep1Id() != null ) {
					if( ! getRequiredClearSubDep1Id().equals( rhs.getRequiredClearSubDep1Id() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredClearSubDep1Id() != null ) {
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
		hashCode = hashCode + getRequiredClearSubDep1Id().hashCode();
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
		else if (obj instanceof ICFBamClearSubDep2) {
			cmp = super.compareTo(obj);
			if (cmp != 0) {
				return( cmp );
			}
			ICFBamClearSubDep2 rhs = (ICFBamClearSubDep2)obj;
			if (getRequiredClearSubDep1Id() != null) {
				if (rhs.getRequiredClearSubDep1Id() != null) {
					cmp = getRequiredClearSubDep1Id().compareTo( rhs.getRequiredClearSubDep1Id() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredClearSubDep1Id() != null) {
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
		else if( obj instanceof ICFBamClearSubDep2H ) {
			cmp = super.compareTo(obj);
			if (cmp != 0) {
				return( cmp );
			}
			ICFBamClearSubDep2H rhs = (ICFBamClearSubDep2H)obj;
			if (getRequiredClearSubDep1Id() != null) {
				if (rhs.getRequiredClearSubDep1Id() != null) {
					cmp = getRequiredClearSubDep1Id().compareTo( rhs.getRequiredClearSubDep1Id() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredClearSubDep1Id() != null) {
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
		else if (obj instanceof ICFBamClearSubDep2ByClearSubDep1IdxKey) {
			ICFBamClearSubDep2ByClearSubDep1IdxKey rhs = (ICFBamClearSubDep2ByClearSubDep1IdxKey)obj;
			if (getRequiredClearSubDep1Id() != null) {
				if (rhs.getRequiredClearSubDep1Id() != null) {
					cmp = getRequiredClearSubDep1Id().compareTo( rhs.getRequiredClearSubDep1Id() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredClearSubDep1Id() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if (obj instanceof ICFBamClearSubDep2ByUNameIdxKey) {
			ICFBamClearSubDep2ByUNameIdxKey rhs = (ICFBamClearSubDep2ByUNameIdxKey)obj;
			if (getRequiredClearSubDep1Id() != null) {
				if (rhs.getRequiredClearSubDep1Id() != null) {
					cmp = getRequiredClearSubDep1Id().compareTo( rhs.getRequiredClearSubDep1Id() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredClearSubDep1Id() != null) {
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
		if( src instanceof ICFBamClearSubDep2 ) {
			setClearSubDep2( (ICFBamClearSubDep2)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamJpaClearSubDep2" );
		}
	}

	@Override
	public void setClearSubDep2( ICFBamClearSubDep2 src ) {
		super.setClearDep( src );
		setRequiredContainerClearSubDep1(src.getRequiredContainerClearSubDep1());
		setRequiredName(src.getRequiredName());
	}

	@Override
	public void set( ICFBamScopeH src ) {
		if( src instanceof ICFBamClearSubDep2H ) {
			setClearSubDep2( (ICFBamClearSubDep2H)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamClearSubDep2H" );
		}
	}

	@Override
	public void setClearSubDep2( ICFBamClearSubDep2H src ) {
		super.setClearDep( src );
		setRequiredContainerClearSubDep1(src.getRequiredClearSubDep1Id());
		setRequiredName(src.getRequiredName());
	}

	@Override
	public String getXmlAttrFragment() {
		String ret = super.getXmlAttrFragment() 
			+ " RequiredId=" + "\"" + getRequiredId().toString() + "\""
			+ " RequiredClearSubDep1Id=" + "\"" + getRequiredClearSubDep1Id().toString() + "\""
			+ " RequiredName=" + "\"" + StringEscapeUtils.escapeXml11( getRequiredName() ) + "\"";
		return( ret );
	}

	@Override
	public String toString() {
		String ret = "<CFBamJpaClearSubDep2" + getXmlAttrFragment() + "/>";
		return( ret );
	}
}
