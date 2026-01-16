// Description: Java 25 JPA implementation of PopSubDep2 history objects

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
 *  CFBamJpaPopSubDep2H provides history objects matching the CFBamPopSubDep2 change history.
 */
@Entity
@Table(
    name = "popsubdep2_h", schema = "CFBam31",
    indexes = {
        @Index(name = "PopSubDep2IdIdx_h", columnList = "auditClusterId, auditStamp, auditAction, requiredRevision, auditSessionId, Id", unique = true),
        @Index(name = "PopSubDep2PopSubDep1Idx_h", columnList = "contpopdep1id", unique = false),
        @Index(name = "PopSubDep2UNameIdx_h", columnList = "contpopdep1id, safe_name", unique = true)
    }
)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("43058")
@Transactional(Transactional.TxType.SUPPORTS)
@PersistenceContext(unitName = "CFBamPU")
public class CFBamJpaPopSubDep2H extends CFBamJpaPopDepH
    implements ICFBamPopSubDep2H
{
	@AttributeOverrides({
		@AttributeOverride(name="bytes", column = @Column( name="contpopdep1id", nullable=false, length=CFLibDbKeyHash256.HASH_LENGTH ) )
	})
	protected CFLibDbKeyHash256 requiredPopSubDep1Id;
	@Column( name="safe_name", nullable=false, length=192 )
	protected String requiredName;

    public CFBamJpaPopSubDep2H() {
            super();
		requiredPopSubDep1Id = CFLibDbKeyHash256.fromHex( ICFBamPopSubDep2.POPSUBDEP1ID_INIT_VALUE.toString() );
		requiredName = ICFBamPopSubDep2.NAME_INIT_VALUE;
    }

    @Override
    public int getClassCode() {
            return( ICFBamPopSubDep2.CLASS_CODE );
    }

	@Override
	public CFLibDbKeyHash256 getRequiredPopSubDep1Id() {
		return( requiredPopSubDep1Id );
	}

	@Override
	public void setRequiredPopSubDep1Id( CFLibDbKeyHash256 value ) {
		if( value == null || value.isNull() ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredPopSubDep1Id",
				1,
				"value" );
		}
		requiredPopSubDep1Id = value;
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
        else if (obj instanceof ICFBamPopSubDep2) {
            ICFBamPopSubDep2 rhs = (ICFBamPopSubDep2)obj;
        if (!super.equals(obj)) {
            return( false );
        }
			if( getRequiredPopSubDep1Id() != null && !getRequiredPopSubDep1Id().isNull() ) {
				if( rhs.getRequiredPopSubDep1Id() != null && !rhs.getRequiredPopSubDep1Id().isNull() ) {
					if( ! getRequiredPopSubDep1Id().equals( rhs.getRequiredPopSubDep1Id() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredPopSubDep1Id() != null && !getRequiredPopSubDep1Id().isNull()) {
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
        else if (obj instanceof ICFBamPopSubDep2H) {
			if (!super.equals(obj)) {
				return( false );
			}
            ICFBamPopSubDep2H rhs = (ICFBamPopSubDep2H)obj;
        if (!super.equals(obj)) {
            return( false );
        }
			if( getRequiredPopSubDep1Id() != null && !getRequiredPopSubDep1Id().isNull() ) {
				if( rhs.getRequiredPopSubDep1Id() != null && !rhs.getRequiredPopSubDep1Id().isNull() ) {
					if( ! getRequiredPopSubDep1Id().equals( rhs.getRequiredPopSubDep1Id() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredPopSubDep1Id() != null && !getRequiredPopSubDep1Id().isNull()) {
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
        else if (obj instanceof ICFBamPopSubDep2ByPopSubDep1IdxKey) {
            ICFBamPopSubDep2ByPopSubDep1IdxKey rhs = (ICFBamPopSubDep2ByPopSubDep1IdxKey)obj;
			if( getRequiredPopSubDep1Id() != null && !getRequiredPopSubDep1Id().isNull() ) {
				if( rhs.getRequiredPopSubDep1Id() != null && !rhs.getRequiredPopSubDep1Id().isNull() ) {
					if( ! getRequiredPopSubDep1Id().equals( rhs.getRequiredPopSubDep1Id() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredPopSubDep1Id() != null && !getRequiredPopSubDep1Id().isNull()) {
					return( false );
				}
			}
            return( true );
        }
        else if (obj instanceof ICFBamPopSubDep2ByUNameIdxKey) {
            ICFBamPopSubDep2ByUNameIdxKey rhs = (ICFBamPopSubDep2ByUNameIdxKey)obj;
			if( getRequiredPopSubDep1Id() != null && !getRequiredPopSubDep1Id().isNull() ) {
				if( rhs.getRequiredPopSubDep1Id() != null && !rhs.getRequiredPopSubDep1Id().isNull() ) {
					if( ! getRequiredPopSubDep1Id().equals( rhs.getRequiredPopSubDep1Id() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredPopSubDep1Id() != null && !getRequiredPopSubDep1Id().isNull()) {
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
		hashCode = hashCode + getRequiredPopSubDep1Id().hashCode();
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
        else if (obj instanceof ICFBamPopSubDep2) {
        cmp = super.compareTo(obj);
        if (cmp != 0) {
            return( cmp );
        }
        ICFBamPopSubDep2 rhs = (ICFBamPopSubDep2)obj;
			if (getRequiredPopSubDep1Id() != null) {
				if (rhs.getRequiredPopSubDep1Id() != null) {
					cmp = getRequiredPopSubDep1Id().compareTo( rhs.getRequiredPopSubDep1Id() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredPopSubDep1Id() != null) {
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
        else if (obj instanceof ICFBamPopSubDep2H) {
        cmp = super.compareTo(obj);
        if (cmp != 0) {
            return( cmp );
        }
        ICFBamPopSubDep2H rhs = (ICFBamPopSubDep2H)obj;
			if (getRequiredPopSubDep1Id() != null) {
				if (rhs.getRequiredPopSubDep1Id() != null) {
					cmp = getRequiredPopSubDep1Id().compareTo( rhs.getRequiredPopSubDep1Id() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredPopSubDep1Id() != null) {
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
        else if (obj instanceof ICFBamPopSubDep2ByPopSubDep1IdxKey ) {
            ICFBamPopSubDep2ByPopSubDep1IdxKey rhs = (ICFBamPopSubDep2ByPopSubDep1IdxKey)obj;
			if (getRequiredPopSubDep1Id() != null) {
				if (rhs.getRequiredPopSubDep1Id() != null) {
					cmp = getRequiredPopSubDep1Id().compareTo( rhs.getRequiredPopSubDep1Id() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredPopSubDep1Id() != null) {
				return( -1 );
			}
            return( 0 );
        }
        else if (obj instanceof ICFBamPopSubDep2ByUNameIdxKey ) {
            ICFBamPopSubDep2ByUNameIdxKey rhs = (ICFBamPopSubDep2ByUNameIdxKey)obj;
			if (getRequiredPopSubDep1Id() != null) {
				if (rhs.getRequiredPopSubDep1Id() != null) {
					cmp = getRequiredPopSubDep1Id().compareTo( rhs.getRequiredPopSubDep1Id() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredPopSubDep1Id() != null) {
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
		if( src instanceof ICFBamPopSubDep2 ) {
			setPopSubDep2( (ICFBamPopSubDep2)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamJpaPopSubDep2" );
		}
    }

	@Override
    public void setPopSubDep2( ICFBamPopSubDep2 src ) {
        super.setPopDep( src );
		setRequiredPopSubDep1Id( src.getRequiredPopSubDep1Id() );
		setRequiredName( src.getRequiredName() );
    }

	@Override
    public void set( ICFBamScopeH src ) {
		if( src instanceof ICFBamPopSubDep2H ) {
			setPopSubDep2( (ICFBamPopSubDep2H)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamPopSubDep2H" );
		}
    }

	@Override
    public void setPopSubDep2( ICFBamPopSubDep2H src ) {
        super.setPopDep( src );
		setRequiredPopSubDep1Id( src.getRequiredPopSubDep1Id() );
		setRequiredName( src.getRequiredName() );
    }

    public String getXmlAttrFragment() {
        String ret = super.getXmlAttrFragment() 
			+ " RequiredPopSubDep1Id=" + "\"" + getRequiredPopSubDep1Id().toString() + "\""
			+ " RequiredName=" + "\"" + StringEscapeUtils.escapeXml11( getRequiredName() ) + "\"";
        return( ret );
    }

    public String toString() {
        String ret = "<CFBamJpaPopSubDep2H" + getXmlAttrFragment() + "/>";
        return( ret );
    }
}
