
// Description: Java 25 JPA Default Factory implementation for Value.

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
 *	CFBamValueFactory JPA implementation for Value
 */
public class CFBamJpaValueDefaultFactory
    implements ICFBamValueFactory
{
    public CFBamJpaValueDefaultFactory() {
    }

    @Override
    public ICFBamValueHPKey newHPKey() {
        ICFBamValueHPKey hpkey =
            new CFBamJpaValueHPKey();
        return( hpkey );
    }

    @Override
    public ICFBamValueByUNameIdxKey newByUNameIdxKey() {
	ICFBamValueByUNameIdxKey key =
            new CFBamJpaValueByUNameIdxKey();
	return( key );
    }

    @Override
    public ICFBamValueByScopeIdxKey newByScopeIdxKey() {
	ICFBamValueByScopeIdxKey key =
            new CFBamJpaValueByScopeIdxKey();
	return( key );
    }

    @Override
    public ICFBamValueByDefSchemaIdxKey newByDefSchemaIdxKey() {
	ICFBamValueByDefSchemaIdxKey key =
            new CFBamJpaValueByDefSchemaIdxKey();
	return( key );
    }

    @Override
    public ICFBamValueByPrevIdxKey newByPrevIdxKey() {
	ICFBamValueByPrevIdxKey key =
            new CFBamJpaValueByPrevIdxKey();
	return( key );
    }

    @Override
    public ICFBamValueByNextIdxKey newByNextIdxKey() {
	ICFBamValueByNextIdxKey key =
            new CFBamJpaValueByNextIdxKey();
	return( key );
    }

    @Override
    public ICFBamValueByContPrevIdxKey newByContPrevIdxKey() {
	ICFBamValueByContPrevIdxKey key =
            new CFBamJpaValueByContPrevIdxKey();
	return( key );
    }

    @Override
    public ICFBamValueByContNextIdxKey newByContNextIdxKey() {
	ICFBamValueByContNextIdxKey key =
            new CFBamJpaValueByContNextIdxKey();
	return( key );
    }

    @Override
    public ICFBamValue newRec() {
        ICFBamValue rec =
            new CFBamJpaValue();
        return( rec );
    }

    @Override
    public ICFBamValueH newHRec() {
        ICFBamValueH hrec =
            new CFBamJpaValueH();
        return( hrec );
    }
}
