// Description: Java 25 JPA implementation of TZDateCol history objects

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
 *  CFBamJpaTZDateColH provides history objects matching the CFBamTZDateCol change history.
 */
@Entity
@Table(
    name = "dzcol_h", schema = "CFBam31",
    indexes = {
        @Index(name = "TZDateColIdIdx_h", columnList = "auditClusterId, auditStamp, auditAction, requiredRevision, auditSessionId, Id", unique = true),
        @Index(name = "TZDateColTableIdx_h", columnList = "TableId", unique = false)
    }
)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("43132")
@Transactional(Transactional.TxType.SUPPORTS)
@PersistenceContext(unitName = "CFBamPU")
public class CFBamJpaTZDateColH extends CFBamJpaTZDateDefH
    implements ICFBamTZDateColH
{
	@AttributeOverrides({
		@AttributeOverride(name="bytes", column = @Column( name="TableId", nullable=false, length=CFLibDbKeyHash256.HASH_LENGTH ) )
	})
	protected CFLibDbKeyHash256 requiredTableId;

    public CFBamJpaTZDateColH() {
            super();
		requiredTableId = CFLibDbKeyHash256.fromHex( ICFBamTZDateCol.TABLEID_INIT_VALUE.toString() );
    }

    @Override
    public int getClassCode() {
            return( ICFBamTZDateCol.CLASS_CODE );
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
        else if (obj instanceof ICFBamTZDateCol) {
            ICFBamTZDateCol rhs = (ICFBamTZDateCol)obj;
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
        else if (obj instanceof ICFBamTZDateColH) {
			if (!super.equals(obj)) {
				return( false );
			}
            ICFBamTZDateColH rhs = (ICFBamTZDateColH)obj;
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
        else if (obj instanceof ICFBamTZDateColByTableIdxKey) {
            ICFBamTZDateColByTableIdxKey rhs = (ICFBamTZDateColByTableIdxKey)obj;
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
        else if (obj instanceof ICFBamTZDateCol) {
        cmp = super.compareTo(obj);
        if (cmp != 0) {
            return( cmp );
        }
        ICFBamTZDateCol rhs = (ICFBamTZDateCol)obj;
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
        else if (obj instanceof ICFBamTZDateColH) {
        cmp = super.compareTo(obj);
        if (cmp != 0) {
            return( cmp );
        }
        ICFBamTZDateColH rhs = (ICFBamTZDateColH)obj;
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
        else if (obj instanceof ICFBamTZDateColByTableIdxKey ) {
            ICFBamTZDateColByTableIdxKey rhs = (ICFBamTZDateColByTableIdxKey)obj;
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
		if( src instanceof ICFBamTZDateCol ) {
			setTZDateCol( (ICFBamTZDateCol)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamJpaTZDateCol" );
		}
    }

	@Override
    public void setTZDateCol( ICFBamTZDateCol src ) {
        super.setTZDateDef( src );
		setRequiredTableId( src.getRequiredTableId() );
    }

	@Override
    public void set( ICFBamValueH src ) {
		if( src instanceof ICFBamTZDateColH ) {
			setTZDateCol( (ICFBamTZDateColH)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamTZDateColH" );
		}
    }

	@Override
    public void setTZDateCol( ICFBamTZDateColH src ) {
        super.setTZDateDef( src );
		setRequiredTableId( src.getRequiredTableId() );
    }

    public String getXmlAttrFragment() {
        String ret = super.getXmlAttrFragment() 
			+ " RequiredTableId=" + "\"" + getRequiredTableId().toString() + "\"";
        return( ret );
    }

    public String toString() {
        String ret = "<CFBamJpaTZDateColH" + getXmlAttrFragment() + "/>";
        return( ret );
    }
}
