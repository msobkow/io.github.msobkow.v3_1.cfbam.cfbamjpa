
// Description: Java 25 DbIO implementation for SchemaDef.

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
 *	CFBamJpaSchemaDefTable database implementation for SchemaDef
 */
public class CFBamJpaSchemaDefTable implements ICFBamSchemaDefTable
{
	protected CFBamJpaSchema schema;


	public CFBamJpaSchemaDefTable(ICFBamSchema schema) {
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
	public ICFBamSchemaDef createSchemaDef( ICFSecAuthorization Authorization,
		ICFBamSchemaDef rec )
	{
		if (rec == null) {
			throw new CFLibNullArgumentException(getClass(), "createSchemaDef", 1, "rec");
		}
		else if (rec instanceof CFBamJpaSchemaDef) {
			CFBamJpaSchemaDef jparec = (CFBamJpaSchemaDef)rec;
			CFBamJpaSchemaDef created = schema.getJpaHooksSchema().getSchemaDefService().create(jparec);
			return( created );
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "createSchemaDef", "rec", rec, "CFBamJpaSchemaDef");
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
	public ICFBamSchemaDef updateSchemaDef( ICFSecAuthorization Authorization,
		ICFBamSchemaDef rec )
	{
		if (rec == null) {
			throw new CFLibNullArgumentException(getClass(), "updateSchemaDef", 1, "rec");
		}
		else if (rec instanceof CFBamJpaSchemaDef) {
			CFBamJpaSchemaDef jparec = (CFBamJpaSchemaDef)rec;
			CFBamJpaSchemaDef updated = schema.getJpaHooksSchema().getSchemaDefService().update(jparec);
			return( updated );
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "updateSchemaDef", "rec", rec, "CFBamJpaSchemaDef");
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
	public void deleteSchemaDef( ICFSecAuthorization Authorization,
		ICFBamSchemaDef rec )
	{
		if (rec == null) {
			return;
		}
		if (rec instanceof CFBamJpaSchemaDef) {
			CFBamJpaSchemaDef jparec = (CFBamJpaSchemaDef)rec;
			schema.getJpaHooksSchema().getSchemaDefService().deleteByIdIdx(jparec.getPKey());
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "deleteSchemaDef", "rec", rec, "CFBamJpaSchemaDef");
		}

		throw new CFLibNotImplementedYetException(getClass(), "deleteSchemaDef");
	}

	/**
	 *	Delete the SchemaDef instances identified by the key CTenantIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	CTenantId	The SchemaDef key attribute of the instance generating the id.
	 */
	@Override
	public void deleteSchemaDefByCTenantIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argCTenantId )
	{
		schema.getJpaHooksSchema().getSchemaDefService().deleteByCTenantIdx(argCTenantId);
	}


	/**
	 *	Delete the SchemaDef instances identified by the key CTenantIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteSchemaDefByCTenantIdx( ICFSecAuthorization Authorization,
		ICFBamSchemaDefByCTenantIdxKey argKey )
	{
		schema.getJpaHooksSchema().getSchemaDefService().deleteByCTenantIdx(argKey.getRequiredCTenantId());
	}

	/**
	 *	Delete the SchemaDef instances identified by the key MinorVersionIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	MinorVersionId	The SchemaDef key attribute of the instance generating the id.
	 */
	@Override
	public void deleteSchemaDefByMinorVersionIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argMinorVersionId )
	{
		schema.getJpaHooksSchema().getSchemaDefService().deleteByMinorVersionIdx(argMinorVersionId);
	}


	/**
	 *	Delete the SchemaDef instances identified by the key MinorVersionIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteSchemaDefByMinorVersionIdx( ICFSecAuthorization Authorization,
		ICFBamSchemaDefByMinorVersionIdxKey argKey )
	{
		schema.getJpaHooksSchema().getSchemaDefService().deleteByMinorVersionIdx(argKey.getRequiredMinorVersionId());
	}

	/**
	 *	Delete the SchemaDef instances identified by the key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	MinorVersionId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@param	Name	The SchemaDef key attribute of the instance generating the id.
	 */
	@Override
	public void deleteSchemaDefByUNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argMinorVersionId,
		String argName )
	{
		schema.getJpaHooksSchema().getSchemaDefService().deleteByUNameIdx(argMinorVersionId,
		argName);
	}


	/**
	 *	Delete the SchemaDef instances identified by the key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteSchemaDefByUNameIdx( ICFSecAuthorization Authorization,
		ICFBamSchemaDefByUNameIdxKey argKey )
	{
		schema.getJpaHooksSchema().getSchemaDefService().deleteByUNameIdx(argKey.getRequiredMinorVersionId(),
			argKey.getRequiredName());
	}

	/**
	 *	Delete the SchemaDef instances identified by the key AuthEMailIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	CTenantId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@param	AuthorEMail	The SchemaDef key attribute of the instance generating the id.
	 */
	@Override
	public void deleteSchemaDefByAuthEMailIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argCTenantId,
		String argAuthorEMail )
	{
		schema.getJpaHooksSchema().getSchemaDefService().deleteByAuthEMailIdx(argCTenantId,
		argAuthorEMail);
	}


	/**
	 *	Delete the SchemaDef instances identified by the key AuthEMailIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteSchemaDefByAuthEMailIdx( ICFSecAuthorization Authorization,
		ICFBamSchemaDefByAuthEMailIdxKey argKey )
	{
		schema.getJpaHooksSchema().getSchemaDefService().deleteByAuthEMailIdx(argKey.getRequiredCTenantId(),
			argKey.getRequiredAuthorEMail());
	}

	/**
	 *	Delete the SchemaDef instances identified by the key ProjectURLIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	CTenantId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@param	ProjectURL	The SchemaDef key attribute of the instance generating the id.
	 */
	@Override
	public void deleteSchemaDefByProjectURLIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argCTenantId,
		String argProjectURL )
	{
		schema.getJpaHooksSchema().getSchemaDefService().deleteByProjectURLIdx(argCTenantId,
		argProjectURL);
	}


	/**
	 *	Delete the SchemaDef instances identified by the key ProjectURLIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteSchemaDefByProjectURLIdx( ICFSecAuthorization Authorization,
		ICFBamSchemaDefByProjectURLIdxKey argKey )
	{
		schema.getJpaHooksSchema().getSchemaDefService().deleteByProjectURLIdx(argKey.getRequiredCTenantId(),
			argKey.getRequiredProjectURL());
	}

	/**
	 *	Delete the SchemaDef instances identified by the key PubURIIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	CTenantId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@param	PublishURI	The SchemaDef key attribute of the instance generating the id.
	 */
	@Override
	public void deleteSchemaDefByPubURIIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argCTenantId,
		String argPublishURI )
	{
		schema.getJpaHooksSchema().getSchemaDefService().deleteByPubURIIdx(argCTenantId,
		argPublishURI);
	}


	/**
	 *	Delete the SchemaDef instances identified by the key PubURIIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteSchemaDefByPubURIIdx( ICFSecAuthorization Authorization,
		ICFBamSchemaDefByPubURIIdxKey argKey )
	{
		schema.getJpaHooksSchema().getSchemaDefService().deleteByPubURIIdx(argKey.getRequiredCTenantId(),
			argKey.getRequiredPublishURI());
	}

	/**
	 *	Delete the SchemaDef instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The primary key identifying the instance to be deleted.
	 */
	@Override
	public void deleteSchemaDefByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argKey )
	{
		schema.getJpaHooksSchema().getSchemaDefService().deleteByIdIdx(argKey);
	}

	/**
	 *	Delete the SchemaDef instances identified by the key TenantIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TenantId	The SchemaDef key attribute of the instance generating the id.
	 */
	@Override
	public void deleteSchemaDefByTenantIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argTenantId )
	{
		schema.getJpaHooksSchema().getSchemaDefService().deleteByTenantIdx(argTenantId);
	}


	/**
	 *	Delete the SchemaDef instances identified by the key TenantIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteSchemaDefByTenantIdx( ICFSecAuthorization Authorization,
		ICFBamScopeByTenantIdxKey argKey )
	{
		schema.getJpaHooksSchema().getSchemaDefService().deleteByTenantIdx(argKey.getRequiredTenantId());
	}


	/**
	 *	Read the derived SchemaDef record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the SchemaDef instance to be read.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	@Override
	public ICFBamSchemaDef readDerived( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey )
	{
		return( schema.getJpaHooksSchema().getSchemaDefService().find(PKey) );
	}

	/**
	 *	Lock the derived SchemaDef record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the SchemaDef instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	@Override
	public ICFBamSchemaDef lockDerived( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey )
	{
		return( schema.getJpaHooksSchema().getSchemaDefService().lockByIdIdx(PKey) );
	}

	/**
	 *	Read all SchemaDef instances.
	 *
	 *	@param	Authorization	The session authorization information.	
	 *
	 *	@return An array of derived record instances, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamSchemaDef[] readAllDerived( ICFSecAuthorization Authorization ) {
		List<CFBamJpaSchemaDef> results = schema.getJpaHooksSchema().getSchemaDefService().findAll();
		ICFBamSchemaDef[] retset = new ICFBamSchemaDef[results.size()];
		int idx = 0;
		for (CFBamJpaSchemaDef cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read the derived SchemaDef record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	Id	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	@Override
	public ICFBamSchemaDef readDerivedByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argId )
	{
		return( schema.getJpaHooksSchema().getSchemaDefService().find(argId) );
	}

	/**
	 *	Read an array of the derived SchemaDef record instances identified by the duplicate key TenantIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TenantId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamSchemaDef[] readDerivedByTenantIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argTenantId )
	{
		List<CFBamJpaSchemaDef> results = schema.getJpaHooksSchema().getSchemaDefService().findByTenantIdx(argTenantId);
		ICFBamSchemaDef[] retset = new ICFBamSchemaDef[results.size()];
		int idx = 0;
		for (CFBamJpaSchemaDef cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read an array of the derived SchemaDef record instances identified by the duplicate key CTenantIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	CTenantId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamSchemaDef[] readDerivedByCTenantIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argCTenantId )
	{
		List<CFBamJpaSchemaDef> results = schema.getJpaHooksSchema().getSchemaDefService().findByCTenantIdx(argCTenantId);
		ICFBamSchemaDef[] retset = new ICFBamSchemaDef[results.size()];
		int idx = 0;
		for (CFBamJpaSchemaDef cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read an array of the derived SchemaDef record instances identified by the duplicate key MinorVersionIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	MinorVersionId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamSchemaDef[] readDerivedByMinorVersionIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argMinorVersionId )
	{
		List<CFBamJpaSchemaDef> results = schema.getJpaHooksSchema().getSchemaDefService().findByMinorVersionIdx(argMinorVersionId);
		ICFBamSchemaDef[] retset = new ICFBamSchemaDef[results.size()];
		int idx = 0;
		for (CFBamJpaSchemaDef cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read the derived SchemaDef record instance identified by the unique key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	MinorVersionId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@param	Name	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	@Override
	public ICFBamSchemaDef readDerivedByUNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argMinorVersionId,
		String argName )
	{
		return( schema.getJpaHooksSchema().getSchemaDefService().findByUNameIdx(argMinorVersionId,
		argName) );
	}

	/**
	 *	Read an array of the derived SchemaDef record instances identified by the duplicate key AuthEMailIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	CTenantId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@param	AuthorEMail	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamSchemaDef[] readDerivedByAuthEMailIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argCTenantId,
		String argAuthorEMail )
	{
		List<CFBamJpaSchemaDef> results = schema.getJpaHooksSchema().getSchemaDefService().findByAuthEMailIdx(argCTenantId,
		argAuthorEMail);
		ICFBamSchemaDef[] retset = new ICFBamSchemaDef[results.size()];
		int idx = 0;
		for (CFBamJpaSchemaDef cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read an array of the derived SchemaDef record instances identified by the duplicate key ProjectURLIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	CTenantId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@param	ProjectURL	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamSchemaDef[] readDerivedByProjectURLIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argCTenantId,
		String argProjectURL )
	{
		List<CFBamJpaSchemaDef> results = schema.getJpaHooksSchema().getSchemaDefService().findByProjectURLIdx(argCTenantId,
		argProjectURL);
		ICFBamSchemaDef[] retset = new ICFBamSchemaDef[results.size()];
		int idx = 0;
		for (CFBamJpaSchemaDef cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read the derived SchemaDef record instance identified by the unique key PubURIIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	CTenantId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@param	PublishURI	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	@Override
	public ICFBamSchemaDef readDerivedByPubURIIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argCTenantId,
		String argPublishURI )
	{
		return( schema.getJpaHooksSchema().getSchemaDefService().findByPubURIIdx(argCTenantId,
		argPublishURI) );
	}

	/**
	 *	Read the specific SchemaDef record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the SchemaDef instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamSchemaDef readRec( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRec");
	}

	/**
	 *	Lock the specific SchemaDef record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the SchemaDef instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamSchemaDef lockRec( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey )
	{
		throw new CFLibNotImplementedYetException(getClass(), "lockRec");
	}

	/**
	 *	Read all the specific SchemaDef record instances.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@return All the specific SchemaDef instances in the database accessible for the Authorization.
	 */
	@Override
	public ICFBamSchemaDef[] readAllRec( ICFSecAuthorization Authorization ) {
		throw new CFLibNotImplementedYetException(getClass(), "readAllRec");
	}


	/**
	 *	Read the specific SchemaDef record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	Id	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamSchemaDef readRecByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByIdIdx");
	}

	/**
	 *	Read an array of the specific SchemaDef record instances identified by the duplicate key TenantIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TenantId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamSchemaDef[] readRecByTenantIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argTenantId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByTenantIdx");
	}

	/**
	 *	Read an array of the specific SchemaDef record instances identified by the duplicate key CTenantIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	CTenantId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamSchemaDef[] readRecByCTenantIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argCTenantId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByCTenantIdx");
	}

	/**
	 *	Read an array of the specific SchemaDef record instances identified by the duplicate key MinorVersionIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	MinorVersionId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamSchemaDef[] readRecByMinorVersionIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argMinorVersionId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByMinorVersionIdx");
	}

	/**
	 *	Read the specific SchemaDef record instance identified by the unique key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	MinorVersionId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@param	Name	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamSchemaDef readRecByUNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argMinorVersionId,
		String argName )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByUNameIdx");
	}

	/**
	 *	Read an array of the specific SchemaDef record instances identified by the duplicate key AuthEMailIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	CTenantId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@param	AuthorEMail	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamSchemaDef[] readRecByAuthEMailIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argCTenantId,
		String argAuthorEMail )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByAuthEMailIdx");
	}

	/**
	 *	Read an array of the specific SchemaDef record instances identified by the duplicate key ProjectURLIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	CTenantId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@param	ProjectURL	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamSchemaDef[] readRecByProjectURLIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argCTenantId,
		String argProjectURL )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByProjectURLIdx");
	}

	/**
	 *	Read the specific SchemaDef record instance identified by the unique key PubURIIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	CTenantId	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@param	PublishURI	The SchemaDef key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamSchemaDef readRecByPubURIIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argCTenantId,
		String argPublishURI )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByPubURIIdx");
	}
}
