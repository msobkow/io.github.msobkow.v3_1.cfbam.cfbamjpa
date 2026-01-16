
// Description: Java 25 DbIO implementation for DbKeyHash160Type.

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

/*
 *	CFBamJpaDbKeyHash160TypeTable database implementation for DbKeyHash160Type
 */
public class CFBamJpaDbKeyHash160TypeTable implements ICFBamDbKeyHash160TypeTable
{
	protected CFBamJpaSchema schema;
    @Autowired
    @Qualifier("cfbam31EntityManagerFactory")
    private LocalContainerEntityManagerFactoryBean cfbamEntityManagerFactory;
	@Autowired
	private CFBamJpaScopeService scopeService;

	@Autowired
	private CFBamJpaSchemaDefService schemadefService;

	@Autowired
	private CFBamJpaSchemaRefService schemarefService;

	@Autowired
	private CFBamJpaServerMethodService servermethodService;

	@Autowired
	private CFBamJpaServerObjFuncService serverobjfuncService;

	@Autowired
	private CFBamJpaServerProcService serverprocService;

	@Autowired
	private CFBamJpaTableService tableService;

	@Autowired
	private CFBamJpaValueService valueService;

	@Autowired
	private CFBamJpaAtomService atomService;

	@Autowired
	private CFBamJpaBlobDefService blobdefService;

	@Autowired
	private CFBamJpaBlobTypeService blobtypeService;

	@Autowired
	private CFBamJpaBoolDefService booldefService;

	@Autowired
	private CFBamJpaBoolTypeService booltypeService;

	@Autowired
	private CFBamJpaChainService chainService;

	@Autowired
	private CFBamJpaClearDepService cleardepService;

	@Autowired
	private CFBamJpaClearSubDep1Service clearsubdep1Service;

	@Autowired
	private CFBamJpaClearSubDep2Service clearsubdep2Service;

	@Autowired
	private CFBamJpaClearSubDep3Service clearsubdep3Service;

	@Autowired
	private CFBamJpaClearTopDepService cleartopdepService;

	@Autowired
	private CFBamJpaDateDefService datedefService;

	@Autowired
	private CFBamJpaDateTypeService datetypeService;

	@Autowired
	private CFBamJpaDelDepService deldepService;

	@Autowired
	private CFBamJpaDelSubDep1Service delsubdep1Service;

	@Autowired
	private CFBamJpaDelSubDep2Service delsubdep2Service;

	@Autowired
	private CFBamJpaDelSubDep3Service delsubdep3Service;

	@Autowired
	private CFBamJpaDelTopDepService deltopdepService;

	@Autowired
	private CFBamJpaDoubleDefService doubledefService;

	@Autowired
	private CFBamJpaDoubleTypeService doubletypeService;

	@Autowired
	private CFBamJpaEnumTagService enumtagService;

	@Autowired
	private CFBamJpaFloatDefService floatdefService;

	@Autowired
	private CFBamJpaFloatTypeService floattypeService;

	@Autowired
	private CFBamJpaIndexService indexService;

	@Autowired
	private CFBamJpaIndexColService indexcolService;

	@Autowired
	private CFBamJpaInt16DefService int16defService;

	@Autowired
	private CFBamJpaInt16TypeService int16typeService;

	@Autowired
	private CFBamJpaInt32DefService int32defService;

	@Autowired
	private CFBamJpaInt32TypeService int32typeService;

	@Autowired
	private CFBamJpaInt64DefService int64defService;

	@Autowired
	private CFBamJpaInt64TypeService int64typeService;

	@Autowired
	private CFBamJpaNmTokenDefService nmtokendefService;

	@Autowired
	private CFBamJpaNmTokenTypeService nmtokentypeService;

	@Autowired
	private CFBamJpaNmTokensDefService nmtokensdefService;

	@Autowired
	private CFBamJpaNmTokensTypeService nmtokenstypeService;

	@Autowired
	private CFBamJpaNumberDefService numberdefService;

	@Autowired
	private CFBamJpaNumberTypeService numbertypeService;

	@Autowired
	private CFBamJpaParamService paramService;

	@Autowired
	private CFBamJpaPopDepService popdepService;

	@Autowired
	private CFBamJpaPopSubDep1Service popsubdep1Service;

	@Autowired
	private CFBamJpaPopSubDep2Service popsubdep2Service;

	@Autowired
	private CFBamJpaPopSubDep3Service popsubdep3Service;

	@Autowired
	private CFBamJpaPopTopDepService poptopdepService;

	@Autowired
	private CFBamJpaRelationService relationService;

	@Autowired
	private CFBamJpaRelationColService relationcolService;

	@Autowired
	private CFBamJpaServerListFuncService serverlistfuncService;

	@Autowired
	private CFBamJpaDbKeyHash128DefService dbkeyhash128defService;

	@Autowired
	private CFBamJpaDbKeyHash128ColService dbkeyhash128colService;

	@Autowired
	private CFBamJpaDbKeyHash128TypeService dbkeyhash128typeService;

	@Autowired
	private CFBamJpaDbKeyHash128GenService dbkeyhash128genService;

	@Autowired
	private CFBamJpaDbKeyHash160DefService dbkeyhash160defService;

	@Autowired
	private CFBamJpaDbKeyHash160ColService dbkeyhash160colService;

	@Autowired
	private CFBamJpaDbKeyHash160TypeService dbkeyhash160typeService;

	@Autowired
	private CFBamJpaDbKeyHash160GenService dbkeyhash160genService;

	@Autowired
	private CFBamJpaDbKeyHash224DefService dbkeyhash224defService;

	@Autowired
	private CFBamJpaDbKeyHash224ColService dbkeyhash224colService;

	@Autowired
	private CFBamJpaDbKeyHash224TypeService dbkeyhash224typeService;

	@Autowired
	private CFBamJpaDbKeyHash224GenService dbkeyhash224genService;

	@Autowired
	private CFBamJpaDbKeyHash256DefService dbkeyhash256defService;

	@Autowired
	private CFBamJpaDbKeyHash256ColService dbkeyhash256colService;

	@Autowired
	private CFBamJpaDbKeyHash256TypeService dbkeyhash256typeService;

	@Autowired
	private CFBamJpaDbKeyHash256GenService dbkeyhash256genService;

	@Autowired
	private CFBamJpaDbKeyHash384DefService dbkeyhash384defService;

	@Autowired
	private CFBamJpaDbKeyHash384ColService dbkeyhash384colService;

	@Autowired
	private CFBamJpaDbKeyHash384TypeService dbkeyhash384typeService;

	@Autowired
	private CFBamJpaDbKeyHash384GenService dbkeyhash384genService;

	@Autowired
	private CFBamJpaDbKeyHash512DefService dbkeyhash512defService;

	@Autowired
	private CFBamJpaDbKeyHash512ColService dbkeyhash512colService;

	@Autowired
	private CFBamJpaDbKeyHash512TypeService dbkeyhash512typeService;

	@Autowired
	private CFBamJpaDbKeyHash512GenService dbkeyhash512genService;

	@Autowired
	private CFBamJpaStringDefService stringdefService;

	@Autowired
	private CFBamJpaStringTypeService stringtypeService;

	@Autowired
	private CFBamJpaTZDateDefService tzdatedefService;

	@Autowired
	private CFBamJpaTZDateTypeService tzdatetypeService;

	@Autowired
	private CFBamJpaTZTimeDefService tztimedefService;

	@Autowired
	private CFBamJpaTZTimeTypeService tztimetypeService;

	@Autowired
	private CFBamJpaTZTimestampDefService tztimestampdefService;

	@Autowired
	private CFBamJpaTZTimestampTypeService tztimestamptypeService;

	@Autowired
	private CFBamJpaTableColService tablecolService;

	@Autowired
	private CFBamJpaTextDefService textdefService;

	@Autowired
	private CFBamJpaTextTypeService texttypeService;

	@Autowired
	private CFBamJpaTimeDefService timedefService;

	@Autowired
	private CFBamJpaTimeTypeService timetypeService;

	@Autowired
	private CFBamJpaTimestampDefService timestampdefService;

	@Autowired
	private CFBamJpaTimestampTypeService timestamptypeService;

	@Autowired
	private CFBamJpaTokenDefService tokendefService;

	@Autowired
	private CFBamJpaTokenTypeService tokentypeService;

	@Autowired
	private CFBamJpaUInt16DefService uint16defService;

	@Autowired
	private CFBamJpaUInt16TypeService uint16typeService;

	@Autowired
	private CFBamJpaUInt32DefService uint32defService;

	@Autowired
	private CFBamJpaUInt32TypeService uint32typeService;

	@Autowired
	private CFBamJpaUInt64DefService uint64defService;

	@Autowired
	private CFBamJpaUInt64TypeService uint64typeService;

	@Autowired
	private CFBamJpaUuidDefService uuiddefService;

	@Autowired
	private CFBamJpaUuid6DefService uuid6defService;

	@Autowired
	private CFBamJpaUuidTypeService uuidtypeService;

	@Autowired
	private CFBamJpaUuid6TypeService uuid6typeService;

	@Autowired
	private CFBamJpaBlobColService blobcolService;

	@Autowired
	private CFBamJpaBoolColService boolcolService;

	@Autowired
	private CFBamJpaDateColService datecolService;

	@Autowired
	private CFBamJpaDoubleColService doublecolService;

	@Autowired
	private CFBamJpaEnumDefService enumdefService;

	@Autowired
	private CFBamJpaEnumTypeService enumtypeService;

	@Autowired
	private CFBamJpaFloatColService floatcolService;

	@Autowired
	private CFBamJpaId16GenService id16genService;

	@Autowired
	private CFBamJpaId32GenService id32genService;

	@Autowired
	private CFBamJpaId64GenService id64genService;

	@Autowired
	private CFBamJpaInt16ColService int16colService;

	@Autowired
	private CFBamJpaInt32ColService int32colService;

	@Autowired
	private CFBamJpaInt64ColService int64colService;

	@Autowired
	private CFBamJpaNmTokenColService nmtokencolService;

	@Autowired
	private CFBamJpaNmTokensColService nmtokenscolService;

	@Autowired
	private CFBamJpaNumberColService numbercolService;

	@Autowired
	private CFBamJpaStringColService stringcolService;

	@Autowired
	private CFBamJpaTZDateColService tzdatecolService;

	@Autowired
	private CFBamJpaTZTimeColService tztimecolService;

	@Autowired
	private CFBamJpaTZTimestampColService tztimestampcolService;

	@Autowired
	private CFBamJpaTextColService textcolService;

	@Autowired
	private CFBamJpaTimeColService timecolService;

	@Autowired
	private CFBamJpaTimestampColService timestampcolService;

	@Autowired
	private CFBamJpaTokenColService tokencolService;

	@Autowired
	private CFBamJpaUInt16ColService uint16colService;

	@Autowired
	private CFBamJpaUInt32ColService uint32colService;

	@Autowired
	private CFBamJpaUInt64ColService uint64colService;

	@Autowired
	private CFBamJpaUuidColService uuidcolService;

	@Autowired
	private CFBamJpaUuid6ColService uuid6colService;

	@Autowired
	private CFBamJpaUuidGenService uuidgenService;

	@Autowired
	private CFBamJpaUuid6GenService uuid6genService;


	public CFBamJpaDbKeyHash160TypeTable(ICFBamSchema schema) {
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
	public ICFBamDbKeyHash160Type createDbKeyHash160Type( ICFSecAuthorization Authorization,
		ICFBamDbKeyHash160Type rec )
	{
		if (rec == null) {
			throw new CFLibNullArgumentException(getClass(), "createDbKeyHash160Type", 1, "rec");
		}
		else if (rec instanceof CFBamJpaDbKeyHash160Type) {
			CFBamJpaDbKeyHash160Type jparec = (CFBamJpaDbKeyHash160Type)rec;
			CFBamJpaDbKeyHash160Type created = dbkeyhash160typeService.create(jparec);
			return( created );
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "createDbKeyHash160Type", "rec", rec, "CFBamJpaDbKeyHash160Type");
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
	public ICFBamDbKeyHash160Type updateDbKeyHash160Type( ICFSecAuthorization Authorization,
		ICFBamDbKeyHash160Type rec )
	{
		if (rec == null) {
			throw new CFLibNullArgumentException(getClass(), "updateDbKeyHash160Type", 1, "rec");
		}
		else if (rec instanceof CFBamJpaDbKeyHash160Type) {
			CFBamJpaDbKeyHash160Type jparec = (CFBamJpaDbKeyHash160Type)rec;
			CFBamJpaDbKeyHash160Type updated = dbkeyhash160typeService.update(jparec);
			return( updated );
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "updateDbKeyHash160Type", "rec", rec, "CFBamJpaDbKeyHash160Type");
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
	public void deleteDbKeyHash160Type( ICFSecAuthorization Authorization,
		ICFBamDbKeyHash160Type rec )
	{
		if (rec == null) {
			return;
		}
		if (rec instanceof CFBamJpaDbKeyHash160Type) {
			CFBamJpaDbKeyHash160Type jparec = (CFBamJpaDbKeyHash160Type)rec;
			dbkeyhash160typeService.deleteByIdIdx(jparec.getPKey());
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "deleteDbKeyHash160Type", "rec", rec, "CFBamJpaDbKeyHash160Type");
		}

		throw new CFLibNotImplementedYetException(getClass(), "deleteDbKeyHash160Type");
	}

	/**
	 *	Delete the DbKeyHash160Type instances identified by the key SchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SchemaDefId	The DbKeyHash160Type key attribute of the instance generating the id.
	 */
	@Override
	public void deleteDbKeyHash160TypeBySchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argSchemaDefId )
	{
		dbkeyhash160typeService.deleteBySchemaIdx(argSchemaDefId);
	}


	/**
	 *	Delete the DbKeyHash160Type instances identified by the key SchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteDbKeyHash160TypeBySchemaIdx( ICFSecAuthorization Authorization,
		ICFBamDbKeyHash160TypeBySchemaIdxKey argKey )
	{
		dbkeyhash160typeService.deleteBySchemaIdx(argKey.getRequiredSchemaDefId());
	}

	/**
	 *	Delete the DbKeyHash160Type instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The primary key identifying the instance to be deleted.
	 */
	@Override
	public void deleteDbKeyHash160TypeByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argKey )
	{
		dbkeyhash160typeService.deleteByIdIdx(argKey);
	}

	/**
	 *	Delete the DbKeyHash160Type instances identified by the key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ScopeId	The DbKeyHash160Type key attribute of the instance generating the id.
	 *
	 *	@param	Name	The DbKeyHash160Type key attribute of the instance generating the id.
	 */
	@Override
	public void deleteDbKeyHash160TypeByUNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argScopeId,
		String argName )
	{
		dbkeyhash160typeService.deleteByUNameIdx(argScopeId,
		argName);
	}


	/**
	 *	Delete the DbKeyHash160Type instances identified by the key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteDbKeyHash160TypeByUNameIdx( ICFSecAuthorization Authorization,
		ICFBamValueByUNameIdxKey argKey )
	{
		dbkeyhash160typeService.deleteByUNameIdx(argKey.getRequiredScopeId(),
			argKey.getRequiredName());
	}

	/**
	 *	Delete the DbKeyHash160Type instances identified by the key ScopeIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ScopeId	The DbKeyHash160Type key attribute of the instance generating the id.
	 */
	@Override
	public void deleteDbKeyHash160TypeByScopeIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argScopeId )
	{
		dbkeyhash160typeService.deleteByScopeIdx(argScopeId);
	}


	/**
	 *	Delete the DbKeyHash160Type instances identified by the key ScopeIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteDbKeyHash160TypeByScopeIdx( ICFSecAuthorization Authorization,
		ICFBamValueByScopeIdxKey argKey )
	{
		dbkeyhash160typeService.deleteByScopeIdx(argKey.getRequiredScopeId());
	}

	/**
	 *	Delete the DbKeyHash160Type instances identified by the key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DefSchemaId	The DbKeyHash160Type key attribute of the instance generating the id.
	 */
	@Override
	public void deleteDbKeyHash160TypeByDefSchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argDefSchemaId )
	{
		dbkeyhash160typeService.deleteByDefSchemaIdx(argDefSchemaId);
	}


	/**
	 *	Delete the DbKeyHash160Type instances identified by the key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteDbKeyHash160TypeByDefSchemaIdx( ICFSecAuthorization Authorization,
		ICFBamValueByDefSchemaIdxKey argKey )
	{
		dbkeyhash160typeService.deleteByDefSchemaIdx(argKey.getOptionalDefSchemaId());
	}

	/**
	 *	Delete the DbKeyHash160Type instances identified by the key PrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PrevId	The DbKeyHash160Type key attribute of the instance generating the id.
	 */
	@Override
	public void deleteDbKeyHash160TypeByPrevIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argPrevId )
	{
		dbkeyhash160typeService.deleteByPrevIdx(argPrevId);
	}


	/**
	 *	Delete the DbKeyHash160Type instances identified by the key PrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteDbKeyHash160TypeByPrevIdx( ICFSecAuthorization Authorization,
		ICFBamValueByPrevIdxKey argKey )
	{
		dbkeyhash160typeService.deleteByPrevIdx(argKey.getOptionalPrevId());
	}

	/**
	 *	Delete the DbKeyHash160Type instances identified by the key NextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	NextId	The DbKeyHash160Type key attribute of the instance generating the id.
	 */
	@Override
	public void deleteDbKeyHash160TypeByNextIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argNextId )
	{
		dbkeyhash160typeService.deleteByNextIdx(argNextId);
	}


	/**
	 *	Delete the DbKeyHash160Type instances identified by the key NextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteDbKeyHash160TypeByNextIdx( ICFSecAuthorization Authorization,
		ICFBamValueByNextIdxKey argKey )
	{
		dbkeyhash160typeService.deleteByNextIdx(argKey.getOptionalNextId());
	}

	/**
	 *	Delete the DbKeyHash160Type instances identified by the key ContPrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ScopeId	The DbKeyHash160Type key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The DbKeyHash160Type key attribute of the instance generating the id.
	 */
	@Override
	public void deleteDbKeyHash160TypeByContPrevIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argScopeId,
		CFLibDbKeyHash256 argPrevId )
	{
		dbkeyhash160typeService.deleteByContPrevIdx(argScopeId,
		argPrevId);
	}


	/**
	 *	Delete the DbKeyHash160Type instances identified by the key ContPrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteDbKeyHash160TypeByContPrevIdx( ICFSecAuthorization Authorization,
		ICFBamValueByContPrevIdxKey argKey )
	{
		dbkeyhash160typeService.deleteByContPrevIdx(argKey.getRequiredScopeId(),
			argKey.getOptionalPrevId());
	}

	/**
	 *	Delete the DbKeyHash160Type instances identified by the key ContNextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ScopeId	The DbKeyHash160Type key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The DbKeyHash160Type key attribute of the instance generating the id.
	 */
	@Override
	public void deleteDbKeyHash160TypeByContNextIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argScopeId,
		CFLibDbKeyHash256 argNextId )
	{
		dbkeyhash160typeService.deleteByContNextIdx(argScopeId,
		argNextId);
	}


	/**
	 *	Delete the DbKeyHash160Type instances identified by the key ContNextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteDbKeyHash160TypeByContNextIdx( ICFSecAuthorization Authorization,
		ICFBamValueByContNextIdxKey argKey )
	{
		dbkeyhash160typeService.deleteByContNextIdx(argKey.getRequiredScopeId(),
			argKey.getOptionalNextId());
	}


	/**
	 *	Read the derived DbKeyHash160Type record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the DbKeyHash160Type instance to be read.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	@Override
	public ICFBamDbKeyHash160Type readDerived( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey )
	{
		return( dbkeyhash160typeService.find(PKey) );
	}

	/**
	 *	Lock the derived DbKeyHash160Type record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the DbKeyHash160Type instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	@Override
	public ICFBamDbKeyHash160Type lockDerived( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey )
	{
		return( dbkeyhash160typeService.lockByIdIdx(PKey) );
	}

	/**
	 *	Read all DbKeyHash160Type instances.
	 *
	 *	@param	Authorization	The session authorization information.	
	 *
	 *	@return An array of derived record instances, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamDbKeyHash160Type[] readAllDerived( ICFSecAuthorization Authorization ) {
		List<CFBamJpaDbKeyHash160Type> results = dbkeyhash160typeService.findAll();
		ICFBamDbKeyHash160Type[] retset = new ICFBamDbKeyHash160Type[results.size()];
		int idx = 0;
		for (CFBamJpaDbKeyHash160Type cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read the derived DbKeyHash160Type record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	Id	The DbKeyHash160Type key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	@Override
	public ICFBamDbKeyHash160Type readDerivedByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argId )
	{
		return( dbkeyhash160typeService.find(argId) );
	}

	/**
	 *	Read the derived DbKeyHash160Type record instance identified by the unique key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ScopeId	The DbKeyHash160Type key attribute of the instance generating the id.
	 *
	 *	@param	Name	The DbKeyHash160Type key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	@Override
	public ICFBamDbKeyHash160Type readDerivedByUNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argScopeId,
		String argName )
	{
		return( dbkeyhash160typeService.findByUNameIdx(argScopeId,
		argName) );
	}

	/**
	 *	Read an array of the derived DbKeyHash160Type record instances identified by the duplicate key ScopeIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ScopeId	The DbKeyHash160Type key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamDbKeyHash160Type[] readDerivedByScopeIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argScopeId )
	{
		List<CFBamJpaDbKeyHash160Type> results = dbkeyhash160typeService.findByScopeIdx(argScopeId);
		ICFBamDbKeyHash160Type[] retset = new ICFBamDbKeyHash160Type[results.size()];
		int idx = 0;
		for (CFBamJpaDbKeyHash160Type cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read an array of the derived DbKeyHash160Type record instances identified by the duplicate key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DefSchemaId	The DbKeyHash160Type key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamDbKeyHash160Type[] readDerivedByDefSchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argDefSchemaId )
	{
		List<CFBamJpaDbKeyHash160Type> results = dbkeyhash160typeService.findByDefSchemaIdx(argDefSchemaId);
		ICFBamDbKeyHash160Type[] retset = new ICFBamDbKeyHash160Type[results.size()];
		int idx = 0;
		for (CFBamJpaDbKeyHash160Type cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read an array of the derived DbKeyHash160Type record instances identified by the duplicate key PrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PrevId	The DbKeyHash160Type key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamDbKeyHash160Type[] readDerivedByPrevIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argPrevId )
	{
		List<CFBamJpaDbKeyHash160Type> results = dbkeyhash160typeService.findByPrevIdx(argPrevId);
		ICFBamDbKeyHash160Type[] retset = new ICFBamDbKeyHash160Type[results.size()];
		int idx = 0;
		for (CFBamJpaDbKeyHash160Type cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read an array of the derived DbKeyHash160Type record instances identified by the duplicate key NextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	NextId	The DbKeyHash160Type key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamDbKeyHash160Type[] readDerivedByNextIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argNextId )
	{
		List<CFBamJpaDbKeyHash160Type> results = dbkeyhash160typeService.findByNextIdx(argNextId);
		ICFBamDbKeyHash160Type[] retset = new ICFBamDbKeyHash160Type[results.size()];
		int idx = 0;
		for (CFBamJpaDbKeyHash160Type cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read an array of the derived DbKeyHash160Type record instances identified by the duplicate key ContPrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ScopeId	The DbKeyHash160Type key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The DbKeyHash160Type key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamDbKeyHash160Type[] readDerivedByContPrevIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argScopeId,
		CFLibDbKeyHash256 argPrevId )
	{
		List<CFBamJpaDbKeyHash160Type> results = dbkeyhash160typeService.findByContPrevIdx(argScopeId,
		argPrevId);
		ICFBamDbKeyHash160Type[] retset = new ICFBamDbKeyHash160Type[results.size()];
		int idx = 0;
		for (CFBamJpaDbKeyHash160Type cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read an array of the derived DbKeyHash160Type record instances identified by the duplicate key ContNextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ScopeId	The DbKeyHash160Type key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The DbKeyHash160Type key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamDbKeyHash160Type[] readDerivedByContNextIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argScopeId,
		CFLibDbKeyHash256 argNextId )
	{
		List<CFBamJpaDbKeyHash160Type> results = dbkeyhash160typeService.findByContNextIdx(argScopeId,
		argNextId);
		ICFBamDbKeyHash160Type[] retset = new ICFBamDbKeyHash160Type[results.size()];
		int idx = 0;
		for (CFBamJpaDbKeyHash160Type cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read an array of the derived DbKeyHash160Type record instances identified by the duplicate key SchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SchemaDefId	The DbKeyHash160Type key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamDbKeyHash160Type[] readDerivedBySchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argSchemaDefId )
	{
		List<CFBamJpaDbKeyHash160Type> results = dbkeyhash160typeService.findBySchemaIdx(argSchemaDefId);
		ICFBamDbKeyHash160Type[] retset = new ICFBamDbKeyHash160Type[results.size()];
		int idx = 0;
		for (CFBamJpaDbKeyHash160Type cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read the specific DbKeyHash160Type record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the DbKeyHash160Type instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamDbKeyHash160Type readRec( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRec");
	}

	/**
	 *	Lock the specific DbKeyHash160Type record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the DbKeyHash160Type instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamDbKeyHash160Type lockRec( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey )
	{
		throw new CFLibNotImplementedYetException(getClass(), "lockRec");
	}

	/**
	 *	Read all the specific DbKeyHash160Type record instances.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@return All the specific DbKeyHash160Type instances in the database accessible for the Authorization.
	 */
	@Override
	public ICFBamDbKeyHash160Type[] readAllRec( ICFSecAuthorization Authorization ) {
		throw new CFLibNotImplementedYetException(getClass(), "readAllRec");
	}


	/**
	 *	Read the specific DbKeyHash160Type record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	Id	The DbKeyHash160Type key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamDbKeyHash160Type readRecByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByIdIdx");
	}

	/**
	 *	Read the specific DbKeyHash160Type record instance identified by the unique key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ScopeId	The DbKeyHash160Type key attribute of the instance generating the id.
	 *
	 *	@param	Name	The DbKeyHash160Type key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamDbKeyHash160Type readRecByUNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argScopeId,
		String argName )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByUNameIdx");
	}

	/**
	 *	Read an array of the specific DbKeyHash160Type record instances identified by the duplicate key ScopeIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ScopeId	The DbKeyHash160Type key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamDbKeyHash160Type[] readRecByScopeIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argScopeId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByScopeIdx");
	}

	/**
	 *	Read an array of the specific DbKeyHash160Type record instances identified by the duplicate key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DefSchemaId	The DbKeyHash160Type key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamDbKeyHash160Type[] readRecByDefSchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argDefSchemaId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByDefSchemaIdx");
	}

	/**
	 *	Read an array of the specific DbKeyHash160Type record instances identified by the duplicate key PrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PrevId	The DbKeyHash160Type key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamDbKeyHash160Type[] readRecByPrevIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argPrevId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByPrevIdx");
	}

	/**
	 *	Read an array of the specific DbKeyHash160Type record instances identified by the duplicate key NextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	NextId	The DbKeyHash160Type key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamDbKeyHash160Type[] readRecByNextIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argNextId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByNextIdx");
	}

	/**
	 *	Read an array of the specific DbKeyHash160Type record instances identified by the duplicate key ContPrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ScopeId	The DbKeyHash160Type key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The DbKeyHash160Type key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamDbKeyHash160Type[] readRecByContPrevIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argScopeId,
		CFLibDbKeyHash256 argPrevId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByContPrevIdx");
	}

	/**
	 *	Read an array of the specific DbKeyHash160Type record instances identified by the duplicate key ContNextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ScopeId	The DbKeyHash160Type key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The DbKeyHash160Type key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamDbKeyHash160Type[] readRecByContNextIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argScopeId,
		CFLibDbKeyHash256 argNextId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByContNextIdx");
	}

	/**
	 *	Read an array of the specific DbKeyHash160Type record instances identified by the duplicate key SchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SchemaDefId	The DbKeyHash160Type key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamDbKeyHash160Type[] readRecBySchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argSchemaDefId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecBySchemaIdx");
	}

	/**
	 *	Move the specified record up in the chain (i.e. to the previous position.)
	 *
	 *	@return	The refreshed record after it has been moved
	 */
	@Override
	public ICFBamDbKeyHash160Type moveRecUp( ICFSecAuthorization Authorization,
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
	public ICFBamDbKeyHash160Type moveRecDown( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argId,
		int revision )
	{
		throw new CFLibNotImplementedYetException(getClass(), "moveRecDown");
	}
}
