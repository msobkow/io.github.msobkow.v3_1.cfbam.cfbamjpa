
// Description: Java 25 JPA Default Factory implementation for DbKeyHash256Gen.

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
 *	CFBamDbKeyHash256GenFactory JPA implementation for DbKeyHash256Gen
 */
public class CFBamJpaDbKeyHash256GenDefaultFactory
    implements ICFBamDbKeyHash256GenFactory
{
    public CFBamJpaDbKeyHash256GenDefaultFactory() {
    }

    @Override
    public ICFBamDbKeyHash256Gen newRec() {
        ICFBamDbKeyHash256Gen rec =
            new CFBamJpaDbKeyHash256Gen();
        return( rec );
    }

	public CFBamJpaDbKeyHash256Gen ensureRec(ICFBamDbKeyHash256Gen rec) {
		if( rec == null ) {
			return( null );
		}
		else if (rec instanceof CFBamJpaDbKeyHash256Gen) {
			return( (CFBamJpaDbKeyHash256Gen)rec );
		}
		else {
			CFBamJpaDbKeyHash256Gen mapped = new CFBamJpaDbKeyHash256Gen();
			mapped.set(rec);
			return( mapped );
		}
	}

    @Override
    public ICFBamDbKeyHash256GenH newHRec() {
        ICFBamDbKeyHash256GenH hrec =
            new CFBamJpaDbKeyHash256GenH();
        return( hrec );
    }

	public CFBamJpaDbKeyHash256GenH ensureHRec(ICFBamDbKeyHash256GenH hrec) {
		if (hrec == null) {
			return( null );
		}
		else if( hrec instanceof CFBamJpaDbKeyHash256GenH) {
			return( (CFBamJpaDbKeyHash256GenH)hrec );
		}
		else {
			CFBamJpaDbKeyHash256GenH mapped = new CFBamJpaDbKeyHash256GenH();
			mapped.set(hrec);
			return( mapped );
		}
	}
}
