// Description: Java 25 Spring JPA Repository for SchemaDef

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
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import io.github.msobkow.v3_1.cfsec.cfsec.*;
import io.github.msobkow.v3_1.cfint.cfint.*;
import io.github.msobkow.v3_1.cfbam.cfbam.*;

/**
 *	JpaRepository for the CFBamJpaSchemaDef entities defined in io.github.msobkow.v3_1.cfbam.cfbam.jpa.
 *	The manufactured repositories try to provide a rich, do-it-all interface to the JPA data store, with both object and argument-based implementations of the interface defined.
 */
@Transactional(readOnly = true)
public interface CFBamJpaSchemaDefRepository extends JpaRepository<CFBamJpaSchemaDef, CFLibDbKeyHash256> {

	/**
	 *	Argument-based get database instance for compatibility with the current MSS code factory code base.
	 *
	 *		@param requiredId
	 *
	 *		@return The retrieved entity, usually from the JPA cache, or null if no such entity exists.
	 */
	@Query("select r from CFBamJpaScope r where r.requiredId = :id")
	CFBamJpaSchemaDef get(@Param("id") CFLibDbKeyHash256 requiredId);

	// CFBamJpaScope specified index readers

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamScopeByTenantIdxKey as arguments.
	 *
	 *		@param requiredTenantId
	 *
	 *		@return List&lt;CFBamJpaSchemaDef&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaScope r where r.requiredTenantId = :tenantId")
	List<CFBamJpaSchemaDef> findByTenantIdx(@Param("tenantId") CFLibDbKeyHash256 requiredTenantId);

	/**
	 *	CFBamScopeByTenantIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamScopeByTenantIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaSchemaDef> findByTenantIdx(ICFBamScopeByTenantIdxKey key) {
		return( findByTenantIdx(key.getRequiredTenantId()));
	}

	// CFBamJpaSchemaDef specified index readers

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamSchemaDefByCTenantIdxKey as arguments.
	 *
	 *		@param requiredCTenantId
	 *
	 *		@return List&lt;CFBamJpaSchemaDef&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaSchemaDef r where r.requiredCTenantId = :cTenantId")
	List<CFBamJpaSchemaDef> findByCTenantIdx(@Param("cTenantId") CFLibDbKeyHash256 requiredCTenantId);

	/**
	 *	CFBamSchemaDefByCTenantIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamSchemaDefByCTenantIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaSchemaDef> findByCTenantIdx(ICFBamSchemaDefByCTenantIdxKey key) {
		return( findByCTenantIdx(key.getRequiredCTenantId()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamSchemaDefByMinorVersionIdxKey as arguments.
	 *
	 *		@param requiredMinorVersionId
	 *
	 *		@return List&lt;CFBamJpaSchemaDef&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaSchemaDef r where r.requiredMinorVersionId = :minorVersionId")
	List<CFBamJpaSchemaDef> findByMinorVersionIdx(@Param("minorVersionId") CFLibDbKeyHash256 requiredMinorVersionId);

	/**
	 *	CFBamSchemaDefByMinorVersionIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamSchemaDefByMinorVersionIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaSchemaDef> findByMinorVersionIdx(ICFBamSchemaDefByMinorVersionIdxKey key) {
		return( findByMinorVersionIdx(key.getRequiredMinorVersionId()));
	}

	/**
	 *	Read an entity using the columns of the CFBamSchemaDefByUNameIdxKey as arguments.
	 *
	 *		@param requiredMinorVersionId
	 *		@param requiredName
	 *
	 *		@return The found entity, typically from the JPA cache, or null if no such entity exists.
	 */
	@Query("select r from CFBamJpaSchemaDef r where r.requiredMinorVersionId = :minorVersionId and r.requiredName = :name")
	CFBamJpaSchemaDef findByUNameIdx(@Param("minorVersionId") CFLibDbKeyHash256 requiredMinorVersionId,
		@Param("name") String requiredName);

	/**
	 *	CFBamSchemaDefByUNameIdxKey entity reader convenience method for object-based access.
	 *
	 *		@param key The CFBamSchemaDefByUNameIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity, typically from the JPA cache, or null if no such entity exists.
	 */
	default CFBamJpaSchemaDef findByUNameIdx(ICFBamSchemaDefByUNameIdxKey key) {
		return( findByUNameIdx(key.getRequiredMinorVersionId(), key.getRequiredName()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamSchemaDefByAuthEMailIdxKey as arguments.
	 *
	 *		@param requiredCTenantId
	 *		@param requiredAuthorEMail
	 *
	 *		@return List&lt;CFBamJpaSchemaDef&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaSchemaDef r where r.requiredCTenantId = :cTenantId and r.requiredAuthorEMail = :authorEMail")
	List<CFBamJpaSchemaDef> findByAuthEMailIdx(@Param("cTenantId") CFLibDbKeyHash256 requiredCTenantId,
		@Param("authorEMail") String requiredAuthorEMail);

	/**
	 *	CFBamSchemaDefByAuthEMailIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamSchemaDefByAuthEMailIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaSchemaDef> findByAuthEMailIdx(ICFBamSchemaDefByAuthEMailIdxKey key) {
		return( findByAuthEMailIdx(key.getRequiredCTenantId(), key.getRequiredAuthorEMail()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamSchemaDefByProjectURLIdxKey as arguments.
	 *
	 *		@param requiredCTenantId
	 *		@param requiredProjectURL
	 *
	 *		@return List&lt;CFBamJpaSchemaDef&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaSchemaDef r where r.requiredCTenantId = :cTenantId and r.requiredProjectURL = :projectURL")
	List<CFBamJpaSchemaDef> findByProjectURLIdx(@Param("cTenantId") CFLibDbKeyHash256 requiredCTenantId,
		@Param("projectURL") String requiredProjectURL);

	/**
	 *	CFBamSchemaDefByProjectURLIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamSchemaDefByProjectURLIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaSchemaDef> findByProjectURLIdx(ICFBamSchemaDefByProjectURLIdxKey key) {
		return( findByProjectURLIdx(key.getRequiredCTenantId(), key.getRequiredProjectURL()));
	}

	/**
	 *	Read an entity using the columns of the CFBamSchemaDefByPubURIIdxKey as arguments.
	 *
	 *		@param requiredCTenantId
	 *		@param requiredPublishURI
	 *
	 *		@return The found entity, typically from the JPA cache, or null if no such entity exists.
	 */
	@Query("select r from CFBamJpaSchemaDef r where r.requiredCTenantId = :cTenantId and r.requiredPublishURI = :publishURI")
	CFBamJpaSchemaDef findByPubURIIdx(@Param("cTenantId") CFLibDbKeyHash256 requiredCTenantId,
		@Param("publishURI") String requiredPublishURI);

	/**
	 *	CFBamSchemaDefByPubURIIdxKey entity reader convenience method for object-based access.
	 *
	 *		@param key The CFBamSchemaDefByPubURIIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity, typically from the JPA cache, or null if no such entity exists.
	 */
	default CFBamJpaSchemaDef findByPubURIIdx(ICFBamSchemaDefByPubURIIdxKey key) {
		return( findByPubURIIdx(key.getRequiredCTenantId(), key.getRequiredPublishURI()));
	}

	// CFBamJpaScope specified delete-by-index methods

	/**
	 *	Argument-based lock database entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredId
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaScope r where r.requiredId = :id")
	CFBamJpaSchemaDef lockByIdIdx(@Param("id") CFLibDbKeyHash256 requiredId);

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredTenantId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaScope r where r.requiredTenantId = :tenantId")
	List<CFBamJpaSchemaDef> lockByTenantIdx(@Param("tenantId") CFLibDbKeyHash256 requiredTenantId);

	/**
	 *	CFBamScopeByTenantIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaSchemaDef> lockByTenantIdx(ICFBamScopeByTenantIdxKey key) {
		return( lockByTenantIdx(key.getRequiredTenantId()));
	}

	// CFBamJpaSchemaDef specified delete-by-index methods

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredCTenantId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaSchemaDef r where r.requiredCTenantId = :cTenantId")
	List<CFBamJpaSchemaDef> lockByCTenantIdx(@Param("cTenantId") CFLibDbKeyHash256 requiredCTenantId);

	/**
	 *	CFBamSchemaDefByCTenantIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaSchemaDef> lockByCTenantIdx(ICFBamSchemaDefByCTenantIdxKey key) {
		return( lockByCTenantIdx(key.getRequiredCTenantId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredMinorVersionId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaSchemaDef r where r.requiredMinorVersionId = :minorVersionId")
	List<CFBamJpaSchemaDef> lockByMinorVersionIdx(@Param("minorVersionId") CFLibDbKeyHash256 requiredMinorVersionId);

	/**
	 *	CFBamSchemaDefByMinorVersionIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaSchemaDef> lockByMinorVersionIdx(ICFBamSchemaDefByMinorVersionIdxKey key) {
		return( lockByMinorVersionIdx(key.getRequiredMinorVersionId()));
	}

	/**
	 *	Argument-based lock database entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredMinorVersionId
	 *		@param requiredName
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaSchemaDef r where r.requiredMinorVersionId = :minorVersionId and r.requiredName = :name")
	CFBamJpaSchemaDef lockByUNameIdx(@Param("minorVersionId") CFLibDbKeyHash256 requiredMinorVersionId,
		@Param("name") String requiredName);

	/**
	 *	CFBamSchemaDefByUNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	default CFBamJpaSchemaDef lockByUNameIdx(ICFBamSchemaDefByUNameIdxKey key) {
		return( lockByUNameIdx(key.getRequiredMinorVersionId(), key.getRequiredName()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredCTenantId
	 *		@param requiredAuthorEMail
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaSchemaDef r where r.requiredCTenantId = :cTenantId and r.requiredAuthorEMail = :authorEMail")
	List<CFBamJpaSchemaDef> lockByAuthEMailIdx(@Param("cTenantId") CFLibDbKeyHash256 requiredCTenantId,
		@Param("authorEMail") String requiredAuthorEMail);

	/**
	 *	CFBamSchemaDefByAuthEMailIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaSchemaDef> lockByAuthEMailIdx(ICFBamSchemaDefByAuthEMailIdxKey key) {
		return( lockByAuthEMailIdx(key.getRequiredCTenantId(), key.getRequiredAuthorEMail()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredCTenantId
	 *		@param requiredProjectURL
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaSchemaDef r where r.requiredCTenantId = :cTenantId and r.requiredProjectURL = :projectURL")
	List<CFBamJpaSchemaDef> lockByProjectURLIdx(@Param("cTenantId") CFLibDbKeyHash256 requiredCTenantId,
		@Param("projectURL") String requiredProjectURL);

	/**
	 *	CFBamSchemaDefByProjectURLIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaSchemaDef> lockByProjectURLIdx(ICFBamSchemaDefByProjectURLIdxKey key) {
		return( lockByProjectURLIdx(key.getRequiredCTenantId(), key.getRequiredProjectURL()));
	}

	/**
	 *	Argument-based lock database entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredCTenantId
	 *		@param requiredPublishURI
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaSchemaDef r where r.requiredCTenantId = :cTenantId and r.requiredPublishURI = :publishURI")
	CFBamJpaSchemaDef lockByPubURIIdx(@Param("cTenantId") CFLibDbKeyHash256 requiredCTenantId,
		@Param("publishURI") String requiredPublishURI);

	/**
	 *	CFBamSchemaDefByPubURIIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	default CFBamJpaSchemaDef lockByPubURIIdx(ICFBamSchemaDefByPubURIIdxKey key) {
		return( lockByPubURIIdx(key.getRequiredCTenantId(), key.getRequiredPublishURI()));
	}

	// CFBamJpaScope specified delete-by-index methods

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaScope r where r.requiredId = :id")
	void deleteByIdIdx(@Param("id") CFLibDbKeyHash256 requiredId);

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredTenantId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaScope r where r.requiredTenantId = :tenantId")
	void deleteByTenantIdx(@Param("tenantId") CFLibDbKeyHash256 requiredTenantId);

	/**
	 *	CFBamScopeByTenantIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamScopeByTenantIdxKey of the entity to be locked.
	 */
	default void deleteByTenantIdx(ICFBamScopeByTenantIdxKey key) {
		deleteByTenantIdx(key.getRequiredTenantId());
	}

	// CFBamJpaSchemaDef specified delete-by-index methods

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredCTenantId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaSchemaDef r where r.requiredCTenantId = :cTenantId")
	void deleteByCTenantIdx(@Param("cTenantId") CFLibDbKeyHash256 requiredCTenantId);

	/**
	 *	CFBamSchemaDefByCTenantIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamSchemaDefByCTenantIdxKey of the entity to be locked.
	 */
	default void deleteByCTenantIdx(ICFBamSchemaDefByCTenantIdxKey key) {
		deleteByCTenantIdx(key.getRequiredCTenantId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredMinorVersionId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaSchemaDef r where r.requiredMinorVersionId = :minorVersionId")
	void deleteByMinorVersionIdx(@Param("minorVersionId") CFLibDbKeyHash256 requiredMinorVersionId);

	/**
	 *	CFBamSchemaDefByMinorVersionIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamSchemaDefByMinorVersionIdxKey of the entity to be locked.
	 */
	default void deleteByMinorVersionIdx(ICFBamSchemaDefByMinorVersionIdxKey key) {
		deleteByMinorVersionIdx(key.getRequiredMinorVersionId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredMinorVersionId
	 *		@param requiredName
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaSchemaDef r where r.requiredMinorVersionId = :minorVersionId and r.requiredName = :name")
	void deleteByUNameIdx(@Param("minorVersionId") CFLibDbKeyHash256 requiredMinorVersionId,
		@Param("name") String requiredName);

	/**
	 *	CFBamSchemaDefByUNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamSchemaDefByUNameIdxKey of the entity to be locked.
	 */
	default void deleteByUNameIdx(ICFBamSchemaDefByUNameIdxKey key) {
		deleteByUNameIdx(key.getRequiredMinorVersionId(), key.getRequiredName());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredCTenantId
	 *		@param requiredAuthorEMail
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaSchemaDef r where r.requiredCTenantId = :cTenantId and r.requiredAuthorEMail = :authorEMail")
	void deleteByAuthEMailIdx(@Param("cTenantId") CFLibDbKeyHash256 requiredCTenantId,
		@Param("authorEMail") String requiredAuthorEMail);

	/**
	 *	CFBamSchemaDefByAuthEMailIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamSchemaDefByAuthEMailIdxKey of the entity to be locked.
	 */
	default void deleteByAuthEMailIdx(ICFBamSchemaDefByAuthEMailIdxKey key) {
		deleteByAuthEMailIdx(key.getRequiredCTenantId(), key.getRequiredAuthorEMail());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredCTenantId
	 *		@param requiredProjectURL
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaSchemaDef r where r.requiredCTenantId = :cTenantId and r.requiredProjectURL = :projectURL")
	void deleteByProjectURLIdx(@Param("cTenantId") CFLibDbKeyHash256 requiredCTenantId,
		@Param("projectURL") String requiredProjectURL);

	/**
	 *	CFBamSchemaDefByProjectURLIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamSchemaDefByProjectURLIdxKey of the entity to be locked.
	 */
	default void deleteByProjectURLIdx(ICFBamSchemaDefByProjectURLIdxKey key) {
		deleteByProjectURLIdx(key.getRequiredCTenantId(), key.getRequiredProjectURL());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredCTenantId
	 *		@param requiredPublishURI
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaSchemaDef r where r.requiredCTenantId = :cTenantId and r.requiredPublishURI = :publishURI")
	void deleteByPubURIIdx(@Param("cTenantId") CFLibDbKeyHash256 requiredCTenantId,
		@Param("publishURI") String requiredPublishURI);

	/**
	 *	CFBamSchemaDefByPubURIIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamSchemaDefByPubURIIdxKey of the entity to be locked.
	 */
	default void deleteByPubURIIdx(ICFBamSchemaDefByPubURIIdxKey key) {
		deleteByPubURIIdx(key.getRequiredCTenantId(), key.getRequiredPublishURI());
	}

}
