// Description: Java 25 Spring JPA Repository for PopSubDep1

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
 *	JpaRepository for the CFBamJpaPopSubDep1 entities defined in io.github.msobkow.v3_1.cfbam.cfbam.jpa.
 *	The manufactured repositories try to provide a rich, do-it-all interface to the JPA data store, with both object and argument-based implementations of the interface defined.
 */
@Transactional(readOnly = true)
public interface CFBamJpaPopSubDep1Repository extends JpaRepository<CFBamJpaPopSubDep1, CFLibDbKeyHash256> {

	/**
	 *	Argument-based get database instance for compatibility with the current MSS code factory code base.
	 *
	 *		@param requiredId
	 *
	 *		@return The retrieved entity, usually from the JPA cache, or null if no such entity exists.
	 */
	@Query("select r from CFBamJpaScope r where r.requiredId = :id")
	CFBamJpaPopSubDep1 get(@Param("id") CFLibDbKeyHash256 requiredId);

	// CFBamJpaScope specified index readers

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamScopeByTenantIdxKey as arguments.
	 *
	 *		@param requiredTenantId
	 *
	 *		@return List&lt;CFBamJpaPopSubDep1&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaScope r where r.requiredTenantId = :tenantId")
	List<CFBamJpaPopSubDep1> findByTenantIdx(@Param("tenantId") CFLibDbKeyHash256 requiredTenantId);

	/**
	 *	CFBamScopeByTenantIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamScopeByTenantIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaPopSubDep1> findByTenantIdx(ICFBamScopeByTenantIdxKey key) {
		return( findByTenantIdx(key.getRequiredTenantId()));
	}

	// CFBamJpaPopDep specified index readers

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamPopDepByRelationIdxKey as arguments.
	 *
	 *		@param requiredRelationId
	 *
	 *		@return List&lt;CFBamJpaPopSubDep1&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaPopDep r where r.requiredLookupRelation.requiredId = :relationId")
	List<CFBamJpaPopSubDep1> findByRelationIdx(@Param("relationId") CFLibDbKeyHash256 requiredRelationId);

	/**
	 *	CFBamPopDepByRelationIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamPopDepByRelationIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaPopSubDep1> findByRelationIdx(ICFBamPopDepByRelationIdxKey key) {
		return( findByRelationIdx(key.getRequiredRelationId()));
	}

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamPopDepByDefSchemaIdxKey as arguments.
	 *
	 *		@param optionalDefSchemaId
	 *
	 *		@return List&lt;CFBamJpaPopSubDep1&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaPopDep r where r.optionalLookupDefSchema.requiredId = :defSchemaId")
	List<CFBamJpaPopSubDep1> findByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId);

	/**
	 *	CFBamPopDepByDefSchemaIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamPopDepByDefSchemaIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaPopSubDep1> findByDefSchemaIdx(ICFBamPopDepByDefSchemaIdxKey key) {
		return( findByDefSchemaIdx(key.getOptionalDefSchemaId()));
	}

	// CFBamJpaPopSubDep1 specified index readers

	/**
	 *	Read zero or more entities into a List using the columns of the CFBamPopSubDep1ByPopTopDepIdxKey as arguments.
	 *
	 *		@param requiredPopTopDepId
	 *
	 *		@return List&lt;CFBamJpaPopSubDep1&gt; of the found entities, typically from the JPA cache, or an empty list if no such entities exist.
	 */
	@Query("select r from CFBamJpaPopSubDep1 r where r.requiredContainerContPopTopDep.requiredId = :popTopDepId")
	List<CFBamJpaPopSubDep1> findByPopTopDepIdx(@Param("popTopDepId") CFLibDbKeyHash256 requiredPopTopDepId);

	/**
	 *	CFBamPopSubDep1ByPopTopDepIdxKey entity list reader convenience method for object-based access.
	 *
	 *		@param key The CFBamPopSubDep1ByPopTopDepIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity list, which may be empty, typically populated from the JPA cache.
	 */
	default List<CFBamJpaPopSubDep1> findByPopTopDepIdx(ICFBamPopSubDep1ByPopTopDepIdxKey key) {
		return( findByPopTopDepIdx(key.getRequiredPopTopDepId()));
	}

	/**
	 *	Read an entity using the columns of the CFBamPopSubDep1ByUNameIdxKey as arguments.
	 *
	 *		@param requiredPopTopDepId
	 *		@param requiredName
	 *
	 *		@return The found entity, typically from the JPA cache, or null if no such entity exists.
	 */
	@Query("select r from CFBamJpaPopSubDep1 r where r.requiredContainerContPopTopDep.requiredId = :popTopDepId and r.requiredName = :name")
	CFBamJpaPopSubDep1 findByUNameIdx(@Param("popTopDepId") CFLibDbKeyHash256 requiredPopTopDepId,
		@Param("name") String requiredName);

	/**
	 *	CFBamPopSubDep1ByUNameIdxKey entity reader convenience method for object-based access.
	 *
	 *		@param key The CFBamPopSubDep1ByUNameIdxKey instance to use for the query arguments.
	 *
	 *		@return The found entity, typically from the JPA cache, or null if no such entity exists.
	 */
	default CFBamJpaPopSubDep1 findByUNameIdx(ICFBamPopSubDep1ByUNameIdxKey key) {
		return( findByUNameIdx(key.getRequiredPopTopDepId(), key.getRequiredName()));
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
	CFBamJpaPopSubDep1 lockByIdIdx(@Param("id") CFLibDbKeyHash256 requiredId);

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
	List<CFBamJpaPopSubDep1> lockByTenantIdx(@Param("tenantId") CFLibDbKeyHash256 requiredTenantId);

	/**
	 *	CFBamScopeByTenantIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaPopSubDep1> lockByTenantIdx(ICFBamScopeByTenantIdxKey key) {
		return( lockByTenantIdx(key.getRequiredTenantId()));
	}

	// CFBamJpaPopDep specified delete-by-index methods

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredRelationId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaPopDep r where r.requiredLookupRelation.requiredId = :relationId")
	List<CFBamJpaPopSubDep1> lockByRelationIdx(@Param("relationId") CFLibDbKeyHash256 requiredRelationId);

	/**
	 *	CFBamPopDepByRelationIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaPopSubDep1> lockByRelationIdx(ICFBamPopDepByRelationIdxKey key) {
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
	@Query("select r from CFBamJpaPopDep r where r.optionalLookupDefSchema.requiredId = :defSchemaId")
	List<CFBamJpaPopSubDep1> lockByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId);

	/**
	 *	CFBamPopDepByDefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaPopSubDep1> lockByDefSchemaIdx(ICFBamPopDepByDefSchemaIdxKey key) {
		return( lockByDefSchemaIdx(key.getOptionalDefSchemaId()));
	}

	// CFBamJpaPopSubDep1 specified delete-by-index methods

	/**
	 *	Argument-based lock database instance for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredPopTopDepId
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaPopSubDep1 r where r.requiredContainerContPopTopDep.requiredId = :popTopDepId")
	List<CFBamJpaPopSubDep1> lockByPopTopDepIdx(@Param("popTopDepId") CFLibDbKeyHash256 requiredPopTopDepId);

	/**
	 *	CFBamPopSubDep1ByPopTopDepIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return A list of locked entities, refreshed from the data store, or an empty list if no such entities exist.
	 */
	default List<CFBamJpaPopSubDep1> lockByPopTopDepIdx(ICFBamPopSubDep1ByPopTopDepIdxKey key) {
		return( lockByPopTopDepIdx(key.getRequiredPopTopDepId()));
	}

	/**
	 *	Argument-based lock database entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity locks, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredPopTopDepId
	 *		@param requiredName
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	@Transactional
	@Lock(LockModeType.WRITE)
	@Query("select r from CFBamJpaPopSubDep1 r where r.requiredContainerContPopTopDep.requiredId = :popTopDepId and r.requiredName = :name")
	CFBamJpaPopSubDep1 lockByUNameIdx(@Param("popTopDepId") CFLibDbKeyHash256 requiredPopTopDepId,
		@Param("name") String requiredName);

	/**
	 *	CFBamPopSubDep1ByUNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The key of the entity to be locked.
	 *
	 *		@return The locked entity, refreshed from the data store, or null if no such entity exists.
	 */
	default CFBamJpaPopSubDep1 lockByUNameIdx(ICFBamPopSubDep1ByUNameIdxKey key) {
		return( lockByUNameIdx(key.getRequiredPopTopDepId(), key.getRequiredName()));
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

	// CFBamJpaPopDep specified delete-by-index methods

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredRelationId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaPopDep r where r.requiredLookupRelation.requiredId = :relationId")
	void deleteByRelationIdx(@Param("relationId") CFLibDbKeyHash256 requiredRelationId);

	/**
	 *	CFBamPopDepByRelationIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamPopDepByRelationIdxKey of the entity to be locked.
	 */
	default void deleteByRelationIdx(ICFBamPopDepByRelationIdxKey key) {
		deleteByRelationIdx(key.getRequiredRelationId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param optionalDefSchemaId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaPopDep r where r.optionalLookupDefSchema.requiredId = :defSchemaId")
	void deleteByDefSchemaIdx(@Param("defSchemaId") CFLibDbKeyHash256 optionalDefSchemaId);

	/**
	 *	CFBamPopDepByDefSchemaIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamPopDepByDefSchemaIdxKey of the entity to be locked.
	 */
	default void deleteByDefSchemaIdx(ICFBamPopDepByDefSchemaIdxKey key) {
		deleteByDefSchemaIdx(key.getOptionalDefSchemaId());
	}

	// CFBamJpaPopSubDep1 specified delete-by-index methods

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredPopTopDepId
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaPopSubDep1 r where r.requiredContainerContPopTopDep.requiredId = :popTopDepId")
	void deleteByPopTopDepIdx(@Param("popTopDepId") CFLibDbKeyHash256 requiredPopTopDepId);

	/**
	 *	CFBamPopSubDep1ByPopTopDepIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamPopSubDep1ByPopTopDepIdxKey of the entity to be locked.
	 */
	default void deleteByPopTopDepIdx(ICFBamPopSubDep1ByPopTopDepIdxKey key) {
		deleteByPopTopDepIdx(key.getRequiredPopTopDepId());
	}

	/**
	 *	Argument-based delete entity for compatibility with the current MSS code factory code base, uses @Transactional to acquire a JPA entity lock, which may or may not imply an actual database lock during the transaction.
	 *
	 *		@param requiredPopTopDepId
	 *		@param requiredName
	 */
	@Transactional
	@Modifying
	@Query("delete from CFBamJpaPopSubDep1 r where r.requiredContainerContPopTopDep.requiredId = :popTopDepId and r.requiredName = :name")
	void deleteByUNameIdx(@Param("popTopDepId") CFLibDbKeyHash256 requiredPopTopDepId,
		@Param("name") String requiredName);

	/**
	 *	CFBamPopSubDep1ByUNameIdxKey based lock method for object-based access.
	 *
	 *		@param key The CFBamPopSubDep1ByUNameIdxKey of the entity to be locked.
	 */
	default void deleteByUNameIdx(ICFBamPopSubDep1ByUNameIdxKey key) {
		deleteByUNameIdx(key.getRequiredPopTopDepId(), key.getRequiredName());
	}

}
