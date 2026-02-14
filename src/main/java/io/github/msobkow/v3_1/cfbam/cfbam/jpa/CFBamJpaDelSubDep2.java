// Description: Java 25 JPA implementation of a DelSubDep2 entity definition object.

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
	name = "delsubdep2", schema = "CFBam31",
	indexes = {
		@Index(name = "DelSubDep2IdIdx", columnList = "Id", unique = true),
		@Index(name = "DelSubDep2DelSubDep1Idx", columnList = "contdeldep1id", unique = false),
		@Index(name = "DelSubDep2UNameIdx", columnList = "contdeldep1id, safe_name", unique = true)
	}
)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("43033")
@Transactional(Transactional.TxType.SUPPORTS)
@PersistenceContext(unitName = "CFBamPU")
public class CFBamJpaDelSubDep2 extends CFBamJpaDelDep
	implements ICFBamDelSubDep2
{
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn( name="contdeldep1id" )
	protected CFBamJpaDelSubDep1 requiredContainerDelSubDep1;
	@OneToMany(fetch=FetchType.LAZY, mappedBy="requiredContainerDelSubDep2")
	protected Set<CFBamJpaDelSubDep3> optionalComponentsDelDep;

	@Column( name="safe_name", nullable=false, length=192 )
	protected String requiredName;

	public CFBamJpaDelSubDep2() {
		super();
		requiredName = ICFBamDelSubDep2.NAME_INIT_VALUE;
	}

	@Override
	public int getClassCode() {
		return( ICFBamDelSubDep2.CLASS_CODE );
	}

	@Override
	public ICFBamDelSubDep1 getRequiredContainerDelSubDep1() {
		return( requiredContainerDelSubDep1 );
	}
	@Override
	public void setRequiredContainerDelSubDep1(ICFBamDelSubDep1 argObj) {
		if(argObj == null) {
			throw new CFLibNullArgumentException(getClass(), "setContainerDelSubDep1", 1, "argObj");
		}
		else if (argObj instanceof CFBamJpaDelSubDep1) {
			requiredContainerDelSubDep1 = (CFBamJpaDelSubDep1)argObj;
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "setContainerDelSubDep1", "argObj", argObj, "CFBamJpaDelSubDep1");
		}
	}

	@Override
	public void setRequiredContainerDelSubDep1(CFLibDbKeyHash256 argDelSubDep1Id) {
		ICFBamSchema targetBackingSchema = ICFBamSchema.getBackingCFBam();
		if (targetBackingSchema == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerDelSubDep1", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamDelSubDep1Table targetTable = targetBackingSchema.getTableDelSubDep1();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerDelSubDep1", 0, "ICFBamSchema.getBackingCFBam().getTableDelSubDep1()");
		}
		ICFBamDelSubDep1 targetRec = targetTable.readDerived(null, argDelSubDep1Id);
		setRequiredContainerDelSubDep1(targetRec);
	}

	@Override
	public List<ICFBamDelSubDep3> getOptionalComponentsDelDep() {
		List<ICFBamDelSubDep3> retlist = new ArrayList<>(optionalComponentsDelDep.size());
		for (CFBamJpaDelSubDep3 cur: optionalComponentsDelDep) {
			retlist.add(cur);
		}
		return( retlist );
	}
	@Override
	public CFLibDbKeyHash256 getRequiredDelSubDep1Id() {
		ICFBamDelSubDep1 result = getRequiredContainerDelSubDep1();
		if (result != null) {
			return result.getRequiredId();
		}
		else {
			return( ICFBamDelSubDep1.ID_INIT_VALUE );
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
		else if (obj instanceof ICFBamDelSubDep2) {
			if (!super.equals(obj)) {
				return( false );
			}
			ICFBamDelSubDep2 rhs = (ICFBamDelSubDep2)obj;
			if( getRequiredDelSubDep1Id() != null ) {
				if( rhs.getRequiredDelSubDep1Id() != null ) {
					if( ! getRequiredDelSubDep1Id().equals( rhs.getRequiredDelSubDep1Id() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredDelSubDep1Id() != null ) {
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
		else if (obj instanceof ICFBamDelSubDep2H) {
			if (!super.equals(obj)) {
				return( false );
			}
			ICFBamDelSubDep2H rhs = (ICFBamDelSubDep2H)obj;
			if( getRequiredDelSubDep1Id() != null ) {
				if( rhs.getRequiredDelSubDep1Id() != null ) {
					if( ! getRequiredDelSubDep1Id().equals( rhs.getRequiredDelSubDep1Id() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredDelSubDep1Id() != null ) {
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
		else if (obj instanceof ICFBamDelSubDep2ByContDelDep1IdxKey) {
			ICFBamDelSubDep2ByContDelDep1IdxKey rhs = (ICFBamDelSubDep2ByContDelDep1IdxKey)obj;
			if( getRequiredDelSubDep1Id() != null ) {
				if( rhs.getRequiredDelSubDep1Id() != null ) {
					if( ! getRequiredDelSubDep1Id().equals( rhs.getRequiredDelSubDep1Id() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredDelSubDep1Id() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if (obj instanceof ICFBamDelSubDep2ByUNameIdxKey) {
			ICFBamDelSubDep2ByUNameIdxKey rhs = (ICFBamDelSubDep2ByUNameIdxKey)obj;
			if( getRequiredDelSubDep1Id() != null ) {
				if( rhs.getRequiredDelSubDep1Id() != null ) {
					if( ! getRequiredDelSubDep1Id().equals( rhs.getRequiredDelSubDep1Id() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredDelSubDep1Id() != null ) {
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
		hashCode = hashCode + getRequiredDelSubDep1Id().hashCode();
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
		else if (obj instanceof ICFBamDelSubDep2) {
			cmp = super.compareTo(obj);
			if (cmp != 0) {
				return( cmp );
			}
			ICFBamDelSubDep2 rhs = (ICFBamDelSubDep2)obj;
			if (getRequiredDelSubDep1Id() != null) {
				if (rhs.getRequiredDelSubDep1Id() != null) {
					cmp = getRequiredDelSubDep1Id().compareTo( rhs.getRequiredDelSubDep1Id() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredDelSubDep1Id() != null) {
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
		else if( obj instanceof ICFBamDelSubDep2H ) {
			cmp = super.compareTo(obj);
			if (cmp != 0) {
				return( cmp );
			}
			ICFBamDelSubDep2H rhs = (ICFBamDelSubDep2H)obj;
			if (getRequiredDelSubDep1Id() != null) {
				if (rhs.getRequiredDelSubDep1Id() != null) {
					cmp = getRequiredDelSubDep1Id().compareTo( rhs.getRequiredDelSubDep1Id() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredDelSubDep1Id() != null) {
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
		else if (obj instanceof ICFBamDelSubDep2ByContDelDep1IdxKey) {
			ICFBamDelSubDep2ByContDelDep1IdxKey rhs = (ICFBamDelSubDep2ByContDelDep1IdxKey)obj;
			if (getRequiredDelSubDep1Id() != null) {
				if (rhs.getRequiredDelSubDep1Id() != null) {
					cmp = getRequiredDelSubDep1Id().compareTo( rhs.getRequiredDelSubDep1Id() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredDelSubDep1Id() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if (obj instanceof ICFBamDelSubDep2ByUNameIdxKey) {
			ICFBamDelSubDep2ByUNameIdxKey rhs = (ICFBamDelSubDep2ByUNameIdxKey)obj;
			if (getRequiredDelSubDep1Id() != null) {
				if (rhs.getRequiredDelSubDep1Id() != null) {
					cmp = getRequiredDelSubDep1Id().compareTo( rhs.getRequiredDelSubDep1Id() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredDelSubDep1Id() != null) {
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
		if( src instanceof ICFBamDelSubDep2 ) {
			setDelSubDep2( (ICFBamDelSubDep2)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamJpaDelSubDep2" );
		}
	}

	@Override
	public void setDelSubDep2( ICFBamDelSubDep2 src ) {
		super.setDelDep( src );
		setRequiredContainerDelSubDep1(src.getRequiredContainerDelSubDep1());
		setRequiredName(src.getRequiredName());
	}

	@Override
	public void set( ICFBamScopeH src ) {
		if( src instanceof ICFBamDelSubDep2H ) {
			setDelSubDep2( (ICFBamDelSubDep2H)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamDelSubDep2H" );
		}
	}

	@Override
	public void setDelSubDep2( ICFBamDelSubDep2H src ) {
		super.setDelDep( src );
		setRequiredContainerDelSubDep1(src.getRequiredDelSubDep1Id());
		setRequiredName(src.getRequiredName());
	}

	@Override
	public String getXmlAttrFragment() {
		String ret = super.getXmlAttrFragment() 
			+ " RequiredId=" + "\"" + getRequiredId().toString() + "\""
			+ " RequiredDelSubDep1Id=" + "\"" + getRequiredDelSubDep1Id().toString() + "\""
			+ " RequiredName=" + "\"" + StringEscapeUtils.escapeXml11( getRequiredName() ) + "\"";
		return( ret );
	}

	@Override
	public String toString() {
		String ret = "<CFBamJpaDelSubDep2" + getXmlAttrFragment() + "/>";
		return( ret );
	}
}
