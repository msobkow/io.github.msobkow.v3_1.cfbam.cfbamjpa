// Description: Java 25 JPA implementation of a DelSubDep3 by DelSubDep2Idx index key object.

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

public class CFBamJpaDelSubDep3ByDelSubDep2IdxKey
	implements ICFBamDelSubDep3ByDelSubDep2IdxKey, Comparable<Object>, Serializable
{
	protected CFLibDbKeyHash256 requiredDelSubDep2Id;
	public CFBamJpaDelSubDep3ByDelSubDep2IdxKey() {
		requiredDelSubDep2Id = CFLibDbKeyHash256.fromHex( ICFBamDelSubDep3.DELSUBDEP2ID_INIT_VALUE.toString() );
	}

	@Override
	public CFLibDbKeyHash256 getRequiredDelSubDep2Id() {
		return( requiredDelSubDep2Id );
	}

	@Override
	public void setRequiredDelSubDep2Id( CFLibDbKeyHash256 value ) {
		if( value == null || value.isNull() ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredDelSubDep2Id",
				1,
				"value" );
		}
		requiredDelSubDep2Id = value;
	}

	@Override
	public boolean equals( Object obj ) {
		if (obj == null) {
			return( false );
		}
		else if (obj instanceof ICFBamDelSubDep3ByDelSubDep2IdxKey) {
			ICFBamDelSubDep3ByDelSubDep2IdxKey rhs = (ICFBamDelSubDep3ByDelSubDep2IdxKey)obj;
			if( getRequiredDelSubDep2Id() != null && !getRequiredDelSubDep2Id().isNull() ) {
				if( rhs.getRequiredDelSubDep2Id() != null && !rhs.getRequiredDelSubDep2Id().isNull() ) {
					if( ! getRequiredDelSubDep2Id().equals( rhs.getRequiredDelSubDep2Id() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredDelSubDep2Id() != null && !getRequiredDelSubDep2Id().isNull()) {
					return( false );
				}
			}
			return( true );
		}
		else if (obj instanceof ICFBamDelSubDep3) {
			ICFBamDelSubDep3 rhs = (ICFBamDelSubDep3)obj;
			if( getRequiredDelSubDep2Id() != null && !getRequiredDelSubDep2Id().isNull() ) {
				if( rhs.getRequiredDelSubDep2Id() != null && !rhs.getRequiredDelSubDep2Id().isNull() ) {
					if( ! getRequiredDelSubDep2Id().equals( rhs.getRequiredDelSubDep2Id() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredDelSubDep2Id() != null && !getRequiredDelSubDep2Id().isNull()) {
					return( false );
				}
			}
			return( true );
		}
		else if (obj instanceof ICFBamDelSubDep3H) {
			ICFBamDelSubDep3H rhs = (ICFBamDelSubDep3H)obj;
			if( getRequiredDelSubDep2Id() != null && !getRequiredDelSubDep2Id().isNull() ) {
				if( rhs.getRequiredDelSubDep2Id() != null && !rhs.getRequiredDelSubDep2Id().isNull() ) {
					if( ! getRequiredDelSubDep2Id().equals( rhs.getRequiredDelSubDep2Id() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredDelSubDep2Id() != null && !getRequiredDelSubDep2Id().isNull()) {
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
		hashCode = hashCode + getRequiredDelSubDep2Id().hashCode();
		return( hashCode & 0x7fffffff );
	}

	@Override
	public int compareTo( Object obj ) {
		int cmp;
		if (obj == null) {
			return( 1 );
		}
		else if (obj instanceof ICFBamDelSubDep3ByDelSubDep2IdxKey) {
			ICFBamDelSubDep3ByDelSubDep2IdxKey rhs = (ICFBamDelSubDep3ByDelSubDep2IdxKey)obj;
			if (getRequiredDelSubDep2Id() != null) {
				if (rhs.getRequiredDelSubDep2Id() != null) {
					cmp = getRequiredDelSubDep2Id().compareTo( rhs.getRequiredDelSubDep2Id() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredDelSubDep2Id() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if (obj instanceof ICFBamDelSubDep3) {
			ICFBamDelSubDep3 rhs = (ICFBamDelSubDep3)obj;
			if (getRequiredDelSubDep2Id() != null) {
				if (rhs.getRequiredDelSubDep2Id() != null) {
					cmp = getRequiredDelSubDep2Id().compareTo( rhs.getRequiredDelSubDep2Id() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredDelSubDep2Id() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if (obj instanceof ICFBamDelSubDep3H) {
			ICFBamDelSubDep3H rhs = (ICFBamDelSubDep3H)obj;
			if (getRequiredDelSubDep2Id() != null) {
				if (rhs.getRequiredDelSubDep2Id() != null) {
					cmp = getRequiredDelSubDep2Id().compareTo( rhs.getRequiredDelSubDep2Id() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredDelSubDep2Id() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(),
				"compareTo",
				"obj",
				obj,
				"ICFBamDelSubDep3ByDelSubDep2IdxKey, ICFBamDelSubDep3, ICFBamDelSubDep3H");
		}
	}

	public String getXmlAttrFragment() {
		String ret = "" 
			+ " RequiredDelSubDep2Id=" + "\"" + getRequiredDelSubDep2Id().toString() + "\"";
		return( ret );
	}

	@Override
	public String toString() {
		String ret = "<CFBamDelSubDep3ByDelSubDep2IdxKey" + getXmlAttrFragment() + "/>";
		return( ret );
	}
}
