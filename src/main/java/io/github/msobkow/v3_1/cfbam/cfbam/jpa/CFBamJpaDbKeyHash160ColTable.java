
// Description: Java 25 DbIO implementation for DbKeyHash160Col.

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
 *	CFBamJpaDbKeyHash160ColTable database implementation for DbKeyHash160Col
 */
public class CFBamJpaDbKeyHash160ColTable implements ICFBamDbKeyHash160ColTable
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


	public CFBamJpaDbKeyHash160ColTable(ICFBamSchema schema) {
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
	public ICFBamDbKeyHash160Col createDbKeyHash160Col( ICFSecAuthorization Authorization,
		ICFBamDbKeyHash160Col rec )
	{
		if (rec == null) {
			throw new CFLibNullArgumentException(getClass(), "createDbKeyHash160Col", 1, "rec");
		}
		else if (rec instanceof CFBamJpaDbKeyHash160Col) {
			CFBamJpaDbKeyHash160Col jparec = (CFBamJpaDbKeyHash160Col)rec;
			CFBamJpaDbKeyHash160Col created = dbkeyhash160colService.create(jparec);
			return( created );
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "createDbKeyHash160Col", "rec", rec, "CFBamJpaDbKeyHash160Col");
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
	public ICFBamDbKeyHash160Col updateDbKeyHash160Col( ICFSecAuthorization Authorization,
		ICFBamDbKeyHash160Col rec )
	{
		if (rec == null) {
			throw new CFLibNullArgumentException(getClass(), "updateDbKeyHash160Col", 1, "rec");
		}
		else if (rec instanceof CFBamJpaDbKeyHash160Col) {
			CFBamJpaDbKeyHash160Col jparec = (CFBamJpaDbKeyHash160Col)rec;
			CFBamJpaDbKeyHash160Col updated = dbkeyhash160colService.update(jparec);
			return( updated );
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "updateDbKeyHash160Col", "rec", rec, "CFBamJpaDbKeyHash160Col");
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
	public void deleteDbKeyHash160Col( ICFSecAuthorization Authorization,
		ICFBamDbKeyHash160Col rec )
	{
		if (rec == null) {
			return;
		}
		if (rec instanceof CFBamJpaDbKeyHash160Col) {
			CFBamJpaDbKeyHash160Col jparec = (CFBamJpaDbKeyHash160Col)rec;
			dbkeyhash160colService.deleteByIdIdx(jparec.getPKey());
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "deleteDbKeyHash160Col", "rec", rec, "CFBamJpaDbKeyHash160Col");
		}

		throw new CFLibNotImplementedYetException(getClass(), "deleteDbKeyHash160Col");
	}

	/**
	 *	Delete the DbKeyHash160Col instances identified by the key TableIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TableId	The DbKeyHash160Col key attribute of the instance generating the id.
	 */
	@Override
	public void deleteDbKeyHash160ColByTableIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argTableId )
	{
		dbkeyhash160colService.deleteByTableIdx(argTableId);
	}


	/**
	 *	Delete the DbKeyHash160Col instances identified by the key TableIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteDbKeyHash160ColByTableIdx( ICFSecAuthorization Authorization,
		ICFBamDbKeyHash160ColByTableIdxKey argKey )
	{
		dbkeyhash160colService.deleteByTableIdx(argKey.getRequiredTableId());
	}

	/**
	 *	Delete the DbKeyHash160Col instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The primary key identifying the instance to be deleted.
	 */
	@Override
	public void deleteDbKeyHash160ColByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argKey )
	{
		dbkeyhash160colService.deleteByIdIdx(argKey);
	}

	/**
	 *	Delete the DbKeyHash160Col instances identified by the key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ScopeId	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@param	Name	The DbKeyHash160Col key attribute of the instance generating the id.
	 */
	@Override
	public void deleteDbKeyHash160ColByUNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argScopeId,
		String argName )
	{
		dbkeyhash160colService.deleteByUNameIdx(argScopeId,
		argName);
	}


	/**
	 *	Delete the DbKeyHash160Col instances identified by the key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteDbKeyHash160ColByUNameIdx( ICFSecAuthorization Authorization,
		ICFBamValueByUNameIdxKey argKey )
	{
		dbkeyhash160colService.deleteByUNameIdx(argKey.getRequiredScopeId(),
			argKey.getRequiredName());
	}

	/**
	 *	Delete the DbKeyHash160Col instances identified by the key ScopeIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ScopeId	The DbKeyHash160Col key attribute of the instance generating the id.
	 */
	@Override
	public void deleteDbKeyHash160ColByScopeIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argScopeId )
	{
		dbkeyhash160colService.deleteByScopeIdx(argScopeId);
	}


	/**
	 *	Delete the DbKeyHash160Col instances identified by the key ScopeIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteDbKeyHash160ColByScopeIdx( ICFSecAuthorization Authorization,
		ICFBamValueByScopeIdxKey argKey )
	{
		dbkeyhash160colService.deleteByScopeIdx(argKey.getRequiredScopeId());
	}

	/**
	 *	Delete the DbKeyHash160Col instances identified by the key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DefSchemaId	The DbKeyHash160Col key attribute of the instance generating the id.
	 */
	@Override
	public void deleteDbKeyHash160ColByDefSchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argDefSchemaId )
	{
		dbkeyhash160colService.deleteByDefSchemaIdx(argDefSchemaId);
	}


	/**
	 *	Delete the DbKeyHash160Col instances identified by the key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteDbKeyHash160ColByDefSchemaIdx( ICFSecAuthorization Authorization,
		ICFBamValueByDefSchemaIdxKey argKey )
	{
		dbkeyhash160colService.deleteByDefSchemaIdx(argKey.getOptionalDefSchemaId());
	}

	/**
	 *	Delete the DbKeyHash160Col instances identified by the key PrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PrevId	The DbKeyHash160Col key attribute of the instance generating the id.
	 */
	@Override
	public void deleteDbKeyHash160ColByPrevIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argPrevId )
	{
		dbkeyhash160colService.deleteByPrevIdx(argPrevId);
	}


	/**
	 *	Delete the DbKeyHash160Col instances identified by the key PrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteDbKeyHash160ColByPrevIdx( ICFSecAuthorization Authorization,
		ICFBamValueByPrevIdxKey argKey )
	{
		dbkeyhash160colService.deleteByPrevIdx(argKey.getOptionalPrevId());
	}

	/**
	 *	Delete the DbKeyHash160Col instances identified by the key NextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	NextId	The DbKeyHash160Col key attribute of the instance generating the id.
	 */
	@Override
	public void deleteDbKeyHash160ColByNextIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argNextId )
	{
		dbkeyhash160colService.deleteByNextIdx(argNextId);
	}


	/**
	 *	Delete the DbKeyHash160Col instances identified by the key NextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteDbKeyHash160ColByNextIdx( ICFSecAuthorization Authorization,
		ICFBamValueByNextIdxKey argKey )
	{
		dbkeyhash160colService.deleteByNextIdx(argKey.getOptionalNextId());
	}

	/**
	 *	Delete the DbKeyHash160Col instances identified by the key ContPrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ScopeId	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The DbKeyHash160Col key attribute of the instance generating the id.
	 */
	@Override
	public void deleteDbKeyHash160ColByContPrevIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argScopeId,
		CFLibDbKeyHash256 argPrevId )
	{
		dbkeyhash160colService.deleteByContPrevIdx(argScopeId,
		argPrevId);
	}


	/**
	 *	Delete the DbKeyHash160Col instances identified by the key ContPrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteDbKeyHash160ColByContPrevIdx( ICFSecAuthorization Authorization,
		ICFBamValueByContPrevIdxKey argKey )
	{
		dbkeyhash160colService.deleteByContPrevIdx(argKey.getRequiredScopeId(),
			argKey.getOptionalPrevId());
	}

	/**
	 *	Delete the DbKeyHash160Col instances identified by the key ContNextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ScopeId	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The DbKeyHash160Col key attribute of the instance generating the id.
	 */
	@Override
	public void deleteDbKeyHash160ColByContNextIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argScopeId,
		CFLibDbKeyHash256 argNextId )
	{
		dbkeyhash160colService.deleteByContNextIdx(argScopeId,
		argNextId);
	}


	/**
	 *	Delete the DbKeyHash160Col instances identified by the key ContNextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteDbKeyHash160ColByContNextIdx( ICFSecAuthorization Authorization,
		ICFBamValueByContNextIdxKey argKey )
	{
		dbkeyhash160colService.deleteByContNextIdx(argKey.getRequiredScopeId(),
			argKey.getOptionalNextId());
	}


	/**
	 *	Read the derived DbKeyHash160Col record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the DbKeyHash160Col instance to be read.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	@Override
	public ICFBamDbKeyHash160Col readDerived( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey )
	{
		return( dbkeyhash160colService.find(PKey) );
	}

	/**
	 *	Lock the derived DbKeyHash160Col record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the DbKeyHash160Col instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	@Override
	public ICFBamDbKeyHash160Col lockDerived( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey )
	{
		return( dbkeyhash160colService.lockByIdIdx(PKey) );
	}

	/**
	 *	Read all DbKeyHash160Col instances.
	 *
	 *	@param	Authorization	The session authorization information.	
	 *
	 *	@return An array of derived record instances, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamDbKeyHash160Col[] readAllDerived( ICFSecAuthorization Authorization ) {
		List<CFBamJpaDbKeyHash160Col> results = dbkeyhash160colService.findAll();
		ICFBamDbKeyHash160Col[] retset = new ICFBamDbKeyHash160Col[results.size()];
		int idx = 0;
		for (CFBamJpaDbKeyHash160Col cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read the derived DbKeyHash160Col record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	Id	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	@Override
	public ICFBamDbKeyHash160Col readDerivedByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argId )
	{
		return( dbkeyhash160colService.find(argId) );
	}

	/**
	 *	Read the derived DbKeyHash160Col record instance identified by the unique key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ScopeId	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@param	Name	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	@Override
	public ICFBamDbKeyHash160Col readDerivedByUNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argScopeId,
		String argName )
	{
		return( dbkeyhash160colService.findByUNameIdx(argScopeId,
		argName) );
	}

	/**
	 *	Read an array of the derived DbKeyHash160Col record instances identified by the duplicate key ScopeIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ScopeId	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamDbKeyHash160Col[] readDerivedByScopeIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argScopeId )
	{
		List<CFBamJpaDbKeyHash160Col> results = dbkeyhash160colService.findByScopeIdx(argScopeId);
		ICFBamDbKeyHash160Col[] retset = new ICFBamDbKeyHash160Col[results.size()];
		int idx = 0;
		for (CFBamJpaDbKeyHash160Col cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read an array of the derived DbKeyHash160Col record instances identified by the duplicate key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DefSchemaId	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamDbKeyHash160Col[] readDerivedByDefSchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argDefSchemaId )
	{
		List<CFBamJpaDbKeyHash160Col> results = dbkeyhash160colService.findByDefSchemaIdx(argDefSchemaId);
		ICFBamDbKeyHash160Col[] retset = new ICFBamDbKeyHash160Col[results.size()];
		int idx = 0;
		for (CFBamJpaDbKeyHash160Col cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read an array of the derived DbKeyHash160Col record instances identified by the duplicate key PrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PrevId	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamDbKeyHash160Col[] readDerivedByPrevIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argPrevId )
	{
		List<CFBamJpaDbKeyHash160Col> results = dbkeyhash160colService.findByPrevIdx(argPrevId);
		ICFBamDbKeyHash160Col[] retset = new ICFBamDbKeyHash160Col[results.size()];
		int idx = 0;
		for (CFBamJpaDbKeyHash160Col cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read an array of the derived DbKeyHash160Col record instances identified by the duplicate key NextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	NextId	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamDbKeyHash160Col[] readDerivedByNextIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argNextId )
	{
		List<CFBamJpaDbKeyHash160Col> results = dbkeyhash160colService.findByNextIdx(argNextId);
		ICFBamDbKeyHash160Col[] retset = new ICFBamDbKeyHash160Col[results.size()];
		int idx = 0;
		for (CFBamJpaDbKeyHash160Col cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read an array of the derived DbKeyHash160Col record instances identified by the duplicate key ContPrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ScopeId	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamDbKeyHash160Col[] readDerivedByContPrevIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argScopeId,
		CFLibDbKeyHash256 argPrevId )
	{
		List<CFBamJpaDbKeyHash160Col> results = dbkeyhash160colService.findByContPrevIdx(argScopeId,
		argPrevId);
		ICFBamDbKeyHash160Col[] retset = new ICFBamDbKeyHash160Col[results.size()];
		int idx = 0;
		for (CFBamJpaDbKeyHash160Col cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read an array of the derived DbKeyHash160Col record instances identified by the duplicate key ContNextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ScopeId	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamDbKeyHash160Col[] readDerivedByContNextIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argScopeId,
		CFLibDbKeyHash256 argNextId )
	{
		List<CFBamJpaDbKeyHash160Col> results = dbkeyhash160colService.findByContNextIdx(argScopeId,
		argNextId);
		ICFBamDbKeyHash160Col[] retset = new ICFBamDbKeyHash160Col[results.size()];
		int idx = 0;
		for (CFBamJpaDbKeyHash160Col cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read an array of the derived DbKeyHash160Col record instances identified by the duplicate key TableIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TableId	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamDbKeyHash160Col[] readDerivedByTableIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argTableId )
	{
		List<CFBamJpaDbKeyHash160Col> results = dbkeyhash160colService.findByTableIdx(argTableId);
		ICFBamDbKeyHash160Col[] retset = new ICFBamDbKeyHash160Col[results.size()];
		int idx = 0;
		for (CFBamJpaDbKeyHash160Col cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read the specific DbKeyHash160Col record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the DbKeyHash160Col instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamDbKeyHash160Col readRec( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRec");
	}

	/**
	 *	Lock the specific DbKeyHash160Col record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the DbKeyHash160Col instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamDbKeyHash160Col lockRec( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey )
	{
		throw new CFLibNotImplementedYetException(getClass(), "lockRec");
	}

	/**
	 *	Read all the specific DbKeyHash160Col record instances.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@return All the specific DbKeyHash160Col instances in the database accessible for the Authorization.
	 */
	@Override
	public ICFBamDbKeyHash160Col[] readAllRec( ICFSecAuthorization Authorization ) {
		throw new CFLibNotImplementedYetException(getClass(), "readAllRec");
	}


	/**
	 *	Read the specific DbKeyHash160Col record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	Id	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamDbKeyHash160Col readRecByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByIdIdx");
	}

	/**
	 *	Read the specific DbKeyHash160Col record instance identified by the unique key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ScopeId	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@param	Name	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamDbKeyHash160Col readRecByUNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argScopeId,
		String argName )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByUNameIdx");
	}

	/**
	 *	Read an array of the specific DbKeyHash160Col record instances identified by the duplicate key ScopeIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ScopeId	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamDbKeyHash160Col[] readRecByScopeIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argScopeId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByScopeIdx");
	}

	/**
	 *	Read an array of the specific DbKeyHash160Col record instances identified by the duplicate key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DefSchemaId	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamDbKeyHash160Col[] readRecByDefSchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argDefSchemaId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByDefSchemaIdx");
	}

	/**
	 *	Read an array of the specific DbKeyHash160Col record instances identified by the duplicate key PrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PrevId	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamDbKeyHash160Col[] readRecByPrevIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argPrevId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByPrevIdx");
	}

	/**
	 *	Read an array of the specific DbKeyHash160Col record instances identified by the duplicate key NextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	NextId	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamDbKeyHash160Col[] readRecByNextIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argNextId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByNextIdx");
	}

	/**
	 *	Read an array of the specific DbKeyHash160Col record instances identified by the duplicate key ContPrevIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ScopeId	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamDbKeyHash160Col[] readRecByContPrevIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argScopeId,
		CFLibDbKeyHash256 argPrevId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByContPrevIdx");
	}

	/**
	 *	Read an array of the specific DbKeyHash160Col record instances identified by the duplicate key ContNextIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ScopeId	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamDbKeyHash160Col[] readRecByContNextIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argScopeId,
		CFLibDbKeyHash256 argNextId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByContNextIdx");
	}

	/**
	 *	Read an array of the specific DbKeyHash160Col record instances identified by the duplicate key TableIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TableId	The DbKeyHash160Col key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamDbKeyHash160Col[] readRecByTableIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argTableId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByTableIdx");
	}

	/**
	 *	Move the specified record up in the chain (i.e. to the previous position.)
	 *
	 *	@return	The refreshed record after it has been moved
	 */
	@Override
	public ICFBamDbKeyHash160Col moveRecUp( ICFSecAuthorization Authorization,
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
	public ICFBamDbKeyHash160Col moveRecDown( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argId,
		int revision )
	{
		throw new CFLibNotImplementedYetException(getClass(), "moveRecDown");
	}
}
