// Description: Java 25 JPA implementation of ServerProc history objects

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
 *  CFBamJpaServerProcH provides history objects matching the CFBamServerProc change history.
 */
@Entity
@Table(
    name = "srvprc_h", schema = "CFBam31",
    indexes = {
        @Index(name = "ServerProcIdIdx_h", columnList = "auditClusterId, auditStamp, auditAction, requiredRevision, auditSessionId, Id", unique = true)
    }
)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("43015")
@Transactional(Transactional.TxType.SUPPORTS)
@PersistenceContext(unitName = "CFBamPU")
public class CFBamJpaServerProcH extends CFBamJpaServerMethodH
    implements ICFBamServerProcH
{
	@Column( name="dmy", nullable=true, length=16 )
	protected String optionalDummy;

    public CFBamJpaServerProcH() {
            super();
		optionalDummy = null;
    }

    @Override
    public int getClassCode() {
            return( ICFBamServerProc.CLASS_CODE );
    }

	@Override
	public String getOptionalDummy() {
		return( optionalDummy );
	}

	@Override
	public void setOptionalDummy( String value ) {
		if( value != null && value.length() > 16 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setOptionalDummy",
				1,
				"value.length()",
				value.length(),
				16 );
		}
		optionalDummy = value;
	}

    @Override
    public boolean equals( Object obj ) {
        if (obj == null) {
            return( false );
        }
        else if (obj instanceof ICFBamServerProc) {
            ICFBamServerProc rhs = (ICFBamServerProc)obj;
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
        else if (obj instanceof ICFBamServerProcH) {
			if (!super.equals(obj)) {
				return( false );
			}
            ICFBamServerProcH rhs = (ICFBamServerProcH)obj;
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
        else if (obj instanceof ICFBamScopeHPKey) {
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
        else if (obj instanceof ICFBamServerProc) {
        cmp = super.compareTo(obj);
        if (cmp != 0) {
            return( cmp );
        }
        ICFBamServerProc rhs = (ICFBamServerProc)obj;
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
        else if (obj instanceof ICFBamScopeHPKey) {
        return( super.compareTo(obj) );
        }
        else if (obj instanceof ICFBamServerProcH) {
        cmp = super.compareTo(obj);
        if (cmp != 0) {
            return( cmp );
        }
        ICFBamServerProcH rhs = (ICFBamServerProcH)obj;
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
    public void set( ICFBamScope src ) {
		if( src instanceof ICFBamServerProc ) {
			setServerProc( (ICFBamServerProc)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamJpaServerProc" );
		}
    }

	@Override
    public void setServerProc( ICFBamServerProc src ) {
        super.setServerMethod( src );
		setOptionalDummy( src.getOptionalDummy() );
    }

	@Override
    public void set( ICFBamScopeH src ) {
		if( src instanceof ICFBamServerProcH ) {
			setServerProc( (ICFBamServerProcH)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamServerProcH" );
		}
    }

	@Override
    public void setServerProc( ICFBamServerProcH src ) {
        super.setServerMethod( src );
		setOptionalDummy( src.getOptionalDummy() );
    }

    public String getXmlAttrFragment() {
        String ret = super.getXmlAttrFragment() 
			+ " OptionalDummy=" + ( ( getOptionalDummy() == null ) ? "null" : "\"" + StringEscapeUtils.escapeXml11( getOptionalDummy() ) + "\"" );
        return( ret );
    }

    public String toString() {
        String ret = "<CFBamJpaServerProcH" + getXmlAttrFragment() + "/>";
        return( ret );
    }
}
