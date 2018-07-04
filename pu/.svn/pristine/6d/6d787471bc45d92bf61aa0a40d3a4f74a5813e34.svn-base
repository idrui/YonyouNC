/* tablename: 委托加工入财务 */
create table po_subcontinfi (pk_stockps char(20) not null 
/*委托加工入主键*/,
pk_group varchar2(20) default '~' null 
/*集团*/,
pk_org varchar2(20) default '~' null 
/*库存组织*/,
pk_org_v varchar2(20) default '~' null 
/*库存组织版本*/,
pk_corp varchar2(20) default '~' null 
/*公司最新版*/,
pk_corp_v varchar2(20) default '~' null 
/*公司*/,
cbilltypecode varchar2(20) null 
/*单据类型编码*/,
vtrantypecode varchar2(20) default '~' null 
/*交易类型编码*/,
pk_busitype varchar2(20) default '~' null 
/*业务流程*/,
vbillcode varchar2(40) null 
/*入库单号*/,
dbilldate char(19) null 
/*入库日期*/,
pk_stordoc varchar2(20) default '~' null 
/*仓库*/,
pk_dept varchar2(20) default '~' null 
/*部门原始信息*/,
pk_dept_v varchar2(20) default '~' null 
/*部门*/,
pk_psndoc varchar2(20) default '~' null 
/*业务员*/,
cwhsmanagerid varchar2(20) default '~' null 
/*库管员*/,
billmaker varchar2(20) default '~' null 
/*制单人*/,
vnote varchar2(181) null 
/*备注*/,
freplenishflag char(1) null 
/*退货标志*/,
modifier varchar2(20) default '~' null 
/*最后修改人*/,
modifiedtime char(19) null 
/*最后修改时间*/,
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
creationtime char(19) null 
/*创建时间*/,
creator varchar2(20) default '~' null 
/*创建人*/,
bnormpur char(1) default 'Y' null 
/*是否普通采购*/,
ctrantypeid varchar2(20) default '~' null 
/*交易类型*/,
approver varchar2(20) null 
/*签字人*/,
taudittime char(19) null 
/*签字日期*/,
 constraint pk_po_subcontinfi primary key (pk_stockps),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: 委托加工入财务明细 */
create table po_subcontinfi_b (pk_stockps_b char(20) not null 
/*委托加工入明细主键*/,
crowno varchar2(20) null 
/*行号*/,
pk_group varchar2(20) default '~' null 
/*集团*/,
pk_org varchar2(20) default '~' null 
/*库存组织*/,
pk_org_v varchar2(20) default '~' null 
/*库存组织版本*/,
pk_financeorg varchar2(20) default '~' null 
/*结算财务组织*/,
pk_financeorg_v varchar2(20) default '~' null 
/*结算财务组织版本*/,
pk_corp varchar2(20) default '~' null 
/*公司最新版本*/,
pk_corp_v varchar2(20) default '~' null 
/*公司*/,
pk_apfinanceorg varchar2(20) default '~' null 
/*应付财务组织*/,
pk_apfinanceorg_v varchar2(20) default '~' null 
/*应付财务组织版本*/,
pk_costregion varchar2(20) default '~' null 
/*成本域*/,
pk_purchaseorg varchar2(20) default '~' null 
/*采购组织*/,
pk_purchaseorg_v varchar2(20) default '~' null 
/*采购组织版本*/,
pk_apliabcenter varchar2(20) default '~' null 
/*利润中心*/,
pk_apliabcenter_v varchar2(20) default '~' null 
/*利润中心版本*/,
pk_srcmaterial varchar2(20) default '~' null 
/*物料*/,
pk_material varchar2(20) default '~' null 
/*物料编码*/,
pk_batchcode varchar2(20) null 
/*批次档案*/,
vbatchcode varchar2(40) null 
/*批次号*/,
dbizdate char(19) null 
/*业务日期*/,
dtocostapdate char(19) null 
/*暂估日期*/,
castunitid varchar2(20) default '~' null 
/*单位*/,
ninnum number(28,8) null 
/*入库主数量*/,
ninassistnum number(28,8) null 
/*实入辅数量*/,
cprojectid varchar2(20) default '~' null 
/*项目*/,
vchangerate varchar2(60) null 
/*换算率*/,
csourcetypecode varchar2(20) default '~' null 
/*来源单据类型*/,
vsourcetrantype varchar2(20) default '~' null 
/*来源交易类型*/,
csourceid varchar2(20) null 
/*来源单据表头*/,
csourcebid varchar2(20) null 
/*来源单据表体*/,
vsourcecode varchar2(40) null 
/*来源单据号*/,
vsourcerowno varchar2(20) null 
/*来源单据行号*/,
cfirsttypecode varchar2(20) default '~' null 
/*源头单据类型*/,
vfirsttrantype varchar2(20) default '~' null 
/*源头单据交易类型*/,
cfirstid varchar2(20) null 
/*源头单据表头*/,
cfirstbid varchar2(20) null 
/*源头单据表体*/,
vfirstcode varchar2(40) null 
/*源头单据号*/,
vfirstrowno varchar2(20) null 
/*源头单据行号*/,
dfirstbilldate varchar2(19) null 
/*源头单据制单日期*/,
vnotebody varchar2(181) null 
/*行备注*/,
vordertrantypecode varchar2(50) null 
/*订单交易类型*/,
pk_order varchar2(20) null 
/*订单*/,
pk_order_b varchar2(20) null 
/*订单明细*/,
vordercode varchar2(50) null 
/*订单号*/,
cproductorid varchar2(20) null 
/*生产厂商*/,
cstateid varchar2(20) null 
/*库存状态*/,
cqualitylevelid varchar2(20) default '~' null 
/*质量等级*/,
pk_supplier varchar2(20) default '~' null 
/*供应商*/,
vctcode varchar2(50) null 
/*采购合同号*/,
cunitid varchar2(20) default '~' null 
/*主单位*/,
ncostprice number(28,8) null 
/*单价*/,
ncostmny number(28,8) null 
/*金额*/,
nplannedprice number(28,8) null 
/*计划单价*/,
nplannedmny number(28,8) null 
/*计划金额*/,
ccurrencyid varchar2(20) null 
/*本位币*/,
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
vproductbillcode varchar2(40) null 
/*生产订单号*/,
vproductbatch varchar2(40) null 
/*生产批号*/,
cworkcenterid varchar2(20) default '~' null 
/*工作中心*/,
ftoiaflag integer default 0 null 
/*传成本标志*/,
baffectcost char(1) default 'Y' null 
/*影响成本标志*/,
bsettlefinish char(1) null 
/*是否结算完成*/,
naccumsettlenum number(28,8) null 
/*累计结算数量*/,
naccestcostwashmny number(28,8) null 
/*累计回冲暂估成本金额*/,
naccgoodssettlemny number(28,8) null 
/*累计货物结算金额*/,
naccsettlemny number(28,8) null 
/*累计结算金额*/,
naccfeesettlemny number(28,8) null 
/*累计费用结算金额*/,
pk_stockps char(20) not null 
/*委托加工入财务_主键*/,
casscustid varchar2(20) null 
/*客户*/,
cffileid varchar2(20) null 
/*特征码*/,
foutputtype integer null 
/*产品类型*/,
 constraint pk_o_subcontinfi_b primary key (pk_stockps_b),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

