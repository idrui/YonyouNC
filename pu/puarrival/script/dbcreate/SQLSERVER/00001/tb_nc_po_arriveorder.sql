/* tablename: 到货单明细表 */
create table po_arriveorder_b (
pk_arriveorder_b nchar(20) not null 
/*到货单明细*/,
crowno nvarchar(20) null 
/*行号*/,
pk_org nvarchar(20) null 
/*库存组织最新版本*/,
pk_org_v nvarchar(20) null 
/*库存组织*/,
pk_group nvarchar(20) null 
/*集团*/,
pk_psfinanceorg nvarchar(20) null 
/*结算财务组织最新版本*/,
pk_psfinanceorg_v nvarchar(20) null 
/*结算财务组织*/,
pk_apfinanceorg nvarchar(20) null 
/*应付组织最新版本*/,
pk_apfinanceorg_v nvarchar(20) null 
/*应付组织*/,
pk_reqstoorg nvarchar(20) null 
/*需求库存组织最新版本*/,
pk_reqstoorg_v nvarchar(20) null 
/*需求库存组织*/,
pk_reqstore nvarchar(20) null 
/*需求仓库*/,
pk_material nvarchar(20) null 
/*物料VID*/,
pk_srcmaterial nvarchar(20) null 
/*物料OID*/,
cunitid nvarchar(20) null 
/*主单位*/,
castunitid nvarchar(20) null 
/*单位*/,
vchangerate nvarchar(60) null 
/*换算率*/,
nnum decimal(28,8) null 
/*到货主数量*/,
nastnum decimal(28,8) null 
/*到货数量*/,
nwastnum decimal(28,8) null 
/*途耗主数量*/,
nwastastnum decimal(28,8) null 
/*途耗数量*/,
nplannum decimal(28,8) null 
/*应到主数量*/,
nplanastnum decimal(28,8) null 
/*应到数量*/,
bpresent nchar(1) null 
/*赠品*/,
npresentnum decimal(28,8) null 
/*赠品主数量*/,
npresentastnum decimal(28,8) null 
/*赠品数量*/,
corigcurrencyid nvarchar(20) null 
/*原币币种*/,
norigtaxprice decimal(28,8) null 
/*主原币含税单价*/,
norigprice decimal(28,8) null 
/*主原币无税单价*/,
norigtaxmny decimal(28,8) null 
/*原币含税金额*/,
norigmny decimal(28,8) null 
/*原币无税金额*/,
ccurrencyid nvarchar(20) null 
/*本币币种*/,
ntaxprice decimal(28,8) null 
/*主本币含税单价*/,
nprice decimal(28,8) null 
/*主本币无税单价*/,
ntaxmny decimal(28,8) null 
/*本币含税金额*/,
nmny decimal(28,8) null 
/*本币无税金额*/,
ntax decimal(28,8) null 
/*税额*/,
nexchangerate decimal(28,8) null 
/*折本汇率*/,
dplanreceivedate nvarchar(19) null 
/*计划到货日期*/,
dproducedate nvarchar(19) null 
/*生产日期*/,
ivalidday int null 
/*保质期天数*/,
dinvaliddate nvarchar(19) null 
/*失效日期*/,
bbackreforder nchar(1) null 
/*退货基于原订单补货*/,
creporterid nvarchar(20) null 
/*报告人*/,
dreportdate nvarchar(19) null 
/*报告日期*/,
nelignum decimal(28,8) null 
/*合格主数量*/,
nnotelignum decimal(28,8) null 
/*不合格主数量*/,
bletgostate nchar(1) null 
/*是否紧急放行状态*/,
pk_passbill nvarchar(20) null 
/*紧急放行单主键*/,
vpassbillcode nvarchar(40) null 
/*紧急放行单号*/,
pk_passbill_b nvarchar(20) null 
/*紧急放行单表体主键*/,
cpassbollrowno nvarchar(20) null 
/*紧急放行单行号*/,
naccumletgonum decimal(28,8) null 
/*累计紧急放行主数量*/,
naccumletgoinnum decimal(28,8) null 
/*累计紧急放行入库主数???*/,
naccumchecknum decimal(28,8) null 
/*累计报检主数量*/,
naccumstorenum decimal(28,8) null 
/*累计入库主数量*/,
naccumreplnum decimal(28,8) null 
/*累计补货主数量*/,
pk_receivestore nvarchar(20) null 
/*收货仓库*/,
pk_rack nvarchar(20) null 
/*货位*/,
pk_batchcode nvarchar(20) null 
/*批次号主键*/,
vmemob nvarchar(181) null 
/*备注*/,
vbackreasonb nvarchar(20) null 
/*退货理由*/,
cprojectid nvarchar(20) null 
/*项目*/,
cproductorid nvarchar(20) null 
/*生产厂商*/,
bfaflag nchar(1) null 
/*已生成资产片*/,
bpresentsource nchar(1) null 
/*来源订单行是否赠品*/,
pk_order nvarchar(20) null 
/*采购订单*/,
pk_order_b nvarchar(20) null 
/*采购订单明细*/,
pk_order_bb1 nvarchar(20) null 
/*订单到货计划*/,
csourcetypecode nvarchar(20) null 
/*来源单据类型*/,
vsourcecode nvarchar(40) null 
/*来源单据号*/,
csourceid nvarchar(20) null 
/*来源单据*/,
csourcebid nvarchar(20) null 
/*来源单据明细*/,
vsourcerowno nvarchar(20) null 
/*来源单据行号*/,
vsourcetrantype nvarchar(20) null 
/*来源交易类型*/,
cfirsttypecode nvarchar(20) null 
/*源头单据类型*/,
vfirstcode nvarchar(40) null 
/*源头单据号*/,
cfirstid nvarchar(20) null 
/*源头单据*/,
cfirstbid nvarchar(20) null 
/*源头单据明细*/,
vfirstrowno nvarchar(20) null 
/*源头单据行号*/,
vfirsttrantype nvarchar(20) null 
/*源头交易类型*/,
vfree1 nvarchar(101) null 
/*自由辅助属性1*/,
vfree2 nvarchar(101) null 
/*自由辅助属性2*/,
vfree3 nvarchar(101) null 
/*自由辅助属性3*/,
vfree4 nvarchar(101) null 
/*自由辅助属性4*/,
vfree5 nvarchar(101) null 
/*自由辅助属性5*/,
vfree6 nvarchar(101) null 
/*自由辅助属性6*/,
vfree7 nvarchar(101) null 
/*自由辅助属性7*/,
vfree8 nvarchar(101) null 
/*自由辅助属性8*/,
vfree9 nvarchar(101) null 
/*自由辅助属性9*/,
vfree10 nvarchar(101) null 
/*自由辅助属性10*/,
vbdef1 nvarchar(101) null 
/*自定义项1*/,
vbdef2 nvarchar(101) null 
/*自定义项2*/,
vbdef3 nvarchar(101) null 
/*自定义项3*/,
vbdef4 nvarchar(101) null 
/*自定义项4*/,
vbdef5 nvarchar(101) null 
/*自定义项5*/,
vbdef6 nvarchar(101) null 
/*自定义项6*/,
vbdef7 nvarchar(101) null 
/*自定义项7*/,
vbdef8 nvarchar(101) null 
/*自定义项8*/,
vbdef9 nvarchar(101) null 
/*自定义项9*/,
vbdef10 nvarchar(101) null 
/*自定义项10*/,
vbdef11 nvarchar(101) null 
/*自定义项11*/,
vbdef12 nvarchar(101) null 
/*自定义项12*/,
vbdef13 nvarchar(101) null 
/*自定义项13*/,
vbdef14 nvarchar(101) null 
/*自定义项14*/,
vbdef15 nvarchar(101) null 
/*自定义项15*/,
vbdef16 nvarchar(101) null 
/*自定义项16*/,
vbdef17 nvarchar(101) null 
/*自定义项17*/,
vbdef18 nvarchar(101) null 
/*自定义项18*/,
vbdef19 nvarchar(101) null 
/*自定义项19*/,
vbdef20 nvarchar(101) null 
/*自定义项20*/,
vbatchcode nvarchar(40) null 
/*批次号编码*/,
dbilldate nchar(19) null 
/*到货日期*/,
ntaxrate decimal(28,8) null 
/*税率*/,
btransasset nchar(1) null default 'N' 
/*已生成转固单*/,
pk_arriveorder nchar(20) not null 
/*到货单主表_主键*/,
cqualitylevelid nvarchar(20) null 
/*质量等级*/,
casscustid nvarchar(20) null 
/*客户*/,
cprojecttaskid nvarchar(20) null 
/*项目任务*/,
csendcountryid nvarchar(20) null 
/*发货国家/地区*/,
crececountryid nvarchar(20) null 
/*收货国家/地区*/,
ctaxcountryid nvarchar(20) null 
/*报税国家/地区*/,
fbuysellflag int null 
/*购销类型*/,
btriatradeflag nchar(1) null 
/*三角贸易*/,
csourcearriveid nchar(20) null 
/*来源到货单*/,
csourcearrivebid nchar(20) null 
/*来源到货单明细*/,
naccumbacknum decimal(28,8) null 
/*累计退货主数量*/,
ftaxtypeflag int null 
/*扣税类别*/,
pk_apliabcenter nvarchar(20) null 
/*结算利润中心最新版本*/,
pk_apliabcenter_v nvarchar(20) null 
/*结算利润中心*/,
pk_arrliabcenter nvarchar(20) null 
/*收货利润中心最新版本*/,
pk_arrliabcenter_v nvarchar(20) null 
/*收货利润中心*/,
fproductclass int null 
/*产品类型*/,
cffileid nvarchar(20) null 
/*特征码*/,
bc_vvendbatchcode nvarchar(50) null 
/*供应商批次号*/,
 constraint pk_o_arriveorder_b primary key (pk_arriveorder_b),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 到货单主表 */
create table po_arriveorder (
pk_arriveorder nchar(20) not null 
/*到货单*/,
pk_group nvarchar(20) null 
/*集团*/,
pk_org nvarchar(20) null 
/*库存组织最新版本*/,
pk_org_v nvarchar(20) null 
/*库存组织*/,
pk_purchaseorg nvarchar(20) null 
/*采购组织最新版本*/,
pk_purchaseorg_v nvarchar(20) null 
/*采购组织*/,
vbillcode nvarchar(40) null 
/*到货单号*/,
dbilldate nchar(19) null 
/*到货日期*/,
pk_freecust nvarchar(20) null 
/*散户*/,
pk_supplier nvarchar(20) null 
/*供应商*/,
pk_busitype nvarchar(20) null 
/*业务流程*/,
vtrantypecode nvarchar(20) null 
/*到货类型编码*/,
pk_transporttype nvarchar(20) null 
/*运输方式*/,
pk_receivepsndoc nvarchar(20) null 
/*收货人*/,
pk_dept nvarchar(20) null 
/*采购部门最新版本*/,
pk_dept_v nvarchar(20) null 
/*采购部门*/,
pk_pupsndoc nvarchar(20) null 
/*采购员*/,
fbillstatus int null default 0 
/*单据状态*/,
vmemo nvarchar(181) null 
/*备注*/,
bisback nchar(1) null 
/*退货*/,
iprintcount int null 
/*打印次数*/,
vbackreason nvarchar(20) null 
/*退货理由*/,
ntotalastnum decimal(28,8) null 
/*总数量*/,
ntotaltaxmny decimal(28,8) null 
/*本币价税合计*/,
creationtime nchar(19) null 
/*创建时间*/,
billmaker nvarchar(20) null 
/*制单人*/,
dmakedate nchar(19) null 
/*制单日期*/,
taudittime nvarchar(19) null 
/*审批日期*/,
approver nvarchar(20) null 
/*审批人*/,
modifiedtime nchar(19) null 
/*最后修改时间*/,
modifier nvarchar(20) null 
/*最后修改人*/,
creator nvarchar(20) null 
/*创建人*/,
vdef1 nvarchar(101) null 
/*自定义项1*/,
vdef2 nvarchar(101) null 
/*自定义项2*/,
vdef3 nvarchar(101) null 
/*自定义项3*/,
vdef4 nvarchar(101) null 
/*自定义项4*/,
vdef5 nvarchar(101) null 
/*自定义项5*/,
vdef6 nvarchar(101) null 
/*自定义项6*/,
vdef7 nvarchar(101) null 
/*自定义项7*/,
vdef8 nvarchar(101) null 
/*自定义项8*/,
vdef9 nvarchar(101) null 
/*自定义项9*/,
vdef10 nvarchar(101) null 
/*自定义项10*/,
vdef11 nvarchar(101) null 
/*自定义项11*/,
vdef12 nvarchar(101) null 
/*自定义项12*/,
vdef13 nvarchar(101) null 
/*自定义项13*/,
vdef14 nvarchar(101) null 
/*自定义项14*/,
vdef15 nvarchar(101) null 
/*自定义项15*/,
vdef16 nvarchar(101) null 
/*自定义项16*/,
vdef17 nvarchar(101) null 
/*自定义项17*/,
vdef18 nvarchar(101) null 
/*自定义项18*/,
vdef19 nvarchar(101) null 
/*自定义项19*/,
vdef20 nvarchar(101) null 
/*自定义项20*/,
ctrantypeid nvarchar(20) null 
/*到货类型*/,
bpublish nchar(1) null 
/*发布*/,
pk_pubpsn nvarchar(20) null 
/*发布人*/,
tpubtime nvarchar(19) null 
/*发布时间*/,
irespstatus int null 
/*响应状态*/,
pk_resppsn nvarchar(20) null 
/*响应人*/,
tresptime nvarchar(19) null 
/*响应时间*/,
vsupbackreason nvarchar(181) null 
/*供应商退货说明*/,
 constraint pk_po_arriveorder primary key (pk_arriveorder),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 到货单质检明细表 */
create table po_arriveorder_bb (
pk_arriveorder_bb nchar(20) not null 
/*到货单质检明细*/,
pk_arriveorder nchar(20) null 
/*到货单主键*/,
pk_group nvarchar(20) null 
/*所属集团*/,
bcanstore nchar(1) null 
/*是否可入库*/,
nnum decimal(28,8) null 
/*主数量*/,
nastnum decimal(28,8) null 
/*数量*/,
pk_inbatchcode nvarchar(20) null 
/*入库批次号主键*/,
vinbatchcode nvarchar(40) null 
/*入库批次号编码*/,
naccumstorenum decimal(28,8) null 
/*累计入库数量*/,
beligible nchar(1) null 
/*是否合格*/,
bchanged nchar(1) null 
/*是否改判*/,
pk_chgmaterial nvarchar(20) null 
/*改判物料VID*/,
pk_chgsrcmaterial nvarchar(20) null 
/*改判物料OID*/,
cunitid nvarchar(20) null 
/*主单位*/,
castunitid nvarchar(20) null 
/*单位*/,
vchangerate nvarchar(60) null 
/*换算率*/,
vchgfree1 nvarchar(100) null 
/*改判自由辅助属性1*/,
vchgfree2 nvarchar(100) null 
/*改判自由辅助属性2*/,
vchgfree3 nvarchar(100) null 
/*改判自由辅助属性3*/,
vchgfree4 nvarchar(100) null 
/*改判自由辅助属性4*/,
vchgfree5 nvarchar(100) null 
/*改判自由辅助属性5*/,
vchgfree6 nvarchar(100) null 
/*改判自由辅助属性6*/,
vchgfree7 nvarchar(100) null 
/*改判自由辅助属性7*/,
vchgfree8 nvarchar(100) null 
/*改判自由辅助属性8*/,
vchgfree9 nvarchar(100) null 
/*改判自由辅助属性9*/,
vchgfree10 nvarchar(100) null 
/*改判自由辅助属性10*/,
pk_qcreport nchar(20) null 
/*质检报告主键*/,
pk_arriveorder_b nchar(20) not null 
/*到货单明细表_主键*/,
 constraint pk__arriveorder_bb primary key (pk_arriveorder_bb),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

