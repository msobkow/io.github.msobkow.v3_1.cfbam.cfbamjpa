// Description: Java 25 JPA implementation of DelDep history objects

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
 *  CFBamJpaDelDepH provides history objects matching the CFBamDelDep change history.
 */
@Entity
@Table(
    name = "del_dep_h", schema = "CFBam31",
    indexes = {
        @Index(name = "DelDepIdIdx_h", columnList = "auditClusterId, auditStamp, auditAction, requiredRevision, auditSessionId, Id", unique = true),
        @Index(name = "DelDepDefSchemaDefIdx_h", columnList = "defschid", unique = false),
        @Index(name = "DelDepRelationIdx_h", columnList = "RelationId", unique = false)
    }
)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("43031")
@Transactional(Transactional.TxType.SUPPORTS)
@PersistenceContext(unitName = "CFBamPU")
public class CFBamJpaDelDepH extends CFBamJpaScopeH
    implements ICFBamDelDepH
{
	@AttributeOverrides({
		@AttributeOverride(name="bytes", column = @Column( name="defschid", nullable=true, length=CFLibDbKeyHash256.HASH_LENGTH ) )
	})
	protected CFLibDbKeyHash256 optionalDefSchemaId;
	@AttributeOverrides({
		@AttributeOverride(name="bytes", column = @Column( name="RelationId", nullable=false, length=CFLibDbKeyHash256.HASH_LENGTH ) )
	})
	protected CFLibDbKeyHash256 requiredRelationId;

    public CFBamJpaDelDepH() {
            super();
		optionalDefSchemaId = CFLibDbKeyHash256.nullGet();
		requiredRelationId = CFLibDbKeyHash256.fromHex( ICFBamDelDep.RELATIONID_INIT_VALUE.toString() );
    }

    @Override
    public int getClassCode() {
            return( ICFBamDelDep.CLASS_CODE );
    }

	@Override
	public CFLibDbKeyHash256 getOptionalDefSchemaId() {
		return( optionalDefSchemaId );
	}

	@Override
	public void setOptionalDefSchemaId( CFLibDbKeyHash256 value ) {
		optionalDefSchemaId = value;
	}

	@Override
	public CFLibDbKeyHash256 getRequiredRelationId() {
		return( requiredRelationId );
	}

	@Override
	public void setRequiredRelationId( CFLibDbKeyHash256 value ) {
		if( value == null || value.isNull() ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredRelationId",
				1,
				"value" );
		}
		requiredRelationId = value;
	}

    @Override
    public boolean equals( Object obj ) {
        if (obj == null) {
            return( false );
        }
        else if (obj instanceof ICFBamDelDep) {
            ICFBamDelDep rhs = (ICFBamDelDep)obj;
        if (!super.equals(obj)) {
            return( false );
        }
			if( getOptionalDefSchemaId() != null && !getOptionalDefSchemaId().isNull() ) {
				if( rhs.getOptionalDefSchemaId() != null && !rhs.getOptionalDefSchemaId().isNull() ) {
					if( ! getOptionalDefSchemaId().equals( rhs.getOptionalDefSchemaId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDefSchemaId() != null && !getOptionalDefSchemaId().isNull()) {
					return( false );
				}
			}
			if( getRequiredRelationId() != null && !getRequiredRelationId().isNull() ) {
				if( rhs.getRequiredRelationId() != null && !rhs.getRequiredRelationId().isNull() ) {
					if( ! getRequiredRelationId().equals( rhs.getRequiredRelationId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredRelationId() != null && !getRequiredRelationId().isNull()) {
					return( false );
				}
			}
            return( true );
        }
        else if (obj instanceof ICFBamDelDepH) {
			if (!super.equals(obj)) {
				return( false );
			}
            ICFBamDelDepH rhs = (ICFBamDelDepH)obj;
        if (!super.equals(obj)) {
            return( false );
        }
			if( getOptionalDefSchemaId() != null && !getOptionalDefSchemaId().isNull() ) {
				if( rhs.getOptionalDefSchemaId() != null && !rhs.getOptionalDefSchemaId().isNull() ) {
					if( ! getOptionalDefSchemaId().equals( rhs.getOptionalDefSchemaId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDefSchemaId() != null && !getOptionalDefSchemaId().isNull()) {
					return( false );
				}
			}
			if( getRequiredRelationId() != null && !getRequiredRelationId().isNull() ) {
				if( rhs.getRequiredRelationId() != null && !rhs.getRequiredRelationId().isNull() ) {
					if( ! getRequiredRelationId().equals( rhs.getRequiredRelationId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredRelationId() != null && !getRequiredRelationId().isNull()) {
					return( false );
				}
			}
            return( true );
        }
        else if (obj instanceof ICFBamScopeHPKey) {
			return( super.equals(obj) );
        }
        else if (obj instanceof ICFBamDelDepByDefSchemaIdxKey) {
            ICFBamDelDepByDefSchemaIdxKey rhs = (ICFBamDelDepByDefSchemaIdxKey)obj;
			if( getOptionalDefSchemaId() != null && !getOptionalDefSchemaId().isNull() ) {
				if( rhs.getOptionalDefSchemaId() != null && !rhs.getOptionalDefSchemaId().isNull() ) {
					if( ! getOptionalDefSchemaId().equals( rhs.getOptionalDefSchemaId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDefSchemaId() != null && !getOptionalDefSchemaId().isNull()) {
					return( false );
				}
			}
            return( true );
        }
        else if (obj instanceof ICFBamDelDepByDelDepIdxKey) {
            ICFBamDelDepByDelDepIdxKey rhs = (ICFBamDelDepByDelDepIdxKey)obj;
			if( getRequiredRelationId() != null && !getRequiredRelationId().isNull() ) {
				if( rhs.getRequiredRelationId() != null && !rhs.getRequiredRelationId().isNull() ) {
					if( ! getRequiredRelationId().equals( rhs.getRequiredRelationId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredRelationId() != null && !getRequiredRelationId().isNull()) {
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
		if( getOptionalDefSchemaId() != null ) {
			hashCode = hashCode + getOptionalDefSchemaId().hashCode();
		}
		hashCode = hashCode + getRequiredRelationId().hashCode();
        return( hashCode & 0x7fffffff );
    }

    @Override
    public int compareTo( Object obj ) {
        int cmp;
        if (obj == null) {
            return( 1 );
        }
        else if (obj instanceof ICFBamDelDep) {
        cmp = super.compareTo(obj);
        if (cmp != 0) {
            return( cmp );
        }
        ICFBamDelDep rhs = (ICFBamDelDep)obj;
			if( getOptionalDefSchemaId() != null ) {
				if( rhs.getOptionalDefSchemaId() != null ) {
					cmp = getOptionalDefSchemaId().compareTo( rhs.getOptionalDefSchemaId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDefSchemaId() != null ) {
					return( -1 );
				}
			}
			if (getRequiredRelationId() != null) {
				if (rhs.getRequiredRelationId() != null) {
					cmp = getRequiredRelationId().compareTo( rhs.getRequiredRelationId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredRelationId() != null) {
				return( -1 );
			}
            return( 0 );
        }
        else if (obj instanceof ICFBamScopeHPKey) {
        return( super.compareTo(obj) );
        }
        else if (obj instanceof ICFBamDelDepH) {
        cmp = super.compareTo(obj);
        if (cmp != 0) {
            return( cmp );
        }
        ICFBamDelDepH rhs = (ICFBamDelDepH)obj;
			if( getOptionalDefSchemaId() != null ) {
				if( rhs.getOptionalDefSchemaId() != null ) {
					cmp = getOptionalDefSchemaId().compareTo( rhs.getOptionalDefSchemaId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDefSchemaId() != null ) {
					return( -1 );
				}
			}
			if (getRequiredRelationId() != null) {
				if (rhs.getRequiredRelationId() != null) {
					cmp = getRequiredRelationId().compareTo( rhs.getRequiredRelationId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredRelationId() != null) {
				return( -1 );
			}
            return( 0 );
        }
        else if (obj instanceof ICFBamDelDepByDefSchemaIdxKey ) {
            ICFBamDelDepByDefSchemaIdxKey rhs = (ICFBamDelDepByDefSchemaIdxKey)obj;
			if( getOptionalDefSchemaId() != null ) {
				if( rhs.getOptionalDefSchemaId() != null ) {
					cmp = getOptionalDefSchemaId().compareTo( rhs.getOptionalDefSchemaId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDefSchemaId() != null ) {
					return( -1 );
				}
			}
            return( 0 );
        }
        else if (obj instanceof ICFBamDelDepByDelDepIdxKey ) {
            ICFBamDelDepByDelDepIdxKey rhs = (ICFBamDelDepByDelDepIdxKey)obj;
			if (getRequiredRelationId() != null) {
				if (rhs.getRequiredRelationId() != null) {
					cmp = getRequiredRelationId().compareTo( rhs.getRequiredRelationId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredRelationId() != null) {
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
		if( src instanceof ICFBamDelDep ) {
			setDelDep( (ICFBamDelDep)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamJpaDelDep" );
		}
    }

	@Override
    public void setDelDep( ICFBamDelDep src ) {
        super.setScope( src );
		setOptionalDefSchemaId( src.getOptionalDefSchemaId() );
		setRequiredRelationId( src.getRequiredRelationId() );
    }

	@Override
    public void set( ICFBamScopeH src ) {
		if( src instanceof ICFBamDelDepH ) {
			setDelDep( (ICFBamDelDepH)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamDelDepH" );
		}
    }

	@Override
    public void setDelDep( ICFBamDelDepH src ) {
        super.setScope( src );
		setOptionalDefSchemaId( src.getOptionalDefSchemaId() );
		setRequiredRelationId( src.getRequiredRelationId() );
    }

    public String getXmlAttrFragment() {
        String ret = super.getXmlAttrFragment() 
			+ " OptionalDefSchemaId=" + ( ( getOptionalDefSchemaId() == null ) ? "null" : "\"" + getOptionalDefSchemaId().toString() + "\"" )
			+ " RequiredRelationId=" + "\"" + getRequiredRelationId().toString() + "\"";
        return( ret );
    }

    public String toString() {
        String ret = "<CFBamJpaDelDepH" + getXmlAttrFragment() + "/>";
        return( ret );
    }
}
