
// Description: Java 25 JPA Default Factory implementation for Param.

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
 *	CFBamParamFactory JPA implementation for Param
 */
public class CFBamJpaParamDefaultFactory
    implements ICFBamParamFactory
{
    public CFBamJpaParamDefaultFactory() {
    }

    @Override
    public ICFBamParamHPKey newHPKey() {
        ICFBamParamHPKey hpkey =
            new CFBamJpaParamHPKey();
        return( hpkey );
    }

	public CFBamJpaParamHPKey ensureHPKey(ICFBamParamHPKey key) {
		if (key == null) {
			return( null );
		}
		else if( key instanceof CFBamJpaParamHPKey) {
			return( (CFBamJpaParamHPKey)key );
		}
		else {
			CFBamJpaParamHPKey mapped = new CFBamJpaParamHPKey();
			mapped.setAuditClusterId(key.getAuditClusterId());
			mapped.setAuditActionId(key.getAuditActionId());
			mapped.setAuditSessionId(key.getAuditSessionId());
			mapped.setAuditStamp(key.getAuditStamp());
			mapped.setRequiredId( key.getRequiredId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamParamByUNameIdxKey newByUNameIdxKey() {
	ICFBamParamByUNameIdxKey key =
            new CFBamJpaParamByUNameIdxKey();
	return( key );
    }

	public CFBamJpaParamByUNameIdxKey ensureByUNameIdxKey(ICFBamParamByUNameIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaParamByUNameIdxKey) {
			return( (CFBamJpaParamByUNameIdxKey)key );
		}
		else {
			CFBamJpaParamByUNameIdxKey mapped = new CFBamJpaParamByUNameIdxKey();
			mapped.setRequiredServerMethodId( key.getRequiredServerMethodId() );
			mapped.setRequiredName( key.getRequiredName() );
			return( mapped );
		}
	}

    @Override
    public ICFBamParamByServerMethodIdxKey newByServerMethodIdxKey() {
	ICFBamParamByServerMethodIdxKey key =
            new CFBamJpaParamByServerMethodIdxKey();
	return( key );
    }

	public CFBamJpaParamByServerMethodIdxKey ensureByServerMethodIdxKey(ICFBamParamByServerMethodIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaParamByServerMethodIdxKey) {
			return( (CFBamJpaParamByServerMethodIdxKey)key );
		}
		else {
			CFBamJpaParamByServerMethodIdxKey mapped = new CFBamJpaParamByServerMethodIdxKey();
			mapped.setRequiredServerMethodId( key.getRequiredServerMethodId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamParamByDefSchemaIdxKey newByDefSchemaIdxKey() {
	ICFBamParamByDefSchemaIdxKey key =
            new CFBamJpaParamByDefSchemaIdxKey();
	return( key );
    }

	public CFBamJpaParamByDefSchemaIdxKey ensureByDefSchemaIdxKey(ICFBamParamByDefSchemaIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaParamByDefSchemaIdxKey) {
			return( (CFBamJpaParamByDefSchemaIdxKey)key );
		}
		else {
			CFBamJpaParamByDefSchemaIdxKey mapped = new CFBamJpaParamByDefSchemaIdxKey();
			mapped.setOptionalDefSchemaId( key.getOptionalDefSchemaId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamParamByServerTypeIdxKey newByServerTypeIdxKey() {
	ICFBamParamByServerTypeIdxKey key =
            new CFBamJpaParamByServerTypeIdxKey();
	return( key );
    }

	public CFBamJpaParamByServerTypeIdxKey ensureByServerTypeIdxKey(ICFBamParamByServerTypeIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaParamByServerTypeIdxKey) {
			return( (CFBamJpaParamByServerTypeIdxKey)key );
		}
		else {
			CFBamJpaParamByServerTypeIdxKey mapped = new CFBamJpaParamByServerTypeIdxKey();
			mapped.setOptionalTypeId( key.getOptionalTypeId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamParamByPrevIdxKey newByPrevIdxKey() {
	ICFBamParamByPrevIdxKey key =
            new CFBamJpaParamByPrevIdxKey();
	return( key );
    }

	public CFBamJpaParamByPrevIdxKey ensureByPrevIdxKey(ICFBamParamByPrevIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaParamByPrevIdxKey) {
			return( (CFBamJpaParamByPrevIdxKey)key );
		}
		else {
			CFBamJpaParamByPrevIdxKey mapped = new CFBamJpaParamByPrevIdxKey();
			mapped.setOptionalPrevId( key.getOptionalPrevId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamParamByNextIdxKey newByNextIdxKey() {
	ICFBamParamByNextIdxKey key =
            new CFBamJpaParamByNextIdxKey();
	return( key );
    }

	public CFBamJpaParamByNextIdxKey ensureByNextIdxKey(ICFBamParamByNextIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaParamByNextIdxKey) {
			return( (CFBamJpaParamByNextIdxKey)key );
		}
		else {
			CFBamJpaParamByNextIdxKey mapped = new CFBamJpaParamByNextIdxKey();
			mapped.setOptionalNextId( key.getOptionalNextId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamParamByContPrevIdxKey newByContPrevIdxKey() {
	ICFBamParamByContPrevIdxKey key =
            new CFBamJpaParamByContPrevIdxKey();
	return( key );
    }

	public CFBamJpaParamByContPrevIdxKey ensureByContPrevIdxKey(ICFBamParamByContPrevIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaParamByContPrevIdxKey) {
			return( (CFBamJpaParamByContPrevIdxKey)key );
		}
		else {
			CFBamJpaParamByContPrevIdxKey mapped = new CFBamJpaParamByContPrevIdxKey();
			mapped.setRequiredServerMethodId( key.getRequiredServerMethodId() );
			mapped.setOptionalPrevId( key.getOptionalPrevId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamParamByContNextIdxKey newByContNextIdxKey() {
	ICFBamParamByContNextIdxKey key =
            new CFBamJpaParamByContNextIdxKey();
	return( key );
    }

	public CFBamJpaParamByContNextIdxKey ensureByContNextIdxKey(ICFBamParamByContNextIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaParamByContNextIdxKey) {
			return( (CFBamJpaParamByContNextIdxKey)key );
		}
		else {
			CFBamJpaParamByContNextIdxKey mapped = new CFBamJpaParamByContNextIdxKey();
			mapped.setRequiredServerMethodId( key.getRequiredServerMethodId() );
			mapped.setOptionalNextId( key.getOptionalNextId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamParam newRec() {
        ICFBamParam rec =
            new CFBamJpaParam();
        return( rec );
    }

	public CFBamJpaParam ensureRec(ICFBamParam rec) {
		if( rec == null ) {
			return( null );
		}
		else if (rec instanceof CFBamJpaParam) {
			return( (CFBamJpaParam)rec );
		}
		else {
			CFBamJpaParam mapped = new CFBamJpaParam();
			mapped.set(rec);
			return( mapped );
		}
	}

    @Override
    public ICFBamParamH newHRec() {
        ICFBamParamH hrec =
            new CFBamJpaParamH();
        return( hrec );
    }

	public CFBamJpaParamH ensureHRec(ICFBamParamH hrec) {
		if (hrec == null) {
			return( null );
		}
		else if( hrec instanceof CFBamJpaParamH) {
			return( (CFBamJpaParamH)hrec );
		}
		else {
			CFBamJpaParamH mapped = new CFBamJpaParamH();
			mapped.set(hrec);
			return( mapped );
		}
	}
}
