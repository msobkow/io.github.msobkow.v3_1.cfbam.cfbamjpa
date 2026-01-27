
// Description: Java 25 JPA Default Factory implementation for DbKeyHash128Type.

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
 *	CFBamDbKeyHash128TypeFactory JPA implementation for DbKeyHash128Type
 */
public class CFBamJpaDbKeyHash128TypeDefaultFactory
    implements ICFBamDbKeyHash128TypeFactory
{
    public CFBamJpaDbKeyHash128TypeDefaultFactory() {
    }

    @Override
    public ICFBamDbKeyHash128TypeBySchemaIdxKey newBySchemaIdxKey() {
	ICFBamDbKeyHash128TypeBySchemaIdxKey key =
            new CFBamJpaDbKeyHash128TypeBySchemaIdxKey();
	return( key );
    }

	public CFBamJpaDbKeyHash128TypeBySchemaIdxKey ensureBySchemaIdxKey(ICFBamDbKeyHash128TypeBySchemaIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaDbKeyHash128TypeBySchemaIdxKey) {
			return( (CFBamJpaDbKeyHash128TypeBySchemaIdxKey)key );
		}
		else {
			CFBamJpaDbKeyHash128TypeBySchemaIdxKey mapped = new CFBamJpaDbKeyHash128TypeBySchemaIdxKey();
			mapped.setRequiredSchemaDefId( key.getRequiredSchemaDefId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamDbKeyHash128Type newRec() {
        ICFBamDbKeyHash128Type rec =
            new CFBamJpaDbKeyHash128Type();
        return( rec );
    }

	public CFBamJpaDbKeyHash128Type ensureRec(ICFBamDbKeyHash128Type rec) {
		if( rec == null ) {
			return( null );
		}
		else if (rec instanceof CFBamJpaDbKeyHash128Type) {
			return( (CFBamJpaDbKeyHash128Type)rec );
		}
		else {
			CFBamJpaDbKeyHash128Type mapped = new CFBamJpaDbKeyHash128Type();
			mapped.set(rec);
			return( mapped );
		}
	}

    @Override
    public ICFBamDbKeyHash128TypeH newHRec() {
        ICFBamDbKeyHash128TypeH hrec =
            new CFBamJpaDbKeyHash128TypeH();
        return( hrec );
    }

	public CFBamJpaDbKeyHash128TypeH ensureHRec(ICFBamDbKeyHash128TypeH hrec) {
		if (hrec == null) {
			return( null );
		}
		else if( hrec instanceof CFBamJpaDbKeyHash128TypeH) {
			return( (CFBamJpaDbKeyHash128TypeH)hrec );
		}
		else {
			CFBamJpaDbKeyHash128TypeH mapped = new CFBamJpaDbKeyHash128TypeH();
			mapped.set(hrec);
			return( mapped );
		}
	}
}
