/* tablename: 期初暂估单明细 */
create table po_initialest_b (
pk_initialest_b nvarchar(20) not null 
/*期初暂估单明细*/,
pk_org nvarchar(20) null 
/*财务组织最新版本*/,
pk_org_v nvarchar(20) null 
/*财务组织*/,
pk_group nvarchar(20) null 
/*所属集团*/,
pk_apfinanceorg nvarchar(20) null 
/*应付组织最新版本*/,
pk_apfinanceorg_v nvarchar(20) null 
/*应付组织*/,
crowno nvarchar(20) null 
/*行号*/,
pk_material nvarchar(20) null 
/*物料VID*/,
pk_srcmaterial nvarchar(20) null 
/*物料OID*/,
castunitid nvarchar(20) null 
/*单位*/,
nastnum decimal(28,8) null 
/*数量*/,
cunitid nvarchar(20) null 
/*主单位*/,
nnum decimal(28,8) null 
/*主数量*/,
vchangerate nvarchar(60) null 
/*换算率*/,
ftaxtypeflag int null default 1 
/*扣税类别*/,
ntaxrate decimal(9,6) null 
/*税率*/,
vordertrantype nvarchar(20) null 
/*订单交易类型*/,
pk_order nvarchar(20) null 
/*订单ID*/,
pk_order_b nvarchar(20) null 
/*订单行ID*/,
vordercode nvarchar(40) null 
/*订单号*/,
corderrowno nvarchar(20) null 
/*订单行号*/,
vctcode nvarchar(40) null 
/*合同号*/,
vbatchcode nvarchar(40) null 
/*批次号*/,
pk_batchcode nvarchar(20) null 
/*批次档案*/,
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
bsettlefinish nchar(1) null default 'N' 
/*是否结算完成*/,
naccsettlenum decimal(28,8) null 
/*累计结算数量*/,
naccwashmny decimal(28,8) null 
/*累计冲减暂估金额*/,
bestimateap nchar(1) null default 'N' 
/*暂估应付标志*/,
naccinvoicenum decimal(28,8) null 
/*累计开票数量*/,
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
cproductorid nvarchar(20) null 
/*生产厂商*/,
cprojectid nvarchar(20) null 
/*项目*/,
norigprice decimal(28,8) null 
/*主无税单价*/,
norigtaxprice decimal(28,8) null 
/*主含税单价*/,
nprice decimal(28,8) null 
/*主本币无税价*/,
ntaxprice decimal(28,8) null 
/*主本币含税价*/,
nastorigprice decimal(28,8) null 
/*无税单价*/,
norigmny decimal(28,8) null 
/*无税金额*/,
nastorigtaxprice decimal(28,8) null 
/*含税单价*/,
norigtaxmny decimal(28,8) null 
/*价税合计*/,
nastprice decimal(28,8) null 
/*本币无税单价*/,
nmny decimal(28,8) null 
/*本币无税金额*/,
nasttaxprice decimal(28,8) null 
/*本币含税单价*/,
ntax decimal(28,8) null 
/*税额*/,
ntaxmny decimal(28,8) null 
/*本币价税合计*/,
naccgoodssettlemny decimal(28,8) null 
/*累计货物结算金额*/,
naccsettlemny decimal(28,8) null 
/*累计结算金额*/,
naccfeesettlemny decimal(28,8) null 
/*累计费用结算金额*/,
vbmemo nvarchar(181) null 
/*备注*/,
csourceid nvarchar(20) null 
/*来源单据*/,
csourcebid nvarchar(20) null 
/*来源单据明细*/,
vsourcetrantype nvarchar(20) null 
/*来源交易类型*/,
vsourcecode nvarchar(40) null 
/*来源单据号*/,
vsourcerowno nvarchar(20) null 
/*来源单据行号*/,
csourcetypecode nvarchar(20) null 
/*来源单据类型*/,
pk_initialest nchar(20) not null 
/*期初暂估单_主键*/,
casscustid nvarchar(20) null 
/*客户*/,
baffectcost nchar(1) null default 'Y' 
/*影响成本标志*/,
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
ctaxcodeid nvarchar(20) null 
/*税码*/,
ncaltaxmny decimal(28,8) null 
/*计税金额*/,
nnosubtaxrate decimal(28,8) null 
/*不可抵扣税率*/,
nnosubtax decimal(28,8) null 
/*不可抵扣税额*/,
ncalcostmny decimal(28,8) null 
/*计成本金额*/,
bopptaxflag nchar(1) null 
/*逆向征税*/,
nestcalcostprice decimal(28,8) null 
/*计成本单价*/,
pk_apliabcenter nvarchar(20) null 
/*结算利润中心最新版本*/,
pk_apliabcenter_v nvarchar(20) null 
/*结算利润中心*/,
baffectpccost nchar(1) null 
/*影响利润中心成本*/,
cffileid nvarchar(20) null 
/*特征码*/,
 constraint pk_po_initialest_b primary key (pk_initialest_b),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 期初暂估单 */
create table po_initialest (
pk_initialest nchar(20) not null 
/*期初暂估单*/,
pk_group nvarchar(20) null 
/*所属集团*/,
pk_org nvarchar(20) null 
/*财务组织最新版本*/,
pk_org_v nvarchar(20) null 
/*财务组织*/,
pk_costregion nvarchar(20) null 
/*成本域*/,
vtrantypecode nvarchar(20) null 
/*期初暂估类型编码*/,
vbillcode nvarchar(40) null 
/*期初暂估单号*/,
pk_stockorg nvarchar(20) null 
/*库存组织最新版本*/,
pk_stockorg_v nvarchar(20) null 
/*库存组织*/,
pk_supplier nvarchar(20) null 
/*供应商*/,
corigcurrencyid nvarchar(20) null 
/*币种*/,
nexchangerate decimal(28,8) null 
/*折本汇率*/,
ccurrencyid nvarchar(20) null 
/*本位币*/,
pk_stordoc nvarchar(20) null 
/*仓库*/,
pk_bizpsn nvarchar(20) null 
/*采购员*/,
pk_managepsn nvarchar(20) null 
/*保管员*/,
pk_purchaseorg_v nvarchar(20) null 
/*采购组织*/,
pk_purchaseorg nvarchar(20) null 
/*采购组织最新版本*/,
pk_dept nvarchar(20) null 
/*采购部门最新版本*/,
pk_dept_v nvarchar(20) null 
/*采购部门*/,
dbilldate nchar(19) null 
/*入库日期*/,
billmaker nvarchar(20) null 
/*制单人*/,
dmakedate nchar(19) null 
/*制单日期*/,
creationtime nchar(19) null 
/*创建时间*/,
approver nvarchar(20) null 
/*审批人*/,
taudittime nvarchar(19) null 
/*审批日期*/,
modifier nvarchar(20) null 
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
pk_busitype nvarchar(20) null 
/*业务流程*/,
vmemo nvarchar(181) null 
/*备注*/,
fbillstatus int null default 0 
/*单据状态*/,
creator nvarchar(20) null 
/*创建人*/,
ntotalastnum decimal(28,8) null 
/*总数量*/,
ntotalorigmny decimal(28,8) null 
/*总价???合计*/,
bnormpur nchar(1) null 
/*是否普通采购*/,
ctrantypeid nvarchar(20) null 
/*期初暂估类型*/,
 constraint pk_po_initialest primary key (pk_initialest),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

