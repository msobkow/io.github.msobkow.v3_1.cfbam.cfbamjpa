
// Description: Java 25 DbIO implementation for ClearSubDep3.

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
 *	CFBamJpaClearSubDep3Table database implementation for ClearSubDep3
 */
public class CFBamJpaClearSubDep3Table implements ICFBamClearSubDep3Table
{
	protected CFBamJpaSchema schema;


	public CFBamJpaClearSubDep3Table(ICFBamSchema schema) {
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
	public ICFBamClearSubDep3 createClearSubDep3( ICFSecAuthorization Authorization,
		ICFBamClearSubDep3 rec )
	{
		if (rec == null) {
			throw new CFLibNullArgumentException(getClass(), "createClearSubDep3", 1, "rec");
		}
		else if (rec instanceof CFBamJpaClearSubDep3) {
			CFBamJpaClearSubDep3 jparec = (CFBamJpaClearSubDep3)rec;
			CFBamJpaClearSubDep3 created = schema.getJpaHooksSchema().getClearSubDep3Service().create(jparec);
			return( created );
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "createClearSubDep3", "rec", rec, "CFBamJpaClearSubDep3");
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
	public ICFBamClearSubDep3 updateClearSubDep3( ICFSecAuthorization Authorization,
		ICFBamClearSubDep3 rec )
	{
		if (rec == null) {
			throw new CFLibNullArgumentException(getClass(), "updateClearSubDep3", 1, "rec");
		}
		else if (rec instanceof CFBamJpaClearSubDep3) {
			CFBamJpaClearSubDep3 jparec = (CFBamJpaClearSubDep3)rec;
			CFBamJpaClearSubDep3 updated = schema.getJpaHooksSchema().getClearSubDep3Service().update(jparec);
			return( updated );
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "updateClearSubDep3", "rec", rec, "CFBamJpaClearSubDep3");
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
	public void deleteClearSubDep3( ICFSecAuthorization Authorization,
		ICFBamClearSubDep3 rec )
	{
		if (rec == null) {
			return;
		}
		if (rec instanceof CFBamJpaClearSubDep3) {
			CFBamJpaClearSubDep3 jparec = (CFBamJpaClearSubDep3)rec;
			schema.getJpaHooksSchema().getClearSubDep3Service().deleteByIdIdx(jparec.getPKey());
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "deleteClearSubDep3", "rec", rec, "CFBamJpaClearSubDep3");
		}

		throw new CFLibNotImplementedYetException(getClass(), "deleteClearSubDep3");
	}

	/**
	 *	Delete the ClearSubDep3 instances identified by the key ClearSubDep2Idx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ClearSubDep2Id	The ClearSubDep3 key attribute of the instance generating the id.
	 */
	@Override
	public void deleteClearSubDep3ByClearSubDep2Idx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argClearSubDep2Id )
	{
		schema.getJpaHooksSchema().getClearSubDep3Service().deleteByClearSubDep2Idx(argClearSubDep2Id);
	}


	/**
	 *	Delete the ClearSubDep3 instances identified by the key ClearSubDep2Idx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteClearSubDep3ByClearSubDep2Idx( ICFSecAuthorization Authorization,
		ICFBamClearSubDep3ByClearSubDep2IdxKey argKey )
	{
		schema.getJpaHooksSchema().getClearSubDep3Service().deleteByClearSubDep2Idx(argKey.getRequiredClearSubDep2Id());
	}

	/**
	 *	Delete the ClearSubDep3 instances identified by the key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ClearSubDep2Id	The ClearSubDep3 key attribute of the instance generating the id.
	 *
	 *	@param	Name	The ClearSubDep3 key attribute of the instance generating the id.
	 */
	@Override
	public void deleteClearSubDep3ByUNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argClearSubDep2Id,
		String argName )
	{
		schema.getJpaHooksSchema().getClearSubDep3Service().deleteByUNameIdx(argClearSubDep2Id,
		argName);
	}


	/**
	 *	Delete the ClearSubDep3 instances identified by the key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteClearSubDep3ByUNameIdx( ICFSecAuthorization Authorization,
		ICFBamClearSubDep3ByUNameIdxKey argKey )
	{
		schema.getJpaHooksSchema().getClearSubDep3Service().deleteByUNameIdx(argKey.getRequiredClearSubDep2Id(),
			argKey.getRequiredName());
	}

	/**
	 *	Delete the ClearSubDep3 instances identified by the key ClearDepIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	RelationId	The ClearSubDep3 key attribute of the instance generating the id.
	 */
	@Override
	public void deleteClearSubDep3ByClearDepIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argRelationId )
	{
		schema.getJpaHooksSchema().getClearSubDep3Service().deleteByClearDepIdx(argRelationId);
	}


	/**
	 *	Delete the ClearSubDep3 instances identified by the key ClearDepIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteClearSubDep3ByClearDepIdx( ICFSecAuthorization Authorization,
		ICFBamClearDepByClearDepIdxKey argKey )
	{
		schema.getJpaHooksSchema().getClearSubDep3Service().deleteByClearDepIdx(argKey.getRequiredRelationId());
	}

	/**
	 *	Delete the ClearSubDep3 instances identified by the key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DefSchemaId	The ClearSubDep3 key attribute of the instance generating the id.
	 */
	@Override
	public void deleteClearSubDep3ByDefSchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argDefSchemaId )
	{
		schema.getJpaHooksSchema().getClearSubDep3Service().deleteByDefSchemaIdx(argDefSchemaId);
	}


	/**
	 *	Delete the ClearSubDep3 instances identified by the key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteClearSubDep3ByDefSchemaIdx( ICFSecAuthorization Authorization,
		ICFBamClearDepByDefSchemaIdxKey argKey )
	{
		schema.getJpaHooksSchema().getClearSubDep3Service().deleteByDefSchemaIdx(argKey.getOptionalDefSchemaId());
	}

	/**
	 *	Delete the ClearSubDep3 instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The primary key identifying the instance to be deleted.
	 */
	@Override
	public void deleteClearSubDep3ByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argKey )
	{
		schema.getJpaHooksSchema().getClearSubDep3Service().deleteByIdIdx(argKey);
	}

	/**
	 *	Delete the ClearSubDep3 instances identified by the key TenantIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TenantId	The ClearSubDep3 key attribute of the instance generating the id.
	 */
	@Override
	public void deleteClearSubDep3ByTenantIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argTenantId )
	{
		schema.getJpaHooksSchema().getClearSubDep3Service().deleteByTenantIdx(argTenantId);
	}


	/**
	 *	Delete the ClearSubDep3 instances identified by the key TenantIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteClearSubDep3ByTenantIdx( ICFSecAuthorization Authorization,
		ICFBamScopeByTenantIdxKey argKey )
	{
		schema.getJpaHooksSchema().getClearSubDep3Service().deleteByTenantIdx(argKey.getRequiredTenantId());
	}


	/**
	 *	Read the derived ClearSubDep3 record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the ClearSubDep3 instance to be read.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	@Override
	public ICFBamClearSubDep3 readDerived( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey )
	{
		return( schema.getJpaHooksSchema().getClearSubDep3Service().find(PKey) );
	}

	/**
	 *	Lock the derived ClearSubDep3 record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the ClearSubDep3 instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	@Override
	public ICFBamClearSubDep3 lockDerived( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey )
	{
		return( schema.getJpaHooksSchema().getClearSubDep3Service().lockByIdIdx(PKey) );
	}

	/**
	 *	Read all ClearSubDep3 instances.
	 *
	 *	@param	Authorization	The session authorization information.	
	 *
	 *	@return An array of derived record instances, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamClearSubDep3[] readAllDerived( ICFSecAuthorization Authorization ) {
		List<CFBamJpaClearSubDep3> results = schema.getJpaHooksSchema().getClearSubDep3Service().findAll();
		ICFBamClearSubDep3[] retset = new ICFBamClearSubDep3[results.size()];
		int idx = 0;
		for (CFBamJpaClearSubDep3 cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read the derived ClearSubDep3 record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	Id	The ClearSubDep3 key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	@Override
	public ICFBamClearSubDep3 readDerivedByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argId )
	{
		return( schema.getJpaHooksSchema().getClearSubDep3Service().find(argId) );
	}

	/**
	 *	Read an array of the derived ClearSubDep3 record instances identified by the duplicate key TenantIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TenantId	The ClearSubDep3 key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamClearSubDep3[] readDerivedByTenantIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argTenantId )
	{
		List<CFBamJpaClearSubDep3> results = schema.getJpaHooksSchema().getClearSubDep3Service().findByTenantIdx(argTenantId);
		ICFBamClearSubDep3[] retset = new ICFBamClearSubDep3[results.size()];
		int idx = 0;
		for (CFBamJpaClearSubDep3 cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read an array of the derived ClearSubDep3 record instances identified by the duplicate key ClearDepIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	RelationId	The ClearSubDep3 key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamClearSubDep3[] readDerivedByClearDepIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argRelationId )
	{
		List<CFBamJpaClearSubDep3> results = schema.getJpaHooksSchema().getClearSubDep3Service().findByClearDepIdx(argRelationId);
		ICFBamClearSubDep3[] retset = new ICFBamClearSubDep3[results.size()];
		int idx = 0;
		for (CFBamJpaClearSubDep3 cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read an array of the derived ClearSubDep3 record instances identified by the duplicate key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DefSchemaId	The ClearSubDep3 key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamClearSubDep3[] readDerivedByDefSchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argDefSchemaId )
	{
		List<CFBamJpaClearSubDep3> results = schema.getJpaHooksSchema().getClearSubDep3Service().findByDefSchemaIdx(argDefSchemaId);
		ICFBamClearSubDep3[] retset = new ICFBamClearSubDep3[results.size()];
		int idx = 0;
		for (CFBamJpaClearSubDep3 cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read an array of the derived ClearSubDep3 record instances identified by the duplicate key ClearSubDep2Idx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ClearSubDep2Id	The ClearSubDep3 key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamClearSubDep3[] readDerivedByClearSubDep2Idx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argClearSubDep2Id )
	{
		List<CFBamJpaClearSubDep3> results = schema.getJpaHooksSchema().getClearSubDep3Service().findByClearSubDep2Idx(argClearSubDep2Id);
		ICFBamClearSubDep3[] retset = new ICFBamClearSubDep3[results.size()];
		int idx = 0;
		for (CFBamJpaClearSubDep3 cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read the derived ClearSubDep3 record instance identified by the unique key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ClearSubDep2Id	The ClearSubDep3 key attribute of the instance generating the id.
	 *
	 *	@param	Name	The ClearSubDep3 key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	@Override
	public ICFBamClearSubDep3 readDerivedByUNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argClearSubDep2Id,
		String argName )
	{
		return( schema.getJpaHooksSchema().getClearSubDep3Service().findByUNameIdx(argClearSubDep2Id,
		argName) );
	}

	/**
	 *	Read the specific ClearSubDep3 record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the ClearSubDep3 instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamClearSubDep3 readRec( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRec");
	}

	/**
	 *	Lock the specific ClearSubDep3 record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the ClearSubDep3 instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamClearSubDep3 lockRec( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey )
	{
		throw new CFLibNotImplementedYetException(getClass(), "lockRec");
	}

	/**
	 *	Read all the specific ClearSubDep3 record instances.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@return All the specific ClearSubDep3 instances in the database accessible for the Authorization.
	 */
	@Override
	public ICFBamClearSubDep3[] readAllRec( ICFSecAuthorization Authorization ) {
		throw new CFLibNotImplementedYetException(getClass(), "readAllRec");
	}


	/**
	 *	Read the specific ClearSubDep3 record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	Id	The ClearSubDep3 key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamClearSubDep3 readRecByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByIdIdx");
	}

	/**
	 *	Read an array of the specific ClearSubDep3 record instances identified by the duplicate key TenantIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TenantId	The ClearSubDep3 key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamClearSubDep3[] readRecByTenantIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argTenantId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByTenantIdx");
	}

	/**
	 *	Read an array of the specific ClearSubDep3 record instances identified by the duplicate key ClearDepIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	RelationId	The ClearSubDep3 key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamClearSubDep3[] readRecByClearDepIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argRelationId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByClearDepIdx");
	}

	/**
	 *	Read an array of the specific ClearSubDep3 record instances identified by the duplicate key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DefSchemaId	The ClearSubDep3 key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamClearSubDep3[] readRecByDefSchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argDefSchemaId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByDefSchemaIdx");
	}

	/**
	 *	Read an array of the specific ClearSubDep3 record instances identified by the duplicate key ClearSubDep2Idx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ClearSubDep2Id	The ClearSubDep3 key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamClearSubDep3[] readRecByClearSubDep2Idx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argClearSubDep2Id )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByClearSubDep2Idx");
	}

	/**
	 *	Read the specific ClearSubDep3 record instance identified by the unique key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ClearSubDep2Id	The ClearSubDep3 key attribute of the instance generating the id.
	 *
	 *	@param	Name	The ClearSubDep3 key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamClearSubDep3 readRecByUNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argClearSubDep2Id,
		String argName )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByUNameIdx");
	}
}
