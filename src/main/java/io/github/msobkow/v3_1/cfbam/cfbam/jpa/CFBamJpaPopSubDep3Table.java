
// Description: Java 25 DbIO implementation for PopSubDep3.

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

import java.lang.reflect.*;
import java.net.*;
import java.rmi.*;
import java.sql.*;
import java.text.*;
import java.time.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import io.github.msobkow.v3_1.cflib.*;
import io.github.msobkow.v3_1.cflib.dbutil.*;
import io.github.msobkow.v3_1.cfsec.cfsec.*;
import io.github.msobkow.v3_1.cfint.cfint.*;
import io.github.msobkow.v3_1.cfbam.cfbam.*;
import io.github.msobkow.v3_1.cfsec.cfsecobj.*;
import io.github.msobkow.v3_1.cfint.cfintobj.*;
import io.github.msobkow.v3_1.cfbam.cfbamobj.*;
import io.github.msobkow.v3_1.cfbam.cfbam.jpa.CFBamJpaHooksSchema;

/*
 *	CFBamJpaPopSubDep3Table database implementation for PopSubDep3
 */
public class CFBamJpaPopSubDep3Table implements ICFBamPopSubDep3Table
{
	protected CFBamJpaSchema schema;


	public CFBamJpaPopSubDep3Table(ICFBamSchema schema) {
		if( schema == null ) {
			throw new CFLibNullArgumentException(getClass(), "constructor", 1, "schema" );
		}
		if (schema instanceof CFBamJpaSchema) {
			this.schema = (CFBamJpaSchema)schema;
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "constructor", "schema", schema, "CFBamJpaSchema");
		}
	}

	/**
	 *	Create the instance in the database, and update the specified record
	 *	with the assigned primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be created.
	 */
	@Override
	public ICFBamPopSubDep3 createPopSubDep3( ICFSecAuthorization Authorization,
		ICFBamPopSubDep3 rec )
	{
		if (rec == null) {
			throw new CFLibNullArgumentException(getClass(), "createPopSubDep3", 1, "rec");
		}
		else if (rec instanceof CFBamJpaPopSubDep3) {
			CFBamJpaPopSubDep3 jparec = (CFBamJpaPopSubDep3)rec;
			CFBamJpaPopSubDep3 created = schema.getJpaHooksSchema().getPopSubDep3Service().create(jparec);
			return( created );
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "createPopSubDep3", "rec", rec, "CFBamJpaPopSubDep3");
		}
	}

	/**
	 *	Update the instance in the database, and update the specified record
	 *	with any calculated changes imposed by the associated stored procedure.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be updated
	 */
	@Override
	public ICFBamPopSubDep3 updatePopSubDep3( ICFSecAuthorization Authorization,
		ICFBamPopSubDep3 rec )
	{
		if (rec == null) {
			throw new CFLibNullArgumentException(getClass(), "updatePopSubDep3", 1, "rec");
		}
		else if (rec instanceof CFBamJpaPopSubDep3) {
			CFBamJpaPopSubDep3 jparec = (CFBamJpaPopSubDep3)rec;
			CFBamJpaPopSubDep3 updated = schema.getJpaHooksSchema().getPopSubDep3Service().update(jparec);
			return( updated );
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "updatePopSubDep3", "rec", rec, "CFBamJpaPopSubDep3");
		}
	}

	/**
	 *	Delete the instance from the database.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be deleted.
	 */
	@Override
	public void deletePopSubDep3( ICFSecAuthorization Authorization,
		ICFBamPopSubDep3 rec )
	{
		if (rec == null) {
			return;
		}
		if (rec instanceof CFBamJpaPopSubDep3) {
			CFBamJpaPopSubDep3 jparec = (CFBamJpaPopSubDep3)rec;
			schema.getJpaHooksSchema().getPopSubDep3Service().deleteByIdIdx(jparec.getPKey());
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "deletePopSubDep3", "rec", rec, "CFBamJpaPopSubDep3");
		}

		throw new CFLibNotImplementedYetException(getClass(), "deletePopSubDep3");
	}

	/**
	 *	Delete the PopSubDep3 instances identified by the key PopSubDep2Idx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PopSubDep2Id	The PopSubDep3 key attribute of the instance generating the id.
	 */
	@Override
	public void deletePopSubDep3ByPopSubDep2Idx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argPopSubDep2Id )
	{
		schema.getJpaHooksSchema().getPopSubDep3Service().deleteByPopSubDep2Idx(argPopSubDep2Id);
	}


	/**
	 *	Delete the PopSubDep3 instances identified by the key PopSubDep2Idx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deletePopSubDep3ByPopSubDep2Idx( ICFSecAuthorization Authorization,
		ICFBamPopSubDep3ByPopSubDep2IdxKey argKey )
	{
		schema.getJpaHooksSchema().getPopSubDep3Service().deleteByPopSubDep2Idx(argKey.getRequiredPopSubDep2Id());
	}

	/**
	 *	Delete the PopSubDep3 instances identified by the key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PopSubDep2Id	The PopSubDep3 key attribute of the instance generating the id.
	 *
	 *	@param	Name	The PopSubDep3 key attribute of the instance generating the id.
	 */
	@Override
	public void deletePopSubDep3ByUNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argPopSubDep2Id,
		String argName )
	{
		schema.getJpaHooksSchema().getPopSubDep3Service().deleteByUNameIdx(argPopSubDep2Id,
		argName);
	}


	/**
	 *	Delete the PopSubDep3 instances identified by the key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deletePopSubDep3ByUNameIdx( ICFSecAuthorization Authorization,
		ICFBamPopSubDep3ByUNameIdxKey argKey )
	{
		schema.getJpaHooksSchema().getPopSubDep3Service().deleteByUNameIdx(argKey.getRequiredPopSubDep2Id(),
			argKey.getRequiredName());
	}

	/**
	 *	Delete the PopSubDep3 instances identified by the key RelationIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	RelationId	The PopSubDep3 key attribute of the instance generating the id.
	 */
	@Override
	public void deletePopSubDep3ByRelationIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argRelationId )
	{
		schema.getJpaHooksSchema().getPopSubDep3Service().deleteByRelationIdx(argRelationId);
	}


	/**
	 *	Delete the PopSubDep3 instances identified by the key RelationIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deletePopSubDep3ByRelationIdx( ICFSecAuthorization Authorization,
		ICFBamPopDepByRelationIdxKey argKey )
	{
		schema.getJpaHooksSchema().getPopSubDep3Service().deleteByRelationIdx(argKey.getRequiredRelationId());
	}

	/**
	 *	Delete the PopSubDep3 instances identified by the key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DefSchemaId	The PopSubDep3 key attribute of the instance generating the id.
	 */
	@Override
	public void deletePopSubDep3ByDefSchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argDefSchemaId )
	{
		schema.getJpaHooksSchema().getPopSubDep3Service().deleteByDefSchemaIdx(argDefSchemaId);
	}


	/**
	 *	Delete the PopSubDep3 instances identified by the key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deletePopSubDep3ByDefSchemaIdx( ICFSecAuthorization Authorization,
		ICFBamPopDepByDefSchemaIdxKey argKey )
	{
		schema.getJpaHooksSchema().getPopSubDep3Service().deleteByDefSchemaIdx(argKey.getOptionalDefSchemaId());
	}

	/**
	 *	Delete the PopSubDep3 instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The primary key identifying the instance to be deleted.
	 */
	@Override
	public void deletePopSubDep3ByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argKey )
	{
		schema.getJpaHooksSchema().getPopSubDep3Service().deleteByIdIdx(argKey);
	}

	/**
	 *	Delete the PopSubDep3 instances identified by the key TenantIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TenantId	The PopSubDep3 key attribute of the instance generating the id.
	 */
	@Override
	public void deletePopSubDep3ByTenantIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argTenantId )
	{
		schema.getJpaHooksSchema().getPopSubDep3Service().deleteByTenantIdx(argTenantId);
	}


	/**
	 *	Delete the PopSubDep3 instances identified by the key TenantIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deletePopSubDep3ByTenantIdx( ICFSecAuthorization Authorization,
		ICFBamScopeByTenantIdxKey argKey )
	{
		schema.getJpaHooksSchema().getPopSubDep3Service().deleteByTenantIdx(argKey.getRequiredTenantId());
	}


	/**
	 *	Read the derived PopSubDep3 record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the PopSubDep3 instance to be read.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	@Override
	public ICFBamPopSubDep3 readDerived( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey )
	{
		return( schema.getJpaHooksSchema().getPopSubDep3Service().find(PKey) );
	}

	/**
	 *	Lock the derived PopSubDep3 record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the PopSubDep3 instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	@Override
	public ICFBamPopSubDep3 lockDerived( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey )
	{
		return( schema.getJpaHooksSchema().getPopSubDep3Service().lockByIdIdx(PKey) );
	}

	/**
	 *	Read all PopSubDep3 instances.
	 *
	 *	@param	Authorization	The session authorization information.	
	 *
	 *	@return An array of derived record instances, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamPopSubDep3[] readAllDerived( ICFSecAuthorization Authorization ) {
		List<CFBamJpaPopSubDep3> results = schema.getJpaHooksSchema().getPopSubDep3Service().findAll();
		ICFBamPopSubDep3[] retset = new ICFBamPopSubDep3[results.size()];
		int idx = 0;
		for (CFBamJpaPopSubDep3 cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read the derived PopSubDep3 record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	Id	The PopSubDep3 key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	@Override
	public ICFBamPopSubDep3 readDerivedByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argId )
	{
		return( schema.getJpaHooksSchema().getPopSubDep3Service().find(argId) );
	}

	/**
	 *	Read an array of the derived PopSubDep3 record instances identified by the duplicate key TenantIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TenantId	The PopSubDep3 key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamPopSubDep3[] readDerivedByTenantIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argTenantId )
	{
		List<CFBamJpaPopSubDep3> results = schema.getJpaHooksSchema().getPopSubDep3Service().findByTenantIdx(argTenantId);
		ICFBamPopSubDep3[] retset = new ICFBamPopSubDep3[results.size()];
		int idx = 0;
		for (CFBamJpaPopSubDep3 cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read an array of the derived PopSubDep3 record instances identified by the duplicate key RelationIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	RelationId	The PopSubDep3 key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamPopSubDep3[] readDerivedByRelationIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argRelationId )
	{
		List<CFBamJpaPopSubDep3> results = schema.getJpaHooksSchema().getPopSubDep3Service().findByRelationIdx(argRelationId);
		ICFBamPopSubDep3[] retset = new ICFBamPopSubDep3[results.size()];
		int idx = 0;
		for (CFBamJpaPopSubDep3 cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read an array of the derived PopSubDep3 record instances identified by the duplicate key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DefSchemaId	The PopSubDep3 key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamPopSubDep3[] readDerivedByDefSchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argDefSchemaId )
	{
		List<CFBamJpaPopSubDep3> results = schema.getJpaHooksSchema().getPopSubDep3Service().findByDefSchemaIdx(argDefSchemaId);
		ICFBamPopSubDep3[] retset = new ICFBamPopSubDep3[results.size()];
		int idx = 0;
		for (CFBamJpaPopSubDep3 cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read an array of the derived PopSubDep3 record instances identified by the duplicate key PopSubDep2Idx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PopSubDep2Id	The PopSubDep3 key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamPopSubDep3[] readDerivedByPopSubDep2Idx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argPopSubDep2Id )
	{
		List<CFBamJpaPopSubDep3> results = schema.getJpaHooksSchema().getPopSubDep3Service().findByPopSubDep2Idx(argPopSubDep2Id);
		ICFBamPopSubDep3[] retset = new ICFBamPopSubDep3[results.size()];
		int idx = 0;
		for (CFBamJpaPopSubDep3 cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read the derived PopSubDep3 record instance identified by the unique key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PopSubDep2Id	The PopSubDep3 key attribute of the instance generating the id.
	 *
	 *	@param	Name	The PopSubDep3 key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	@Override
	public ICFBamPopSubDep3 readDerivedByUNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argPopSubDep2Id,
		String argName )
	{
		return( schema.getJpaHooksSchema().getPopSubDep3Service().findByUNameIdx(argPopSubDep2Id,
		argName) );
	}

	/**
	 *	Read the specific PopSubDep3 record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the PopSubDep3 instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamPopSubDep3 readRec( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRec");
	}

	/**
	 *	Lock the specific PopSubDep3 record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the PopSubDep3 instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamPopSubDep3 lockRec( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey )
	{
		throw new CFLibNotImplementedYetException(getClass(), "lockRec");
	}

	/**
	 *	Read all the specific PopSubDep3 record instances.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@return All the specific PopSubDep3 instances in the database accessible for the Authorization.
	 */
	@Override
	public ICFBamPopSubDep3[] readAllRec( ICFSecAuthorization Authorization ) {
		throw new CFLibNotImplementedYetException(getClass(), "readAllRec");
	}


	/**
	 *	Read the specific PopSubDep3 record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	Id	The PopSubDep3 key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamPopSubDep3 readRecByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByIdIdx");
	}

	/**
	 *	Read an array of the specific PopSubDep3 record instances identified by the duplicate key TenantIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TenantId	The PopSubDep3 key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamPopSubDep3[] readRecByTenantIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argTenantId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByTenantIdx");
	}

	/**
	 *	Read an array of the specific PopSubDep3 record instances identified by the duplicate key RelationIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	RelationId	The PopSubDep3 key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamPopSubDep3[] readRecByRelationIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argRelationId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByRelationIdx");
	}

	/**
	 *	Read an array of the specific PopSubDep3 record instances identified by the duplicate key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DefSchemaId	The PopSubDep3 key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamPopSubDep3[] readRecByDefSchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argDefSchemaId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByDefSchemaIdx");
	}

	/**
	 *	Read an array of the specific PopSubDep3 record instances identified by the duplicate key PopSubDep2Idx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PopSubDep2Id	The PopSubDep3 key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamPopSubDep3[] readRecByPopSubDep2Idx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argPopSubDep2Id )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByPopSubDep2Idx");
	}

	/**
	 *	Read the specific PopSubDep3 record instance identified by the unique key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PopSubDep2Id	The PopSubDep3 key attribute of the instance generating the id.
	 *
	 *	@param	Name	The PopSubDep3 key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamPopSubDep3 readRecByUNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argPopSubDep2Id,
		String argName )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByUNameIdx");
	}
}
