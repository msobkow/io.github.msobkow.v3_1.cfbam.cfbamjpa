// Description: Java 25 Spring JPA Repository for DelSubDep1

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
 *	JpaRepository for the CFBamJpaDelSubDep1 entities defined in io.github.msobkow.v3_1.cfbam.cfbam.jpa.
 *	The manufactured repositories try to provide a rich, do-it-all interface to the JPA data store, with both object and argument-based implementations of the interface defined.
 */
@Transactional(readOnly = true)
public interface CFBamJpaDelSubDep1Repository extends JpaRepository<CFBamJpaDelSubDep1, CFLibDbKeyHash256> {

	/**
	 *	Argument-based get database instance for compatibility with the current MSS code factory code base.
	 *
	 *		@param requiredId
	 *
	 *		@return The retrieved entity, usually from the JPA cache, or null if no such entity exists.
	 */
	@Query("select r from CFBamJpaScope r where r.requiredId = :id")
	CFBamJpaDelSubDep1 get(@Param("id") CFLibDbKeyHash256 requiredId);

	// CFBamJpaScope specified index readers

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamScopeByTenantIdxKey as arguments.
	 *
	 *		@param requiredTenantId
	 *
	 *		@return List&lt;CFBamJpaDelSubDep1&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaScope r where r.requiredTenantId = :tenantId")
	List<CFBamJpaDelSubDep1> findByTenantIdx(@Param("tenantId") CFLibDbKeyHash256 requiredTenantId);

	/**
	 *	CFBamScopeByTenantIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamScopeByTenantIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaDelSubDep1> findByTenantIdx(ICFBamScopeByTenantIdxKey key) {
		return( findByTenantIdx(key.getRequiredTenantId()));
	}

	// CFBamJpaDelDep specified index readers

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamDelDepByDefSchemaIdxKey as arguments.
	 *
	 *		@param optionalDefSchemaId
	 *
	 *		@return List&lt;CFBamJpaDelSubDep1&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaDelDep r where r.optionalLookupDefSchema.requiredId = :defSchemaId")
	List<CFBamJpaDelSubDep1> findByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId);

	/**
	 *	CFBamDelDepByDefSchemaIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamDelDepByDefSchemaIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaDelSubDep1> findByDefSchemaIdx(ICFBamDelDepByDefSchemaIdxKey key) {
		return( findByDefSchemaIdx(key.getOptionalDefSchemaId()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamDelDepByDelDepIdxKey as arguments.
	 *
	 *		@param requiredRelationId
	 *
	 *		@return List&lt;CFBamJpaDelSubDep1&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaDelDep r where r.requiredLookupRelation.requiredId = :relationId")
	List<CFBamJpaDelSubDep1> findByDelDepIdx(@Param("relationId") CFLibDbKeyHash256 requiredRelationId);

	/**
	 *	CFBamDelDepByDelDepIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamDelDepByDelDepIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaDelSubDep1> findByDelDepIdx(ICFBamDelDepByDelDepIdxKey key) {
		return( findByDelDepIdx(key.getRequiredRelationId()));
	}

	// CFBamJpaDelSubDep1 specified index readers

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamDelSubDep1ByDelTopDepIdxKey as arguments.
	 *
	 *		@param requiredDelTopDepId
	 *
	 *		@return List&lt;CFBamJpaDelSubDep1&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaDelSubDep1 r where r.requiredContainerDelTopDep.requiredId = :delTopDepId")
	List<CFBamJpaDelSubDep1> findByDelTopDepIdx(@Param("delTopDepId") CFLibDbKeyHash256 requiredDelTopDepId);

	/**
	 *	CFBamDelSubDep1ByDelTopDepIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamDelSubDep1ByDelTopDepIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaDelSubDep1> findByDelTopDepIdx(ICFBamDelSubDep1ByDelTopDepIdxKey key) {
		return( findByDelTopDepIdx(key.getRequiredDelTopDepId()));
	}

	/**
	 *	Read an entity using the columns of the CFBamDelSubDep1ByUNameIdxKey as arguments.
	 *
	 *		@param requiredDelTopDepId
	 *		@param requiredName
	 *
	 *		@return The found entity, typically from the JPA cache, or null if no such entity exists.
	 */
	@Query("select r from CFBamJpaDelSubDep1 r where r.requiredContainerDelTopDep.requiredId = :delTopDepId and r.requiredName = :name")
	CFBamJpaDelSubDep1 findByUNameIdx(@Param("delTopDepId") CFLibDbKeyHash256 requiredDelTopDepId,
		@Param("name") String requiredName);

	/**
	 *	CFBamDelSubDep1ByUNameIdxKey entity reader convenience method for object-based access.
	 *
	 *		@param key The CFBamDelSubDep1ByUNameIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity, typically from the JPA cache, or null if no such entity exists.
	 */
	default CFBamJpaDelSubDep1 findByUNameIdx(ICFBamDelSubDep1ByUNameIdxKey key) {
		return( findByUNameIdx(key.getRequiredDelTopDepId(), key.getRequiredName()));
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
	CFBamJpaDelSubDep1 lockByIdIdx(@Param("id") CFLibDbKeyHash256 requiredId);

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
	List<CFBamJpaDelSubDep1> lockByTenantIdx(@Param("tenantId") CFLibDbKeyHash256 requiredTenantId);

	/**
	 *	CFBamScopeByTenantIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaDelSubDep1> lockByTenantIdx(ICFBamScopeByTenantIdxKey key) {
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
	List<CFBamJpaDelSubDep1> lockByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId);

	/**
	 *	CFBamDelDepByDefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaDelSubDep1> lockByDefSchemaIdx(ICFBamDelDepByDefSchemaIdxKey key) {
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
	List<CFBamJpaDelSubDep1> lockByDelDepIdx(@Param("relationId") CFLibDbKeyHash256 requiredRelationId);

	/**
	 *	CFBamDelDepByDelDepIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaDelSubDep1> lockByDelDepIdx(ICFBamDelDepByDelDepIdxKey key) {
		return( lockByDelDepIdx(key.getRequiredRelationId()));
	}

	// CFBamJpaDelSubDep1 specified delete-by-index methods

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredDelTopDepId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaDelSubDep1 r where r.requiredContainerDelTopDep.requiredId = :delTopDepId")
	List<CFBamJpaDelSubDep1> lockByDelTopDepIdx(@Param("delTopDepId") CFLibDbKeyHash256 requiredDelTopDepId);

	/**
	 *	CFBamDelSubDep1ByDelTopDepIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaDelSubDep1> lockByDelTopDepIdx(ICFBamDelSubDep1ByDelTopDepIdxKey key) {
		return( lockByDelTopDepIdx(key.getRequiredDelTopDepId()));
	}

	/**
	 *	Argument-based lock database entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredDelTopDepId
	 *		@param requiredName
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaDelSubDep1 r where r.requiredContainerDelTopDep.requiredId = :delTopDepId and r.requiredName = :name")
	CFBamJpaDelSubDep1 lockByUNameIdx(@Param("delTopDepId") CFLibDbKeyHash256 requiredDelTopDepId,
		@Param("name") String requiredName);

	/**
	 *	CFBamDelSubDep1ByUNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	default CFBamJpaDelSubDep1 lockByUNameIdx(ICFBamDelSubDep1ByUNameIdxKey key) {
		return( lockByUNameIdx(key.getRequiredDelTopDepId(), key.getRequiredName()));
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

	// CFBamJpaDelSubDep1 specified delete-by-index methods

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredDelTopDepId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaDelSubDep1 r where r.requiredContainerDelTopDep.requiredId = :delTopDepId")
	void deleteByDelTopDepIdx(@Param("delTopDepId") CFLibDbKeyHash256 requiredDelTopDepId);

	/**
	 *	CFBamDelSubDep1ByDelTopDepIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamDelSubDep1ByDelTopDepIdxKey of the entity to be locked.
	 */
	default void deleteByDelTopDepIdx(ICFBamDelSubDep1ByDelTopDepIdxKey key) {
		deleteByDelTopDepIdx(key.getRequiredDelTopDepId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredDelTopDepId
	 *		@param requiredName
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaDelSubDep1 r where r.requiredContainerDelTopDep.requiredId = :delTopDepId and r.requiredName = :name")
	void deleteByUNameIdx(@Param("delTopDepId") CFLibDbKeyHash256 requiredDelTopDepId,
		@Param("name") String requiredName);

	/**
	 *	CFBamDelSubDep1ByUNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamDelSubDep1ByUNameIdxKey of the entity to be locked.
	 */
	default void deleteByUNameIdx(ICFBamDelSubDep1ByUNameIdxKey key) {
		deleteByUNameIdx(key.getRequiredDelTopDepId(), key.getRequiredName());
	}

}
