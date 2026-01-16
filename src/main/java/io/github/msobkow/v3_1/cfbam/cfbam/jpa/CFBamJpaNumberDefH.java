// Description: Java 25 JPA implementation of NumberDef history objects

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
 *  CFBamJpaNumberDefH provides history objects matching the CFBamNumberDef change history.
 */
@Entity
@Table(
    name = "numdef_h", schema = "CFBam31",
    indexes = {
        @Index(name = "NumberDefIdIdx_h", columnList = "auditClusterId, auditStamp, auditAction, requiredRevision, auditSessionId, Id", unique = true)
    }
)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("43053")
@Transactional(Transactional.TxType.SUPPORTS)
@PersistenceContext(unitName = "CFBamPU")
public class CFBamJpaNumberDefH extends CFBamJpaAtomH
    implements ICFBamNumberDefH
{
	@Column( name="Digits", nullable=false )
	protected short requiredDigits;
	@Column( name="Precis", nullable=false )
	protected short requiredPrecis;
	@Column( name="InitVal", nullable=true, scale=31, precision=5 )
	protected BigDecimal optionalInitValue;
	@Column( name="MinVal", nullable=true, scale=31, precision=5 )
	protected BigDecimal optionalMinValue;
	@Column( name="MaxVal", nullable=true, scale=31, precision=5 )
	protected BigDecimal optionalMaxValue;

    public CFBamJpaNumberDefH() {
            super();
		requiredDigits = ICFBamNumberDef.DIGITS_INIT_VALUE;
		requiredPrecis = ICFBamNumberDef.PRECIS_INIT_VALUE;
		optionalInitValue = null;
		optionalMinValue = null;
		optionalMaxValue = null;
    }

    @Override
    public int getClassCode() {
            return( ICFBamNumberDef.CLASS_CODE );
    }

	@Override
	public short getRequiredDigits() {
		return( requiredDigits );
	}

	@Override
	public void setRequiredDigits( short value ) {
		if( value < ICFBamNumberDef.DIGITS_MIN_VALUE ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				"setRequiredDigits",
				1,
				"value",
				value,
				ICFBamNumberDef.DIGITS_MIN_VALUE );
		}
		if( value > ICFBamNumberDef.DIGITS_MAX_VALUE ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setRequiredDigits",
				1,
				"value",
				value,
				ICFBamNumberDef.DIGITS_MAX_VALUE );
		}
		requiredDigits = value;
	}

	@Override
	public short getRequiredPrecis() {
		return( requiredPrecis );
	}

	@Override
	public void setRequiredPrecis( short value ) {
		if( value < ICFBamNumberDef.PRECIS_MIN_VALUE ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				"setRequiredPrecis",
				1,
				"value",
				value,
				ICFBamNumberDef.PRECIS_MIN_VALUE );
		}
		if( value > ICFBamNumberDef.PRECIS_MAX_VALUE ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setRequiredPrecis",
				1,
				"value",
				value,
				ICFBamNumberDef.PRECIS_MAX_VALUE );
		}
		requiredPrecis = value;
	}

	@Override
	public BigDecimal getOptionalInitValue() {
		return( optionalInitValue );
	}

	@Override
	public void setOptionalInitValue( BigDecimal value ) {
		optionalInitValue = value;
	}

	@Override
	public BigDecimal getOptionalMinValue() {
		return( optionalMinValue );
	}

	@Override
	public void setOptionalMinValue( BigDecimal value ) {
		optionalMinValue = value;
	}

	@Override
	public BigDecimal getOptionalMaxValue() {
		return( optionalMaxValue );
	}

	@Override
	public void setOptionalMaxValue( BigDecimal value ) {
		optionalMaxValue = value;
	}

    @Override
    public boolean equals( Object obj ) {
        if (obj == null) {
            return( false );
        }
        else if (obj instanceof ICFBamNumberDef) {
            ICFBamNumberDef rhs = (ICFBamNumberDef)obj;
        if (!super.equals(obj)) {
            return( false );
        }
			if( getRequiredDigits() != rhs.getRequiredDigits() ) {
				return( false );
			}
			if( getRequiredPrecis() != rhs.getRequiredPrecis() ) {
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
			if( getOptionalMinValue() != null ) {
				if( rhs.getOptionalMinValue() != null ) {
					if( ! getOptionalMinValue().equals( rhs.getOptionalMinValue() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalMinValue() != null ) {
					return( false );
				}
			}
			if( getOptionalMaxValue() != null ) {
				if( rhs.getOptionalMaxValue() != null ) {
					if( ! getOptionalMaxValue().equals( rhs.getOptionalMaxValue() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalMaxValue() != null ) {
					return( false );
				}
			}
            return( true );
        }
        else if (obj instanceof ICFBamNumberDefH) {
			if (!super.equals(obj)) {
				return( false );
			}
            ICFBamNumberDefH rhs = (ICFBamNumberDefH)obj;
        if (!super.equals(obj)) {
            return( false );
        }
			if( getRequiredDigits() != rhs.getRequiredDigits() ) {
				return( false );
			}
			if( getRequiredPrecis() != rhs.getRequiredPrecis() ) {
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
			if( getOptionalMinValue() != null ) {
				if( rhs.getOptionalMinValue() != null ) {
					if( ! getOptionalMinValue().equals( rhs.getOptionalMinValue() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalMinValue() != null ) {
					return( false );
				}
			}
			if( getOptionalMaxValue() != null ) {
				if( rhs.getOptionalMaxValue() != null ) {
					if( ! getOptionalMaxValue().equals( rhs.getOptionalMaxValue() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalMaxValue() != null ) {
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
		hashCode = ( hashCode * 0x10000 ) + getRequiredDigits();
		hashCode = ( hashCode * 0x10000 ) + getRequiredPrecis();
		if( getOptionalInitValue() != null ) {
			hashCode = hashCode + getOptionalInitValue().hashCode();
		}
		if( getOptionalMinValue() != null ) {
			hashCode = hashCode + getOptionalMinValue().hashCode();
		}
		if( getOptionalMaxValue() != null ) {
			hashCode = hashCode + getOptionalMaxValue().hashCode();
		}
        return( hashCode & 0x7fffffff );
    }

    @Override
    public int compareTo( Object obj ) {
        int cmp;
        if (obj == null) {
            return( 1 );
        }
        else if (obj instanceof ICFBamNumberDef) {
        cmp = super.compareTo(obj);
        if (cmp != 0) {
            return( cmp );
        }
        ICFBamNumberDef rhs = (ICFBamNumberDef)obj;
			if( getRequiredDigits() < rhs.getRequiredDigits() ) {
				return( -1 );
			}
			else if( getRequiredDigits() > rhs.getRequiredDigits() ) {
				return( 1 );
			}
			if( getRequiredPrecis() < rhs.getRequiredPrecis() ) {
				return( -1 );
			}
			else if( getRequiredPrecis() > rhs.getRequiredPrecis() ) {
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
			if( getOptionalMinValue() != null ) {
				if( rhs.getOptionalMinValue() != null ) {
					cmp = getOptionalMinValue().compareTo( rhs.getOptionalMinValue() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalMinValue() != null ) {
					return( -1 );
				}
			}
			if( getOptionalMaxValue() != null ) {
				if( rhs.getOptionalMaxValue() != null ) {
					cmp = getOptionalMaxValue().compareTo( rhs.getOptionalMaxValue() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalMaxValue() != null ) {
					return( -1 );
				}
			}
            return( 0 );
        }
        else if (obj instanceof ICFBamValueHPKey) {
        return( super.compareTo(obj) );
        }
        else if (obj instanceof ICFBamNumberDefH) {
        cmp = super.compareTo(obj);
        if (cmp != 0) {
            return( cmp );
        }
        ICFBamNumberDefH rhs = (ICFBamNumberDefH)obj;
			if( getRequiredDigits() < rhs.getRequiredDigits() ) {
				return( -1 );
			}
			else if( getRequiredDigits() > rhs.getRequiredDigits() ) {
				return( 1 );
			}
			if( getRequiredPrecis() < rhs.getRequiredPrecis() ) {
				return( -1 );
			}
			else if( getRequiredPrecis() > rhs.getRequiredPrecis() ) {
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
			if( getOptionalMinValue() != null ) {
				if( rhs.getOptionalMinValue() != null ) {
					cmp = getOptionalMinValue().compareTo( rhs.getOptionalMinValue() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalMinValue() != null ) {
					return( -1 );
				}
			}
			if( getOptionalMaxValue() != null ) {
				if( rhs.getOptionalMaxValue() != null ) {
					cmp = getOptionalMaxValue().compareTo( rhs.getOptionalMaxValue() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalMaxValue() != null ) {
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
		if( src instanceof ICFBamNumberDef ) {
			setNumberDef( (ICFBamNumberDef)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamJpaNumberDef" );
		}
    }

	@Override
    public void setNumberDef( ICFBamNumberDef src ) {
        super.setAtom( src );
		setRequiredDigits( src.getRequiredDigits() );
		setRequiredPrecis( src.getRequiredPrecis() );
		setOptionalInitValue( src.getOptionalInitValue() );
		setOptionalMinValue( src.getOptionalMinValue() );
		setOptionalMaxValue( src.getOptionalMaxValue() );
    }

	@Override
    public void set( ICFBamValueH src ) {
		if( src instanceof ICFBamNumberDefH ) {
			setNumberDef( (ICFBamNumberDefH)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamNumberDefH" );
		}
    }

	@Override
    public void setNumberDef( ICFBamNumberDefH src ) {
        super.setAtom( src );
		setRequiredDigits( src.getRequiredDigits() );
		setRequiredPrecis( src.getRequiredPrecis() );
		setOptionalInitValue( src.getOptionalInitValue() );
		setOptionalMinValue( src.getOptionalMinValue() );
		setOptionalMaxValue( src.getOptionalMaxValue() );
    }

    public String getXmlAttrFragment() {
        String ret = super.getXmlAttrFragment() 
			+ " RequiredDigits=" + "\"" + Short.toString( getRequiredDigits() ) + "\""
			+ " RequiredPrecis=" + "\"" + Short.toString( getRequiredPrecis() ) + "\""
			+ " OptionalInitValue=" + ( ( getOptionalInitValue() == null ) ? "null" : "\"" + getOptionalInitValue().toString() + "\"" )
			+ " OptionalMinValue=" + ( ( getOptionalMinValue() == null ) ? "null" : "\"" + getOptionalMinValue().toString() + "\"" )
			+ " OptionalMaxValue=" + ( ( getOptionalMaxValue() == null ) ? "null" : "\"" + getOptionalMaxValue().toString() + "\"" );
        return( ret );
    }

    public String toString() {
        String ret = "<CFBamJpaNumberDefH" + getXmlAttrFragment() + "/>";
        return( ret );
    }
}
