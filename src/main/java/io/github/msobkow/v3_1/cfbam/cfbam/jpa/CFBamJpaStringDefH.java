// Description: Java 25 JPA implementation of StringDef history objects

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
 *  CFBamJpaStringDefH provides history objects matching the CFBamStringDef change history.
 */
@Entity
@Table(
    name = "strdef_h", schema = "CFBam31",
    indexes = {
        @Index(name = "StringDefIdIdx_h", columnList = "auditClusterId, auditStamp, auditAction, requiredRevision, auditSessionId, Id", unique = true)
    }
)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("43088")
@Transactional(Transactional.TxType.SUPPORTS)
@PersistenceContext(unitName = "CFBamPU")
public class CFBamJpaStringDefH extends CFBamJpaAtomH
    implements ICFBamStringDefH
{
	@Column( name="db_max_len", nullable=false )
	protected int requiredMaxLen;
	@Column( name="is_polymorph", nullable=false )
	protected boolean requiredIsPolymorph;
	@Column( name="InitVal", nullable=true, length=500 )
	protected String optionalInitValue;

    public CFBamJpaStringDefH() {
            super();
		requiredMaxLen = ICFBamStringDef.MAXLEN_INIT_VALUE;
		requiredIsPolymorph = ICFBamStringDef.ISPOLYMORPH_INIT_VALUE;
		optionalInitValue = null;
    }

    @Override
    public int getClassCode() {
            return( ICFBamStringDef.CLASS_CODE );
    }

	@Override
	public int getRequiredMaxLen() {
		return( requiredMaxLen );
	}

	@Override
	public void setRequiredMaxLen( int value ) {
		if( value < ICFBamStringDef.MAXLEN_MIN_VALUE ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				"setRequiredMaxLen",
				1,
				"value",
				value,
				ICFBamStringDef.MAXLEN_MIN_VALUE );
		}
		if( value > ICFBamStringDef.MAXLEN_MAX_VALUE ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setRequiredMaxLen",
				1,
				"value",
				value,
				ICFBamStringDef.MAXLEN_MAX_VALUE );
		}
		requiredMaxLen = value;
	}

	@Override
	public boolean getRequiredIsPolymorph() {
		return( requiredIsPolymorph );
	}

	@Override
	public void setRequiredIsPolymorph( boolean value ) {
		requiredIsPolymorph = value;
	}

	@Override
	public String getOptionalInitValue() {
		return( optionalInitValue );
	}

	@Override
	public void setOptionalInitValue( String value ) {
		if( value != null && value.length() > 500 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setOptionalInitValue",
				1,
				"value.length()",
				value.length(),
				500 );
		}
		optionalInitValue = value;
	}

    @Override
    public boolean equals( Object obj ) {
        if (obj == null) {
            return( false );
        }
        else if (obj instanceof ICFBamStringDef) {
            ICFBamStringDef rhs = (ICFBamStringDef)obj;
        if (!super.equals(obj)) {
            return( false );
        }
			if( getRequiredMaxLen() != rhs.getRequiredMaxLen() ) {
				return( false );
			}
			if( getRequiredIsPolymorph() != rhs.getRequiredIsPolymorph() ) {
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
            return( true );
        }
        else if (obj instanceof ICFBamStringDefH) {
			if (!super.equals(obj)) {
				return( false );
			}
            ICFBamStringDefH rhs = (ICFBamStringDefH)obj;
        if (!super.equals(obj)) {
            return( false );
        }
			if( getRequiredMaxLen() != rhs.getRequiredMaxLen() ) {
				return( false );
			}
			if( getRequiredIsPolymorph() != rhs.getRequiredIsPolymorph() ) {
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
		if( getRequiredIsPolymorph() ) {
			hashCode = ( hashCode * 2 ) + 1;
		}
		else {
			hashCode = hashCode * 2;
		}
		if( getOptionalInitValue() != null ) {
			hashCode = hashCode + getOptionalInitValue().hashCode();
		}
        return( hashCode & 0x7fffffff );
    }

    @Override
    public int compareTo( Object obj ) {
        int cmp;
        if (obj == null) {
            return( 1 );
        }
        else if (obj instanceof ICFBamStringDef) {
        cmp = super.compareTo(obj);
        if (cmp != 0) {
            return( cmp );
        }
        ICFBamStringDef rhs = (ICFBamStringDef)obj;
			if( getRequiredMaxLen() < rhs.getRequiredMaxLen() ) {
				return( -1 );
			}
			else if( getRequiredMaxLen() > rhs.getRequiredMaxLen() ) {
				return( 1 );
			}
			if( getRequiredIsPolymorph() ) {
				if( ! rhs.getRequiredIsPolymorph() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredIsPolymorph() ) {
					return( -1 );
				}
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
            return( 0 );
        }
        else if (obj instanceof ICFBamValueHPKey) {
        return( super.compareTo(obj) );
        }
        else if (obj instanceof ICFBamStringDefH) {
        cmp = super.compareTo(obj);
        if (cmp != 0) {
            return( cmp );
        }
        ICFBamStringDefH rhs = (ICFBamStringDefH)obj;
			if( getRequiredMaxLen() < rhs.getRequiredMaxLen() ) {
				return( -1 );
			}
			else if( getRequiredMaxLen() > rhs.getRequiredMaxLen() ) {
				return( 1 );
			}
			if( getRequiredIsPolymorph() ) {
				if( ! rhs.getRequiredIsPolymorph() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredIsPolymorph() ) {
					return( -1 );
				}
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
            return( 0 );
        }
        else {
        return( super.compareTo(obj) );
        }
    }
	@Override
    public void set( ICFBamValue src ) {
		if( src instanceof ICFBamStringDef ) {
			setStringDef( (ICFBamStringDef)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamJpaStringDef" );
		}
    }

	@Override
    public void setStringDef( ICFBamStringDef src ) {
        super.setAtom( src );
		setRequiredMaxLen( src.getRequiredMaxLen() );
		setRequiredIsPolymorph( src.getRequiredIsPolymorph() );
		setOptionalInitValue( src.getOptionalInitValue() );
    }

	@Override
    public void set( ICFBamValueH src ) {
		if( src instanceof ICFBamStringDefH ) {
			setStringDef( (ICFBamStringDefH)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamStringDefH" );
		}
    }

	@Override
    public void setStringDef( ICFBamStringDefH src ) {
        super.setAtom( src );
		setRequiredMaxLen( src.getRequiredMaxLen() );
		setRequiredIsPolymorph( src.getRequiredIsPolymorph() );
		setOptionalInitValue( src.getOptionalInitValue() );
    }

    public String getXmlAttrFragment() {
        String ret = super.getXmlAttrFragment() 
			+ " RequiredMaxLen=" + "\"" + Integer.toString( getRequiredMaxLen() ) + "\""
			+ " RequiredIsPolymorph=" + (( getRequiredIsPolymorph() ) ? "\"true\"" : "\"false\"" )
			+ " OptionalInitValue=" + ( ( getOptionalInitValue() == null ) ? "null" : "\"" + StringEscapeUtils.escapeXml11( getOptionalInitValue() ) + "\"" );
        return( ret );
    }

    public String toString() {
        String ret = "<CFBamJpaStringDefH" + getXmlAttrFragment() + "/>";
        return( ret );
    }
}
