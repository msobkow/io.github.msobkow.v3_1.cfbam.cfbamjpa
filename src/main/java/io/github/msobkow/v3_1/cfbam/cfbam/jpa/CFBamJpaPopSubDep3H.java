// Description: Java 25 JPA implementation of PopSubDep3 history objects

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
 *  CFBamJpaPopSubDep3H provides history objects matching the CFBamPopSubDep3 change history.
 */
@Entity
@Table(
    name = "popsubdep3_h", schema = "CFBam31",
    indexes = {
        @Index(name = "PopSubDep3IdIdx_h", columnList = "auditClusterId, auditStamp, auditAction, requiredRevision, auditSessionId, Id", unique = true),
        @Index(name = "PopSubDep3PopSubDep2Idx_h", columnList = "contpopdep2id", unique = false),
        @Index(name = "PopSubDep3UNameIdx_h", columnList = "contpopdep2id, safe_name", unique = true)
    }
)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("43059")
@Transactional(Transactional.TxType.SUPPORTS)
@PersistenceContext(unitName = "CFBamPU")
public class CFBamJpaPopSubDep3H extends CFBamJpaPopDepH
    implements ICFBamPopSubDep3H
{
	@AttributeOverrides({
		@AttributeOverride(name="bytes", column = @Column( name="contpopdep2id", nullable=false, length=CFLibDbKeyHash256.HASH_LENGTH ) )
	})
	protected CFLibDbKeyHash256 requiredPopSubDep2Id;
	@Column( name="safe_name", nullable=false, length=192 )
	protected String requiredName;

    public CFBamJpaPopSubDep3H() {
            super();
		requiredPopSubDep2Id = CFLibDbKeyHash256.fromHex( ICFBamPopSubDep3.POPSUBDEP2ID_INIT_VALUE.toString() );
		requiredName = ICFBamPopSubDep3.NAME_INIT_VALUE;
    }

    @Override
    public int getClassCode() {
            return( ICFBamPopSubDep3.CLASS_CODE );
    }

	@Override
	public CFLibDbKeyHash256 getRequiredPopSubDep2Id() {
		return( requiredPopSubDep2Id );
	}

	@Override
	public void setRequiredPopSubDep2Id( CFLibDbKeyHash256 value ) {
		if( value == null || value.isNull() ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredPopSubDep2Id",
				1,
				"value" );
		}
		requiredPopSubDep2Id = value;
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
            ICFBamPopSubDep3 rhs = (ICFBamPopSubDep3)obj;
        if (!super.equals(obj)) {
            return( false );
        }
			if( getRequiredPopSubDep2Id() != null && !getRequiredPopSubDep2Id().isNull() ) {
				if( rhs.getRequiredPopSubDep2Id() != null && !rhs.getRequiredPopSubDep2Id().isNull() ) {
					if( ! getRequiredPopSubDep2Id().equals( rhs.getRequiredPopSubDep2Id() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredPopSubDep2Id() != null && !getRequiredPopSubDep2Id().isNull()) {
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
        if (!super.equals(obj)) {
            return( false );
        }
			if( getRequiredPopSubDep2Id() != null && !getRequiredPopSubDep2Id().isNull() ) {
				if( rhs.getRequiredPopSubDep2Id() != null && !rhs.getRequiredPopSubDep2Id().isNull() ) {
					if( ! getRequiredPopSubDep2Id().equals( rhs.getRequiredPopSubDep2Id() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredPopSubDep2Id() != null && !getRequiredPopSubDep2Id().isNull()) {
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
			if( getRequiredPopSubDep2Id() != null && !getRequiredPopSubDep2Id().isNull() ) {
				if( rhs.getRequiredPopSubDep2Id() != null && !rhs.getRequiredPopSubDep2Id().isNull() ) {
					if( ! getRequiredPopSubDep2Id().equals( rhs.getRequiredPopSubDep2Id() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredPopSubDep2Id() != null && !getRequiredPopSubDep2Id().isNull()) {
					return( false );
				}
			}
            return( true );
        }
        else if (obj instanceof ICFBamPopSubDep3ByUNameIdxKey) {
            ICFBamPopSubDep3ByUNameIdxKey rhs = (ICFBamPopSubDep3ByUNameIdxKey)obj;
			if( getRequiredPopSubDep2Id() != null && !getRequiredPopSubDep2Id().isNull() ) {
				if( rhs.getRequiredPopSubDep2Id() != null && !rhs.getRequiredPopSubDep2Id().isNull() ) {
					if( ! getRequiredPopSubDep2Id().equals( rhs.getRequiredPopSubDep2Id() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredPopSubDep2Id() != null && !getRequiredPopSubDep2Id().isNull()) {
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
        else if (obj instanceof ICFBamScopeHPKey) {
        return( super.compareTo(obj) );
        }
        else if (obj instanceof ICFBamPopSubDep3H) {
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
        else if (obj instanceof ICFBamPopSubDep3ByPopSubDep2IdxKey ) {
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
        else if (obj instanceof ICFBamPopSubDep3ByUNameIdxKey ) {
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
        return( super.compareTo(obj) );
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
		setRequiredPopSubDep2Id( src.getRequiredPopSubDep2Id() );
		setRequiredName( src.getRequiredName() );
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
		setRequiredPopSubDep2Id( src.getRequiredPopSubDep2Id() );
		setRequiredName( src.getRequiredName() );
    }

    public String getXmlAttrFragment() {
        String ret = super.getXmlAttrFragment() 
			+ " RequiredPopSubDep2Id=" + "\"" + getRequiredPopSubDep2Id().toString() + "\""
			+ " RequiredName=" + "\"" + StringEscapeUtils.escapeXml11( getRequiredName() ) + "\"";
        return( ret );
    }

    public String toString() {
        String ret = "<CFBamJpaPopSubDep3H" + getXmlAttrFragment() + "/>";
        return( ret );
    }
}
