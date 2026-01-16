// Description: Java 25 JPA implementation of EnumDef history objects

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

import java.io.Serializable;
import java.math.*;
import java.time.*;
import java.util.*;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import io.github.msobkow.v3_1.cflib.*;
import io.github.msobkow.v3_1.cflib.dbutil.*;
import io.github.msobkow.v3_1.cflib.xml.CFLibXmlUtil;
import io.github.msobkow.v3_1.cfsec.cfsec.*;
import io.github.msobkow.v3_1.cfint.cfint.*;
import io.github.msobkow.v3_1.cfbam.cfbam.*;
import io.github.msobkow.v3_1.cfsec.cfsec.jpa.*;
import io.github.msobkow.v3_1.cfint.cfint.jpa.*;

/**
 *  CFBamJpaEnumDefH provides history objects matching the CFBamEnumDef change history.
 */
@Entity
@Table(
    name = "enumdef_h", schema = "CFBam31",
    indexes = {
        @Index(name = "EnumDefIdIdx_h", columnList = "auditClusterId, auditStamp, auditAction, requiredRevision, auditSessionId, Id", unique = true)
    }
)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("43119")
@Transactional(Transactional.TxType.SUPPORTS)
@PersistenceContext(unitName = "CFBamPU")
public class CFBamJpaEnumDefH extends CFBamJpaInt16DefH
    implements ICFBamEnumDefH
{

    public CFBamJpaEnumDefH() {
            super();
    }

    @Override
    public int getClassCode() {
            return( ICFBamEnumDef.CLASS_CODE );
    }

    @Override
    public boolean equals( Object obj ) {
        if (obj == null) {
            return( false );
        }
        else if (obj instanceof ICFBamEnumDef) {
            ICFBamEnumDef rhs = (ICFBamEnumDef)obj;
        if (!super.equals(obj)) {
            return( false );
        }
            return( true );
        }
        else if (obj instanceof ICFBamEnumDefH) {
			if (!super.equals(obj)) {
				return( false );
			}
            ICFBamEnumDefH rhs = (ICFBamEnumDefH)obj;
        if (!super.equals(obj)) {
            return( false );
        }
            return( true );
        }
        else if (obj instanceof ICFBamValueHPKey) {
			return( super.equals(obj) );
        }
        else {
			return( super.equals(obj) );
        }
    }
    
    @Override
    public int hashCode() {
        int hashCode = super.hashCode();
        return( hashCode & 0x7fffffff );
    }

    @Override
    public int compareTo( Object obj ) {
        int cmp;
        if (obj == null) {
            return( 1 );
        }
        else if (obj instanceof ICFBamEnumDef) {
        cmp = super.compareTo(obj);
        if (cmp != 0) {
            return( cmp );
        }
        ICFBamEnumDef rhs = (ICFBamEnumDef)obj;
            return( 0 );
        }
        else if (obj instanceof ICFBamValueHPKey) {
        return( super.compareTo(obj) );
        }
        else if (obj instanceof ICFBamEnumDefH) {
        cmp = super.compareTo(obj);
        if (cmp != 0) {
            return( cmp );
        }
        ICFBamEnumDefH rhs = (ICFBamEnumDefH)obj;
            return( 0 );
        }
        else {
        return( super.compareTo(obj) );
        }
    }
	@Override
    public void set( ICFBamValue src ) {
		if( src instanceof ICFBamEnumDef ) {
			setEnumDef( (ICFBamEnumDef)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamJpaEnumDef" );
		}
    }

	@Override
    public void setEnumDef( ICFBamEnumDef src ) {
        super.setInt16Def( src );
    }

	@Override
    public void set( ICFBamValueH src ) {
		if( src instanceof ICFBamEnumDefH ) {
			setEnumDef( (ICFBamEnumDefH)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamEnumDefH" );
		}
    }

	@Override
    public void setEnumDef( ICFBamEnumDefH src ) {
        super.setInt16Def( src );
    }

    public String getXmlAttrFragment() {
        String ret = super.getXmlAttrFragment() ;
        return( ret );
    }

    public String toString() {
        String ret = "<CFBamJpaEnumDefH" + getXmlAttrFragment() + "/>";
        return( ret );
    }
}
