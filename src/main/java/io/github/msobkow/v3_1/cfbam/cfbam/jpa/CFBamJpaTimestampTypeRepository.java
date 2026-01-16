// Description: Java 25 Spring JPA Repository for TimestampType

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
 *	JpaRepository for the CFBamJpaTimestampType entities defined in io.github.msobkow.v3_1.cfbam.cfbam.jpa.
 *	The manufactured repositories try to provide a rich, do-it-all interface to the JPA data store, with both object and argument-based implementations of the interface defined.
 */
@Transactional(readOnly = true)
public interface CFBamJpaTimestampTypeRepository extends JpaRepository<CFBamJpaTimestampType, CFLibDbKeyHash256> {

	/**
	 *	Argument-based get database instance for compatibility with the current MSS code factory code base.
	 *
	 *		@param requiredId
	 *
	 *		@return The retrieved entity, usually from the JPA cache, or null if no such entity exists.
	 */
	@Query("select r from CFBamJpaValue r where r.requiredId = :id")
	CFBamJpaTimestampType get(@Param("id") CFLibDbKeyHash256 requiredId);

	// CFBamJpaValue specified index readers

	/**
	 *	Read an entity using the columns of the CFBamValueByUNameIdxKey as arguments.
	 *
	 *		@param requiredScopeId
	 *		@param requiredName
	 *
	 *		@return The found entity, typically from the JPA cache, or null if no such entity exists.
	 */
	@Query("select r from CFBamJpaValue r where r.requiredScopeId = :scopeId and r.requiredName = :name")
	CFBamJpaTimestampType findByUNameIdx(@Param("scopeId") CFLibDbKeyHash256 requiredScopeId,
		@Param("name") String requiredName);

	/**
	 *	CFBamValueByUNameIdxKey entity reader convenience method for object-based access.
	 *
	 *		@param key The CFBamValueByUNameIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity, typically from the JPA cache, or null if no such entity exists.
	 */
	default CFBamJpaTimestampType findByUNameIdx(ICFBamValueByUNameIdxKey key) {
		return( findByUNameIdx(key.getRequiredScopeId(), key.getRequiredName()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamValueByScopeIdxKey as arguments.
	 *
	 *		@param requiredScopeId
	 *
	 *		@return List&lt;CFBamJpaTimestampType&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaValue r where r.requiredScopeId = :scopeId")
	List<CFBamJpaTimestampType> findByScopeIdx(@Param("scopeId") CFLibDbKeyHash256 requiredScopeId);

	/**
	 *	CFBamValueByScopeIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamValueByScopeIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaTimestampType> findByScopeIdx(ICFBamValueByScopeIdxKey key) {
		return( findByScopeIdx(key.getRequiredScopeId()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamValueByDefSchemaIdxKey as arguments.
	 *
	 *		@param optionalDefSchemaId
	 *
	 *		@return List&lt;CFBamJpaTimestampType&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaValue r where r.optionalLookupDefSchema.requiredId = :defSchemaId")
	List<CFBamJpaTimestampType> findByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId);

	/**
	 *	CFBamValueByDefSchemaIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamValueByDefSchemaIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaTimestampType> findByDefSchemaIdx(ICFBamValueByDefSchemaIdxKey key) {
		return( findByDefSchemaIdx(key.getOptionalDefSchemaId()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamValueByPrevIdxKey as arguments.
	 *
	 *		@param optionalPrevId
	 *
	 *		@return List&lt;CFBamJpaTimestampType&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaValue r where r.optionalLookupPrev.requiredId = :prevId")
	List<CFBamJpaTimestampType> findByPrevIdx(@Param("prevId") CFLibDbKeyHash256 optionalPrevId);

	/**
	 *	CFBamValueByPrevIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamValueByPrevIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaTimestampType> findByPrevIdx(ICFBamValueByPrevIdxKey key) {
		return( findByPrevIdx(key.getOptionalPrevId()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamValueByNextIdxKey as arguments.
	 *
	 *		@param optionalNextId
	 *
	 *		@return List&lt;CFBamJpaTimestampType&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaValue r where r.optionalLookupNext.requiredId = :nextId")
	List<CFBamJpaTimestampType> findByNextIdx(@Param("nextId") CFLibDbKeyHash256 optionalNextId);

	/**
	 *	CFBamValueByNextIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamValueByNextIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaTimestampType> findByNextIdx(ICFBamValueByNextIdxKey key) {
		return( findByNextIdx(key.getOptionalNextId()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamValueByContPrevIdxKey as arguments.
	 *
	 *		@param requiredScopeId
	 *		@param optionalPrevId
	 *
	 *		@return List&lt;CFBamJpaTimestampType&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaValue r where r.requiredScopeId = :scopeId and r.optionalLookupPrev.requiredId = :prevId")
	List<CFBamJpaTimestampType> findByContPrevIdx(@Param("scopeId") CFLibDbKeyHash256 requiredScopeId,
		@Param("prevId") CFLibDbKeyHash256 optionalPrevId);

	/**
	 *	CFBamValueByContPrevIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamValueByContPrevIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaTimestampType> findByContPrevIdx(ICFBamValueByContPrevIdxKey key) {
		return( findByContPrevIdx(key.getRequiredScopeId(), key.getOptionalPrevId()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamValueByContNextIdxKey as arguments.
	 *
	 *		@param requiredScopeId
	 *		@param optionalNextId
	 *
	 *		@return List&lt;CFBamJpaTimestampType&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaValue r where r.requiredScopeId = :scopeId and r.optionalLookupNext.requiredId = :nextId")
	List<CFBamJpaTimestampType> findByContNextIdx(@Param("scopeId") CFLibDbKeyHash256 requiredScopeId,
		@Param("nextId") CFLibDbKeyHash256 optionalNextId);

	/**
	 *	CFBamValueByContNextIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamValueByContNextIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaTimestampType> findByContNextIdx(ICFBamValueByContNextIdxKey key) {
		return( findByContNextIdx(key.getRequiredScopeId(), key.getOptionalNextId()));
	}

	// CFBamJpaAtom specified index readers

	// CFBamJpaTimestampDef specified index readers

	// CFBamJpaTimestampType specified index readers

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamTimestampTypeBySchemaIdxKey as arguments.
	 *
	 *		@param requiredSchemaDefId
	 *
	 *		@return List&lt;CFBamJpaTimestampType&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaTimestampType r where r.requiredSchemaDefId = :schemaDefId")
	List<CFBamJpaTimestampType> findBySchemaIdx(@Param("schemaDefId") CFLibDbKeyHash256 requiredSchemaDefId);

	/**
	 *	CFBamTimestampTypeBySchemaIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamTimestampTypeBySchemaIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaTimestampType> findBySchemaIdx(ICFBamTimestampTypeBySchemaIdxKey key) {
		return( findBySchemaIdx(key.getRequiredSchemaDefId()));
	}

	// CFBamJpaValue specified delete-by-index methods

	/**
	 *	Argument-based lock database entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredId
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaValue r where r.requiredId = :id")
	CFBamJpaTimestampType lockByIdIdx(@Param("id") CFLibDbKeyHash256 requiredId);

	/**
	 *	Argument-based lock database entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredScopeId
	 *		@param requiredName
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaValue r where r.requiredScopeId = :scopeId and r.requiredName = :name")
	CFBamJpaTimestampType lockByUNameIdx(@Param("scopeId") CFLibDbKeyHash256 requiredScopeId,
		@Param("name") String requiredName);

	/**
	 *	CFBamValueByUNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	default CFBamJpaTimestampType lockByUNameIdx(ICFBamValueByUNameIdxKey key) {
		return( lockByUNameIdx(key.getRequiredScopeId(), key.getRequiredName()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredScopeId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaValue r where r.requiredScopeId = :scopeId")
	List<CFBamJpaTimestampType> lockByScopeIdx(@Param("scopeId") CFLibDbKeyHash256 requiredScopeId);

	/**
	 *	CFBamValueByScopeIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaTimestampType> lockByScopeIdx(ICFBamValueByScopeIdxKey key) {
		return( lockByScopeIdx(key.getRequiredScopeId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalDefSchemaId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaValue r where r.optionalLookupDefSchema.requiredId = :defSchemaId")
	List<CFBamJpaTimestampType> lockByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId);

	/**
	 *	CFBamValueByDefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaTimestampType> lockByDefSchemaIdx(ICFBamValueByDefSchemaIdxKey key) {
		return( lockByDefSchemaIdx(key.getOptionalDefSchemaId()));
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
	@Query("select r from CFBamJpaValue r where r.optionalLookupPrev.requiredId = :prevId")
	List<CFBamJpaTimestampType> lockByPrevIdx(@Param("prevId") CFLibDbKeyHash256 optionalPrevId);

	/**
	 *	CFBamValueByPrevIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaTimestampType> lockByPrevIdx(ICFBamValueByPrevIdxKey key) {
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
	@Query("select r from CFBamJpaValue r where r.optionalLookupNext.requiredId = :nextId")
	List<CFBamJpaTimestampType> lockByNextIdx(@Param("nextId") CFLibDbKeyHash256 optionalNextId);

	/**
	 *	CFBamValueByNextIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaTimestampType> lockByNextIdx(ICFBamValueByNextIdxKey key) {
		return( lockByNextIdx(key.getOptionalNextId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredScopeId
	 *		@param optionalPrevId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaValue r where r.requiredScopeId = :scopeId and r.optionalLookupPrev.requiredId = :prevId")
	List<CFBamJpaTimestampType> lockByContPrevIdx(@Param("scopeId") CFLibDbKeyHash256 requiredScopeId,
		@Param("prevId") CFLibDbKeyHash256 optionalPrevId);

	/**
	 *	CFBamValueByContPrevIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaTimestampType> lockByContPrevIdx(ICFBamValueByContPrevIdxKey key) {
		return( lockByContPrevIdx(key.getRequiredScopeId(), key.getOptionalPrevId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredScopeId
	 *		@param optionalNextId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaValue r where r.requiredScopeId = :scopeId and r.optionalLookupNext.requiredId = :nextId")
	List<CFBamJpaTimestampType> lockByContNextIdx(@Param("scopeId") CFLibDbKeyHash256 requiredScopeId,
		@Param("nextId") CFLibDbKeyHash256 optionalNextId);

	/**
	 *	CFBamValueByContNextIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaTimestampType> lockByContNextIdx(ICFBamValueByContNextIdxKey key) {
		return( lockByContNextIdx(key.getRequiredScopeId(), key.getOptionalNextId()));
	}

	// CFBamJpaAtom specified delete-by-index methods

	// CFBamJpaTimestampDef specified delete-by-index methods

	// CFBamJpaTimestampType specified delete-by-index methods

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredSchemaDefId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaTimestampType r where r.requiredSchemaDefId = :schemaDefId")
	List<CFBamJpaTimestampType> lockBySchemaIdx(@Param("schemaDefId") CFLibDbKeyHash256 requiredSchemaDefId);

	/**
	 *	CFBamTimestampTypeBySchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaTimestampType> lockBySchemaIdx(ICFBamTimestampTypeBySchemaIdxKey key) {
		return( lockBySchemaIdx(key.getRequiredSchemaDefId()));
	}

	// CFBamJpaValue specified delete-by-index methods

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaValue r where r.requiredId = :id")
	void deleteByIdIdx(@Param("id") CFLibDbKeyHash256 requiredId);

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredScopeId
	 *		@param requiredName
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaValue r where r.requiredScopeId = :scopeId and r.requiredName = :name")
	void deleteByUNameIdx(@Param("scopeId") CFLibDbKeyHash256 requiredScopeId,
		@Param("name") String requiredName);

	/**
	 *	CFBamValueByUNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamValueByUNameIdxKey of the entity to be locked.
	 */
	default void deleteByUNameIdx(ICFBamValueByUNameIdxKey key) {
		deleteByUNameIdx(key.getRequiredScopeId(), key.getRequiredName());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredScopeId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaValue r where r.requiredScopeId = :scopeId")
	void deleteByScopeIdx(@Param("scopeId") CFLibDbKeyHash256 requiredScopeId);

	/**
	 *	CFBamValueByScopeIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamValueByScopeIdxKey of the entity to be locked.
	 */
	default void deleteByScopeIdx(ICFBamValueByScopeIdxKey key) {
		deleteByScopeIdx(key.getRequiredScopeId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalDefSchemaId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaValue r where r.optionalLookupDefSchema.requiredId = :defSchemaId")
	void deleteByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId);

	/**
	 *	CFBamValueByDefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamValueByDefSchemaIdxKey of the entity to be locked.
	 */
	default void deleteByDefSchemaIdx(ICFBamValueByDefSchemaIdxKey key) {
		deleteByDefSchemaIdx(key.getOptionalDefSchemaId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalPrevId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaValue r where r.optionalLookupPrev.requiredId = :prevId")
	void deleteByPrevIdx(@Param("prevId") CFLibDbKeyHash256 optionalPrevId);

	/**
	 *	CFBamValueByPrevIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamValueByPrevIdxKey of the entity to be locked.
	 */
	default void deleteByPrevIdx(ICFBamValueByPrevIdxKey key) {
		deleteByPrevIdx(key.getOptionalPrevId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalNextId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaValue r where r.optionalLookupNext.requiredId = :nextId")
	void deleteByNextIdx(@Param("nextId") CFLibDbKeyHash256 optionalNextId);

	/**
	 *	CFBamValueByNextIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamValueByNextIdxKey of the entity to be locked.
	 */
	default void deleteByNextIdx(ICFBamValueByNextIdxKey key) {
		deleteByNextIdx(key.getOptionalNextId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredScopeId
	 *		@param optionalPrevId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaValue r where r.requiredScopeId = :scopeId and r.optionalLookupPrev.requiredId = :prevId")
	void deleteByContPrevIdx(@Param("scopeId") CFLibDbKeyHash256 requiredScopeId,
		@Param("prevId") CFLibDbKeyHash256 optionalPrevId);

	/**
	 *	CFBamValueByContPrevIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamValueByContPrevIdxKey of the entity to be locked.
	 */
	default void deleteByContPrevIdx(ICFBamValueByContPrevIdxKey key) {
		deleteByContPrevIdx(key.getRequiredScopeId(), key.getOptionalPrevId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredScopeId
	 *		@param optionalNextId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaValue r where r.requiredScopeId = :scopeId and r.optionalLookupNext.requiredId = :nextId")
	void deleteByContNextIdx(@Param("scopeId") CFLibDbKeyHash256 requiredScopeId,
		@Param("nextId") CFLibDbKeyHash256 optionalNextId);

	/**
	 *	CFBamValueByContNextIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamValueByContNextIdxKey of the entity to be locked.
	 */
	default void deleteByContNextIdx(ICFBamValueByContNextIdxKey key) {
		deleteByContNextIdx(key.getRequiredScopeId(), key.getOptionalNextId());
	}

	// CFBamJpaAtom specified delete-by-index methods

	// CFBamJpaTimestampDef specified delete-by-index methods

	// CFBamJpaTimestampType specified delete-by-index methods

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredSchemaDefId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaTimestampType r where r.requiredSchemaDefId = :schemaDefId")
	void deleteBySchemaIdx(@Param("schemaDefId") CFLibDbKeyHash256 requiredSchemaDefId);

	/**
	 *	CFBamTimestampTypeBySchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamTimestampTypeBySchemaIdxKey of the entity to be locked.
	 */
	default void deleteBySchemaIdx(ICFBamTimestampTypeBySchemaIdxKey key) {
		deleteBySchemaIdx(key.getRequiredSchemaDefId());
	}

}
