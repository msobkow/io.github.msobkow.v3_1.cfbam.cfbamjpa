// Description: Java 25 Spring JPA Service for ServerObjFunc

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
 *	Service for the CFBamServerObjFunc entities defined in io.github.msobkow.v3_1.cfbam.cfbam.jpa
 *	using the CFBamServerObjFuncRepository to access them.
 */
@Service("cfbam31JpaServerObjFuncService")
public class CFBamJpaServerObjFuncService {

	@Autowired
	@Qualifier("cfbam31EntityManagerFactory")
	private LocalContainerEntityManagerFactoryBean cfbam31EntityManagerFactory;

	@Autowired
	private CFBamJpaServerObjFuncRepository cfbam31ServerObjFuncRepository;

	/**
	 *	Create an entity, generating any database keys required along the way.
	 *
	 *		@param	data	The entity to be instantiated; must be a specific instance of CFBamJpaServerObjFunc, not a subclass.
	 *
	 *		@return The updated/created entity.
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaServerObjFunc create(CFBamJpaServerObjFunc data) {
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
		if(data.getRequiredJMethodBody() == null) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredJMethodBody");
		}
		if(data.getRequiredCppMethodBody() == null) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredCppMethodBody");
		}
		if(data.getRequiredCsMethodBody() == null) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredCsMethodBody");
		}
		try {
			if (data.getRequiredId() == null || data.getRequiredId().isNull()) {
				data.setRequiredId(new CFLibDbKeyHash256(0));
				generatedRequiredId = true;
			}
			LocalDateTime now = LocalDateTime.now();
			data.setCreatedAt(now);
			data.setUpdatedAt(now);
			if(data.getPKey() != null && cfbam31ServerObjFuncRepository.existsById((CFLibDbKeyHash256)data.getPKey())) {
				return( (CFBamJpaServerObjFunc)(cfbam31ServerObjFuncRepository.findById((CFLibDbKeyHash256)(data.getPKey())).get()));
			}
			return cfbam31ServerObjFuncRepository.save(data);
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
	public CFBamJpaServerObjFunc update(CFBamJpaServerObjFunc data) {
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
		if(data.getRequiredJMethodBody() == null) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredJMethodBody");
		}
		if(data.getRequiredCppMethodBody() == null) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredCppMethodBody");
		}
		if(data.getRequiredCsMethodBody() == null) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredCsMethodBody");
		}
		// Ensure the entity exists and that the revision matches
		CFBamJpaServerObjFunc existing = cfbam31ServerObjFuncRepository.findById((CFLibDbKeyHash256)(data.getPKey()))
			.orElseThrow(() -> new CFLibCollisionDetectedException(getClass(), S_ProcName, data.getPKey()));
		if (existing.getRequiredRevision() != data.getRequiredRevision()) {
			throw new CFLibCollisionDetectedException(getClass(), S_ProcName, data.getPKey());
		}
		// Apply superior data relationships of CFBamScope to existing object
		existing.setRequiredOwnerTenant(data.getRequiredTenantId());
		// Apply data columns of CFBamScope to existing object
		// Apply superior data relationships of CFBamServerMethod to existing object
		existing.setOptionalLookupDefSchema(data.getOptionalLookupDefSchema());
		existing.setRequiredContainerForTable(data.getRequiredContainerForTable());
		// Apply data columns of CFBamServerMethod to existing object
		existing.setRequiredName(data.getRequiredName());
		existing.setOptionalShortName(data.getOptionalShortName());
		existing.setOptionalLabel(data.getOptionalLabel());
		existing.setOptionalShortDescription(data.getOptionalShortDescription());
		existing.setOptionalDescription(data.getOptionalDescription());
		existing.setOptionalSuffix(data.getOptionalSuffix());
		existing.setRequiredIsInstanceMethod(data.getRequiredIsInstanceMethod());
		existing.setRequiredIsServerOnly(data.getRequiredIsServerOnly());
		existing.setRequiredJMethodBody(data.getRequiredJMethodBody());
		existing.setRequiredCppMethodBody(data.getRequiredCppMethodBody());
		existing.setRequiredCsMethodBody(data.getRequiredCsMethodBody());
		// Apply superior data relationships of CFBamServerObjFunc to existing object
		existing.setOptionalLookupRetTable(data.getOptionalLookupRetTable());
		// Apply data columns of CFBamServerObjFunc to existing object
		// Update the audit columns
		data.setUpdatedAt(LocalDateTime.now());
		// Save the changes we've made
		return cfbam31ServerObjFuncRepository.save(existing);
	}

	/**
	 *	Argument-based find database instance for compatibility with the current MSS code factory code base.
	 *
	 *		@param requiredId
	 *
	 *		@return The retrieved entity, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaServerObjFunc find(@Param("id") CFLibDbKeyHash256 requiredId) {
		return( cfbam31ServerObjFuncRepository.get(requiredId));
	}

	/**
	 *	Retrieve all entities from the repository
	 *
	 *		@return The list of retrieved entities, which may be empty
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaServerObjFunc> findAll() {
		return( cfbam31ServerObjFuncRepository.findAll() );
	}

	// CFBamScope specified index finders

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamScopeByTenantIdxKey as arguments.
	 *
	 *		@param requiredTenantId
	 *
	 *		@return List&lt;CFBamJpaServerObjFunc&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaServerObjFunc> findByTenantIdx(@Param("tenantId") CFLibDbKeyHash256 requiredTenantId) {
		return( cfbam31ServerObjFuncRepository.findByTenantIdx(requiredTenantId));
	}

	/**
	 *	ICFBamScopeByTenantIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamScopeByTenantIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaServerObjFunc> findByTenantIdx(ICFBamScopeByTenantIdxKey key) {
		return( cfbam31ServerObjFuncRepository.findByTenantIdx(key.getRequiredTenantId()));
	}

	// CFBamServerMethod specified index finders

	/**
	 *	Find an entity using the columns of the ICFBamServerMethodByUNameIdxKey as arguments.
	 *
	 *		@param requiredTableId
	 *		@param requiredName
	 *
	 *		@return The found entity, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaServerObjFunc findByUNameIdx(@Param("tableId") CFLibDbKeyHash256 requiredTableId,
		@Param("name") String requiredName) {
		return( cfbam31ServerObjFuncRepository.findByUNameIdx(requiredTableId,
			requiredName));
	}

	/**
	 *	ICFBamServerMethodByUNameIdxKey entity finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamServerMethodByUNameIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaServerObjFunc findByUNameIdx(ICFBamServerMethodByUNameIdxKey key) {
		return( cfbam31ServerObjFuncRepository.findByUNameIdx(key.getRequiredTableId(), key.getRequiredName()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamServerMethodByMethTableIdxKey as arguments.
	 *
	 *		@param requiredTableId
	 *
	 *		@return List&lt;CFBamJpaServerObjFunc&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaServerObjFunc> findByMethTableIdx(@Param("tableId") CFLibDbKeyHash256 requiredTableId) {
		return( cfbam31ServerObjFuncRepository.findByMethTableIdx(requiredTableId));
	}

	/**
	 *	ICFBamServerMethodByMethTableIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamServerMethodByMethTableIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaServerObjFunc> findByMethTableIdx(ICFBamServerMethodByMethTableIdxKey key) {
		return( cfbam31ServerObjFuncRepository.findByMethTableIdx(key.getRequiredTableId()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamServerMethodByDefSchemaIdxKey as arguments.
	 *
	 *		@param optionalDefSchemaId
	 *
	 *		@return List&lt;CFBamJpaServerObjFunc&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaServerObjFunc> findByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId) {
		return( cfbam31ServerObjFuncRepository.findByDefSchemaIdx(optionalDefSchemaId));
	}

	/**
	 *	ICFBamServerMethodByDefSchemaIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamServerMethodByDefSchemaIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaServerObjFunc> findByDefSchemaIdx(ICFBamServerMethodByDefSchemaIdxKey key) {
		return( cfbam31ServerObjFuncRepository.findByDefSchemaIdx(key.getOptionalDefSchemaId()));
	}

	// CFBamServerObjFunc specified index finders

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamServerObjFuncByRetTblIdxKey as arguments.
	 *
	 *		@param optionalRetTableId
	 *
	 *		@return List&lt;CFBamJpaServerObjFunc&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaServerObjFunc> findByRetTblIdx(@Param("retTableId") CFLibDbKeyHash256 optionalRetTableId) {
		return( cfbam31ServerObjFuncRepository.findByRetTblIdx(optionalRetTableId));
	}

	/**
	 *	ICFBamServerObjFuncByRetTblIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamServerObjFuncByRetTblIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaServerObjFunc> findByRetTblIdx(ICFBamServerObjFuncByRetTblIdxKey key) {
		return( cfbam31ServerObjFuncRepository.findByRetTblIdx(key.getOptionalRetTableId()));
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
	public CFBamJpaServerObjFunc lockByIdIdx(@Param("id") CFLibDbKeyHash256 requiredId) {
		return( cfbam31ServerObjFuncRepository.lockByIdIdx(requiredId));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredTenantId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaServerObjFunc> lockByTenantIdx(@Param("tenantId") CFLibDbKeyHash256 requiredTenantId) {
		return( cfbam31ServerObjFuncRepository.lockByTenantIdx(requiredTenantId));
	}

	/**
	 *	ICFBamScopeByTenantIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaServerObjFunc> lockByTenantIdx(ICFBamScopeByTenantIdxKey key) {
		return( cfbam31ServerObjFuncRepository.lockByTenantIdx(key.getRequiredTenantId()));
	}

	// CFBamServerMethod specified lock-by-index methods

	/**
	 *	Argument-based lock database entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredTableId
	 *		@param requiredName
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaServerObjFunc lockByUNameIdx(@Param("tableId") CFLibDbKeyHash256 requiredTableId,
		@Param("name") String requiredName) {
		return( cfbam31ServerObjFuncRepository.lockByUNameIdx(requiredTableId,
			requiredName));
	}

	/**
	 *	ICFBamServerMethodByUNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaServerObjFunc lockByUNameIdx(ICFBamServerMethodByUNameIdxKey key) {
		return( cfbam31ServerObjFuncRepository.lockByUNameIdx(key.getRequiredTableId(), key.getRequiredName()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredTableId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaServerObjFunc> lockByMethTableIdx(@Param("tableId") CFLibDbKeyHash256 requiredTableId) {
		return( cfbam31ServerObjFuncRepository.lockByMethTableIdx(requiredTableId));
	}

	/**
	 *	ICFBamServerMethodByMethTableIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaServerObjFunc> lockByMethTableIdx(ICFBamServerMethodByMethTableIdxKey key) {
		return( cfbam31ServerObjFuncRepository.lockByMethTableIdx(key.getRequiredTableId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalDefSchemaId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaServerObjFunc> lockByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId) {
		return( cfbam31ServerObjFuncRepository.lockByDefSchemaIdx(optionalDefSchemaId));
	}

	/**
	 *	ICFBamServerMethodByDefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaServerObjFunc> lockByDefSchemaIdx(ICFBamServerMethodByDefSchemaIdxKey key) {
		return( cfbam31ServerObjFuncRepository.lockByDefSchemaIdx(key.getOptionalDefSchemaId()));
	}

	// CFBamServerObjFunc specified lock-by-index methods

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalRetTableId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaServerObjFunc> lockByRetTblIdx(@Param("retTableId") CFLibDbKeyHash256 optionalRetTableId) {
		return( cfbam31ServerObjFuncRepository.lockByRetTblIdx(optionalRetTableId));
	}

	/**
	 *	ICFBamServerObjFuncByRetTblIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaServerObjFunc> lockByRetTblIdx(ICFBamServerObjFuncByRetTblIdxKey key) {
		return( cfbam31ServerObjFuncRepository.lockByRetTblIdx(key.getOptionalRetTableId()));
	}

	// CFBamScope specified delete-by-index methods

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByIdIdx(@Param("id") CFLibDbKeyHash256 requiredId) {
		cfbam31ServerObjFuncRepository.deleteByIdIdx(requiredId);
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredTenantId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByTenantIdx(@Param("tenantId") CFLibDbKeyHash256 requiredTenantId) {
		cfbam31ServerObjFuncRepository.deleteByTenantIdx(requiredTenantId);
	}

	/**
	 *	ICFBamScopeByTenantIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamScopeByTenantIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByTenantIdx(ICFBamScopeByTenantIdxKey key) {
		cfbam31ServerObjFuncRepository.deleteByTenantIdx(key.getRequiredTenantId());
	}

	// CFBamServerMethod specified delete-by-index methods

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredTableId
	 *		@param requiredName
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByUNameIdx(@Param("tableId") CFLibDbKeyHash256 requiredTableId,
		@Param("name") String requiredName) {
		cfbam31ServerObjFuncRepository.deleteByUNameIdx(requiredTableId,
			requiredName);
	}

	/**
	 *	ICFBamServerMethodByUNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamServerMethodByUNameIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByUNameIdx(ICFBamServerMethodByUNameIdxKey key) {
		cfbam31ServerObjFuncRepository.deleteByUNameIdx(key.getRequiredTableId(), key.getRequiredName());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredTableId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByMethTableIdx(@Param("tableId") CFLibDbKeyHash256 requiredTableId) {
		cfbam31ServerObjFuncRepository.deleteByMethTableIdx(requiredTableId);
	}

	/**
	 *	ICFBamServerMethodByMethTableIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamServerMethodByMethTableIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByMethTableIdx(ICFBamServerMethodByMethTableIdxKey key) {
		cfbam31ServerObjFuncRepository.deleteByMethTableIdx(key.getRequiredTableId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalDefSchemaId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId) {
		cfbam31ServerObjFuncRepository.deleteByDefSchemaIdx(optionalDefSchemaId);
	}

	/**
	 *	ICFBamServerMethodByDefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamServerMethodByDefSchemaIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByDefSchemaIdx(ICFBamServerMethodByDefSchemaIdxKey key) {
		cfbam31ServerObjFuncRepository.deleteByDefSchemaIdx(key.getOptionalDefSchemaId());
	}

	// CFBamServerObjFunc specified delete-by-index methods

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalRetTableId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByRetTblIdx(@Param("retTableId") CFLibDbKeyHash256 optionalRetTableId) {
		cfbam31ServerObjFuncRepository.deleteByRetTblIdx(optionalRetTableId);
	}

	/**
	 *	ICFBamServerObjFuncByRetTblIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamServerObjFuncByRetTblIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByRetTblIdx(ICFBamServerObjFuncByRetTblIdxKey key) {
		cfbam31ServerObjFuncRepository.deleteByRetTblIdx(key.getOptionalRetTableId());
	}

}
