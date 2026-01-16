// Description: Java 25 JPA implementation of a RelationCol by FromColIdx index key object.

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

public class CFBamJpaRelationColByFromColIdxKey
	implements ICFBamRelationColByFromColIdxKey, Comparable<Object>, Serializable
{
	protected CFLibDbKeyHash256 requiredFromColId;
	public CFBamJpaRelationColByFromColIdxKey() {
		requiredFromColId = CFLibDbKeyHash256.fromHex( ICFBamRelationCol.FROMCOLID_INIT_VALUE.toString() );
	}

	@Override
	public CFLibDbKeyHash256 getRequiredFromColId() {
		return( requiredFromColId );
	}

	@Override
	public void setRequiredFromColId( CFLibDbKeyHash256 value ) {
		if( value == null || value.isNull() ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredFromColId",
				1,
				"value" );
		}
		requiredFromColId = value;
	}

	@Override
	public boolean equals( Object obj ) {
		if (obj == null) {
			return( false );
		}
		else if (obj instanceof ICFBamRelationColByFromColIdxKey) {
			ICFBamRelationColByFromColIdxKey rhs = (ICFBamRelationColByFromColIdxKey)obj;
			if( getRequiredFromColId() != null && !getRequiredFromColId().isNull() ) {
				if( rhs.getRequiredFromColId() != null && !rhs.getRequiredFromColId().isNull() ) {
					if( ! getRequiredFromColId().equals( rhs.getRequiredFromColId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredFromColId() != null && !getRequiredFromColId().isNull()) {
					return( false );
				}
			}
			return( true );
		}
		else if (obj instanceof ICFBamRelationCol) {
			ICFBamRelationCol rhs = (ICFBamRelationCol)obj;
			if( getRequiredFromColId() != null && !getRequiredFromColId().isNull() ) {
				if( rhs.getRequiredFromColId() != null && !rhs.getRequiredFromColId().isNull() ) {
					if( ! getRequiredFromColId().equals( rhs.getRequiredFromColId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredFromColId() != null && !getRequiredFromColId().isNull()) {
					return( false );
				}
			}
			return( true );
		}
		else if (obj instanceof ICFBamRelationColH) {
			ICFBamRelationColH rhs = (ICFBamRelationColH)obj;
			if( getRequiredFromColId() != null && !getRequiredFromColId().isNull() ) {
				if( rhs.getRequiredFromColId() != null && !rhs.getRequiredFromColId().isNull() ) {
					if( ! getRequiredFromColId().equals( rhs.getRequiredFromColId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredFromColId() != null && !getRequiredFromColId().isNull()) {
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
		hashCode = hashCode + getRequiredFromColId().hashCode();
		return( hashCode & 0x7fffffff );
	}

	@Override
	public int compareTo( Object obj ) {
		int cmp;
		if (obj == null) {
			return( 1 );
		}
		else if (obj instanceof ICFBamRelationColByFromColIdxKey) {
			ICFBamRelationColByFromColIdxKey rhs = (ICFBamRelationColByFromColIdxKey)obj;
			if (getRequiredFromColId() != null) {
				if (rhs.getRequiredFromColId() != null) {
					cmp = getRequiredFromColId().compareTo( rhs.getRequiredFromColId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredFromColId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if (obj instanceof ICFBamRelationCol) {
			ICFBamRelationCol rhs = (ICFBamRelationCol)obj;
			if (getRequiredFromColId() != null) {
				if (rhs.getRequiredFromColId() != null) {
					cmp = getRequiredFromColId().compareTo( rhs.getRequiredFromColId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredFromColId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if (obj instanceof ICFBamRelationColH) {
			ICFBamRelationColH rhs = (ICFBamRelationColH)obj;
			if (getRequiredFromColId() != null) {
				if (rhs.getRequiredFromColId() != null) {
					cmp = getRequiredFromColId().compareTo( rhs.getRequiredFromColId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredFromColId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(),
				"compareTo",
				"obj",
				obj,
				"ICFBamRelationColByFromColIdxKey, ICFBamRelationCol, ICFBamRelationColH");
		}
	}

	public String getXmlAttrFragment() {
		String ret = "" 
			+ " RequiredFromColId=" + "\"" + getRequiredFromColId().toString() + "\"";
		return( ret );
	}

	@Override
	public String toString() {
		String ret = "<CFBamRelationColByFromColIdxKey" + getXmlAttrFragment() + "/>";
		return( ret );
	}
}
