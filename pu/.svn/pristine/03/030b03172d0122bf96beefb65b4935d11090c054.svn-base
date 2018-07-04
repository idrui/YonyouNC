/* tablename: 价格结算单 */
create table po_pricesettle (pk_pricesettle char(20) not null 
/*价格结算单*/,
pk_group varchar2(20) null 
/*集团*/,
pk_org varchar2(20) null 
/*采购组织最新版本*/,
pk_org_v varchar2(20) null 
/*采购组织*/,
vbillcode varchar2(40) null 
/*价格结算单号*/,
pk_storeorg varchar2(20) null 
/*库存组织最新版本*/,
pk_storeorg_v varchar2(20) null 
/*库存组织*/,
pk_supplier varchar2(20) null 
/*供应商*/,
pk_employee varchar2(20) null 
/*采购员*/,
pk_dept varchar2(20) null 
/*采购部门最新版本*/,
pk_dept_v varchar2(20) null 
/*采购部门*/,
dbilldate char(19) null 
/*单据日期*/,
vmemo varchar2(181) null 
/*备注*/,
ccurrencyid varchar2(20) null 
/*本币*/,
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
fbillstatus integer null 
/*单据状态*/,
creator varchar2(20) null 
/*创建人*/,
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
iprintcount integer null 
/*打印次数*/,
 constraint pk_po_pricesettle primary key (pk_pricesettle),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: 价格结算单子表 */
create table po_pricesettle_b (pk_pricesettle_b char(20) not null 
/*价格结算单子表*/,
pk_group varchar2(20) null 
/*集团*/,
pk_org varchar2(20) null 
/*采购组织最新版本*/,
pk_org_v varchar2(20) null 
/*采购组织*/,
crowno varchar2(50) null 
/*行号*/,
pk_material varchar2(20) null 
/*物料编码*/,
pk_srcmaterial varchar2(20) null 
/*物料最新版本*/,
pk_batchcode varchar2(20) null 
/*批次号档案*/,
vbatchcode varchar2(40) null 
/*批次号*/,
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
castunitid varchar2(20) null 
/*单位*/,
vchangerate varchar2(60) null 
/*换算率*/,
cunitid varchar2(20) null 
/*主单位*/,
nastarrvnum number(28,8) null 
/*到货数量*/,
narrvnum number(28,8) null 
/*到货主数量*/,
nastshouldinnum number(28,8) null 
/*质检后数量*/,
nshouldinnum number(28,8) null 
/*质检后主数量*/,
nastinnum number(28,8) null 
/*优质优价扣吨后数量*/,
ninnum number(28,8) null 
/*优质优价扣吨后主数量*/,
ftaxtypeflag integer null 
/*扣税类别*/,
ntaxrate number(28,8) null 
/*税率*/,
ncalqualprice number(28,8) null 
/*本币含税优质优价*/,
nbasetaxprice number(28,8) null 
/*本币含税基价*/,
nbaseprice number(28,8) null 
/*本币无税基价*/,
ntaxprice number(28,8) null 
/*本币含税单价*/,
nprice number(28,8) null 
/*本币无税单价*/,
ntaxmny number(28,8) null 
/*本币价税合计*/,
pk_stordoc varchar2(20) null 
/*收货仓库*/,
dbizdate varchar2(19) null 
/*入库日期*/,
pk_usedept varchar2(20) null 
/*使用部门最新版本*/,
pk_usedept_v varchar2(20) null 
/*使用部门*/,
csourcetype varchar2(20) null 
/*来源单据类型*/,
csourcetypecode varchar2(20) null 
/*来源单据交易类型*/,
vsourcecode varchar2(40) null 
/*入库单号*/,
csourceid varchar2(20) null 
/*来源单据PK*/,
csourcebid varchar2(20) null 
/*来源单据行PK*/,
ccontractid varchar2(20) null 
/*合同PK*/,
vcontractcode varchar2(50) null 
/*对应采购合同号*/,
corderid varchar2(20) null 
/*订单PK*/,
cordercode varchar2(50) null 
/*对应采购订单号*/,
ccheckbillid varchar2(1000) null 
/*质检单PK*/,
ccheckbillcode varchar2(500) null 
/*对应质检单号*/,
cqpbaseschemeid varchar2(20) null 
/*优质优价主体方案*/,
cqpschemeid varchar2(20) null 
/*优质优价方案主表*/,
dbaseprice number(28,8) null 
/*基准含税单价*/,
vschemefrmlcode varchar2(996) null 
/*总公式编码*/,
vschemefrmlname varchar2(996) null 
/*总公式*/,
nschemecalvalue number(28,8) null 
/*总计算结果*/,
vbmemo varchar2(181) null 
/*备注*/,
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
pk_pricesettle char(20) not null 
/*价格结算单_主键*/,
 constraint pk_o_pricesettle_b primary key (pk_pricesettle_b),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: 价格结算单子子表 */
create table po_pricesettle_bb (pk_pricesettle_bb char(20) not null 
/*价格结算单子子表*/,
pk_group varchar2(20) null 
/*集团*/,
pk_org varchar2(20) null 
/*采购组织最新版本*/,
pk_org_v varchar2(20) null 
/*采购组织*/,
cqpschemeid varchar2(20) null 
/*优质优价方案主表*/,
cqpbasestandid varchar2(20) null 
/*优质优价主体标准*/,
cqpstandid varchar2(50) null 
/*优质优价标准主表*/,
cqpstandbid varchar2(20) null 
/*优质优价标准子表*/,
ipriority integer null 
/*计算优先级*/,
vprecondcode varchar2(996) null 
/*计算先决条件编码*/,
vprecondname varchar2(996) null 
/*计算先决条件*/,
bweightavg char(1) null 
/*检验值是否加权平均*/,
vbaselowlmt varchar2(30) null 
/*基准值下限*/,
vbaseuplmt varchar2(30) null 
/*基准值上限*/,
vcheckvalue varchar2(30) null 
/*实际检验值*/,
nstandcalvalue number(28,8) null 
/*计算结果*/,
faccumtype integer null 
/*累计方式*/,
fvaluetype integer null 
/*标准值类型*/,
fadjusttype integer null 
/*调整类型*/,
bincludelower char(1) null 
/*是否包含下限*/,
bincludeupper char(1) null 
/*是否包含上限*/,
vlower varchar2(30) null 
/*下限值*/,
vupper varchar2(30) null 
/*上限值*/,
vfrmlcode varchar2(996) null 
/*优质优价公式编码*/,
vfrmlname varchar2(996) null 
/*优质优价公式名称*/,
po_pricesettle char(20) not null 
/*价格结算单_主键*/,
 constraint pk__pricesettle_bb primary key (pk_pricesettle_bb),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

