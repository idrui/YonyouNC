/* tablename: 请购单子表 */
create table po_praybill_b (pk_praybill_b char(20) not null 
/*请购单子表*/,
pk_group varchar(20) null 
/*所属???团*/,
pk_org varchar(20) null 
/*库存组织最新版本*/,
pk_org_v varchar(20) null 
/*库存组织*/,
crowno varchar(20) null 
/*行号*/,
pk_srcmaterial varchar(20) null 
/*物料信息*/,
pk_material varchar(20) null 
/*物料最新版本*/,
castunitid varchar(20) null 
/*单位*/,
nastnum decimal(28,8) null 
/*数量*/,
vchangerate varchar(60) null 
/*换算率*/,
cunitid varchar(20) null 
/*主单位*/,
nnum decimal(28,8) null 
/*主数量*/,
pk_reqdept varchar(20) null 
/*需求部门最新版本*/,
pk_reqdept_v varchar(20) null 
/*需求部门*/,
ntaxprice decimal(28,8) null 
/*主本币含税单价*/,
ntaxmny decimal(28,8) null 
/*本币价税合计*/,
dreqdate varchar(19) null 
/*需求日期*/,
dsuggestdate varchar(19) null 
/*建议订货日期*/,
pk_purchaseorg varchar(20) null 
/*采购组织最新版本*/,
pk_purchaseorg_v varchar(20) null 
/*采购组织*/,
bcanpurchaseorgedit char(1) null 
/*采购组织可编辑*/,
cordertrantypecode varchar(20) null 
/*订单类型*/,
pk_suggestsupplier varchar(20) null 
/*建议供应商*/,
pk_reqstor varchar(20) null 
/*需求仓库*/,
csourcetypecode varchar(20) null 
/*来源单据类型*/,
csourceid varchar(20) null 
/*来源单据标识*/,
csourcebid varchar(20) null 
/*来源单据分录标识*/,
vsourcecode varchar(40) null 
/*来源单据号*/,
vsourcerowno varchar(20) null 
/*来源单据行号*/,
vsrctrantypecode varchar(20) null 
/*来源交易类型*/,
cfirsttypecode varchar(20) null 
/*源头单据类型*/,
cfirstid varchar(20) null 
/*源头单据标识*/,
cfirstbid varchar(20) null 
/*源头单据分录标识*/,
vfirstcode varchar(40) null 
/*源头单据号*/,
vfirstrowno varchar(20) null 
/*源头单据行号*/,
vfirsttrantype varchar(20) null 
/*源头交易类型*/,
cprojectid varchar(20) null 
/*项目*/,
cproductorid varchar(20) null 
/*生产厂商*/,
pk_batchcode varchar(20) null 
/*批次档案*/,
vbatchcode varchar(40) null 
/*批次号*/,
pk_employee varchar(20) null 
/*采购员*/,
vfree1 varchar(101) null 
/*自由辅助属性1*/,
vfree2 varchar(101) null 
/*自由辅助属性2*/,
vfree3 varchar(101) null 
/*自由辅助属性3*/,
vfree4 varchar(101) null 
/*自由辅助属性4*/,
vfree5 varchar(101) null 
/*自由辅助属性5*/,
vfree6 varchar(101) null 
/*自由辅助属性6*/,
vfree7 varchar(101) null 
/*自由辅助属性7*/,
vfree8 varchar(101) null 
/*自由辅助属性8*/,
vfree9 varchar(101) null 
/*自由辅助属性9*/,
vfree10 varchar(101) null 
/*自由辅助属性10*/,
vbdef1 varchar(101) null 
/*自定义项1*/,
vbdef2 varchar(101) null 
/*自定义项2*/,
vbdef3 varchar(101) null 
/*自定义项3*/,
vbdef4 varchar(101) null 
/*自定义项4*/,
vbdef5 varchar(101) null 
/*自定义项5*/,
vbdef6 varchar(101) null 
/*自定义项6*/,
vbdef7 varchar(101) null 
/*自定义项7*/,
vbdef8 varchar(101) null 
/*自定义项8*/,
vbdef9 varchar(101) null 
/*自定义项9*/,
vbdef10 varchar(101) null 
/*自定义项10*/,
vbdef11 varchar(101) null 
/*自定义项11*/,
vbdef12 varchar(101) null 
/*自定义项12*/,
vbdef13 varchar(101) null 
/*自定义项13*/,
vbdef14 varchar(101) null 
/*自定义项14*/,
vbdef15 varchar(101) null 
/*自定义项15*/,
vbdef16 varchar(101) null 
/*自定义项16*/,
vbdef17 varchar(101) null 
/*自定义项17*/,
vbdef18 varchar(101) null 
/*自定义项18*/,
vbdef19 varchar(101) null 
/*自定义项19*/,
vbdef20 varchar(101) null 
/*自定义项20*/,
vbmemo varchar(181) null 
/*备注*/,
naccumulatenum decimal(28,8) null 
/*累计订货主数量*/,
ngenct integer null 
/*生成合同次数*/,
npriceauditbill integer null 
/*生成价格审批单次数*/,
nquotebill integer null 
/*生成询报价单次数*/,
pk_product varchar(20) null 
/*产品*/,
pk_product_v varchar(20) null 
/*产品版本*/,
pk_customer varchar(20) null 
/*销售客户*/,
browclose char(1) null 
/*行关闭*/,
dbilldate char(19) null 
/*请购日期*/,
bpublishtoec char(1) null 
/*发布到电子商务*/,
pk_praybill char(20) not null 
/*请购单主表_主键*/,
casscustid varchar(20) null 
/*客户*/,
cprojecttaskid varchar(20) null 
/*项目任务*/,
bisgensaorder char(1) null 
/*已生成总括订单*/,
pk_reqstoorg_v varchar(20) null 
/*原始需求库存组织*/,
pk_reqstoorg varchar(20) null 
/*原始需求库存组织最新版本*/,
cffileid varchar(20) null 
/*特征码*/,
bisarrange char(1) null 
/*已安排*/,
 constraint pk_po_praybill_b primary key (pk_praybill_b),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: 请购单主表 */
create table po_praybill (pk_praybill char(20) not null 
/*请购单*/,
pk_group varchar(20) null 
/*所属集团*/,
pk_org varchar(20) null 
/*库存组织最新版本*/,
pk_org_v varchar(20) null 
/*库存组织*/,
vbillcode varchar(40) null 
/*请购单号*/,
dbilldate char(19) null 
/*请购日期*/,
bsctype char(1) null 
/*委外*/,
fpraysource integer null 
/*请购来源*/,
vtrantypecode varchar(20) null 
/*请购类型编码*/,
pk_planpsn varchar(20) null 
/*计划员*/,
pk_plandept varchar(20) null 
/*计划部门最新版本*/,
pk_plandept_v varchar(20) null 
/*计划部门*/,
bdirecttransit char(1) null 
/*直运*/,
ntotalastnum decimal(28,8) null 
/*总数量*/,
ntotaltaxmny decimal(28,8) null 
/*本币价税合计*/,
ccurrencyid varchar(20) null 
/*本币币种*/,
nversion integer null 
/*版本号*/,
vdef1 varchar(101) null 
/*自定义项1*/,
vdef2 varchar(101) null 
/*自定义项2*/,
vdef3 varchar(101) null 
/*自定义项3*/,
vdef4 varchar(101) null 
/*自定义项4*/,
vdef5 varchar(101) null 
/*自定义项5*/,
vdef6 varchar(101) null 
/*自定义项6*/,
vdef7 varchar(101) null 
/*自定义项7*/,
vdef8 varchar(101) null 
/*自定义项8*/,
vdef9 varchar(101) null 
/*自定义项9*/,
vdef10 varchar(101) null 
/*自定义项10*/,
vdef11 varchar(101) null 
/*自定义项11*/,
vdef12 varchar(101) null 
/*自定义项12*/,
vdef13 varchar(101) null 
/*自定义项13*/,
vdef14 varchar(101) null 
/*自定义项14*/,
vdef15 varchar(101) null 
/*自定义项15*/,
vdef16 varchar(101) null 
/*自定义项16*/,
vdef17 varchar(101) null 
/*自定义项17*/,
vdef18 varchar(101) null 
/*自定义项18*/,
vdef19 varchar(101) null 
/*自定义项19*/,
vdef20 varchar(101) null 
/*自定义项20*/,
vmemo varchar(181) null 
/*备注*/,
fbillstatus integer null 
/*单据状态*/,
creator varchar(20) null 
/*创建人*/,
creationtime char(19) null 
/*创建时间*/,
billmaker varchar(20) null 
/*制单人*/,
dmakedate char(19) null 
/*制单日期*/,
approver varchar(20) null 
/*审批人*/,
taudittime varchar(19) null 
/*审批日期*/,
creviseoperid varchar(20) null 
/*修订人*/,
trevisiontime varchar(19) null 
/*修订日期*/,
modifier varchar(20) null 
/*最后修改人*/,
modifiedtime char(19) null 
/*最后修改时间*/,
iprintcount integer null 
/*打印次数*/,
bislatest char(1) null default 'Y' 
/*最新版本*/,
pk_srcpraybill varchar(20) null 
/*请购单最新版本*/,
ctrantypeid varchar(20) null 
/*请购类型*/,
 constraint pk_po_praybill primary key (pk_praybill),
 ts char(19) null,
dr smallint null default 0
)
;

