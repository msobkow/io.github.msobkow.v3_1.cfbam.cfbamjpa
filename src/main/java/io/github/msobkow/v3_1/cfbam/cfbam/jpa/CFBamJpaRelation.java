// Description: Java 25 JPA implementation of a Relation entity definition object.

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

@Entity
@Table(
	name = "reldef", schema = "CFBam31",
	indexes = {
		@Index(name = "RelationIdIdx", columnList = "Id", unique = true),
		@Index(name = "RelationUNameIdx", columnList = "TableId, safe_name", unique = true),
		@Index(name = "RelationTableIdx", columnList = "TableId", unique = false),
		@Index(name = "RelationDefSchemaDefIdx", columnList = "defschid", unique = false),
		@Index(name = "RelationFromKeyIdx", columnList = "FromIndexId", unique = false),
		@Index(name = "RelationToTblIdx", columnList = "ToTableId", unique = false),
		@Index(name = "RelationToKeyIdx", columnList = "ToIndexId", unique = false),
		@Index(name = "RelationNarrowedIdx", columnList = "NarrowedId", unique = false)
	}
)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("43061")
@Transactional(Transactional.TxType.SUPPORTS)
@PersistenceContext(unitName = "CFBamPU")
public class CFBamJpaRelation extends CFBamJpaScope
	implements ICFBamRelation
{
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn( name="defschid" )
	protected CFBamJpaSchemaDef optionalLookupDefSchema;
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn( name="TableId" )
	protected CFBamJpaTable requiredContainerFromTable;
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn( name="FromIndexId" )
	protected CFBamJpaIndex requiredLookupFromIndex;
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn( name="ToTableId" )
	protected CFBamJpaTable requiredLookupToTable;
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn( name="ToIndexId" )
	protected CFBamJpaIndex requiredLookupToIndex;
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn( name="NarrowedId" )
	protected CFBamJpaRelation optionalLookupNarrowed;
	@OneToMany(fetch=FetchType.LAZY, mappedBy="requiredContainerRelation")
	protected Set<CFBamJpaRelationCol> optionalComponentsColumns;
	@OneToMany(fetch=FetchType.LAZY, mappedBy="requiredContainerContRelation")
	protected Set<CFBamJpaPopTopDep> optionalComponentsPopDep;

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
	@Column( name="RelationType", nullable=false )
	protected ICFBamSchema.RelationTypeEnum requiredRelationType;
	@Column( name="DbName", nullable=true, length=32 )
	protected String optionalDbName;
	@Column( name="Suffix", nullable=true, length=16 )
	protected String optionalSuffix;
	@Column( name="IsRequired", nullable=false )
	protected boolean requiredIsRequired;
	@Column( name="IsXsdContainer", nullable=false )
	protected boolean requiredIsXsdContainer;
	@Column( name="IsLateResolver", nullable=false )
	protected boolean requiredIsLateResolver;
	@Column( name="AllowAddendum", nullable=false )
	protected boolean requiredAllowAddendum;

	public CFBamJpaRelation() {
		super();
		requiredName = ICFBamRelation.NAME_INIT_VALUE;
		optionalShortName = null;
		optionalLabel = null;
		optionalShortDescription = null;
		optionalDescription = null;
		requiredRelationType = ICFBamRelation.RELATIONTYPE_INIT_VALUE;
		optionalDbName = null;
		optionalSuffix = null;
		requiredIsRequired = ICFBamRelation.ISREQUIRED_INIT_VALUE;
		requiredIsXsdContainer = ICFBamRelation.ISXSDCONTAINER_INIT_VALUE;
		requiredIsLateResolver = ICFBamRelation.ISLATERESOLVER_INIT_VALUE;
		requiredAllowAddendum = ICFBamRelation.ALLOWADDENDUM_INIT_VALUE;
	}

	@Override
	public int getClassCode() {
		return( ICFBamRelation.CLASS_CODE );
	}

	@Override
	public ICFBamSchemaDef getOptionalLookupDefSchema() {
		return( optionalLookupDefSchema );
	}
	@Override
	public void setOptionalLookupDefSchema(ICFBamSchemaDef argObj) {
		if(argObj == null) {
			optionalLookupDefSchema = null;
		}
		else if (argObj instanceof CFBamJpaSchemaDef) {
			optionalLookupDefSchema = (CFBamJpaSchemaDef)argObj;
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "setLookupDefSchema", "argObj", argObj, "CFBamJpaSchemaDef");
		}
	}

	@Override
	public void setOptionalLookupDefSchema(CFLibDbKeyHash256 argDefSchemaId) {
		ICFBamSchema targetBackingSchema = ICFBamSchema.getBackingCFBam();
		if (targetBackingSchema == null) {
			throw new CFLibNullArgumentException(getClass(), "setOptionalLookupDefSchema", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamSchemaDefTable targetTable = targetBackingSchema.getTableSchemaDef();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setOptionalLookupDefSchema", 0, "ICFBamSchema.getBackingCFBam().getTableSchemaDef()");
		}
		ICFBamSchemaDef targetRec = targetTable.readDerived(null, argDefSchemaId);
		setOptionalLookupDefSchema(targetRec);
	}

	@Override
	public ICFBamTable getRequiredContainerFromTable() {
		return( requiredContainerFromTable );
	}
	@Override
	public void setRequiredContainerFromTable(ICFBamTable argObj) {
		if(argObj == null) {
			throw new CFLibNullArgumentException(getClass(), "setContainerFromTable", 1, "argObj");
		}
		else if (argObj instanceof CFBamJpaTable) {
			requiredContainerFromTable = (CFBamJpaTable)argObj;
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "setContainerFromTable", "argObj", argObj, "CFBamJpaTable");
		}
	}

	@Override
	public void setRequiredContainerFromTable(CFLibDbKeyHash256 argTableId) {
		ICFBamSchema targetBackingSchema = ICFBamSchema.getBackingCFBam();
		if (targetBackingSchema == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerFromTable", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamTableTable targetTable = targetBackingSchema.getTableTable();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerFromTable", 0, "ICFBamSchema.getBackingCFBam().getTableTable()");
		}
		ICFBamTable targetRec = targetTable.readDerived(null, argTableId);
		setRequiredContainerFromTable(targetRec);
	}

	@Override
	public ICFBamIndex getRequiredLookupFromIndex() {
		return( requiredLookupFromIndex );
	}
	@Override
	public void setRequiredLookupFromIndex(ICFBamIndex argObj) {
		if(argObj == null) {
			throw new CFLibNullArgumentException(getClass(), "setLookupFromIndex", 1, "argObj");
		}
		else if (argObj instanceof CFBamJpaIndex) {
			requiredLookupFromIndex = (CFBamJpaIndex)argObj;
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "setLookupFromIndex", "argObj", argObj, "CFBamJpaIndex");
		}
	}

	@Override
	public void setRequiredLookupFromIndex(CFLibDbKeyHash256 argFromIndexId) {
		ICFBamSchema targetBackingSchema = ICFBamSchema.getBackingCFBam();
		if (targetBackingSchema == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredLookupFromIndex", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamIndexTable targetTable = targetBackingSchema.getTableIndex();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredLookupFromIndex", 0, "ICFBamSchema.getBackingCFBam().getTableIndex()");
		}
		ICFBamIndex targetRec = targetTable.readDerived(null, argFromIndexId);
		setRequiredLookupFromIndex(targetRec);
	}

	@Override
	public ICFBamTable getRequiredLookupToTable() {
		return( requiredLookupToTable );
	}
	@Override
	public void setRequiredLookupToTable(ICFBamTable argObj) {
		if(argObj == null) {
			throw new CFLibNullArgumentException(getClass(), "setLookupToTable", 1, "argObj");
		}
		else if (argObj instanceof CFBamJpaTable) {
			requiredLookupToTable = (CFBamJpaTable)argObj;
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "setLookupToTable", "argObj", argObj, "CFBamJpaTable");
		}
	}

	@Override
	public void setRequiredLookupToTable(CFLibDbKeyHash256 argToTableId) {
		ICFBamSchema targetBackingSchema = ICFBamSchema.getBackingCFBam();
		if (targetBackingSchema == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredLookupToTable", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamTableTable targetTable = targetBackingSchema.getTableTable();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredLookupToTable", 0, "ICFBamSchema.getBackingCFBam().getTableTable()");
		}
		ICFBamTable targetRec = targetTable.readDerived(null, argToTableId);
		setRequiredLookupToTable(targetRec);
	}

	@Override
	public ICFBamIndex getRequiredLookupToIndex() {
		return( requiredLookupToIndex );
	}
	@Override
	public void setRequiredLookupToIndex(ICFBamIndex argObj) {
		if(argObj == null) {
			throw new CFLibNullArgumentException(getClass(), "setLookupToIndex", 1, "argObj");
		}
		else if (argObj instanceof CFBamJpaIndex) {
			requiredLookupToIndex = (CFBamJpaIndex)argObj;
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "setLookupToIndex", "argObj", argObj, "CFBamJpaIndex");
		}
	}

	@Override
	public void setRequiredLookupToIndex(CFLibDbKeyHash256 argToIndexId) {
		ICFBamSchema targetBackingSchema = ICFBamSchema.getBackingCFBam();
		if (targetBackingSchema == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredLookupToIndex", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamIndexTable targetTable = targetBackingSchema.getTableIndex();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredLookupToIndex", 0, "ICFBamSchema.getBackingCFBam().getTableIndex()");
		}
		ICFBamIndex targetRec = targetTable.readDerived(null, argToIndexId);
		setRequiredLookupToIndex(targetRec);
	}

	@Override
	public ICFBamRelation getOptionalLookupNarrowed() {
		return( optionalLookupNarrowed );
	}
	@Override
	public void setOptionalLookupNarrowed(ICFBamRelation argObj) {
		if(argObj == null) {
			optionalLookupNarrowed = null;
		}
		else if (argObj instanceof CFBamJpaRelation) {
			optionalLookupNarrowed = (CFBamJpaRelation)argObj;
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "setLookupNarrowed", "argObj", argObj, "CFBamJpaRelation");
		}
	}

	@Override
	public void setOptionalLookupNarrowed(CFLibDbKeyHash256 argNarrowedId) {
		ICFBamSchema targetBackingSchema = ICFBamSchema.getBackingCFBam();
		if (targetBackingSchema == null) {
			throw new CFLibNullArgumentException(getClass(), "setOptionalLookupNarrowed", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamRelationTable targetTable = targetBackingSchema.getTableRelation();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setOptionalLookupNarrowed", 0, "ICFBamSchema.getBackingCFBam().getTableRelation()");
		}
		ICFBamRelation targetRec = targetTable.readDerived(null, argNarrowedId);
		setOptionalLookupNarrowed(targetRec);
	}

	@Override
	public List<ICFBamRelationCol> getOptionalComponentsColumns() {
		List<ICFBamRelationCol> retlist = new ArrayList<>(optionalComponentsColumns.size());
		for (CFBamJpaRelationCol cur: optionalComponentsColumns) {
			retlist.add(cur);
		}
		return( retlist );
	}
	@Override
	public List<ICFBamPopTopDep> getOptionalComponentsPopDep() {
		List<ICFBamPopTopDep> retlist = new ArrayList<>(optionalComponentsPopDep.size());
		for (CFBamJpaPopTopDep cur: optionalComponentsPopDep) {
			retlist.add(cur);
		}
		return( retlist );
	}
	@Override
	public CFLibDbKeyHash256 getRequiredTableId() {
		ICFBamTable result = getRequiredContainerFromTable();
		if (result != null) {
			return result.getRequiredId();
		}
		else {
			return( ICFBamTable.ID_INIT_VALUE );
		}
	}

	@Override
	public CFLibDbKeyHash256 getOptionalDefSchemaId() {
		ICFBamSchemaDef result = getOptionalLookupDefSchema();
		if (result != null) {
			return result.getRequiredId();
		}
		else {
			return null;
		}
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
	public ICFBamSchema.RelationTypeEnum getRequiredRelationType() {
		return( requiredRelationType );
	}

	@Override
	public void setRequiredRelationType( ICFBamSchema.RelationTypeEnum value ) {
		if( value == null ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredRelationType",
				1,
				"value" );
		}
		requiredRelationType = value;
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
	public String getOptionalSuffix() {
		return( optionalSuffix );
	}

	@Override
	public void setOptionalSuffix( String value ) {
		if( value != null && value.length() > 16 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setOptionalSuffix",
				1,
				"value.length()",
				value.length(),
				16 );
		}
		optionalSuffix = value;
	}

	@Override
	public CFLibDbKeyHash256 getRequiredFromIndexId() {
		ICFBamIndex result = getRequiredLookupFromIndex();
		if (result != null) {
			return result.getRequiredId();
		}
		else {
			return( ICFBamIndex.ID_INIT_VALUE );
		}
	}

	@Override
	public CFLibDbKeyHash256 getRequiredToTableId() {
		ICFBamTable result = getRequiredLookupToTable();
		if (result != null) {
			return result.getRequiredId();
		}
		else {
			return( ICFBamTable.ID_INIT_VALUE );
		}
	}

	@Override
	public CFLibDbKeyHash256 getRequiredToIndexId() {
		ICFBamIndex result = getRequiredLookupToIndex();
		if (result != null) {
			return result.getRequiredId();
		}
		else {
			return( ICFBamIndex.ID_INIT_VALUE );
		}
	}

	@Override
	public boolean getRequiredIsRequired() {
		return( requiredIsRequired );
	}

	@Override
	public void setRequiredIsRequired( boolean value ) {
		requiredIsRequired = value;
	}

	@Override
	public boolean getRequiredIsXsdContainer() {
		return( requiredIsXsdContainer );
	}

	@Override
	public void setRequiredIsXsdContainer( boolean value ) {
		requiredIsXsdContainer = value;
	}

	@Override
	public boolean getRequiredIsLateResolver() {
		return( requiredIsLateResolver );
	}

	@Override
	public void setRequiredIsLateResolver( boolean value ) {
		requiredIsLateResolver = value;
	}

	@Override
	public boolean getRequiredAllowAddendum() {
		return( requiredAllowAddendum );
	}

	@Override
	public void setRequiredAllowAddendum( boolean value ) {
		requiredAllowAddendum = value;
	}

	@Override
	public CFLibDbKeyHash256 getOptionalNarrowedId() {
		ICFBamRelation result = getOptionalLookupNarrowed();
		if (result != null) {
			return result.getRequiredId();
		}
		else {
			return null;
		}
	}

	@Override
	public boolean equals( Object obj ) {
		if (obj == null) {
			return( false );
		}
		else if (obj instanceof ICFBamRelation) {
			if (!super.equals(obj)) {
				return( false );
			}
			ICFBamRelation rhs = (ICFBamRelation)obj;
			if( getRequiredTableId() != null ) {
				if( rhs.getRequiredTableId() != null ) {
					if( ! getRequiredTableId().equals( rhs.getRequiredTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredTableId() != null ) {
					return( false );
				}
			}
			if( getOptionalDefSchemaId() != null ) {
				if( rhs.getOptionalDefSchemaId() != null ) {
					if( ! getOptionalDefSchemaId().equals( rhs.getOptionalDefSchemaId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDefSchemaId() != null ) {
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
			if( getRequiredRelationType() != null ) {
				if( rhs.getRequiredRelationType() != null ) {
					if( ! getRequiredRelationType().equals( rhs.getRequiredRelationType() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredRelationType() != null ) {
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
			if( getOptionalSuffix() != null ) {
				if( rhs.getOptionalSuffix() != null ) {
					if( ! getOptionalSuffix().equals( rhs.getOptionalSuffix() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalSuffix() != null ) {
					return( false );
				}
			}
			if( getRequiredFromIndexId() != null ) {
				if( rhs.getRequiredFromIndexId() != null ) {
					if( ! getRequiredFromIndexId().equals( rhs.getRequiredFromIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredFromIndexId() != null ) {
					return( false );
				}
			}
			if( getRequiredToTableId() != null ) {
				if( rhs.getRequiredToTableId() != null ) {
					if( ! getRequiredToTableId().equals( rhs.getRequiredToTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredToTableId() != null ) {
					return( false );
				}
			}
			if( getRequiredToIndexId() != null ) {
				if( rhs.getRequiredToIndexId() != null ) {
					if( ! getRequiredToIndexId().equals( rhs.getRequiredToIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredToIndexId() != null ) {
					return( false );
				}
			}
			if( getRequiredIsRequired() != rhs.getRequiredIsRequired() ) {
				return( false );
			}
			if( getRequiredIsXsdContainer() != rhs.getRequiredIsXsdContainer() ) {
				return( false );
			}
			if( getRequiredIsLateResolver() != rhs.getRequiredIsLateResolver() ) {
				return( false );
			}
			if( getRequiredAllowAddendum() != rhs.getRequiredAllowAddendum() ) {
				return( false );
			}
			if( getOptionalNarrowedId() != null ) {
				if( rhs.getOptionalNarrowedId() != null ) {
					if( ! getOptionalNarrowedId().equals( rhs.getOptionalNarrowedId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalNarrowedId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if (obj instanceof ICFBamRelationH) {
			if (!super.equals(obj)) {
				return( false );
			}
			ICFBamRelationH rhs = (ICFBamRelationH)obj;
			if( getRequiredTableId() != null ) {
				if( rhs.getRequiredTableId() != null ) {
					if( ! getRequiredTableId().equals( rhs.getRequiredTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredTableId() != null ) {
					return( false );
				}
			}
			if( getOptionalDefSchemaId() != null ) {
				if( rhs.getOptionalDefSchemaId() != null ) {
					if( ! getOptionalDefSchemaId().equals( rhs.getOptionalDefSchemaId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDefSchemaId() != null ) {
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
			if( getRequiredRelationType() != null ) {
				if( rhs.getRequiredRelationType() != null ) {
					if( ! getRequiredRelationType().equals( rhs.getRequiredRelationType() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredRelationType() != null ) {
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
			if( getOptionalSuffix() != null ) {
				if( rhs.getOptionalSuffix() != null ) {
					if( ! getOptionalSuffix().equals( rhs.getOptionalSuffix() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalSuffix() != null ) {
					return( false );
				}
			}
			if( getRequiredFromIndexId() != null ) {
				if( rhs.getRequiredFromIndexId() != null ) {
					if( ! getRequiredFromIndexId().equals( rhs.getRequiredFromIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredFromIndexId() != null ) {
					return( false );
				}
			}
			if( getRequiredToTableId() != null ) {
				if( rhs.getRequiredToTableId() != null ) {
					if( ! getRequiredToTableId().equals( rhs.getRequiredToTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredToTableId() != null ) {
					return( false );
				}
			}
			if( getRequiredToIndexId() != null ) {
				if( rhs.getRequiredToIndexId() != null ) {
					if( ! getRequiredToIndexId().equals( rhs.getRequiredToIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredToIndexId() != null ) {
					return( false );
				}
			}
			if( getRequiredIsRequired() != rhs.getRequiredIsRequired() ) {
				return( false );
			}
			if( getRequiredIsXsdContainer() != rhs.getRequiredIsXsdContainer() ) {
				return( false );
			}
			if( getRequiredIsLateResolver() != rhs.getRequiredIsLateResolver() ) {
				return( false );
			}
			if( getRequiredAllowAddendum() != rhs.getRequiredAllowAddendum() ) {
				return( false );
			}
			if( getOptionalNarrowedId() != null ) {
				if( rhs.getOptionalNarrowedId() != null ) {
					if( ! getOptionalNarrowedId().equals( rhs.getOptionalNarrowedId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalNarrowedId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if (obj instanceof ICFBamScopeHPKey) {
			return( super.equals(obj) );
		}
		else if (obj instanceof ICFBamRelationByUNameIdxKey) {
			ICFBamRelationByUNameIdxKey rhs = (ICFBamRelationByUNameIdxKey)obj;
			if( getRequiredTableId() != null ) {
				if( rhs.getRequiredTableId() != null ) {
					if( ! getRequiredTableId().equals( rhs.getRequiredTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredTableId() != null ) {
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
		else if (obj instanceof ICFBamRelationByRelTableIdxKey) {
			ICFBamRelationByRelTableIdxKey rhs = (ICFBamRelationByRelTableIdxKey)obj;
			if( getRequiredTableId() != null ) {
				if( rhs.getRequiredTableId() != null ) {
					if( ! getRequiredTableId().equals( rhs.getRequiredTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredTableId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if (obj instanceof ICFBamRelationByDefSchemaIdxKey) {
			ICFBamRelationByDefSchemaIdxKey rhs = (ICFBamRelationByDefSchemaIdxKey)obj;
			if( getOptionalDefSchemaId() != null ) {
				if( rhs.getOptionalDefSchemaId() != null ) {
					if( ! getOptionalDefSchemaId().equals( rhs.getOptionalDefSchemaId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDefSchemaId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if (obj instanceof ICFBamRelationByFromKeyIdxKey) {
			ICFBamRelationByFromKeyIdxKey rhs = (ICFBamRelationByFromKeyIdxKey)obj;
			if( getRequiredFromIndexId() != null ) {
				if( rhs.getRequiredFromIndexId() != null ) {
					if( ! getRequiredFromIndexId().equals( rhs.getRequiredFromIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredFromIndexId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if (obj instanceof ICFBamRelationByToTblIdxKey) {
			ICFBamRelationByToTblIdxKey rhs = (ICFBamRelationByToTblIdxKey)obj;
			if( getRequiredToTableId() != null ) {
				if( rhs.getRequiredToTableId() != null ) {
					if( ! getRequiredToTableId().equals( rhs.getRequiredToTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredToTableId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if (obj instanceof ICFBamRelationByToKeyIdxKey) {
			ICFBamRelationByToKeyIdxKey rhs = (ICFBamRelationByToKeyIdxKey)obj;
			if( getRequiredToIndexId() != null ) {
				if( rhs.getRequiredToIndexId() != null ) {
					if( ! getRequiredToIndexId().equals( rhs.getRequiredToIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredToIndexId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if (obj instanceof ICFBamRelationByNarrowedIdxKey) {
			ICFBamRelationByNarrowedIdxKey rhs = (ICFBamRelationByNarrowedIdxKey)obj;
			if( getOptionalNarrowedId() != null ) {
				if( rhs.getOptionalNarrowedId() != null ) {
					if( ! getOptionalNarrowedId().equals( rhs.getOptionalNarrowedId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalNarrowedId() != null ) {
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
		hashCode = hashCode + getRequiredTableId().hashCode();
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
		hashCode = ( hashCode * 0x10000 ) + getRequiredRelationType().ordinal();
		if( getOptionalDbName() != null ) {
			hashCode = hashCode + getOptionalDbName().hashCode();
		}
		if( getOptionalSuffix() != null ) {
			hashCode = hashCode + getOptionalSuffix().hashCode();
		}
		hashCode = hashCode + getRequiredFromIndexId().hashCode();
		hashCode = hashCode + getRequiredToTableId().hashCode();
		hashCode = hashCode + getRequiredToIndexId().hashCode();
		if( getRequiredIsRequired() ) {
			hashCode = ( hashCode * 2 ) + 1;
		}
		else {
			hashCode = hashCode * 2;
		}
		if( getRequiredIsXsdContainer() ) {
			hashCode = ( hashCode * 2 ) + 1;
		}
		else {
			hashCode = hashCode * 2;
		}
		if( getRequiredIsLateResolver() ) {
			hashCode = ( hashCode * 2 ) + 1;
		}
		else {
			hashCode = hashCode * 2;
		}
		if( getRequiredAllowAddendum() ) {
			hashCode = ( hashCode * 2 ) + 1;
		}
		else {
			hashCode = hashCode * 2;
		}
		if( getOptionalNarrowedId() != null ) {
			hashCode = hashCode + getOptionalNarrowedId().hashCode();
		}
		return( hashCode & 0x7fffffff );
	}

	@Override
	public int compareTo( Object obj ) {
		int cmp;
		if (obj == null) {
			return( 1 );
		}
		else if (obj instanceof ICFBamRelation) {
			cmp = super.compareTo(obj);
			if (cmp != 0) {
				return( cmp );
			}
			ICFBamRelation rhs = (ICFBamRelation)obj;
			if (getRequiredTableId() != null) {
				if (rhs.getRequiredTableId() != null) {
					cmp = getRequiredTableId().compareTo( rhs.getRequiredTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredTableId() != null) {
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
			if (getRequiredRelationType() != null) {
				if (rhs.getRequiredRelationType() != null) {
					cmp = getRequiredRelationType().compareTo( rhs.getRequiredRelationType() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredRelationType() != null) {
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
			if( getOptionalSuffix() != null ) {
				if( rhs.getOptionalSuffix() != null ) {
					cmp = getOptionalSuffix().compareTo( rhs.getOptionalSuffix() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalSuffix() != null ) {
					return( -1 );
				}
			}
			if (getRequiredFromIndexId() != null) {
				if (rhs.getRequiredFromIndexId() != null) {
					cmp = getRequiredFromIndexId().compareTo( rhs.getRequiredFromIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredFromIndexId() != null) {
				return( -1 );
			}
			if (getRequiredToTableId() != null) {
				if (rhs.getRequiredToTableId() != null) {
					cmp = getRequiredToTableId().compareTo( rhs.getRequiredToTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredToTableId() != null) {
				return( -1 );
			}
			if (getRequiredToIndexId() != null) {
				if (rhs.getRequiredToIndexId() != null) {
					cmp = getRequiredToIndexId().compareTo( rhs.getRequiredToIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredToIndexId() != null) {
				return( -1 );
			}
			if( getRequiredIsRequired() ) {
				if( ! rhs.getRequiredIsRequired() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredIsRequired() ) {
					return( -1 );
				}
			}
			if( getRequiredIsXsdContainer() ) {
				if( ! rhs.getRequiredIsXsdContainer() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredIsXsdContainer() ) {
					return( -1 );
				}
			}
			if( getRequiredIsLateResolver() ) {
				if( ! rhs.getRequiredIsLateResolver() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredIsLateResolver() ) {
					return( -1 );
				}
			}
			if( getRequiredAllowAddendum() ) {
				if( ! rhs.getRequiredAllowAddendum() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredAllowAddendum() ) {
					return( -1 );
				}
			}
			if( getOptionalNarrowedId() != null ) {
				if( rhs.getOptionalNarrowedId() != null ) {
					cmp = getOptionalNarrowedId().compareTo( rhs.getOptionalNarrowedId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalNarrowedId() != null ) {
					return( -1 );
				}
			}
			return( 0 );
		}
		else if( obj instanceof ICFBamRelationH ) {
			cmp = super.compareTo(obj);
			if (cmp != 0) {
				return( cmp );
			}
			ICFBamRelationH rhs = (ICFBamRelationH)obj;
			if (getRequiredTableId() != null) {
				if (rhs.getRequiredTableId() != null) {
					cmp = getRequiredTableId().compareTo( rhs.getRequiredTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredTableId() != null) {
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
			if (getRequiredRelationType() != null) {
				if (rhs.getRequiredRelationType() != null) {
					cmp = getRequiredRelationType().compareTo( rhs.getRequiredRelationType() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredRelationType() != null) {
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
			if( getOptionalSuffix() != null ) {
				if( rhs.getOptionalSuffix() != null ) {
					cmp = getOptionalSuffix().compareTo( rhs.getOptionalSuffix() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalSuffix() != null ) {
					return( -1 );
				}
			}
			if (getRequiredFromIndexId() != null) {
				if (rhs.getRequiredFromIndexId() != null) {
					cmp = getRequiredFromIndexId().compareTo( rhs.getRequiredFromIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredFromIndexId() != null) {
				return( -1 );
			}
			if (getRequiredToTableId() != null) {
				if (rhs.getRequiredToTableId() != null) {
					cmp = getRequiredToTableId().compareTo( rhs.getRequiredToTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredToTableId() != null) {
				return( -1 );
			}
			if (getRequiredToIndexId() != null) {
				if (rhs.getRequiredToIndexId() != null) {
					cmp = getRequiredToIndexId().compareTo( rhs.getRequiredToIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredToIndexId() != null) {
				return( -1 );
			}
			if( getRequiredIsRequired() ) {
				if( ! rhs.getRequiredIsRequired() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredIsRequired() ) {
					return( -1 );
				}
			}
			if( getRequiredIsXsdContainer() ) {
				if( ! rhs.getRequiredIsXsdContainer() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredIsXsdContainer() ) {
					return( -1 );
				}
			}
			if( getRequiredIsLateResolver() ) {
				if( ! rhs.getRequiredIsLateResolver() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredIsLateResolver() ) {
					return( -1 );
				}
			}
			if( getRequiredAllowAddendum() ) {
				if( ! rhs.getRequiredAllowAddendum() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredAllowAddendum() ) {
					return( -1 );
				}
			}
			if( getOptionalNarrowedId() != null ) {
				if( rhs.getOptionalNarrowedId() != null ) {
					cmp = getOptionalNarrowedId().compareTo( rhs.getOptionalNarrowedId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalNarrowedId() != null ) {
					return( -1 );
				}
			}
			return( 0 );
		}
		else if (obj instanceof ICFBamRelationByUNameIdxKey) {
			ICFBamRelationByUNameIdxKey rhs = (ICFBamRelationByUNameIdxKey)obj;
			if (getRequiredTableId() != null) {
				if (rhs.getRequiredTableId() != null) {
					cmp = getRequiredTableId().compareTo( rhs.getRequiredTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredTableId() != null) {
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
		else if (obj instanceof ICFBamRelationByRelTableIdxKey) {
			ICFBamRelationByRelTableIdxKey rhs = (ICFBamRelationByRelTableIdxKey)obj;
			if (getRequiredTableId() != null) {
				if (rhs.getRequiredTableId() != null) {
					cmp = getRequiredTableId().compareTo( rhs.getRequiredTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredTableId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if (obj instanceof ICFBamRelationByDefSchemaIdxKey) {
			ICFBamRelationByDefSchemaIdxKey rhs = (ICFBamRelationByDefSchemaIdxKey)obj;
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
		else if (obj instanceof ICFBamRelationByFromKeyIdxKey) {
			ICFBamRelationByFromKeyIdxKey rhs = (ICFBamRelationByFromKeyIdxKey)obj;
			if (getRequiredFromIndexId() != null) {
				if (rhs.getRequiredFromIndexId() != null) {
					cmp = getRequiredFromIndexId().compareTo( rhs.getRequiredFromIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredFromIndexId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if (obj instanceof ICFBamRelationByToTblIdxKey) {
			ICFBamRelationByToTblIdxKey rhs = (ICFBamRelationByToTblIdxKey)obj;
			if (getRequiredToTableId() != null) {
				if (rhs.getRequiredToTableId() != null) {
					cmp = getRequiredToTableId().compareTo( rhs.getRequiredToTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredToTableId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if (obj instanceof ICFBamRelationByToKeyIdxKey) {
			ICFBamRelationByToKeyIdxKey rhs = (ICFBamRelationByToKeyIdxKey)obj;
			if (getRequiredToIndexId() != null) {
				if (rhs.getRequiredToIndexId() != null) {
					cmp = getRequiredToIndexId().compareTo( rhs.getRequiredToIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredToIndexId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if (obj instanceof ICFBamRelationByNarrowedIdxKey) {
			ICFBamRelationByNarrowedIdxKey rhs = (ICFBamRelationByNarrowedIdxKey)obj;
			if( getOptionalNarrowedId() != null ) {
				if( rhs.getOptionalNarrowedId() != null ) {
					cmp = getOptionalNarrowedId().compareTo( rhs.getOptionalNarrowedId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalNarrowedId() != null ) {
					return( -1 );
				}
			}
			return( 0 );
		}
		else {
			cmp = super.compareTo(obj);
			return( cmp );
		}
	}

	@Override
	public void set( ICFBamScope src ) {
		if( src instanceof ICFBamRelation ) {
			setRelation( (ICFBamRelation)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamJpaRelation" );
		}
	}

	@Override
	public void setRelation( ICFBamRelation src ) {
		super.setScope( src );
		setOptionalLookupDefSchema(src.getOptionalLookupDefSchema());
		setRequiredContainerFromTable(src.getRequiredContainerFromTable());
		setRequiredLookupFromIndex(src.getRequiredLookupFromIndex());
		setRequiredLookupToTable(src.getRequiredLookupToTable());
		setRequiredLookupToIndex(src.getRequiredLookupToIndex());
		setOptionalLookupNarrowed(src.getOptionalLookupNarrowed());
		setRequiredName(src.getRequiredName());
		setOptionalShortName(src.getOptionalShortName());
		setOptionalLabel(src.getOptionalLabel());
		setOptionalShortDescription(src.getOptionalShortDescription());
		setOptionalDescription(src.getOptionalDescription());
		setRequiredRelationType(src.getRequiredRelationType());
		setOptionalDbName(src.getOptionalDbName());
		setOptionalSuffix(src.getOptionalSuffix());
		setRequiredIsRequired(src.getRequiredIsRequired());
		setRequiredIsXsdContainer(src.getRequiredIsXsdContainer());
		setRequiredIsLateResolver(src.getRequiredIsLateResolver());
		setRequiredAllowAddendum(src.getRequiredAllowAddendum());
	}

	@Override
	public void set( ICFBamScopeH src ) {
		if( src instanceof ICFBamRelationH ) {
			setRelation( (ICFBamRelationH)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamRelationH" );
		}
	}

	@Override
	public void setRelation( ICFBamRelationH src ) {
		super.setScope( src );
		setOptionalLookupDefSchema(src.getOptionalDefSchemaId());
		setRequiredContainerFromTable(src.getRequiredTableId());
		setRequiredLookupFromIndex(src.getRequiredFromIndexId());
		setRequiredLookupToTable(src.getRequiredToTableId());
		setRequiredLookupToIndex(src.getRequiredToIndexId());
		setOptionalLookupNarrowed(src.getOptionalNarrowedId());
		setRequiredName(src.getRequiredName());
		setOptionalShortName(src.getOptionalShortName());
		setOptionalLabel(src.getOptionalLabel());
		setOptionalShortDescription(src.getOptionalShortDescription());
		setOptionalDescription(src.getOptionalDescription());
		setRequiredRelationType(src.getRequiredRelationType());
		setOptionalDbName(src.getOptionalDbName());
		setOptionalSuffix(src.getOptionalSuffix());
		setRequiredIsRequired(src.getRequiredIsRequired());
		setRequiredIsXsdContainer(src.getRequiredIsXsdContainer());
		setRequiredIsLateResolver(src.getRequiredIsLateResolver());
		setRequiredAllowAddendum(src.getRequiredAllowAddendum());
	}

	@Override
	public String getXmlAttrFragment() {
		String ret = super.getXmlAttrFragment() 
			+ " RequiredTableId=" + "\"" + getRequiredTableId().toString() + "\""
			+ " RequiredId=" + "\"" + getRequiredId().toString() + "\""
			+ " OptionalDefSchemaId=" + ( ( getOptionalDefSchemaId() == null ) ? "null" : "\"" + getOptionalDefSchemaId().toString() + "\"" )
			+ " RequiredName=" + "\"" + StringEscapeUtils.escapeXml11( getRequiredName() ) + "\""
			+ " OptionalShortName=" + ( ( getOptionalShortName() == null ) ? "null" : "\"" + StringEscapeUtils.escapeXml11( getOptionalShortName() ) + "\"" )
			+ " OptionalLabel=" + ( ( getOptionalLabel() == null ) ? "null" : "\"" + StringEscapeUtils.escapeXml11( getOptionalLabel() ) + "\"" )
			+ " OptionalShortDescription=" + ( ( getOptionalShortDescription() == null ) ? "null" : "\"" + StringEscapeUtils.escapeXml11( getOptionalShortDescription() ) + "\"" )
			+ " OptionalDescription=" + ( ( getOptionalDescription() == null ) ? "null" : "\"" + StringEscapeUtils.escapeXml11( getOptionalDescription() ) + "\"" )
			+ " RequiredRelationType=" + "\"" + getRequiredRelationType().toString() + "\""
			+ " OptionalDbName=" + ( ( getOptionalDbName() == null ) ? "null" : "\"" + StringEscapeUtils.escapeXml11( getOptionalDbName() ) + "\"" )
			+ " OptionalSuffix=" + ( ( getOptionalSuffix() == null ) ? "null" : "\"" + StringEscapeUtils.escapeXml11( getOptionalSuffix() ) + "\"" )
			+ " RequiredFromIndexId=" + "\"" + getRequiredFromIndexId().toString() + "\""
			+ " RequiredToTableId=" + "\"" + getRequiredToTableId().toString() + "\""
			+ " RequiredToIndexId=" + "\"" + getRequiredToIndexId().toString() + "\""
			+ " RequiredIsRequired=" + (( getRequiredIsRequired() ) ? "\"true\"" : "\"false\"" )
			+ " RequiredIsXsdContainer=" + (( getRequiredIsXsdContainer() ) ? "\"true\"" : "\"false\"" )
			+ " RequiredIsLateResolver=" + (( getRequiredIsLateResolver() ) ? "\"true\"" : "\"false\"" )
			+ " RequiredAllowAddendum=" + (( getRequiredAllowAddendum() ) ? "\"true\"" : "\"false\"" )
			+ " OptionalNarrowedId=" + ( ( getOptionalNarrowedId() == null ) ? "null" : "\"" + getOptionalNarrowedId().toString() + "\"" );
		return( ret );
	}

	@Override
	public String toString() {
		String ret = "<CFBamJpaRelation" + getXmlAttrFragment() + "/>";
		return( ret );
	}
}
