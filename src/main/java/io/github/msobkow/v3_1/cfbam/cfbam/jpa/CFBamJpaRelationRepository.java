// Description: Java 25 Spring JPA Repository for Relation

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
 *	JpaRepository for the CFBamJpaRelation entities defined in io.github.msobkow.v3_1.cfbam.cfbam.jpa.
 *	The manufactured repositories try to provide a rich, do-it-all interface to the JPA data store, with both object and argument-based implementations of the interface defined.
 */
@Transactional(readOnly = true)
public interface CFBamJpaRelationRepository extends JpaRepository<CFBamJpaRelation, CFLibDbKeyHash256> {

	/**
	 *	Argument-based get database instance for compatibility with the current MSS code factory code base.
	 *
	 *		@param requiredId
	 *
	 *		@return The retrieved entity, usually from the JPA cache, or null if no such entity exists.
	 */
	@Query("select r from CFBamJpaScope r where r.requiredId = :id")
	CFBamJpaRelation get(@Param("id") CFLibDbKeyHash256 requiredId);

	// CFBamJpaScope specified index readers

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamScopeByTenantIdxKey as arguments.
	 *
	 *		@param requiredTenantId
	 *
	 *		@return List&lt;CFBamJpaRelation&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaScope r where r.requiredTenantId = :tenantId")
	List<CFBamJpaRelation> findByTenantIdx(@Param("tenantId") CFLibDbKeyHash256 requiredTenantId);

	/**
	 *	CFBamScopeByTenantIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamScopeByTenantIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaRelation> findByTenantIdx(ICFBamScopeByTenantIdxKey key) {
		return( findByTenantIdx(key.getRequiredTenantId()));
	}

	// CFBamJpaRelation specified index readers

	/**
	 *	Read an entity using the columns of the CFBamRelationByUNameIdxKey as arguments.
	 *
	 *		@param requiredTableId
	 *		@param requiredName
	 *
	 *		@return The found entity, typically from the JPA cache, or null if no such entity exists.
	 */
	@Query("select r from CFBamJpaRelation r where r.requiredContainerFromTable.requiredId = :tableId and r.requiredName = :name")
	CFBamJpaRelation findByUNameIdx(@Param("tableId") CFLibDbKeyHash256 requiredTableId,
		@Param("name") String requiredName);

	/**
	 *	CFBamRelationByUNameIdxKey entity reader convenience method for object-based access.
	 *
	 *		@param key The CFBamRelationByUNameIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity, typically from the JPA cache, or null if no such entity exists.
	 */
	default CFBamJpaRelation findByUNameIdx(ICFBamRelationByUNameIdxKey key) {
		return( findByUNameIdx(key.getRequiredTableId(), key.getRequiredName()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamRelationByRelTableIdxKey as arguments.
	 *
	 *		@param requiredTableId
	 *
	 *		@return List&lt;CFBamJpaRelation&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaRelation r where r.requiredContainerFromTable.requiredId = :tableId")
	List<CFBamJpaRelation> findByRelTableIdx(@Param("tableId") CFLibDbKeyHash256 requiredTableId);

	/**
	 *	CFBamRelationByRelTableIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamRelationByRelTableIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaRelation> findByRelTableIdx(ICFBamRelationByRelTableIdxKey key) {
		return( findByRelTableIdx(key.getRequiredTableId()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamRelationByDefSchemaIdxKey as arguments.
	 *
	 *		@param optionalDefSchemaId
	 *
	 *		@return List&lt;CFBamJpaRelation&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaRelation r where r.optionalLookupDefSchema.requiredId = :defSchemaId")
	List<CFBamJpaRelation> findByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId);

	/**
	 *	CFBamRelationByDefSchemaIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamRelationByDefSchemaIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaRelation> findByDefSchemaIdx(ICFBamRelationByDefSchemaIdxKey key) {
		return( findByDefSchemaIdx(key.getOptionalDefSchemaId()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamRelationByFromKeyIdxKey as arguments.
	 *
	 *		@param requiredFromIndexId
	 *
	 *		@return List&lt;CFBamJpaRelation&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaRelation r where r.requiredLookupFromIndex.requiredId = :fromIndexId")
	List<CFBamJpaRelation> findByFromKeyIdx(@Param("fromIndexId") CFLibDbKeyHash256 requiredFromIndexId);

	/**
	 *	CFBamRelationByFromKeyIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamRelationByFromKeyIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaRelation> findByFromKeyIdx(ICFBamRelationByFromKeyIdxKey key) {
		return( findByFromKeyIdx(key.getRequiredFromIndexId()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamRelationByToTblIdxKey as arguments.
	 *
	 *		@param requiredToTableId
	 *
	 *		@return List&lt;CFBamJpaRelation&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaRelation r where r.requiredLookupToTable.requiredId = :toTableId")
	List<CFBamJpaRelation> findByToTblIdx(@Param("toTableId") CFLibDbKeyHash256 requiredToTableId);

	/**
	 *	CFBamRelationByToTblIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamRelationByToTblIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaRelation> findByToTblIdx(ICFBamRelationByToTblIdxKey key) {
		return( findByToTblIdx(key.getRequiredToTableId()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamRelationByToKeyIdxKey as arguments.
	 *
	 *		@param requiredToIndexId
	 *
	 *		@return List&lt;CFBamJpaRelation&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaRelation r where r.requiredLookupToIndex.requiredId = :toIndexId")
	List<CFBamJpaRelation> findByToKeyIdx(@Param("toIndexId") CFLibDbKeyHash256 requiredToIndexId);

	/**
	 *	CFBamRelationByToKeyIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamRelationByToKeyIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaRelation> findByToKeyIdx(ICFBamRelationByToKeyIdxKey key) {
		return( findByToKeyIdx(key.getRequiredToIndexId()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamRelationByNarrowedIdxKey as arguments.
	 *
	 *		@param optionalNarrowedId
	 *
	 *		@return List&lt;CFBamJpaRelation&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaRelation r where r.optionalLookupNarrowed.requiredId = :narrowedId")
	List<CFBamJpaRelation> findByNarrowedIdx(@Param("narrowedId") CFLibDbKeyHash256 optionalNarrowedId);

	/**
	 *	CFBamRelationByNarrowedIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamRelationByNarrowedIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaRelation> findByNarrowedIdx(ICFBamRelationByNarrowedIdxKey key) {
		return( findByNarrowedIdx(key.getOptionalNarrowedId()));
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
	CFBamJpaRelation lockByIdIdx(@Param("id") CFLibDbKeyHash256 requiredId);

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
	List<CFBamJpaRelation> lockByTenantIdx(@Param("tenantId") CFLibDbKeyHash256 requiredTenantId);

	/**
	 *	CFBamScopeByTenantIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaRelation> lockByTenantIdx(ICFBamScopeByTenantIdxKey key) {
		return( lockByTenantIdx(key.getRequiredTenantId()));
	}

	// CFBamJpaRelation specified delete-by-index methods

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
	@Query("select r from CFBamJpaRelation r where r.requiredContainerFromTable.requiredId = :tableId and r.requiredName = :name")
	CFBamJpaRelation lockByUNameIdx(@Param("tableId") CFLibDbKeyHash256 requiredTableId,
		@Param("name") String requiredName);

	/**
	 *	CFBamRelationByUNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	default CFBamJpaRelation lockByUNameIdx(ICFBamRelationByUNameIdxKey key) {
		return( lockByUNameIdx(key.getRequiredTableId(), key.getRequiredName()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredTableId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaRelation r where r.requiredContainerFromTable.requiredId = :tableId")
	List<CFBamJpaRelation> lockByRelTableIdx(@Param("tableId") CFLibDbKeyHash256 requiredTableId);

	/**
	 *	CFBamRelationByRelTableIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaRelation> lockByRelTableIdx(ICFBamRelationByRelTableIdxKey key) {
		return( lockByRelTableIdx(key.getRequiredTableId()));
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
	@Query("select r from CFBamJpaRelation r where r.optionalLookupDefSchema.requiredId = :defSchemaId")
	List<CFBamJpaRelation> lockByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId);

	/**
	 *	CFBamRelationByDefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaRelation> lockByDefSchemaIdx(ICFBamRelationByDefSchemaIdxKey key) {
		return( lockByDefSchemaIdx(key.getOptionalDefSchemaId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredFromIndexId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaRelation r where r.requiredLookupFromIndex.requiredId = :fromIndexId")
	List<CFBamJpaRelation> lockByFromKeyIdx(@Param("fromIndexId") CFLibDbKeyHash256 requiredFromIndexId);

	/**
	 *	CFBamRelationByFromKeyIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaRelation> lockByFromKeyIdx(ICFBamRelationByFromKeyIdxKey key) {
		return( lockByFromKeyIdx(key.getRequiredFromIndexId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredToTableId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaRelation r where r.requiredLookupToTable.requiredId = :toTableId")
	List<CFBamJpaRelation> lockByToTblIdx(@Param("toTableId") CFLibDbKeyHash256 requiredToTableId);

	/**
	 *	CFBamRelationByToTblIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaRelation> lockByToTblIdx(ICFBamRelationByToTblIdxKey key) {
		return( lockByToTblIdx(key.getRequiredToTableId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredToIndexId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaRelation r where r.requiredLookupToIndex.requiredId = :toIndexId")
	List<CFBamJpaRelation> lockByToKeyIdx(@Param("toIndexId") CFLibDbKeyHash256 requiredToIndexId);

	/**
	 *	CFBamRelationByToKeyIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaRelation> lockByToKeyIdx(ICFBamRelationByToKeyIdxKey key) {
		return( lockByToKeyIdx(key.getRequiredToIndexId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalNarrowedId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaRelation r where r.optionalLookupNarrowed.requiredId = :narrowedId")
	List<CFBamJpaRelation> lockByNarrowedIdx(@Param("narrowedId") CFLibDbKeyHash256 optionalNarrowedId);

	/**
	 *	CFBamRelationByNarrowedIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaRelation> lockByNarrowedIdx(ICFBamRelationByNarrowedIdxKey key) {
		return( lockByNarrowedIdx(key.getOptionalNarrowedId()));
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

	// CFBamJpaRelation specified delete-by-index methods

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredTableId
	 *		@param requiredName
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaRelation r where r.requiredContainerFromTable.requiredId = :tableId and r.requiredName = :name")
	void deleteByUNameIdx(@Param("tableId") CFLibDbKeyHash256 requiredTableId,
		@Param("name") String requiredName);

	/**
	 *	CFBamRelationByUNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamRelationByUNameIdxKey of the entity to be locked.
	 */
	default void deleteByUNameIdx(ICFBamRelationByUNameIdxKey key) {
		deleteByUNameIdx(key.getRequiredTableId(), key.getRequiredName());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredTableId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaRelation r where r.requiredContainerFromTable.requiredId = :tableId")
	void deleteByRelTableIdx(@Param("tableId") CFLibDbKeyHash256 requiredTableId);

	/**
	 *	CFBamRelationByRelTableIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamRelationByRelTableIdxKey of the entity to be locked.
	 */
	default void deleteByRelTableIdx(ICFBamRelationByRelTableIdxKey key) {
		deleteByRelTableIdx(key.getRequiredTableId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalDefSchemaId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaRelation r where r.optionalLookupDefSchema.requiredId = :defSchemaId")
	void deleteByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId);

	/**
	 *	CFBamRelationByDefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamRelationByDefSchemaIdxKey of the entity to be locked.
	 */
	default void deleteByDefSchemaIdx(ICFBamRelationByDefSchemaIdxKey key) {
		deleteByDefSchemaIdx(key.getOptionalDefSchemaId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredFromIndexId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaRelation r where r.requiredLookupFromIndex.requiredId = :fromIndexId")
	void deleteByFromKeyIdx(@Param("fromIndexId") CFLibDbKeyHash256 requiredFromIndexId);

	/**
	 *	CFBamRelationByFromKeyIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamRelationByFromKeyIdxKey of the entity to be locked.
	 */
	default void deleteByFromKeyIdx(ICFBamRelationByFromKeyIdxKey key) {
		deleteByFromKeyIdx(key.getRequiredFromIndexId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredToTableId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaRelation r where r.requiredLookupToTable.requiredId = :toTableId")
	void deleteByToTblIdx(@Param("toTableId") CFLibDbKeyHash256 requiredToTableId);

	/**
	 *	CFBamRelationByToTblIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamRelationByToTblIdxKey of the entity to be locked.
	 */
	default void deleteByToTblIdx(ICFBamRelationByToTblIdxKey key) {
		deleteByToTblIdx(key.getRequiredToTableId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredToIndexId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaRelation r where r.requiredLookupToIndex.requiredId = :toIndexId")
	void deleteByToKeyIdx(@Param("toIndexId") CFLibDbKeyHash256 requiredToIndexId);

	/**
	 *	CFBamRelationByToKeyIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamRelationByToKeyIdxKey of the entity to be locked.
	 */
	default void deleteByToKeyIdx(ICFBamRelationByToKeyIdxKey key) {
		deleteByToKeyIdx(key.getRequiredToIndexId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalNarrowedId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaRelation r where r.optionalLookupNarrowed.requiredId = :narrowedId")
	void deleteByNarrowedIdx(@Param("narrowedId") CFLibDbKeyHash256 optionalNarrowedId);

	/**
	 *	CFBamRelationByNarrowedIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamRelationByNarrowedIdxKey of the entity to be locked.
	 */
	default void deleteByNarrowedIdx(ICFBamRelationByNarrowedIdxKey key) {
		deleteByNarrowedIdx(key.getOptionalNarrowedId());
	}

}
