
// Description: Java 25 JPA Default Factory implementation for DbKeyHash384Type.

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

import java.lang.reflect.*;
import java.net.*;
import java.rmi.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import io.github.msobkow.v3_1.cflib.*;
import io.github.msobkow.v3_1.cflib.dbutil.*;
import io.github.msobkow.v3_1.cfsec.cfsec.*;
import io.github.msobkow.v3_1.cfint.cfint.*;
import io.github.msobkow.v3_1.cfbam.cfbam.*;
import io.github.msobkow.v3_1.cfsec.cfsec.jpa.*;
import io.github.msobkow.v3_1.cfint.cfint.jpa.*;

/*
 *	CFBamDbKeyHash384TypeFactory JPA implementation for DbKeyHash384Type
 */
public class CFBamJpaDbKeyHash384TypeDefaultFactory
    implements ICFBamDbKeyHash384TypeFactory
{
    public CFBamJpaDbKeyHash384TypeDefaultFactory() {
    }

    @Override
    public ICFBamDbKeyHash384TypeBySchemaIdxKey newBySchemaIdxKey() {
	ICFBamDbKeyHash384TypeBySchemaIdxKey key =
            new CFBamJpaDbKeyHash384TypeBySchemaIdxKey();
	return( key );
    }

	public CFBamJpaDbKeyHash384TypeBySchemaIdxKey ensureBySchemaIdxKey(ICFBamDbKeyHash384TypeBySchemaIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaDbKeyHash384TypeBySchemaIdxKey) {
			return( (CFBamJpaDbKeyHash384TypeBySchemaIdxKey)key );
		}
		else {
			CFBamJpaDbKeyHash384TypeBySchemaIdxKey mapped = new CFBamJpaDbKeyHash384TypeBySchemaIdxKey();
			mapped.setRequiredSchemaDefId( key.getRequiredSchemaDefId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamDbKeyHash384Type newRec() {
        ICFBamDbKeyHash384Type rec =
            new CFBamJpaDbKeyHash384Type();
        return( rec );
    }

	public CFBamJpaDbKeyHash384Type ensureRec(ICFBamDbKeyHash384Type rec) {
		if( rec == null ) {
			return( null );
		}
		else if (rec instanceof CFBamJpaDbKeyHash384Type) {
			return( (CFBamJpaDbKeyHash384Type)rec );
		}
		else {
			CFBamJpaDbKeyHash384Type mapped = new CFBamJpaDbKeyHash384Type();
			mapped.set(rec);
			return( mapped );
		}
	}

    @Override
    public ICFBamDbKeyHash384TypeH newHRec() {
        ICFBamDbKeyHash384TypeH hrec =
            new CFBamJpaDbKeyHash384TypeH();
        return( hrec );
    }

	public CFBamJpaDbKeyHash384TypeH ensureHRec(ICFBamDbKeyHash384TypeH hrec) {
		if (hrec == null) {
			return( null );
		}
		else if( hrec instanceof CFBamJpaDbKeyHash384TypeH) {
			return( (CFBamJpaDbKeyHash384TypeH)hrec );
		}
		else {
			CFBamJpaDbKeyHash384TypeH mapped = new CFBamJpaDbKeyHash384TypeH();
			mapped.set(hrec);
			return( mapped );
		}
	}
}
