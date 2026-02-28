// Description: Java 25 Spring JPA Service for CFBam

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

import java.io.Serializable;
import java.math.*;
import java.net.InetAddress;
import java.time.*;
import java.util.*;
import jakarta.persistence.*;
import io.github.msobkow.v3_1.cflib.*;
import io.github.msobkow.v3_1.cflib.dbutil.*;
import io.github.msobkow.v3_1.cflib.xml.CFLibXmlUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import io.github.msobkow.v3_1.cfsec.cfsec.*;
import io.github.msobkow.v3_1.cfint.cfint.*;
import io.github.msobkow.v3_1.cfbam.cfbam.*;

/**
 *	Services for schema CFBam defined in io.github.msobkow.v3_1.cfbam.cfbam.jpa
 *	using the CFBam*Repository objects to access the data directly, bypassing normal application security for the bootstrap and login processing.
 */
@Service("cfbam31JpaSchemaService")
public class CFBamJpaSchemaService {

	@Autowired
	@Qualifier("cfbam31EntityManagerFactory")
	private LocalContainerEntityManagerFactoryBean cfbam31EntityManagerFactory;
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


	public void bootstrapSchema() {
		ICFSecSchema.getBackingCFSec().bootstrapSchema();
		ICFIntSchema.getBackingCFInt().bootstrapSchema();
		bootstrapAllTablesSecurity();
	}

	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "$secdbschemaname$TransactionManager")
	public void bootstrapAllTablesSecurity() {
		LocalDateTime now = LocalDateTime.now();
		ICFSecSecSession bootstrapSession;
		CFLibDbKeyHash256 bootstrapSessionID = new CFLibDbKeyHash256(0);

		ICFSecAuthorization auth = new CFSecAuthorization();
		auth.setAuthUuid6(CFLibUuid6.generateUuid6());
		auth.setSecClusterId(ICFSecSchema.getSysClusterId());
		auth.setSecTenantId(ICFSecSchema.getSysTenantId());
		auth.setSecSessionId(bootstrapSessionID);
//ICFSecSchema.getSysTenantId(), ICFSecSchema.getSysAdminId()
		bootstrapSession = ICFSecSchema.getBackingCFSec().getFactorySecSession().newRec();
		bootstrapSession.setRequiredRevision(1);
		bootstrapSession.setRequiredSecSessionId(bootstrapSessionID);
		bootstrapSession.setRequiredSecUserId(ICFSecSchema.getSysAdminId());
		bootstrapSession.setOptionalSecProxyId(ICFSecSchema.getSysAdminId());
		bootstrapSession.setOptionalSecDevName(null);
		bootstrapSession.setRequiredStart(now);
		bootstrapSession.setOptionalFinish(null);
		bootstrapSession = ICFSecSchema.getBackingCFSec().getTableSecSession().createSecSession(auth, bootstrapSession);
		bootstrapSessionID = bootstrapSession.getRequiredSecSessionId();

		bootstrapTableSecurity(auth, "Scope", true, false);
		bootstrapTableSecurity(auth, "SchemaDef", true, false);
		bootstrapTableSecurity(auth, "SchemaRef", true, false);
		bootstrapTableSecurity(auth, "ServerMethod", true, false);
		bootstrapTableSecurity(auth, "ServerObjFunc", true, false);
		bootstrapTableSecurity(auth, "ServerProc", true, false);
		bootstrapTableSecurity(auth, "Table", true, false);
		bootstrapTableSecurity(auth, "Value", true, false);
		bootstrapTableSecurity(auth, "Atom", true, false);
		bootstrapTableSecurity(auth, "BlobDef", true, false);
		bootstrapTableSecurity(auth, "BlobType", true, false);
		bootstrapTableSecurity(auth, "BoolDef", true, false);
		bootstrapTableSecurity(auth, "BoolType", true, false);
		bootstrapTableSecurity(auth, "Chain", true, false);
		bootstrapTableSecurity(auth, "ClearDep", true, false);
		bootstrapTableSecurity(auth, "ClearSubDep1", true, false);
		bootstrapTableSecurity(auth, "ClearSubDep2", true, false);
		bootstrapTableSecurity(auth, "ClearSubDep3", true, false);
		bootstrapTableSecurity(auth, "ClearTopDep", true, false);
		bootstrapTableSecurity(auth, "DateDef", true, false);
		bootstrapTableSecurity(auth, "DateType", true, false);
		bootstrapTableSecurity(auth, "DelDep", true, false);
		bootstrapTableSecurity(auth, "DelSubDep1", true, false);
		bootstrapTableSecurity(auth, "DelSubDep2", true, false);
		bootstrapTableSecurity(auth, "DelSubDep3", true, false);
		bootstrapTableSecurity(auth, "DelTopDep", true, false);
		bootstrapTableSecurity(auth, "DoubleDef", true, false);
		bootstrapTableSecurity(auth, "DoubleType", true, false);
		bootstrapTableSecurity(auth, "EnumTag", true, false);
		bootstrapTableSecurity(auth, "FloatDef", true, false);
		bootstrapTableSecurity(auth, "FloatType", true, false);
		bootstrapTableSecurity(auth, "Index", true, false);
		bootstrapTableSecurity(auth, "IndexCol", true, false);
		bootstrapTableSecurity(auth, "Int16Def", true, false);
		bootstrapTableSecurity(auth, "Int16Type", true, false);
		bootstrapTableSecurity(auth, "Int32Def", true, false);
		bootstrapTableSecurity(auth, "Int32Type", true, false);
		bootstrapTableSecurity(auth, "Int64Def", true, false);
		bootstrapTableSecurity(auth, "Int64Type", true, false);
		bootstrapTableSecurity(auth, "NmTokenDef", true, false);
		bootstrapTableSecurity(auth, "NmTokenType", true, false);
		bootstrapTableSecurity(auth, "NmTokensDef", true, false);
		bootstrapTableSecurity(auth, "NmTokensType", true, false);
		bootstrapTableSecurity(auth, "NumberDef", true, false);
		bootstrapTableSecurity(auth, "NumberType", true, false);
		bootstrapTableSecurity(auth, "Param", true, false);
		bootstrapTableSecurity(auth, "PopDep", true, false);
		bootstrapTableSecurity(auth, "PopSubDep1", true, false);
		bootstrapTableSecurity(auth, "PopSubDep2", true, false);
		bootstrapTableSecurity(auth, "PopSubDep3", true, false);
		bootstrapTableSecurity(auth, "PopTopDep", true, false);
		bootstrapTableSecurity(auth, "Relation", true, false);
		bootstrapTableSecurity(auth, "RelationCol", true, false);
		bootstrapTableSecurity(auth, "ServerListFunc", true, false);
		bootstrapTableSecurity(auth, "DbKeyHash128Def", true, false);
		bootstrapTableSecurity(auth, "DbKeyHash128Col", true, false);
		bootstrapTableSecurity(auth, "DbKeyHash128Type", true, false);
		bootstrapTableSecurity(auth, "DbKeyHash128Gen", true, false);
		bootstrapTableSecurity(auth, "DbKeyHash160Def", true, false);
		bootstrapTableSecurity(auth, "DbKeyHash160Col", true, false);
		bootstrapTableSecurity(auth, "DbKeyHash160Type", true, false);
		bootstrapTableSecurity(auth, "DbKeyHash160Gen", true, false);
		bootstrapTableSecurity(auth, "DbKeyHash224Def", true, false);
		bootstrapTableSecurity(auth, "DbKeyHash224Col", true, false);
		bootstrapTableSecurity(auth, "DbKeyHash224Type", true, false);
		bootstrapTableSecurity(auth, "DbKeyHash224Gen", true, false);
		bootstrapTableSecurity(auth, "DbKeyHash256Def", true, false);
		bootstrapTableSecurity(auth, "DbKeyHash256Col", true, false);
		bootstrapTableSecurity(auth, "DbKeyHash256Type", true, false);
		bootstrapTableSecurity(auth, "DbKeyHash256Gen", true, false);
		bootstrapTableSecurity(auth, "DbKeyHash384Def", true, false);
		bootstrapTableSecurity(auth, "DbKeyHash384Col", true, false);
		bootstrapTableSecurity(auth, "DbKeyHash384Type", true, false);
		bootstrapTableSecurity(auth, "DbKeyHash384Gen", true, false);
		bootstrapTableSecurity(auth, "DbKeyHash512Def", true, false);
		bootstrapTableSecurity(auth, "DbKeyHash512Col", true, false);
		bootstrapTableSecurity(auth, "DbKeyHash512Type", true, false);
		bootstrapTableSecurity(auth, "DbKeyHash512Gen", true, false);
		bootstrapTableSecurity(auth, "StringDef", true, false);
		bootstrapTableSecurity(auth, "StringType", true, false);
		bootstrapTableSecurity(auth, "TZDateDef", true, false);
		bootstrapTableSecurity(auth, "TZDateType", true, false);
		bootstrapTableSecurity(auth, "TZTimeDef", true, false);
		bootstrapTableSecurity(auth, "TZTimeType", true, false);
		bootstrapTableSecurity(auth, "TZTimestampDef", true, false);
		bootstrapTableSecurity(auth, "TZTimestampType", true, false);
		bootstrapTableSecurity(auth, "TableCol", true, false);
		bootstrapTableSecurity(auth, "TextDef", true, false);
		bootstrapTableSecurity(auth, "TextType", true, false);
		bootstrapTableSecurity(auth, "TimeDef", true, false);
		bootstrapTableSecurity(auth, "TimeType", true, false);
		bootstrapTableSecurity(auth, "TimestampDef", true, false);
		bootstrapTableSecurity(auth, "TimestampType", true, false);
		bootstrapTableSecurity(auth, "TokenDef", true, false);
		bootstrapTableSecurity(auth, "TokenType", true, false);
		bootstrapTableSecurity(auth, "UInt16Def", true, false);
		bootstrapTableSecurity(auth, "UInt16Type", true, false);
		bootstrapTableSecurity(auth, "UInt32Def", true, false);
		bootstrapTableSecurity(auth, "UInt32Type", true, false);
		bootstrapTableSecurity(auth, "UInt64Def", true, false);
		bootstrapTableSecurity(auth, "UInt64Type", true, false);
		bootstrapTableSecurity(auth, "UuidDef", true, false);
		bootstrapTableSecurity(auth, "Uuid6Def", true, false);
		bootstrapTableSecurity(auth, "UuidType", true, false);
		bootstrapTableSecurity(auth, "Uuid6Type", true, false);
		bootstrapTableSecurity(auth, "BlobCol", true, false);
		bootstrapTableSecurity(auth, "BoolCol", true, false);
		bootstrapTableSecurity(auth, "DateCol", true, false);
		bootstrapTableSecurity(auth, "DoubleCol", true, false);
		bootstrapTableSecurity(auth, "EnumDef", true, false);
		bootstrapTableSecurity(auth, "EnumType", true, false);
		bootstrapTableSecurity(auth, "FloatCol", true, false);
		bootstrapTableSecurity(auth, "Id16Gen", true, false);
		bootstrapTableSecurity(auth, "Id32Gen", true, false);
		bootstrapTableSecurity(auth, "Id64Gen", true, false);
		bootstrapTableSecurity(auth, "Int16Col", true, false);
		bootstrapTableSecurity(auth, "Int32Col", true, false);
		bootstrapTableSecurity(auth, "Int64Col", true, false);
		bootstrapTableSecurity(auth, "NmTokenCol", true, false);
		bootstrapTableSecurity(auth, "NmTokensCol", true, false);
		bootstrapTableSecurity(auth, "NumberCol", true, false);
		bootstrapTableSecurity(auth, "StringCol", true, false);
		bootstrapTableSecurity(auth, "TZDateCol", true, false);
		bootstrapTableSecurity(auth, "TZTimeCol", true, false);
		bootstrapTableSecurity(auth, "TZTimestampCol", true, false);
		bootstrapTableSecurity(auth, "TextCol", true, false);
		bootstrapTableSecurity(auth, "TimeCol", true, false);
		bootstrapTableSecurity(auth, "TimestampCol", true, false);
		bootstrapTableSecurity(auth, "TokenCol", true, false);
		bootstrapTableSecurity(auth, "UInt16Col", true, false);
		bootstrapTableSecurity(auth, "UInt32Col", true, false);
		bootstrapTableSecurity(auth, "UInt64Col", true, false);
		bootstrapTableSecurity(auth, "UuidCol", true, false);
		bootstrapTableSecurity(auth, "Uuid6Col", true, false);
		bootstrapTableSecurity(auth, "UuidGen", true, false);
		bootstrapTableSecurity(auth, "Uuid6Gen", true, false);

		if (bootstrapSession != null && bootstrapSessionID != null && !bootstrapSessionID.isNull() && bootstrapSession.getOptionalFinish() == null) {
			bootstrapSession.setOptionalFinish(LocalDateTime.now());
			bootstrapSession = ICFSecSchema.getBackingCFSec().getTableSecSession().updateSecSession(auth, bootstrapSession);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NoResultException.class, transactionManager = "$secdbschemaname$TransactionManager")
	public void bootstrapTableSecurity(ICFSecAuthorization auth, String tableName, boolean hasHistory, boolean isMutable) {
		LocalDateTime now = LocalDateTime.now();
		String lowerTableName = tableName.toLowerCase();
		String createPermName = "create" + lowerTableName;
		String readPermName = "read" + lowerTableName;
		String updatePermName = "update" + lowerTableName;
		String deletePermName = "delete" + lowerTableName;
		String restorePermName = "restore" + lowerTableName;
		String mutatePermName = "mutate" + lowerTableName;
		ICFSecSecGroup secGroupCreate;
		CFLibDbKeyHash256 secGroupCreateID;
		ICFSecSecGrpMemb secGroupCreateMembSysadmin;
		CFLibDbKeyHash256 secGroupCreateMembSysadminID;
		ICFSecSecGroup secGroupRead;
		CFLibDbKeyHash256 secGroupReadID;
		ICFSecSecGrpMemb secGroupReadMembSysadmin;
		CFLibDbKeyHash256 secGroupReadMembSysadminID;
		ICFSecSecGroup secGroupUpdate;
		CFLibDbKeyHash256 secGroupUpdateID;
		ICFSecSecGrpMemb secGroupUpdateMembSysadmin;
		CFLibDbKeyHash256 secGroupUpdateMembSysadminID;
		ICFSecSecGroup secGroupDelete;
		CFLibDbKeyHash256 secGroupDeleteID;
		ICFSecSecGrpMemb secGroupDeleteMembSysadmin;
		CFLibDbKeyHash256 secGroupDeleteMembSysadminID;
		ICFSecSecGroup secGroupRestore;
		CFLibDbKeyHash256 secGroupRestoreID;
		ICFSecSecGrpMemb secGroupRestoreMembSysadmin;
		CFLibDbKeyHash256 secGroupRestoreMembSysadminID;
		ICFSecSecGroup secGroupMutate;
		CFLibDbKeyHash256 secGroupMutateID;
		ICFSecSecGrpMemb secGroupMutateMembSysadmin;
		CFLibDbKeyHash256 secGroupMutateMembSysadminID;

		secGroupCreate = ICFSecSchema.getBackingCFSec().getTableSecGroup().readDerivedByUNameIdx(auth, ICFSecSchema.getSysClusterId(), createPermName);
		if (secGroupCreate != null) {
			secGroupCreateID = secGroupCreate.getRequiredSecGroupId();
			secGroupCreateMembSysadmin = ICFSecSchema.getBackingCFSec().getTableSecGrpMemb().readDerivedByUUserIdx(auth, ICFSecSchema.getSysClusterId(), secGroupCreateID, ICFSecSchema.getSysAdminId());
			if (secGroupCreateMembSysadmin != null) {
				secGroupCreateMembSysadminID = secGroupCreateMembSysadmin.getRequiredSecGrpMembId();
			}
			else {
				secGroupCreateMembSysadminID = null;
			}
		}
		else {
			secGroupCreateID = null;
			secGroupCreateMembSysadmin = null;
			secGroupCreateMembSysadminID = null;
		}

		secGroupRead = ICFSecSchema.getBackingCFSec().getTableSecGroup().readDerivedByUNameIdx(auth, ICFSecSchema.getSysClusterId(), readPermName);
		if (secGroupRead != null) {
			secGroupReadID = secGroupRead.getRequiredSecGroupId();
			secGroupReadMembSysadmin = ICFSecSchema.getBackingCFSec().getTableSecGrpMemb().readDerivedByUUserIdx(auth, ICFSecSchema.getSysClusterId(), secGroupReadID, ICFSecSchema.getSysAdminId());
			if (secGroupReadMembSysadmin != null) {
				secGroupReadMembSysadminID = secGroupReadMembSysadmin.getRequiredSecGrpMembId();
			}
			else {
				secGroupReadMembSysadminID = null;
			}
		}
		else {
			secGroupReadID = null;
			secGroupReadMembSysadmin = null;
			secGroupReadMembSysadminID = null;
		}

		secGroupUpdate = ICFSecSchema.getBackingCFSec().getTableSecGroup().readDerivedByUNameIdx(auth, ICFSecSchema.getSysClusterId(), updatePermName);
		if (secGroupUpdate != null) {
			secGroupUpdateID = secGroupUpdate.getRequiredSecGroupId();
			secGroupUpdateMembSysadmin = ICFSecSchema.getBackingCFSec().getTableSecGrpMemb().readDerivedByUUserIdx(auth, ICFSecSchema.getSysClusterId(), secGroupUpdateID, ICFSecSchema.getSysAdminId());
			if (secGroupUpdateMembSysadmin != null) {
				secGroupUpdateMembSysadminID = secGroupUpdateMembSysadmin.getRequiredSecGrpMembId();
			}
			else {
				secGroupUpdateMembSysadminID = null;
			}
		}
		else {
			secGroupUpdateID = null;
			secGroupUpdateMembSysadmin = null;
			secGroupUpdateMembSysadminID = null;
		}

		secGroupDelete = ICFSecSchema.getBackingCFSec().getTableSecGroup().readDerivedByUNameIdx(auth, ICFSecSchema.getSysClusterId(), deletePermName);
		if (secGroupDelete != null) {
			secGroupDeleteID = secGroupDelete.getRequiredSecGroupId();
			secGroupDeleteMembSysadmin = ICFSecSchema.getBackingCFSec().getTableSecGrpMemb().readDerivedByUUserIdx(auth, ICFSecSchema.getSysClusterId(), secGroupDeleteID, ICFSecSchema.getSysAdminId());
			if (secGroupDeleteMembSysadmin != null) {
				secGroupDeleteMembSysadminID = secGroupDeleteMembSysadmin.getRequiredSecGrpMembId();
			}
			else {
				secGroupDeleteMembSysadminID = null;
			}
		}
		else {
			secGroupDeleteID = null;
			secGroupDeleteMembSysadmin = null;
			secGroupDeleteMembSysadminID = null;
		}

		if (hasHistory) {
			secGroupRestore = ICFSecSchema.getBackingCFSec().getTableSecGroup().readDerivedByUNameIdx(auth, ICFSecSchema.getSysClusterId(), restorePermName);
			if (secGroupRestore != null) {
				secGroupRestoreID = secGroupRestore.getRequiredSecGroupId();
				secGroupRestoreMembSysadmin = ICFSecSchema.getBackingCFSec().getTableSecGrpMemb().readDerivedByUUserIdx(auth, ICFSecSchema.getSysClusterId(), secGroupRestoreID, ICFSecSchema.getSysAdminId());
				if (secGroupRestoreMembSysadmin != null) {
					secGroupRestoreMembSysadminID = secGroupRestoreMembSysadmin.getRequiredSecGrpMembId();
				}
				else {
					secGroupRestoreMembSysadminID = null;
				}
			}
			else {
				secGroupRestoreID = null;
				secGroupRestoreMembSysadmin = null;
				secGroupRestoreMembSysadminID = null;
			}
		}
		else {
			secGroupRestore = null;
			secGroupRestoreID = null;
			secGroupRestoreMembSysadmin = null;
			secGroupRestoreMembSysadminID = null;
		}

		if (isMutable) {
			secGroupMutate = ICFSecSchema.getBackingCFSec().getTableSecGroup().readDerivedByUNameIdx(auth, ICFSecSchema.getSysClusterId(), mutatePermName);
			if (secGroupMutate != null) {
				secGroupMutateID = secGroupMutate.getRequiredSecGroupId();
				secGroupMutateMembSysadmin = ICFSecSchema.getBackingCFSec().getTableSecGrpMemb().readDerivedByUUserIdx(auth, ICFSecSchema.getSysClusterId(), secGroupMutateID, ICFSecSchema.getSysAdminId());
				if (secGroupMutateMembSysadmin != null) {
					secGroupMutateMembSysadminID = secGroupMutateMembSysadmin.getRequiredSecGrpMembId();
				}
				else {
					secGroupMutateMembSysadminID = null;
				}
			}
			else {
				secGroupMutateID = null;
				secGroupMutateMembSysadmin = null;
				secGroupMutateMembSysadminID = null;
			}
		}
		else {
			secGroupMutate = null;
			secGroupMutateID = null;
			secGroupMutateMembSysadmin = null;
			secGroupMutateMembSysadminID = null;
		}
		
		if (secGroupCreateID == null || secGroupCreateID.isNull()) {
			secGroupCreateID = new CFLibDbKeyHash256(0);
		}
		if (secGroupCreateMembSysadminID == null || secGroupCreateMembSysadminID.isNull()) {
			secGroupCreateMembSysadminID = new CFLibDbKeyHash256(0);
		}
		if (secGroupReadID == null || secGroupReadID.isNull()) {
			secGroupReadID = new CFLibDbKeyHash256(0);
		}
		if (secGroupReadMembSysadminID == null || secGroupReadMembSysadminID.isNull()) {
			secGroupReadMembSysadminID = new CFLibDbKeyHash256(0);
		}
		if (secGroupUpdateID == null || secGroupUpdateID.isNull()) {
			secGroupUpdateID = new CFLibDbKeyHash256(0);
		}
		if (secGroupUpdateMembSysadminID == null || secGroupUpdateMembSysadminID.isNull()) {
			secGroupUpdateMembSysadminID = new CFLibDbKeyHash256(0);
		}
		if (secGroupDeleteID == null || secGroupDeleteID.isNull()) {
			secGroupDeleteID = new CFLibDbKeyHash256(0);
		}
		if (secGroupDeleteMembSysadminID == null || secGroupDeleteMembSysadminID.isNull()) {
			secGroupDeleteMembSysadminID = new CFLibDbKeyHash256(0);
		}
		if (hasHistory) {
			if (secGroupRestoreID == null || secGroupRestoreID.isNull()) {
				secGroupRestoreID = new CFLibDbKeyHash256(0);
			}
			if (secGroupRestoreMembSysadminID == null || secGroupRestoreMembSysadminID.isNull()) {
				secGroupRestoreMembSysadminID = new CFLibDbKeyHash256(0);
			}
		}
		if (isMutable) {
			if (secGroupMutateID == null || secGroupMutateID.isNull()) {
				secGroupMutateID = new CFLibDbKeyHash256(0);
			}
			if (secGroupMutateMembSysadminID == null || secGroupMutateMembSysadminID.isNull()) {
				secGroupMutateMembSysadminID = new CFLibDbKeyHash256(0);
			}
		}

		if (secGroupCreate == null) {
			secGroupCreate = ICFSecSchema.getBackingCFSec().getFactorySecGroup().newRec();
			secGroupCreate.setRequiredRevision(1);
			secGroupCreate.setRequiredContainerCluster(ICFSecSchema.getSysClusterId());
			secGroupCreate.setRequiredName(createPermName);
			secGroupCreate.setRequiredIsVisible(true);
			secGroupCreate.setRequiredSecGroupId(secGroupCreateID);
			secGroupCreate = ICFSecSchema.getBackingCFSec().getTableSecGroup().createSecGroup(auth, secGroupCreate);
			secGroupCreateID = secGroupCreate.getRequiredSecGroupId();
		}

		if (secGroupCreateMembSysadmin == null) {
			secGroupCreateMembSysadmin = ICFSecSchema.getBackingCFSec().getFactorySecGrpMemb().newRec();
			secGroupCreateMembSysadmin.setRequiredRevision(1);
			secGroupCreateMembSysadmin.setRequiredOwnerCluster(ICFSecSchema.getSysClusterId());
			secGroupCreateMembSysadmin.setRequiredContainerGroup(secGroupCreateID);
			secGroupCreateMembSysadmin.setRequiredParentUser(ICFSecSchema.getSysAdminId());
			secGroupCreateMembSysadmin.setRequiredSecGrpMembId(secGroupCreateMembSysadminID);
			secGroupCreateMembSysadmin = ICFSecSchema.getBackingCFSec().getTableSecGrpMemb().createSecGrpMemb(auth, secGroupCreateMembSysadmin);
			secGroupCreateMembSysadminID = secGroupCreateMembSysadmin.getRequiredSecGrpMembId();
		}

		if (secGroupRead == null) {
			secGroupRead = ICFSecSchema.getBackingCFSec().getFactorySecGroup().newRec();
			secGroupRead.setRequiredRevision(1);
			secGroupRead.setRequiredContainerCluster(ICFSecSchema.getSysClusterId());
			secGroupRead.setRequiredName(readPermName);
			secGroupRead.setRequiredIsVisible(true);
			secGroupRead.setRequiredSecGroupId(secGroupReadID);
			secGroupRead = ICFSecSchema.getBackingCFSec().getTableSecGroup().createSecGroup(auth, secGroupRead);
			secGroupReadID = secGroupRead.getRequiredSecGroupId();
		}

		if (secGroupReadMembSysadmin == null) {
			secGroupReadMembSysadmin = ICFSecSchema.getBackingCFSec().getFactorySecGrpMemb().newRec();
			secGroupReadMembSysadmin.setRequiredRevision(1);
			secGroupReadMembSysadmin.setRequiredOwnerCluster(ICFSecSchema.getSysClusterId());
			secGroupReadMembSysadmin.setRequiredContainerGroup(secGroupReadID);
			secGroupReadMembSysadmin.setRequiredParentUser(ICFSecSchema.getSysAdminId());
			secGroupReadMembSysadmin.setRequiredSecGrpMembId(secGroupReadMembSysadminID);
			secGroupReadMembSysadmin = ICFSecSchema.getBackingCFSec().getTableSecGrpMemb().createSecGrpMemb(auth, secGroupReadMembSysadmin);
			secGroupReadMembSysadminID = secGroupReadMembSysadmin.getRequiredSecGrpMembId();
		}

		if (secGroupUpdate == null) {
			secGroupUpdate = ICFSecSchema.getBackingCFSec().getFactorySecGroup().newRec();
			secGroupUpdate.setRequiredRevision(1);
			secGroupUpdate.setRequiredContainerCluster(ICFSecSchema.getSysClusterId());
			secGroupUpdate.setRequiredName(updatePermName);
			secGroupUpdate.setRequiredIsVisible(true);
			secGroupUpdate.setRequiredSecGroupId(secGroupUpdateID);
			secGroupUpdate = ICFSecSchema.getBackingCFSec().getTableSecGroup().createSecGroup(auth, secGroupUpdate);
			secGroupUpdateID = secGroupUpdate.getRequiredSecGroupId();
		}

		if (secGroupUpdateMembSysadmin == null) {
			secGroupUpdateMembSysadmin = ICFSecSchema.getBackingCFSec().getFactorySecGrpMemb().newRec();
			secGroupUpdateMembSysadmin.setRequiredRevision(1);
			secGroupUpdateMembSysadmin.setRequiredOwnerCluster(ICFSecSchema.getSysClusterId());
			secGroupUpdateMembSysadmin.setRequiredContainerGroup(secGroupUpdateID);
			secGroupUpdateMembSysadmin.setRequiredParentUser(ICFSecSchema.getSysAdminId());
			secGroupUpdateMembSysadmin.setRequiredSecGrpMembId(secGroupUpdateMembSysadminID);
			secGroupUpdateMembSysadmin = ICFSecSchema.getBackingCFSec().getTableSecGrpMemb().createSecGrpMemb(auth, secGroupUpdateMembSysadmin);
			secGroupUpdateMembSysadminID = secGroupUpdateMembSysadmin.getRequiredSecGrpMembId();
		}

		if (secGroupDelete == null) {
			secGroupDelete = ICFSecSchema.getBackingCFSec().getFactorySecGroup().newRec();
			secGroupDelete.setRequiredRevision(1);
			secGroupDelete.setRequiredContainerCluster(ICFSecSchema.getSysClusterId());
			secGroupDelete.setRequiredName(deletePermName);
			secGroupDelete.setRequiredIsVisible(true);
			secGroupDelete.setRequiredSecGroupId(secGroupDeleteID);
			secGroupDelete = ICFSecSchema.getBackingCFSec().getTableSecGroup().createSecGroup(auth, secGroupDelete);
			secGroupDeleteID = secGroupDelete.getRequiredSecGroupId();
		}

		if (secGroupDeleteMembSysadmin == null) {
			secGroupDeleteMembSysadmin = ICFSecSchema.getBackingCFSec().getFactorySecGrpMemb().newRec();
			secGroupDeleteMembSysadmin.setRequiredRevision(1);
			secGroupDeleteMembSysadmin.setRequiredOwnerCluster(ICFSecSchema.getSysClusterId());
			secGroupDeleteMembSysadmin.setRequiredContainerGroup(secGroupDeleteID);
			secGroupDeleteMembSysadmin.setRequiredParentUser(ICFSecSchema.getSysAdminId());
			secGroupDeleteMembSysadmin.setRequiredSecGrpMembId(secGroupDeleteMembSysadminID);
			secGroupDeleteMembSysadmin = ICFSecSchema.getBackingCFSec().getTableSecGrpMemb().createSecGrpMemb(auth, secGroupDeleteMembSysadmin);
			secGroupDeleteMembSysadminID = secGroupDeleteMembSysadmin.getRequiredSecGrpMembId();
		}
		
		if (hasHistory) {
			if (secGroupRestore == null) {
				secGroupRestore = ICFSecSchema.getBackingCFSec().getFactorySecGroup().newRec();
				secGroupRestore.setRequiredRevision(1);
				secGroupRestore.setRequiredContainerCluster(ICFSecSchema.getSysClusterId());
				secGroupRestore.setRequiredName(restorePermName);
				secGroupRestore.setRequiredIsVisible(true);
				secGroupRestore.setRequiredSecGroupId(secGroupRestoreID);
				secGroupRestore = ICFSecSchema.getBackingCFSec().getTableSecGroup().createSecGroup(auth, secGroupRestore);
				secGroupRestoreID = secGroupRestore.getRequiredSecGroupId();
			}

			if (secGroupRestoreMembSysadmin == null) {
				secGroupRestoreMembSysadmin = ICFSecSchema.getBackingCFSec().getFactorySecGrpMemb().newRec();
				secGroupRestoreMembSysadmin.setRequiredRevision(1);
				secGroupRestoreMembSysadmin.setRequiredOwnerCluster(ICFSecSchema.getSysClusterId());
				secGroupRestoreMembSysadmin.setRequiredContainerGroup(secGroupRestoreID);
				secGroupRestoreMembSysadmin.setRequiredParentUser(ICFSecSchema.getSysAdminId());
				secGroupRestoreMembSysadmin.setRequiredSecGrpMembId(secGroupRestoreMembSysadminID);
				secGroupRestoreMembSysadmin = ICFSecSchema.getBackingCFSec().getTableSecGrpMemb().createSecGrpMemb(auth, secGroupRestoreMembSysadmin);
				secGroupRestoreMembSysadminID = secGroupRestoreMembSysadmin.getRequiredSecGrpMembId();
			}
		}
		
		if (isMutable) {
			if (secGroupMutate == null) {
				secGroupMutate = ICFSecSchema.getBackingCFSec().getFactorySecGroup().newRec();
				secGroupMutate.setRequiredRevision(1);
				secGroupMutate.setRequiredContainerCluster(ICFSecSchema.getSysClusterId());
				secGroupMutate.setRequiredName(mutatePermName);
				secGroupMutate.setRequiredIsVisible(true);
				secGroupMutate.setRequiredSecGroupId(secGroupMutateID);
				secGroupMutate = ICFSecSchema.getBackingCFSec().getTableSecGroup().createSecGroup(auth, secGroupMutate);
				secGroupMutateID = secGroupMutate.getRequiredSecGroupId();
			}

			if (secGroupMutateMembSysadmin == null) {
				secGroupMutateMembSysadmin = ICFSecSchema.getBackingCFSec().getFactorySecGrpMemb().newRec();
				secGroupMutateMembSysadmin.setRequiredRevision(1);
				secGroupMutateMembSysadmin.setRequiredOwnerCluster(ICFSecSchema.getSysClusterId());
				secGroupMutateMembSysadmin.setRequiredContainerGroup(secGroupMutateID);
				secGroupMutateMembSysadmin.setRequiredParentUser(ICFSecSchema.getSysAdminId());
				secGroupMutateMembSysadmin.setRequiredSecGrpMembId(secGroupMutateMembSysadminID);
				secGroupMutateMembSysadmin = ICFSecSchema.getBackingCFSec().getTableSecGrpMemb().createSecGrpMemb(auth, secGroupMutateMembSysadmin);
				secGroupMutateMembSysadminID = secGroupMutateMembSysadmin.getRequiredSecGrpMembId();
			}
		}
	}		


}
