
// Description: Java 25 JPA Default Factory implementation for DelSubDep3.

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
 *	CFBamDelSubDep3Factory JPA implementation for DelSubDep3
 */
public class CFBamJpaDelSubDep3DefaultFactory
    implements ICFBamDelSubDep3Factory
{
    public CFBamJpaDelSubDep3DefaultFactory() {
    }

    @Override
    public ICFBamDelSubDep3ByDelSubDep2IdxKey newByDelSubDep2IdxKey() {
	ICFBamDelSubDep3ByDelSubDep2IdxKey key =
            new CFBamJpaDelSubDep3ByDelSubDep2IdxKey();
	return( key );
    }

	public CFBamJpaDelSubDep3ByDelSubDep2IdxKey ensureByDelSubDep2IdxKey(ICFBamDelSubDep3ByDelSubDep2IdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaDelSubDep3ByDelSubDep2IdxKey) {
			return( (CFBamJpaDelSubDep3ByDelSubDep2IdxKey)key );
		}
		else {
			CFBamJpaDelSubDep3ByDelSubDep2IdxKey mapped = new CFBamJpaDelSubDep3ByDelSubDep2IdxKey();
			mapped.setRequiredDelSubDep2Id( key.getRequiredDelSubDep2Id() );
			return( mapped );
		}
	}

    @Override
    public ICFBamDelSubDep3ByUNameIdxKey newByUNameIdxKey() {
	ICFBamDelSubDep3ByUNameIdxKey key =
            new CFBamJpaDelSubDep3ByUNameIdxKey();
	return( key );
    }

	public CFBamJpaDelSubDep3ByUNameIdxKey ensureByUNameIdxKey(ICFBamDelSubDep3ByUNameIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaDelSubDep3ByUNameIdxKey) {
			return( (CFBamJpaDelSubDep3ByUNameIdxKey)key );
		}
		else {
			CFBamJpaDelSubDep3ByUNameIdxKey mapped = new CFBamJpaDelSubDep3ByUNameIdxKey();
			mapped.setRequiredDelSubDep2Id( key.getRequiredDelSubDep2Id() );
			mapped.setRequiredName( key.getRequiredName() );
			return( mapped );
		}
	}

    @Override
    public ICFBamDelSubDep3 newRec() {
        ICFBamDelSubDep3 rec =
            new CFBamJpaDelSubDep3();
        return( rec );
    }

	public CFBamJpaDelSubDep3 ensureRec(ICFBamDelSubDep3 rec) {
		if( rec == null ) {
			return( null );
		}
		else if (rec instanceof CFBamJpaDelSubDep3) {
			return( (CFBamJpaDelSubDep3)rec );
		}
		else {
			CFBamJpaDelSubDep3 mapped = new CFBamJpaDelSubDep3();
			mapped.set(rec);
			return( mapped );
		}
	}

    @Override
    public ICFBamDelSubDep3H newHRec() {
        ICFBamDelSubDep3H hrec =
            new CFBamJpaDelSubDep3H();
        return( hrec );
    }

	public CFBamJpaDelSubDep3H ensureHRec(ICFBamDelSubDep3H hrec) {
		if (hrec == null) {
			return( null );
		}
		else if( hrec instanceof CFBamJpaDelSubDep3H) {
			return( (CFBamJpaDelSubDep3H)hrec );
		}
		else {
			CFBamJpaDelSubDep3H mapped = new CFBamJpaDelSubDep3H();
			mapped.set(hrec);
			return( mapped );
		}
	}
}
