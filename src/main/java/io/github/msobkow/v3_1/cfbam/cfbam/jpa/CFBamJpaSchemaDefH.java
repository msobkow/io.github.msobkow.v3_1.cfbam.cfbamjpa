// Description: Java 25 JPA implementation of SchemaDef history objects

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
 *  CFBamJpaSchemaDefH provides history objects matching the CFBamSchemaDef change history.
 */
@Entity
@Table(
    name = "schemadef_h", schema = "CFBam31",
    indexes = {
        @Index(name = "SchemaDefIdIdx_h", columnList = "auditClusterId, auditStamp, auditAction, requiredRevision, auditSessionId, Id", unique = true),
        @Index(name = "SchemaDefCTenantIdx_h", columnList = "CTenantId", unique = false),
        @Index(name = "SchemaDefMinorVersionIdx_h", columnList = "MinorVersionId", unique = false),
        @Index(name = "SchemaDefUNameIdx_h", columnList = "MinorVersionId, safe_name", unique = true),
        @Index(name = "SchemaAuthorEMailIdx_h", columnList = "CTenantId, AuthEMail", unique = false),
        @Index(name = "SchemaProjectURLIdx_h", columnList = "CTenantId, ProjURL", unique = false),
        @Index(name = "SchemaPublishURIIdx_h", columnList = "CTenantId, PubURI", unique = true)
    }
)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("43010")
@Transactional(Transactional.TxType.SUPPORTS)
@PersistenceContext(unitName = "CFBamPU")
public class CFBamJpaSchemaDefH extends CFBamJpaScopeH
    implements ICFBamSchemaDefH
{
	@AttributeOverrides({
		@AttributeOverride(name="bytes", column = @Column( name="CTenantId", nullable=false, length=CFLibDbKeyHash256.HASH_LENGTH ) )
	})
	protected CFLibDbKeyHash256 requiredCTenantId;
	@AttributeOverrides({
		@AttributeOverride(name="bytes", column = @Column( name="MinorVersionId", nullable=false, length=CFLibDbKeyHash256.HASH_LENGTH ) )
	})
	protected CFLibDbKeyHash256 requiredMinorVersionId;
	@Column( name="safe_name", nullable=false, length=192 )
	protected String requiredName;
	@Column( name="DbName", nullable=true, length=12 )
	protected String optionalDbName;
	@Column( name="short_name", nullable=true, length=16 )
	protected String optionalShortName;
	@Column( name="Label", nullable=true, length=64 )
	protected String optionalLabel;
	@Column( name="short_descr", nullable=true, length=128 )
	protected String optionalShortDescription;
	@Column( name="descr", nullable=true, length=1023 )
	protected String optionalDescription;
	@Column( name="CopyPerd", nullable=false, length=10 )
	protected String requiredCopyrightPeriod;
	@Column( name="CopyHold", nullable=false, length=511 )
	protected String requiredCopyrightHolder;
	@Column( name="AuthEMail", nullable=false, length=512 )
	protected String requiredAuthorEMail;
	@Column( name="ProjURL", nullable=false, length=1024 )
	protected String requiredProjectURL;
	@Column( name="PubURI", nullable=false, length=512 )
	protected String requiredPublishURI;

    public CFBamJpaSchemaDefH() {
            super();
		requiredCTenantId = CFLibDbKeyHash256.fromHex( ICFBamSchemaDef.CTENANTID_INIT_VALUE.toString() );
		requiredMinorVersionId = CFLibDbKeyHash256.fromHex( ICFBamSchemaDef.MINORVERSIONID_INIT_VALUE.toString() );
		requiredName = ICFBamSchemaDef.NAME_INIT_VALUE;
		optionalDbName = null;
		optionalShortName = null;
		optionalLabel = null;
		optionalShortDescription = null;
		optionalDescription = null;
		requiredCopyrightPeriod = ICFBamSchemaDef.COPYRIGHTPERIOD_INIT_VALUE;
		requiredCopyrightHolder = ICFBamSchemaDef.COPYRIGHTHOLDER_INIT_VALUE;
		requiredAuthorEMail = ICFBamSchemaDef.AUTHOREMAIL_INIT_VALUE;
		requiredProjectURL = ICFBamSchemaDef.PROJECTURL_INIT_VALUE;
		requiredPublishURI = ICFBamSchemaDef.PUBLISHURI_INIT_VALUE;
    }

    @Override
    public int getClassCode() {
            return( ICFBamSchemaDef.CLASS_CODE );
    }

	@Override
	public CFLibDbKeyHash256 getRequiredCTenantId() {
		return( requiredCTenantId );
	}

	@Override
	public void setRequiredCTenantId( CFLibDbKeyHash256 value ) {
		if( value == null || value.isNull() ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredCTenantId",
				1,
				"value" );
		}
		requiredCTenantId = value;
	}

	@Override
	public CFLibDbKeyHash256 getRequiredMinorVersionId() {
		return( requiredMinorVersionId );
	}

	@Override
	public void setRequiredMinorVersionId( CFLibDbKeyHash256 value ) {
		if( value == null || value.isNull() ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredMinorVersionId",
				1,
				"value" );
		}
		requiredMinorVersionId = value;
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
		if( value != null && value.length() > 12 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setOptionalDbName",
				1,
				"value.length()",
				value.length(),
				12 );
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
	public String getRequiredCopyrightPeriod() {
		return( requiredCopyrightPeriod );
	}

	@Override
	public void setRequiredCopyrightPeriod( String value ) {
		if( value == null ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredCopyrightPeriod",
				1,
				"value" );
		}
		else if( value.length() > 10 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setRequiredCopyrightPeriod",
				1,
				"value.length()",
				value.length(),
				10 );
		}
		requiredCopyrightPeriod = value;
	}

	@Override
	public String getRequiredCopyrightHolder() {
		return( requiredCopyrightHolder );
	}

	@Override
	public void setRequiredCopyrightHolder( String value ) {
		if( value == null ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredCopyrightHolder",
				1,
				"value" );
		}
		else if( value.length() > 511 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setRequiredCopyrightHolder",
				1,
				"value.length()",
				value.length(),
				511 );
		}
		requiredCopyrightHolder = value;
	}

	@Override
	public String getRequiredAuthorEMail() {
		return( requiredAuthorEMail );
	}

	@Override
	public void setRequiredAuthorEMail( String value ) {
		if( value == null ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredAuthorEMail",
				1,
				"value" );
		}
		else if( value.length() > 512 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setRequiredAuthorEMail",
				1,
				"value.length()",
				value.length(),
				512 );
		}
		requiredAuthorEMail = value;
	}

	@Override
	public String getRequiredProjectURL() {
		return( requiredProjectURL );
	}

	@Override
	public void setRequiredProjectURL( String value ) {
		if( value == null ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredProjectURL",
				1,
				"value" );
		}
		else if( value.length() > 1024 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setRequiredProjectURL",
				1,
				"value.length()",
				value.length(),
				1024 );
		}
		requiredProjectURL = value;
	}

	@Override
	public String getRequiredPublishURI() {
		return( requiredPublishURI );
	}

	@Override
	public void setRequiredPublishURI( String value ) {
		if( value == null ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredPublishURI",
				1,
				"value" );
		}
		else if( value.length() > 512 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setRequiredPublishURI",
				1,
				"value.length()",
				value.length(),
				512 );
		}
		requiredPublishURI = value;
	}

    @Override
    public boolean equals( Object obj ) {
        if (obj == null) {
            return( false );
        }
        else if (obj instanceof ICFBamSchemaDef) {
            ICFBamSchemaDef rhs = (ICFBamSchemaDef)obj;
        if (!super.equals(obj)) {
            return( false );
        }
			if( getRequiredCTenantId() != null && !getRequiredCTenantId().isNull() ) {
				if( rhs.getRequiredCTenantId() != null && !rhs.getRequiredCTenantId().isNull() ) {
					if( ! getRequiredCTenantId().equals( rhs.getRequiredCTenantId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredCTenantId() != null && !getRequiredCTenantId().isNull()) {
					return( false );
				}
			}
			if( getRequiredMinorVersionId() != null && !getRequiredMinorVersionId().isNull() ) {
				if( rhs.getRequiredMinorVersionId() != null && !rhs.getRequiredMinorVersionId().isNull() ) {
					if( ! getRequiredMinorVersionId().equals( rhs.getRequiredMinorVersionId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredMinorVersionId() != null && !getRequiredMinorVersionId().isNull()) {
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
			if( getRequiredCopyrightPeriod() != null ) {
				if( rhs.getRequiredCopyrightPeriod() != null ) {
					if( ! getRequiredCopyrightPeriod().equals( rhs.getRequiredCopyrightPeriod() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredCopyrightPeriod() != null ) {
					return( false );
				}
			}
			if( getRequiredCopyrightHolder() != null ) {
				if( rhs.getRequiredCopyrightHolder() != null ) {
					if( ! getRequiredCopyrightHolder().equals( rhs.getRequiredCopyrightHolder() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredCopyrightHolder() != null ) {
					return( false );
				}
			}
			if( getRequiredAuthorEMail() != null ) {
				if( rhs.getRequiredAuthorEMail() != null ) {
					if( ! getRequiredAuthorEMail().equals( rhs.getRequiredAuthorEMail() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredAuthorEMail() != null ) {
					return( false );
				}
			}
			if( getRequiredProjectURL() != null ) {
				if( rhs.getRequiredProjectURL() != null ) {
					if( ! getRequiredProjectURL().equals( rhs.getRequiredProjectURL() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredProjectURL() != null ) {
					return( false );
				}
			}
			if( getRequiredPublishURI() != null ) {
				if( rhs.getRequiredPublishURI() != null ) {
					if( ! getRequiredPublishURI().equals( rhs.getRequiredPublishURI() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredPublishURI() != null ) {
					return( false );
				}
			}
            return( true );
        }
        else if (obj instanceof ICFBamSchemaDefH) {
			if (!super.equals(obj)) {
				return( false );
			}
            ICFBamSchemaDefH rhs = (ICFBamSchemaDefH)obj;
        if (!super.equals(obj)) {
            return( false );
        }
			if( getRequiredCTenantId() != null && !getRequiredCTenantId().isNull() ) {
				if( rhs.getRequiredCTenantId() != null && !rhs.getRequiredCTenantId().isNull() ) {
					if( ! getRequiredCTenantId().equals( rhs.getRequiredCTenantId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredCTenantId() != null && !getRequiredCTenantId().isNull()) {
					return( false );
				}
			}
			if( getRequiredMinorVersionId() != null && !getRequiredMinorVersionId().isNull() ) {
				if( rhs.getRequiredMinorVersionId() != null && !rhs.getRequiredMinorVersionId().isNull() ) {
					if( ! getRequiredMinorVersionId().equals( rhs.getRequiredMinorVersionId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredMinorVersionId() != null && !getRequiredMinorVersionId().isNull()) {
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
			if( getRequiredCopyrightPeriod() != null ) {
				if( rhs.getRequiredCopyrightPeriod() != null ) {
					if( ! getRequiredCopyrightPeriod().equals( rhs.getRequiredCopyrightPeriod() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredCopyrightPeriod() != null ) {
					return( false );
				}
			}
			if( getRequiredCopyrightHolder() != null ) {
				if( rhs.getRequiredCopyrightHolder() != null ) {
					if( ! getRequiredCopyrightHolder().equals( rhs.getRequiredCopyrightHolder() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredCopyrightHolder() != null ) {
					return( false );
				}
			}
			if( getRequiredAuthorEMail() != null ) {
				if( rhs.getRequiredAuthorEMail() != null ) {
					if( ! getRequiredAuthorEMail().equals( rhs.getRequiredAuthorEMail() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredAuthorEMail() != null ) {
					return( false );
				}
			}
			if( getRequiredProjectURL() != null ) {
				if( rhs.getRequiredProjectURL() != null ) {
					if( ! getRequiredProjectURL().equals( rhs.getRequiredProjectURL() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredProjectURL() != null ) {
					return( false );
				}
			}
			if( getRequiredPublishURI() != null ) {
				if( rhs.getRequiredPublishURI() != null ) {
					if( ! getRequiredPublishURI().equals( rhs.getRequiredPublishURI() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredPublishURI() != null ) {
					return( false );
				}
			}
            return( true );
        }
        else if (obj instanceof ICFBamScopeHPKey) {
			return( super.equals(obj) );
        }
        else if (obj instanceof ICFBamSchemaDefByCTenantIdxKey) {
            ICFBamSchemaDefByCTenantIdxKey rhs = (ICFBamSchemaDefByCTenantIdxKey)obj;
			if( getRequiredCTenantId() != null && !getRequiredCTenantId().isNull() ) {
				if( rhs.getRequiredCTenantId() != null && !rhs.getRequiredCTenantId().isNull() ) {
					if( ! getRequiredCTenantId().equals( rhs.getRequiredCTenantId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredCTenantId() != null && !getRequiredCTenantId().isNull()) {
					return( false );
				}
			}
            return( true );
        }
        else if (obj instanceof ICFBamSchemaDefByMinorVersionIdxKey) {
            ICFBamSchemaDefByMinorVersionIdxKey rhs = (ICFBamSchemaDefByMinorVersionIdxKey)obj;
			if( getRequiredMinorVersionId() != null && !getRequiredMinorVersionId().isNull() ) {
				if( rhs.getRequiredMinorVersionId() != null && !rhs.getRequiredMinorVersionId().isNull() ) {
					if( ! getRequiredMinorVersionId().equals( rhs.getRequiredMinorVersionId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredMinorVersionId() != null && !getRequiredMinorVersionId().isNull()) {
					return( false );
				}
			}
            return( true );
        }
        else if (obj instanceof ICFBamSchemaDefByUNameIdxKey) {
            ICFBamSchemaDefByUNameIdxKey rhs = (ICFBamSchemaDefByUNameIdxKey)obj;
			if( getRequiredMinorVersionId() != null && !getRequiredMinorVersionId().isNull() ) {
				if( rhs.getRequiredMinorVersionId() != null && !rhs.getRequiredMinorVersionId().isNull() ) {
					if( ! getRequiredMinorVersionId().equals( rhs.getRequiredMinorVersionId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredMinorVersionId() != null && !getRequiredMinorVersionId().isNull()) {
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
        else if (obj instanceof ICFBamSchemaDefByAuthEMailIdxKey) {
            ICFBamSchemaDefByAuthEMailIdxKey rhs = (ICFBamSchemaDefByAuthEMailIdxKey)obj;
			if( getRequiredCTenantId() != null && !getRequiredCTenantId().isNull() ) {
				if( rhs.getRequiredCTenantId() != null && !rhs.getRequiredCTenantId().isNull() ) {
					if( ! getRequiredCTenantId().equals( rhs.getRequiredCTenantId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredCTenantId() != null && !getRequiredCTenantId().isNull()) {
					return( false );
				}
			}
			if( getRequiredAuthorEMail() != null ) {
				if( rhs.getRequiredAuthorEMail() != null ) {
					if( ! getRequiredAuthorEMail().equals( rhs.getRequiredAuthorEMail() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredAuthorEMail() != null ) {
					return( false );
				}
			}
            return( true );
        }
        else if (obj instanceof ICFBamSchemaDefByProjectURLIdxKey) {
            ICFBamSchemaDefByProjectURLIdxKey rhs = (ICFBamSchemaDefByProjectURLIdxKey)obj;
			if( getRequiredCTenantId() != null && !getRequiredCTenantId().isNull() ) {
				if( rhs.getRequiredCTenantId() != null && !rhs.getRequiredCTenantId().isNull() ) {
					if( ! getRequiredCTenantId().equals( rhs.getRequiredCTenantId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredCTenantId() != null && !getRequiredCTenantId().isNull()) {
					return( false );
				}
			}
			if( getRequiredProjectURL() != null ) {
				if( rhs.getRequiredProjectURL() != null ) {
					if( ! getRequiredProjectURL().equals( rhs.getRequiredProjectURL() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredProjectURL() != null ) {
					return( false );
				}
			}
            return( true );
        }
        else if (obj instanceof ICFBamSchemaDefByPubURIIdxKey) {
            ICFBamSchemaDefByPubURIIdxKey rhs = (ICFBamSchemaDefByPubURIIdxKey)obj;
			if( getRequiredCTenantId() != null && !getRequiredCTenantId().isNull() ) {
				if( rhs.getRequiredCTenantId() != null && !rhs.getRequiredCTenantId().isNull() ) {
					if( ! getRequiredCTenantId().equals( rhs.getRequiredCTenantId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredCTenantId() != null && !getRequiredCTenantId().isNull()) {
					return( false );
				}
			}
			if( getRequiredPublishURI() != null ) {
				if( rhs.getRequiredPublishURI() != null ) {
					if( ! getRequiredPublishURI().equals( rhs.getRequiredPublishURI() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredPublishURI() != null ) {
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
		hashCode = hashCode + getRequiredCTenantId().hashCode();
		hashCode = hashCode + getRequiredMinorVersionId().hashCode();
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
		if( getRequiredCopyrightPeriod() != null ) {
			hashCode = hashCode + getRequiredCopyrightPeriod().hashCode();
		}
		if( getRequiredCopyrightHolder() != null ) {
			hashCode = hashCode + getRequiredCopyrightHolder().hashCode();
		}
		if( getRequiredAuthorEMail() != null ) {
			hashCode = hashCode + getRequiredAuthorEMail().hashCode();
		}
		if( getRequiredProjectURL() != null ) {
			hashCode = hashCode + getRequiredProjectURL().hashCode();
		}
		if( getRequiredPublishURI() != null ) {
			hashCode = hashCode + getRequiredPublishURI().hashCode();
		}
        return( hashCode & 0x7fffffff );
    }

    @Override
    public int compareTo( Object obj ) {
        int cmp;
        if (obj == null) {
            return( 1 );
        }
        else if (obj instanceof ICFBamSchemaDef) {
        cmp = super.compareTo(obj);
        if (cmp != 0) {
            return( cmp );
        }
        ICFBamSchemaDef rhs = (ICFBamSchemaDef)obj;
			if (getRequiredCTenantId() != null) {
				if (rhs.getRequiredCTenantId() != null) {
					cmp = getRequiredCTenantId().compareTo( rhs.getRequiredCTenantId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredCTenantId() != null) {
				return( -1 );
			}
			if (getRequiredMinorVersionId() != null) {
				if (rhs.getRequiredMinorVersionId() != null) {
					cmp = getRequiredMinorVersionId().compareTo( rhs.getRequiredMinorVersionId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredMinorVersionId() != null) {
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
			if (getRequiredCopyrightPeriod() != null) {
				if (rhs.getRequiredCopyrightPeriod() != null) {
					cmp = getRequiredCopyrightPeriod().compareTo( rhs.getRequiredCopyrightPeriod() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredCopyrightPeriod() != null) {
				return( -1 );
			}
			if (getRequiredCopyrightHolder() != null) {
				if (rhs.getRequiredCopyrightHolder() != null) {
					cmp = getRequiredCopyrightHolder().compareTo( rhs.getRequiredCopyrightHolder() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredCopyrightHolder() != null) {
				return( -1 );
			}
			if (getRequiredAuthorEMail() != null) {
				if (rhs.getRequiredAuthorEMail() != null) {
					cmp = getRequiredAuthorEMail().compareTo( rhs.getRequiredAuthorEMail() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredAuthorEMail() != null) {
				return( -1 );
			}
			if (getRequiredProjectURL() != null) {
				if (rhs.getRequiredProjectURL() != null) {
					cmp = getRequiredProjectURL().compareTo( rhs.getRequiredProjectURL() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredProjectURL() != null) {
				return( -1 );
			}
			if (getRequiredPublishURI() != null) {
				if (rhs.getRequiredPublishURI() != null) {
					cmp = getRequiredPublishURI().compareTo( rhs.getRequiredPublishURI() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredPublishURI() != null) {
				return( -1 );
			}
            return( 0 );
        }
        else if (obj instanceof ICFBamScopeHPKey) {
        return( super.compareTo(obj) );
        }
        else if (obj instanceof ICFBamSchemaDefH) {
        cmp = super.compareTo(obj);
        if (cmp != 0) {
            return( cmp );
        }
        ICFBamSchemaDefH rhs = (ICFBamSchemaDefH)obj;
			if (getRequiredCTenantId() != null) {
				if (rhs.getRequiredCTenantId() != null) {
					cmp = getRequiredCTenantId().compareTo( rhs.getRequiredCTenantId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredCTenantId() != null) {
				return( -1 );
			}
			if (getRequiredMinorVersionId() != null) {
				if (rhs.getRequiredMinorVersionId() != null) {
					cmp = getRequiredMinorVersionId().compareTo( rhs.getRequiredMinorVersionId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredMinorVersionId() != null) {
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
			if (getRequiredCopyrightPeriod() != null) {
				if (rhs.getRequiredCopyrightPeriod() != null) {
					cmp = getRequiredCopyrightPeriod().compareTo( rhs.getRequiredCopyrightPeriod() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredCopyrightPeriod() != null) {
				return( -1 );
			}
			if (getRequiredCopyrightHolder() != null) {
				if (rhs.getRequiredCopyrightHolder() != null) {
					cmp = getRequiredCopyrightHolder().compareTo( rhs.getRequiredCopyrightHolder() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredCopyrightHolder() != null) {
				return( -1 );
			}
			if (getRequiredAuthorEMail() != null) {
				if (rhs.getRequiredAuthorEMail() != null) {
					cmp = getRequiredAuthorEMail().compareTo( rhs.getRequiredAuthorEMail() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredAuthorEMail() != null) {
				return( -1 );
			}
			if (getRequiredProjectURL() != null) {
				if (rhs.getRequiredProjectURL() != null) {
					cmp = getRequiredProjectURL().compareTo( rhs.getRequiredProjectURL() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredProjectURL() != null) {
				return( -1 );
			}
			if (getRequiredPublishURI() != null) {
				if (rhs.getRequiredPublishURI() != null) {
					cmp = getRequiredPublishURI().compareTo( rhs.getRequiredPublishURI() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredPublishURI() != null) {
				return( -1 );
			}
            return( 0 );
        }
        else if (obj instanceof ICFBamSchemaDefByCTenantIdxKey ) {
            ICFBamSchemaDefByCTenantIdxKey rhs = (ICFBamSchemaDefByCTenantIdxKey)obj;
			if (getRequiredCTenantId() != null) {
				if (rhs.getRequiredCTenantId() != null) {
					cmp = getRequiredCTenantId().compareTo( rhs.getRequiredCTenantId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredCTenantId() != null) {
				return( -1 );
			}
            return( 0 );
        }
        else if (obj instanceof ICFBamSchemaDefByMinorVersionIdxKey ) {
            ICFBamSchemaDefByMinorVersionIdxKey rhs = (ICFBamSchemaDefByMinorVersionIdxKey)obj;
			if (getRequiredMinorVersionId() != null) {
				if (rhs.getRequiredMinorVersionId() != null) {
					cmp = getRequiredMinorVersionId().compareTo( rhs.getRequiredMinorVersionId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredMinorVersionId() != null) {
				return( -1 );
			}
            return( 0 );
        }
        else if (obj instanceof ICFBamSchemaDefByUNameIdxKey ) {
            ICFBamSchemaDefByUNameIdxKey rhs = (ICFBamSchemaDefByUNameIdxKey)obj;
			if (getRequiredMinorVersionId() != null) {
				if (rhs.getRequiredMinorVersionId() != null) {
					cmp = getRequiredMinorVersionId().compareTo( rhs.getRequiredMinorVersionId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredMinorVersionId() != null) {
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
        else if (obj instanceof ICFBamSchemaDefByAuthEMailIdxKey ) {
            ICFBamSchemaDefByAuthEMailIdxKey rhs = (ICFBamSchemaDefByAuthEMailIdxKey)obj;
			if (getRequiredCTenantId() != null) {
				if (rhs.getRequiredCTenantId() != null) {
					cmp = getRequiredCTenantId().compareTo( rhs.getRequiredCTenantId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredCTenantId() != null) {
				return( -1 );
			}
			if (getRequiredAuthorEMail() != null) {
				if (rhs.getRequiredAuthorEMail() != null) {
					cmp = getRequiredAuthorEMail().compareTo( rhs.getRequiredAuthorEMail() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredAuthorEMail() != null) {
				return( -1 );
			}
            return( 0 );
        }
        else if (obj instanceof ICFBamSchemaDefByProjectURLIdxKey ) {
            ICFBamSchemaDefByProjectURLIdxKey rhs = (ICFBamSchemaDefByProjectURLIdxKey)obj;
			if (getRequiredCTenantId() != null) {
				if (rhs.getRequiredCTenantId() != null) {
					cmp = getRequiredCTenantId().compareTo( rhs.getRequiredCTenantId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredCTenantId() != null) {
				return( -1 );
			}
			if (getRequiredProjectURL() != null) {
				if (rhs.getRequiredProjectURL() != null) {
					cmp = getRequiredProjectURL().compareTo( rhs.getRequiredProjectURL() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredProjectURL() != null) {
				return( -1 );
			}
            return( 0 );
        }
        else if (obj instanceof ICFBamSchemaDefByPubURIIdxKey ) {
            ICFBamSchemaDefByPubURIIdxKey rhs = (ICFBamSchemaDefByPubURIIdxKey)obj;
			if (getRequiredCTenantId() != null) {
				if (rhs.getRequiredCTenantId() != null) {
					cmp = getRequiredCTenantId().compareTo( rhs.getRequiredCTenantId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredCTenantId() != null) {
				return( -1 );
			}
			if (getRequiredPublishURI() != null) {
				if (rhs.getRequiredPublishURI() != null) {
					cmp = getRequiredPublishURI().compareTo( rhs.getRequiredPublishURI() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredPublishURI() != null) {
				return( -1 );
			}
            return( 0 );
        }
        else {
        return( super.compareTo(obj) );
        }
    }
	@Override
    public void set( ICFBamScope src ) {
		if( src instanceof ICFBamSchemaDef ) {
			setSchemaDef( (ICFBamSchemaDef)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamJpaSchemaDef" );
		}
    }

	@Override
    public void setSchemaDef( ICFBamSchemaDef src ) {
        super.setScope( src );
		setRequiredCTenantId( src.getRequiredCTenantId() );
		setRequiredMinorVersionId( src.getRequiredMinorVersionId() );
		setRequiredName( src.getRequiredName() );
		setOptionalDbName( src.getOptionalDbName() );
		setOptionalShortName( src.getOptionalShortName() );
		setOptionalLabel( src.getOptionalLabel() );
		setOptionalShortDescription( src.getOptionalShortDescription() );
		setOptionalDescription( src.getOptionalDescription() );
		setRequiredCopyrightPeriod( src.getRequiredCopyrightPeriod() );
		setRequiredCopyrightHolder( src.getRequiredCopyrightHolder() );
		setRequiredAuthorEMail( src.getRequiredAuthorEMail() );
		setRequiredProjectURL( src.getRequiredProjectURL() );
		setRequiredPublishURI( src.getRequiredPublishURI() );
    }

	@Override
    public void set( ICFBamScopeH src ) {
		if( src instanceof ICFBamSchemaDefH ) {
			setSchemaDef( (ICFBamSchemaDefH)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamSchemaDefH" );
		}
    }

	@Override
    public void setSchemaDef( ICFBamSchemaDefH src ) {
        super.setScope( src );
		setRequiredCTenantId( src.getRequiredCTenantId() );
		setRequiredMinorVersionId( src.getRequiredMinorVersionId() );
		setRequiredName( src.getRequiredName() );
		setOptionalDbName( src.getOptionalDbName() );
		setOptionalShortName( src.getOptionalShortName() );
		setOptionalLabel( src.getOptionalLabel() );
		setOptionalShortDescription( src.getOptionalShortDescription() );
		setOptionalDescription( src.getOptionalDescription() );
		setRequiredCopyrightPeriod( src.getRequiredCopyrightPeriod() );
		setRequiredCopyrightHolder( src.getRequiredCopyrightHolder() );
		setRequiredAuthorEMail( src.getRequiredAuthorEMail() );
		setRequiredProjectURL( src.getRequiredProjectURL() );
		setRequiredPublishURI( src.getRequiredPublishURI() );
    }

    public String getXmlAttrFragment() {
        String ret = super.getXmlAttrFragment() 
			+ " RequiredCTenantId=" + "\"" + getRequiredCTenantId().toString() + "\""
			+ " RequiredMinorVersionId=" + "\"" + getRequiredMinorVersionId().toString() + "\""
			+ " RequiredName=" + "\"" + StringEscapeUtils.escapeXml11( getRequiredName() ) + "\""
			+ " OptionalDbName=" + ( ( getOptionalDbName() == null ) ? "null" : "\"" + StringEscapeUtils.escapeXml11( getOptionalDbName() ) + "\"" )
			+ " OptionalShortName=" + ( ( getOptionalShortName() == null ) ? "null" : "\"" + StringEscapeUtils.escapeXml11( getOptionalShortName() ) + "\"" )
			+ " OptionalLabel=" + ( ( getOptionalLabel() == null ) ? "null" : "\"" + StringEscapeUtils.escapeXml11( getOptionalLabel() ) + "\"" )
			+ " OptionalShortDescription=" + ( ( getOptionalShortDescription() == null ) ? "null" : "\"" + StringEscapeUtils.escapeXml11( getOptionalShortDescription() ) + "\"" )
			+ " OptionalDescription=" + ( ( getOptionalDescription() == null ) ? "null" : "\"" + StringEscapeUtils.escapeXml11( getOptionalDescription() ) + "\"" )
			+ " RequiredCopyrightPeriod=" + "\"" + StringEscapeUtils.escapeXml11( getRequiredCopyrightPeriod() ) + "\""
			+ " RequiredCopyrightHolder=" + "\"" + StringEscapeUtils.escapeXml11( getRequiredCopyrightHolder() ) + "\""
			+ " RequiredAuthorEMail=" + "\"" + StringEscapeUtils.escapeXml11( getRequiredAuthorEMail() ) + "\""
			+ " RequiredProjectURL=" + "\"" + StringEscapeUtils.escapeXml11( getRequiredProjectURL() ) + "\""
			+ " RequiredPublishURI=" + "\"" + StringEscapeUtils.escapeXml11( getRequiredPublishURI() ) + "\"";
        return( ret );
    }

    public String toString() {
        String ret = "<CFBamJpaSchemaDefH" + getXmlAttrFragment() + "/>";
        return( ret );
    }
}
