
// Description: Java 25 JPA Default Factory implementation for Table.

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
 *	CFBamTableFactory JPA implementation for Table
 */
public class CFBamJpaTableDefaultFactory
    implements ICFBamTableFactory
{
    public CFBamJpaTableDefaultFactory() {
    }

    @Override
    public ICFBamTableBySchemaDefIdxKey newBySchemaDefIdxKey() {
	ICFBamTableBySchemaDefIdxKey key =
            new CFBamJpaTableBySchemaDefIdxKey();
	return( key );
    }

	public CFBamJpaTableBySchemaDefIdxKey ensureBySchemaDefIdxKey(ICFBamTableBySchemaDefIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaTableBySchemaDefIdxKey) {
			return( (CFBamJpaTableBySchemaDefIdxKey)key );
		}
		else {
			CFBamJpaTableBySchemaDefIdxKey mapped = new CFBamJpaTableBySchemaDefIdxKey();
			mapped.setRequiredSchemaDefId( key.getRequiredSchemaDefId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamTableByDefSchemaIdxKey newByDefSchemaIdxKey() {
	ICFBamTableByDefSchemaIdxKey key =
            new CFBamJpaTableByDefSchemaIdxKey();
	return( key );
    }

	public CFBamJpaTableByDefSchemaIdxKey ensureByDefSchemaIdxKey(ICFBamTableByDefSchemaIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaTableByDefSchemaIdxKey) {
			return( (CFBamJpaTableByDefSchemaIdxKey)key );
		}
		else {
			CFBamJpaTableByDefSchemaIdxKey mapped = new CFBamJpaTableByDefSchemaIdxKey();
			mapped.setOptionalDefSchemaId( key.getOptionalDefSchemaId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamTableByUNameIdxKey newByUNameIdxKey() {
	ICFBamTableByUNameIdxKey key =
            new CFBamJpaTableByUNameIdxKey();
	return( key );
    }

	public CFBamJpaTableByUNameIdxKey ensureByUNameIdxKey(ICFBamTableByUNameIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaTableByUNameIdxKey) {
			return( (CFBamJpaTableByUNameIdxKey)key );
		}
		else {
			CFBamJpaTableByUNameIdxKey mapped = new CFBamJpaTableByUNameIdxKey();
			mapped.setRequiredSchemaDefId( key.getRequiredSchemaDefId() );
			mapped.setRequiredName( key.getRequiredName() );
			return( mapped );
		}
	}

    @Override
    public ICFBamTableBySchemaCdIdxKey newBySchemaCdIdxKey() {
	ICFBamTableBySchemaCdIdxKey key =
            new CFBamJpaTableBySchemaCdIdxKey();
	return( key );
    }

	public CFBamJpaTableBySchemaCdIdxKey ensureBySchemaCdIdxKey(ICFBamTableBySchemaCdIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaTableBySchemaCdIdxKey) {
			return( (CFBamJpaTableBySchemaCdIdxKey)key );
		}
		else {
			CFBamJpaTableBySchemaCdIdxKey mapped = new CFBamJpaTableBySchemaCdIdxKey();
			mapped.setRequiredSchemaDefId( key.getRequiredSchemaDefId() );
			mapped.setRequiredTableClassCode( key.getRequiredTableClassCode() );
			return( mapped );
		}
	}

    @Override
    public ICFBamTableByPrimaryIndexIdxKey newByPrimaryIndexIdxKey() {
	ICFBamTableByPrimaryIndexIdxKey key =
            new CFBamJpaTableByPrimaryIndexIdxKey();
	return( key );
    }

	public CFBamJpaTableByPrimaryIndexIdxKey ensureByPrimaryIndexIdxKey(ICFBamTableByPrimaryIndexIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaTableByPrimaryIndexIdxKey) {
			return( (CFBamJpaTableByPrimaryIndexIdxKey)key );
		}
		else {
			CFBamJpaTableByPrimaryIndexIdxKey mapped = new CFBamJpaTableByPrimaryIndexIdxKey();
			mapped.setOptionalPrimaryIndexId( key.getOptionalPrimaryIndexId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamTableByLookupIndexIdxKey newByLookupIndexIdxKey() {
	ICFBamTableByLookupIndexIdxKey key =
            new CFBamJpaTableByLookupIndexIdxKey();
	return( key );
    }

	public CFBamJpaTableByLookupIndexIdxKey ensureByLookupIndexIdxKey(ICFBamTableByLookupIndexIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaTableByLookupIndexIdxKey) {
			return( (CFBamJpaTableByLookupIndexIdxKey)key );
		}
		else {
			CFBamJpaTableByLookupIndexIdxKey mapped = new CFBamJpaTableByLookupIndexIdxKey();
			mapped.setOptionalLookupIndexId( key.getOptionalLookupIndexId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamTableByAltIndexIdxKey newByAltIndexIdxKey() {
	ICFBamTableByAltIndexIdxKey key =
            new CFBamJpaTableByAltIndexIdxKey();
	return( key );
    }

	public CFBamJpaTableByAltIndexIdxKey ensureByAltIndexIdxKey(ICFBamTableByAltIndexIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaTableByAltIndexIdxKey) {
			return( (CFBamJpaTableByAltIndexIdxKey)key );
		}
		else {
			CFBamJpaTableByAltIndexIdxKey mapped = new CFBamJpaTableByAltIndexIdxKey();
			mapped.setOptionalAltIndexId( key.getOptionalAltIndexId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamTableByQualTableIdxKey newByQualTableIdxKey() {
	ICFBamTableByQualTableIdxKey key =
            new CFBamJpaTableByQualTableIdxKey();
	return( key );
    }

	public CFBamJpaTableByQualTableIdxKey ensureByQualTableIdxKey(ICFBamTableByQualTableIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaTableByQualTableIdxKey) {
			return( (CFBamJpaTableByQualTableIdxKey)key );
		}
		else {
			CFBamJpaTableByQualTableIdxKey mapped = new CFBamJpaTableByQualTableIdxKey();
			mapped.setOptionalQualifyingTableId( key.getOptionalQualifyingTableId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamTable newRec() {
        ICFBamTable rec =
            new CFBamJpaTable();
        return( rec );
    }

	public CFBamJpaTable ensureRec(ICFBamTable rec) {
		if( rec == null ) {
			return( null );
		}
		else if (rec instanceof CFBamJpaTable) {
			return( (CFBamJpaTable)rec );
		}
		else {
			CFBamJpaTable mapped = new CFBamJpaTable();
			mapped.set(rec);
			return( mapped );
		}
	}

    @Override
    public ICFBamTableH newHRec() {
        ICFBamTableH hrec =
            new CFBamJpaTableH();
        return( hrec );
    }

	public CFBamJpaTableH ensureHRec(ICFBamTableH hrec) {
		if (hrec == null) {
			return( null );
		}
		else if( hrec instanceof CFBamJpaTableH) {
			return( (CFBamJpaTableH)hrec );
		}
		else {
			CFBamJpaTableH mapped = new CFBamJpaTableH();
			mapped.set(hrec);
			return( mapped );
		}
	}
}
