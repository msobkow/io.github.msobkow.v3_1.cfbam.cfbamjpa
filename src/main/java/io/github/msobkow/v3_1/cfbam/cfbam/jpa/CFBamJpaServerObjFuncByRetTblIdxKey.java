// Description: Java 25 JPA implementation of a ServerObjFunc by RetTblIdx index key object.

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

public class CFBamJpaServerObjFuncByRetTblIdxKey
	implements ICFBamServerObjFuncByRetTblIdxKey, Comparable<Object>, Serializable
{
	protected CFLibDbKeyHash256 optionalRetTableId;
	public CFBamJpaServerObjFuncByRetTblIdxKey() {
		optionalRetTableId = CFLibDbKeyHash256.nullGet();
	}

	@Override
	public CFLibDbKeyHash256 getOptionalRetTableId() {
		return( optionalRetTableId );
	}

	@Override
	public void setOptionalRetTableId( CFLibDbKeyHash256 value ) {
		optionalRetTableId = value;
	}

	@Override
	public boolean equals( Object obj ) {
		if (obj == null) {
			return( false );
		}
		else if (obj instanceof ICFBamServerObjFuncByRetTblIdxKey) {
			ICFBamServerObjFuncByRetTblIdxKey rhs = (ICFBamServerObjFuncByRetTblIdxKey)obj;
			if( getOptionalRetTableId() != null && !getOptionalRetTableId().isNull() ) {
				if( rhs.getOptionalRetTableId() != null && !rhs.getOptionalRetTableId().isNull() ) {
					if( ! getOptionalRetTableId().equals( rhs.getOptionalRetTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalRetTableId() != null && !getOptionalRetTableId().isNull()) {
					return( false );
				}
			}
			return( true );
		}
		else if (obj instanceof ICFBamServerObjFunc) {
			ICFBamServerObjFunc rhs = (ICFBamServerObjFunc)obj;
			if( getOptionalRetTableId() != null && !getOptionalRetTableId().isNull() ) {
				if( rhs.getOptionalRetTableId() != null && !rhs.getOptionalRetTableId().isNull() ) {
					if( ! getOptionalRetTableId().equals( rhs.getOptionalRetTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalRetTableId() != null && !getOptionalRetTableId().isNull()) {
					return( false );
				}
			}
			return( true );
		}
		else if (obj instanceof ICFBamServerObjFuncH) {
			ICFBamServerObjFuncH rhs = (ICFBamServerObjFuncH)obj;
			if( getOptionalRetTableId() != null && !getOptionalRetTableId().isNull() ) {
				if( rhs.getOptionalRetTableId() != null && !rhs.getOptionalRetTableId().isNull() ) {
					if( ! getOptionalRetTableId().equals( rhs.getOptionalRetTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalRetTableId() != null && !getOptionalRetTableId().isNull()) {
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
		if( getOptionalRetTableId() != null ) {
			hashCode = hashCode + getOptionalRetTableId().hashCode();
		}
		return( hashCode & 0x7fffffff );
	}

	@Override
	public int compareTo( Object obj ) {
		int cmp;
		if (obj == null) {
			return( 1 );
		}
		else if (obj instanceof ICFBamServerObjFuncByRetTblIdxKey) {
			ICFBamServerObjFuncByRetTblIdxKey rhs = (ICFBamServerObjFuncByRetTblIdxKey)obj;
			if( getOptionalRetTableId() != null ) {
				if( rhs.getOptionalRetTableId() != null ) {
					cmp = getOptionalRetTableId().compareTo( rhs.getOptionalRetTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalRetTableId() != null ) {
					return( -1 );
				}
			}
			return( 0 );
		}
		else if (obj instanceof ICFBamServerObjFunc) {
			ICFBamServerObjFunc rhs = (ICFBamServerObjFunc)obj;
			if( getOptionalRetTableId() != null ) {
				if( rhs.getOptionalRetTableId() != null ) {
					cmp = getOptionalRetTableId().compareTo( rhs.getOptionalRetTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalRetTableId() != null ) {
					return( -1 );
				}
			}
			return( 0 );
		}
		else if (obj instanceof ICFBamServerObjFuncH) {
			ICFBamServerObjFuncH rhs = (ICFBamServerObjFuncH)obj;
			if( getOptionalRetTableId() != null ) {
				if( rhs.getOptionalRetTableId() != null ) {
					cmp = getOptionalRetTableId().compareTo( rhs.getOptionalRetTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalRetTableId() != null ) {
					return( -1 );
				}
			}
			return( 0 );
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(),
				"compareTo",
				"obj",
				obj,
				"ICFBamServerObjFuncByRetTblIdxKey, ICFBamServerObjFunc, ICFBamServerObjFuncH");
		}
	}

	public String getXmlAttrFragment() {
		String ret = "" 
			+ " OptionalRetTableId=" + ( ( getOptionalRetTableId() == null ) ? "null" : "\"" + getOptionalRetTableId().toString() + "\"" );
		return( ret );
	}

	@Override
	public String toString() {
		String ret = "<CFBamServerObjFuncByRetTblIdxKey" + getXmlAttrFragment() + "/>";
		return( ret );
	}
}
