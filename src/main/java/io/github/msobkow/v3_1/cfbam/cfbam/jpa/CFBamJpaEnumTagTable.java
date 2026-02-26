
// Description: Java 25 DbIO implementation for EnumTag.

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
 *	CFBamJpaEnumTagTable database implementation for EnumTag
 */
public class CFBamJpaEnumTagTable implements ICFBamEnumTagTable
{
	protected CFBamJpaSchema schema;


	public CFBamJpaEnumTagTable(ICFBamSchema schema) {
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
	public ICFBamEnumTag createEnumTag( ICFSecAuthorization Authorization,
		ICFBamEnumTag rec )
	{
		if (rec == null) {
			throw new CFLibNullArgumentException(getClass(), "createEnumTag", 1, "rec");
		}
		else if (rec instanceof CFBamJpaEnumTag) {
			CFBamJpaEnumTag jparec = (CFBamJpaEnumTag)rec;
			CFBamJpaEnumTag created = schema.getJpaHooksSchema().getEnumTagService().create(jparec);
			return( created );
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "createEnumTag", "rec", rec, "CFBamJpaEnumTag");
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
	public ICFBamEnumTag updateEnumTag( ICFSecAuthorization Authorization,
		ICFBamEnumTag rec )
	{
		if (rec == null) {
			throw new CFLibNullArgumentException(getClass(), "updateEnumTag", 1, "rec");
		}
		else if (rec instanceof CFBamJpaEnumTag) {
			CFBamJpaEnumTag jparec = (CFBamJpaEnumTag)rec;
			CFBamJpaEnumTag updated = schema.getJpaHooksSchema().getEnumTagService().update(jparec);
			return( updated );
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "updateEnumTag", "rec", rec, "CFBamJpaEnumTag");
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
	public void deleteEnumTag( ICFSecAuthorization Authorization,
		ICFBamEnumTag rec )
	{
		if (rec == null) {
			return;
		}
		if (rec instanceof CFBamJpaEnumTag) {
			CFBamJpaEnumTag jparec = (CFBamJpaEnumTag)rec;
			schema.getJpaHooksSchema().getEnumTagService().deleteByIdIdx(jparec.getPKey());
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "deleteEnumTag", "rec", rec, "CFBamJpaEnumTag");
		}

		throw new CFLibNotImplementedYetException(getClass(), "deleteEnumTag");
	}

	/**
	 *	Delete the EnumTag instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The primary key identifying the instance to be deleted.
	 */
	@Override
	public void deleteEnumTagByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argKey )
	{
		schema.getJpaHooksSchema().getEnumTagService().deleteByIdIdx(argKey);
	}

	/**
	 *	Delete the EnumTag instances identified by the key EnumIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	EnumId	The EnumTag key attribute of the instance generating the id.
	 */
	@Override
	public void deleteEnumTagByEnumIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argEnumId )
	{
		schema.getJpaHooksSchema().getEnumTagService().deleteByEnumIdx(argEnumId);
	}


	/**
	 *	Delete the EnumTag instances identified by the key EnumIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteEnumTagByEnumIdx( ICFSecAuthorization Authorization,
		ICFBamEnumTagByEnumIdxKey argKey )
	{
		schema.getJpaHooksSchema().getEnumTagService().deleteByEnumIdx(argKey.getRequiredEnumId());
	}

	/**
	 *	Delete the EnumTag instances identified by the key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DefSchemaId	The EnumTag key attribute of the instance generating the id.
	 */
	@Override
	public void deleteEnumTagByDefSchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argDefSchemaId )
	{
		schema.getJpaHooksSchema().getEnumTagService().deleteByDefSchemaIdx(argDefSchemaId);
	}


	/**
	 *	Delete the EnumTag instances identified by the key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteEnumTagByDefSchemaIdx( ICFSecAuthorization Authorization,
		ICFBamEnumTagByDefSchemaIdxKey argKey )
	{
		schema.getJpaHooksSchema().getEnumTagService().deleteByDefSchemaIdx(argKey.getOptionalDefSchemaId());
	}

	/**
	 *	Delete the EnumTag instances identified by the key EnumNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	EnumId	The EnumTag key attribute of the instance generating the id.
	 *
	 *	@param	Name	The EnumTag key attribute of the instance generating the id.
	 */
	@Override
	public void deleteEnumTagByEnumNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argEnumId,
		String argName )
	{
		schema.getJpaHooksSchema().getEnumTagService().deleteByEnumNameIdx(argEnumId,
		argName);
	}


	/**
	 *	Delete the EnumTag instances identified by the key EnumNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteEnumTagByEnumNameIdx( ICFSecAuthorization Authorization,
		ICFBamEnumTagByEnumNameIdxKey argKey )
	{
		schema.getJpaHooksSchema().getEnumTagService().deleteByEnumNameIdx(argKey.getRequiredEnumId(),
			argKey.getRequiredName());
	}

	/**
	 *	Delete the EnumTag instances identified by the key PrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PrevId	The EnumTag key attribute of the instance generating the id.
	 */
	@Override
	public void deleteEnumTagByPrevIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argPrevId )
	{
		schema.getJpaHooksSchema().getEnumTagService().deleteByPrevIdx(argPrevId);
	}


	/**
	 *	Delete the EnumTag instances identified by the key PrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteEnumTagByPrevIdx( ICFSecAuthorization Authorization,
		ICFBamEnumTagByPrevIdxKey argKey )
	{
		schema.getJpaHooksSchema().getEnumTagService().deleteByPrevIdx(argKey.getOptionalPrevId());
	}

	/**
	 *	Delete the EnumTag instances identified by the key NextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	NextId	The EnumTag key attribute of the instance generating the id.
	 */
	@Override
	public void deleteEnumTagByNextIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argNextId )
	{
		schema.getJpaHooksSchema().getEnumTagService().deleteByNextIdx(argNextId);
	}


	/**
	 *	Delete the EnumTag instances identified by the key NextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteEnumTagByNextIdx( ICFSecAuthorization Authorization,
		ICFBamEnumTagByNextIdxKey argKey )
	{
		schema.getJpaHooksSchema().getEnumTagService().deleteByNextIdx(argKey.getOptionalNextId());
	}


	/**
	 *	Read the derived EnumTag record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the EnumTag instance to be read.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	@Override
	public ICFBamEnumTag readDerived( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey )
	{
		return( schema.getJpaHooksSchema().getEnumTagService().find(PKey) );
	}

	/**
	 *	Lock the derived EnumTag record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the EnumTag instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	@Override
	public ICFBamEnumTag lockDerived( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey )
	{
		return( schema.getJpaHooksSchema().getEnumTagService().lockByIdIdx(PKey) );
	}

	/**
	 *	Read all EnumTag instances.
	 *
	 *	@param	Authorization	The session authorization information.	
	 *
	 *	@return An array of derived record instances, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamEnumTag[] readAllDerived( ICFSecAuthorization Authorization ) {
		List<CFBamJpaEnumTag> results = schema.getJpaHooksSchema().getEnumTagService().findAll();
		ICFBamEnumTag[] retset = new ICFBamEnumTag[results.size()];
		int idx = 0;
		for (CFBamJpaEnumTag cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read the derived EnumTag record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	Id	The EnumTag key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	@Override
	public ICFBamEnumTag readDerivedByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argId )
	{
		return( schema.getJpaHooksSchema().getEnumTagService().find(argId) );
	}

	/**
	 *	Read an array of the derived EnumTag record instances identified by the duplicate key EnumIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	EnumId	The EnumTag key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamEnumTag[] readDerivedByEnumIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argEnumId )
	{
		List<CFBamJpaEnumTag> results = schema.getJpaHooksSchema().getEnumTagService().findByEnumIdx(argEnumId);
		ICFBamEnumTag[] retset = new ICFBamEnumTag[results.size()];
		int idx = 0;
		for (CFBamJpaEnumTag cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read an array of the derived EnumTag record instances identified by the duplicate key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DefSchemaId	The EnumTag key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamEnumTag[] readDerivedByDefSchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argDefSchemaId )
	{
		List<CFBamJpaEnumTag> results = schema.getJpaHooksSchema().getEnumTagService().findByDefSchemaIdx(argDefSchemaId);
		ICFBamEnumTag[] retset = new ICFBamEnumTag[results.size()];
		int idx = 0;
		for (CFBamJpaEnumTag cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read the derived EnumTag record instance identified by the unique key EnumNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	EnumId	The EnumTag key attribute of the instance generating the id.
	 *
	 *	@param	Name	The EnumTag key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	@Override
	public ICFBamEnumTag readDerivedByEnumNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argEnumId,
		String argName )
	{
		return( schema.getJpaHooksSchema().getEnumTagService().findByEnumNameIdx(argEnumId,
		argName) );
	}

	/**
	 *	Read an array of the derived EnumTag record instances identified by the duplicate key PrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PrevId	The EnumTag key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamEnumTag[] readDerivedByPrevIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argPrevId )
	{
		List<CFBamJpaEnumTag> results = schema.getJpaHooksSchema().getEnumTagService().findByPrevIdx(argPrevId);
		ICFBamEnumTag[] retset = new ICFBamEnumTag[results.size()];
		int idx = 0;
		for (CFBamJpaEnumTag cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read an array of the derived EnumTag record instances identified by the duplicate key NextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	NextId	The EnumTag key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamEnumTag[] readDerivedByNextIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argNextId )
	{
		List<CFBamJpaEnumTag> results = schema.getJpaHooksSchema().getEnumTagService().findByNextIdx(argNextId);
		ICFBamEnumTag[] retset = new ICFBamEnumTag[results.size()];
		int idx = 0;
		for (CFBamJpaEnumTag cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read the specific EnumTag record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the EnumTag instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamEnumTag readRec( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRec");
	}

	/**
	 *	Lock the specific EnumTag record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the EnumTag instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamEnumTag lockRec( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey )
	{
		throw new CFLibNotImplementedYetException(getClass(), "lockRec");
	}

	/**
	 *	Read all the specific EnumTag record instances.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@return All the specific EnumTag instances in the database accessible for the Authorization.
	 */
	@Override
	public ICFBamEnumTag[] readAllRec( ICFSecAuthorization Authorization ) {
		throw new CFLibNotImplementedYetException(getClass(), "readAllRec");
	}


	/**
	 *	Read the specific EnumTag record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	Id	The EnumTag key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamEnumTag readRecByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByIdIdx");
	}

	/**
	 *	Read an array of the specific EnumTag record instances identified by the duplicate key EnumIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	EnumId	The EnumTag key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamEnumTag[] readRecByEnumIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argEnumId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByEnumIdx");
	}

	/**
	 *	Read an array of the specific EnumTag record instances identified by the duplicate key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DefSchemaId	The EnumTag key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamEnumTag[] readRecByDefSchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argDefSchemaId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByDefSchemaIdx");
	}

	/**
	 *	Read the specific EnumTag record instance identified by the unique key EnumNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	EnumId	The EnumTag key attribute of the instance generating the id.
	 *
	 *	@param	Name	The EnumTag key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamEnumTag readRecByEnumNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argEnumId,
		String argName )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByEnumNameIdx");
	}

	/**
	 *	Read an array of the specific EnumTag record instances identified by the duplicate key PrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PrevId	The EnumTag key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamEnumTag[] readRecByPrevIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argPrevId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByPrevIdx");
	}

	/**
	 *	Read an array of the specific EnumTag record instances identified by the duplicate key NextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	NextId	The EnumTag key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamEnumTag[] readRecByNextIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argNextId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByNextIdx");
	}

	/**
	 *	Move the specified record up in the chain (i.e. to the previous position.)
	 *
	 *	@return	The refreshed record after it has been moved
	 */
	@Override
	public ICFBamEnumTag moveRecUp( ICFSecAuthorization Authorization,
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
	public ICFBamEnumTag moveRecDown( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argId,
		int revision )
	{
		throw new CFLibNotImplementedYetException(getClass(), "moveRecDown");
	}
}
