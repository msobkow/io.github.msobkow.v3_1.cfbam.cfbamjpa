
// Description: Java 25 JPA Default Factory implementation for Scope.

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
 *	CFBamScopeFactory JPA implementation for Scope
 */
public class CFBamJpaScopeDefaultFactory
    implements ICFBamScopeFactory
{
    public CFBamJpaScopeDefaultFactory() {
    }

    @Override
    public ICFBamScopeHPKey newHPKey() {
        ICFBamScopeHPKey hpkey =
            new CFBamJpaScopeHPKey();
        return( hpkey );
    }

	public CFBamJpaScopeHPKey ensureHPKey(ICFBamScopeHPKey key) {
		if (key == null) {
			return( null );
		}
		else if( key instanceof CFBamJpaScopeHPKey) {
			return( (CFBamJpaScopeHPKey)key );
		}
		else {
			CFBamJpaScopeHPKey mapped = new CFBamJpaScopeHPKey();
			mapped.setAuditClusterId(key.getAuditClusterId());
			mapped.setAuditActionId(key.getAuditActionId());
			mapped.setAuditSessionId(key.getAuditSessionId());
			mapped.setAuditStamp(key.getAuditStamp());
			mapped.setRequiredId( key.getRequiredId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamScopeByTenantIdxKey newByTenantIdxKey() {
	ICFBamScopeByTenantIdxKey key =
            new CFBamJpaScopeByTenantIdxKey();
	return( key );
    }

	public CFBamJpaScopeByTenantIdxKey ensureByTenantIdxKey(ICFBamScopeByTenantIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamJpaScopeByTenantIdxKey) {
			return( (CFBamJpaScopeByTenantIdxKey)key );
		}
		else {
			CFBamJpaScopeByTenantIdxKey mapped = new CFBamJpaScopeByTenantIdxKey();
			mapped.setRequiredTenantId( key.getRequiredTenantId() );
			return( mapped );
		}
	}

    @Override
    public ICFBamScope newRec() {
        ICFBamScope rec =
            new CFBamJpaScope();
        return( rec );
    }

	public CFBamJpaScope ensureRec(ICFBamScope rec) {
		if( rec == null ) {
			return( null );
		}
		else if (rec instanceof CFBamJpaScope) {
			return( (CFBamJpaScope)rec );
		}
		else {
			CFBamJpaScope mapped = new CFBamJpaScope();
			mapped.set(rec);
			return( mapped );
		}
	}

    @Override
    public ICFBamScopeH newHRec() {
        ICFBamScopeH hrec =
            new CFBamJpaScopeH();
        return( hrec );
    }

	public CFBamJpaScopeH ensureHRec(ICFBamScopeH hrec) {
		if (hrec == null) {
			return( null );
		}
		else if( hrec instanceof CFBamJpaScopeH) {
			return( (CFBamJpaScopeH)hrec );
		}
		else {
			CFBamJpaScopeH mapped = new CFBamJpaScopeH();
			mapped.set(hrec);
			return( mapped );
		}
	}
}
