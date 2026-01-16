// Description: Java 25 JPA implementation of ClearSubDep2 history objects

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
 *  CFBamJpaClearSubDep2H provides history objects matching the CFBamClearSubDep2 change history.
 */
@Entity
@Table(
    name = "clrsubdep2_h", schema = "CFBam31",
    indexes = {
        @Index(name = "ClearSubDep2IdIdx_h", columnList = "auditClusterId, auditStamp, auditAction, requiredRevision, auditSessionId, Id", unique = true),
        @Index(name = "ClearSubDep2ClearSubDep1Idx_h", columnList = "contclrdep1id", unique = false),
        @Index(name = "ClearSubDep2UNameIdx_h", columnList = "contclrdep1id, safe_name", unique = true)
    }
)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("43026")
@Transactional(Transactional.TxType.SUPPORTS)
@PersistenceContext(unitName = "CFBamPU")
public class CFBamJpaClearSubDep2H extends CFBamJpaClearDepH
    implements ICFBamClearSubDep2H
{
	@AttributeOverrides({
		@AttributeOverride(name="bytes", column = @Column( name="contclrdep1id", nullable=false, length=CFLibDbKeyHash256.HASH_LENGTH ) )
	})
	protected CFLibDbKeyHash256 requiredClearSubDep1Id;
	@Column( name="safe_name", nullable=false, length=192 )
	protected String requiredName;

    public CFBamJpaClearSubDep2H() {
            super();
		requiredClearSubDep1Id = CFLibDbKeyHash256.fromHex( ICFBamClearSubDep2.CLEARSUBDEP1ID_INIT_VALUE.toString() );
		requiredName = ICFBamClearSubDep2.NAME_INIT_VALUE;
    }

    @Override
    public int getClassCode() {
            return( ICFBamClearSubDep2.CLASS_CODE );
    }

	@Override
	public CFLibDbKeyHash256 getRequiredClearSubDep1Id() {
		return( requiredClearSubDep1Id );
	}

	@Override
	public void setRequiredClearSubDep1Id( CFLibDbKeyHash256 value ) {
		if( value == null || value.isNull() ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredClearSubDep1Id",
				1,
				"value" );
		}
		requiredClearSubDep1Id = value;
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
            ICFBamClearSubDep2 rhs = (ICFBamClearSubDep2)obj;
        if (!super.equals(obj)) {
            return( false );
        }
			if( getRequiredClearSubDep1Id() != null && !getRequiredClearSubDep1Id().isNull() ) {
				if( rhs.getRequiredClearSubDep1Id() != null && !rhs.getRequiredClearSubDep1Id().isNull() ) {
					if( ! getRequiredClearSubDep1Id().equals( rhs.getRequiredClearSubDep1Id() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredClearSubDep1Id() != null && !getRequiredClearSubDep1Id().isNull()) {
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
        if (!super.equals(obj)) {
            return( false );
        }
			if( getRequiredClearSubDep1Id() != null && !getRequiredClearSubDep1Id().isNull() ) {
				if( rhs.getRequiredClearSubDep1Id() != null && !rhs.getRequiredClearSubDep1Id().isNull() ) {
					if( ! getRequiredClearSubDep1Id().equals( rhs.getRequiredClearSubDep1Id() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredClearSubDep1Id() != null && !getRequiredClearSubDep1Id().isNull()) {
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
			if( getRequiredClearSubDep1Id() != null && !getRequiredClearSubDep1Id().isNull() ) {
				if( rhs.getRequiredClearSubDep1Id() != null && !rhs.getRequiredClearSubDep1Id().isNull() ) {
					if( ! getRequiredClearSubDep1Id().equals( rhs.getRequiredClearSubDep1Id() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredClearSubDep1Id() != null && !getRequiredClearSubDep1Id().isNull()) {
					return( false );
				}
			}
            return( true );
        }
        else if (obj instanceof ICFBamClearSubDep2ByUNameIdxKey) {
            ICFBamClearSubDep2ByUNameIdxKey rhs = (ICFBamClearSubDep2ByUNameIdxKey)obj;
			if( getRequiredClearSubDep1Id() != null && !getRequiredClearSubDep1Id().isNull() ) {
				if( rhs.getRequiredClearSubDep1Id() != null && !rhs.getRequiredClearSubDep1Id().isNull() ) {
					if( ! getRequiredClearSubDep1Id().equals( rhs.getRequiredClearSubDep1Id() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredClearSubDep1Id() != null && !getRequiredClearSubDep1Id().isNull()) {
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
        else if (obj instanceof ICFBamScopeHPKey) {
        return( super.compareTo(obj) );
        }
        else if (obj instanceof ICFBamClearSubDep2H) {
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
        else if (obj instanceof ICFBamClearSubDep2ByClearSubDep1IdxKey ) {
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
        else if (obj instanceof ICFBamClearSubDep2ByUNameIdxKey ) {
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
        return( super.compareTo(obj) );
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
		setRequiredClearSubDep1Id( src.getRequiredClearSubDep1Id() );
		setRequiredName( src.getRequiredName() );
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
		setRequiredClearSubDep1Id( src.getRequiredClearSubDep1Id() );
		setRequiredName( src.getRequiredName() );
    }

    public String getXmlAttrFragment() {
        String ret = super.getXmlAttrFragment() 
			+ " RequiredClearSubDep1Id=" + "\"" + getRequiredClearSubDep1Id().toString() + "\""
			+ " RequiredName=" + "\"" + StringEscapeUtils.escapeXml11( getRequiredName() ) + "\"";
        return( ret );
    }

    public String toString() {
        String ret = "<CFBamJpaClearSubDep2H" + getXmlAttrFragment() + "/>";
        return( ret );
    }
}
