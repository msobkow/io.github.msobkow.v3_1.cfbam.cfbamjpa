// Description: Java 25 Spring JPA Service for ClearSubDep1

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
 *	Service for the CFBamClearSubDep1 entities defined in io.github.msobkow.v3_1.cfbam.cfbam.jpa
 *	using the CFBamClearSubDep1Repository to access them.
 */
@Service("cfbam31JpaClearSubDep1Service")
public class CFBamJpaClearSubDep1Service {

	@Autowired
	@Qualifier("cfbam31EntityManagerFactory")
	private LocalContainerEntityManagerFactoryBean cfbam31EntityManagerFactory;

	@Autowired
	private CFBamJpaClearSubDep1Repository cfbam31ClearSubDep1Repository;

	/**
	 *	Create an entity, generating any database keys required along the way.
	 *
	 *		@param	data	The entity to be instantiated; must be a specific instance of CFBamJpaClearSubDep1, not a subclass.
	 *
	 *		@return The updated/created entity.
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaClearSubDep1 create(CFBamJpaClearSubDep1 data) {
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
		if(data.getRequiredClearTopDepId() == null || data.getRequiredClearTopDepId().isNull()) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredClearTopDepId");
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
			if(data.getPKey() != null && cfbam31ClearSubDep1Repository.existsById((CFLibDbKeyHash256)data.getPKey())) {
				return( (CFBamJpaClearSubDep1)(cfbam31ClearSubDep1Repository.findById((CFLibDbKeyHash256)(data.getPKey())).get()));
			}
			return cfbam31ClearSubDep1Repository.save(data);
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
	public CFBamJpaClearSubDep1 update(CFBamJpaClearSubDep1 data) {
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
		if(data.getRequiredClearTopDepId() == null || data.getRequiredClearTopDepId().isNull()) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredClearTopDepId");
		}
		if(data.getRequiredName() == null) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredName");
		}
		// Ensure the entity exists and that the revision matches
		CFBamJpaClearSubDep1 existing = cfbam31ClearSubDep1Repository.findById((CFLibDbKeyHash256)(data.getPKey()))
			.orElseThrow(() -> new CFLibCollisionDetectedException(getClass(), S_ProcName, data.getPKey()));
		if (existing.getRequiredRevision() != data.getRequiredRevision()) {
			throw new CFLibCollisionDetectedException(getClass(), S_ProcName, data.getPKey());
		}
		// Apply superior data relationships of CFBamScope to existing object
		existing.setRequiredOwnerTenant(data.getRequiredTenantId());
		// Apply data columns of CFBamScope to existing object
		// Apply superior data relationships of CFBamClearDep to existing object
		existing.setRequiredLookupRelation(data.getRequiredLookupRelation());
		existing.setOptionalLookupDefSchema(data.getOptionalLookupDefSchema());
		// Apply data columns of CFBamClearDep to existing object
		// Apply superior data relationships of CFBamClearSubDep1 to existing object
		existing.setRequiredContainerClearTopDep(data.getRequiredContainerClearTopDep());
		// Apply data columns of CFBamClearSubDep1 to existing object
		existing.setRequiredName(data.getRequiredName());
		// Update the audit columns
		data.setUpdatedAt(LocalDateTime.now());
		// Save the changes we've made
		return cfbam31ClearSubDep1Repository.save(existing);
	}

	/**
	 *	Argument-based find database instance for compatibility with the current MSS code factory code base.
	 *
	 *		@param requiredId
	 *
	 *		@return The retrieved entity, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaClearSubDep1 find(@Param("id") CFLibDbKeyHash256 requiredId) {
		return( cfbam31ClearSubDep1Repository.get(requiredId));
	}

	/**
	 *	Retrieve all entities from the repository
	 *
	 *		@return The list of retrieved entities, which may be empty
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaClearSubDep1> findAll() {
		return( cfbam31ClearSubDep1Repository.findAll() );
	}

	// CFBamScope specified index finders

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamScopeByTenantIdxKey as arguments.
	 *
	 *		@param requiredTenantId
	 *
	 *		@return List&lt;CFBamJpaClearSubDep1&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaClearSubDep1> findByTenantIdx(@Param("tenantId") CFLibDbKeyHash256 requiredTenantId) {
		return( cfbam31ClearSubDep1Repository.findByTenantIdx(requiredTenantId));
	}

	/**
	 *	ICFBamScopeByTenantIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamScopeByTenantIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaClearSubDep1> findByTenantIdx(ICFBamScopeByTenantIdxKey key) {
		return( cfbam31ClearSubDep1Repository.findByTenantIdx(key.getRequiredTenantId()));
	}

	// CFBamClearDep specified index finders

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamClearDepByClearDepIdxKey as arguments.
	 *
	 *		@param requiredRelationId
	 *
	 *		@return List&lt;CFBamJpaClearSubDep1&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaClearSubDep1> findByClearDepIdx(@Param("relationId") CFLibDbKeyHash256 requiredRelationId) {
		return( cfbam31ClearSubDep1Repository.findByClearDepIdx(requiredRelationId));
	}

	/**
	 *	ICFBamClearDepByClearDepIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamClearDepByClearDepIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaClearSubDep1> findByClearDepIdx(ICFBamClearDepByClearDepIdxKey key) {
		return( cfbam31ClearSubDep1Repository.findByClearDepIdx(key.getRequiredRelationId()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamClearDepByDefSchemaIdxKey as arguments.
	 *
	 *		@param optionalDefSchemaId
	 *
	 *		@return List&lt;CFBamJpaClearSubDep1&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaClearSubDep1> findByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId) {
		return( cfbam31ClearSubDep1Repository.findByDefSchemaIdx(optionalDefSchemaId));
	}

	/**
	 *	ICFBamClearDepByDefSchemaIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamClearDepByDefSchemaIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaClearSubDep1> findByDefSchemaIdx(ICFBamClearDepByDefSchemaIdxKey key) {
		return( cfbam31ClearSubDep1Repository.findByDefSchemaIdx(key.getOptionalDefSchemaId()));
	}

	// CFBamClearSubDep1 specified index finders

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamClearSubDep1ByClearTopDepIdxKey as arguments.
	 *
	 *		@param requiredClearTopDepId
	 *
	 *		@return List&lt;CFBamJpaClearSubDep1&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaClearSubDep1> findByClearTopDepIdx(@Param("clearTopDepId") CFLibDbKeyHash256 requiredClearTopDepId) {
		return( cfbam31ClearSubDep1Repository.findByClearTopDepIdx(requiredClearTopDepId));
	}

	/**
	 *	ICFBamClearSubDep1ByClearTopDepIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamClearSubDep1ByClearTopDepIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaClearSubDep1> findByClearTopDepIdx(ICFBamClearSubDep1ByClearTopDepIdxKey key) {
		return( cfbam31ClearSubDep1Repository.findByClearTopDepIdx(key.getRequiredClearTopDepId()));
	}

	/**
	 *	Find an entity using the columns of the ICFBamClearSubDep1ByUNameIdxKey as arguments.
	 *
	 *		@param requiredClearTopDepId
	 *		@param requiredName
	 *
	 *		@return The found entity, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaClearSubDep1 findByUNameIdx(@Param("clearTopDepId") CFLibDbKeyHash256 requiredClearTopDepId,
		@Param("name") String requiredName) {
		return( cfbam31ClearSubDep1Repository.findByUNameIdx(requiredClearTopDepId,
			requiredName));
	}

	/**
	 *	ICFBamClearSubDep1ByUNameIdxKey entity finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamClearSubDep1ByUNameIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaClearSubDep1 findByUNameIdx(ICFBamClearSubDep1ByUNameIdxKey key) {
		return( cfbam31ClearSubDep1Repository.findByUNameIdx(key.getRequiredClearTopDepId(), key.getRequiredName()));
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
	public CFBamJpaClearSubDep1 lockByIdIdx(@Param("id") CFLibDbKeyHash256 requiredId) {
		return( cfbam31ClearSubDep1Repository.lockByIdIdx(requiredId));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredTenantId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaClearSubDep1> lockByTenantIdx(@Param("tenantId") CFLibDbKeyHash256 requiredTenantId) {
		return( cfbam31ClearSubDep1Repository.lockByTenantIdx(requiredTenantId));
	}

	/**
	 *	ICFBamScopeByTenantIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaClearSubDep1> lockByTenantIdx(ICFBamScopeByTenantIdxKey key) {
		return( cfbam31ClearSubDep1Repository.lockByTenantIdx(key.getRequiredTenantId()));
	}

	// CFBamClearDep specified lock-by-index methods

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredRelationId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaClearSubDep1> lockByClearDepIdx(@Param("relationId") CFLibDbKeyHash256 requiredRelationId) {
		return( cfbam31ClearSubDep1Repository.lockByClearDepIdx(requiredRelationId));
	}

	/**
	 *	ICFBamClearDepByClearDepIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaClearSubDep1> lockByClearDepIdx(ICFBamClearDepByClearDepIdxKey key) {
		return( cfbam31ClearSubDep1Repository.lockByClearDepIdx(key.getRequiredRelationId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalDefSchemaId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaClearSubDep1> lockByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId) {
		return( cfbam31ClearSubDep1Repository.lockByDefSchemaIdx(optionalDefSchemaId));
	}

	/**
	 *	ICFBamClearDepByDefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaClearSubDep1> lockByDefSchemaIdx(ICFBamClearDepByDefSchemaIdxKey key) {
		return( cfbam31ClearSubDep1Repository.lockByDefSchemaIdx(key.getOptionalDefSchemaId()));
	}

	// CFBamClearSubDep1 specified lock-by-index methods

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredClearTopDepId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaClearSubDep1> lockByClearTopDepIdx(@Param("clearTopDepId") CFLibDbKeyHash256 requiredClearTopDepId) {
		return( cfbam31ClearSubDep1Repository.lockByClearTopDepIdx(requiredClearTopDepId));
	}

	/**
	 *	ICFBamClearSubDep1ByClearTopDepIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaClearSubDep1> lockByClearTopDepIdx(ICFBamClearSubDep1ByClearTopDepIdxKey key) {
		return( cfbam31ClearSubDep1Repository.lockByClearTopDepIdx(key.getRequiredClearTopDepId()));
	}

	/**
	 *	Argument-based lock database entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredClearTopDepId
	 *		@param requiredName
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaClearSubDep1 lockByUNameIdx(@Param("clearTopDepId") CFLibDbKeyHash256 requiredClearTopDepId,
		@Param("name") String requiredName) {
		return( cfbam31ClearSubDep1Repository.lockByUNameIdx(requiredClearTopDepId,
			requiredName));
	}

	/**
	 *	ICFBamClearSubDep1ByUNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaClearSubDep1 lockByUNameIdx(ICFBamClearSubDep1ByUNameIdxKey key) {
		return( cfbam31ClearSubDep1Repository.lockByUNameIdx(key.getRequiredClearTopDepId(), key.getRequiredName()));
	}

	// CFBamScope specified delete-by-index methods

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByIdIdx(@Param("id") CFLibDbKeyHash256 requiredId) {
		cfbam31ClearSubDep1Repository.deleteByIdIdx(requiredId);
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredTenantId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByTenantIdx(@Param("tenantId") CFLibDbKeyHash256 requiredTenantId) {
		cfbam31ClearSubDep1Repository.deleteByTenantIdx(requiredTenantId);
	}

	/**
	 *	ICFBamScopeByTenantIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamScopeByTenantIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByTenantIdx(ICFBamScopeByTenantIdxKey key) {
		cfbam31ClearSubDep1Repository.deleteByTenantIdx(key.getRequiredTenantId());
	}

	// CFBamClearDep specified delete-by-index methods

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredRelationId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByClearDepIdx(@Param("relationId") CFLibDbKeyHash256 requiredRelationId) {
		cfbam31ClearSubDep1Repository.deleteByClearDepIdx(requiredRelationId);
	}

	/**
	 *	ICFBamClearDepByClearDepIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamClearDepByClearDepIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByClearDepIdx(ICFBamClearDepByClearDepIdxKey key) {
		cfbam31ClearSubDep1Repository.deleteByClearDepIdx(key.getRequiredRelationId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalDefSchemaId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId) {
		cfbam31ClearSubDep1Repository.deleteByDefSchemaIdx(optionalDefSchemaId);
	}

	/**
	 *	ICFBamClearDepByDefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamClearDepByDefSchemaIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByDefSchemaIdx(ICFBamClearDepByDefSchemaIdxKey key) {
		cfbam31ClearSubDep1Repository.deleteByDefSchemaIdx(key.getOptionalDefSchemaId());
	}

	// CFBamClearSubDep1 specified delete-by-index methods

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredClearTopDepId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByClearTopDepIdx(@Param("clearTopDepId") CFLibDbKeyHash256 requiredClearTopDepId) {
		cfbam31ClearSubDep1Repository.deleteByClearTopDepIdx(requiredClearTopDepId);
	}

	/**
	 *	ICFBamClearSubDep1ByClearTopDepIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamClearSubDep1ByClearTopDepIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByClearTopDepIdx(ICFBamClearSubDep1ByClearTopDepIdxKey key) {
		cfbam31ClearSubDep1Repository.deleteByClearTopDepIdx(key.getRequiredClearTopDepId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredClearTopDepId
	 *		@param requiredName
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByUNameIdx(@Param("clearTopDepId") CFLibDbKeyHash256 requiredClearTopDepId,
		@Param("name") String requiredName) {
		cfbam31ClearSubDep1Repository.deleteByUNameIdx(requiredClearTopDepId,
			requiredName);
	}

	/**
	 *	ICFBamClearSubDep1ByUNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamClearSubDep1ByUNameIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByUNameIdx(ICFBamClearSubDep1ByUNameIdxKey key) {
		cfbam31ClearSubDep1Repository.deleteByUNameIdx(key.getRequiredClearTopDepId(), key.getRequiredName());
	}

}
