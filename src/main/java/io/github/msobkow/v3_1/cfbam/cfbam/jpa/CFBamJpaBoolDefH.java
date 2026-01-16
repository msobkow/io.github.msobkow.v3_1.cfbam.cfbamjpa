// Description: Java 25 JPA implementation of BoolDef history objects

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
 *  CFBamJpaBoolDefH provides history objects matching the CFBamBoolDef change history.
 */
@Entity
@Table(
    name = "booldef_h", schema = "CFBam31",
    indexes = {
        @Index(name = "BoolDefIdIdx_h", columnList = "auditClusterId, auditStamp, auditAction, requiredRevision, auditSessionId, Id", unique = true)
    }
)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("43021")
@Transactional(Transactional.TxType.SUPPORTS)
@PersistenceContext(unitName = "CFBamPU")
public class CFBamJpaBoolDefH extends CFBamJpaAtomH
    implements ICFBamBoolDefH
{
	@Column( name="InitVal", nullable=true )
	protected Boolean optionalInitValue;
	@Column( name="FalseString", nullable=true, length=32 )
	protected String optionalFalseString;
	@Column( name="TrueString", nullable=true, length=32 )
	protected String optionalTrueString;
	@Column( name="NullString", nullable=true, length=32 )
	protected String optionalNullString;

    public CFBamJpaBoolDefH() {
            super();
		optionalInitValue = null;
		optionalFalseString = null;
		optionalTrueString = null;
		optionalNullString = null;
    }

    @Override
    public int getClassCode() {
            return( ICFBamBoolDef.CLASS_CODE );
    }

	@Override
	public Boolean getOptionalInitValue() {
		return( optionalInitValue );
	}

	@Override
	public void setOptionalInitValue( Boolean value ) {
		optionalInitValue = value;
	}

	@Override
	public String getOptionalFalseString() {
		return( optionalFalseString );
	}

	@Override
	public void setOptionalFalseString( String value ) {
		if( value != null && value.length() > 32 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setOptionalFalseString",
				1,
				"value.length()",
				value.length(),
				32 );
		}
		optionalFalseString = value;
	}

	@Override
	public String getOptionalTrueString() {
		return( optionalTrueString );
	}

	@Override
	public void setOptionalTrueString( String value ) {
		if( value != null && value.length() > 32 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setOptionalTrueString",
				1,
				"value.length()",
				value.length(),
				32 );
		}
		optionalTrueString = value;
	}

	@Override
	public String getOptionalNullString() {
		return( optionalNullString );
	}

	@Override
	public void setOptionalNullString( String value ) {
		if( value != null && value.length() > 32 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setOptionalNullString",
				1,
				"value.length()",
				value.length(),
				32 );
		}
		optionalNullString = value;
	}

    @Override
    public boolean equals( Object obj ) {
        if (obj == null) {
            return( false );
        }
        else if (obj instanceof ICFBamBoolDef) {
            ICFBamBoolDef rhs = (ICFBamBoolDef)obj;
        if (!super.equals(obj)) {
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
			if( getOptionalFalseString() != null ) {
				if( rhs.getOptionalFalseString() != null ) {
					if( ! getOptionalFalseString().equals( rhs.getOptionalFalseString() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalFalseString() != null ) {
					return( false );
				}
			}
			if( getOptionalTrueString() != null ) {
				if( rhs.getOptionalTrueString() != null ) {
					if( ! getOptionalTrueString().equals( rhs.getOptionalTrueString() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalTrueString() != null ) {
					return( false );
				}
			}
			if( getOptionalNullString() != null ) {
				if( rhs.getOptionalNullString() != null ) {
					if( ! getOptionalNullString().equals( rhs.getOptionalNullString() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalNullString() != null ) {
					return( false );
				}
			}
            return( true );
        }
        else if (obj instanceof ICFBamBoolDefH) {
			if (!super.equals(obj)) {
				return( false );
			}
            ICFBamBoolDefH rhs = (ICFBamBoolDefH)obj;
        if (!super.equals(obj)) {
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
			if( getOptionalFalseString() != null ) {
				if( rhs.getOptionalFalseString() != null ) {
					if( ! getOptionalFalseString().equals( rhs.getOptionalFalseString() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalFalseString() != null ) {
					return( false );
				}
			}
			if( getOptionalTrueString() != null ) {
				if( rhs.getOptionalTrueString() != null ) {
					if( ! getOptionalTrueString().equals( rhs.getOptionalTrueString() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalTrueString() != null ) {
					return( false );
				}
			}
			if( getOptionalNullString() != null ) {
				if( rhs.getOptionalNullString() != null ) {
					if( ! getOptionalNullString().equals( rhs.getOptionalNullString() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalNullString() != null ) {
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
		if( getOptionalInitValue() != null ) {
			if( getOptionalInitValue() ) {
				hashCode = ( hashCode * 4 ) + 1;
			}
			else {
				hashCode = hashCode * 4;
			}
		}
		else {
			hashCode = (hashCode * 4 ) + 3;
		}
		if( getOptionalFalseString() != null ) {
			hashCode = hashCode + getOptionalFalseString().hashCode();
		}
		if( getOptionalTrueString() != null ) {
			hashCode = hashCode + getOptionalTrueString().hashCode();
		}
		if( getOptionalNullString() != null ) {
			hashCode = hashCode + getOptionalNullString().hashCode();
		}
        return( hashCode & 0x7fffffff );
    }

    @Override
    public int compareTo( Object obj ) {
        int cmp;
        if (obj == null) {
            return( 1 );
        }
        else if (obj instanceof ICFBamBoolDef) {
        cmp = super.compareTo(obj);
        if (cmp != 0) {
            return( cmp );
        }
        ICFBamBoolDef rhs = (ICFBamBoolDef)obj;
			if( getOptionalInitValue() != null ) {
				Boolean lhsInitValue = getOptionalInitValue();
				if( rhs.getOptionalInitValue() ) {
					Boolean rhsInitValue = rhs.getOptionalInitValue();
					if( lhsInitValue ) {
						if( ! rhsInitValue ) {
							return( 1 );
						}
					}
					else {
						if( rhsInitValue ) {
							return( -1 );
						}
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
			if( getOptionalFalseString() != null ) {
				if( rhs.getOptionalFalseString() != null ) {
					cmp = getOptionalFalseString().compareTo( rhs.getOptionalFalseString() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalFalseString() != null ) {
					return( -1 );
				}
			}
			if( getOptionalTrueString() != null ) {
				if( rhs.getOptionalTrueString() != null ) {
					cmp = getOptionalTrueString().compareTo( rhs.getOptionalTrueString() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalTrueString() != null ) {
					return( -1 );
				}
			}
			if( getOptionalNullString() != null ) {
				if( rhs.getOptionalNullString() != null ) {
					cmp = getOptionalNullString().compareTo( rhs.getOptionalNullString() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalNullString() != null ) {
					return( -1 );
				}
			}
            return( 0 );
        }
        else if (obj instanceof ICFBamValueHPKey) {
        return( super.compareTo(obj) );
        }
        else if (obj instanceof ICFBamBoolDefH) {
        cmp = super.compareTo(obj);
        if (cmp != 0) {
            return( cmp );
        }
        ICFBamBoolDefH rhs = (ICFBamBoolDefH)obj;
			if( getOptionalInitValue() != null ) {
				Boolean lhsInitValue = getOptionalInitValue();
				if( rhs.getOptionalInitValue() ) {
					Boolean rhsInitValue = rhs.getOptionalInitValue();
					if( lhsInitValue ) {
						if( ! rhsInitValue ) {
							return( 1 );
						}
					}
					else {
						if( rhsInitValue ) {
							return( -1 );
						}
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
			if( getOptionalFalseString() != null ) {
				if( rhs.getOptionalFalseString() != null ) {
					cmp = getOptionalFalseString().compareTo( rhs.getOptionalFalseString() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalFalseString() != null ) {
					return( -1 );
				}
			}
			if( getOptionalTrueString() != null ) {
				if( rhs.getOptionalTrueString() != null ) {
					cmp = getOptionalTrueString().compareTo( rhs.getOptionalTrueString() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalTrueString() != null ) {
					return( -1 );
				}
			}
			if( getOptionalNullString() != null ) {
				if( rhs.getOptionalNullString() != null ) {
					cmp = getOptionalNullString().compareTo( rhs.getOptionalNullString() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalNullString() != null ) {
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
		if( src instanceof ICFBamBoolDef ) {
			setBoolDef( (ICFBamBoolDef)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamJpaBoolDef" );
		}
    }

	@Override
    public void setBoolDef( ICFBamBoolDef src ) {
        super.setAtom( src );
		setOptionalInitValue( src.getOptionalInitValue() );
		setOptionalFalseString( src.getOptionalFalseString() );
		setOptionalTrueString( src.getOptionalTrueString() );
		setOptionalNullString( src.getOptionalNullString() );
    }

	@Override
    public void set( ICFBamValueH src ) {
		if( src instanceof ICFBamBoolDefH ) {
			setBoolDef( (ICFBamBoolDefH)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamBoolDefH" );
		}
    }

	@Override
    public void setBoolDef( ICFBamBoolDefH src ) {
        super.setAtom( src );
		setOptionalInitValue( src.getOptionalInitValue() );
		setOptionalFalseString( src.getOptionalFalseString() );
		setOptionalTrueString( src.getOptionalTrueString() );
		setOptionalNullString( src.getOptionalNullString() );
    }

    public String getXmlAttrFragment() {
        String ret = super.getXmlAttrFragment() 
			+ " OptionalInitValue=" + ( ( getOptionalInitValue() == null ) ? "null" : ( ( getOptionalInitValue() ) ? "\"true\"" : "\"false\"" ) )
			+ " OptionalFalseString=" + ( ( getOptionalFalseString() == null ) ? "null" : "\"" + StringEscapeUtils.escapeXml11( getOptionalFalseString() ) + "\"" )
			+ " OptionalTrueString=" + ( ( getOptionalTrueString() == null ) ? "null" : "\"" + StringEscapeUtils.escapeXml11( getOptionalTrueString() ) + "\"" )
			+ " OptionalNullString=" + ( ( getOptionalNullString() == null ) ? "null" : "\"" + StringEscapeUtils.escapeXml11( getOptionalNullString() ) + "\"" );
        return( ret );
    }

    public String toString() {
        String ret = "<CFBamJpaBoolDefH" + getXmlAttrFragment() + "/>";
        return( ret );
    }
}
