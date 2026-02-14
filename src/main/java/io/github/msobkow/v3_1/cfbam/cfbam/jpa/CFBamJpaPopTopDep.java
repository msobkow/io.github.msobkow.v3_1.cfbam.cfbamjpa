// Description: Java 25 JPA implementation of a PopTopDep entity definition object.

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
	name = "pop_topdep", schema = "CFBam31",
	indexes = {
		@Index(name = "PopTopDepIdIdx", columnList = "Id", unique = true),
		@Index(name = "PopTopDepContRelationIdx", columnList = "ContRelationId", unique = false),
		@Index(name = "PopTopDepUNameIdx", columnList = "ContRelationId, safe_name", unique = true)
	}
)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("43060")
@Transactional(Transactional.TxType.SUPPORTS)
@PersistenceContext(unitName = "CFBamPU")
public class CFBamJpaPopTopDep extends CFBamJpaPopDep
	implements ICFBamPopTopDep
{
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn( name="ContRelationId" )
	protected CFBamJpaRelation requiredContainerContRelation;
	@OneToMany(fetch=FetchType.LAZY, mappedBy="requiredContainerContPopTopDep")
	protected Set<CFBamJpaPopSubDep1> optionalComponentsPopDep;

	@Column( name="safe_name", nullable=false, length=192 )
	protected String requiredName;

	public CFBamJpaPopTopDep() {
		super();
		requiredName = ICFBamPopTopDep.NAME_INIT_VALUE;
	}

	@Override
	public int getClassCode() {
		return( ICFBamPopTopDep.CLASS_CODE );
	}

	@Override
	public ICFBamRelation getRequiredContainerContRelation() {
		return( requiredContainerContRelation );
	}
	@Override
	public void setRequiredContainerContRelation(ICFBamRelation argObj) {
		if(argObj == null) {
			throw new CFLibNullArgumentException(getClass(), "setContainerContRelation", 1, "argObj");
		}
		else if (argObj instanceof CFBamJpaRelation) {
			requiredContainerContRelation = (CFBamJpaRelation)argObj;
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "setContainerContRelation", "argObj", argObj, "CFBamJpaRelation");
		}
	}

	@Override
	public void setRequiredContainerContRelation(CFLibDbKeyHash256 argContRelationId) {
		ICFBamSchema targetBackingSchema = ICFBamSchema.getBackingCFBam();
		if (targetBackingSchema == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerContRelation", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamRelationTable targetTable = targetBackingSchema.getTableRelation();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerContRelation", 0, "ICFBamSchema.getBackingCFBam().getTableRelation()");
		}
		ICFBamRelation targetRec = targetTable.readDerived(null, argContRelationId);
		setRequiredContainerContRelation(targetRec);
	}

	@Override
	public List<ICFBamPopSubDep1> getOptionalComponentsPopDep() {
		List<ICFBamPopSubDep1> retlist = new ArrayList<>(optionalComponentsPopDep.size());
		for (CFBamJpaPopSubDep1 cur: optionalComponentsPopDep) {
			retlist.add(cur);
		}
		return( retlist );
	}
	@Override
	public CFLibDbKeyHash256 getRequiredContRelationId() {
		ICFBamRelation result = getRequiredContainerContRelation();
		if (result != null) {
			return result.getRequiredId();
		}
		else {
			return( ICFBamRelation.ID_INIT_VALUE );
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
		else if (obj instanceof ICFBamPopTopDep) {
			if (!super.equals(obj)) {
				return( false );
			}
			ICFBamPopTopDep rhs = (ICFBamPopTopDep)obj;
			if( getRequiredContRelationId() != null ) {
				if( rhs.getRequiredContRelationId() != null ) {
					if( ! getRequiredContRelationId().equals( rhs.getRequiredContRelationId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredContRelationId() != null ) {
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
		else if (obj instanceof ICFBamPopTopDepH) {
			if (!super.equals(obj)) {
				return( false );
			}
			ICFBamPopTopDepH rhs = (ICFBamPopTopDepH)obj;
			if( getRequiredContRelationId() != null ) {
				if( rhs.getRequiredContRelationId() != null ) {
					if( ! getRequiredContRelationId().equals( rhs.getRequiredContRelationId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredContRelationId() != null ) {
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
		else if (obj instanceof ICFBamPopTopDepByContRelIdxKey) {
			ICFBamPopTopDepByContRelIdxKey rhs = (ICFBamPopTopDepByContRelIdxKey)obj;
			if( getRequiredContRelationId() != null ) {
				if( rhs.getRequiredContRelationId() != null ) {
					if( ! getRequiredContRelationId().equals( rhs.getRequiredContRelationId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredContRelationId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if (obj instanceof ICFBamPopTopDepByUNameIdxKey) {
			ICFBamPopTopDepByUNameIdxKey rhs = (ICFBamPopTopDepByUNameIdxKey)obj;
			if( getRequiredContRelationId() != null ) {
				if( rhs.getRequiredContRelationId() != null ) {
					if( ! getRequiredContRelationId().equals( rhs.getRequiredContRelationId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredContRelationId() != null ) {
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
		hashCode = hashCode + getRequiredContRelationId().hashCode();
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
		else if (obj instanceof ICFBamPopTopDep) {
			cmp = super.compareTo(obj);
			if (cmp != 0) {
				return( cmp );
			}
			ICFBamPopTopDep rhs = (ICFBamPopTopDep)obj;
			if (getRequiredContRelationId() != null) {
				if (rhs.getRequiredContRelationId() != null) {
					cmp = getRequiredContRelationId().compareTo( rhs.getRequiredContRelationId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredContRelationId() != null) {
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
		else if( obj instanceof ICFBamPopTopDepH ) {
			cmp = super.compareTo(obj);
			if (cmp != 0) {
				return( cmp );
			}
			ICFBamPopTopDepH rhs = (ICFBamPopTopDepH)obj;
			if (getRequiredContRelationId() != null) {
				if (rhs.getRequiredContRelationId() != null) {
					cmp = getRequiredContRelationId().compareTo( rhs.getRequiredContRelationId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredContRelationId() != null) {
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
		else if (obj instanceof ICFBamPopTopDepByContRelIdxKey) {
			ICFBamPopTopDepByContRelIdxKey rhs = (ICFBamPopTopDepByContRelIdxKey)obj;
			if (getRequiredContRelationId() != null) {
				if (rhs.getRequiredContRelationId() != null) {
					cmp = getRequiredContRelationId().compareTo( rhs.getRequiredContRelationId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredContRelationId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if (obj instanceof ICFBamPopTopDepByUNameIdxKey) {
			ICFBamPopTopDepByUNameIdxKey rhs = (ICFBamPopTopDepByUNameIdxKey)obj;
			if (getRequiredContRelationId() != null) {
				if (rhs.getRequiredContRelationId() != null) {
					cmp = getRequiredContRelationId().compareTo( rhs.getRequiredContRelationId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredContRelationId() != null) {
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
		if( src instanceof ICFBamPopTopDep ) {
			setPopTopDep( (ICFBamPopTopDep)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamJpaPopTopDep" );
		}
	}

	@Override
	public void setPopTopDep( ICFBamPopTopDep src ) {
		super.setPopDep( src );
		setRequiredContainerContRelation(src.getRequiredContainerContRelation());
		setRequiredName(src.getRequiredName());
	}

	@Override
	public void set( ICFBamScopeH src ) {
		if( src instanceof ICFBamPopTopDepH ) {
			setPopTopDep( (ICFBamPopTopDepH)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamPopTopDepH" );
		}
	}

	@Override
	public void setPopTopDep( ICFBamPopTopDepH src ) {
		super.setPopDep( src );
		setRequiredContainerContRelation(src.getRequiredContRelationId());
		setRequiredName(src.getRequiredName());
	}

	@Override
	public String getXmlAttrFragment() {
		String ret = super.getXmlAttrFragment() 
			+ " RequiredId=" + "\"" + getRequiredId().toString() + "\""
			+ " RequiredContRelationId=" + "\"" + getRequiredContRelationId().toString() + "\""
			+ " RequiredName=" + "\"" + StringEscapeUtils.escapeXml11( getRequiredName() ) + "\"";
		return( ret );
	}

	@Override
	public String toString() {
		String ret = "<CFBamJpaPopTopDep" + getXmlAttrFragment() + "/>";
		return( ret );
	}
}
