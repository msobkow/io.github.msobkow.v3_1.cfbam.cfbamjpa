
// Description: Java 25 DbIO implementation for IndexCol.

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
 *	CFBamJpaIndexColTable database implementation for IndexCol
 */
public class CFBamJpaIndexColTable implements ICFBamIndexColTable
{
	protected CFBamJpaSchema schema;


	public CFBamJpaIndexColTable(ICFBamSchema schema) {
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
	public ICFBamIndexCol createIndexCol( ICFSecAuthorization Authorization,
		ICFBamIndexCol rec )
	{
		if (rec == null) {
			throw new CFLibNullArgumentException(getClass(), "createIndexCol", 1, "rec");
		}
		else if (rec instanceof CFBamJpaIndexCol) {
			CFBamJpaIndexCol jparec = (CFBamJpaIndexCol)rec;
			CFBamJpaIndexCol created = schema.getJpaHooksSchema().getIndexColService().create(jparec);
			return( created );
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "createIndexCol", "rec", rec, "CFBamJpaIndexCol");
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
	public ICFBamIndexCol updateIndexCol( ICFSecAuthorization Authorization,
		ICFBamIndexCol rec )
	{
		if (rec == null) {
			throw new CFLibNullArgumentException(getClass(), "updateIndexCol", 1, "rec");
		}
		else if (rec instanceof CFBamJpaIndexCol) {
			CFBamJpaIndexCol jparec = (CFBamJpaIndexCol)rec;
			CFBamJpaIndexCol updated = schema.getJpaHooksSchema().getIndexColService().update(jparec);
			return( updated );
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "updateIndexCol", "rec", rec, "CFBamJpaIndexCol");
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
	public void deleteIndexCol( ICFSecAuthorization Authorization,
		ICFBamIndexCol rec )
	{
		if (rec == null) {
			return;
		}
		if (rec instanceof CFBamJpaIndexCol) {
			CFBamJpaIndexCol jparec = (CFBamJpaIndexCol)rec;
			schema.getJpaHooksSchema().getIndexColService().deleteByIdIdx(jparec.getPKey());
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "deleteIndexCol", "rec", rec, "CFBamJpaIndexCol");
		}

		throw new CFLibNotImplementedYetException(getClass(), "deleteIndexCol");
	}

	/**
	 *	Delete the IndexCol instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The primary key identifying the instance to be deleted.
	 */
	@Override
	public void deleteIndexColByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argKey )
	{
		schema.getJpaHooksSchema().getIndexColService().deleteByIdIdx(argKey);
	}

	/**
	 *	Delete the IndexCol instances identified by the key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	IndexId	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@param	Name	The IndexCol key attribute of the instance generating the id.
	 */
	@Override
	public void deleteIndexColByUNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argIndexId,
		String argName )
	{
		schema.getJpaHooksSchema().getIndexColService().deleteByUNameIdx(argIndexId,
		argName);
	}


	/**
	 *	Delete the IndexCol instances identified by the key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteIndexColByUNameIdx( ICFSecAuthorization Authorization,
		ICFBamIndexColByUNameIdxKey argKey )
	{
		schema.getJpaHooksSchema().getIndexColService().deleteByUNameIdx(argKey.getRequiredIndexId(),
			argKey.getRequiredName());
	}

	/**
	 *	Delete the IndexCol instances identified by the key IndexIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	IndexId	The IndexCol key attribute of the instance generating the id.
	 */
	@Override
	public void deleteIndexColByIndexIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argIndexId )
	{
		schema.getJpaHooksSchema().getIndexColService().deleteByIndexIdx(argIndexId);
	}


	/**
	 *	Delete the IndexCol instances identified by the key IndexIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteIndexColByIndexIdx( ICFSecAuthorization Authorization,
		ICFBamIndexColByIndexIdxKey argKey )
	{
		schema.getJpaHooksSchema().getIndexColService().deleteByIndexIdx(argKey.getRequiredIndexId());
	}

	/**
	 *	Delete the IndexCol instances identified by the key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DefSchemaId	The IndexCol key attribute of the instance generating the id.
	 */
	@Override
	public void deleteIndexColByDefSchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argDefSchemaId )
	{
		schema.getJpaHooksSchema().getIndexColService().deleteByDefSchemaIdx(argDefSchemaId);
	}


	/**
	 *	Delete the IndexCol instances identified by the key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteIndexColByDefSchemaIdx( ICFSecAuthorization Authorization,
		ICFBamIndexColByDefSchemaIdxKey argKey )
	{
		schema.getJpaHooksSchema().getIndexColService().deleteByDefSchemaIdx(argKey.getOptionalDefSchemaId());
	}

	/**
	 *	Delete the IndexCol instances identified by the key ColIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ColumnId	The IndexCol key attribute of the instance generating the id.
	 */
	@Override
	public void deleteIndexColByColIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argColumnId )
	{
		schema.getJpaHooksSchema().getIndexColService().deleteByColIdx(argColumnId);
	}


	/**
	 *	Delete the IndexCol instances identified by the key ColIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteIndexColByColIdx( ICFSecAuthorization Authorization,
		ICFBamIndexColByColIdxKey argKey )
	{
		schema.getJpaHooksSchema().getIndexColService().deleteByColIdx(argKey.getRequiredColumnId());
	}

	/**
	 *	Delete the IndexCol instances identified by the key PrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PrevId	The IndexCol key attribute of the instance generating the id.
	 */
	@Override
	public void deleteIndexColByPrevIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argPrevId )
	{
		schema.getJpaHooksSchema().getIndexColService().deleteByPrevIdx(argPrevId);
	}


	/**
	 *	Delete the IndexCol instances identified by the key PrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteIndexColByPrevIdx( ICFSecAuthorization Authorization,
		ICFBamIndexColByPrevIdxKey argKey )
	{
		schema.getJpaHooksSchema().getIndexColService().deleteByPrevIdx(argKey.getOptionalPrevId());
	}

	/**
	 *	Delete the IndexCol instances identified by the key NextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	NextId	The IndexCol key attribute of the instance generating the id.
	 */
	@Override
	public void deleteIndexColByNextIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argNextId )
	{
		schema.getJpaHooksSchema().getIndexColService().deleteByNextIdx(argNextId);
	}


	/**
	 *	Delete the IndexCol instances identified by the key NextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteIndexColByNextIdx( ICFSecAuthorization Authorization,
		ICFBamIndexColByNextIdxKey argKey )
	{
		schema.getJpaHooksSchema().getIndexColService().deleteByNextIdx(argKey.getOptionalNextId());
	}

	/**
	 *	Delete the IndexCol instances identified by the key IdxPrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	IndexId	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The IndexCol key attribute of the instance generating the id.
	 */
	@Override
	public void deleteIndexColByIdxPrevIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argIndexId,
		CFLibDbKeyHash256 argPrevId )
	{
		schema.getJpaHooksSchema().getIndexColService().deleteByIdxPrevIdx(argIndexId,
		argPrevId);
	}


	/**
	 *	Delete the IndexCol instances identified by the key IdxPrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteIndexColByIdxPrevIdx( ICFSecAuthorization Authorization,
		ICFBamIndexColByIdxPrevIdxKey argKey )
	{
		schema.getJpaHooksSchema().getIndexColService().deleteByIdxPrevIdx(argKey.getRequiredIndexId(),
			argKey.getOptionalPrevId());
	}

	/**
	 *	Delete the IndexCol instances identified by the key IdxNextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	IndexId	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The IndexCol key attribute of the instance generating the id.
	 */
	@Override
	public void deleteIndexColByIdxNextIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argIndexId,
		CFLibDbKeyHash256 argNextId )
	{
		schema.getJpaHooksSchema().getIndexColService().deleteByIdxNextIdx(argIndexId,
		argNextId);
	}


	/**
	 *	Delete the IndexCol instances identified by the key IdxNextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteIndexColByIdxNextIdx( ICFSecAuthorization Authorization,
		ICFBamIndexColByIdxNextIdxKey argKey )
	{
		schema.getJpaHooksSchema().getIndexColService().deleteByIdxNextIdx(argKey.getRequiredIndexId(),
			argKey.getOptionalNextId());
	}


	/**
	 *	Read the derived IndexCol record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the IndexCol instance to be read.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	@Override
	public ICFBamIndexCol readDerived( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey )
	{
		return( schema.getJpaHooksSchema().getIndexColService().find(PKey) );
	}

	/**
	 *	Lock the derived IndexCol record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the IndexCol instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	@Override
	public ICFBamIndexCol lockDerived( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey )
	{
		return( schema.getJpaHooksSchema().getIndexColService().lockByIdIdx(PKey) );
	}

	/**
	 *	Read all IndexCol instances.
	 *
	 *	@param	Authorization	The session authorization information.	
	 *
	 *	@return An array of derived record instances, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamIndexCol[] readAllDerived( ICFSecAuthorization Authorization ) {
		List<CFBamJpaIndexCol> results = schema.getJpaHooksSchema().getIndexColService().findAll();
		ICFBamIndexCol[] retset = new ICFBamIndexCol[results.size()];
		int idx = 0;
		for (CFBamJpaIndexCol cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read the derived IndexCol record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	Id	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	@Override
	public ICFBamIndexCol readDerivedByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argId )
	{
		return( schema.getJpaHooksSchema().getIndexColService().find(argId) );
	}

	/**
	 *	Read the derived IndexCol record instance identified by the unique key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	IndexId	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@param	Name	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	@Override
	public ICFBamIndexCol readDerivedByUNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argIndexId,
		String argName )
	{
		return( schema.getJpaHooksSchema().getIndexColService().findByUNameIdx(argIndexId,
		argName) );
	}

	/**
	 *	Read an array of the derived IndexCol record instances identified by the duplicate key IndexIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	IndexId	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamIndexCol[] readDerivedByIndexIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argIndexId )
	{
		List<CFBamJpaIndexCol> results = schema.getJpaHooksSchema().getIndexColService().findByIndexIdx(argIndexId);
		ICFBamIndexCol[] retset = new ICFBamIndexCol[results.size()];
		int idx = 0;
		for (CFBamJpaIndexCol cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read an array of the derived IndexCol record instances identified by the duplicate key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DefSchemaId	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamIndexCol[] readDerivedByDefSchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argDefSchemaId )
	{
		List<CFBamJpaIndexCol> results = schema.getJpaHooksSchema().getIndexColService().findByDefSchemaIdx(argDefSchemaId);
		ICFBamIndexCol[] retset = new ICFBamIndexCol[results.size()];
		int idx = 0;
		for (CFBamJpaIndexCol cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read an array of the derived IndexCol record instances identified by the duplicate key ColIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ColumnId	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamIndexCol[] readDerivedByColIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argColumnId )
	{
		List<CFBamJpaIndexCol> results = schema.getJpaHooksSchema().getIndexColService().findByColIdx(argColumnId);
		ICFBamIndexCol[] retset = new ICFBamIndexCol[results.size()];
		int idx = 0;
		for (CFBamJpaIndexCol cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read an array of the derived IndexCol record instances identified by the duplicate key PrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PrevId	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamIndexCol[] readDerivedByPrevIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argPrevId )
	{
		List<CFBamJpaIndexCol> results = schema.getJpaHooksSchema().getIndexColService().findByPrevIdx(argPrevId);
		ICFBamIndexCol[] retset = new ICFBamIndexCol[results.size()];
		int idx = 0;
		for (CFBamJpaIndexCol cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read an array of the derived IndexCol record instances identified by the duplicate key NextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	NextId	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamIndexCol[] readDerivedByNextIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argNextId )
	{
		List<CFBamJpaIndexCol> results = schema.getJpaHooksSchema().getIndexColService().findByNextIdx(argNextId);
		ICFBamIndexCol[] retset = new ICFBamIndexCol[results.size()];
		int idx = 0;
		for (CFBamJpaIndexCol cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read an array of the derived IndexCol record instances identified by the duplicate key IdxPrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	IndexId	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamIndexCol[] readDerivedByIdxPrevIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argIndexId,
		CFLibDbKeyHash256 argPrevId )
	{
		List<CFBamJpaIndexCol> results = schema.getJpaHooksSchema().getIndexColService().findByIdxPrevIdx(argIndexId,
		argPrevId);
		ICFBamIndexCol[] retset = new ICFBamIndexCol[results.size()];
		int idx = 0;
		for (CFBamJpaIndexCol cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read an array of the derived IndexCol record instances identified by the duplicate key IdxNextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	IndexId	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamIndexCol[] readDerivedByIdxNextIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argIndexId,
		CFLibDbKeyHash256 argNextId )
	{
		List<CFBamJpaIndexCol> results = schema.getJpaHooksSchema().getIndexColService().findByIdxNextIdx(argIndexId,
		argNextId);
		ICFBamIndexCol[] retset = new ICFBamIndexCol[results.size()];
		int idx = 0;
		for (CFBamJpaIndexCol cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read the specific IndexCol record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the IndexCol instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamIndexCol readRec( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRec");
	}

	/**
	 *	Lock the specific IndexCol record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the IndexCol instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamIndexCol lockRec( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey )
	{
		throw new CFLibNotImplementedYetException(getClass(), "lockRec");
	}

	/**
	 *	Read all the specific IndexCol record instances.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@return All the specific IndexCol instances in the database accessible for the Authorization.
	 */
	@Override
	public ICFBamIndexCol[] readAllRec( ICFSecAuthorization Authorization ) {
		throw new CFLibNotImplementedYetException(getClass(), "readAllRec");
	}


	/**
	 *	Read the specific IndexCol record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	Id	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamIndexCol readRecByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByIdIdx");
	}

	/**
	 *	Read the specific IndexCol record instance identified by the unique key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	IndexId	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@param	Name	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamIndexCol readRecByUNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argIndexId,
		String argName )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByUNameIdx");
	}

	/**
	 *	Read an array of the specific IndexCol record instances identified by the duplicate key IndexIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	IndexId	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamIndexCol[] readRecByIndexIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argIndexId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByIndexIdx");
	}

	/**
	 *	Read an array of the specific IndexCol record instances identified by the duplicate key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DefSchemaId	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamIndexCol[] readRecByDefSchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argDefSchemaId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByDefSchemaIdx");
	}

	/**
	 *	Read an array of the specific IndexCol record instances identified by the duplicate key ColIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ColumnId	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamIndexCol[] readRecByColIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argColumnId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByColIdx");
	}

	/**
	 *	Read an array of the specific IndexCol record instances identified by the duplicate key PrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PrevId	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamIndexCol[] readRecByPrevIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argPrevId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByPrevIdx");
	}

	/**
	 *	Read an array of the specific IndexCol record instances identified by the duplicate key NextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	NextId	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamIndexCol[] readRecByNextIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argNextId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByNextIdx");
	}

	/**
	 *	Read an array of the specific IndexCol record instances identified by the duplicate key IdxPrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	IndexId	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamIndexCol[] readRecByIdxPrevIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argIndexId,
		CFLibDbKeyHash256 argPrevId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByIdxPrevIdx");
	}

	/**
	 *	Read an array of the specific IndexCol record instances identified by the duplicate key IdxNextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	IndexId	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The IndexCol key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamIndexCol[] readRecByIdxNextIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argIndexId,
		CFLibDbKeyHash256 argNextId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByIdxNextIdx");
	}

	/**
	 *	Move the specified record up in the chain (i.e. to the previous position.)
	 *
	 *	@return	The refreshed record after it has been moved
	 */
	@Override
	public ICFBamIndexCol moveRecUp( ICFSecAuthorization Authorization,
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
	public ICFBamIndexCol moveRecDown( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argId,
		int revision )
	{
		throw new CFLibNotImplementedYetException(getClass(), "moveRecDown");
	}
}
