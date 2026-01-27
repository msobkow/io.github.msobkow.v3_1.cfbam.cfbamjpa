
// Description: Java 25 JPA Default Factory implementation for RelationCol.

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
 *	CFBamRelationColFactory JPA implementation for RelationCol
 */
public class CFBamJpaRelationColDefaultFactory
    implements ICFBamRelationColFactory
{
    public CFBamJpaRelationColDefaultFactory() {
    }

    @Override
    public ICFBamRelationColHPKey newHPKey() {
        ICFBamRelationColHPKey hpkey =
            new CFBamJpaRelationColHPKey();
        return( hpkey );
    }

	public CFBamJpaRelationColHPKey ensureHPKey(ICFBamRelationColHPKey key) {
		if (key == null) {
			return( null );
		}
		else if( key instanceof CFBamJpaRelationColHPKey) {
			return( (CFBamJpaRelationColHPKey)key );
		}
		else {
			CFBamJpaRelationColHPKey mapped = new CFBamJpaRelationColHPKey();
			mapped.setAuditClusterId(key.getAuditClusterId());
			mapped.setAuditActionId(key.getAuditActionId());
			mapped.setAuditSessionId(key.getAuditSessionId());
			mapped.setAuditStamp(key.getAuditStamp());
			mapped.setRequiredId( key.getRequiredId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamRelationColByUNameIdxKey newByUNameIdxKey() {
	ICFBamRelationColByUNameIdxKey key =
            new CFBamJpaRelationColByUNameIdxKey();
	return( key );
    }

	public CFBamJpaRelationColByUNameIdxKey ensureByUNameIdxKey(ICFBamRelationColByUNameIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaRelationColByUNameIdxKey) {
			return( (CFBamJpaRelationColByUNameIdxKey)key );
		}
		else {
			CFBamJpaRelationColByUNameIdxKey mapped = new CFBamJpaRelationColByUNameIdxKey();
			mapped.setRequiredRelationId( key.getRequiredRelationId() );
			mapped.setRequiredName( key.getRequiredName() );
			return( mapped );
		}
	}

    @Override
    public ICFBamRelationColByRelationIdxKey newByRelationIdxKey() {
	ICFBamRelationColByRelationIdxKey key =
            new CFBamJpaRelationColByRelationIdxKey();
	return( key );
    }

	public CFBamJpaRelationColByRelationIdxKey ensureByRelationIdxKey(ICFBamRelationColByRelationIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaRelationColByRelationIdxKey) {
			return( (CFBamJpaRelationColByRelationIdxKey)key );
		}
		else {
			CFBamJpaRelationColByRelationIdxKey mapped = new CFBamJpaRelationColByRelationIdxKey();
			mapped.setRequiredRelationId( key.getRequiredRelationId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamRelationColByDefSchemaIdxKey newByDefSchemaIdxKey() {
	ICFBamRelationColByDefSchemaIdxKey key =
            new CFBamJpaRelationColByDefSchemaIdxKey();
	return( key );
    }

	public CFBamJpaRelationColByDefSchemaIdxKey ensureByDefSchemaIdxKey(ICFBamRelationColByDefSchemaIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaRelationColByDefSchemaIdxKey) {
			return( (CFBamJpaRelationColByDefSchemaIdxKey)key );
		}
		else {
			CFBamJpaRelationColByDefSchemaIdxKey mapped = new CFBamJpaRelationColByDefSchemaIdxKey();
			mapped.setOptionalDefSchemaId( key.getOptionalDefSchemaId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamRelationColByFromColIdxKey newByFromColIdxKey() {
	ICFBamRelationColByFromColIdxKey key =
            new CFBamJpaRelationColByFromColIdxKey();
	return( key );
    }

	public CFBamJpaRelationColByFromColIdxKey ensureByFromColIdxKey(ICFBamRelationColByFromColIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaRelationColByFromColIdxKey) {
			return( (CFBamJpaRelationColByFromColIdxKey)key );
		}
		else {
			CFBamJpaRelationColByFromColIdxKey mapped = new CFBamJpaRelationColByFromColIdxKey();
			mapped.setRequiredFromColId( key.getRequiredFromColId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamRelationColByToColIdxKey newByToColIdxKey() {
	ICFBamRelationColByToColIdxKey key =
            new CFBamJpaRelationColByToColIdxKey();
	return( key );
    }

	public CFBamJpaRelationColByToColIdxKey ensureByToColIdxKey(ICFBamRelationColByToColIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaRelationColByToColIdxKey) {
			return( (CFBamJpaRelationColByToColIdxKey)key );
		}
		else {
			CFBamJpaRelationColByToColIdxKey mapped = new CFBamJpaRelationColByToColIdxKey();
			mapped.setRequiredToColId( key.getRequiredToColId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamRelationColByPrevIdxKey newByPrevIdxKey() {
	ICFBamRelationColByPrevIdxKey key =
            new CFBamJpaRelationColByPrevIdxKey();
	return( key );
    }

	public CFBamJpaRelationColByPrevIdxKey ensureByPrevIdxKey(ICFBamRelationColByPrevIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaRelationColByPrevIdxKey) {
			return( (CFBamJpaRelationColByPrevIdxKey)key );
		}
		else {
			CFBamJpaRelationColByPrevIdxKey mapped = new CFBamJpaRelationColByPrevIdxKey();
			mapped.setOptionalPrevId( key.getOptionalPrevId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamRelationColByNextIdxKey newByNextIdxKey() {
	ICFBamRelationColByNextIdxKey key =
            new CFBamJpaRelationColByNextIdxKey();
	return( key );
    }

	public CFBamJpaRelationColByNextIdxKey ensureByNextIdxKey(ICFBamRelationColByNextIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaRelationColByNextIdxKey) {
			return( (CFBamJpaRelationColByNextIdxKey)key );
		}
		else {
			CFBamJpaRelationColByNextIdxKey mapped = new CFBamJpaRelationColByNextIdxKey();
			mapped.setOptionalNextId( key.getOptionalNextId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamRelationColByRelPrevIdxKey newByRelPrevIdxKey() {
	ICFBamRelationColByRelPrevIdxKey key =
            new CFBamJpaRelationColByRelPrevIdxKey();
	return( key );
    }

	public CFBamJpaRelationColByRelPrevIdxKey ensureByRelPrevIdxKey(ICFBamRelationColByRelPrevIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaRelationColByRelPrevIdxKey) {
			return( (CFBamJpaRelationColByRelPrevIdxKey)key );
		}
		else {
			CFBamJpaRelationColByRelPrevIdxKey mapped = new CFBamJpaRelationColByRelPrevIdxKey();
			mapped.setRequiredRelationId( key.getRequiredRelationId() );
			mapped.setOptionalPrevId( key.getOptionalPrevId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamRelationColByRelNextIdxKey newByRelNextIdxKey() {
	ICFBamRelationColByRelNextIdxKey key =
            new CFBamJpaRelationColByRelNextIdxKey();
	return( key );
    }

	public CFBamJpaRelationColByRelNextIdxKey ensureByRelNextIdxKey(ICFBamRelationColByRelNextIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaRelationColByRelNextIdxKey) {
			return( (CFBamJpaRelationColByRelNextIdxKey)key );
		}
		else {
			CFBamJpaRelationColByRelNextIdxKey mapped = new CFBamJpaRelationColByRelNextIdxKey();
			mapped.setRequiredRelationId( key.getRequiredRelationId() );
			mapped.setOptionalNextId( key.getOptionalNextId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamRelationCol newRec() {
        ICFBamRelationCol rec =
            new CFBamJpaRelationCol();
        return( rec );
    }

	public CFBamJpaRelationCol ensureRec(ICFBamRelationCol rec) {
		if( rec == null ) {
			return( null );
		}
		else if (rec instanceof CFBamJpaRelationCol) {
			return( (CFBamJpaRelationCol)rec );
		}
		else {
			CFBamJpaRelationCol mapped = new CFBamJpaRelationCol();
			mapped.set(rec);
			return( mapped );
		}
	}

    @Override
    public ICFBamRelationColH newHRec() {
        ICFBamRelationColH hrec =
            new CFBamJpaRelationColH();
        return( hrec );
    }

	public CFBamJpaRelationColH ensureHRec(ICFBamRelationColH hrec) {
		if (hrec == null) {
			return( null );
		}
		else if( hrec instanceof CFBamJpaRelationColH) {
			return( (CFBamJpaRelationColH)hrec );
		}
		else {
			CFBamJpaRelationColH mapped = new CFBamJpaRelationColH();
			mapped.set(hrec);
			return( mapped );
		}
	}
}
