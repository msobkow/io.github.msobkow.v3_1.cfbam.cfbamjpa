
// Description: Java 25 JPA Default Factory implementation for ClearSubDep2.

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
 *	CFBamClearSubDep2Factory JPA implementation for ClearSubDep2
 */
public class CFBamJpaClearSubDep2DefaultFactory
    implements ICFBamClearSubDep2Factory
{
    public CFBamJpaClearSubDep2DefaultFactory() {
    }

    @Override
    public ICFBamClearSubDep2ByClearSubDep1IdxKey newByClearSubDep1IdxKey() {
	ICFBamClearSubDep2ByClearSubDep1IdxKey key =
            new CFBamJpaClearSubDep2ByClearSubDep1IdxKey();
	return( key );
    }

	public CFBamJpaClearSubDep2ByClearSubDep1IdxKey ensureByClearSubDep1IdxKey(ICFBamClearSubDep2ByClearSubDep1IdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaClearSubDep2ByClearSubDep1IdxKey) {
			return( (CFBamJpaClearSubDep2ByClearSubDep1IdxKey)key );
		}
		else {
			CFBamJpaClearSubDep2ByClearSubDep1IdxKey mapped = new CFBamJpaClearSubDep2ByClearSubDep1IdxKey();
			mapped.setRequiredClearSubDep1Id( key.getRequiredClearSubDep1Id() );
			return( mapped );
		}
	}

    @Override
    public ICFBamClearSubDep2ByUNameIdxKey newByUNameIdxKey() {
	ICFBamClearSubDep2ByUNameIdxKey key =
            new CFBamJpaClearSubDep2ByUNameIdxKey();
	return( key );
    }

	public CFBamJpaClearSubDep2ByUNameIdxKey ensureByUNameIdxKey(ICFBamClearSubDep2ByUNameIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaClearSubDep2ByUNameIdxKey) {
			return( (CFBamJpaClearSubDep2ByUNameIdxKey)key );
		}
		else {
			CFBamJpaClearSubDep2ByUNameIdxKey mapped = new CFBamJpaClearSubDep2ByUNameIdxKey();
			mapped.setRequiredClearSubDep1Id( key.getRequiredClearSubDep1Id() );
			mapped.setRequiredName( key.getRequiredName() );
			return( mapped );
		}
	}

    @Override
    public ICFBamClearSubDep2 newRec() {
        ICFBamClearSubDep2 rec =
            new CFBamJpaClearSubDep2();
        return( rec );
    }

	public CFBamJpaClearSubDep2 ensureRec(ICFBamClearSubDep2 rec) {
		if( rec == null ) {
			return( null );
		}
		else if (rec instanceof CFBamJpaClearSubDep2) {
			return( (CFBamJpaClearSubDep2)rec );
		}
		else {
			CFBamJpaClearSubDep2 mapped = new CFBamJpaClearSubDep2();
			mapped.set(rec);
			return( mapped );
		}
	}

    @Override
    public ICFBamClearSubDep2H newHRec() {
        ICFBamClearSubDep2H hrec =
            new CFBamJpaClearSubDep2H();
        return( hrec );
    }

	public CFBamJpaClearSubDep2H ensureHRec(ICFBamClearSubDep2H hrec) {
		if (hrec == null) {
			return( null );
		}
		else if( hrec instanceof CFBamJpaClearSubDep2H) {
			return( (CFBamJpaClearSubDep2H)hrec );
		}
		else {
			CFBamJpaClearSubDep2H mapped = new CFBamJpaClearSubDep2H();
			mapped.set(hrec);
			return( mapped );
		}
	}
}
