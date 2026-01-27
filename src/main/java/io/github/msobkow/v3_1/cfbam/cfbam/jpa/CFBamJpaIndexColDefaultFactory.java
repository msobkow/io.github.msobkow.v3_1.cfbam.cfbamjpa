
// Description: Java 25 JPA Default Factory implementation for IndexCol.

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
 *	CFBamIndexColFactory JPA implementation for IndexCol
 */
public class CFBamJpaIndexColDefaultFactory
    implements ICFBamIndexColFactory
{
    public CFBamJpaIndexColDefaultFactory() {
    }

    @Override
    public ICFBamIndexColHPKey newHPKey() {
        ICFBamIndexColHPKey hpkey =
            new CFBamJpaIndexColHPKey();
        return( hpkey );
    }

	public CFBamJpaIndexColHPKey ensureHPKey(ICFBamIndexColHPKey key) {
		if (key == null) {
			return( null );
		}
		else if( key instanceof CFBamJpaIndexColHPKey) {
			return( (CFBamJpaIndexColHPKey)key );
		}
		else {
			CFBamJpaIndexColHPKey mapped = new CFBamJpaIndexColHPKey();
			mapped.setAuditClusterId(key.getAuditClusterId());
			mapped.setAuditActionId(key.getAuditActionId());
			mapped.setAuditSessionId(key.getAuditSessionId());
			mapped.setAuditStamp(key.getAuditStamp());
			mapped.setRequiredId( key.getRequiredId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamIndexColByUNameIdxKey newByUNameIdxKey() {
	ICFBamIndexColByUNameIdxKey key =
            new CFBamJpaIndexColByUNameIdxKey();
	return( key );
    }

	public CFBamJpaIndexColByUNameIdxKey ensureByUNameIdxKey(ICFBamIndexColByUNameIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaIndexColByUNameIdxKey) {
			return( (CFBamJpaIndexColByUNameIdxKey)key );
		}
		else {
			CFBamJpaIndexColByUNameIdxKey mapped = new CFBamJpaIndexColByUNameIdxKey();
			mapped.setRequiredIndexId( key.getRequiredIndexId() );
			mapped.setRequiredName( key.getRequiredName() );
			return( mapped );
		}
	}

    @Override
    public ICFBamIndexColByIndexIdxKey newByIndexIdxKey() {
	ICFBamIndexColByIndexIdxKey key =
            new CFBamJpaIndexColByIndexIdxKey();
	return( key );
    }

	public CFBamJpaIndexColByIndexIdxKey ensureByIndexIdxKey(ICFBamIndexColByIndexIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaIndexColByIndexIdxKey) {
			return( (CFBamJpaIndexColByIndexIdxKey)key );
		}
		else {
			CFBamJpaIndexColByIndexIdxKey mapped = new CFBamJpaIndexColByIndexIdxKey();
			mapped.setRequiredIndexId( key.getRequiredIndexId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamIndexColByDefSchemaIdxKey newByDefSchemaIdxKey() {
	ICFBamIndexColByDefSchemaIdxKey key =
            new CFBamJpaIndexColByDefSchemaIdxKey();
	return( key );
    }

	public CFBamJpaIndexColByDefSchemaIdxKey ensureByDefSchemaIdxKey(ICFBamIndexColByDefSchemaIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaIndexColByDefSchemaIdxKey) {
			return( (CFBamJpaIndexColByDefSchemaIdxKey)key );
		}
		else {
			CFBamJpaIndexColByDefSchemaIdxKey mapped = new CFBamJpaIndexColByDefSchemaIdxKey();
			mapped.setOptionalDefSchemaId( key.getOptionalDefSchemaId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamIndexColByColIdxKey newByColIdxKey() {
	ICFBamIndexColByColIdxKey key =
            new CFBamJpaIndexColByColIdxKey();
	return( key );
    }

	public CFBamJpaIndexColByColIdxKey ensureByColIdxKey(ICFBamIndexColByColIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaIndexColByColIdxKey) {
			return( (CFBamJpaIndexColByColIdxKey)key );
		}
		else {
			CFBamJpaIndexColByColIdxKey mapped = new CFBamJpaIndexColByColIdxKey();
			mapped.setRequiredColumnId( key.getRequiredColumnId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamIndexColByPrevIdxKey newByPrevIdxKey() {
	ICFBamIndexColByPrevIdxKey key =
            new CFBamJpaIndexColByPrevIdxKey();
	return( key );
    }

	public CFBamJpaIndexColByPrevIdxKey ensureByPrevIdxKey(ICFBamIndexColByPrevIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaIndexColByPrevIdxKey) {
			return( (CFBamJpaIndexColByPrevIdxKey)key );
		}
		else {
			CFBamJpaIndexColByPrevIdxKey mapped = new CFBamJpaIndexColByPrevIdxKey();
			mapped.setOptionalPrevId( key.getOptionalPrevId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamIndexColByNextIdxKey newByNextIdxKey() {
	ICFBamIndexColByNextIdxKey key =
            new CFBamJpaIndexColByNextIdxKey();
	return( key );
    }

	public CFBamJpaIndexColByNextIdxKey ensureByNextIdxKey(ICFBamIndexColByNextIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaIndexColByNextIdxKey) {
			return( (CFBamJpaIndexColByNextIdxKey)key );
		}
		else {
			CFBamJpaIndexColByNextIdxKey mapped = new CFBamJpaIndexColByNextIdxKey();
			mapped.setOptionalNextId( key.getOptionalNextId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamIndexColByIdxPrevIdxKey newByIdxPrevIdxKey() {
	ICFBamIndexColByIdxPrevIdxKey key =
            new CFBamJpaIndexColByIdxPrevIdxKey();
	return( key );
    }

	public CFBamJpaIndexColByIdxPrevIdxKey ensureByIdxPrevIdxKey(ICFBamIndexColByIdxPrevIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaIndexColByIdxPrevIdxKey) {
			return( (CFBamJpaIndexColByIdxPrevIdxKey)key );
		}
		else {
			CFBamJpaIndexColByIdxPrevIdxKey mapped = new CFBamJpaIndexColByIdxPrevIdxKey();
			mapped.setRequiredIndexId( key.getRequiredIndexId() );
			mapped.setOptionalPrevId( key.getOptionalPrevId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamIndexColByIdxNextIdxKey newByIdxNextIdxKey() {
	ICFBamIndexColByIdxNextIdxKey key =
            new CFBamJpaIndexColByIdxNextIdxKey();
	return( key );
    }

	public CFBamJpaIndexColByIdxNextIdxKey ensureByIdxNextIdxKey(ICFBamIndexColByIdxNextIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaIndexColByIdxNextIdxKey) {
			return( (CFBamJpaIndexColByIdxNextIdxKey)key );
		}
		else {
			CFBamJpaIndexColByIdxNextIdxKey mapped = new CFBamJpaIndexColByIdxNextIdxKey();
			mapped.setRequiredIndexId( key.getRequiredIndexId() );
			mapped.setOptionalNextId( key.getOptionalNextId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamIndexCol newRec() {
        ICFBamIndexCol rec =
            new CFBamJpaIndexCol();
        return( rec );
    }

	public CFBamJpaIndexCol ensureRec(ICFBamIndexCol rec) {
		if( rec == null ) {
			return( null );
		}
		else if (rec instanceof CFBamJpaIndexCol) {
			return( (CFBamJpaIndexCol)rec );
		}
		else {
			CFBamJpaIndexCol mapped = new CFBamJpaIndexCol();
			mapped.set(rec);
			return( mapped );
		}
	}

    @Override
    public ICFBamIndexColH newHRec() {
        ICFBamIndexColH hrec =
            new CFBamJpaIndexColH();
        return( hrec );
    }

	public CFBamJpaIndexColH ensureHRec(ICFBamIndexColH hrec) {
		if (hrec == null) {
			return( null );
		}
		else if( hrec instanceof CFBamJpaIndexColH) {
			return( (CFBamJpaIndexColH)hrec );
		}
		else {
			CFBamJpaIndexColH mapped = new CFBamJpaIndexColH();
			mapped.set(hrec);
			return( mapped );
		}
	}
}
