// Description: Java 25 JPA implementation of a BlobDef entity definition object.

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
	name = "blbdef", schema = "CFBam31",
	indexes = {
		@Index(name = "BlobDefIdIdx", columnList = "Id", unique = true)
	}
)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("43019")
@Transactional(Transactional.TxType.SUPPORTS)
@PersistenceContext(unitName = "CFBamPU")
public class CFBamJpaBlobDef extends CFBamJpaAtom
	implements ICFBamBlobDef
{

	@Column( name="db_max_len", nullable=false )
	protected int requiredMaxLen;
	@Column( name="InitVal", nullable=true, length=16384 )
	protected byte[] optionalInitValue;

	public CFBamJpaBlobDef() {
		super();
		requiredMaxLen = ICFBamBlobDef.MAXLEN_INIT_VALUE;
		optionalInitValue = null;
	}

	@Override
	public int getClassCode() {
		return( ICFBamBlobDef.CLASS_CODE );
	}

	@Override
	public int getRequiredMaxLen() {
		return( requiredMaxLen );
	}

	@Override
	public void setRequiredMaxLen( int value ) {
		if( value < ICFBamBlobDef.MAXLEN_MIN_VALUE ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				"setRequiredMaxLen",
				1,
				"value",
				value,
				ICFBamBlobDef.MAXLEN_MIN_VALUE );
		}
		if( value > ICFBamBlobDef.MAXLEN_MAX_VALUE ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setRequiredMaxLen",
				1,
				"value",
				value,
				ICFBamBlobDef.MAXLEN_MAX_VALUE );
		}
		requiredMaxLen = value;
	}

	@Override
	public byte[] getOptionalInitValue() {
		return( optionalInitValue );
	}

	@Override
	public void setOptionalInitValue( byte[] value ) {
		if( value != null && value.length > 16384 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setOptionalInitValue",
				1,
				"value.length",
				value.length,
				16384 );
		}
		optionalInitValue = value;
	}

	@Override
	public boolean equals( Object obj ) {
		if (obj == null) {
			return( false );
		}
		else if (obj instanceof ICFBamBlobDef) {
			if (!super.equals(obj)) {
				return( false );
			}
			ICFBamBlobDef rhs = (ICFBamBlobDef)obj;
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
			return( true );
		}
		else if (obj instanceof ICFBamBlobDefH) {
			if (!super.equals(obj)) {
				return( false );
			}
			ICFBamBlobDefH rhs = (ICFBamBlobDefH)obj;
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
		return( hashCode & 0x7fffffff );
	}

	@Override
	public int compareTo( Object obj ) {
		int cmp;
		if (obj == null) {
			return( 1 );
		}
		else if (obj instanceof ICFBamBlobDef) {
			cmp = super.compareTo(obj);
			if (cmp != 0) {
				return( cmp );
			}
			ICFBamBlobDef rhs = (ICFBamBlobDef)obj;
			if( getRequiredMaxLen() < rhs.getRequiredMaxLen() ) {
				return( -1 );
			}
			else if( getRequiredMaxLen() > rhs.getRequiredMaxLen() ) {
				return( 1 );
			}
			if( getOptionalInitValue() != null ) {
				if( rhs.getOptionalInitValue() != null ) {
					byte[] larr = getOptionalInitValue();
					byte[] rarr = rhs.getOptionalInitValue();
					int llen = larr.length;
					int rlen = rarr.length;
					int idx = 0;
					byte lval;
					byte rval;
					while( ( idx < llen ) && ( idx < rlen ) ) {
						lval = larr[idx];
						rval = rarr[idx];
						if( lval < rval ) {
							return( -1 );
						}
						else if( lval > rval ) {
							return( 1 );
						}
						idx ++;
					}
					if( llen < rlen ) {
						return( -1 );
					}
					else if( llen > rlen ) {
						return( 1 );
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
		else if( obj instanceof ICFBamBlobDefH ) {
			cmp = super.compareTo(obj);
			if (cmp != 0) {
				return( cmp );
			}
			ICFBamBlobDefH rhs = (ICFBamBlobDefH)obj;
			if( getRequiredMaxLen() < rhs.getRequiredMaxLen() ) {
				return( -1 );
			}
			else if( getRequiredMaxLen() > rhs.getRequiredMaxLen() ) {
				return( 1 );
			}
			if( getOptionalInitValue() != null ) {
				if( rhs.getOptionalInitValue() != null ) {
					byte[] larr = getOptionalInitValue();
					byte[] rarr = rhs.getOptionalInitValue();
					int llen = larr.length;
					int rlen = rarr.length;
					int idx = 0;
					byte lval;
					byte rval;
					while( ( idx < llen ) && ( idx < rlen ) ) {
						lval = larr[idx];
						rval = rarr[idx];
						if( lval < rval ) {
							return( -1 );
						}
						else if( lval > rval ) {
							return( 1 );
						}
						idx ++;
					}
					if( llen < rlen ) {
						return( -1 );
					}
					else if( llen > rlen ) {
						return( 1 );
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
			cmp = super.compareTo(obj);
			return( cmp );
		}
	}

	@Override
	public void set( ICFBamValue src ) {
		if( src instanceof ICFBamBlobDef ) {
			setBlobDef( (ICFBamBlobDef)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamJpaBlobDef" );
		}
	}

	@Override
	public void setBlobDef( ICFBamBlobDef src ) {
		super.setAtom( src );
		setRequiredMaxLen(src.getRequiredMaxLen());
		setOptionalInitValue(src.getOptionalInitValue());
	}

	@Override
	public void set( ICFBamValueH src ) {
		if( src instanceof ICFBamBlobDefH ) {
			setBlobDef( (ICFBamBlobDefH)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamBlobDefH" );
		}
	}

	@Override
	public void setBlobDef( ICFBamBlobDefH src ) {
		super.setAtom( src );
		setRequiredMaxLen(src.getRequiredMaxLen());
		setOptionalInitValue(src.getOptionalInitValue());
	}

	@Override
	public String getXmlAttrFragment() {
		String ret = super.getXmlAttrFragment() 
			+ " RequiredId=" + "\"" + getRequiredId().toString() + "\""
			+ " RequiredMaxLen=" + "\"" + Integer.toString( getRequiredMaxLen() ) + "\""
			+ " OptionalInitValue=" + ( ( getOptionalInitValue() == null ) ? "null" : "\"" + Base64.encodeBase64( getOptionalInitValue() ) + "\"" );
		return( ret );
	}

	@Override
	public String toString() {
		String ret = "<CFBamJpaBlobDef" + getXmlAttrFragment() + "/>";
		return( ret );
	}
}
