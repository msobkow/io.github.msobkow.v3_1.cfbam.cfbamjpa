// Description: Java 25 Spring JPA Repository for SchemaRef

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
 *	JpaRepository for the CFBamJpaSchemaRef entities defined in io.github.msobkow.v3_1.cfbam.cfbam.jpa.
 *	The manufactured repositories try to provide a rich, do-it-all interface to the JPA data store, with both object and argument-based implementations of the interface defined.
 */
@Transactional(readOnly = true)
public interface CFBamJpaSchemaRefRepository extends JpaRepository<CFBamJpaSchemaRef, CFLibDbKeyHash256> {

	/**
	 *	Argument-based get database instance for compatibility with the current MSS code factory code base.
	 *
	 *		@param requiredId
	 *
	 *		@return The retrieved entity, usually from the JPA cache, or null if no such entity exists.
	 */
	@Query("select r from CFBamJpaScope r where r.requiredId = :id")
	CFBamJpaSchemaRef get(@Param("id") CFLibDbKeyHash256 requiredId);

	// CFBamJpaScope specified index readers

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamScopeByTenantIdxKey as arguments.
	 *
	 *		@param requiredTenantId
	 *
	 *		@return List&lt;CFBamJpaSchemaRef&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaScope r where r.requiredTenantId = :tenantId")
	List<CFBamJpaSchemaRef> findByTenantIdx(@Param("tenantId") CFLibDbKeyHash256 requiredTenantId);

	/**
	 *	CFBamScopeByTenantIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamScopeByTenantIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaSchemaRef> findByTenantIdx(ICFBamScopeByTenantIdxKey key) {
		return( findByTenantIdx(key.getRequiredTenantId()));
	}

	// CFBamJpaSchemaRef specified index readers

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamSchemaRefBySchemaIdxKey as arguments.
	 *
	 *		@param requiredSchemaId
	 *
	 *		@return List&lt;CFBamJpaSchemaRef&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaSchemaRef r where r.requiredContainerSchema.requiredId = :schemaId")
	List<CFBamJpaSchemaRef> findBySchemaIdx(@Param("schemaId") CFLibDbKeyHash256 requiredSchemaId);

	/**
	 *	CFBamSchemaRefBySchemaIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamSchemaRefBySchemaIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaSchemaRef> findBySchemaIdx(ICFBamSchemaRefBySchemaIdxKey key) {
		return( findBySchemaIdx(key.getRequiredSchemaId()));
	}

	/**
	 *	Read an entity using the columns of the CFBamSchemaRefByUNameIdxKey as arguments.
	 *
	 *		@param requiredSchemaId
	 *		@param requiredName
	 *
	 *		@return The found entity, typically from the JPA cache, or null if no such entity exists.
	 */
	@Query("select r from CFBamJpaSchemaRef r where r.requiredContainerSchema.requiredId = :schemaId and r.requiredName = :name")
	CFBamJpaSchemaRef findByUNameIdx(@Param("schemaId") CFLibDbKeyHash256 requiredSchemaId,
		@Param("name") String requiredName);

	/**
	 *	CFBamSchemaRefByUNameIdxKey entity reader convenience method for object-based access.
	 *
	 *		@param key The CFBamSchemaRefByUNameIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity, typically from the JPA cache, or null if no such entity exists.
	 */
	default CFBamJpaSchemaRef findByUNameIdx(ICFBamSchemaRefByUNameIdxKey key) {
		return( findByUNameIdx(key.getRequiredSchemaId(), key.getRequiredName()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamSchemaRefByRefSchemaIdxKey as arguments.
	 *
	 *		@param optionalRefSchemaId
	 *
	 *		@return List&lt;CFBamJpaSchemaRef&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaSchemaRef r where r.optionalLookupRefSchema.requiredId = :refSchemaId")
	List<CFBamJpaSchemaRef> findByRefSchemaIdx(@Param("refSchemaId") CFLibDbKeyHash256 optionalRefSchemaId);

	/**
	 *	CFBamSchemaRefByRefSchemaIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamSchemaRefByRefSchemaIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaSchemaRef> findByRefSchemaIdx(ICFBamSchemaRefByRefSchemaIdxKey key) {
		return( findByRefSchemaIdx(key.getOptionalRefSchemaId()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamSchemaRefByPrevIdxKey as arguments.
	 *
	 *		@param optionalPrevId
	 *
	 *		@return List&lt;CFBamJpaSchemaRef&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaSchemaRef r where r.optionalLookupPrev.requiredId = :prevId")
	List<CFBamJpaSchemaRef> findByPrevIdx(@Param("prevId") CFLibDbKeyHash256 optionalPrevId);

	/**
	 *	CFBamSchemaRefByPrevIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamSchemaRefByPrevIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaSchemaRef> findByPrevIdx(ICFBamSchemaRefByPrevIdxKey key) {
		return( findByPrevIdx(key.getOptionalPrevId()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamSchemaRefByNextIdxKey as arguments.
	 *
	 *		@param optionalNextId
	 *
	 *		@return List&lt;CFBamJpaSchemaRef&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaSchemaRef r where r.optionalLookupNext.requiredId = :nextId")
	List<CFBamJpaSchemaRef> findByNextIdx(@Param("nextId") CFLibDbKeyHash256 optionalNextId);

	/**
	 *	CFBamSchemaRefByNextIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamSchemaRefByNextIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaSchemaRef> findByNextIdx(ICFBamSchemaRefByNextIdxKey key) {
		return( findByNextIdx(key.getOptionalNextId()));
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
	CFBamJpaSchemaRef lockByIdIdx(@Param("id") CFLibDbKeyHash256 requiredId);

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
	List<CFBamJpaSchemaRef> lockByTenantIdx(@Param("tenantId") CFLibDbKeyHash256 requiredTenantId);

	/**
	 *	CFBamScopeByTenantIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaSchemaRef> lockByTenantIdx(ICFBamScopeByTenantIdxKey key) {
		return( lockByTenantIdx(key.getRequiredTenantId()));
	}

	// CFBamJpaSchemaRef specified delete-by-index methods

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredSchemaId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaSchemaRef r where r.requiredContainerSchema.requiredId = :schemaId")
	List<CFBamJpaSchemaRef> lockBySchemaIdx(@Param("schemaId") CFLibDbKeyHash256 requiredSchemaId);

	/**
	 *	CFBamSchemaRefBySchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaSchemaRef> lockBySchemaIdx(ICFBamSchemaRefBySchemaIdxKey key) {
		return( lockBySchemaIdx(key.getRequiredSchemaId()));
	}

	/**
	 *	Argument-based lock database entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredSchemaId
	 *		@param requiredName
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaSchemaRef r where r.requiredContainerSchema.requiredId = :schemaId and r.requiredName = :name")
	CFBamJpaSchemaRef lockByUNameIdx(@Param("schemaId") CFLibDbKeyHash256 requiredSchemaId,
		@Param("name") String requiredName);

	/**
	 *	CFBamSchemaRefByUNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	default CFBamJpaSchemaRef lockByUNameIdx(ICFBamSchemaRefByUNameIdxKey key) {
		return( lockByUNameIdx(key.getRequiredSchemaId(), key.getRequiredName()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalRefSchemaId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaSchemaRef r where r.optionalLookupRefSchema.requiredId = :refSchemaId")
	List<CFBamJpaSchemaRef> lockByRefSchemaIdx(@Param("refSchemaId") CFLibDbKeyHash256 optionalRefSchemaId);

	/**
	 *	CFBamSchemaRefByRefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaSchemaRef> lockByRefSchemaIdx(ICFBamSchemaRefByRefSchemaIdxKey key) {
		return( lockByRefSchemaIdx(key.getOptionalRefSchemaId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalPrevId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaSchemaRef r where r.optionalLookupPrev.requiredId = :prevId")
	List<CFBamJpaSchemaRef> lockByPrevIdx(@Param("prevId") CFLibDbKeyHash256 optionalPrevId);

	/**
	 *	CFBamSchemaRefByPrevIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaSchemaRef> lockByPrevIdx(ICFBamSchemaRefByPrevIdxKey key) {
		return( lockByPrevIdx(key.getOptionalPrevId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalNextId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaSchemaRef r where r.optionalLookupNext.requiredId = :nextId")
	List<CFBamJpaSchemaRef> lockByNextIdx(@Param("nextId") CFLibDbKeyHash256 optionalNextId);

	/**
	 *	CFBamSchemaRefByNextIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaSchemaRef> lockByNextIdx(ICFBamSchemaRefByNextIdxKey key) {
		return( lockByNextIdx(key.getOptionalNextId()));
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

	// CFBamJpaSchemaRef specified delete-by-index methods

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredSchemaId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaSchemaRef r where r.requiredContainerSchema.requiredId = :schemaId")
	void deleteBySchemaIdx(@Param("schemaId") CFLibDbKeyHash256 requiredSchemaId);

	/**
	 *	CFBamSchemaRefBySchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamSchemaRefBySchemaIdxKey of the entity to be locked.
	 */
	default void deleteBySchemaIdx(ICFBamSchemaRefBySchemaIdxKey key) {
		deleteBySchemaIdx(key.getRequiredSchemaId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredSchemaId
	 *		@param requiredName
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaSchemaRef r where r.requiredContainerSchema.requiredId = :schemaId and r.requiredName = :name")
	void deleteByUNameIdx(@Param("schemaId") CFLibDbKeyHash256 requiredSchemaId,
		@Param("name") String requiredName);

	/**
	 *	CFBamSchemaRefByUNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamSchemaRefByUNameIdxKey of the entity to be locked.
	 */
	default void deleteByUNameIdx(ICFBamSchemaRefByUNameIdxKey key) {
		deleteByUNameIdx(key.getRequiredSchemaId(), key.getRequiredName());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalRefSchemaId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaSchemaRef r where r.optionalLookupRefSchema.requiredId = :refSchemaId")
	void deleteByRefSchemaIdx(@Param("refSchemaId") CFLibDbKeyHash256 optionalRefSchemaId);

	/**
	 *	CFBamSchemaRefByRefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamSchemaRefByRefSchemaIdxKey of the entity to be locked.
	 */
	default void deleteByRefSchemaIdx(ICFBamSchemaRefByRefSchemaIdxKey key) {
		deleteByRefSchemaIdx(key.getOptionalRefSchemaId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalPrevId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaSchemaRef r where r.optionalLookupPrev.requiredId = :prevId")
	void deleteByPrevIdx(@Param("prevId") CFLibDbKeyHash256 optionalPrevId);

	/**
	 *	CFBamSchemaRefByPrevIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamSchemaRefByPrevIdxKey of the entity to be locked.
	 */
	default void deleteByPrevIdx(ICFBamSchemaRefByPrevIdxKey key) {
		deleteByPrevIdx(key.getOptionalPrevId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalNextId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaSchemaRef r where r.optionalLookupNext.requiredId = :nextId")
	void deleteByNextIdx(@Param("nextId") CFLibDbKeyHash256 optionalNextId);

	/**
	 *	CFBamSchemaRefByNextIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamSchemaRefByNextIdxKey of the entity to be locked.
	 */
	default void deleteByNextIdx(ICFBamSchemaRefByNextIdxKey key) {
		deleteByNextIdx(key.getOptionalNextId());
	}

}
