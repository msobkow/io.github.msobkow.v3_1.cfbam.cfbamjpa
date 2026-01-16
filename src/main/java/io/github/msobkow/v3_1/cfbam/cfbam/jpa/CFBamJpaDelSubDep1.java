// Description: Java 25 JPA implementation of a DelSubDep1 entity definition object.

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
	name = "delsubdep1", schema = "CFBam31",
	indexes = {
		@Index(name = "DelSubDep1IdIdx", columnList = "Id", unique = true),
		@Index(name = "DelSubDep1DelTopDepIdx", columnList = "contdeldepid", unique = false),
		@Index(name = "DelSubDep1UNameIdx", columnList = "contdeldepid, safe_name", unique = true)
	}
)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("43032")
@Transactional(Transactional.TxType.SUPPORTS)
@PersistenceContext(unitName = "CFBamPU")
public class CFBamJpaDelSubDep1 extends CFBamJpaDelDep
	implements ICFBamDelSubDep1
{
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn( name="contdeldepid" )
	protected CFBamJpaDelTopDep requiredContainerDelTopDep;
	@OneToMany(fetch=FetchType.LAZY, mappedBy="requiredContainerDelSubDep1")
	protected Set<CFBamJpaDelSubDep2> optionalComponentsDelDep;

	@Column( name="safe_name", nullable=false, length=192 )
	protected String requiredName;

	public CFBamJpaDelSubDep1() {
		super();
		requiredName = ICFBamDelSubDep1.NAME_INIT_VALUE;
	}

	@Override
	public int getClassCode() {
		return( ICFBamDelSubDep1.CLASS_CODE );
	}

	@Override
	public ICFBamDelTopDep getRequiredContainerDelTopDep() {
		return( requiredContainerDelTopDep );
	}
	@Override
	public void setRequiredContainerDelTopDep(ICFBamDelTopDep argObj) {
		if(argObj == null) {
			throw new CFLibNullArgumentException(getClass(), "setContainerDelTopDep", 1, "argObj");
		}
		else if (argObj instanceof CFBamJpaDelTopDep) {
			requiredContainerDelTopDep = (CFBamJpaDelTopDep)argObj;
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "setContainerDelTopDep", "argObj", argObj, "CFBamJpaDelTopDep");
		}
	}

	@Override
	public void setRequiredContainerDelTopDep(CFLibDbKeyHash256 argDelTopDepId) {
		ICFBamSchema targetBackingSchema = ICFBamSchema.getBackingCFBam();
		if (targetBackingSchema == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerDelTopDep", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamDelTopDepTable targetTable = targetBackingSchema.getTableDelTopDep();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerDelTopDep", 0, "ICFBamSchema.getBackingCFBam().getTableDelTopDep()");
		}
		ICFBamDelTopDep targetRec = targetTable.readDerived(null, argDelTopDepId);
		setRequiredContainerDelTopDep(targetRec);
	}

	@Override
	public List<ICFBamDelSubDep2> getOptionalComponentsDelDep() {
		List<ICFBamDelSubDep2> retlist = new ArrayList<>(optionalComponentsDelDep.size());
		for (CFBamJpaDelSubDep2 cur: optionalComponentsDelDep) {
			retlist.add(cur);
		}
		return( retlist );
	}
	@Override
	public CFLibDbKeyHash256 getRequiredDelTopDepId() {
		ICFBamDelTopDep result = getRequiredContainerDelTopDep();
		if (result != null) {
			return result.getRequiredId();
		}
		else {
			return( ICFBamDelTopDep.ID_INIT_VALUE );
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
		else if (obj instanceof ICFBamDelSubDep1) {
			if (!super.equals(obj)) {
				return( false );
			}
			ICFBamDelSubDep1 rhs = (ICFBamDelSubDep1)obj;
			if( getRequiredDelTopDepId() != null && !getRequiredDelTopDepId().isNull() ) {
				if( rhs.getRequiredDelTopDepId() != null && !rhs.getRequiredDelTopDepId().isNull() ) {
					if( ! getRequiredDelTopDepId().equals( rhs.getRequiredDelTopDepId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredDelTopDepId() != null && !getRequiredDelTopDepId().isNull()) {
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
		else if (obj instanceof ICFBamDelSubDep1H) {
			if (!super.equals(obj)) {
				return( false );
			}
			ICFBamDelSubDep1H rhs = (ICFBamDelSubDep1H)obj;
			if( getRequiredDelTopDepId() != null && !getRequiredDelTopDepId().isNull() ) {
				if( rhs.getRequiredDelTopDepId() != null && !rhs.getRequiredDelTopDepId().isNull() ) {
					if( ! getRequiredDelTopDepId().equals( rhs.getRequiredDelTopDepId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredDelTopDepId() != null && !getRequiredDelTopDepId().isNull()) {
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
		else if (obj instanceof ICFBamDelSubDep1ByDelTopDepIdxKey) {
			ICFBamDelSubDep1ByDelTopDepIdxKey rhs = (ICFBamDelSubDep1ByDelTopDepIdxKey)obj;
			if( getRequiredDelTopDepId() != null && !getRequiredDelTopDepId().isNull() ) {
				if( rhs.getRequiredDelTopDepId() != null && !rhs.getRequiredDelTopDepId().isNull() ) {
					if( ! getRequiredDelTopDepId().equals( rhs.getRequiredDelTopDepId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredDelTopDepId() != null && !getRequiredDelTopDepId().isNull()) {
					return( false );
				}
			}
			return( true );
		}
		else if (obj instanceof ICFBamDelSubDep1ByUNameIdxKey) {
			ICFBamDelSubDep1ByUNameIdxKey rhs = (ICFBamDelSubDep1ByUNameIdxKey)obj;
			if( getRequiredDelTopDepId() != null && !getRequiredDelTopDepId().isNull() ) {
				if( rhs.getRequiredDelTopDepId() != null && !rhs.getRequiredDelTopDepId().isNull() ) {
					if( ! getRequiredDelTopDepId().equals( rhs.getRequiredDelTopDepId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredDelTopDepId() != null && !getRequiredDelTopDepId().isNull()) {
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
		hashCode = hashCode + getRequiredDelTopDepId().hashCode();
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
		else if (obj instanceof ICFBamDelSubDep1) {
			cmp = super.compareTo(obj);
			if (cmp != 0) {
				return( cmp );
			}
			ICFBamDelSubDep1 rhs = (ICFBamDelSubDep1)obj;
			if (getRequiredDelTopDepId() != null) {
				if (rhs.getRequiredDelTopDepId() != null) {
					cmp = getRequiredDelTopDepId().compareTo( rhs.getRequiredDelTopDepId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredDelTopDepId() != null) {
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
		else if( obj instanceof ICFBamDelSubDep1H ) {
			cmp = super.compareTo(obj);
			if (cmp != 0) {
				return( cmp );
			}
			ICFBamDelSubDep1H rhs = (ICFBamDelSubDep1H)obj;
			if (getRequiredDelTopDepId() != null) {
				if (rhs.getRequiredDelTopDepId() != null) {
					cmp = getRequiredDelTopDepId().compareTo( rhs.getRequiredDelTopDepId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredDelTopDepId() != null) {
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
		else if (obj instanceof ICFBamDelSubDep1ByDelTopDepIdxKey) {
			ICFBamDelSubDep1ByDelTopDepIdxKey rhs = (ICFBamDelSubDep1ByDelTopDepIdxKey)obj;
			if (getRequiredDelTopDepId() != null) {
				if (rhs.getRequiredDelTopDepId() != null) {
					cmp = getRequiredDelTopDepId().compareTo( rhs.getRequiredDelTopDepId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredDelTopDepId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if (obj instanceof ICFBamDelSubDep1ByUNameIdxKey) {
			ICFBamDelSubDep1ByUNameIdxKey rhs = (ICFBamDelSubDep1ByUNameIdxKey)obj;
			if (getRequiredDelTopDepId() != null) {
				if (rhs.getRequiredDelTopDepId() != null) {
					cmp = getRequiredDelTopDepId().compareTo( rhs.getRequiredDelTopDepId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredDelTopDepId() != null) {
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
		if( src instanceof ICFBamDelSubDep1 ) {
			setDelSubDep1( (ICFBamDelSubDep1)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamJpaDelSubDep1" );
		}
	}

	@Override
	public void setDelSubDep1( ICFBamDelSubDep1 src ) {
		super.setDelDep( src );
		setRequiredContainerDelTopDep(src.getRequiredContainerDelTopDep());
		setRequiredName(src.getRequiredName());
	}

	@Override
	public void set( ICFBamScopeH src ) {
		if( src instanceof ICFBamDelSubDep1H ) {
			setDelSubDep1( (ICFBamDelSubDep1H)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamDelSubDep1H" );
		}
	}

	@Override
	public void setDelSubDep1( ICFBamDelSubDep1H src ) {
		super.setDelDep( src );
		setRequiredContainerDelTopDep(src.getRequiredDelTopDepId());
		setRequiredName(src.getRequiredName());
	}

	@Override
	public String getXmlAttrFragment() {
		String ret = super.getXmlAttrFragment() 
			+ " RequiredId=" + "\"" + getRequiredId().toString() + "\""
			+ " RequiredDelTopDepId=" + "\"" + getRequiredDelTopDepId().toString() + "\""
			+ " RequiredName=" + "\"" + StringEscapeUtils.escapeXml11( getRequiredName() ) + "\"";
		return( ret );
	}

	@Override
	public String toString() {
		String ret = "<CFBamJpaDelSubDep1" + getXmlAttrFragment() + "/>";
		return( ret );
	}
}
