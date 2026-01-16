// Description: Java 25 Spring JPA Repository for Param

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
 *	JpaRepository for the CFBamJpaParam entities defined in io.github.msobkow.v3_1.cfbam.cfbam.jpa.
 *	The manufactured repositories try to provide a rich, do-it-all interface to the JPA data store, with both object and argument-based implementations of the interface defined.
 */
@Transactional(readOnly = true)
public interface CFBamJpaParamRepository extends JpaRepository<CFBamJpaParam, CFLibDbKeyHash256> {

	/**
	 *	Argument-based get database instance for compatibility with the current MSS code factory code base.
	 *
	 *		@param requiredId
	 *
	 *		@return The retrieved entity, usually from the JPA cache, or null if no such entity exists.
	 */
	@Query("select r from CFBamJpaParam r where r.requiredId = :id")
	CFBamJpaParam get(@Param("id") CFLibDbKeyHash256 requiredId);

	// CFBamJpaParam specified index readers

	/**
	 *	Read an entity using the columns of the CFBamParamByUNameIdxKey as arguments.
	 *
	 *		@param requiredServerMethodId
	 *		@param requiredName
	 *
	 *		@return The found entity, typically from the JPA cache, or null if no such entity exists.
	 */
	@Query("select r from CFBamJpaParam r where r.requiredContainerServerMeth.requiredId = :serverMethodId and r.requiredName = :name")
	CFBamJpaParam findByUNameIdx(@Param("serverMethodId") CFLibDbKeyHash256 requiredServerMethodId,
		@Param("name") String requiredName);

	/**
	 *	CFBamParamByUNameIdxKey entity reader convenience method for object-based access.
	 *
	 *		@param key The CFBamParamByUNameIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity, typically from the JPA cache, or null if no such entity exists.
	 */
	default CFBamJpaParam findByUNameIdx(ICFBamParamByUNameIdxKey key) {
		return( findByUNameIdx(key.getRequiredServerMethodId(), key.getRequiredName()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamParamByServerMethodIdxKey as arguments.
	 *
	 *		@param requiredServerMethodId
	 *
	 *		@return List&lt;CFBamJpaParam&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaParam r where r.requiredContainerServerMeth.requiredId = :serverMethodId")
	List<CFBamJpaParam> findByServerMethodIdx(@Param("serverMethodId") CFLibDbKeyHash256 requiredServerMethodId);

	/**
	 *	CFBamParamByServerMethodIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamParamByServerMethodIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaParam> findByServerMethodIdx(ICFBamParamByServerMethodIdxKey key) {
		return( findByServerMethodIdx(key.getRequiredServerMethodId()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamParamByDefSchemaIdxKey as arguments.
	 *
	 *		@param optionalDefSchemaId
	 *
	 *		@return List&lt;CFBamJpaParam&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaParam r where r.optionalLookupDefSchema.requiredId = :defSchemaId")
	List<CFBamJpaParam> findByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId);

	/**
	 *	CFBamParamByDefSchemaIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamParamByDefSchemaIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaParam> findByDefSchemaIdx(ICFBamParamByDefSchemaIdxKey key) {
		return( findByDefSchemaIdx(key.getOptionalDefSchemaId()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamParamByServerTypeIdxKey as arguments.
	 *
	 *		@param optionalTypeId
	 *
	 *		@return List&lt;CFBamJpaParam&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaParam r where r.requiredLookupType.requiredId = :typeId")
	List<CFBamJpaParam> findByServerTypeIdx(@Param("typeId") CFLibDbKeyHash256 optionalTypeId);

	/**
	 *	CFBamParamByServerTypeIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamParamByServerTypeIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaParam> findByServerTypeIdx(ICFBamParamByServerTypeIdxKey key) {
		return( findByServerTypeIdx(key.getOptionalTypeId()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamParamByPrevIdxKey as arguments.
	 *
	 *		@param optionalPrevId
	 *
	 *		@return List&lt;CFBamJpaParam&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaParam r where r.optionalLookupPrev.requiredId = :prevId")
	List<CFBamJpaParam> findByPrevIdx(@Param("prevId") CFLibDbKeyHash256 optionalPrevId);

	/**
	 *	CFBamParamByPrevIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamParamByPrevIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaParam> findByPrevIdx(ICFBamParamByPrevIdxKey key) {
		return( findByPrevIdx(key.getOptionalPrevId()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamParamByNextIdxKey as arguments.
	 *
	 *		@param optionalNextId
	 *
	 *		@return List&lt;CFBamJpaParam&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaParam r where r.optionalLookupNext.requiredId = :nextId")
	List<CFBamJpaParam> findByNextIdx(@Param("nextId") CFLibDbKeyHash256 optionalNextId);

	/**
	 *	CFBamParamByNextIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamParamByNextIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaParam> findByNextIdx(ICFBamParamByNextIdxKey key) {
		return( findByNextIdx(key.getOptionalNextId()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamParamByContPrevIdxKey as arguments.
	 *
	 *		@param requiredServerMethodId
	 *		@param optionalPrevId
	 *
	 *		@return List&lt;CFBamJpaParam&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaParam r where r.requiredContainerServerMeth.requiredId = :serverMethodId and r.optionalLookupPrev.requiredId = :prevId")
	List<CFBamJpaParam> findByContPrevIdx(@Param("serverMethodId") CFLibDbKeyHash256 requiredServerMethodId,
		@Param("prevId") CFLibDbKeyHash256 optionalPrevId);

	/**
	 *	CFBamParamByContPrevIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamParamByContPrevIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaParam> findByContPrevIdx(ICFBamParamByContPrevIdxKey key) {
		return( findByContPrevIdx(key.getRequiredServerMethodId(), key.getOptionalPrevId()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamParamByContNextIdxKey as arguments.
	 *
	 *		@param requiredServerMethodId
	 *		@param optionalNextId
	 *
	 *		@return List&lt;CFBamJpaParam&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaParam r where r.requiredContainerServerMeth.requiredId = :serverMethodId and r.optionalLookupNext.requiredId = :nextId")
	List<CFBamJpaParam> findByContNextIdx(@Param("serverMethodId") CFLibDbKeyHash256 requiredServerMethodId,
		@Param("nextId") CFLibDbKeyHash256 optionalNextId);

	/**
	 *	CFBamParamByContNextIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamParamByContNextIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaParam> findByContNextIdx(ICFBamParamByContNextIdxKey key) {
		return( findByContNextIdx(key.getRequiredServerMethodId(), key.getOptionalNextId()));
	}

	// CFBamJpaParam specified delete-by-index methods

	/**
	 *	Argument-based lock database entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredId
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaParam r where r.requiredId = :id")
	CFBamJpaParam lockByIdIdx(@Param("id") CFLibDbKeyHash256 requiredId);

	/**
	 *	Argument-based lock database entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredServerMethodId
	 *		@param requiredName
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaParam r where r.requiredContainerServerMeth.requiredId = :serverMethodId and r.requiredName = :name")
	CFBamJpaParam lockByUNameIdx(@Param("serverMethodId") CFLibDbKeyHash256 requiredServerMethodId,
		@Param("name") String requiredName);

	/**
	 *	CFBamParamByUNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	default CFBamJpaParam lockByUNameIdx(ICFBamParamByUNameIdxKey key) {
		return( lockByUNameIdx(key.getRequiredServerMethodId(), key.getRequiredName()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredServerMethodId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaParam r where r.requiredContainerServerMeth.requiredId = :serverMethodId")
	List<CFBamJpaParam> lockByServerMethodIdx(@Param("serverMethodId") CFLibDbKeyHash256 requiredServerMethodId);

	/**
	 *	CFBamParamByServerMethodIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaParam> lockByServerMethodIdx(ICFBamParamByServerMethodIdxKey key) {
		return( lockByServerMethodIdx(key.getRequiredServerMethodId()));
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
	@Query("select r from CFBamJpaParam r where r.optionalLookupDefSchema.requiredId = :defSchemaId")
	List<CFBamJpaParam> lockByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId);

	/**
	 *	CFBamParamByDefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaParam> lockByDefSchemaIdx(ICFBamParamByDefSchemaIdxKey key) {
		return( lockByDefSchemaIdx(key.getOptionalDefSchemaId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalTypeId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaParam r where r.requiredLookupType.requiredId = :typeId")
	List<CFBamJpaParam> lockByServerTypeIdx(@Param("typeId") CFLibDbKeyHash256 optionalTypeId);

	/**
	 *	CFBamParamByServerTypeIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaParam> lockByServerTypeIdx(ICFBamParamByServerTypeIdxKey key) {
		return( lockByServerTypeIdx(key.getOptionalTypeId()));
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
	@Query("select r from CFBamJpaParam r where r.optionalLookupPrev.requiredId = :prevId")
	List<CFBamJpaParam> lockByPrevIdx(@Param("prevId") CFLibDbKeyHash256 optionalPrevId);

	/**
	 *	CFBamParamByPrevIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaParam> lockByPrevIdx(ICFBamParamByPrevIdxKey key) {
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
	@Query("select r from CFBamJpaParam r where r.optionalLookupNext.requiredId = :nextId")
	List<CFBamJpaParam> lockByNextIdx(@Param("nextId") CFLibDbKeyHash256 optionalNextId);

	/**
	 *	CFBamParamByNextIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaParam> lockByNextIdx(ICFBamParamByNextIdxKey key) {
		return( lockByNextIdx(key.getOptionalNextId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredServerMethodId
	 *		@param optionalPrevId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaParam r where r.requiredContainerServerMeth.requiredId = :serverMethodId and r.optionalLookupPrev.requiredId = :prevId")
	List<CFBamJpaParam> lockByContPrevIdx(@Param("serverMethodId") CFLibDbKeyHash256 requiredServerMethodId,
		@Param("prevId") CFLibDbKeyHash256 optionalPrevId);

	/**
	 *	CFBamParamByContPrevIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaParam> lockByContPrevIdx(ICFBamParamByContPrevIdxKey key) {
		return( lockByContPrevIdx(key.getRequiredServerMethodId(), key.getOptionalPrevId()));
	}

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredServerMethodId
	 *		@param optionalNextId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaParam r where r.requiredContainerServerMeth.requiredId = :serverMethodId and r.optionalLookupNext.requiredId = :nextId")
	List<CFBamJpaParam> lockByContNextIdx(@Param("serverMethodId") CFLibDbKeyHash256 requiredServerMethodId,
		@Param("nextId") CFLibDbKeyHash256 optionalNextId);

	/**
	 *	CFBamParamByContNextIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaParam> lockByContNextIdx(ICFBamParamByContNextIdxKey key) {
		return( lockByContNextIdx(key.getRequiredServerMethodId(), key.getOptionalNextId()));
	}

	// CFBamJpaParam specified delete-by-index methods

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaParam r where r.requiredId = :id")
	void deleteByIdIdx(@Param("id") CFLibDbKeyHash256 requiredId);

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredServerMethodId
	 *		@param requiredName
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaParam r where r.requiredContainerServerMeth.requiredId = :serverMethodId and r.requiredName = :name")
	void deleteByUNameIdx(@Param("serverMethodId") CFLibDbKeyHash256 requiredServerMethodId,
		@Param("name") String requiredName);

	/**
	 *	CFBamParamByUNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamParamByUNameIdxKey of the entity to be locked.
	 */
	default void deleteByUNameIdx(ICFBamParamByUNameIdxKey key) {
		deleteByUNameIdx(key.getRequiredServerMethodId(), key.getRequiredName());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredServerMethodId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaParam r where r.requiredContainerServerMeth.requiredId = :serverMethodId")
	void deleteByServerMethodIdx(@Param("serverMethodId") CFLibDbKeyHash256 requiredServerMethodId);

	/**
	 *	CFBamParamByServerMethodIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamParamByServerMethodIdxKey of the entity to be locked.
	 */
	default void deleteByServerMethodIdx(ICFBamParamByServerMethodIdxKey key) {
		deleteByServerMethodIdx(key.getRequiredServerMethodId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalDefSchemaId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaParam r where r.optionalLookupDefSchema.requiredId = :defSchemaId")
	void deleteByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId);

	/**
	 *	CFBamParamByDefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamParamByDefSchemaIdxKey of the entity to be locked.
	 */
	default void deleteByDefSchemaIdx(ICFBamParamByDefSchemaIdxKey key) {
		deleteByDefSchemaIdx(key.getOptionalDefSchemaId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalTypeId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaParam r where r.requiredLookupType.requiredId = :typeId")
	void deleteByServerTypeIdx(@Param("typeId") CFLibDbKeyHash256 optionalTypeId);

	/**
	 *	CFBamParamByServerTypeIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamParamByServerTypeIdxKey of the entity to be locked.
	 */
	default void deleteByServerTypeIdx(ICFBamParamByServerTypeIdxKey key) {
		deleteByServerTypeIdx(key.getOptionalTypeId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalPrevId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaParam r where r.optionalLookupPrev.requiredId = :prevId")
	void deleteByPrevIdx(@Param("prevId") CFLibDbKeyHash256 optionalPrevId);

	/**
	 *	CFBamParamByPrevIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamParamByPrevIdxKey of the entity to be locked.
	 */
	default void deleteByPrevIdx(ICFBamParamByPrevIdxKey key) {
		deleteByPrevIdx(key.getOptionalPrevId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalNextId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaParam r where r.optionalLookupNext.requiredId = :nextId")
	void deleteByNextIdx(@Param("nextId") CFLibDbKeyHash256 optionalNextId);

	/**
	 *	CFBamParamByNextIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamParamByNextIdxKey of the entity to be locked.
	 */
	default void deleteByNextIdx(ICFBamParamByNextIdxKey key) {
		deleteByNextIdx(key.getOptionalNextId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredServerMethodId
	 *		@param optionalPrevId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaParam r where r.requiredContainerServerMeth.requiredId = :serverMethodId and r.optionalLookupPrev.requiredId = :prevId")
	void deleteByContPrevIdx(@Param("serverMethodId") CFLibDbKeyHash256 requiredServerMethodId,
		@Param("prevId") CFLibDbKeyHash256 optionalPrevId);

	/**
	 *	CFBamParamByContPrevIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamParamByContPrevIdxKey of the entity to be locked.
	 */
	default void deleteByContPrevIdx(ICFBamParamByContPrevIdxKey key) {
		deleteByContPrevIdx(key.getRequiredServerMethodId(), key.getOptionalPrevId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredServerMethodId
	 *		@param optionalNextId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaParam r where r.requiredContainerServerMeth.requiredId = :serverMethodId and r.optionalLookupNext.requiredId = :nextId")
	void deleteByContNextIdx(@Param("serverMethodId") CFLibDbKeyHash256 requiredServerMethodId,
		@Param("nextId") CFLibDbKeyHash256 optionalNextId);

	/**
	 *	CFBamParamByContNextIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamParamByContNextIdxKey of the entity to be locked.
	 */
	default void deleteByContNextIdx(ICFBamParamByContNextIdxKey key) {
		deleteByContNextIdx(key.getRequiredServerMethodId(), key.getOptionalNextId());
	}

}
