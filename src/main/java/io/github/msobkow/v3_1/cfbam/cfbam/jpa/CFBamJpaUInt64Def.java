// Description: Java 25 JPA implementation of a UInt64Def entity definition object.

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

@Entity
@Table(
	name = "u64def", schema = "CFBam31",
	indexes = {
		@Index(name = "UInt64DefIdIdx", columnList = "Id", unique = true)
	}
)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("43109")
@Transactional(Transactional.TxType.SUPPORTS)
@PersistenceContext(unitName = "CFBamPU")
public class CFBamJpaUInt64Def extends CFBamJpaAtom
	implements ICFBamUInt64Def
{

	@Column( name="InitVal", nullable=true )
	protected BigDecimal optionalInitValue;
	@Column( name="MinVal", nullable=true )
	protected BigDecimal optionalMinValue;
	@Column( name="MaxVal", nullable=true )
	protected BigDecimal optionalMaxValue;

	public CFBamJpaUInt64Def() {
		super();
		optionalInitValue = null;
		optionalMinValue = null;
		optionalMaxValue = null;
	}

	@Override
	public int getClassCode() {
		return( ICFBamUInt64Def.CLASS_CODE );
	}

	@Override
	public BigDecimal getOptionalInitValue() {
		return( optionalInitValue );
	}

	@Override
	public void setOptionalInitValue( BigDecimal value ) {
		if( value != null && value.compareTo( ICFBamUInt64Def.INITVALUE_MIN_VALUE ) < 0 ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				"setOptionalInitValue",
				1,
				"value",
				value.toString(),
				ICFBamUInt64Def.INITVALUE_MIN_VALUE.toString() );
		}
		optionalInitValue = value;
	}

	@Override
	public BigDecimal getOptionalMinValue() {
		return( optionalMinValue );
	}

	@Override
	public void setOptionalMinValue( BigDecimal value ) {
		if( value != null && value.compareTo( ICFBamUInt64Def.MINVALUE_MIN_VALUE ) < 0 ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				"setOptionalMinValue",
				1,
				"value",
				value.toString(),
				ICFBamUInt64Def.MINVALUE_MIN_VALUE.toString() );
		}
		optionalMinValue = value;
	}

	@Override
	public BigDecimal getOptionalMaxValue() {
		return( optionalMaxValue );
	}

	@Override
	public void setOptionalMaxValue( BigDecimal value ) {
		if( value != null && value.compareTo( ICFBamUInt64Def.MAXVALUE_MIN_VALUE ) < 0 ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				"setOptionalMaxValue",
				1,
				"value",
				value.toString(),
				ICFBamUInt64Def.MAXVALUE_MIN_VALUE.toString() );
		}
		optionalMaxValue = value;
	}

	@Override
	public boolean equals( Object obj ) {
		if (obj == null) {
			return( false );
		}
		else if (obj instanceof ICFBamUInt64Def) {
			if (!super.equals(obj)) {
				return( false );
			}
			ICFBamUInt64Def rhs = (ICFBamUInt64Def)obj;
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
		else if (obj instanceof ICFBamUInt64DefH) {
			if (!super.equals(obj)) {
				return( false );
			}
			ICFBamUInt64DefH rhs = (ICFBamUInt64DefH)obj;
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
		else if (obj instanceof ICFBamUInt64Def) {
			cmp = super.compareTo(obj);
			if (cmp != 0) {
				return( cmp );
			}
			ICFBamUInt64Def rhs = (ICFBamUInt64Def)obj;
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
		else if( obj instanceof ICFBamUInt64DefH ) {
			cmp = super.compareTo(obj);
			if (cmp != 0) {
				return( cmp );
			}
			ICFBamUInt64DefH rhs = (ICFBamUInt64DefH)obj;
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
			cmp = super.compareTo(obj);
			return( cmp );
		}
	}

	@Override
	public void set( ICFBamValue src ) {
		if( src instanceof ICFBamUInt64Def ) {
			setUInt64Def( (ICFBamUInt64Def)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamJpaUInt64Def" );
		}
	}

	@Override
	public void setUInt64Def( ICFBamUInt64Def src ) {
		super.setAtom( src );
		setOptionalInitValue(src.getOptionalInitValue());
		setOptionalMinValue(src.getOptionalMinValue());
		setOptionalMaxValue(src.getOptionalMaxValue());
	}

	@Override
	public void set( ICFBamValueH src ) {
		if( src instanceof ICFBamUInt64DefH ) {
			setUInt64Def( (ICFBamUInt64DefH)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamUInt64DefH" );
		}
	}

	@Override
	public void setUInt64Def( ICFBamUInt64DefH src ) {
		super.setAtom( src );
		setOptionalInitValue(src.getOptionalInitValue());
		setOptionalMinValue(src.getOptionalMinValue());
		setOptionalMaxValue(src.getOptionalMaxValue());
	}

	@Override
	public String getXmlAttrFragment() {
		String ret = super.getXmlAttrFragment() 
			+ " RequiredId=" + "\"" + getRequiredId().toString() + "\""
			+ " OptionalInitValue=" + ( ( getOptionalInitValue() == null ) ? "null" : "\"" + getOptionalInitValue().toString() + "\"" )
			+ " OptionalMinValue=" + ( ( getOptionalMinValue() == null ) ? "null" : "\"" + getOptionalMinValue().toString() + "\"" )
			+ " OptionalMaxValue=" + ( ( getOptionalMaxValue() == null ) ? "null" : "\"" + getOptionalMaxValue().toString() + "\"" );
		return( ret );
	}

	@Override
	public String toString() {
		String ret = "<CFBamJpaUInt64Def" + getXmlAttrFragment() + "/>";
		return( ret );
	}
}
