
// Description: Java 25 DbIO implementation for DelSubDep1.

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
 *	CFBamJpaDelSubDep1Table database implementation for DelSubDep1
 */
public class CFBamJpaDelSubDep1Table implements ICFBamDelSubDep1Table
{
	protected CFBamJpaSchema schema;


	public CFBamJpaDelSubDep1Table(ICFBamSchema schema) {
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
	public ICFBamDelSubDep1 createDelSubDep1( ICFSecAuthorization Authorization,
		ICFBamDelSubDep1 rec )
	{
		if (rec == null) {
			throw new CFLibNullArgumentException(getClass(), "createDelSubDep1", 1, "rec");
		}
		else if (rec instanceof CFBamJpaDelSubDep1) {
			CFBamJpaDelSubDep1 jparec = (CFBamJpaDelSubDep1)rec;
			CFBamJpaDelSubDep1 created = schema.getJpaHooksSchema().getDelSubDep1Service().create(jparec);
			return( created );
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "createDelSubDep1", "rec", rec, "CFBamJpaDelSubDep1");
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
	public ICFBamDelSubDep1 updateDelSubDep1( ICFSecAuthorization Authorization,
		ICFBamDelSubDep1 rec )
	{
		if (rec == null) {
			throw new CFLibNullArgumentException(getClass(), "updateDelSubDep1", 1, "rec");
		}
		else if (rec instanceof CFBamJpaDelSubDep1) {
			CFBamJpaDelSubDep1 jparec = (CFBamJpaDelSubDep1)rec;
			CFBamJpaDelSubDep1 updated = schema.getJpaHooksSchema().getDelSubDep1Service().update(jparec);
			return( updated );
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "updateDelSubDep1", "rec", rec, "CFBamJpaDelSubDep1");
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
	public void deleteDelSubDep1( ICFSecAuthorization Authorization,
		ICFBamDelSubDep1 rec )
	{
		if (rec == null) {
			return;
		}
		if (rec instanceof CFBamJpaDelSubDep1) {
			CFBamJpaDelSubDep1 jparec = (CFBamJpaDelSubDep1)rec;
			schema.getJpaHooksSchema().getDelSubDep1Service().deleteByIdIdx(jparec.getPKey());
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "deleteDelSubDep1", "rec", rec, "CFBamJpaDelSubDep1");
		}

		throw new CFLibNotImplementedYetException(getClass(), "deleteDelSubDep1");
	}

	/**
	 *	Delete the DelSubDep1 instances identified by the key DelTopDepIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DelTopDepId	The DelSubDep1 key attribute of the instance generating the id.
	 */
	@Override
	public void deleteDelSubDep1ByDelTopDepIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argDelTopDepId )
	{
		schema.getJpaHooksSchema().getDelSubDep1Service().deleteByDelTopDepIdx(argDelTopDepId);
	}


	/**
	 *	Delete the DelSubDep1 instances identified by the key DelTopDepIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteDelSubDep1ByDelTopDepIdx( ICFSecAuthorization Authorization,
		ICFBamDelSubDep1ByDelTopDepIdxKey argKey )
	{
		schema.getJpaHooksSchema().getDelSubDep1Service().deleteByDelTopDepIdx(argKey.getRequiredDelTopDepId());
	}

	/**
	 *	Delete the DelSubDep1 instances identified by the key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DelTopDepId	The DelSubDep1 key attribute of the instance generating the id.
	 *
	 *	@param	Name	The DelSubDep1 key attribute of the instance generating the id.
	 */
	@Override
	public void deleteDelSubDep1ByUNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argDelTopDepId,
		String argName )
	{
		schema.getJpaHooksSchema().getDelSubDep1Service().deleteByUNameIdx(argDelTopDepId,
		argName);
	}


	/**
	 *	Delete the DelSubDep1 instances identified by the key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteDelSubDep1ByUNameIdx( ICFSecAuthorization Authorization,
		ICFBamDelSubDep1ByUNameIdxKey argKey )
	{
		schema.getJpaHooksSchema().getDelSubDep1Service().deleteByUNameIdx(argKey.getRequiredDelTopDepId(),
			argKey.getRequiredName());
	}

	/**
	 *	Delete the DelSubDep1 instances identified by the key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DefSchemaId	The DelSubDep1 key attribute of the instance generating the id.
	 */
	@Override
	public void deleteDelSubDep1ByDefSchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argDefSchemaId )
	{
		schema.getJpaHooksSchema().getDelSubDep1Service().deleteByDefSchemaIdx(argDefSchemaId);
	}


	/**
	 *	Delete the DelSubDep1 instances identified by the key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteDelSubDep1ByDefSchemaIdx( ICFSecAuthorization Authorization,
		ICFBamDelDepByDefSchemaIdxKey argKey )
	{
		schema.getJpaHooksSchema().getDelSubDep1Service().deleteByDefSchemaIdx(argKey.getOptionalDefSchemaId());
	}

	/**
	 *	Delete the DelSubDep1 instances identified by the key DelDepIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	RelationId	The DelSubDep1 key attribute of the instance generating the id.
	 */
	@Override
	public void deleteDelSubDep1ByDelDepIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argRelationId )
	{
		schema.getJpaHooksSchema().getDelSubDep1Service().deleteByDelDepIdx(argRelationId);
	}


	/**
	 *	Delete the DelSubDep1 instances identified by the key DelDepIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteDelSubDep1ByDelDepIdx( ICFSecAuthorization Authorization,
		ICFBamDelDepByDelDepIdxKey argKey )
	{
		schema.getJpaHooksSchema().getDelSubDep1Service().deleteByDelDepIdx(argKey.getRequiredRelationId());
	}

	/**
	 *	Delete the DelSubDep1 instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The primary key identifying the instance to be deleted.
	 */
	@Override
	public void deleteDelSubDep1ByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argKey )
	{
		schema.getJpaHooksSchema().getDelSubDep1Service().deleteByIdIdx(argKey);
	}

	/**
	 *	Delete the DelSubDep1 instances identified by the key TenantIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TenantId	The DelSubDep1 key attribute of the instance generating the id.
	 */
	@Override
	public void deleteDelSubDep1ByTenantIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argTenantId )
	{
		schema.getJpaHooksSchema().getDelSubDep1Service().deleteByTenantIdx(argTenantId);
	}


	/**
	 *	Delete the DelSubDep1 instances identified by the key TenantIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteDelSubDep1ByTenantIdx( ICFSecAuthorization Authorization,
		ICFBamScopeByTenantIdxKey argKey )
	{
		schema.getJpaHooksSchema().getDelSubDep1Service().deleteByTenantIdx(argKey.getRequiredTenantId());
	}


	/**
	 *	Read the derived DelSubDep1 record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the DelSubDep1 instance to be read.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	@Override
	public ICFBamDelSubDep1 readDerived( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey )
	{
		return( schema.getJpaHooksSchema().getDelSubDep1Service().find(PKey) );
	}

	/**
	 *	Lock the derived DelSubDep1 record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the DelSubDep1 instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	@Override
	public ICFBamDelSubDep1 lockDerived( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey )
	{
		return( schema.getJpaHooksSchema().getDelSubDep1Service().lockByIdIdx(PKey) );
	}

	/**
	 *	Read all DelSubDep1 instances.
	 *
	 *	@param	Authorization	The session authorization information.	
	 *
	 *	@return An array of derived record instances, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamDelSubDep1[] readAllDerived( ICFSecAuthorization Authorization ) {
		List<CFBamJpaDelSubDep1> results = schema.getJpaHooksSchema().getDelSubDep1Service().findAll();
		ICFBamDelSubDep1[] retset = new ICFBamDelSubDep1[results.size()];
		int idx = 0;
		for (CFBamJpaDelSubDep1 cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read the derived DelSubDep1 record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	Id	The DelSubDep1 key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	@Override
	public ICFBamDelSubDep1 readDerivedByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argId )
	{
		return( schema.getJpaHooksSchema().getDelSubDep1Service().find(argId) );
	}

	/**
	 *	Read an array of the derived DelSubDep1 record instances identified by the duplicate key TenantIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TenantId	The DelSubDep1 key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamDelSubDep1[] readDerivedByTenantIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argTenantId )
	{
		List<CFBamJpaDelSubDep1> results = schema.getJpaHooksSchema().getDelSubDep1Service().findByTenantIdx(argTenantId);
		ICFBamDelSubDep1[] retset = new ICFBamDelSubDep1[results.size()];
		int idx = 0;
		for (CFBamJpaDelSubDep1 cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read an array of the derived DelSubDep1 record instances identified by the duplicate key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DefSchemaId	The DelSubDep1 key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamDelSubDep1[] readDerivedByDefSchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argDefSchemaId )
	{
		List<CFBamJpaDelSubDep1> results = schema.getJpaHooksSchema().getDelSubDep1Service().findByDefSchemaIdx(argDefSchemaId);
		ICFBamDelSubDep1[] retset = new ICFBamDelSubDep1[results.size()];
		int idx = 0;
		for (CFBamJpaDelSubDep1 cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read an array of the derived DelSubDep1 record instances identified by the duplicate key DelDepIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	RelationId	The DelSubDep1 key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamDelSubDep1[] readDerivedByDelDepIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argRelationId )
	{
		List<CFBamJpaDelSubDep1> results = schema.getJpaHooksSchema().getDelSubDep1Service().findByDelDepIdx(argRelationId);
		ICFBamDelSubDep1[] retset = new ICFBamDelSubDep1[results.size()];
		int idx = 0;
		for (CFBamJpaDelSubDep1 cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read an array of the derived DelSubDep1 record instances identified by the duplicate key DelTopDepIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DelTopDepId	The DelSubDep1 key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamDelSubDep1[] readDerivedByDelTopDepIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argDelTopDepId )
	{
		List<CFBamJpaDelSubDep1> results = schema.getJpaHooksSchema().getDelSubDep1Service().findByDelTopDepIdx(argDelTopDepId);
		ICFBamDelSubDep1[] retset = new ICFBamDelSubDep1[results.size()];
		int idx = 0;
		for (CFBamJpaDelSubDep1 cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read the derived DelSubDep1 record instance identified by the unique key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DelTopDepId	The DelSubDep1 key attribute of the instance generating the id.
	 *
	 *	@param	Name	The DelSubDep1 key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	@Override
	public ICFBamDelSubDep1 readDerivedByUNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argDelTopDepId,
		String argName )
	{
		return( schema.getJpaHooksSchema().getDelSubDep1Service().findByUNameIdx(argDelTopDepId,
		argName) );
	}

	/**
	 *	Read the specific DelSubDep1 record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the DelSubDep1 instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamDelSubDep1 readRec( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRec");
	}

	/**
	 *	Lock the specific DelSubDep1 record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the DelSubDep1 instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamDelSubDep1 lockRec( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey )
	{
		throw new CFLibNotImplementedYetException(getClass(), "lockRec");
	}

	/**
	 *	Read all the specific DelSubDep1 record instances.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@return All the specific DelSubDep1 instances in the database accessible for the Authorization.
	 */
	@Override
	public ICFBamDelSubDep1[] readAllRec( ICFSecAuthorization Authorization ) {
		throw new CFLibNotImplementedYetException(getClass(), "readAllRec");
	}


	/**
	 *	Read the specific DelSubDep1 record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	Id	The DelSubDep1 key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamDelSubDep1 readRecByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByIdIdx");
	}

	/**
	 *	Read an array of the specific DelSubDep1 record instances identified by the duplicate key TenantIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TenantId	The DelSubDep1 key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamDelSubDep1[] readRecByTenantIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argTenantId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByTenantIdx");
	}

	/**
	 *	Read an array of the specific DelSubDep1 record instances identified by the duplicate key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DefSchemaId	The DelSubDep1 key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamDelSubDep1[] readRecByDefSchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argDefSchemaId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByDefSchemaIdx");
	}

	/**
	 *	Read an array of the specific DelSubDep1 record instances identified by the duplicate key DelDepIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	RelationId	The DelSubDep1 key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamDelSubDep1[] readRecByDelDepIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argRelationId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByDelDepIdx");
	}

	/**
	 *	Read an array of the specific DelSubDep1 record instances identified by the duplicate key DelTopDepIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DelTopDepId	The DelSubDep1 key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamDelSubDep1[] readRecByDelTopDepIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argDelTopDepId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByDelTopDepIdx");
	}

	/**
	 *	Read the specific DelSubDep1 record instance identified by the unique key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DelTopDepId	The DelSubDep1 key attribute of the instance generating the id.
	 *
	 *	@param	Name	The DelSubDep1 key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamDelSubDep1 readRecByUNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argDelTopDepId,
		String argName )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByUNameIdx");
	}
}
