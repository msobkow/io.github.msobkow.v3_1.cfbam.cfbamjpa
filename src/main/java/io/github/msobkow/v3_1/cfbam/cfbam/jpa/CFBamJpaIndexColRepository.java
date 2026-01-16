// Description: Java 25 Spring JPA Repository for IndexCol

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
 *	JpaRepository for the CFBamJpaIndexCol entities defined in io.github.msobkow.v3_1.cfbam.cfbam.jpa.
 *	The manufactured repositories try to provide a rich, do-it-all interface to the JPA data store, with both object and argument-based implementations of the interface defined.
 */
@Transactional(readOnly = true)
public interface CFBamJpaIndexColRepository extends JpaRepository<CFBamJpaIndexCol, CFLibDbKeyHash256> {

	/**
	 *	Argument-based get database instance for compatibility with the current MSS code factory code base.
	 *
	 *		@param requiredId
	 *
	 *		@return The retrieved entity, usually from the JPA cache, or null if no such entity exists.
	 */
	@Query("select r from CFBamJpaIndexCol r where r.requiredId = :id")
	CFBamJpaIndexCol get(@Param("id") CFLibDbKeyHash256 requiredId);

	// CFBamJpaIndexCol specified index readers

	/**
	 *	Read an entity using the columns of the CFBamIndexColByUNameIdxKey as arguments.
	 *
	 *		@param requiredIndexId
	 *		@param requiredName
	 *
	 *		@return The found entity, typically from the JPA cache, or null if no such entity exists.
	 */
	@Query("select r from CFBamJpaIndexCol r where r.requiredContainerIndex.requiredId = :indexId and r.requiredName = :name")
	CFBamJpaIndexCol findByUNameIdx(@Param("indexId") CFLibDbKeyHash256 requiredIndexId,
		@Param("name") String requiredName);

	/**
	 *	CFBamIndexColByUNameIdxKey entity reader convenience method for object-based access.
	 *
	 *		@param key The CFBamIndexColByUNameIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity, typically from the JPA cache, or null if no such entity exists.
	 */
	default CFBamJpaIndexCol findByUNameIdx(ICFBamIndexColByUNameIdxKey key) {
		return( findByUNameIdx(key.getRequiredIndexId(), key.getRequiredName()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamIndexColByIndexIdxKey as arguments.
	 *
	 *		@param requiredIndexId
	 *
	 *		@return List&lt;CFBamJpaIndexCol&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaIndexCol r where r.requiredContainerIndex.requiredId = :indexId")
	List<CFBamJpaIndexCol> findByIndexIdx(@Param("indexId") CFLibDbKeyHash256 requiredIndexId);

	/**
	 *	CFBamIndexColByIndexIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamIndexColByIndexIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaIndexCol> findByIndexIdx(ICFBamIndexColByIndexIdxKey key) {
		return( findByIndexIdx(key.getRequiredIndexId()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamIndexColByDefSchemaIdxKey as arguments.
	 *
	 *		@param optionalDefSchemaId
	 *
	 *		@return List&lt;CFBamJpaIndexCol&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaIndexCol r where r.optionalLookupDefSchema.requiredId = :defSchemaId")
	List<CFBamJpaIndexCol> findByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId);

	/**
	 *	CFBamIndexColByDefSchemaIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamIndexColByDefSchemaIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaIndexCol> findByDefSchemaIdx(ICFBamIndexColByDefSchemaIdxKey key) {
		return( findByDefSchemaIdx(key.getOptionalDefSchemaId()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamIndexColByColIdxKey as arguments.
	 *
	 *		@param requiredColumnId
	 *
	 *		@return List&lt;CFBamJpaIndexCol&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaIndexCol r where r.requiredLookupColumn.requiredId = :columnId")
	List<CFBamJpaIndexCol> findByColIdx(@Param("columnId") CFLibDbKeyHash256 requiredColumnId);

	/**
	 *	CFBamIndexColByColIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamIndexColByColIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaIndexCol> findByColIdx(ICFBamIndexColByColIdxKey key) {
		return( findByColIdx(key.getRequiredColumnId()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamIndexColByPrevIdxKey as arguments.
	 *
	 *		@param optionalPrevId
	 *
	 *		@return List&lt;CFBamJpaIndexCol&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaIndexCol r where r.optionalLookupPrev.requiredId = :prevId")
	List<CFBamJpaIndexCol> findByPrevIdx(@Param("prevId") CFLibDbKeyHash256 optionalPrevId);

	/**
	 *	CFBamIndexColByPrevIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamIndexColByPrevIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaIndexCol> findByPrevIdx(ICFBamIndexColByPrevIdxKey key) {
		return( findByPrevIdx(key.getOptionalPrevId()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamIndexColByNextIdxKey as arguments.
	 *
	 *		@param optionalNextId
	 *
	 *		@return List&lt;CFBamJpaIndexCol&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaIndexCol r where r.optionalLookupNext.requiredId = :nextId")
	List<CFBamJpaIndexCol> findByNextIdx(@Param("nextId") CFLibDbKeyHash256 optionalNextId);

	/**
	 *	CFBamIndexColByNextIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamIndexColByNextIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaIndexCol> findByNextIdx(ICFBamIndexColByNextIdxKey key) {
		return( findByNextIdx(key.getOptionalNextId()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamIndexColByIdxPrevIdxKey as arguments.
	 *
	 *		@param requiredIndexId
	 *		@param optionalPrevId
	 *
	 *		@return List&lt;CFBamJpaIndexCol&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaIndexCol r where r.requiredContainerIndex.requiredId = :indexId and r.optionalLookupPrev.requiredId = :prevId")
	List<CFBamJpaIndexCol> findByIdxPrevIdx(@Param("indexId") CFLibDbKeyHash256 requiredIndexId,
		@Param("prevId") CFLibDbKeyHash256 optionalPrevId);

	/**
	 *	CFBamIndexColByIdxPrevIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamIndexColByIdxPrevIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaIndexCol> findByIdxPrevIdx(ICFBamIndexColByIdxPrevIdxKey key) {
		return( findByIdxPrevIdx(key.getRequiredIndexId(), key.getOptionalPrevId()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamIndexColByIdxNextIdxKey as arguments.
	 *
	 *		@param requiredIndexId
	 *		@param optionalNextId
	 *
	 *		@return List&lt;CFBamJpaIndexCol&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaIndexCol r where r.requiredContainerIndex.requiredId = :indexId and r.optionalLookupNext.requiredId = :nextId")
	List<CFBamJpaIndexCol> findByIdxNextIdx(@Param("indexId") CFLibDbKeyHash256 requiredIndexId,
		@Param("nextId") CFLibDbKeyHash256 optionalNextId);

	/**
	 *	CFBamIndexColByIdxNextIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamIndexColByIdxNextIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaIndexCol> findByIdxNextIdx(ICFBamIndexColByIdxNextIdxKey key) {
		return( findByIdxNextIdx(key.getRequiredIndexId(), key.getOptionalNextId()));
	}

	// CFBamJpaIndexCol specified delete-by-index methods

	/**
	 *	Argument-based lock database entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredId
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaIndexCol r where r.requiredId = :id")
	CFBamJpaIndexCol lockByIdIdx(@Param("id") CFLibDbKeyHash256 requiredId);

	/**
	 *	Argument-based lock database entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredIndexId
	 *		@param requiredName
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaIndexCol r where r.requiredContainerIndex.requiredId = :indexId and r.requiredName = :name")
	CFBamJpaIndexCol lockByUNameIdx(@Param("indexId") CFLibDbKeyHash256 requiredIndexId,
		@Param("name") String requiredName);

	/**
	 *	CFBamIndexColByUNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	default CFBamJpaIndexCol lockByUNameIdx(ICFBamIndexColByUNameIdxKey key) {
		return( lockByUNameIdx(key.getRequiredIndexId(), key.getRequiredName()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredIndexId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaIndexCol r where r.requiredContainerIndex.requiredId = :indexId")
	List<CFBamJpaIndexCol> lockByIndexIdx(@Param("indexId") CFLibDbKeyHash256 requiredIndexId);

	/**
	 *	CFBamIndexColByIndexIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaIndexCol> lockByIndexIdx(ICFBamIndexColByIndexIdxKey key) {
		return( lockByIndexIdx(key.getRequiredIndexId()));
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
	@Query("select r from CFBamJpaIndexCol r where r.optionalLookupDefSchema.requiredId = :defSchemaId")
	List<CFBamJpaIndexCol> lockByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId);

	/**
	 *	CFBamIndexColByDefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaIndexCol> lockByDefSchemaIdx(ICFBamIndexColByDefSchemaIdxKey key) {
		return( lockByDefSchemaIdx(key.getOptionalDefSchemaId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredColumnId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaIndexCol r where r.requiredLookupColumn.requiredId = :columnId")
	List<CFBamJpaIndexCol> lockByColIdx(@Param("columnId") CFLibDbKeyHash256 requiredColumnId);

	/**
	 *	CFBamIndexColByColIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaIndexCol> lockByColIdx(ICFBamIndexColByColIdxKey key) {
		return( lockByColIdx(key.getRequiredColumnId()));
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
	@Query("select r from CFBamJpaIndexCol r where r.optionalLookupPrev.requiredId = :prevId")
	List<CFBamJpaIndexCol> lockByPrevIdx(@Param("prevId") CFLibDbKeyHash256 optionalPrevId);

	/**
	 *	CFBamIndexColByPrevIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaIndexCol> lockByPrevIdx(ICFBamIndexColByPrevIdxKey key) {
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
	@Query("select r from CFBamJpaIndexCol r where r.optionalLookupNext.requiredId = :nextId")
	List<CFBamJpaIndexCol> lockByNextIdx(@Param("nextId") CFLibDbKeyHash256 optionalNextId);

	/**
	 *	CFBamIndexColByNextIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaIndexCol> lockByNextIdx(ICFBamIndexColByNextIdxKey key) {
		return( lockByNextIdx(key.getOptionalNextId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredIndexId
	 *		@param optionalPrevId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaIndexCol r where r.requiredContainerIndex.requiredId = :indexId and r.optionalLookupPrev.requiredId = :prevId")
	List<CFBamJpaIndexCol> lockByIdxPrevIdx(@Param("indexId") CFLibDbKeyHash256 requiredIndexId,
		@Param("prevId") CFLibDbKeyHash256 optionalPrevId);

	/**
	 *	CFBamIndexColByIdxPrevIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaIndexCol> lockByIdxPrevIdx(ICFBamIndexColByIdxPrevIdxKey key) {
		return( lockByIdxPrevIdx(key.getRequiredIndexId(), key.getOptionalPrevId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredIndexId
	 *		@param optionalNextId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaIndexCol r where r.requiredContainerIndex.requiredId = :indexId and r.optionalLookupNext.requiredId = :nextId")
	List<CFBamJpaIndexCol> lockByIdxNextIdx(@Param("indexId") CFLibDbKeyHash256 requiredIndexId,
		@Param("nextId") CFLibDbKeyHash256 optionalNextId);

	/**
	 *	CFBamIndexColByIdxNextIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaIndexCol> lockByIdxNextIdx(ICFBamIndexColByIdxNextIdxKey key) {
		return( lockByIdxNextIdx(key.getRequiredIndexId(), key.getOptionalNextId()));
	}

	// CFBamJpaIndexCol specified delete-by-index methods

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaIndexCol r where r.requiredId = :id")
	void deleteByIdIdx(@Param("id") CFLibDbKeyHash256 requiredId);

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredIndexId
	 *		@param requiredName
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaIndexCol r where r.requiredContainerIndex.requiredId = :indexId and r.requiredName = :name")
	void deleteByUNameIdx(@Param("indexId") CFLibDbKeyHash256 requiredIndexId,
		@Param("name") String requiredName);

	/**
	 *	CFBamIndexColByUNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamIndexColByUNameIdxKey of the entity to be locked.
	 */
	default void deleteByUNameIdx(ICFBamIndexColByUNameIdxKey key) {
		deleteByUNameIdx(key.getRequiredIndexId(), key.getRequiredName());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredIndexId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaIndexCol r where r.requiredContainerIndex.requiredId = :indexId")
	void deleteByIndexIdx(@Param("indexId") CFLibDbKeyHash256 requiredIndexId);

	/**
	 *	CFBamIndexColByIndexIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamIndexColByIndexIdxKey of the entity to be locked.
	 */
	default void deleteByIndexIdx(ICFBamIndexColByIndexIdxKey key) {
		deleteByIndexIdx(key.getRequiredIndexId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalDefSchemaId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaIndexCol r where r.optionalLookupDefSchema.requiredId = :defSchemaId")
	void deleteByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId);

	/**
	 *	CFBamIndexColByDefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamIndexColByDefSchemaIdxKey of the entity to be locked.
	 */
	default void deleteByDefSchemaIdx(ICFBamIndexColByDefSchemaIdxKey key) {
		deleteByDefSchemaIdx(key.getOptionalDefSchemaId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredColumnId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaIndexCol r where r.requiredLookupColumn.requiredId = :columnId")
	void deleteByColIdx(@Param("columnId") CFLibDbKeyHash256 requiredColumnId);

	/**
	 *	CFBamIndexColByColIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamIndexColByColIdxKey of the entity to be locked.
	 */
	default void deleteByColIdx(ICFBamIndexColByColIdxKey key) {
		deleteByColIdx(key.getRequiredColumnId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalPrevId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaIndexCol r where r.optionalLookupPrev.requiredId = :prevId")
	void deleteByPrevIdx(@Param("prevId") CFLibDbKeyHash256 optionalPrevId);

	/**
	 *	CFBamIndexColByPrevIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamIndexColByPrevIdxKey of the entity to be locked.
	 */
	default void deleteByPrevIdx(ICFBamIndexColByPrevIdxKey key) {
		deleteByPrevIdx(key.getOptionalPrevId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalNextId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaIndexCol r where r.optionalLookupNext.requiredId = :nextId")
	void deleteByNextIdx(@Param("nextId") CFLibDbKeyHash256 optionalNextId);

	/**
	 *	CFBamIndexColByNextIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamIndexColByNextIdxKey of the entity to be locked.
	 */
	default void deleteByNextIdx(ICFBamIndexColByNextIdxKey key) {
		deleteByNextIdx(key.getOptionalNextId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredIndexId
	 *		@param optionalPrevId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaIndexCol r where r.requiredContainerIndex.requiredId = :indexId and r.optionalLookupPrev.requiredId = :prevId")
	void deleteByIdxPrevIdx(@Param("indexId") CFLibDbKeyHash256 requiredIndexId,
		@Param("prevId") CFLibDbKeyHash256 optionalPrevId);

	/**
	 *	CFBamIndexColByIdxPrevIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamIndexColByIdxPrevIdxKey of the entity to be locked.
	 */
	default void deleteByIdxPrevIdx(ICFBamIndexColByIdxPrevIdxKey key) {
		deleteByIdxPrevIdx(key.getRequiredIndexId(), key.getOptionalPrevId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredIndexId
	 *		@param optionalNextId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaIndexCol r where r.requiredContainerIndex.requiredId = :indexId and r.optionalLookupNext.requiredId = :nextId")
	void deleteByIdxNextIdx(@Param("indexId") CFLibDbKeyHash256 requiredIndexId,
		@Param("nextId") CFLibDbKeyHash256 optionalNextId);

	/**
	 *	CFBamIndexColByIdxNextIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamIndexColByIdxNextIdxKey of the entity to be locked.
	 */
	default void deleteByIdxNextIdx(ICFBamIndexColByIdxNextIdxKey key) {
		deleteByIdxNextIdx(key.getRequiredIndexId(), key.getOptionalNextId());
	}

}
