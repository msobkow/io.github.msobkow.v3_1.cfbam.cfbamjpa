// Description: Java 25 Spring JPA Repository for EnumTag

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
 *	JpaRepository for the CFBamJpaEnumTag entities defined in io.github.msobkow.v3_1.cfbam.cfbam.jpa.
 *	The manufactured repositories try to provide a rich, do-it-all interface to the JPA data store, with both object and argument-based implementations of the interface defined.
 */
@Transactional(readOnly = true)
public interface CFBamJpaEnumTagRepository extends JpaRepository<CFBamJpaEnumTag, CFLibDbKeyHash256> {

	/**
	 *	Argument-based get database instance for compatibility with the current MSS code factory code base.
	 *
	 *		@param requiredId
	 *
	 *		@return The retrieved entity, usually from the JPA cache, or null if no such entity exists.
	 */
	@Query("select r from CFBamJpaEnumTag r where r.requiredId = :id")
	CFBamJpaEnumTag get(@Param("id") CFLibDbKeyHash256 requiredId);

	// CFBamJpaEnumTag specified index readers

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamEnumTagByEnumIdxKey as arguments.
	 *
	 *		@param requiredEnumId
	 *
	 *		@return List&lt;CFBamJpaEnumTag&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaEnumTag r where r.requiredContainerEnumDef.requiredId = :enumId")
	List<CFBamJpaEnumTag> findByEnumIdx(@Param("enumId") CFLibDbKeyHash256 requiredEnumId);

	/**
	 *	CFBamEnumTagByEnumIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamEnumTagByEnumIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaEnumTag> findByEnumIdx(ICFBamEnumTagByEnumIdxKey key) {
		return( findByEnumIdx(key.getRequiredEnumId()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamEnumTagByDefSchemaIdxKey as arguments.
	 *
	 *		@param optionalDefSchemaId
	 *
	 *		@return List&lt;CFBamJpaEnumTag&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaEnumTag r where r.optionalLookupDefSchema.requiredId = :defSchemaId")
	List<CFBamJpaEnumTag> findByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId);

	/**
	 *	CFBamEnumTagByDefSchemaIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamEnumTagByDefSchemaIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaEnumTag> findByDefSchemaIdx(ICFBamEnumTagByDefSchemaIdxKey key) {
		return( findByDefSchemaIdx(key.getOptionalDefSchemaId()));
	}

	/**
	 *	Read an entity using the columns of the CFBamEnumTagByEnumNameIdxKey as arguments.
	 *
	 *		@param requiredEnumId
	 *		@param requiredName
	 *
	 *		@return The found entity, typically from the JPA cache, or null if no such entity exists.
	 */
	@Query("select r from CFBamJpaEnumTag r where r.requiredContainerEnumDef.requiredId = :enumId and r.requiredName = :name")
	CFBamJpaEnumTag findByEnumNameIdx(@Param("enumId") CFLibDbKeyHash256 requiredEnumId,
		@Param("name") String requiredName);

	/**
	 *	CFBamEnumTagByEnumNameIdxKey entity reader convenience method for object-based access.
	 *
	 *		@param key The CFBamEnumTagByEnumNameIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity, typically from the JPA cache, or null if no such entity exists.
	 */
	default CFBamJpaEnumTag findByEnumNameIdx(ICFBamEnumTagByEnumNameIdxKey key) {
		return( findByEnumNameIdx(key.getRequiredEnumId(), key.getRequiredName()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamEnumTagByPrevIdxKey as arguments.
	 *
	 *		@param optionalPrevId
	 *
	 *		@return List&lt;CFBamJpaEnumTag&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaEnumTag r where r.optionalLookupPrev.requiredId = :prevId")
	List<CFBamJpaEnumTag> findByPrevIdx(@Param("prevId") CFLibDbKeyHash256 optionalPrevId);

	/**
	 *	CFBamEnumTagByPrevIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamEnumTagByPrevIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaEnumTag> findByPrevIdx(ICFBamEnumTagByPrevIdxKey key) {
		return( findByPrevIdx(key.getOptionalPrevId()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamEnumTagByNextIdxKey as arguments.
	 *
	 *		@param optionalNextId
	 *
	 *		@return List&lt;CFBamJpaEnumTag&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaEnumTag r where r.optionalLookupNext.requiredId = :nextId")
	List<CFBamJpaEnumTag> findByNextIdx(@Param("nextId") CFLibDbKeyHash256 optionalNextId);

	/**
	 *	CFBamEnumTagByNextIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamEnumTagByNextIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaEnumTag> findByNextIdx(ICFBamEnumTagByNextIdxKey key) {
		return( findByNextIdx(key.getOptionalNextId()));
	}

	// CFBamJpaEnumTag specified delete-by-index methods

	/**
	 *	Argument-based lock database entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredId
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaEnumTag r where r.requiredId = :id")
	CFBamJpaEnumTag lockByIdIdx(@Param("id") CFLibDbKeyHash256 requiredId);

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredEnumId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaEnumTag r where r.requiredContainerEnumDef.requiredId = :enumId")
	List<CFBamJpaEnumTag> lockByEnumIdx(@Param("enumId") CFLibDbKeyHash256 requiredEnumId);

	/**
	 *	CFBamEnumTagByEnumIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaEnumTag> lockByEnumIdx(ICFBamEnumTagByEnumIdxKey key) {
		return( lockByEnumIdx(key.getRequiredEnumId()));
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
	@Query("select r from CFBamJpaEnumTag r where r.optionalLookupDefSchema.requiredId = :defSchemaId")
	List<CFBamJpaEnumTag> lockByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId);

	/**
	 *	CFBamEnumTagByDefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaEnumTag> lockByDefSchemaIdx(ICFBamEnumTagByDefSchemaIdxKey key) {
		return( lockByDefSchemaIdx(key.getOptionalDefSchemaId()));
	}

	/**
	 *	Argument-based lock database entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredEnumId
	 *		@param requiredName
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaEnumTag r where r.requiredContainerEnumDef.requiredId = :enumId and r.requiredName = :name")
	CFBamJpaEnumTag lockByEnumNameIdx(@Param("enumId") CFLibDbKeyHash256 requiredEnumId,
		@Param("name") String requiredName);

	/**
	 *	CFBamEnumTagByEnumNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	default CFBamJpaEnumTag lockByEnumNameIdx(ICFBamEnumTagByEnumNameIdxKey key) {
		return( lockByEnumNameIdx(key.getRequiredEnumId(), key.getRequiredName()));
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
	@Query("select r from CFBamJpaEnumTag r where r.optionalLookupPrev.requiredId = :prevId")
	List<CFBamJpaEnumTag> lockByPrevIdx(@Param("prevId") CFLibDbKeyHash256 optionalPrevId);

	/**
	 *	CFBamEnumTagByPrevIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaEnumTag> lockByPrevIdx(ICFBamEnumTagByPrevIdxKey key) {
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
	@Query("select r from CFBamJpaEnumTag r where r.optionalLookupNext.requiredId = :nextId")
	List<CFBamJpaEnumTag> lockByNextIdx(@Param("nextId") CFLibDbKeyHash256 optionalNextId);

	/**
	 *	CFBamEnumTagByNextIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaEnumTag> lockByNextIdx(ICFBamEnumTagByNextIdxKey key) {
		return( lockByNextIdx(key.getOptionalNextId()));
	}

	// CFBamJpaEnumTag specified delete-by-index methods

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaEnumTag r where r.requiredId = :id")
	void deleteByIdIdx(@Param("id") CFLibDbKeyHash256 requiredId);

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredEnumId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaEnumTag r where r.requiredContainerEnumDef.requiredId = :enumId")
	void deleteByEnumIdx(@Param("enumId") CFLibDbKeyHash256 requiredEnumId);

	/**
	 *	CFBamEnumTagByEnumIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamEnumTagByEnumIdxKey of the entity to be locked.
	 */
	default void deleteByEnumIdx(ICFBamEnumTagByEnumIdxKey key) {
		deleteByEnumIdx(key.getRequiredEnumId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalDefSchemaId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaEnumTag r where r.optionalLookupDefSchema.requiredId = :defSchemaId")
	void deleteByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId);

	/**
	 *	CFBamEnumTagByDefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamEnumTagByDefSchemaIdxKey of the entity to be locked.
	 */
	default void deleteByDefSchemaIdx(ICFBamEnumTagByDefSchemaIdxKey key) {
		deleteByDefSchemaIdx(key.getOptionalDefSchemaId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredEnumId
	 *		@param requiredName
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaEnumTag r where r.requiredContainerEnumDef.requiredId = :enumId and r.requiredName = :name")
	void deleteByEnumNameIdx(@Param("enumId") CFLibDbKeyHash256 requiredEnumId,
		@Param("name") String requiredName);

	/**
	 *	CFBamEnumTagByEnumNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamEnumTagByEnumNameIdxKey of the entity to be locked.
	 */
	default void deleteByEnumNameIdx(ICFBamEnumTagByEnumNameIdxKey key) {
		deleteByEnumNameIdx(key.getRequiredEnumId(), key.getRequiredName());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalPrevId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaEnumTag r where r.optionalLookupPrev.requiredId = :prevId")
	void deleteByPrevIdx(@Param("prevId") CFLibDbKeyHash256 optionalPrevId);

	/**
	 *	CFBamEnumTagByPrevIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamEnumTagByPrevIdxKey of the entity to be locked.
	 */
	default void deleteByPrevIdx(ICFBamEnumTagByPrevIdxKey key) {
		deleteByPrevIdx(key.getOptionalPrevId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalNextId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaEnumTag r where r.optionalLookupNext.requiredId = :nextId")
	void deleteByNextIdx(@Param("nextId") CFLibDbKeyHash256 optionalNextId);

	/**
	 *	CFBamEnumTagByNextIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamEnumTagByNextIdxKey of the entity to be locked.
	 */
	default void deleteByNextIdx(ICFBamEnumTagByNextIdxKey key) {
		deleteByNextIdx(key.getOptionalNextId());
	}

}
