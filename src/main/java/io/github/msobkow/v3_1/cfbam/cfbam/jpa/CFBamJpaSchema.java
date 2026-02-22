// Description: Java 25 JPA implementation of a CFBam schema.

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
//package io.github.msobkow.v3_1.cfbam.cfbam.jpa;

import java.io.Serializable;
import java.math.*;
import java.time.*;
import java.net.InetAddress;
import java.util.*;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import io.github.msobkow.v3_1.cflib.*;
import io.github.msobkow.v3_1.cflib.dbutil.*;
import io.github.msobkow.v3_1.cflib.inz.Inz;
import io.github.msobkow.v3_1.cflib.xml.CFLibXmlUtil;
import io.github.msobkow.v3_1.cfsec.cfsec.*;
import io.github.msobkow.v3_1.cfint.cfint.*;
import io.github.msobkow.v3_1.cfbam.cfbam.*;
import io.github.msobkow.v3_1.cfsec.cfsec.jpa.*;
import io.github.msobkow.v3_1.cfint.cfint.jpa.*;

public class CFBamJpaSchema
	implements ICFBamSchema,
		ICFSecSchema,
		ICFIntSchema, ApplicationContextAware
{
	private static ApplicationContext applicationContext = null;
	private CFBamJpaSchemaService cfbamJpaSchemaService = null;

	protected ICFBamAtomTable tableAtom;
	protected ICFBamBlobColTable tableBlobCol;
	protected ICFBamBlobDefTable tableBlobDef;
	protected ICFBamBlobTypeTable tableBlobType;
	protected ICFBamBoolColTable tableBoolCol;
	protected ICFBamBoolDefTable tableBoolDef;
	protected ICFBamBoolTypeTable tableBoolType;
	protected ICFBamChainTable tableChain;
	protected ICFBamClearDepTable tableClearDep;
	protected ICFBamClearSubDep1Table tableClearSubDep1;
	protected ICFBamClearSubDep2Table tableClearSubDep2;
	protected ICFBamClearSubDep3Table tableClearSubDep3;
	protected ICFBamClearTopDepTable tableClearTopDep;
	protected ICFSecClusterTable tableCluster;
	protected ICFBamDateColTable tableDateCol;
	protected ICFBamDateDefTable tableDateDef;
	protected ICFBamDateTypeTable tableDateType;
	protected ICFBamDbKeyHash128ColTable tableDbKeyHash128Col;
	protected ICFBamDbKeyHash128DefTable tableDbKeyHash128Def;
	protected ICFBamDbKeyHash128GenTable tableDbKeyHash128Gen;
	protected ICFBamDbKeyHash128TypeTable tableDbKeyHash128Type;
	protected ICFBamDbKeyHash160ColTable tableDbKeyHash160Col;
	protected ICFBamDbKeyHash160DefTable tableDbKeyHash160Def;
	protected ICFBamDbKeyHash160GenTable tableDbKeyHash160Gen;
	protected ICFBamDbKeyHash160TypeTable tableDbKeyHash160Type;
	protected ICFBamDbKeyHash224ColTable tableDbKeyHash224Col;
	protected ICFBamDbKeyHash224DefTable tableDbKeyHash224Def;
	protected ICFBamDbKeyHash224GenTable tableDbKeyHash224Gen;
	protected ICFBamDbKeyHash224TypeTable tableDbKeyHash224Type;
	protected ICFBamDbKeyHash256ColTable tableDbKeyHash256Col;
	protected ICFBamDbKeyHash256DefTable tableDbKeyHash256Def;
	protected ICFBamDbKeyHash256GenTable tableDbKeyHash256Gen;
	protected ICFBamDbKeyHash256TypeTable tableDbKeyHash256Type;
	protected ICFBamDbKeyHash384ColTable tableDbKeyHash384Col;
	protected ICFBamDbKeyHash384DefTable tableDbKeyHash384Def;
	protected ICFBamDbKeyHash384GenTable tableDbKeyHash384Gen;
	protected ICFBamDbKeyHash384TypeTable tableDbKeyHash384Type;
	protected ICFBamDbKeyHash512ColTable tableDbKeyHash512Col;
	protected ICFBamDbKeyHash512DefTable tableDbKeyHash512Def;
	protected ICFBamDbKeyHash512GenTable tableDbKeyHash512Gen;
	protected ICFBamDbKeyHash512TypeTable tableDbKeyHash512Type;
	protected ICFBamDelDepTable tableDelDep;
	protected ICFBamDelSubDep1Table tableDelSubDep1;
	protected ICFBamDelSubDep2Table tableDelSubDep2;
	protected ICFBamDelSubDep3Table tableDelSubDep3;
	protected ICFBamDelTopDepTable tableDelTopDep;
	protected ICFBamDoubleColTable tableDoubleCol;
	protected ICFBamDoubleDefTable tableDoubleDef;
	protected ICFBamDoubleTypeTable tableDoubleType;
	protected ICFBamEnumDefTable tableEnumDef;
	protected ICFBamEnumTagTable tableEnumTag;
	protected ICFBamEnumTypeTable tableEnumType;
	protected ICFBamFloatColTable tableFloatCol;
	protected ICFBamFloatDefTable tableFloatDef;
	protected ICFBamFloatTypeTable tableFloatType;
	protected ICFSecHostNodeTable tableHostNode;
	protected ICFSecISOCcyTable tableISOCcy;
	protected ICFSecISOCtryTable tableISOCtry;
	protected ICFSecISOCtryCcyTable tableISOCtryCcy;
	protected ICFSecISOCtryLangTable tableISOCtryLang;
	protected ICFSecISOLangTable tableISOLang;
	protected ICFSecISOTZoneTable tableISOTZone;
	protected ICFBamId16GenTable tableId16Gen;
	protected ICFBamId32GenTable tableId32Gen;
	protected ICFBamId64GenTable tableId64Gen;
	protected ICFBamIndexTable tableIndex;
	protected ICFBamIndexColTable tableIndexCol;
	protected ICFBamInt16ColTable tableInt16Col;
	protected ICFBamInt16DefTable tableInt16Def;
	protected ICFBamInt16TypeTable tableInt16Type;
	protected ICFBamInt32ColTable tableInt32Col;
	protected ICFBamInt32DefTable tableInt32Def;
	protected ICFBamInt32TypeTable tableInt32Type;
	protected ICFBamInt64ColTable tableInt64Col;
	protected ICFBamInt64DefTable tableInt64Def;
	protected ICFBamInt64TypeTable tableInt64Type;
	protected ICFIntLicenseTable tableLicense;
	protected ICFIntMajorVersionTable tableMajorVersion;
	protected ICFIntMimeTypeTable tableMimeType;
	protected ICFIntMinorVersionTable tableMinorVersion;
	protected ICFBamNmTokenColTable tableNmTokenCol;
	protected ICFBamNmTokenDefTable tableNmTokenDef;
	protected ICFBamNmTokenTypeTable tableNmTokenType;
	protected ICFBamNmTokensColTable tableNmTokensCol;
	protected ICFBamNmTokensDefTable tableNmTokensDef;
	protected ICFBamNmTokensTypeTable tableNmTokensType;
	protected ICFBamNumberColTable tableNumberCol;
	protected ICFBamNumberDefTable tableNumberDef;
	protected ICFBamNumberTypeTable tableNumberType;
	protected ICFBamParamTable tableParam;
	protected ICFBamPopDepTable tablePopDep;
	protected ICFBamPopSubDep1Table tablePopSubDep1;
	protected ICFBamPopSubDep2Table tablePopSubDep2;
	protected ICFBamPopSubDep3Table tablePopSubDep3;
	protected ICFBamPopTopDepTable tablePopTopDep;
	protected ICFBamRelationTable tableRelation;
	protected ICFBamRelationColTable tableRelationCol;
	protected ICFBamSchemaDefTable tableSchemaDef;
	protected ICFBamSchemaRefTable tableSchemaRef;
	protected ICFBamScopeTable tableScope;
	protected ICFSecSecDeviceTable tableSecDevice;
	protected ICFSecSecGroupTable tableSecGroup;
	protected ICFSecSecGrpIncTable tableSecGrpInc;
	protected ICFSecSecGrpMembTable tableSecGrpMemb;
	protected ICFSecSecSessionTable tableSecSession;
	protected ICFSecSecUserTable tableSecUser;
	protected ICFBamServerListFuncTable tableServerListFunc;
	protected ICFBamServerMethodTable tableServerMethod;
	protected ICFBamServerObjFuncTable tableServerObjFunc;
	protected ICFBamServerProcTable tableServerProc;
	protected ICFSecServiceTable tableService;
	protected ICFSecServiceTypeTable tableServiceType;
	protected ICFBamStringColTable tableStringCol;
	protected ICFBamStringDefTable tableStringDef;
	protected ICFBamStringTypeTable tableStringType;
	protected ICFIntSubProjectTable tableSubProject;
	protected ICFSecSysClusterTable tableSysCluster;
	protected ICFSecTSecGroupTable tableTSecGroup;
	protected ICFSecTSecGrpIncTable tableTSecGrpInc;
	protected ICFSecTSecGrpMembTable tableTSecGrpMemb;
	protected ICFBamTZDateColTable tableTZDateCol;
	protected ICFBamTZDateDefTable tableTZDateDef;
	protected ICFBamTZDateTypeTable tableTZDateType;
	protected ICFBamTZTimeColTable tableTZTimeCol;
	protected ICFBamTZTimeDefTable tableTZTimeDef;
	protected ICFBamTZTimeTypeTable tableTZTimeType;
	protected ICFBamTZTimestampColTable tableTZTimestampCol;
	protected ICFBamTZTimestampDefTable tableTZTimestampDef;
	protected ICFBamTZTimestampTypeTable tableTZTimestampType;
	protected ICFBamTableTable tableTable;
	protected ICFBamTableColTable tableTableCol;
	protected ICFSecTenantTable tableTenant;
	protected ICFBamTextColTable tableTextCol;
	protected ICFBamTextDefTable tableTextDef;
	protected ICFBamTextTypeTable tableTextType;
	protected ICFBamTimeColTable tableTimeCol;
	protected ICFBamTimeDefTable tableTimeDef;
	protected ICFBamTimeTypeTable tableTimeType;
	protected ICFBamTimestampColTable tableTimestampCol;
	protected ICFBamTimestampDefTable tableTimestampDef;
	protected ICFBamTimestampTypeTable tableTimestampType;
	protected ICFIntTldTable tableTld;
	protected ICFBamTokenColTable tableTokenCol;
	protected ICFBamTokenDefTable tableTokenDef;
	protected ICFBamTokenTypeTable tableTokenType;
	protected ICFIntTopDomainTable tableTopDomain;
	protected ICFIntTopProjectTable tableTopProject;
	protected ICFBamUInt16ColTable tableUInt16Col;
	protected ICFBamUInt16DefTable tableUInt16Def;
	protected ICFBamUInt16TypeTable tableUInt16Type;
	protected ICFBamUInt32ColTable tableUInt32Col;
	protected ICFBamUInt32DefTable tableUInt32Def;
	protected ICFBamUInt32TypeTable tableUInt32Type;
	protected ICFBamUInt64ColTable tableUInt64Col;
	protected ICFBamUInt64DefTable tableUInt64Def;
	protected ICFBamUInt64TypeTable tableUInt64Type;
	protected ICFIntURLProtocolTable tableURLProtocol;
	protected ICFBamUuid6ColTable tableUuid6Col;
	protected ICFBamUuid6DefTable tableUuid6Def;
	protected ICFBamUuid6GenTable tableUuid6Gen;
	protected ICFBamUuid6TypeTable tableUuid6Type;
	protected ICFBamUuidColTable tableUuidCol;
	protected ICFBamUuidDefTable tableUuidDef;
	protected ICFBamUuidGenTable tableUuidGen;
	protected ICFBamUuidTypeTable tableUuidType;
	protected ICFBamValueTable tableValue;

	protected ICFBamAtomFactory factoryAtom;
	protected ICFBamBlobColFactory factoryBlobCol;
	protected ICFBamBlobDefFactory factoryBlobDef;
	protected ICFBamBlobTypeFactory factoryBlobType;
	protected ICFBamBoolColFactory factoryBoolCol;
	protected ICFBamBoolDefFactory factoryBoolDef;
	protected ICFBamBoolTypeFactory factoryBoolType;
	protected ICFBamChainFactory factoryChain;
	protected ICFBamClearDepFactory factoryClearDep;
	protected ICFBamClearSubDep1Factory factoryClearSubDep1;
	protected ICFBamClearSubDep2Factory factoryClearSubDep2;
	protected ICFBamClearSubDep3Factory factoryClearSubDep3;
	protected ICFBamClearTopDepFactory factoryClearTopDep;
	protected ICFSecClusterFactory factoryCluster;
	protected ICFBamDateColFactory factoryDateCol;
	protected ICFBamDateDefFactory factoryDateDef;
	protected ICFBamDateTypeFactory factoryDateType;
	protected ICFBamDbKeyHash128ColFactory factoryDbKeyHash128Col;
	protected ICFBamDbKeyHash128DefFactory factoryDbKeyHash128Def;
	protected ICFBamDbKeyHash128GenFactory factoryDbKeyHash128Gen;
	protected ICFBamDbKeyHash128TypeFactory factoryDbKeyHash128Type;
	protected ICFBamDbKeyHash160ColFactory factoryDbKeyHash160Col;
	protected ICFBamDbKeyHash160DefFactory factoryDbKeyHash160Def;
	protected ICFBamDbKeyHash160GenFactory factoryDbKeyHash160Gen;
	protected ICFBamDbKeyHash160TypeFactory factoryDbKeyHash160Type;
	protected ICFBamDbKeyHash224ColFactory factoryDbKeyHash224Col;
	protected ICFBamDbKeyHash224DefFactory factoryDbKeyHash224Def;
	protected ICFBamDbKeyHash224GenFactory factoryDbKeyHash224Gen;
	protected ICFBamDbKeyHash224TypeFactory factoryDbKeyHash224Type;
	protected ICFBamDbKeyHash256ColFactory factoryDbKeyHash256Col;
	protected ICFBamDbKeyHash256DefFactory factoryDbKeyHash256Def;
	protected ICFBamDbKeyHash256GenFactory factoryDbKeyHash256Gen;
	protected ICFBamDbKeyHash256TypeFactory factoryDbKeyHash256Type;
	protected ICFBamDbKeyHash384ColFactory factoryDbKeyHash384Col;
	protected ICFBamDbKeyHash384DefFactory factoryDbKeyHash384Def;
	protected ICFBamDbKeyHash384GenFactory factoryDbKeyHash384Gen;
	protected ICFBamDbKeyHash384TypeFactory factoryDbKeyHash384Type;
	protected ICFBamDbKeyHash512ColFactory factoryDbKeyHash512Col;
	protected ICFBamDbKeyHash512DefFactory factoryDbKeyHash512Def;
	protected ICFBamDbKeyHash512GenFactory factoryDbKeyHash512Gen;
	protected ICFBamDbKeyHash512TypeFactory factoryDbKeyHash512Type;
	protected ICFBamDelDepFactory factoryDelDep;
	protected ICFBamDelSubDep1Factory factoryDelSubDep1;
	protected ICFBamDelSubDep2Factory factoryDelSubDep2;
	protected ICFBamDelSubDep3Factory factoryDelSubDep3;
	protected ICFBamDelTopDepFactory factoryDelTopDep;
	protected ICFBamDoubleColFactory factoryDoubleCol;
	protected ICFBamDoubleDefFactory factoryDoubleDef;
	protected ICFBamDoubleTypeFactory factoryDoubleType;
	protected ICFBamEnumDefFactory factoryEnumDef;
	protected ICFBamEnumTagFactory factoryEnumTag;
	protected ICFBamEnumTypeFactory factoryEnumType;
	protected ICFBamFloatColFactory factoryFloatCol;
	protected ICFBamFloatDefFactory factoryFloatDef;
	protected ICFBamFloatTypeFactory factoryFloatType;
	protected ICFSecHostNodeFactory factoryHostNode;
	protected ICFSecISOCcyFactory factoryISOCcy;
	protected ICFSecISOCtryFactory factoryISOCtry;
	protected ICFSecISOCtryCcyFactory factoryISOCtryCcy;
	protected ICFSecISOCtryLangFactory factoryISOCtryLang;
	protected ICFSecISOLangFactory factoryISOLang;
	protected ICFSecISOTZoneFactory factoryISOTZone;
	protected ICFBamId16GenFactory factoryId16Gen;
	protected ICFBamId32GenFactory factoryId32Gen;
	protected ICFBamId64GenFactory factoryId64Gen;
	protected ICFBamIndexFactory factoryIndex;
	protected ICFBamIndexColFactory factoryIndexCol;
	protected ICFBamInt16ColFactory factoryInt16Col;
	protected ICFBamInt16DefFactory factoryInt16Def;
	protected ICFBamInt16TypeFactory factoryInt16Type;
	protected ICFBamInt32ColFactory factoryInt32Col;
	protected ICFBamInt32DefFactory factoryInt32Def;
	protected ICFBamInt32TypeFactory factoryInt32Type;
	protected ICFBamInt64ColFactory factoryInt64Col;
	protected ICFBamInt64DefFactory factoryInt64Def;
	protected ICFBamInt64TypeFactory factoryInt64Type;
	protected ICFIntLicenseFactory factoryLicense;
	protected ICFIntMajorVersionFactory factoryMajorVersion;
	protected ICFIntMimeTypeFactory factoryMimeType;
	protected ICFIntMinorVersionFactory factoryMinorVersion;
	protected ICFBamNmTokenColFactory factoryNmTokenCol;
	protected ICFBamNmTokenDefFactory factoryNmTokenDef;
	protected ICFBamNmTokenTypeFactory factoryNmTokenType;
	protected ICFBamNmTokensColFactory factoryNmTokensCol;
	protected ICFBamNmTokensDefFactory factoryNmTokensDef;
	protected ICFBamNmTokensTypeFactory factoryNmTokensType;
	protected ICFBamNumberColFactory factoryNumberCol;
	protected ICFBamNumberDefFactory factoryNumberDef;
	protected ICFBamNumberTypeFactory factoryNumberType;
	protected ICFBamParamFactory factoryParam;
	protected ICFBamPopDepFactory factoryPopDep;
	protected ICFBamPopSubDep1Factory factoryPopSubDep1;
	protected ICFBamPopSubDep2Factory factoryPopSubDep2;
	protected ICFBamPopSubDep3Factory factoryPopSubDep3;
	protected ICFBamPopTopDepFactory factoryPopTopDep;
	protected ICFBamRelationFactory factoryRelation;
	protected ICFBamRelationColFactory factoryRelationCol;
	protected ICFBamSchemaDefFactory factorySchemaDef;
	protected ICFBamSchemaRefFactory factorySchemaRef;
	protected ICFBamScopeFactory factoryScope;
	protected ICFSecSecDeviceFactory factorySecDevice;
	protected ICFSecSecGroupFactory factorySecGroup;
	protected ICFSecSecGrpIncFactory factorySecGrpInc;
	protected ICFSecSecGrpMembFactory factorySecGrpMemb;
	protected ICFSecSecSessionFactory factorySecSession;
	protected ICFSecSecUserFactory factorySecUser;
	protected ICFBamServerListFuncFactory factoryServerListFunc;
	protected ICFBamServerMethodFactory factoryServerMethod;
	protected ICFBamServerObjFuncFactory factoryServerObjFunc;
	protected ICFBamServerProcFactory factoryServerProc;
	protected ICFSecServiceFactory factoryService;
	protected ICFSecServiceTypeFactory factoryServiceType;
	protected ICFBamStringColFactory factoryStringCol;
	protected ICFBamStringDefFactory factoryStringDef;
	protected ICFBamStringTypeFactory factoryStringType;
	protected ICFIntSubProjectFactory factorySubProject;
	protected ICFSecSysClusterFactory factorySysCluster;
	protected ICFSecTSecGroupFactory factoryTSecGroup;
	protected ICFSecTSecGrpIncFactory factoryTSecGrpInc;
	protected ICFSecTSecGrpMembFactory factoryTSecGrpMemb;
	protected ICFBamTZDateColFactory factoryTZDateCol;
	protected ICFBamTZDateDefFactory factoryTZDateDef;
	protected ICFBamTZDateTypeFactory factoryTZDateType;
	protected ICFBamTZTimeColFactory factoryTZTimeCol;
	protected ICFBamTZTimeDefFactory factoryTZTimeDef;
	protected ICFBamTZTimeTypeFactory factoryTZTimeType;
	protected ICFBamTZTimestampColFactory factoryTZTimestampCol;
	protected ICFBamTZTimestampDefFactory factoryTZTimestampDef;
	protected ICFBamTZTimestampTypeFactory factoryTZTimestampType;
	protected ICFBamTableFactory factoryTable;
	protected ICFBamTableColFactory factoryTableCol;
	protected ICFSecTenantFactory factoryTenant;
	protected ICFBamTextColFactory factoryTextCol;
	protected ICFBamTextDefFactory factoryTextDef;
	protected ICFBamTextTypeFactory factoryTextType;
	protected ICFBamTimeColFactory factoryTimeCol;
	protected ICFBamTimeDefFactory factoryTimeDef;
	protected ICFBamTimeTypeFactory factoryTimeType;
	protected ICFBamTimestampColFactory factoryTimestampCol;
	protected ICFBamTimestampDefFactory factoryTimestampDef;
	protected ICFBamTimestampTypeFactory factoryTimestampType;
	protected ICFIntTldFactory factoryTld;
	protected ICFBamTokenColFactory factoryTokenCol;
	protected ICFBamTokenDefFactory factoryTokenDef;
	protected ICFBamTokenTypeFactory factoryTokenType;
	protected ICFIntTopDomainFactory factoryTopDomain;
	protected ICFIntTopProjectFactory factoryTopProject;
	protected ICFBamUInt16ColFactory factoryUInt16Col;
	protected ICFBamUInt16DefFactory factoryUInt16Def;
	protected ICFBamUInt16TypeFactory factoryUInt16Type;
	protected ICFBamUInt32ColFactory factoryUInt32Col;
	protected ICFBamUInt32DefFactory factoryUInt32Def;
	protected ICFBamUInt32TypeFactory factoryUInt32Type;
	protected ICFBamUInt64ColFactory factoryUInt64Col;
	protected ICFBamUInt64DefFactory factoryUInt64Def;
	protected ICFBamUInt64TypeFactory factoryUInt64Type;
	protected ICFIntURLProtocolFactory factoryURLProtocol;
	protected ICFBamUuid6ColFactory factoryUuid6Col;
	protected ICFBamUuid6DefFactory factoryUuid6Def;
	protected ICFBamUuid6GenFactory factoryUuid6Gen;
	protected ICFBamUuid6TypeFactory factoryUuid6Type;
	protected ICFBamUuidColFactory factoryUuidCol;
	protected ICFBamUuidDefFactory factoryUuidDef;
	protected ICFBamUuidGenFactory factoryUuidGen;
	protected ICFBamUuidTypeFactory factoryUuidType;
	protected ICFBamValueFactory factoryValue;


	@Override
	public int initClassMapEntries(int value) {
		return( ICFBamSchema.doInitClassMapEntries(value) );
	}

	@Override
	public void wireRecConstructors() {
		ICFSecSchema.ClassMapEntry entry;
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamScope.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamScope ret = new CFBamJpaScope();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamScope.CLASS_CODE)[" + ICFBamScope.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamSchemaDef.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamSchemaDef ret = new CFBamJpaSchemaDef();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamSchemaDef.CLASS_CODE)[" + ICFBamSchemaDef.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamSchemaRef.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamSchemaRef ret = new CFBamJpaSchemaRef();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamSchemaRef.CLASS_CODE)[" + ICFBamSchemaRef.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamServerMethod.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamServerMethod ret = new CFBamJpaServerMethod();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamServerMethod.CLASS_CODE)[" + ICFBamServerMethod.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamServerObjFunc.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamServerObjFunc ret = new CFBamJpaServerObjFunc();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamServerObjFunc.CLASS_CODE)[" + ICFBamServerObjFunc.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamServerProc.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamServerProc ret = new CFBamJpaServerProc();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamServerProc.CLASS_CODE)[" + ICFBamServerProc.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamTable.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamTable ret = new CFBamJpaTable();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamTable.CLASS_CODE)[" + ICFBamTable.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamValue.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamValue ret = new CFBamJpaValue();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamValue.CLASS_CODE)[" + ICFBamValue.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamAtom.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamAtom ret = new CFBamJpaAtom();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamAtom.CLASS_CODE)[" + ICFBamAtom.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamBlobDef.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamBlobDef ret = new CFBamJpaBlobDef();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamBlobDef.CLASS_CODE)[" + ICFBamBlobDef.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamBlobType.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamBlobType ret = new CFBamJpaBlobType();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamBlobType.CLASS_CODE)[" + ICFBamBlobType.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamBoolDef.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamBoolDef ret = new CFBamJpaBoolDef();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamBoolDef.CLASS_CODE)[" + ICFBamBoolDef.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamBoolType.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamBoolType ret = new CFBamJpaBoolType();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamBoolType.CLASS_CODE)[" + ICFBamBoolType.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamChain.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamChain ret = new CFBamJpaChain();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamChain.CLASS_CODE)[" + ICFBamChain.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamClearDep.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamClearDep ret = new CFBamJpaClearDep();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamClearDep.CLASS_CODE)[" + ICFBamClearDep.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamClearSubDep1.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamClearSubDep1 ret = new CFBamJpaClearSubDep1();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamClearSubDep1.CLASS_CODE)[" + ICFBamClearSubDep1.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamClearSubDep2.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamClearSubDep2 ret = new CFBamJpaClearSubDep2();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamClearSubDep2.CLASS_CODE)[" + ICFBamClearSubDep2.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamClearSubDep3.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamClearSubDep3 ret = new CFBamJpaClearSubDep3();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamClearSubDep3.CLASS_CODE)[" + ICFBamClearSubDep3.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamClearTopDep.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamClearTopDep ret = new CFBamJpaClearTopDep();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamClearTopDep.CLASS_CODE)[" + ICFBamClearTopDep.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDateDef.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDateDef ret = new CFBamJpaDateDef();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDateDef.CLASS_CODE)[" + ICFBamDateDef.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDateType.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDateType ret = new CFBamJpaDateType();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDateType.CLASS_CODE)[" + ICFBamDateType.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDelDep.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDelDep ret = new CFBamJpaDelDep();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDelDep.CLASS_CODE)[" + ICFBamDelDep.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDelSubDep1.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDelSubDep1 ret = new CFBamJpaDelSubDep1();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDelSubDep1.CLASS_CODE)[" + ICFBamDelSubDep1.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDelSubDep2.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDelSubDep2 ret = new CFBamJpaDelSubDep2();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDelSubDep2.CLASS_CODE)[" + ICFBamDelSubDep2.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDelSubDep3.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDelSubDep3 ret = new CFBamJpaDelSubDep3();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDelSubDep3.CLASS_CODE)[" + ICFBamDelSubDep3.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDelTopDep.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDelTopDep ret = new CFBamJpaDelTopDep();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDelTopDep.CLASS_CODE)[" + ICFBamDelTopDep.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDoubleDef.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDoubleDef ret = new CFBamJpaDoubleDef();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDoubleDef.CLASS_CODE)[" + ICFBamDoubleDef.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDoubleType.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDoubleType ret = new CFBamJpaDoubleType();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDoubleType.CLASS_CODE)[" + ICFBamDoubleType.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamEnumTag.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamEnumTag ret = new CFBamJpaEnumTag();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamEnumTag.CLASS_CODE)[" + ICFBamEnumTag.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamFloatDef.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamFloatDef ret = new CFBamJpaFloatDef();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamFloatDef.CLASS_CODE)[" + ICFBamFloatDef.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamFloatType.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamFloatType ret = new CFBamJpaFloatType();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamFloatType.CLASS_CODE)[" + ICFBamFloatType.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamIndex.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamIndex ret = new CFBamJpaIndex();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamIndex.CLASS_CODE)[" + ICFBamIndex.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamIndexCol.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamIndexCol ret = new CFBamJpaIndexCol();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamIndexCol.CLASS_CODE)[" + ICFBamIndexCol.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamInt16Def.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamInt16Def ret = new CFBamJpaInt16Def();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamInt16Def.CLASS_CODE)[" + ICFBamInt16Def.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamInt16Type.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamInt16Type ret = new CFBamJpaInt16Type();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamInt16Type.CLASS_CODE)[" + ICFBamInt16Type.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamInt32Def.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamInt32Def ret = new CFBamJpaInt32Def();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamInt32Def.CLASS_CODE)[" + ICFBamInt32Def.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamInt32Type.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamInt32Type ret = new CFBamJpaInt32Type();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamInt32Type.CLASS_CODE)[" + ICFBamInt32Type.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamInt64Def.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamInt64Def ret = new CFBamJpaInt64Def();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamInt64Def.CLASS_CODE)[" + ICFBamInt64Def.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamInt64Type.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamInt64Type ret = new CFBamJpaInt64Type();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamInt64Type.CLASS_CODE)[" + ICFBamInt64Type.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamNmTokenDef.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamNmTokenDef ret = new CFBamJpaNmTokenDef();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamNmTokenDef.CLASS_CODE)[" + ICFBamNmTokenDef.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamNmTokenType.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamNmTokenType ret = new CFBamJpaNmTokenType();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamNmTokenType.CLASS_CODE)[" + ICFBamNmTokenType.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamNmTokensDef.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamNmTokensDef ret = new CFBamJpaNmTokensDef();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamNmTokensDef.CLASS_CODE)[" + ICFBamNmTokensDef.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamNmTokensType.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamNmTokensType ret = new CFBamJpaNmTokensType();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamNmTokensType.CLASS_CODE)[" + ICFBamNmTokensType.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamNumberDef.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamNumberDef ret = new CFBamJpaNumberDef();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamNumberDef.CLASS_CODE)[" + ICFBamNumberDef.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamNumberType.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamNumberType ret = new CFBamJpaNumberType();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamNumberType.CLASS_CODE)[" + ICFBamNumberType.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamParam.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamParam ret = new CFBamJpaParam();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamParam.CLASS_CODE)[" + ICFBamParam.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamPopDep.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamPopDep ret = new CFBamJpaPopDep();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamPopDep.CLASS_CODE)[" + ICFBamPopDep.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamPopSubDep1.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamPopSubDep1 ret = new CFBamJpaPopSubDep1();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamPopSubDep1.CLASS_CODE)[" + ICFBamPopSubDep1.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamPopSubDep2.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamPopSubDep2 ret = new CFBamJpaPopSubDep2();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamPopSubDep2.CLASS_CODE)[" + ICFBamPopSubDep2.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamPopSubDep3.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamPopSubDep3 ret = new CFBamJpaPopSubDep3();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamPopSubDep3.CLASS_CODE)[" + ICFBamPopSubDep3.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamPopTopDep.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamPopTopDep ret = new CFBamJpaPopTopDep();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamPopTopDep.CLASS_CODE)[" + ICFBamPopTopDep.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamRelation.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamRelation ret = new CFBamJpaRelation();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamRelation.CLASS_CODE)[" + ICFBamRelation.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamRelationCol.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamRelationCol ret = new CFBamJpaRelationCol();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamRelationCol.CLASS_CODE)[" + ICFBamRelationCol.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamServerListFunc.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamServerListFunc ret = new CFBamJpaServerListFunc();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamServerListFunc.CLASS_CODE)[" + ICFBamServerListFunc.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash128Def.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDbKeyHash128Def ret = new CFBamJpaDbKeyHash128Def();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash128Def.CLASS_CODE)[" + ICFBamDbKeyHash128Def.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash128Col.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDbKeyHash128Col ret = new CFBamJpaDbKeyHash128Col();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash128Col.CLASS_CODE)[" + ICFBamDbKeyHash128Col.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash128Type.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDbKeyHash128Type ret = new CFBamJpaDbKeyHash128Type();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash128Type.CLASS_CODE)[" + ICFBamDbKeyHash128Type.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash128Gen.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDbKeyHash128Gen ret = new CFBamJpaDbKeyHash128Gen();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash128Gen.CLASS_CODE)[" + ICFBamDbKeyHash128Gen.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash160Def.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDbKeyHash160Def ret = new CFBamJpaDbKeyHash160Def();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash160Def.CLASS_CODE)[" + ICFBamDbKeyHash160Def.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash160Col.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDbKeyHash160Col ret = new CFBamJpaDbKeyHash160Col();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash160Col.CLASS_CODE)[" + ICFBamDbKeyHash160Col.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash160Type.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDbKeyHash160Type ret = new CFBamJpaDbKeyHash160Type();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash160Type.CLASS_CODE)[" + ICFBamDbKeyHash160Type.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash160Gen.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDbKeyHash160Gen ret = new CFBamJpaDbKeyHash160Gen();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash160Gen.CLASS_CODE)[" + ICFBamDbKeyHash160Gen.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash224Def.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDbKeyHash224Def ret = new CFBamJpaDbKeyHash224Def();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash224Def.CLASS_CODE)[" + ICFBamDbKeyHash224Def.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash224Col.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDbKeyHash224Col ret = new CFBamJpaDbKeyHash224Col();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash224Col.CLASS_CODE)[" + ICFBamDbKeyHash224Col.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash224Type.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDbKeyHash224Type ret = new CFBamJpaDbKeyHash224Type();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash224Type.CLASS_CODE)[" + ICFBamDbKeyHash224Type.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash224Gen.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDbKeyHash224Gen ret = new CFBamJpaDbKeyHash224Gen();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash224Gen.CLASS_CODE)[" + ICFBamDbKeyHash224Gen.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash256Def.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDbKeyHash256Def ret = new CFBamJpaDbKeyHash256Def();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash256Def.CLASS_CODE)[" + ICFBamDbKeyHash256Def.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash256Col.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDbKeyHash256Col ret = new CFBamJpaDbKeyHash256Col();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash256Col.CLASS_CODE)[" + ICFBamDbKeyHash256Col.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash256Type.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDbKeyHash256Type ret = new CFBamJpaDbKeyHash256Type();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash256Type.CLASS_CODE)[" + ICFBamDbKeyHash256Type.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash256Gen.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDbKeyHash256Gen ret = new CFBamJpaDbKeyHash256Gen();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash256Gen.CLASS_CODE)[" + ICFBamDbKeyHash256Gen.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash384Def.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDbKeyHash384Def ret = new CFBamJpaDbKeyHash384Def();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash384Def.CLASS_CODE)[" + ICFBamDbKeyHash384Def.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash384Col.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDbKeyHash384Col ret = new CFBamJpaDbKeyHash384Col();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash384Col.CLASS_CODE)[" + ICFBamDbKeyHash384Col.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash384Type.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDbKeyHash384Type ret = new CFBamJpaDbKeyHash384Type();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash384Type.CLASS_CODE)[" + ICFBamDbKeyHash384Type.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash384Gen.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDbKeyHash384Gen ret = new CFBamJpaDbKeyHash384Gen();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash384Gen.CLASS_CODE)[" + ICFBamDbKeyHash384Gen.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash512Def.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDbKeyHash512Def ret = new CFBamJpaDbKeyHash512Def();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash512Def.CLASS_CODE)[" + ICFBamDbKeyHash512Def.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash512Col.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDbKeyHash512Col ret = new CFBamJpaDbKeyHash512Col();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash512Col.CLASS_CODE)[" + ICFBamDbKeyHash512Col.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash512Type.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDbKeyHash512Type ret = new CFBamJpaDbKeyHash512Type();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash512Type.CLASS_CODE)[" + ICFBamDbKeyHash512Type.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash512Gen.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDbKeyHash512Gen ret = new CFBamJpaDbKeyHash512Gen();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash512Gen.CLASS_CODE)[" + ICFBamDbKeyHash512Gen.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamStringDef.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamStringDef ret = new CFBamJpaStringDef();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamStringDef.CLASS_CODE)[" + ICFBamStringDef.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamStringType.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamStringType ret = new CFBamJpaStringType();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamStringType.CLASS_CODE)[" + ICFBamStringType.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamTZDateDef.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamTZDateDef ret = new CFBamJpaTZDateDef();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamTZDateDef.CLASS_CODE)[" + ICFBamTZDateDef.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamTZDateType.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamTZDateType ret = new CFBamJpaTZDateType();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamTZDateType.CLASS_CODE)[" + ICFBamTZDateType.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamTZTimeDef.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamTZTimeDef ret = new CFBamJpaTZTimeDef();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamTZTimeDef.CLASS_CODE)[" + ICFBamTZTimeDef.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamTZTimeType.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamTZTimeType ret = new CFBamJpaTZTimeType();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamTZTimeType.CLASS_CODE)[" + ICFBamTZTimeType.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamTZTimestampDef.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamTZTimestampDef ret = new CFBamJpaTZTimestampDef();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamTZTimestampDef.CLASS_CODE)[" + ICFBamTZTimestampDef.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamTZTimestampType.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamTZTimestampType ret = new CFBamJpaTZTimestampType();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamTZTimestampType.CLASS_CODE)[" + ICFBamTZTimestampType.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamTableCol.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamTableCol ret = new CFBamJpaTableCol();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamTableCol.CLASS_CODE)[" + ICFBamTableCol.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamTextDef.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamTextDef ret = new CFBamJpaTextDef();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamTextDef.CLASS_CODE)[" + ICFBamTextDef.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamTextType.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamTextType ret = new CFBamJpaTextType();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamTextType.CLASS_CODE)[" + ICFBamTextType.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamTimeDef.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamTimeDef ret = new CFBamJpaTimeDef();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamTimeDef.CLASS_CODE)[" + ICFBamTimeDef.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamTimeType.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamTimeType ret = new CFBamJpaTimeType();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamTimeType.CLASS_CODE)[" + ICFBamTimeType.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamTimestampDef.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamTimestampDef ret = new CFBamJpaTimestampDef();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamTimestampDef.CLASS_CODE)[" + ICFBamTimestampDef.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamTimestampType.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamTimestampType ret = new CFBamJpaTimestampType();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamTimestampType.CLASS_CODE)[" + ICFBamTimestampType.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamTokenDef.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamTokenDef ret = new CFBamJpaTokenDef();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamTokenDef.CLASS_CODE)[" + ICFBamTokenDef.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamTokenType.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamTokenType ret = new CFBamJpaTokenType();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamTokenType.CLASS_CODE)[" + ICFBamTokenType.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamUInt16Def.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamUInt16Def ret = new CFBamJpaUInt16Def();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamUInt16Def.CLASS_CODE)[" + ICFBamUInt16Def.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamUInt16Type.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamUInt16Type ret = new CFBamJpaUInt16Type();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamUInt16Type.CLASS_CODE)[" + ICFBamUInt16Type.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamUInt32Def.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamUInt32Def ret = new CFBamJpaUInt32Def();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamUInt32Def.CLASS_CODE)[" + ICFBamUInt32Def.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamUInt32Type.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamUInt32Type ret = new CFBamJpaUInt32Type();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamUInt32Type.CLASS_CODE)[" + ICFBamUInt32Type.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamUInt64Def.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamUInt64Def ret = new CFBamJpaUInt64Def();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamUInt64Def.CLASS_CODE)[" + ICFBamUInt64Def.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamUInt64Type.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamUInt64Type ret = new CFBamJpaUInt64Type();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamUInt64Type.CLASS_CODE)[" + ICFBamUInt64Type.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamUuidDef.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamUuidDef ret = new CFBamJpaUuidDef();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamUuidDef.CLASS_CODE)[" + ICFBamUuidDef.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamUuid6Def.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamUuid6Def ret = new CFBamJpaUuid6Def();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamUuid6Def.CLASS_CODE)[" + ICFBamUuid6Def.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamUuidType.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamUuidType ret = new CFBamJpaUuidType();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamUuidType.CLASS_CODE)[" + ICFBamUuidType.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamUuid6Type.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamUuid6Type ret = new CFBamJpaUuid6Type();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamUuid6Type.CLASS_CODE)[" + ICFBamUuid6Type.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamBlobCol.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamBlobCol ret = new CFBamJpaBlobCol();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamBlobCol.CLASS_CODE)[" + ICFBamBlobCol.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamBoolCol.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamBoolCol ret = new CFBamJpaBoolCol();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamBoolCol.CLASS_CODE)[" + ICFBamBoolCol.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDateCol.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDateCol ret = new CFBamJpaDateCol();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDateCol.CLASS_CODE)[" + ICFBamDateCol.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDoubleCol.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDoubleCol ret = new CFBamJpaDoubleCol();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDoubleCol.CLASS_CODE)[" + ICFBamDoubleCol.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamEnumDef.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamEnumDef ret = new CFBamJpaEnumDef();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamEnumDef.CLASS_CODE)[" + ICFBamEnumDef.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamEnumType.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamEnumType ret = new CFBamJpaEnumType();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamEnumType.CLASS_CODE)[" + ICFBamEnumType.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamFloatCol.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamFloatCol ret = new CFBamJpaFloatCol();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamFloatCol.CLASS_CODE)[" + ICFBamFloatCol.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamId16Gen.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamId16Gen ret = new CFBamJpaId16Gen();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamId16Gen.CLASS_CODE)[" + ICFBamId16Gen.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamId32Gen.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamId32Gen ret = new CFBamJpaId32Gen();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamId32Gen.CLASS_CODE)[" + ICFBamId32Gen.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamId64Gen.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamId64Gen ret = new CFBamJpaId64Gen();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamId64Gen.CLASS_CODE)[" + ICFBamId64Gen.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamInt16Col.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamInt16Col ret = new CFBamJpaInt16Col();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamInt16Col.CLASS_CODE)[" + ICFBamInt16Col.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamInt32Col.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamInt32Col ret = new CFBamJpaInt32Col();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamInt32Col.CLASS_CODE)[" + ICFBamInt32Col.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamInt64Col.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamInt64Col ret = new CFBamJpaInt64Col();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamInt64Col.CLASS_CODE)[" + ICFBamInt64Col.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamNmTokenCol.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamNmTokenCol ret = new CFBamJpaNmTokenCol();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamNmTokenCol.CLASS_CODE)[" + ICFBamNmTokenCol.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamNmTokensCol.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamNmTokensCol ret = new CFBamJpaNmTokensCol();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamNmTokensCol.CLASS_CODE)[" + ICFBamNmTokensCol.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamNumberCol.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamNumberCol ret = new CFBamJpaNumberCol();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamNumberCol.CLASS_CODE)[" + ICFBamNumberCol.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamStringCol.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamStringCol ret = new CFBamJpaStringCol();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamStringCol.CLASS_CODE)[" + ICFBamStringCol.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamTZDateCol.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamTZDateCol ret = new CFBamJpaTZDateCol();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamTZDateCol.CLASS_CODE)[" + ICFBamTZDateCol.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamTZTimeCol.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamTZTimeCol ret = new CFBamJpaTZTimeCol();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamTZTimeCol.CLASS_CODE)[" + ICFBamTZTimeCol.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamTZTimestampCol.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamTZTimestampCol ret = new CFBamJpaTZTimestampCol();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamTZTimestampCol.CLASS_CODE)[" + ICFBamTZTimestampCol.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamTextCol.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamTextCol ret = new CFBamJpaTextCol();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamTextCol.CLASS_CODE)[" + ICFBamTextCol.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamTimeCol.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamTimeCol ret = new CFBamJpaTimeCol();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamTimeCol.CLASS_CODE)[" + ICFBamTimeCol.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamTimestampCol.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamTimestampCol ret = new CFBamJpaTimestampCol();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamTimestampCol.CLASS_CODE)[" + ICFBamTimestampCol.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamTokenCol.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamTokenCol ret = new CFBamJpaTokenCol();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamTokenCol.CLASS_CODE)[" + ICFBamTokenCol.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamUInt16Col.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamUInt16Col ret = new CFBamJpaUInt16Col();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamUInt16Col.CLASS_CODE)[" + ICFBamUInt16Col.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamUInt32Col.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamUInt32Col ret = new CFBamJpaUInt32Col();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamUInt32Col.CLASS_CODE)[" + ICFBamUInt32Col.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamUInt64Col.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamUInt64Col ret = new CFBamJpaUInt64Col();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamUInt64Col.CLASS_CODE)[" + ICFBamUInt64Col.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamUuidCol.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamUuidCol ret = new CFBamJpaUuidCol();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamUuidCol.CLASS_CODE)[" + ICFBamUuidCol.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamUuid6Col.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamUuid6Col ret = new CFBamJpaUuid6Col();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamUuid6Col.CLASS_CODE)[" + ICFBamUuid6Col.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamUuidGen.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamUuidGen ret = new CFBamJpaUuidGen();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamUuidGen.CLASS_CODE)[" + ICFBamUuidGen.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamUuid6Gen.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamUuid6Gen ret = new CFBamJpaUuid6Gen();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamJpaSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamUuid6Gen.CLASS_CODE)[" + ICFBamUuid6Gen.CLASS_CODE + "]");
		}
	
	}

	@Override
	public void wireTableTableInstances() {
		if (tableScope == null || !(tableScope instanceof CFBamJpaScopeTable)) {
			tableScope = new CFBamJpaScopeTable(this);
		}
		if (tableSchemaDef == null || !(tableSchemaDef instanceof CFBamJpaSchemaDefTable)) {
			tableSchemaDef = new CFBamJpaSchemaDefTable(this);
		}
		if (tableSchemaRef == null || !(tableSchemaRef instanceof CFBamJpaSchemaRefTable)) {
			tableSchemaRef = new CFBamJpaSchemaRefTable(this);
		}
		if (tableServerMethod == null || !(tableServerMethod instanceof CFBamJpaServerMethodTable)) {
			tableServerMethod = new CFBamJpaServerMethodTable(this);
		}
		if (tableServerObjFunc == null || !(tableServerObjFunc instanceof CFBamJpaServerObjFuncTable)) {
			tableServerObjFunc = new CFBamJpaServerObjFuncTable(this);
		}
		if (tableServerProc == null || !(tableServerProc instanceof CFBamJpaServerProcTable)) {
			tableServerProc = new CFBamJpaServerProcTable(this);
		}
		if (tableTable == null || !(tableTable instanceof CFBamJpaTableTable)) {
			tableTable = new CFBamJpaTableTable(this);
		}
		if (tableValue == null || !(tableValue instanceof CFBamJpaValueTable)) {
			tableValue = new CFBamJpaValueTable(this);
		}
		if (tableAtom == null || !(tableAtom instanceof CFBamJpaAtomTable)) {
			tableAtom = new CFBamJpaAtomTable(this);
		}
		if (tableBlobDef == null || !(tableBlobDef instanceof CFBamJpaBlobDefTable)) {
			tableBlobDef = new CFBamJpaBlobDefTable(this);
		}
		if (tableBlobType == null || !(tableBlobType instanceof CFBamJpaBlobTypeTable)) {
			tableBlobType = new CFBamJpaBlobTypeTable(this);
		}
		if (tableBoolDef == null || !(tableBoolDef instanceof CFBamJpaBoolDefTable)) {
			tableBoolDef = new CFBamJpaBoolDefTable(this);
		}
		if (tableBoolType == null || !(tableBoolType instanceof CFBamJpaBoolTypeTable)) {
			tableBoolType = new CFBamJpaBoolTypeTable(this);
		}
		if (tableChain == null || !(tableChain instanceof CFBamJpaChainTable)) {
			tableChain = new CFBamJpaChainTable(this);
		}
		if (tableClearDep == null || !(tableClearDep instanceof CFBamJpaClearDepTable)) {
			tableClearDep = new CFBamJpaClearDepTable(this);
		}
		if (tableClearSubDep1 == null || !(tableClearSubDep1 instanceof CFBamJpaClearSubDep1Table)) {
			tableClearSubDep1 = new CFBamJpaClearSubDep1Table(this);
		}
		if (tableClearSubDep2 == null || !(tableClearSubDep2 instanceof CFBamJpaClearSubDep2Table)) {
			tableClearSubDep2 = new CFBamJpaClearSubDep2Table(this);
		}
		if (tableClearSubDep3 == null || !(tableClearSubDep3 instanceof CFBamJpaClearSubDep3Table)) {
			tableClearSubDep3 = new CFBamJpaClearSubDep3Table(this);
		}
		if (tableClearTopDep == null || !(tableClearTopDep instanceof CFBamJpaClearTopDepTable)) {
			tableClearTopDep = new CFBamJpaClearTopDepTable(this);
		}
		if (tableDateDef == null || !(tableDateDef instanceof CFBamJpaDateDefTable)) {
			tableDateDef = new CFBamJpaDateDefTable(this);
		}
		if (tableDateType == null || !(tableDateType instanceof CFBamJpaDateTypeTable)) {
			tableDateType = new CFBamJpaDateTypeTable(this);
		}
		if (tableDelDep == null || !(tableDelDep instanceof CFBamJpaDelDepTable)) {
			tableDelDep = new CFBamJpaDelDepTable(this);
		}
		if (tableDelSubDep1 == null || !(tableDelSubDep1 instanceof CFBamJpaDelSubDep1Table)) {
			tableDelSubDep1 = new CFBamJpaDelSubDep1Table(this);
		}
		if (tableDelSubDep2 == null || !(tableDelSubDep2 instanceof CFBamJpaDelSubDep2Table)) {
			tableDelSubDep2 = new CFBamJpaDelSubDep2Table(this);
		}
		if (tableDelSubDep3 == null || !(tableDelSubDep3 instanceof CFBamJpaDelSubDep3Table)) {
			tableDelSubDep3 = new CFBamJpaDelSubDep3Table(this);
		}
		if (tableDelTopDep == null || !(tableDelTopDep instanceof CFBamJpaDelTopDepTable)) {
			tableDelTopDep = new CFBamJpaDelTopDepTable(this);
		}
		if (tableDoubleDef == null || !(tableDoubleDef instanceof CFBamJpaDoubleDefTable)) {
			tableDoubleDef = new CFBamJpaDoubleDefTable(this);
		}
		if (tableDoubleType == null || !(tableDoubleType instanceof CFBamJpaDoubleTypeTable)) {
			tableDoubleType = new CFBamJpaDoubleTypeTable(this);
		}
		if (tableEnumTag == null || !(tableEnumTag instanceof CFBamJpaEnumTagTable)) {
			tableEnumTag = new CFBamJpaEnumTagTable(this);
		}
		if (tableFloatDef == null || !(tableFloatDef instanceof CFBamJpaFloatDefTable)) {
			tableFloatDef = new CFBamJpaFloatDefTable(this);
		}
		if (tableFloatType == null || !(tableFloatType instanceof CFBamJpaFloatTypeTable)) {
			tableFloatType = new CFBamJpaFloatTypeTable(this);
		}
		if (tableIndex == null || !(tableIndex instanceof CFBamJpaIndexTable)) {
			tableIndex = new CFBamJpaIndexTable(this);
		}
		if (tableIndexCol == null || !(tableIndexCol instanceof CFBamJpaIndexColTable)) {
			tableIndexCol = new CFBamJpaIndexColTable(this);
		}
		if (tableInt16Def == null || !(tableInt16Def instanceof CFBamJpaInt16DefTable)) {
			tableInt16Def = new CFBamJpaInt16DefTable(this);
		}
		if (tableInt16Type == null || !(tableInt16Type instanceof CFBamJpaInt16TypeTable)) {
			tableInt16Type = new CFBamJpaInt16TypeTable(this);
		}
		if (tableInt32Def == null || !(tableInt32Def instanceof CFBamJpaInt32DefTable)) {
			tableInt32Def = new CFBamJpaInt32DefTable(this);
		}
		if (tableInt32Type == null || !(tableInt32Type instanceof CFBamJpaInt32TypeTable)) {
			tableInt32Type = new CFBamJpaInt32TypeTable(this);
		}
		if (tableInt64Def == null || !(tableInt64Def instanceof CFBamJpaInt64DefTable)) {
			tableInt64Def = new CFBamJpaInt64DefTable(this);
		}
		if (tableInt64Type == null || !(tableInt64Type instanceof CFBamJpaInt64TypeTable)) {
			tableInt64Type = new CFBamJpaInt64TypeTable(this);
		}
		if (tableNmTokenDef == null || !(tableNmTokenDef instanceof CFBamJpaNmTokenDefTable)) {
			tableNmTokenDef = new CFBamJpaNmTokenDefTable(this);
		}
		if (tableNmTokenType == null || !(tableNmTokenType instanceof CFBamJpaNmTokenTypeTable)) {
			tableNmTokenType = new CFBamJpaNmTokenTypeTable(this);
		}
		if (tableNmTokensDef == null || !(tableNmTokensDef instanceof CFBamJpaNmTokensDefTable)) {
			tableNmTokensDef = new CFBamJpaNmTokensDefTable(this);
		}
		if (tableNmTokensType == null || !(tableNmTokensType instanceof CFBamJpaNmTokensTypeTable)) {
			tableNmTokensType = new CFBamJpaNmTokensTypeTable(this);
		}
		if (tableNumberDef == null || !(tableNumberDef instanceof CFBamJpaNumberDefTable)) {
			tableNumberDef = new CFBamJpaNumberDefTable(this);
		}
		if (tableNumberType == null || !(tableNumberType instanceof CFBamJpaNumberTypeTable)) {
			tableNumberType = new CFBamJpaNumberTypeTable(this);
		}
		if (tableParam == null || !(tableParam instanceof CFBamJpaParamTable)) {
			tableParam = new CFBamJpaParamTable(this);
		}
		if (tablePopDep == null || !(tablePopDep instanceof CFBamJpaPopDepTable)) {
			tablePopDep = new CFBamJpaPopDepTable(this);
		}
		if (tablePopSubDep1 == null || !(tablePopSubDep1 instanceof CFBamJpaPopSubDep1Table)) {
			tablePopSubDep1 = new CFBamJpaPopSubDep1Table(this);
		}
		if (tablePopSubDep2 == null || !(tablePopSubDep2 instanceof CFBamJpaPopSubDep2Table)) {
			tablePopSubDep2 = new CFBamJpaPopSubDep2Table(this);
		}
		if (tablePopSubDep3 == null || !(tablePopSubDep3 instanceof CFBamJpaPopSubDep3Table)) {
			tablePopSubDep3 = new CFBamJpaPopSubDep3Table(this);
		}
		if (tablePopTopDep == null || !(tablePopTopDep instanceof CFBamJpaPopTopDepTable)) {
			tablePopTopDep = new CFBamJpaPopTopDepTable(this);
		}
		if (tableRelation == null || !(tableRelation instanceof CFBamJpaRelationTable)) {
			tableRelation = new CFBamJpaRelationTable(this);
		}
		if (tableRelationCol == null || !(tableRelationCol instanceof CFBamJpaRelationColTable)) {
			tableRelationCol = new CFBamJpaRelationColTable(this);
		}
		if (tableServerListFunc == null || !(tableServerListFunc instanceof CFBamJpaServerListFuncTable)) {
			tableServerListFunc = new CFBamJpaServerListFuncTable(this);
		}
		if (tableDbKeyHash128Def == null || !(tableDbKeyHash128Def instanceof CFBamJpaDbKeyHash128DefTable)) {
			tableDbKeyHash128Def = new CFBamJpaDbKeyHash128DefTable(this);
		}
		if (tableDbKeyHash128Col == null || !(tableDbKeyHash128Col instanceof CFBamJpaDbKeyHash128ColTable)) {
			tableDbKeyHash128Col = new CFBamJpaDbKeyHash128ColTable(this);
		}
		if (tableDbKeyHash128Type == null || !(tableDbKeyHash128Type instanceof CFBamJpaDbKeyHash128TypeTable)) {
			tableDbKeyHash128Type = new CFBamJpaDbKeyHash128TypeTable(this);
		}
		if (tableDbKeyHash128Gen == null || !(tableDbKeyHash128Gen instanceof CFBamJpaDbKeyHash128GenTable)) {
			tableDbKeyHash128Gen = new CFBamJpaDbKeyHash128GenTable(this);
		}
		if (tableDbKeyHash160Def == null || !(tableDbKeyHash160Def instanceof CFBamJpaDbKeyHash160DefTable)) {
			tableDbKeyHash160Def = new CFBamJpaDbKeyHash160DefTable(this);
		}
		if (tableDbKeyHash160Col == null || !(tableDbKeyHash160Col instanceof CFBamJpaDbKeyHash160ColTable)) {
			tableDbKeyHash160Col = new CFBamJpaDbKeyHash160ColTable(this);
		}
		if (tableDbKeyHash160Type == null || !(tableDbKeyHash160Type instanceof CFBamJpaDbKeyHash160TypeTable)) {
			tableDbKeyHash160Type = new CFBamJpaDbKeyHash160TypeTable(this);
		}
		if (tableDbKeyHash160Gen == null || !(tableDbKeyHash160Gen instanceof CFBamJpaDbKeyHash160GenTable)) {
			tableDbKeyHash160Gen = new CFBamJpaDbKeyHash160GenTable(this);
		}
		if (tableDbKeyHash224Def == null || !(tableDbKeyHash224Def instanceof CFBamJpaDbKeyHash224DefTable)) {
			tableDbKeyHash224Def = new CFBamJpaDbKeyHash224DefTable(this);
		}
		if (tableDbKeyHash224Col == null || !(tableDbKeyHash224Col instanceof CFBamJpaDbKeyHash224ColTable)) {
			tableDbKeyHash224Col = new CFBamJpaDbKeyHash224ColTable(this);
		}
		if (tableDbKeyHash224Type == null || !(tableDbKeyHash224Type instanceof CFBamJpaDbKeyHash224TypeTable)) {
			tableDbKeyHash224Type = new CFBamJpaDbKeyHash224TypeTable(this);
		}
		if (tableDbKeyHash224Gen == null || !(tableDbKeyHash224Gen instanceof CFBamJpaDbKeyHash224GenTable)) {
			tableDbKeyHash224Gen = new CFBamJpaDbKeyHash224GenTable(this);
		}
		if (tableDbKeyHash256Def == null || !(tableDbKeyHash256Def instanceof CFBamJpaDbKeyHash256DefTable)) {
			tableDbKeyHash256Def = new CFBamJpaDbKeyHash256DefTable(this);
		}
		if (tableDbKeyHash256Col == null || !(tableDbKeyHash256Col instanceof CFBamJpaDbKeyHash256ColTable)) {
			tableDbKeyHash256Col = new CFBamJpaDbKeyHash256ColTable(this);
		}
		if (tableDbKeyHash256Type == null || !(tableDbKeyHash256Type instanceof CFBamJpaDbKeyHash256TypeTable)) {
			tableDbKeyHash256Type = new CFBamJpaDbKeyHash256TypeTable(this);
		}
		if (tableDbKeyHash256Gen == null || !(tableDbKeyHash256Gen instanceof CFBamJpaDbKeyHash256GenTable)) {
			tableDbKeyHash256Gen = new CFBamJpaDbKeyHash256GenTable(this);
		}
		if (tableDbKeyHash384Def == null || !(tableDbKeyHash384Def instanceof CFBamJpaDbKeyHash384DefTable)) {
			tableDbKeyHash384Def = new CFBamJpaDbKeyHash384DefTable(this);
		}
		if (tableDbKeyHash384Col == null || !(tableDbKeyHash384Col instanceof CFBamJpaDbKeyHash384ColTable)) {
			tableDbKeyHash384Col = new CFBamJpaDbKeyHash384ColTable(this);
		}
		if (tableDbKeyHash384Type == null || !(tableDbKeyHash384Type instanceof CFBamJpaDbKeyHash384TypeTable)) {
			tableDbKeyHash384Type = new CFBamJpaDbKeyHash384TypeTable(this);
		}
		if (tableDbKeyHash384Gen == null || !(tableDbKeyHash384Gen instanceof CFBamJpaDbKeyHash384GenTable)) {
			tableDbKeyHash384Gen = new CFBamJpaDbKeyHash384GenTable(this);
		}
		if (tableDbKeyHash512Def == null || !(tableDbKeyHash512Def instanceof CFBamJpaDbKeyHash512DefTable)) {
			tableDbKeyHash512Def = new CFBamJpaDbKeyHash512DefTable(this);
		}
		if (tableDbKeyHash512Col == null || !(tableDbKeyHash512Col instanceof CFBamJpaDbKeyHash512ColTable)) {
			tableDbKeyHash512Col = new CFBamJpaDbKeyHash512ColTable(this);
		}
		if (tableDbKeyHash512Type == null || !(tableDbKeyHash512Type instanceof CFBamJpaDbKeyHash512TypeTable)) {
			tableDbKeyHash512Type = new CFBamJpaDbKeyHash512TypeTable(this);
		}
		if (tableDbKeyHash512Gen == null || !(tableDbKeyHash512Gen instanceof CFBamJpaDbKeyHash512GenTable)) {
			tableDbKeyHash512Gen = new CFBamJpaDbKeyHash512GenTable(this);
		}
		if (tableStringDef == null || !(tableStringDef instanceof CFBamJpaStringDefTable)) {
			tableStringDef = new CFBamJpaStringDefTable(this);
		}
		if (tableStringType == null || !(tableStringType instanceof CFBamJpaStringTypeTable)) {
			tableStringType = new CFBamJpaStringTypeTable(this);
		}
		if (tableTZDateDef == null || !(tableTZDateDef instanceof CFBamJpaTZDateDefTable)) {
			tableTZDateDef = new CFBamJpaTZDateDefTable(this);
		}
		if (tableTZDateType == null || !(tableTZDateType instanceof CFBamJpaTZDateTypeTable)) {
			tableTZDateType = new CFBamJpaTZDateTypeTable(this);
		}
		if (tableTZTimeDef == null || !(tableTZTimeDef instanceof CFBamJpaTZTimeDefTable)) {
			tableTZTimeDef = new CFBamJpaTZTimeDefTable(this);
		}
		if (tableTZTimeType == null || !(tableTZTimeType instanceof CFBamJpaTZTimeTypeTable)) {
			tableTZTimeType = new CFBamJpaTZTimeTypeTable(this);
		}
		if (tableTZTimestampDef == null || !(tableTZTimestampDef instanceof CFBamJpaTZTimestampDefTable)) {
			tableTZTimestampDef = new CFBamJpaTZTimestampDefTable(this);
		}
		if (tableTZTimestampType == null || !(tableTZTimestampType instanceof CFBamJpaTZTimestampTypeTable)) {
			tableTZTimestampType = new CFBamJpaTZTimestampTypeTable(this);
		}
		if (tableTableCol == null || !(tableTableCol instanceof CFBamJpaTableColTable)) {
			tableTableCol = new CFBamJpaTableColTable(this);
		}
		if (tableTextDef == null || !(tableTextDef instanceof CFBamJpaTextDefTable)) {
			tableTextDef = new CFBamJpaTextDefTable(this);
		}
		if (tableTextType == null || !(tableTextType instanceof CFBamJpaTextTypeTable)) {
			tableTextType = new CFBamJpaTextTypeTable(this);
		}
		if (tableTimeDef == null || !(tableTimeDef instanceof CFBamJpaTimeDefTable)) {
			tableTimeDef = new CFBamJpaTimeDefTable(this);
		}
		if (tableTimeType == null || !(tableTimeType instanceof CFBamJpaTimeTypeTable)) {
			tableTimeType = new CFBamJpaTimeTypeTable(this);
		}
		if (tableTimestampDef == null || !(tableTimestampDef instanceof CFBamJpaTimestampDefTable)) {
			tableTimestampDef = new CFBamJpaTimestampDefTable(this);
		}
		if (tableTimestampType == null || !(tableTimestampType instanceof CFBamJpaTimestampTypeTable)) {
			tableTimestampType = new CFBamJpaTimestampTypeTable(this);
		}
		if (tableTokenDef == null || !(tableTokenDef instanceof CFBamJpaTokenDefTable)) {
			tableTokenDef = new CFBamJpaTokenDefTable(this);
		}
		if (tableTokenType == null || !(tableTokenType instanceof CFBamJpaTokenTypeTable)) {
			tableTokenType = new CFBamJpaTokenTypeTable(this);
		}
		if (tableUInt16Def == null || !(tableUInt16Def instanceof CFBamJpaUInt16DefTable)) {
			tableUInt16Def = new CFBamJpaUInt16DefTable(this);
		}
		if (tableUInt16Type == null || !(tableUInt16Type instanceof CFBamJpaUInt16TypeTable)) {
			tableUInt16Type = new CFBamJpaUInt16TypeTable(this);
		}
		if (tableUInt32Def == null || !(tableUInt32Def instanceof CFBamJpaUInt32DefTable)) {
			tableUInt32Def = new CFBamJpaUInt32DefTable(this);
		}
		if (tableUInt32Type == null || !(tableUInt32Type instanceof CFBamJpaUInt32TypeTable)) {
			tableUInt32Type = new CFBamJpaUInt32TypeTable(this);
		}
		if (tableUInt64Def == null || !(tableUInt64Def instanceof CFBamJpaUInt64DefTable)) {
			tableUInt64Def = new CFBamJpaUInt64DefTable(this);
		}
		if (tableUInt64Type == null || !(tableUInt64Type instanceof CFBamJpaUInt64TypeTable)) {
			tableUInt64Type = new CFBamJpaUInt64TypeTable(this);
		}
		if (tableUuidDef == null || !(tableUuidDef instanceof CFBamJpaUuidDefTable)) {
			tableUuidDef = new CFBamJpaUuidDefTable(this);
		}
		if (tableUuid6Def == null || !(tableUuid6Def instanceof CFBamJpaUuid6DefTable)) {
			tableUuid6Def = new CFBamJpaUuid6DefTable(this);
		}
		if (tableUuidType == null || !(tableUuidType instanceof CFBamJpaUuidTypeTable)) {
			tableUuidType = new CFBamJpaUuidTypeTable(this);
		}
		if (tableUuid6Type == null || !(tableUuid6Type instanceof CFBamJpaUuid6TypeTable)) {
			tableUuid6Type = new CFBamJpaUuid6TypeTable(this);
		}
		if (tableBlobCol == null || !(tableBlobCol instanceof CFBamJpaBlobColTable)) {
			tableBlobCol = new CFBamJpaBlobColTable(this);
		}
		if (tableBoolCol == null || !(tableBoolCol instanceof CFBamJpaBoolColTable)) {
			tableBoolCol = new CFBamJpaBoolColTable(this);
		}
		if (tableDateCol == null || !(tableDateCol instanceof CFBamJpaDateColTable)) {
			tableDateCol = new CFBamJpaDateColTable(this);
		}
		if (tableDoubleCol == null || !(tableDoubleCol instanceof CFBamJpaDoubleColTable)) {
			tableDoubleCol = new CFBamJpaDoubleColTable(this);
		}
		if (tableEnumDef == null || !(tableEnumDef instanceof CFBamJpaEnumDefTable)) {
			tableEnumDef = new CFBamJpaEnumDefTable(this);
		}
		if (tableEnumType == null || !(tableEnumType instanceof CFBamJpaEnumTypeTable)) {
			tableEnumType = new CFBamJpaEnumTypeTable(this);
		}
		if (tableFloatCol == null || !(tableFloatCol instanceof CFBamJpaFloatColTable)) {
			tableFloatCol = new CFBamJpaFloatColTable(this);
		}
		if (tableId16Gen == null || !(tableId16Gen instanceof CFBamJpaId16GenTable)) {
			tableId16Gen = new CFBamJpaId16GenTable(this);
		}
		if (tableId32Gen == null || !(tableId32Gen instanceof CFBamJpaId32GenTable)) {
			tableId32Gen = new CFBamJpaId32GenTable(this);
		}
		if (tableId64Gen == null || !(tableId64Gen instanceof CFBamJpaId64GenTable)) {
			tableId64Gen = new CFBamJpaId64GenTable(this);
		}
		if (tableInt16Col == null || !(tableInt16Col instanceof CFBamJpaInt16ColTable)) {
			tableInt16Col = new CFBamJpaInt16ColTable(this);
		}
		if (tableInt32Col == null || !(tableInt32Col instanceof CFBamJpaInt32ColTable)) {
			tableInt32Col = new CFBamJpaInt32ColTable(this);
		}
		if (tableInt64Col == null || !(tableInt64Col instanceof CFBamJpaInt64ColTable)) {
			tableInt64Col = new CFBamJpaInt64ColTable(this);
		}
		if (tableNmTokenCol == null || !(tableNmTokenCol instanceof CFBamJpaNmTokenColTable)) {
			tableNmTokenCol = new CFBamJpaNmTokenColTable(this);
		}
		if (tableNmTokensCol == null || !(tableNmTokensCol instanceof CFBamJpaNmTokensColTable)) {
			tableNmTokensCol = new CFBamJpaNmTokensColTable(this);
		}
		if (tableNumberCol == null || !(tableNumberCol instanceof CFBamJpaNumberColTable)) {
			tableNumberCol = new CFBamJpaNumberColTable(this);
		}
		if (tableStringCol == null || !(tableStringCol instanceof CFBamJpaStringColTable)) {
			tableStringCol = new CFBamJpaStringColTable(this);
		}
		if (tableTZDateCol == null || !(tableTZDateCol instanceof CFBamJpaTZDateColTable)) {
			tableTZDateCol = new CFBamJpaTZDateColTable(this);
		}
		if (tableTZTimeCol == null || !(tableTZTimeCol instanceof CFBamJpaTZTimeColTable)) {
			tableTZTimeCol = new CFBamJpaTZTimeColTable(this);
		}
		if (tableTZTimestampCol == null || !(tableTZTimestampCol instanceof CFBamJpaTZTimestampColTable)) {
			tableTZTimestampCol = new CFBamJpaTZTimestampColTable(this);
		}
		if (tableTextCol == null || !(tableTextCol instanceof CFBamJpaTextColTable)) {
			tableTextCol = new CFBamJpaTextColTable(this);
		}
		if (tableTimeCol == null || !(tableTimeCol instanceof CFBamJpaTimeColTable)) {
			tableTimeCol = new CFBamJpaTimeColTable(this);
		}
		if (tableTimestampCol == null || !(tableTimestampCol instanceof CFBamJpaTimestampColTable)) {
			tableTimestampCol = new CFBamJpaTimestampColTable(this);
		}
		if (tableTokenCol == null || !(tableTokenCol instanceof CFBamJpaTokenColTable)) {
			tableTokenCol = new CFBamJpaTokenColTable(this);
		}
		if (tableUInt16Col == null || !(tableUInt16Col instanceof CFBamJpaUInt16ColTable)) {
			tableUInt16Col = new CFBamJpaUInt16ColTable(this);
		}
		if (tableUInt32Col == null || !(tableUInt32Col instanceof CFBamJpaUInt32ColTable)) {
			tableUInt32Col = new CFBamJpaUInt32ColTable(this);
		}
		if (tableUInt64Col == null || !(tableUInt64Col instanceof CFBamJpaUInt64ColTable)) {
			tableUInt64Col = new CFBamJpaUInt64ColTable(this);
		}
		if (tableUuidCol == null || !(tableUuidCol instanceof CFBamJpaUuidColTable)) {
			tableUuidCol = new CFBamJpaUuidColTable(this);
		}
		if (tableUuid6Col == null || !(tableUuid6Col instanceof CFBamJpaUuid6ColTable)) {
			tableUuid6Col = new CFBamJpaUuid6ColTable(this);
		}
		if (tableUuidGen == null || !(tableUuidGen instanceof CFBamJpaUuidGenTable)) {
			tableUuidGen = new CFBamJpaUuidGenTable(this);
		}
		if (tableUuid6Gen == null || !(tableUuid6Gen instanceof CFBamJpaUuid6GenTable)) {
			tableUuid6Gen = new CFBamJpaUuid6GenTable(this);
		}
	}

	@Override		
	public ICFSecSchema getCFSecSchema() {
		return( ICFSecSchema.getBackingCFSec() );
	}

	@Override
	public void setCFSecSchema(ICFSecSchema schema) {
		ICFSecSchema.setBackingCFSec(schema);
		schema.wireRecConstructors();
	}

	@Override		
	public ICFIntSchema getCFIntSchema() {
		return( ICFIntSchema.getBackingCFInt() );
	}

	@Override
	public void setCFIntSchema(ICFIntSchema schema) {
		ICFIntSchema.setBackingCFInt(schema);
		schema.wireRecConstructors();
	}

	@Override		
	public ICFBamSchema getCFBamSchema() {
		return( ICFBamSchema.getBackingCFBam() );
	}

	@Override
	public void setCFBamSchema(ICFBamSchema schema) {
		ICFBamSchema.setBackingCFBam(schema);
		schema.wireRecConstructors();
	}

	@Override
	public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return( applicationContext );
	}

	public CFBamJpaSchemaService getJpaSchemaService() {
		final String S_ProcName = "getJpaSchemaService";
		final String S_BeanClassName = "CFBamJpaSchemaService";
		final String S_BeanName = "cfbam31JpaSchemaService";
		final String S_MemberName = "cfbamJpaSchemaService";
		if ( cfbamJpaSchemaService == null ) {
			if (applicationContext == null) {
				throw new CFLibNullArgumentException(getClass(), S_ProcName, 0, "applicationContext");
			}
			try {
				if (!applicationContext.containsBean(S_BeanName)) {
					throw new CFLibInvalidStateException(getClass(), S_ProcName,
						String.format(Inz.s("cflib.common.ACContainsBeanFailure"), S_BeanName),
						String.format(Inz.x("cflib.common.ACContainsBeanFailure"), S_BeanName));
				}
				if (!applicationContext.isTypeMatch(S_BeanName, CFBamJpaSchemaService.class)) {
					throw new CFLibInvalidStateException(getClass(), S_ProcName,
						String.format(Inz.s("cflib.common.ACBeanTypeFailure"), S_BeanName, S_BeanClassName),
						String.format(Inz.x("cflib.common.ACBeanTypeFailure"), S_BeanName, S_BeanClassName));
				}
				if (applicationContext.isSingleton(S_BeanName)) {
					// Resolve singletons like configuration data if they exist, hope that default construction takes place
					cfbamJpaSchemaService = (CFBamJpaSchemaService)(applicationContext.getBean(S_BeanName, CFBamJpaSchemaService.class));
				}
				else if(applicationContext.isPrototype(S_BeanName)) {
					// If it is a prototype, get a copy for our manual 'autowiring' like Spring would do
					cfbamJpaSchemaService = (CFBamJpaSchemaService)(applicationContext.getBean(S_BeanName, CFBamJpaSchemaService.class));
				}
				// Otherwise it would be a scoped bean, and I've no idea how to search scoped beans as of 2026-02-21, so we'll default to constructing a local instance -- mark.sobkow@gmail.com
				if (cfbamJpaSchemaService == null) {
					// Need to manually construct and register instance; the plumbing is presumed to happen automagically via createBean()
					cfbamJpaSchemaService = (CFBamJpaSchemaService)(applicationContext.getAutowireCapableBeanFactory().createBean(CFBamJpaSchemaService.class));
					if (cfbamJpaSchemaService == null) {
						throw new CFLibInvalidStateException( getClass(), S_ProcName,
							String.format(Inz.s("cflib.common.ACAutowireBeanInstanceCreationFailure"),	S_BeanClassName),
							 String.format(Inz.x("cflib.common.ACAutowireBeanInstanceCreationFailure"), S_BeanClassName));
					}
				}
			}
			catch (BeansException ex) {
				cfbamJpaSchemaService = null;
				throw new CFLibInvalidStateException(getClass(), S_ProcName, 
					String.format(Inz.s("cflib.common.CaughtWhileTryingToResolve"), ex.getClass().getName(), S_BeanClassName, S_MemberName, ex.getMessage()),
					String.format(Inz.x("cflib.common.CaughtWhileTryingToResolve"), ex.getClass().getName(), S_BeanClassName, S_MemberName, ex.getLocalizedMessage()),
					ex);
			}
		}
		return( cfbamJpaSchemaService );
	}


	public CFBamJpaSchema() {

		tableAtom = null;
		tableBlobCol = null;
		tableBlobDef = null;
		tableBlobType = null;
		tableBoolCol = null;
		tableBoolDef = null;
		tableBoolType = null;
		tableChain = null;
		tableClearDep = null;
		tableClearSubDep1 = null;
		tableClearSubDep2 = null;
		tableClearSubDep3 = null;
		tableClearTopDep = null;
		tableCluster = null;
		tableDateCol = null;
		tableDateDef = null;
		tableDateType = null;
		tableDbKeyHash128Col = null;
		tableDbKeyHash128Def = null;
		tableDbKeyHash128Gen = null;
		tableDbKeyHash128Type = null;
		tableDbKeyHash160Col = null;
		tableDbKeyHash160Def = null;
		tableDbKeyHash160Gen = null;
		tableDbKeyHash160Type = null;
		tableDbKeyHash224Col = null;
		tableDbKeyHash224Def = null;
		tableDbKeyHash224Gen = null;
		tableDbKeyHash224Type = null;
		tableDbKeyHash256Col = null;
		tableDbKeyHash256Def = null;
		tableDbKeyHash256Gen = null;
		tableDbKeyHash256Type = null;
		tableDbKeyHash384Col = null;
		tableDbKeyHash384Def = null;
		tableDbKeyHash384Gen = null;
		tableDbKeyHash384Type = null;
		tableDbKeyHash512Col = null;
		tableDbKeyHash512Def = null;
		tableDbKeyHash512Gen = null;
		tableDbKeyHash512Type = null;
		tableDelDep = null;
		tableDelSubDep1 = null;
		tableDelSubDep2 = null;
		tableDelSubDep3 = null;
		tableDelTopDep = null;
		tableDoubleCol = null;
		tableDoubleDef = null;
		tableDoubleType = null;
		tableEnumDef = null;
		tableEnumTag = null;
		tableEnumType = null;
		tableFloatCol = null;
		tableFloatDef = null;
		tableFloatType = null;
		tableHostNode = null;
		tableISOCcy = null;
		tableISOCtry = null;
		tableISOCtryCcy = null;
		tableISOCtryLang = null;
		tableISOLang = null;
		tableISOTZone = null;
		tableId16Gen = null;
		tableId32Gen = null;
		tableId64Gen = null;
		tableIndex = null;
		tableIndexCol = null;
		tableInt16Col = null;
		tableInt16Def = null;
		tableInt16Type = null;
		tableInt32Col = null;
		tableInt32Def = null;
		tableInt32Type = null;
		tableInt64Col = null;
		tableInt64Def = null;
		tableInt64Type = null;
		tableLicense = null;
		tableMajorVersion = null;
		tableMimeType = null;
		tableMinorVersion = null;
		tableNmTokenCol = null;
		tableNmTokenDef = null;
		tableNmTokenType = null;
		tableNmTokensCol = null;
		tableNmTokensDef = null;
		tableNmTokensType = null;
		tableNumberCol = null;
		tableNumberDef = null;
		tableNumberType = null;
		tableParam = null;
		tablePopDep = null;
		tablePopSubDep1 = null;
		tablePopSubDep2 = null;
		tablePopSubDep3 = null;
		tablePopTopDep = null;
		tableRelation = null;
		tableRelationCol = null;
		tableSchemaDef = null;
		tableSchemaRef = null;
		tableScope = null;
		tableSecDevice = null;
		tableSecGroup = null;
		tableSecGrpInc = null;
		tableSecGrpMemb = null;
		tableSecSession = null;
		tableSecUser = null;
		tableServerListFunc = null;
		tableServerMethod = null;
		tableServerObjFunc = null;
		tableServerProc = null;
		tableService = null;
		tableServiceType = null;
		tableStringCol = null;
		tableStringDef = null;
		tableStringType = null;
		tableSubProject = null;
		tableSysCluster = null;
		tableTSecGroup = null;
		tableTSecGrpInc = null;
		tableTSecGrpMemb = null;
		tableTZDateCol = null;
		tableTZDateDef = null;
		tableTZDateType = null;
		tableTZTimeCol = null;
		tableTZTimeDef = null;
		tableTZTimeType = null;
		tableTZTimestampCol = null;
		tableTZTimestampDef = null;
		tableTZTimestampType = null;
		tableTable = null;
		tableTableCol = null;
		tableTenant = null;
		tableTextCol = null;
		tableTextDef = null;
		tableTextType = null;
		tableTimeCol = null;
		tableTimeDef = null;
		tableTimeType = null;
		tableTimestampCol = null;
		tableTimestampDef = null;
		tableTimestampType = null;
		tableTld = null;
		tableTokenCol = null;
		tableTokenDef = null;
		tableTokenType = null;
		tableTopDomain = null;
		tableTopProject = null;
		tableUInt16Col = null;
		tableUInt16Def = null;
		tableUInt16Type = null;
		tableUInt32Col = null;
		tableUInt32Def = null;
		tableUInt32Type = null;
		tableUInt64Col = null;
		tableUInt64Def = null;
		tableUInt64Type = null;
		tableURLProtocol = null;
		tableUuid6Col = null;
		tableUuid6Def = null;
		tableUuid6Gen = null;
		tableUuid6Type = null;
		tableUuidCol = null;
		tableUuidDef = null;
		tableUuidGen = null;
		tableUuidType = null;
		tableValue = null;

		factoryAtom = new CFBamJpaAtomDefaultFactory();
		factoryBlobCol = new CFBamJpaBlobColDefaultFactory();
		factoryBlobDef = new CFBamJpaBlobDefDefaultFactory();
		factoryBlobType = new CFBamJpaBlobTypeDefaultFactory();
		factoryBoolCol = new CFBamJpaBoolColDefaultFactory();
		factoryBoolDef = new CFBamJpaBoolDefDefaultFactory();
		factoryBoolType = new CFBamJpaBoolTypeDefaultFactory();
		factoryChain = new CFBamJpaChainDefaultFactory();
		factoryClearDep = new CFBamJpaClearDepDefaultFactory();
		factoryClearSubDep1 = new CFBamJpaClearSubDep1DefaultFactory();
		factoryClearSubDep2 = new CFBamJpaClearSubDep2DefaultFactory();
		factoryClearSubDep3 = new CFBamJpaClearSubDep3DefaultFactory();
		factoryClearTopDep = new CFBamJpaClearTopDepDefaultFactory();
		factoryCluster = new CFSecJpaClusterDefaultFactory();
		factoryDateCol = new CFBamJpaDateColDefaultFactory();
		factoryDateDef = new CFBamJpaDateDefDefaultFactory();
		factoryDateType = new CFBamJpaDateTypeDefaultFactory();
		factoryDbKeyHash128Col = new CFBamJpaDbKeyHash128ColDefaultFactory();
		factoryDbKeyHash128Def = new CFBamJpaDbKeyHash128DefDefaultFactory();
		factoryDbKeyHash128Gen = new CFBamJpaDbKeyHash128GenDefaultFactory();
		factoryDbKeyHash128Type = new CFBamJpaDbKeyHash128TypeDefaultFactory();
		factoryDbKeyHash160Col = new CFBamJpaDbKeyHash160ColDefaultFactory();
		factoryDbKeyHash160Def = new CFBamJpaDbKeyHash160DefDefaultFactory();
		factoryDbKeyHash160Gen = new CFBamJpaDbKeyHash160GenDefaultFactory();
		factoryDbKeyHash160Type = new CFBamJpaDbKeyHash160TypeDefaultFactory();
		factoryDbKeyHash224Col = new CFBamJpaDbKeyHash224ColDefaultFactory();
		factoryDbKeyHash224Def = new CFBamJpaDbKeyHash224DefDefaultFactory();
		factoryDbKeyHash224Gen = new CFBamJpaDbKeyHash224GenDefaultFactory();
		factoryDbKeyHash224Type = new CFBamJpaDbKeyHash224TypeDefaultFactory();
		factoryDbKeyHash256Col = new CFBamJpaDbKeyHash256ColDefaultFactory();
		factoryDbKeyHash256Def = new CFBamJpaDbKeyHash256DefDefaultFactory();
		factoryDbKeyHash256Gen = new CFBamJpaDbKeyHash256GenDefaultFactory();
		factoryDbKeyHash256Type = new CFBamJpaDbKeyHash256TypeDefaultFactory();
		factoryDbKeyHash384Col = new CFBamJpaDbKeyHash384ColDefaultFactory();
		factoryDbKeyHash384Def = new CFBamJpaDbKeyHash384DefDefaultFactory();
		factoryDbKeyHash384Gen = new CFBamJpaDbKeyHash384GenDefaultFactory();
		factoryDbKeyHash384Type = new CFBamJpaDbKeyHash384TypeDefaultFactory();
		factoryDbKeyHash512Col = new CFBamJpaDbKeyHash512ColDefaultFactory();
		factoryDbKeyHash512Def = new CFBamJpaDbKeyHash512DefDefaultFactory();
		factoryDbKeyHash512Gen = new CFBamJpaDbKeyHash512GenDefaultFactory();
		factoryDbKeyHash512Type = new CFBamJpaDbKeyHash512TypeDefaultFactory();
		factoryDelDep = new CFBamJpaDelDepDefaultFactory();
		factoryDelSubDep1 = new CFBamJpaDelSubDep1DefaultFactory();
		factoryDelSubDep2 = new CFBamJpaDelSubDep2DefaultFactory();
		factoryDelSubDep3 = new CFBamJpaDelSubDep3DefaultFactory();
		factoryDelTopDep = new CFBamJpaDelTopDepDefaultFactory();
		factoryDoubleCol = new CFBamJpaDoubleColDefaultFactory();
		factoryDoubleDef = new CFBamJpaDoubleDefDefaultFactory();
		factoryDoubleType = new CFBamJpaDoubleTypeDefaultFactory();
		factoryEnumDef = new CFBamJpaEnumDefDefaultFactory();
		factoryEnumTag = new CFBamJpaEnumTagDefaultFactory();
		factoryEnumType = new CFBamJpaEnumTypeDefaultFactory();
		factoryFloatCol = new CFBamJpaFloatColDefaultFactory();
		factoryFloatDef = new CFBamJpaFloatDefDefaultFactory();
		factoryFloatType = new CFBamJpaFloatTypeDefaultFactory();
		factoryHostNode = new CFSecJpaHostNodeDefaultFactory();
		factoryISOCcy = new CFSecJpaISOCcyDefaultFactory();
		factoryISOCtry = new CFSecJpaISOCtryDefaultFactory();
		factoryISOCtryCcy = new CFSecJpaISOCtryCcyDefaultFactory();
		factoryISOCtryLang = new CFSecJpaISOCtryLangDefaultFactory();
		factoryISOLang = new CFSecJpaISOLangDefaultFactory();
		factoryISOTZone = new CFSecJpaISOTZoneDefaultFactory();
		factoryId16Gen = new CFBamJpaId16GenDefaultFactory();
		factoryId32Gen = new CFBamJpaId32GenDefaultFactory();
		factoryId64Gen = new CFBamJpaId64GenDefaultFactory();
		factoryIndex = new CFBamJpaIndexDefaultFactory();
		factoryIndexCol = new CFBamJpaIndexColDefaultFactory();
		factoryInt16Col = new CFBamJpaInt16ColDefaultFactory();
		factoryInt16Def = new CFBamJpaInt16DefDefaultFactory();
		factoryInt16Type = new CFBamJpaInt16TypeDefaultFactory();
		factoryInt32Col = new CFBamJpaInt32ColDefaultFactory();
		factoryInt32Def = new CFBamJpaInt32DefDefaultFactory();
		factoryInt32Type = new CFBamJpaInt32TypeDefaultFactory();
		factoryInt64Col = new CFBamJpaInt64ColDefaultFactory();
		factoryInt64Def = new CFBamJpaInt64DefDefaultFactory();
		factoryInt64Type = new CFBamJpaInt64TypeDefaultFactory();
		factoryLicense = new CFIntJpaLicenseDefaultFactory();
		factoryMajorVersion = new CFIntJpaMajorVersionDefaultFactory();
		factoryMimeType = new CFIntJpaMimeTypeDefaultFactory();
		factoryMinorVersion = new CFIntJpaMinorVersionDefaultFactory();
		factoryNmTokenCol = new CFBamJpaNmTokenColDefaultFactory();
		factoryNmTokenDef = new CFBamJpaNmTokenDefDefaultFactory();
		factoryNmTokenType = new CFBamJpaNmTokenTypeDefaultFactory();
		factoryNmTokensCol = new CFBamJpaNmTokensColDefaultFactory();
		factoryNmTokensDef = new CFBamJpaNmTokensDefDefaultFactory();
		factoryNmTokensType = new CFBamJpaNmTokensTypeDefaultFactory();
		factoryNumberCol = new CFBamJpaNumberColDefaultFactory();
		factoryNumberDef = new CFBamJpaNumberDefDefaultFactory();
		factoryNumberType = new CFBamJpaNumberTypeDefaultFactory();
		factoryParam = new CFBamJpaParamDefaultFactory();
		factoryPopDep = new CFBamJpaPopDepDefaultFactory();
		factoryPopSubDep1 = new CFBamJpaPopSubDep1DefaultFactory();
		factoryPopSubDep2 = new CFBamJpaPopSubDep2DefaultFactory();
		factoryPopSubDep3 = new CFBamJpaPopSubDep3DefaultFactory();
		factoryPopTopDep = new CFBamJpaPopTopDepDefaultFactory();
		factoryRelation = new CFBamJpaRelationDefaultFactory();
		factoryRelationCol = new CFBamJpaRelationColDefaultFactory();
		factorySchemaDef = new CFBamJpaSchemaDefDefaultFactory();
		factorySchemaRef = new CFBamJpaSchemaRefDefaultFactory();
		factoryScope = new CFBamJpaScopeDefaultFactory();
		factorySecDevice = new CFSecJpaSecDeviceDefaultFactory();
		factorySecGroup = new CFSecJpaSecGroupDefaultFactory();
		factorySecGrpInc = new CFSecJpaSecGrpIncDefaultFactory();
		factorySecGrpMemb = new CFSecJpaSecGrpMembDefaultFactory();
		factorySecSession = new CFSecJpaSecSessionDefaultFactory();
		factorySecUser = new CFSecJpaSecUserDefaultFactory();
		factoryServerListFunc = new CFBamJpaServerListFuncDefaultFactory();
		factoryServerMethod = new CFBamJpaServerMethodDefaultFactory();
		factoryServerObjFunc = new CFBamJpaServerObjFuncDefaultFactory();
		factoryServerProc = new CFBamJpaServerProcDefaultFactory();
		factoryService = new CFSecJpaServiceDefaultFactory();
		factoryServiceType = new CFSecJpaServiceTypeDefaultFactory();
		factoryStringCol = new CFBamJpaStringColDefaultFactory();
		factoryStringDef = new CFBamJpaStringDefDefaultFactory();
		factoryStringType = new CFBamJpaStringTypeDefaultFactory();
		factorySubProject = new CFIntJpaSubProjectDefaultFactory();
		factorySysCluster = new CFSecJpaSysClusterDefaultFactory();
		factoryTSecGroup = new CFSecJpaTSecGroupDefaultFactory();
		factoryTSecGrpInc = new CFSecJpaTSecGrpIncDefaultFactory();
		factoryTSecGrpMemb = new CFSecJpaTSecGrpMembDefaultFactory();
		factoryTZDateCol = new CFBamJpaTZDateColDefaultFactory();
		factoryTZDateDef = new CFBamJpaTZDateDefDefaultFactory();
		factoryTZDateType = new CFBamJpaTZDateTypeDefaultFactory();
		factoryTZTimeCol = new CFBamJpaTZTimeColDefaultFactory();
		factoryTZTimeDef = new CFBamJpaTZTimeDefDefaultFactory();
		factoryTZTimeType = new CFBamJpaTZTimeTypeDefaultFactory();
		factoryTZTimestampCol = new CFBamJpaTZTimestampColDefaultFactory();
		factoryTZTimestampDef = new CFBamJpaTZTimestampDefDefaultFactory();
		factoryTZTimestampType = new CFBamJpaTZTimestampTypeDefaultFactory();
		factoryTable = new CFBamJpaTableDefaultFactory();
		factoryTableCol = new CFBamJpaTableColDefaultFactory();
		factoryTenant = new CFSecJpaTenantDefaultFactory();
		factoryTextCol = new CFBamJpaTextColDefaultFactory();
		factoryTextDef = new CFBamJpaTextDefDefaultFactory();
		factoryTextType = new CFBamJpaTextTypeDefaultFactory();
		factoryTimeCol = new CFBamJpaTimeColDefaultFactory();
		factoryTimeDef = new CFBamJpaTimeDefDefaultFactory();
		factoryTimeType = new CFBamJpaTimeTypeDefaultFactory();
		factoryTimestampCol = new CFBamJpaTimestampColDefaultFactory();
		factoryTimestampDef = new CFBamJpaTimestampDefDefaultFactory();
		factoryTimestampType = new CFBamJpaTimestampTypeDefaultFactory();
		factoryTld = new CFIntJpaTldDefaultFactory();
		factoryTokenCol = new CFBamJpaTokenColDefaultFactory();
		factoryTokenDef = new CFBamJpaTokenDefDefaultFactory();
		factoryTokenType = new CFBamJpaTokenTypeDefaultFactory();
		factoryTopDomain = new CFIntJpaTopDomainDefaultFactory();
		factoryTopProject = new CFIntJpaTopProjectDefaultFactory();
		factoryUInt16Col = new CFBamJpaUInt16ColDefaultFactory();
		factoryUInt16Def = new CFBamJpaUInt16DefDefaultFactory();
		factoryUInt16Type = new CFBamJpaUInt16TypeDefaultFactory();
		factoryUInt32Col = new CFBamJpaUInt32ColDefaultFactory();
		factoryUInt32Def = new CFBamJpaUInt32DefDefaultFactory();
		factoryUInt32Type = new CFBamJpaUInt32TypeDefaultFactory();
		factoryUInt64Col = new CFBamJpaUInt64ColDefaultFactory();
		factoryUInt64Def = new CFBamJpaUInt64DefDefaultFactory();
		factoryUInt64Type = new CFBamJpaUInt64TypeDefaultFactory();
		factoryURLProtocol = new CFIntJpaURLProtocolDefaultFactory();
		factoryUuid6Col = new CFBamJpaUuid6ColDefaultFactory();
		factoryUuid6Def = new CFBamJpaUuid6DefDefaultFactory();
		factoryUuid6Gen = new CFBamJpaUuid6GenDefaultFactory();
		factoryUuid6Type = new CFBamJpaUuid6TypeDefaultFactory();
		factoryUuidCol = new CFBamJpaUuidColDefaultFactory();
		factoryUuidDef = new CFBamJpaUuidDefDefaultFactory();
		factoryUuidGen = new CFBamJpaUuidGenDefaultFactory();
		factoryUuidType = new CFBamJpaUuidTypeDefaultFactory();
		factoryValue = new CFBamJpaValueDefaultFactory();	}

	@Override
	public ICFBamSchema newSchema() {
		throw new CFLibMustOverrideException( getClass(), "newSchema" );
	}

	@Override
	public short nextISOCcyIdGen() {
		throw new CFLibNotImplementedYetException( getClass(), "nextISOCcyIdGen" );
	}

	@Override
	public short nextISOCtryIdGen() {
		throw new CFLibNotImplementedYetException( getClass(), "nextISOCtryIdGen" );
	}

	@Override
	public short nextISOLangIdGen() {
		throw new CFLibNotImplementedYetException( getClass(), "nextISOLangIdGen" );
	}

	@Override
	public short nextISOTZoneIdGen() {
		throw new CFLibNotImplementedYetException( getClass(), "nextISOTZoneIdGen" );
	}

	@Override
	public int nextMimeTypeIdGen() {
		throw new CFLibNotImplementedYetException( getClass(), "nextMimeTypeIdGen" );
	}

	@Override
	public int nextURLProtocolIdGen() {
		throw new CFLibNotImplementedYetException( getClass(), "nextURLProtocolIdGen" );
	}

	@Override
	public CFLibDbKeyHash256 nextClusterIdGen() {
		CFLibDbKeyHash256 retval = new CFLibDbKeyHash256(0);
		return( retval );
	}

	@Override
	public CFLibDbKeyHash256 nextSecSessionIdGen() {
		CFLibDbKeyHash256 retval = new CFLibDbKeyHash256(0);
		return( retval );
	}

	@Override
	public CFLibDbKeyHash256 nextSecUserIdGen() {
		CFLibDbKeyHash256 retval = new CFLibDbKeyHash256(0);
		return( retval );
	}

	@Override
	public CFLibDbKeyHash256 nextServiceTypeIdGen() {
		CFLibDbKeyHash256 retval = new CFLibDbKeyHash256(0);
		return( retval );
	}

	@Override
	public CFLibDbKeyHash256 nextTenantIdGen() {
		CFLibDbKeyHash256 retval = new CFLibDbKeyHash256(0);
		return( retval );
	}

	@Override
	public CFLibDbKeyHash256 nextHostNodeIdGen() {
		CFLibDbKeyHash256 retval = new CFLibDbKeyHash256(0);
		return( retval );
	}

	@Override
	public CFLibDbKeyHash256 nextSecGroupIdGen() {
		CFLibDbKeyHash256 retval = new CFLibDbKeyHash256(0);
		return( retval );
	}

	@Override
	public CFLibDbKeyHash256 nextSecGrpIncIdGen() {
		CFLibDbKeyHash256 retval = new CFLibDbKeyHash256(0);
		return( retval );
	}

	@Override
	public CFLibDbKeyHash256 nextSecGrpMembIdGen() {
		CFLibDbKeyHash256 retval = new CFLibDbKeyHash256(0);
		return( retval );
	}

	@Override
	public CFLibDbKeyHash256 nextServiceIdGen() {
		CFLibDbKeyHash256 retval = new CFLibDbKeyHash256(0);
		return( retval );
	}

	@Override
	public CFLibDbKeyHash256 nextTSecGroupIdGen() {
		CFLibDbKeyHash256 retval = new CFLibDbKeyHash256(0);
		return( retval );
	}

	@Override
	public CFLibDbKeyHash256 nextTSecGrpIncIdGen() {
		CFLibDbKeyHash256 retval = new CFLibDbKeyHash256(0);
		return( retval );
	}

	@Override
	public CFLibDbKeyHash256 nextTSecGrpMembIdGen() {
		CFLibDbKeyHash256 retval = new CFLibDbKeyHash256(0);
		return( retval );
	}

	@Override
	public CFLibDbKeyHash256 nextMajorVersionIdGen() {
		CFLibDbKeyHash256 retval = new CFLibDbKeyHash256(0);
		return( retval );
	}

	@Override
	public CFLibDbKeyHash256 nextMinorVersionIdGen() {
		CFLibDbKeyHash256 retval = new CFLibDbKeyHash256(0);
		return( retval );
	}

	@Override
	public CFLibDbKeyHash256 nextSubProjectIdGen() {
		CFLibDbKeyHash256 retval = new CFLibDbKeyHash256(0);
		return( retval );
	}

	@Override
	public CFLibDbKeyHash256 nextTldIdGen() {
		CFLibDbKeyHash256 retval = new CFLibDbKeyHash256(0);
		return( retval );
	}

	@Override
	public CFLibDbKeyHash256 nextTopDomainIdGen() {
		CFLibDbKeyHash256 retval = new CFLibDbKeyHash256(0);
		return( retval );
	}

	@Override
	public CFLibDbKeyHash256 nextTopProjectIdGen() {
		CFLibDbKeyHash256 retval = new CFLibDbKeyHash256(0);
		return( retval );
	}

	@Override
	public CFLibDbKeyHash256 nextLicenseIdGen() {
		CFLibDbKeyHash256 retval = new CFLibDbKeyHash256(0);
		return( retval );
	}

	@Override
	public CFLibDbKeyHash256 nextChainIdGen() {
		CFLibDbKeyHash256 retval = new CFLibDbKeyHash256(0);
		return( retval );
	}

	@Override
	public CFLibDbKeyHash256 nextEnumTagIdGen() {
		CFLibDbKeyHash256 retval = new CFLibDbKeyHash256(0);
		return( retval );
	}

	@Override
	public CFLibDbKeyHash256 nextIndexColIdGen() {
		CFLibDbKeyHash256 retval = new CFLibDbKeyHash256(0);
		return( retval );
	}

	@Override
	public CFLibDbKeyHash256 nextParamIdGen() {
		CFLibDbKeyHash256 retval = new CFLibDbKeyHash256(0);
		return( retval );
	}

	@Override
	public CFLibDbKeyHash256 nextRelationColIdGen() {
		CFLibDbKeyHash256 retval = new CFLibDbKeyHash256(0);
		return( retval );
	}

	@Override
	public CFLibDbKeyHash256 nextScopeIdGen() {
		CFLibDbKeyHash256 retval = new CFLibDbKeyHash256(0);
		return( retval );
	}

	@Override
	public CFLibDbKeyHash256 nextValueIdGen() {
		CFLibDbKeyHash256 retval = new CFLibDbKeyHash256(0);
		return( retval );
	}

	public ICFBamAtomTable getTableAtom() {
		return( tableAtom );
	}

	public void setTableAtom( ICFBamAtomTable value ) {
		tableAtom = value;
	}

	public ICFBamAtomFactory getFactoryAtom() {
		return( factoryAtom );
	}

	public void setFactoryAtom( ICFBamAtomFactory value ) {
		factoryAtom = value;
	}

	public ICFBamBlobColTable getTableBlobCol() {
		return( tableBlobCol );
	}

	public void setTableBlobCol( ICFBamBlobColTable value ) {
		tableBlobCol = value;
	}

	public ICFBamBlobColFactory getFactoryBlobCol() {
		return( factoryBlobCol );
	}

	public void setFactoryBlobCol( ICFBamBlobColFactory value ) {
		factoryBlobCol = value;
	}

	public ICFBamBlobDefTable getTableBlobDef() {
		return( tableBlobDef );
	}

	public void setTableBlobDef( ICFBamBlobDefTable value ) {
		tableBlobDef = value;
	}

	public ICFBamBlobDefFactory getFactoryBlobDef() {
		return( factoryBlobDef );
	}

	public void setFactoryBlobDef( ICFBamBlobDefFactory value ) {
		factoryBlobDef = value;
	}

	public ICFBamBlobTypeTable getTableBlobType() {
		return( tableBlobType );
	}

	public void setTableBlobType( ICFBamBlobTypeTable value ) {
		tableBlobType = value;
	}

	public ICFBamBlobTypeFactory getFactoryBlobType() {
		return( factoryBlobType );
	}

	public void setFactoryBlobType( ICFBamBlobTypeFactory value ) {
		factoryBlobType = value;
	}

	public ICFBamBoolColTable getTableBoolCol() {
		return( tableBoolCol );
	}

	public void setTableBoolCol( ICFBamBoolColTable value ) {
		tableBoolCol = value;
	}

	public ICFBamBoolColFactory getFactoryBoolCol() {
		return( factoryBoolCol );
	}

	public void setFactoryBoolCol( ICFBamBoolColFactory value ) {
		factoryBoolCol = value;
	}

	public ICFBamBoolDefTable getTableBoolDef() {
		return( tableBoolDef );
	}

	public void setTableBoolDef( ICFBamBoolDefTable value ) {
		tableBoolDef = value;
	}

	public ICFBamBoolDefFactory getFactoryBoolDef() {
		return( factoryBoolDef );
	}

	public void setFactoryBoolDef( ICFBamBoolDefFactory value ) {
		factoryBoolDef = value;
	}

	public ICFBamBoolTypeTable getTableBoolType() {
		return( tableBoolType );
	}

	public void setTableBoolType( ICFBamBoolTypeTable value ) {
		tableBoolType = value;
	}

	public ICFBamBoolTypeFactory getFactoryBoolType() {
		return( factoryBoolType );
	}

	public void setFactoryBoolType( ICFBamBoolTypeFactory value ) {
		factoryBoolType = value;
	}

	public ICFBamChainTable getTableChain() {
		return( tableChain );
	}

	public void setTableChain( ICFBamChainTable value ) {
		tableChain = value;
	}

	public ICFBamChainFactory getFactoryChain() {
		return( factoryChain );
	}

	public void setFactoryChain( ICFBamChainFactory value ) {
		factoryChain = value;
	}

	public ICFBamClearDepTable getTableClearDep() {
		return( tableClearDep );
	}

	public void setTableClearDep( ICFBamClearDepTable value ) {
		tableClearDep = value;
	}

	public ICFBamClearDepFactory getFactoryClearDep() {
		return( factoryClearDep );
	}

	public void setFactoryClearDep( ICFBamClearDepFactory value ) {
		factoryClearDep = value;
	}

	public ICFBamClearSubDep1Table getTableClearSubDep1() {
		return( tableClearSubDep1 );
	}

	public void setTableClearSubDep1( ICFBamClearSubDep1Table value ) {
		tableClearSubDep1 = value;
	}

	public ICFBamClearSubDep1Factory getFactoryClearSubDep1() {
		return( factoryClearSubDep1 );
	}

	public void setFactoryClearSubDep1( ICFBamClearSubDep1Factory value ) {
		factoryClearSubDep1 = value;
	}

	public ICFBamClearSubDep2Table getTableClearSubDep2() {
		return( tableClearSubDep2 );
	}

	public void setTableClearSubDep2( ICFBamClearSubDep2Table value ) {
		tableClearSubDep2 = value;
	}

	public ICFBamClearSubDep2Factory getFactoryClearSubDep2() {
		return( factoryClearSubDep2 );
	}

	public void setFactoryClearSubDep2( ICFBamClearSubDep2Factory value ) {
		factoryClearSubDep2 = value;
	}

	public ICFBamClearSubDep3Table getTableClearSubDep3() {
		return( tableClearSubDep3 );
	}

	public void setTableClearSubDep3( ICFBamClearSubDep3Table value ) {
		tableClearSubDep3 = value;
	}

	public ICFBamClearSubDep3Factory getFactoryClearSubDep3() {
		return( factoryClearSubDep3 );
	}

	public void setFactoryClearSubDep3( ICFBamClearSubDep3Factory value ) {
		factoryClearSubDep3 = value;
	}

	public ICFBamClearTopDepTable getTableClearTopDep() {
		return( tableClearTopDep );
	}

	public void setTableClearTopDep( ICFBamClearTopDepTable value ) {
		tableClearTopDep = value;
	}

	public ICFBamClearTopDepFactory getFactoryClearTopDep() {
		return( factoryClearTopDep );
	}

	public void setFactoryClearTopDep( ICFBamClearTopDepFactory value ) {
		factoryClearTopDep = value;
	}

	public ICFSecClusterTable getTableCluster() {
		return( tableCluster );
	}

	public void setTableCluster( ICFSecClusterTable value ) {
		tableCluster = value;
	}

	public ICFSecClusterFactory getFactoryCluster() {
		return( factoryCluster );
	}

	public void setFactoryCluster( ICFSecClusterFactory value ) {
		factoryCluster = value;
	}

	public ICFBamDateColTable getTableDateCol() {
		return( tableDateCol );
	}

	public void setTableDateCol( ICFBamDateColTable value ) {
		tableDateCol = value;
	}

	public ICFBamDateColFactory getFactoryDateCol() {
		return( factoryDateCol );
	}

	public void setFactoryDateCol( ICFBamDateColFactory value ) {
		factoryDateCol = value;
	}

	public ICFBamDateDefTable getTableDateDef() {
		return( tableDateDef );
	}

	public void setTableDateDef( ICFBamDateDefTable value ) {
		tableDateDef = value;
	}

	public ICFBamDateDefFactory getFactoryDateDef() {
		return( factoryDateDef );
	}

	public void setFactoryDateDef( ICFBamDateDefFactory value ) {
		factoryDateDef = value;
	}

	public ICFBamDateTypeTable getTableDateType() {
		return( tableDateType );
	}

	public void setTableDateType( ICFBamDateTypeTable value ) {
		tableDateType = value;
	}

	public ICFBamDateTypeFactory getFactoryDateType() {
		return( factoryDateType );
	}

	public void setFactoryDateType( ICFBamDateTypeFactory value ) {
		factoryDateType = value;
	}

	public ICFBamDbKeyHash128ColTable getTableDbKeyHash128Col() {
		return( tableDbKeyHash128Col );
	}

	public void setTableDbKeyHash128Col( ICFBamDbKeyHash128ColTable value ) {
		tableDbKeyHash128Col = value;
	}

	public ICFBamDbKeyHash128ColFactory getFactoryDbKeyHash128Col() {
		return( factoryDbKeyHash128Col );
	}

	public void setFactoryDbKeyHash128Col( ICFBamDbKeyHash128ColFactory value ) {
		factoryDbKeyHash128Col = value;
	}

	public ICFBamDbKeyHash128DefTable getTableDbKeyHash128Def() {
		return( tableDbKeyHash128Def );
	}

	public void setTableDbKeyHash128Def( ICFBamDbKeyHash128DefTable value ) {
		tableDbKeyHash128Def = value;
	}

	public ICFBamDbKeyHash128DefFactory getFactoryDbKeyHash128Def() {
		return( factoryDbKeyHash128Def );
	}

	public void setFactoryDbKeyHash128Def( ICFBamDbKeyHash128DefFactory value ) {
		factoryDbKeyHash128Def = value;
	}

	public ICFBamDbKeyHash128GenTable getTableDbKeyHash128Gen() {
		return( tableDbKeyHash128Gen );
	}

	public void setTableDbKeyHash128Gen( ICFBamDbKeyHash128GenTable value ) {
		tableDbKeyHash128Gen = value;
	}

	public ICFBamDbKeyHash128GenFactory getFactoryDbKeyHash128Gen() {
		return( factoryDbKeyHash128Gen );
	}

	public void setFactoryDbKeyHash128Gen( ICFBamDbKeyHash128GenFactory value ) {
		factoryDbKeyHash128Gen = value;
	}

	public ICFBamDbKeyHash128TypeTable getTableDbKeyHash128Type() {
		return( tableDbKeyHash128Type );
	}

	public void setTableDbKeyHash128Type( ICFBamDbKeyHash128TypeTable value ) {
		tableDbKeyHash128Type = value;
	}

	public ICFBamDbKeyHash128TypeFactory getFactoryDbKeyHash128Type() {
		return( factoryDbKeyHash128Type );
	}

	public void setFactoryDbKeyHash128Type( ICFBamDbKeyHash128TypeFactory value ) {
		factoryDbKeyHash128Type = value;
	}

	public ICFBamDbKeyHash160ColTable getTableDbKeyHash160Col() {
		return( tableDbKeyHash160Col );
	}

	public void setTableDbKeyHash160Col( ICFBamDbKeyHash160ColTable value ) {
		tableDbKeyHash160Col = value;
	}

	public ICFBamDbKeyHash160ColFactory getFactoryDbKeyHash160Col() {
		return( factoryDbKeyHash160Col );
	}

	public void setFactoryDbKeyHash160Col( ICFBamDbKeyHash160ColFactory value ) {
		factoryDbKeyHash160Col = value;
	}

	public ICFBamDbKeyHash160DefTable getTableDbKeyHash160Def() {
		return( tableDbKeyHash160Def );
	}

	public void setTableDbKeyHash160Def( ICFBamDbKeyHash160DefTable value ) {
		tableDbKeyHash160Def = value;
	}

	public ICFBamDbKeyHash160DefFactory getFactoryDbKeyHash160Def() {
		return( factoryDbKeyHash160Def );
	}

	public void setFactoryDbKeyHash160Def( ICFBamDbKeyHash160DefFactory value ) {
		factoryDbKeyHash160Def = value;
	}

	public ICFBamDbKeyHash160GenTable getTableDbKeyHash160Gen() {
		return( tableDbKeyHash160Gen );
	}

	public void setTableDbKeyHash160Gen( ICFBamDbKeyHash160GenTable value ) {
		tableDbKeyHash160Gen = value;
	}

	public ICFBamDbKeyHash160GenFactory getFactoryDbKeyHash160Gen() {
		return( factoryDbKeyHash160Gen );
	}

	public void setFactoryDbKeyHash160Gen( ICFBamDbKeyHash160GenFactory value ) {
		factoryDbKeyHash160Gen = value;
	}

	public ICFBamDbKeyHash160TypeTable getTableDbKeyHash160Type() {
		return( tableDbKeyHash160Type );
	}

	public void setTableDbKeyHash160Type( ICFBamDbKeyHash160TypeTable value ) {
		tableDbKeyHash160Type = value;
	}

	public ICFBamDbKeyHash160TypeFactory getFactoryDbKeyHash160Type() {
		return( factoryDbKeyHash160Type );
	}

	public void setFactoryDbKeyHash160Type( ICFBamDbKeyHash160TypeFactory value ) {
		factoryDbKeyHash160Type = value;
	}

	public ICFBamDbKeyHash224ColTable getTableDbKeyHash224Col() {
		return( tableDbKeyHash224Col );
	}

	public void setTableDbKeyHash224Col( ICFBamDbKeyHash224ColTable value ) {
		tableDbKeyHash224Col = value;
	}

	public ICFBamDbKeyHash224ColFactory getFactoryDbKeyHash224Col() {
		return( factoryDbKeyHash224Col );
	}

	public void setFactoryDbKeyHash224Col( ICFBamDbKeyHash224ColFactory value ) {
		factoryDbKeyHash224Col = value;
	}

	public ICFBamDbKeyHash224DefTable getTableDbKeyHash224Def() {
		return( tableDbKeyHash224Def );
	}

	public void setTableDbKeyHash224Def( ICFBamDbKeyHash224DefTable value ) {
		tableDbKeyHash224Def = value;
	}

	public ICFBamDbKeyHash224DefFactory getFactoryDbKeyHash224Def() {
		return( factoryDbKeyHash224Def );
	}

	public void setFactoryDbKeyHash224Def( ICFBamDbKeyHash224DefFactory value ) {
		factoryDbKeyHash224Def = value;
	}

	public ICFBamDbKeyHash224GenTable getTableDbKeyHash224Gen() {
		return( tableDbKeyHash224Gen );
	}

	public void setTableDbKeyHash224Gen( ICFBamDbKeyHash224GenTable value ) {
		tableDbKeyHash224Gen = value;
	}

	public ICFBamDbKeyHash224GenFactory getFactoryDbKeyHash224Gen() {
		return( factoryDbKeyHash224Gen );
	}

	public void setFactoryDbKeyHash224Gen( ICFBamDbKeyHash224GenFactory value ) {
		factoryDbKeyHash224Gen = value;
	}

	public ICFBamDbKeyHash224TypeTable getTableDbKeyHash224Type() {
		return( tableDbKeyHash224Type );
	}

	public void setTableDbKeyHash224Type( ICFBamDbKeyHash224TypeTable value ) {
		tableDbKeyHash224Type = value;
	}

	public ICFBamDbKeyHash224TypeFactory getFactoryDbKeyHash224Type() {
		return( factoryDbKeyHash224Type );
	}

	public void setFactoryDbKeyHash224Type( ICFBamDbKeyHash224TypeFactory value ) {
		factoryDbKeyHash224Type = value;
	}

	public ICFBamDbKeyHash256ColTable getTableDbKeyHash256Col() {
		return( tableDbKeyHash256Col );
	}

	public void setTableDbKeyHash256Col( ICFBamDbKeyHash256ColTable value ) {
		tableDbKeyHash256Col = value;
	}

	public ICFBamDbKeyHash256ColFactory getFactoryDbKeyHash256Col() {
		return( factoryDbKeyHash256Col );
	}

	public void setFactoryDbKeyHash256Col( ICFBamDbKeyHash256ColFactory value ) {
		factoryDbKeyHash256Col = value;
	}

	public ICFBamDbKeyHash256DefTable getTableDbKeyHash256Def() {
		return( tableDbKeyHash256Def );
	}

	public void setTableDbKeyHash256Def( ICFBamDbKeyHash256DefTable value ) {
		tableDbKeyHash256Def = value;
	}

	public ICFBamDbKeyHash256DefFactory getFactoryDbKeyHash256Def() {
		return( factoryDbKeyHash256Def );
	}

	public void setFactoryDbKeyHash256Def( ICFBamDbKeyHash256DefFactory value ) {
		factoryDbKeyHash256Def = value;
	}

	public ICFBamDbKeyHash256GenTable getTableDbKeyHash256Gen() {
		return( tableDbKeyHash256Gen );
	}

	public void setTableDbKeyHash256Gen( ICFBamDbKeyHash256GenTable value ) {
		tableDbKeyHash256Gen = value;
	}

	public ICFBamDbKeyHash256GenFactory getFactoryDbKeyHash256Gen() {
		return( factoryDbKeyHash256Gen );
	}

	public void setFactoryDbKeyHash256Gen( ICFBamDbKeyHash256GenFactory value ) {
		factoryDbKeyHash256Gen = value;
	}

	public ICFBamDbKeyHash256TypeTable getTableDbKeyHash256Type() {
		return( tableDbKeyHash256Type );
	}

	public void setTableDbKeyHash256Type( ICFBamDbKeyHash256TypeTable value ) {
		tableDbKeyHash256Type = value;
	}

	public ICFBamDbKeyHash256TypeFactory getFactoryDbKeyHash256Type() {
		return( factoryDbKeyHash256Type );
	}

	public void setFactoryDbKeyHash256Type( ICFBamDbKeyHash256TypeFactory value ) {
		factoryDbKeyHash256Type = value;
	}

	public ICFBamDbKeyHash384ColTable getTableDbKeyHash384Col() {
		return( tableDbKeyHash384Col );
	}

	public void setTableDbKeyHash384Col( ICFBamDbKeyHash384ColTable value ) {
		tableDbKeyHash384Col = value;
	}

	public ICFBamDbKeyHash384ColFactory getFactoryDbKeyHash384Col() {
		return( factoryDbKeyHash384Col );
	}

	public void setFactoryDbKeyHash384Col( ICFBamDbKeyHash384ColFactory value ) {
		factoryDbKeyHash384Col = value;
	}

	public ICFBamDbKeyHash384DefTable getTableDbKeyHash384Def() {
		return( tableDbKeyHash384Def );
	}

	public void setTableDbKeyHash384Def( ICFBamDbKeyHash384DefTable value ) {
		tableDbKeyHash384Def = value;
	}

	public ICFBamDbKeyHash384DefFactory getFactoryDbKeyHash384Def() {
		return( factoryDbKeyHash384Def );
	}

	public void setFactoryDbKeyHash384Def( ICFBamDbKeyHash384DefFactory value ) {
		factoryDbKeyHash384Def = value;
	}

	public ICFBamDbKeyHash384GenTable getTableDbKeyHash384Gen() {
		return( tableDbKeyHash384Gen );
	}

	public void setTableDbKeyHash384Gen( ICFBamDbKeyHash384GenTable value ) {
		tableDbKeyHash384Gen = value;
	}

	public ICFBamDbKeyHash384GenFactory getFactoryDbKeyHash384Gen() {
		return( factoryDbKeyHash384Gen );
	}

	public void setFactoryDbKeyHash384Gen( ICFBamDbKeyHash384GenFactory value ) {
		factoryDbKeyHash384Gen = value;
	}

	public ICFBamDbKeyHash384TypeTable getTableDbKeyHash384Type() {
		return( tableDbKeyHash384Type );
	}

	public void setTableDbKeyHash384Type( ICFBamDbKeyHash384TypeTable value ) {
		tableDbKeyHash384Type = value;
	}

	public ICFBamDbKeyHash384TypeFactory getFactoryDbKeyHash384Type() {
		return( factoryDbKeyHash384Type );
	}

	public void setFactoryDbKeyHash384Type( ICFBamDbKeyHash384TypeFactory value ) {
		factoryDbKeyHash384Type = value;
	}

	public ICFBamDbKeyHash512ColTable getTableDbKeyHash512Col() {
		return( tableDbKeyHash512Col );
	}

	public void setTableDbKeyHash512Col( ICFBamDbKeyHash512ColTable value ) {
		tableDbKeyHash512Col = value;
	}

	public ICFBamDbKeyHash512ColFactory getFactoryDbKeyHash512Col() {
		return( factoryDbKeyHash512Col );
	}

	public void setFactoryDbKeyHash512Col( ICFBamDbKeyHash512ColFactory value ) {
		factoryDbKeyHash512Col = value;
	}

	public ICFBamDbKeyHash512DefTable getTableDbKeyHash512Def() {
		return( tableDbKeyHash512Def );
	}

	public void setTableDbKeyHash512Def( ICFBamDbKeyHash512DefTable value ) {
		tableDbKeyHash512Def = value;
	}

	public ICFBamDbKeyHash512DefFactory getFactoryDbKeyHash512Def() {
		return( factoryDbKeyHash512Def );
	}

	public void setFactoryDbKeyHash512Def( ICFBamDbKeyHash512DefFactory value ) {
		factoryDbKeyHash512Def = value;
	}

	public ICFBamDbKeyHash512GenTable getTableDbKeyHash512Gen() {
		return( tableDbKeyHash512Gen );
	}

	public void setTableDbKeyHash512Gen( ICFBamDbKeyHash512GenTable value ) {
		tableDbKeyHash512Gen = value;
	}

	public ICFBamDbKeyHash512GenFactory getFactoryDbKeyHash512Gen() {
		return( factoryDbKeyHash512Gen );
	}

	public void setFactoryDbKeyHash512Gen( ICFBamDbKeyHash512GenFactory value ) {
		factoryDbKeyHash512Gen = value;
	}

	public ICFBamDbKeyHash512TypeTable getTableDbKeyHash512Type() {
		return( tableDbKeyHash512Type );
	}

	public void setTableDbKeyHash512Type( ICFBamDbKeyHash512TypeTable value ) {
		tableDbKeyHash512Type = value;
	}

	public ICFBamDbKeyHash512TypeFactory getFactoryDbKeyHash512Type() {
		return( factoryDbKeyHash512Type );
	}

	public void setFactoryDbKeyHash512Type( ICFBamDbKeyHash512TypeFactory value ) {
		factoryDbKeyHash512Type = value;
	}

	public ICFBamDelDepTable getTableDelDep() {
		return( tableDelDep );
	}

	public void setTableDelDep( ICFBamDelDepTable value ) {
		tableDelDep = value;
	}

	public ICFBamDelDepFactory getFactoryDelDep() {
		return( factoryDelDep );
	}

	public void setFactoryDelDep( ICFBamDelDepFactory value ) {
		factoryDelDep = value;
	}

	public ICFBamDelSubDep1Table getTableDelSubDep1() {
		return( tableDelSubDep1 );
	}

	public void setTableDelSubDep1( ICFBamDelSubDep1Table value ) {
		tableDelSubDep1 = value;
	}

	public ICFBamDelSubDep1Factory getFactoryDelSubDep1() {
		return( factoryDelSubDep1 );
	}

	public void setFactoryDelSubDep1( ICFBamDelSubDep1Factory value ) {
		factoryDelSubDep1 = value;
	}

	public ICFBamDelSubDep2Table getTableDelSubDep2() {
		return( tableDelSubDep2 );
	}

	public void setTableDelSubDep2( ICFBamDelSubDep2Table value ) {
		tableDelSubDep2 = value;
	}

	public ICFBamDelSubDep2Factory getFactoryDelSubDep2() {
		return( factoryDelSubDep2 );
	}

	public void setFactoryDelSubDep2( ICFBamDelSubDep2Factory value ) {
		factoryDelSubDep2 = value;
	}

	public ICFBamDelSubDep3Table getTableDelSubDep3() {
		return( tableDelSubDep3 );
	}

	public void setTableDelSubDep3( ICFBamDelSubDep3Table value ) {
		tableDelSubDep3 = value;
	}

	public ICFBamDelSubDep3Factory getFactoryDelSubDep3() {
		return( factoryDelSubDep3 );
	}

	public void setFactoryDelSubDep3( ICFBamDelSubDep3Factory value ) {
		factoryDelSubDep3 = value;
	}

	public ICFBamDelTopDepTable getTableDelTopDep() {
		return( tableDelTopDep );
	}

	public void setTableDelTopDep( ICFBamDelTopDepTable value ) {
		tableDelTopDep = value;
	}

	public ICFBamDelTopDepFactory getFactoryDelTopDep() {
		return( factoryDelTopDep );
	}

	public void setFactoryDelTopDep( ICFBamDelTopDepFactory value ) {
		factoryDelTopDep = value;
	}

	public ICFBamDoubleColTable getTableDoubleCol() {
		return( tableDoubleCol );
	}

	public void setTableDoubleCol( ICFBamDoubleColTable value ) {
		tableDoubleCol = value;
	}

	public ICFBamDoubleColFactory getFactoryDoubleCol() {
		return( factoryDoubleCol );
	}

	public void setFactoryDoubleCol( ICFBamDoubleColFactory value ) {
		factoryDoubleCol = value;
	}

	public ICFBamDoubleDefTable getTableDoubleDef() {
		return( tableDoubleDef );
	}

	public void setTableDoubleDef( ICFBamDoubleDefTable value ) {
		tableDoubleDef = value;
	}

	public ICFBamDoubleDefFactory getFactoryDoubleDef() {
		return( factoryDoubleDef );
	}

	public void setFactoryDoubleDef( ICFBamDoubleDefFactory value ) {
		factoryDoubleDef = value;
	}

	public ICFBamDoubleTypeTable getTableDoubleType() {
		return( tableDoubleType );
	}

	public void setTableDoubleType( ICFBamDoubleTypeTable value ) {
		tableDoubleType = value;
	}

	public ICFBamDoubleTypeFactory getFactoryDoubleType() {
		return( factoryDoubleType );
	}

	public void setFactoryDoubleType( ICFBamDoubleTypeFactory value ) {
		factoryDoubleType = value;
	}

	public ICFBamEnumDefTable getTableEnumDef() {
		return( tableEnumDef );
	}

	public void setTableEnumDef( ICFBamEnumDefTable value ) {
		tableEnumDef = value;
	}

	public ICFBamEnumDefFactory getFactoryEnumDef() {
		return( factoryEnumDef );
	}

	public void setFactoryEnumDef( ICFBamEnumDefFactory value ) {
		factoryEnumDef = value;
	}

	public ICFBamEnumTagTable getTableEnumTag() {
		return( tableEnumTag );
	}

	public void setTableEnumTag( ICFBamEnumTagTable value ) {
		tableEnumTag = value;
	}

	public ICFBamEnumTagFactory getFactoryEnumTag() {
		return( factoryEnumTag );
	}

	public void setFactoryEnumTag( ICFBamEnumTagFactory value ) {
		factoryEnumTag = value;
	}

	public ICFBamEnumTypeTable getTableEnumType() {
		return( tableEnumType );
	}

	public void setTableEnumType( ICFBamEnumTypeTable value ) {
		tableEnumType = value;
	}

	public ICFBamEnumTypeFactory getFactoryEnumType() {
		return( factoryEnumType );
	}

	public void setFactoryEnumType( ICFBamEnumTypeFactory value ) {
		factoryEnumType = value;
	}

	public ICFBamFloatColTable getTableFloatCol() {
		return( tableFloatCol );
	}

	public void setTableFloatCol( ICFBamFloatColTable value ) {
		tableFloatCol = value;
	}

	public ICFBamFloatColFactory getFactoryFloatCol() {
		return( factoryFloatCol );
	}

	public void setFactoryFloatCol( ICFBamFloatColFactory value ) {
		factoryFloatCol = value;
	}

	public ICFBamFloatDefTable getTableFloatDef() {
		return( tableFloatDef );
	}

	public void setTableFloatDef( ICFBamFloatDefTable value ) {
		tableFloatDef = value;
	}

	public ICFBamFloatDefFactory getFactoryFloatDef() {
		return( factoryFloatDef );
	}

	public void setFactoryFloatDef( ICFBamFloatDefFactory value ) {
		factoryFloatDef = value;
	}

	public ICFBamFloatTypeTable getTableFloatType() {
		return( tableFloatType );
	}

	public void setTableFloatType( ICFBamFloatTypeTable value ) {
		tableFloatType = value;
	}

	public ICFBamFloatTypeFactory getFactoryFloatType() {
		return( factoryFloatType );
	}

	public void setFactoryFloatType( ICFBamFloatTypeFactory value ) {
		factoryFloatType = value;
	}

	public ICFSecHostNodeTable getTableHostNode() {
		return( tableHostNode );
	}

	public void setTableHostNode( ICFSecHostNodeTable value ) {
		tableHostNode = value;
	}

	public ICFSecHostNodeFactory getFactoryHostNode() {
		return( factoryHostNode );
	}

	public void setFactoryHostNode( ICFSecHostNodeFactory value ) {
		factoryHostNode = value;
	}

	public ICFSecISOCcyTable getTableISOCcy() {
		return( tableISOCcy );
	}

	public void setTableISOCcy( ICFSecISOCcyTable value ) {
		tableISOCcy = value;
	}

	public ICFSecISOCcyFactory getFactoryISOCcy() {
		return( factoryISOCcy );
	}

	public void setFactoryISOCcy( ICFSecISOCcyFactory value ) {
		factoryISOCcy = value;
	}

	public ICFSecISOCtryTable getTableISOCtry() {
		return( tableISOCtry );
	}

	public void setTableISOCtry( ICFSecISOCtryTable value ) {
		tableISOCtry = value;
	}

	public ICFSecISOCtryFactory getFactoryISOCtry() {
		return( factoryISOCtry );
	}

	public void setFactoryISOCtry( ICFSecISOCtryFactory value ) {
		factoryISOCtry = value;
	}

	public ICFSecISOCtryCcyTable getTableISOCtryCcy() {
		return( tableISOCtryCcy );
	}

	public void setTableISOCtryCcy( ICFSecISOCtryCcyTable value ) {
		tableISOCtryCcy = value;
	}

	public ICFSecISOCtryCcyFactory getFactoryISOCtryCcy() {
		return( factoryISOCtryCcy );
	}

	public void setFactoryISOCtryCcy( ICFSecISOCtryCcyFactory value ) {
		factoryISOCtryCcy = value;
	}

	public ICFSecISOCtryLangTable getTableISOCtryLang() {
		return( tableISOCtryLang );
	}

	public void setTableISOCtryLang( ICFSecISOCtryLangTable value ) {
		tableISOCtryLang = value;
	}

	public ICFSecISOCtryLangFactory getFactoryISOCtryLang() {
		return( factoryISOCtryLang );
	}

	public void setFactoryISOCtryLang( ICFSecISOCtryLangFactory value ) {
		factoryISOCtryLang = value;
	}

	public ICFSecISOLangTable getTableISOLang() {
		return( tableISOLang );
	}

	public void setTableISOLang( ICFSecISOLangTable value ) {
		tableISOLang = value;
	}

	public ICFSecISOLangFactory getFactoryISOLang() {
		return( factoryISOLang );
	}

	public void setFactoryISOLang( ICFSecISOLangFactory value ) {
		factoryISOLang = value;
	}

	public ICFSecISOTZoneTable getTableISOTZone() {
		return( tableISOTZone );
	}

	public void setTableISOTZone( ICFSecISOTZoneTable value ) {
		tableISOTZone = value;
	}

	public ICFSecISOTZoneFactory getFactoryISOTZone() {
		return( factoryISOTZone );
	}

	public void setFactoryISOTZone( ICFSecISOTZoneFactory value ) {
		factoryISOTZone = value;
	}

	public ICFBamId16GenTable getTableId16Gen() {
		return( tableId16Gen );
	}

	public void setTableId16Gen( ICFBamId16GenTable value ) {
		tableId16Gen = value;
	}

	public ICFBamId16GenFactory getFactoryId16Gen() {
		return( factoryId16Gen );
	}

	public void setFactoryId16Gen( ICFBamId16GenFactory value ) {
		factoryId16Gen = value;
	}

	public ICFBamId32GenTable getTableId32Gen() {
		return( tableId32Gen );
	}

	public void setTableId32Gen( ICFBamId32GenTable value ) {
		tableId32Gen = value;
	}

	public ICFBamId32GenFactory getFactoryId32Gen() {
		return( factoryId32Gen );
	}

	public void setFactoryId32Gen( ICFBamId32GenFactory value ) {
		factoryId32Gen = value;
	}

	public ICFBamId64GenTable getTableId64Gen() {
		return( tableId64Gen );
	}

	public void setTableId64Gen( ICFBamId64GenTable value ) {
		tableId64Gen = value;
	}

	public ICFBamId64GenFactory getFactoryId64Gen() {
		return( factoryId64Gen );
	}

	public void setFactoryId64Gen( ICFBamId64GenFactory value ) {
		factoryId64Gen = value;
	}

	public ICFBamIndexTable getTableIndex() {
		return( tableIndex );
	}

	public void setTableIndex( ICFBamIndexTable value ) {
		tableIndex = value;
	}

	public ICFBamIndexFactory getFactoryIndex() {
		return( factoryIndex );
	}

	public void setFactoryIndex( ICFBamIndexFactory value ) {
		factoryIndex = value;
	}

	public ICFBamIndexColTable getTableIndexCol() {
		return( tableIndexCol );
	}

	public void setTableIndexCol( ICFBamIndexColTable value ) {
		tableIndexCol = value;
	}

	public ICFBamIndexColFactory getFactoryIndexCol() {
		return( factoryIndexCol );
	}

	public void setFactoryIndexCol( ICFBamIndexColFactory value ) {
		factoryIndexCol = value;
	}

	public ICFBamInt16ColTable getTableInt16Col() {
		return( tableInt16Col );
	}

	public void setTableInt16Col( ICFBamInt16ColTable value ) {
		tableInt16Col = value;
	}

	public ICFBamInt16ColFactory getFactoryInt16Col() {
		return( factoryInt16Col );
	}

	public void setFactoryInt16Col( ICFBamInt16ColFactory value ) {
		factoryInt16Col = value;
	}

	public ICFBamInt16DefTable getTableInt16Def() {
		return( tableInt16Def );
	}

	public void setTableInt16Def( ICFBamInt16DefTable value ) {
		tableInt16Def = value;
	}

	public ICFBamInt16DefFactory getFactoryInt16Def() {
		return( factoryInt16Def );
	}

	public void setFactoryInt16Def( ICFBamInt16DefFactory value ) {
		factoryInt16Def = value;
	}

	public ICFBamInt16TypeTable getTableInt16Type() {
		return( tableInt16Type );
	}

	public void setTableInt16Type( ICFBamInt16TypeTable value ) {
		tableInt16Type = value;
	}

	public ICFBamInt16TypeFactory getFactoryInt16Type() {
		return( factoryInt16Type );
	}

	public void setFactoryInt16Type( ICFBamInt16TypeFactory value ) {
		factoryInt16Type = value;
	}

	public ICFBamInt32ColTable getTableInt32Col() {
		return( tableInt32Col );
	}

	public void setTableInt32Col( ICFBamInt32ColTable value ) {
		tableInt32Col = value;
	}

	public ICFBamInt32ColFactory getFactoryInt32Col() {
		return( factoryInt32Col );
	}

	public void setFactoryInt32Col( ICFBamInt32ColFactory value ) {
		factoryInt32Col = value;
	}

	public ICFBamInt32DefTable getTableInt32Def() {
		return( tableInt32Def );
	}

	public void setTableInt32Def( ICFBamInt32DefTable value ) {
		tableInt32Def = value;
	}

	public ICFBamInt32DefFactory getFactoryInt32Def() {
		return( factoryInt32Def );
	}

	public void setFactoryInt32Def( ICFBamInt32DefFactory value ) {
		factoryInt32Def = value;
	}

	public ICFBamInt32TypeTable getTableInt32Type() {
		return( tableInt32Type );
	}

	public void setTableInt32Type( ICFBamInt32TypeTable value ) {
		tableInt32Type = value;
	}

	public ICFBamInt32TypeFactory getFactoryInt32Type() {
		return( factoryInt32Type );
	}

	public void setFactoryInt32Type( ICFBamInt32TypeFactory value ) {
		factoryInt32Type = value;
	}

	public ICFBamInt64ColTable getTableInt64Col() {
		return( tableInt64Col );
	}

	public void setTableInt64Col( ICFBamInt64ColTable value ) {
		tableInt64Col = value;
	}

	public ICFBamInt64ColFactory getFactoryInt64Col() {
		return( factoryInt64Col );
	}

	public void setFactoryInt64Col( ICFBamInt64ColFactory value ) {
		factoryInt64Col = value;
	}

	public ICFBamInt64DefTable getTableInt64Def() {
		return( tableInt64Def );
	}

	public void setTableInt64Def( ICFBamInt64DefTable value ) {
		tableInt64Def = value;
	}

	public ICFBamInt64DefFactory getFactoryInt64Def() {
		return( factoryInt64Def );
	}

	public void setFactoryInt64Def( ICFBamInt64DefFactory value ) {
		factoryInt64Def = value;
	}

	public ICFBamInt64TypeTable getTableInt64Type() {
		return( tableInt64Type );
	}

	public void setTableInt64Type( ICFBamInt64TypeTable value ) {
		tableInt64Type = value;
	}

	public ICFBamInt64TypeFactory getFactoryInt64Type() {
		return( factoryInt64Type );
	}

	public void setFactoryInt64Type( ICFBamInt64TypeFactory value ) {
		factoryInt64Type = value;
	}

	public ICFIntLicenseTable getTableLicense() {
		return( tableLicense );
	}

	public void setTableLicense( ICFIntLicenseTable value ) {
		tableLicense = value;
	}

	public ICFIntLicenseFactory getFactoryLicense() {
		return( factoryLicense );
	}

	public void setFactoryLicense( ICFIntLicenseFactory value ) {
		factoryLicense = value;
	}

	public ICFIntMajorVersionTable getTableMajorVersion() {
		return( tableMajorVersion );
	}

	public void setTableMajorVersion( ICFIntMajorVersionTable value ) {
		tableMajorVersion = value;
	}

	public ICFIntMajorVersionFactory getFactoryMajorVersion() {
		return( factoryMajorVersion );
	}

	public void setFactoryMajorVersion( ICFIntMajorVersionFactory value ) {
		factoryMajorVersion = value;
	}

	public ICFIntMimeTypeTable getTableMimeType() {
		return( tableMimeType );
	}

	public void setTableMimeType( ICFIntMimeTypeTable value ) {
		tableMimeType = value;
	}

	public ICFIntMimeTypeFactory getFactoryMimeType() {
		return( factoryMimeType );
	}

	public void setFactoryMimeType( ICFIntMimeTypeFactory value ) {
		factoryMimeType = value;
	}

	public ICFIntMinorVersionTable getTableMinorVersion() {
		return( tableMinorVersion );
	}

	public void setTableMinorVersion( ICFIntMinorVersionTable value ) {
		tableMinorVersion = value;
	}

	public ICFIntMinorVersionFactory getFactoryMinorVersion() {
		return( factoryMinorVersion );
	}

	public void setFactoryMinorVersion( ICFIntMinorVersionFactory value ) {
		factoryMinorVersion = value;
	}

	public ICFBamNmTokenColTable getTableNmTokenCol() {
		return( tableNmTokenCol );
	}

	public void setTableNmTokenCol( ICFBamNmTokenColTable value ) {
		tableNmTokenCol = value;
	}

	public ICFBamNmTokenColFactory getFactoryNmTokenCol() {
		return( factoryNmTokenCol );
	}

	public void setFactoryNmTokenCol( ICFBamNmTokenColFactory value ) {
		factoryNmTokenCol = value;
	}

	public ICFBamNmTokenDefTable getTableNmTokenDef() {
		return( tableNmTokenDef );
	}

	public void setTableNmTokenDef( ICFBamNmTokenDefTable value ) {
		tableNmTokenDef = value;
	}

	public ICFBamNmTokenDefFactory getFactoryNmTokenDef() {
		return( factoryNmTokenDef );
	}

	public void setFactoryNmTokenDef( ICFBamNmTokenDefFactory value ) {
		factoryNmTokenDef = value;
	}

	public ICFBamNmTokenTypeTable getTableNmTokenType() {
		return( tableNmTokenType );
	}

	public void setTableNmTokenType( ICFBamNmTokenTypeTable value ) {
		tableNmTokenType = value;
	}

	public ICFBamNmTokenTypeFactory getFactoryNmTokenType() {
		return( factoryNmTokenType );
	}

	public void setFactoryNmTokenType( ICFBamNmTokenTypeFactory value ) {
		factoryNmTokenType = value;
	}

	public ICFBamNmTokensColTable getTableNmTokensCol() {
		return( tableNmTokensCol );
	}

	public void setTableNmTokensCol( ICFBamNmTokensColTable value ) {
		tableNmTokensCol = value;
	}

	public ICFBamNmTokensColFactory getFactoryNmTokensCol() {
		return( factoryNmTokensCol );
	}

	public void setFactoryNmTokensCol( ICFBamNmTokensColFactory value ) {
		factoryNmTokensCol = value;
	}

	public ICFBamNmTokensDefTable getTableNmTokensDef() {
		return( tableNmTokensDef );
	}

	public void setTableNmTokensDef( ICFBamNmTokensDefTable value ) {
		tableNmTokensDef = value;
	}

	public ICFBamNmTokensDefFactory getFactoryNmTokensDef() {
		return( factoryNmTokensDef );
	}

	public void setFactoryNmTokensDef( ICFBamNmTokensDefFactory value ) {
		factoryNmTokensDef = value;
	}

	public ICFBamNmTokensTypeTable getTableNmTokensType() {
		return( tableNmTokensType );
	}

	public void setTableNmTokensType( ICFBamNmTokensTypeTable value ) {
		tableNmTokensType = value;
	}

	public ICFBamNmTokensTypeFactory getFactoryNmTokensType() {
		return( factoryNmTokensType );
	}

	public void setFactoryNmTokensType( ICFBamNmTokensTypeFactory value ) {
		factoryNmTokensType = value;
	}

	public ICFBamNumberColTable getTableNumberCol() {
		return( tableNumberCol );
	}

	public void setTableNumberCol( ICFBamNumberColTable value ) {
		tableNumberCol = value;
	}

	public ICFBamNumberColFactory getFactoryNumberCol() {
		return( factoryNumberCol );
	}

	public void setFactoryNumberCol( ICFBamNumberColFactory value ) {
		factoryNumberCol = value;
	}

	public ICFBamNumberDefTable getTableNumberDef() {
		return( tableNumberDef );
	}

	public void setTableNumberDef( ICFBamNumberDefTable value ) {
		tableNumberDef = value;
	}

	public ICFBamNumberDefFactory getFactoryNumberDef() {
		return( factoryNumberDef );
	}

	public void setFactoryNumberDef( ICFBamNumberDefFactory value ) {
		factoryNumberDef = value;
	}

	public ICFBamNumberTypeTable getTableNumberType() {
		return( tableNumberType );
	}

	public void setTableNumberType( ICFBamNumberTypeTable value ) {
		tableNumberType = value;
	}

	public ICFBamNumberTypeFactory getFactoryNumberType() {
		return( factoryNumberType );
	}

	public void setFactoryNumberType( ICFBamNumberTypeFactory value ) {
		factoryNumberType = value;
	}

	public ICFBamParamTable getTableParam() {
		return( tableParam );
	}

	public void setTableParam( ICFBamParamTable value ) {
		tableParam = value;
	}

	public ICFBamParamFactory getFactoryParam() {
		return( factoryParam );
	}

	public void setFactoryParam( ICFBamParamFactory value ) {
		factoryParam = value;
	}

	public ICFBamPopDepTable getTablePopDep() {
		return( tablePopDep );
	}

	public void setTablePopDep( ICFBamPopDepTable value ) {
		tablePopDep = value;
	}

	public ICFBamPopDepFactory getFactoryPopDep() {
		return( factoryPopDep );
	}

	public void setFactoryPopDep( ICFBamPopDepFactory value ) {
		factoryPopDep = value;
	}

	public ICFBamPopSubDep1Table getTablePopSubDep1() {
		return( tablePopSubDep1 );
	}

	public void setTablePopSubDep1( ICFBamPopSubDep1Table value ) {
		tablePopSubDep1 = value;
	}

	public ICFBamPopSubDep1Factory getFactoryPopSubDep1() {
		return( factoryPopSubDep1 );
	}

	public void setFactoryPopSubDep1( ICFBamPopSubDep1Factory value ) {
		factoryPopSubDep1 = value;
	}

	public ICFBamPopSubDep2Table getTablePopSubDep2() {
		return( tablePopSubDep2 );
	}

	public void setTablePopSubDep2( ICFBamPopSubDep2Table value ) {
		tablePopSubDep2 = value;
	}

	public ICFBamPopSubDep2Factory getFactoryPopSubDep2() {
		return( factoryPopSubDep2 );
	}

	public void setFactoryPopSubDep2( ICFBamPopSubDep2Factory value ) {
		factoryPopSubDep2 = value;
	}

	public ICFBamPopSubDep3Table getTablePopSubDep3() {
		return( tablePopSubDep3 );
	}

	public void setTablePopSubDep3( ICFBamPopSubDep3Table value ) {
		tablePopSubDep3 = value;
	}

	public ICFBamPopSubDep3Factory getFactoryPopSubDep3() {
		return( factoryPopSubDep3 );
	}

	public void setFactoryPopSubDep3( ICFBamPopSubDep3Factory value ) {
		factoryPopSubDep3 = value;
	}

	public ICFBamPopTopDepTable getTablePopTopDep() {
		return( tablePopTopDep );
	}

	public void setTablePopTopDep( ICFBamPopTopDepTable value ) {
		tablePopTopDep = value;
	}

	public ICFBamPopTopDepFactory getFactoryPopTopDep() {
		return( factoryPopTopDep );
	}

	public void setFactoryPopTopDep( ICFBamPopTopDepFactory value ) {
		factoryPopTopDep = value;
	}

	public ICFBamRelationTable getTableRelation() {
		return( tableRelation );
	}

	public void setTableRelation( ICFBamRelationTable value ) {
		tableRelation = value;
	}

	public ICFBamRelationFactory getFactoryRelation() {
		return( factoryRelation );
	}

	public void setFactoryRelation( ICFBamRelationFactory value ) {
		factoryRelation = value;
	}

	public ICFBamRelationColTable getTableRelationCol() {
		return( tableRelationCol );
	}

	public void setTableRelationCol( ICFBamRelationColTable value ) {
		tableRelationCol = value;
	}

	public ICFBamRelationColFactory getFactoryRelationCol() {
		return( factoryRelationCol );
	}

	public void setFactoryRelationCol( ICFBamRelationColFactory value ) {
		factoryRelationCol = value;
	}

	public ICFBamSchemaDefTable getTableSchemaDef() {
		return( tableSchemaDef );
	}

	public void setTableSchemaDef( ICFBamSchemaDefTable value ) {
		tableSchemaDef = value;
	}

	public ICFBamSchemaDefFactory getFactorySchemaDef() {
		return( factorySchemaDef );
	}

	public void setFactorySchemaDef( ICFBamSchemaDefFactory value ) {
		factorySchemaDef = value;
	}

	public ICFBamSchemaRefTable getTableSchemaRef() {
		return( tableSchemaRef );
	}

	public void setTableSchemaRef( ICFBamSchemaRefTable value ) {
		tableSchemaRef = value;
	}

	public ICFBamSchemaRefFactory getFactorySchemaRef() {
		return( factorySchemaRef );
	}

	public void setFactorySchemaRef( ICFBamSchemaRefFactory value ) {
		factorySchemaRef = value;
	}

	public ICFBamScopeTable getTableScope() {
		return( tableScope );
	}

	public void setTableScope( ICFBamScopeTable value ) {
		tableScope = value;
	}

	public ICFBamScopeFactory getFactoryScope() {
		return( factoryScope );
	}

	public void setFactoryScope( ICFBamScopeFactory value ) {
		factoryScope = value;
	}

	public ICFSecSecDeviceTable getTableSecDevice() {
		return( tableSecDevice );
	}

	public void setTableSecDevice( ICFSecSecDeviceTable value ) {
		tableSecDevice = value;
	}

	public ICFSecSecDeviceFactory getFactorySecDevice() {
		return( factorySecDevice );
	}

	public void setFactorySecDevice( ICFSecSecDeviceFactory value ) {
		factorySecDevice = value;
	}

	public ICFSecSecGroupTable getTableSecGroup() {
		return( tableSecGroup );
	}

	public void setTableSecGroup( ICFSecSecGroupTable value ) {
		tableSecGroup = value;
	}

	public ICFSecSecGroupFactory getFactorySecGroup() {
		return( factorySecGroup );
	}

	public void setFactorySecGroup( ICFSecSecGroupFactory value ) {
		factorySecGroup = value;
	}

	public ICFSecSecGrpIncTable getTableSecGrpInc() {
		return( tableSecGrpInc );
	}

	public void setTableSecGrpInc( ICFSecSecGrpIncTable value ) {
		tableSecGrpInc = value;
	}

	public ICFSecSecGrpIncFactory getFactorySecGrpInc() {
		return( factorySecGrpInc );
	}

	public void setFactorySecGrpInc( ICFSecSecGrpIncFactory value ) {
		factorySecGrpInc = value;
	}

	public ICFSecSecGrpMembTable getTableSecGrpMemb() {
		return( tableSecGrpMemb );
	}

	public void setTableSecGrpMemb( ICFSecSecGrpMembTable value ) {
		tableSecGrpMemb = value;
	}

	public ICFSecSecGrpMembFactory getFactorySecGrpMemb() {
		return( factorySecGrpMemb );
	}

	public void setFactorySecGrpMemb( ICFSecSecGrpMembFactory value ) {
		factorySecGrpMemb = value;
	}

	public ICFSecSecSessionTable getTableSecSession() {
		return( tableSecSession );
	}

	public void setTableSecSession( ICFSecSecSessionTable value ) {
		tableSecSession = value;
	}

	public ICFSecSecSessionFactory getFactorySecSession() {
		return( factorySecSession );
	}

	public void setFactorySecSession( ICFSecSecSessionFactory value ) {
		factorySecSession = value;
	}

	public ICFSecSecUserTable getTableSecUser() {
		return( tableSecUser );
	}

	public void setTableSecUser( ICFSecSecUserTable value ) {
		tableSecUser = value;
	}

	public ICFSecSecUserFactory getFactorySecUser() {
		return( factorySecUser );
	}

	public void setFactorySecUser( ICFSecSecUserFactory value ) {
		factorySecUser = value;
	}

	public ICFBamServerListFuncTable getTableServerListFunc() {
		return( tableServerListFunc );
	}

	public void setTableServerListFunc( ICFBamServerListFuncTable value ) {
		tableServerListFunc = value;
	}

	public ICFBamServerListFuncFactory getFactoryServerListFunc() {
		return( factoryServerListFunc );
	}

	public void setFactoryServerListFunc( ICFBamServerListFuncFactory value ) {
		factoryServerListFunc = value;
	}

	public ICFBamServerMethodTable getTableServerMethod() {
		return( tableServerMethod );
	}

	public void setTableServerMethod( ICFBamServerMethodTable value ) {
		tableServerMethod = value;
	}

	public ICFBamServerMethodFactory getFactoryServerMethod() {
		return( factoryServerMethod );
	}

	public void setFactoryServerMethod( ICFBamServerMethodFactory value ) {
		factoryServerMethod = value;
	}

	public ICFBamServerObjFuncTable getTableServerObjFunc() {
		return( tableServerObjFunc );
	}

	public void setTableServerObjFunc( ICFBamServerObjFuncTable value ) {
		tableServerObjFunc = value;
	}

	public ICFBamServerObjFuncFactory getFactoryServerObjFunc() {
		return( factoryServerObjFunc );
	}

	public void setFactoryServerObjFunc( ICFBamServerObjFuncFactory value ) {
		factoryServerObjFunc = value;
	}

	public ICFBamServerProcTable getTableServerProc() {
		return( tableServerProc );
	}

	public void setTableServerProc( ICFBamServerProcTable value ) {
		tableServerProc = value;
	}

	public ICFBamServerProcFactory getFactoryServerProc() {
		return( factoryServerProc );
	}

	public void setFactoryServerProc( ICFBamServerProcFactory value ) {
		factoryServerProc = value;
	}

	public ICFSecServiceTable getTableService() {
		return( tableService );
	}

	public void setTableService( ICFSecServiceTable value ) {
		tableService = value;
	}

	public ICFSecServiceFactory getFactoryService() {
		return( factoryService );
	}

	public void setFactoryService( ICFSecServiceFactory value ) {
		factoryService = value;
	}

	public ICFSecServiceTypeTable getTableServiceType() {
		return( tableServiceType );
	}

	public void setTableServiceType( ICFSecServiceTypeTable value ) {
		tableServiceType = value;
	}

	public ICFSecServiceTypeFactory getFactoryServiceType() {
		return( factoryServiceType );
	}

	public void setFactoryServiceType( ICFSecServiceTypeFactory value ) {
		factoryServiceType = value;
	}

	public ICFBamStringColTable getTableStringCol() {
		return( tableStringCol );
	}

	public void setTableStringCol( ICFBamStringColTable value ) {
		tableStringCol = value;
	}

	public ICFBamStringColFactory getFactoryStringCol() {
		return( factoryStringCol );
	}

	public void setFactoryStringCol( ICFBamStringColFactory value ) {
		factoryStringCol = value;
	}

	public ICFBamStringDefTable getTableStringDef() {
		return( tableStringDef );
	}

	public void setTableStringDef( ICFBamStringDefTable value ) {
		tableStringDef = value;
	}

	public ICFBamStringDefFactory getFactoryStringDef() {
		return( factoryStringDef );
	}

	public void setFactoryStringDef( ICFBamStringDefFactory value ) {
		factoryStringDef = value;
	}

	public ICFBamStringTypeTable getTableStringType() {
		return( tableStringType );
	}

	public void setTableStringType( ICFBamStringTypeTable value ) {
		tableStringType = value;
	}

	public ICFBamStringTypeFactory getFactoryStringType() {
		return( factoryStringType );
	}

	public void setFactoryStringType( ICFBamStringTypeFactory value ) {
		factoryStringType = value;
	}

	public ICFIntSubProjectTable getTableSubProject() {
		return( tableSubProject );
	}

	public void setTableSubProject( ICFIntSubProjectTable value ) {
		tableSubProject = value;
	}

	public ICFIntSubProjectFactory getFactorySubProject() {
		return( factorySubProject );
	}

	public void setFactorySubProject( ICFIntSubProjectFactory value ) {
		factorySubProject = value;
	}

	public ICFSecSysClusterTable getTableSysCluster() {
		return( tableSysCluster );
	}

	public void setTableSysCluster( ICFSecSysClusterTable value ) {
		tableSysCluster = value;
	}

	public ICFSecSysClusterFactory getFactorySysCluster() {
		return( factorySysCluster );
	}

	public void setFactorySysCluster( ICFSecSysClusterFactory value ) {
		factorySysCluster = value;
	}

	public ICFSecTSecGroupTable getTableTSecGroup() {
		return( tableTSecGroup );
	}

	public void setTableTSecGroup( ICFSecTSecGroupTable value ) {
		tableTSecGroup = value;
	}

	public ICFSecTSecGroupFactory getFactoryTSecGroup() {
		return( factoryTSecGroup );
	}

	public void setFactoryTSecGroup( ICFSecTSecGroupFactory value ) {
		factoryTSecGroup = value;
	}

	public ICFSecTSecGrpIncTable getTableTSecGrpInc() {
		return( tableTSecGrpInc );
	}

	public void setTableTSecGrpInc( ICFSecTSecGrpIncTable value ) {
		tableTSecGrpInc = value;
	}

	public ICFSecTSecGrpIncFactory getFactoryTSecGrpInc() {
		return( factoryTSecGrpInc );
	}

	public void setFactoryTSecGrpInc( ICFSecTSecGrpIncFactory value ) {
		factoryTSecGrpInc = value;
	}

	public ICFSecTSecGrpMembTable getTableTSecGrpMemb() {
		return( tableTSecGrpMemb );
	}

	public void setTableTSecGrpMemb( ICFSecTSecGrpMembTable value ) {
		tableTSecGrpMemb = value;
	}

	public ICFSecTSecGrpMembFactory getFactoryTSecGrpMemb() {
		return( factoryTSecGrpMemb );
	}

	public void setFactoryTSecGrpMemb( ICFSecTSecGrpMembFactory value ) {
		factoryTSecGrpMemb = value;
	}

	public ICFBamTZDateColTable getTableTZDateCol() {
		return( tableTZDateCol );
	}

	public void setTableTZDateCol( ICFBamTZDateColTable value ) {
		tableTZDateCol = value;
	}

	public ICFBamTZDateColFactory getFactoryTZDateCol() {
		return( factoryTZDateCol );
	}

	public void setFactoryTZDateCol( ICFBamTZDateColFactory value ) {
		factoryTZDateCol = value;
	}

	public ICFBamTZDateDefTable getTableTZDateDef() {
		return( tableTZDateDef );
	}

	public void setTableTZDateDef( ICFBamTZDateDefTable value ) {
		tableTZDateDef = value;
	}

	public ICFBamTZDateDefFactory getFactoryTZDateDef() {
		return( factoryTZDateDef );
	}

	public void setFactoryTZDateDef( ICFBamTZDateDefFactory value ) {
		factoryTZDateDef = value;
	}

	public ICFBamTZDateTypeTable getTableTZDateType() {
		return( tableTZDateType );
	}

	public void setTableTZDateType( ICFBamTZDateTypeTable value ) {
		tableTZDateType = value;
	}

	public ICFBamTZDateTypeFactory getFactoryTZDateType() {
		return( factoryTZDateType );
	}

	public void setFactoryTZDateType( ICFBamTZDateTypeFactory value ) {
		factoryTZDateType = value;
	}

	public ICFBamTZTimeColTable getTableTZTimeCol() {
		return( tableTZTimeCol );
	}

	public void setTableTZTimeCol( ICFBamTZTimeColTable value ) {
		tableTZTimeCol = value;
	}

	public ICFBamTZTimeColFactory getFactoryTZTimeCol() {
		return( factoryTZTimeCol );
	}

	public void setFactoryTZTimeCol( ICFBamTZTimeColFactory value ) {
		factoryTZTimeCol = value;
	}

	public ICFBamTZTimeDefTable getTableTZTimeDef() {
		return( tableTZTimeDef );
	}

	public void setTableTZTimeDef( ICFBamTZTimeDefTable value ) {
		tableTZTimeDef = value;
	}

	public ICFBamTZTimeDefFactory getFactoryTZTimeDef() {
		return( factoryTZTimeDef );
	}

	public void setFactoryTZTimeDef( ICFBamTZTimeDefFactory value ) {
		factoryTZTimeDef = value;
	}

	public ICFBamTZTimeTypeTable getTableTZTimeType() {
		return( tableTZTimeType );
	}

	public void setTableTZTimeType( ICFBamTZTimeTypeTable value ) {
		tableTZTimeType = value;
	}

	public ICFBamTZTimeTypeFactory getFactoryTZTimeType() {
		return( factoryTZTimeType );
	}

	public void setFactoryTZTimeType( ICFBamTZTimeTypeFactory value ) {
		factoryTZTimeType = value;
	}

	public ICFBamTZTimestampColTable getTableTZTimestampCol() {
		return( tableTZTimestampCol );
	}

	public void setTableTZTimestampCol( ICFBamTZTimestampColTable value ) {
		tableTZTimestampCol = value;
	}

	public ICFBamTZTimestampColFactory getFactoryTZTimestampCol() {
		return( factoryTZTimestampCol );
	}

	public void setFactoryTZTimestampCol( ICFBamTZTimestampColFactory value ) {
		factoryTZTimestampCol = value;
	}

	public ICFBamTZTimestampDefTable getTableTZTimestampDef() {
		return( tableTZTimestampDef );
	}

	public void setTableTZTimestampDef( ICFBamTZTimestampDefTable value ) {
		tableTZTimestampDef = value;
	}

	public ICFBamTZTimestampDefFactory getFactoryTZTimestampDef() {
		return( factoryTZTimestampDef );
	}

	public void setFactoryTZTimestampDef( ICFBamTZTimestampDefFactory value ) {
		factoryTZTimestampDef = value;
	}

	public ICFBamTZTimestampTypeTable getTableTZTimestampType() {
		return( tableTZTimestampType );
	}

	public void setTableTZTimestampType( ICFBamTZTimestampTypeTable value ) {
		tableTZTimestampType = value;
	}

	public ICFBamTZTimestampTypeFactory getFactoryTZTimestampType() {
		return( factoryTZTimestampType );
	}

	public void setFactoryTZTimestampType( ICFBamTZTimestampTypeFactory value ) {
		factoryTZTimestampType = value;
	}

	public ICFBamTableTable getTableTable() {
		return( tableTable );
	}

	public void setTableTable( ICFBamTableTable value ) {
		tableTable = value;
	}

	public ICFBamTableFactory getFactoryTable() {
		return( factoryTable );
	}

	public void setFactoryTable( ICFBamTableFactory value ) {
		factoryTable = value;
	}

	public ICFBamTableColTable getTableTableCol() {
		return( tableTableCol );
	}

	public void setTableTableCol( ICFBamTableColTable value ) {
		tableTableCol = value;
	}

	public ICFBamTableColFactory getFactoryTableCol() {
		return( factoryTableCol );
	}

	public void setFactoryTableCol( ICFBamTableColFactory value ) {
		factoryTableCol = value;
	}

	public ICFSecTenantTable getTableTenant() {
		return( tableTenant );
	}

	public void setTableTenant( ICFSecTenantTable value ) {
		tableTenant = value;
	}

	public ICFSecTenantFactory getFactoryTenant() {
		return( factoryTenant );
	}

	public void setFactoryTenant( ICFSecTenantFactory value ) {
		factoryTenant = value;
	}

	public ICFBamTextColTable getTableTextCol() {
		return( tableTextCol );
	}

	public void setTableTextCol( ICFBamTextColTable value ) {
		tableTextCol = value;
	}

	public ICFBamTextColFactory getFactoryTextCol() {
		return( factoryTextCol );
	}

	public void setFactoryTextCol( ICFBamTextColFactory value ) {
		factoryTextCol = value;
	}

	public ICFBamTextDefTable getTableTextDef() {
		return( tableTextDef );
	}

	public void setTableTextDef( ICFBamTextDefTable value ) {
		tableTextDef = value;
	}

	public ICFBamTextDefFactory getFactoryTextDef() {
		return( factoryTextDef );
	}

	public void setFactoryTextDef( ICFBamTextDefFactory value ) {
		factoryTextDef = value;
	}

	public ICFBamTextTypeTable getTableTextType() {
		return( tableTextType );
	}

	public void setTableTextType( ICFBamTextTypeTable value ) {
		tableTextType = value;
	}

	public ICFBamTextTypeFactory getFactoryTextType() {
		return( factoryTextType );
	}

	public void setFactoryTextType( ICFBamTextTypeFactory value ) {
		factoryTextType = value;
	}

	public ICFBamTimeColTable getTableTimeCol() {
		return( tableTimeCol );
	}

	public void setTableTimeCol( ICFBamTimeColTable value ) {
		tableTimeCol = value;
	}

	public ICFBamTimeColFactory getFactoryTimeCol() {
		return( factoryTimeCol );
	}

	public void setFactoryTimeCol( ICFBamTimeColFactory value ) {
		factoryTimeCol = value;
	}

	public ICFBamTimeDefTable getTableTimeDef() {
		return( tableTimeDef );
	}

	public void setTableTimeDef( ICFBamTimeDefTable value ) {
		tableTimeDef = value;
	}

	public ICFBamTimeDefFactory getFactoryTimeDef() {
		return( factoryTimeDef );
	}

	public void setFactoryTimeDef( ICFBamTimeDefFactory value ) {
		factoryTimeDef = value;
	}

	public ICFBamTimeTypeTable getTableTimeType() {
		return( tableTimeType );
	}

	public void setTableTimeType( ICFBamTimeTypeTable value ) {
		tableTimeType = value;
	}

	public ICFBamTimeTypeFactory getFactoryTimeType() {
		return( factoryTimeType );
	}

	public void setFactoryTimeType( ICFBamTimeTypeFactory value ) {
		factoryTimeType = value;
	}

	public ICFBamTimestampColTable getTableTimestampCol() {
		return( tableTimestampCol );
	}

	public void setTableTimestampCol( ICFBamTimestampColTable value ) {
		tableTimestampCol = value;
	}

	public ICFBamTimestampColFactory getFactoryTimestampCol() {
		return( factoryTimestampCol );
	}

	public void setFactoryTimestampCol( ICFBamTimestampColFactory value ) {
		factoryTimestampCol = value;
	}

	public ICFBamTimestampDefTable getTableTimestampDef() {
		return( tableTimestampDef );
	}

	public void setTableTimestampDef( ICFBamTimestampDefTable value ) {
		tableTimestampDef = value;
	}

	public ICFBamTimestampDefFactory getFactoryTimestampDef() {
		return( factoryTimestampDef );
	}

	public void setFactoryTimestampDef( ICFBamTimestampDefFactory value ) {
		factoryTimestampDef = value;
	}

	public ICFBamTimestampTypeTable getTableTimestampType() {
		return( tableTimestampType );
	}

	public void setTableTimestampType( ICFBamTimestampTypeTable value ) {
		tableTimestampType = value;
	}

	public ICFBamTimestampTypeFactory getFactoryTimestampType() {
		return( factoryTimestampType );
	}

	public void setFactoryTimestampType( ICFBamTimestampTypeFactory value ) {
		factoryTimestampType = value;
	}

	public ICFIntTldTable getTableTld() {
		return( tableTld );
	}

	public void setTableTld( ICFIntTldTable value ) {
		tableTld = value;
	}

	public ICFIntTldFactory getFactoryTld() {
		return( factoryTld );
	}

	public void setFactoryTld( ICFIntTldFactory value ) {
		factoryTld = value;
	}

	public ICFBamTokenColTable getTableTokenCol() {
		return( tableTokenCol );
	}

	public void setTableTokenCol( ICFBamTokenColTable value ) {
		tableTokenCol = value;
	}

	public ICFBamTokenColFactory getFactoryTokenCol() {
		return( factoryTokenCol );
	}

	public void setFactoryTokenCol( ICFBamTokenColFactory value ) {
		factoryTokenCol = value;
	}

	public ICFBamTokenDefTable getTableTokenDef() {
		return( tableTokenDef );
	}

	public void setTableTokenDef( ICFBamTokenDefTable value ) {
		tableTokenDef = value;
	}

	public ICFBamTokenDefFactory getFactoryTokenDef() {
		return( factoryTokenDef );
	}

	public void setFactoryTokenDef( ICFBamTokenDefFactory value ) {
		factoryTokenDef = value;
	}

	public ICFBamTokenTypeTable getTableTokenType() {
		return( tableTokenType );
	}

	public void setTableTokenType( ICFBamTokenTypeTable value ) {
		tableTokenType = value;
	}

	public ICFBamTokenTypeFactory getFactoryTokenType() {
		return( factoryTokenType );
	}

	public void setFactoryTokenType( ICFBamTokenTypeFactory value ) {
		factoryTokenType = value;
	}

	public ICFIntTopDomainTable getTableTopDomain() {
		return( tableTopDomain );
	}

	public void setTableTopDomain( ICFIntTopDomainTable value ) {
		tableTopDomain = value;
	}

	public ICFIntTopDomainFactory getFactoryTopDomain() {
		return( factoryTopDomain );
	}

	public void setFactoryTopDomain( ICFIntTopDomainFactory value ) {
		factoryTopDomain = value;
	}

	public ICFIntTopProjectTable getTableTopProject() {
		return( tableTopProject );
	}

	public void setTableTopProject( ICFIntTopProjectTable value ) {
		tableTopProject = value;
	}

	public ICFIntTopProjectFactory getFactoryTopProject() {
		return( factoryTopProject );
	}

	public void setFactoryTopProject( ICFIntTopProjectFactory value ) {
		factoryTopProject = value;
	}

	public ICFBamUInt16ColTable getTableUInt16Col() {
		return( tableUInt16Col );
	}

	public void setTableUInt16Col( ICFBamUInt16ColTable value ) {
		tableUInt16Col = value;
	}

	public ICFBamUInt16ColFactory getFactoryUInt16Col() {
		return( factoryUInt16Col );
	}

	public void setFactoryUInt16Col( ICFBamUInt16ColFactory value ) {
		factoryUInt16Col = value;
	}

	public ICFBamUInt16DefTable getTableUInt16Def() {
		return( tableUInt16Def );
	}

	public void setTableUInt16Def( ICFBamUInt16DefTable value ) {
		tableUInt16Def = value;
	}

	public ICFBamUInt16DefFactory getFactoryUInt16Def() {
		return( factoryUInt16Def );
	}

	public void setFactoryUInt16Def( ICFBamUInt16DefFactory value ) {
		factoryUInt16Def = value;
	}

	public ICFBamUInt16TypeTable getTableUInt16Type() {
		return( tableUInt16Type );
	}

	public void setTableUInt16Type( ICFBamUInt16TypeTable value ) {
		tableUInt16Type = value;
	}

	public ICFBamUInt16TypeFactory getFactoryUInt16Type() {
		return( factoryUInt16Type );
	}

	public void setFactoryUInt16Type( ICFBamUInt16TypeFactory value ) {
		factoryUInt16Type = value;
	}

	public ICFBamUInt32ColTable getTableUInt32Col() {
		return( tableUInt32Col );
	}

	public void setTableUInt32Col( ICFBamUInt32ColTable value ) {
		tableUInt32Col = value;
	}

	public ICFBamUInt32ColFactory getFactoryUInt32Col() {
		return( factoryUInt32Col );
	}

	public void setFactoryUInt32Col( ICFBamUInt32ColFactory value ) {
		factoryUInt32Col = value;
	}

	public ICFBamUInt32DefTable getTableUInt32Def() {
		return( tableUInt32Def );
	}

	public void setTableUInt32Def( ICFBamUInt32DefTable value ) {
		tableUInt32Def = value;
	}

	public ICFBamUInt32DefFactory getFactoryUInt32Def() {
		return( factoryUInt32Def );
	}

	public void setFactoryUInt32Def( ICFBamUInt32DefFactory value ) {
		factoryUInt32Def = value;
	}

	public ICFBamUInt32TypeTable getTableUInt32Type() {
		return( tableUInt32Type );
	}

	public void setTableUInt32Type( ICFBamUInt32TypeTable value ) {
		tableUInt32Type = value;
	}

	public ICFBamUInt32TypeFactory getFactoryUInt32Type() {
		return( factoryUInt32Type );
	}

	public void setFactoryUInt32Type( ICFBamUInt32TypeFactory value ) {
		factoryUInt32Type = value;
	}

	public ICFBamUInt64ColTable getTableUInt64Col() {
		return( tableUInt64Col );
	}

	public void setTableUInt64Col( ICFBamUInt64ColTable value ) {
		tableUInt64Col = value;
	}

	public ICFBamUInt64ColFactory getFactoryUInt64Col() {
		return( factoryUInt64Col );
	}

	public void setFactoryUInt64Col( ICFBamUInt64ColFactory value ) {
		factoryUInt64Col = value;
	}

	public ICFBamUInt64DefTable getTableUInt64Def() {
		return( tableUInt64Def );
	}

	public void setTableUInt64Def( ICFBamUInt64DefTable value ) {
		tableUInt64Def = value;
	}

	public ICFBamUInt64DefFactory getFactoryUInt64Def() {
		return( factoryUInt64Def );
	}

	public void setFactoryUInt64Def( ICFBamUInt64DefFactory value ) {
		factoryUInt64Def = value;
	}

	public ICFBamUInt64TypeTable getTableUInt64Type() {
		return( tableUInt64Type );
	}

	public void setTableUInt64Type( ICFBamUInt64TypeTable value ) {
		tableUInt64Type = value;
	}

	public ICFBamUInt64TypeFactory getFactoryUInt64Type() {
		return( factoryUInt64Type );
	}

	public void setFactoryUInt64Type( ICFBamUInt64TypeFactory value ) {
		factoryUInt64Type = value;
	}

	public ICFIntURLProtocolTable getTableURLProtocol() {
		return( tableURLProtocol );
	}

	public void setTableURLProtocol( ICFIntURLProtocolTable value ) {
		tableURLProtocol = value;
	}

	public ICFIntURLProtocolFactory getFactoryURLProtocol() {
		return( factoryURLProtocol );
	}

	public void setFactoryURLProtocol( ICFIntURLProtocolFactory value ) {
		factoryURLProtocol = value;
	}

	public ICFBamUuid6ColTable getTableUuid6Col() {
		return( tableUuid6Col );
	}

	public void setTableUuid6Col( ICFBamUuid6ColTable value ) {
		tableUuid6Col = value;
	}

	public ICFBamUuid6ColFactory getFactoryUuid6Col() {
		return( factoryUuid6Col );
	}

	public void setFactoryUuid6Col( ICFBamUuid6ColFactory value ) {
		factoryUuid6Col = value;
	}

	public ICFBamUuid6DefTable getTableUuid6Def() {
		return( tableUuid6Def );
	}

	public void setTableUuid6Def( ICFBamUuid6DefTable value ) {
		tableUuid6Def = value;
	}

	public ICFBamUuid6DefFactory getFactoryUuid6Def() {
		return( factoryUuid6Def );
	}

	public void setFactoryUuid6Def( ICFBamUuid6DefFactory value ) {
		factoryUuid6Def = value;
	}

	public ICFBamUuid6GenTable getTableUuid6Gen() {
		return( tableUuid6Gen );
	}

	public void setTableUuid6Gen( ICFBamUuid6GenTable value ) {
		tableUuid6Gen = value;
	}

	public ICFBamUuid6GenFactory getFactoryUuid6Gen() {
		return( factoryUuid6Gen );
	}

	public void setFactoryUuid6Gen( ICFBamUuid6GenFactory value ) {
		factoryUuid6Gen = value;
	}

	public ICFBamUuid6TypeTable getTableUuid6Type() {
		return( tableUuid6Type );
	}

	public void setTableUuid6Type( ICFBamUuid6TypeTable value ) {
		tableUuid6Type = value;
	}

	public ICFBamUuid6TypeFactory getFactoryUuid6Type() {
		return( factoryUuid6Type );
	}

	public void setFactoryUuid6Type( ICFBamUuid6TypeFactory value ) {
		factoryUuid6Type = value;
	}

	public ICFBamUuidColTable getTableUuidCol() {
		return( tableUuidCol );
	}

	public void setTableUuidCol( ICFBamUuidColTable value ) {
		tableUuidCol = value;
	}

	public ICFBamUuidColFactory getFactoryUuidCol() {
		return( factoryUuidCol );
	}

	public void setFactoryUuidCol( ICFBamUuidColFactory value ) {
		factoryUuidCol = value;
	}

	public ICFBamUuidDefTable getTableUuidDef() {
		return( tableUuidDef );
	}

	public void setTableUuidDef( ICFBamUuidDefTable value ) {
		tableUuidDef = value;
	}

	public ICFBamUuidDefFactory getFactoryUuidDef() {
		return( factoryUuidDef );
	}

	public void setFactoryUuidDef( ICFBamUuidDefFactory value ) {
		factoryUuidDef = value;
	}

	public ICFBamUuidGenTable getTableUuidGen() {
		return( tableUuidGen );
	}

	public void setTableUuidGen( ICFBamUuidGenTable value ) {
		tableUuidGen = value;
	}

	public ICFBamUuidGenFactory getFactoryUuidGen() {
		return( factoryUuidGen );
	}

	public void setFactoryUuidGen( ICFBamUuidGenFactory value ) {
		factoryUuidGen = value;
	}

	public ICFBamUuidTypeTable getTableUuidType() {
		return( tableUuidType );
	}

	public void setTableUuidType( ICFBamUuidTypeTable value ) {
		tableUuidType = value;
	}

	public ICFBamUuidTypeFactory getFactoryUuidType() {
		return( factoryUuidType );
	}

	public void setFactoryUuidType( ICFBamUuidTypeFactory value ) {
		factoryUuidType = value;
	}

	public ICFBamValueTable getTableValue() {
		return( tableValue );
	}

	public void setTableValue( ICFBamValueTable value ) {
		tableValue = value;
	}

	public ICFBamValueFactory getFactoryValue() {
		return( factoryValue );
	}

	public void setFactoryValue( ICFBamValueFactory value ) {
		factoryValue = value;
	}

	/**
	 *	Get the Table Permissions interface for the schema.
	 *
	 *	@return	The Table Permissions interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public static ICFSecTablePerms getTablePerms() {
		return(CFSecJpaSchema.getTablePerms());
	}

	/**
	 *	Get the Table Permissions interface cast to this schema's implementation.
	 *
	 *	@return The Table Permissions interface for this schema.
	 */
	public static ICFBamTablePerms getCFBamTablePerms() {
		return (ICFBamTablePerms)getTablePerms();
	}

	/**
	 *	Set the Table Permissions interface for the schema.  All fractal subclasses of
	 *	the ICFSecTablePerms implement at least that interface plus their own
	 *	accessors.
	 *
	 *	@param	value	The Table Permissions interface to be used by the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public static void setTablePerms( ICFSecTablePerms value ) {
		CFSecJpaSchema.setTablePerms(value);
	}

	public void bootstrapSchema() {
		getJpaSchemaService().bootstrapSchema();
	}
}
