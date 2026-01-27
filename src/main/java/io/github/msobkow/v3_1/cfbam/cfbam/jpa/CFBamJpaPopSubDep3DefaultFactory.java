
// Description: Java 25 JPA Default Factory implementation for PopSubDep3.

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
 *	CFBamPopSubDep3Factory JPA implementation for PopSubDep3
 */
public class CFBamJpaPopSubDep3DefaultFactory
    implements ICFBamPopSubDep3Factory
{
    public CFBamJpaPopSubDep3DefaultFactory() {
    }

    @Override
    public ICFBamPopSubDep3ByPopSubDep2IdxKey newByPopSubDep2IdxKey() {
	ICFBamPopSubDep3ByPopSubDep2IdxKey key =
            new CFBamJpaPopSubDep3ByPopSubDep2IdxKey();
	return( key );
    }

	public CFBamJpaPopSubDep3ByPopSubDep2IdxKey ensureByPopSubDep2IdxKey(ICFBamPopSubDep3ByPopSubDep2IdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaPopSubDep3ByPopSubDep2IdxKey) {
			return( (CFBamJpaPopSubDep3ByPopSubDep2IdxKey)key );
		}
		else {
			CFBamJpaPopSubDep3ByPopSubDep2IdxKey mapped = new CFBamJpaPopSubDep3ByPopSubDep2IdxKey();
			mapped.setRequiredPopSubDep2Id( key.getRequiredPopSubDep2Id() );
			return( mapped );
		}
	}

    @Override
    public ICFBamPopSubDep3ByUNameIdxKey newByUNameIdxKey() {
	ICFBamPopSubDep3ByUNameIdxKey key =
            new CFBamJpaPopSubDep3ByUNameIdxKey();
	return( key );
    }

	public CFBamJpaPopSubDep3ByUNameIdxKey ensureByUNameIdxKey(ICFBamPopSubDep3ByUNameIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaPopSubDep3ByUNameIdxKey) {
			return( (CFBamJpaPopSubDep3ByUNameIdxKey)key );
		}
		else {
			CFBamJpaPopSubDep3ByUNameIdxKey mapped = new CFBamJpaPopSubDep3ByUNameIdxKey();
			mapped.setRequiredPopSubDep2Id( key.getRequiredPopSubDep2Id() );
			mapped.setRequiredName( key.getRequiredName() );
			return( mapped );
		}
	}

    @Override
    public ICFBamPopSubDep3 newRec() {
        ICFBamPopSubDep3 rec =
            new CFBamJpaPopSubDep3();
        return( rec );
    }

	public CFBamJpaPopSubDep3 ensureRec(ICFBamPopSubDep3 rec) {
		if( rec == null ) {
			return( null );
		}
		else if (rec instanceof CFBamJpaPopSubDep3) {
			return( (CFBamJpaPopSubDep3)rec );
		}
		else {
			CFBamJpaPopSubDep3 mapped = new CFBamJpaPopSubDep3();
			mapped.set(rec);
			return( mapped );
		}
	}

    @Override
    public ICFBamPopSubDep3H newHRec() {
        ICFBamPopSubDep3H hrec =
            new CFBamJpaPopSubDep3H();
        return( hrec );
    }

	public CFBamJpaPopSubDep3H ensureHRec(ICFBamPopSubDep3H hrec) {
		if (hrec == null) {
			return( null );
		}
		else if( hrec instanceof CFBamJpaPopSubDep3H) {
			return( (CFBamJpaPopSubDep3H)hrec );
		}
		else {
			CFBamJpaPopSubDep3H mapped = new CFBamJpaPopSubDep3H();
			mapped.set(hrec);
			return( mapped );
		}
	}
}
