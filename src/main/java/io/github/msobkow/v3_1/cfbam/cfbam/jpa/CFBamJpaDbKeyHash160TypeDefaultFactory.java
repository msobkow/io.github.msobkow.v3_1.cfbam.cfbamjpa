
// Description: Java 25 JPA Default Factory implementation for DbKeyHash160Type.

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
 *	CFBamDbKeyHash160TypeFactory JPA implementation for DbKeyHash160Type
 */
public class CFBamJpaDbKeyHash160TypeDefaultFactory
    implements ICFBamDbKeyHash160TypeFactory
{
    public CFBamJpaDbKeyHash160TypeDefaultFactory() {
    }

    @Override
    public ICFBamDbKeyHash160TypeBySchemaIdxKey newBySchemaIdxKey() {
	ICFBamDbKeyHash160TypeBySchemaIdxKey key =
            new CFBamJpaDbKeyHash160TypeBySchemaIdxKey();
	return( key );
    }

	public CFBamJpaDbKeyHash160TypeBySchemaIdxKey ensureBySchemaIdxKey(ICFBamDbKeyHash160TypeBySchemaIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaDbKeyHash160TypeBySchemaIdxKey) {
			return( (CFBamJpaDbKeyHash160TypeBySchemaIdxKey)key );
		}
		else {
			CFBamJpaDbKeyHash160TypeBySchemaIdxKey mapped = new CFBamJpaDbKeyHash160TypeBySchemaIdxKey();
			mapped.setRequiredSchemaDefId( key.getRequiredSchemaDefId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamDbKeyHash160Type newRec() {
        ICFBamDbKeyHash160Type rec =
            new CFBamJpaDbKeyHash160Type();
        return( rec );
    }

	public CFBamJpaDbKeyHash160Type ensureRec(ICFBamDbKeyHash160Type rec) {
		if( rec == null ) {
			return( null );
		}
		else if (rec instanceof CFBamJpaDbKeyHash160Type) {
			return( (CFBamJpaDbKeyHash160Type)rec );
		}
		else {
			CFBamJpaDbKeyHash160Type mapped = new CFBamJpaDbKeyHash160Type();
			mapped.set(rec);
			return( mapped );
		}
	}

    @Override
    public ICFBamDbKeyHash160TypeH newHRec() {
        ICFBamDbKeyHash160TypeH hrec =
            new CFBamJpaDbKeyHash160TypeH();
        return( hrec );
    }

	public CFBamJpaDbKeyHash160TypeH ensureHRec(ICFBamDbKeyHash160TypeH hrec) {
		if (hrec == null) {
			return( null );
		}
		else if( hrec instanceof CFBamJpaDbKeyHash160TypeH) {
			return( (CFBamJpaDbKeyHash160TypeH)hrec );
		}
		else {
			CFBamJpaDbKeyHash160TypeH mapped = new CFBamJpaDbKeyHash160TypeH();
			mapped.set(hrec);
			return( mapped );
		}
	}
}
