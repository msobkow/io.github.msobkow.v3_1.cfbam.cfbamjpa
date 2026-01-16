// Description: Java 25 JPA implementation of a IndexCol by IndexIdx index key object.

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

public class CFBamJpaIndexColByIndexIdxKey
	implements ICFBamIndexColByIndexIdxKey, Comparable<Object>, Serializable
{
	protected CFLibDbKeyHash256 requiredIndexId;
	public CFBamJpaIndexColByIndexIdxKey() {
		requiredIndexId = CFLibDbKeyHash256.fromHex( ICFBamIndexCol.INDEXID_INIT_VALUE.toString() );
	}

	@Override
	public CFLibDbKeyHash256 getRequiredIndexId() {
		return( requiredIndexId );
	}

	@Override
	public void setRequiredIndexId( CFLibDbKeyHash256 value ) {
		if( value == null || value.isNull() ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredIndexId",
				1,
				"value" );
		}
		requiredIndexId = value;
	}

	@Override
	public boolean equals( Object obj ) {
		if (obj == null) {
			return( false );
		}
		else if (obj instanceof ICFBamIndexColByIndexIdxKey) {
			ICFBamIndexColByIndexIdxKey rhs = (ICFBamIndexColByIndexIdxKey)obj;
			if( getRequiredIndexId() != null && !getRequiredIndexId().isNull() ) {
				if( rhs.getRequiredIndexId() != null && !rhs.getRequiredIndexId().isNull() ) {
					if( ! getRequiredIndexId().equals( rhs.getRequiredIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredIndexId() != null && !getRequiredIndexId().isNull()) {
					return( false );
				}
			}
			return( true );
		}
		else if (obj instanceof ICFBamIndexCol) {
			ICFBamIndexCol rhs = (ICFBamIndexCol)obj;
			if( getRequiredIndexId() != null && !getRequiredIndexId().isNull() ) {
				if( rhs.getRequiredIndexId() != null && !rhs.getRequiredIndexId().isNull() ) {
					if( ! getRequiredIndexId().equals( rhs.getRequiredIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredIndexId() != null && !getRequiredIndexId().isNull()) {
					return( false );
				}
			}
			return( true );
		}
		else if (obj instanceof ICFBamIndexColH) {
			ICFBamIndexColH rhs = (ICFBamIndexColH)obj;
			if( getRequiredIndexId() != null && !getRequiredIndexId().isNull() ) {
				if( rhs.getRequiredIndexId() != null && !rhs.getRequiredIndexId().isNull() ) {
					if( ! getRequiredIndexId().equals( rhs.getRequiredIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredIndexId() != null && !getRequiredIndexId().isNull()) {
					return( false );
				}
			}
			return( true );
		}
		else {
			return( false );
		}
	}

	@Override
	public int hashCode() {
		int hashCode = 0;
		hashCode = hashCode + getRequiredIndexId().hashCode();
		return( hashCode & 0x7fffffff );
	}

	@Override
	public int compareTo( Object obj ) {
		int cmp;
		if (obj == null) {
			return( 1 );
		}
		else if (obj instanceof ICFBamIndexColByIndexIdxKey) {
			ICFBamIndexColByIndexIdxKey rhs = (ICFBamIndexColByIndexIdxKey)obj;
			if (getRequiredIndexId() != null) {
				if (rhs.getRequiredIndexId() != null) {
					cmp = getRequiredIndexId().compareTo( rhs.getRequiredIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredIndexId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if (obj instanceof ICFBamIndexCol) {
			ICFBamIndexCol rhs = (ICFBamIndexCol)obj;
			if (getRequiredIndexId() != null) {
				if (rhs.getRequiredIndexId() != null) {
					cmp = getRequiredIndexId().compareTo( rhs.getRequiredIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredIndexId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if (obj instanceof ICFBamIndexColH) {
			ICFBamIndexColH rhs = (ICFBamIndexColH)obj;
			if (getRequiredIndexId() != null) {
				if (rhs.getRequiredIndexId() != null) {
					cmp = getRequiredIndexId().compareTo( rhs.getRequiredIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredIndexId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(),
				"compareTo",
				"obj",
				obj,
				"ICFBamIndexColByIndexIdxKey, ICFBamIndexCol, ICFBamIndexColH");
		}
	}

	public String getXmlAttrFragment() {
		String ret = "" 
			+ " RequiredIndexId=" + "\"" + getRequiredIndexId().toString() + "\"";
		return( ret );
	}

	@Override
	public String toString() {
		String ret = "<CFBamIndexColByIndexIdxKey" + getXmlAttrFragment() + "/>";
		return( ret );
	}
}
