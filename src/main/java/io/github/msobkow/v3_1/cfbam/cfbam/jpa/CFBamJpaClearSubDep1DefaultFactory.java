
// Description: Java 25 JPA Default Factory implementation for ClearSubDep1.

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
 *	CFBamClearSubDep1Factory JPA implementation for ClearSubDep1
 */
public class CFBamJpaClearSubDep1DefaultFactory
    implements ICFBamClearSubDep1Factory
{
    public CFBamJpaClearSubDep1DefaultFactory() {
    }

    @Override
    public ICFBamClearSubDep1ByClearTopDepIdxKey newByClearTopDepIdxKey() {
	ICFBamClearSubDep1ByClearTopDepIdxKey key =
            new CFBamJpaClearSubDep1ByClearTopDepIdxKey();
	return( key );
    }

	public CFBamJpaClearSubDep1ByClearTopDepIdxKey ensureByClearTopDepIdxKey(ICFBamClearSubDep1ByClearTopDepIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaClearSubDep1ByClearTopDepIdxKey) {
			return( (CFBamJpaClearSubDep1ByClearTopDepIdxKey)key );
		}
		else {
			CFBamJpaClearSubDep1ByClearTopDepIdxKey mapped = new CFBamJpaClearSubDep1ByClearTopDepIdxKey();
			mapped.setRequiredClearTopDepId( key.getRequiredClearTopDepId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamClearSubDep1ByUNameIdxKey newByUNameIdxKey() {
	ICFBamClearSubDep1ByUNameIdxKey key =
            new CFBamJpaClearSubDep1ByUNameIdxKey();
	return( key );
    }

	public CFBamJpaClearSubDep1ByUNameIdxKey ensureByUNameIdxKey(ICFBamClearSubDep1ByUNameIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaClearSubDep1ByUNameIdxKey) {
			return( (CFBamJpaClearSubDep1ByUNameIdxKey)key );
		}
		else {
			CFBamJpaClearSubDep1ByUNameIdxKey mapped = new CFBamJpaClearSubDep1ByUNameIdxKey();
			mapped.setRequiredClearTopDepId( key.getRequiredClearTopDepId() );
			mapped.setRequiredName( key.getRequiredName() );
			return( mapped );
		}
	}

    @Override
    public ICFBamClearSubDep1 newRec() {
        ICFBamClearSubDep1 rec =
            new CFBamJpaClearSubDep1();
        return( rec );
    }

	public CFBamJpaClearSubDep1 ensureRec(ICFBamClearSubDep1 rec) {
		if( rec == null ) {
			return( null );
		}
		else if (rec instanceof CFBamJpaClearSubDep1) {
			return( (CFBamJpaClearSubDep1)rec );
		}
		else {
			CFBamJpaClearSubDep1 mapped = new CFBamJpaClearSubDep1();
			mapped.set(rec);
			return( mapped );
		}
	}

    @Override
    public ICFBamClearSubDep1H newHRec() {
        ICFBamClearSubDep1H hrec =
            new CFBamJpaClearSubDep1H();
        return( hrec );
    }

	public CFBamJpaClearSubDep1H ensureHRec(ICFBamClearSubDep1H hrec) {
		if (hrec == null) {
			return( null );
		}
		else if( hrec instanceof CFBamJpaClearSubDep1H) {
			return( (CFBamJpaClearSubDep1H)hrec );
		}
		else {
			CFBamJpaClearSubDep1H mapped = new CFBamJpaClearSubDep1H();
			mapped.set(hrec);
			return( mapped );
		}
	}
}
