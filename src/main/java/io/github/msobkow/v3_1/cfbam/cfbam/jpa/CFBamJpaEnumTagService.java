// Description: Java 25 Spring JPA Service for EnumTag

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
 *	Service for the CFBamEnumTag entities defined in io.github.msobkow.v3_1.cfbam.cfbam.jpa
 *	using the CFBamEnumTagRepository to access them.
 */
@Service("cfbam31JpaEnumTagService")
public class CFBamJpaEnumTagService {

	@Autowired
	@Qualifier("cfbam31EntityManagerFactory")
	private LocalContainerEntityManagerFactoryBean cfbam31EntityManagerFactory;

	@Autowired
	private CFBamJpaEnumTagRepository cfbam31EnumTagRepository;

	/**
	 *	Create an entity, generating any database keys required along the way.
	 *
	 *		@param	data	The entity to be instantiated; must be a specific instance of CFBamJpaEnumTag, not a subclass.
	 *
	 *		@return The updated/created entity.
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaEnumTag create(CFBamJpaEnumTag data) {
		final String S_ProcName = "create";
		if (data == null) {
			return( null );
		}
		CFLibDbKeyHash256 originalRequiredId = data.getRequiredId();
		boolean generatedRequiredId = false;
		if(data.getRequiredEnumId() == null || data.getRequiredEnumId().isNull()) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredEnumId");
		}
		if( data.getOptionalEnumCode() != null && data.getOptionalEnumCode().compareTo(ICFBamEnumTag.ENUMCODE_MIN_VALUE) < 0) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"getOptionalEnumCode()",
				data.getOptionalEnumCode(),
				ICFBamEnumTag.ENUMCODE_MIN_VALUE );
		}
		if( data.getOptionalEnumCode() != null && data.getOptionalEnumCode().compareTo(ICFBamEnumTag.ENUMCODE_MAX_VALUE) > 0) {
			throw new CFLibArgumentOverflowException( getClass(),
				S_ProcName,
				0,
				"getOptionalEnumCode()",
				data.getOptionalEnumCode(),
				ICFBamEnumTag.ENUMCODE_MAX_VALUE );
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
			if(data.getPKey() != null && cfbam31EnumTagRepository.existsById((CFLibDbKeyHash256)data.getPKey())) {
				return( (CFBamJpaEnumTag)(cfbam31EnumTagRepository.findById((CFLibDbKeyHash256)(data.getPKey())).get()));
			}
			return cfbam31EnumTagRepository.save(data);
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
	public CFBamJpaEnumTag update(CFBamJpaEnumTag data) {
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
		if(data.getRequiredEnumId() == null || data.getRequiredEnumId().isNull()) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredEnumId");
		}
		if( data.getOptionalEnumCode() != null && data.getOptionalEnumCode().compareTo(ICFBamEnumTag.ENUMCODE_MIN_VALUE) < 0) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"getOptionalEnumCode()",
				data.getOptionalEnumCode(),
				ICFBamEnumTag.ENUMCODE_MIN_VALUE );
		}
		if( data.getOptionalEnumCode() != null && data.getOptionalEnumCode().compareTo(ICFBamEnumTag.ENUMCODE_MAX_VALUE) > 0) {
			throw new CFLibArgumentOverflowException( getClass(),
				S_ProcName,
				0,
				"getOptionalEnumCode()",
				data.getOptionalEnumCode(),
				ICFBamEnumTag.ENUMCODE_MAX_VALUE );
		}
		if(data.getRequiredName() == null) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredName");
		}
		// Ensure the entity exists and that the revision matches
		CFBamJpaEnumTag existing = cfbam31EnumTagRepository.findById((CFLibDbKeyHash256)(data.getPKey()))
			.orElseThrow(() -> new CFLibCollisionDetectedException(getClass(), S_ProcName, data.getPKey()));
		if (existing.getRequiredRevision() != data.getRequiredRevision()) {
			throw new CFLibCollisionDetectedException(getClass(), S_ProcName, data.getPKey());
		}
		// Apply superior data relationships of CFBamEnumTag to existing object
		existing.setRequiredContainerEnumDef(data.getRequiredContainerEnumDef());
		existing.setOptionalLookupDefSchema(data.getOptionalLookupDefSchema());
		existing.setOptionalLookupPrev(data.getOptionalLookupPrev());
		existing.setOptionalLookupNext(data.getOptionalLookupNext());
		// Apply data columns of CFBamEnumTag to existing object
		existing.setOptionalEnumCode(data.getOptionalEnumCode());
		existing.setRequiredName(data.getRequiredName());
		// Update the audit columns
		data.setUpdatedAt(LocalDateTime.now());
		// Save the changes we've made
		return cfbam31EnumTagRepository.save(existing);
	}

	/**
	 *	Argument-based find database instance for compatibility with the current MSS code factory code base.
	 *
	 *		@param requiredId
	 *
	 *		@return The retrieved entity, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaEnumTag find(@Param("id") CFLibDbKeyHash256 requiredId) {
		return( cfbam31EnumTagRepository.get(requiredId));
	}

	/**
	 *	Retrieve all entities from the repository
	 *
	 *		@return The list of retrieved entities, which may be empty
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaEnumTag> findAll() {
		return( cfbam31EnumTagRepository.findAll() );
	}

	// CFBamEnumTag specified index finders

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamEnumTagByEnumIdxKey as arguments.
	 *
	 *		@param requiredEnumId
	 *
	 *		@return List&lt;CFBamJpaEnumTag&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaEnumTag> findByEnumIdx(@Param("enumId") CFLibDbKeyHash256 requiredEnumId) {
		return( cfbam31EnumTagRepository.findByEnumIdx(requiredEnumId));
	}

	/**
	 *	ICFBamEnumTagByEnumIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamEnumTagByEnumIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaEnumTag> findByEnumIdx(ICFBamEnumTagByEnumIdxKey key) {
		return( cfbam31EnumTagRepository.findByEnumIdx(key.getRequiredEnumId()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamEnumTagByDefSchemaIdxKey as arguments.
	 *
	 *		@param optionalDefSchemaId
	 *
	 *		@return List&lt;CFBamJpaEnumTag&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaEnumTag> findByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId) {
		return( cfbam31EnumTagRepository.findByDefSchemaIdx(optionalDefSchemaId));
	}

	/**
	 *	ICFBamEnumTagByDefSchemaIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamEnumTagByDefSchemaIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaEnumTag> findByDefSchemaIdx(ICFBamEnumTagByDefSchemaIdxKey key) {
		return( cfbam31EnumTagRepository.findByDefSchemaIdx(key.getOptionalDefSchemaId()));
	}

	/**
	 *	Find an entity using the columns of the ICFBamEnumTagByEnumNameIdxKey as arguments.
	 *
	 *		@param requiredEnumId
	 *		@param requiredName
	 *
	 *		@return The found entity, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaEnumTag findByEnumNameIdx(@Param("enumId") CFLibDbKeyHash256 requiredEnumId,
		@Param("name") String requiredName) {
		return( cfbam31EnumTagRepository.findByEnumNameIdx(requiredEnumId,
			requiredName));
	}

	/**
	 *	ICFBamEnumTagByEnumNameIdxKey entity finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamEnumTagByEnumNameIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaEnumTag findByEnumNameIdx(ICFBamEnumTagByEnumNameIdxKey key) {
		return( cfbam31EnumTagRepository.findByEnumNameIdx(key.getRequiredEnumId(), key.getRequiredName()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamEnumTagByPrevIdxKey as arguments.
	 *
	 *		@param optionalPrevId
	 *
	 *		@return List&lt;CFBamJpaEnumTag&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaEnumTag> findByPrevIdx(@Param("prevId") CFLibDbKeyHash256 optionalPrevId) {
		return( cfbam31EnumTagRepository.findByPrevIdx(optionalPrevId));
	}

	/**
	 *	ICFBamEnumTagByPrevIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamEnumTagByPrevIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaEnumTag> findByPrevIdx(ICFBamEnumTagByPrevIdxKey key) {
		return( cfbam31EnumTagRepository.findByPrevIdx(key.getOptionalPrevId()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamEnumTagByNextIdxKey as arguments.
	 *
	 *		@param optionalNextId
	 *
	 *		@return List&lt;CFBamJpaEnumTag&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaEnumTag> findByNextIdx(@Param("nextId") CFLibDbKeyHash256 optionalNextId) {
		return( cfbam31EnumTagRepository.findByNextIdx(optionalNextId));
	}

	/**
	 *	ICFBamEnumTagByNextIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamEnumTagByNextIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaEnumTag> findByNextIdx(ICFBamEnumTagByNextIdxKey key) {
		return( cfbam31EnumTagRepository.findByNextIdx(key.getOptionalNextId()));
	}

	// CFBamEnumTag specified lock-by-index methods

	/**
	 *	Argument-based lock database entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredId
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaEnumTag lockByIdIdx(@Param("id") CFLibDbKeyHash256 requiredId) {
		return( cfbam31EnumTagRepository.lockByIdIdx(requiredId));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredEnumId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaEnumTag> lockByEnumIdx(@Param("enumId") CFLibDbKeyHash256 requiredEnumId) {
		return( cfbam31EnumTagRepository.lockByEnumIdx(requiredEnumId));
	}

	/**
	 *	ICFBamEnumTagByEnumIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaEnumTag> lockByEnumIdx(ICFBamEnumTagByEnumIdxKey key) {
		return( cfbam31EnumTagRepository.lockByEnumIdx(key.getRequiredEnumId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalDefSchemaId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaEnumTag> lockByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId) {
		return( cfbam31EnumTagRepository.lockByDefSchemaIdx(optionalDefSchemaId));
	}

	/**
	 *	ICFBamEnumTagByDefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaEnumTag> lockByDefSchemaIdx(ICFBamEnumTagByDefSchemaIdxKey key) {
		return( cfbam31EnumTagRepository.lockByDefSchemaIdx(key.getOptionalDefSchemaId()));
	}

	/**
	 *	Argument-based lock database entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredEnumId
	 *		@param requiredName
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaEnumTag lockByEnumNameIdx(@Param("enumId") CFLibDbKeyHash256 requiredEnumId,
		@Param("name") String requiredName) {
		return( cfbam31EnumTagRepository.lockByEnumNameIdx(requiredEnumId,
			requiredName));
	}

	/**
	 *	ICFBamEnumTagByEnumNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaEnumTag lockByEnumNameIdx(ICFBamEnumTagByEnumNameIdxKey key) {
		return( cfbam31EnumTagRepository.lockByEnumNameIdx(key.getRequiredEnumId(), key.getRequiredName()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalPrevId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaEnumTag> lockByPrevIdx(@Param("prevId") CFLibDbKeyHash256 optionalPrevId) {
		return( cfbam31EnumTagRepository.lockByPrevIdx(optionalPrevId));
	}

	/**
	 *	ICFBamEnumTagByPrevIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaEnumTag> lockByPrevIdx(ICFBamEnumTagByPrevIdxKey key) {
		return( cfbam31EnumTagRepository.lockByPrevIdx(key.getOptionalPrevId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalNextId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaEnumTag> lockByNextIdx(@Param("nextId") CFLibDbKeyHash256 optionalNextId) {
		return( cfbam31EnumTagRepository.lockByNextIdx(optionalNextId));
	}

	/**
	 *	ICFBamEnumTagByNextIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaEnumTag> lockByNextIdx(ICFBamEnumTagByNextIdxKey key) {
		return( cfbam31EnumTagRepository.lockByNextIdx(key.getOptionalNextId()));
	}

	// CFBamEnumTag specified delete-by-index methods

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByIdIdx(@Param("id") CFLibDbKeyHash256 requiredId) {
		cfbam31EnumTagRepository.deleteByIdIdx(requiredId);
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredEnumId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByEnumIdx(@Param("enumId") CFLibDbKeyHash256 requiredEnumId) {
		cfbam31EnumTagRepository.deleteByEnumIdx(requiredEnumId);
	}

	/**
	 *	ICFBamEnumTagByEnumIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamEnumTagByEnumIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByEnumIdx(ICFBamEnumTagByEnumIdxKey key) {
		cfbam31EnumTagRepository.deleteByEnumIdx(key.getRequiredEnumId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalDefSchemaId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId) {
		cfbam31EnumTagRepository.deleteByDefSchemaIdx(optionalDefSchemaId);
	}

	/**
	 *	ICFBamEnumTagByDefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamEnumTagByDefSchemaIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByDefSchemaIdx(ICFBamEnumTagByDefSchemaIdxKey key) {
		cfbam31EnumTagRepository.deleteByDefSchemaIdx(key.getOptionalDefSchemaId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredEnumId
	 *		@param requiredName
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByEnumNameIdx(@Param("enumId") CFLibDbKeyHash256 requiredEnumId,
		@Param("name") String requiredName) {
		cfbam31EnumTagRepository.deleteByEnumNameIdx(requiredEnumId,
			requiredName);
	}

	/**
	 *	ICFBamEnumTagByEnumNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamEnumTagByEnumNameIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByEnumNameIdx(ICFBamEnumTagByEnumNameIdxKey key) {
		cfbam31EnumTagRepository.deleteByEnumNameIdx(key.getRequiredEnumId(), key.getRequiredName());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalPrevId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByPrevIdx(@Param("prevId") CFLibDbKeyHash256 optionalPrevId) {
		cfbam31EnumTagRepository.deleteByPrevIdx(optionalPrevId);
	}

	/**
	 *	ICFBamEnumTagByPrevIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamEnumTagByPrevIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByPrevIdx(ICFBamEnumTagByPrevIdxKey key) {
		cfbam31EnumTagRepository.deleteByPrevIdx(key.getOptionalPrevId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalNextId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByNextIdx(@Param("nextId") CFLibDbKeyHash256 optionalNextId) {
		cfbam31EnumTagRepository.deleteByNextIdx(optionalNextId);
	}

	/**
	 *	ICFBamEnumTagByNextIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamEnumTagByNextIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByNextIdx(ICFBamEnumTagByNextIdxKey key) {
		cfbam31EnumTagRepository.deleteByNextIdx(key.getOptionalNextId());
	}

}
