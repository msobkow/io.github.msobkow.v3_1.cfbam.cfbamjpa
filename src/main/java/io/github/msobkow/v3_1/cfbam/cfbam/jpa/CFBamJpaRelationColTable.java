
// Description: Java 25 DbIO implementation for RelationCol.

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
 *	CFBamJpaRelationColTable database implementation for RelationCol
 */
public class CFBamJpaRelationColTable implements ICFBamRelationColTable
{
	protected CFBamJpaSchema schema;


	public CFBamJpaRelationColTable(ICFBamSchema schema) {
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
	public ICFBamRelationCol createRelationCol( ICFSecAuthorization Authorization,
		ICFBamRelationCol rec )
	{
		if (rec == null) {
			throw new CFLibNullArgumentException(getClass(), "createRelationCol", 1, "rec");
		}
		else if (rec instanceof CFBamJpaRelationCol) {
			CFBamJpaRelationCol jparec = (CFBamJpaRelationCol)rec;
			CFBamJpaRelationCol created = schema.getJpaHooksSchema().getRelationColService().create(jparec);
			return( created );
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "createRelationCol", "rec", rec, "CFBamJpaRelationCol");
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
	public ICFBamRelationCol updateRelationCol( ICFSecAuthorization Authorization,
		ICFBamRelationCol rec )
	{
		if (rec == null) {
			throw new CFLibNullArgumentException(getClass(), "updateRelationCol", 1, "rec");
		}
		else if (rec instanceof CFBamJpaRelationCol) {
			CFBamJpaRelationCol jparec = (CFBamJpaRelationCol)rec;
			CFBamJpaRelationCol updated = schema.getJpaHooksSchema().getRelationColService().update(jparec);
			return( updated );
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "updateRelationCol", "rec", rec, "CFBamJpaRelationCol");
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
	public void deleteRelationCol( ICFSecAuthorization Authorization,
		ICFBamRelationCol rec )
	{
		if (rec == null) {
			return;
		}
		if (rec instanceof CFBamJpaRelationCol) {
			CFBamJpaRelationCol jparec = (CFBamJpaRelationCol)rec;
			schema.getJpaHooksSchema().getRelationColService().deleteByIdIdx(jparec.getPKey());
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "deleteRelationCol", "rec", rec, "CFBamJpaRelationCol");
		}

		throw new CFLibNotImplementedYetException(getClass(), "deleteRelationCol");
	}

	/**
	 *	Delete the RelationCol instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The primary key identifying the instance to be deleted.
	 */
	@Override
	public void deleteRelationColByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argKey )
	{
		schema.getJpaHooksSchema().getRelationColService().deleteByIdIdx(argKey);
	}

	/**
	 *	Delete the RelationCol instances identified by the key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	RelationId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@param	Name	The RelationCol key attribute of the instance generating the id.
	 */
	@Override
	public void deleteRelationColByUNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argRelationId,
		String argName )
	{
		schema.getJpaHooksSchema().getRelationColService().deleteByUNameIdx(argRelationId,
		argName);
	}


	/**
	 *	Delete the RelationCol instances identified by the key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteRelationColByUNameIdx( ICFSecAuthorization Authorization,
		ICFBamRelationColByUNameIdxKey argKey )
	{
		schema.getJpaHooksSchema().getRelationColService().deleteByUNameIdx(argKey.getRequiredRelationId(),
			argKey.getRequiredName());
	}

	/**
	 *	Delete the RelationCol instances identified by the key RelationIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	RelationId	The RelationCol key attribute of the instance generating the id.
	 */
	@Override
	public void deleteRelationColByRelationIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argRelationId )
	{
		schema.getJpaHooksSchema().getRelationColService().deleteByRelationIdx(argRelationId);
	}


	/**
	 *	Delete the RelationCol instances identified by the key RelationIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteRelationColByRelationIdx( ICFSecAuthorization Authorization,
		ICFBamRelationColByRelationIdxKey argKey )
	{
		schema.getJpaHooksSchema().getRelationColService().deleteByRelationIdx(argKey.getRequiredRelationId());
	}

	/**
	 *	Delete the RelationCol instances identified by the key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DefSchemaId	The RelationCol key attribute of the instance generating the id.
	 */
	@Override
	public void deleteRelationColByDefSchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argDefSchemaId )
	{
		schema.getJpaHooksSchema().getRelationColService().deleteByDefSchemaIdx(argDefSchemaId);
	}


	/**
	 *	Delete the RelationCol instances identified by the key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteRelationColByDefSchemaIdx( ICFSecAuthorization Authorization,
		ICFBamRelationColByDefSchemaIdxKey argKey )
	{
		schema.getJpaHooksSchema().getRelationColService().deleteByDefSchemaIdx(argKey.getOptionalDefSchemaId());
	}

	/**
	 *	Delete the RelationCol instances identified by the key FromColIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	FromColId	The RelationCol key attribute of the instance generating the id.
	 */
	@Override
	public void deleteRelationColByFromColIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argFromColId )
	{
		schema.getJpaHooksSchema().getRelationColService().deleteByFromColIdx(argFromColId);
	}


	/**
	 *	Delete the RelationCol instances identified by the key FromColIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteRelationColByFromColIdx( ICFSecAuthorization Authorization,
		ICFBamRelationColByFromColIdxKey argKey )
	{
		schema.getJpaHooksSchema().getRelationColService().deleteByFromColIdx(argKey.getRequiredFromColId());
	}

	/**
	 *	Delete the RelationCol instances identified by the key ToColIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ToColId	The RelationCol key attribute of the instance generating the id.
	 */
	@Override
	public void deleteRelationColByToColIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argToColId )
	{
		schema.getJpaHooksSchema().getRelationColService().deleteByToColIdx(argToColId);
	}


	/**
	 *	Delete the RelationCol instances identified by the key ToColIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteRelationColByToColIdx( ICFSecAuthorization Authorization,
		ICFBamRelationColByToColIdxKey argKey )
	{
		schema.getJpaHooksSchema().getRelationColService().deleteByToColIdx(argKey.getRequiredToColId());
	}

	/**
	 *	Delete the RelationCol instances identified by the key PrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PrevId	The RelationCol key attribute of the instance generating the id.
	 */
	@Override
	public void deleteRelationColByPrevIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argPrevId )
	{
		schema.getJpaHooksSchema().getRelationColService().deleteByPrevIdx(argPrevId);
	}


	/**
	 *	Delete the RelationCol instances identified by the key PrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteRelationColByPrevIdx( ICFSecAuthorization Authorization,
		ICFBamRelationColByPrevIdxKey argKey )
	{
		schema.getJpaHooksSchema().getRelationColService().deleteByPrevIdx(argKey.getOptionalPrevId());
	}

	/**
	 *	Delete the RelationCol instances identified by the key NextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	NextId	The RelationCol key attribute of the instance generating the id.
	 */
	@Override
	public void deleteRelationColByNextIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argNextId )
	{
		schema.getJpaHooksSchema().getRelationColService().deleteByNextIdx(argNextId);
	}


	/**
	 *	Delete the RelationCol instances identified by the key NextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteRelationColByNextIdx( ICFSecAuthorization Authorization,
		ICFBamRelationColByNextIdxKey argKey )
	{
		schema.getJpaHooksSchema().getRelationColService().deleteByNextIdx(argKey.getOptionalNextId());
	}

	/**
	 *	Delete the RelationCol instances identified by the key RelPrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	RelationId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The RelationCol key attribute of the instance generating the id.
	 */
	@Override
	public void deleteRelationColByRelPrevIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argRelationId,
		CFLibDbKeyHash256 argPrevId )
	{
		schema.getJpaHooksSchema().getRelationColService().deleteByRelPrevIdx(argRelationId,
		argPrevId);
	}


	/**
	 *	Delete the RelationCol instances identified by the key RelPrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteRelationColByRelPrevIdx( ICFSecAuthorization Authorization,
		ICFBamRelationColByRelPrevIdxKey argKey )
	{
		schema.getJpaHooksSchema().getRelationColService().deleteByRelPrevIdx(argKey.getRequiredRelationId(),
			argKey.getOptionalPrevId());
	}

	/**
	 *	Delete the RelationCol instances identified by the key RelNextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	RelationId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The RelationCol key attribute of the instance generating the id.
	 */
	@Override
	public void deleteRelationColByRelNextIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argRelationId,
		CFLibDbKeyHash256 argNextId )
	{
		schema.getJpaHooksSchema().getRelationColService().deleteByRelNextIdx(argRelationId,
		argNextId);
	}


	/**
	 *	Delete the RelationCol instances identified by the key RelNextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteRelationColByRelNextIdx( ICFSecAuthorization Authorization,
		ICFBamRelationColByRelNextIdxKey argKey )
	{
		schema.getJpaHooksSchema().getRelationColService().deleteByRelNextIdx(argKey.getRequiredRelationId(),
			argKey.getOptionalNextId());
	}


	/**
	 *	Read the derived RelationCol record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the RelationCol instance to be read.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	@Override
	public ICFBamRelationCol readDerived( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey )
	{
		return( schema.getJpaHooksSchema().getRelationColService().find(PKey) );
	}

	/**
	 *	Lock the derived RelationCol record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the RelationCol instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	@Override
	public ICFBamRelationCol lockDerived( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey )
	{
		return( schema.getJpaHooksSchema().getRelationColService().lockByIdIdx(PKey) );
	}

	/**
	 *	Read all RelationCol instances.
	 *
	 *	@param	Authorization	The session authorization information.	
	 *
	 *	@return An array of derived record instances, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamRelationCol[] readAllDerived( ICFSecAuthorization Authorization ) {
		List<CFBamJpaRelationCol> results = schema.getJpaHooksSchema().getRelationColService().findAll();
		ICFBamRelationCol[] retset = new ICFBamRelationCol[results.size()];
		int idx = 0;
		for (CFBamJpaRelationCol cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read the derived RelationCol record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	Id	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	@Override
	public ICFBamRelationCol readDerivedByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argId )
	{
		return( schema.getJpaHooksSchema().getRelationColService().find(argId) );
	}

	/**
	 *	Read the derived RelationCol record instance identified by the unique key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	RelationId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@param	Name	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	@Override
	public ICFBamRelationCol readDerivedByUNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argRelationId,
		String argName )
	{
		return( schema.getJpaHooksSchema().getRelationColService().findByUNameIdx(argRelationId,
		argName) );
	}

	/**
	 *	Read an array of the derived RelationCol record instances identified by the duplicate key RelationIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	RelationId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamRelationCol[] readDerivedByRelationIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argRelationId )
	{
		List<CFBamJpaRelationCol> results = schema.getJpaHooksSchema().getRelationColService().findByRelationIdx(argRelationId);
		ICFBamRelationCol[] retset = new ICFBamRelationCol[results.size()];
		int idx = 0;
		for (CFBamJpaRelationCol cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read an array of the derived RelationCol record instances identified by the duplicate key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DefSchemaId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamRelationCol[] readDerivedByDefSchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argDefSchemaId )
	{
		List<CFBamJpaRelationCol> results = schema.getJpaHooksSchema().getRelationColService().findByDefSchemaIdx(argDefSchemaId);
		ICFBamRelationCol[] retset = new ICFBamRelationCol[results.size()];
		int idx = 0;
		for (CFBamJpaRelationCol cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read an array of the derived RelationCol record instances identified by the duplicate key FromColIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	FromColId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamRelationCol[] readDerivedByFromColIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argFromColId )
	{
		List<CFBamJpaRelationCol> results = schema.getJpaHooksSchema().getRelationColService().findByFromColIdx(argFromColId);
		ICFBamRelationCol[] retset = new ICFBamRelationCol[results.size()];
		int idx = 0;
		for (CFBamJpaRelationCol cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read an array of the derived RelationCol record instances identified by the duplicate key ToColIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ToColId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamRelationCol[] readDerivedByToColIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argToColId )
	{
		List<CFBamJpaRelationCol> results = schema.getJpaHooksSchema().getRelationColService().findByToColIdx(argToColId);
		ICFBamRelationCol[] retset = new ICFBamRelationCol[results.size()];
		int idx = 0;
		for (CFBamJpaRelationCol cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read an array of the derived RelationCol record instances identified by the duplicate key PrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PrevId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamRelationCol[] readDerivedByPrevIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argPrevId )
	{
		List<CFBamJpaRelationCol> results = schema.getJpaHooksSchema().getRelationColService().findByPrevIdx(argPrevId);
		ICFBamRelationCol[] retset = new ICFBamRelationCol[results.size()];
		int idx = 0;
		for (CFBamJpaRelationCol cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read an array of the derived RelationCol record instances identified by the duplicate key NextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	NextId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamRelationCol[] readDerivedByNextIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argNextId )
	{
		List<CFBamJpaRelationCol> results = schema.getJpaHooksSchema().getRelationColService().findByNextIdx(argNextId);
		ICFBamRelationCol[] retset = new ICFBamRelationCol[results.size()];
		int idx = 0;
		for (CFBamJpaRelationCol cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read an array of the derived RelationCol record instances identified by the duplicate key RelPrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	RelationId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamRelationCol[] readDerivedByRelPrevIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argRelationId,
		CFLibDbKeyHash256 argPrevId )
	{
		List<CFBamJpaRelationCol> results = schema.getJpaHooksSchema().getRelationColService().findByRelPrevIdx(argRelationId,
		argPrevId);
		ICFBamRelationCol[] retset = new ICFBamRelationCol[results.size()];
		int idx = 0;
		for (CFBamJpaRelationCol cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read an array of the derived RelationCol record instances identified by the duplicate key RelNextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	RelationId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamRelationCol[] readDerivedByRelNextIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argRelationId,
		CFLibDbKeyHash256 argNextId )
	{
		List<CFBamJpaRelationCol> results = schema.getJpaHooksSchema().getRelationColService().findByRelNextIdx(argRelationId,
		argNextId);
		ICFBamRelationCol[] retset = new ICFBamRelationCol[results.size()];
		int idx = 0;
		for (CFBamJpaRelationCol cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read the specific RelationCol record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the RelationCol instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamRelationCol readRec( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRec");
	}

	/**
	 *	Lock the specific RelationCol record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the RelationCol instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamRelationCol lockRec( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey )
	{
		throw new CFLibNotImplementedYetException(getClass(), "lockRec");
	}

	/**
	 *	Read all the specific RelationCol record instances.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@return All the specific RelationCol instances in the database accessible for the Authorization.
	 */
	@Override
	public ICFBamRelationCol[] readAllRec( ICFSecAuthorization Authorization ) {
		throw new CFLibNotImplementedYetException(getClass(), "readAllRec");
	}


	/**
	 *	Read the specific RelationCol record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	Id	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamRelationCol readRecByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByIdIdx");
	}

	/**
	 *	Read the specific RelationCol record instance identified by the unique key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	RelationId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@param	Name	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamRelationCol readRecByUNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argRelationId,
		String argName )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByUNameIdx");
	}

	/**
	 *	Read an array of the specific RelationCol record instances identified by the duplicate key RelationIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	RelationId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamRelationCol[] readRecByRelationIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argRelationId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByRelationIdx");
	}

	/**
	 *	Read an array of the specific RelationCol record instances identified by the duplicate key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DefSchemaId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamRelationCol[] readRecByDefSchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argDefSchemaId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByDefSchemaIdx");
	}

	/**
	 *	Read an array of the specific RelationCol record instances identified by the duplicate key FromColIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	FromColId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamRelationCol[] readRecByFromColIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argFromColId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByFromColIdx");
	}

	/**
	 *	Read an array of the specific RelationCol record instances identified by the duplicate key ToColIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ToColId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamRelationCol[] readRecByToColIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argToColId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByToColIdx");
	}

	/**
	 *	Read an array of the specific RelationCol record instances identified by the duplicate key PrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PrevId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamRelationCol[] readRecByPrevIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argPrevId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByPrevIdx");
	}

	/**
	 *	Read an array of the specific RelationCol record instances identified by the duplicate key NextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	NextId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamRelationCol[] readRecByNextIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argNextId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByNextIdx");
	}

	/**
	 *	Read an array of the specific RelationCol record instances identified by the duplicate key RelPrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	RelationId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamRelationCol[] readRecByRelPrevIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argRelationId,
		CFLibDbKeyHash256 argPrevId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByRelPrevIdx");
	}

	/**
	 *	Read an array of the specific RelationCol record instances identified by the duplicate key RelNextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	RelationId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamRelationCol[] readRecByRelNextIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argRelationId,
		CFLibDbKeyHash256 argNextId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByRelNextIdx");
	}

	/**
	 *	Move the specified record up in the chain (i.e. to the previous position.)
	 *
	 *	@return	The refreshed record after it has been moved
	 */
	@Override
	public ICFBamRelationCol moveRecUp( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argId,
		int revision )
	{
		throw new CFLibNotImplementedYetException(getClass(), "moveRecUp");
	}

	/**
	 *	Move the specified record down in the chain (i.e. to the next position.)
	 *
	 *	@return	The refreshed record after it has been moved
	 */
	@Override
	public ICFBamRelationCol moveRecDown( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argId,
		int revision )
	{
		throw new CFLibNotImplementedYetException(getClass(), "moveRecDown");
	}
}
