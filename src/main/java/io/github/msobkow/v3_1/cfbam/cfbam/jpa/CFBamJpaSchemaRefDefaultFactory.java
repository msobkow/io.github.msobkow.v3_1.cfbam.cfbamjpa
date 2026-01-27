
// Description: Java 25 JPA Default Factory implementation for SchemaRef.

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
 *	CFBamSchemaRefFactory JPA implementation for SchemaRef
 */
public class CFBamJpaSchemaRefDefaultFactory
    implements ICFBamSchemaRefFactory
{
    public CFBamJpaSchemaRefDefaultFactory() {
    }

    @Override
    public ICFBamSchemaRefBySchemaIdxKey newBySchemaIdxKey() {
	ICFBamSchemaRefBySchemaIdxKey key =
            new CFBamJpaSchemaRefBySchemaIdxKey();
	return( key );
    }

	public CFBamJpaSchemaRefBySchemaIdxKey ensureBySchemaIdxKey(ICFBamSchemaRefBySchemaIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaSchemaRefBySchemaIdxKey) {
			return( (CFBamJpaSchemaRefBySchemaIdxKey)key );
		}
		else {
			CFBamJpaSchemaRefBySchemaIdxKey mapped = new CFBamJpaSchemaRefBySchemaIdxKey();
			mapped.setRequiredSchemaId( key.getRequiredSchemaId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamSchemaRefByUNameIdxKey newByUNameIdxKey() {
	ICFBamSchemaRefByUNameIdxKey key =
            new CFBamJpaSchemaRefByUNameIdxKey();
	return( key );
    }

	public CFBamJpaSchemaRefByUNameIdxKey ensureByUNameIdxKey(ICFBamSchemaRefByUNameIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaSchemaRefByUNameIdxKey) {
			return( (CFBamJpaSchemaRefByUNameIdxKey)key );
		}
		else {
			CFBamJpaSchemaRefByUNameIdxKey mapped = new CFBamJpaSchemaRefByUNameIdxKey();
			mapped.setRequiredSchemaId( key.getRequiredSchemaId() );
			mapped.setRequiredName( key.getRequiredName() );
			return( mapped );
		}
	}

    @Override
    public ICFBamSchemaRefByRefSchemaIdxKey newByRefSchemaIdxKey() {
	ICFBamSchemaRefByRefSchemaIdxKey key =
            new CFBamJpaSchemaRefByRefSchemaIdxKey();
	return( key );
    }

	public CFBamJpaSchemaRefByRefSchemaIdxKey ensureByRefSchemaIdxKey(ICFBamSchemaRefByRefSchemaIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaSchemaRefByRefSchemaIdxKey) {
			return( (CFBamJpaSchemaRefByRefSchemaIdxKey)key );
		}
		else {
			CFBamJpaSchemaRefByRefSchemaIdxKey mapped = new CFBamJpaSchemaRefByRefSchemaIdxKey();
			mapped.setOptionalRefSchemaId( key.getOptionalRefSchemaId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamSchemaRefByPrevIdxKey newByPrevIdxKey() {
	ICFBamSchemaRefByPrevIdxKey key =
            new CFBamJpaSchemaRefByPrevIdxKey();
	return( key );
    }

	public CFBamJpaSchemaRefByPrevIdxKey ensureByPrevIdxKey(ICFBamSchemaRefByPrevIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaSchemaRefByPrevIdxKey) {
			return( (CFBamJpaSchemaRefByPrevIdxKey)key );
		}
		else {
			CFBamJpaSchemaRefByPrevIdxKey mapped = new CFBamJpaSchemaRefByPrevIdxKey();
			mapped.setOptionalPrevId( key.getOptionalPrevId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamSchemaRefByNextIdxKey newByNextIdxKey() {
	ICFBamSchemaRefByNextIdxKey key =
            new CFBamJpaSchemaRefByNextIdxKey();
	return( key );
    }

	public CFBamJpaSchemaRefByNextIdxKey ensureByNextIdxKey(ICFBamSchemaRefByNextIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaSchemaRefByNextIdxKey) {
			return( (CFBamJpaSchemaRefByNextIdxKey)key );
		}
		else {
			CFBamJpaSchemaRefByNextIdxKey mapped = new CFBamJpaSchemaRefByNextIdxKey();
			mapped.setOptionalNextId( key.getOptionalNextId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamSchemaRef newRec() {
        ICFBamSchemaRef rec =
            new CFBamJpaSchemaRef();
        return( rec );
    }

	public CFBamJpaSchemaRef ensureRec(ICFBamSchemaRef rec) {
		if( rec == null ) {
			return( null );
		}
		else if (rec instanceof CFBamJpaSchemaRef) {
			return( (CFBamJpaSchemaRef)rec );
		}
		else {
			CFBamJpaSchemaRef mapped = new CFBamJpaSchemaRef();
			mapped.set(rec);
			return( mapped );
		}
	}

    @Override
    public ICFBamSchemaRefH newHRec() {
        ICFBamSchemaRefH hrec =
            new CFBamJpaSchemaRefH();
        return( hrec );
    }

	public CFBamJpaSchemaRefH ensureHRec(ICFBamSchemaRefH hrec) {
		if (hrec == null) {
			return( null );
		}
		else if( hrec instanceof CFBamJpaSchemaRefH) {
			return( (CFBamJpaSchemaRefH)hrec );
		}
		else {
			CFBamJpaSchemaRefH mapped = new CFBamJpaSchemaRefH();
			mapped.set(hrec);
			return( mapped );
		}
	}
}
