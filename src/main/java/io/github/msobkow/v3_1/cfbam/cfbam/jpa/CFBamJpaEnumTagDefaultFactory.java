
// Description: Java 25 JPA Default Factory implementation for EnumTag.

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
 *	CFBamEnumTagFactory JPA implementation for EnumTag
 */
public class CFBamJpaEnumTagDefaultFactory
    implements ICFBamEnumTagFactory
{
    public CFBamJpaEnumTagDefaultFactory() {
    }

    @Override
    public ICFBamEnumTagHPKey newHPKey() {
        ICFBamEnumTagHPKey hpkey =
            new CFBamJpaEnumTagHPKey();
        return( hpkey );
    }

	public CFBamJpaEnumTagHPKey ensureHPKey(ICFBamEnumTagHPKey key) {
		if (key == null) {
			return( null );
		}
		else if( key instanceof CFBamJpaEnumTagHPKey) {
			return( (CFBamJpaEnumTagHPKey)key );
		}
		else {
			CFBamJpaEnumTagHPKey mapped = new CFBamJpaEnumTagHPKey();
			mapped.setAuditClusterId(key.getAuditClusterId());
			mapped.setAuditActionId(key.getAuditActionId());
			mapped.setAuditSessionId(key.getAuditSessionId());
			mapped.setAuditStamp(key.getAuditStamp());
			mapped.setRequiredId( key.getRequiredId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamEnumTagByEnumIdxKey newByEnumIdxKey() {
	ICFBamEnumTagByEnumIdxKey key =
            new CFBamJpaEnumTagByEnumIdxKey();
	return( key );
    }

	public CFBamJpaEnumTagByEnumIdxKey ensureByEnumIdxKey(ICFBamEnumTagByEnumIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaEnumTagByEnumIdxKey) {
			return( (CFBamJpaEnumTagByEnumIdxKey)key );
		}
		else {
			CFBamJpaEnumTagByEnumIdxKey mapped = new CFBamJpaEnumTagByEnumIdxKey();
			mapped.setRequiredEnumId( key.getRequiredEnumId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamEnumTagByDefSchemaIdxKey newByDefSchemaIdxKey() {
	ICFBamEnumTagByDefSchemaIdxKey key =
            new CFBamJpaEnumTagByDefSchemaIdxKey();
	return( key );
    }

	public CFBamJpaEnumTagByDefSchemaIdxKey ensureByDefSchemaIdxKey(ICFBamEnumTagByDefSchemaIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaEnumTagByDefSchemaIdxKey) {
			return( (CFBamJpaEnumTagByDefSchemaIdxKey)key );
		}
		else {
			CFBamJpaEnumTagByDefSchemaIdxKey mapped = new CFBamJpaEnumTagByDefSchemaIdxKey();
			mapped.setOptionalDefSchemaId( key.getOptionalDefSchemaId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamEnumTagByEnumNameIdxKey newByEnumNameIdxKey() {
	ICFBamEnumTagByEnumNameIdxKey key =
            new CFBamJpaEnumTagByEnumNameIdxKey();
	return( key );
    }

	public CFBamJpaEnumTagByEnumNameIdxKey ensureByEnumNameIdxKey(ICFBamEnumTagByEnumNameIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaEnumTagByEnumNameIdxKey) {
			return( (CFBamJpaEnumTagByEnumNameIdxKey)key );
		}
		else {
			CFBamJpaEnumTagByEnumNameIdxKey mapped = new CFBamJpaEnumTagByEnumNameIdxKey();
			mapped.setRequiredEnumId( key.getRequiredEnumId() );
			mapped.setRequiredName( key.getRequiredName() );
			return( mapped );
		}
	}

    @Override
    public ICFBamEnumTagByPrevIdxKey newByPrevIdxKey() {
	ICFBamEnumTagByPrevIdxKey key =
            new CFBamJpaEnumTagByPrevIdxKey();
	return( key );
    }

	public CFBamJpaEnumTagByPrevIdxKey ensureByPrevIdxKey(ICFBamEnumTagByPrevIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaEnumTagByPrevIdxKey) {
			return( (CFBamJpaEnumTagByPrevIdxKey)key );
		}
		else {
			CFBamJpaEnumTagByPrevIdxKey mapped = new CFBamJpaEnumTagByPrevIdxKey();
			mapped.setOptionalPrevId( key.getOptionalPrevId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamEnumTagByNextIdxKey newByNextIdxKey() {
	ICFBamEnumTagByNextIdxKey key =
            new CFBamJpaEnumTagByNextIdxKey();
	return( key );
    }

	public CFBamJpaEnumTagByNextIdxKey ensureByNextIdxKey(ICFBamEnumTagByNextIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaEnumTagByNextIdxKey) {
			return( (CFBamJpaEnumTagByNextIdxKey)key );
		}
		else {
			CFBamJpaEnumTagByNextIdxKey mapped = new CFBamJpaEnumTagByNextIdxKey();
			mapped.setOptionalNextId( key.getOptionalNextId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamEnumTag newRec() {
        ICFBamEnumTag rec =
            new CFBamJpaEnumTag();
        return( rec );
    }

	public CFBamJpaEnumTag ensureRec(ICFBamEnumTag rec) {
		if( rec == null ) {
			return( null );
		}
		else if (rec instanceof CFBamJpaEnumTag) {
			return( (CFBamJpaEnumTag)rec );
		}
		else {
			CFBamJpaEnumTag mapped = new CFBamJpaEnumTag();
			mapped.set(rec);
			return( mapped );
		}
	}

    @Override
    public ICFBamEnumTagH newHRec() {
        ICFBamEnumTagH hrec =
            new CFBamJpaEnumTagH();
        return( hrec );
    }

	public CFBamJpaEnumTagH ensureHRec(ICFBamEnumTagH hrec) {
		if (hrec == null) {
			return( null );
		}
		else if( hrec instanceof CFBamJpaEnumTagH) {
			return( (CFBamJpaEnumTagH)hrec );
		}
		else {
			CFBamJpaEnumTagH mapped = new CFBamJpaEnumTagH();
			mapped.set(hrec);
			return( mapped );
		}
	}
}
