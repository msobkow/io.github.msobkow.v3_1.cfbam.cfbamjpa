// Description: Java 25 Spring JPA Service for Int64Col

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
 *	Service for the CFBamInt64Col entities defined in io.github.msobkow.v3_1.cfbam.cfbam.jpa
 *	using the CFBamInt64ColRepository to access them.
 */
@Service("cfbam31JpaInt64ColService")
public class CFBamJpaInt64ColService {

	@Autowired
	@Qualifier("cfbam31EntityManagerFactory")
	private LocalContainerEntityManagerFactoryBean cfbam31EntityManagerFactory;

	@Autowired
	private CFBamJpaInt64ColRepository cfbam31Int64ColRepository;

	/**
	 *	Create an entity, generating any database keys required along the way.
	 *
	 *		@param	data	The entity to be instantiated; must be a specific instance of CFBamJpaInt64Col, not a subclass.
	 *
	 *		@return The updated/created entity.
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaInt64Col create(CFBamJpaInt64Col data) {
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
		if( data.getOptionalInitValue() != null && data.getOptionalInitValue().compareTo(ICFBamInt64Def.INITVALUE_MIN_VALUE) < 0) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"getOptionalInitValue()",
				data.getOptionalInitValue(),
				ICFBamInt64Def.INITVALUE_MIN_VALUE );
		}
		if( data.getOptionalInitValue() != null && data.getOptionalInitValue().compareTo(ICFBamInt64Def.INITVALUE_MAX_VALUE) > 0) {
			throw new CFLibArgumentOverflowException( getClass(),
				S_ProcName,
				0,
				"getOptionalInitValue()",
				data.getOptionalInitValue(),
				ICFBamInt64Def.INITVALUE_MAX_VALUE );
		}
		if( data.getOptionalMinValue() != null && data.getOptionalMinValue().compareTo(ICFBamInt64Def.MINVALUE_MIN_VALUE) < 0) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"getOptionalMinValue()",
				data.getOptionalMinValue(),
				ICFBamInt64Def.MINVALUE_MIN_VALUE );
		}
		if( data.getOptionalMinValue() != null && data.getOptionalMinValue().compareTo(ICFBamInt64Def.MINVALUE_MAX_VALUE) > 0) {
			throw new CFLibArgumentOverflowException( getClass(),
				S_ProcName,
				0,
				"getOptionalMinValue()",
				data.getOptionalMinValue(),
				ICFBamInt64Def.MINVALUE_MAX_VALUE );
		}
		if( data.getOptionalMaxValue() != null && data.getOptionalMaxValue().compareTo(ICFBamInt64Def.MAXVALUE_MIN_VALUE) < 0) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"getOptionalMaxValue()",
				data.getOptionalMaxValue(),
				ICFBamInt64Def.MAXVALUE_MIN_VALUE );
		}
		if( data.getOptionalMaxValue() != null && data.getOptionalMaxValue().compareTo(ICFBamInt64Def.MAXVALUE_MAX_VALUE) > 0) {
			throw new CFLibArgumentOverflowException( getClass(),
				S_ProcName,
				0,
				"getOptionalMaxValue()",
				data.getOptionalMaxValue(),
				ICFBamInt64Def.MAXVALUE_MAX_VALUE );
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
			if(data.getPKey() != null && cfbam31Int64ColRepository.existsById((CFLibDbKeyHash256)data.getPKey())) {
				return( (CFBamJpaInt64Col)(cfbam31Int64ColRepository.findById((CFLibDbKeyHash256)(data.getPKey())).get()));
			}
			return cfbam31Int64ColRepository.save(data);
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
	public CFBamJpaInt64Col update(CFBamJpaInt64Col data) {
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
		if( data.getOptionalInitValue() != null && data.getOptionalInitValue().compareTo(ICFBamInt64Def.INITVALUE_MIN_VALUE) < 0) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"getOptionalInitValue()",
				data.getOptionalInitValue(),
				ICFBamInt64Def.INITVALUE_MIN_VALUE );
		}
		if( data.getOptionalInitValue() != null && data.getOptionalInitValue().compareTo(ICFBamInt64Def.INITVALUE_MAX_VALUE) > 0) {
			throw new CFLibArgumentOverflowException( getClass(),
				S_ProcName,
				0,
				"getOptionalInitValue()",
				data.getOptionalInitValue(),
				ICFBamInt64Def.INITVALUE_MAX_VALUE );
		}
		if( data.getOptionalMinValue() != null && data.getOptionalMinValue().compareTo(ICFBamInt64Def.MINVALUE_MIN_VALUE) < 0) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"getOptionalMinValue()",
				data.getOptionalMinValue(),
				ICFBamInt64Def.MINVALUE_MIN_VALUE );
		}
		if( data.getOptionalMinValue() != null && data.getOptionalMinValue().compareTo(ICFBamInt64Def.MINVALUE_MAX_VALUE) > 0) {
			throw new CFLibArgumentOverflowException( getClass(),
				S_ProcName,
				0,
				"getOptionalMinValue()",
				data.getOptionalMinValue(),
				ICFBamInt64Def.MINVALUE_MAX_VALUE );
		}
		if( data.getOptionalMaxValue() != null && data.getOptionalMaxValue().compareTo(ICFBamInt64Def.MAXVALUE_MIN_VALUE) < 0) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"getOptionalMaxValue()",
				data.getOptionalMaxValue(),
				ICFBamInt64Def.MAXVALUE_MIN_VALUE );
		}
		if( data.getOptionalMaxValue() != null && data.getOptionalMaxValue().compareTo(ICFBamInt64Def.MAXVALUE_MAX_VALUE) > 0) {
			throw new CFLibArgumentOverflowException( getClass(),
				S_ProcName,
				0,
				"getOptionalMaxValue()",
				data.getOptionalMaxValue(),
				ICFBamInt64Def.MAXVALUE_MAX_VALUE );
		}
		if(data.getRequiredTableId() == null || data.getRequiredTableId().isNull()) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredTableId");
		}
		// Ensure the entity exists and that the revision matches
		CFBamJpaInt64Col existing = cfbam31Int64ColRepository.findById((CFLibDbKeyHash256)(data.getPKey()))
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
		// Apply superior data relationships of CFBamInt64Def to existing object
		// Apply data columns of CFBamInt64Def to existing object
		existing.setOptionalInitValue(data.getOptionalInitValue());
		existing.setOptionalMinValue(data.getOptionalMinValue());
		existing.setOptionalMaxValue(data.getOptionalMaxValue());
		// Apply superior data relationships of CFBamInt64Col to existing object
		existing.setRequiredContainerTable(data.getRequiredContainerTable());
		// Apply data columns of CFBamInt64Col to existing object
		// Update the audit columns
		data.setUpdatedAt(LocalDateTime.now());
		// Save the changes we've made
		return cfbam31Int64ColRepository.save(existing);
	}

	/**
	 *	Argument-based find database instance for compatibility with the current MSS code factory code base.
	 *
	 *		@param requiredId
	 *
	 *		@return The retrieved entity, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaInt64Col find(@Param("id") CFLibDbKeyHash256 requiredId) {
		return( cfbam31Int64ColRepository.get(requiredId));
	}

	/**
	 *	Retrieve all entities from the repository
	 *
	 *		@return The list of retrieved entities, which may be empty
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaInt64Col> findAll() {
		return( cfbam31Int64ColRepository.findAll() );
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
	public CFBamJpaInt64Col findByUNameIdx(@Param("scopeId") CFLibDbKeyHash256 requiredScopeId,
		@Param("name") String requiredName) {
		return( cfbam31Int64ColRepository.findByUNameIdx(requiredScopeId,
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
	public CFBamJpaInt64Col findByUNameIdx(ICFBamValueByUNameIdxKey key) {
		return( cfbam31Int64ColRepository.findByUNameIdx(key.getRequiredScopeId(), key.getRequiredName()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamValueByScopeIdxKey as arguments.
	 *
	 *		@param requiredScopeId
	 *
	 *		@return List&lt;CFBamJpaInt64Col&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaInt64Col> findByScopeIdx(@Param("scopeId") CFLibDbKeyHash256 requiredScopeId) {
		return( cfbam31Int64ColRepository.findByScopeIdx(requiredScopeId));
	}

	/**
	 *	ICFBamValueByScopeIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamValueByScopeIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaInt64Col> findByScopeIdx(ICFBamValueByScopeIdxKey key) {
		return( cfbam31Int64ColRepository.findByScopeIdx(key.getRequiredScopeId()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamValueByDefSchemaIdxKey as arguments.
	 *
	 *		@param optionalDefSchemaId
	 *
	 *		@return List&lt;CFBamJpaInt64Col&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaInt64Col> findByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId) {
		return( cfbam31Int64ColRepository.findByDefSchemaIdx(optionalDefSchemaId));
	}

	/**
	 *	ICFBamValueByDefSchemaIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamValueByDefSchemaIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaInt64Col> findByDefSchemaIdx(ICFBamValueByDefSchemaIdxKey key) {
		return( cfbam31Int64ColRepository.findByDefSchemaIdx(key.getOptionalDefSchemaId()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamValueByPrevIdxKey as arguments.
	 *
	 *		@param optionalPrevId
	 *
	 *		@return List&lt;CFBamJpaInt64Col&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaInt64Col> findByPrevIdx(@Param("prevId") CFLibDbKeyHash256 optionalPrevId) {
		return( cfbam31Int64ColRepository.findByPrevIdx(optionalPrevId));
	}

	/**
	 *	ICFBamValueByPrevIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamValueByPrevIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaInt64Col> findByPrevIdx(ICFBamValueByPrevIdxKey key) {
		return( cfbam31Int64ColRepository.findByPrevIdx(key.getOptionalPrevId()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamValueByNextIdxKey as arguments.
	 *
	 *		@param optionalNextId
	 *
	 *		@return List&lt;CFBamJpaInt64Col&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaInt64Col> findByNextIdx(@Param("nextId") CFLibDbKeyHash256 optionalNextId) {
		return( cfbam31Int64ColRepository.findByNextIdx(optionalNextId));
	}

	/**
	 *	ICFBamValueByNextIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamValueByNextIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaInt64Col> findByNextIdx(ICFBamValueByNextIdxKey key) {
		return( cfbam31Int64ColRepository.findByNextIdx(key.getOptionalNextId()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamValueByContPrevIdxKey as arguments.
	 *
	 *		@param requiredScopeId
	 *		@param optionalPrevId
	 *
	 *		@return List&lt;CFBamJpaInt64Col&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaInt64Col> findByContPrevIdx(@Param("scopeId") CFLibDbKeyHash256 requiredScopeId,
		@Param("prevId") CFLibDbKeyHash256 optionalPrevId) {
		return( cfbam31Int64ColRepository.findByContPrevIdx(requiredScopeId,
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
	public List<CFBamJpaInt64Col> findByContPrevIdx(ICFBamValueByContPrevIdxKey key) {
		return( cfbam31Int64ColRepository.findByContPrevIdx(key.getRequiredScopeId(), key.getOptionalPrevId()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamValueByContNextIdxKey as arguments.
	 *
	 *		@param requiredScopeId
	 *		@param optionalNextId
	 *
	 *		@return List&lt;CFBamJpaInt64Col&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaInt64Col> findByContNextIdx(@Param("scopeId") CFLibDbKeyHash256 requiredScopeId,
		@Param("nextId") CFLibDbKeyHash256 optionalNextId) {
		return( cfbam31Int64ColRepository.findByContNextIdx(requiredScopeId,
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
	public List<CFBamJpaInt64Col> findByContNextIdx(ICFBamValueByContNextIdxKey key) {
		return( cfbam31Int64ColRepository.findByContNextIdx(key.getRequiredScopeId(), key.getOptionalNextId()));
	}

	// CFBamAtom specified index finders

	// CFBamInt64Def specified index finders

	// CFBamInt64Col specified index finders

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamInt64ColByTableIdxKey as arguments.
	 *
	 *		@param requiredTableId
	 *
	 *		@return List&lt;CFBamJpaInt64Col&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaInt64Col> findByTableIdx(@Param("tableId") CFLibDbKeyHash256 requiredTableId) {
		return( cfbam31Int64ColRepository.findByTableIdx(requiredTableId));
	}

	/**
	 *	ICFBamInt64ColByTableIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamInt64ColByTableIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaInt64Col> findByTableIdx(ICFBamInt64ColByTableIdxKey key) {
		return( cfbam31Int64ColRepository.findByTableIdx(key.getRequiredTableId()));
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
	public CFBamJpaInt64Col lockByIdIdx(@Param("id") CFLibDbKeyHash256 requiredId) {
		return( cfbam31Int64ColRepository.lockByIdIdx(requiredId));
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
	public CFBamJpaInt64Col lockByUNameIdx(@Param("scopeId") CFLibDbKeyHash256 requiredScopeId,
		@Param("name") String requiredName) {
		return( cfbam31Int64ColRepository.lockByUNameIdx(requiredScopeId,
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
	public CFBamJpaInt64Col lockByUNameIdx(ICFBamValueByUNameIdxKey key) {
		return( cfbam31Int64ColRepository.lockByUNameIdx(key.getRequiredScopeId(), key.getRequiredName()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredScopeId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaInt64Col> lockByScopeIdx(@Param("scopeId") CFLibDbKeyHash256 requiredScopeId) {
		return( cfbam31Int64ColRepository.lockByScopeIdx(requiredScopeId));
	}

	/**
	 *	ICFBamValueByScopeIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaInt64Col> lockByScopeIdx(ICFBamValueByScopeIdxKey key) {
		return( cfbam31Int64ColRepository.lockByScopeIdx(key.getRequiredScopeId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalDefSchemaId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaInt64Col> lockByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId) {
		return( cfbam31Int64ColRepository.lockByDefSchemaIdx(optionalDefSchemaId));
	}

	/**
	 *	ICFBamValueByDefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaInt64Col> lockByDefSchemaIdx(ICFBamValueByDefSchemaIdxKey key) {
		return( cfbam31Int64ColRepository.lockByDefSchemaIdx(key.getOptionalDefSchemaId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalPrevId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaInt64Col> lockByPrevIdx(@Param("prevId") CFLibDbKeyHash256 optionalPrevId) {
		return( cfbam31Int64ColRepository.lockByPrevIdx(optionalPrevId));
	}

	/**
	 *	ICFBamValueByPrevIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaInt64Col> lockByPrevIdx(ICFBamValueByPrevIdxKey key) {
		return( cfbam31Int64ColRepository.lockByPrevIdx(key.getOptionalPrevId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalNextId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaInt64Col> lockByNextIdx(@Param("nextId") CFLibDbKeyHash256 optionalNextId) {
		return( cfbam31Int64ColRepository.lockByNextIdx(optionalNextId));
	}

	/**
	 *	ICFBamValueByNextIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaInt64Col> lockByNextIdx(ICFBamValueByNextIdxKey key) {
		return( cfbam31Int64ColRepository.lockByNextIdx(key.getOptionalNextId()));
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
	public List<CFBamJpaInt64Col> lockByContPrevIdx(@Param("scopeId") CFLibDbKeyHash256 requiredScopeId,
		@Param("prevId") CFLibDbKeyHash256 optionalPrevId) {
		return( cfbam31Int64ColRepository.lockByContPrevIdx(requiredScopeId,
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
	public List<CFBamJpaInt64Col> lockByContPrevIdx(ICFBamValueByContPrevIdxKey key) {
		return( cfbam31Int64ColRepository.lockByContPrevIdx(key.getRequiredScopeId(), key.getOptionalPrevId()));
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
	public List<CFBamJpaInt64Col> lockByContNextIdx(@Param("scopeId") CFLibDbKeyHash256 requiredScopeId,
		@Param("nextId") CFLibDbKeyHash256 optionalNextId) {
		return( cfbam31Int64ColRepository.lockByContNextIdx(requiredScopeId,
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
	public List<CFBamJpaInt64Col> lockByContNextIdx(ICFBamValueByContNextIdxKey key) {
		return( cfbam31Int64ColRepository.lockByContNextIdx(key.getRequiredScopeId(), key.getOptionalNextId()));
	}

	// CFBamAtom specified lock-by-index methods

	// CFBamInt64Def specified lock-by-index methods

	// CFBamInt64Col specified lock-by-index methods

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredTableId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaInt64Col> lockByTableIdx(@Param("tableId") CFLibDbKeyHash256 requiredTableId) {
		return( cfbam31Int64ColRepository.lockByTableIdx(requiredTableId));
	}

	/**
	 *	ICFBamInt64ColByTableIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaInt64Col> lockByTableIdx(ICFBamInt64ColByTableIdxKey key) {
		return( cfbam31Int64ColRepository.lockByTableIdx(key.getRequiredTableId()));
	}

	// CFBamValue specified delete-by-index methods

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByIdIdx(@Param("id") CFLibDbKeyHash256 requiredId) {
		cfbam31Int64ColRepository.deleteByIdIdx(requiredId);
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
		cfbam31Int64ColRepository.deleteByUNameIdx(requiredScopeId,
			requiredName);
	}

	/**
	 *	ICFBamValueByUNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamValueByUNameIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByUNameIdx(ICFBamValueByUNameIdxKey key) {
		cfbam31Int64ColRepository.deleteByUNameIdx(key.getRequiredScopeId(), key.getRequiredName());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredScopeId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByScopeIdx(@Param("scopeId") CFLibDbKeyHash256 requiredScopeId) {
		cfbam31Int64ColRepository.deleteByScopeIdx(requiredScopeId);
	}

	/**
	 *	ICFBamValueByScopeIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamValueByScopeIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByScopeIdx(ICFBamValueByScopeIdxKey key) {
		cfbam31Int64ColRepository.deleteByScopeIdx(key.getRequiredScopeId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalDefSchemaId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId) {
		cfbam31Int64ColRepository.deleteByDefSchemaIdx(optionalDefSchemaId);
	}

	/**
	 *	ICFBamValueByDefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamValueByDefSchemaIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByDefSchemaIdx(ICFBamValueByDefSchemaIdxKey key) {
		cfbam31Int64ColRepository.deleteByDefSchemaIdx(key.getOptionalDefSchemaId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalPrevId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByPrevIdx(@Param("prevId") CFLibDbKeyHash256 optionalPrevId) {
		cfbam31Int64ColRepository.deleteByPrevIdx(optionalPrevId);
	}

	/**
	 *	ICFBamValueByPrevIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamValueByPrevIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByPrevIdx(ICFBamValueByPrevIdxKey key) {
		cfbam31Int64ColRepository.deleteByPrevIdx(key.getOptionalPrevId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalNextId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByNextIdx(@Param("nextId") CFLibDbKeyHash256 optionalNextId) {
		cfbam31Int64ColRepository.deleteByNextIdx(optionalNextId);
	}

	/**
	 *	ICFBamValueByNextIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamValueByNextIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByNextIdx(ICFBamValueByNextIdxKey key) {
		cfbam31Int64ColRepository.deleteByNextIdx(key.getOptionalNextId());
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
		cfbam31Int64ColRepository.deleteByContPrevIdx(requiredScopeId,
			optionalPrevId);
	}

	/**
	 *	ICFBamValueByContPrevIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamValueByContPrevIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByContPrevIdx(ICFBamValueByContPrevIdxKey key) {
		cfbam31Int64ColRepository.deleteByContPrevIdx(key.getRequiredScopeId(), key.getOptionalPrevId());
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
		cfbam31Int64ColRepository.deleteByContNextIdx(requiredScopeId,
			optionalNextId);
	}

	/**
	 *	ICFBamValueByContNextIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamValueByContNextIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByContNextIdx(ICFBamValueByContNextIdxKey key) {
		cfbam31Int64ColRepository.deleteByContNextIdx(key.getRequiredScopeId(), key.getOptionalNextId());
	}

	// CFBamAtom specified delete-by-index methods

	// CFBamInt64Def specified delete-by-index methods

	// CFBamInt64Col specified delete-by-index methods

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredTableId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByTableIdx(@Param("tableId") CFLibDbKeyHash256 requiredTableId) {
		cfbam31Int64ColRepository.deleteByTableIdx(requiredTableId);
	}

	/**
	 *	ICFBamInt64ColByTableIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamInt64ColByTableIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByTableIdx(ICFBamInt64ColByTableIdxKey key) {
		cfbam31Int64ColRepository.deleteByTableIdx(key.getRequiredTableId());
	}

}
