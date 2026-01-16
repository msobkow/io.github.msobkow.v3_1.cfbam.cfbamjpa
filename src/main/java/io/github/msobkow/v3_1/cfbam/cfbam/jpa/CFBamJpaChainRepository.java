// Description: Java 25 Spring JPA Repository for Chain

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
 *	JpaRepository for the CFBamJpaChain entities defined in io.github.msobkow.v3_1.cfbam.cfbam.jpa.
 *	The manufactured repositories try to provide a rich, do-it-all interface to the JPA data store, with both object and argument-based implementations of the interface defined.
 */
@Transactional(readOnly = true)
public interface CFBamJpaChainRepository extends JpaRepository<CFBamJpaChain, CFLibDbKeyHash256> {

	/**
	 *	Argument-based get database instance for compatibility with the current MSS code factory code base.
	 *
	 *		@param requiredId
	 *
	 *		@return The retrieved entity, usually from the JPA cache, or null if no such entity exists.
	 */
	@Query("select r from CFBamJpaChain r where r.requiredId = :id")
	CFBamJpaChain get(@Param("id") CFLibDbKeyHash256 requiredId);

	// CFBamJpaChain specified index readers

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamChainByChainTableIdxKey as arguments.
	 *
	 *		@param requiredTableId
	 *
	 *		@return List&lt;CFBamJpaChain&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaChain r where r.requiredContainerTable.requiredId = :tableId")
	List<CFBamJpaChain> findByChainTableIdx(@Param("tableId") CFLibDbKeyHash256 requiredTableId);

	/**
	 *	CFBamChainByChainTableIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamChainByChainTableIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaChain> findByChainTableIdx(ICFBamChainByChainTableIdxKey key) {
		return( findByChainTableIdx(key.getRequiredTableId()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamChainByDefSchemaIdxKey as arguments.
	 *
	 *		@param optionalDefSchemaId
	 *
	 *		@return List&lt;CFBamJpaChain&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaChain r where r.optionalLookupDefSchema.requiredId = :defSchemaId")
	List<CFBamJpaChain> findByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId);

	/**
	 *	CFBamChainByDefSchemaIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamChainByDefSchemaIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaChain> findByDefSchemaIdx(ICFBamChainByDefSchemaIdxKey key) {
		return( findByDefSchemaIdx(key.getOptionalDefSchemaId()));
	}

	/**
	 *	Read an entity using the columns of the CFBamChainByUNameIdxKey as arguments.
	 *
	 *		@param requiredTableId
	 *		@param requiredName
	 *
	 *		@return The found entity, typically from the JPA cache, or null if no such entity exists.
	 */
	@Query("select r from CFBamJpaChain r where r.requiredContainerTable.requiredId = :tableId and r.requiredName = :name")
	CFBamJpaChain findByUNameIdx(@Param("tableId") CFLibDbKeyHash256 requiredTableId,
		@Param("name") String requiredName);

	/**
	 *	CFBamChainByUNameIdxKey entity reader convenience method for object-based access.
	 *
	 *		@param key The CFBamChainByUNameIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity, typically from the JPA cache, or null if no such entity exists.
	 */
	default CFBamJpaChain findByUNameIdx(ICFBamChainByUNameIdxKey key) {
		return( findByUNameIdx(key.getRequiredTableId(), key.getRequiredName()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamChainByPrevRelIdxKey as arguments.
	 *
	 *		@param requiredPrevRelationId
	 *
	 *		@return List&lt;CFBamJpaChain&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaChain r where r.requiredLookupPrevRel.requiredId = :prevRelationId")
	List<CFBamJpaChain> findByPrevRelIdx(@Param("prevRelationId") CFLibDbKeyHash256 requiredPrevRelationId);

	/**
	 *	CFBamChainByPrevRelIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamChainByPrevRelIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaChain> findByPrevRelIdx(ICFBamChainByPrevRelIdxKey key) {
		return( findByPrevRelIdx(key.getRequiredPrevRelationId()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamChainByNextRelIdxKey as arguments.
	 *
	 *		@param requiredNextRelationId
	 *
	 *		@return List&lt;CFBamJpaChain&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaChain r where r.requiredLookupNextRel.requiredId = :nextRelationId")
	List<CFBamJpaChain> findByNextRelIdx(@Param("nextRelationId") CFLibDbKeyHash256 requiredNextRelationId);

	/**
	 *	CFBamChainByNextRelIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamChainByNextRelIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaChain> findByNextRelIdx(ICFBamChainByNextRelIdxKey key) {
		return( findByNextRelIdx(key.getRequiredNextRelationId()));
	}

	// CFBamJpaChain specified delete-by-index methods

	/**
	 *	Argument-based lock database entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredId
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaChain r where r.requiredId = :id")
	CFBamJpaChain lockByIdIdx(@Param("id") CFLibDbKeyHash256 requiredId);

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredTableId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaChain r where r.requiredContainerTable.requiredId = :tableId")
	List<CFBamJpaChain> lockByChainTableIdx(@Param("tableId") CFLibDbKeyHash256 requiredTableId);

	/**
	 *	CFBamChainByChainTableIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaChain> lockByChainTableIdx(ICFBamChainByChainTableIdxKey key) {
		return( lockByChainTableIdx(key.getRequiredTableId()));
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
	@Query("select r from CFBamJpaChain r where r.optionalLookupDefSchema.requiredId = :defSchemaId")
	List<CFBamJpaChain> lockByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId);

	/**
	 *	CFBamChainByDefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaChain> lockByDefSchemaIdx(ICFBamChainByDefSchemaIdxKey key) {
		return( lockByDefSchemaIdx(key.getOptionalDefSchemaId()));
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
	@Query("select r from CFBamJpaChain r where r.requiredContainerTable.requiredId = :tableId and r.requiredName = :name")
	CFBamJpaChain lockByUNameIdx(@Param("tableId") CFLibDbKeyHash256 requiredTableId,
		@Param("name") String requiredName);

	/**
	 *	CFBamChainByUNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	default CFBamJpaChain lockByUNameIdx(ICFBamChainByUNameIdxKey key) {
		return( lockByUNameIdx(key.getRequiredTableId(), key.getRequiredName()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredPrevRelationId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaChain r where r.requiredLookupPrevRel.requiredId = :prevRelationId")
	List<CFBamJpaChain> lockByPrevRelIdx(@Param("prevRelationId") CFLibDbKeyHash256 requiredPrevRelationId);

	/**
	 *	CFBamChainByPrevRelIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaChain> lockByPrevRelIdx(ICFBamChainByPrevRelIdxKey key) {
		return( lockByPrevRelIdx(key.getRequiredPrevRelationId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredNextRelationId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaChain r where r.requiredLookupNextRel.requiredId = :nextRelationId")
	List<CFBamJpaChain> lockByNextRelIdx(@Param("nextRelationId") CFLibDbKeyHash256 requiredNextRelationId);

	/**
	 *	CFBamChainByNextRelIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaChain> lockByNextRelIdx(ICFBamChainByNextRelIdxKey key) {
		return( lockByNextRelIdx(key.getRequiredNextRelationId()));
	}

	// CFBamJpaChain specified delete-by-index methods

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaChain r where r.requiredId = :id")
	void deleteByIdIdx(@Param("id") CFLibDbKeyHash256 requiredId);

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredTableId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaChain r where r.requiredContainerTable.requiredId = :tableId")
	void deleteByChainTableIdx(@Param("tableId") CFLibDbKeyHash256 requiredTableId);

	/**
	 *	CFBamChainByChainTableIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamChainByChainTableIdxKey of the entity to be locked.
	 */
	default void deleteByChainTableIdx(ICFBamChainByChainTableIdxKey key) {
		deleteByChainTableIdx(key.getRequiredTableId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalDefSchemaId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaChain r where r.optionalLookupDefSchema.requiredId = :defSchemaId")
	void deleteByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId);

	/**
	 *	CFBamChainByDefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamChainByDefSchemaIdxKey of the entity to be locked.
	 */
	default void deleteByDefSchemaIdx(ICFBamChainByDefSchemaIdxKey key) {
		deleteByDefSchemaIdx(key.getOptionalDefSchemaId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredTableId
	 *		@param requiredName
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaChain r where r.requiredContainerTable.requiredId = :tableId and r.requiredName = :name")
	void deleteByUNameIdx(@Param("tableId") CFLibDbKeyHash256 requiredTableId,
		@Param("name") String requiredName);

	/**
	 *	CFBamChainByUNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamChainByUNameIdxKey of the entity to be locked.
	 */
	default void deleteByUNameIdx(ICFBamChainByUNameIdxKey key) {
		deleteByUNameIdx(key.getRequiredTableId(), key.getRequiredName());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredPrevRelationId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaChain r where r.requiredLookupPrevRel.requiredId = :prevRelationId")
	void deleteByPrevRelIdx(@Param("prevRelationId") CFLibDbKeyHash256 requiredPrevRelationId);

	/**
	 *	CFBamChainByPrevRelIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamChainByPrevRelIdxKey of the entity to be locked.
	 */
	default void deleteByPrevRelIdx(ICFBamChainByPrevRelIdxKey key) {
		deleteByPrevRelIdx(key.getRequiredPrevRelationId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredNextRelationId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaChain r where r.requiredLookupNextRel.requiredId = :nextRelationId")
	void deleteByNextRelIdx(@Param("nextRelationId") CFLibDbKeyHash256 requiredNextRelationId);

	/**
	 *	CFBamChainByNextRelIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamChainByNextRelIdxKey of the entity to be locked.
	 */
	default void deleteByNextRelIdx(ICFBamChainByNextRelIdxKey key) {
		deleteByNextRelIdx(key.getRequiredNextRelationId());
	}

}
