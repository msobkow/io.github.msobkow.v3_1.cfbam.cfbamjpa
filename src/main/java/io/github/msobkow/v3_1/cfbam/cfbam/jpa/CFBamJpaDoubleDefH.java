// Description: Java 25 JPA implementation of DoubleDef history objects

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
 *  CFBamJpaDoubleDefH provides history objects matching the CFBamDoubleDef change history.
 */
@Entity
@Table(
    name = "dbldef_h", schema = "CFBam31",
    indexes = {
        @Index(name = "DoubleDefIdIdx_h", columnList = "auditClusterId, auditStamp, auditAction, requiredRevision, auditSessionId, Id", unique = true)
    }
)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("43036")
@Transactional(Transactional.TxType.SUPPORTS)
@PersistenceContext(unitName = "CFBamPU")
public class CFBamJpaDoubleDefH extends CFBamJpaAtomH
    implements ICFBamDoubleDefH
{
	@Column( name="InitVal", nullable=true )
	protected Double optionalInitValue;
	@Column( name="MinVal", nullable=true )
	protected Double optionalMinValue;
	@Column( name="MaxVal", nullable=true )
	protected Double optionalMaxValue;

    public CFBamJpaDoubleDefH() {
            super();
		optionalInitValue = null;
		optionalMinValue = null;
		optionalMaxValue = null;
    }

    @Override
    public int getClassCode() {
            return( ICFBamDoubleDef.CLASS_CODE );
    }

	@Override
	public Double getOptionalInitValue() {
		return( optionalInitValue );
	}

	@Override
	public void setOptionalInitValue( Double value ) {
		if( value < ICFBamDoubleDef.INITVALUE_MIN_VALUE ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				"setOptionalInitValue",
				1,
				"value",
				value,
				ICFBamDoubleDef.INITVALUE_MIN_VALUE );
		}
		else if( value > ICFBamDoubleDef.INITVALUE_MAX_VALUE ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setOptionalInitValue",
				1,
				"value",
				value,
				ICFBamDoubleDef.INITVALUE_MAX_VALUE );
		}
		optionalInitValue = value;
	}

	@Override
	public Double getOptionalMinValue() {
		return( optionalMinValue );
	}

	@Override
	public void setOptionalMinValue( Double value ) {
		if( value < ICFBamDoubleDef.MINVALUE_MIN_VALUE ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				"setOptionalMinValue",
				1,
				"value",
				value,
				ICFBamDoubleDef.MINVALUE_MIN_VALUE );
		}
		else if( value > ICFBamDoubleDef.MINVALUE_MAX_VALUE ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setOptionalMinValue",
				1,
				"value",
				value,
				ICFBamDoubleDef.MINVALUE_MAX_VALUE );
		}
		optionalMinValue = value;
	}

	@Override
	public Double getOptionalMaxValue() {
		return( optionalMaxValue );
	}

	@Override
	public void setOptionalMaxValue( Double value ) {
		if( value < ICFBamDoubleDef.MAXVALUE_MIN_VALUE ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				"setOptionalMaxValue",
				1,
				"value",
				value,
				ICFBamDoubleDef.MAXVALUE_MIN_VALUE );
		}
		else if( value > ICFBamDoubleDef.MAXVALUE_MAX_VALUE ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setOptionalMaxValue",
				1,
				"value",
				value,
				ICFBamDoubleDef.MAXVALUE_MAX_VALUE );
		}
		optionalMaxValue = value;
	}

    @Override
    public boolean equals( Object obj ) {
        if (obj == null) {
            return( false );
        }
        else if (obj instanceof ICFBamDoubleDef) {
            ICFBamDoubleDef rhs = (ICFBamDoubleDef)obj;
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
        else if (obj instanceof ICFBamDoubleDefH) {
			if (!super.equals(obj)) {
				return( false );
			}
            ICFBamDoubleDefH rhs = (ICFBamDoubleDefH)obj;
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
        else if (obj instanceof ICFBamDoubleDef) {
        cmp = super.compareTo(obj);
        if (cmp != 0) {
            return( cmp );
        }
        ICFBamDoubleDef rhs = (ICFBamDoubleDef)obj;
			if( getOptionalInitValue() != null ) {
				Double lhsInitValue = getOptionalInitValue();
				if( rhs.getOptionalInitValue() != null ) {
					Double rhsInitValue = rhs.getOptionalInitValue();
					cmp = lhsInitValue.compareTo( rhsInitValue );
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
				Double lhsMinValue = getOptionalMinValue();
				if( rhs.getOptionalMinValue() != null ) {
					Double rhsMinValue = rhs.getOptionalMinValue();
					cmp = lhsMinValue.compareTo( rhsMinValue );
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
				Double lhsMaxValue = getOptionalMaxValue();
				if( rhs.getOptionalMaxValue() != null ) {
					Double rhsMaxValue = rhs.getOptionalMaxValue();
					cmp = lhsMaxValue.compareTo( rhsMaxValue );
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
        else if (obj instanceof ICFBamDoubleDefH) {
        cmp = super.compareTo(obj);
        if (cmp != 0) {
            return( cmp );
        }
        ICFBamDoubleDefH rhs = (ICFBamDoubleDefH)obj;
			if( getOptionalInitValue() != null ) {
				Double lhsInitValue = getOptionalInitValue();
				if( rhs.getOptionalInitValue() != null ) {
					Double rhsInitValue = rhs.getOptionalInitValue();
					cmp = lhsInitValue.compareTo( rhsInitValue );
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
				Double lhsMinValue = getOptionalMinValue();
				if( rhs.getOptionalMinValue() != null ) {
					Double rhsMinValue = rhs.getOptionalMinValue();
					cmp = lhsMinValue.compareTo( rhsMinValue );
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
				Double lhsMaxValue = getOptionalMaxValue();
				if( rhs.getOptionalMaxValue() != null ) {
					Double rhsMaxValue = rhs.getOptionalMaxValue();
					cmp = lhsMaxValue.compareTo( rhsMaxValue );
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
		if( src instanceof ICFBamDoubleDef ) {
			setDoubleDef( (ICFBamDoubleDef)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamJpaDoubleDef" );
		}
    }

	@Override
    public void setDoubleDef( ICFBamDoubleDef src ) {
        super.setAtom( src );
		setOptionalInitValue( src.getOptionalInitValue() );
		setOptionalMinValue( src.getOptionalMinValue() );
		setOptionalMaxValue( src.getOptionalMaxValue() );
    }

	@Override
    public void set( ICFBamValueH src ) {
		if( src instanceof ICFBamDoubleDefH ) {
			setDoubleDef( (ICFBamDoubleDefH)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamDoubleDefH" );
		}
    }

	@Override
    public void setDoubleDef( ICFBamDoubleDefH src ) {
        super.setAtom( src );
		setOptionalInitValue( src.getOptionalInitValue() );
		setOptionalMinValue( src.getOptionalMinValue() );
		setOptionalMaxValue( src.getOptionalMaxValue() );
    }

    public String getXmlAttrFragment() {
        String ret = super.getXmlAttrFragment() 
			+ " OptionalInitValue=" + ( ( getOptionalInitValue() == null ) ? "null" : "\"" + getOptionalInitValue().toString() + "\"" )
			+ " OptionalMinValue=" + ( ( getOptionalMinValue() == null ) ? "null" : "\"" + getOptionalMinValue().toString() + "\"" )
			+ " OptionalMaxValue=" + ( ( getOptionalMaxValue() == null ) ? "null" : "\"" + getOptionalMaxValue().toString() + "\"" );
        return( ret );
    }

    public String toString() {
        String ret = "<CFBamJpaDoubleDefH" + getXmlAttrFragment() + "/>";
        return( ret );
    }
}
