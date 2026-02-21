// Description: Java 25 Spring JPA Service for DelSubDep2

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
 *	Service for the CFBamDelSubDep2 entities defined in io.github.msobkow.v3_1.cfbam.cfbam.jpa
 *	using the CFBamDelSubDep2Repository to access them.
 */
@Service("cfbam31JpaDelSubDep2Service")
public class CFBamJpaDelSubDep2Service {

	@Autowired
	@Qualifier("cfbam31EntityManagerFactory")
	private LocalContainerEntityManagerFactoryBean cfbam31EntityManagerFactory;

	@Autowired
	private CFBamJpaDelSubDep2Repository cfbam31DelSubDep2Repository;

	/**
	 *	Create an entity, generating any database keys required along the way.
	 *
	 *		@param	data	The entity to be instantiated; must be a specific instance of CFBamJpaDelSubDep2, not a subclass.
	 *
	 *		@return The updated/created entity.
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaDelSubDep2 create(CFBamJpaDelSubDep2 data) {
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
		if(data.getRequiredRelationId() == null || data.getRequiredRelationId().isNull()) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredRelationId");
		}
		if(data.getRequiredDelSubDep1Id() == null || data.getRequiredDelSubDep1Id().isNull()) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredDelSubDep1Id");
		}
		if(data.getRequiredName() == null) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredName");
		}
		try {
			if (data.getRequiredId() == null || data.getRequiredId().isNull()) {
				data.setRequiredId(new CFLibDbKeyHash256(0));
				generatedRequiredId = true;
			}
			LocalDateTime now = LocalDateTime.now();
			data.setCreatedAt(now);
			data.setUpdatedAt(now);
			if(data.getPKey() != null && cfbam31DelSubDep2Repository.existsById((CFLibDbKeyHash256)data.getPKey())) {
				return( (CFBamJpaDelSubDep2)(cfbam31DelSubDep2Repository.findById((CFLibDbKeyHash256)(data.getPKey())).get()));
			}
			return cfbam31DelSubDep2Repository.save(data);
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
	public CFBamJpaDelSubDep2 update(CFBamJpaDelSubDep2 data) {
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
		if(data.getRequiredRelationId() == null || data.getRequiredRelationId().isNull()) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredRelationId");
		}
		if(data.getRequiredDelSubDep1Id() == null || data.getRequiredDelSubDep1Id().isNull()) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredDelSubDep1Id");
		}
		if(data.getRequiredName() == null) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredName");
		}
		// Ensure the entity exists and that the revision matches
		CFBamJpaDelSubDep2 existing = cfbam31DelSubDep2Repository.findById((CFLibDbKeyHash256)(data.getPKey()))
			.orElseThrow(() -> new CFLibCollisionDetectedException(getClass(), S_ProcName, data.getPKey()));
		if (existing.getRequiredRevision() != data.getRequiredRevision()) {
			throw new CFLibCollisionDetectedException(getClass(), S_ProcName, data.getPKey());
		}
		// Apply superior data relationships of CFBamScope to existing object
		existing.setRequiredOwnerTenant(data.getRequiredTenantId());
		// Apply data columns of CFBamScope to existing object
		// Apply superior data relationships of CFBamDelDep to existing object
		existing.setRequiredLookupRelation(data.getRequiredLookupRelation());
		existing.setOptionalLookupDefSchema(data.getOptionalLookupDefSchema());
		// Apply data columns of CFBamDelDep to existing object
		// Apply superior data relationships of CFBamDelSubDep2 to existing object
		existing.setRequiredContainerDelSubDep1(data.getRequiredContainerDelSubDep1());
		// Apply data columns of CFBamDelSubDep2 to existing object
		existing.setRequiredName(data.getRequiredName());
		// Update the audit columns
		data.setUpdatedAt(LocalDateTime.now());
		// Save the changes we've made
		return cfbam31DelSubDep2Repository.save(existing);
	}

	/**
	 *	Argument-based find database instance for compatibility with the current MSS code factory code base.
	 *
	 *		@param requiredId
	 *
	 *		@return The retrieved entity, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaDelSubDep2 find(@Param("id") CFLibDbKeyHash256 requiredId) {
		return( cfbam31DelSubDep2Repository.get(requiredId));
	}

	/**
	 *	Retrieve all entities from the repository
	 *
	 *		@return The list of retrieved entities, which may be empty
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDelSubDep2> findAll() {
		return( cfbam31DelSubDep2Repository.findAll() );
	}

	// CFBamScope specified index finders

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamScopeByTenantIdxKey as arguments.
	 *
	 *		@param requiredTenantId
	 *
	 *		@return List&lt;CFBamJpaDelSubDep2&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDelSubDep2> findByTenantIdx(@Param("tenantId") CFLibDbKeyHash256 requiredTenantId) {
		return( cfbam31DelSubDep2Repository.findByTenantIdx(requiredTenantId));
	}

	/**
	 *	ICFBamScopeByTenantIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamScopeByTenantIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDelSubDep2> findByTenantIdx(ICFBamScopeByTenantIdxKey key) {
		return( cfbam31DelSubDep2Repository.findByTenantIdx(key.getRequiredTenantId()));
	}

	// CFBamDelDep specified index finders

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamDelDepByDefSchemaIdxKey as arguments.
	 *
	 *		@param optionalDefSchemaId
	 *
	 *		@return List&lt;CFBamJpaDelSubDep2&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDelSubDep2> findByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId) {
		return( cfbam31DelSubDep2Repository.findByDefSchemaIdx(optionalDefSchemaId));
	}

	/**
	 *	ICFBamDelDepByDefSchemaIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamDelDepByDefSchemaIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDelSubDep2> findByDefSchemaIdx(ICFBamDelDepByDefSchemaIdxKey key) {
		return( cfbam31DelSubDep2Repository.findByDefSchemaIdx(key.getOptionalDefSchemaId()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamDelDepByDelDepIdxKey as arguments.
	 *
	 *		@param requiredRelationId
	 *
	 *		@return List&lt;CFBamJpaDelSubDep2&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDelSubDep2> findByDelDepIdx(@Param("relationId") CFLibDbKeyHash256 requiredRelationId) {
		return( cfbam31DelSubDep2Repository.findByDelDepIdx(requiredRelationId));
	}

	/**
	 *	ICFBamDelDepByDelDepIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamDelDepByDelDepIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDelSubDep2> findByDelDepIdx(ICFBamDelDepByDelDepIdxKey key) {
		return( cfbam31DelSubDep2Repository.findByDelDepIdx(key.getRequiredRelationId()));
	}

	// CFBamDelSubDep2 specified index finders

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamDelSubDep2ByContDelDep1IdxKey as arguments.
	 *
	 *		@param requiredDelSubDep1Id
	 *
	 *		@return List&lt;CFBamJpaDelSubDep2&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDelSubDep2> findByContDelDep1Idx(@Param("delSubDep1Id") CFLibDbKeyHash256 requiredDelSubDep1Id) {
		return( cfbam31DelSubDep2Repository.findByContDelDep1Idx(requiredDelSubDep1Id));
	}

	/**
	 *	ICFBamDelSubDep2ByContDelDep1IdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamDelSubDep2ByContDelDep1IdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDelSubDep2> findByContDelDep1Idx(ICFBamDelSubDep2ByContDelDep1IdxKey key) {
		return( cfbam31DelSubDep2Repository.findByContDelDep1Idx(key.getRequiredDelSubDep1Id()));
	}

	/**
	 *	Find an entity using the columns of the ICFBamDelSubDep2ByUNameIdxKey as arguments.
	 *
	 *		@param requiredDelSubDep1Id
	 *		@param requiredName
	 *
	 *		@return The found entity, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaDelSubDep2 findByUNameIdx(@Param("delSubDep1Id") CFLibDbKeyHash256 requiredDelSubDep1Id,
		@Param("name") String requiredName) {
		return( cfbam31DelSubDep2Repository.findByUNameIdx(requiredDelSubDep1Id,
			requiredName));
	}

	/**
	 *	ICFBamDelSubDep2ByUNameIdxKey entity finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamDelSubDep2ByUNameIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaDelSubDep2 findByUNameIdx(ICFBamDelSubDep2ByUNameIdxKey key) {
		return( cfbam31DelSubDep2Repository.findByUNameIdx(key.getRequiredDelSubDep1Id(), key.getRequiredName()));
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
	public CFBamJpaDelSubDep2 lockByIdIdx(@Param("id") CFLibDbKeyHash256 requiredId) {
		return( cfbam31DelSubDep2Repository.lockByIdIdx(requiredId));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredTenantId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDelSubDep2> lockByTenantIdx(@Param("tenantId") CFLibDbKeyHash256 requiredTenantId) {
		return( cfbam31DelSubDep2Repository.lockByTenantIdx(requiredTenantId));
	}

	/**
	 *	ICFBamScopeByTenantIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDelSubDep2> lockByTenantIdx(ICFBamScopeByTenantIdxKey key) {
		return( cfbam31DelSubDep2Repository.lockByTenantIdx(key.getRequiredTenantId()));
	}

	// CFBamDelDep specified lock-by-index methods

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalDefSchemaId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDelSubDep2> lockByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId) {
		return( cfbam31DelSubDep2Repository.lockByDefSchemaIdx(optionalDefSchemaId));
	}

	/**
	 *	ICFBamDelDepByDefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDelSubDep2> lockByDefSchemaIdx(ICFBamDelDepByDefSchemaIdxKey key) {
		return( cfbam31DelSubDep2Repository.lockByDefSchemaIdx(key.getOptionalDefSchemaId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredRelationId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDelSubDep2> lockByDelDepIdx(@Param("relationId") CFLibDbKeyHash256 requiredRelationId) {
		return( cfbam31DelSubDep2Repository.lockByDelDepIdx(requiredRelationId));
	}

	/**
	 *	ICFBamDelDepByDelDepIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDelSubDep2> lockByDelDepIdx(ICFBamDelDepByDelDepIdxKey key) {
		return( cfbam31DelSubDep2Repository.lockByDelDepIdx(key.getRequiredRelationId()));
	}

	// CFBamDelSubDep2 specified lock-by-index methods

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredDelSubDep1Id
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDelSubDep2> lockByContDelDep1Idx(@Param("delSubDep1Id") CFLibDbKeyHash256 requiredDelSubDep1Id) {
		return( cfbam31DelSubDep2Repository.lockByContDelDep1Idx(requiredDelSubDep1Id));
	}

	/**
	 *	ICFBamDelSubDep2ByContDelDep1IdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDelSubDep2> lockByContDelDep1Idx(ICFBamDelSubDep2ByContDelDep1IdxKey key) {
		return( cfbam31DelSubDep2Repository.lockByContDelDep1Idx(key.getRequiredDelSubDep1Id()));
	}

	/**
	 *	Argument-based lock database entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredDelSubDep1Id
	 *		@param requiredName
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaDelSubDep2 lockByUNameIdx(@Param("delSubDep1Id") CFLibDbKeyHash256 requiredDelSubDep1Id,
		@Param("name") String requiredName) {
		return( cfbam31DelSubDep2Repository.lockByUNameIdx(requiredDelSubDep1Id,
			requiredName));
	}

	/**
	 *	ICFBamDelSubDep2ByUNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaDelSubDep2 lockByUNameIdx(ICFBamDelSubDep2ByUNameIdxKey key) {
		return( cfbam31DelSubDep2Repository.lockByUNameIdx(key.getRequiredDelSubDep1Id(), key.getRequiredName()));
	}

	// CFBamScope specified delete-by-index methods

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByIdIdx(@Param("id") CFLibDbKeyHash256 requiredId) {
		cfbam31DelSubDep2Repository.deleteByIdIdx(requiredId);
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredTenantId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByTenantIdx(@Param("tenantId") CFLibDbKeyHash256 requiredTenantId) {
		cfbam31DelSubDep2Repository.deleteByTenantIdx(requiredTenantId);
	}

	/**
	 *	ICFBamScopeByTenantIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamScopeByTenantIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByTenantIdx(ICFBamScopeByTenantIdxKey key) {
		cfbam31DelSubDep2Repository.deleteByTenantIdx(key.getRequiredTenantId());
	}

	// CFBamDelDep specified delete-by-index methods

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalDefSchemaId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId) {
		cfbam31DelSubDep2Repository.deleteByDefSchemaIdx(optionalDefSchemaId);
	}

	/**
	 *	ICFBamDelDepByDefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamDelDepByDefSchemaIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByDefSchemaIdx(ICFBamDelDepByDefSchemaIdxKey key) {
		cfbam31DelSubDep2Repository.deleteByDefSchemaIdx(key.getOptionalDefSchemaId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredRelationId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByDelDepIdx(@Param("relationId") CFLibDbKeyHash256 requiredRelationId) {
		cfbam31DelSubDep2Repository.deleteByDelDepIdx(requiredRelationId);
	}

	/**
	 *	ICFBamDelDepByDelDepIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamDelDepByDelDepIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByDelDepIdx(ICFBamDelDepByDelDepIdxKey key) {
		cfbam31DelSubDep2Repository.deleteByDelDepIdx(key.getRequiredRelationId());
	}

	// CFBamDelSubDep2 specified delete-by-index methods

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredDelSubDep1Id
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByContDelDep1Idx(@Param("delSubDep1Id") CFLibDbKeyHash256 requiredDelSubDep1Id) {
		cfbam31DelSubDep2Repository.deleteByContDelDep1Idx(requiredDelSubDep1Id);
	}

	/**
	 *	ICFBamDelSubDep2ByContDelDep1IdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamDelSubDep2ByContDelDep1IdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByContDelDep1Idx(ICFBamDelSubDep2ByContDelDep1IdxKey key) {
		cfbam31DelSubDep2Repository.deleteByContDelDep1Idx(key.getRequiredDelSubDep1Id());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredDelSubDep1Id
	 *		@param requiredName
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByUNameIdx(@Param("delSubDep1Id") CFLibDbKeyHash256 requiredDelSubDep1Id,
		@Param("name") String requiredName) {
		cfbam31DelSubDep2Repository.deleteByUNameIdx(requiredDelSubDep1Id,
			requiredName);
	}

	/**
	 *	ICFBamDelSubDep2ByUNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamDelSubDep2ByUNameIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByUNameIdx(ICFBamDelSubDep2ByUNameIdxKey key) {
		cfbam31DelSubDep2Repository.deleteByUNameIdx(key.getRequiredDelSubDep1Id(), key.getRequiredName());
	}
}
