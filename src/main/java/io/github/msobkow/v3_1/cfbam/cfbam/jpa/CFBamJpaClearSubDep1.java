// Description: Java 25 JPA implementation of a ClearSubDep1 entity definition object.

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
	name = "clrsubdep1", schema = "CFBam31",
	indexes = {
		@Index(name = "ClearSubDep1IdIdx", columnList = "Id", unique = true),
		@Index(name = "ClearSubDep1ClearTopDepIdx", columnList = "contclrdepid", unique = false),
		@Index(name = "ClearSubDep1UNameIdx", columnList = "contclrdepid, safe_name", unique = true)
	}
)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("43025")
@Transactional(Transactional.TxType.SUPPORTS)
@PersistenceContext(unitName = "CFBamPU")
public class CFBamJpaClearSubDep1 extends CFBamJpaClearDep
	implements ICFBamClearSubDep1
{
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn( name="contclrdepid" )
	protected CFBamJpaClearTopDep requiredContainerClearTopDep;
	@OneToMany(fetch=FetchType.LAZY, mappedBy="requiredContainerClearSubDep1")
	protected Set<CFBamJpaClearSubDep2> optionalComponentsClearDep;

	@Column( name="safe_name", nullable=false, length=192 )
	protected String requiredName;

	public CFBamJpaClearSubDep1() {
		super();
		requiredName = ICFBamClearSubDep1.NAME_INIT_VALUE;
	}

	@Override
	public int getClassCode() {
		return( ICFBamClearSubDep1.CLASS_CODE );
	}

	@Override
	public ICFBamClearTopDep getRequiredContainerClearTopDep() {
		return( requiredContainerClearTopDep );
	}
	@Override
	public void setRequiredContainerClearTopDep(ICFBamClearTopDep argObj) {
		if(argObj == null) {
			throw new CFLibNullArgumentException(getClass(), "setContainerClearTopDep", 1, "argObj");
		}
		else if (argObj instanceof CFBamJpaClearTopDep) {
			requiredContainerClearTopDep = (CFBamJpaClearTopDep)argObj;
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "setContainerClearTopDep", "argObj", argObj, "CFBamJpaClearTopDep");
		}
	}

	@Override
	public void setRequiredContainerClearTopDep(CFLibDbKeyHash256 argClearTopDepId) {
		ICFBamSchema targetBackingSchema = ICFBamSchema.getBackingCFBam();
		if (targetBackingSchema == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerClearTopDep", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamClearTopDepTable targetTable = targetBackingSchema.getTableClearTopDep();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerClearTopDep", 0, "ICFBamSchema.getBackingCFBam().getTableClearTopDep()");
		}
		ICFBamClearTopDep targetRec = targetTable.readDerived(null, argClearTopDepId);
		setRequiredContainerClearTopDep(targetRec);
	}

	@Override
	public List<ICFBamClearSubDep2> getOptionalComponentsClearDep() {
		List<ICFBamClearSubDep2> retlist = new ArrayList<>(optionalComponentsClearDep.size());
		for (CFBamJpaClearSubDep2 cur: optionalComponentsClearDep) {
			retlist.add(cur);
		}
		return( retlist );
	}
	@Override
	public CFLibDbKeyHash256 getRequiredClearTopDepId() {
		ICFBamClearTopDep result = getRequiredContainerClearTopDep();
		if (result != null) {
			return result.getRequiredId();
		}
		else {
			return( ICFBamClearTopDep.ID_INIT_VALUE );
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
		else if (obj instanceof ICFBamClearSubDep1) {
			if (!super.equals(obj)) {
				return( false );
			}
			ICFBamClearSubDep1 rhs = (ICFBamClearSubDep1)obj;
			if( getRequiredClearTopDepId() != null ) {
				if( rhs.getRequiredClearTopDepId() != null ) {
					if( ! getRequiredClearTopDepId().equals( rhs.getRequiredClearTopDepId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredClearTopDepId() != null ) {
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
		else if (obj instanceof ICFBamClearSubDep1H) {
			if (!super.equals(obj)) {
				return( false );
			}
			ICFBamClearSubDep1H rhs = (ICFBamClearSubDep1H)obj;
			if( getRequiredClearTopDepId() != null ) {
				if( rhs.getRequiredClearTopDepId() != null ) {
					if( ! getRequiredClearTopDepId().equals( rhs.getRequiredClearTopDepId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredClearTopDepId() != null ) {
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
		else if (obj instanceof ICFBamClearSubDep1ByClearTopDepIdxKey) {
			ICFBamClearSubDep1ByClearTopDepIdxKey rhs = (ICFBamClearSubDep1ByClearTopDepIdxKey)obj;
			if( getRequiredClearTopDepId() != null ) {
				if( rhs.getRequiredClearTopDepId() != null ) {
					if( ! getRequiredClearTopDepId().equals( rhs.getRequiredClearTopDepId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredClearTopDepId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if (obj instanceof ICFBamClearSubDep1ByUNameIdxKey) {
			ICFBamClearSubDep1ByUNameIdxKey rhs = (ICFBamClearSubDep1ByUNameIdxKey)obj;
			if( getRequiredClearTopDepId() != null ) {
				if( rhs.getRequiredClearTopDepId() != null ) {
					if( ! getRequiredClearTopDepId().equals( rhs.getRequiredClearTopDepId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredClearTopDepId() != null ) {
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
		hashCode = hashCode + getRequiredClearTopDepId().hashCode();
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
		else if (obj instanceof ICFBamClearSubDep1) {
			cmp = super.compareTo(obj);
			if (cmp != 0) {
				return( cmp );
			}
			ICFBamClearSubDep1 rhs = (ICFBamClearSubDep1)obj;
			if (getRequiredClearTopDepId() != null) {
				if (rhs.getRequiredClearTopDepId() != null) {
					cmp = getRequiredClearTopDepId().compareTo( rhs.getRequiredClearTopDepId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredClearTopDepId() != null) {
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
		else if( obj instanceof ICFBamClearSubDep1H ) {
			cmp = super.compareTo(obj);
			if (cmp != 0) {
				return( cmp );
			}
			ICFBamClearSubDep1H rhs = (ICFBamClearSubDep1H)obj;
			if (getRequiredClearTopDepId() != null) {
				if (rhs.getRequiredClearTopDepId() != null) {
					cmp = getRequiredClearTopDepId().compareTo( rhs.getRequiredClearTopDepId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredClearTopDepId() != null) {
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
		else if (obj instanceof ICFBamClearSubDep1ByClearTopDepIdxKey) {
			ICFBamClearSubDep1ByClearTopDepIdxKey rhs = (ICFBamClearSubDep1ByClearTopDepIdxKey)obj;
			if (getRequiredClearTopDepId() != null) {
				if (rhs.getRequiredClearTopDepId() != null) {
					cmp = getRequiredClearTopDepId().compareTo( rhs.getRequiredClearTopDepId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredClearTopDepId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if (obj instanceof ICFBamClearSubDep1ByUNameIdxKey) {
			ICFBamClearSubDep1ByUNameIdxKey rhs = (ICFBamClearSubDep1ByUNameIdxKey)obj;
			if (getRequiredClearTopDepId() != null) {
				if (rhs.getRequiredClearTopDepId() != null) {
					cmp = getRequiredClearTopDepId().compareTo( rhs.getRequiredClearTopDepId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredClearTopDepId() != null) {
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
		if( src instanceof ICFBamClearSubDep1 ) {
			setClearSubDep1( (ICFBamClearSubDep1)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamJpaClearSubDep1" );
		}
	}

	@Override
	public void setClearSubDep1( ICFBamClearSubDep1 src ) {
		super.setClearDep( src );
		setRequiredContainerClearTopDep(src.getRequiredContainerClearTopDep());
		setRequiredName(src.getRequiredName());
	}

	@Override
	public void set( ICFBamScopeH src ) {
		if( src instanceof ICFBamClearSubDep1H ) {
			setClearSubDep1( (ICFBamClearSubDep1H)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamClearSubDep1H" );
		}
	}

	@Override
	public void setClearSubDep1( ICFBamClearSubDep1H src ) {
		super.setClearDep( src );
		setRequiredContainerClearTopDep(src.getRequiredClearTopDepId());
		setRequiredName(src.getRequiredName());
	}

	@Override
	public String getXmlAttrFragment() {
		String ret = super.getXmlAttrFragment() 
			+ " RequiredId=" + "\"" + getRequiredId().toString() + "\""
			+ " RequiredClearTopDepId=" + "\"" + getRequiredClearTopDepId().toString() + "\""
			+ " RequiredName=" + "\"" + StringEscapeUtils.escapeXml11( getRequiredName() ) + "\"";
		return( ret );
	}

	@Override
	public String toString() {
		String ret = "<CFBamJpaClearSubDep1" + getXmlAttrFragment() + "/>";
		return( ret );
	}
}
