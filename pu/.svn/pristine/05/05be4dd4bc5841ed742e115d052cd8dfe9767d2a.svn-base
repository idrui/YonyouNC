/* tablename: 委托加工入财务 */
create table po_subcontinfi (
pk_stockps nchar(20) not null 
/*委托加工入主键*/,
pk_group nvarchar(20) null default '~' 
/*集团*/,
pk_org nvarchar(20) null default '~' 
/*库存组织*/,
pk_org_v nvarchar(20) null default '~' 
/*库存组织版本*/,
pk_corp nvarchar(20) null default '~' 
/*公司最新版*/,
pk_corp_v nvarchar(20) null default '~' 
/*公司*/,
cbilltypecode nvarchar(20) null 
/*单据类型编码*/,
vtrantypecode nvarchar(20) null default '~' 
/*交易类型编码*/,
pk_busitype nvarchar(20) null default '~' 
/*业务流程*/,
vbillcode nvarchar(40) null 
/*入库单号*/,
dbilldate nchar(19) null 
/*入库日期*/,
pk_stordoc nvarchar(20) null default '~' 
/*仓库*/,
pk_dept nvarchar(20) null default '~' 
/*部门原始信息*/,
pk_dept_v nvarchar(20) null default '~' 
/*部门*/,
pk_psndoc nvarchar(20) null default '~' 
/*业务员*/,
cwhsmanagerid nvarchar(20) null default '~' 
/*库管员*/,
billmaker nvarchar(20) null default '~' 
/*制单人*/,
vnote nvarchar(181) null 
/*备注*/,
freplenishflag nchar(1) null 
/*退货标志*/,
modifier nvarchar(20) null default '~' 
/*最后修改人*/,
modifiedtime nchar(19) null 
/*最后修改时间*/,
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
creationtime nchar(19) null 
/*创建时间*/,
creator nvarchar(20) null default '~' 
/*创建人*/,
bnormpur nchar(1) null default 'Y' 
/*是否普通采购*/,
ctrantypeid nvarchar(20) null default '~' 
/*交易类型*/,
approver nvarchar(20) null 
/*签字人*/,
taudittime nchar(19) null 
/*签字日期*/,
 constraint pk_po_subcontinfi primary key (pk_stockps),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 委托加工入财务明细 */
create table po_subcontinfi_b (
pk_stockps_b nchar(20) not null 
/*委托加工入明细主键*/,
crowno nvarchar(20) null 
/*行号*/,
pk_group nvarchar(20) null default '~' 
/*集团*/,
pk_org nvarchar(20) null default '~' 
/*库存组织*/,
pk_org_v nvarchar(20) null default '~' 
/*库存组织版本*/,
pk_financeorg nvarchar(20) null default '~' 
/*结算财务组织*/,
pk_financeorg_v nvarchar(20) null default '~' 
/*结算财务组织版本*/,
pk_corp nvarchar(20) null default '~' 
/*公司最新版本*/,
pk_corp_v nvarchar(20) null default '~' 
/*公司*/,
pk_apfinanceorg nvarchar(20) null default '~' 
/*应付财务组织*/,
pk_apfinanceorg_v nvarchar(20) null default '~' 
/*应付财务组织版本*/,
pk_costregion nvarchar(20) null default '~' 
/*成本域*/,
pk_purchaseorg nvarchar(20) null default '~' 
/*采购组织*/,
pk_purchaseorg_v nvarchar(20) null default '~' 
/*采购组织版本*/,
pk_apliabcenter nvarchar(20) null default '~' 
/*利润中心*/,
pk_apliabcenter_v nvarchar(20) null default '~' 
/*利润中心版本*/,
pk_srcmaterial nvarchar(20) null default '~' 
/*物料*/,
pk_material nvarchar(20) null default '~' 
/*物料编码*/,
pk_batchcode nvarchar(20) null 
/*批次档案*/,
vbatchcode nvarchar(40) null 
/*批次号*/,
dbizdate nchar(19) null 
/*业务日期*/,
dtocostapdate nchar(19) null 
/*暂估日期*/,
castunitid nvarchar(20) null default '~' 
/*单位*/,
ninnum decimal(28,8) null 
/*入库主数量*/,
ninassistnum decimal(28,8) null 
/*实入辅数量*/,
cprojectid nvarchar(20) null default '~' 
/*项目*/,
vchangerate nvarchar(60) null 
/*换算率*/,
csourcetypecode nvarchar(20) null default '~' 
/*来源单据类型*/,
vsourcetrantype nvarchar(20) null default '~' 
/*来源交易类型*/,
csourceid nvarchar(20) null 
/*来源单据表头*/,
csourcebid nvarchar(20) null 
/*来源单据表体*/,
vsourcecode nvarchar(40) null 
/*来源单据号*/,
vsourcerowno nvarchar(20) null 
/*来源单据行号*/,
cfirsttypecode nvarchar(20) null default '~' 
/*源头单据类型*/,
vfirsttrantype nvarchar(20) null default '~' 
/*源头单据交易类型*/,
cfirstid nvarchar(20) null 
/*源头单据表头*/,
cfirstbid nvarchar(20) null 
/*源头单据表体*/,
vfirstcode nvarchar(40) null 
/*源头单据号*/,
vfirstrowno nvarchar(20) null 
/*源头单据行号*/,
dfirstbilldate nvarchar(19) null 
/*源头单据制单日期*/,
vnotebody nvarchar(181) null 
/*行备注*/,
vordertrantypecode nvarchar(50) null 
/*订单交易类型*/,
pk_order nvarchar(20) null 
/*订单*/,
pk_order_b nvarchar(20) null 
/*订单明细*/,
vordercode nvarchar(50) null 
/*订单号*/,
cproductorid nvarchar(20) null 
/*生产厂商*/,
cstateid nvarchar(20) null 
/*库存状态*/,
cqualitylevelid nvarchar(20) null default '~' 
/*质量等级*/,
pk_supplier nvarchar(20) null default '~' 
/*供应商*/,
vctcode nvarchar(50) null 
/*采购合同号*/,
cunitid nvarchar(20) null default '~' 
/*主单位*/,
ncostprice decimal(28,8) null 
/*单价*/,
ncostmny decimal(28,8) null 
/*金额*/,
nplannedprice decimal(28,8) null 
/*计划单价*/,
nplannedmny decimal(28,8) null 
/*计划金额*/,
ccurrencyid nvarchar(20) null 
/*本位币*/,
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
vproductbillcode nvarchar(40) null 
/*生产订单号*/,
vproductbatch nvarchar(40) null 
/*生产批号*/,
cworkcenterid nvarchar(20) null default '~' 
/*工作中心*/,
ftoiaflag int null default 0 
/*传成本标志*/,
baffectcost nchar(1) null default 'Y' 
/*影响成本标志*/,
bsettlefinish nchar(1) null 
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
pk_stockps nchar(20) not null 
/*委托加工入财务_主键*/,
casscustid nvarchar(20) null 
/*客户*/,
cffileid nvarchar(20) null 
/*特征码*/,
foutputtype int null 
/*产品类型*/,
 constraint pk_o_subcontinfi_b primary key (pk_stockps_b),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

