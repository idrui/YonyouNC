/* tablename: 请购单子表 */
create table po_praybill_b (pk_praybill_b char(20) not null 
/*请购单子表*/,
pk_group varchar2(20) null 
/*所属???团*/,
pk_org varchar2(20) null 
/*库存组织最新版本*/,
pk_org_v varchar2(20) null 
/*库存组织*/,
crowno varchar2(20) null 
/*行号*/,
pk_srcmaterial varchar2(20) null 
/*物料信息*/,
pk_material varchar2(20) null 
/*物料最新版本*/,
castunitid varchar2(20) null 
/*单位*/,
nastnum number(28,8) null 
/*数量*/,
vchangerate varchar2(60) null 
/*换算率*/,
cunitid varchar2(20) null 
/*主单位*/,
nnum number(28,8) null 
/*主数量*/,
pk_reqdept varchar2(20) null 
/*需求部门最新版本*/,
pk_reqdept_v varchar2(20) null 
/*需求部门*/,
ntaxprice number(28,8) null 
/*主本币含税单价*/,
ntaxmny number(28,8) null 
/*本币价税合计*/,
dreqdate varchar2(19) null 
/*需求日期*/,
dsuggestdate varchar2(19) null 
/*建议订货日期*/,
pk_purchaseorg varchar2(20) null 
/*采购组织最新版本*/,
pk_purchaseorg_v varchar2(20) null 
/*采购组织*/,
bcanpurchaseorgedit char(1) null 
/*采购组织可编辑*/,
cordertrantypecode varchar2(20) null 
/*订单类型*/,
pk_suggestsupplier varchar2(20) null 
/*建议供应商*/,
pk_reqstor varchar2(20) null 
/*需求仓库*/,
csourcetypecode varchar2(20) null 
/*来源单据类型*/,
csourceid varchar2(20) null 
/*来源单据标识*/,
csourcebid varchar2(20) null 
/*来源单据分录标识*/,
vsourcecode varchar2(40) null 
/*来源单据号*/,
vsourcerowno varchar2(20) null 
/*来源单据行号*/,
vsrctrantypecode varchar2(20) null 
/*来源交易类型*/,
cfirsttypecode varchar2(20) null 
/*源头单据类型*/,
cfirstid varchar2(20) null 
/*源头单据标识*/,
cfirstbid varchar2(20) null 
/*源头单据分录标识*/,
vfirstcode varchar2(40) null 
/*源头单据号*/,
vfirstrowno varchar2(20) null 
/*源头单据行号*/,
vfirsttrantype varchar2(20) null 
/*源头交易类型*/,
cprojectid varchar2(20) null 
/*项目*/,
cproductorid varchar2(20) null 
/*生产厂商*/,
pk_batchcode varchar2(20) null 
/*批次档案*/,
vbatchcode varchar2(40) null 
/*批次号*/,
pk_employee varchar2(20) null 
/*采购员*/,
vfree1 varchar2(101) null 
/*自由辅助属性1*/,
vfree2 varchar2(101) null 
/*自由辅助属性2*/,
vfree3 varchar2(101) null 
/*自由辅助属性3*/,
vfree4 varchar2(101) null 
/*自由辅助属性4*/,
vfree5 varchar2(101) null 
/*自由辅助属性5*/,
vfree6 varchar2(101) null 
/*自由辅助属性6*/,
vfree7 varchar2(101) null 
/*自由辅助属性7*/,
vfree8 varchar2(101) null 
/*自由辅助属性8*/,
vfree9 varchar2(101) null 
/*自由辅助属性9*/,
vfree10 varchar2(101) null 
/*自由辅助属性10*/,
vbdef1 varchar2(101) null 
/*自定义项1*/,
vbdef2 varchar2(101) null 
/*自定义项2*/,
vbdef3 varchar2(101) null 
/*自定义项3*/,
vbdef4 varchar2(101) null 
/*自定义项4*/,
vbdef5 varchar2(101) null 
/*自定义项5*/,
vbdef6 varchar2(101) null 
/*自定义项6*/,
vbdef7 varchar2(101) null 
/*自定义项7*/,
vbdef8 varchar2(101) null 
/*自定义项8*/,
vbdef9 varchar2(101) null 
/*自定义项9*/,
vbdef10 varchar2(101) null 
/*自定义项10*/,
vbdef11 varchar2(101) null 
/*自定义项11*/,
vbdef12 varchar2(101) null 
/*自定义项12*/,
vbdef13 varchar2(101) null 
/*自定义项13*/,
vbdef14 varchar2(101) null 
/*自定义项14*/,
vbdef15 varchar2(101) null 
/*自定义项15*/,
vbdef16 varchar2(101) null 
/*自定义项16*/,
vbdef17 varchar2(101) null 
/*自定义项17*/,
vbdef18 varchar2(101) null 
/*自定义项18*/,
vbdef19 varchar2(101) null 
/*自定义项19*/,
vbdef20 varchar2(101) null 
/*自定义项20*/,
vbmemo varchar2(181) null 
/*备注*/,
naccumulatenum number(28,8) null 
/*累计订货主数量*/,
ngenct integer null 
/*生成合同次数*/,
npriceauditbill integer null 
/*生成价格审批单次数*/,
nquotebill integer null 
/*生成询报价单次数*/,
pk_product varchar2(20) null 
/*产品*/,
pk_product_v varchar2(20) null 
/*产品版本*/,
pk_customer varchar2(20) null 
/*销售客户*/,
browclose char(1) null 
/*行关闭*/,
dbilldate char(19) null 
/*请购日期*/,
bpublishtoec char(1) null 
/*发布到电子商务*/,
pk_praybill char(20) not null 
/*请购单主表_主键*/,
casscustid varchar2(20) null 
/*客户*/,
cprojecttaskid varchar2(20) null 
/*项目任务*/,
bisgensaorder char(1) null 
/*已生成总括订单*/,
pk_reqstoorg_v varchar2(20) null 
/*原始需求库存组织*/,
pk_reqstoorg varchar2(20) null 
/*原始需求库存组织最新版本*/,
cffileid varchar2(20) null 
/*特征码*/,
bisarrange char(1) null 
/*已安排*/,
 constraint pk_po_praybill_b primary key (pk_praybill_b),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: 请购单主表 */
create table po_praybill (pk_praybill char(20) not null 
/*请购单*/,
pk_group varchar2(20) null 
/*所属集团*/,
pk_org varchar2(20) null 
/*库存组织最新版本*/,
pk_org_v varchar2(20) null 
/*库存组织*/,
vbillcode varchar2(40) null 
/*请购单号*/,
dbilldate char(19) null 
/*请购日期*/,
bsctype char(1) null 
/*委外*/,
fpraysource integer null 
/*请购来源*/,
vtrantypecode varchar2(20) null 
/*请购类型编码*/,
pk_planpsn varchar2(20) null 
/*计划员*/,
pk_plandept varchar2(20) null 
/*计划部门最新版本*/,
pk_plandept_v varchar2(20) null 
/*计划部门*/,
bdirecttransit char(1) null 
/*直运*/,
ntotalastnum number(28,8) null 
/*总数量*/,
ntotaltaxmny number(28,8) null 
/*本币价税合计*/,
ccurrencyid varchar2(20) null 
/*本币币种*/,
nversion integer null 
/*版本号*/,
vdef1 varchar2(101) null 
/*自定义项1*/,
vdef2 varchar2(101) null 
/*自定义项2*/,
vdef3 varchar2(101) null 
/*自定义项3*/,
vdef4 varchar2(101) null 
/*自定义项4*/,
vdef5 varchar2(101) null 
/*自定义项5*/,
vdef6 varchar2(101) null 
/*自定义项6*/,
vdef7 varchar2(101) null 
/*自定义项7*/,
vdef8 varchar2(101) null 
/*自定义项8*/,
vdef9 varchar2(101) null 
/*自定义项9*/,
vdef10 varchar2(101) null 
/*自定义项10*/,
vdef11 varchar2(101) null 
/*自定义项11*/,
vdef12 varchar2(101) null 
/*自定义项12*/,
vdef13 varchar2(101) null 
/*自定义项13*/,
vdef14 varchar2(101) null 
/*自定义项14*/,
vdef15 varchar2(101) null 
/*自定义项15*/,
vdef16 varchar2(101) null 
/*自定义项16*/,
vdef17 varchar2(101) null 
/*自定义项17*/,
vdef18 varchar2(101) null 
/*自定义项18*/,
vdef19 varchar2(101) null 
/*自定义项19*/,
vdef20 varchar2(101) null 
/*自定义项20*/,
vmemo varchar2(181) null 
/*备注*/,
fbillstatus integer null 
/*单据状态*/,
creator varchar2(20) null 
/*创建人*/,
creationtime char(19) null 
/*创建时间*/,
billmaker varchar2(20) null 
/*制单人*/,
dmakedate char(19) null 
/*制单日期*/,
approver varchar2(20) null 
/*审批人*/,
taudittime varchar2(19) null 
/*审批日期*/,
creviseoperid varchar2(20) null 
/*修订人*/,
trevisiontime varchar2(19) null 
/*修订日期*/,
modifier varchar2(20) null 
/*最后修改人*/,
modifiedtime char(19) null 
/*最后修改时间*/,
iprintcount integer null 
/*打印次数*/,
bislatest char(1) default 'Y' null 
/*最新版本*/,
pk_srcpraybill varchar2(20) null 
/*请购单最新版本*/,
ctrantypeid varchar2(20) null 
/*请购类型*/,
 constraint pk_po_praybill primary key (pk_praybill),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

