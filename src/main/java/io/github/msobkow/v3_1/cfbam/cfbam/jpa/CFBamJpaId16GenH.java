// Description: Java 25 JPA implementation of Id16Gen history objects

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
 *  CFBamJpaId16GenH provides history objects matching the CFBamId16Gen change history.
 */
@Entity
@Table(
    name = "idg16_h", schema = "CFBam31",
    indexes = {
        @Index(name = "Id16GenIdIdx_h", columnList = "auditClusterId, auditStamp, auditAction, requiredRevision, auditSessionId, Id", unique = true)
    }
)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("43122")
@Transactional(Transactional.TxType.SUPPORTS)
@PersistenceContext(unitName = "CFBamPU")
public class CFBamJpaId16GenH extends CFBamJpaInt16TypeH
    implements ICFBamId16GenH
{
	@Column( name="Slice", nullable=false )
	protected short requiredSlice;
	@Column( name="BlockSize", nullable=false )
	protected short requiredBlockSize;

    public CFBamJpaId16GenH() {
            super();
		requiredSlice = ICFBamId16Gen.SLICE_INIT_VALUE;
		requiredBlockSize = ICFBamId16Gen.BLOCKSIZE_INIT_VALUE;
    }

    @Override
    public int getClassCode() {
            return( ICFBamId16Gen.CLASS_CODE );
    }

	@Override
	public short getRequiredSlice() {
		return( requiredSlice );
	}

	@Override
	public void setRequiredSlice( short value ) {
		if( value < ICFBamId16Gen.SLICE_MIN_VALUE ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				"setRequiredSlice",
				1,
				"value",
				value,
				ICFBamId16Gen.SLICE_MIN_VALUE );
		}
		if( value > ICFBamId16Gen.SLICE_MAX_VALUE ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setRequiredSlice",
				1,
				"value",
				value,
				ICFBamId16Gen.SLICE_MAX_VALUE );
		}
		requiredSlice = value;
	}

	@Override
	public short getRequiredBlockSize() {
		return( requiredBlockSize );
	}

	@Override
	public void setRequiredBlockSize( short value ) {
		if( value < ICFBamId16Gen.BLOCKSIZE_MIN_VALUE ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				"setRequiredBlockSize",
				1,
				"value",
				value,
				ICFBamId16Gen.BLOCKSIZE_MIN_VALUE );
		}
		if( value > ICFBamId16Gen.BLOCKSIZE_MAX_VALUE ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setRequiredBlockSize",
				1,
				"value",
				value,
				ICFBamId16Gen.BLOCKSIZE_MAX_VALUE );
		}
		requiredBlockSize = value;
	}

    @Override
    public boolean equals( Object obj ) {
        if (obj == null) {
            return( false );
        }
        else if (obj instanceof ICFBamId16Gen) {
            ICFBamId16Gen rhs = (ICFBamId16Gen)obj;
        if (!super.equals(obj)) {
            return( false );
        }
			if( getRequiredSlice() != rhs.getRequiredSlice() ) {
				return( false );
			}
			if( getRequiredBlockSize() != rhs.getRequiredBlockSize() ) {
				return( false );
			}
            return( true );
        }
        else if (obj instanceof ICFBamId16GenH) {
			if (!super.equals(obj)) {
				return( false );
			}
            ICFBamId16GenH rhs = (ICFBamId16GenH)obj;
        if (!super.equals(obj)) {
            return( false );
        }
			if( getRequiredSlice() != rhs.getRequiredSlice() ) {
				return( false );
			}
			if( getRequiredBlockSize() != rhs.getRequiredBlockSize() ) {
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
		hashCode = ( hashCode * 0x10000 ) + getRequiredSlice();
		hashCode = ( hashCode * 0x10000 ) + getRequiredBlockSize();
        return( hashCode & 0x7fffffff );
    }

    @Override
    public int compareTo( Object obj ) {
        int cmp;
        if (obj == null) {
            return( 1 );
        }
        else if (obj instanceof ICFBamId16Gen) {
        cmp = super.compareTo(obj);
        if (cmp != 0) {
            return( cmp );
        }
        ICFBamId16Gen rhs = (ICFBamId16Gen)obj;
			if( getRequiredSlice() < rhs.getRequiredSlice() ) {
				return( -1 );
			}
			else if( getRequiredSlice() > rhs.getRequiredSlice() ) {
				return( 1 );
			}
			if( getRequiredBlockSize() < rhs.getRequiredBlockSize() ) {
				return( -1 );
			}
			else if( getRequiredBlockSize() > rhs.getRequiredBlockSize() ) {
				return( 1 );
			}
            return( 0 );
        }
        else if (obj instanceof ICFBamValueHPKey) {
        return( super.compareTo(obj) );
        }
        else if (obj instanceof ICFBamId16GenH) {
        cmp = super.compareTo(obj);
        if (cmp != 0) {
            return( cmp );
        }
        ICFBamId16GenH rhs = (ICFBamId16GenH)obj;
			if( getRequiredSlice() < rhs.getRequiredSlice() ) {
				return( -1 );
			}
			else if( getRequiredSlice() > rhs.getRequiredSlice() ) {
				return( 1 );
			}
			if( getRequiredBlockSize() < rhs.getRequiredBlockSize() ) {
				return( -1 );
			}
			else if( getRequiredBlockSize() > rhs.getRequiredBlockSize() ) {
				return( 1 );
			}
            return( 0 );
        }
        else {
        return( super.compareTo(obj) );
        }
    }
	@Override
    public void set( ICFBamValue src ) {
		if( src instanceof ICFBamId16Gen ) {
			setId16Gen( (ICFBamId16Gen)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamJpaId16Gen" );
		}
    }

	@Override
    public void setId16Gen( ICFBamId16Gen src ) {
        super.setInt16Type( src );
		setRequiredSlice( src.getRequiredSlice() );
		setRequiredBlockSize( src.getRequiredBlockSize() );
    }

	@Override
    public void set( ICFBamValueH src ) {
		if( src instanceof ICFBamId16GenH ) {
			setId16Gen( (ICFBamId16GenH)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamId16GenH" );
		}
    }

	@Override
    public void setId16Gen( ICFBamId16GenH src ) {
        super.setInt16Type( src );
		setRequiredSlice( src.getRequiredSlice() );
		setRequiredBlockSize( src.getRequiredBlockSize() );
    }

    public String getXmlAttrFragment() {
        String ret = super.getXmlAttrFragment() 
			+ " RequiredSlice=" + "\"" + Short.toString( getRequiredSlice() ) + "\""
			+ " RequiredBlockSize=" + "\"" + Short.toString( getRequiredBlockSize() ) + "\"";
        return( ret );
    }

    public String toString() {
        String ret = "<CFBamJpaId16GenH" + getXmlAttrFragment() + "/>";
        return( ret );
    }
}
