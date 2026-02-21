// Description: Java 25 Spring JPA Service for DbKeyHash256Gen

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
 *	Service for the CFBamDbKeyHash256Gen entities defined in io.github.msobkow.v3_1.cfbam.cfbam.jpa
 *	using the CFBamDbKeyHash256GenRepository to access them.
 */
@Service("cfbam31JpaDbKeyHash256GenService")
public class CFBamJpaDbKeyHash256GenService {

	@Autowired
	@Qualifier("cfbam31EntityManagerFactory")
	private LocalContainerEntityManagerFactoryBean cfbam31EntityManagerFactory;

	@Autowired
	private CFBamJpaDbKeyHash256GenRepository cfbam31DbKeyHash256GenRepository;

	/**
	 *	Create an entity, generating any database keys required along the way.
	 *
	 *		@param	data	The entity to be instantiated; must be a specific instance of CFBamJpaDbKeyHash256Gen, not a subclass.
	 *
	 *		@return The updated/created entity.
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaDbKeyHash256Gen create(CFBamJpaDbKeyHash256Gen data) {
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
		if(data.getRequiredSchemaDefId() == null || data.getRequiredSchemaDefId().isNull()) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredSchemaDefId");
		}
		if( data.getRequiredSlice() < ICFBamDbKeyHash256Gen.SLICE_MIN_VALUE ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"getRequiredSlice()",
				data.getRequiredSlice(),
				ICFBamDbKeyHash256Gen.SLICE_MIN_VALUE );
		}
		if( data.getRequiredSlice() > ICFBamDbKeyHash256Gen.SLICE_MAX_VALUE ) {
			throw new CFLibArgumentOverflowException( getClass(),
				S_ProcName,
				0,
				"getRequiredSlice()",
				data.getRequiredSlice(),
				ICFBamDbKeyHash256Gen.SLICE_MAX_VALUE );
		}
		if( data.getRequiredBlockSize() < ICFBamDbKeyHash256Gen.BLOCKSIZE_MIN_VALUE ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"getRequiredBlockSize()",
				data.getRequiredBlockSize(),
				ICFBamDbKeyHash256Gen.BLOCKSIZE_MIN_VALUE );
		}
		if( data.getRequiredBlockSize() > ICFBamDbKeyHash256Gen.BLOCKSIZE_MAX_VALUE ) {
			throw new CFLibArgumentOverflowException( getClass(),
				S_ProcName,
				0,
				"getRequiredBlockSize()",
				data.getRequiredBlockSize(),
				ICFBamDbKeyHash256Gen.BLOCKSIZE_MAX_VALUE );
		}
		try {
			if (data.getRequiredId() == null || data.getRequiredId().isNull()) {
				data.setRequiredId(new CFLibDbKeyHash256(0));
				generatedRequiredId = true;
			}
			LocalDateTime now = LocalDateTime.now();
			data.setCreatedAt(now);
			data.setUpdatedAt(now);
			if(data.getPKey() != null && cfbam31DbKeyHash256GenRepository.existsById((CFLibDbKeyHash256)data.getPKey())) {
				return( (CFBamJpaDbKeyHash256Gen)(cfbam31DbKeyHash256GenRepository.findById((CFLibDbKeyHash256)(data.getPKey())).get()));
			}
			return cfbam31DbKeyHash256GenRepository.save(data);
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
	public CFBamJpaDbKeyHash256Gen update(CFBamJpaDbKeyHash256Gen data) {
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
		if(data.getRequiredSchemaDefId() == null || data.getRequiredSchemaDefId().isNull()) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredSchemaDefId");
		}
		if( data.getRequiredSlice() < ICFBamDbKeyHash256Gen.SLICE_MIN_VALUE ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"getRequiredSlice()",
				data.getRequiredSlice(),
				ICFBamDbKeyHash256Gen.SLICE_MIN_VALUE );
		}
		if( data.getRequiredSlice() > ICFBamDbKeyHash256Gen.SLICE_MAX_VALUE ) {
			throw new CFLibArgumentOverflowException( getClass(),
				S_ProcName,
				0,
				"getRequiredSlice()",
				data.getRequiredSlice(),
				ICFBamDbKeyHash256Gen.SLICE_MAX_VALUE );
		}
		if( data.getRequiredBlockSize() < ICFBamDbKeyHash256Gen.BLOCKSIZE_MIN_VALUE ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"getRequiredBlockSize()",
				data.getRequiredBlockSize(),
				ICFBamDbKeyHash256Gen.BLOCKSIZE_MIN_VALUE );
		}
		if( data.getRequiredBlockSize() > ICFBamDbKeyHash256Gen.BLOCKSIZE_MAX_VALUE ) {
			throw new CFLibArgumentOverflowException( getClass(),
				S_ProcName,
				0,
				"getRequiredBlockSize()",
				data.getRequiredBlockSize(),
				ICFBamDbKeyHash256Gen.BLOCKSIZE_MAX_VALUE );
		}
		// Ensure the entity exists and that the revision matches
		CFBamJpaDbKeyHash256Gen existing = cfbam31DbKeyHash256GenRepository.findById((CFLibDbKeyHash256)(data.getPKey()))
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
		// Apply superior data relationships of CFBamDbKeyHash256Def to existing object
		// Apply data columns of CFBamDbKeyHash256Def to existing object
		existing.setOptionalInitValue(data.getOptionalInitValue());
		// Apply superior data relationships of CFBamDbKeyHash256Type to existing object
		existing.setRequiredContainerSchemaDef(data.getRequiredContainerSchemaDef());
		// Apply data columns of CFBamDbKeyHash256Type to existing object
		// Apply superior data relationships of CFBamDbKeyHash256Gen to existing object
		// Apply data columns of CFBamDbKeyHash256Gen to existing object
		existing.setRequiredSlice(data.getRequiredSlice());
		existing.setRequiredBlockSize(data.getRequiredBlockSize());
		// Update the audit columns
		data.setUpdatedAt(LocalDateTime.now());
		// Save the changes we've made
		return cfbam31DbKeyHash256GenRepository.save(existing);
	}

	/**
	 *	Argument-based find database instance for compatibility with the current MSS code factory code base.
	 *
	 *		@param requiredId
	 *
	 *		@return The retrieved entity, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaDbKeyHash256Gen find(@Param("id") CFLibDbKeyHash256 requiredId) {
		return( cfbam31DbKeyHash256GenRepository.get(requiredId));
	}

	/**
	 *	Retrieve all entities from the repository
	 *
	 *		@return The list of retrieved entities, which may be empty
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDbKeyHash256Gen> findAll() {
		return( cfbam31DbKeyHash256GenRepository.findAll() );
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
	public CFBamJpaDbKeyHash256Gen findByUNameIdx(@Param("scopeId") CFLibDbKeyHash256 requiredScopeId,
		@Param("name") String requiredName) {
		return( cfbam31DbKeyHash256GenRepository.findByUNameIdx(requiredScopeId,
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
	public CFBamJpaDbKeyHash256Gen findByUNameIdx(ICFBamValueByUNameIdxKey key) {
		return( cfbam31DbKeyHash256GenRepository.findByUNameIdx(key.getRequiredScopeId(), key.getRequiredName()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamValueByScopeIdxKey as arguments.
	 *
	 *		@param requiredScopeId
	 *
	 *		@return List&lt;CFBamJpaDbKeyHash256Gen&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDbKeyHash256Gen> findByScopeIdx(@Param("scopeId") CFLibDbKeyHash256 requiredScopeId) {
		return( cfbam31DbKeyHash256GenRepository.findByScopeIdx(requiredScopeId));
	}

	/**
	 *	ICFBamValueByScopeIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamValueByScopeIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDbKeyHash256Gen> findByScopeIdx(ICFBamValueByScopeIdxKey key) {
		return( cfbam31DbKeyHash256GenRepository.findByScopeIdx(key.getRequiredScopeId()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamValueByDefSchemaIdxKey as arguments.
	 *
	 *		@param optionalDefSchemaId
	 *
	 *		@return List&lt;CFBamJpaDbKeyHash256Gen&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDbKeyHash256Gen> findByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId) {
		return( cfbam31DbKeyHash256GenRepository.findByDefSchemaIdx(optionalDefSchemaId));
	}

	/**
	 *	ICFBamValueByDefSchemaIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamValueByDefSchemaIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDbKeyHash256Gen> findByDefSchemaIdx(ICFBamValueByDefSchemaIdxKey key) {
		return( cfbam31DbKeyHash256GenRepository.findByDefSchemaIdx(key.getOptionalDefSchemaId()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamValueByPrevIdxKey as arguments.
	 *
	 *		@param optionalPrevId
	 *
	 *		@return List&lt;CFBamJpaDbKeyHash256Gen&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDbKeyHash256Gen> findByPrevIdx(@Param("prevId") CFLibDbKeyHash256 optionalPrevId) {
		return( cfbam31DbKeyHash256GenRepository.findByPrevIdx(optionalPrevId));
	}

	/**
	 *	ICFBamValueByPrevIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamValueByPrevIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDbKeyHash256Gen> findByPrevIdx(ICFBamValueByPrevIdxKey key) {
		return( cfbam31DbKeyHash256GenRepository.findByPrevIdx(key.getOptionalPrevId()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamValueByNextIdxKey as arguments.
	 *
	 *		@param optionalNextId
	 *
	 *		@return List&lt;CFBamJpaDbKeyHash256Gen&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDbKeyHash256Gen> findByNextIdx(@Param("nextId") CFLibDbKeyHash256 optionalNextId) {
		return( cfbam31DbKeyHash256GenRepository.findByNextIdx(optionalNextId));
	}

	/**
	 *	ICFBamValueByNextIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamValueByNextIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDbKeyHash256Gen> findByNextIdx(ICFBamValueByNextIdxKey key) {
		return( cfbam31DbKeyHash256GenRepository.findByNextIdx(key.getOptionalNextId()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamValueByContPrevIdxKey as arguments.
	 *
	 *		@param requiredScopeId
	 *		@param optionalPrevId
	 *
	 *		@return List&lt;CFBamJpaDbKeyHash256Gen&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDbKeyHash256Gen> findByContPrevIdx(@Param("scopeId") CFLibDbKeyHash256 requiredScopeId,
		@Param("prevId") CFLibDbKeyHash256 optionalPrevId) {
		return( cfbam31DbKeyHash256GenRepository.findByContPrevIdx(requiredScopeId,
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
	public List<CFBamJpaDbKeyHash256Gen> findByContPrevIdx(ICFBamValueByContPrevIdxKey key) {
		return( cfbam31DbKeyHash256GenRepository.findByContPrevIdx(key.getRequiredScopeId(), key.getOptionalPrevId()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamValueByContNextIdxKey as arguments.
	 *
	 *		@param requiredScopeId
	 *		@param optionalNextId
	 *
	 *		@return List&lt;CFBamJpaDbKeyHash256Gen&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDbKeyHash256Gen> findByContNextIdx(@Param("scopeId") CFLibDbKeyHash256 requiredScopeId,
		@Param("nextId") CFLibDbKeyHash256 optionalNextId) {
		return( cfbam31DbKeyHash256GenRepository.findByContNextIdx(requiredScopeId,
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
	public List<CFBamJpaDbKeyHash256Gen> findByContNextIdx(ICFBamValueByContNextIdxKey key) {
		return( cfbam31DbKeyHash256GenRepository.findByContNextIdx(key.getRequiredScopeId(), key.getOptionalNextId()));
	}

	// CFBamAtom specified index finders

	// CFBamDbKeyHash256Def specified index finders

	// CFBamDbKeyHash256Type specified index finders

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamDbKeyHash256TypeBySchemaIdxKey as arguments.
	 *
	 *		@param requiredSchemaDefId
	 *
	 *		@return List&lt;CFBamJpaDbKeyHash256Gen&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDbKeyHash256Gen> findBySchemaIdx(@Param("schemaDefId") CFLibDbKeyHash256 requiredSchemaDefId) {
		return( cfbam31DbKeyHash256GenRepository.findBySchemaIdx(requiredSchemaDefId));
	}

	/**
	 *	ICFBamDbKeyHash256TypeBySchemaIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamDbKeyHash256TypeBySchemaIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDbKeyHash256Gen> findBySchemaIdx(ICFBamDbKeyHash256TypeBySchemaIdxKey key) {
		return( cfbam31DbKeyHash256GenRepository.findBySchemaIdx(key.getRequiredSchemaDefId()));
	}

	// CFBamDbKeyHash256Gen specified index finders

	// CFBamValue specified lock-by-index methods

	/**
	 *	Argument-based lock database entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredId
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaDbKeyHash256Gen lockByIdIdx(@Param("id") CFLibDbKeyHash256 requiredId) {
		return( cfbam31DbKeyHash256GenRepository.lockByIdIdx(requiredId));
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
	public CFBamJpaDbKeyHash256Gen lockByUNameIdx(@Param("scopeId") CFLibDbKeyHash256 requiredScopeId,
		@Param("name") String requiredName) {
		return( cfbam31DbKeyHash256GenRepository.lockByUNameIdx(requiredScopeId,
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
	public CFBamJpaDbKeyHash256Gen lockByUNameIdx(ICFBamValueByUNameIdxKey key) {
		return( cfbam31DbKeyHash256GenRepository.lockByUNameIdx(key.getRequiredScopeId(), key.getRequiredName()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredScopeId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDbKeyHash256Gen> lockByScopeIdx(@Param("scopeId") CFLibDbKeyHash256 requiredScopeId) {
		return( cfbam31DbKeyHash256GenRepository.lockByScopeIdx(requiredScopeId));
	}

	/**
	 *	ICFBamValueByScopeIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDbKeyHash256Gen> lockByScopeIdx(ICFBamValueByScopeIdxKey key) {
		return( cfbam31DbKeyHash256GenRepository.lockByScopeIdx(key.getRequiredScopeId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalDefSchemaId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDbKeyHash256Gen> lockByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId) {
		return( cfbam31DbKeyHash256GenRepository.lockByDefSchemaIdx(optionalDefSchemaId));
	}

	/**
	 *	ICFBamValueByDefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDbKeyHash256Gen> lockByDefSchemaIdx(ICFBamValueByDefSchemaIdxKey key) {
		return( cfbam31DbKeyHash256GenRepository.lockByDefSchemaIdx(key.getOptionalDefSchemaId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalPrevId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDbKeyHash256Gen> lockByPrevIdx(@Param("prevId") CFLibDbKeyHash256 optionalPrevId) {
		return( cfbam31DbKeyHash256GenRepository.lockByPrevIdx(optionalPrevId));
	}

	/**
	 *	ICFBamValueByPrevIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDbKeyHash256Gen> lockByPrevIdx(ICFBamValueByPrevIdxKey key) {
		return( cfbam31DbKeyHash256GenRepository.lockByPrevIdx(key.getOptionalPrevId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalNextId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDbKeyHash256Gen> lockByNextIdx(@Param("nextId") CFLibDbKeyHash256 optionalNextId) {
		return( cfbam31DbKeyHash256GenRepository.lockByNextIdx(optionalNextId));
	}

	/**
	 *	ICFBamValueByNextIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDbKeyHash256Gen> lockByNextIdx(ICFBamValueByNextIdxKey key) {
		return( cfbam31DbKeyHash256GenRepository.lockByNextIdx(key.getOptionalNextId()));
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
	public List<CFBamJpaDbKeyHash256Gen> lockByContPrevIdx(@Param("scopeId") CFLibDbKeyHash256 requiredScopeId,
		@Param("prevId") CFLibDbKeyHash256 optionalPrevId) {
		return( cfbam31DbKeyHash256GenRepository.lockByContPrevIdx(requiredScopeId,
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
	public List<CFBamJpaDbKeyHash256Gen> lockByContPrevIdx(ICFBamValueByContPrevIdxKey key) {
		return( cfbam31DbKeyHash256GenRepository.lockByContPrevIdx(key.getRequiredScopeId(), key.getOptionalPrevId()));
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
	public List<CFBamJpaDbKeyHash256Gen> lockByContNextIdx(@Param("scopeId") CFLibDbKeyHash256 requiredScopeId,
		@Param("nextId") CFLibDbKeyHash256 optionalNextId) {
		return( cfbam31DbKeyHash256GenRepository.lockByContNextIdx(requiredScopeId,
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
	public List<CFBamJpaDbKeyHash256Gen> lockByContNextIdx(ICFBamValueByContNextIdxKey key) {
		return( cfbam31DbKeyHash256GenRepository.lockByContNextIdx(key.getRequiredScopeId(), key.getOptionalNextId()));
	}

	// CFBamAtom specified lock-by-index methods

	// CFBamDbKeyHash256Def specified lock-by-index methods

	// CFBamDbKeyHash256Type specified lock-by-index methods

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredSchemaDefId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDbKeyHash256Gen> lockBySchemaIdx(@Param("schemaDefId") CFLibDbKeyHash256 requiredSchemaDefId) {
		return( cfbam31DbKeyHash256GenRepository.lockBySchemaIdx(requiredSchemaDefId));
	}

	/**
	 *	ICFBamDbKeyHash256TypeBySchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaDbKeyHash256Gen> lockBySchemaIdx(ICFBamDbKeyHash256TypeBySchemaIdxKey key) {
		return( cfbam31DbKeyHash256GenRepository.lockBySchemaIdx(key.getRequiredSchemaDefId()));
	}

	// CFBamDbKeyHash256Gen specified lock-by-index methods

	// CFBamValue specified delete-by-index methods

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByIdIdx(@Param("id") CFLibDbKeyHash256 requiredId) {
		cfbam31DbKeyHash256GenRepository.deleteByIdIdx(requiredId);
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
		cfbam31DbKeyHash256GenRepository.deleteByUNameIdx(requiredScopeId,
			requiredName);
	}

	/**
	 *	ICFBamValueByUNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamValueByUNameIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByUNameIdx(ICFBamValueByUNameIdxKey key) {
		cfbam31DbKeyHash256GenRepository.deleteByUNameIdx(key.getRequiredScopeId(), key.getRequiredName());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredScopeId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByScopeIdx(@Param("scopeId") CFLibDbKeyHash256 requiredScopeId) {
		cfbam31DbKeyHash256GenRepository.deleteByScopeIdx(requiredScopeId);
	}

	/**
	 *	ICFBamValueByScopeIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamValueByScopeIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByScopeIdx(ICFBamValueByScopeIdxKey key) {
		cfbam31DbKeyHash256GenRepository.deleteByScopeIdx(key.getRequiredScopeId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalDefSchemaId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId) {
		cfbam31DbKeyHash256GenRepository.deleteByDefSchemaIdx(optionalDefSchemaId);
	}

	/**
	 *	ICFBamValueByDefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamValueByDefSchemaIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByDefSchemaIdx(ICFBamValueByDefSchemaIdxKey key) {
		cfbam31DbKeyHash256GenRepository.deleteByDefSchemaIdx(key.getOptionalDefSchemaId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalPrevId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByPrevIdx(@Param("prevId") CFLibDbKeyHash256 optionalPrevId) {
		cfbam31DbKeyHash256GenRepository.deleteByPrevIdx(optionalPrevId);
	}

	/**
	 *	ICFBamValueByPrevIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamValueByPrevIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByPrevIdx(ICFBamValueByPrevIdxKey key) {
		cfbam31DbKeyHash256GenRepository.deleteByPrevIdx(key.getOptionalPrevId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalNextId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByNextIdx(@Param("nextId") CFLibDbKeyHash256 optionalNextId) {
		cfbam31DbKeyHash256GenRepository.deleteByNextIdx(optionalNextId);
	}

	/**
	 *	ICFBamValueByNextIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamValueByNextIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByNextIdx(ICFBamValueByNextIdxKey key) {
		cfbam31DbKeyHash256GenRepository.deleteByNextIdx(key.getOptionalNextId());
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
		cfbam31DbKeyHash256GenRepository.deleteByContPrevIdx(requiredScopeId,
			optionalPrevId);
	}

	/**
	 *	ICFBamValueByContPrevIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamValueByContPrevIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByContPrevIdx(ICFBamValueByContPrevIdxKey key) {
		cfbam31DbKeyHash256GenRepository.deleteByContPrevIdx(key.getRequiredScopeId(), key.getOptionalPrevId());
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
		cfbam31DbKeyHash256GenRepository.deleteByContNextIdx(requiredScopeId,
			optionalNextId);
	}

	/**
	 *	ICFBamValueByContNextIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamValueByContNextIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByContNextIdx(ICFBamValueByContNextIdxKey key) {
		cfbam31DbKeyHash256GenRepository.deleteByContNextIdx(key.getRequiredScopeId(), key.getOptionalNextId());
	}

	// CFBamAtom specified delete-by-index methods

	// CFBamDbKeyHash256Def specified delete-by-index methods

	// CFBamDbKeyHash256Type specified delete-by-index methods

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredSchemaDefId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteBySchemaIdx(@Param("schemaDefId") CFLibDbKeyHash256 requiredSchemaDefId) {
		cfbam31DbKeyHash256GenRepository.deleteBySchemaIdx(requiredSchemaDefId);
	}

	/**
	 *	ICFBamDbKeyHash256TypeBySchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamDbKeyHash256TypeBySchemaIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteBySchemaIdx(ICFBamDbKeyHash256TypeBySchemaIdxKey key) {
		cfbam31DbKeyHash256GenRepository.deleteBySchemaIdx(key.getRequiredSchemaDefId());
	}

	// CFBamDbKeyHash256Gen specified delete-by-index methods
}
