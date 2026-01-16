// Description: Java 25 JPA implementation of PopSubDep1 history objects

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

/**
 *  CFBamJpaPopSubDep1H provides history objects matching the CFBamPopSubDep1 change history.
 */
@Entity
@Table(
    name = "popsubdep1_h", schema = "CFBam31",
    indexes = {
        @Index(name = "PopSubDep1IdIdx_h", columnList = "auditClusterId, auditStamp, auditAction, requiredRevision, auditSessionId, Id", unique = true),
        @Index(name = "PopSubDep1PopTopDepIdx_h", columnList = "contpopdepid", unique = false),
        @Index(name = "PopSubDep1UNameIdx_h", columnList = "contpopdepid, safe_name", unique = true)
    }
)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("43057")
@Transactional(Transactional.TxType.SUPPORTS)
@PersistenceContext(unitName = "CFBamPU")
public class CFBamJpaPopSubDep1H extends CFBamJpaPopDepH
    implements ICFBamPopSubDep1H
{
	@AttributeOverrides({
		@AttributeOverride(name="bytes", column = @Column( name="contpopdepid", nullable=false, length=CFLibDbKeyHash256.HASH_LENGTH ) )
	})
	protected CFLibDbKeyHash256 requiredPopTopDepId;
	@Column( name="safe_name", nullable=false, length=192 )
	protected String requiredName;

    public CFBamJpaPopSubDep1H() {
            super();
		requiredPopTopDepId = CFLibDbKeyHash256.fromHex( ICFBamPopSubDep1.POPTOPDEPID_INIT_VALUE.toString() );
		requiredName = ICFBamPopSubDep1.NAME_INIT_VALUE;
    }

    @Override
    public int getClassCode() {
            return( ICFBamPopSubDep1.CLASS_CODE );
    }

	@Override
	public CFLibDbKeyHash256 getRequiredPopTopDepId() {
		return( requiredPopTopDepId );
	}

	@Override
	public void setRequiredPopTopDepId( CFLibDbKeyHash256 value ) {
		if( value == null || value.isNull() ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredPopTopDepId",
				1,
				"value" );
		}
		requiredPopTopDepId = value;
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
        else if (obj instanceof ICFBamPopSubDep1) {
            ICFBamPopSubDep1 rhs = (ICFBamPopSubDep1)obj;
        if (!super.equals(obj)) {
            return( false );
        }
			if( getRequiredPopTopDepId() != null && !getRequiredPopTopDepId().isNull() ) {
				if( rhs.getRequiredPopTopDepId() != null && !rhs.getRequiredPopTopDepId().isNull() ) {
					if( ! getRequiredPopTopDepId().equals( rhs.getRequiredPopTopDepId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredPopTopDepId() != null && !getRequiredPopTopDepId().isNull()) {
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
        else if (obj instanceof ICFBamPopSubDep1H) {
			if (!super.equals(obj)) {
				return( false );
			}
            ICFBamPopSubDep1H rhs = (ICFBamPopSubDep1H)obj;
        if (!super.equals(obj)) {
            return( false );
        }
			if( getRequiredPopTopDepId() != null && !getRequiredPopTopDepId().isNull() ) {
				if( rhs.getRequiredPopTopDepId() != null && !rhs.getRequiredPopTopDepId().isNull() ) {
					if( ! getRequiredPopTopDepId().equals( rhs.getRequiredPopTopDepId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredPopTopDepId() != null && !getRequiredPopTopDepId().isNull()) {
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
        else if (obj instanceof ICFBamPopSubDep1ByPopTopDepIdxKey) {
            ICFBamPopSubDep1ByPopTopDepIdxKey rhs = (ICFBamPopSubDep1ByPopTopDepIdxKey)obj;
			if( getRequiredPopTopDepId() != null && !getRequiredPopTopDepId().isNull() ) {
				if( rhs.getRequiredPopTopDepId() != null && !rhs.getRequiredPopTopDepId().isNull() ) {
					if( ! getRequiredPopTopDepId().equals( rhs.getRequiredPopTopDepId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredPopTopDepId() != null && !getRequiredPopTopDepId().isNull()) {
					return( false );
				}
			}
            return( true );
        }
        else if (obj instanceof ICFBamPopSubDep1ByUNameIdxKey) {
            ICFBamPopSubDep1ByUNameIdxKey rhs = (ICFBamPopSubDep1ByUNameIdxKey)obj;
			if( getRequiredPopTopDepId() != null && !getRequiredPopTopDepId().isNull() ) {
				if( rhs.getRequiredPopTopDepId() != null && !rhs.getRequiredPopTopDepId().isNull() ) {
					if( ! getRequiredPopTopDepId().equals( rhs.getRequiredPopTopDepId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredPopTopDepId() != null && !getRequiredPopTopDepId().isNull()) {
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
		hashCode = hashCode + getRequiredPopTopDepId().hashCode();
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
        else if (obj instanceof ICFBamPopSubDep1) {
        cmp = super.compareTo(obj);
        if (cmp != 0) {
            return( cmp );
        }
        ICFBamPopSubDep1 rhs = (ICFBamPopSubDep1)obj;
			if (getRequiredPopTopDepId() != null) {
				if (rhs.getRequiredPopTopDepId() != null) {
					cmp = getRequiredPopTopDepId().compareTo( rhs.getRequiredPopTopDepId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredPopTopDepId() != null) {
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
        else if (obj instanceof ICFBamScopeHPKey) {
        return( super.compareTo(obj) );
        }
        else if (obj instanceof ICFBamPopSubDep1H) {
        cmp = super.compareTo(obj);
        if (cmp != 0) {
            return( cmp );
        }
        ICFBamPopSubDep1H rhs = (ICFBamPopSubDep1H)obj;
			if (getRequiredPopTopDepId() != null) {
				if (rhs.getRequiredPopTopDepId() != null) {
					cmp = getRequiredPopTopDepId().compareTo( rhs.getRequiredPopTopDepId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredPopTopDepId() != null) {
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
        else if (obj instanceof ICFBamPopSubDep1ByPopTopDepIdxKey ) {
            ICFBamPopSubDep1ByPopTopDepIdxKey rhs = (ICFBamPopSubDep1ByPopTopDepIdxKey)obj;
			if (getRequiredPopTopDepId() != null) {
				if (rhs.getRequiredPopTopDepId() != null) {
					cmp = getRequiredPopTopDepId().compareTo( rhs.getRequiredPopTopDepId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredPopTopDepId() != null) {
				return( -1 );
			}
            return( 0 );
        }
        else if (obj instanceof ICFBamPopSubDep1ByUNameIdxKey ) {
            ICFBamPopSubDep1ByUNameIdxKey rhs = (ICFBamPopSubDep1ByUNameIdxKey)obj;
			if (getRequiredPopTopDepId() != null) {
				if (rhs.getRequiredPopTopDepId() != null) {
					cmp = getRequiredPopTopDepId().compareTo( rhs.getRequiredPopTopDepId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredPopTopDepId() != null) {
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
        return( super.compareTo(obj) );
        }
    }
	@Override
    public void set( ICFBamScope src ) {
		if( src instanceof ICFBamPopSubDep1 ) {
			setPopSubDep1( (ICFBamPopSubDep1)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamJpaPopSubDep1" );
		}
    }

	@Override
    public void setPopSubDep1( ICFBamPopSubDep1 src ) {
        super.setPopDep( src );
		setRequiredPopTopDepId( src.getRequiredPopTopDepId() );
		setRequiredName( src.getRequiredName() );
    }

	@Override
    public void set( ICFBamScopeH src ) {
		if( src instanceof ICFBamPopSubDep1H ) {
			setPopSubDep1( (ICFBamPopSubDep1H)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamPopSubDep1H" );
		}
    }

	@Override
    public void setPopSubDep1( ICFBamPopSubDep1H src ) {
        super.setPopDep( src );
		setRequiredPopTopDepId( src.getRequiredPopTopDepId() );
		setRequiredName( src.getRequiredName() );
    }

    public String getXmlAttrFragment() {
        String ret = super.getXmlAttrFragment() 
			+ " RequiredPopTopDepId=" + "\"" + getRequiredPopTopDepId().toString() + "\""
			+ " RequiredName=" + "\"" + StringEscapeUtils.escapeXml11( getRequiredName() ) + "\"";
        return( ret );
    }

    public String toString() {
        String ret = "<CFBamJpaPopSubDep1H" + getXmlAttrFragment() + "/>";
        return( ret );
    }
}
