// Description: Java 25 JPA implementation of UInt32Col history objects

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
 *  CFBamJpaUInt32ColH provides history objects matching the CFBamUInt32Col change history.
 */
@Entity
@Table(
    name = "uint32col_h", schema = "CFBam31",
    indexes = {
        @Index(name = "UInt32ColIdIdx_h", columnList = "auditClusterId, auditStamp, auditAction, requiredRevision, auditSessionId, Id", unique = true),
        @Index(name = "UInt32ColTableIdx_h", columnList = "TableId", unique = false)
    }
)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("43140")
@Transactional(Transactional.TxType.SUPPORTS)
@PersistenceContext(unitName = "CFBamPU")
public class CFBamJpaUInt32ColH extends CFBamJpaUInt32DefH
    implements ICFBamUInt32ColH
{
	@AttributeOverrides({
		@AttributeOverride(name="bytes", column = @Column( name="TableId", nullable=false, length=CFLibDbKeyHash256.HASH_LENGTH ) )
	})
	protected CFLibDbKeyHash256 requiredTableId;

    public CFBamJpaUInt32ColH() {
            super();
		requiredTableId = CFLibDbKeyHash256.fromHex( ICFBamUInt32Col.TABLEID_INIT_VALUE.toString() );
    }

    @Override
    public int getClassCode() {
            return( ICFBamUInt32Col.CLASS_CODE );
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
    public boolean equals( Object obj ) {
        if (obj == null) {
            return( false );
        }
        else if (obj instanceof ICFBamUInt32Col) {
            ICFBamUInt32Col rhs = (ICFBamUInt32Col)obj;
        if (!super.equals(obj)) {
            return( false );
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
            return( true );
        }
        else if (obj instanceof ICFBamUInt32ColH) {
			if (!super.equals(obj)) {
				return( false );
			}
            ICFBamUInt32ColH rhs = (ICFBamUInt32ColH)obj;
        if (!super.equals(obj)) {
            return( false );
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
            return( true );
        }
        else if (obj instanceof ICFBamValueHPKey) {
			return( super.equals(obj) );
        }
        else if (obj instanceof ICFBamUInt32ColByTableIdxKey) {
            ICFBamUInt32ColByTableIdxKey rhs = (ICFBamUInt32ColByTableIdxKey)obj;
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
        else {
			return( super.equals(obj) );
        }
    }
    
    @Override
    public int hashCode() {
        int hashCode = super.hashCode();
		hashCode = hashCode + getRequiredTableId().hashCode();
        return( hashCode & 0x7fffffff );
    }

    @Override
    public int compareTo( Object obj ) {
        int cmp;
        if (obj == null) {
            return( 1 );
        }
        else if (obj instanceof ICFBamUInt32Col) {
        cmp = super.compareTo(obj);
        if (cmp != 0) {
            return( cmp );
        }
        ICFBamUInt32Col rhs = (ICFBamUInt32Col)obj;
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
        else if (obj instanceof ICFBamValueHPKey) {
        return( super.compareTo(obj) );
        }
        else if (obj instanceof ICFBamUInt32ColH) {
        cmp = super.compareTo(obj);
        if (cmp != 0) {
            return( cmp );
        }
        ICFBamUInt32ColH rhs = (ICFBamUInt32ColH)obj;
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
        else if (obj instanceof ICFBamUInt32ColByTableIdxKey ) {
            ICFBamUInt32ColByTableIdxKey rhs = (ICFBamUInt32ColByTableIdxKey)obj;
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
        else {
        return( super.compareTo(obj) );
        }
    }
	@Override
    public void set( ICFBamValue src ) {
		if( src instanceof ICFBamUInt32Col ) {
			setUInt32Col( (ICFBamUInt32Col)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamJpaUInt32Col" );
		}
    }

	@Override
    public void setUInt32Col( ICFBamUInt32Col src ) {
        super.setUInt32Def( src );
		setRequiredTableId( src.getRequiredTableId() );
    }

	@Override
    public void set( ICFBamValueH src ) {
		if( src instanceof ICFBamUInt32ColH ) {
			setUInt32Col( (ICFBamUInt32ColH)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamUInt32ColH" );
		}
    }

	@Override
    public void setUInt32Col( ICFBamUInt32ColH src ) {
        super.setUInt32Def( src );
		setRequiredTableId( src.getRequiredTableId() );
    }

    public String getXmlAttrFragment() {
        String ret = super.getXmlAttrFragment() 
			+ " RequiredTableId=" + "\"" + getRequiredTableId().toString() + "\"";
        return( ret );
    }

    public String toString() {
        String ret = "<CFBamJpaUInt32ColH" + getXmlAttrFragment() + "/>";
        return( ret );
    }
}
