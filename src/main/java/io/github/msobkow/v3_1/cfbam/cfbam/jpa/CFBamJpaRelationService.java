// Description: Java 25 Spring JPA Service for Relation

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
 *	Service for the CFBamRelation entities defined in io.github.msobkow.v3_1.cfbam.cfbam.jpa
 *	using the CFBamRelationRepository to access them.
 */
@Service("cfbam31JpaRelationService")
public class CFBamJpaRelationService {

	@Autowired
	@Qualifier("cfbam31EntityManagerFactory")
	private LocalContainerEntityManagerFactoryBean cfbam31EntityManagerFactory;

	@Autowired
	private CFBamJpaRelationRepository cfbam31RelationRepository;

	/**
	 *	Create an entity, generating any database keys required along the way.
	 *
	 *		@param	data	The entity to be instantiated; must be a specific instance of CFBamJpaRelation, not a subclass.
	 *
	 *		@return The updated/created entity.
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaRelation create(CFBamJpaRelation data) {
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
		if(data.getRequiredTableId() == null || data.getRequiredTableId().isNull()) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredTableId");
		}
		if(data.getRequiredName() == null) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredName");
		}
		if(data.getRequiredRelationType() == null) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredRelationType");
		}
		if(data.getRequiredFromIndexId() == null || data.getRequiredFromIndexId().isNull()) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredFromIndexId");
		}
		if(data.getRequiredToTableId() == null || data.getRequiredToTableId().isNull()) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredToTableId");
		}
		if(data.getRequiredToIndexId() == null || data.getRequiredToIndexId().isNull()) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredToIndexId");
		}
		try {
			if (data.getRequiredId() == null || data.getRequiredId().isNull()) {
				data.setRequiredId(new CFLibDbKeyHash256(0));
				generatedRequiredId = true;
			}
			LocalDateTime now = LocalDateTime.now();
			data.setCreatedAt(now);
			data.setUpdatedAt(now);
			if(data.getPKey() != null && cfbam31RelationRepository.existsById((CFLibDbKeyHash256)data.getPKey())) {
				return( (CFBamJpaRelation)(cfbam31RelationRepository.findById((CFLibDbKeyHash256)(data.getPKey())).get()));
			}
			return cfbam31RelationRepository.save(data);
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
	public CFBamJpaRelation update(CFBamJpaRelation data) {
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
		if(data.getRequiredTableId() == null || data.getRequiredTableId().isNull()) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredTableId");
		}
		if(data.getRequiredName() == null) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredName");
		}
		if(data.getRequiredRelationType() == null) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredRelationType");
		}
		if(data.getRequiredFromIndexId() == null || data.getRequiredFromIndexId().isNull()) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredFromIndexId");
		}
		if(data.getRequiredToTableId() == null || data.getRequiredToTableId().isNull()) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredToTableId");
		}
		if(data.getRequiredToIndexId() == null || data.getRequiredToIndexId().isNull()) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredToIndexId");
		}
		// Ensure the entity exists and that the revision matches
		CFBamJpaRelation existing = cfbam31RelationRepository.findById((CFLibDbKeyHash256)(data.getPKey()))
			.orElseThrow(() -> new CFLibCollisionDetectedException(getClass(), S_ProcName, data.getPKey()));
		if (existing.getRequiredRevision() != data.getRequiredRevision()) {
			throw new CFLibCollisionDetectedException(getClass(), S_ProcName, data.getPKey());
		}
		// Apply superior data relationships of CFBamScope to existing object
		existing.setRequiredOwnerTenant(data.getRequiredTenantId());
		// Apply data columns of CFBamScope to existing object
		// Apply superior data relationships of CFBamRelation to existing object
		existing.setOptionalLookupDefSchema(data.getOptionalLookupDefSchema());
		existing.setRequiredContainerFromTable(data.getRequiredContainerFromTable());
		existing.setRequiredLookupFromIndex(data.getRequiredLookupFromIndex());
		existing.setRequiredLookupToTable(data.getRequiredLookupToTable());
		existing.setRequiredLookupToIndex(data.getRequiredLookupToIndex());
		existing.setOptionalLookupNarrowed(data.getOptionalLookupNarrowed());
		// Apply data columns of CFBamRelation to existing object
		existing.setRequiredName(data.getRequiredName());
		existing.setOptionalShortName(data.getOptionalShortName());
		existing.setOptionalLabel(data.getOptionalLabel());
		existing.setOptionalShortDescription(data.getOptionalShortDescription());
		existing.setOptionalDescription(data.getOptionalDescription());
		existing.setRequiredRelationType(data.getRequiredRelationType());
		existing.setOptionalDbName(data.getOptionalDbName());
		existing.setOptionalSuffix(data.getOptionalSuffix());
		existing.setRequiredIsRequired(data.getRequiredIsRequired());
		existing.setRequiredIsXsdContainer(data.getRequiredIsXsdContainer());
		existing.setRequiredIsLateResolver(data.getRequiredIsLateResolver());
		existing.setRequiredAllowAddendum(data.getRequiredAllowAddendum());
		// Update the audit columns
		data.setUpdatedAt(LocalDateTime.now());
		// Save the changes we've made
		return cfbam31RelationRepository.save(existing);
	}

	/**
	 *	Argument-based find database instance for compatibility with the current MSS code factory code base.
	 *
	 *		@param requiredId
	 *
	 *		@return The retrieved entity, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaRelation find(@Param("id") CFLibDbKeyHash256 requiredId) {
		return( cfbam31RelationRepository.get(requiredId));
	}

	/**
	 *	Retrieve all entities from the repository
	 *
	 *		@return The list of retrieved entities, which may be empty
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelation> findAll() {
		return( cfbam31RelationRepository.findAll() );
	}

	// CFBamScope specified index finders

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamScopeByTenantIdxKey as arguments.
	 *
	 *		@param requiredTenantId
	 *
	 *		@return List&lt;CFBamJpaRelation&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelation> findByTenantIdx(@Param("tenantId") CFLibDbKeyHash256 requiredTenantId) {
		return( cfbam31RelationRepository.findByTenantIdx(requiredTenantId));
	}

	/**
	 *	ICFBamScopeByTenantIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamScopeByTenantIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelation> findByTenantIdx(ICFBamScopeByTenantIdxKey key) {
		return( cfbam31RelationRepository.findByTenantIdx(key.getRequiredTenantId()));
	}

	// CFBamRelation specified index finders

	/**
	 *	Find an entity using the columns of the ICFBamRelationByUNameIdxKey as arguments.
	 *
	 *		@param requiredTableId
	 *		@param requiredName
	 *
	 *		@return The found entity, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaRelation findByUNameIdx(@Param("tableId") CFLibDbKeyHash256 requiredTableId,
		@Param("name") String requiredName) {
		return( cfbam31RelationRepository.findByUNameIdx(requiredTableId,
			requiredName));
	}

	/**
	 *	ICFBamRelationByUNameIdxKey entity finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamRelationByUNameIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaRelation findByUNameIdx(ICFBamRelationByUNameIdxKey key) {
		return( cfbam31RelationRepository.findByUNameIdx(key.getRequiredTableId(), key.getRequiredName()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamRelationByRelTableIdxKey as arguments.
	 *
	 *		@param requiredTableId
	 *
	 *		@return List&lt;CFBamJpaRelation&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelation> findByRelTableIdx(@Param("tableId") CFLibDbKeyHash256 requiredTableId) {
		return( cfbam31RelationRepository.findByRelTableIdx(requiredTableId));
	}

	/**
	 *	ICFBamRelationByRelTableIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamRelationByRelTableIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelation> findByRelTableIdx(ICFBamRelationByRelTableIdxKey key) {
		return( cfbam31RelationRepository.findByRelTableIdx(key.getRequiredTableId()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamRelationByDefSchemaIdxKey as arguments.
	 *
	 *		@param optionalDefSchemaId
	 *
	 *		@return List&lt;CFBamJpaRelation&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelation> findByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId) {
		return( cfbam31RelationRepository.findByDefSchemaIdx(optionalDefSchemaId));
	}

	/**
	 *	ICFBamRelationByDefSchemaIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamRelationByDefSchemaIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelation> findByDefSchemaIdx(ICFBamRelationByDefSchemaIdxKey key) {
		return( cfbam31RelationRepository.findByDefSchemaIdx(key.getOptionalDefSchemaId()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamRelationByFromKeyIdxKey as arguments.
	 *
	 *		@param requiredFromIndexId
	 *
	 *		@return List&lt;CFBamJpaRelation&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelation> findByFromKeyIdx(@Param("fromIndexId") CFLibDbKeyHash256 requiredFromIndexId) {
		return( cfbam31RelationRepository.findByFromKeyIdx(requiredFromIndexId));
	}

	/**
	 *	ICFBamRelationByFromKeyIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamRelationByFromKeyIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelation> findByFromKeyIdx(ICFBamRelationByFromKeyIdxKey key) {
		return( cfbam31RelationRepository.findByFromKeyIdx(key.getRequiredFromIndexId()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamRelationByToTblIdxKey as arguments.
	 *
	 *		@param requiredToTableId
	 *
	 *		@return List&lt;CFBamJpaRelation&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelation> findByToTblIdx(@Param("toTableId") CFLibDbKeyHash256 requiredToTableId) {
		return( cfbam31RelationRepository.findByToTblIdx(requiredToTableId));
	}

	/**
	 *	ICFBamRelationByToTblIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamRelationByToTblIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelation> findByToTblIdx(ICFBamRelationByToTblIdxKey key) {
		return( cfbam31RelationRepository.findByToTblIdx(key.getRequiredToTableId()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamRelationByToKeyIdxKey as arguments.
	 *
	 *		@param requiredToIndexId
	 *
	 *		@return List&lt;CFBamJpaRelation&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelation> findByToKeyIdx(@Param("toIndexId") CFLibDbKeyHash256 requiredToIndexId) {
		return( cfbam31RelationRepository.findByToKeyIdx(requiredToIndexId));
	}

	/**
	 *	ICFBamRelationByToKeyIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamRelationByToKeyIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelation> findByToKeyIdx(ICFBamRelationByToKeyIdxKey key) {
		return( cfbam31RelationRepository.findByToKeyIdx(key.getRequiredToIndexId()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamRelationByNarrowedIdxKey as arguments.
	 *
	 *		@param optionalNarrowedId
	 *
	 *		@return List&lt;CFBamJpaRelation&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelation> findByNarrowedIdx(@Param("narrowedId") CFLibDbKeyHash256 optionalNarrowedId) {
		return( cfbam31RelationRepository.findByNarrowedIdx(optionalNarrowedId));
	}

	/**
	 *	ICFBamRelationByNarrowedIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamRelationByNarrowedIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelation> findByNarrowedIdx(ICFBamRelationByNarrowedIdxKey key) {
		return( cfbam31RelationRepository.findByNarrowedIdx(key.getOptionalNarrowedId()));
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
	public CFBamJpaRelation lockByIdIdx(@Param("id") CFLibDbKeyHash256 requiredId) {
		return( cfbam31RelationRepository.lockByIdIdx(requiredId));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredTenantId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelation> lockByTenantIdx(@Param("tenantId") CFLibDbKeyHash256 requiredTenantId) {
		return( cfbam31RelationRepository.lockByTenantIdx(requiredTenantId));
	}

	/**
	 *	ICFBamScopeByTenantIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelation> lockByTenantIdx(ICFBamScopeByTenantIdxKey key) {
		return( cfbam31RelationRepository.lockByTenantIdx(key.getRequiredTenantId()));
	}

	// CFBamRelation specified lock-by-index methods

	/**
	 *	Argument-based lock database entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredTableId
	 *		@param requiredName
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaRelation lockByUNameIdx(@Param("tableId") CFLibDbKeyHash256 requiredTableId,
		@Param("name") String requiredName) {
		return( cfbam31RelationRepository.lockByUNameIdx(requiredTableId,
			requiredName));
	}

	/**
	 *	ICFBamRelationByUNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaRelation lockByUNameIdx(ICFBamRelationByUNameIdxKey key) {
		return( cfbam31RelationRepository.lockByUNameIdx(key.getRequiredTableId(), key.getRequiredName()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredTableId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelation> lockByRelTableIdx(@Param("tableId") CFLibDbKeyHash256 requiredTableId) {
		return( cfbam31RelationRepository.lockByRelTableIdx(requiredTableId));
	}

	/**
	 *	ICFBamRelationByRelTableIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelation> lockByRelTableIdx(ICFBamRelationByRelTableIdxKey key) {
		return( cfbam31RelationRepository.lockByRelTableIdx(key.getRequiredTableId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalDefSchemaId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelation> lockByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId) {
		return( cfbam31RelationRepository.lockByDefSchemaIdx(optionalDefSchemaId));
	}

	/**
	 *	ICFBamRelationByDefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelation> lockByDefSchemaIdx(ICFBamRelationByDefSchemaIdxKey key) {
		return( cfbam31RelationRepository.lockByDefSchemaIdx(key.getOptionalDefSchemaId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredFromIndexId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelation> lockByFromKeyIdx(@Param("fromIndexId") CFLibDbKeyHash256 requiredFromIndexId) {
		return( cfbam31RelationRepository.lockByFromKeyIdx(requiredFromIndexId));
	}

	/**
	 *	ICFBamRelationByFromKeyIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelation> lockByFromKeyIdx(ICFBamRelationByFromKeyIdxKey key) {
		return( cfbam31RelationRepository.lockByFromKeyIdx(key.getRequiredFromIndexId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredToTableId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelation> lockByToTblIdx(@Param("toTableId") CFLibDbKeyHash256 requiredToTableId) {
		return( cfbam31RelationRepository.lockByToTblIdx(requiredToTableId));
	}

	/**
	 *	ICFBamRelationByToTblIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelation> lockByToTblIdx(ICFBamRelationByToTblIdxKey key) {
		return( cfbam31RelationRepository.lockByToTblIdx(key.getRequiredToTableId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredToIndexId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelation> lockByToKeyIdx(@Param("toIndexId") CFLibDbKeyHash256 requiredToIndexId) {
		return( cfbam31RelationRepository.lockByToKeyIdx(requiredToIndexId));
	}

	/**
	 *	ICFBamRelationByToKeyIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelation> lockByToKeyIdx(ICFBamRelationByToKeyIdxKey key) {
		return( cfbam31RelationRepository.lockByToKeyIdx(key.getRequiredToIndexId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalNarrowedId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelation> lockByNarrowedIdx(@Param("narrowedId") CFLibDbKeyHash256 optionalNarrowedId) {
		return( cfbam31RelationRepository.lockByNarrowedIdx(optionalNarrowedId));
	}

	/**
	 *	ICFBamRelationByNarrowedIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelation> lockByNarrowedIdx(ICFBamRelationByNarrowedIdxKey key) {
		return( cfbam31RelationRepository.lockByNarrowedIdx(key.getOptionalNarrowedId()));
	}

	// CFBamScope specified delete-by-index methods

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByIdIdx(@Param("id") CFLibDbKeyHash256 requiredId) {
		cfbam31RelationRepository.deleteByIdIdx(requiredId);
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredTenantId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByTenantIdx(@Param("tenantId") CFLibDbKeyHash256 requiredTenantId) {
		cfbam31RelationRepository.deleteByTenantIdx(requiredTenantId);
	}

	/**
	 *	ICFBamScopeByTenantIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamScopeByTenantIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByTenantIdx(ICFBamScopeByTenantIdxKey key) {
		cfbam31RelationRepository.deleteByTenantIdx(key.getRequiredTenantId());
	}

	// CFBamRelation specified delete-by-index methods

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredTableId
	 *		@param requiredName
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByUNameIdx(@Param("tableId") CFLibDbKeyHash256 requiredTableId,
		@Param("name") String requiredName) {
		cfbam31RelationRepository.deleteByUNameIdx(requiredTableId,
			requiredName);
	}

	/**
	 *	ICFBamRelationByUNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamRelationByUNameIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByUNameIdx(ICFBamRelationByUNameIdxKey key) {
		cfbam31RelationRepository.deleteByUNameIdx(key.getRequiredTableId(), key.getRequiredName());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredTableId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByRelTableIdx(@Param("tableId") CFLibDbKeyHash256 requiredTableId) {
		cfbam31RelationRepository.deleteByRelTableIdx(requiredTableId);
	}

	/**
	 *	ICFBamRelationByRelTableIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamRelationByRelTableIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByRelTableIdx(ICFBamRelationByRelTableIdxKey key) {
		cfbam31RelationRepository.deleteByRelTableIdx(key.getRequiredTableId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalDefSchemaId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId) {
		cfbam31RelationRepository.deleteByDefSchemaIdx(optionalDefSchemaId);
	}

	/**
	 *	ICFBamRelationByDefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamRelationByDefSchemaIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByDefSchemaIdx(ICFBamRelationByDefSchemaIdxKey key) {
		cfbam31RelationRepository.deleteByDefSchemaIdx(key.getOptionalDefSchemaId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredFromIndexId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByFromKeyIdx(@Param("fromIndexId") CFLibDbKeyHash256 requiredFromIndexId) {
		cfbam31RelationRepository.deleteByFromKeyIdx(requiredFromIndexId);
	}

	/**
	 *	ICFBamRelationByFromKeyIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamRelationByFromKeyIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByFromKeyIdx(ICFBamRelationByFromKeyIdxKey key) {
		cfbam31RelationRepository.deleteByFromKeyIdx(key.getRequiredFromIndexId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredToTableId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByToTblIdx(@Param("toTableId") CFLibDbKeyHash256 requiredToTableId) {
		cfbam31RelationRepository.deleteByToTblIdx(requiredToTableId);
	}

	/**
	 *	ICFBamRelationByToTblIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamRelationByToTblIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByToTblIdx(ICFBamRelationByToTblIdxKey key) {
		cfbam31RelationRepository.deleteByToTblIdx(key.getRequiredToTableId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredToIndexId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByToKeyIdx(@Param("toIndexId") CFLibDbKeyHash256 requiredToIndexId) {
		cfbam31RelationRepository.deleteByToKeyIdx(requiredToIndexId);
	}

	/**
	 *	ICFBamRelationByToKeyIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamRelationByToKeyIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByToKeyIdx(ICFBamRelationByToKeyIdxKey key) {
		cfbam31RelationRepository.deleteByToKeyIdx(key.getRequiredToIndexId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalNarrowedId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByNarrowedIdx(@Param("narrowedId") CFLibDbKeyHash256 optionalNarrowedId) {
		cfbam31RelationRepository.deleteByNarrowedIdx(optionalNarrowedId);
	}

	/**
	 *	ICFBamRelationByNarrowedIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamRelationByNarrowedIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByNarrowedIdx(ICFBamRelationByNarrowedIdxKey key) {
		cfbam31RelationRepository.deleteByNarrowedIdx(key.getOptionalNarrowedId());
	}

}
