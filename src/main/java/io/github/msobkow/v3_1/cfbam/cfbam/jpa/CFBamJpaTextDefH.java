// Description: Java 25 JPA implementation of TextDef history objects

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
 *  CFBamJpaTextDefH provides history objects matching the CFBamTextDef change history.
 */
@Entity
@Table(
    name = "txtdef_h", schema = "CFBam31",
    indexes = {
        @Index(name = "TextDefIdIdx_h", columnList = "auditClusterId, auditStamp, auditAction, requiredRevision, auditSessionId, Id", unique = true)
    }
)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("43097")
@Transactional(Transactional.TxType.SUPPORTS)
@PersistenceContext(unitName = "CFBamPU")
public class CFBamJpaTextDefH extends CFBamJpaAtomH
    implements ICFBamTextDefH
{
	@Column( name="db_max_len", nullable=false )
	protected int requiredMaxLen;
	@Column( name="InitVal", nullable=true, length=65535 )
	protected String optionalInitValue;
	@Column( name="xml_elt_name", nullable=true, length=192 )
	protected String optionalXmlElementName;

    public CFBamJpaTextDefH() {
            super();
		requiredMaxLen = ICFBamTextDef.MAXLEN_INIT_VALUE;
		optionalInitValue = null;
		optionalXmlElementName = null;
    }

    @Override
    public int getClassCode() {
            return( ICFBamTextDef.CLASS_CODE );
    }

	@Override
	public int getRequiredMaxLen() {
		return( requiredMaxLen );
	}

	@Override
	public void setRequiredMaxLen( int value ) {
		if( value < ICFBamTextDef.MAXLEN_MIN_VALUE ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				"setRequiredMaxLen",
				1,
				"value",
				value,
				ICFBamTextDef.MAXLEN_MIN_VALUE );
		}
		if( value > ICFBamTextDef.MAXLEN_MAX_VALUE ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setRequiredMaxLen",
				1,
				"value",
				value,
				ICFBamTextDef.MAXLEN_MAX_VALUE );
		}
		requiredMaxLen = value;
	}

	@Override
	public String getOptionalInitValue() {
		return( optionalInitValue );
	}

	@Override
	public void setOptionalInitValue( String value ) {
		if( value != null && value.length() > 65535 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setOptionalInitValue",
				1,
				"value.length()",
				value.length(),
				65535 );
		}
		optionalInitValue = value;
	}

	@Override
	public String getOptionalXmlElementName() {
		return( optionalXmlElementName );
	}

	@Override
	public void setOptionalXmlElementName( String value ) {
		if( value != null && value.length() > 192 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setOptionalXmlElementName",
				1,
				"value.length()",
				value.length(),
				192 );
		}
		optionalXmlElementName = value;
	}

    @Override
    public boolean equals( Object obj ) {
        if (obj == null) {
            return( false );
        }
        else if (obj instanceof ICFBamTextDef) {
            ICFBamTextDef rhs = (ICFBamTextDef)obj;
        if (!super.equals(obj)) {
            return( false );
        }
			if( getRequiredMaxLen() != rhs.getRequiredMaxLen() ) {
				return( false );
			}
			if( getOptionalInitValue() != null ) {
				if( rhs.getOptionalInitValue() != null ) {
					if( ! getOptionalInitValue().equals( rhs.getOptionalInitValue() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalInitValue() != null ) {
					return( false );
				}
			}
			if( getOptionalXmlElementName() != null ) {
				if( rhs.getOptionalXmlElementName() != null ) {
					if( ! getOptionalXmlElementName().equals( rhs.getOptionalXmlElementName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalXmlElementName() != null ) {
					return( false );
				}
			}
            return( true );
        }
        else if (obj instanceof ICFBamTextDefH) {
			if (!super.equals(obj)) {
				return( false );
			}
            ICFBamTextDefH rhs = (ICFBamTextDefH)obj;
        if (!super.equals(obj)) {
            return( false );
        }
			if( getRequiredMaxLen() != rhs.getRequiredMaxLen() ) {
				return( false );
			}
			if( getOptionalInitValue() != null ) {
				if( rhs.getOptionalInitValue() != null ) {
					if( ! getOptionalInitValue().equals( rhs.getOptionalInitValue() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalInitValue() != null ) {
					return( false );
				}
			}
			if( getOptionalXmlElementName() != null ) {
				if( rhs.getOptionalXmlElementName() != null ) {
					if( ! getOptionalXmlElementName().equals( rhs.getOptionalXmlElementName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalXmlElementName() != null ) {
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
		hashCode = hashCode + getRequiredMaxLen();
		if( getOptionalInitValue() != null ) {
			hashCode = hashCode + getOptionalInitValue().hashCode();
		}
		if( getOptionalXmlElementName() != null ) {
			hashCode = hashCode + getOptionalXmlElementName().hashCode();
		}
        return( hashCode & 0x7fffffff );
    }

    @Override
    public int compareTo( Object obj ) {
        int cmp;
        if (obj == null) {
            return( 1 );
        }
        else if (obj instanceof ICFBamTextDef) {
        cmp = super.compareTo(obj);
        if (cmp != 0) {
            return( cmp );
        }
        ICFBamTextDef rhs = (ICFBamTextDef)obj;
			if( getRequiredMaxLen() < rhs.getRequiredMaxLen() ) {
				return( -1 );
			}
			else if( getRequiredMaxLen() > rhs.getRequiredMaxLen() ) {
				return( 1 );
			}
			if( getOptionalInitValue() != null ) {
				if( rhs.getOptionalInitValue() != null ) {
					cmp = getOptionalInitValue().compareTo( rhs.getOptionalInitValue() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalInitValue() != null ) {
					return( -1 );
				}
			}
			if( getOptionalXmlElementName() != null ) {
				if( rhs.getOptionalXmlElementName() != null ) {
					cmp = getOptionalXmlElementName().compareTo( rhs.getOptionalXmlElementName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalXmlElementName() != null ) {
					return( -1 );
				}
			}
            return( 0 );
        }
        else if (obj instanceof ICFBamValueHPKey) {
        return( super.compareTo(obj) );
        }
        else if (obj instanceof ICFBamTextDefH) {
        cmp = super.compareTo(obj);
        if (cmp != 0) {
            return( cmp );
        }
        ICFBamTextDefH rhs = (ICFBamTextDefH)obj;
			if( getRequiredMaxLen() < rhs.getRequiredMaxLen() ) {
				return( -1 );
			}
			else if( getRequiredMaxLen() > rhs.getRequiredMaxLen() ) {
				return( 1 );
			}
			if( getOptionalInitValue() != null ) {
				if( rhs.getOptionalInitValue() != null ) {
					cmp = getOptionalInitValue().compareTo( rhs.getOptionalInitValue() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalInitValue() != null ) {
					return( -1 );
				}
			}
			if( getOptionalXmlElementName() != null ) {
				if( rhs.getOptionalXmlElementName() != null ) {
					cmp = getOptionalXmlElementName().compareTo( rhs.getOptionalXmlElementName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalXmlElementName() != null ) {
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
		if( src instanceof ICFBamTextDef ) {
			setTextDef( (ICFBamTextDef)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamJpaTextDef" );
		}
    }

	@Override
    public void setTextDef( ICFBamTextDef src ) {
        super.setAtom( src );
		setRequiredMaxLen( src.getRequiredMaxLen() );
		setOptionalInitValue( src.getOptionalInitValue() );
		setOptionalXmlElementName( src.getOptionalXmlElementName() );
    }

	@Override
    public void set( ICFBamValueH src ) {
		if( src instanceof ICFBamTextDefH ) {
			setTextDef( (ICFBamTextDefH)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamTextDefH" );
		}
    }

	@Override
    public void setTextDef( ICFBamTextDefH src ) {
        super.setAtom( src );
		setRequiredMaxLen( src.getRequiredMaxLen() );
		setOptionalInitValue( src.getOptionalInitValue() );
		setOptionalXmlElementName( src.getOptionalXmlElementName() );
    }

    public String getXmlAttrFragment() {
        String ret = super.getXmlAttrFragment() 
			+ " RequiredMaxLen=" + "\"" + Integer.toString( getRequiredMaxLen() ) + "\""
			+ " OptionalInitValue=" + ( ( getOptionalInitValue() == null ) ? "null" : "\"" + StringEscapeUtils.escapeXml11( getOptionalInitValue() ) + "\"" )
			+ " OptionalXmlElementName=" + ( ( getOptionalXmlElementName() == null ) ? "null" : "\"" + StringEscapeUtils.escapeXml11( getOptionalXmlElementName() ) + "\"" );
        return( ret );
    }

    public String toString() {
        String ret = "<CFBamJpaTextDefH" + getXmlAttrFragment() + "/>";
        return( ret );
    }
}
