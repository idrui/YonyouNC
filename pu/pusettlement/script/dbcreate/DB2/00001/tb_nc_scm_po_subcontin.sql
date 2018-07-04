/* tablename: 委托加工入财务 */
create table po_subcontinfi (pk_stockps char(20) not null 
/*委托加工入主键*/,
pk_group varchar(20) null default '~' 
/*集团*/,
pk_org varchar(20) null default '~' 
/*库存组织*/,
pk_org_v varchar(20) null default '~' 
/*库存组织版本*/,
pk_corp varchar(20) null default '~' 
/*公司最新版*/,
pk_corp_v varchar(20) null default '~' 
/*公司*/,
cbilltypecode varchar(20) null 
/*单据类型编码*/,
vtrantypecode varchar(20) null default '~' 
/*交易类型编码*/,
pk_busitype varchar(20) null default '~' 
/*业务流程*/,
vbillcode varchar(40) null 
/*入库单号*/,
dbilldate char(19) null 
/*入库日期*/,
pk_stordoc varchar(20) null default '~' 
/*仓库*/,
pk_dept varchar(20) null default '~' 
/*部门原始信息*/,
pk_dept_v varchar(20) null default '~' 
/*部门*/,
pk_psndoc varchar(20) null default '~' 
/*业务员*/,
cwhsmanagerid varchar(20) null default '~' 
/*库管员*/,
billmaker varchar(20) null default '~' 
/*制单人*/,
vnote varchar(181) null 
/*备注*/,
freplenishflag char(1) null 
/*退货标志*/,
modifier varchar(20) null default '~' 
/*最后修改人*/,
modifiedtime char(19) null 
/*最后修改时间*/,
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
creationtime char(19) null 
/*创建时间*/,
creator varchar(20) null default '~' 
/*创建人*/,
bnormpur char(1) null default 'Y' 
/*是否普通采购*/,
ctrantypeid varchar(20) null default '~' 
/*交易类型*/,
approver varchar(20) null 
/*签字人*/,
taudittime char(19) null 
/*签字日期*/,
 constraint pk_po_subcontinfi primary key (pk_stockps),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: 委托加工入财务明细 */
create table po_subcontinfi_b (pk_stockps_b char(20) not null 
/*委托加工入明细主键*/,
crowno varchar(20) null 
/*行号*/,
pk_group varchar(20) null default '~' 
/*集团*/,
pk_org varchar(20) null default '~' 
/*库存组织*/,
pk_org_v varchar(20) null default '~' 
/*库存组织版本*/,
pk_financeorg varchar(20) null default '~' 
/*结算财务组织*/,
pk_financeorg_v varchar(20) null default '~' 
/*结算财务组织版本*/,
pk_corp varchar(20) null default '~' 
/*公司最新版本*/,
pk_corp_v varchar(20) null default '~' 
/*公司*/,
pk_apfinanceorg varchar(20) null default '~' 
/*应付财务组织*/,
pk_apfinanceorg_v varchar(20) null default '~' 
/*应付财务组织版本*/,
pk_costregion varchar(20) null default '~' 
/*成本域*/,
pk_purchaseorg varchar(20) null default '~' 
/*采购组织*/,
pk_purchaseorg_v varchar(20) null default '~' 
/*采购组织版本*/,
pk_apliabcenter varchar(20) null default '~' 
/*利润中心*/,
pk_apliabcenter_v varchar(20) null default '~' 
/*利润中心版本*/,
pk_srcmaterial varchar(20) null default '~' 
/*物料*/,
pk_material varchar(20) null default '~' 
/*物料编码*/,
pk_batchcode varchar(20) null 
/*批次档案*/,
vbatchcode varchar(40) null 
/*批次号*/,
dbizdate char(19) null 
/*业务日期*/,
dtocostapdate char(19) null 
/*暂估日期*/,
castunitid varchar(20) null default '~' 
/*单位*/,
ninnum decimal(28,8) null 
/*入库主数量*/,
ninassistnum decimal(28,8) null 
/*实入辅数量*/,
cprojectid varchar(20) null default '~' 
/*项目*/,
vchangerate varchar(60) null 
/*换算率*/,
csourcetypecode varchar(20) null default '~' 
/*来源单据类型*/,
vsourcetrantype varchar(20) null default '~' 
/*来源交易类型*/,
csourceid varchar(20) null 
/*来源单据表头*/,
csourcebid varchar(20) null 
/*来源单据表体*/,
vsourcecode varchar(40) null 
/*来源单据号*/,
vsourcerowno varchar(20) null 
/*来源单据行号*/,
cfirsttypecode varchar(20) null default '~' 
/*源头单据类型*/,
vfirsttrantype varchar(20) null default '~' 
/*源头单据交易类型*/,
cfirstid varchar(20) null 
/*源头单据表头*/,
cfirstbid varchar(20) null 
/*源头单据表体*/,
vfirstcode varchar(40) null 
/*源头单据号*/,
vfirstrowno varchar(20) null 
/*源头单据行号*/,
dfirstbilldate varchar(19) null 
/*源头单据制单日期*/,
vnotebody varchar(181) null 
/*行备注*/,
vordertrantypecode varchar(50) null 
/*订单交易类型*/,
pk_order varchar(20) null 
/*订单*/,
pk_order_b varchar(20) null 
/*订单明细*/,
vordercode varchar(50) null 
/*订单号*/,
cproductorid varchar(20) null 
/*生产厂商*/,
cstateid varchar(20) null 
/*库存状态*/,
cqualitylevelid varchar(20) null default '~' 
/*质量等级*/,
pk_supplier varchar(20) null default '~' 
/*供应商*/,
vctcode varchar(50) null 
/*采购合同号*/,
cunitid varchar(20) null default '~' 
/*主单位*/,
ncostprice decimal(28,8) null 
/*单价*/,
ncostmny decimal(28,8) null 
/*金额*/,
nplannedprice decimal(28,8) null 
/*计划单价*/,
nplannedmny decimal(28,8) null 
/*计划金额*/,
ccurrencyid varchar(20) null 
/*本位币*/,
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
vproductbillcode varchar(40) null 
/*生产订单号*/,
vproductbatch varchar(40) null 
/*生产批号*/,
cworkcenterid varchar(20) null default '~' 
/*工作中心*/,
ftoiaflag integer null default 0 
/*传成本标志*/,
baffectcost char(1) null default 'Y' 
/*影响成本标志*/,
bsettlefinish char(1) null 
/*是否结算完成*/,
naccumsettlenum decimal(28,8) null 
/*累计结算数量*/,
naccestcostwashmny decimal(28,8) null 
/*累计回冲暂估成本金额*/,
naccgoodssettlemny decimal(28,8) null 
/*累计货物结算金额*/,
naccsettlemny decimal(28,8) null 
/*累计结算金额*/,
naccfeesettlemny decimal(28,8) null 
/*累计费用结算金额*/,
pk_stockps char(20) not null 
/*委托加工入财务_主键*/,
casscustid varchar(20) null 
/*客户*/,
cffileid varchar(20) null 
/*特征码*/,
foutputtype integer null 
/*产品类型*/,
 constraint pk_o_subcontinfi_b primary key (pk_stockps_b),
 ts char(19) null,
dr smallint null default 0
)
;

