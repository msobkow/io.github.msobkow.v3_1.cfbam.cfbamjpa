
// Description: Java 25 DbIO implementation for Table.

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
 *	CFBamJpaTableTable database implementation for Table
 */
public class CFBamJpaTableTable implements ICFBamTableTable
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


	public CFBamJpaTableTable(ICFBamSchema schema) {
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
	public ICFBamTable createTable( ICFSecAuthorization Authorization,
		ICFBamTable rec )
	{
		if (rec == null) {
			throw new CFLibNullArgumentException(getClass(), "createTable", 1, "rec");
		}
		else if (rec instanceof CFBamJpaTable) {
			CFBamJpaTable jparec = (CFBamJpaTable)rec;
			CFBamJpaTable created = tableService.create(jparec);
			return( created );
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "createTable", "rec", rec, "CFBamJpaTable");
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
	public ICFBamTable updateTable( ICFSecAuthorization Authorization,
		ICFBamTable rec )
	{
		if (rec == null) {
			throw new CFLibNullArgumentException(getClass(), "updateTable", 1, "rec");
		}
		else if (rec instanceof CFBamJpaTable) {
			CFBamJpaTable jparec = (CFBamJpaTable)rec;
			CFBamJpaTable updated = tableService.update(jparec);
			return( updated );
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "updateTable", "rec", rec, "CFBamJpaTable");
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
	public void deleteTable( ICFSecAuthorization Authorization,
		ICFBamTable rec )
	{
		if (rec == null) {
			return;
		}
		if (rec instanceof CFBamJpaTable) {
			CFBamJpaTable jparec = (CFBamJpaTable)rec;
			tableService.deleteByIdIdx(jparec.getPKey());
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "deleteTable", "rec", rec, "CFBamJpaTable");
		}

		throw new CFLibNotImplementedYetException(getClass(), "deleteTable");
	}

	/**
	 *	Delete the Table instances identified by the key SchemaDefIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SchemaDefId	The Table key attribute of the instance generating the id.
	 */
	@Override
	public void deleteTableBySchemaDefIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argSchemaDefId )
	{
		tableService.deleteBySchemaDefIdx(argSchemaDefId);
	}


	/**
	 *	Delete the Table instances identified by the key SchemaDefIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteTableBySchemaDefIdx( ICFSecAuthorization Authorization,
		ICFBamTableBySchemaDefIdxKey argKey )
	{
		tableService.deleteBySchemaDefIdx(argKey.getRequiredSchemaDefId());
	}

	/**
	 *	Delete the Table instances identified by the key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DefSchemaId	The Table key attribute of the instance generating the id.
	 */
	@Override
	public void deleteTableByDefSchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argDefSchemaId )
	{
		tableService.deleteByDefSchemaIdx(argDefSchemaId);
	}


	/**
	 *	Delete the Table instances identified by the key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteTableByDefSchemaIdx( ICFSecAuthorization Authorization,
		ICFBamTableByDefSchemaIdxKey argKey )
	{
		tableService.deleteByDefSchemaIdx(argKey.getOptionalDefSchemaId());
	}

	/**
	 *	Delete the Table instances identified by the key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SchemaDefId	The Table key attribute of the instance generating the id.
	 *
	 *	@param	Name	The Table key attribute of the instance generating the id.
	 */
	@Override
	public void deleteTableByUNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argSchemaDefId,
		String argName )
	{
		tableService.deleteByUNameIdx(argSchemaDefId,
		argName);
	}


	/**
	 *	Delete the Table instances identified by the key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteTableByUNameIdx( ICFSecAuthorization Authorization,
		ICFBamTableByUNameIdxKey argKey )
	{
		tableService.deleteByUNameIdx(argKey.getRequiredSchemaDefId(),
			argKey.getRequiredName());
	}

	/**
	 *	Delete the Table instances identified by the key SchemaCdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SchemaDefId	The Table key attribute of the instance generating the id.
	 *
	 *	@param	TableClassCode	The Table key attribute of the instance generating the id.
	 */
	@Override
	public void deleteTableBySchemaCdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argSchemaDefId,
		String argTableClassCode )
	{
		tableService.deleteBySchemaCdIdx(argSchemaDefId,
		argTableClassCode);
	}


	/**
	 *	Delete the Table instances identified by the key SchemaCdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteTableBySchemaCdIdx( ICFSecAuthorization Authorization,
		ICFBamTableBySchemaCdIdxKey argKey )
	{
		tableService.deleteBySchemaCdIdx(argKey.getRequiredSchemaDefId(),
			argKey.getRequiredTableClassCode());
	}

	/**
	 *	Delete the Table instances identified by the key PrimaryIndexIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PrimaryIndexId	The Table key attribute of the instance generating the id.
	 */
	@Override
	public void deleteTableByPrimaryIndexIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argPrimaryIndexId )
	{
		tableService.deleteByPrimaryIndexIdx(argPrimaryIndexId);
	}


	/**
	 *	Delete the Table instances identified by the key PrimaryIndexIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteTableByPrimaryIndexIdx( ICFSecAuthorization Authorization,
		ICFBamTableByPrimaryIndexIdxKey argKey )
	{
		tableService.deleteByPrimaryIndexIdx(argKey.getOptionalPrimaryIndexId());
	}

	/**
	 *	Delete the Table instances identified by the key LookupIndexIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	LookupIndexId	The Table key attribute of the instance generating the id.
	 */
	@Override
	public void deleteTableByLookupIndexIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argLookupIndexId )
	{
		tableService.deleteByLookupIndexIdx(argLookupIndexId);
	}


	/**
	 *	Delete the Table instances identified by the key LookupIndexIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteTableByLookupIndexIdx( ICFSecAuthorization Authorization,
		ICFBamTableByLookupIndexIdxKey argKey )
	{
		tableService.deleteByLookupIndexIdx(argKey.getOptionalLookupIndexId());
	}

	/**
	 *	Delete the Table instances identified by the key AltIndexIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	AltIndexId	The Table key attribute of the instance generating the id.
	 */
	@Override
	public void deleteTableByAltIndexIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argAltIndexId )
	{
		tableService.deleteByAltIndexIdx(argAltIndexId);
	}


	/**
	 *	Delete the Table instances identified by the key AltIndexIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteTableByAltIndexIdx( ICFSecAuthorization Authorization,
		ICFBamTableByAltIndexIdxKey argKey )
	{
		tableService.deleteByAltIndexIdx(argKey.getOptionalAltIndexId());
	}

	/**
	 *	Delete the Table instances identified by the key QualTableIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	QualifyingTableId	The Table key attribute of the instance generating the id.
	 */
	@Override
	public void deleteTableByQualTableIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argQualifyingTableId )
	{
		tableService.deleteByQualTableIdx(argQualifyingTableId);
	}


	/**
	 *	Delete the Table instances identified by the key QualTableIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteTableByQualTableIdx( ICFSecAuthorization Authorization,
		ICFBamTableByQualTableIdxKey argKey )
	{
		tableService.deleteByQualTableIdx(argKey.getOptionalQualifyingTableId());
	}

	/**
	 *	Delete the Table instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The primary key identifying the instance to be deleted.
	 */
	@Override
	public void deleteTableByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argKey )
	{
		tableService.deleteByIdIdx(argKey);
	}

	/**
	 *	Delete the Table instances identified by the key TenantIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TenantId	The Table key attribute of the instance generating the id.
	 */
	@Override
	public void deleteTableByTenantIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argTenantId )
	{
		tableService.deleteByTenantIdx(argTenantId);
	}


	/**
	 *	Delete the Table instances identified by the key TenantIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	@Override
	public void deleteTableByTenantIdx( ICFSecAuthorization Authorization,
		ICFBamScopeByTenantIdxKey argKey )
	{
		tableService.deleteByTenantIdx(argKey.getRequiredTenantId());
	}


	/**
	 *	Read the derived Table record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the Table instance to be read.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	@Override
	public ICFBamTable readDerived( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey )
	{
		return( tableService.find(PKey) );
	}

	/**
	 *	Lock the derived Table record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the Table instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	@Override
	public ICFBamTable lockDerived( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey )
	{
		return( tableService.lockByIdIdx(PKey) );
	}

	/**
	 *	Read all Table instances.
	 *
	 *	@param	Authorization	The session authorization information.	
	 *
	 *	@return An array of derived record instances, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamTable[] readAllDerived( ICFSecAuthorization Authorization ) {
		List<CFBamJpaTable> results = tableService.findAll();
		ICFBamTable[] retset = new ICFBamTable[results.size()];
		int idx = 0;
		for (CFBamJpaTable cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read the derived Table record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	Id	The Table key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	@Override
	public ICFBamTable readDerivedByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argId )
	{
		return( tableService.find(argId) );
	}

	/**
	 *	Read an array of the derived Table record instances identified by the duplicate key TenantIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TenantId	The Table key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamTable[] readDerivedByTenantIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argTenantId )
	{
		List<CFBamJpaTable> results = tableService.findByTenantIdx(argTenantId);
		ICFBamTable[] retset = new ICFBamTable[results.size()];
		int idx = 0;
		for (CFBamJpaTable cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read an array of the derived Table record instances identified by the duplicate key SchemaDefIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SchemaDefId	The Table key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamTable[] readDerivedBySchemaDefIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argSchemaDefId )
	{
		List<CFBamJpaTable> results = tableService.findBySchemaDefIdx(argSchemaDefId);
		ICFBamTable[] retset = new ICFBamTable[results.size()];
		int idx = 0;
		for (CFBamJpaTable cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read an array of the derived Table record instances identified by the duplicate key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DefSchemaId	The Table key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamTable[] readDerivedByDefSchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argDefSchemaId )
	{
		List<CFBamJpaTable> results = tableService.findByDefSchemaIdx(argDefSchemaId);
		ICFBamTable[] retset = new ICFBamTable[results.size()];
		int idx = 0;
		for (CFBamJpaTable cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read the derived Table record instance identified by the unique key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SchemaDefId	The Table key attribute of the instance generating the id.
	 *
	 *	@param	Name	The Table key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	@Override
	public ICFBamTable readDerivedByUNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argSchemaDefId,
		String argName )
	{
		return( tableService.findByUNameIdx(argSchemaDefId,
		argName) );
	}

	/**
	 *	Read the derived Table record instance identified by the unique key SchemaCdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SchemaDefId	The Table key attribute of the instance generating the id.
	 *
	 *	@param	TableClassCode	The Table key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	@Override
	public ICFBamTable readDerivedBySchemaCdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argSchemaDefId,
		String argTableClassCode )
	{
		return( tableService.findBySchemaCdIdx(argSchemaDefId,
		argTableClassCode) );
	}

	/**
	 *	Read an array of the derived Table record instances identified by the duplicate key PrimaryIndexIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PrimaryIndexId	The Table key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamTable[] readDerivedByPrimaryIndexIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argPrimaryIndexId )
	{
		List<CFBamJpaTable> results = tableService.findByPrimaryIndexIdx(argPrimaryIndexId);
		ICFBamTable[] retset = new ICFBamTable[results.size()];
		int idx = 0;
		for (CFBamJpaTable cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read an array of the derived Table record instances identified by the duplicate key LookupIndexIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	LookupIndexId	The Table key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamTable[] readDerivedByLookupIndexIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argLookupIndexId )
	{
		List<CFBamJpaTable> results = tableService.findByLookupIndexIdx(argLookupIndexId);
		ICFBamTable[] retset = new ICFBamTable[results.size()];
		int idx = 0;
		for (CFBamJpaTable cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read an array of the derived Table record instances identified by the duplicate key AltIndexIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	AltIndexId	The Table key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamTable[] readDerivedByAltIndexIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argAltIndexId )
	{
		List<CFBamJpaTable> results = tableService.findByAltIndexIdx(argAltIndexId);
		ICFBamTable[] retset = new ICFBamTable[results.size()];
		int idx = 0;
		for (CFBamJpaTable cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read an array of the derived Table record instances identified by the duplicate key QualTableIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	QualifyingTableId	The Table key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	@Override
	public ICFBamTable[] readDerivedByQualTableIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argQualifyingTableId )
	{
		List<CFBamJpaTable> results = tableService.findByQualTableIdx(argQualifyingTableId);
		ICFBamTable[] retset = new ICFBamTable[results.size()];
		int idx = 0;
		for (CFBamJpaTable cur: results) {
			retset[idx++] = cur;
		}
		return( retset );
	}

	/**
	 *	Read the specific Table record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the Table instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamTable readRec( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRec");
	}

	/**
	 *	Lock the specific Table record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the Table instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamTable lockRec( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 PKey )
	{
		throw new CFLibNotImplementedYetException(getClass(), "lockRec");
	}

	/**
	 *	Read all the specific Table record instances.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@return All the specific Table instances in the database accessible for the Authorization.
	 */
	@Override
	public ICFBamTable[] readAllRec( ICFSecAuthorization Authorization ) {
		throw new CFLibNotImplementedYetException(getClass(), "readAllRec");
	}


	/**
	 *	Read the specific Table record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	Id	The Table key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamTable readRecByIdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByIdIdx");
	}

	/**
	 *	Read an array of the specific Table record instances identified by the duplicate key TenantIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TenantId	The Table key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamTable[] readRecByTenantIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argTenantId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByTenantIdx");
	}

	/**
	 *	Read an array of the specific Table record instances identified by the duplicate key SchemaDefIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SchemaDefId	The Table key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamTable[] readRecBySchemaDefIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argSchemaDefId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecBySchemaDefIdx");
	}

	/**
	 *	Read an array of the specific Table record instances identified by the duplicate key DefSchemaIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	DefSchemaId	The Table key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamTable[] readRecByDefSchemaIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argDefSchemaId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByDefSchemaIdx");
	}

	/**
	 *	Read the specific Table record instance identified by the unique key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SchemaDefId	The Table key attribute of the instance generating the id.
	 *
	 *	@param	Name	The Table key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamTable readRecByUNameIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argSchemaDefId,
		String argName )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByUNameIdx");
	}

	/**
	 *	Read the specific Table record instance identified by the unique key SchemaCdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SchemaDefId	The Table key attribute of the instance generating the id.
	 *
	 *	@param	TableClassCode	The Table key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamTable readRecBySchemaCdIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argSchemaDefId,
		String argTableClassCode )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecBySchemaCdIdx");
	}

	/**
	 *	Read an array of the specific Table record instances identified by the duplicate key PrimaryIndexIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PrimaryIndexId	The Table key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamTable[] readRecByPrimaryIndexIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argPrimaryIndexId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByPrimaryIndexIdx");
	}

	/**
	 *	Read an array of the specific Table record instances identified by the duplicate key LookupIndexIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	LookupIndexId	The Table key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamTable[] readRecByLookupIndexIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argLookupIndexId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByLookupIndexIdx");
	}

	/**
	 *	Read an array of the specific Table record instances identified by the duplicate key AltIndexIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	AltIndexId	The Table key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamTable[] readRecByAltIndexIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argAltIndexId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByAltIndexIdx");
	}

	/**
	 *	Read an array of the specific Table record instances identified by the duplicate key QualTableIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	QualifyingTableId	The Table key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	@Override
	public ICFBamTable[] readRecByQualTableIdx( ICFSecAuthorization Authorization,
		CFLibDbKeyHash256 argQualifyingTableId )
	{
		throw new CFLibNotImplementedYetException(getClass(), "readRecByQualTableIdx");
	}
}
