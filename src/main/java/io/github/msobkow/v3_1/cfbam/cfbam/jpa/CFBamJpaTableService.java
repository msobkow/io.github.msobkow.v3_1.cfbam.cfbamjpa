// Description: Java 25 Spring JPA Service for Table

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
import io.github.msobkow.v3_1.cflib.*;
import io.github.msobkow.v3_1.cflib.dbutil.*;
import io.github.msobkow.v3_1.cflib.xml.CFLibXmlUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import io.github.msobkow.v3_1.cfsec.cfsec.*;
import io.github.msobkow.v3_1.cfint.cfint.*;
import io.github.msobkow.v3_1.cfbam.cfbam.*;

/**
 *	Service for the CFBamTable entities defined in io.github.msobkow.v3_1.cfbam.cfbam.jpa
 *	using the CFBamTableRepository to access them.
 */
@Service("cfbam31JpaTableService")
public class CFBamJpaTableService {

	@Autowired
	@Qualifier("cfbam31EntityManagerFactory")
	private LocalContainerEntityManagerFactoryBean cfbam31EntityManagerFactory;

	@Autowired
	private CFBamJpaTableRepository cfbam31TableRepository;

	/**
	 *	Create an entity, generating any database keys required along the way.
	 *
	 *		@param	data	The entity to be instantiated; must be a specific instance of CFBamJpaTable, not a subclass.
	 *
	 *		@return The updated/created entity.
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaTable create(CFBamJpaTable data) {
		final String S_ProcName = "create";
		if (data == null) {
			return( null );
		}
		CFLibDbKeyHash256 originalRequiredId = data.getRequiredId();
		boolean generatedRequiredId = false;
		if(data.getRequiredTenantId() == null || data.getRequiredTenantId().isNull()) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredTenantId");
		}
		if(data.getRequiredSchemaDefId() == null || data.getRequiredSchemaDefId().isNull()) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredSchemaDefId");
		}
		if(data.getRequiredName() == null) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredName");
		}
		if(data.getRequiredTableClassCode() == null) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredTableClassCode");
		}
		if(data.getRequiredLoaderBehaviour() == null) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredLoaderBehaviour");
		}
		if(data.getRequiredSecScope() == null) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredSecScope");
		}
		try {
			if (data.getRequiredId() == null || data.getRequiredId().isNull()) {
				data.setRequiredId(new CFLibDbKeyHash256(0));
				generatedRequiredId = true;
			}
			LocalDateTime now = LocalDateTime.now();
			data.setCreatedAt(now);
			data.setUpdatedAt(now);
			if(data.getPKey() != null && cfbam31TableRepository.existsById((CFLibDbKeyHash256)data.getPKey())) {
				return( (CFBamJpaTable)(cfbam31TableRepository.findById((CFLibDbKeyHash256)(data.getPKey())).get()));
			}
			return cfbam31TableRepository.save(data);
		}
		catch(Exception ex) {
				if(generatedRequiredId) {
					data.setRequiredId(originalRequiredId);
				}
			throw new CFLibDbException(getClass(),
				S_ProcName,
				ex);
		}
	}

	/**
	 *	Update an existing entity.
	 *
	 *		@param	data	The entity to be updated.
	 *
	 *		@return The updated entity.
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaTable update(CFBamJpaTable data) {
		final String S_ProcName = "update";
		if (data == null) {
			return( null );
		}
		if (data.getPKey() == null) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.getPKey()");
		}
		if(data.getRequiredTenantId() == null || data.getRequiredTenantId().isNull()) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredTenantId");
		}
		if(data.getRequiredSchemaDefId() == null || data.getRequiredSchemaDefId().isNull()) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredSchemaDefId");
		}
		if(data.getRequiredName() == null) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredName");
		}
		if(data.getRequiredTableClassCode() == null) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredTableClassCode");
		}
		if(data.getRequiredLoaderBehaviour() == null) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredLoaderBehaviour");
		}
		if(data.getRequiredSecScope() == null) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredSecScope");
		}
		// Ensure the entity exists and that the revision matches
		CFBamJpaTable existing = cfbam31TableRepository.findById((CFLibDbKeyHash256)(data.getPKey()))
			.orElseThrow(() -> new CFLibCollisionDetectedException(getClass(), S_ProcName, data.getPKey()));
		if (existing.getRequiredRevision() != data.getRequiredRevision()) {
			throw new CFLibCollisionDetectedException(getClass(), S_ProcName, data.getPKey());
		}
		// Apply superior data relationships of CFBamScope to existing object
		existing.setRequiredOwnerTenant(data.getRequiredTenantId());
		// Apply data columns of CFBamScope to existing object
		// Apply superior data relationships of CFBamTable to existing object
		existing.setRequiredContainerSchemaDef(data.getRequiredContainerSchemaDef());
		existing.setOptionalLookupDefSchema(data.getOptionalLookupDefSchema());
		existing.setOptionalLookupLookupIndex(data.getOptionalLookupLookupIndex());
		existing.setOptionalLookupAltIndex(data.getOptionalLookupAltIndex());
		existing.setOptionalLookupQualTable(data.getOptionalLookupQualTable());
		existing.setOptionalLookupPrimaryIndex(data.getOptionalLookupPrimaryIndex());
		// Apply data columns of CFBamTable to existing object
		existing.setRequiredName(data.getRequiredName());
		existing.setOptionalDbName(data.getOptionalDbName());
		existing.setOptionalShortName(data.getOptionalShortName());
		existing.setOptionalLabel(data.getOptionalLabel());
		existing.setOptionalShortDescription(data.getOptionalShortDescription());
		existing.setOptionalDescription(data.getOptionalDescription());
		existing.setRequiredPageData(data.getRequiredPageData());
		existing.setRequiredTableClassCode(data.getRequiredTableClassCode());
		existing.setRequiredIsInstantiable(data.getRequiredIsInstantiable());
		existing.setRequiredHasHistory(data.getRequiredHasHistory());
		existing.setRequiredHasAuditColumns(data.getRequiredHasAuditColumns());
		existing.setRequiredIsMutable(data.getRequiredIsMutable());
		existing.setRequiredIsServerOnly(data.getRequiredIsServerOnly());
		existing.setRequiredLoaderBehaviour(data.getRequiredLoaderBehaviour());
		existing.setRequiredSecScope(data.getRequiredSecScope());
		// Update the audit columns
		data.setUpdatedAt(LocalDateTime.now());
		// Save the changes we've made
		return cfbam31TableRepository.save(existing);
	}

	/**
	 *	Argument-based find database instance for compatibility with the current MSS code factory code base.
	 *
	 *		@param requiredId
	 *
	 *		@return The retrieved entity, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaTable find(@Param("id") CFLibDbKeyHash256 requiredId) {
		return( cfbam31TableRepository.get(requiredId));
	}

	/**
	 *	Retrieve all entities from the repository
	 *
	 *		@return The list of retrieved entities, which may be empty
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaTable> findAll() {
		return( cfbam31TableRepository.findAll() );
	}

	// CFBamScope specified index finders

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamScopeByTenantIdxKey as arguments.
	 *
	 *		@param requiredTenantId
	 *
	 *		@return List&lt;CFBamJpaTable&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaTable> findByTenantIdx(@Param("tenantId") CFLibDbKeyHash256 requiredTenantId) {
		return( cfbam31TableRepository.findByTenantIdx(requiredTenantId));
	}

	/**
	 *	ICFBamScopeByTenantIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamScopeByTenantIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaTable> findByTenantIdx(ICFBamScopeByTenantIdxKey key) {
		return( cfbam31TableRepository.findByTenantIdx(key.getRequiredTenantId()));
	}

	// CFBamTable specified index finders

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamTableBySchemaDefIdxKey as arguments.
	 *
	 *		@param requiredSchemaDefId
	 *
	 *		@return List&lt;CFBamJpaTable&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaTable> findBySchemaDefIdx(@Param("schemaDefId") CFLibDbKeyHash256 requiredSchemaDefId) {
		return( cfbam31TableRepository.findBySchemaDefIdx(requiredSchemaDefId));
	}

	/**
	 *	ICFBamTableBySchemaDefIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamTableBySchemaDefIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaTable> findBySchemaDefIdx(ICFBamTableBySchemaDefIdxKey key) {
		return( cfbam31TableRepository.findBySchemaDefIdx(key.getRequiredSchemaDefId()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamTableByDefSchemaIdxKey as arguments.
	 *
	 *		@param optionalDefSchemaId
	 *
	 *		@return List&lt;CFBamJpaTable&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaTable> findByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId) {
		return( cfbam31TableRepository.findByDefSchemaIdx(optionalDefSchemaId));
	}

	/**
	 *	ICFBamTableByDefSchemaIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamTableByDefSchemaIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaTable> findByDefSchemaIdx(ICFBamTableByDefSchemaIdxKey key) {
		return( cfbam31TableRepository.findByDefSchemaIdx(key.getOptionalDefSchemaId()));
	}

	/**
	 *	Find an entity using the columns of the ICFBamTableByUNameIdxKey as arguments.
	 *
	 *		@param requiredSchemaDefId
	 *		@param requiredName
	 *
	 *		@return The found entity, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaTable findByUNameIdx(@Param("schemaDefId") CFLibDbKeyHash256 requiredSchemaDefId,
		@Param("name") String requiredName) {
		return( cfbam31TableRepository.findByUNameIdx(requiredSchemaDefId,
			requiredName));
	}

	/**
	 *	ICFBamTableByUNameIdxKey entity finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamTableByUNameIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaTable findByUNameIdx(ICFBamTableByUNameIdxKey key) {
		return( cfbam31TableRepository.findByUNameIdx(key.getRequiredSchemaDefId(), key.getRequiredName()));
	}

	/**
	 *	Find an entity using the columns of the ICFBamTableBySchemaCdIdxKey as arguments.
	 *
	 *		@param requiredSchemaDefId
	 *		@param requiredTableClassCode
	 *
	 *		@return The found entity, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaTable findBySchemaCdIdx(@Param("schemaDefId") CFLibDbKeyHash256 requiredSchemaDefId,
		@Param("tableClassCode") String requiredTableClassCode) {
		return( cfbam31TableRepository.findBySchemaCdIdx(requiredSchemaDefId,
			requiredTableClassCode));
	}

	/**
	 *	ICFBamTableBySchemaCdIdxKey entity finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamTableBySchemaCdIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaTable findBySchemaCdIdx(ICFBamTableBySchemaCdIdxKey key) {
		return( cfbam31TableRepository.findBySchemaCdIdx(key.getRequiredSchemaDefId(), key.getRequiredTableClassCode()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamTableByPrimaryIndexIdxKey as arguments.
	 *
	 *		@param optionalPrimaryIndexId
	 *
	 *		@return List&lt;CFBamJpaTable&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaTable> findByPrimaryIndexIdx(@Param("primaryIndexId") CFLibDbKeyHash256 optionalPrimaryIndexId) {
		return( cfbam31TableRepository.findByPrimaryIndexIdx(optionalPrimaryIndexId));
	}

	/**
	 *	ICFBamTableByPrimaryIndexIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamTableByPrimaryIndexIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaTable> findByPrimaryIndexIdx(ICFBamTableByPrimaryIndexIdxKey key) {
		return( cfbam31TableRepository.findByPrimaryIndexIdx(key.getOptionalPrimaryIndexId()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamTableByLookupIndexIdxKey as arguments.
	 *
	 *		@param optionalLookupIndexId
	 *
	 *		@return List&lt;CFBamJpaTable&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaTable> findByLookupIndexIdx(@Param("lookupIndexId") CFLibDbKeyHash256 optionalLookupIndexId) {
		return( cfbam31TableRepository.findByLookupIndexIdx(optionalLookupIndexId));
	}

	/**
	 *	ICFBamTableByLookupIndexIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamTableByLookupIndexIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaTable> findByLookupIndexIdx(ICFBamTableByLookupIndexIdxKey key) {
		return( cfbam31TableRepository.findByLookupIndexIdx(key.getOptionalLookupIndexId()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamTableByAltIndexIdxKey as arguments.
	 *
	 *		@param optionalAltIndexId
	 *
	 *		@return List&lt;CFBamJpaTable&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaTable> findByAltIndexIdx(@Param("altIndexId") CFLibDbKeyHash256 optionalAltIndexId) {
		return( cfbam31TableRepository.findByAltIndexIdx(optionalAltIndexId));
	}

	/**
	 *	ICFBamTableByAltIndexIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamTableByAltIndexIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaTable> findByAltIndexIdx(ICFBamTableByAltIndexIdxKey key) {
		return( cfbam31TableRepository.findByAltIndexIdx(key.getOptionalAltIndexId()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamTableByQualTableIdxKey as arguments.
	 *
	 *		@param optionalQualifyingTableId
	 *
	 *		@return List&lt;CFBamJpaTable&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaTable> findByQualTableIdx(@Param("qualifyingTableId") CFLibDbKeyHash256 optionalQualifyingTableId) {
		return( cfbam31TableRepository.findByQualTableIdx(optionalQualifyingTableId));
	}

	/**
	 *	ICFBamTableByQualTableIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamTableByQualTableIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaTable> findByQualTableIdx(ICFBamTableByQualTableIdxKey key) {
		return( cfbam31TableRepository.findByQualTableIdx(key.getOptionalQualifyingTableId()));
	}

	// CFBamScope specified lock-by-index methods

	/**
	 *	Argument-based lock database entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredId
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaTable lockByIdIdx(@Param("id") CFLibDbKeyHash256 requiredId) {
		return( cfbam31TableRepository.lockByIdIdx(requiredId));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredTenantId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaTable> lockByTenantIdx(@Param("tenantId") CFLibDbKeyHash256 requiredTenantId) {
		return( cfbam31TableRepository.lockByTenantIdx(requiredTenantId));
	}

	/**
	 *	ICFBamScopeByTenantIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaTable> lockByTenantIdx(ICFBamScopeByTenantIdxKey key) {
		return( cfbam31TableRepository.lockByTenantIdx(key.getRequiredTenantId()));
	}

	// CFBamTable specified lock-by-index methods

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredSchemaDefId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaTable> lockBySchemaDefIdx(@Param("schemaDefId") CFLibDbKeyHash256 requiredSchemaDefId) {
		return( cfbam31TableRepository.lockBySchemaDefIdx(requiredSchemaDefId));
	}

	/**
	 *	ICFBamTableBySchemaDefIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaTable> lockBySchemaDefIdx(ICFBamTableBySchemaDefIdxKey key) {
		return( cfbam31TableRepository.lockBySchemaDefIdx(key.getRequiredSchemaDefId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalDefSchemaId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaTable> lockByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId) {
		return( cfbam31TableRepository.lockByDefSchemaIdx(optionalDefSchemaId));
	}

	/**
	 *	ICFBamTableByDefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaTable> lockByDefSchemaIdx(ICFBamTableByDefSchemaIdxKey key) {
		return( cfbam31TableRepository.lockByDefSchemaIdx(key.getOptionalDefSchemaId()));
	}

	/**
	 *	Argument-based lock database entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredSchemaDefId
	 *		@param requiredName
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaTable lockByUNameIdx(@Param("schemaDefId") CFLibDbKeyHash256 requiredSchemaDefId,
		@Param("name") String requiredName) {
		return( cfbam31TableRepository.lockByUNameIdx(requiredSchemaDefId,
			requiredName));
	}

	/**
	 *	ICFBamTableByUNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaTable lockByUNameIdx(ICFBamTableByUNameIdxKey key) {
		return( cfbam31TableRepository.lockByUNameIdx(key.getRequiredSchemaDefId(), key.getRequiredName()));
	}

	/**
	 *	Argument-based lock database entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredSchemaDefId
	 *		@param requiredTableClassCode
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaTable lockBySchemaCdIdx(@Param("schemaDefId") CFLibDbKeyHash256 requiredSchemaDefId,
		@Param("tableClassCode") String requiredTableClassCode) {
		return( cfbam31TableRepository.lockBySchemaCdIdx(requiredSchemaDefId,
			requiredTableClassCode));
	}

	/**
	 *	ICFBamTableBySchemaCdIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaTable lockBySchemaCdIdx(ICFBamTableBySchemaCdIdxKey key) {
		return( cfbam31TableRepository.lockBySchemaCdIdx(key.getRequiredSchemaDefId(), key.getRequiredTableClassCode()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalPrimaryIndexId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaTable> lockByPrimaryIndexIdx(@Param("primaryIndexId") CFLibDbKeyHash256 optionalPrimaryIndexId) {
		return( cfbam31TableRepository.lockByPrimaryIndexIdx(optionalPrimaryIndexId));
	}

	/**
	 *	ICFBamTableByPrimaryIndexIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaTable> lockByPrimaryIndexIdx(ICFBamTableByPrimaryIndexIdxKey key) {
		return( cfbam31TableRepository.lockByPrimaryIndexIdx(key.getOptionalPrimaryIndexId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalLookupIndexId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaTable> lockByLookupIndexIdx(@Param("lookupIndexId") CFLibDbKeyHash256 optionalLookupIndexId) {
		return( cfbam31TableRepository.lockByLookupIndexIdx(optionalLookupIndexId));
	}

	/**
	 *	ICFBamTableByLookupIndexIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaTable> lockByLookupIndexIdx(ICFBamTableByLookupIndexIdxKey key) {
		return( cfbam31TableRepository.lockByLookupIndexIdx(key.getOptionalLookupIndexId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalAltIndexId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaTable> lockByAltIndexIdx(@Param("altIndexId") CFLibDbKeyHash256 optionalAltIndexId) {
		return( cfbam31TableRepository.lockByAltIndexIdx(optionalAltIndexId));
	}

	/**
	 *	ICFBamTableByAltIndexIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaTable> lockByAltIndexIdx(ICFBamTableByAltIndexIdxKey key) {
		return( cfbam31TableRepository.lockByAltIndexIdx(key.getOptionalAltIndexId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalQualifyingTableId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaTable> lockByQualTableIdx(@Param("qualifyingTableId") CFLibDbKeyHash256 optionalQualifyingTableId) {
		return( cfbam31TableRepository.lockByQualTableIdx(optionalQualifyingTableId));
	}

	/**
	 *	ICFBamTableByQualTableIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaTable> lockByQualTableIdx(ICFBamTableByQualTableIdxKey key) {
		return( cfbam31TableRepository.lockByQualTableIdx(key.getOptionalQualifyingTableId()));
	}

	// CFBamScope specified delete-by-index methods

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByIdIdx(@Param("id") CFLibDbKeyHash256 requiredId) {
		cfbam31TableRepository.deleteByIdIdx(requiredId);
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredTenantId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByTenantIdx(@Param("tenantId") CFLibDbKeyHash256 requiredTenantId) {
		cfbam31TableRepository.deleteByTenantIdx(requiredTenantId);
	}

	/**
	 *	ICFBamScopeByTenantIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamScopeByTenantIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByTenantIdx(ICFBamScopeByTenantIdxKey key) {
		cfbam31TableRepository.deleteByTenantIdx(key.getRequiredTenantId());
	}

	// CFBamTable specified delete-by-index methods

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredSchemaDefId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteBySchemaDefIdx(@Param("schemaDefId") CFLibDbKeyHash256 requiredSchemaDefId) {
		cfbam31TableRepository.deleteBySchemaDefIdx(requiredSchemaDefId);
	}

	/**
	 *	ICFBamTableBySchemaDefIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamTableBySchemaDefIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteBySchemaDefIdx(ICFBamTableBySchemaDefIdxKey key) {
		cfbam31TableRepository.deleteBySchemaDefIdx(key.getRequiredSchemaDefId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalDefSchemaId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId) {
		cfbam31TableRepository.deleteByDefSchemaIdx(optionalDefSchemaId);
	}

	/**
	 *	ICFBamTableByDefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamTableByDefSchemaIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByDefSchemaIdx(ICFBamTableByDefSchemaIdxKey key) {
		cfbam31TableRepository.deleteByDefSchemaIdx(key.getOptionalDefSchemaId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredSchemaDefId
	 *		@param requiredName
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByUNameIdx(@Param("schemaDefId") CFLibDbKeyHash256 requiredSchemaDefId,
		@Param("name") String requiredName) {
		cfbam31TableRepository.deleteByUNameIdx(requiredSchemaDefId,
			requiredName);
	}

	/**
	 *	ICFBamTableByUNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamTableByUNameIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByUNameIdx(ICFBamTableByUNameIdxKey key) {
		cfbam31TableRepository.deleteByUNameIdx(key.getRequiredSchemaDefId(), key.getRequiredName());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredSchemaDefId
	 *		@param requiredTableClassCode
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteBySchemaCdIdx(@Param("schemaDefId") CFLibDbKeyHash256 requiredSchemaDefId,
		@Param("tableClassCode") String requiredTableClassCode) {
		cfbam31TableRepository.deleteBySchemaCdIdx(requiredSchemaDefId,
			requiredTableClassCode);
	}

	/**
	 *	ICFBamTableBySchemaCdIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamTableBySchemaCdIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteBySchemaCdIdx(ICFBamTableBySchemaCdIdxKey key) {
		cfbam31TableRepository.deleteBySchemaCdIdx(key.getRequiredSchemaDefId(), key.getRequiredTableClassCode());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalPrimaryIndexId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByPrimaryIndexIdx(@Param("primaryIndexId") CFLibDbKeyHash256 optionalPrimaryIndexId) {
		cfbam31TableRepository.deleteByPrimaryIndexIdx(optionalPrimaryIndexId);
	}

	/**
	 *	ICFBamTableByPrimaryIndexIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamTableByPrimaryIndexIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByPrimaryIndexIdx(ICFBamTableByPrimaryIndexIdxKey key) {
		cfbam31TableRepository.deleteByPrimaryIndexIdx(key.getOptionalPrimaryIndexId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalLookupIndexId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByLookupIndexIdx(@Param("lookupIndexId") CFLibDbKeyHash256 optionalLookupIndexId) {
		cfbam31TableRepository.deleteByLookupIndexIdx(optionalLookupIndexId);
	}

	/**
	 *	ICFBamTableByLookupIndexIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamTableByLookupIndexIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByLookupIndexIdx(ICFBamTableByLookupIndexIdxKey key) {
		cfbam31TableRepository.deleteByLookupIndexIdx(key.getOptionalLookupIndexId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalAltIndexId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByAltIndexIdx(@Param("altIndexId") CFLibDbKeyHash256 optionalAltIndexId) {
		cfbam31TableRepository.deleteByAltIndexIdx(optionalAltIndexId);
	}

	/**
	 *	ICFBamTableByAltIndexIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamTableByAltIndexIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByAltIndexIdx(ICFBamTableByAltIndexIdxKey key) {
		cfbam31TableRepository.deleteByAltIndexIdx(key.getOptionalAltIndexId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalQualifyingTableId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByQualTableIdx(@Param("qualifyingTableId") CFLibDbKeyHash256 optionalQualifyingTableId) {
		cfbam31TableRepository.deleteByQualTableIdx(optionalQualifyingTableId);
	}

	/**
	 *	ICFBamTableByQualTableIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamTableByQualTableIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByQualTableIdx(ICFBamTableByQualTableIdxKey key) {
		cfbam31TableRepository.deleteByQualTableIdx(key.getOptionalQualifyingTableId());
	}

}
