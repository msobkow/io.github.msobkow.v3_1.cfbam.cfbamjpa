
// Description: Java 25 JPA Default Factory implementation for Relation.

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
 *	CFBamRelationFactory JPA implementation for Relation
 */
public class CFBamJpaRelationDefaultFactory
    implements ICFBamRelationFactory
{
    public CFBamJpaRelationDefaultFactory() {
    }

    @Override
    public ICFBamRelationByUNameIdxKey newByUNameIdxKey() {
	ICFBamRelationByUNameIdxKey key =
            new CFBamJpaRelationByUNameIdxKey();
	return( key );
    }

	public CFBamJpaRelationByUNameIdxKey ensureByUNameIdxKey(ICFBamRelationByUNameIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaRelationByUNameIdxKey) {
			return( (CFBamJpaRelationByUNameIdxKey)key );
		}
		else {
			CFBamJpaRelationByUNameIdxKey mapped = new CFBamJpaRelationByUNameIdxKey();
			mapped.setRequiredTableId( key.getRequiredTableId() );
			mapped.setRequiredName( key.getRequiredName() );
			return( mapped );
		}
	}

    @Override
    public ICFBamRelationByRelTableIdxKey newByRelTableIdxKey() {
	ICFBamRelationByRelTableIdxKey key =
            new CFBamJpaRelationByRelTableIdxKey();
	return( key );
    }

	public CFBamJpaRelationByRelTableIdxKey ensureByRelTableIdxKey(ICFBamRelationByRelTableIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaRelationByRelTableIdxKey) {
			return( (CFBamJpaRelationByRelTableIdxKey)key );
		}
		else {
			CFBamJpaRelationByRelTableIdxKey mapped = new CFBamJpaRelationByRelTableIdxKey();
			mapped.setRequiredTableId( key.getRequiredTableId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamRelationByDefSchemaIdxKey newByDefSchemaIdxKey() {
	ICFBamRelationByDefSchemaIdxKey key =
            new CFBamJpaRelationByDefSchemaIdxKey();
	return( key );
    }

	public CFBamJpaRelationByDefSchemaIdxKey ensureByDefSchemaIdxKey(ICFBamRelationByDefSchemaIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaRelationByDefSchemaIdxKey) {
			return( (CFBamJpaRelationByDefSchemaIdxKey)key );
		}
		else {
			CFBamJpaRelationByDefSchemaIdxKey mapped = new CFBamJpaRelationByDefSchemaIdxKey();
			mapped.setOptionalDefSchemaId( key.getOptionalDefSchemaId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamRelationByFromKeyIdxKey newByFromKeyIdxKey() {
	ICFBamRelationByFromKeyIdxKey key =
            new CFBamJpaRelationByFromKeyIdxKey();
	return( key );
    }

	public CFBamJpaRelationByFromKeyIdxKey ensureByFromKeyIdxKey(ICFBamRelationByFromKeyIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaRelationByFromKeyIdxKey) {
			return( (CFBamJpaRelationByFromKeyIdxKey)key );
		}
		else {
			CFBamJpaRelationByFromKeyIdxKey mapped = new CFBamJpaRelationByFromKeyIdxKey();
			mapped.setRequiredFromIndexId( key.getRequiredFromIndexId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamRelationByToTblIdxKey newByToTblIdxKey() {
	ICFBamRelationByToTblIdxKey key =
            new CFBamJpaRelationByToTblIdxKey();
	return( key );
    }

	public CFBamJpaRelationByToTblIdxKey ensureByToTblIdxKey(ICFBamRelationByToTblIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaRelationByToTblIdxKey) {
			return( (CFBamJpaRelationByToTblIdxKey)key );
		}
		else {
			CFBamJpaRelationByToTblIdxKey mapped = new CFBamJpaRelationByToTblIdxKey();
			mapped.setRequiredToTableId( key.getRequiredToTableId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamRelationByToKeyIdxKey newByToKeyIdxKey() {
	ICFBamRelationByToKeyIdxKey key =
            new CFBamJpaRelationByToKeyIdxKey();
	return( key );
    }

	public CFBamJpaRelationByToKeyIdxKey ensureByToKeyIdxKey(ICFBamRelationByToKeyIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaRelationByToKeyIdxKey) {
			return( (CFBamJpaRelationByToKeyIdxKey)key );
		}
		else {
			CFBamJpaRelationByToKeyIdxKey mapped = new CFBamJpaRelationByToKeyIdxKey();
			mapped.setRequiredToIndexId( key.getRequiredToIndexId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamRelationByNarrowedIdxKey newByNarrowedIdxKey() {
	ICFBamRelationByNarrowedIdxKey key =
            new CFBamJpaRelationByNarrowedIdxKey();
	return( key );
    }

	public CFBamJpaRelationByNarrowedIdxKey ensureByNarrowedIdxKey(ICFBamRelationByNarrowedIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaRelationByNarrowedIdxKey) {
			return( (CFBamJpaRelationByNarrowedIdxKey)key );
		}
		else {
			CFBamJpaRelationByNarrowedIdxKey mapped = new CFBamJpaRelationByNarrowedIdxKey();
			mapped.setOptionalNarrowedId( key.getOptionalNarrowedId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamRelation newRec() {
        ICFBamRelation rec =
            new CFBamJpaRelation();
        return( rec );
    }

	public CFBamJpaRelation ensureRec(ICFBamRelation rec) {
		if( rec == null ) {
			return( null );
		}
		else if (rec instanceof CFBamJpaRelation) {
			return( (CFBamJpaRelation)rec );
		}
		else {
			CFBamJpaRelation mapped = new CFBamJpaRelation();
			mapped.set(rec);
			return( mapped );
		}
	}

    @Override
    public ICFBamRelationH newHRec() {
        ICFBamRelationH hrec =
            new CFBamJpaRelationH();
        return( hrec );
    }

	public CFBamJpaRelationH ensureHRec(ICFBamRelationH hrec) {
		if (hrec == null) {
			return( null );
		}
		else if( hrec instanceof CFBamJpaRelationH) {
			return( (CFBamJpaRelationH)hrec );
		}
		else {
			CFBamJpaRelationH mapped = new CFBamJpaRelationH();
			mapped.set(hrec);
			return( mapped );
		}
	}
}
