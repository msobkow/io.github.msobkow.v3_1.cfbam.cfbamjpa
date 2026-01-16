// Description: Java 25 JPA implementation of Table history objects

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
 *  CFBamJpaTableH provides history objects matching the CFBamTable change history.
 */
@Entity
@Table(
    name = "tbldef_h", schema = "CFBam31",
    indexes = {
        @Index(name = "TableIdIdx_h", columnList = "auditClusterId, auditStamp, auditAction, requiredRevision, auditSessionId, Id", unique = true),
        @Index(name = "TableSchemaDefIdx_h", columnList = "SchemaDefId", unique = false),
        @Index(name = "TableDefSchemaDefIdx_h", columnList = "defschid", unique = false),
        @Index(name = "TableUNameIdx_h", columnList = "SchemaDefId, safe_name", unique = true),
        @Index(name = "TableSchemaCodeIdx_h", columnList = "SchemaDefId, TblClsCd", unique = true),
        @Index(name = "TablePrimaryIndexIdx_h", columnList = "PrimIdxId", unique = false),
        @Index(name = "TableLookupIndexIdx_h", columnList = "LookIdxId", unique = false),
        @Index(name = "TableAltIndexIdx_h", columnList = "AltIdxId", unique = false),
        @Index(name = "TableQualifyingTableIdx_h", columnList = "QualTblId", unique = false)
    }
)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("43016")
@Transactional(Transactional.TxType.SUPPORTS)
@PersistenceContext(unitName = "CFBamPU")
public class CFBamJpaTableH extends CFBamJpaScopeH
    implements ICFBamTableH
{
	@AttributeOverrides({
		@AttributeOverride(name="bytes", column = @Column( name="SchemaDefId", nullable=false, length=CFLibDbKeyHash256.HASH_LENGTH ) )
	})
	protected CFLibDbKeyHash256 requiredSchemaDefId;
	@AttributeOverrides({
		@AttributeOverride(name="bytes", column = @Column( name="defschid", nullable=true, length=CFLibDbKeyHash256.HASH_LENGTH ) )
	})
	protected CFLibDbKeyHash256 optionalDefSchemaId;
	@Column( name="safe_name", nullable=false, length=192 )
	protected String requiredName;
	@Column( name="DbName", nullable=true, length=32 )
	protected String optionalDbName;
	@Column( name="short_name", nullable=true, length=16 )
	protected String optionalShortName;
	@Column( name="Label", nullable=true, length=64 )
	protected String optionalLabel;
	@Column( name="short_descr", nullable=true, length=50 )
	protected String optionalShortDescription;
	@Column( name="descr", nullable=true, length=100 )
	protected String optionalDescription;
	@Column( name="PageData", nullable=false )
	protected boolean requiredPageData;
	@AttributeOverrides({
		@AttributeOverride(name="bytes", column = @Column( name="PrimIdxId", nullable=true, length=CFLibDbKeyHash256.HASH_LENGTH ) )
	})
	protected CFLibDbKeyHash256 optionalPrimaryIndexId;
	@Column( name="TblClsCd", nullable=false, length=4 )
	protected String requiredTableClassCode;
	@AttributeOverrides({
		@AttributeOverride(name="bytes", column = @Column( name="LookIdxId", nullable=true, length=CFLibDbKeyHash256.HASH_LENGTH ) )
	})
	protected CFLibDbKeyHash256 optionalLookupIndexId;
	@AttributeOverrides({
		@AttributeOverride(name="bytes", column = @Column( name="AltIdxId", nullable=true, length=CFLibDbKeyHash256.HASH_LENGTH ) )
	})
	protected CFLibDbKeyHash256 optionalAltIndexId;
	@AttributeOverrides({
		@AttributeOverride(name="bytes", column = @Column( name="QualTblId", nullable=true, length=CFLibDbKeyHash256.HASH_LENGTH ) )
	})
	protected CFLibDbKeyHash256 optionalQualifyingTableId;
	@Column( name="IsInstantiable", nullable=false )
	protected boolean requiredIsInstantiable;
	@Column( name="HasHistory", nullable=false )
	protected boolean requiredHasHistory;
	@Column( name="HasAuditColumns", nullable=false )
	protected boolean requiredHasAuditColumns;
	@Column( name="IsMutable", nullable=false )
	protected boolean requiredIsMutable;
	@Column( name="IsServerOnly", nullable=false )
	protected boolean requiredIsServerOnly;
	@Column( name="LoadBehavId", nullable=false )
	protected ICFBamSchema.LoaderBehaviourEnum requiredLoaderBehaviour;
	@Column( name="SecScopeId", nullable=false )
	protected ICFBamSchema.SecScopeEnum requiredSecScope;

    public CFBamJpaTableH() {
            super();
		requiredSchemaDefId = CFLibDbKeyHash256.fromHex( ICFBamTable.SCHEMADEFID_INIT_VALUE.toString() );
		optionalDefSchemaId = CFLibDbKeyHash256.nullGet();
		requiredName = ICFBamTable.NAME_INIT_VALUE;
		optionalDbName = null;
		optionalShortName = null;
		optionalLabel = null;
		optionalShortDescription = null;
		optionalDescription = null;
		requiredPageData = ICFBamTable.PAGEDATA_INIT_VALUE;
		optionalPrimaryIndexId = CFLibDbKeyHash256.nullGet();
		requiredTableClassCode = ICFBamTable.TABLECLASSCODE_INIT_VALUE;
		optionalLookupIndexId = CFLibDbKeyHash256.nullGet();
		optionalAltIndexId = CFLibDbKeyHash256.nullGet();
		optionalQualifyingTableId = CFLibDbKeyHash256.nullGet();
		requiredIsInstantiable = ICFBamTable.ISINSTANTIABLE_INIT_VALUE;
		requiredHasHistory = ICFBamTable.HASHISTORY_INIT_VALUE;
		requiredHasAuditColumns = ICFBamTable.HASAUDITCOLUMNS_INIT_VALUE;
		requiredIsMutable = ICFBamTable.ISMUTABLE_INIT_VALUE;
		requiredIsServerOnly = ICFBamTable.ISSERVERONLY_INIT_VALUE;
		requiredLoaderBehaviour = ICFBamTable.LOADERBEHAVIOUR_INIT_VALUE;
		requiredSecScope = ICFBamTable.SECSCOPE_INIT_VALUE;
    }

    @Override
    public int getClassCode() {
            return( ICFBamTable.CLASS_CODE );
    }

	@Override
	public CFLibDbKeyHash256 getRequiredSchemaDefId() {
		return( requiredSchemaDefId );
	}

	@Override
	public void setRequiredSchemaDefId( CFLibDbKeyHash256 value ) {
		if( value == null || value.isNull() ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredSchemaDefId",
				1,
				"value" );
		}
		requiredSchemaDefId = value;
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
	public String getOptionalDbName() {
		return( optionalDbName );
	}

	@Override
	public void setOptionalDbName( String value ) {
		if( value != null && value.length() > 32 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setOptionalDbName",
				1,
				"value.length()",
				value.length(),
				32 );
		}
		optionalDbName = value;
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
		if( value != null && value.length() > 50 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setOptionalShortDescription",
				1,
				"value.length()",
				value.length(),
				50 );
		}
		optionalShortDescription = value;
	}

	@Override
	public String getOptionalDescription() {
		return( optionalDescription );
	}

	@Override
	public void setOptionalDescription( String value ) {
		if( value != null && value.length() > 100 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setOptionalDescription",
				1,
				"value.length()",
				value.length(),
				100 );
		}
		optionalDescription = value;
	}

	@Override
	public boolean getRequiredPageData() {
		return( requiredPageData );
	}

	@Override
	public void setRequiredPageData( boolean value ) {
		requiredPageData = value;
	}

	@Override
	public CFLibDbKeyHash256 getOptionalPrimaryIndexId() {
		return( optionalPrimaryIndexId );
	}

	@Override
	public void setOptionalPrimaryIndexId( CFLibDbKeyHash256 value ) {
		optionalPrimaryIndexId = value;
	}

	@Override
	public String getRequiredTableClassCode() {
		return( requiredTableClassCode );
	}

	@Override
	public void setRequiredTableClassCode( String value ) {
		if( value == null ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredTableClassCode",
				1,
				"value" );
		}
		else if( value.length() > 4 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setRequiredTableClassCode",
				1,
				"value.length()",
				value.length(),
				4 );
		}
		requiredTableClassCode = value;
	}

	@Override
	public CFLibDbKeyHash256 getOptionalLookupIndexId() {
		return( optionalLookupIndexId );
	}

	@Override
	public void setOptionalLookupIndexId( CFLibDbKeyHash256 value ) {
		optionalLookupIndexId = value;
	}

	@Override
	public CFLibDbKeyHash256 getOptionalAltIndexId() {
		return( optionalAltIndexId );
	}

	@Override
	public void setOptionalAltIndexId( CFLibDbKeyHash256 value ) {
		optionalAltIndexId = value;
	}

	@Override
	public CFLibDbKeyHash256 getOptionalQualifyingTableId() {
		return( optionalQualifyingTableId );
	}

	@Override
	public void setOptionalQualifyingTableId( CFLibDbKeyHash256 value ) {
		optionalQualifyingTableId = value;
	}

	@Override
	public boolean getRequiredIsInstantiable() {
		return( requiredIsInstantiable );
	}

	@Override
	public void setRequiredIsInstantiable( boolean value ) {
		requiredIsInstantiable = value;
	}

	@Override
	public boolean getRequiredHasHistory() {
		return( requiredHasHistory );
	}

	@Override
	public void setRequiredHasHistory( boolean value ) {
		requiredHasHistory = value;
	}

	@Override
	public boolean getRequiredHasAuditColumns() {
		return( requiredHasAuditColumns );
	}

	@Override
	public void setRequiredHasAuditColumns( boolean value ) {
		requiredHasAuditColumns = value;
	}

	@Override
	public boolean getRequiredIsMutable() {
		return( requiredIsMutable );
	}

	@Override
	public void setRequiredIsMutable( boolean value ) {
		requiredIsMutable = value;
	}

	@Override
	public boolean getRequiredIsServerOnly() {
		return( requiredIsServerOnly );
	}

	@Override
	public void setRequiredIsServerOnly( boolean value ) {
		requiredIsServerOnly = value;
	}

	@Override
	public ICFBamSchema.LoaderBehaviourEnum getRequiredLoaderBehaviour() {
		return( requiredLoaderBehaviour );
	}

	@Override
	public void setRequiredLoaderBehaviour( ICFBamSchema.LoaderBehaviourEnum value ) {
		if( value == null ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredLoaderBehaviour",
				1,
				"value" );
		}
		requiredLoaderBehaviour = value;
	}

	@Override
	public ICFBamSchema.SecScopeEnum getRequiredSecScope() {
		return( requiredSecScope );
	}

	@Override
	public void setRequiredSecScope( ICFBamSchema.SecScopeEnum value ) {
		if( value == null ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredSecScope",
				1,
				"value" );
		}
		requiredSecScope = value;
	}

    @Override
    public boolean equals( Object obj ) {
        if (obj == null) {
            return( false );
        }
        else if (obj instanceof ICFBamTable) {
            ICFBamTable rhs = (ICFBamTable)obj;
        if (!super.equals(obj)) {
            return( false );
        }
			if( getRequiredSchemaDefId() != null && !getRequiredSchemaDefId().isNull() ) {
				if( rhs.getRequiredSchemaDefId() != null && !rhs.getRequiredSchemaDefId().isNull() ) {
					if( ! getRequiredSchemaDefId().equals( rhs.getRequiredSchemaDefId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSchemaDefId() != null && !getRequiredSchemaDefId().isNull()) {
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
			if( getOptionalDbName() != null ) {
				if( rhs.getOptionalDbName() != null ) {
					if( ! getOptionalDbName().equals( rhs.getOptionalDbName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDbName() != null ) {
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
			if( getRequiredPageData() != rhs.getRequiredPageData() ) {
				return( false );
			}
			if( getOptionalPrimaryIndexId() != null && !getOptionalPrimaryIndexId().isNull() ) {
				if( rhs.getOptionalPrimaryIndexId() != null && !rhs.getOptionalPrimaryIndexId().isNull() ) {
					if( ! getOptionalPrimaryIndexId().equals( rhs.getOptionalPrimaryIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalPrimaryIndexId() != null && !getOptionalPrimaryIndexId().isNull()) {
					return( false );
				}
			}
			if( getRequiredTableClassCode() != null ) {
				if( rhs.getRequiredTableClassCode() != null ) {
					if( ! getRequiredTableClassCode().equals( rhs.getRequiredTableClassCode() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredTableClassCode() != null ) {
					return( false );
				}
			}
			if( getOptionalLookupIndexId() != null && !getOptionalLookupIndexId().isNull() ) {
				if( rhs.getOptionalLookupIndexId() != null && !rhs.getOptionalLookupIndexId().isNull() ) {
					if( ! getOptionalLookupIndexId().equals( rhs.getOptionalLookupIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalLookupIndexId() != null && !getOptionalLookupIndexId().isNull()) {
					return( false );
				}
			}
			if( getOptionalAltIndexId() != null && !getOptionalAltIndexId().isNull() ) {
				if( rhs.getOptionalAltIndexId() != null && !rhs.getOptionalAltIndexId().isNull() ) {
					if( ! getOptionalAltIndexId().equals( rhs.getOptionalAltIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalAltIndexId() != null && !getOptionalAltIndexId().isNull()) {
					return( false );
				}
			}
			if( getOptionalQualifyingTableId() != null && !getOptionalQualifyingTableId().isNull() ) {
				if( rhs.getOptionalQualifyingTableId() != null && !rhs.getOptionalQualifyingTableId().isNull() ) {
					if( ! getOptionalQualifyingTableId().equals( rhs.getOptionalQualifyingTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalQualifyingTableId() != null && !getOptionalQualifyingTableId().isNull()) {
					return( false );
				}
			}
			if( getRequiredIsInstantiable() != rhs.getRequiredIsInstantiable() ) {
				return( false );
			}
			if( getRequiredHasHistory() != rhs.getRequiredHasHistory() ) {
				return( false );
			}
			if( getRequiredHasAuditColumns() != rhs.getRequiredHasAuditColumns() ) {
				return( false );
			}
			if( getRequiredIsMutable() != rhs.getRequiredIsMutable() ) {
				return( false );
			}
			if( getRequiredIsServerOnly() != rhs.getRequiredIsServerOnly() ) {
				return( false );
			}
			if( getRequiredLoaderBehaviour() != null ) {
				if( rhs.getRequiredLoaderBehaviour() != null ) {
					if( ! getRequiredLoaderBehaviour().equals( rhs.getRequiredLoaderBehaviour() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredLoaderBehaviour() != null ) {
					return( false );
				}
			}
			if( getRequiredSecScope() != null ) {
				if( rhs.getRequiredSecScope() != null ) {
					if( ! getRequiredSecScope().equals( rhs.getRequiredSecScope() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecScope() != null ) {
					return( false );
				}
			}
            return( true );
        }
        else if (obj instanceof ICFBamTableH) {
			if (!super.equals(obj)) {
				return( false );
			}
            ICFBamTableH rhs = (ICFBamTableH)obj;
        if (!super.equals(obj)) {
            return( false );
        }
			if( getRequiredSchemaDefId() != null && !getRequiredSchemaDefId().isNull() ) {
				if( rhs.getRequiredSchemaDefId() != null && !rhs.getRequiredSchemaDefId().isNull() ) {
					if( ! getRequiredSchemaDefId().equals( rhs.getRequiredSchemaDefId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSchemaDefId() != null && !getRequiredSchemaDefId().isNull()) {
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
			if( getOptionalDbName() != null ) {
				if( rhs.getOptionalDbName() != null ) {
					if( ! getOptionalDbName().equals( rhs.getOptionalDbName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDbName() != null ) {
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
			if( getRequiredPageData() != rhs.getRequiredPageData() ) {
				return( false );
			}
			if( getOptionalPrimaryIndexId() != null && !getOptionalPrimaryIndexId().isNull() ) {
				if( rhs.getOptionalPrimaryIndexId() != null && !rhs.getOptionalPrimaryIndexId().isNull() ) {
					if( ! getOptionalPrimaryIndexId().equals( rhs.getOptionalPrimaryIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalPrimaryIndexId() != null && !getOptionalPrimaryIndexId().isNull()) {
					return( false );
				}
			}
			if( getRequiredTableClassCode() != null ) {
				if( rhs.getRequiredTableClassCode() != null ) {
					if( ! getRequiredTableClassCode().equals( rhs.getRequiredTableClassCode() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredTableClassCode() != null ) {
					return( false );
				}
			}
			if( getOptionalLookupIndexId() != null && !getOptionalLookupIndexId().isNull() ) {
				if( rhs.getOptionalLookupIndexId() != null && !rhs.getOptionalLookupIndexId().isNull() ) {
					if( ! getOptionalLookupIndexId().equals( rhs.getOptionalLookupIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalLookupIndexId() != null && !getOptionalLookupIndexId().isNull()) {
					return( false );
				}
			}
			if( getOptionalAltIndexId() != null && !getOptionalAltIndexId().isNull() ) {
				if( rhs.getOptionalAltIndexId() != null && !rhs.getOptionalAltIndexId().isNull() ) {
					if( ! getOptionalAltIndexId().equals( rhs.getOptionalAltIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalAltIndexId() != null && !getOptionalAltIndexId().isNull()) {
					return( false );
				}
			}
			if( getOptionalQualifyingTableId() != null && !getOptionalQualifyingTableId().isNull() ) {
				if( rhs.getOptionalQualifyingTableId() != null && !rhs.getOptionalQualifyingTableId().isNull() ) {
					if( ! getOptionalQualifyingTableId().equals( rhs.getOptionalQualifyingTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalQualifyingTableId() != null && !getOptionalQualifyingTableId().isNull()) {
					return( false );
				}
			}
			if( getRequiredIsInstantiable() != rhs.getRequiredIsInstantiable() ) {
				return( false );
			}
			if( getRequiredHasHistory() != rhs.getRequiredHasHistory() ) {
				return( false );
			}
			if( getRequiredHasAuditColumns() != rhs.getRequiredHasAuditColumns() ) {
				return( false );
			}
			if( getRequiredIsMutable() != rhs.getRequiredIsMutable() ) {
				return( false );
			}
			if( getRequiredIsServerOnly() != rhs.getRequiredIsServerOnly() ) {
				return( false );
			}
			if( getRequiredLoaderBehaviour() != null ) {
				if( rhs.getRequiredLoaderBehaviour() != null ) {
					if( ! getRequiredLoaderBehaviour().equals( rhs.getRequiredLoaderBehaviour() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredLoaderBehaviour() != null ) {
					return( false );
				}
			}
			if( getRequiredSecScope() != null ) {
				if( rhs.getRequiredSecScope() != null ) {
					if( ! getRequiredSecScope().equals( rhs.getRequiredSecScope() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecScope() != null ) {
					return( false );
				}
			}
            return( true );
        }
        else if (obj instanceof ICFBamScopeHPKey) {
			return( super.equals(obj) );
        }
        else if (obj instanceof ICFBamTableBySchemaDefIdxKey) {
            ICFBamTableBySchemaDefIdxKey rhs = (ICFBamTableBySchemaDefIdxKey)obj;
			if( getRequiredSchemaDefId() != null && !getRequiredSchemaDefId().isNull() ) {
				if( rhs.getRequiredSchemaDefId() != null && !rhs.getRequiredSchemaDefId().isNull() ) {
					if( ! getRequiredSchemaDefId().equals( rhs.getRequiredSchemaDefId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSchemaDefId() != null && !getRequiredSchemaDefId().isNull()) {
					return( false );
				}
			}
            return( true );
        }
        else if (obj instanceof ICFBamTableByDefSchemaIdxKey) {
            ICFBamTableByDefSchemaIdxKey rhs = (ICFBamTableByDefSchemaIdxKey)obj;
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
        else if (obj instanceof ICFBamTableByUNameIdxKey) {
            ICFBamTableByUNameIdxKey rhs = (ICFBamTableByUNameIdxKey)obj;
			if( getRequiredSchemaDefId() != null && !getRequiredSchemaDefId().isNull() ) {
				if( rhs.getRequiredSchemaDefId() != null && !rhs.getRequiredSchemaDefId().isNull() ) {
					if( ! getRequiredSchemaDefId().equals( rhs.getRequiredSchemaDefId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSchemaDefId() != null && !getRequiredSchemaDefId().isNull()) {
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
        else if (obj instanceof ICFBamTableBySchemaCdIdxKey) {
            ICFBamTableBySchemaCdIdxKey rhs = (ICFBamTableBySchemaCdIdxKey)obj;
			if( getRequiredSchemaDefId() != null && !getRequiredSchemaDefId().isNull() ) {
				if( rhs.getRequiredSchemaDefId() != null && !rhs.getRequiredSchemaDefId().isNull() ) {
					if( ! getRequiredSchemaDefId().equals( rhs.getRequiredSchemaDefId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSchemaDefId() != null && !getRequiredSchemaDefId().isNull()) {
					return( false );
				}
			}
			if( getRequiredTableClassCode() != null ) {
				if( rhs.getRequiredTableClassCode() != null ) {
					if( ! getRequiredTableClassCode().equals( rhs.getRequiredTableClassCode() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredTableClassCode() != null ) {
					return( false );
				}
			}
            return( true );
        }
        else if (obj instanceof ICFBamTableByPrimaryIndexIdxKey) {
            ICFBamTableByPrimaryIndexIdxKey rhs = (ICFBamTableByPrimaryIndexIdxKey)obj;
			if( getOptionalPrimaryIndexId() != null && !getOptionalPrimaryIndexId().isNull() ) {
				if( rhs.getOptionalPrimaryIndexId() != null && !rhs.getOptionalPrimaryIndexId().isNull() ) {
					if( ! getOptionalPrimaryIndexId().equals( rhs.getOptionalPrimaryIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalPrimaryIndexId() != null && !getOptionalPrimaryIndexId().isNull()) {
					return( false );
				}
			}
            return( true );
        }
        else if (obj instanceof ICFBamTableByLookupIndexIdxKey) {
            ICFBamTableByLookupIndexIdxKey rhs = (ICFBamTableByLookupIndexIdxKey)obj;
			if( getOptionalLookupIndexId() != null && !getOptionalLookupIndexId().isNull() ) {
				if( rhs.getOptionalLookupIndexId() != null && !rhs.getOptionalLookupIndexId().isNull() ) {
					if( ! getOptionalLookupIndexId().equals( rhs.getOptionalLookupIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalLookupIndexId() != null && !getOptionalLookupIndexId().isNull()) {
					return( false );
				}
			}
            return( true );
        }
        else if (obj instanceof ICFBamTableByAltIndexIdxKey) {
            ICFBamTableByAltIndexIdxKey rhs = (ICFBamTableByAltIndexIdxKey)obj;
			if( getOptionalAltIndexId() != null && !getOptionalAltIndexId().isNull() ) {
				if( rhs.getOptionalAltIndexId() != null && !rhs.getOptionalAltIndexId().isNull() ) {
					if( ! getOptionalAltIndexId().equals( rhs.getOptionalAltIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalAltIndexId() != null && !getOptionalAltIndexId().isNull()) {
					return( false );
				}
			}
            return( true );
        }
        else if (obj instanceof ICFBamTableByQualTableIdxKey) {
            ICFBamTableByQualTableIdxKey rhs = (ICFBamTableByQualTableIdxKey)obj;
			if( getOptionalQualifyingTableId() != null && !getOptionalQualifyingTableId().isNull() ) {
				if( rhs.getOptionalQualifyingTableId() != null && !rhs.getOptionalQualifyingTableId().isNull() ) {
					if( ! getOptionalQualifyingTableId().equals( rhs.getOptionalQualifyingTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalQualifyingTableId() != null && !getOptionalQualifyingTableId().isNull()) {
					return( false );
				}
			}
            return( true );
        }
        else {
			return( super.equals(obj) );
        }
    }
    
    @Override
    public int hashCode() {
        int hashCode = super.hashCode();
		hashCode = hashCode + getRequiredSchemaDefId().hashCode();
		if( getOptionalDefSchemaId() != null ) {
			hashCode = hashCode + getOptionalDefSchemaId().hashCode();
		}
		if( getRequiredName() != null ) {
			hashCode = hashCode + getRequiredName().hashCode();
		}
		if( getOptionalDbName() != null ) {
			hashCode = hashCode + getOptionalDbName().hashCode();
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
		if( getRequiredPageData() ) {
			hashCode = ( hashCode * 2 ) + 1;
		}
		else {
			hashCode = hashCode * 2;
		}
		if( getOptionalPrimaryIndexId() != null ) {
			hashCode = hashCode + getOptionalPrimaryIndexId().hashCode();
		}
		if( getRequiredTableClassCode() != null ) {
			hashCode = hashCode + getRequiredTableClassCode().hashCode();
		}
		if( getOptionalLookupIndexId() != null ) {
			hashCode = hashCode + getOptionalLookupIndexId().hashCode();
		}
		if( getOptionalAltIndexId() != null ) {
			hashCode = hashCode + getOptionalAltIndexId().hashCode();
		}
		if( getOptionalQualifyingTableId() != null ) {
			hashCode = hashCode + getOptionalQualifyingTableId().hashCode();
		}
		if( getRequiredIsInstantiable() ) {
			hashCode = ( hashCode * 2 ) + 1;
		}
		else {
			hashCode = hashCode * 2;
		}
		if( getRequiredHasHistory() ) {
			hashCode = ( hashCode * 2 ) + 1;
		}
		else {
			hashCode = hashCode * 2;
		}
		if( getRequiredHasAuditColumns() ) {
			hashCode = ( hashCode * 2 ) + 1;
		}
		else {
			hashCode = hashCode * 2;
		}
		if( getRequiredIsMutable() ) {
			hashCode = ( hashCode * 2 ) + 1;
		}
		else {
			hashCode = hashCode * 2;
		}
		if( getRequiredIsServerOnly() ) {
			hashCode = ( hashCode * 2 ) + 1;
		}
		else {
			hashCode = hashCode * 2;
		}
		hashCode = ( hashCode * 0x10000 ) + getRequiredLoaderBehaviour().ordinal();
		hashCode = ( hashCode * 0x10000 ) + getRequiredSecScope().ordinal();
        return( hashCode & 0x7fffffff );
    }

    @Override
    public int compareTo( Object obj ) {
        int cmp;
        if (obj == null) {
            return( 1 );
        }
        else if (obj instanceof ICFBamTable) {
        cmp = super.compareTo(obj);
        if (cmp != 0) {
            return( cmp );
        }
        ICFBamTable rhs = (ICFBamTable)obj;
			if (getRequiredSchemaDefId() != null) {
				if (rhs.getRequiredSchemaDefId() != null) {
					cmp = getRequiredSchemaDefId().compareTo( rhs.getRequiredSchemaDefId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSchemaDefId() != null) {
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
			if( getOptionalDbName() != null ) {
				if( rhs.getOptionalDbName() != null ) {
					cmp = getOptionalDbName().compareTo( rhs.getOptionalDbName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDbName() != null ) {
					return( -1 );
				}
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
			if( getRequiredPageData() ) {
				if( ! rhs.getRequiredPageData() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredPageData() ) {
					return( -1 );
				}
			}
			if( getOptionalPrimaryIndexId() != null ) {
				if( rhs.getOptionalPrimaryIndexId() != null ) {
					cmp = getOptionalPrimaryIndexId().compareTo( rhs.getOptionalPrimaryIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalPrimaryIndexId() != null ) {
					return( -1 );
				}
			}
			if (getRequiredTableClassCode() != null) {
				if (rhs.getRequiredTableClassCode() != null) {
					cmp = getRequiredTableClassCode().compareTo( rhs.getRequiredTableClassCode() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredTableClassCode() != null) {
				return( -1 );
			}
			if( getOptionalLookupIndexId() != null ) {
				if( rhs.getOptionalLookupIndexId() != null ) {
					cmp = getOptionalLookupIndexId().compareTo( rhs.getOptionalLookupIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalLookupIndexId() != null ) {
					return( -1 );
				}
			}
			if( getOptionalAltIndexId() != null ) {
				if( rhs.getOptionalAltIndexId() != null ) {
					cmp = getOptionalAltIndexId().compareTo( rhs.getOptionalAltIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalAltIndexId() != null ) {
					return( -1 );
				}
			}
			if( getOptionalQualifyingTableId() != null ) {
				if( rhs.getOptionalQualifyingTableId() != null ) {
					cmp = getOptionalQualifyingTableId().compareTo( rhs.getOptionalQualifyingTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalQualifyingTableId() != null ) {
					return( -1 );
				}
			}
			if( getRequiredIsInstantiable() ) {
				if( ! rhs.getRequiredIsInstantiable() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredIsInstantiable() ) {
					return( -1 );
				}
			}
			if( getRequiredHasHistory() ) {
				if( ! rhs.getRequiredHasHistory() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredHasHistory() ) {
					return( -1 );
				}
			}
			if( getRequiredHasAuditColumns() ) {
				if( ! rhs.getRequiredHasAuditColumns() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredHasAuditColumns() ) {
					return( -1 );
				}
			}
			if( getRequiredIsMutable() ) {
				if( ! rhs.getRequiredIsMutable() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredIsMutable() ) {
					return( -1 );
				}
			}
			if( getRequiredIsServerOnly() ) {
				if( ! rhs.getRequiredIsServerOnly() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredIsServerOnly() ) {
					return( -1 );
				}
			}
			if (getRequiredLoaderBehaviour() != null) {
				if (rhs.getRequiredLoaderBehaviour() != null) {
					cmp = getRequiredLoaderBehaviour().compareTo( rhs.getRequiredLoaderBehaviour() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredLoaderBehaviour() != null) {
				return( -1 );
			}
			if (getRequiredSecScope() != null) {
				if (rhs.getRequiredSecScope() != null) {
					cmp = getRequiredSecScope().compareTo( rhs.getRequiredSecScope() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecScope() != null) {
				return( -1 );
			}
            return( 0 );
        }
        else if (obj instanceof ICFBamScopeHPKey) {
        return( super.compareTo(obj) );
        }
        else if (obj instanceof ICFBamTableH) {
        cmp = super.compareTo(obj);
        if (cmp != 0) {
            return( cmp );
        }
        ICFBamTableH rhs = (ICFBamTableH)obj;
			if (getRequiredSchemaDefId() != null) {
				if (rhs.getRequiredSchemaDefId() != null) {
					cmp = getRequiredSchemaDefId().compareTo( rhs.getRequiredSchemaDefId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSchemaDefId() != null) {
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
			if( getOptionalDbName() != null ) {
				if( rhs.getOptionalDbName() != null ) {
					cmp = getOptionalDbName().compareTo( rhs.getOptionalDbName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDbName() != null ) {
					return( -1 );
				}
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
			if( getRequiredPageData() ) {
				if( ! rhs.getRequiredPageData() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredPageData() ) {
					return( -1 );
				}
			}
			if( getOptionalPrimaryIndexId() != null ) {
				if( rhs.getOptionalPrimaryIndexId() != null ) {
					cmp = getOptionalPrimaryIndexId().compareTo( rhs.getOptionalPrimaryIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalPrimaryIndexId() != null ) {
					return( -1 );
				}
			}
			if (getRequiredTableClassCode() != null) {
				if (rhs.getRequiredTableClassCode() != null) {
					cmp = getRequiredTableClassCode().compareTo( rhs.getRequiredTableClassCode() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredTableClassCode() != null) {
				return( -1 );
			}
			if( getOptionalLookupIndexId() != null ) {
				if( rhs.getOptionalLookupIndexId() != null ) {
					cmp = getOptionalLookupIndexId().compareTo( rhs.getOptionalLookupIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalLookupIndexId() != null ) {
					return( -1 );
				}
			}
			if( getOptionalAltIndexId() != null ) {
				if( rhs.getOptionalAltIndexId() != null ) {
					cmp = getOptionalAltIndexId().compareTo( rhs.getOptionalAltIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalAltIndexId() != null ) {
					return( -1 );
				}
			}
			if( getOptionalQualifyingTableId() != null ) {
				if( rhs.getOptionalQualifyingTableId() != null ) {
					cmp = getOptionalQualifyingTableId().compareTo( rhs.getOptionalQualifyingTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalQualifyingTableId() != null ) {
					return( -1 );
				}
			}
			if( getRequiredIsInstantiable() ) {
				if( ! rhs.getRequiredIsInstantiable() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredIsInstantiable() ) {
					return( -1 );
				}
			}
			if( getRequiredHasHistory() ) {
				if( ! rhs.getRequiredHasHistory() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredHasHistory() ) {
					return( -1 );
				}
			}
			if( getRequiredHasAuditColumns() ) {
				if( ! rhs.getRequiredHasAuditColumns() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredHasAuditColumns() ) {
					return( -1 );
				}
			}
			if( getRequiredIsMutable() ) {
				if( ! rhs.getRequiredIsMutable() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredIsMutable() ) {
					return( -1 );
				}
			}
			if( getRequiredIsServerOnly() ) {
				if( ! rhs.getRequiredIsServerOnly() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredIsServerOnly() ) {
					return( -1 );
				}
			}
			if (getRequiredLoaderBehaviour() != null) {
				if (rhs.getRequiredLoaderBehaviour() != null) {
					cmp = getRequiredLoaderBehaviour().compareTo( rhs.getRequiredLoaderBehaviour() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredLoaderBehaviour() != null) {
				return( -1 );
			}
			if (getRequiredSecScope() != null) {
				if (rhs.getRequiredSecScope() != null) {
					cmp = getRequiredSecScope().compareTo( rhs.getRequiredSecScope() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecScope() != null) {
				return( -1 );
			}
            return( 0 );
        }
        else if (obj instanceof ICFBamTableBySchemaDefIdxKey ) {
            ICFBamTableBySchemaDefIdxKey rhs = (ICFBamTableBySchemaDefIdxKey)obj;
			if (getRequiredSchemaDefId() != null) {
				if (rhs.getRequiredSchemaDefId() != null) {
					cmp = getRequiredSchemaDefId().compareTo( rhs.getRequiredSchemaDefId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSchemaDefId() != null) {
				return( -1 );
			}
            return( 0 );
        }
        else if (obj instanceof ICFBamTableByDefSchemaIdxKey ) {
            ICFBamTableByDefSchemaIdxKey rhs = (ICFBamTableByDefSchemaIdxKey)obj;
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
        else if (obj instanceof ICFBamTableByUNameIdxKey ) {
            ICFBamTableByUNameIdxKey rhs = (ICFBamTableByUNameIdxKey)obj;
			if (getRequiredSchemaDefId() != null) {
				if (rhs.getRequiredSchemaDefId() != null) {
					cmp = getRequiredSchemaDefId().compareTo( rhs.getRequiredSchemaDefId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSchemaDefId() != null) {
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
        else if (obj instanceof ICFBamTableBySchemaCdIdxKey ) {
            ICFBamTableBySchemaCdIdxKey rhs = (ICFBamTableBySchemaCdIdxKey)obj;
			if (getRequiredSchemaDefId() != null) {
				if (rhs.getRequiredSchemaDefId() != null) {
					cmp = getRequiredSchemaDefId().compareTo( rhs.getRequiredSchemaDefId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSchemaDefId() != null) {
				return( -1 );
			}
			if (getRequiredTableClassCode() != null) {
				if (rhs.getRequiredTableClassCode() != null) {
					cmp = getRequiredTableClassCode().compareTo( rhs.getRequiredTableClassCode() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredTableClassCode() != null) {
				return( -1 );
			}
            return( 0 );
        }
        else if (obj instanceof ICFBamTableByPrimaryIndexIdxKey ) {
            ICFBamTableByPrimaryIndexIdxKey rhs = (ICFBamTableByPrimaryIndexIdxKey)obj;
			if( getOptionalPrimaryIndexId() != null ) {
				if( rhs.getOptionalPrimaryIndexId() != null ) {
					cmp = getOptionalPrimaryIndexId().compareTo( rhs.getOptionalPrimaryIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalPrimaryIndexId() != null ) {
					return( -1 );
				}
			}
            return( 0 );
        }
        else if (obj instanceof ICFBamTableByLookupIndexIdxKey ) {
            ICFBamTableByLookupIndexIdxKey rhs = (ICFBamTableByLookupIndexIdxKey)obj;
			if( getOptionalLookupIndexId() != null ) {
				if( rhs.getOptionalLookupIndexId() != null ) {
					cmp = getOptionalLookupIndexId().compareTo( rhs.getOptionalLookupIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalLookupIndexId() != null ) {
					return( -1 );
				}
			}
            return( 0 );
        }
        else if (obj instanceof ICFBamTableByAltIndexIdxKey ) {
            ICFBamTableByAltIndexIdxKey rhs = (ICFBamTableByAltIndexIdxKey)obj;
			if( getOptionalAltIndexId() != null ) {
				if( rhs.getOptionalAltIndexId() != null ) {
					cmp = getOptionalAltIndexId().compareTo( rhs.getOptionalAltIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalAltIndexId() != null ) {
					return( -1 );
				}
			}
            return( 0 );
        }
        else if (obj instanceof ICFBamTableByQualTableIdxKey ) {
            ICFBamTableByQualTableIdxKey rhs = (ICFBamTableByQualTableIdxKey)obj;
			if( getOptionalQualifyingTableId() != null ) {
				if( rhs.getOptionalQualifyingTableId() != null ) {
					cmp = getOptionalQualifyingTableId().compareTo( rhs.getOptionalQualifyingTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalQualifyingTableId() != null ) {
					return( -1 );
				}
			}
            return( 0 );
        }
        else {
        return( super.compareTo(obj) );
        }
    }
	@Override
    public void set( ICFBamScope src ) {
		if( src instanceof ICFBamTable ) {
			setTable( (ICFBamTable)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamJpaTable" );
		}
    }

	@Override
    public void setTable( ICFBamTable src ) {
        super.setScope( src );
		setRequiredSchemaDefId( src.getRequiredSchemaDefId() );
		setOptionalDefSchemaId( src.getOptionalDefSchemaId() );
		setRequiredName( src.getRequiredName() );
		setOptionalDbName( src.getOptionalDbName() );
		setOptionalShortName( src.getOptionalShortName() );
		setOptionalLabel( src.getOptionalLabel() );
		setOptionalShortDescription( src.getOptionalShortDescription() );
		setOptionalDescription( src.getOptionalDescription() );
		setRequiredPageData( src.getRequiredPageData() );
		setOptionalPrimaryIndexId( src.getOptionalPrimaryIndexId() );
		setRequiredTableClassCode( src.getRequiredTableClassCode() );
		setOptionalLookupIndexId( src.getOptionalLookupIndexId() );
		setOptionalAltIndexId( src.getOptionalAltIndexId() );
		setOptionalQualifyingTableId( src.getOptionalQualifyingTableId() );
		setRequiredIsInstantiable( src.getRequiredIsInstantiable() );
		setRequiredHasHistory( src.getRequiredHasHistory() );
		setRequiredHasAuditColumns( src.getRequiredHasAuditColumns() );
		setRequiredIsMutable( src.getRequiredIsMutable() );
		setRequiredIsServerOnly( src.getRequiredIsServerOnly() );
		setRequiredLoaderBehaviour( src.getRequiredLoaderBehaviour() );
		setRequiredSecScope( src.getRequiredSecScope() );
    }

	@Override
    public void set( ICFBamScopeH src ) {
		if( src instanceof ICFBamTableH ) {
			setTable( (ICFBamTableH)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamTableH" );
		}
    }

	@Override
    public void setTable( ICFBamTableH src ) {
        super.setScope( src );
		setRequiredSchemaDefId( src.getRequiredSchemaDefId() );
		setOptionalDefSchemaId( src.getOptionalDefSchemaId() );
		setRequiredName( src.getRequiredName() );
		setOptionalDbName( src.getOptionalDbName() );
		setOptionalShortName( src.getOptionalShortName() );
		setOptionalLabel( src.getOptionalLabel() );
		setOptionalShortDescription( src.getOptionalShortDescription() );
		setOptionalDescription( src.getOptionalDescription() );
		setRequiredPageData( src.getRequiredPageData() );
		setOptionalPrimaryIndexId( src.getOptionalPrimaryIndexId() );
		setRequiredTableClassCode( src.getRequiredTableClassCode() );
		setOptionalLookupIndexId( src.getOptionalLookupIndexId() );
		setOptionalAltIndexId( src.getOptionalAltIndexId() );
		setOptionalQualifyingTableId( src.getOptionalQualifyingTableId() );
		setRequiredIsInstantiable( src.getRequiredIsInstantiable() );
		setRequiredHasHistory( src.getRequiredHasHistory() );
		setRequiredHasAuditColumns( src.getRequiredHasAuditColumns() );
		setRequiredIsMutable( src.getRequiredIsMutable() );
		setRequiredIsServerOnly( src.getRequiredIsServerOnly() );
		setRequiredLoaderBehaviour( src.getRequiredLoaderBehaviour() );
		setRequiredSecScope( src.getRequiredSecScope() );
    }

    public String getXmlAttrFragment() {
        String ret = super.getXmlAttrFragment() 
			+ " RequiredSchemaDefId=" + "\"" + getRequiredSchemaDefId().toString() + "\""
			+ " OptionalDefSchemaId=" + ( ( getOptionalDefSchemaId() == null ) ? "null" : "\"" + getOptionalDefSchemaId().toString() + "\"" )
			+ " RequiredName=" + "\"" + StringEscapeUtils.escapeXml11( getRequiredName() ) + "\""
			+ " OptionalDbName=" + ( ( getOptionalDbName() == null ) ? "null" : "\"" + StringEscapeUtils.escapeXml11( getOptionalDbName() ) + "\"" )
			+ " OptionalShortName=" + ( ( getOptionalShortName() == null ) ? "null" : "\"" + StringEscapeUtils.escapeXml11( getOptionalShortName() ) + "\"" )
			+ " OptionalLabel=" + ( ( getOptionalLabel() == null ) ? "null" : "\"" + StringEscapeUtils.escapeXml11( getOptionalLabel() ) + "\"" )
			+ " OptionalShortDescription=" + ( ( getOptionalShortDescription() == null ) ? "null" : "\"" + StringEscapeUtils.escapeXml11( getOptionalShortDescription() ) + "\"" )
			+ " OptionalDescription=" + ( ( getOptionalDescription() == null ) ? "null" : "\"" + StringEscapeUtils.escapeXml11( getOptionalDescription() ) + "\"" )
			+ " RequiredPageData=" + (( getRequiredPageData() ) ? "\"true\"" : "\"false\"" )
			+ " OptionalPrimaryIndexId=" + ( ( getOptionalPrimaryIndexId() == null ) ? "null" : "\"" + getOptionalPrimaryIndexId().toString() + "\"" )
			+ " RequiredTableClassCode=" + "\"" + StringEscapeUtils.escapeXml11( getRequiredTableClassCode() ) + "\""
			+ " OptionalLookupIndexId=" + ( ( getOptionalLookupIndexId() == null ) ? "null" : "\"" + getOptionalLookupIndexId().toString() + "\"" )
			+ " OptionalAltIndexId=" + ( ( getOptionalAltIndexId() == null ) ? "null" : "\"" + getOptionalAltIndexId().toString() + "\"" )
			+ " OptionalQualifyingTableId=" + ( ( getOptionalQualifyingTableId() == null ) ? "null" : "\"" + getOptionalQualifyingTableId().toString() + "\"" )
			+ " RequiredIsInstantiable=" + (( getRequiredIsInstantiable() ) ? "\"true\"" : "\"false\"" )
			+ " RequiredHasHistory=" + (( getRequiredHasHistory() ) ? "\"true\"" : "\"false\"" )
			+ " RequiredHasAuditColumns=" + (( getRequiredHasAuditColumns() ) ? "\"true\"" : "\"false\"" )
			+ " RequiredIsMutable=" + (( getRequiredIsMutable() ) ? "\"true\"" : "\"false\"" )
			+ " RequiredIsServerOnly=" + (( getRequiredIsServerOnly() ) ? "\"true\"" : "\"false\"" )
			+ " RequiredLoaderBehaviour=" + "\"" + getRequiredLoaderBehaviour().toString() + "\""
			+ " RequiredSecScope=" + "\"" + getRequiredSecScope().toString() + "\"";
        return( ret );
    }

    public String toString() {
        String ret = "<CFBamJpaTableH" + getXmlAttrFragment() + "/>";
        return( ret );
    }
}
