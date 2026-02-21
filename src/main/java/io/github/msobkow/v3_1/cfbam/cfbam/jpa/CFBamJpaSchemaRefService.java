// Description: Java 25 Spring JPA Service for SchemaRef

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
 *	Service for the CFBamSchemaRef entities defined in io.github.msobkow.v3_1.cfbam.cfbam.jpa
 *	using the CFBamSchemaRefRepository to access them.
 */
@Service("cfbam31JpaSchemaRefService")
public class CFBamJpaSchemaRefService {

	@Autowired
	@Qualifier("cfbam31EntityManagerFactory")
	private LocalContainerEntityManagerFactoryBean cfbam31EntityManagerFactory;

	@Autowired
	private CFBamJpaSchemaRefRepository cfbam31SchemaRefRepository;

	/**
	 *	Create an entity, generating any database keys required along the way.
	 *
	 *		@param	data	The entity to be instantiated; must be a specific instance of CFBamJpaSchemaRef, not a subclass.
	 *
	 *		@return The updated/created entity.
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaSchemaRef create(CFBamJpaSchemaRef data) {
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
		if(data.getRequiredSchemaId() == null || data.getRequiredSchemaId().isNull()) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredSchemaId");
		}
		if(data.getRequiredName() == null) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredName");
		}
		if(data.getRequiredRefModelName() == null) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredRefModelName");
		}
		if(data.getRequiredIncludeRoot() == null) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredIncludeRoot");
		}
		try {
			if (data.getRequiredId() == null || data.getRequiredId().isNull()) {
				data.setRequiredId(new CFLibDbKeyHash256(0));
				generatedRequiredId = true;
			}
			LocalDateTime now = LocalDateTime.now();
			data.setCreatedAt(now);
			data.setUpdatedAt(now);
			if(data.getPKey() != null && cfbam31SchemaRefRepository.existsById((CFLibDbKeyHash256)data.getPKey())) {
				return( (CFBamJpaSchemaRef)(cfbam31SchemaRefRepository.findById((CFLibDbKeyHash256)(data.getPKey())).get()));
			}
			return cfbam31SchemaRefRepository.save(data);
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
	public CFBamJpaSchemaRef update(CFBamJpaSchemaRef data) {
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
		if(data.getRequiredSchemaId() == null || data.getRequiredSchemaId().isNull()) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredSchemaId");
		}
		if(data.getRequiredName() == null) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredName");
		}
		if(data.getRequiredRefModelName() == null) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredRefModelName");
		}
		if(data.getRequiredIncludeRoot() == null) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredIncludeRoot");
		}
		// Ensure the entity exists and that the revision matches
		CFBamJpaSchemaRef existing = cfbam31SchemaRefRepository.findById((CFLibDbKeyHash256)(data.getPKey()))
			.orElseThrow(() -> new CFLibCollisionDetectedException(getClass(), S_ProcName, data.getPKey()));
		if (existing.getRequiredRevision() != data.getRequiredRevision()) {
			throw new CFLibCollisionDetectedException(getClass(), S_ProcName, data.getPKey());
		}
		// Apply superior data relationships of CFBamScope to existing object
		existing.setRequiredOwnerTenant(data.getRequiredTenantId());
		// Apply data columns of CFBamScope to existing object
		// Apply superior data relationships of CFBamSchemaRef to existing object
		existing.setRequiredContainerSchema(data.getRequiredContainerSchema());
		existing.setOptionalLookupRefSchema(data.getOptionalLookupRefSchema());
		existing.setOptionalLookupPrev(data.getOptionalLookupPrev());
		existing.setOptionalLookupNext(data.getOptionalLookupNext());
		// Apply data columns of CFBamSchemaRef to existing object
		existing.setRequiredName(data.getRequiredName());
		existing.setRequiredRefModelName(data.getRequiredRefModelName());
		existing.setRequiredIncludeRoot(data.getRequiredIncludeRoot());
		// Update the audit columns
		data.setUpdatedAt(LocalDateTime.now());
		// Save the changes we've made
		return cfbam31SchemaRefRepository.save(existing);
	}

	/**
	 *	Argument-based find database instance for compatibility with the current MSS code factory code base.
	 *
	 *		@param requiredId
	 *
	 *		@return The retrieved entity, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaSchemaRef find(@Param("id") CFLibDbKeyHash256 requiredId) {
		return( cfbam31SchemaRefRepository.get(requiredId));
	}

	/**
	 *	Retrieve all entities from the repository
	 *
	 *		@return The list of retrieved entities, which may be empty
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaSchemaRef> findAll() {
		return( cfbam31SchemaRefRepository.findAll() );
	}

	// CFBamScope specified index finders

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamScopeByTenantIdxKey as arguments.
	 *
	 *		@param requiredTenantId
	 *
	 *		@return List&lt;CFBamJpaSchemaRef&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaSchemaRef> findByTenantIdx(@Param("tenantId") CFLibDbKeyHash256 requiredTenantId) {
		return( cfbam31SchemaRefRepository.findByTenantIdx(requiredTenantId));
	}

	/**
	 *	ICFBamScopeByTenantIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamScopeByTenantIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaSchemaRef> findByTenantIdx(ICFBamScopeByTenantIdxKey key) {
		return( cfbam31SchemaRefRepository.findByTenantIdx(key.getRequiredTenantId()));
	}

	// CFBamSchemaRef specified index finders

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamSchemaRefBySchemaIdxKey as arguments.
	 *
	 *		@param requiredSchemaId
	 *
	 *		@return List&lt;CFBamJpaSchemaRef&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaSchemaRef> findBySchemaIdx(@Param("schemaId") CFLibDbKeyHash256 requiredSchemaId) {
		return( cfbam31SchemaRefRepository.findBySchemaIdx(requiredSchemaId));
	}

	/**
	 *	ICFBamSchemaRefBySchemaIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamSchemaRefBySchemaIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaSchemaRef> findBySchemaIdx(ICFBamSchemaRefBySchemaIdxKey key) {
		return( cfbam31SchemaRefRepository.findBySchemaIdx(key.getRequiredSchemaId()));
	}

	/**
	 *	Find an entity using the columns of the ICFBamSchemaRefByUNameIdxKey as arguments.
	 *
	 *		@param requiredSchemaId
	 *		@param requiredName
	 *
	 *		@return The found entity, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaSchemaRef findByUNameIdx(@Param("schemaId") CFLibDbKeyHash256 requiredSchemaId,
		@Param("name") String requiredName) {
		return( cfbam31SchemaRefRepository.findByUNameIdx(requiredSchemaId,
			requiredName));
	}

	/**
	 *	ICFBamSchemaRefByUNameIdxKey entity finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamSchemaRefByUNameIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaSchemaRef findByUNameIdx(ICFBamSchemaRefByUNameIdxKey key) {
		return( cfbam31SchemaRefRepository.findByUNameIdx(key.getRequiredSchemaId(), key.getRequiredName()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamSchemaRefByRefSchemaIdxKey as arguments.
	 *
	 *		@param optionalRefSchemaId
	 *
	 *		@return List&lt;CFBamJpaSchemaRef&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaSchemaRef> findByRefSchemaIdx(@Param("refSchemaId") CFLibDbKeyHash256 optionalRefSchemaId) {
		return( cfbam31SchemaRefRepository.findByRefSchemaIdx(optionalRefSchemaId));
	}

	/**
	 *	ICFBamSchemaRefByRefSchemaIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamSchemaRefByRefSchemaIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaSchemaRef> findByRefSchemaIdx(ICFBamSchemaRefByRefSchemaIdxKey key) {
		return( cfbam31SchemaRefRepository.findByRefSchemaIdx(key.getOptionalRefSchemaId()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamSchemaRefByPrevIdxKey as arguments.
	 *
	 *		@param optionalPrevId
	 *
	 *		@return List&lt;CFBamJpaSchemaRef&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaSchemaRef> findByPrevIdx(@Param("prevId") CFLibDbKeyHash256 optionalPrevId) {
		return( cfbam31SchemaRefRepository.findByPrevIdx(optionalPrevId));
	}

	/**
	 *	ICFBamSchemaRefByPrevIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamSchemaRefByPrevIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaSchemaRef> findByPrevIdx(ICFBamSchemaRefByPrevIdxKey key) {
		return( cfbam31SchemaRefRepository.findByPrevIdx(key.getOptionalPrevId()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamSchemaRefByNextIdxKey as arguments.
	 *
	 *		@param optionalNextId
	 *
	 *		@return List&lt;CFBamJpaSchemaRef&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaSchemaRef> findByNextIdx(@Param("nextId") CFLibDbKeyHash256 optionalNextId) {
		return( cfbam31SchemaRefRepository.findByNextIdx(optionalNextId));
	}

	/**
	 *	ICFBamSchemaRefByNextIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamSchemaRefByNextIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaSchemaRef> findByNextIdx(ICFBamSchemaRefByNextIdxKey key) {
		return( cfbam31SchemaRefRepository.findByNextIdx(key.getOptionalNextId()));
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
	public CFBamJpaSchemaRef lockByIdIdx(@Param("id") CFLibDbKeyHash256 requiredId) {
		return( cfbam31SchemaRefRepository.lockByIdIdx(requiredId));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredTenantId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaSchemaRef> lockByTenantIdx(@Param("tenantId") CFLibDbKeyHash256 requiredTenantId) {
		return( cfbam31SchemaRefRepository.lockByTenantIdx(requiredTenantId));
	}

	/**
	 *	ICFBamScopeByTenantIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaSchemaRef> lockByTenantIdx(ICFBamScopeByTenantIdxKey key) {
		return( cfbam31SchemaRefRepository.lockByTenantIdx(key.getRequiredTenantId()));
	}

	// CFBamSchemaRef specified lock-by-index methods

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredSchemaId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaSchemaRef> lockBySchemaIdx(@Param("schemaId") CFLibDbKeyHash256 requiredSchemaId) {
		return( cfbam31SchemaRefRepository.lockBySchemaIdx(requiredSchemaId));
	}

	/**
	 *	ICFBamSchemaRefBySchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaSchemaRef> lockBySchemaIdx(ICFBamSchemaRefBySchemaIdxKey key) {
		return( cfbam31SchemaRefRepository.lockBySchemaIdx(key.getRequiredSchemaId()));
	}

	/**
	 *	Argument-based lock database entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredSchemaId
	 *		@param requiredName
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaSchemaRef lockByUNameIdx(@Param("schemaId") CFLibDbKeyHash256 requiredSchemaId,
		@Param("name") String requiredName) {
		return( cfbam31SchemaRefRepository.lockByUNameIdx(requiredSchemaId,
			requiredName));
	}

	/**
	 *	ICFBamSchemaRefByUNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaSchemaRef lockByUNameIdx(ICFBamSchemaRefByUNameIdxKey key) {
		return( cfbam31SchemaRefRepository.lockByUNameIdx(key.getRequiredSchemaId(), key.getRequiredName()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalRefSchemaId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaSchemaRef> lockByRefSchemaIdx(@Param("refSchemaId") CFLibDbKeyHash256 optionalRefSchemaId) {
		return( cfbam31SchemaRefRepository.lockByRefSchemaIdx(optionalRefSchemaId));
	}

	/**
	 *	ICFBamSchemaRefByRefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaSchemaRef> lockByRefSchemaIdx(ICFBamSchemaRefByRefSchemaIdxKey key) {
		return( cfbam31SchemaRefRepository.lockByRefSchemaIdx(key.getOptionalRefSchemaId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalPrevId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaSchemaRef> lockByPrevIdx(@Param("prevId") CFLibDbKeyHash256 optionalPrevId) {
		return( cfbam31SchemaRefRepository.lockByPrevIdx(optionalPrevId));
	}

	/**
	 *	ICFBamSchemaRefByPrevIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaSchemaRef> lockByPrevIdx(ICFBamSchemaRefByPrevIdxKey key) {
		return( cfbam31SchemaRefRepository.lockByPrevIdx(key.getOptionalPrevId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalNextId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaSchemaRef> lockByNextIdx(@Param("nextId") CFLibDbKeyHash256 optionalNextId) {
		return( cfbam31SchemaRefRepository.lockByNextIdx(optionalNextId));
	}

	/**
	 *	ICFBamSchemaRefByNextIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaSchemaRef> lockByNextIdx(ICFBamSchemaRefByNextIdxKey key) {
		return( cfbam31SchemaRefRepository.lockByNextIdx(key.getOptionalNextId()));
	}

	// CFBamScope specified delete-by-index methods

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByIdIdx(@Param("id") CFLibDbKeyHash256 requiredId) {
		cfbam31SchemaRefRepository.deleteByIdIdx(requiredId);
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredTenantId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByTenantIdx(@Param("tenantId") CFLibDbKeyHash256 requiredTenantId) {
		cfbam31SchemaRefRepository.deleteByTenantIdx(requiredTenantId);
	}

	/**
	 *	ICFBamScopeByTenantIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamScopeByTenantIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByTenantIdx(ICFBamScopeByTenantIdxKey key) {
		cfbam31SchemaRefRepository.deleteByTenantIdx(key.getRequiredTenantId());
	}

	// CFBamSchemaRef specified delete-by-index methods

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredSchemaId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteBySchemaIdx(@Param("schemaId") CFLibDbKeyHash256 requiredSchemaId) {
		cfbam31SchemaRefRepository.deleteBySchemaIdx(requiredSchemaId);
	}

	/**
	 *	ICFBamSchemaRefBySchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamSchemaRefBySchemaIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteBySchemaIdx(ICFBamSchemaRefBySchemaIdxKey key) {
		cfbam31SchemaRefRepository.deleteBySchemaIdx(key.getRequiredSchemaId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredSchemaId
	 *		@param requiredName
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByUNameIdx(@Param("schemaId") CFLibDbKeyHash256 requiredSchemaId,
		@Param("name") String requiredName) {
		cfbam31SchemaRefRepository.deleteByUNameIdx(requiredSchemaId,
			requiredName);
	}

	/**
	 *	ICFBamSchemaRefByUNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamSchemaRefByUNameIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByUNameIdx(ICFBamSchemaRefByUNameIdxKey key) {
		cfbam31SchemaRefRepository.deleteByUNameIdx(key.getRequiredSchemaId(), key.getRequiredName());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalRefSchemaId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByRefSchemaIdx(@Param("refSchemaId") CFLibDbKeyHash256 optionalRefSchemaId) {
		cfbam31SchemaRefRepository.deleteByRefSchemaIdx(optionalRefSchemaId);
	}

	/**
	 *	ICFBamSchemaRefByRefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamSchemaRefByRefSchemaIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByRefSchemaIdx(ICFBamSchemaRefByRefSchemaIdxKey key) {
		cfbam31SchemaRefRepository.deleteByRefSchemaIdx(key.getOptionalRefSchemaId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalPrevId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByPrevIdx(@Param("prevId") CFLibDbKeyHash256 optionalPrevId) {
		cfbam31SchemaRefRepository.deleteByPrevIdx(optionalPrevId);
	}

	/**
	 *	ICFBamSchemaRefByPrevIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamSchemaRefByPrevIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByPrevIdx(ICFBamSchemaRefByPrevIdxKey key) {
		cfbam31SchemaRefRepository.deleteByPrevIdx(key.getOptionalPrevId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalNextId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByNextIdx(@Param("nextId") CFLibDbKeyHash256 optionalNextId) {
		cfbam31SchemaRefRepository.deleteByNextIdx(optionalNextId);
	}

	/**
	 *	ICFBamSchemaRefByNextIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamSchemaRefByNextIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByNextIdx(ICFBamSchemaRefByNextIdxKey key) {
		cfbam31SchemaRefRepository.deleteByNextIdx(key.getOptionalNextId());
	}
}
