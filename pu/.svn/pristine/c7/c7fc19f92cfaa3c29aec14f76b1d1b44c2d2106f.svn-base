/* tablename: 期初暂估单明细 */
create table po_initialest_b (pk_initialest_b varchar2(20) not null 
/*期初暂估单明细*/,
pk_org varchar2(20) null 
/*财务组织最新版本*/,
pk_org_v varchar2(20) null 
/*财务组织*/,
pk_group varchar2(20) null 
/*所属集团*/,
pk_apfinanceorg varchar2(20) null 
/*应付组织最新版本*/,
pk_apfinanceorg_v varchar2(20) null 
/*应付组织*/,
crowno varchar2(20) null 
/*行号*/,
pk_material varchar2(20) null 
/*物料VID*/,
pk_srcmaterial varchar2(20) null 
/*物料OID*/,
castunitid varchar2(20) null 
/*单位*/,
nastnum number(28,8) null 
/*数量*/,
cunitid varchar2(20) null 
/*主单位*/,
nnum number(28,8) null 
/*主数量*/,
vchangerate varchar2(60) null 
/*换算率*/,
ftaxtypeflag integer default 1 null 
/*扣税类别*/,
ntaxrate number(9,6) null 
/*税率*/,
vordertrantype varchar2(20) null 
/*订单交易类型*/,
pk_order varchar2(20) null 
/*订单ID*/,
pk_order_b varchar2(20) null 
/*订单行ID*/,
vordercode varchar2(40) null 
/*订单号*/,
corderrowno varchar2(20) null 
/*订单行号*/,
vctcode varchar2(40) null 
/*合同号*/,
vbatchcode varchar2(40) null 
/*批次号*/,
pk_batchcode varchar2(20) null 
/*批次档案*/,
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
bsettlefinish char(1) default 'N' null 
/*是否结算完成*/,
naccsettlenum number(28,8) null 
/*累计结算数量*/,
naccwashmny number(28,8) null 
/*累计冲减暂估金额*/,
bestimateap char(1) default 'N' null 
/*暂估应付标志*/,
naccinvoicenum number(28,8) null 
/*累计开票数量*/,
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
cproductorid varchar2(20) null 
/*生产厂商*/,
cprojectid varchar2(20) null 
/*项目*/,
norigprice number(28,8) null 
/*主无税单价*/,
norigtaxprice number(28,8) null 
/*主含税单价*/,
nprice number(28,8) null 
/*主本币无税价*/,
ntaxprice number(28,8) null 
/*主本币含税价*/,
nastorigprice number(28,8) null 
/*无税单价*/,
norigmny number(28,8) null 
/*无税金额*/,
nastorigtaxprice number(28,8) null 
/*含税单价*/,
norigtaxmny number(28,8) null 
/*价税合计*/,
nastprice number(28,8) null 
/*本币无税单价*/,
nmny number(28,8) null 
/*本币无税金额*/,
nasttaxprice number(28,8) null 
/*本币含税单价*/,
ntax number(28,8) null 
/*税额*/,
ntaxmny number(28,8) null 
/*本币价税合计*/,
naccgoodssettlemny number(28,8) null 
/*累计货物结算金额*/,
naccsettlemny number(28,8) null 
/*累计结算金额*/,
naccfeesettlemny number(28,8) null 
/*累计费用结算金额*/,
vbmemo varchar2(181) null 
/*备注*/,
csourceid varchar2(20) null 
/*来源单据*/,
csourcebid varchar2(20) null 
/*来源单据明细*/,
vsourcetrantype varchar2(20) null 
/*来源交易类型*/,
vsourcecode varchar2(40) null 
/*来源单据号*/,
vsourcerowno varchar2(20) null 
/*来源单据行号*/,
csourcetypecode varchar2(20) null 
/*来源单据类型*/,
pk_initialest char(20) not null 
/*期初暂估单_主键*/,
casscustid varchar2(20) null 
/*客户*/,
baffectcost char(1) default 'Y' null 
/*影响成本标志*/,
csendcountryid varchar2(20) null 
/*发货国家/地区*/,
crececountryid varchar2(20) null 
/*收货国家/地区*/,
ctaxcountryid varchar2(20) null 
/*报税国家/地区*/,
fbuysellflag integer null 
/*购销类型*/,
btriatradeflag char(1) null 
/*三角贸易*/,
ctaxcodeid varchar2(20) null 
/*税码*/,
ncaltaxmny number(28,8) null 
/*计税金额*/,
nnosubtaxrate number(28,8) null 
/*不可抵扣税率*/,
nnosubtax number(28,8) null 
/*不可抵扣税额*/,
ncalcostmny number(28,8) null 
/*计成本金额*/,
bopptaxflag char(1) null 
/*逆向征税*/,
nestcalcostprice number(28,8) null 
/*计成本单价*/,
pk_apliabcenter varchar2(20) null 
/*结算利润中心最新版本*/,
pk_apliabcenter_v varchar2(20) null 
/*结算利润中心*/,
baffectpccost char(1) null 
/*影响利润中心成本*/,
cffileid varchar2(20) null 
/*特征码*/,
 constraint pk_po_initialest_b primary key (pk_initialest_b),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: 期初暂估单 */
create table po_initialest (pk_initialest char(20) not null 
/*期初暂估单*/,
pk_group varchar2(20) null 
/*所属集团*/,
pk_org varchar2(20) null 
/*财务组织最新版本*/,
pk_org_v varchar2(20) null 
/*财务组织*/,
pk_costregion varchar2(20) null 
/*成本域*/,
vtrantypecode varchar2(20) null 
/*期初暂估类型编码*/,
vbillcode varchar2(40) null 
/*期初暂估单号*/,
pk_stockorg varchar2(20) null 
/*库存组织最新版本*/,
pk_stockorg_v varchar2(20) null 
/*库存组织*/,
pk_supplier varchar2(20) null 
/*供应商*/,
corigcurrencyid varchar2(20) null 
/*币种*/,
nexchangerate number(28,8) null 
/*折本汇率*/,
ccurrencyid varchar2(20) null 
/*本位币*/,
pk_stordoc varchar2(20) null 
/*仓库*/,
pk_bizpsn varchar2(20) null 
/*采购员*/,
pk_managepsn varchar2(20) null 
/*保管员*/,
pk_purchaseorg_v varchar2(20) null 
/*采购组织*/,
pk_purchaseorg varchar2(20) null 
/*采购组织最新版本*/,
pk_dept varchar2(20) null 
/*采购部门最新版本*/,
pk_dept_v varchar2(20) null 
/*采购部门*/,
dbilldate char(19) null 
/*入库日期*/,
billmaker varchar2(20) null 
/*制单人*/,
dmakedate char(19) null 
/*制单日期*/,
creationtime char(19) null 
/*创建时间*/,
approver varchar2(20) null 
/*审批人*/,
taudittime varchar2(19) null 
/*审批日期*/,
modifier varchar2(20) null 
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
pk_busitype varchar2(20) null 
/*业务流程*/,
vmemo varchar2(181) null 
/*备注*/,
fbillstatus integer default 0 null 
/*单据状态*/,
creator varchar2(20) null 
/*创建人*/,
ntotalastnum number(28,8) null 
/*总数量*/,
ntotalorigmny number(28,8) null 
/*总价???合计*/,
bnormpur char(1) null 
/*是否普通采购*/,
ctrantypeid varchar2(20) null 
/*期初暂估类型*/,
 constraint pk_po_initialest primary key (pk_initialest),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

