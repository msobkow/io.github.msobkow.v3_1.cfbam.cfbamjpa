// Description: Java 25 Spring JPA Service for PopSubDep1

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
 *	Service for the CFBamPopSubDep1 entities defined in io.github.msobkow.v3_1.cfbam.cfbam.jpa
 *	using the CFBamPopSubDep1Repository to access them.
 */
@Service("cfbam31JpaPopSubDep1Service")
public class CFBamJpaPopSubDep1Service {

	@Autowired
	@Qualifier("cfbam31EntityManagerFactory")
	private LocalContainerEntityManagerFactoryBean cfbam31EntityManagerFactory;

	@Autowired
	private CFBamJpaPopSubDep1Repository cfbam31PopSubDep1Repository;

	/**
	 *	Create an entity, generating any database keys required along the way.
	 *
	 *		@param	data	The entity to be instantiated; must be a specific instance of CFBamJpaPopSubDep1, not a subclass.
	 *
	 *		@return The updated/created entity.
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaPopSubDep1 create(CFBamJpaPopSubDep1 data) {
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
		if(data.getRequiredPopTopDepId() == null || data.getRequiredPopTopDepId().isNull()) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredPopTopDepId");
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
			if(data.getPKey() != null && cfbam31PopSubDep1Repository.existsById((CFLibDbKeyHash256)data.getPKey())) {
				return( (CFBamJpaPopSubDep1)(cfbam31PopSubDep1Repository.findById((CFLibDbKeyHash256)(data.getPKey())).get()));
			}
			return cfbam31PopSubDep1Repository.save(data);
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
	public CFBamJpaPopSubDep1 update(CFBamJpaPopSubDep1 data) {
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
		if(data.getRequiredPopTopDepId() == null || data.getRequiredPopTopDepId().isNull()) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredPopTopDepId");
		}
		if(data.getRequiredName() == null) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredName");
		}
		// Ensure the entity exists and that the revision matches
		CFBamJpaPopSubDep1 existing = cfbam31PopSubDep1Repository.findById((CFLibDbKeyHash256)(data.getPKey()))
			.orElseThrow(() -> new CFLibCollisionDetectedException(getClass(), S_ProcName, data.getPKey()));
		if (existing.getRequiredRevision() != data.getRequiredRevision()) {
			throw new CFLibCollisionDetectedException(getClass(), S_ProcName, data.getPKey());
		}
		// Apply superior data relationships of CFBamScope to existing object
		existing.setRequiredOwnerTenant(data.getRequiredTenantId());
		// Apply data columns of CFBamScope to existing object
		// Apply superior data relationships of CFBamPopDep to existing object
		existing.setRequiredLookupRelation(data.getRequiredLookupRelation());
		existing.setOptionalLookupDefSchema(data.getOptionalLookupDefSchema());
		// Apply data columns of CFBamPopDep to existing object
		// Apply superior data relationships of CFBamPopSubDep1 to existing object
		existing.setRequiredContainerContPopTopDep(data.getRequiredContainerContPopTopDep());
		// Apply data columns of CFBamPopSubDep1 to existing object
		existing.setRequiredName(data.getRequiredName());
		// Update the audit columns
		data.setUpdatedAt(LocalDateTime.now());
		// Save the changes we've made
		return cfbam31PopSubDep1Repository.save(existing);
	}

	/**
	 *	Argument-based find database instance for compatibility with the current MSS code factory code base.
	 *
	 *		@param requiredId
	 *
	 *		@return The retrieved entity, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaPopSubDep1 find(@Param("id") CFLibDbKeyHash256 requiredId) {
		return( cfbam31PopSubDep1Repository.get(requiredId));
	}

	/**
	 *	Retrieve all entities from the repository
	 *
	 *		@return The list of retrieved entities, which may be empty
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaPopSubDep1> findAll() {
		return( cfbam31PopSubDep1Repository.findAll() );
	}

	// CFBamScope specified index finders

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamScopeByTenantIdxKey as arguments.
	 *
	 *		@param requiredTenantId
	 *
	 *		@return List&lt;CFBamJpaPopSubDep1&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaPopSubDep1> findByTenantIdx(@Param("tenantId") CFLibDbKeyHash256 requiredTenantId) {
		return( cfbam31PopSubDep1Repository.findByTenantIdx(requiredTenantId));
	}

	/**
	 *	ICFBamScopeByTenantIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamScopeByTenantIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaPopSubDep1> findByTenantIdx(ICFBamScopeByTenantIdxKey key) {
		return( cfbam31PopSubDep1Repository.findByTenantIdx(key.getRequiredTenantId()));
	}

	// CFBamPopDep specified index finders

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamPopDepByRelationIdxKey as arguments.
	 *
	 *		@param requiredRelationId
	 *
	 *		@return List&lt;CFBamJpaPopSubDep1&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaPopSubDep1> findByRelationIdx(@Param("relationId") CFLibDbKeyHash256 requiredRelationId) {
		return( cfbam31PopSubDep1Repository.findByRelationIdx(requiredRelationId));
	}

	/**
	 *	ICFBamPopDepByRelationIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamPopDepByRelationIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaPopSubDep1> findByRelationIdx(ICFBamPopDepByRelationIdxKey key) {
		return( cfbam31PopSubDep1Repository.findByRelationIdx(key.getRequiredRelationId()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamPopDepByDefSchemaIdxKey as arguments.
	 *
	 *		@param optionalDefSchemaId
	 *
	 *		@return List&lt;CFBamJpaPopSubDep1&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaPopSubDep1> findByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId) {
		return( cfbam31PopSubDep1Repository.findByDefSchemaIdx(optionalDefSchemaId));
	}

	/**
	 *	ICFBamPopDepByDefSchemaIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamPopDepByDefSchemaIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaPopSubDep1> findByDefSchemaIdx(ICFBamPopDepByDefSchemaIdxKey key) {
		return( cfbam31PopSubDep1Repository.findByDefSchemaIdx(key.getOptionalDefSchemaId()));
	}

	// CFBamPopSubDep1 specified index finders

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamPopSubDep1ByPopTopDepIdxKey as arguments.
	 *
	 *		@param requiredPopTopDepId
	 *
	 *		@return List&lt;CFBamJpaPopSubDep1&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaPopSubDep1> findByPopTopDepIdx(@Param("popTopDepId") CFLibDbKeyHash256 requiredPopTopDepId) {
		return( cfbam31PopSubDep1Repository.findByPopTopDepIdx(requiredPopTopDepId));
	}

	/**
	 *	ICFBamPopSubDep1ByPopTopDepIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamPopSubDep1ByPopTopDepIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaPopSubDep1> findByPopTopDepIdx(ICFBamPopSubDep1ByPopTopDepIdxKey key) {
		return( cfbam31PopSubDep1Repository.findByPopTopDepIdx(key.getRequiredPopTopDepId()));
	}

	/**
	 *	Find an entity using the columns of the ICFBamPopSubDep1ByUNameIdxKey as arguments.
	 *
	 *		@param requiredPopTopDepId
	 *		@param requiredName
	 *
	 *		@return The found entity, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaPopSubDep1 findByUNameIdx(@Param("popTopDepId") CFLibDbKeyHash256 requiredPopTopDepId,
		@Param("name") String requiredName) {
		return( cfbam31PopSubDep1Repository.findByUNameIdx(requiredPopTopDepId,
			requiredName));
	}

	/**
	 *	ICFBamPopSubDep1ByUNameIdxKey entity finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamPopSubDep1ByUNameIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaPopSubDep1 findByUNameIdx(ICFBamPopSubDep1ByUNameIdxKey key) {
		return( cfbam31PopSubDep1Repository.findByUNameIdx(key.getRequiredPopTopDepId(), key.getRequiredName()));
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
	public CFBamJpaPopSubDep1 lockByIdIdx(@Param("id") CFLibDbKeyHash256 requiredId) {
		return( cfbam31PopSubDep1Repository.lockByIdIdx(requiredId));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredTenantId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaPopSubDep1> lockByTenantIdx(@Param("tenantId") CFLibDbKeyHash256 requiredTenantId) {
		return( cfbam31PopSubDep1Repository.lockByTenantIdx(requiredTenantId));
	}

	/**
	 *	ICFBamScopeByTenantIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaPopSubDep1> lockByTenantIdx(ICFBamScopeByTenantIdxKey key) {
		return( cfbam31PopSubDep1Repository.lockByTenantIdx(key.getRequiredTenantId()));
	}

	// CFBamPopDep specified lock-by-index methods

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredRelationId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaPopSubDep1> lockByRelationIdx(@Param("relationId") CFLibDbKeyHash256 requiredRelationId) {
		return( cfbam31PopSubDep1Repository.lockByRelationIdx(requiredRelationId));
	}

	/**
	 *	ICFBamPopDepByRelationIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaPopSubDep1> lockByRelationIdx(ICFBamPopDepByRelationIdxKey key) {
		return( cfbam31PopSubDep1Repository.lockByRelationIdx(key.getRequiredRelationId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalDefSchemaId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaPopSubDep1> lockByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId) {
		return( cfbam31PopSubDep1Repository.lockByDefSchemaIdx(optionalDefSchemaId));
	}

	/**
	 *	ICFBamPopDepByDefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaPopSubDep1> lockByDefSchemaIdx(ICFBamPopDepByDefSchemaIdxKey key) {
		return( cfbam31PopSubDep1Repository.lockByDefSchemaIdx(key.getOptionalDefSchemaId()));
	}

	// CFBamPopSubDep1 specified lock-by-index methods

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredPopTopDepId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaPopSubDep1> lockByPopTopDepIdx(@Param("popTopDepId") CFLibDbKeyHash256 requiredPopTopDepId) {
		return( cfbam31PopSubDep1Repository.lockByPopTopDepIdx(requiredPopTopDepId));
	}

	/**
	 *	ICFBamPopSubDep1ByPopTopDepIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaPopSubDep1> lockByPopTopDepIdx(ICFBamPopSubDep1ByPopTopDepIdxKey key) {
		return( cfbam31PopSubDep1Repository.lockByPopTopDepIdx(key.getRequiredPopTopDepId()));
	}

	/**
	 *	Argument-based lock database entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredPopTopDepId
	 *		@param requiredName
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaPopSubDep1 lockByUNameIdx(@Param("popTopDepId") CFLibDbKeyHash256 requiredPopTopDepId,
		@Param("name") String requiredName) {
		return( cfbam31PopSubDep1Repository.lockByUNameIdx(requiredPopTopDepId,
			requiredName));
	}

	/**
	 *	ICFBamPopSubDep1ByUNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaPopSubDep1 lockByUNameIdx(ICFBamPopSubDep1ByUNameIdxKey key) {
		return( cfbam31PopSubDep1Repository.lockByUNameIdx(key.getRequiredPopTopDepId(), key.getRequiredName()));
	}

	// CFBamScope specified delete-by-index methods

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByIdIdx(@Param("id") CFLibDbKeyHash256 requiredId) {
		cfbam31PopSubDep1Repository.deleteByIdIdx(requiredId);
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredTenantId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByTenantIdx(@Param("tenantId") CFLibDbKeyHash256 requiredTenantId) {
		cfbam31PopSubDep1Repository.deleteByTenantIdx(requiredTenantId);
	}

	/**
	 *	ICFBamScopeByTenantIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamScopeByTenantIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByTenantIdx(ICFBamScopeByTenantIdxKey key) {
		cfbam31PopSubDep1Repository.deleteByTenantIdx(key.getRequiredTenantId());
	}

	// CFBamPopDep specified delete-by-index methods

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredRelationId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByRelationIdx(@Param("relationId") CFLibDbKeyHash256 requiredRelationId) {
		cfbam31PopSubDep1Repository.deleteByRelationIdx(requiredRelationId);
	}

	/**
	 *	ICFBamPopDepByRelationIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamPopDepByRelationIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByRelationIdx(ICFBamPopDepByRelationIdxKey key) {
		cfbam31PopSubDep1Repository.deleteByRelationIdx(key.getRequiredRelationId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalDefSchemaId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId) {
		cfbam31PopSubDep1Repository.deleteByDefSchemaIdx(optionalDefSchemaId);
	}

	/**
	 *	ICFBamPopDepByDefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamPopDepByDefSchemaIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByDefSchemaIdx(ICFBamPopDepByDefSchemaIdxKey key) {
		cfbam31PopSubDep1Repository.deleteByDefSchemaIdx(key.getOptionalDefSchemaId());
	}

	// CFBamPopSubDep1 specified delete-by-index methods

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredPopTopDepId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByPopTopDepIdx(@Param("popTopDepId") CFLibDbKeyHash256 requiredPopTopDepId) {
		cfbam31PopSubDep1Repository.deleteByPopTopDepIdx(requiredPopTopDepId);
	}

	/**
	 *	ICFBamPopSubDep1ByPopTopDepIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamPopSubDep1ByPopTopDepIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByPopTopDepIdx(ICFBamPopSubDep1ByPopTopDepIdxKey key) {
		cfbam31PopSubDep1Repository.deleteByPopTopDepIdx(key.getRequiredPopTopDepId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredPopTopDepId
	 *		@param requiredName
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByUNameIdx(@Param("popTopDepId") CFLibDbKeyHash256 requiredPopTopDepId,
		@Param("name") String requiredName) {
		cfbam31PopSubDep1Repository.deleteByUNameIdx(requiredPopTopDepId,
			requiredName);
	}

	/**
	 *	ICFBamPopSubDep1ByUNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamPopSubDep1ByUNameIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByUNameIdx(ICFBamPopSubDep1ByUNameIdxKey key) {
		cfbam31PopSubDep1Repository.deleteByUNameIdx(key.getRequiredPopTopDepId(), key.getRequiredName());
	}

}
