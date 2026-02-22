// Description: Java 25 Spring JPA Hooks for CFBam

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

package io.github.msobkow.v3_1.cfbam.cfbamjpahooks;

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
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import io.github.msobkow.v3_1.cfsec.cfsec.*;
import io.github.msobkow.v3_1.cfint.cfint.*;
import io.github.msobkow.v3_1.cfbam.cfbam.*;
import io.github.msobkow.v3_1.cfsec.cfsec.jpa.*;
import io.github.msobkow.v3_1.cfint.cfint.jpa.*;
import io.github.msobkow.v3_1.cfbam.cfbam.jpa.*;

/**
 *	Hooks for schema CFBam Spring resources that need to be used by getter-wrappers for AtomicReference members of the multi-threaded wedge between Spring resources and POJO code.
 *	This implementation wraps the Spring singletons instantiated during Spring's boot process for the io.github.msobkow.v3_1.cfbam.cfbam.jpa package.  It resolves all known standard Spring resources for the LocalContainerEntityManagerFactoryBean, SchemaService, IdGenService, and the CFBam*Repository and CFBam*Service singletons that the POJOs need to invoke.  However, it relies on late-initialization during dynamic instance creation (i.e. new CFBamJpaSchemaHooks()) instead of being initialized as a Spring singleton. As the Spring instance hierarchy is instantiated before any instances of this class are instantiated, the net result bridges the gap between POJO and Spring JPA.
 */
@Service("cfbam31JpaHooksSchema")
public class CFBamJpaHooksSchema {

	@Autowired
	@Qualifier("cfbam31EntityManagerFactory")
	private LocalContainerEntityManagerFactoryBean cfbam31EntityManagerFactory;

	@Autowired
	private CFBamJpaScopeRepository scopeRepository;

	@Autowired
	private CFBamJpaSchemaDefRepository schemaDefRepository;

	@Autowired
	private CFBamJpaSchemaRefRepository schemaRefRepository;

	@Autowired
	private CFBamJpaServerMethodRepository serverMethodRepository;

	@Autowired
	private CFBamJpaServerObjFuncRepository serverObjFuncRepository;

	@Autowired
	private CFBamJpaServerProcRepository serverProcRepository;

	@Autowired
	private CFBamJpaTableRepository tableRepository;

	@Autowired
	private CFBamJpaValueRepository valueRepository;

	@Autowired
	private CFBamJpaAtomRepository atomRepository;

	@Autowired
	private CFBamJpaBlobDefRepository blobDefRepository;

	@Autowired
	private CFBamJpaBlobTypeRepository blobTypeRepository;

	@Autowired
	private CFBamJpaBoolDefRepository boolDefRepository;

	@Autowired
	private CFBamJpaBoolTypeRepository boolTypeRepository;

	@Autowired
	private CFBamJpaChainRepository chainRepository;

	@Autowired
	private CFBamJpaClearDepRepository clearDepRepository;

	@Autowired
	private CFBamJpaClearSubDep1Repository clearSubDep1Repository;

	@Autowired
	private CFBamJpaClearSubDep2Repository clearSubDep2Repository;

	@Autowired
	private CFBamJpaClearSubDep3Repository clearSubDep3Repository;

	@Autowired
	private CFBamJpaClearTopDepRepository clearTopDepRepository;

	@Autowired
	private CFBamJpaDateDefRepository dateDefRepository;

	@Autowired
	private CFBamJpaDateTypeRepository dateTypeRepository;

	@Autowired
	private CFBamJpaDelDepRepository delDepRepository;

	@Autowired
	private CFBamJpaDelSubDep1Repository delSubDep1Repository;

	@Autowired
	private CFBamJpaDelSubDep2Repository delSubDep2Repository;

	@Autowired
	private CFBamJpaDelSubDep3Repository delSubDep3Repository;

	@Autowired
	private CFBamJpaDelTopDepRepository delTopDepRepository;

	@Autowired
	private CFBamJpaDoubleDefRepository doubleDefRepository;

	@Autowired
	private CFBamJpaDoubleTypeRepository doubleTypeRepository;

	@Autowired
	private CFBamJpaEnumTagRepository enumTagRepository;

	@Autowired
	private CFBamJpaFloatDefRepository floatDefRepository;

	@Autowired
	private CFBamJpaFloatTypeRepository floatTypeRepository;

	@Autowired
	private CFBamJpaIndexRepository indexRepository;

	@Autowired
	private CFBamJpaIndexColRepository indexColRepository;

	@Autowired
	private CFBamJpaInt16DefRepository int16DefRepository;

	@Autowired
	private CFBamJpaInt16TypeRepository int16TypeRepository;

	@Autowired
	private CFBamJpaInt32DefRepository int32DefRepository;

	@Autowired
	private CFBamJpaInt32TypeRepository int32TypeRepository;

	@Autowired
	private CFBamJpaInt64DefRepository int64DefRepository;

	@Autowired
	private CFBamJpaInt64TypeRepository int64TypeRepository;

	@Autowired
	private CFBamJpaNmTokenDefRepository nmTokenDefRepository;

	@Autowired
	private CFBamJpaNmTokenTypeRepository nmTokenTypeRepository;

	@Autowired
	private CFBamJpaNmTokensDefRepository nmTokensDefRepository;

	@Autowired
	private CFBamJpaNmTokensTypeRepository nmTokensTypeRepository;

	@Autowired
	private CFBamJpaNumberDefRepository numberDefRepository;

	@Autowired
	private CFBamJpaNumberTypeRepository numberTypeRepository;

	@Autowired
	private CFBamJpaParamRepository paramRepository;

	@Autowired
	private CFBamJpaPopDepRepository popDepRepository;

	@Autowired
	private CFBamJpaPopSubDep1Repository popSubDep1Repository;

	@Autowired
	private CFBamJpaPopSubDep2Repository popSubDep2Repository;

	@Autowired
	private CFBamJpaPopSubDep3Repository popSubDep3Repository;

	@Autowired
	private CFBamJpaPopTopDepRepository popTopDepRepository;

	@Autowired
	private CFBamJpaRelationRepository relationRepository;

	@Autowired
	private CFBamJpaRelationColRepository relationColRepository;

	@Autowired
	private CFBamJpaServerListFuncRepository serverListFuncRepository;

	@Autowired
	private CFBamJpaDbKeyHash128DefRepository dbKeyHash128DefRepository;

	@Autowired
	private CFBamJpaDbKeyHash128ColRepository dbKeyHash128ColRepository;

	@Autowired
	private CFBamJpaDbKeyHash128TypeRepository dbKeyHash128TypeRepository;

	@Autowired
	private CFBamJpaDbKeyHash128GenRepository dbKeyHash128GenRepository;

	@Autowired
	private CFBamJpaDbKeyHash160DefRepository dbKeyHash160DefRepository;

	@Autowired
	private CFBamJpaDbKeyHash160ColRepository dbKeyHash160ColRepository;

	@Autowired
	private CFBamJpaDbKeyHash160TypeRepository dbKeyHash160TypeRepository;

	@Autowired
	private CFBamJpaDbKeyHash160GenRepository dbKeyHash160GenRepository;

	@Autowired
	private CFBamJpaDbKeyHash224DefRepository dbKeyHash224DefRepository;

	@Autowired
	private CFBamJpaDbKeyHash224ColRepository dbKeyHash224ColRepository;

	@Autowired
	private CFBamJpaDbKeyHash224TypeRepository dbKeyHash224TypeRepository;

	@Autowired
	private CFBamJpaDbKeyHash224GenRepository dbKeyHash224GenRepository;

	@Autowired
	private CFBamJpaDbKeyHash256DefRepository dbKeyHash256DefRepository;

	@Autowired
	private CFBamJpaDbKeyHash256ColRepository dbKeyHash256ColRepository;

	@Autowired
	private CFBamJpaDbKeyHash256TypeRepository dbKeyHash256TypeRepository;

	@Autowired
	private CFBamJpaDbKeyHash256GenRepository dbKeyHash256GenRepository;

	@Autowired
	private CFBamJpaDbKeyHash384DefRepository dbKeyHash384DefRepository;

	@Autowired
	private CFBamJpaDbKeyHash384ColRepository dbKeyHash384ColRepository;

	@Autowired
	private CFBamJpaDbKeyHash384TypeRepository dbKeyHash384TypeRepository;

	@Autowired
	private CFBamJpaDbKeyHash384GenRepository dbKeyHash384GenRepository;

	@Autowired
	private CFBamJpaDbKeyHash512DefRepository dbKeyHash512DefRepository;

	@Autowired
	private CFBamJpaDbKeyHash512ColRepository dbKeyHash512ColRepository;

	@Autowired
	private CFBamJpaDbKeyHash512TypeRepository dbKeyHash512TypeRepository;

	@Autowired
	private CFBamJpaDbKeyHash512GenRepository dbKeyHash512GenRepository;

	@Autowired
	private CFBamJpaStringDefRepository stringDefRepository;

	@Autowired
	private CFBamJpaStringTypeRepository stringTypeRepository;

	@Autowired
	private CFBamJpaTZDateDefRepository tZDateDefRepository;

	@Autowired
	private CFBamJpaTZDateTypeRepository tZDateTypeRepository;

	@Autowired
	private CFBamJpaTZTimeDefRepository tZTimeDefRepository;

	@Autowired
	private CFBamJpaTZTimeTypeRepository tZTimeTypeRepository;

	@Autowired
	private CFBamJpaTZTimestampDefRepository tZTimestampDefRepository;

	@Autowired
	private CFBamJpaTZTimestampTypeRepository tZTimestampTypeRepository;

	@Autowired
	private CFBamJpaTableColRepository tableColRepository;

	@Autowired
	private CFBamJpaTextDefRepository textDefRepository;

	@Autowired
	private CFBamJpaTextTypeRepository textTypeRepository;

	@Autowired
	private CFBamJpaTimeDefRepository timeDefRepository;

	@Autowired
	private CFBamJpaTimeTypeRepository timeTypeRepository;

	@Autowired
	private CFBamJpaTimestampDefRepository timestampDefRepository;

	@Autowired
	private CFBamJpaTimestampTypeRepository timestampTypeRepository;

	@Autowired
	private CFBamJpaTokenDefRepository tokenDefRepository;

	@Autowired
	private CFBamJpaTokenTypeRepository tokenTypeRepository;

	@Autowired
	private CFBamJpaUInt16DefRepository uInt16DefRepository;

	@Autowired
	private CFBamJpaUInt16TypeRepository uInt16TypeRepository;

	@Autowired
	private CFBamJpaUInt32DefRepository uInt32DefRepository;

	@Autowired
	private CFBamJpaUInt32TypeRepository uInt32TypeRepository;

	@Autowired
	private CFBamJpaUInt64DefRepository uInt64DefRepository;

	@Autowired
	private CFBamJpaUInt64TypeRepository uInt64TypeRepository;

	@Autowired
	private CFBamJpaUuidDefRepository uuidDefRepository;

	@Autowired
	private CFBamJpaUuid6DefRepository uuid6DefRepository;

	@Autowired
	private CFBamJpaUuidTypeRepository uuidTypeRepository;

	@Autowired
	private CFBamJpaUuid6TypeRepository uuid6TypeRepository;

	@Autowired
	private CFBamJpaBlobColRepository blobColRepository;

	@Autowired
	private CFBamJpaBoolColRepository boolColRepository;

	@Autowired
	private CFBamJpaDateColRepository dateColRepository;

	@Autowired
	private CFBamJpaDoubleColRepository doubleColRepository;

	@Autowired
	private CFBamJpaEnumDefRepository enumDefRepository;

	@Autowired
	private CFBamJpaEnumTypeRepository enumTypeRepository;

	@Autowired
	private CFBamJpaFloatColRepository floatColRepository;

	@Autowired
	private CFBamJpaId16GenRepository id16GenRepository;

	@Autowired
	private CFBamJpaId32GenRepository id32GenRepository;

	@Autowired
	private CFBamJpaId64GenRepository id64GenRepository;

	@Autowired
	private CFBamJpaInt16ColRepository int16ColRepository;

	@Autowired
	private CFBamJpaInt32ColRepository int32ColRepository;

	@Autowired
	private CFBamJpaInt64ColRepository int64ColRepository;

	@Autowired
	private CFBamJpaNmTokenColRepository nmTokenColRepository;

	@Autowired
	private CFBamJpaNmTokensColRepository nmTokensColRepository;

	@Autowired
	private CFBamJpaNumberColRepository numberColRepository;

	@Autowired
	private CFBamJpaStringColRepository stringColRepository;

	@Autowired
	private CFBamJpaTZDateColRepository tZDateColRepository;

	@Autowired
	private CFBamJpaTZTimeColRepository tZTimeColRepository;

	@Autowired
	private CFBamJpaTZTimestampColRepository tZTimestampColRepository;

	@Autowired
	private CFBamJpaTextColRepository textColRepository;

	@Autowired
	private CFBamJpaTimeColRepository timeColRepository;

	@Autowired
	private CFBamJpaTimestampColRepository timestampColRepository;

	@Autowired
	private CFBamJpaTokenColRepository tokenColRepository;

	@Autowired
	private CFBamJpaUInt16ColRepository uInt16ColRepository;

	@Autowired
	private CFBamJpaUInt32ColRepository uInt32ColRepository;

	@Autowired
	private CFBamJpaUInt64ColRepository uInt64ColRepository;

	@Autowired
	private CFBamJpaUuidColRepository uuidColRepository;

	@Autowired
	private CFBamJpaUuid6ColRepository uuid6ColRepository;

	@Autowired
	private CFBamJpaUuidGenRepository uuidGenRepository;

	@Autowired
	private CFBamJpaUuid6GenRepository uuid6GenRepository;

	@Autowired
	@Qualifier("cfbam31JpaSchemaService")
	private CFBamJpaSchemaService schemaService;

	@Autowired
	@Qualifier("CFBamJpaIdGenService")
	private CFBamJpaIdGenService idGenService;

	@Autowired
	@Qualifier("cfbam31JpaScopeService")
	private CFBamJpaScopeService scopeService;

	@Autowired
	@Qualifier("cfbam31JpaSchemaDefService")
	private CFBamJpaSchemaDefService schemaDefService;

	@Autowired
	@Qualifier("cfbam31JpaSchemaRefService")
	private CFBamJpaSchemaRefService schemaRefService;

	@Autowired
	@Qualifier("cfbam31JpaServerMethodService")
	private CFBamJpaServerMethodService serverMethodService;

	@Autowired
	@Qualifier("cfbam31JpaServerObjFuncService")
	private CFBamJpaServerObjFuncService serverObjFuncService;

	@Autowired
	@Qualifier("cfbam31JpaServerProcService")
	private CFBamJpaServerProcService serverProcService;

	@Autowired
	@Qualifier("cfbam31JpaTableService")
	private CFBamJpaTableService tableService;

	@Autowired
	@Qualifier("cfbam31JpaValueService")
	private CFBamJpaValueService valueService;

	@Autowired
	@Qualifier("cfbam31JpaAtomService")
	private CFBamJpaAtomService atomService;

	@Autowired
	@Qualifier("cfbam31JpaBlobDefService")
	private CFBamJpaBlobDefService blobDefService;

	@Autowired
	@Qualifier("cfbam31JpaBlobTypeService")
	private CFBamJpaBlobTypeService blobTypeService;

	@Autowired
	@Qualifier("cfbam31JpaBoolDefService")
	private CFBamJpaBoolDefService boolDefService;

	@Autowired
	@Qualifier("cfbam31JpaBoolTypeService")
	private CFBamJpaBoolTypeService boolTypeService;

	@Autowired
	@Qualifier("cfbam31JpaChainService")
	private CFBamJpaChainService chainService;

	@Autowired
	@Qualifier("cfbam31JpaClearDepService")
	private CFBamJpaClearDepService clearDepService;

	@Autowired
	@Qualifier("cfbam31JpaClearSubDep1Service")
	private CFBamJpaClearSubDep1Service clearSubDep1Service;

	@Autowired
	@Qualifier("cfbam31JpaClearSubDep2Service")
	private CFBamJpaClearSubDep2Service clearSubDep2Service;

	@Autowired
	@Qualifier("cfbam31JpaClearSubDep3Service")
	private CFBamJpaClearSubDep3Service clearSubDep3Service;

	@Autowired
	@Qualifier("cfbam31JpaClearTopDepService")
	private CFBamJpaClearTopDepService clearTopDepService;

	@Autowired
	@Qualifier("cfbam31JpaDateDefService")
	private CFBamJpaDateDefService dateDefService;

	@Autowired
	@Qualifier("cfbam31JpaDateTypeService")
	private CFBamJpaDateTypeService dateTypeService;

	@Autowired
	@Qualifier("cfbam31JpaDelDepService")
	private CFBamJpaDelDepService delDepService;

	@Autowired
	@Qualifier("cfbam31JpaDelSubDep1Service")
	private CFBamJpaDelSubDep1Service delSubDep1Service;

	@Autowired
	@Qualifier("cfbam31JpaDelSubDep2Service")
	private CFBamJpaDelSubDep2Service delSubDep2Service;

	@Autowired
	@Qualifier("cfbam31JpaDelSubDep3Service")
	private CFBamJpaDelSubDep3Service delSubDep3Service;

	@Autowired
	@Qualifier("cfbam31JpaDelTopDepService")
	private CFBamJpaDelTopDepService delTopDepService;

	@Autowired
	@Qualifier("cfbam31JpaDoubleDefService")
	private CFBamJpaDoubleDefService doubleDefService;

	@Autowired
	@Qualifier("cfbam31JpaDoubleTypeService")
	private CFBamJpaDoubleTypeService doubleTypeService;

	@Autowired
	@Qualifier("cfbam31JpaEnumTagService")
	private CFBamJpaEnumTagService enumTagService;

	@Autowired
	@Qualifier("cfbam31JpaFloatDefService")
	private CFBamJpaFloatDefService floatDefService;

	@Autowired
	@Qualifier("cfbam31JpaFloatTypeService")
	private CFBamJpaFloatTypeService floatTypeService;

	@Autowired
	@Qualifier("cfbam31JpaIndexService")
	private CFBamJpaIndexService indexService;

	@Autowired
	@Qualifier("cfbam31JpaIndexColService")
	private CFBamJpaIndexColService indexColService;

	@Autowired
	@Qualifier("cfbam31JpaInt16DefService")
	private CFBamJpaInt16DefService int16DefService;

	@Autowired
	@Qualifier("cfbam31JpaInt16TypeService")
	private CFBamJpaInt16TypeService int16TypeService;

	@Autowired
	@Qualifier("cfbam31JpaInt32DefService")
	private CFBamJpaInt32DefService int32DefService;

	@Autowired
	@Qualifier("cfbam31JpaInt32TypeService")
	private CFBamJpaInt32TypeService int32TypeService;

	@Autowired
	@Qualifier("cfbam31JpaInt64DefService")
	private CFBamJpaInt64DefService int64DefService;

	@Autowired
	@Qualifier("cfbam31JpaInt64TypeService")
	private CFBamJpaInt64TypeService int64TypeService;

	@Autowired
	@Qualifier("cfbam31JpaNmTokenDefService")
	private CFBamJpaNmTokenDefService nmTokenDefService;

	@Autowired
	@Qualifier("cfbam31JpaNmTokenTypeService")
	private CFBamJpaNmTokenTypeService nmTokenTypeService;

	@Autowired
	@Qualifier("cfbam31JpaNmTokensDefService")
	private CFBamJpaNmTokensDefService nmTokensDefService;

	@Autowired
	@Qualifier("cfbam31JpaNmTokensTypeService")
	private CFBamJpaNmTokensTypeService nmTokensTypeService;

	@Autowired
	@Qualifier("cfbam31JpaNumberDefService")
	private CFBamJpaNumberDefService numberDefService;

	@Autowired
	@Qualifier("cfbam31JpaNumberTypeService")
	private CFBamJpaNumberTypeService numberTypeService;

	@Autowired
	@Qualifier("cfbam31JpaParamService")
	private CFBamJpaParamService paramService;

	@Autowired
	@Qualifier("cfbam31JpaPopDepService")
	private CFBamJpaPopDepService popDepService;

	@Autowired
	@Qualifier("cfbam31JpaPopSubDep1Service")
	private CFBamJpaPopSubDep1Service popSubDep1Service;

	@Autowired
	@Qualifier("cfbam31JpaPopSubDep2Service")
	private CFBamJpaPopSubDep2Service popSubDep2Service;

	@Autowired
	@Qualifier("cfbam31JpaPopSubDep3Service")
	private CFBamJpaPopSubDep3Service popSubDep3Service;

	@Autowired
	@Qualifier("cfbam31JpaPopTopDepService")
	private CFBamJpaPopTopDepService popTopDepService;

	@Autowired
	@Qualifier("cfbam31JpaRelationService")
	private CFBamJpaRelationService relationService;

	@Autowired
	@Qualifier("cfbam31JpaRelationColService")
	private CFBamJpaRelationColService relationColService;

	@Autowired
	@Qualifier("cfbam31JpaServerListFuncService")
	private CFBamJpaServerListFuncService serverListFuncService;

	@Autowired
	@Qualifier("cfbam31JpaDbKeyHash128DefService")
	private CFBamJpaDbKeyHash128DefService dbKeyHash128DefService;

	@Autowired
	@Qualifier("cfbam31JpaDbKeyHash128ColService")
	private CFBamJpaDbKeyHash128ColService dbKeyHash128ColService;

	@Autowired
	@Qualifier("cfbam31JpaDbKeyHash128TypeService")
	private CFBamJpaDbKeyHash128TypeService dbKeyHash128TypeService;

	@Autowired
	@Qualifier("cfbam31JpaDbKeyHash128GenService")
	private CFBamJpaDbKeyHash128GenService dbKeyHash128GenService;

	@Autowired
	@Qualifier("cfbam31JpaDbKeyHash160DefService")
	private CFBamJpaDbKeyHash160DefService dbKeyHash160DefService;

	@Autowired
	@Qualifier("cfbam31JpaDbKeyHash160ColService")
	private CFBamJpaDbKeyHash160ColService dbKeyHash160ColService;

	@Autowired
	@Qualifier("cfbam31JpaDbKeyHash160TypeService")
	private CFBamJpaDbKeyHash160TypeService dbKeyHash160TypeService;

	@Autowired
	@Qualifier("cfbam31JpaDbKeyHash160GenService")
	private CFBamJpaDbKeyHash160GenService dbKeyHash160GenService;

	@Autowired
	@Qualifier("cfbam31JpaDbKeyHash224DefService")
	private CFBamJpaDbKeyHash224DefService dbKeyHash224DefService;

	@Autowired
	@Qualifier("cfbam31JpaDbKeyHash224ColService")
	private CFBamJpaDbKeyHash224ColService dbKeyHash224ColService;

	@Autowired
	@Qualifier("cfbam31JpaDbKeyHash224TypeService")
	private CFBamJpaDbKeyHash224TypeService dbKeyHash224TypeService;

	@Autowired
	@Qualifier("cfbam31JpaDbKeyHash224GenService")
	private CFBamJpaDbKeyHash224GenService dbKeyHash224GenService;

	@Autowired
	@Qualifier("cfbam31JpaDbKeyHash256DefService")
	private CFBamJpaDbKeyHash256DefService dbKeyHash256DefService;

	@Autowired
	@Qualifier("cfbam31JpaDbKeyHash256ColService")
	private CFBamJpaDbKeyHash256ColService dbKeyHash256ColService;

	@Autowired
	@Qualifier("cfbam31JpaDbKeyHash256TypeService")
	private CFBamJpaDbKeyHash256TypeService dbKeyHash256TypeService;

	@Autowired
	@Qualifier("cfbam31JpaDbKeyHash256GenService")
	private CFBamJpaDbKeyHash256GenService dbKeyHash256GenService;

	@Autowired
	@Qualifier("cfbam31JpaDbKeyHash384DefService")
	private CFBamJpaDbKeyHash384DefService dbKeyHash384DefService;

	@Autowired
	@Qualifier("cfbam31JpaDbKeyHash384ColService")
	private CFBamJpaDbKeyHash384ColService dbKeyHash384ColService;

	@Autowired
	@Qualifier("cfbam31JpaDbKeyHash384TypeService")
	private CFBamJpaDbKeyHash384TypeService dbKeyHash384TypeService;

	@Autowired
	@Qualifier("cfbam31JpaDbKeyHash384GenService")
	private CFBamJpaDbKeyHash384GenService dbKeyHash384GenService;

	@Autowired
	@Qualifier("cfbam31JpaDbKeyHash512DefService")
	private CFBamJpaDbKeyHash512DefService dbKeyHash512DefService;

	@Autowired
	@Qualifier("cfbam31JpaDbKeyHash512ColService")
	private CFBamJpaDbKeyHash512ColService dbKeyHash512ColService;

	@Autowired
	@Qualifier("cfbam31JpaDbKeyHash512TypeService")
	private CFBamJpaDbKeyHash512TypeService dbKeyHash512TypeService;

	@Autowired
	@Qualifier("cfbam31JpaDbKeyHash512GenService")
	private CFBamJpaDbKeyHash512GenService dbKeyHash512GenService;

	@Autowired
	@Qualifier("cfbam31JpaStringDefService")
	private CFBamJpaStringDefService stringDefService;

	@Autowired
	@Qualifier("cfbam31JpaStringTypeService")
	private CFBamJpaStringTypeService stringTypeService;

	@Autowired
	@Qualifier("cfbam31JpaTZDateDefService")
	private CFBamJpaTZDateDefService tZDateDefService;

	@Autowired
	@Qualifier("cfbam31JpaTZDateTypeService")
	private CFBamJpaTZDateTypeService tZDateTypeService;

	@Autowired
	@Qualifier("cfbam31JpaTZTimeDefService")
	private CFBamJpaTZTimeDefService tZTimeDefService;

	@Autowired
	@Qualifier("cfbam31JpaTZTimeTypeService")
	private CFBamJpaTZTimeTypeService tZTimeTypeService;

	@Autowired
	@Qualifier("cfbam31JpaTZTimestampDefService")
	private CFBamJpaTZTimestampDefService tZTimestampDefService;

	@Autowired
	@Qualifier("cfbam31JpaTZTimestampTypeService")
	private CFBamJpaTZTimestampTypeService tZTimestampTypeService;

	@Autowired
	@Qualifier("cfbam31JpaTableColService")
	private CFBamJpaTableColService tableColService;

	@Autowired
	@Qualifier("cfbam31JpaTextDefService")
	private CFBamJpaTextDefService textDefService;

	@Autowired
	@Qualifier("cfbam31JpaTextTypeService")
	private CFBamJpaTextTypeService textTypeService;

	@Autowired
	@Qualifier("cfbam31JpaTimeDefService")
	private CFBamJpaTimeDefService timeDefService;

	@Autowired
	@Qualifier("cfbam31JpaTimeTypeService")
	private CFBamJpaTimeTypeService timeTypeService;

	@Autowired
	@Qualifier("cfbam31JpaTimestampDefService")
	private CFBamJpaTimestampDefService timestampDefService;

	@Autowired
	@Qualifier("cfbam31JpaTimestampTypeService")
	private CFBamJpaTimestampTypeService timestampTypeService;

	@Autowired
	@Qualifier("cfbam31JpaTokenDefService")
	private CFBamJpaTokenDefService tokenDefService;

	@Autowired
	@Qualifier("cfbam31JpaTokenTypeService")
	private CFBamJpaTokenTypeService tokenTypeService;

	@Autowired
	@Qualifier("cfbam31JpaUInt16DefService")
	private CFBamJpaUInt16DefService uInt16DefService;

	@Autowired
	@Qualifier("cfbam31JpaUInt16TypeService")
	private CFBamJpaUInt16TypeService uInt16TypeService;

	@Autowired
	@Qualifier("cfbam31JpaUInt32DefService")
	private CFBamJpaUInt32DefService uInt32DefService;

	@Autowired
	@Qualifier("cfbam31JpaUInt32TypeService")
	private CFBamJpaUInt32TypeService uInt32TypeService;

	@Autowired
	@Qualifier("cfbam31JpaUInt64DefService")
	private CFBamJpaUInt64DefService uInt64DefService;

	@Autowired
	@Qualifier("cfbam31JpaUInt64TypeService")
	private CFBamJpaUInt64TypeService uInt64TypeService;

	@Autowired
	@Qualifier("cfbam31JpaUuidDefService")
	private CFBamJpaUuidDefService uuidDefService;

	@Autowired
	@Qualifier("cfbam31JpaUuid6DefService")
	private CFBamJpaUuid6DefService uuid6DefService;

	@Autowired
	@Qualifier("cfbam31JpaUuidTypeService")
	private CFBamJpaUuidTypeService uuidTypeService;

	@Autowired
	@Qualifier("cfbam31JpaUuid6TypeService")
	private CFBamJpaUuid6TypeService uuid6TypeService;

	@Autowired
	@Qualifier("cfbam31JpaBlobColService")
	private CFBamJpaBlobColService blobColService;

	@Autowired
	@Qualifier("cfbam31JpaBoolColService")
	private CFBamJpaBoolColService boolColService;

	@Autowired
	@Qualifier("cfbam31JpaDateColService")
	private CFBamJpaDateColService dateColService;

	@Autowired
	@Qualifier("cfbam31JpaDoubleColService")
	private CFBamJpaDoubleColService doubleColService;

	@Autowired
	@Qualifier("cfbam31JpaEnumDefService")
	private CFBamJpaEnumDefService enumDefService;

	@Autowired
	@Qualifier("cfbam31JpaEnumTypeService")
	private CFBamJpaEnumTypeService enumTypeService;

	@Autowired
	@Qualifier("cfbam31JpaFloatColService")
	private CFBamJpaFloatColService floatColService;

	@Autowired
	@Qualifier("cfbam31JpaId16GenService")
	private CFBamJpaId16GenService id16GenService;

	@Autowired
	@Qualifier("cfbam31JpaId32GenService")
	private CFBamJpaId32GenService id32GenService;

	@Autowired
	@Qualifier("cfbam31JpaId64GenService")
	private CFBamJpaId64GenService id64GenService;

	@Autowired
	@Qualifier("cfbam31JpaInt16ColService")
	private CFBamJpaInt16ColService int16ColService;

	@Autowired
	@Qualifier("cfbam31JpaInt32ColService")
	private CFBamJpaInt32ColService int32ColService;

	@Autowired
	@Qualifier("cfbam31JpaInt64ColService")
	private CFBamJpaInt64ColService int64ColService;

	@Autowired
	@Qualifier("cfbam31JpaNmTokenColService")
	private CFBamJpaNmTokenColService nmTokenColService;

	@Autowired
	@Qualifier("cfbam31JpaNmTokensColService")
	private CFBamJpaNmTokensColService nmTokensColService;

	@Autowired
	@Qualifier("cfbam31JpaNumberColService")
	private CFBamJpaNumberColService numberColService;

	@Autowired
	@Qualifier("cfbam31JpaStringColService")
	private CFBamJpaStringColService stringColService;

	@Autowired
	@Qualifier("cfbam31JpaTZDateColService")
	private CFBamJpaTZDateColService tZDateColService;

	@Autowired
	@Qualifier("cfbam31JpaTZTimeColService")
	private CFBamJpaTZTimeColService tZTimeColService;

	@Autowired
	@Qualifier("cfbam31JpaTZTimestampColService")
	private CFBamJpaTZTimestampColService tZTimestampColService;

	@Autowired
	@Qualifier("cfbam31JpaTextColService")
	private CFBamJpaTextColService textColService;

	@Autowired
	@Qualifier("cfbam31JpaTimeColService")
	private CFBamJpaTimeColService timeColService;

	@Autowired
	@Qualifier("cfbam31JpaTimestampColService")
	private CFBamJpaTimestampColService timestampColService;

	@Autowired
	@Qualifier("cfbam31JpaTokenColService")
	private CFBamJpaTokenColService tokenColService;

	@Autowired
	@Qualifier("cfbam31JpaUInt16ColService")
	private CFBamJpaUInt16ColService uInt16ColService;

	@Autowired
	@Qualifier("cfbam31JpaUInt32ColService")
	private CFBamJpaUInt32ColService uInt32ColService;

	@Autowired
	@Qualifier("cfbam31JpaUInt64ColService")
	private CFBamJpaUInt64ColService uInt64ColService;

	@Autowired
	@Qualifier("cfbam31JpaUuidColService")
	private CFBamJpaUuidColService uuidColService;

	@Autowired
	@Qualifier("cfbam31JpaUuid6ColService")
	private CFBamJpaUuid6ColService uuid6ColService;

	@Autowired
	@Qualifier("cfbam31JpaUuidGenService")
	private CFBamJpaUuidGenService uuidGenService;

	@Autowired
	@Qualifier("cfbam31JpaUuid6GenService")
	private CFBamJpaUuid6GenService uuid6GenService;

	public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean() {
		if ( cfbam31EntityManagerFactory == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getEntityManagerFactoryBean",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( cfbam31EntityManagerFactory );
	}

	public CFBamJpaSchemaService getSchemaService() {
		if ( schemaService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getSchemaService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( schemaService );
	}

	public CFBamJpaIdGenService getIdGenService() {
		if ( idGenService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getIdGenService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( idGenService );
	}

	public CFBamJpaScopeRepository getScopeRepository() {
		if ( scopeRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getScopeRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( scopeRepository );
	}

	public CFBamJpaSchemaDefRepository getSchemaDefRepository() {
		if ( schemaDefRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getSchemaDefRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( schemaDefRepository );
	}

	public CFBamJpaSchemaRefRepository getSchemaRefRepository() {
		if ( schemaRefRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getSchemaRefRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( schemaRefRepository );
	}

	public CFBamJpaServerMethodRepository getServerMethodRepository() {
		if ( serverMethodRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getServerMethodRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( serverMethodRepository );
	}

	public CFBamJpaServerObjFuncRepository getServerObjFuncRepository() {
		if ( serverObjFuncRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getServerObjFuncRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( serverObjFuncRepository );
	}

	public CFBamJpaServerProcRepository getServerProcRepository() {
		if ( serverProcRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getServerProcRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( serverProcRepository );
	}

	public CFBamJpaTableRepository getTableRepository() {
		if ( tableRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getTableRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( tableRepository );
	}

	public CFBamJpaValueRepository getValueRepository() {
		if ( valueRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getValueRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( valueRepository );
	}

	public CFBamJpaAtomRepository getAtomRepository() {
		if ( atomRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getAtomRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( atomRepository );
	}

	public CFBamJpaBlobDefRepository getBlobDefRepository() {
		if ( blobDefRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getBlobDefRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( blobDefRepository );
	}

	public CFBamJpaBlobTypeRepository getBlobTypeRepository() {
		if ( blobTypeRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getBlobTypeRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( blobTypeRepository );
	}

	public CFBamJpaBoolDefRepository getBoolDefRepository() {
		if ( boolDefRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getBoolDefRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( boolDefRepository );
	}

	public CFBamJpaBoolTypeRepository getBoolTypeRepository() {
		if ( boolTypeRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getBoolTypeRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( boolTypeRepository );
	}

	public CFBamJpaChainRepository getChainRepository() {
		if ( chainRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getChainRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( chainRepository );
	}

	public CFBamJpaClearDepRepository getClearDepRepository() {
		if ( clearDepRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getClearDepRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( clearDepRepository );
	}

	public CFBamJpaClearSubDep1Repository getClearSubDep1Repository() {
		if ( clearSubDep1Repository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getClearSubDep1Repository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( clearSubDep1Repository );
	}

	public CFBamJpaClearSubDep2Repository getClearSubDep2Repository() {
		if ( clearSubDep2Repository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getClearSubDep2Repository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( clearSubDep2Repository );
	}

	public CFBamJpaClearSubDep3Repository getClearSubDep3Repository() {
		if ( clearSubDep3Repository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getClearSubDep3Repository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( clearSubDep3Repository );
	}

	public CFBamJpaClearTopDepRepository getClearTopDepRepository() {
		if ( clearTopDepRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getClearTopDepRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( clearTopDepRepository );
	}

	public CFBamJpaDateDefRepository getDateDefRepository() {
		if ( dateDefRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getDateDefRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dateDefRepository );
	}

	public CFBamJpaDateTypeRepository getDateTypeRepository() {
		if ( dateTypeRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getDateTypeRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dateTypeRepository );
	}

	public CFBamJpaDelDepRepository getDelDepRepository() {
		if ( delDepRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getDelDepRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( delDepRepository );
	}

	public CFBamJpaDelSubDep1Repository getDelSubDep1Repository() {
		if ( delSubDep1Repository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getDelSubDep1Repository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( delSubDep1Repository );
	}

	public CFBamJpaDelSubDep2Repository getDelSubDep2Repository() {
		if ( delSubDep2Repository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getDelSubDep2Repository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( delSubDep2Repository );
	}

	public CFBamJpaDelSubDep3Repository getDelSubDep3Repository() {
		if ( delSubDep3Repository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getDelSubDep3Repository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( delSubDep3Repository );
	}

	public CFBamJpaDelTopDepRepository getDelTopDepRepository() {
		if ( delTopDepRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getDelTopDepRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( delTopDepRepository );
	}

	public CFBamJpaDoubleDefRepository getDoubleDefRepository() {
		if ( doubleDefRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getDoubleDefRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( doubleDefRepository );
	}

	public CFBamJpaDoubleTypeRepository getDoubleTypeRepository() {
		if ( doubleTypeRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getDoubleTypeRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( doubleTypeRepository );
	}

	public CFBamJpaEnumTagRepository getEnumTagRepository() {
		if ( enumTagRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getEnumTagRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( enumTagRepository );
	}

	public CFBamJpaFloatDefRepository getFloatDefRepository() {
		if ( floatDefRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getFloatDefRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( floatDefRepository );
	}

	public CFBamJpaFloatTypeRepository getFloatTypeRepository() {
		if ( floatTypeRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getFloatTypeRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( floatTypeRepository );
	}

	public CFBamJpaIndexRepository getIndexRepository() {
		if ( indexRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getIndexRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( indexRepository );
	}

	public CFBamJpaIndexColRepository getIndexColRepository() {
		if ( indexColRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getIndexColRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( indexColRepository );
	}

	public CFBamJpaInt16DefRepository getInt16DefRepository() {
		if ( int16DefRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getInt16DefRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( int16DefRepository );
	}

	public CFBamJpaInt16TypeRepository getInt16TypeRepository() {
		if ( int16TypeRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getInt16TypeRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( int16TypeRepository );
	}

	public CFBamJpaInt32DefRepository getInt32DefRepository() {
		if ( int32DefRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getInt32DefRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( int32DefRepository );
	}

	public CFBamJpaInt32TypeRepository getInt32TypeRepository() {
		if ( int32TypeRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getInt32TypeRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( int32TypeRepository );
	}

	public CFBamJpaInt64DefRepository getInt64DefRepository() {
		if ( int64DefRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getInt64DefRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( int64DefRepository );
	}

	public CFBamJpaInt64TypeRepository getInt64TypeRepository() {
		if ( int64TypeRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getInt64TypeRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( int64TypeRepository );
	}

	public CFBamJpaNmTokenDefRepository getNmTokenDefRepository() {
		if ( nmTokenDefRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getNmTokenDefRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( nmTokenDefRepository );
	}

	public CFBamJpaNmTokenTypeRepository getNmTokenTypeRepository() {
		if ( nmTokenTypeRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getNmTokenTypeRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( nmTokenTypeRepository );
	}

	public CFBamJpaNmTokensDefRepository getNmTokensDefRepository() {
		if ( nmTokensDefRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getNmTokensDefRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( nmTokensDefRepository );
	}

	public CFBamJpaNmTokensTypeRepository getNmTokensTypeRepository() {
		if ( nmTokensTypeRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getNmTokensTypeRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( nmTokensTypeRepository );
	}

	public CFBamJpaNumberDefRepository getNumberDefRepository() {
		if ( numberDefRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getNumberDefRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( numberDefRepository );
	}

	public CFBamJpaNumberTypeRepository getNumberTypeRepository() {
		if ( numberTypeRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getNumberTypeRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( numberTypeRepository );
	}

	public CFBamJpaParamRepository getParamRepository() {
		if ( paramRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getParamRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( paramRepository );
	}

	public CFBamJpaPopDepRepository getPopDepRepository() {
		if ( popDepRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getPopDepRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( popDepRepository );
	}

	public CFBamJpaPopSubDep1Repository getPopSubDep1Repository() {
		if ( popSubDep1Repository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getPopSubDep1Repository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( popSubDep1Repository );
	}

	public CFBamJpaPopSubDep2Repository getPopSubDep2Repository() {
		if ( popSubDep2Repository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getPopSubDep2Repository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( popSubDep2Repository );
	}

	public CFBamJpaPopSubDep3Repository getPopSubDep3Repository() {
		if ( popSubDep3Repository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getPopSubDep3Repository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( popSubDep3Repository );
	}

	public CFBamJpaPopTopDepRepository getPopTopDepRepository() {
		if ( popTopDepRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getPopTopDepRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( popTopDepRepository );
	}

	public CFBamJpaRelationRepository getRelationRepository() {
		if ( relationRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getRelationRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( relationRepository );
	}

	public CFBamJpaRelationColRepository getRelationColRepository() {
		if ( relationColRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getRelationColRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( relationColRepository );
	}

	public CFBamJpaServerListFuncRepository getServerListFuncRepository() {
		if ( serverListFuncRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getServerListFuncRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( serverListFuncRepository );
	}

	public CFBamJpaDbKeyHash128DefRepository getDbKeyHash128DefRepository() {
		if ( dbKeyHash128DefRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getDbKeyHash128DefRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dbKeyHash128DefRepository );
	}

	public CFBamJpaDbKeyHash128ColRepository getDbKeyHash128ColRepository() {
		if ( dbKeyHash128ColRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getDbKeyHash128ColRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dbKeyHash128ColRepository );
	}

	public CFBamJpaDbKeyHash128TypeRepository getDbKeyHash128TypeRepository() {
		if ( dbKeyHash128TypeRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getDbKeyHash128TypeRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dbKeyHash128TypeRepository );
	}

	public CFBamJpaDbKeyHash128GenRepository getDbKeyHash128GenRepository() {
		if ( dbKeyHash128GenRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getDbKeyHash128GenRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dbKeyHash128GenRepository );
	}

	public CFBamJpaDbKeyHash160DefRepository getDbKeyHash160DefRepository() {
		if ( dbKeyHash160DefRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getDbKeyHash160DefRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dbKeyHash160DefRepository );
	}

	public CFBamJpaDbKeyHash160ColRepository getDbKeyHash160ColRepository() {
		if ( dbKeyHash160ColRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getDbKeyHash160ColRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dbKeyHash160ColRepository );
	}

	public CFBamJpaDbKeyHash160TypeRepository getDbKeyHash160TypeRepository() {
		if ( dbKeyHash160TypeRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getDbKeyHash160TypeRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dbKeyHash160TypeRepository );
	}

	public CFBamJpaDbKeyHash160GenRepository getDbKeyHash160GenRepository() {
		if ( dbKeyHash160GenRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getDbKeyHash160GenRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dbKeyHash160GenRepository );
	}

	public CFBamJpaDbKeyHash224DefRepository getDbKeyHash224DefRepository() {
		if ( dbKeyHash224DefRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getDbKeyHash224DefRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dbKeyHash224DefRepository );
	}

	public CFBamJpaDbKeyHash224ColRepository getDbKeyHash224ColRepository() {
		if ( dbKeyHash224ColRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getDbKeyHash224ColRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dbKeyHash224ColRepository );
	}

	public CFBamJpaDbKeyHash224TypeRepository getDbKeyHash224TypeRepository() {
		if ( dbKeyHash224TypeRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getDbKeyHash224TypeRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dbKeyHash224TypeRepository );
	}

	public CFBamJpaDbKeyHash224GenRepository getDbKeyHash224GenRepository() {
		if ( dbKeyHash224GenRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getDbKeyHash224GenRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dbKeyHash224GenRepository );
	}

	public CFBamJpaDbKeyHash256DefRepository getDbKeyHash256DefRepository() {
		if ( dbKeyHash256DefRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getDbKeyHash256DefRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dbKeyHash256DefRepository );
	}

	public CFBamJpaDbKeyHash256ColRepository getDbKeyHash256ColRepository() {
		if ( dbKeyHash256ColRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getDbKeyHash256ColRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dbKeyHash256ColRepository );
	}

	public CFBamJpaDbKeyHash256TypeRepository getDbKeyHash256TypeRepository() {
		if ( dbKeyHash256TypeRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getDbKeyHash256TypeRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dbKeyHash256TypeRepository );
	}

	public CFBamJpaDbKeyHash256GenRepository getDbKeyHash256GenRepository() {
		if ( dbKeyHash256GenRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getDbKeyHash256GenRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dbKeyHash256GenRepository );
	}

	public CFBamJpaDbKeyHash384DefRepository getDbKeyHash384DefRepository() {
		if ( dbKeyHash384DefRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getDbKeyHash384DefRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dbKeyHash384DefRepository );
	}

	public CFBamJpaDbKeyHash384ColRepository getDbKeyHash384ColRepository() {
		if ( dbKeyHash384ColRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getDbKeyHash384ColRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dbKeyHash384ColRepository );
	}

	public CFBamJpaDbKeyHash384TypeRepository getDbKeyHash384TypeRepository() {
		if ( dbKeyHash384TypeRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getDbKeyHash384TypeRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dbKeyHash384TypeRepository );
	}

	public CFBamJpaDbKeyHash384GenRepository getDbKeyHash384GenRepository() {
		if ( dbKeyHash384GenRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getDbKeyHash384GenRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dbKeyHash384GenRepository );
	}

	public CFBamJpaDbKeyHash512DefRepository getDbKeyHash512DefRepository() {
		if ( dbKeyHash512DefRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getDbKeyHash512DefRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dbKeyHash512DefRepository );
	}

	public CFBamJpaDbKeyHash512ColRepository getDbKeyHash512ColRepository() {
		if ( dbKeyHash512ColRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getDbKeyHash512ColRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dbKeyHash512ColRepository );
	}

	public CFBamJpaDbKeyHash512TypeRepository getDbKeyHash512TypeRepository() {
		if ( dbKeyHash512TypeRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getDbKeyHash512TypeRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dbKeyHash512TypeRepository );
	}

	public CFBamJpaDbKeyHash512GenRepository getDbKeyHash512GenRepository() {
		if ( dbKeyHash512GenRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getDbKeyHash512GenRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dbKeyHash512GenRepository );
	}

	public CFBamJpaStringDefRepository getStringDefRepository() {
		if ( stringDefRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getStringDefRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( stringDefRepository );
	}

	public CFBamJpaStringTypeRepository getStringTypeRepository() {
		if ( stringTypeRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getStringTypeRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( stringTypeRepository );
	}

	public CFBamJpaTZDateDefRepository getTZDateDefRepository() {
		if ( tZDateDefRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getTZDateDefRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( tZDateDefRepository );
	}

	public CFBamJpaTZDateTypeRepository getTZDateTypeRepository() {
		if ( tZDateTypeRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getTZDateTypeRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( tZDateTypeRepository );
	}

	public CFBamJpaTZTimeDefRepository getTZTimeDefRepository() {
		if ( tZTimeDefRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getTZTimeDefRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( tZTimeDefRepository );
	}

	public CFBamJpaTZTimeTypeRepository getTZTimeTypeRepository() {
		if ( tZTimeTypeRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getTZTimeTypeRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( tZTimeTypeRepository );
	}

	public CFBamJpaTZTimestampDefRepository getTZTimestampDefRepository() {
		if ( tZTimestampDefRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getTZTimestampDefRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( tZTimestampDefRepository );
	}

	public CFBamJpaTZTimestampTypeRepository getTZTimestampTypeRepository() {
		if ( tZTimestampTypeRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getTZTimestampTypeRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( tZTimestampTypeRepository );
	}

	public CFBamJpaTableColRepository getTableColRepository() {
		if ( tableColRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getTableColRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( tableColRepository );
	}

	public CFBamJpaTextDefRepository getTextDefRepository() {
		if ( textDefRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getTextDefRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( textDefRepository );
	}

	public CFBamJpaTextTypeRepository getTextTypeRepository() {
		if ( textTypeRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getTextTypeRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( textTypeRepository );
	}

	public CFBamJpaTimeDefRepository getTimeDefRepository() {
		if ( timeDefRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getTimeDefRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( timeDefRepository );
	}

	public CFBamJpaTimeTypeRepository getTimeTypeRepository() {
		if ( timeTypeRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getTimeTypeRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( timeTypeRepository );
	}

	public CFBamJpaTimestampDefRepository getTimestampDefRepository() {
		if ( timestampDefRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getTimestampDefRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( timestampDefRepository );
	}

	public CFBamJpaTimestampTypeRepository getTimestampTypeRepository() {
		if ( timestampTypeRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getTimestampTypeRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( timestampTypeRepository );
	}

	public CFBamJpaTokenDefRepository getTokenDefRepository() {
		if ( tokenDefRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getTokenDefRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( tokenDefRepository );
	}

	public CFBamJpaTokenTypeRepository getTokenTypeRepository() {
		if ( tokenTypeRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getTokenTypeRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( tokenTypeRepository );
	}

	public CFBamJpaUInt16DefRepository getUInt16DefRepository() {
		if ( uInt16DefRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getUInt16DefRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( uInt16DefRepository );
	}

	public CFBamJpaUInt16TypeRepository getUInt16TypeRepository() {
		if ( uInt16TypeRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getUInt16TypeRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( uInt16TypeRepository );
	}

	public CFBamJpaUInt32DefRepository getUInt32DefRepository() {
		if ( uInt32DefRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getUInt32DefRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( uInt32DefRepository );
	}

	public CFBamJpaUInt32TypeRepository getUInt32TypeRepository() {
		if ( uInt32TypeRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getUInt32TypeRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( uInt32TypeRepository );
	}

	public CFBamJpaUInt64DefRepository getUInt64DefRepository() {
		if ( uInt64DefRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getUInt64DefRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( uInt64DefRepository );
	}

	public CFBamJpaUInt64TypeRepository getUInt64TypeRepository() {
		if ( uInt64TypeRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getUInt64TypeRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( uInt64TypeRepository );
	}

	public CFBamJpaUuidDefRepository getUuidDefRepository() {
		if ( uuidDefRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getUuidDefRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( uuidDefRepository );
	}

	public CFBamJpaUuid6DefRepository getUuid6DefRepository() {
		if ( uuid6DefRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getUuid6DefRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( uuid6DefRepository );
	}

	public CFBamJpaUuidTypeRepository getUuidTypeRepository() {
		if ( uuidTypeRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getUuidTypeRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( uuidTypeRepository );
	}

	public CFBamJpaUuid6TypeRepository getUuid6TypeRepository() {
		if ( uuid6TypeRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getUuid6TypeRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( uuid6TypeRepository );
	}

	public CFBamJpaBlobColRepository getBlobColRepository() {
		if ( blobColRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getBlobColRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( blobColRepository );
	}

	public CFBamJpaBoolColRepository getBoolColRepository() {
		if ( boolColRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getBoolColRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( boolColRepository );
	}

	public CFBamJpaDateColRepository getDateColRepository() {
		if ( dateColRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getDateColRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dateColRepository );
	}

	public CFBamJpaDoubleColRepository getDoubleColRepository() {
		if ( doubleColRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getDoubleColRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( doubleColRepository );
	}

	public CFBamJpaEnumDefRepository getEnumDefRepository() {
		if ( enumDefRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getEnumDefRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( enumDefRepository );
	}

	public CFBamJpaEnumTypeRepository getEnumTypeRepository() {
		if ( enumTypeRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getEnumTypeRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( enumTypeRepository );
	}

	public CFBamJpaFloatColRepository getFloatColRepository() {
		if ( floatColRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getFloatColRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( floatColRepository );
	}

	public CFBamJpaId16GenRepository getId16GenRepository() {
		if ( id16GenRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getId16GenRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( id16GenRepository );
	}

	public CFBamJpaId32GenRepository getId32GenRepository() {
		if ( id32GenRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getId32GenRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( id32GenRepository );
	}

	public CFBamJpaId64GenRepository getId64GenRepository() {
		if ( id64GenRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getId64GenRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( id64GenRepository );
	}

	public CFBamJpaInt16ColRepository getInt16ColRepository() {
		if ( int16ColRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getInt16ColRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( int16ColRepository );
	}

	public CFBamJpaInt32ColRepository getInt32ColRepository() {
		if ( int32ColRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getInt32ColRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( int32ColRepository );
	}

	public CFBamJpaInt64ColRepository getInt64ColRepository() {
		if ( int64ColRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getInt64ColRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( int64ColRepository );
	}

	public CFBamJpaNmTokenColRepository getNmTokenColRepository() {
		if ( nmTokenColRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getNmTokenColRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( nmTokenColRepository );
	}

	public CFBamJpaNmTokensColRepository getNmTokensColRepository() {
		if ( nmTokensColRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getNmTokensColRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( nmTokensColRepository );
	}

	public CFBamJpaNumberColRepository getNumberColRepository() {
		if ( numberColRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getNumberColRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( numberColRepository );
	}

	public CFBamJpaStringColRepository getStringColRepository() {
		if ( stringColRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getStringColRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( stringColRepository );
	}

	public CFBamJpaTZDateColRepository getTZDateColRepository() {
		if ( tZDateColRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getTZDateColRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( tZDateColRepository );
	}

	public CFBamJpaTZTimeColRepository getTZTimeColRepository() {
		if ( tZTimeColRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getTZTimeColRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( tZTimeColRepository );
	}

	public CFBamJpaTZTimestampColRepository getTZTimestampColRepository() {
		if ( tZTimestampColRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getTZTimestampColRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( tZTimestampColRepository );
	}

	public CFBamJpaTextColRepository getTextColRepository() {
		if ( textColRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getTextColRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( textColRepository );
	}

	public CFBamJpaTimeColRepository getTimeColRepository() {
		if ( timeColRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getTimeColRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( timeColRepository );
	}

	public CFBamJpaTimestampColRepository getTimestampColRepository() {
		if ( timestampColRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getTimestampColRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( timestampColRepository );
	}

	public CFBamJpaTokenColRepository getTokenColRepository() {
		if ( tokenColRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getTokenColRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( tokenColRepository );
	}

	public CFBamJpaUInt16ColRepository getUInt16ColRepository() {
		if ( uInt16ColRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getUInt16ColRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( uInt16ColRepository );
	}

	public CFBamJpaUInt32ColRepository getUInt32ColRepository() {
		if ( uInt32ColRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getUInt32ColRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( uInt32ColRepository );
	}

	public CFBamJpaUInt64ColRepository getUInt64ColRepository() {
		if ( uInt64ColRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getUInt64ColRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( uInt64ColRepository );
	}

	public CFBamJpaUuidColRepository getUuidColRepository() {
		if ( uuidColRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getUuidColRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( uuidColRepository );
	}

	public CFBamJpaUuid6ColRepository getUuid6ColRepository() {
		if ( uuid6ColRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getUuid6ColRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( uuid6ColRepository );
	}

	public CFBamJpaUuidGenRepository getUuidGenRepository() {
		if ( uuidGenRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getUuidGenRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( uuidGenRepository );
	}

	public CFBamJpaUuid6GenRepository getUuid6GenRepository() {
		if ( uuid6GenRepository == null ) {
			// Dynamically resolve the repository by interface type
			throw new CFLibNotImplementedYetException( getClass(), "getUuid6GenRepository",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( uuid6GenRepository );
	}

	public CFBamJpaScopeService getScopeService() {
		if ( scopeService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getScopeService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( scopeService );
	}

	public CFBamJpaSchemaDefService getSchemaDefService() {
		if ( schemaDefService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getSchemaDefService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( schemaDefService );
	}

	public CFBamJpaSchemaRefService getSchemaRefService() {
		if ( schemaRefService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getSchemaRefService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( schemaRefService );
	}

	public CFBamJpaServerMethodService getServerMethodService() {
		if ( serverMethodService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getServerMethodService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( serverMethodService );
	}

	public CFBamJpaServerObjFuncService getServerObjFuncService() {
		if ( serverObjFuncService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getServerObjFuncService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( serverObjFuncService );
	}

	public CFBamJpaServerProcService getServerProcService() {
		if ( serverProcService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getServerProcService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( serverProcService );
	}

	public CFBamJpaTableService getTableService() {
		if ( tableService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getTableService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( tableService );
	}

	public CFBamJpaValueService getValueService() {
		if ( valueService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getValueService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( valueService );
	}

	public CFBamJpaAtomService getAtomService() {
		if ( atomService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getAtomService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( atomService );
	}

	public CFBamJpaBlobDefService getBlobDefService() {
		if ( blobDefService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getBlobDefService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( blobDefService );
	}

	public CFBamJpaBlobTypeService getBlobTypeService() {
		if ( blobTypeService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getBlobTypeService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( blobTypeService );
	}

	public CFBamJpaBoolDefService getBoolDefService() {
		if ( boolDefService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getBoolDefService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( boolDefService );
	}

	public CFBamJpaBoolTypeService getBoolTypeService() {
		if ( boolTypeService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getBoolTypeService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( boolTypeService );
	}

	public CFBamJpaChainService getChainService() {
		if ( chainService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getChainService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( chainService );
	}

	public CFBamJpaClearDepService getClearDepService() {
		if ( clearDepService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getClearDepService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( clearDepService );
	}

	public CFBamJpaClearSubDep1Service getClearSubDep1Service() {
		if ( clearSubDep1Service == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getClearSubDep1Service",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( clearSubDep1Service );
	}

	public CFBamJpaClearSubDep2Service getClearSubDep2Service() {
		if ( clearSubDep2Service == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getClearSubDep2Service",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( clearSubDep2Service );
	}

	public CFBamJpaClearSubDep3Service getClearSubDep3Service() {
		if ( clearSubDep3Service == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getClearSubDep3Service",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( clearSubDep3Service );
	}

	public CFBamJpaClearTopDepService getClearTopDepService() {
		if ( clearTopDepService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getClearTopDepService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( clearTopDepService );
	}

	public CFBamJpaDateDefService getDateDefService() {
		if ( dateDefService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getDateDefService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dateDefService );
	}

	public CFBamJpaDateTypeService getDateTypeService() {
		if ( dateTypeService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getDateTypeService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dateTypeService );
	}

	public CFBamJpaDelDepService getDelDepService() {
		if ( delDepService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getDelDepService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( delDepService );
	}

	public CFBamJpaDelSubDep1Service getDelSubDep1Service() {
		if ( delSubDep1Service == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getDelSubDep1Service",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( delSubDep1Service );
	}

	public CFBamJpaDelSubDep2Service getDelSubDep2Service() {
		if ( delSubDep2Service == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getDelSubDep2Service",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( delSubDep2Service );
	}

	public CFBamJpaDelSubDep3Service getDelSubDep3Service() {
		if ( delSubDep3Service == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getDelSubDep3Service",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( delSubDep3Service );
	}

	public CFBamJpaDelTopDepService getDelTopDepService() {
		if ( delTopDepService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getDelTopDepService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( delTopDepService );
	}

	public CFBamJpaDoubleDefService getDoubleDefService() {
		if ( doubleDefService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getDoubleDefService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( doubleDefService );
	}

	public CFBamJpaDoubleTypeService getDoubleTypeService() {
		if ( doubleTypeService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getDoubleTypeService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( doubleTypeService );
	}

	public CFBamJpaEnumTagService getEnumTagService() {
		if ( enumTagService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getEnumTagService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( enumTagService );
	}

	public CFBamJpaFloatDefService getFloatDefService() {
		if ( floatDefService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getFloatDefService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( floatDefService );
	}

	public CFBamJpaFloatTypeService getFloatTypeService() {
		if ( floatTypeService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getFloatTypeService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( floatTypeService );
	}

	public CFBamJpaIndexService getIndexService() {
		if ( indexService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getIndexService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( indexService );
	}

	public CFBamJpaIndexColService getIndexColService() {
		if ( indexColService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getIndexColService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( indexColService );
	}

	public CFBamJpaInt16DefService getInt16DefService() {
		if ( int16DefService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getInt16DefService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( int16DefService );
	}

	public CFBamJpaInt16TypeService getInt16TypeService() {
		if ( int16TypeService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getInt16TypeService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( int16TypeService );
	}

	public CFBamJpaInt32DefService getInt32DefService() {
		if ( int32DefService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getInt32DefService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( int32DefService );
	}

	public CFBamJpaInt32TypeService getInt32TypeService() {
		if ( int32TypeService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getInt32TypeService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( int32TypeService );
	}

	public CFBamJpaInt64DefService getInt64DefService() {
		if ( int64DefService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getInt64DefService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( int64DefService );
	}

	public CFBamJpaInt64TypeService getInt64TypeService() {
		if ( int64TypeService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getInt64TypeService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( int64TypeService );
	}

	public CFBamJpaNmTokenDefService getNmTokenDefService() {
		if ( nmTokenDefService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getNmTokenDefService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( nmTokenDefService );
	}

	public CFBamJpaNmTokenTypeService getNmTokenTypeService() {
		if ( nmTokenTypeService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getNmTokenTypeService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( nmTokenTypeService );
	}

	public CFBamJpaNmTokensDefService getNmTokensDefService() {
		if ( nmTokensDefService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getNmTokensDefService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( nmTokensDefService );
	}

	public CFBamJpaNmTokensTypeService getNmTokensTypeService() {
		if ( nmTokensTypeService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getNmTokensTypeService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( nmTokensTypeService );
	}

	public CFBamJpaNumberDefService getNumberDefService() {
		if ( numberDefService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getNumberDefService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( numberDefService );
	}

	public CFBamJpaNumberTypeService getNumberTypeService() {
		if ( numberTypeService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getNumberTypeService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( numberTypeService );
	}

	public CFBamJpaParamService getParamService() {
		if ( paramService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getParamService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( paramService );
	}

	public CFBamJpaPopDepService getPopDepService() {
		if ( popDepService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getPopDepService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( popDepService );
	}

	public CFBamJpaPopSubDep1Service getPopSubDep1Service() {
		if ( popSubDep1Service == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getPopSubDep1Service",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( popSubDep1Service );
	}

	public CFBamJpaPopSubDep2Service getPopSubDep2Service() {
		if ( popSubDep2Service == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getPopSubDep2Service",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( popSubDep2Service );
	}

	public CFBamJpaPopSubDep3Service getPopSubDep3Service() {
		if ( popSubDep3Service == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getPopSubDep3Service",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( popSubDep3Service );
	}

	public CFBamJpaPopTopDepService getPopTopDepService() {
		if ( popTopDepService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getPopTopDepService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( popTopDepService );
	}

	public CFBamJpaRelationService getRelationService() {
		if ( relationService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getRelationService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( relationService );
	}

	public CFBamJpaRelationColService getRelationColService() {
		if ( relationColService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getRelationColService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( relationColService );
	}

	public CFBamJpaServerListFuncService getServerListFuncService() {
		if ( serverListFuncService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getServerListFuncService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( serverListFuncService );
	}

	public CFBamJpaDbKeyHash128DefService getDbKeyHash128DefService() {
		if ( dbKeyHash128DefService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getDbKeyHash128DefService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dbKeyHash128DefService );
	}

	public CFBamJpaDbKeyHash128ColService getDbKeyHash128ColService() {
		if ( dbKeyHash128ColService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getDbKeyHash128ColService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dbKeyHash128ColService );
	}

	public CFBamJpaDbKeyHash128TypeService getDbKeyHash128TypeService() {
		if ( dbKeyHash128TypeService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getDbKeyHash128TypeService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dbKeyHash128TypeService );
	}

	public CFBamJpaDbKeyHash128GenService getDbKeyHash128GenService() {
		if ( dbKeyHash128GenService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getDbKeyHash128GenService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dbKeyHash128GenService );
	}

	public CFBamJpaDbKeyHash160DefService getDbKeyHash160DefService() {
		if ( dbKeyHash160DefService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getDbKeyHash160DefService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dbKeyHash160DefService );
	}

	public CFBamJpaDbKeyHash160ColService getDbKeyHash160ColService() {
		if ( dbKeyHash160ColService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getDbKeyHash160ColService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dbKeyHash160ColService );
	}

	public CFBamJpaDbKeyHash160TypeService getDbKeyHash160TypeService() {
		if ( dbKeyHash160TypeService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getDbKeyHash160TypeService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dbKeyHash160TypeService );
	}

	public CFBamJpaDbKeyHash160GenService getDbKeyHash160GenService() {
		if ( dbKeyHash160GenService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getDbKeyHash160GenService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dbKeyHash160GenService );
	}

	public CFBamJpaDbKeyHash224DefService getDbKeyHash224DefService() {
		if ( dbKeyHash224DefService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getDbKeyHash224DefService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dbKeyHash224DefService );
	}

	public CFBamJpaDbKeyHash224ColService getDbKeyHash224ColService() {
		if ( dbKeyHash224ColService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getDbKeyHash224ColService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dbKeyHash224ColService );
	}

	public CFBamJpaDbKeyHash224TypeService getDbKeyHash224TypeService() {
		if ( dbKeyHash224TypeService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getDbKeyHash224TypeService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dbKeyHash224TypeService );
	}

	public CFBamJpaDbKeyHash224GenService getDbKeyHash224GenService() {
		if ( dbKeyHash224GenService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getDbKeyHash224GenService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dbKeyHash224GenService );
	}

	public CFBamJpaDbKeyHash256DefService getDbKeyHash256DefService() {
		if ( dbKeyHash256DefService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getDbKeyHash256DefService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dbKeyHash256DefService );
	}

	public CFBamJpaDbKeyHash256ColService getDbKeyHash256ColService() {
		if ( dbKeyHash256ColService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getDbKeyHash256ColService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dbKeyHash256ColService );
	}

	public CFBamJpaDbKeyHash256TypeService getDbKeyHash256TypeService() {
		if ( dbKeyHash256TypeService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getDbKeyHash256TypeService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dbKeyHash256TypeService );
	}

	public CFBamJpaDbKeyHash256GenService getDbKeyHash256GenService() {
		if ( dbKeyHash256GenService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getDbKeyHash256GenService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dbKeyHash256GenService );
	}

	public CFBamJpaDbKeyHash384DefService getDbKeyHash384DefService() {
		if ( dbKeyHash384DefService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getDbKeyHash384DefService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dbKeyHash384DefService );
	}

	public CFBamJpaDbKeyHash384ColService getDbKeyHash384ColService() {
		if ( dbKeyHash384ColService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getDbKeyHash384ColService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dbKeyHash384ColService );
	}

	public CFBamJpaDbKeyHash384TypeService getDbKeyHash384TypeService() {
		if ( dbKeyHash384TypeService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getDbKeyHash384TypeService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dbKeyHash384TypeService );
	}

	public CFBamJpaDbKeyHash384GenService getDbKeyHash384GenService() {
		if ( dbKeyHash384GenService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getDbKeyHash384GenService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dbKeyHash384GenService );
	}

	public CFBamJpaDbKeyHash512DefService getDbKeyHash512DefService() {
		if ( dbKeyHash512DefService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getDbKeyHash512DefService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dbKeyHash512DefService );
	}

	public CFBamJpaDbKeyHash512ColService getDbKeyHash512ColService() {
		if ( dbKeyHash512ColService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getDbKeyHash512ColService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dbKeyHash512ColService );
	}

	public CFBamJpaDbKeyHash512TypeService getDbKeyHash512TypeService() {
		if ( dbKeyHash512TypeService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getDbKeyHash512TypeService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dbKeyHash512TypeService );
	}

	public CFBamJpaDbKeyHash512GenService getDbKeyHash512GenService() {
		if ( dbKeyHash512GenService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getDbKeyHash512GenService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dbKeyHash512GenService );
	}

	public CFBamJpaStringDefService getStringDefService() {
		if ( stringDefService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getStringDefService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( stringDefService );
	}

	public CFBamJpaStringTypeService getStringTypeService() {
		if ( stringTypeService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getStringTypeService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( stringTypeService );
	}

	public CFBamJpaTZDateDefService getTZDateDefService() {
		if ( tZDateDefService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getTZDateDefService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( tZDateDefService );
	}

	public CFBamJpaTZDateTypeService getTZDateTypeService() {
		if ( tZDateTypeService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getTZDateTypeService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( tZDateTypeService );
	}

	public CFBamJpaTZTimeDefService getTZTimeDefService() {
		if ( tZTimeDefService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getTZTimeDefService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( tZTimeDefService );
	}

	public CFBamJpaTZTimeTypeService getTZTimeTypeService() {
		if ( tZTimeTypeService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getTZTimeTypeService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( tZTimeTypeService );
	}

	public CFBamJpaTZTimestampDefService getTZTimestampDefService() {
		if ( tZTimestampDefService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getTZTimestampDefService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( tZTimestampDefService );
	}

	public CFBamJpaTZTimestampTypeService getTZTimestampTypeService() {
		if ( tZTimestampTypeService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getTZTimestampTypeService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( tZTimestampTypeService );
	}

	public CFBamJpaTableColService getTableColService() {
		if ( tableColService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getTableColService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( tableColService );
	}

	public CFBamJpaTextDefService getTextDefService() {
		if ( textDefService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getTextDefService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( textDefService );
	}

	public CFBamJpaTextTypeService getTextTypeService() {
		if ( textTypeService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getTextTypeService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( textTypeService );
	}

	public CFBamJpaTimeDefService getTimeDefService() {
		if ( timeDefService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getTimeDefService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( timeDefService );
	}

	public CFBamJpaTimeTypeService getTimeTypeService() {
		if ( timeTypeService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getTimeTypeService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( timeTypeService );
	}

	public CFBamJpaTimestampDefService getTimestampDefService() {
		if ( timestampDefService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getTimestampDefService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( timestampDefService );
	}

	public CFBamJpaTimestampTypeService getTimestampTypeService() {
		if ( timestampTypeService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getTimestampTypeService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( timestampTypeService );
	}

	public CFBamJpaTokenDefService getTokenDefService() {
		if ( tokenDefService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getTokenDefService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( tokenDefService );
	}

	public CFBamJpaTokenTypeService getTokenTypeService() {
		if ( tokenTypeService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getTokenTypeService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( tokenTypeService );
	}

	public CFBamJpaUInt16DefService getUInt16DefService() {
		if ( uInt16DefService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getUInt16DefService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( uInt16DefService );
	}

	public CFBamJpaUInt16TypeService getUInt16TypeService() {
		if ( uInt16TypeService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getUInt16TypeService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( uInt16TypeService );
	}

	public CFBamJpaUInt32DefService getUInt32DefService() {
		if ( uInt32DefService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getUInt32DefService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( uInt32DefService );
	}

	public CFBamJpaUInt32TypeService getUInt32TypeService() {
		if ( uInt32TypeService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getUInt32TypeService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( uInt32TypeService );
	}

	public CFBamJpaUInt64DefService getUInt64DefService() {
		if ( uInt64DefService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getUInt64DefService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( uInt64DefService );
	}

	public CFBamJpaUInt64TypeService getUInt64TypeService() {
		if ( uInt64TypeService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getUInt64TypeService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( uInt64TypeService );
	}

	public CFBamJpaUuidDefService getUuidDefService() {
		if ( uuidDefService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getUuidDefService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( uuidDefService );
	}

	public CFBamJpaUuid6DefService getUuid6DefService() {
		if ( uuid6DefService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getUuid6DefService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( uuid6DefService );
	}

	public CFBamJpaUuidTypeService getUuidTypeService() {
		if ( uuidTypeService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getUuidTypeService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( uuidTypeService );
	}

	public CFBamJpaUuid6TypeService getUuid6TypeService() {
		if ( uuid6TypeService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getUuid6TypeService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( uuid6TypeService );
	}

	public CFBamJpaBlobColService getBlobColService() {
		if ( blobColService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getBlobColService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( blobColService );
	}

	public CFBamJpaBoolColService getBoolColService() {
		if ( boolColService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getBoolColService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( boolColService );
	}

	public CFBamJpaDateColService getDateColService() {
		if ( dateColService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getDateColService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( dateColService );
	}

	public CFBamJpaDoubleColService getDoubleColService() {
		if ( doubleColService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getDoubleColService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( doubleColService );
	}

	public CFBamJpaEnumDefService getEnumDefService() {
		if ( enumDefService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getEnumDefService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( enumDefService );
	}

	public CFBamJpaEnumTypeService getEnumTypeService() {
		if ( enumTypeService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getEnumTypeService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( enumTypeService );
	}

	public CFBamJpaFloatColService getFloatColService() {
		if ( floatColService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getFloatColService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( floatColService );
	}

	public CFBamJpaId16GenService getId16GenService() {
		if ( id16GenService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getId16GenService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( id16GenService );
	}

	public CFBamJpaId32GenService getId32GenService() {
		if ( id32GenService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getId32GenService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( id32GenService );
	}

	public CFBamJpaId64GenService getId64GenService() {
		if ( id64GenService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getId64GenService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( id64GenService );
	}

	public CFBamJpaInt16ColService getInt16ColService() {
		if ( int16ColService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getInt16ColService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( int16ColService );
	}

	public CFBamJpaInt32ColService getInt32ColService() {
		if ( int32ColService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getInt32ColService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( int32ColService );
	}

	public CFBamJpaInt64ColService getInt64ColService() {
		if ( int64ColService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getInt64ColService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( int64ColService );
	}

	public CFBamJpaNmTokenColService getNmTokenColService() {
		if ( nmTokenColService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getNmTokenColService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( nmTokenColService );
	}

	public CFBamJpaNmTokensColService getNmTokensColService() {
		if ( nmTokensColService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getNmTokensColService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( nmTokensColService );
	}

	public CFBamJpaNumberColService getNumberColService() {
		if ( numberColService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getNumberColService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( numberColService );
	}

	public CFBamJpaStringColService getStringColService() {
		if ( stringColService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getStringColService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( stringColService );
	}

	public CFBamJpaTZDateColService getTZDateColService() {
		if ( tZDateColService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getTZDateColService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( tZDateColService );
	}

	public CFBamJpaTZTimeColService getTZTimeColService() {
		if ( tZTimeColService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getTZTimeColService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( tZTimeColService );
	}

	public CFBamJpaTZTimestampColService getTZTimestampColService() {
		if ( tZTimestampColService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getTZTimestampColService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( tZTimestampColService );
	}

	public CFBamJpaTextColService getTextColService() {
		if ( textColService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getTextColService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( textColService );
	}

	public CFBamJpaTimeColService getTimeColService() {
		if ( timeColService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getTimeColService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( timeColService );
	}

	public CFBamJpaTimestampColService getTimestampColService() {
		if ( timestampColService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getTimestampColService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( timestampColService );
	}

	public CFBamJpaTokenColService getTokenColService() {
		if ( tokenColService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getTokenColService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( tokenColService );
	}

	public CFBamJpaUInt16ColService getUInt16ColService() {
		if ( uInt16ColService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getUInt16ColService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( uInt16ColService );
	}

	public CFBamJpaUInt32ColService getUInt32ColService() {
		if ( uInt32ColService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getUInt32ColService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( uInt32ColService );
	}

	public CFBamJpaUInt64ColService getUInt64ColService() {
		if ( uInt64ColService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getUInt64ColService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( uInt64ColService );
	}

	public CFBamJpaUuidColService getUuidColService() {
		if ( uuidColService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getUuidColService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( uuidColService );
	}

	public CFBamJpaUuid6ColService getUuid6ColService() {
		if ( uuid6ColService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getUuid6ColService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( uuid6ColService );
	}

	public CFBamJpaUuidGenService getUuidGenService() {
		if ( uuidGenService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getUuidGenService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( uuidGenService );
	}

	public CFBamJpaUuid6GenService getUuid6GenService() {
		if ( uuid6GenService == null ) {
			// Dynamically resolve the repository by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getUuid6GenService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( uuid6GenService );
	}
}
