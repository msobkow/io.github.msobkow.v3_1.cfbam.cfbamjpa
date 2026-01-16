// Description: Java 25 Spring JPA Repository for RelationCol

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
 *	JpaRepository for the CFBamJpaRelationCol entities defined in io.github.msobkow.v3_1.cfbam.cfbam.jpa.
 *	The manufactured repositories try to provide a rich, do-it-all interface to the JPA data store, with both object and argument-based implementations of the interface defined.
 */
@Transactional(readOnly = true)
public interface CFBamJpaRelationColRepository extends JpaRepository<CFBamJpaRelationCol, CFLibDbKeyHash256> {

	/**
	 *	Argument-based get database instance for compatibility with the current MSS code factory code base.
	 *
	 *		@param requiredId
	 *
	 *		@return The retrieved entity, usually from the JPA cache, or null if no such entity exists.
	 */
	@Query("select r from CFBamJpaRelationCol r where r.requiredId = :id")
	CFBamJpaRelationCol get(@Param("id") CFLibDbKeyHash256 requiredId);

	// CFBamJpaRelationCol specified index readers

	/**
	 *	Read an entity using the columns of the CFBamRelationColByUNameIdxKey as arguments.
	 *
	 *		@param requiredRelationId
	 *		@param requiredName
	 *
	 *		@return The found entity, typically from the JPA cache, or null if no such entity exists.
	 */
	@Query("select r from CFBamJpaRelationCol r where r.requiredContainerRelation.requiredId = :relationId and r.requiredName = :name")
	CFBamJpaRelationCol findByUNameIdx(@Param("relationId") CFLibDbKeyHash256 requiredRelationId,
		@Param("name") String requiredName);

	/**
	 *	CFBamRelationColByUNameIdxKey entity reader convenience method for object-based access.
	 *
	 *		@param key The CFBamRelationColByUNameIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity, typically from the JPA cache, or null if no such entity exists.
	 */
	default CFBamJpaRelationCol findByUNameIdx(ICFBamRelationColByUNameIdxKey key) {
		return( findByUNameIdx(key.getRequiredRelationId(), key.getRequiredName()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamRelationColByRelationIdxKey as arguments.
	 *
	 *		@param requiredRelationId
	 *
	 *		@return List&lt;CFBamJpaRelationCol&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaRelationCol r where r.requiredContainerRelation.requiredId = :relationId")
	List<CFBamJpaRelationCol> findByRelationIdx(@Param("relationId") CFLibDbKeyHash256 requiredRelationId);

	/**
	 *	CFBamRelationColByRelationIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamRelationColByRelationIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaRelationCol> findByRelationIdx(ICFBamRelationColByRelationIdxKey key) {
		return( findByRelationIdx(key.getRequiredRelationId()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamRelationColByDefSchemaIdxKey as arguments.
	 *
	 *		@param optionalDefSchemaId
	 *
	 *		@return List&lt;CFBamJpaRelationCol&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaRelationCol r where r.optionalLookupDefSchema.requiredId = :defSchemaId")
	List<CFBamJpaRelationCol> findByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId);

	/**
	 *	CFBamRelationColByDefSchemaIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamRelationColByDefSchemaIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaRelationCol> findByDefSchemaIdx(ICFBamRelationColByDefSchemaIdxKey key) {
		return( findByDefSchemaIdx(key.getOptionalDefSchemaId()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamRelationColByFromColIdxKey as arguments.
	 *
	 *		@param requiredFromColId
	 *
	 *		@return List&lt;CFBamJpaRelationCol&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaRelationCol r where r.requiredLookupFromCol.requiredId = :fromColId")
	List<CFBamJpaRelationCol> findByFromColIdx(@Param("fromColId") CFLibDbKeyHash256 requiredFromColId);

	/**
	 *	CFBamRelationColByFromColIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamRelationColByFromColIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaRelationCol> findByFromColIdx(ICFBamRelationColByFromColIdxKey key) {
		return( findByFromColIdx(key.getRequiredFromColId()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamRelationColByToColIdxKey as arguments.
	 *
	 *		@param requiredToColId
	 *
	 *		@return List&lt;CFBamJpaRelationCol&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaRelationCol r where r.requiredLookupToCol.requiredId = :toColId")
	List<CFBamJpaRelationCol> findByToColIdx(@Param("toColId") CFLibDbKeyHash256 requiredToColId);

	/**
	 *	CFBamRelationColByToColIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamRelationColByToColIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaRelationCol> findByToColIdx(ICFBamRelationColByToColIdxKey key) {
		return( findByToColIdx(key.getRequiredToColId()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamRelationColByPrevIdxKey as arguments.
	 *
	 *		@param optionalPrevId
	 *
	 *		@return List&lt;CFBamJpaRelationCol&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaRelationCol r where r.optionalLookupPrev.requiredId = :prevId")
	List<CFBamJpaRelationCol> findByPrevIdx(@Param("prevId") CFLibDbKeyHash256 optionalPrevId);

	/**
	 *	CFBamRelationColByPrevIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamRelationColByPrevIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaRelationCol> findByPrevIdx(ICFBamRelationColByPrevIdxKey key) {
		return( findByPrevIdx(key.getOptionalPrevId()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamRelationColByNextIdxKey as arguments.
	 *
	 *		@param optionalNextId
	 *
	 *		@return List&lt;CFBamJpaRelationCol&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaRelationCol r where r.optionalLookupNext.requiredId = :nextId")
	List<CFBamJpaRelationCol> findByNextIdx(@Param("nextId") CFLibDbKeyHash256 optionalNextId);

	/**
	 *	CFBamRelationColByNextIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamRelationColByNextIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaRelationCol> findByNextIdx(ICFBamRelationColByNextIdxKey key) {
		return( findByNextIdx(key.getOptionalNextId()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamRelationColByRelPrevIdxKey as arguments.
	 *
	 *		@param requiredRelationId
	 *		@param optionalPrevId
	 *
	 *		@return List&lt;CFBamJpaRelationCol&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaRelationCol r where r.requiredContainerRelation.requiredId = :relationId and r.optionalLookupPrev.requiredId = :prevId")
	List<CFBamJpaRelationCol> findByRelPrevIdx(@Param("relationId") CFLibDbKeyHash256 requiredRelationId,
		@Param("prevId") CFLibDbKeyHash256 optionalPrevId);

	/**
	 *	CFBamRelationColByRelPrevIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamRelationColByRelPrevIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaRelationCol> findByRelPrevIdx(ICFBamRelationColByRelPrevIdxKey key) {
		return( findByRelPrevIdx(key.getRequiredRelationId(), key.getOptionalPrevId()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamRelationColByRelNextIdxKey as arguments.
	 *
	 *		@param requiredRelationId
	 *		@param optionalNextId
	 *
	 *		@return List&lt;CFBamJpaRelationCol&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaRelationCol r where r.requiredContainerRelation.requiredId = :relationId and r.optionalLookupNext.requiredId = :nextId")
	List<CFBamJpaRelationCol> findByRelNextIdx(@Param("relationId") CFLibDbKeyHash256 requiredRelationId,
		@Param("nextId") CFLibDbKeyHash256 optionalNextId);

	/**
	 *	CFBamRelationColByRelNextIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamRelationColByRelNextIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaRelationCol> findByRelNextIdx(ICFBamRelationColByRelNextIdxKey key) {
		return( findByRelNextIdx(key.getRequiredRelationId(), key.getOptionalNextId()));
	}

	// CFBamJpaRelationCol specified delete-by-index methods

	/**
	 *	Argument-based lock database entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredId
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaRelationCol r where r.requiredId = :id")
	CFBamJpaRelationCol lockByIdIdx(@Param("id") CFLibDbKeyHash256 requiredId);

	/**
	 *	Argument-based lock database entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredRelationId
	 *		@param requiredName
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaRelationCol r where r.requiredContainerRelation.requiredId = :relationId and r.requiredName = :name")
	CFBamJpaRelationCol lockByUNameIdx(@Param("relationId") CFLibDbKeyHash256 requiredRelationId,
		@Param("name") String requiredName);

	/**
	 *	CFBamRelationColByUNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	default CFBamJpaRelationCol lockByUNameIdx(ICFBamRelationColByUNameIdxKey key) {
		return( lockByUNameIdx(key.getRequiredRelationId(), key.getRequiredName()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredRelationId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaRelationCol r where r.requiredContainerRelation.requiredId = :relationId")
	List<CFBamJpaRelationCol> lockByRelationIdx(@Param("relationId") CFLibDbKeyHash256 requiredRelationId);

	/**
	 *	CFBamRelationColByRelationIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaRelationCol> lockByRelationIdx(ICFBamRelationColByRelationIdxKey key) {
		return( lockByRelationIdx(key.getRequiredRelationId()));
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
	@Query("select r from CFBamJpaRelationCol r where r.optionalLookupDefSchema.requiredId = :defSchemaId")
	List<CFBamJpaRelationCol> lockByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId);

	/**
	 *	CFBamRelationColByDefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaRelationCol> lockByDefSchemaIdx(ICFBamRelationColByDefSchemaIdxKey key) {
		return( lockByDefSchemaIdx(key.getOptionalDefSchemaId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredFromColId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaRelationCol r where r.requiredLookupFromCol.requiredId = :fromColId")
	List<CFBamJpaRelationCol> lockByFromColIdx(@Param("fromColId") CFLibDbKeyHash256 requiredFromColId);

	/**
	 *	CFBamRelationColByFromColIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaRelationCol> lockByFromColIdx(ICFBamRelationColByFromColIdxKey key) {
		return( lockByFromColIdx(key.getRequiredFromColId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredToColId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaRelationCol r where r.requiredLookupToCol.requiredId = :toColId")
	List<CFBamJpaRelationCol> lockByToColIdx(@Param("toColId") CFLibDbKeyHash256 requiredToColId);

	/**
	 *	CFBamRelationColByToColIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaRelationCol> lockByToColIdx(ICFBamRelationColByToColIdxKey key) {
		return( lockByToColIdx(key.getRequiredToColId()));
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
	@Query("select r from CFBamJpaRelationCol r where r.optionalLookupPrev.requiredId = :prevId")
	List<CFBamJpaRelationCol> lockByPrevIdx(@Param("prevId") CFLibDbKeyHash256 optionalPrevId);

	/**
	 *	CFBamRelationColByPrevIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaRelationCol> lockByPrevIdx(ICFBamRelationColByPrevIdxKey key) {
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
	@Query("select r from CFBamJpaRelationCol r where r.optionalLookupNext.requiredId = :nextId")
	List<CFBamJpaRelationCol> lockByNextIdx(@Param("nextId") CFLibDbKeyHash256 optionalNextId);

	/**
	 *	CFBamRelationColByNextIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaRelationCol> lockByNextIdx(ICFBamRelationColByNextIdxKey key) {
		return( lockByNextIdx(key.getOptionalNextId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredRelationId
	 *		@param optionalPrevId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaRelationCol r where r.requiredContainerRelation.requiredId = :relationId and r.optionalLookupPrev.requiredId = :prevId")
	List<CFBamJpaRelationCol> lockByRelPrevIdx(@Param("relationId") CFLibDbKeyHash256 requiredRelationId,
		@Param("prevId") CFLibDbKeyHash256 optionalPrevId);

	/**
	 *	CFBamRelationColByRelPrevIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaRelationCol> lockByRelPrevIdx(ICFBamRelationColByRelPrevIdxKey key) {
		return( lockByRelPrevIdx(key.getRequiredRelationId(), key.getOptionalPrevId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredRelationId
	 *		@param optionalNextId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaRelationCol r where r.requiredContainerRelation.requiredId = :relationId and r.optionalLookupNext.requiredId = :nextId")
	List<CFBamJpaRelationCol> lockByRelNextIdx(@Param("relationId") CFLibDbKeyHash256 requiredRelationId,
		@Param("nextId") CFLibDbKeyHash256 optionalNextId);

	/**
	 *	CFBamRelationColByRelNextIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaRelationCol> lockByRelNextIdx(ICFBamRelationColByRelNextIdxKey key) {
		return( lockByRelNextIdx(key.getRequiredRelationId(), key.getOptionalNextId()));
	}

	// CFBamJpaRelationCol specified delete-by-index methods

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaRelationCol r where r.requiredId = :id")
	void deleteByIdIdx(@Param("id") CFLibDbKeyHash256 requiredId);

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredRelationId
	 *		@param requiredName
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaRelationCol r where r.requiredContainerRelation.requiredId = :relationId and r.requiredName = :name")
	void deleteByUNameIdx(@Param("relationId") CFLibDbKeyHash256 requiredRelationId,
		@Param("name") String requiredName);

	/**
	 *	CFBamRelationColByUNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamRelationColByUNameIdxKey of the entity to be locked.
	 */
	default void deleteByUNameIdx(ICFBamRelationColByUNameIdxKey key) {
		deleteByUNameIdx(key.getRequiredRelationId(), key.getRequiredName());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredRelationId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaRelationCol r where r.requiredContainerRelation.requiredId = :relationId")
	void deleteByRelationIdx(@Param("relationId") CFLibDbKeyHash256 requiredRelationId);

	/**
	 *	CFBamRelationColByRelationIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamRelationColByRelationIdxKey of the entity to be locked.
	 */
	default void deleteByRelationIdx(ICFBamRelationColByRelationIdxKey key) {
		deleteByRelationIdx(key.getRequiredRelationId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalDefSchemaId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaRelationCol r where r.optionalLookupDefSchema.requiredId = :defSchemaId")
	void deleteByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId);

	/**
	 *	CFBamRelationColByDefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamRelationColByDefSchemaIdxKey of the entity to be locked.
	 */
	default void deleteByDefSchemaIdx(ICFBamRelationColByDefSchemaIdxKey key) {
		deleteByDefSchemaIdx(key.getOptionalDefSchemaId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredFromColId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaRelationCol r where r.requiredLookupFromCol.requiredId = :fromColId")
	void deleteByFromColIdx(@Param("fromColId") CFLibDbKeyHash256 requiredFromColId);

	/**
	 *	CFBamRelationColByFromColIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamRelationColByFromColIdxKey of the entity to be locked.
	 */
	default void deleteByFromColIdx(ICFBamRelationColByFromColIdxKey key) {
		deleteByFromColIdx(key.getRequiredFromColId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredToColId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaRelationCol r where r.requiredLookupToCol.requiredId = :toColId")
	void deleteByToColIdx(@Param("toColId") CFLibDbKeyHash256 requiredToColId);

	/**
	 *	CFBamRelationColByToColIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamRelationColByToColIdxKey of the entity to be locked.
	 */
	default void deleteByToColIdx(ICFBamRelationColByToColIdxKey key) {
		deleteByToColIdx(key.getRequiredToColId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalPrevId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaRelationCol r where r.optionalLookupPrev.requiredId = :prevId")
	void deleteByPrevIdx(@Param("prevId") CFLibDbKeyHash256 optionalPrevId);

	/**
	 *	CFBamRelationColByPrevIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamRelationColByPrevIdxKey of the entity to be locked.
	 */
	default void deleteByPrevIdx(ICFBamRelationColByPrevIdxKey key) {
		deleteByPrevIdx(key.getOptionalPrevId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalNextId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaRelationCol r where r.optionalLookupNext.requiredId = :nextId")
	void deleteByNextIdx(@Param("nextId") CFLibDbKeyHash256 optionalNextId);

	/**
	 *	CFBamRelationColByNextIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamRelationColByNextIdxKey of the entity to be locked.
	 */
	default void deleteByNextIdx(ICFBamRelationColByNextIdxKey key) {
		deleteByNextIdx(key.getOptionalNextId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredRelationId
	 *		@param optionalPrevId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaRelationCol r where r.requiredContainerRelation.requiredId = :relationId and r.optionalLookupPrev.requiredId = :prevId")
	void deleteByRelPrevIdx(@Param("relationId") CFLibDbKeyHash256 requiredRelationId,
		@Param("prevId") CFLibDbKeyHash256 optionalPrevId);

	/**
	 *	CFBamRelationColByRelPrevIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamRelationColByRelPrevIdxKey of the entity to be locked.
	 */
	default void deleteByRelPrevIdx(ICFBamRelationColByRelPrevIdxKey key) {
		deleteByRelPrevIdx(key.getRequiredRelationId(), key.getOptionalPrevId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredRelationId
	 *		@param optionalNextId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaRelationCol r where r.requiredContainerRelation.requiredId = :relationId and r.optionalLookupNext.requiredId = :nextId")
	void deleteByRelNextIdx(@Param("relationId") CFLibDbKeyHash256 requiredRelationId,
		@Param("nextId") CFLibDbKeyHash256 optionalNextId);

	/**
	 *	CFBamRelationColByRelNextIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamRelationColByRelNextIdxKey of the entity to be locked.
	 */
	default void deleteByRelNextIdx(ICFBamRelationColByRelNextIdxKey key) {
		deleteByRelNextIdx(key.getRequiredRelationId(), key.getOptionalNextId());
	}

}
