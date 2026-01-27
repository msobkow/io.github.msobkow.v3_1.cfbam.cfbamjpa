
// Description: Java 25 JPA Default Factory implementation for DelTopDep.

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
 *	CFBamDelTopDepFactory JPA implementation for DelTopDep
 */
public class CFBamJpaDelTopDepDefaultFactory
    implements ICFBamDelTopDepFactory
{
    public CFBamJpaDelTopDepDefaultFactory() {
    }

    @Override
    public ICFBamDelTopDepByDelTopDepTblIdxKey newByDelTopDepTblIdxKey() {
	ICFBamDelTopDepByDelTopDepTblIdxKey key =
            new CFBamJpaDelTopDepByDelTopDepTblIdxKey();
	return( key );
    }

	public CFBamJpaDelTopDepByDelTopDepTblIdxKey ensureByDelTopDepTblIdxKey(ICFBamDelTopDepByDelTopDepTblIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaDelTopDepByDelTopDepTblIdxKey) {
			return( (CFBamJpaDelTopDepByDelTopDepTblIdxKey)key );
		}
		else {
			CFBamJpaDelTopDepByDelTopDepTblIdxKey mapped = new CFBamJpaDelTopDepByDelTopDepTblIdxKey();
			mapped.setRequiredTableId( key.getRequiredTableId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamDelTopDepByUNameIdxKey newByUNameIdxKey() {
	ICFBamDelTopDepByUNameIdxKey key =
            new CFBamJpaDelTopDepByUNameIdxKey();
	return( key );
    }

	public CFBamJpaDelTopDepByUNameIdxKey ensureByUNameIdxKey(ICFBamDelTopDepByUNameIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaDelTopDepByUNameIdxKey) {
			return( (CFBamJpaDelTopDepByUNameIdxKey)key );
		}
		else {
			CFBamJpaDelTopDepByUNameIdxKey mapped = new CFBamJpaDelTopDepByUNameIdxKey();
			mapped.setRequiredTableId( key.getRequiredTableId() );
			mapped.setRequiredName( key.getRequiredName() );
			return( mapped );
		}
	}

    @Override
    public ICFBamDelTopDepByPrevIdxKey newByPrevIdxKey() {
	ICFBamDelTopDepByPrevIdxKey key =
            new CFBamJpaDelTopDepByPrevIdxKey();
	return( key );
    }

	public CFBamJpaDelTopDepByPrevIdxKey ensureByPrevIdxKey(ICFBamDelTopDepByPrevIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaDelTopDepByPrevIdxKey) {
			return( (CFBamJpaDelTopDepByPrevIdxKey)key );
		}
		else {
			CFBamJpaDelTopDepByPrevIdxKey mapped = new CFBamJpaDelTopDepByPrevIdxKey();
			mapped.setOptionalPrevId( key.getOptionalPrevId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamDelTopDepByNextIdxKey newByNextIdxKey() {
	ICFBamDelTopDepByNextIdxKey key =
            new CFBamJpaDelTopDepByNextIdxKey();
	return( key );
    }

	public CFBamJpaDelTopDepByNextIdxKey ensureByNextIdxKey(ICFBamDelTopDepByNextIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaDelTopDepByNextIdxKey) {
			return( (CFBamJpaDelTopDepByNextIdxKey)key );
		}
		else {
			CFBamJpaDelTopDepByNextIdxKey mapped = new CFBamJpaDelTopDepByNextIdxKey();
			mapped.setOptionalNextId( key.getOptionalNextId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamDelTopDep newRec() {
        ICFBamDelTopDep rec =
            new CFBamJpaDelTopDep();
        return( rec );
    }

	public CFBamJpaDelTopDep ensureRec(ICFBamDelTopDep rec) {
		if( rec == null ) {
			return( null );
		}
		else if (rec instanceof CFBamJpaDelTopDep) {
			return( (CFBamJpaDelTopDep)rec );
		}
		else {
			CFBamJpaDelTopDep mapped = new CFBamJpaDelTopDep();
			mapped.set(rec);
			return( mapped );
		}
	}

    @Override
    public ICFBamDelTopDepH newHRec() {
        ICFBamDelTopDepH hrec =
            new CFBamJpaDelTopDepH();
        return( hrec );
    }

	public CFBamJpaDelTopDepH ensureHRec(ICFBamDelTopDepH hrec) {
		if (hrec == null) {
			return( null );
		}
		else if( hrec instanceof CFBamJpaDelTopDepH) {
			return( (CFBamJpaDelTopDepH)hrec );
		}
		else {
			CFBamJpaDelTopDepH mapped = new CFBamJpaDelTopDepH();
			mapped.set(hrec);
			return( mapped );
		}
	}
}
