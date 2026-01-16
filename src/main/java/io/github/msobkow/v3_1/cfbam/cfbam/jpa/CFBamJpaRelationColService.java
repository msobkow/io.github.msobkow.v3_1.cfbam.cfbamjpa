// Description: Java 25 Spring JPA Service for RelationCol

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
 *	Service for the CFBamRelationCol entities defined in io.github.msobkow.v3_1.cfbam.cfbam.jpa
 *	using the CFBamRelationColRepository to access them.
 */
@Service("cfbam31JpaRelationColService")
public class CFBamJpaRelationColService {

	@Autowired
	@Qualifier("cfbam31EntityManagerFactory")
	private LocalContainerEntityManagerFactoryBean cfbam31EntityManagerFactory;

	@Autowired
	private CFBamJpaRelationColRepository cfbam31RelationColRepository;

	/**
	 *	Create an entity, generating any database keys required along the way.
	 *
	 *		@param	data	The entity to be instantiated; must be a specific instance of CFBamJpaRelationCol, not a subclass.
	 *
	 *		@return The updated/created entity.
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaRelationCol create(CFBamJpaRelationCol data) {
		final String S_ProcName = "create";
		if (data == null) {
			return( null );
		}
		CFLibDbKeyHash256 originalRequiredId = data.getRequiredId();
		boolean generatedRequiredId = false;
		if(data.getRequiredRelationId() == null || data.getRequiredRelationId().isNull()) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredRelationId");
		}
		if(data.getRequiredName() == null) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredName");
		}
		if(data.getRequiredFromColId() == null || data.getRequiredFromColId().isNull()) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredFromColId");
		}
		if(data.getRequiredToColId() == null || data.getRequiredToColId().isNull()) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredToColId");
		}
		try {
			if (data.getRequiredId() == null || data.getRequiredId().isNull()) {
				data.setRequiredId(new CFLibDbKeyHash256(0));
				generatedRequiredId = true;
			}
			LocalDateTime now = LocalDateTime.now();
			data.setCreatedAt(now);
			data.setUpdatedAt(now);
			if(data.getPKey() != null && cfbam31RelationColRepository.existsById((CFLibDbKeyHash256)data.getPKey())) {
				return( (CFBamJpaRelationCol)(cfbam31RelationColRepository.findById((CFLibDbKeyHash256)(data.getPKey())).get()));
			}
			return cfbam31RelationColRepository.save(data);
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
	public CFBamJpaRelationCol update(CFBamJpaRelationCol data) {
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
		if(data.getRequiredRelationId() == null || data.getRequiredRelationId().isNull()) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredRelationId");
		}
		if(data.getRequiredName() == null) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredName");
		}
		if(data.getRequiredFromColId() == null || data.getRequiredFromColId().isNull()) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredFromColId");
		}
		if(data.getRequiredToColId() == null || data.getRequiredToColId().isNull()) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredToColId");
		}
		// Ensure the entity exists and that the revision matches
		CFBamJpaRelationCol existing = cfbam31RelationColRepository.findById((CFLibDbKeyHash256)(data.getPKey()))
			.orElseThrow(() -> new CFLibCollisionDetectedException(getClass(), S_ProcName, data.getPKey()));
		if (existing.getRequiredRevision() != data.getRequiredRevision()) {
			throw new CFLibCollisionDetectedException(getClass(), S_ProcName, data.getPKey());
		}
		// Apply superior data relationships of CFBamRelationCol to existing object
		existing.setRequiredContainerRelation(data.getRequiredContainerRelation());
		existing.setOptionalLookupDefSchema(data.getOptionalLookupDefSchema());
		existing.setOptionalLookupPrev(data.getOptionalLookupPrev());
		existing.setOptionalLookupNext(data.getOptionalLookupNext());
		existing.setRequiredLookupFromCol(data.getRequiredLookupFromCol());
		existing.setRequiredLookupToCol(data.getRequiredLookupToCol());
		// Apply data columns of CFBamRelationCol to existing object
		existing.setRequiredName(data.getRequiredName());
		existing.setOptionalShortName(data.getOptionalShortName());
		existing.setOptionalLabel(data.getOptionalLabel());
		existing.setOptionalShortDescription(data.getOptionalShortDescription());
		existing.setOptionalDescription(data.getOptionalDescription());
		// Update the audit columns
		data.setUpdatedAt(LocalDateTime.now());
		// Save the changes we've made
		return cfbam31RelationColRepository.save(existing);
	}

	/**
	 *	Argument-based find database instance for compatibility with the current MSS code factory code base.
	 *
	 *		@param requiredId
	 *
	 *		@return The retrieved entity, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaRelationCol find(@Param("id") CFLibDbKeyHash256 requiredId) {
		return( cfbam31RelationColRepository.get(requiredId));
	}

	/**
	 *	Retrieve all entities from the repository
	 *
	 *		@return The list of retrieved entities, which may be empty
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelationCol> findAll() {
		return( cfbam31RelationColRepository.findAll() );
	}

	// CFBamRelationCol specified index finders

	/**
	 *	Find an entity using the columns of the ICFBamRelationColByUNameIdxKey as arguments.
	 *
	 *		@param requiredRelationId
	 *		@param requiredName
	 *
	 *		@return The found entity, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaRelationCol findByUNameIdx(@Param("relationId") CFLibDbKeyHash256 requiredRelationId,
		@Param("name") String requiredName) {
		return( cfbam31RelationColRepository.findByUNameIdx(requiredRelationId,
			requiredName));
	}

	/**
	 *	ICFBamRelationColByUNameIdxKey entity finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamRelationColByUNameIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaRelationCol findByUNameIdx(ICFBamRelationColByUNameIdxKey key) {
		return( cfbam31RelationColRepository.findByUNameIdx(key.getRequiredRelationId(), key.getRequiredName()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamRelationColByRelationIdxKey as arguments.
	 *
	 *		@param requiredRelationId
	 *
	 *		@return List&lt;CFBamJpaRelationCol&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelationCol> findByRelationIdx(@Param("relationId") CFLibDbKeyHash256 requiredRelationId) {
		return( cfbam31RelationColRepository.findByRelationIdx(requiredRelationId));
	}

	/**
	 *	ICFBamRelationColByRelationIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamRelationColByRelationIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelationCol> findByRelationIdx(ICFBamRelationColByRelationIdxKey key) {
		return( cfbam31RelationColRepository.findByRelationIdx(key.getRequiredRelationId()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamRelationColByDefSchemaIdxKey as arguments.
	 *
	 *		@param optionalDefSchemaId
	 *
	 *		@return List&lt;CFBamJpaRelationCol&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelationCol> findByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId) {
		return( cfbam31RelationColRepository.findByDefSchemaIdx(optionalDefSchemaId));
	}

	/**
	 *	ICFBamRelationColByDefSchemaIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamRelationColByDefSchemaIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelationCol> findByDefSchemaIdx(ICFBamRelationColByDefSchemaIdxKey key) {
		return( cfbam31RelationColRepository.findByDefSchemaIdx(key.getOptionalDefSchemaId()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamRelationColByFromColIdxKey as arguments.
	 *
	 *		@param requiredFromColId
	 *
	 *		@return List&lt;CFBamJpaRelationCol&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelationCol> findByFromColIdx(@Param("fromColId") CFLibDbKeyHash256 requiredFromColId) {
		return( cfbam31RelationColRepository.findByFromColIdx(requiredFromColId));
	}

	/**
	 *	ICFBamRelationColByFromColIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamRelationColByFromColIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelationCol> findByFromColIdx(ICFBamRelationColByFromColIdxKey key) {
		return( cfbam31RelationColRepository.findByFromColIdx(key.getRequiredFromColId()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamRelationColByToColIdxKey as arguments.
	 *
	 *		@param requiredToColId
	 *
	 *		@return List&lt;CFBamJpaRelationCol&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelationCol> findByToColIdx(@Param("toColId") CFLibDbKeyHash256 requiredToColId) {
		return( cfbam31RelationColRepository.findByToColIdx(requiredToColId));
	}

	/**
	 *	ICFBamRelationColByToColIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamRelationColByToColIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelationCol> findByToColIdx(ICFBamRelationColByToColIdxKey key) {
		return( cfbam31RelationColRepository.findByToColIdx(key.getRequiredToColId()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamRelationColByPrevIdxKey as arguments.
	 *
	 *		@param optionalPrevId
	 *
	 *		@return List&lt;CFBamJpaRelationCol&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelationCol> findByPrevIdx(@Param("prevId") CFLibDbKeyHash256 optionalPrevId) {
		return( cfbam31RelationColRepository.findByPrevIdx(optionalPrevId));
	}

	/**
	 *	ICFBamRelationColByPrevIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamRelationColByPrevIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelationCol> findByPrevIdx(ICFBamRelationColByPrevIdxKey key) {
		return( cfbam31RelationColRepository.findByPrevIdx(key.getOptionalPrevId()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamRelationColByNextIdxKey as arguments.
	 *
	 *		@param optionalNextId
	 *
	 *		@return List&lt;CFBamJpaRelationCol&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelationCol> findByNextIdx(@Param("nextId") CFLibDbKeyHash256 optionalNextId) {
		return( cfbam31RelationColRepository.findByNextIdx(optionalNextId));
	}

	/**
	 *	ICFBamRelationColByNextIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamRelationColByNextIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelationCol> findByNextIdx(ICFBamRelationColByNextIdxKey key) {
		return( cfbam31RelationColRepository.findByNextIdx(key.getOptionalNextId()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamRelationColByRelPrevIdxKey as arguments.
	 *
	 *		@param requiredRelationId
	 *		@param optionalPrevId
	 *
	 *		@return List&lt;CFBamJpaRelationCol&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelationCol> findByRelPrevIdx(@Param("relationId") CFLibDbKeyHash256 requiredRelationId,
		@Param("prevId") CFLibDbKeyHash256 optionalPrevId) {
		return( cfbam31RelationColRepository.findByRelPrevIdx(requiredRelationId,
			optionalPrevId));
	}

	/**
	 *	ICFBamRelationColByRelPrevIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamRelationColByRelPrevIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelationCol> findByRelPrevIdx(ICFBamRelationColByRelPrevIdxKey key) {
		return( cfbam31RelationColRepository.findByRelPrevIdx(key.getRequiredRelationId(), key.getOptionalPrevId()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamRelationColByRelNextIdxKey as arguments.
	 *
	 *		@param requiredRelationId
	 *		@param optionalNextId
	 *
	 *		@return List&lt;CFBamJpaRelationCol&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelationCol> findByRelNextIdx(@Param("relationId") CFLibDbKeyHash256 requiredRelationId,
		@Param("nextId") CFLibDbKeyHash256 optionalNextId) {
		return( cfbam31RelationColRepository.findByRelNextIdx(requiredRelationId,
			optionalNextId));
	}

	/**
	 *	ICFBamRelationColByRelNextIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamRelationColByRelNextIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelationCol> findByRelNextIdx(ICFBamRelationColByRelNextIdxKey key) {
		return( cfbam31RelationColRepository.findByRelNextIdx(key.getRequiredRelationId(), key.getOptionalNextId()));
	}

	// CFBamRelationCol specified lock-by-index methods

	/**
	 *	Argument-based lock database entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredId
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaRelationCol lockByIdIdx(@Param("id") CFLibDbKeyHash256 requiredId) {
		return( cfbam31RelationColRepository.lockByIdIdx(requiredId));
	}

	/**
	 *	Argument-based lock database entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredRelationId
	 *		@param requiredName
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaRelationCol lockByUNameIdx(@Param("relationId") CFLibDbKeyHash256 requiredRelationId,
		@Param("name") String requiredName) {
		return( cfbam31RelationColRepository.lockByUNameIdx(requiredRelationId,
			requiredName));
	}

	/**
	 *	ICFBamRelationColByUNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaRelationCol lockByUNameIdx(ICFBamRelationColByUNameIdxKey key) {
		return( cfbam31RelationColRepository.lockByUNameIdx(key.getRequiredRelationId(), key.getRequiredName()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredRelationId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelationCol> lockByRelationIdx(@Param("relationId") CFLibDbKeyHash256 requiredRelationId) {
		return( cfbam31RelationColRepository.lockByRelationIdx(requiredRelationId));
	}

	/**
	 *	ICFBamRelationColByRelationIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelationCol> lockByRelationIdx(ICFBamRelationColByRelationIdxKey key) {
		return( cfbam31RelationColRepository.lockByRelationIdx(key.getRequiredRelationId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalDefSchemaId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelationCol> lockByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId) {
		return( cfbam31RelationColRepository.lockByDefSchemaIdx(optionalDefSchemaId));
	}

	/**
	 *	ICFBamRelationColByDefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelationCol> lockByDefSchemaIdx(ICFBamRelationColByDefSchemaIdxKey key) {
		return( cfbam31RelationColRepository.lockByDefSchemaIdx(key.getOptionalDefSchemaId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredFromColId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelationCol> lockByFromColIdx(@Param("fromColId") CFLibDbKeyHash256 requiredFromColId) {
		return( cfbam31RelationColRepository.lockByFromColIdx(requiredFromColId));
	}

	/**
	 *	ICFBamRelationColByFromColIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelationCol> lockByFromColIdx(ICFBamRelationColByFromColIdxKey key) {
		return( cfbam31RelationColRepository.lockByFromColIdx(key.getRequiredFromColId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredToColId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelationCol> lockByToColIdx(@Param("toColId") CFLibDbKeyHash256 requiredToColId) {
		return( cfbam31RelationColRepository.lockByToColIdx(requiredToColId));
	}

	/**
	 *	ICFBamRelationColByToColIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelationCol> lockByToColIdx(ICFBamRelationColByToColIdxKey key) {
		return( cfbam31RelationColRepository.lockByToColIdx(key.getRequiredToColId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalPrevId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelationCol> lockByPrevIdx(@Param("prevId") CFLibDbKeyHash256 optionalPrevId) {
		return( cfbam31RelationColRepository.lockByPrevIdx(optionalPrevId));
	}

	/**
	 *	ICFBamRelationColByPrevIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelationCol> lockByPrevIdx(ICFBamRelationColByPrevIdxKey key) {
		return( cfbam31RelationColRepository.lockByPrevIdx(key.getOptionalPrevId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalNextId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelationCol> lockByNextIdx(@Param("nextId") CFLibDbKeyHash256 optionalNextId) {
		return( cfbam31RelationColRepository.lockByNextIdx(optionalNextId));
	}

	/**
	 *	ICFBamRelationColByNextIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelationCol> lockByNextIdx(ICFBamRelationColByNextIdxKey key) {
		return( cfbam31RelationColRepository.lockByNextIdx(key.getOptionalNextId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredRelationId
	 *		@param optionalPrevId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelationCol> lockByRelPrevIdx(@Param("relationId") CFLibDbKeyHash256 requiredRelationId,
		@Param("prevId") CFLibDbKeyHash256 optionalPrevId) {
		return( cfbam31RelationColRepository.lockByRelPrevIdx(requiredRelationId,
			optionalPrevId));
	}

	/**
	 *	ICFBamRelationColByRelPrevIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelationCol> lockByRelPrevIdx(ICFBamRelationColByRelPrevIdxKey key) {
		return( cfbam31RelationColRepository.lockByRelPrevIdx(key.getRequiredRelationId(), key.getOptionalPrevId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredRelationId
	 *		@param optionalNextId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelationCol> lockByRelNextIdx(@Param("relationId") CFLibDbKeyHash256 requiredRelationId,
		@Param("nextId") CFLibDbKeyHash256 optionalNextId) {
		return( cfbam31RelationColRepository.lockByRelNextIdx(requiredRelationId,
			optionalNextId));
	}

	/**
	 *	ICFBamRelationColByRelNextIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaRelationCol> lockByRelNextIdx(ICFBamRelationColByRelNextIdxKey key) {
		return( cfbam31RelationColRepository.lockByRelNextIdx(key.getRequiredRelationId(), key.getOptionalNextId()));
	}

	// CFBamRelationCol specified delete-by-index methods

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByIdIdx(@Param("id") CFLibDbKeyHash256 requiredId) {
		cfbam31RelationColRepository.deleteByIdIdx(requiredId);
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredRelationId
	 *		@param requiredName
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByUNameIdx(@Param("relationId") CFLibDbKeyHash256 requiredRelationId,
		@Param("name") String requiredName) {
		cfbam31RelationColRepository.deleteByUNameIdx(requiredRelationId,
			requiredName);
	}

	/**
	 *	ICFBamRelationColByUNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamRelationColByUNameIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByUNameIdx(ICFBamRelationColByUNameIdxKey key) {
		cfbam31RelationColRepository.deleteByUNameIdx(key.getRequiredRelationId(), key.getRequiredName());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredRelationId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByRelationIdx(@Param("relationId") CFLibDbKeyHash256 requiredRelationId) {
		cfbam31RelationColRepository.deleteByRelationIdx(requiredRelationId);
	}

	/**
	 *	ICFBamRelationColByRelationIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamRelationColByRelationIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByRelationIdx(ICFBamRelationColByRelationIdxKey key) {
		cfbam31RelationColRepository.deleteByRelationIdx(key.getRequiredRelationId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalDefSchemaId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId) {
		cfbam31RelationColRepository.deleteByDefSchemaIdx(optionalDefSchemaId);
	}

	/**
	 *	ICFBamRelationColByDefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamRelationColByDefSchemaIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByDefSchemaIdx(ICFBamRelationColByDefSchemaIdxKey key) {
		cfbam31RelationColRepository.deleteByDefSchemaIdx(key.getOptionalDefSchemaId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredFromColId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByFromColIdx(@Param("fromColId") CFLibDbKeyHash256 requiredFromColId) {
		cfbam31RelationColRepository.deleteByFromColIdx(requiredFromColId);
	}

	/**
	 *	ICFBamRelationColByFromColIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamRelationColByFromColIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByFromColIdx(ICFBamRelationColByFromColIdxKey key) {
		cfbam31RelationColRepository.deleteByFromColIdx(key.getRequiredFromColId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredToColId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByToColIdx(@Param("toColId") CFLibDbKeyHash256 requiredToColId) {
		cfbam31RelationColRepository.deleteByToColIdx(requiredToColId);
	}

	/**
	 *	ICFBamRelationColByToColIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamRelationColByToColIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByToColIdx(ICFBamRelationColByToColIdxKey key) {
		cfbam31RelationColRepository.deleteByToColIdx(key.getRequiredToColId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalPrevId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByPrevIdx(@Param("prevId") CFLibDbKeyHash256 optionalPrevId) {
		cfbam31RelationColRepository.deleteByPrevIdx(optionalPrevId);
	}

	/**
	 *	ICFBamRelationColByPrevIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamRelationColByPrevIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByPrevIdx(ICFBamRelationColByPrevIdxKey key) {
		cfbam31RelationColRepository.deleteByPrevIdx(key.getOptionalPrevId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalNextId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByNextIdx(@Param("nextId") CFLibDbKeyHash256 optionalNextId) {
		cfbam31RelationColRepository.deleteByNextIdx(optionalNextId);
	}

	/**
	 *	ICFBamRelationColByNextIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamRelationColByNextIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByNextIdx(ICFBamRelationColByNextIdxKey key) {
		cfbam31RelationColRepository.deleteByNextIdx(key.getOptionalNextId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredRelationId
	 *		@param optionalPrevId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByRelPrevIdx(@Param("relationId") CFLibDbKeyHash256 requiredRelationId,
		@Param("prevId") CFLibDbKeyHash256 optionalPrevId) {
		cfbam31RelationColRepository.deleteByRelPrevIdx(requiredRelationId,
			optionalPrevId);
	}

	/**
	 *	ICFBamRelationColByRelPrevIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamRelationColByRelPrevIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByRelPrevIdx(ICFBamRelationColByRelPrevIdxKey key) {
		cfbam31RelationColRepository.deleteByRelPrevIdx(key.getRequiredRelationId(), key.getOptionalPrevId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredRelationId
	 *		@param optionalNextId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByRelNextIdx(@Param("relationId") CFLibDbKeyHash256 requiredRelationId,
		@Param("nextId") CFLibDbKeyHash256 optionalNextId) {
		cfbam31RelationColRepository.deleteByRelNextIdx(requiredRelationId,
			optionalNextId);
	}

	/**
	 *	ICFBamRelationColByRelNextIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamRelationColByRelNextIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByRelNextIdx(ICFBamRelationColByRelNextIdxKey key) {
		cfbam31RelationColRepository.deleteByRelNextIdx(key.getRequiredRelationId(), key.getOptionalNextId());
	}

}
