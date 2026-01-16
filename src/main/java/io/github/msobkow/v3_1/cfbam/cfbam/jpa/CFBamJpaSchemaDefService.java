// Description: Java 25 Spring JPA Service for SchemaDef

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
 *	Service for the CFBamSchemaDef entities defined in io.github.msobkow.v3_1.cfbam.cfbam.jpa
 *	using the CFBamSchemaDefRepository to access them.
 */
@Service("cfbam31JpaSchemaDefService")
public class CFBamJpaSchemaDefService {

	@Autowired
	@Qualifier("cfbam31EntityManagerFactory")
	private LocalContainerEntityManagerFactoryBean cfbam31EntityManagerFactory;

	@Autowired
	private CFBamJpaSchemaDefRepository cfbam31SchemaDefRepository;

	/**
	 *	Create an entity, generating any database keys required along the way.
	 *
	 *		@param	data	The entity to be instantiated; must be a specific instance of CFBamJpaSchemaDef, not a subclass.
	 *
	 *		@return The updated/created entity.
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaSchemaDef create(CFBamJpaSchemaDef data) {
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
		if(data.getRequiredCTenantId() == null || data.getRequiredCTenantId().isNull()) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredCTenantId");
		}
		if(data.getRequiredMinorVersionId() == null || data.getRequiredMinorVersionId().isNull()) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredMinorVersionId");
		}
		if(data.getRequiredName() == null) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredName");
		}
		if(data.getRequiredCopyrightPeriod() == null) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredCopyrightPeriod");
		}
		if(data.getRequiredCopyrightHolder() == null) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredCopyrightHolder");
		}
		if(data.getRequiredAuthorEMail() == null) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredAuthorEMail");
		}
		if(data.getRequiredProjectURL() == null) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredProjectURL");
		}
		if(data.getRequiredPublishURI() == null) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredPublishURI");
		}
		try {
			if (data.getRequiredId() == null || data.getRequiredId().isNull()) {
				data.setRequiredId(new CFLibDbKeyHash256(0));
				generatedRequiredId = true;
			}
			LocalDateTime now = LocalDateTime.now();
			data.setCreatedAt(now);
			data.setUpdatedAt(now);
			if(data.getPKey() != null && cfbam31SchemaDefRepository.existsById((CFLibDbKeyHash256)data.getPKey())) {
				return( (CFBamJpaSchemaDef)(cfbam31SchemaDefRepository.findById((CFLibDbKeyHash256)(data.getPKey())).get()));
			}
			return cfbam31SchemaDefRepository.save(data);
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
	public CFBamJpaSchemaDef update(CFBamJpaSchemaDef data) {
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
		if(data.getRequiredCTenantId() == null || data.getRequiredCTenantId().isNull()) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredCTenantId");
		}
		if(data.getRequiredMinorVersionId() == null || data.getRequiredMinorVersionId().isNull()) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredMinorVersionId");
		}
		if(data.getRequiredName() == null) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredName");
		}
		if(data.getRequiredCopyrightPeriod() == null) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredCopyrightPeriod");
		}
		if(data.getRequiredCopyrightHolder() == null) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredCopyrightHolder");
		}
		if(data.getRequiredAuthorEMail() == null) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredAuthorEMail");
		}
		if(data.getRequiredProjectURL() == null) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredProjectURL");
		}
		if(data.getRequiredPublishURI() == null) {
			throw new CFLibNullArgumentException(getClass(),
				S_ProcName,
				0,
				"data.requiredPublishURI");
		}
		// Ensure the entity exists and that the revision matches
		CFBamJpaSchemaDef existing = cfbam31SchemaDefRepository.findById((CFLibDbKeyHash256)(data.getPKey()))
			.orElseThrow(() -> new CFLibCollisionDetectedException(getClass(), S_ProcName, data.getPKey()));
		if (existing.getRequiredRevision() != data.getRequiredRevision()) {
			throw new CFLibCollisionDetectedException(getClass(), S_ProcName, data.getPKey());
		}
		// Apply superior data relationships of CFBamScope to existing object
		existing.setRequiredOwnerTenant(data.getRequiredTenantId());
		// Apply data columns of CFBamScope to existing object
		// Apply superior data relationships of CFBamSchemaDef to existing object
		existing.setRequiredContainerMinorVersion(data.getRequiredMinorVersionId());
		existing.setRequiredOwnerCTenant(data.getRequiredCTenantId());
		// Apply data columns of CFBamSchemaDef to existing object
		existing.setRequiredName(data.getRequiredName());
		existing.setOptionalDbName(data.getOptionalDbName());
		existing.setOptionalShortName(data.getOptionalShortName());
		existing.setOptionalLabel(data.getOptionalLabel());
		existing.setOptionalShortDescription(data.getOptionalShortDescription());
		existing.setOptionalDescription(data.getOptionalDescription());
		existing.setRequiredCopyrightPeriod(data.getRequiredCopyrightPeriod());
		existing.setRequiredCopyrightHolder(data.getRequiredCopyrightHolder());
		existing.setRequiredAuthorEMail(data.getRequiredAuthorEMail());
		existing.setRequiredProjectURL(data.getRequiredProjectURL());
		existing.setRequiredPublishURI(data.getRequiredPublishURI());
		// Update the audit columns
		data.setUpdatedAt(LocalDateTime.now());
		// Save the changes we've made
		return cfbam31SchemaDefRepository.save(existing);
	}

	/**
	 *	Argument-based find database instance for compatibility with the current MSS code factory code base.
	 *
	 *		@param requiredId
	 *
	 *		@return The retrieved entity, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaSchemaDef find(@Param("id") CFLibDbKeyHash256 requiredId) {
		return( cfbam31SchemaDefRepository.get(requiredId));
	}

	/**
	 *	Retrieve all entities from the repository
	 *
	 *		@return The list of retrieved entities, which may be empty
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaSchemaDef> findAll() {
		return( cfbam31SchemaDefRepository.findAll() );
	}

	// CFBamScope specified index finders

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamScopeByTenantIdxKey as arguments.
	 *
	 *		@param requiredTenantId
	 *
	 *		@return List&lt;CFBamJpaSchemaDef&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaSchemaDef> findByTenantIdx(@Param("tenantId") CFLibDbKeyHash256 requiredTenantId) {
		return( cfbam31SchemaDefRepository.findByTenantIdx(requiredTenantId));
	}

	/**
	 *	ICFBamScopeByTenantIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamScopeByTenantIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaSchemaDef> findByTenantIdx(ICFBamScopeByTenantIdxKey key) {
		return( cfbam31SchemaDefRepository.findByTenantIdx(key.getRequiredTenantId()));
	}

	// CFBamSchemaDef specified index finders

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamSchemaDefByCTenantIdxKey as arguments.
	 *
	 *		@param requiredCTenantId
	 *
	 *		@return List&lt;CFBamJpaSchemaDef&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaSchemaDef> findByCTenantIdx(@Param("cTenantId") CFLibDbKeyHash256 requiredCTenantId) {
		return( cfbam31SchemaDefRepository.findByCTenantIdx(requiredCTenantId));
	}

	/**
	 *	ICFBamSchemaDefByCTenantIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamSchemaDefByCTenantIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaSchemaDef> findByCTenantIdx(ICFBamSchemaDefByCTenantIdxKey key) {
		return( cfbam31SchemaDefRepository.findByCTenantIdx(key.getRequiredCTenantId()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamSchemaDefByMinorVersionIdxKey as arguments.
	 *
	 *		@param requiredMinorVersionId
	 *
	 *		@return List&lt;CFBamJpaSchemaDef&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaSchemaDef> findByMinorVersionIdx(@Param("minorVersionId") CFLibDbKeyHash256 requiredMinorVersionId) {
		return( cfbam31SchemaDefRepository.findByMinorVersionIdx(requiredMinorVersionId));
	}

	/**
	 *	ICFBamSchemaDefByMinorVersionIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamSchemaDefByMinorVersionIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaSchemaDef> findByMinorVersionIdx(ICFBamSchemaDefByMinorVersionIdxKey key) {
		return( cfbam31SchemaDefRepository.findByMinorVersionIdx(key.getRequiredMinorVersionId()));
	}

	/**
	 *	Find an entity using the columns of the ICFBamSchemaDefByUNameIdxKey as arguments.
	 *
	 *		@param requiredMinorVersionId
	 *		@param requiredName
	 *
	 *		@return The found entity, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaSchemaDef findByUNameIdx(@Param("minorVersionId") CFLibDbKeyHash256 requiredMinorVersionId,
		@Param("name") String requiredName) {
		return( cfbam31SchemaDefRepository.findByUNameIdx(requiredMinorVersionId,
			requiredName));
	}

	/**
	 *	ICFBamSchemaDefByUNameIdxKey entity finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamSchemaDefByUNameIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaSchemaDef findByUNameIdx(ICFBamSchemaDefByUNameIdxKey key) {
		return( cfbam31SchemaDefRepository.findByUNameIdx(key.getRequiredMinorVersionId(), key.getRequiredName()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamSchemaDefByAuthEMailIdxKey as arguments.
	 *
	 *		@param requiredCTenantId
	 *		@param requiredAuthorEMail
	 *
	 *		@return List&lt;CFBamJpaSchemaDef&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaSchemaDef> findByAuthEMailIdx(@Param("cTenantId") CFLibDbKeyHash256 requiredCTenantId,
		@Param("authorEMail") String requiredAuthorEMail) {
		return( cfbam31SchemaDefRepository.findByAuthEMailIdx(requiredCTenantId,
			requiredAuthorEMail));
	}

	/**
	 *	ICFBamSchemaDefByAuthEMailIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamSchemaDefByAuthEMailIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaSchemaDef> findByAuthEMailIdx(ICFBamSchemaDefByAuthEMailIdxKey key) {
		return( cfbam31SchemaDefRepository.findByAuthEMailIdx(key.getRequiredCTenantId(), key.getRequiredAuthorEMail()));
	}

	/**
	 *	Find zero or more entities into a List using the columns of the ICFBamSchemaDefByProjectURLIdxKey as arguments.
	 *
	 *		@param requiredCTenantId
	 *		@param requiredProjectURL
	 *
	 *		@return List&lt;CFBamJpaSchemaDef&gt; of the found entities, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaSchemaDef> findByProjectURLIdx(@Param("cTenantId") CFLibDbKeyHash256 requiredCTenantId,
		@Param("projectURL") String requiredProjectURL) {
		return( cfbam31SchemaDefRepository.findByProjectURLIdx(requiredCTenantId,
			requiredProjectURL));
	}

	/**
	 *	ICFBamSchemaDefByProjectURLIdxKey entity list finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamSchemaDefByProjectURLIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaSchemaDef> findByProjectURLIdx(ICFBamSchemaDefByProjectURLIdxKey key) {
		return( cfbam31SchemaDefRepository.findByProjectURLIdx(key.getRequiredCTenantId(), key.getRequiredProjectURL()));
	}

	/**
	 *	Find an entity using the columns of the ICFBamSchemaDefByPubURIIdxKey as arguments.
	 *
	 *		@param requiredCTenantId
	 *		@param requiredPublishURI
	 *
	 *		@return The found entity, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaSchemaDef findByPubURIIdx(@Param("cTenantId") CFLibDbKeyHash256 requiredCTenantId,
		@Param("publishURI") String requiredPublishURI) {
		return( cfbam31SchemaDefRepository.findByPubURIIdx(requiredCTenantId,
			requiredPublishURI));
	}

	/**
	 *	ICFBamSchemaDefByPubURIIdxKey entity finder convenience method for object-based access.
	 *
	 *		@param key The ICFBamSchemaDefByPubURIIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaSchemaDef findByPubURIIdx(ICFBamSchemaDefByPubURIIdxKey key) {
		return( cfbam31SchemaDefRepository.findByPubURIIdx(key.getRequiredCTenantId(), key.getRequiredPublishURI()));
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
	public CFBamJpaSchemaDef lockByIdIdx(@Param("id") CFLibDbKeyHash256 requiredId) {
		return( cfbam31SchemaDefRepository.lockByIdIdx(requiredId));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredTenantId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaSchemaDef> lockByTenantIdx(@Param("tenantId") CFLibDbKeyHash256 requiredTenantId) {
		return( cfbam31SchemaDefRepository.lockByTenantIdx(requiredTenantId));
	}

	/**
	 *	ICFBamScopeByTenantIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaSchemaDef> lockByTenantIdx(ICFBamScopeByTenantIdxKey key) {
		return( cfbam31SchemaDefRepository.lockByTenantIdx(key.getRequiredTenantId()));
	}

	// CFBamSchemaDef specified lock-by-index methods

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredCTenantId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaSchemaDef> lockByCTenantIdx(@Param("cTenantId") CFLibDbKeyHash256 requiredCTenantId) {
		return( cfbam31SchemaDefRepository.lockByCTenantIdx(requiredCTenantId));
	}

	/**
	 *	ICFBamSchemaDefByCTenantIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaSchemaDef> lockByCTenantIdx(ICFBamSchemaDefByCTenantIdxKey key) {
		return( cfbam31SchemaDefRepository.lockByCTenantIdx(key.getRequiredCTenantId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredMinorVersionId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaSchemaDef> lockByMinorVersionIdx(@Param("minorVersionId") CFLibDbKeyHash256 requiredMinorVersionId) {
		return( cfbam31SchemaDefRepository.lockByMinorVersionIdx(requiredMinorVersionId));
	}

	/**
	 *	ICFBamSchemaDefByMinorVersionIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaSchemaDef> lockByMinorVersionIdx(ICFBamSchemaDefByMinorVersionIdxKey key) {
		return( cfbam31SchemaDefRepository.lockByMinorVersionIdx(key.getRequiredMinorVersionId()));
	}

	/**
	 *	Argument-based lock database entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredMinorVersionId
	 *		@param requiredName
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaSchemaDef lockByUNameIdx(@Param("minorVersionId") CFLibDbKeyHash256 requiredMinorVersionId,
		@Param("name") String requiredName) {
		return( cfbam31SchemaDefRepository.lockByUNameIdx(requiredMinorVersionId,
			requiredName));
	}

	/**
	 *	ICFBamSchemaDefByUNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaSchemaDef lockByUNameIdx(ICFBamSchemaDefByUNameIdxKey key) {
		return( cfbam31SchemaDefRepository.lockByUNameIdx(key.getRequiredMinorVersionId(), key.getRequiredName()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredCTenantId
	 *		@param requiredAuthorEMail
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaSchemaDef> lockByAuthEMailIdx(@Param("cTenantId") CFLibDbKeyHash256 requiredCTenantId,
		@Param("authorEMail") String requiredAuthorEMail) {
		return( cfbam31SchemaDefRepository.lockByAuthEMailIdx(requiredCTenantId,
			requiredAuthorEMail));
	}

	/**
	 *	ICFBamSchemaDefByAuthEMailIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaSchemaDef> lockByAuthEMailIdx(ICFBamSchemaDefByAuthEMailIdxKey key) {
		return( cfbam31SchemaDefRepository.lockByAuthEMailIdx(key.getRequiredCTenantId(), key.getRequiredAuthorEMail()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredCTenantId
	 *		@param requiredProjectURL
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaSchemaDef> lockByProjectURLIdx(@Param("cTenantId") CFLibDbKeyHash256 requiredCTenantId,
		@Param("projectURL") String requiredProjectURL) {
		return( cfbam31SchemaDefRepository.lockByProjectURLIdx(requiredCTenantId,
			requiredProjectURL));
	}

	/**
	 *	ICFBamSchemaDefByProjectURLIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public List<CFBamJpaSchemaDef> lockByProjectURLIdx(ICFBamSchemaDefByProjectURLIdxKey key) {
		return( cfbam31SchemaDefRepository.lockByProjectURLIdx(key.getRequiredCTenantId(), key.getRequiredProjectURL()));
	}

	/**
	 *	Argument-based lock database entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredCTenantId
	 *		@param requiredPublishURI
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaSchemaDef lockByPubURIIdx(@Param("cTenantId") CFLibDbKeyHash256 requiredCTenantId,
		@Param("publishURI") String requiredPublishURI) {
		return( cfbam31SchemaDefRepository.lockByPubURIIdx(requiredCTenantId,
			requiredPublishURI));
	}

	/**
	 *	ICFBamSchemaDefByPubURIIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public CFBamJpaSchemaDef lockByPubURIIdx(ICFBamSchemaDefByPubURIIdxKey key) {
		return( cfbam31SchemaDefRepository.lockByPubURIIdx(key.getRequiredCTenantId(), key.getRequiredPublishURI()));
	}

	// CFBamScope specified delete-by-index methods

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByIdIdx(@Param("id") CFLibDbKeyHash256 requiredId) {
		cfbam31SchemaDefRepository.deleteByIdIdx(requiredId);
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredTenantId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByTenantIdx(@Param("tenantId") CFLibDbKeyHash256 requiredTenantId) {
		cfbam31SchemaDefRepository.deleteByTenantIdx(requiredTenantId);
	}

	/**
	 *	ICFBamScopeByTenantIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamScopeByTenantIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByTenantIdx(ICFBamScopeByTenantIdxKey key) {
		cfbam31SchemaDefRepository.deleteByTenantIdx(key.getRequiredTenantId());
	}

	// CFBamSchemaDef specified delete-by-index methods

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredCTenantId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByCTenantIdx(@Param("cTenantId") CFLibDbKeyHash256 requiredCTenantId) {
		cfbam31SchemaDefRepository.deleteByCTenantIdx(requiredCTenantId);
	}

	/**
	 *	ICFBamSchemaDefByCTenantIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamSchemaDefByCTenantIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByCTenantIdx(ICFBamSchemaDefByCTenantIdxKey key) {
		cfbam31SchemaDefRepository.deleteByCTenantIdx(key.getRequiredCTenantId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredMinorVersionId
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByMinorVersionIdx(@Param("minorVersionId") CFLibDbKeyHash256 requiredMinorVersionId) {
		cfbam31SchemaDefRepository.deleteByMinorVersionIdx(requiredMinorVersionId);
	}

	/**
	 *	ICFBamSchemaDefByMinorVersionIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamSchemaDefByMinorVersionIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByMinorVersionIdx(ICFBamSchemaDefByMinorVersionIdxKey key) {
		cfbam31SchemaDefRepository.deleteByMinorVersionIdx(key.getRequiredMinorVersionId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredMinorVersionId
	 *		@param requiredName
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByUNameIdx(@Param("minorVersionId") CFLibDbKeyHash256 requiredMinorVersionId,
		@Param("name") String requiredName) {
		cfbam31SchemaDefRepository.deleteByUNameIdx(requiredMinorVersionId,
			requiredName);
	}

	/**
	 *	ICFBamSchemaDefByUNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamSchemaDefByUNameIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByUNameIdx(ICFBamSchemaDefByUNameIdxKey key) {
		cfbam31SchemaDefRepository.deleteByUNameIdx(key.getRequiredMinorVersionId(), key.getRequiredName());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredCTenantId
	 *		@param requiredAuthorEMail
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByAuthEMailIdx(@Param("cTenantId") CFLibDbKeyHash256 requiredCTenantId,
		@Param("authorEMail") String requiredAuthorEMail) {
		cfbam31SchemaDefRepository.deleteByAuthEMailIdx(requiredCTenantId,
			requiredAuthorEMail);
	}

	/**
	 *	ICFBamSchemaDefByAuthEMailIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamSchemaDefByAuthEMailIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByAuthEMailIdx(ICFBamSchemaDefByAuthEMailIdxKey key) {
		cfbam31SchemaDefRepository.deleteByAuthEMailIdx(key.getRequiredCTenantId(), key.getRequiredAuthorEMail());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredCTenantId
	 *		@param requiredProjectURL
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByProjectURLIdx(@Param("cTenantId") CFLibDbKeyHash256 requiredCTenantId,
		@Param("projectURL") String requiredProjectURL) {
		cfbam31SchemaDefRepository.deleteByProjectURLIdx(requiredCTenantId,
			requiredProjectURL);
	}

	/**
	 *	ICFBamSchemaDefByProjectURLIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamSchemaDefByProjectURLIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByProjectURLIdx(ICFBamSchemaDefByProjectURLIdxKey key) {
		cfbam31SchemaDefRepository.deleteByProjectURLIdx(key.getRequiredCTenantId(), key.getRequiredProjectURL());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredCTenantId
	 *		@param requiredPublishURI
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByPubURIIdx(@Param("cTenantId") CFLibDbKeyHash256 requiredCTenantId,
		@Param("publishURI") String requiredPublishURI) {
		cfbam31SchemaDefRepository.deleteByPubURIIdx(requiredCTenantId,
			requiredPublishURI);
	}

	/**
	 *	ICFBamSchemaDefByPubURIIdxKey based lock method for object-based access.
	 *
	 *		@param key The ICFBamSchemaDefByPubURIIdxKey of the entity to be locked.
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "cfbam31TransactionManager")
	public void deleteByPubURIIdx(ICFBamSchemaDefByPubURIIdxKey key) {
		cfbam31SchemaDefRepository.deleteByPubURIIdx(key.getRequiredCTenantId(), key.getRequiredPublishURI());
	}

}
