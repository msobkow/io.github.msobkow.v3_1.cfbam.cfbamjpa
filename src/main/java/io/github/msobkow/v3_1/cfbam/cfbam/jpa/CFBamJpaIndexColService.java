// Description: Java 25 Spring JPA Service for IndexCol

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
 *	Service for the CFBamIndexCol entities defined in io.github.msobkow.v3_1.cfbam.cfbam.jpa
 *	using the CFBamIndexColRepository to access them.
 */
@Service("cfbam31JpaIndexColService")
public class CFBamJpaIndexColService {

	@Autowired
	@Qualifier("cfbam31EntityManagerFactory")
	private LocalContainerEntityManagerFactoryBean cfbam31EntityManagerFactory;

	@Autowired
	private CFBamJpaIndexColRepository cfbam31IndexColRepository;

	/**
	 *	Create an entity, generating any database keys required along the way.
	 *
	 *		@param	data	The entity to be instantiated; must be a specific instance of CFBamJpaIndexCol, not a subclass.
	 *
	 *		@return The updated/created entity.
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaIndexCol create(CFBamJpaIndexCol data) {
		final String S_ProcName = "create";
		if (data == null) {
			return( null );
		}
		CFLibDbKeyHash256 originalRequiredId = data.getRequiredId();
		boolean generatedRequiredId = false;
		if(data.getRequiredIndexId() == null || data.getRequiredIndexId().isNull()) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredIndexId");
		}
		if(data.getRequiredName() == null) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredName");
		}
		if(data.getRequiredColumnId() == null || data.getRequiredColumnId().isNull()) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredColumnId");
		}
		try {
			if (data.getRequiredId() == null || data.getRequiredId().isNull()) {
				data.setRequiredId(new CFLibDbKeyHash256(0));
				generatedRequiredId = true;
			}
			LocalDateTime now = LocalDateTime.now();
			data.setCreatedAt(now);
			data.setUpdatedAt(now);
			if(data.getPKey() != null && cfbam31IndexColRepository.existsById((CFLibDbKeyHash256)data.getPKey())) {
				return( (CFBamJpaIndexCol)(cfbam31IndexColRepository.findById((CFLibDbKeyHash256)(data.getPKey())).get()));
			}
			return cfbam31IndexColRepository.save(data);
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
	public CFBamJpaIndexCol update(CFBamJpaIndexCol data) {
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
		if(data.getRequiredIndexId() == null || data.getRequiredIndexId().isNull()) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredIndexId");
		}
		if(data.getRequiredName() == null) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredName");
		}
		if(data.getRequiredColumnId() == null || data.getRequiredColumnId().isNull()) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredColumnId");
		}
		// Ensure the entity exists and that the revision matches
		CFBamJpaIndexCol existing = cfbam31IndexColRepository.findById((CFLibDbKeyHash256)(data.getPKey()))
			.orElseThrow(() -> new CFLibCollisionDetectedException(getClass(), S_ProcName, data.getPKey()));
		if (existing.getRequiredRevision() != data.getRequiredRevision()) {
			throw new CFLibCollisionDetectedException(getClass(), S_ProcName, data.getPKey());
		}
		// Apply superior data relationships of CFBamIndexCol to existing object
		existing.setRequiredContainerIndex(data.getRequiredContainerIndex());
		existing.setOptionalLookupDefSchema(data.getOptionalLookupDefSchema());
		existing.setOptionalLookupPrev(data.getOptionalLookupPrev());
		existing.setOptionalLookupNext(data.getOptionalLookupNext());
		existing.setRequiredLookupColumn(data.getRequiredLookupColumn());
		// Apply data columns of CFBamIndexCol to existing object
		existing.setRequiredName(data.getRequiredName());
		existing.setOptionalShortName(data.getOptionalShortName());
		existing.setOptionalLabel(data.getOptionalLabel());
		existing.setOptionalShortDescription(data.getOptionalShortDescription());
		existing.setOptionalDescription(data.getOptionalDescription());
		existing.setRequiredIsAscending(data.getRequiredIsAscending());
		// Update the audit columns
		data.setUpdatedAt(LocalDateTime.now());
		// Save the changes we've made
		return cfbam31IndexColRepository.save(existing);
	}

	/**
	 *	Argument-based find database instance for compatibility with the current MSS code factory code base.
	 *
	 *		@param requiredId
	 *
	 *		@return The retrieved entity, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaIndexCol find(@Param("id") CFLibDbKeyHash256 requiredId) {
		return( cfbam31IndexColRepository.get(requiredId));
	}

	/**
	 *	Retrieve all entities from the repository
	 *
	 *		@return The list of retrieved entities, which may be empty
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaIndexCol> findAll() {
		return( cfbam31IndexColRepository.findAll() );
	}

	// CFBamIndexCol specified index finders

	/**
	 *	Find an entity using the columns of the ICFBamIndexColByUNameIdxKey as arguments.
	 *
	 *		@param requiredIndexId
	 *		@param requiredName
	 *
	 *		@return The found entity, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaIndexCol findByUNameIdx(@Param("indexId") CFLibDbKeyHash256 requiredIndexId,
		@Param("name") String requiredName) {
		return( cfbam31IndexColRepository.findByUNameIdx(requiredIndexId,
			requiredName));
	}

	/**
	 *	ICFBamIndexColByUNameIdxKey entity finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamIndexColByUNameIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaIndexCol findByUNameIdx(ICFBamIndexColByUNameIdxKey key) {
		return( cfbam31IndexColRepository.findByUNameIdx(key.getRequiredIndexId(), key.getRequiredName()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamIndexColByIndexIdxKey as arguments.
	 *
	 *		@param requiredIndexId
	 *
	 *		@return List&lt;CFBamJpaIndexCol&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaIndexCol> findByIndexIdx(@Param("indexId") CFLibDbKeyHash256 requiredIndexId) {
		return( cfbam31IndexColRepository.findByIndexIdx(requiredIndexId));
	}

	/**
	 *	ICFBamIndexColByIndexIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamIndexColByIndexIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaIndexCol> findByIndexIdx(ICFBamIndexColByIndexIdxKey key) {
		return( cfbam31IndexColRepository.findByIndexIdx(key.getRequiredIndexId()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamIndexColByDefSchemaIdxKey as arguments.
	 *
	 *		@param optionalDefSchemaId
	 *
	 *		@return List&lt;CFBamJpaIndexCol&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaIndexCol> findByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId) {
		return( cfbam31IndexColRepository.findByDefSchemaIdx(optionalDefSchemaId));
	}

	/**
	 *	ICFBamIndexColByDefSchemaIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamIndexColByDefSchemaIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaIndexCol> findByDefSchemaIdx(ICFBamIndexColByDefSchemaIdxKey key) {
		return( cfbam31IndexColRepository.findByDefSchemaIdx(key.getOptionalDefSchemaId()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamIndexColByColIdxKey as arguments.
	 *
	 *		@param requiredColumnId
	 *
	 *		@return List&lt;CFBamJpaIndexCol&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaIndexCol> findByColIdx(@Param("columnId") CFLibDbKeyHash256 requiredColumnId) {
		return( cfbam31IndexColRepository.findByColIdx(requiredColumnId));
	}

	/**
	 *	ICFBamIndexColByColIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamIndexColByColIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaIndexCol> findByColIdx(ICFBamIndexColByColIdxKey key) {
		return( cfbam31IndexColRepository.findByColIdx(key.getRequiredColumnId()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamIndexColByPrevIdxKey as arguments.
	 *
	 *		@param optionalPrevId
	 *
	 *		@return List&lt;CFBamJpaIndexCol&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaIndexCol> findByPrevIdx(@Param("prevId") CFLibDbKeyHash256 optionalPrevId) {
		return( cfbam31IndexColRepository.findByPrevIdx(optionalPrevId));
	}

	/**
	 *	ICFBamIndexColByPrevIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamIndexColByPrevIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaIndexCol> findByPrevIdx(ICFBamIndexColByPrevIdxKey key) {
		return( cfbam31IndexColRepository.findByPrevIdx(key.getOptionalPrevId()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamIndexColByNextIdxKey as arguments.
	 *
	 *		@param optionalNextId
	 *
	 *		@return List&lt;CFBamJpaIndexCol&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaIndexCol> findByNextIdx(@Param("nextId") CFLibDbKeyHash256 optionalNextId) {
		return( cfbam31IndexColRepository.findByNextIdx(optionalNextId));
	}

	/**
	 *	ICFBamIndexColByNextIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamIndexColByNextIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaIndexCol> findByNextIdx(ICFBamIndexColByNextIdxKey key) {
		return( cfbam31IndexColRepository.findByNextIdx(key.getOptionalNextId()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamIndexColByIdxPrevIdxKey as arguments.
	 *
	 *		@param requiredIndexId
	 *		@param optionalPrevId
	 *
	 *		@return List&lt;CFBamJpaIndexCol&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaIndexCol> findByIdxPrevIdx(@Param("indexId") CFLibDbKeyHash256 requiredIndexId,
		@Param("prevId") CFLibDbKeyHash256 optionalPrevId) {
		return( cfbam31IndexColRepository.findByIdxPrevIdx(requiredIndexId,
			optionalPrevId));
	}

	/**
	 *	ICFBamIndexColByIdxPrevIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamIndexColByIdxPrevIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaIndexCol> findByIdxPrevIdx(ICFBamIndexColByIdxPrevIdxKey key) {
		return( cfbam31IndexColRepository.findByIdxPrevIdx(key.getRequiredIndexId(), key.getOptionalPrevId()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamIndexColByIdxNextIdxKey as arguments.
	 *
	 *		@param requiredIndexId
	 *		@param optionalNextId
	 *
	 *		@return List&lt;CFBamJpaIndexCol&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaIndexCol> findByIdxNextIdx(@Param("indexId") CFLibDbKeyHash256 requiredIndexId,
		@Param("nextId") CFLibDbKeyHash256 optionalNextId) {
		return( cfbam31IndexColRepository.findByIdxNextIdx(requiredIndexId,
			optionalNextId));
	}

	/**
	 *	ICFBamIndexColByIdxNextIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamIndexColByIdxNextIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaIndexCol> findByIdxNextIdx(ICFBamIndexColByIdxNextIdxKey key) {
		return( cfbam31IndexColRepository.findByIdxNextIdx(key.getRequiredIndexId(), key.getOptionalNextId()));
	}

	// CFBamIndexCol specified lock-by-index methods

	/**
	 *	Argument-based lock database entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredId
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaIndexCol lockByIdIdx(@Param("id") CFLibDbKeyHash256 requiredId) {
		return( cfbam31IndexColRepository.lockByIdIdx(requiredId));
	}

	/**
	 *	Argument-based lock database entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredIndexId
	 *		@param requiredName
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaIndexCol lockByUNameIdx(@Param("indexId") CFLibDbKeyHash256 requiredIndexId,
		@Param("name") String requiredName) {
		return( cfbam31IndexColRepository.lockByUNameIdx(requiredIndexId,
			requiredName));
	}

	/**
	 *	ICFBamIndexColByUNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaIndexCol lockByUNameIdx(ICFBamIndexColByUNameIdxKey key) {
		return( cfbam31IndexColRepository.lockByUNameIdx(key.getRequiredIndexId(), key.getRequiredName()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredIndexId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaIndexCol> lockByIndexIdx(@Param("indexId") CFLibDbKeyHash256 requiredIndexId) {
		return( cfbam31IndexColRepository.lockByIndexIdx(requiredIndexId));
	}

	/**
	 *	ICFBamIndexColByIndexIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaIndexCol> lockByIndexIdx(ICFBamIndexColByIndexIdxKey key) {
		return( cfbam31IndexColRepository.lockByIndexIdx(key.getRequiredIndexId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalDefSchemaId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaIndexCol> lockByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId) {
		return( cfbam31IndexColRepository.lockByDefSchemaIdx(optionalDefSchemaId));
	}

	/**
	 *	ICFBamIndexColByDefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaIndexCol> lockByDefSchemaIdx(ICFBamIndexColByDefSchemaIdxKey key) {
		return( cfbam31IndexColRepository.lockByDefSchemaIdx(key.getOptionalDefSchemaId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredColumnId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaIndexCol> lockByColIdx(@Param("columnId") CFLibDbKeyHash256 requiredColumnId) {
		return( cfbam31IndexColRepository.lockByColIdx(requiredColumnId));
	}

	/**
	 *	ICFBamIndexColByColIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaIndexCol> lockByColIdx(ICFBamIndexColByColIdxKey key) {
		return( cfbam31IndexColRepository.lockByColIdx(key.getRequiredColumnId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalPrevId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaIndexCol> lockByPrevIdx(@Param("prevId") CFLibDbKeyHash256 optionalPrevId) {
		return( cfbam31IndexColRepository.lockByPrevIdx(optionalPrevId));
	}

	/**
	 *	ICFBamIndexColByPrevIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaIndexCol> lockByPrevIdx(ICFBamIndexColByPrevIdxKey key) {
		return( cfbam31IndexColRepository.lockByPrevIdx(key.getOptionalPrevId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalNextId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaIndexCol> lockByNextIdx(@Param("nextId") CFLibDbKeyHash256 optionalNextId) {
		return( cfbam31IndexColRepository.lockByNextIdx(optionalNextId));
	}

	/**
	 *	ICFBamIndexColByNextIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaIndexCol> lockByNextIdx(ICFBamIndexColByNextIdxKey key) {
		return( cfbam31IndexColRepository.lockByNextIdx(key.getOptionalNextId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredIndexId
	 *		@param optionalPrevId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaIndexCol> lockByIdxPrevIdx(@Param("indexId") CFLibDbKeyHash256 requiredIndexId,
		@Param("prevId") CFLibDbKeyHash256 optionalPrevId) {
		return( cfbam31IndexColRepository.lockByIdxPrevIdx(requiredIndexId,
			optionalPrevId));
	}

	/**
	 *	ICFBamIndexColByIdxPrevIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaIndexCol> lockByIdxPrevIdx(ICFBamIndexColByIdxPrevIdxKey key) {
		return( cfbam31IndexColRepository.lockByIdxPrevIdx(key.getRequiredIndexId(), key.getOptionalPrevId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredIndexId
	 *		@param optionalNextId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaIndexCol> lockByIdxNextIdx(@Param("indexId") CFLibDbKeyHash256 requiredIndexId,
		@Param("nextId") CFLibDbKeyHash256 optionalNextId) {
		return( cfbam31IndexColRepository.lockByIdxNextIdx(requiredIndexId,
			optionalNextId));
	}

	/**
	 *	ICFBamIndexColByIdxNextIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaIndexCol> lockByIdxNextIdx(ICFBamIndexColByIdxNextIdxKey key) {
		return( cfbam31IndexColRepository.lockByIdxNextIdx(key.getRequiredIndexId(), key.getOptionalNextId()));
	}

	// CFBamIndexCol specified delete-by-index methods

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByIdIdx(@Param("id") CFLibDbKeyHash256 requiredId) {
		cfbam31IndexColRepository.deleteByIdIdx(requiredId);
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredIndexId
	 *		@param requiredName
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByUNameIdx(@Param("indexId") CFLibDbKeyHash256 requiredIndexId,
		@Param("name") String requiredName) {
		cfbam31IndexColRepository.deleteByUNameIdx(requiredIndexId,
			requiredName);
	}

	/**
	 *	ICFBamIndexColByUNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamIndexColByUNameIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByUNameIdx(ICFBamIndexColByUNameIdxKey key) {
		cfbam31IndexColRepository.deleteByUNameIdx(key.getRequiredIndexId(), key.getRequiredName());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredIndexId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByIndexIdx(@Param("indexId") CFLibDbKeyHash256 requiredIndexId) {
		cfbam31IndexColRepository.deleteByIndexIdx(requiredIndexId);
	}

	/**
	 *	ICFBamIndexColByIndexIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamIndexColByIndexIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByIndexIdx(ICFBamIndexColByIndexIdxKey key) {
		cfbam31IndexColRepository.deleteByIndexIdx(key.getRequiredIndexId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalDefSchemaId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId) {
		cfbam31IndexColRepository.deleteByDefSchemaIdx(optionalDefSchemaId);
	}

	/**
	 *	ICFBamIndexColByDefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamIndexColByDefSchemaIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByDefSchemaIdx(ICFBamIndexColByDefSchemaIdxKey key) {
		cfbam31IndexColRepository.deleteByDefSchemaIdx(key.getOptionalDefSchemaId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredColumnId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByColIdx(@Param("columnId") CFLibDbKeyHash256 requiredColumnId) {
		cfbam31IndexColRepository.deleteByColIdx(requiredColumnId);
	}

	/**
	 *	ICFBamIndexColByColIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamIndexColByColIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByColIdx(ICFBamIndexColByColIdxKey key) {
		cfbam31IndexColRepository.deleteByColIdx(key.getRequiredColumnId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalPrevId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByPrevIdx(@Param("prevId") CFLibDbKeyHash256 optionalPrevId) {
		cfbam31IndexColRepository.deleteByPrevIdx(optionalPrevId);
	}

	/**
	 *	ICFBamIndexColByPrevIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamIndexColByPrevIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByPrevIdx(ICFBamIndexColByPrevIdxKey key) {
		cfbam31IndexColRepository.deleteByPrevIdx(key.getOptionalPrevId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalNextId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByNextIdx(@Param("nextId") CFLibDbKeyHash256 optionalNextId) {
		cfbam31IndexColRepository.deleteByNextIdx(optionalNextId);
	}

	/**
	 *	ICFBamIndexColByNextIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamIndexColByNextIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByNextIdx(ICFBamIndexColByNextIdxKey key) {
		cfbam31IndexColRepository.deleteByNextIdx(key.getOptionalNextId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredIndexId
	 *		@param optionalPrevId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByIdxPrevIdx(@Param("indexId") CFLibDbKeyHash256 requiredIndexId,
		@Param("prevId") CFLibDbKeyHash256 optionalPrevId) {
		cfbam31IndexColRepository.deleteByIdxPrevIdx(requiredIndexId,
			optionalPrevId);
	}

	/**
	 *	ICFBamIndexColByIdxPrevIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamIndexColByIdxPrevIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByIdxPrevIdx(ICFBamIndexColByIdxPrevIdxKey key) {
		cfbam31IndexColRepository.deleteByIdxPrevIdx(key.getRequiredIndexId(), key.getOptionalPrevId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredIndexId
	 *		@param optionalNextId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByIdxNextIdx(@Param("indexId") CFLibDbKeyHash256 requiredIndexId,
		@Param("nextId") CFLibDbKeyHash256 optionalNextId) {
		cfbam31IndexColRepository.deleteByIdxNextIdx(requiredIndexId,
			optionalNextId);
	}

	/**
	 *	ICFBamIndexColByIdxNextIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamIndexColByIdxNextIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByIdxNextIdx(ICFBamIndexColByIdxNextIdxKey key) {
		cfbam31IndexColRepository.deleteByIdxNextIdx(key.getRequiredIndexId(), key.getOptionalNextId());
	}
}
