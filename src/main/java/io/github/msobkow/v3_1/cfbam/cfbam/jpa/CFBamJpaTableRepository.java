// Description: Java 25 Spring JPA Repository for Table

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
 *	JpaRepository for the CFBamJpaTable entities defined in io.github.msobkow.v3_1.cfbam.cfbam.jpa.
 *	The manufactured repositories try to provide a rich, do-it-all interface to the JPA data store, with both object and argument-based implementations of the interface defined.
 */
@Transactional(readOnly = true)
public interface CFBamJpaTableRepository extends JpaRepository<CFBamJpaTable, CFLibDbKeyHash256> {

	/**
	 *	Argument-based get database instance for compatibility with the current MSS code factory code base.
	 *
	 *		@param requiredId
	 *
	 *		@return The retrieved entity, usually from the JPA cache, or null if no such entity exists.
	 */
	@Query("select r from CFBamJpaScope r where r.requiredId = :id")
	CFBamJpaTable get(@Param("id") CFLibDbKeyHash256 requiredId);

	// CFBamJpaScope specified index readers

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamScopeByTenantIdxKey as arguments.
	 *
	 *		@param requiredTenantId
	 *
	 *		@return List&lt;CFBamJpaTable&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaScope r where r.requiredTenantId = :tenantId")
	List<CFBamJpaTable> findByTenantIdx(@Param("tenantId") CFLibDbKeyHash256 requiredTenantId);

	/**
	 *	CFBamScopeByTenantIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamScopeByTenantIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaTable> findByTenantIdx(ICFBamScopeByTenantIdxKey key) {
		return( findByTenantIdx(key.getRequiredTenantId()));
	}

	// CFBamJpaTable specified index readers

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamTableBySchemaDefIdxKey as arguments.
	 *
	 *		@param requiredSchemaDefId
	 *
	 *		@return List&lt;CFBamJpaTable&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaTable r where r.requiredContainerSchemaDef.requiredId = :schemaDefId")
	List<CFBamJpaTable> findBySchemaDefIdx(@Param("schemaDefId") CFLibDbKeyHash256 requiredSchemaDefId);

	/**
	 *	CFBamTableBySchemaDefIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamTableBySchemaDefIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaTable> findBySchemaDefIdx(ICFBamTableBySchemaDefIdxKey key) {
		return( findBySchemaDefIdx(key.getRequiredSchemaDefId()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamTableByDefSchemaIdxKey as arguments.
	 *
	 *		@param optionalDefSchemaId
	 *
	 *		@return List&lt;CFBamJpaTable&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaTable r where r.optionalLookupDefSchema.requiredId = :defSchemaId")
	List<CFBamJpaTable> findByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId);

	/**
	 *	CFBamTableByDefSchemaIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamTableByDefSchemaIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaTable> findByDefSchemaIdx(ICFBamTableByDefSchemaIdxKey key) {
		return( findByDefSchemaIdx(key.getOptionalDefSchemaId()));
	}

	/**
	 *	Read an entity using the columns of the CFBamTableByUNameIdxKey as arguments.
	 *
	 *		@param requiredSchemaDefId
	 *		@param requiredName
	 *
	 *		@return The found entity, typically from the JPA cache, or null if no such entity exists.
	 */
	@Query("select r from CFBamJpaTable r where r.requiredContainerSchemaDef.requiredId = :schemaDefId and r.requiredName = :name")
	CFBamJpaTable findByUNameIdx(@Param("schemaDefId") CFLibDbKeyHash256 requiredSchemaDefId,
		@Param("name") String requiredName);

	/**
	 *	CFBamTableByUNameIdxKey entity reader convenience method for object-based access.
	 *
	 *		@param key The CFBamTableByUNameIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity, typically from the JPA cache, or null if no such entity exists.
	 */
	default CFBamJpaTable findByUNameIdx(ICFBamTableByUNameIdxKey key) {
		return( findByUNameIdx(key.getRequiredSchemaDefId(), key.getRequiredName()));
	}

	/**
	 *	Read an entity using the columns of the CFBamTableBySchemaCdIdxKey as arguments.
	 *
	 *		@param requiredSchemaDefId
	 *		@param requiredTableClassCode
	 *
	 *		@return The found entity, typically from the JPA cache, or null if no such entity exists.
	 */
	@Query("select r from CFBamJpaTable r where r.requiredContainerSchemaDef.requiredId = :schemaDefId and r.requiredTableClassCode = :tableClassCode")
	CFBamJpaTable findBySchemaCdIdx(@Param("schemaDefId") CFLibDbKeyHash256 requiredSchemaDefId,
		@Param("tableClassCode") String requiredTableClassCode);

	/**
	 *	CFBamTableBySchemaCdIdxKey entity reader convenience method for object-based access.
	 *
	 *		@param key The CFBamTableBySchemaCdIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity, typically from the JPA cache, or null if no such entity exists.
	 */
	default CFBamJpaTable findBySchemaCdIdx(ICFBamTableBySchemaCdIdxKey key) {
		return( findBySchemaCdIdx(key.getRequiredSchemaDefId(), key.getRequiredTableClassCode()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamTableByPrimaryIndexIdxKey as arguments.
	 *
	 *		@param optionalPrimaryIndexId
	 *
	 *		@return List&lt;CFBamJpaTable&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaTable r where r.optionalLookupPrimaryIndex.requiredId = :primaryIndexId")
	List<CFBamJpaTable> findByPrimaryIndexIdx(@Param("primaryIndexId") CFLibDbKeyHash256 optionalPrimaryIndexId);

	/**
	 *	CFBamTableByPrimaryIndexIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamTableByPrimaryIndexIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaTable> findByPrimaryIndexIdx(ICFBamTableByPrimaryIndexIdxKey key) {
		return( findByPrimaryIndexIdx(key.getOptionalPrimaryIndexId()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamTableByLookupIndexIdxKey as arguments.
	 *
	 *		@param optionalLookupIndexId
	 *
	 *		@return List&lt;CFBamJpaTable&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaTable r where r.optionalLookupLookupIndex.requiredId = :lookupIndexId")
	List<CFBamJpaTable> findByLookupIndexIdx(@Param("lookupIndexId") CFLibDbKeyHash256 optionalLookupIndexId);

	/**
	 *	CFBamTableByLookupIndexIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamTableByLookupIndexIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaTable> findByLookupIndexIdx(ICFBamTableByLookupIndexIdxKey key) {
		return( findByLookupIndexIdx(key.getOptionalLookupIndexId()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamTableByAltIndexIdxKey as arguments.
	 *
	 *		@param optionalAltIndexId
	 *
	 *		@return List&lt;CFBamJpaTable&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaTable r where r.optionalLookupAltIndex.requiredId = :altIndexId")
	List<CFBamJpaTable> findByAltIndexIdx(@Param("altIndexId") CFLibDbKeyHash256 optionalAltIndexId);

	/**
	 *	CFBamTableByAltIndexIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamTableByAltIndexIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaTable> findByAltIndexIdx(ICFBamTableByAltIndexIdxKey key) {
		return( findByAltIndexIdx(key.getOptionalAltIndexId()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamTableByQualTableIdxKey as arguments.
	 *
	 *		@param optionalQualifyingTableId
	 *
	 *		@return List&lt;CFBamJpaTable&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaTable r where r.optionalLookupQualTable.requiredId = :qualifyingTableId")
	List<CFBamJpaTable> findByQualTableIdx(@Param("qualifyingTableId") CFLibDbKeyHash256 optionalQualifyingTableId);

	/**
	 *	CFBamTableByQualTableIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamTableByQualTableIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaTable> findByQualTableIdx(ICFBamTableByQualTableIdxKey key) {
		return( findByQualTableIdx(key.getOptionalQualifyingTableId()));
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
	CFBamJpaTable lockByIdIdx(@Param("id") CFLibDbKeyHash256 requiredId);

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
	List<CFBamJpaTable> lockByTenantIdx(@Param("tenantId") CFLibDbKeyHash256 requiredTenantId);

	/**
	 *	CFBamScopeByTenantIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaTable> lockByTenantIdx(ICFBamScopeByTenantIdxKey key) {
		return( lockByTenantIdx(key.getRequiredTenantId()));
	}

	// CFBamJpaTable specified delete-by-index methods

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredSchemaDefId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaTable r where r.requiredContainerSchemaDef.requiredId = :schemaDefId")
	List<CFBamJpaTable> lockBySchemaDefIdx(@Param("schemaDefId") CFLibDbKeyHash256 requiredSchemaDefId);

	/**
	 *	CFBamTableBySchemaDefIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaTable> lockBySchemaDefIdx(ICFBamTableBySchemaDefIdxKey key) {
		return( lockBySchemaDefIdx(key.getRequiredSchemaDefId()));
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
	@Query("select r from CFBamJpaTable r where r.optionalLookupDefSchema.requiredId = :defSchemaId")
	List<CFBamJpaTable> lockByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId);

	/**
	 *	CFBamTableByDefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaTable> lockByDefSchemaIdx(ICFBamTableByDefSchemaIdxKey key) {
		return( lockByDefSchemaIdx(key.getOptionalDefSchemaId()));
	}

	/**
	 *	Argument-based lock database entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredSchemaDefId
	 *		@param requiredName
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaTable r where r.requiredContainerSchemaDef.requiredId = :schemaDefId and r.requiredName = :name")
	CFBamJpaTable lockByUNameIdx(@Param("schemaDefId") CFLibDbKeyHash256 requiredSchemaDefId,
		@Param("name") String requiredName);

	/**
	 *	CFBamTableByUNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	default CFBamJpaTable lockByUNameIdx(ICFBamTableByUNameIdxKey key) {
		return( lockByUNameIdx(key.getRequiredSchemaDefId(), key.getRequiredName()));
	}

	/**
	 *	Argument-based lock database entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredSchemaDefId
	 *		@param requiredTableClassCode
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaTable r where r.requiredContainerSchemaDef.requiredId = :schemaDefId and r.requiredTableClassCode = :tableClassCode")
	CFBamJpaTable lockBySchemaCdIdx(@Param("schemaDefId") CFLibDbKeyHash256 requiredSchemaDefId,
		@Param("tableClassCode") String requiredTableClassCode);

	/**
	 *	CFBamTableBySchemaCdIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	default CFBamJpaTable lockBySchemaCdIdx(ICFBamTableBySchemaCdIdxKey key) {
		return( lockBySchemaCdIdx(key.getRequiredSchemaDefId(), key.getRequiredTableClassCode()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalPrimaryIndexId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaTable r where r.optionalLookupPrimaryIndex.requiredId = :primaryIndexId")
	List<CFBamJpaTable> lockByPrimaryIndexIdx(@Param("primaryIndexId") CFLibDbKeyHash256 optionalPrimaryIndexId);

	/**
	 *	CFBamTableByPrimaryIndexIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaTable> lockByPrimaryIndexIdx(ICFBamTableByPrimaryIndexIdxKey key) {
		return( lockByPrimaryIndexIdx(key.getOptionalPrimaryIndexId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalLookupIndexId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaTable r where r.optionalLookupLookupIndex.requiredId = :lookupIndexId")
	List<CFBamJpaTable> lockByLookupIndexIdx(@Param("lookupIndexId") CFLibDbKeyHash256 optionalLookupIndexId);

	/**
	 *	CFBamTableByLookupIndexIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaTable> lockByLookupIndexIdx(ICFBamTableByLookupIndexIdxKey key) {
		return( lockByLookupIndexIdx(key.getOptionalLookupIndexId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalAltIndexId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaTable r where r.optionalLookupAltIndex.requiredId = :altIndexId")
	List<CFBamJpaTable> lockByAltIndexIdx(@Param("altIndexId") CFLibDbKeyHash256 optionalAltIndexId);

	/**
	 *	CFBamTableByAltIndexIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaTable> lockByAltIndexIdx(ICFBamTableByAltIndexIdxKey key) {
		return( lockByAltIndexIdx(key.getOptionalAltIndexId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalQualifyingTableId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaTable r where r.optionalLookupQualTable.requiredId = :qualifyingTableId")
	List<CFBamJpaTable> lockByQualTableIdx(@Param("qualifyingTableId") CFLibDbKeyHash256 optionalQualifyingTableId);

	/**
	 *	CFBamTableByQualTableIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaTable> lockByQualTableIdx(ICFBamTableByQualTableIdxKey key) {
		return( lockByQualTableIdx(key.getOptionalQualifyingTableId()));
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

	// CFBamJpaTable specified delete-by-index methods

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredSchemaDefId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaTable r where r.requiredContainerSchemaDef.requiredId = :schemaDefId")
	void deleteBySchemaDefIdx(@Param("schemaDefId") CFLibDbKeyHash256 requiredSchemaDefId);

	/**
	 *	CFBamTableBySchemaDefIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamTableBySchemaDefIdxKey of the entity to be locked.
	 */
	default void deleteBySchemaDefIdx(ICFBamTableBySchemaDefIdxKey key) {
		deleteBySchemaDefIdx(key.getRequiredSchemaDefId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalDefSchemaId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaTable r where r.optionalLookupDefSchema.requiredId = :defSchemaId")
	void deleteByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId);

	/**
	 *	CFBamTableByDefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamTableByDefSchemaIdxKey of the entity to be locked.
	 */
	default void deleteByDefSchemaIdx(ICFBamTableByDefSchemaIdxKey key) {
		deleteByDefSchemaIdx(key.getOptionalDefSchemaId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredSchemaDefId
	 *		@param requiredName
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaTable r where r.requiredContainerSchemaDef.requiredId = :schemaDefId and r.requiredName = :name")
	void deleteByUNameIdx(@Param("schemaDefId") CFLibDbKeyHash256 requiredSchemaDefId,
		@Param("name") String requiredName);

	/**
	 *	CFBamTableByUNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamTableByUNameIdxKey of the entity to be locked.
	 */
	default void deleteByUNameIdx(ICFBamTableByUNameIdxKey key) {
		deleteByUNameIdx(key.getRequiredSchemaDefId(), key.getRequiredName());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredSchemaDefId
	 *		@param requiredTableClassCode
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaTable r where r.requiredContainerSchemaDef.requiredId = :schemaDefId and r.requiredTableClassCode = :tableClassCode")
	void deleteBySchemaCdIdx(@Param("schemaDefId") CFLibDbKeyHash256 requiredSchemaDefId,
		@Param("tableClassCode") String requiredTableClassCode);

	/**
	 *	CFBamTableBySchemaCdIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamTableBySchemaCdIdxKey of the entity to be locked.
	 */
	default void deleteBySchemaCdIdx(ICFBamTableBySchemaCdIdxKey key) {
		deleteBySchemaCdIdx(key.getRequiredSchemaDefId(), key.getRequiredTableClassCode());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalPrimaryIndexId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaTable r where r.optionalLookupPrimaryIndex.requiredId = :primaryIndexId")
	void deleteByPrimaryIndexIdx(@Param("primaryIndexId") CFLibDbKeyHash256 optionalPrimaryIndexId);

	/**
	 *	CFBamTableByPrimaryIndexIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamTableByPrimaryIndexIdxKey of the entity to be locked.
	 */
	default void deleteByPrimaryIndexIdx(ICFBamTableByPrimaryIndexIdxKey key) {
		deleteByPrimaryIndexIdx(key.getOptionalPrimaryIndexId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalLookupIndexId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaTable r where r.optionalLookupLookupIndex.requiredId = :lookupIndexId")
	void deleteByLookupIndexIdx(@Param("lookupIndexId") CFLibDbKeyHash256 optionalLookupIndexId);

	/**
	 *	CFBamTableByLookupIndexIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamTableByLookupIndexIdxKey of the entity to be locked.
	 */
	default void deleteByLookupIndexIdx(ICFBamTableByLookupIndexIdxKey key) {
		deleteByLookupIndexIdx(key.getOptionalLookupIndexId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalAltIndexId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaTable r where r.optionalLookupAltIndex.requiredId = :altIndexId")
	void deleteByAltIndexIdx(@Param("altIndexId") CFLibDbKeyHash256 optionalAltIndexId);

	/**
	 *	CFBamTableByAltIndexIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamTableByAltIndexIdxKey of the entity to be locked.
	 */
	default void deleteByAltIndexIdx(ICFBamTableByAltIndexIdxKey key) {
		deleteByAltIndexIdx(key.getOptionalAltIndexId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalQualifyingTableId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaTable r where r.optionalLookupQualTable.requiredId = :qualifyingTableId")
	void deleteByQualTableIdx(@Param("qualifyingTableId") CFLibDbKeyHash256 optionalQualifyingTableId);

	/**
	 *	CFBamTableByQualTableIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamTableByQualTableIdxKey of the entity to be locked.
	 */
	default void deleteByQualTableIdx(ICFBamTableByQualTableIdxKey key) {
		deleteByQualTableIdx(key.getOptionalQualifyingTableId());
	}

}
