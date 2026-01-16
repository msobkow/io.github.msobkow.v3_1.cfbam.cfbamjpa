// Description: Java 25 JPA implementation of DateDef history objects

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
 *  CFBamJpaDateDefH provides history objects matching the CFBamDateDef change history.
 */
@Entity
@Table(
    name = "dtdef_h", schema = "CFBam31",
    indexes = {
        @Index(name = "DateDefIdIdx_h", columnList = "auditClusterId, auditStamp, auditAction, requiredRevision, auditSessionId, Id", unique = true)
    }
)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("43029")
@Transactional(Transactional.TxType.SUPPORTS)
@PersistenceContext(unitName = "CFBamPU")
public class CFBamJpaDateDefH extends CFBamJpaAtomH
    implements ICFBamDateDefH
{
	@Column( name="Dummy", nullable=true, length=4 )
	protected String optionalDummy;

    public CFBamJpaDateDefH() {
            super();
		optionalDummy = null;
    }

    @Override
    public int getClassCode() {
            return( ICFBamDateDef.CLASS_CODE );
    }

	@Override
	public String getOptionalDummy() {
		return( optionalDummy );
	}

	@Override
	public void setOptionalDummy( String value ) {
		if( value != null && value.length() > 4 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setOptionalDummy",
				1,
				"value.length()",
				value.length(),
				4 );
		}
		optionalDummy = value;
	}

    @Override
    public boolean equals( Object obj ) {
        if (obj == null) {
            return( false );
        }
        else if (obj instanceof ICFBamDateDef) {
            ICFBamDateDef rhs = (ICFBamDateDef)obj;
        if (!super.equals(obj)) {
            return( false );
        }
			if( getOptionalDummy() != null ) {
				if( rhs.getOptionalDummy() != null ) {
					if( ! getOptionalDummy().equals( rhs.getOptionalDummy() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDummy() != null ) {
					return( false );
				}
			}
            return( true );
        }
        else if (obj instanceof ICFBamDateDefH) {
			if (!super.equals(obj)) {
				return( false );
			}
            ICFBamDateDefH rhs = (ICFBamDateDefH)obj;
        if (!super.equals(obj)) {
            return( false );
        }
			if( getOptionalDummy() != null ) {
				if( rhs.getOptionalDummy() != null ) {
					if( ! getOptionalDummy().equals( rhs.getOptionalDummy() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDummy() != null ) {
					return( false );
				}
			}
            return( true );
        }
        else if (obj instanceof ICFBamValueHPKey) {
			return( super.equals(obj) );
        }
        else {
			return( super.equals(obj) );
        }
    }
    
    @Override
    public int hashCode() {
        int hashCode = super.hashCode();
		if( getOptionalDummy() != null ) {
			hashCode = hashCode + getOptionalDummy().hashCode();
		}
        return( hashCode & 0x7fffffff );
    }

    @Override
    public int compareTo( Object obj ) {
        int cmp;
        if (obj == null) {
            return( 1 );
        }
        else if (obj instanceof ICFBamDateDef) {
        cmp = super.compareTo(obj);
        if (cmp != 0) {
            return( cmp );
        }
        ICFBamDateDef rhs = (ICFBamDateDef)obj;
			if( getOptionalDummy() != null ) {
				if( rhs.getOptionalDummy() != null ) {
					cmp = getOptionalDummy().compareTo( rhs.getOptionalDummy() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDummy() != null ) {
					return( -1 );
				}
			}
            return( 0 );
        }
        else if (obj instanceof ICFBamValueHPKey) {
        return( super.compareTo(obj) );
        }
        else if (obj instanceof ICFBamDateDefH) {
        cmp = super.compareTo(obj);
        if (cmp != 0) {
            return( cmp );
        }
        ICFBamDateDefH rhs = (ICFBamDateDefH)obj;
			if( getOptionalDummy() != null ) {
				if( rhs.getOptionalDummy() != null ) {
					cmp = getOptionalDummy().compareTo( rhs.getOptionalDummy() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDummy() != null ) {
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
    public void set( ICFBamValue src ) {
		if( src instanceof ICFBamDateDef ) {
			setDateDef( (ICFBamDateDef)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamJpaDateDef" );
		}
    }

	@Override
    public void setDateDef( ICFBamDateDef src ) {
        super.setAtom( src );
		setOptionalDummy( src.getOptionalDummy() );
    }

	@Override
    public void set( ICFBamValueH src ) {
		if( src instanceof ICFBamDateDefH ) {
			setDateDef( (ICFBamDateDefH)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamDateDefH" );
		}
    }

	@Override
    public void setDateDef( ICFBamDateDefH src ) {
        super.setAtom( src );
		setOptionalDummy( src.getOptionalDummy() );
    }

    public String getXmlAttrFragment() {
        String ret = super.getXmlAttrFragment() 
			+ " OptionalDummy=" + ( ( getOptionalDummy() == null ) ? "null" : "\"" + StringEscapeUtils.escapeXml11( getOptionalDummy() ) + "\"" );
        return( ret );
    }

    public String toString() {
        String ret = "<CFBamJpaDateDefH" + getXmlAttrFragment() + "/>";
        return( ret );
    }
}
