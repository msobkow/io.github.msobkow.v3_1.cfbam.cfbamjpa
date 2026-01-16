// Description: Java 25 Spring JPA Service for DateCol

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
 *	Service for the CFBamDateCol entities defined in io.github.msobkow.v3_1.cfbam.cfbam.jpa
 *	using the CFBamDateColRepository to access them.
 */
@Service("cfbam31JpaDateColService")
public class CFBamJpaDateColService {

	@Autowired
	@Qualifier("cfbam31EntityManagerFactory")
	private LocalContainerEntityManagerFactoryBean cfbam31EntityManagerFactory;

	@Autowired
	private CFBamJpaDateColRepository cfbam31DateColRepository;

	/**
	 *	Create an entity, generating any database keys required along the way.
	 *
	 *		@param	data	The entity to be instantiated; must be a specific instance of CFBamJpaDateCol, not a subclass.
	 *
	 *		@return The updated/created entity.
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaDateCol create(CFBamJpaDateCol data) {
		final String S_ProcName = "create";
		if (data == null) {
			return( null );
		}
		CFLibDbKeyHash256 originalRequiredId = data.getRequiredId();
		boolean generatedRequiredId = false;
		if(data.getRequiredScopeId() == null || data.getRequiredScopeId().isNull()) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredScopeId");
		}
		if(data.getRequiredName() == null) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredName");
		}
		if(data.getRequiredTableId() == null || data.getRequiredTableId().isNull()) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredTableId");
		}
		try {
			if (data.getRequiredId() == null || data.getRequiredId().isNull()) {
				data.setRequiredId(new CFLibDbKeyHash256(0));
				generatedRequiredId = true;
			}
			LocalDateTime now = LocalDateTime.now();
			data.setCreatedAt(now);
			data.setUpdatedAt(now);
			if(data.getPKey() != null && cfbam31DateColRepository.existsById((CFLibDbKeyHash256)data.getPKey())) {
				return( (CFBamJpaDateCol)(cfbam31DateColRepository.findById((CFLibDbKeyHash256)(data.getPKey())).get()));
			}
			return cfbam31DateColRepository.save(data);
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
	public CFBamJpaDateCol update(CFBamJpaDateCol data) {
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
		if(data.getRequiredScopeId() == null || data.getRequiredScopeId().isNull()) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredScopeId");
		}
		if(data.getRequiredName() == null) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredName");
		}
		if(data.getRequiredTableId() == null || data.getRequiredTableId().isNull()) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredTableId");
		}
		// Ensure the entity exists and that the revision matches
		CFBamJpaDateCol existing = cfbam31DateColRepository.findById((CFLibDbKeyHash256)(data.getPKey()))
			.orElseThrow(() -> new CFLibCollisionDetectedException(getClass(), S_ProcName, data.getPKey()));
		if (existing.getRequiredRevision() != data.getRequiredRevision()) {
			throw new CFLibCollisionDetectedException(getClass(), S_ProcName, data.getPKey());
		}
		// Apply superior data relationships of CFBamValue to existing object
		existing.setRequiredContainerScope(data.getRequiredContainerScope());
		existing.setOptionalLookupDefSchema(data.getOptionalLookupDefSchema());
		existing.setOptionalLookupPrev(data.getOptionalLookupPrev());
		existing.setOptionalLookupNext(data.getOptionalLookupNext());
		// Apply data columns of CFBamValue to existing object
		existing.setRequiredName(data.getRequiredName());
		existing.setOptionalShortName(data.getOptionalShortName());
		existing.setOptionalLabel(data.getOptionalLabel());
		existing.setOptionalShortDescription(data.getOptionalShortDescription());
		existing.setOptionalDescription(data.getOptionalDescription());
		existing.setOptionalDefaultXmlValue(data.getOptionalDefaultXmlValue());
		existing.setRequiredIsNullable(data.getRequiredIsNullable());
		existing.setOptionalGenerateId(data.getOptionalGenerateId());
		existing.setRequiredImplementsPolymorph(data.getRequiredImplementsPolymorph());
		// Apply superior data relationships of CFBamAtom to existing object
		// Apply data columns of CFBamAtom to existing object
		existing.setOptionalDbName(data.getOptionalDbName());
		// Apply superior data relationships of CFBamDateDef to existing object
		// Apply data columns of CFBamDateDef to existing object
		existing.setOptionalDummy(data.getOptionalDummy());
		// Apply superior data relationships of CFBamDateCol to existing object
		existing.setRequiredContainerTable(data.getRequiredContainerTable());
		// Apply data columns of CFBamDateCol to existing object
		// Update the audit columns
		data.setUpdatedAt(LocalDateTime.now());
		// Save the changes we've made
		return cfbam31DateColRepository.save(existing);
	}

	/**
	 *	Argument-based find database instance for compatibility with the current MSS code factory code base.
	 *
	 *		@param requiredId
	 *
	 *		@return The retrieved entity, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaDateCol find(@Param("id") CFLibDbKeyHash256 requiredId) {
		return( cfbam31DateColRepository.get(requiredId));
	}

	/**
	 *	Retrieve all entities from the repository
	 *
	 *		@return The list of retrieved entities, which may be empty
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDateCol> findAll() {
		return( cfbam31DateColRepository.findAll() );
	}

	// CFBamValue specified index finders

	/**
	 *	Find an entity using the columns of the ICFBamValueByUNameIdxKey as arguments.
	 *
	 *		@param requiredScopeId
	 *		@param requiredName
	 *
	 *		@return The found entity, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaDateCol findByUNameIdx(@Param("scopeId") CFLibDbKeyHash256 requiredScopeId,
		@Param("name") String requiredName) {
		return( cfbam31DateColRepository.findByUNameIdx(requiredScopeId,
			requiredName));
	}

	/**
	 *	ICFBamValueByUNameIdxKey entity finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamValueByUNameIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaDateCol findByUNameIdx(ICFBamValueByUNameIdxKey key) {
		return( cfbam31DateColRepository.findByUNameIdx(key.getRequiredScopeId(), key.getRequiredName()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamValueByScopeIdxKey as arguments.
	 *
	 *		@param requiredScopeId
	 *
	 *		@return List&lt;CFBamJpaDateCol&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDateCol> findByScopeIdx(@Param("scopeId") CFLibDbKeyHash256 requiredScopeId) {
		return( cfbam31DateColRepository.findByScopeIdx(requiredScopeId));
	}

	/**
	 *	ICFBamValueByScopeIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamValueByScopeIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDateCol> findByScopeIdx(ICFBamValueByScopeIdxKey key) {
		return( cfbam31DateColRepository.findByScopeIdx(key.getRequiredScopeId()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamValueByDefSchemaIdxKey as arguments.
	 *
	 *		@param optionalDefSchemaId
	 *
	 *		@return List&lt;CFBamJpaDateCol&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDateCol> findByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId) {
		return( cfbam31DateColRepository.findByDefSchemaIdx(optionalDefSchemaId));
	}

	/**
	 *	ICFBamValueByDefSchemaIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamValueByDefSchemaIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDateCol> findByDefSchemaIdx(ICFBamValueByDefSchemaIdxKey key) {
		return( cfbam31DateColRepository.findByDefSchemaIdx(key.getOptionalDefSchemaId()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamValueByPrevIdxKey as arguments.
	 *
	 *		@param optionalPrevId
	 *
	 *		@return List&lt;CFBamJpaDateCol&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDateCol> findByPrevIdx(@Param("prevId") CFLibDbKeyHash256 optionalPrevId) {
		return( cfbam31DateColRepository.findByPrevIdx(optionalPrevId));
	}

	/**
	 *	ICFBamValueByPrevIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamValueByPrevIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDateCol> findByPrevIdx(ICFBamValueByPrevIdxKey key) {
		return( cfbam31DateColRepository.findByPrevIdx(key.getOptionalPrevId()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamValueByNextIdxKey as arguments.
	 *
	 *		@param optionalNextId
	 *
	 *		@return List&lt;CFBamJpaDateCol&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDateCol> findByNextIdx(@Param("nextId") CFLibDbKeyHash256 optionalNextId) {
		return( cfbam31DateColRepository.findByNextIdx(optionalNextId));
	}

	/**
	 *	ICFBamValueByNextIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamValueByNextIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDateCol> findByNextIdx(ICFBamValueByNextIdxKey key) {
		return( cfbam31DateColRepository.findByNextIdx(key.getOptionalNextId()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamValueByContPrevIdxKey as arguments.
	 *
	 *		@param requiredScopeId
	 *		@param optionalPrevId
	 *
	 *		@return List&lt;CFBamJpaDateCol&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDateCol> findByContPrevIdx(@Param("scopeId") CFLibDbKeyHash256 requiredScopeId,
		@Param("prevId") CFLibDbKeyHash256 optionalPrevId) {
		return( cfbam31DateColRepository.findByContPrevIdx(requiredScopeId,
			optionalPrevId));
	}

	/**
	 *	ICFBamValueByContPrevIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamValueByContPrevIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDateCol> findByContPrevIdx(ICFBamValueByContPrevIdxKey key) {
		return( cfbam31DateColRepository.findByContPrevIdx(key.getRequiredScopeId(), key.getOptionalPrevId()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamValueByContNextIdxKey as arguments.
	 *
	 *		@param requiredScopeId
	 *		@param optionalNextId
	 *
	 *		@return List&lt;CFBamJpaDateCol&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDateCol> findByContNextIdx(@Param("scopeId") CFLibDbKeyHash256 requiredScopeId,
		@Param("nextId") CFLibDbKeyHash256 optionalNextId) {
		return( cfbam31DateColRepository.findByContNextIdx(requiredScopeId,
			optionalNextId));
	}

	/**
	 *	ICFBamValueByContNextIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamValueByContNextIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDateCol> findByContNextIdx(ICFBamValueByContNextIdxKey key) {
		return( cfbam31DateColRepository.findByContNextIdx(key.getRequiredScopeId(), key.getOptionalNextId()));
	}

	// CFBamAtom specified index finders

	// CFBamDateDef specified index finders

	// CFBamDateCol specified index finders

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamDateColByTableIdxKey as arguments.
	 *
	 *		@param requiredTableId
	 *
	 *		@return List&lt;CFBamJpaDateCol&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDateCol> findByTableIdx(@Param("tableId") CFLibDbKeyHash256 requiredTableId) {
		return( cfbam31DateColRepository.findByTableIdx(requiredTableId));
	}

	/**
	 *	ICFBamDateColByTableIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamDateColByTableIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDateCol> findByTableIdx(ICFBamDateColByTableIdxKey key) {
		return( cfbam31DateColRepository.findByTableIdx(key.getRequiredTableId()));
	}

	// CFBamValue specified lock-by-index methods

	/**
	 *	Argument-based lock database entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredId
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaDateCol lockByIdIdx(@Param("id") CFLibDbKeyHash256 requiredId) {
		return( cfbam31DateColRepository.lockByIdIdx(requiredId));
	}

	/**
	 *	Argument-based lock database entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredScopeId
	 *		@param requiredName
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaDateCol lockByUNameIdx(@Param("scopeId") CFLibDbKeyHash256 requiredScopeId,
		@Param("name") String requiredName) {
		return( cfbam31DateColRepository.lockByUNameIdx(requiredScopeId,
			requiredName));
	}

	/**
	 *	ICFBamValueByUNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaDateCol lockByUNameIdx(ICFBamValueByUNameIdxKey key) {
		return( cfbam31DateColRepository.lockByUNameIdx(key.getRequiredScopeId(), key.getRequiredName()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredScopeId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDateCol> lockByScopeIdx(@Param("scopeId") CFLibDbKeyHash256 requiredScopeId) {
		return( cfbam31DateColRepository.lockByScopeIdx(requiredScopeId));
	}

	/**
	 *	ICFBamValueByScopeIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDateCol> lockByScopeIdx(ICFBamValueByScopeIdxKey key) {
		return( cfbam31DateColRepository.lockByScopeIdx(key.getRequiredScopeId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalDefSchemaId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDateCol> lockByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId) {
		return( cfbam31DateColRepository.lockByDefSchemaIdx(optionalDefSchemaId));
	}

	/**
	 *	ICFBamValueByDefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDateCol> lockByDefSchemaIdx(ICFBamValueByDefSchemaIdxKey key) {
		return( cfbam31DateColRepository.lockByDefSchemaIdx(key.getOptionalDefSchemaId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalPrevId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDateCol> lockByPrevIdx(@Param("prevId") CFLibDbKeyHash256 optionalPrevId) {
		return( cfbam31DateColRepository.lockByPrevIdx(optionalPrevId));
	}

	/**
	 *	ICFBamValueByPrevIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDateCol> lockByPrevIdx(ICFBamValueByPrevIdxKey key) {
		return( cfbam31DateColRepository.lockByPrevIdx(key.getOptionalPrevId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalNextId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDateCol> lockByNextIdx(@Param("nextId") CFLibDbKeyHash256 optionalNextId) {
		return( cfbam31DateColRepository.lockByNextIdx(optionalNextId));
	}

	/**
	 *	ICFBamValueByNextIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDateCol> lockByNextIdx(ICFBamValueByNextIdxKey key) {
		return( cfbam31DateColRepository.lockByNextIdx(key.getOptionalNextId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredScopeId
	 *		@param optionalPrevId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDateCol> lockByContPrevIdx(@Param("scopeId") CFLibDbKeyHash256 requiredScopeId,
		@Param("prevId") CFLibDbKeyHash256 optionalPrevId) {
		return( cfbam31DateColRepository.lockByContPrevIdx(requiredScopeId,
			optionalPrevId));
	}

	/**
	 *	ICFBamValueByContPrevIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDateCol> lockByContPrevIdx(ICFBamValueByContPrevIdxKey key) {
		return( cfbam31DateColRepository.lockByContPrevIdx(key.getRequiredScopeId(), key.getOptionalPrevId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredScopeId
	 *		@param optionalNextId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDateCol> lockByContNextIdx(@Param("scopeId") CFLibDbKeyHash256 requiredScopeId,
		@Param("nextId") CFLibDbKeyHash256 optionalNextId) {
		return( cfbam31DateColRepository.lockByContNextIdx(requiredScopeId,
			optionalNextId));
	}

	/**
	 *	ICFBamValueByContNextIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDateCol> lockByContNextIdx(ICFBamValueByContNextIdxKey key) {
		return( cfbam31DateColRepository.lockByContNextIdx(key.getRequiredScopeId(), key.getOptionalNextId()));
	}

	// CFBamAtom specified lock-by-index methods

	// CFBamDateDef specified lock-by-index methods

	// CFBamDateCol specified lock-by-index methods

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredTableId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDateCol> lockByTableIdx(@Param("tableId") CFLibDbKeyHash256 requiredTableId) {
		return( cfbam31DateColRepository.lockByTableIdx(requiredTableId));
	}

	/**
	 *	ICFBamDateColByTableIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDateCol> lockByTableIdx(ICFBamDateColByTableIdxKey key) {
		return( cfbam31DateColRepository.lockByTableIdx(key.getRequiredTableId()));
	}

	// CFBamValue specified delete-by-index methods

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByIdIdx(@Param("id") CFLibDbKeyHash256 requiredId) {
		cfbam31DateColRepository.deleteByIdIdx(requiredId);
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredScopeId
	 *		@param requiredName
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByUNameIdx(@Param("scopeId") CFLibDbKeyHash256 requiredScopeId,
		@Param("name") String requiredName) {
		cfbam31DateColRepository.deleteByUNameIdx(requiredScopeId,
			requiredName);
	}

	/**
	 *	ICFBamValueByUNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamValueByUNameIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByUNameIdx(ICFBamValueByUNameIdxKey key) {
		cfbam31DateColRepository.deleteByUNameIdx(key.getRequiredScopeId(), key.getRequiredName());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredScopeId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByScopeIdx(@Param("scopeId") CFLibDbKeyHash256 requiredScopeId) {
		cfbam31DateColRepository.deleteByScopeIdx(requiredScopeId);
	}

	/**
	 *	ICFBamValueByScopeIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamValueByScopeIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByScopeIdx(ICFBamValueByScopeIdxKey key) {
		cfbam31DateColRepository.deleteByScopeIdx(key.getRequiredScopeId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalDefSchemaId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId) {
		cfbam31DateColRepository.deleteByDefSchemaIdx(optionalDefSchemaId);
	}

	/**
	 *	ICFBamValueByDefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamValueByDefSchemaIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByDefSchemaIdx(ICFBamValueByDefSchemaIdxKey key) {
		cfbam31DateColRepository.deleteByDefSchemaIdx(key.getOptionalDefSchemaId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalPrevId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByPrevIdx(@Param("prevId") CFLibDbKeyHash256 optionalPrevId) {
		cfbam31DateColRepository.deleteByPrevIdx(optionalPrevId);
	}

	/**
	 *	ICFBamValueByPrevIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamValueByPrevIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByPrevIdx(ICFBamValueByPrevIdxKey key) {
		cfbam31DateColRepository.deleteByPrevIdx(key.getOptionalPrevId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalNextId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByNextIdx(@Param("nextId") CFLibDbKeyHash256 optionalNextId) {
		cfbam31DateColRepository.deleteByNextIdx(optionalNextId);
	}

	/**
	 *	ICFBamValueByNextIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamValueByNextIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByNextIdx(ICFBamValueByNextIdxKey key) {
		cfbam31DateColRepository.deleteByNextIdx(key.getOptionalNextId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredScopeId
	 *		@param optionalPrevId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByContPrevIdx(@Param("scopeId") CFLibDbKeyHash256 requiredScopeId,
		@Param("prevId") CFLibDbKeyHash256 optionalPrevId) {
		cfbam31DateColRepository.deleteByContPrevIdx(requiredScopeId,
			optionalPrevId);
	}

	/**
	 *	ICFBamValueByContPrevIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamValueByContPrevIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByContPrevIdx(ICFBamValueByContPrevIdxKey key) {
		cfbam31DateColRepository.deleteByContPrevIdx(key.getRequiredScopeId(), key.getOptionalPrevId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredScopeId
	 *		@param optionalNextId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByContNextIdx(@Param("scopeId") CFLibDbKeyHash256 requiredScopeId,
		@Param("nextId") CFLibDbKeyHash256 optionalNextId) {
		cfbam31DateColRepository.deleteByContNextIdx(requiredScopeId,
			optionalNextId);
	}

	/**
	 *	ICFBamValueByContNextIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamValueByContNextIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByContNextIdx(ICFBamValueByContNextIdxKey key) {
		cfbam31DateColRepository.deleteByContNextIdx(key.getRequiredScopeId(), key.getOptionalNextId());
	}

	// CFBamAtom specified delete-by-index methods

	// CFBamDateDef specified delete-by-index methods

	// CFBamDateCol specified delete-by-index methods

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredTableId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByTableIdx(@Param("tableId") CFLibDbKeyHash256 requiredTableId) {
		cfbam31DateColRepository.deleteByTableIdx(requiredTableId);
	}

	/**
	 *	ICFBamDateColByTableIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamDateColByTableIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByTableIdx(ICFBamDateColByTableIdxKey key) {
		cfbam31DateColRepository.deleteByTableIdx(key.getRequiredTableId());
	}

}
