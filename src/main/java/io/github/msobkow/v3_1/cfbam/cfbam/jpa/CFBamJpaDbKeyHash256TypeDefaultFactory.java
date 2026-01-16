
// Description: Java 25 JPA Default Factory implementation for DbKeyHash256Type.

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
 *	CFBamDbKeyHash256TypeFactory JPA implementation for DbKeyHash256Type
 */
public class CFBamJpaDbKeyHash256TypeDefaultFactory
    implements ICFBamDbKeyHash256TypeFactory
{
    public CFBamJpaDbKeyHash256TypeDefaultFactory() {
    }

    @Override
    public ICFBamDbKeyHash256TypeBySchemaIdxKey newBySchemaIdxKey() {
	ICFBamDbKeyHash256TypeBySchemaIdxKey key =
            new CFBamJpaDbKeyHash256TypeBySchemaIdxKey();
	return( key );
    }

    @Override
    public ICFBamDbKeyHash256Type newRec() {
        ICFBamDbKeyHash256Type rec =
            new CFBamJpaDbKeyHash256Type();
        return( rec );
    }

    @Override
    public ICFBamDbKeyHash256TypeH newHRec() {
        ICFBamDbKeyHash256TypeH hrec =
            new CFBamJpaDbKeyHash256TypeH();
        return( hrec );
    }
}
