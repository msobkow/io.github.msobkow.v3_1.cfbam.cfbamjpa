// Description: Java 25 JPA implementation of a TimestampDef entity definition object.

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
	name = "tsdef", schema = "CFBam31",
	indexes = {
		@Index(name = "TimestampDefIdIdx", columnList = "Id", unique = true)
	}
)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("43101")
@Transactional(Transactional.TxType.SUPPORTS)
@PersistenceContext(unitName = "CFBamPU")
public class CFBamJpaTimestampDef extends CFBamJpaAtom
	implements ICFBamTimestampDef
{

	@Column( name="Dummy", nullable=true, length=4 )
	protected String optionalDummy;

	public CFBamJpaTimestampDef() {
		super();
		optionalDummy = null;
	}

	@Override
	public int getClassCode() {
		return( ICFBamTimestampDef.CLASS_CODE );
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
		else if (obj instanceof ICFBamTimestampDef) {
			if (!super.equals(obj)) {
				return( false );
			}
			ICFBamTimestampDef rhs = (ICFBamTimestampDef)obj;
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
		else if (obj instanceof ICFBamTimestampDefH) {
			if (!super.equals(obj)) {
				return( false );
			}
			ICFBamTimestampDefH rhs = (ICFBamTimestampDefH)obj;
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
		else if (obj instanceof ICFBamTimestampDef) {
			cmp = super.compareTo(obj);
			if (cmp != 0) {
				return( cmp );
			}
			ICFBamTimestampDef rhs = (ICFBamTimestampDef)obj;
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
		else if( obj instanceof ICFBamTimestampDefH ) {
			cmp = super.compareTo(obj);
			if (cmp != 0) {
				return( cmp );
			}
			ICFBamTimestampDefH rhs = (ICFBamTimestampDefH)obj;
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
			cmp = super.compareTo(obj);
			return( cmp );
		}
	}

	@Override
	public void set( ICFBamValue src ) {
		if( src instanceof ICFBamTimestampDef ) {
			setTimestampDef( (ICFBamTimestampDef)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamJpaTimestampDef" );
		}
	}

	@Override
	public void setTimestampDef( ICFBamTimestampDef src ) {
		super.setAtom( src );
		setOptionalDummy(src.getOptionalDummy());
	}

	@Override
	public void set( ICFBamValueH src ) {
		if( src instanceof ICFBamTimestampDefH ) {
			setTimestampDef( (ICFBamTimestampDefH)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamTimestampDefH" );
		}
	}

	@Override
	public void setTimestampDef( ICFBamTimestampDefH src ) {
		super.setAtom( src );
		setOptionalDummy(src.getOptionalDummy());
	}

	@Override
	public String getXmlAttrFragment() {
		String ret = super.getXmlAttrFragment() 
			+ " RequiredId=" + "\"" + getRequiredId().toString() + "\""
			+ " OptionalDummy=" + ( ( getOptionalDummy() == null ) ? "null" : "\"" + StringEscapeUtils.escapeXml11( getOptionalDummy() ) + "\"" );
		return( ret );
	}

	@Override
	public String toString() {
		String ret = "<CFBamJpaTimestampDef" + getXmlAttrFragment() + "/>";
		return( ret );
	}
}
