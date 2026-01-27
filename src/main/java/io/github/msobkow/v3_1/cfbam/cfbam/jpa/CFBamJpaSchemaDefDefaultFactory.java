
// Description: Java 25 JPA Default Factory implementation for SchemaDef.

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
 *	CFBamSchemaDefFactory JPA implementation for SchemaDef
 */
public class CFBamJpaSchemaDefDefaultFactory
    implements ICFBamSchemaDefFactory
{
    public CFBamJpaSchemaDefDefaultFactory() {
    }

    @Override
    public ICFBamSchemaDefByCTenantIdxKey newByCTenantIdxKey() {
	ICFBamSchemaDefByCTenantIdxKey key =
            new CFBamJpaSchemaDefByCTenantIdxKey();
	return( key );
    }

	public CFBamJpaSchemaDefByCTenantIdxKey ensureByCTenantIdxKey(ICFBamSchemaDefByCTenantIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaSchemaDefByCTenantIdxKey) {
			return( (CFBamJpaSchemaDefByCTenantIdxKey)key );
		}
		else {
			CFBamJpaSchemaDefByCTenantIdxKey mapped = new CFBamJpaSchemaDefByCTenantIdxKey();
			mapped.setRequiredCTenantId( key.getRequiredCTenantId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamSchemaDefByMinorVersionIdxKey newByMinorVersionIdxKey() {
	ICFBamSchemaDefByMinorVersionIdxKey key =
            new CFBamJpaSchemaDefByMinorVersionIdxKey();
	return( key );
    }

	public CFBamJpaSchemaDefByMinorVersionIdxKey ensureByMinorVersionIdxKey(ICFBamSchemaDefByMinorVersionIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaSchemaDefByMinorVersionIdxKey) {
			return( (CFBamJpaSchemaDefByMinorVersionIdxKey)key );
		}
		else {
			CFBamJpaSchemaDefByMinorVersionIdxKey mapped = new CFBamJpaSchemaDefByMinorVersionIdxKey();
			mapped.setRequiredMinorVersionId( key.getRequiredMinorVersionId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamSchemaDefByUNameIdxKey newByUNameIdxKey() {
	ICFBamSchemaDefByUNameIdxKey key =
            new CFBamJpaSchemaDefByUNameIdxKey();
	return( key );
    }

	public CFBamJpaSchemaDefByUNameIdxKey ensureByUNameIdxKey(ICFBamSchemaDefByUNameIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaSchemaDefByUNameIdxKey) {
			return( (CFBamJpaSchemaDefByUNameIdxKey)key );
		}
		else {
			CFBamJpaSchemaDefByUNameIdxKey mapped = new CFBamJpaSchemaDefByUNameIdxKey();
			mapped.setRequiredMinorVersionId( key.getRequiredMinorVersionId() );
			mapped.setRequiredName( key.getRequiredName() );
			return( mapped );
		}
	}

    @Override
    public ICFBamSchemaDefByAuthEMailIdxKey newByAuthEMailIdxKey() {
	ICFBamSchemaDefByAuthEMailIdxKey key =
            new CFBamJpaSchemaDefByAuthEMailIdxKey();
	return( key );
    }

	public CFBamJpaSchemaDefByAuthEMailIdxKey ensureByAuthEMailIdxKey(ICFBamSchemaDefByAuthEMailIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaSchemaDefByAuthEMailIdxKey) {
			return( (CFBamJpaSchemaDefByAuthEMailIdxKey)key );
		}
		else {
			CFBamJpaSchemaDefByAuthEMailIdxKey mapped = new CFBamJpaSchemaDefByAuthEMailIdxKey();
			mapped.setRequiredCTenantId( key.getRequiredCTenantId() );
			mapped.setRequiredAuthorEMail( key.getRequiredAuthorEMail() );
			return( mapped );
		}
	}

    @Override
    public ICFBamSchemaDefByProjectURLIdxKey newByProjectURLIdxKey() {
	ICFBamSchemaDefByProjectURLIdxKey key =
            new CFBamJpaSchemaDefByProjectURLIdxKey();
	return( key );
    }

	public CFBamJpaSchemaDefByProjectURLIdxKey ensureByProjectURLIdxKey(ICFBamSchemaDefByProjectURLIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaSchemaDefByProjectURLIdxKey) {
			return( (CFBamJpaSchemaDefByProjectURLIdxKey)key );
		}
		else {
			CFBamJpaSchemaDefByProjectURLIdxKey mapped = new CFBamJpaSchemaDefByProjectURLIdxKey();
			mapped.setRequiredCTenantId( key.getRequiredCTenantId() );
			mapped.setRequiredProjectURL( key.getRequiredProjectURL() );
			return( mapped );
		}
	}

    @Override
    public ICFBamSchemaDefByPubURIIdxKey newByPubURIIdxKey() {
	ICFBamSchemaDefByPubURIIdxKey key =
            new CFBamJpaSchemaDefByPubURIIdxKey();
	return( key );
    }

	public CFBamJpaSchemaDefByPubURIIdxKey ensureByPubURIIdxKey(ICFBamSchemaDefByPubURIIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaSchemaDefByPubURIIdxKey) {
			return( (CFBamJpaSchemaDefByPubURIIdxKey)key );
		}
		else {
			CFBamJpaSchemaDefByPubURIIdxKey mapped = new CFBamJpaSchemaDefByPubURIIdxKey();
			mapped.setRequiredCTenantId( key.getRequiredCTenantId() );
			mapped.setRequiredPublishURI( key.getRequiredPublishURI() );
			return( mapped );
		}
	}

    @Override
    public ICFBamSchemaDef newRec() {
        ICFBamSchemaDef rec =
            new CFBamJpaSchemaDef();
        return( rec );
    }

	public CFBamJpaSchemaDef ensureRec(ICFBamSchemaDef rec) {
		if( rec == null ) {
			return( null );
		}
		else if (rec instanceof CFBamJpaSchemaDef) {
			return( (CFBamJpaSchemaDef)rec );
		}
		else {
			CFBamJpaSchemaDef mapped = new CFBamJpaSchemaDef();
			mapped.set(rec);
			return( mapped );
		}
	}

    @Override
    public ICFBamSchemaDefH newHRec() {
        ICFBamSchemaDefH hrec =
            new CFBamJpaSchemaDefH();
        return( hrec );
    }

	public CFBamJpaSchemaDefH ensureHRec(ICFBamSchemaDefH hrec) {
		if (hrec == null) {
			return( null );
		}
		else if( hrec instanceof CFBamJpaSchemaDefH) {
			return( (CFBamJpaSchemaDefH)hrec );
		}
		else {
			CFBamJpaSchemaDefH mapped = new CFBamJpaSchemaDefH();
			mapped.set(hrec);
			return( mapped );
		}
	}
}
