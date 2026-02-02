// Description: Java 25 JPA implementation of IndexCol history objects

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
 *  CFBamJpaIndexColH provides history objects matching the CFBamIndexCol change history.
 */
@Entity
@Table(
    name = "idxcol_h", schema = "CFBam31",
    indexes = {
        @Index(name = "IndexColIdIdx_h", columnList = "auditClusterId, auditStamp, auditAction, requiredRevision, auditSessionId, Id", unique = true),
        @Index(name = "IndexColUNameIdx_h", columnList = "IndexId, safe_name", unique = true),
        @Index(name = "IndexColIndexIdx_h", columnList = "IndexId", unique = false),
        @Index(name = "IndexColDefSchemaDefIdx_h", columnList = "defschid", unique = false),
        @Index(name = "IndexColColumnIdx_h", columnList = "ColumnId", unique = false),
        @Index(name = "IndexColPrevIdx_h", columnList = "PrevId", unique = false),
        @Index(name = "IndexColNextIdx_h", columnList = "NextId", unique = false),
        @Index(name = "IndexColIndexPrevIdx_h", columnList = "IndexId, PrevId", unique = false),
        @Index(name = "IndexColIndexNextIdx_h", columnList = "IndexId, NextId", unique = false)
    }
)
@Transactional(Transactional.TxType.SUPPORTS)
@PersistenceContext(unitName = "CFBamPU")
public class CFBamJpaIndexColH
    implements ICFBamIndexColH, Comparable<Object>, Serializable
{
	@AttributeOverrides({
		@AttributeOverride(name="auditClusterId", column = @Column( name="auditClusterId", nullable=false, length=CFLibDbKeyHash256.HASH_LENGTH ) ),
		@AttributeOverride(name="auditStamp", column = @Column( name="auditStamp", nullable=false ) ),
		@AttributeOverride(name="auditAction", column = @Column( name="auditAction", nullable=false ) ),
		@AttributeOverride(name="requiredRevision", column = @Column( name="requiredRevision", nullable=false ) ),
		@AttributeOverride(name="auditSessionId", column = @Column( name="auditSessionId", nullable=false, length=CFLibDbKeyHash256.HASH_LENGTH ) ),
		@AttributeOverride(name="Id", column = @Column( name="Id", nullable=false, length=CFLibDbKeyHash256.HASH_LENGTH ) )
	})
    @EmbeddedId
    protected CFBamJpaIndexColHPKey pkey;
	@AttributeOverrides({
		@AttributeOverride( name="bytes", column = @Column( name="CreatedByUserId", nullable=false, length=CFLibDbKeyHash256.HASH_LENGTH ) )
	})
	protected CFLibDbKeyHash256 createdByUserId = CFLibDbKeyHash256.fromHex(ICFBamIndexCol.S_INIT_CREATED_BY);

	@Column(name="CreatedAt", nullable=false)
	protected LocalDateTime createdAt = LocalDateTime.now();

	@AttributeOverrides({
		@AttributeOverride( name="bytes", column= @Column( name="UpdatedByUserId", nullable=false, length=CFLibDbKeyHash256.HASH_LENGTH ) )
	})
	protected CFLibDbKeyHash256 updatedByUserId = CFLibDbKeyHash256.fromHex(ICFBamIndexCol.S_INIT_UPDATED_BY);

	@Column(name="UpdatedAt", nullable=false)
	protected LocalDateTime updatedAt = LocalDateTime.now();
	@AttributeOverrides({
		@AttributeOverride(name="bytes", column = @Column( name="IndexId", nullable=false, length=CFLibDbKeyHash256.HASH_LENGTH ) )
	})
	protected CFLibDbKeyHash256 requiredIndexId;
	@AttributeOverrides({
		@AttributeOverride(name="bytes", column = @Column( name="defschid", nullable=true, length=CFLibDbKeyHash256.HASH_LENGTH ) )
	})
	protected CFLibDbKeyHash256 optionalDefSchemaId;
	@Column( name="safe_name", nullable=false, length=192 )
	protected String requiredName;
	@Column( name="short_name", nullable=true, length=16 )
	protected String optionalShortName;
	@Column( name="Label", nullable=true, length=64 )
	protected String optionalLabel;
	@Column( name="short_descr", nullable=true, length=128 )
	protected String optionalShortDescription;
	@Column( name="descr", nullable=true, length=1023 )
	protected String optionalDescription;
	@AttributeOverrides({
		@AttributeOverride(name="bytes", column = @Column( name="ColumnId", nullable=false, length=CFLibDbKeyHash256.HASH_LENGTH ) )
	})
	protected CFLibDbKeyHash256 requiredColumnId;
	@Column( name="IsAscending", nullable=false )
	protected boolean requiredIsAscending;
	@AttributeOverrides({
		@AttributeOverride(name="bytes", column = @Column( name="PrevId", nullable=true, length=CFLibDbKeyHash256.HASH_LENGTH ) )
	})
	protected CFLibDbKeyHash256 optionalPrevId;
	@AttributeOverrides({
		@AttributeOverride(name="bytes", column = @Column( name="NextId", nullable=true, length=CFLibDbKeyHash256.HASH_LENGTH ) )
	})
	protected CFLibDbKeyHash256 optionalNextId;

    public CFBamJpaIndexColH() {
            // The primary key member attributes are initialized on construction
            pkey = new CFBamJpaIndexColHPKey();
		requiredIndexId = CFLibDbKeyHash256.fromHex( ICFBamIndexCol.INDEXID_INIT_VALUE.toString() );
		optionalDefSchemaId = CFLibDbKeyHash256.nullGet();
		requiredName = ICFBamIndexCol.NAME_INIT_VALUE;
		optionalShortName = null;
		optionalLabel = null;
		optionalShortDescription = null;
		optionalDescription = null;
		requiredColumnId = CFLibDbKeyHash256.fromHex( ICFBamIndexCol.COLUMNID_INIT_VALUE.toString() );
		requiredIsAscending = ICFBamIndexCol.ISASCENDING_INIT_VALUE;
		optionalPrevId = CFLibDbKeyHash256.nullGet();
		optionalNextId = CFLibDbKeyHash256.nullGet();
    }

    @Override
    public int getClassCode() {
            return( ICFBamIndexCol.CLASS_CODE );
    }

    @Override
    public CFLibDbKeyHash256 getCreatedByUserId() {
        return( createdByUserId );
    }

    @Override
    public void setCreatedByUserId( CFLibDbKeyHash256 value ) {
        if (value == null || value.isNull()) {
            throw new CFLibNullArgumentException(getClass(), "setCreatedByUserId", 1, "value");
        }
        createdByUserId = value;
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return( createdAt );
    }

    @Override
    public void setCreatedAt( LocalDateTime value ) {
        if (value == null) {
            throw new CFLibNullArgumentException(getClass(), "setCreatedAt", 1, "value");
        }
        createdAt = value;
    }

    @Override
    public CFLibDbKeyHash256 getUpdatedByUserId() {
        return( updatedByUserId );
    }

    @Override
    public void setUpdatedByUserId( CFLibDbKeyHash256 value ) {
        if (value == null || value.isNull()) {
            throw new CFLibNullArgumentException(getClass(), "setUpdatedByUserId", 1, "value");
        }
        updatedByUserId = value;
    }

    @Override
    public LocalDateTime getUpdatedAt() {
        return( updatedAt );
    }

    @Override
    public void setUpdatedAt( LocalDateTime value ) {
        if (value == null) {
            throw new CFLibNullArgumentException(getClass(), "setUpdatedAt", 1, "value");
        }
        updatedAt = value;
    }

    @Override
    public ICFBamIndexColHPKey getPKey() {
        return( pkey );
    }

    @Override
    public void setPKey( ICFBamIndexColHPKey pkey ) {
        if (pkey != null) {
            if (pkey instanceof CFBamJpaIndexColHPKey) {
                this.pkey = (CFBamJpaIndexColHPKey)pkey;
            }
            else {
                throw new CFLibUnsupportedClassException(getClass(), "setPKey", "pkey", pkey, "CFBamJpaIndexColHPKey");
            }
        }
    }

    @Override
    public CFLibDbKeyHash256 getAuditClusterId() {
        return pkey.getAuditClusterId();
    }

    @Override
    public void setAuditClusterId(CFLibDbKeyHash256 auditClusterId) {
        pkey.setAuditClusterId(auditClusterId);
    }

    @Override
    public LocalDateTime getAuditStamp() {
        return pkey.getAuditStamp();
    }

    @Override
    public void setAuditStamp(LocalDateTime auditStamp) {
        pkey.setAuditStamp(auditStamp);
    }

    @Override
    public short getAuditActionId() {
        return pkey.getAuditActionId();
    }

    @Override
    public void setAuditActionId(short auditActionId) {
        pkey.setAuditActionId(auditActionId);
    }

    @Override
    public int getRequiredRevision() {
        return pkey.getRequiredRevision();
    }

    @Override
    public void setRequiredRevision(int revision) {
        pkey.setRequiredRevision(revision);
    }

    @Override
    public CFLibDbKeyHash256 getAuditSessionId() {
        return pkey.getAuditSessionId();
    }

    @Override
    public void setAuditSessionId(CFLibDbKeyHash256 auditSessionId) {
        pkey.setAuditSessionId(auditSessionId);
    }

    @Override
    public CFLibDbKeyHash256 getRequiredId() {
        return( pkey.getRequiredId() );
    }

    @Override
    public void setRequiredId( CFLibDbKeyHash256 requiredId ) {
        pkey.setRequiredId( requiredId );
    }

	@Override
	public CFLibDbKeyHash256 getRequiredIndexId() {
		return( requiredIndexId );
	}

	@Override
	public void setRequiredIndexId( CFLibDbKeyHash256 value ) {
		if( value == null || value.isNull() ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredIndexId",
				1,
				"value" );
		}
		requiredIndexId = value;
	}

	@Override
	public CFLibDbKeyHash256 getOptionalDefSchemaId() {
		return( optionalDefSchemaId );
	}

	@Override
	public void setOptionalDefSchemaId( CFLibDbKeyHash256 value ) {
		optionalDefSchemaId = value;
	}

	@Override
	public String getRequiredName() {
		return( requiredName );
	}

	@Override
	public void setRequiredName( String value ) {
		if( value == null ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredName",
				1,
				"value" );
		}
		else if( value.length() > 192 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setRequiredName",
				1,
				"value.length()",
				value.length(),
				192 );
		}
		requiredName = value;
	}

	@Override
	public String getOptionalShortName() {
		return( optionalShortName );
	}

	@Override
	public void setOptionalShortName( String value ) {
		if( value != null && value.length() > 16 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setOptionalShortName",
				1,
				"value.length()",
				value.length(),
				16 );
		}
		optionalShortName = value;
	}

	@Override
	public String getOptionalLabel() {
		return( optionalLabel );
	}

	@Override
	public void setOptionalLabel( String value ) {
		if( value != null && value.length() > 64 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setOptionalLabel",
				1,
				"value.length()",
				value.length(),
				64 );
		}
		optionalLabel = value;
	}

	@Override
	public String getOptionalShortDescription() {
		return( optionalShortDescription );
	}

	@Override
	public void setOptionalShortDescription( String value ) {
		if( value != null && value.length() > 128 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setOptionalShortDescription",
				1,
				"value.length()",
				value.length(),
				128 );
		}
		optionalShortDescription = value;
	}

	@Override
	public String getOptionalDescription() {
		return( optionalDescription );
	}

	@Override
	public void setOptionalDescription( String value ) {
		if( value != null && value.length() > 1023 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setOptionalDescription",
				1,
				"value.length()",
				value.length(),
				1023 );
		}
		optionalDescription = value;
	}

	@Override
	public CFLibDbKeyHash256 getRequiredColumnId() {
		return( requiredColumnId );
	}

	@Override
	public void setRequiredColumnId( CFLibDbKeyHash256 value ) {
		if( value == null || value.isNull() ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredColumnId",
				1,
				"value" );
		}
		requiredColumnId = value;
	}

	@Override
	public boolean getRequiredIsAscending() {
		return( requiredIsAscending );
	}

	@Override
	public void setRequiredIsAscending( boolean value ) {
		requiredIsAscending = value;
	}

	@Override
	public CFLibDbKeyHash256 getOptionalPrevId() {
		return( optionalPrevId );
	}

	@Override
	public void setOptionalPrevId( CFLibDbKeyHash256 value ) {
		optionalPrevId = value;
	}

	@Override
	public CFLibDbKeyHash256 getOptionalNextId() {
		return( optionalNextId );
	}

	@Override
	public void setOptionalNextId( CFLibDbKeyHash256 value ) {
		optionalNextId = value;
	}

    @Override
    public boolean equals( Object obj ) {
        if (obj == null) {
            return( false );
        }
        else if (obj instanceof ICFBamIndexCol) {
            ICFBamIndexCol rhs = (ICFBamIndexCol)obj;
		if (getPKey() != null) {
			if (rhs.getPKey() != null) {
				if (!getPKey().equals(rhs.getPKey())) {
					return( false );
				}
			}
			else {
				return( false );
			}
		}
		else if (rhs.getPKey() != null) {
			return( false );
		}

			if( getRequiredIndexId() != null && !getRequiredIndexId().isNull() ) {
				if( rhs.getRequiredIndexId() != null && !rhs.getRequiredIndexId().isNull() ) {
					if( ! getRequiredIndexId().equals( rhs.getRequiredIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredIndexId() != null && !getRequiredIndexId().isNull()) {
					return( false );
				}
			}
			if( getOptionalDefSchemaId() != null && !getOptionalDefSchemaId().isNull() ) {
				if( rhs.getOptionalDefSchemaId() != null && !rhs.getOptionalDefSchemaId().isNull() ) {
					if( ! getOptionalDefSchemaId().equals( rhs.getOptionalDefSchemaId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDefSchemaId() != null && !getOptionalDefSchemaId().isNull()) {
					return( false );
				}
			}
			if( getRequiredName() != null ) {
				if( rhs.getRequiredName() != null ) {
					if( ! getRequiredName().equals( rhs.getRequiredName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredName() != null ) {
					return( false );
				}
			}
			if( getOptionalShortName() != null ) {
				if( rhs.getOptionalShortName() != null ) {
					if( ! getOptionalShortName().equals( rhs.getOptionalShortName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalShortName() != null ) {
					return( false );
				}
			}
			if( getOptionalLabel() != null ) {
				if( rhs.getOptionalLabel() != null ) {
					if( ! getOptionalLabel().equals( rhs.getOptionalLabel() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalLabel() != null ) {
					return( false );
				}
			}
			if( getOptionalShortDescription() != null ) {
				if( rhs.getOptionalShortDescription() != null ) {
					if( ! getOptionalShortDescription().equals( rhs.getOptionalShortDescription() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalShortDescription() != null ) {
					return( false );
				}
			}
			if( getOptionalDescription() != null ) {
				if( rhs.getOptionalDescription() != null ) {
					if( ! getOptionalDescription().equals( rhs.getOptionalDescription() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDescription() != null ) {
					return( false );
				}
			}
			if( getRequiredColumnId() != null && !getRequiredColumnId().isNull() ) {
				if( rhs.getRequiredColumnId() != null && !rhs.getRequiredColumnId().isNull() ) {
					if( ! getRequiredColumnId().equals( rhs.getRequiredColumnId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredColumnId() != null && !getRequiredColumnId().isNull()) {
					return( false );
				}
			}
			if( getRequiredIsAscending() != rhs.getRequiredIsAscending() ) {
				return( false );
			}
			if( getOptionalPrevId() != null && !getOptionalPrevId().isNull() ) {
				if( rhs.getOptionalPrevId() != null && !rhs.getOptionalPrevId().isNull() ) {
					if( ! getOptionalPrevId().equals( rhs.getOptionalPrevId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalPrevId() != null && !getOptionalPrevId().isNull()) {
					return( false );
				}
			}
			if( getOptionalNextId() != null && !getOptionalNextId().isNull() ) {
				if( rhs.getOptionalNextId() != null && !rhs.getOptionalNextId().isNull() ) {
					if( ! getOptionalNextId().equals( rhs.getOptionalNextId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalNextId() != null && !getOptionalNextId().isNull()) {
					return( false );
				}
			}
            return( true );
        }
        else if (obj instanceof ICFBamIndexColH) {
            ICFBamIndexColH rhs = (ICFBamIndexColH)obj;
		if (getPKey() != null) {
			if (rhs.getPKey() != null) {
				if (!getPKey().equals(rhs.getPKey())) {
					return( false );
				}
			}
			else {
				return( false );
			}
		}
		else if (rhs.getPKey() != null) {
			return( false );
		}

			if( getRequiredIndexId() != null && !getRequiredIndexId().isNull() ) {
				if( rhs.getRequiredIndexId() != null && !rhs.getRequiredIndexId().isNull() ) {
					if( ! getRequiredIndexId().equals( rhs.getRequiredIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredIndexId() != null && !getRequiredIndexId().isNull()) {
					return( false );
				}
			}
			if( getOptionalDefSchemaId() != null && !getOptionalDefSchemaId().isNull() ) {
				if( rhs.getOptionalDefSchemaId() != null && !rhs.getOptionalDefSchemaId().isNull() ) {
					if( ! getOptionalDefSchemaId().equals( rhs.getOptionalDefSchemaId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDefSchemaId() != null && !getOptionalDefSchemaId().isNull()) {
					return( false );
				}
			}
			if( getRequiredName() != null ) {
				if( rhs.getRequiredName() != null ) {
					if( ! getRequiredName().equals( rhs.getRequiredName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredName() != null ) {
					return( false );
				}
			}
			if( getOptionalShortName() != null ) {
				if( rhs.getOptionalShortName() != null ) {
					if( ! getOptionalShortName().equals( rhs.getOptionalShortName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalShortName() != null ) {
					return( false );
				}
			}
			if( getOptionalLabel() != null ) {
				if( rhs.getOptionalLabel() != null ) {
					if( ! getOptionalLabel().equals( rhs.getOptionalLabel() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalLabel() != null ) {
					return( false );
				}
			}
			if( getOptionalShortDescription() != null ) {
				if( rhs.getOptionalShortDescription() != null ) {
					if( ! getOptionalShortDescription().equals( rhs.getOptionalShortDescription() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalShortDescription() != null ) {
					return( false );
				}
			}
			if( getOptionalDescription() != null ) {
				if( rhs.getOptionalDescription() != null ) {
					if( ! getOptionalDescription().equals( rhs.getOptionalDescription() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDescription() != null ) {
					return( false );
				}
			}
			if( getRequiredColumnId() != null && !getRequiredColumnId().isNull() ) {
				if( rhs.getRequiredColumnId() != null && !rhs.getRequiredColumnId().isNull() ) {
					if( ! getRequiredColumnId().equals( rhs.getRequiredColumnId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredColumnId() != null && !getRequiredColumnId().isNull()) {
					return( false );
				}
			}
			if( getRequiredIsAscending() != rhs.getRequiredIsAscending() ) {
				return( false );
			}
			if( getOptionalPrevId() != null && !getOptionalPrevId().isNull() ) {
				if( rhs.getOptionalPrevId() != null && !rhs.getOptionalPrevId().isNull() ) {
					if( ! getOptionalPrevId().equals( rhs.getOptionalPrevId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalPrevId() != null && !getOptionalPrevId().isNull()) {
					return( false );
				}
			}
			if( getOptionalNextId() != null && !getOptionalNextId().isNull() ) {
				if( rhs.getOptionalNextId() != null && !rhs.getOptionalNextId().isNull() ) {
					if( ! getOptionalNextId().equals( rhs.getOptionalNextId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalNextId() != null && !getOptionalNextId().isNull()) {
					return( false );
				}
			}
            return( true );
        }
        else if (obj instanceof ICFBamIndexColHPKey) {
		ICFBamIndexColHPKey rhs = (ICFBamIndexColHPKey)obj;
			if( getRequiredId() != null && !getRequiredId().isNull() ) {
				if( rhs.getRequiredId() != null && !rhs.getRequiredId().isNull() ) {
					if( ! getRequiredId().equals( rhs.getRequiredId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredId() != null && !getRequiredId().isNull()) {
					return( false );
				}
			}
		return( true );
        }
        else if (obj instanceof ICFBamIndexColByUNameIdxKey) {
            ICFBamIndexColByUNameIdxKey rhs = (ICFBamIndexColByUNameIdxKey)obj;
			if( getRequiredIndexId() != null && !getRequiredIndexId().isNull() ) {
				if( rhs.getRequiredIndexId() != null && !rhs.getRequiredIndexId().isNull() ) {
					if( ! getRequiredIndexId().equals( rhs.getRequiredIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredIndexId() != null && !getRequiredIndexId().isNull()) {
					return( false );
				}
			}
			if( getRequiredName() != null ) {
				if( rhs.getRequiredName() != null ) {
					if( ! getRequiredName().equals( rhs.getRequiredName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredName() != null ) {
					return( false );
				}
			}
            return( true );
        }
        else if (obj instanceof ICFBamIndexColByIndexIdxKey) {
            ICFBamIndexColByIndexIdxKey rhs = (ICFBamIndexColByIndexIdxKey)obj;
			if( getRequiredIndexId() != null && !getRequiredIndexId().isNull() ) {
				if( rhs.getRequiredIndexId() != null && !rhs.getRequiredIndexId().isNull() ) {
					if( ! getRequiredIndexId().equals( rhs.getRequiredIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredIndexId() != null && !getRequiredIndexId().isNull()) {
					return( false );
				}
			}
            return( true );
        }
        else if (obj instanceof ICFBamIndexColByDefSchemaIdxKey) {
            ICFBamIndexColByDefSchemaIdxKey rhs = (ICFBamIndexColByDefSchemaIdxKey)obj;
			if( getOptionalDefSchemaId() != null && !getOptionalDefSchemaId().isNull() ) {
				if( rhs.getOptionalDefSchemaId() != null && !rhs.getOptionalDefSchemaId().isNull() ) {
					if( ! getOptionalDefSchemaId().equals( rhs.getOptionalDefSchemaId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDefSchemaId() != null && !getOptionalDefSchemaId().isNull()) {
					return( false );
				}
			}
            return( true );
        }
        else if (obj instanceof ICFBamIndexColByColIdxKey) {
            ICFBamIndexColByColIdxKey rhs = (ICFBamIndexColByColIdxKey)obj;
			if( getRequiredColumnId() != null && !getRequiredColumnId().isNull() ) {
				if( rhs.getRequiredColumnId() != null && !rhs.getRequiredColumnId().isNull() ) {
					if( ! getRequiredColumnId().equals( rhs.getRequiredColumnId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredColumnId() != null && !getRequiredColumnId().isNull()) {
					return( false );
				}
			}
            return( true );
        }
        else if (obj instanceof ICFBamIndexColByPrevIdxKey) {
            ICFBamIndexColByPrevIdxKey rhs = (ICFBamIndexColByPrevIdxKey)obj;
			if( getOptionalPrevId() != null && !getOptionalPrevId().isNull() ) {
				if( rhs.getOptionalPrevId() != null && !rhs.getOptionalPrevId().isNull() ) {
					if( ! getOptionalPrevId().equals( rhs.getOptionalPrevId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalPrevId() != null && !getOptionalPrevId().isNull()) {
					return( false );
				}
			}
            return( true );
        }
        else if (obj instanceof ICFBamIndexColByNextIdxKey) {
            ICFBamIndexColByNextIdxKey rhs = (ICFBamIndexColByNextIdxKey)obj;
			if( getOptionalNextId() != null && !getOptionalNextId().isNull() ) {
				if( rhs.getOptionalNextId() != null && !rhs.getOptionalNextId().isNull() ) {
					if( ! getOptionalNextId().equals( rhs.getOptionalNextId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalNextId() != null && !getOptionalNextId().isNull()) {
					return( false );
				}
			}
            return( true );
        }
        else if (obj instanceof ICFBamIndexColByIdxPrevIdxKey) {
            ICFBamIndexColByIdxPrevIdxKey rhs = (ICFBamIndexColByIdxPrevIdxKey)obj;
			if( getRequiredIndexId() != null && !getRequiredIndexId().isNull() ) {
				if( rhs.getRequiredIndexId() != null && !rhs.getRequiredIndexId().isNull() ) {
					if( ! getRequiredIndexId().equals( rhs.getRequiredIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredIndexId() != null && !getRequiredIndexId().isNull()) {
					return( false );
				}
			}
			if( getOptionalPrevId() != null && !getOptionalPrevId().isNull() ) {
				if( rhs.getOptionalPrevId() != null && !rhs.getOptionalPrevId().isNull() ) {
					if( ! getOptionalPrevId().equals( rhs.getOptionalPrevId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalPrevId() != null && !getOptionalPrevId().isNull()) {
					return( false );
				}
			}
            return( true );
        }
        else if (obj instanceof ICFBamIndexColByIdxNextIdxKey) {
            ICFBamIndexColByIdxNextIdxKey rhs = (ICFBamIndexColByIdxNextIdxKey)obj;
			if( getRequiredIndexId() != null && !getRequiredIndexId().isNull() ) {
				if( rhs.getRequiredIndexId() != null && !rhs.getRequiredIndexId().isNull() ) {
					if( ! getRequiredIndexId().equals( rhs.getRequiredIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredIndexId() != null && !getRequiredIndexId().isNull()) {
					return( false );
				}
			}
			if( getOptionalNextId() != null && !getOptionalNextId().isNull() ) {
				if( rhs.getOptionalNextId() != null && !rhs.getOptionalNextId().isNull() ) {
					if( ! getOptionalNextId().equals( rhs.getOptionalNextId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalNextId() != null && !getOptionalNextId().isNull()) {
					return( false );
				}
			}
            return( true );
        }
        else {
			return( false );
        }
    }
    
    @Override
    public int hashCode() {
        int hashCode = pkey.hashCode();
		hashCode = hashCode + getRequiredIndexId().hashCode();
		if( getOptionalDefSchemaId() != null ) {
			hashCode = hashCode + getOptionalDefSchemaId().hashCode();
		}
		if( getRequiredName() != null ) {
			hashCode = hashCode + getRequiredName().hashCode();
		}
		if( getOptionalShortName() != null ) {
			hashCode = hashCode + getOptionalShortName().hashCode();
		}
		if( getOptionalLabel() != null ) {
			hashCode = hashCode + getOptionalLabel().hashCode();
		}
		if( getOptionalShortDescription() != null ) {
			hashCode = hashCode + getOptionalShortDescription().hashCode();
		}
		if( getOptionalDescription() != null ) {
			hashCode = hashCode + getOptionalDescription().hashCode();
		}
		hashCode = hashCode + getRequiredColumnId().hashCode();
		if( getRequiredIsAscending() ) {
			hashCode = ( hashCode * 2 ) + 1;
		}
		else {
			hashCode = hashCode * 2;
		}
		if( getOptionalPrevId() != null ) {
			hashCode = hashCode + getOptionalPrevId().hashCode();
		}
		if( getOptionalNextId() != null ) {
			hashCode = hashCode + getOptionalNextId().hashCode();
		}
        return( hashCode & 0x7fffffff );
    }

    @Override
    public int compareTo( Object obj ) {
        int cmp;
        if (obj == null) {
            return( 1 );
        }
        else if (obj instanceof ICFBamIndexCol) {
		ICFBamIndexCol rhs = (ICFBamIndexCol)obj;
		if (getPKey() != null) {
			if (rhs.getPKey() == null) {
				return( 1 );
			}
			else {
				cmp = getPKey().compareTo(rhs.getPKey());
				if (cmp != 0) {
					return( cmp );
				}
			}
		}
		else {
			if (rhs.getPKey() != null) {
				return( -1 );
			}
		}
			if (getRequiredIndexId() != null) {
				if (rhs.getRequiredIndexId() != null) {
					cmp = getRequiredIndexId().compareTo( rhs.getRequiredIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredIndexId() != null) {
				return( -1 );
			}
			if( getOptionalDefSchemaId() != null ) {
				if( rhs.getOptionalDefSchemaId() != null ) {
					cmp = getOptionalDefSchemaId().compareTo( rhs.getOptionalDefSchemaId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDefSchemaId() != null ) {
					return( -1 );
				}
			}
			if (getRequiredName() != null) {
				if (rhs.getRequiredName() != null) {
					cmp = getRequiredName().compareTo( rhs.getRequiredName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredName() != null) {
				return( -1 );
			}
			if( getOptionalShortName() != null ) {
				if( rhs.getOptionalShortName() != null ) {
					cmp = getOptionalShortName().compareTo( rhs.getOptionalShortName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalShortName() != null ) {
					return( -1 );
				}
			}
			if( getOptionalLabel() != null ) {
				if( rhs.getOptionalLabel() != null ) {
					cmp = getOptionalLabel().compareTo( rhs.getOptionalLabel() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalLabel() != null ) {
					return( -1 );
				}
			}
			if( getOptionalShortDescription() != null ) {
				if( rhs.getOptionalShortDescription() != null ) {
					cmp = getOptionalShortDescription().compareTo( rhs.getOptionalShortDescription() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalShortDescription() != null ) {
					return( -1 );
				}
			}
			if( getOptionalDescription() != null ) {
				if( rhs.getOptionalDescription() != null ) {
					cmp = getOptionalDescription().compareTo( rhs.getOptionalDescription() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDescription() != null ) {
					return( -1 );
				}
			}
			if (getRequiredColumnId() != null) {
				if (rhs.getRequiredColumnId() != null) {
					cmp = getRequiredColumnId().compareTo( rhs.getRequiredColumnId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredColumnId() != null) {
				return( -1 );
			}
			if( getRequiredIsAscending() ) {
				if( ! rhs.getRequiredIsAscending() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredIsAscending() ) {
					return( -1 );
				}
			}
			if( getOptionalPrevId() != null ) {
				if( rhs.getOptionalPrevId() != null ) {
					cmp = getOptionalPrevId().compareTo( rhs.getOptionalPrevId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalPrevId() != null ) {
					return( -1 );
				}
			}
			if( getOptionalNextId() != null ) {
				if( rhs.getOptionalNextId() != null ) {
					cmp = getOptionalNextId().compareTo( rhs.getOptionalNextId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalNextId() != null ) {
					return( -1 );
				}
			}
            return( 0 );
        }
        else if (obj instanceof ICFBamIndexColHPKey) {
        if (getPKey() != null) {
            return( getPKey().compareTo( obj ));
        }
        else {
            return( -1 );
        }
        }
        else if (obj instanceof ICFBamIndexColH) {
		ICFBamIndexColH rhs = (ICFBamIndexColH)obj;
		if (getPKey() != null) {
			if (rhs.getPKey() == null) {
				return( 1 );
			}
			else {
				cmp = getPKey().compareTo(rhs.getPKey());
				if (cmp != 0) {
					return( cmp );
				}
			}
		}
		else {
			if (rhs.getPKey() != null) {
				return( -1 );
			}
		}
			if (getRequiredIndexId() != null) {
				if (rhs.getRequiredIndexId() != null) {
					cmp = getRequiredIndexId().compareTo( rhs.getRequiredIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredIndexId() != null) {
				return( -1 );
			}
			if( getOptionalDefSchemaId() != null ) {
				if( rhs.getOptionalDefSchemaId() != null ) {
					cmp = getOptionalDefSchemaId().compareTo( rhs.getOptionalDefSchemaId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDefSchemaId() != null ) {
					return( -1 );
				}
			}
			if (getRequiredName() != null) {
				if (rhs.getRequiredName() != null) {
					cmp = getRequiredName().compareTo( rhs.getRequiredName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredName() != null) {
				return( -1 );
			}
			if( getOptionalShortName() != null ) {
				if( rhs.getOptionalShortName() != null ) {
					cmp = getOptionalShortName().compareTo( rhs.getOptionalShortName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalShortName() != null ) {
					return( -1 );
				}
			}
			if( getOptionalLabel() != null ) {
				if( rhs.getOptionalLabel() != null ) {
					cmp = getOptionalLabel().compareTo( rhs.getOptionalLabel() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalLabel() != null ) {
					return( -1 );
				}
			}
			if( getOptionalShortDescription() != null ) {
				if( rhs.getOptionalShortDescription() != null ) {
					cmp = getOptionalShortDescription().compareTo( rhs.getOptionalShortDescription() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalShortDescription() != null ) {
					return( -1 );
				}
			}
			if( getOptionalDescription() != null ) {
				if( rhs.getOptionalDescription() != null ) {
					cmp = getOptionalDescription().compareTo( rhs.getOptionalDescription() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDescription() != null ) {
					return( -1 );
				}
			}
			if (getRequiredColumnId() != null) {
				if (rhs.getRequiredColumnId() != null) {
					cmp = getRequiredColumnId().compareTo( rhs.getRequiredColumnId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredColumnId() != null) {
				return( -1 );
			}
			if( getRequiredIsAscending() ) {
				if( ! rhs.getRequiredIsAscending() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredIsAscending() ) {
					return( -1 );
				}
			}
			if( getOptionalPrevId() != null ) {
				if( rhs.getOptionalPrevId() != null ) {
					cmp = getOptionalPrevId().compareTo( rhs.getOptionalPrevId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalPrevId() != null ) {
					return( -1 );
				}
			}
			if( getOptionalNextId() != null ) {
				if( rhs.getOptionalNextId() != null ) {
					cmp = getOptionalNextId().compareTo( rhs.getOptionalNextId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalNextId() != null ) {
					return( -1 );
				}
			}
            return( 0 );
        }
        else if (obj instanceof ICFBamIndexColByUNameIdxKey ) {
            ICFBamIndexColByUNameIdxKey rhs = (ICFBamIndexColByUNameIdxKey)obj;
			if (getRequiredIndexId() != null) {
				if (rhs.getRequiredIndexId() != null) {
					cmp = getRequiredIndexId().compareTo( rhs.getRequiredIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredIndexId() != null) {
				return( -1 );
			}
			if (getRequiredName() != null) {
				if (rhs.getRequiredName() != null) {
					cmp = getRequiredName().compareTo( rhs.getRequiredName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredName() != null) {
				return( -1 );
			}
            return( 0 );
        }
        else if (obj instanceof ICFBamIndexColByIndexIdxKey ) {
            ICFBamIndexColByIndexIdxKey rhs = (ICFBamIndexColByIndexIdxKey)obj;
			if (getRequiredIndexId() != null) {
				if (rhs.getRequiredIndexId() != null) {
					cmp = getRequiredIndexId().compareTo( rhs.getRequiredIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredIndexId() != null) {
				return( -1 );
			}
            return( 0 );
        }
        else if (obj instanceof ICFBamIndexColByDefSchemaIdxKey ) {
            ICFBamIndexColByDefSchemaIdxKey rhs = (ICFBamIndexColByDefSchemaIdxKey)obj;
			if( getOptionalDefSchemaId() != null ) {
				if( rhs.getOptionalDefSchemaId() != null ) {
					cmp = getOptionalDefSchemaId().compareTo( rhs.getOptionalDefSchemaId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDefSchemaId() != null ) {
					return( -1 );
				}
			}
            return( 0 );
        }
        else if (obj instanceof ICFBamIndexColByColIdxKey ) {
            ICFBamIndexColByColIdxKey rhs = (ICFBamIndexColByColIdxKey)obj;
			if (getRequiredColumnId() != null) {
				if (rhs.getRequiredColumnId() != null) {
					cmp = getRequiredColumnId().compareTo( rhs.getRequiredColumnId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredColumnId() != null) {
				return( -1 );
			}
            return( 0 );
        }
        else if (obj instanceof ICFBamIndexColByPrevIdxKey ) {
            ICFBamIndexColByPrevIdxKey rhs = (ICFBamIndexColByPrevIdxKey)obj;
			if( getOptionalPrevId() != null ) {
				if( rhs.getOptionalPrevId() != null ) {
					cmp = getOptionalPrevId().compareTo( rhs.getOptionalPrevId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalPrevId() != null ) {
					return( -1 );
				}
			}
            return( 0 );
        }
        else if (obj instanceof ICFBamIndexColByNextIdxKey ) {
            ICFBamIndexColByNextIdxKey rhs = (ICFBamIndexColByNextIdxKey)obj;
			if( getOptionalNextId() != null ) {
				if( rhs.getOptionalNextId() != null ) {
					cmp = getOptionalNextId().compareTo( rhs.getOptionalNextId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalNextId() != null ) {
					return( -1 );
				}
			}
            return( 0 );
        }
        else if (obj instanceof ICFBamIndexColByIdxPrevIdxKey ) {
            ICFBamIndexColByIdxPrevIdxKey rhs = (ICFBamIndexColByIdxPrevIdxKey)obj;
			if (getRequiredIndexId() != null) {
				if (rhs.getRequiredIndexId() != null) {
					cmp = getRequiredIndexId().compareTo( rhs.getRequiredIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredIndexId() != null) {
				return( -1 );
			}
			if( getOptionalPrevId() != null ) {
				if( rhs.getOptionalPrevId() != null ) {
					cmp = getOptionalPrevId().compareTo( rhs.getOptionalPrevId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalPrevId() != null ) {
					return( -1 );
				}
			}
            return( 0 );
        }
        else if (obj instanceof ICFBamIndexColByIdxNextIdxKey ) {
            ICFBamIndexColByIdxNextIdxKey rhs = (ICFBamIndexColByIdxNextIdxKey)obj;
			if (getRequiredIndexId() != null) {
				if (rhs.getRequiredIndexId() != null) {
					cmp = getRequiredIndexId().compareTo( rhs.getRequiredIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredIndexId() != null) {
				return( -1 );
			}
			if( getOptionalNextId() != null ) {
				if( rhs.getOptionalNextId() != null ) {
					cmp = getOptionalNextId().compareTo( rhs.getOptionalNextId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalNextId() != null ) {
					return( -1 );
				}
			}
            return( 0 );
        }
        else {
            throw new CFLibUnsupportedClassException( getClass(),
                "compareTo",
                "obj",
                obj,
                null );
        }
    }
	@Override
    public void set( ICFBamIndexCol src ) {
		setIndexCol( src );
    }

	@Override
    public void setIndexCol( ICFBamIndexCol src ) {
		setRequiredId( src.getRequiredId() );
		setRequiredIndexId( src.getRequiredIndexId() );
		setOptionalDefSchemaId( src.getOptionalDefSchemaId() );
		setRequiredName( src.getRequiredName() );
		setOptionalShortName( src.getOptionalShortName() );
		setOptionalLabel( src.getOptionalLabel() );
		setOptionalShortDescription( src.getOptionalShortDescription() );
		setOptionalDescription( src.getOptionalDescription() );
		setRequiredColumnId( src.getRequiredColumnId() );
		setRequiredIsAscending( src.getRequiredIsAscending() );
		setOptionalPrevId( src.getOptionalPrevId() );
		setOptionalNextId( src.getOptionalNextId() );
		setRequiredRevision( src.getRequiredRevision() );
    }

	@Override
    public void set( ICFBamIndexColH src ) {
		setIndexCol( src );
    }

	@Override
    public void setIndexCol( ICFBamIndexColH src ) {
		setRequiredId( src.getRequiredId() );
		setRequiredIndexId( src.getRequiredIndexId() );
		setOptionalDefSchemaId( src.getOptionalDefSchemaId() );
		setRequiredName( src.getRequiredName() );
		setOptionalShortName( src.getOptionalShortName() );
		setOptionalLabel( src.getOptionalLabel() );
		setOptionalShortDescription( src.getOptionalShortDescription() );
		setOptionalDescription( src.getOptionalDescription() );
		setRequiredColumnId( src.getRequiredColumnId() );
		setRequiredIsAscending( src.getRequiredIsAscending() );
		setOptionalPrevId( src.getOptionalPrevId() );
		setOptionalNextId( src.getOptionalNextId() );
		setRequiredRevision( src.getRequiredRevision() );
    }

    public String getXmlAttrFragment() {
        String ret = pkey.getXmlAttrFragment() 
			+ " RequiredRevision=\"" + Integer.toString( getRequiredRevision() ) + "\""
			+ " RequiredIndexId=" + "\"" + getRequiredIndexId().toString() + "\""
			+ " OptionalDefSchemaId=" + ( ( getOptionalDefSchemaId() == null ) ? "null" : "\"" + getOptionalDefSchemaId().toString() + "\"" )
			+ " RequiredName=" + "\"" + StringEscapeUtils.escapeXml11( getRequiredName() ) + "\""
			+ " OptionalShortName=" + ( ( getOptionalShortName() == null ) ? "null" : "\"" + StringEscapeUtils.escapeXml11( getOptionalShortName() ) + "\"" )
			+ " OptionalLabel=" + ( ( getOptionalLabel() == null ) ? "null" : "\"" + StringEscapeUtils.escapeXml11( getOptionalLabel() ) + "\"" )
			+ " OptionalShortDescription=" + ( ( getOptionalShortDescription() == null ) ? "null" : "\"" + StringEscapeUtils.escapeXml11( getOptionalShortDescription() ) + "\"" )
			+ " OptionalDescription=" + ( ( getOptionalDescription() == null ) ? "null" : "\"" + StringEscapeUtils.escapeXml11( getOptionalDescription() ) + "\"" )
			+ " RequiredColumnId=" + "\"" + getRequiredColumnId().toString() + "\""
			+ " RequiredIsAscending=" + (( getRequiredIsAscending() ) ? "\"true\"" : "\"false\"" )
			+ " OptionalPrevId=" + ( ( getOptionalPrevId() == null ) ? "null" : "\"" + getOptionalPrevId().toString() + "\"" )
			+ " OptionalNextId=" + ( ( getOptionalNextId() == null ) ? "null" : "\"" + getOptionalNextId().toString() + "\"" );
        return( ret );
    }

    public String toString() {
        String ret = "<CFBamJpaIndexColH" + getXmlAttrFragment() + "/>";
        return( ret );
    }
}
