/* tablename: 价格结算单 */
create table po_pricesettle (
pk_pricesettle nchar(20) not null 
/*价格结算单*/,
pk_group nvarchar(20) null 
/*集团*/,
pk_org nvarchar(20) null 
/*采购组织最新版本*/,
pk_org_v nvarchar(20) null 
/*采购组织*/,
vbillcode nvarchar(40) null 
/*价格结算单号*/,
pk_storeorg nvarchar(20) null 
/*库存组织最新版本*/,
pk_storeorg_v nvarchar(20) null 
/*库存组织*/,
pk_supplier nvarchar(20) null 
/*供应商*/,
pk_employee nvarchar(20) null 
/*采购员*/,
pk_dept nvarchar(20) null 
/*采购部门最新版本*/,
pk_dept_v nvarchar(20) null 
/*采购部门*/,
dbilldate nchar(19) null 
/*单据日期*/,
vmemo nvarchar(181) null 
/*备注*/,
ccurrencyid nvarchar(20) null 
/*本币*/,
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
fbillstatus int null 
/*单据状态*/,
creator nvarchar(20) null 
/*创建人*/,
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
iprintcount int null 
/*打印次数*/,
 constraint pk_po_pricesettle primary key (pk_pricesettle),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 价格结算单子表 */
create table po_pricesettle_b (
pk_pricesettle_b nchar(20) not null 
/*价格结算单子表*/,
pk_group nvarchar(20) null 
/*集团*/,
pk_org nvarchar(20) null 
/*采购组织最新版本*/,
pk_org_v nvarchar(20) null 
/*采购组织*/,
crowno nvarchar(50) null 
/*行号*/,
pk_material nvarchar(20) null 
/*物料编码*/,
pk_srcmaterial nvarchar(20) null 
/*物料最新版本*/,
pk_batchcode nvarchar(20) null 
/*批次号档案*/,
vbatchcode nvarchar(40) null 
/*批次号*/,
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
castunitid nvarchar(20) null 
/*单位*/,
vchangerate nvarchar(60) null 
/*换算率*/,
cunitid nvarchar(20) null 
/*主单位*/,
nastarrvnum decimal(28,8) null 
/*到货数量*/,
narrvnum decimal(28,8) null 
/*到货主数量*/,
nastshouldinnum decimal(28,8) null 
/*质检后数量*/,
nshouldinnum decimal(28,8) null 
/*质检后主数量*/,
nastinnum decimal(28,8) null 
/*优质优价扣吨后数量*/,
ninnum decimal(28,8) null 
/*优质优价扣吨后主数量*/,
ftaxtypeflag int null 
/*扣税类别*/,
ntaxrate decimal(28,8) null 
/*税率*/,
ncalqualprice decimal(28,8) null 
/*本币含税优质优价*/,
nbasetaxprice decimal(28,8) null 
/*本币含税基价*/,
nbaseprice decimal(28,8) null 
/*本币无税基价*/,
ntaxprice decimal(28,8) null 
/*本币含税单价*/,
nprice decimal(28,8) null 
/*本币无税单价*/,
ntaxmny decimal(28,8) null 
/*本币价税合计*/,
pk_stordoc nvarchar(20) null 
/*收货仓库*/,
dbizdate nvarchar(19) null 
/*入库日期*/,
pk_usedept nvarchar(20) null 
/*使用部门最新版本*/,
pk_usedept_v nvarchar(20) null 
/*使用部门*/,
csourcetype nvarchar(20) null 
/*来源单据类型*/,
csourcetypecode nvarchar(20) null 
/*来源单据交易类型*/,
vsourcecode nvarchar(40) null 
/*入库单号*/,
csourceid nvarchar(20) null 
/*来源单据PK*/,
csourcebid nvarchar(20) null 
/*来源单据行PK*/,
ccontractid nvarchar(20) null 
/*合同PK*/,
vcontractcode nvarchar(50) null 
/*对应采购合同号*/,
corderid nvarchar(20) null 
/*订单PK*/,
cordercode nvarchar(50) null 
/*对应采购订单号*/,
ccheckbillid nvarchar(1000) null 
/*质检单PK*/,
ccheckbillcode nvarchar(500) null 
/*对应质检单号*/,
cqpbaseschemeid nvarchar(20) null 
/*优质优价主体方案*/,
cqpschemeid nvarchar(20) null 
/*优质优价方案主表*/,
dbaseprice decimal(28,8) null 
/*基准含税单价*/,
vschemefrmlcode nvarchar(996) null 
/*总公式编码*/,
vschemefrmlname nvarchar(996) null 
/*总公式*/,
nschemecalvalue decimal(28,8) null 
/*总计算结果*/,
vbmemo nvarchar(181) null 
/*备注*/,
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
pk_pricesettle nchar(20) not null 
/*价格结算单_主键*/,
 constraint pk_o_pricesettle_b primary key (pk_pricesettle_b),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 价格结算单子子表 */
create table po_pricesettle_bb (
pk_pricesettle_bb nchar(20) not null 
/*价格结算单子子表*/,
pk_group nvarchar(20) null 
/*集团*/,
pk_org nvarchar(20) null 
/*采购组织最新版本*/,
pk_org_v nvarchar(20) null 
/*采购组织*/,
cqpschemeid nvarchar(20) null 
/*优质优价方案主表*/,
cqpbasestandid nvarchar(20) null 
/*优质优价主体标准*/,
cqpstandid nvarchar(50) null 
/*优质优价标准主表*/,
cqpstandbid nvarchar(20) null 
/*优质优价标准子表*/,
ipriority int null 
/*计算优先级*/,
vprecondcode nvarchar(996) null 
/*计算先决条件编码*/,
vprecondname nvarchar(996) null 
/*计算先决条件*/,
bweightavg nchar(1) null 
/*检验值是否加权平均*/,
vbaselowlmt nvarchar(30) null 
/*基准值下限*/,
vbaseuplmt nvarchar(30) null 
/*基准值上限*/,
vcheckvalue nvarchar(30) null 
/*实际检验值*/,
nstandcalvalue decimal(28,8) null 
/*计算结果*/,
faccumtype int null 
/*累计方式*/,
fvaluetype int null 
/*标准值类型*/,
fadjusttype int null 
/*调整类型*/,
bincludelower nchar(1) null 
/*是否包含下限*/,
bincludeupper nchar(1) null 
/*是否包含上限*/,
vlower nvarchar(30) null 
/*下限值*/,
vupper nvarchar(30) null 
/*上限值*/,
vfrmlcode nvarchar(996) null 
/*优质优价公式编码*/,
vfrmlname nvarchar(996) null 
/*优质优价公式名称*/,
po_pricesettle nchar(20) not null 
/*价格结算单_主键*/,
 constraint pk__pricesettle_bb primary key (pk_pricesettle_bb),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

