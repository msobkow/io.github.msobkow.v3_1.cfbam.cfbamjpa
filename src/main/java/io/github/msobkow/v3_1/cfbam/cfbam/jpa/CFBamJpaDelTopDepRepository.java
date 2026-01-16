// Description: Java 25 Spring JPA Repository for DelTopDep

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
 *	JpaRepository for the CFBamJpaDelTopDep entities defined in io.github.msobkow.v3_1.cfbam.cfbam.jpa.
 *	The manufactured repositories try to provide a rich, do-it-all interface to the JPA data store, with both object and argument-based implementations of the interface defined.
 */
@Transactional(readOnly = true)
public interface CFBamJpaDelTopDepRepository extends JpaRepository<CFBamJpaDelTopDep, CFLibDbKeyHash256> {

	/**
	 *	Argument-based get database instance for compatibility with the current MSS code factory code base.
	 *
	 *		@param requiredId
	 *
	 *		@return The retrieved entity, usually from the JPA cache, or null if no such entity exists.
	 */
	@Query("select r from CFBamJpaScope r where r.requiredId = :id")
	CFBamJpaDelTopDep get(@Param("id") CFLibDbKeyHash256 requiredId);

	// CFBamJpaScope specified index readers

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamScopeByTenantIdxKey as arguments.
	 *
	 *		@param requiredTenantId
	 *
	 *		@return List&lt;CFBamJpaDelTopDep&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaScope r where r.requiredTenantId = :tenantId")
	List<CFBamJpaDelTopDep> findByTenantIdx(@Param("tenantId") CFLibDbKeyHash256 requiredTenantId);

	/**
	 *	CFBamScopeByTenantIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamScopeByTenantIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaDelTopDep> findByTenantIdx(ICFBamScopeByTenantIdxKey key) {
		return( findByTenantIdx(key.getRequiredTenantId()));
	}

	// CFBamJpaDelDep specified index readers

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamDelDepByDefSchemaIdxKey as arguments.
	 *
	 *		@param optionalDefSchemaId
	 *
	 *		@return List&lt;CFBamJpaDelTopDep&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaDelDep r where r.optionalLookupDefSchema.requiredId = :defSchemaId")
	List<CFBamJpaDelTopDep> findByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId);

	/**
	 *	CFBamDelDepByDefSchemaIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamDelDepByDefSchemaIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaDelTopDep> findByDefSchemaIdx(ICFBamDelDepByDefSchemaIdxKey key) {
		return( findByDefSchemaIdx(key.getOptionalDefSchemaId()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamDelDepByDelDepIdxKey as arguments.
	 *
	 *		@param requiredRelationId
	 *
	 *		@return List&lt;CFBamJpaDelTopDep&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaDelDep r where r.requiredLookupRelation.requiredId = :relationId")
	List<CFBamJpaDelTopDep> findByDelDepIdx(@Param("relationId") CFLibDbKeyHash256 requiredRelationId);

	/**
	 *	CFBamDelDepByDelDepIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamDelDepByDelDepIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaDelTopDep> findByDelDepIdx(ICFBamDelDepByDelDepIdxKey key) {
		return( findByDelDepIdx(key.getRequiredRelationId()));
	}

	// CFBamJpaDelTopDep specified index readers

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamDelTopDepByDelTopDepTblIdxKey as arguments.
	 *
	 *		@param requiredTableId
	 *
	 *		@return List&lt;CFBamJpaDelTopDep&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaDelTopDep r where r.requiredContainerTable.requiredId = :tableId")
	List<CFBamJpaDelTopDep> findByDelTopDepTblIdx(@Param("tableId") CFLibDbKeyHash256 requiredTableId);

	/**
	 *	CFBamDelTopDepByDelTopDepTblIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamDelTopDepByDelTopDepTblIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaDelTopDep> findByDelTopDepTblIdx(ICFBamDelTopDepByDelTopDepTblIdxKey key) {
		return( findByDelTopDepTblIdx(key.getRequiredTableId()));
	}

	/**
	 *	Read an entity using the columns of the CFBamDelTopDepByUNameIdxKey as arguments.
	 *
	 *		@param requiredTableId
	 *		@param requiredName
	 *
	 *		@return The found entity, typically from the JPA cache, or null if no such entity exists.
	 */
	@Query("select r from CFBamJpaDelTopDep r where r.requiredContainerTable.requiredId = :tableId and r.requiredName = :name")
	CFBamJpaDelTopDep findByUNameIdx(@Param("tableId") CFLibDbKeyHash256 requiredTableId,
		@Param("name") String requiredName);

	/**
	 *	CFBamDelTopDepByUNameIdxKey entity reader convenience method for object-based access.
	 *
	 *		@param key The CFBamDelTopDepByUNameIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity, typically from the JPA cache, or null if no such entity exists.
	 */
	default CFBamJpaDelTopDep findByUNameIdx(ICFBamDelTopDepByUNameIdxKey key) {
		return( findByUNameIdx(key.getRequiredTableId(), key.getRequiredName()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamDelTopDepByPrevIdxKey as arguments.
	 *
	 *		@param optionalPrevId
	 *
	 *		@return List&lt;CFBamJpaDelTopDep&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaDelTopDep r where r.optionalLookupPrev.requiredId = :prevId")
	List<CFBamJpaDelTopDep> findByPrevIdx(@Param("prevId") CFLibDbKeyHash256 optionalPrevId);

	/**
	 *	CFBamDelTopDepByPrevIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamDelTopDepByPrevIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaDelTopDep> findByPrevIdx(ICFBamDelTopDepByPrevIdxKey key) {
		return( findByPrevIdx(key.getOptionalPrevId()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamDelTopDepByNextIdxKey as arguments.
	 *
	 *		@param optionalNextId
	 *
	 *		@return List&lt;CFBamJpaDelTopDep&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaDelTopDep r where r.optionalLookupNext.requiredId = :nextId")
	List<CFBamJpaDelTopDep> findByNextIdx(@Param("nextId") CFLibDbKeyHash256 optionalNextId);

	/**
	 *	CFBamDelTopDepByNextIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamDelTopDepByNextIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaDelTopDep> findByNextIdx(ICFBamDelTopDepByNextIdxKey key) {
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
	CFBamJpaDelTopDep lockByIdIdx(@Param("id") CFLibDbKeyHash256 requiredId);

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
	List<CFBamJpaDelTopDep> lockByTenantIdx(@Param("tenantId") CFLibDbKeyHash256 requiredTenantId);

	/**
	 *	CFBamScopeByTenantIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaDelTopDep> lockByTenantIdx(ICFBamScopeByTenantIdxKey key) {
		return( lockByTenantIdx(key.getRequiredTenantId()));
	}

	// CFBamJpaDelDep specified delete-by-index methods

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalDefSchemaId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaDelDep r where r.optionalLookupDefSchema.requiredId = :defSchemaId")
	List<CFBamJpaDelTopDep> lockByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId);

	/**
	 *	CFBamDelDepByDefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaDelTopDep> lockByDefSchemaIdx(ICFBamDelDepByDefSchemaIdxKey key) {
		return( lockByDefSchemaIdx(key.getOptionalDefSchemaId()));
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
	@Query("select r from CFBamJpaDelDep r where r.requiredLookupRelation.requiredId = :relationId")
	List<CFBamJpaDelTopDep> lockByDelDepIdx(@Param("relationId") CFLibDbKeyHash256 requiredRelationId);

	/**
	 *	CFBamDelDepByDelDepIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaDelTopDep> lockByDelDepIdx(ICFBamDelDepByDelDepIdxKey key) {
		return( lockByDelDepIdx(key.getRequiredRelationId()));
	}

	// CFBamJpaDelTopDep specified delete-by-index methods

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredTableId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaDelTopDep r where r.requiredContainerTable.requiredId = :tableId")
	List<CFBamJpaDelTopDep> lockByDelTopDepTblIdx(@Param("tableId") CFLibDbKeyHash256 requiredTableId);

	/**
	 *	CFBamDelTopDepByDelTopDepTblIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaDelTopDep> lockByDelTopDepTblIdx(ICFBamDelTopDepByDelTopDepTblIdxKey key) {
		return( lockByDelTopDepTblIdx(key.getRequiredTableId()));
	}

	/**
	 *	Argument-based lock database entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredTableId
	 *		@param requiredName
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaDelTopDep r where r.requiredContainerTable.requiredId = :tableId and r.requiredName = :name")
	CFBamJpaDelTopDep lockByUNameIdx(@Param("tableId") CFLibDbKeyHash256 requiredTableId,
		@Param("name") String requiredName);

	/**
	 *	CFBamDelTopDepByUNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	default CFBamJpaDelTopDep lockByUNameIdx(ICFBamDelTopDepByUNameIdxKey key) {
		return( lockByUNameIdx(key.getRequiredTableId(), key.getRequiredName()));
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
	@Query("select r from CFBamJpaDelTopDep r where r.optionalLookupPrev.requiredId = :prevId")
	List<CFBamJpaDelTopDep> lockByPrevIdx(@Param("prevId") CFLibDbKeyHash256 optionalPrevId);

	/**
	 *	CFBamDelTopDepByPrevIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaDelTopDep> lockByPrevIdx(ICFBamDelTopDepByPrevIdxKey key) {
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
	@Query("select r from CFBamJpaDelTopDep r where r.optionalLookupNext.requiredId = :nextId")
	List<CFBamJpaDelTopDep> lockByNextIdx(@Param("nextId") CFLibDbKeyHash256 optionalNextId);

	/**
	 *	CFBamDelTopDepByNextIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaDelTopDep> lockByNextIdx(ICFBamDelTopDepByNextIdxKey key) {
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

	// CFBamJpaDelDep specified delete-by-index methods

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalDefSchemaId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaDelDep r where r.optionalLookupDefSchema.requiredId = :defSchemaId")
	void deleteByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId);

	/**
	 *	CFBamDelDepByDefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamDelDepByDefSchemaIdxKey of the entity to be locked.
	 */
	default void deleteByDefSchemaIdx(ICFBamDelDepByDefSchemaIdxKey key) {
		deleteByDefSchemaIdx(key.getOptionalDefSchemaId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredRelationId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaDelDep r where r.requiredLookupRelation.requiredId = :relationId")
	void deleteByDelDepIdx(@Param("relationId") CFLibDbKeyHash256 requiredRelationId);

	/**
	 *	CFBamDelDepByDelDepIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamDelDepByDelDepIdxKey of the entity to be locked.
	 */
	default void deleteByDelDepIdx(ICFBamDelDepByDelDepIdxKey key) {
		deleteByDelDepIdx(key.getRequiredRelationId());
	}

	// CFBamJpaDelTopDep specified delete-by-index methods

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredTableId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaDelTopDep r where r.requiredContainerTable.requiredId = :tableId")
	void deleteByDelTopDepTblIdx(@Param("tableId") CFLibDbKeyHash256 requiredTableId);

	/**
	 *	CFBamDelTopDepByDelTopDepTblIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamDelTopDepByDelTopDepTblIdxKey of the entity to be locked.
	 */
	default void deleteByDelTopDepTblIdx(ICFBamDelTopDepByDelTopDepTblIdxKey key) {
		deleteByDelTopDepTblIdx(key.getRequiredTableId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredTableId
	 *		@param requiredName
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaDelTopDep r where r.requiredContainerTable.requiredId = :tableId and r.requiredName = :name")
	void deleteByUNameIdx(@Param("tableId") CFLibDbKeyHash256 requiredTableId,
		@Param("name") String requiredName);

	/**
	 *	CFBamDelTopDepByUNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamDelTopDepByUNameIdxKey of the entity to be locked.
	 */
	default void deleteByUNameIdx(ICFBamDelTopDepByUNameIdxKey key) {
		deleteByUNameIdx(key.getRequiredTableId(), key.getRequiredName());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalPrevId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaDelTopDep r where r.optionalLookupPrev.requiredId = :prevId")
	void deleteByPrevIdx(@Param("prevId") CFLibDbKeyHash256 optionalPrevId);

	/**
	 *	CFBamDelTopDepByPrevIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamDelTopDepByPrevIdxKey of the entity to be locked.
	 */
	default void deleteByPrevIdx(ICFBamDelTopDepByPrevIdxKey key) {
		deleteByPrevIdx(key.getOptionalPrevId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalNextId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaDelTopDep r where r.optionalLookupNext.requiredId = :nextId")
	void deleteByNextIdx(@Param("nextId") CFLibDbKeyHash256 optionalNextId);

	/**
	 *	CFBamDelTopDepByNextIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamDelTopDepByNextIdxKey of the entity to be locked.
	 */
	default void deleteByNextIdx(ICFBamDelTopDepByNextIdxKey key) {
		deleteByNextIdx(key.getOptionalNextId());
	}

}
