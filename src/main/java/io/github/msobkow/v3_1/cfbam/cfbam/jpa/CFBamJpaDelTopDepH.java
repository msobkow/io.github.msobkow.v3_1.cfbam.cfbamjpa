// Description: Java 25 JPA implementation of DelTopDep history objects

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
 *  CFBamJpaDelTopDepH provides history objects matching the CFBamDelTopDep change history.
 */
@Entity
@Table(
    name = "del_topdep_h", schema = "CFBam31",
    indexes = {
        @Index(name = "DelTopDepIdIdx_h", columnList = "auditClusterId, auditStamp, auditAction, requiredRevision, auditSessionId, Id", unique = true),
        @Index(name = "DelTopDepTableIdx_h", columnList = "TableId", unique = false),
        @Index(name = "DelTopDepUNameIdx_h", columnList = "TableId, safe_name", unique = true),
        @Index(name = "DelTopDepPrevIdx_h", columnList = "PrevId", unique = false),
        @Index(name = "DelTopDepNextIdx_h", columnList = "NextId", unique = false)
    }
)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("43035")
@Transactional(Transactional.TxType.SUPPORTS)
@PersistenceContext(unitName = "CFBamPU")
public class CFBamJpaDelTopDepH extends CFBamJpaDelDepH
    implements ICFBamDelTopDepH
{
	@Column( name="safe_name", nullable=false, length=192 )
	protected String requiredName;
	@AttributeOverrides({
		@AttributeOverride(name="bytes", column = @Column( name="TableId", nullable=false, length=CFLibDbKeyHash256.HASH_LENGTH ) )
	})
	protected CFLibDbKeyHash256 requiredTableId;
	@AttributeOverrides({
		@AttributeOverride(name="bytes", column = @Column( name="PrevId", nullable=true, length=CFLibDbKeyHash256.HASH_LENGTH ) )
	})
	protected CFLibDbKeyHash256 optionalPrevId;
	@AttributeOverrides({
		@AttributeOverride(name="bytes", column = @Column( name="NextId", nullable=true, length=CFLibDbKeyHash256.HASH_LENGTH ) )
	})
	protected CFLibDbKeyHash256 optionalNextId;

    public CFBamJpaDelTopDepH() {
            super();
		requiredName = ICFBamDelTopDep.NAME_INIT_VALUE;
		requiredTableId = CFLibDbKeyHash256.fromHex( ICFBamDelTopDep.TABLEID_INIT_VALUE.toString() );
		optionalPrevId = CFLibDbKeyHash256.nullGet();
		optionalNextId = CFLibDbKeyHash256.nullGet();
    }

    @Override
    public int getClassCode() {
            return( ICFBamDelTopDep.CLASS_CODE );
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
	public CFLibDbKeyHash256 getRequiredTableId() {
		return( requiredTableId );
	}

	@Override
	public void setRequiredTableId( CFLibDbKeyHash256 value ) {
		if( value == null || value.isNull() ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredTableId",
				1,
				"value" );
		}
		requiredTableId = value;
	}

	@Override
	public CFLibDbKeyHash256 getOptionalPrevId() {
		return( optionalPrevId );
	}

	@Override
	public void setOptionalPrevId( CFLibDbKeyHash256 value ) {
		optionalPrevId = value;
	}

	@Override
	public CFLibDbKeyHash256 getOptionalNextId() {
		return( optionalNextId );
	}

	@Override
	public void setOptionalNextId( CFLibDbKeyHash256 value ) {
		optionalNextId = value;
	}

    @Override
    public boolean equals( Object obj ) {
        if (obj == null) {
            return( false );
        }
        else if (obj instanceof ICFBamDelTopDep) {
            ICFBamDelTopDep rhs = (ICFBamDelTopDep)obj;
        if (!super.equals(obj)) {
            return( false );
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
			if( getRequiredTableId() != null && !getRequiredTableId().isNull() ) {
				if( rhs.getRequiredTableId() != null && !rhs.getRequiredTableId().isNull() ) {
					if( ! getRequiredTableId().equals( rhs.getRequiredTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredTableId() != null && !getRequiredTableId().isNull()) {
					return( false );
				}
			}
			if( getOptionalPrevId() != null && !getOptionalPrevId().isNull() ) {
				if( rhs.getOptionalPrevId() != null && !rhs.getOptionalPrevId().isNull() ) {
					if( ! getOptionalPrevId().equals( rhs.getOptionalPrevId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalPrevId() != null && !getOptionalPrevId().isNull()) {
					return( false );
				}
			}
			if( getOptionalNextId() != null && !getOptionalNextId().isNull() ) {
				if( rhs.getOptionalNextId() != null && !rhs.getOptionalNextId().isNull() ) {
					if( ! getOptionalNextId().equals( rhs.getOptionalNextId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalNextId() != null && !getOptionalNextId().isNull()) {
					return( false );
				}
			}
            return( true );
        }
        else if (obj instanceof ICFBamDelTopDepH) {
			if (!super.equals(obj)) {
				return( false );
			}
            ICFBamDelTopDepH rhs = (ICFBamDelTopDepH)obj;
        if (!super.equals(obj)) {
            return( false );
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
			if( getRequiredTableId() != null && !getRequiredTableId().isNull() ) {
				if( rhs.getRequiredTableId() != null && !rhs.getRequiredTableId().isNull() ) {
					if( ! getRequiredTableId().equals( rhs.getRequiredTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredTableId() != null && !getRequiredTableId().isNull()) {
					return( false );
				}
			}
			if( getOptionalPrevId() != null && !getOptionalPrevId().isNull() ) {
				if( rhs.getOptionalPrevId() != null && !rhs.getOptionalPrevId().isNull() ) {
					if( ! getOptionalPrevId().equals( rhs.getOptionalPrevId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalPrevId() != null && !getOptionalPrevId().isNull()) {
					return( false );
				}
			}
			if( getOptionalNextId() != null && !getOptionalNextId().isNull() ) {
				if( rhs.getOptionalNextId() != null && !rhs.getOptionalNextId().isNull() ) {
					if( ! getOptionalNextId().equals( rhs.getOptionalNextId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalNextId() != null && !getOptionalNextId().isNull()) {
					return( false );
				}
			}
            return( true );
        }
        else if (obj instanceof ICFBamScopeHPKey) {
			return( super.equals(obj) );
        }
        else if (obj instanceof ICFBamDelTopDepByDelTopDepTblIdxKey) {
            ICFBamDelTopDepByDelTopDepTblIdxKey rhs = (ICFBamDelTopDepByDelTopDepTblIdxKey)obj;
			if( getRequiredTableId() != null && !getRequiredTableId().isNull() ) {
				if( rhs.getRequiredTableId() != null && !rhs.getRequiredTableId().isNull() ) {
					if( ! getRequiredTableId().equals( rhs.getRequiredTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredTableId() != null && !getRequiredTableId().isNull()) {
					return( false );
				}
			}
            return( true );
        }
        else if (obj instanceof ICFBamDelTopDepByUNameIdxKey) {
            ICFBamDelTopDepByUNameIdxKey rhs = (ICFBamDelTopDepByUNameIdxKey)obj;
			if( getRequiredTableId() != null && !getRequiredTableId().isNull() ) {
				if( rhs.getRequiredTableId() != null && !rhs.getRequiredTableId().isNull() ) {
					if( ! getRequiredTableId().equals( rhs.getRequiredTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredTableId() != null && !getRequiredTableId().isNull()) {
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
        else if (obj instanceof ICFBamDelTopDepByPrevIdxKey) {
            ICFBamDelTopDepByPrevIdxKey rhs = (ICFBamDelTopDepByPrevIdxKey)obj;
			if( getOptionalPrevId() != null && !getOptionalPrevId().isNull() ) {
				if( rhs.getOptionalPrevId() != null && !rhs.getOptionalPrevId().isNull() ) {
					if( ! getOptionalPrevId().equals( rhs.getOptionalPrevId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalPrevId() != null && !getOptionalPrevId().isNull()) {
					return( false );
				}
			}
            return( true );
        }
        else if (obj instanceof ICFBamDelTopDepByNextIdxKey) {
            ICFBamDelTopDepByNextIdxKey rhs = (ICFBamDelTopDepByNextIdxKey)obj;
			if( getOptionalNextId() != null && !getOptionalNextId().isNull() ) {
				if( rhs.getOptionalNextId() != null && !rhs.getOptionalNextId().isNull() ) {
					if( ! getOptionalNextId().equals( rhs.getOptionalNextId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalNextId() != null && !getOptionalNextId().isNull()) {
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
		if( getRequiredName() != null ) {
			hashCode = hashCode + getRequiredName().hashCode();
		}
		hashCode = hashCode + getRequiredTableId().hashCode();
		if( getOptionalPrevId() != null ) {
			hashCode = hashCode + getOptionalPrevId().hashCode();
		}
		if( getOptionalNextId() != null ) {
			hashCode = hashCode + getOptionalNextId().hashCode();
		}
        return( hashCode & 0x7fffffff );
    }

    @Override
    public int compareTo( Object obj ) {
        int cmp;
        if (obj == null) {
            return( 1 );
        }
        else if (obj instanceof ICFBamDelTopDep) {
        cmp = super.compareTo(obj);
        if (cmp != 0) {
            return( cmp );
        }
        ICFBamDelTopDep rhs = (ICFBamDelTopDep)obj;
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
			if (getRequiredTableId() != null) {
				if (rhs.getRequiredTableId() != null) {
					cmp = getRequiredTableId().compareTo( rhs.getRequiredTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredTableId() != null) {
				return( -1 );
			}
			if( getOptionalPrevId() != null ) {
				if( rhs.getOptionalPrevId() != null ) {
					cmp = getOptionalPrevId().compareTo( rhs.getOptionalPrevId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalPrevId() != null ) {
					return( -1 );
				}
			}
			if( getOptionalNextId() != null ) {
				if( rhs.getOptionalNextId() != null ) {
					cmp = getOptionalNextId().compareTo( rhs.getOptionalNextId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalNextId() != null ) {
					return( -1 );
				}
			}
            return( 0 );
        }
        else if (obj instanceof ICFBamScopeHPKey) {
        return( super.compareTo(obj) );
        }
        else if (obj instanceof ICFBamDelTopDepH) {
        cmp = super.compareTo(obj);
        if (cmp != 0) {
            return( cmp );
        }
        ICFBamDelTopDepH rhs = (ICFBamDelTopDepH)obj;
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
			if (getRequiredTableId() != null) {
				if (rhs.getRequiredTableId() != null) {
					cmp = getRequiredTableId().compareTo( rhs.getRequiredTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredTableId() != null) {
				return( -1 );
			}
			if( getOptionalPrevId() != null ) {
				if( rhs.getOptionalPrevId() != null ) {
					cmp = getOptionalPrevId().compareTo( rhs.getOptionalPrevId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalPrevId() != null ) {
					return( -1 );
				}
			}
			if( getOptionalNextId() != null ) {
				if( rhs.getOptionalNextId() != null ) {
					cmp = getOptionalNextId().compareTo( rhs.getOptionalNextId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalNextId() != null ) {
					return( -1 );
				}
			}
            return( 0 );
        }
        else if (obj instanceof ICFBamDelTopDepByDelTopDepTblIdxKey ) {
            ICFBamDelTopDepByDelTopDepTblIdxKey rhs = (ICFBamDelTopDepByDelTopDepTblIdxKey)obj;
			if (getRequiredTableId() != null) {
				if (rhs.getRequiredTableId() != null) {
					cmp = getRequiredTableId().compareTo( rhs.getRequiredTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredTableId() != null) {
				return( -1 );
			}
            return( 0 );
        }
        else if (obj instanceof ICFBamDelTopDepByUNameIdxKey ) {
            ICFBamDelTopDepByUNameIdxKey rhs = (ICFBamDelTopDepByUNameIdxKey)obj;
			if (getRequiredTableId() != null) {
				if (rhs.getRequiredTableId() != null) {
					cmp = getRequiredTableId().compareTo( rhs.getRequiredTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredTableId() != null) {
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
        else if (obj instanceof ICFBamDelTopDepByPrevIdxKey ) {
            ICFBamDelTopDepByPrevIdxKey rhs = (ICFBamDelTopDepByPrevIdxKey)obj;
			if( getOptionalPrevId() != null ) {
				if( rhs.getOptionalPrevId() != null ) {
					cmp = getOptionalPrevId().compareTo( rhs.getOptionalPrevId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalPrevId() != null ) {
					return( -1 );
				}
			}
            return( 0 );
        }
        else if (obj instanceof ICFBamDelTopDepByNextIdxKey ) {
            ICFBamDelTopDepByNextIdxKey rhs = (ICFBamDelTopDepByNextIdxKey)obj;
			if( getOptionalNextId() != null ) {
				if( rhs.getOptionalNextId() != null ) {
					cmp = getOptionalNextId().compareTo( rhs.getOptionalNextId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalNextId() != null ) {
					return( -1 );
				}
			}
            return( 0 );
        }
        else {
        return( super.compareTo(obj) );
        }
    }
	@Override
    public void set( ICFBamScope src ) {
		if( src instanceof ICFBamDelTopDep ) {
			setDelTopDep( (ICFBamDelTopDep)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamJpaDelTopDep" );
		}
    }

	@Override
    public void setDelTopDep( ICFBamDelTopDep src ) {
        super.setDelDep( src );
		setRequiredName( src.getRequiredName() );
		setRequiredTableId( src.getRequiredTableId() );
		setOptionalPrevId( src.getOptionalPrevId() );
		setOptionalNextId( src.getOptionalNextId() );
    }

	@Override
    public void set( ICFBamScopeH src ) {
		if( src instanceof ICFBamDelTopDepH ) {
			setDelTopDep( (ICFBamDelTopDepH)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamDelTopDepH" );
		}
    }

	@Override
    public void setDelTopDep( ICFBamDelTopDepH src ) {
        super.setDelDep( src );
		setRequiredName( src.getRequiredName() );
		setRequiredTableId( src.getRequiredTableId() );
		setOptionalPrevId( src.getOptionalPrevId() );
		setOptionalNextId( src.getOptionalNextId() );
    }

    public String getXmlAttrFragment() {
        String ret = super.getXmlAttrFragment() 
			+ " RequiredName=" + "\"" + StringEscapeUtils.escapeXml11( getRequiredName() ) + "\""
			+ " RequiredTableId=" + "\"" + getRequiredTableId().toString() + "\""
			+ " OptionalPrevId=" + ( ( getOptionalPrevId() == null ) ? "null" : "\"" + getOptionalPrevId().toString() + "\"" )
			+ " OptionalNextId=" + ( ( getOptionalNextId() == null ) ? "null" : "\"" + getOptionalNextId().toString() + "\"" );
        return( ret );
    }

    public String toString() {
        String ret = "<CFBamJpaDelTopDepH" + getXmlAttrFragment() + "/>";
        return( ret );
    }
}
