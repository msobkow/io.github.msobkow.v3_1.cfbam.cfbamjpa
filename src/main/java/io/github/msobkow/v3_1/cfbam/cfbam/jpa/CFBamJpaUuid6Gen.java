// Description: Java 25 JPA implementation of a Uuid6Gen entity definition object.

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
	name = "idguuid6", schema = "CFBam31",
	indexes = {
		@Index(name = "Uuid6GenIdIdx", columnList = "Id", unique = true)
	}
)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("43145")
@Transactional(Transactional.TxType.SUPPORTS)
@PersistenceContext(unitName = "CFBamPU")
public class CFBamJpaUuid6Gen extends CFBamJpaUuid6Type
	implements ICFBamUuid6Gen
{

	@Column( name="Slice", nullable=false )
	protected short requiredSlice;
	@Column( name="BlockSize", nullable=false )
	protected int requiredBlockSize;

	public CFBamJpaUuid6Gen() {
		super();
		requiredSlice = ICFBamUuid6Gen.SLICE_INIT_VALUE;
		requiredBlockSize = ICFBamUuid6Gen.BLOCKSIZE_INIT_VALUE;
	}

	@Override
	public int getClassCode() {
		return( ICFBamUuid6Gen.CLASS_CODE );
	}

	@Override
	public short getRequiredSlice() {
		return( requiredSlice );
	}

	@Override
	public void setRequiredSlice( short value ) {
		if( value < ICFBamUuid6Gen.SLICE_MIN_VALUE ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				"setRequiredSlice",
				1,
				"value",
				value,
				ICFBamUuid6Gen.SLICE_MIN_VALUE );
		}
		if( value > ICFBamUuid6Gen.SLICE_MAX_VALUE ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setRequiredSlice",
				1,
				"value",
				value,
				ICFBamUuid6Gen.SLICE_MAX_VALUE );
		}
		requiredSlice = value;
	}

	@Override
	public int getRequiredBlockSize() {
		return( requiredBlockSize );
	}

	@Override
	public void setRequiredBlockSize( int value ) {
		if( value < ICFBamUuid6Gen.BLOCKSIZE_MIN_VALUE ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				"setRequiredBlockSize",
				1,
				"value",
				value,
				ICFBamUuid6Gen.BLOCKSIZE_MIN_VALUE );
		}
		if( value > ICFBamUuid6Gen.BLOCKSIZE_MAX_VALUE ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setRequiredBlockSize",
				1,
				"value",
				value,
				ICFBamUuid6Gen.BLOCKSIZE_MAX_VALUE );
		}
		requiredBlockSize = value;
	}

	@Override
	public boolean equals( Object obj ) {
		if (obj == null) {
			return( false );
		}
		else if (obj instanceof ICFBamUuid6Gen) {
			if (!super.equals(obj)) {
				return( false );
			}
			ICFBamUuid6Gen rhs = (ICFBamUuid6Gen)obj;
			if( getRequiredSlice() != rhs.getRequiredSlice() ) {
				return( false );
			}
			if( getRequiredBlockSize() != rhs.getRequiredBlockSize() ) {
				return( false );
			}
			return( true );
		}
		else if (obj instanceof ICFBamUuid6GenH) {
			if (!super.equals(obj)) {
				return( false );
			}
			ICFBamUuid6GenH rhs = (ICFBamUuid6GenH)obj;
			if( getRequiredSlice() != rhs.getRequiredSlice() ) {
				return( false );
			}
			if( getRequiredBlockSize() != rhs.getRequiredBlockSize() ) {
				return( false );
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
		hashCode = ( hashCode * 0x10000 ) + getRequiredSlice();
		hashCode = hashCode + getRequiredBlockSize();
		return( hashCode & 0x7fffffff );
	}

	@Override
	public int compareTo( Object obj ) {
		int cmp;
		if (obj == null) {
			return( 1 );
		}
		else if (obj instanceof ICFBamUuid6Gen) {
			cmp = super.compareTo(obj);
			if (cmp != 0) {
				return( cmp );
			}
			ICFBamUuid6Gen rhs = (ICFBamUuid6Gen)obj;
			if( getRequiredSlice() < rhs.getRequiredSlice() ) {
				return( -1 );
			}
			else if( getRequiredSlice() > rhs.getRequiredSlice() ) {
				return( 1 );
			}
			if( getRequiredBlockSize() < rhs.getRequiredBlockSize() ) {
				return( -1 );
			}
			else if( getRequiredBlockSize() > rhs.getRequiredBlockSize() ) {
				return( 1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFBamUuid6GenH ) {
			cmp = super.compareTo(obj);
			if (cmp != 0) {
				return( cmp );
			}
			ICFBamUuid6GenH rhs = (ICFBamUuid6GenH)obj;
			if( getRequiredSlice() < rhs.getRequiredSlice() ) {
				return( -1 );
			}
			else if( getRequiredSlice() > rhs.getRequiredSlice() ) {
				return( 1 );
			}
			if( getRequiredBlockSize() < rhs.getRequiredBlockSize() ) {
				return( -1 );
			}
			else if( getRequiredBlockSize() > rhs.getRequiredBlockSize() ) {
				return( 1 );
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
		if( src instanceof ICFBamUuid6Gen ) {
			setUuid6Gen( (ICFBamUuid6Gen)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamJpaUuid6Gen" );
		}
	}

	@Override
	public void setUuid6Gen( ICFBamUuid6Gen src ) {
		super.setUuid6Type( src );
		setRequiredSlice(src.getRequiredSlice());
		setRequiredBlockSize(src.getRequiredBlockSize());
	}

	@Override
	public void set( ICFBamValueH src ) {
		if( src instanceof ICFBamUuid6GenH ) {
			setUuid6Gen( (ICFBamUuid6GenH)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamUuid6GenH" );
		}
	}

	@Override
	public void setUuid6Gen( ICFBamUuid6GenH src ) {
		super.setUuid6Type( src );
		setRequiredSlice(src.getRequiredSlice());
		setRequiredBlockSize(src.getRequiredBlockSize());
	}

	@Override
	public String getXmlAttrFragment() {
		String ret = super.getXmlAttrFragment() 
			+ " RequiredId=" + "\"" + getRequiredId().toString() + "\""
			+ " RequiredSlice=" + "\"" + Short.toString( getRequiredSlice() ) + "\""
			+ " RequiredBlockSize=" + "\"" + Integer.toString( getRequiredBlockSize() ) + "\"";
		return( ret );
	}

	@Override
	public String toString() {
		String ret = "<CFBamJpaUuid6Gen" + getXmlAttrFragment() + "/>";
		return( ret );
	}
}
