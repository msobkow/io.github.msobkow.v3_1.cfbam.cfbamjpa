// Description: Java 25 JPA implementation of a Int32Def entity definition object.

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
	name = "int32def", schema = "CFBam31",
	indexes = {
		@Index(name = "Int32DefIdIdx", columnList = "Id", unique = true)
	}
)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("43045")
@Transactional(Transactional.TxType.SUPPORTS)
@PersistenceContext(unitName = "CFBamPU")
public class CFBamJpaInt32Def extends CFBamJpaAtom
	implements ICFBamInt32Def
{

	@Column( name="InitVal", nullable=true )
	protected Integer optionalInitValue;
	@Column( name="MinVal", nullable=true )
	protected Integer optionalMinValue;
	@Column( name="MaxVal", nullable=true )
	protected Integer optionalMaxValue;

	public CFBamJpaInt32Def() {
		super();
		optionalInitValue = null;
		optionalMinValue = null;
		optionalMaxValue = null;
	}

	@Override
	public int getClassCode() {
		return( ICFBamInt32Def.CLASS_CODE );
	}

	@Override
	public Integer getOptionalInitValue() {
		return( optionalInitValue );
	}

	@Override
	public void setOptionalInitValue( Integer value ) {
		if( value < ICFBamInt32Def.INITVALUE_MIN_VALUE ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				"setOptionalInitValue",
				1,
				"value",
				value,
				ICFBamInt32Def.INITVALUE_MIN_VALUE );
		}
		else if( value > ICFBamInt32Def.INITVALUE_MAX_VALUE ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setOptionalInitValue",
				1,
				"value",
				value,
				ICFBamInt32Def.INITVALUE_MAX_VALUE );
		}
		optionalInitValue = value;
	}

	@Override
	public Integer getOptionalMinValue() {
		return( optionalMinValue );
	}

	@Override
	public void setOptionalMinValue( Integer value ) {
		if( value < ICFBamInt32Def.MINVALUE_MIN_VALUE ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				"setOptionalMinValue",
				1,
				"value",
				value,
				ICFBamInt32Def.MINVALUE_MIN_VALUE );
		}
		else if( value > ICFBamInt32Def.MINVALUE_MAX_VALUE ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setOptionalMinValue",
				1,
				"value",
				value,
				ICFBamInt32Def.MINVALUE_MAX_VALUE );
		}
		optionalMinValue = value;
	}

	@Override
	public Integer getOptionalMaxValue() {
		return( optionalMaxValue );
	}

	@Override
	public void setOptionalMaxValue( Integer value ) {
		if( value < ICFBamInt32Def.MAXVALUE_MIN_VALUE ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				"setOptionalMaxValue",
				1,
				"value",
				value,
				ICFBamInt32Def.MAXVALUE_MIN_VALUE );
		}
		else if( value > ICFBamInt32Def.MAXVALUE_MAX_VALUE ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setOptionalMaxValue",
				1,
				"value",
				value,
				ICFBamInt32Def.MAXVALUE_MAX_VALUE );
		}
		optionalMaxValue = value;
	}

	@Override
	public boolean equals( Object obj ) {
		if (obj == null) {
			return( false );
		}
		else if (obj instanceof ICFBamInt32Def) {
			if (!super.equals(obj)) {
				return( false );
			}
			ICFBamInt32Def rhs = (ICFBamInt32Def)obj;
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
		else if (obj instanceof ICFBamInt32DefH) {
			if (!super.equals(obj)) {
				return( false );
			}
			ICFBamInt32DefH rhs = (ICFBamInt32DefH)obj;
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
			hashCode = hashCode + getOptionalInitValue();
		}
		if( getOptionalMinValue() != null ) {
			hashCode = hashCode + getOptionalMinValue();
		}
		if( getOptionalMaxValue() != null ) {
			hashCode = hashCode + getOptionalMaxValue();
		}
		return( hashCode & 0x7fffffff );
	}

	@Override
	public int compareTo( Object obj ) {
		int cmp;
		if (obj == null) {
			return( 1 );
		}
		else if (obj instanceof ICFBamInt32Def) {
			cmp = super.compareTo(obj);
			if (cmp != 0) {
				return( cmp );
			}
			ICFBamInt32Def rhs = (ICFBamInt32Def)obj;
			if( getOptionalInitValue() != null ) {
				Integer lhsInitValue = getOptionalInitValue();
				if( rhs.getOptionalInitValue() != null ) {
					Integer rhsInitValue = rhs.getOptionalInitValue();
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
				Integer lhsMinValue = getOptionalMinValue();
				if( rhs.getOptionalMinValue() != null ) {
					Integer rhsMinValue = rhs.getOptionalMinValue();
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
				Integer lhsMaxValue = getOptionalMaxValue();
				if( rhs.getOptionalMaxValue() != null ) {
					Integer rhsMaxValue = rhs.getOptionalMaxValue();
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
		else if( obj instanceof ICFBamInt32DefH ) {
			cmp = super.compareTo(obj);
			if (cmp != 0) {
				return( cmp );
			}
			ICFBamInt32DefH rhs = (ICFBamInt32DefH)obj;
			if( getOptionalInitValue() != null ) {
				Integer lhsInitValue = getOptionalInitValue();
				if( rhs.getOptionalInitValue() != null ) {
					Integer rhsInitValue = rhs.getOptionalInitValue();
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
				Integer lhsMinValue = getOptionalMinValue();
				if( rhs.getOptionalMinValue() != null ) {
					Integer rhsMinValue = rhs.getOptionalMinValue();
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
				Integer lhsMaxValue = getOptionalMaxValue();
				if( rhs.getOptionalMaxValue() != null ) {
					Integer rhsMaxValue = rhs.getOptionalMaxValue();
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
			cmp = super.compareTo(obj);
			return( cmp );
		}
	}

	@Override
	public void set( ICFBamValue src ) {
		if( src instanceof ICFBamInt32Def ) {
			setInt32Def( (ICFBamInt32Def)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamJpaInt32Def" );
		}
	}

	@Override
	public void setInt32Def( ICFBamInt32Def src ) {
		super.setAtom( src );
		setOptionalInitValue(src.getOptionalInitValue());
		setOptionalMinValue(src.getOptionalMinValue());
		setOptionalMaxValue(src.getOptionalMaxValue());
	}

	@Override
	public void set( ICFBamValueH src ) {
		if( src instanceof ICFBamInt32DefH ) {
			setInt32Def( (ICFBamInt32DefH)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamInt32DefH" );
		}
	}

	@Override
	public void setInt32Def( ICFBamInt32DefH src ) {
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
		String ret = "<CFBamJpaInt32Def" + getXmlAttrFragment() + "/>";
		return( ret );
	}
}
