/* tablename: 采购发票主表 */
create table po_invoice (
pk_invoice nchar(20) not null 
/*采购发票*/,
pk_group nvarchar(20) null 
/*所属集团*/,
pk_org nvarchar(20) null 
/*财务组织(OID)*/,
pk_org_v nvarchar(20) null 
/*财务组织(VID)*/,
vbillcode nvarchar(200) null 
/*发票号*/,
dbilldate nchar(19) null 
/*发票日期*/,
darrivedate nvarchar(19) null 
/*票到日期*/,
vtrantypecode nvarchar(20) null 
/*发票类型(交易类型)编码*/,
pk_busitype nvarchar(20) null 
/*业务流程*/,
bfee nchar(1) null default 'N' 
/*费用发票*/,
pk_purchaseorg nvarchar(20) null 
/*采购组织(OID)*/,
pk_purchaseorg_v nvarchar(20) null 
/*采购组织(VID)*/,
pk_stockorg nvarchar(20) null 
/*库存组织(OID)*/,
pk_stockorg_v nvarchar(20) null 
/*库存组织(VID)*/,
pk_supplier nvarchar(20) null 
/*供应商*/,
pk_bankaccbas nvarchar(20) null 
/*银行账户*/,
pk_freecust nvarchar(20) null 
/*散户*/,
pk_bizpsn nvarchar(20) null 
/*业务员*/,
pk_dept nvarchar(20) null 
/*采购部门(OID)*/,
pk_dept_v nvarchar(20) null 
/*采购部门(VID)*/,
pk_paytosupplier nvarchar(20) null 
/*付款单位*/,
pk_payterm nvarchar(20) null 
/*付款协议*/,
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
corigcurrencyid nvarchar(20) null 
/*币种*/,
nexchangerate decimal(28,8) null default 1.00 
/*汇率*/,
bapflag nchar(1) null default 'N' 
/*已传应付标志*/,
bfrozen nchar(1) null default 'N' 
/*冻结*/,
vfrozenreason nvarchar(181) null 
/*最后冻结原因*/,
ntotalastnum decimal(28,8) null 
/*整单数量*/,
ntotalorigmny decimal(28,8) null 
/*整单价税合计(原币)*/,
vmemo nvarchar(181) null 
/*备注*/,
pk_frozenuser nvarchar(20) null 
/*冻结人*/,
tfrozentime nvarchar(19) null 
/*冻结日期*/,
fbillstatus int null default 0 
/*单据状态*/,
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
iprintcount int null default 0 
/*打印次数*/,
pk_parentinvoice nvarchar(20) null 
/*费用发票对应货物发票*/,
finvoiceclass int null default 0 
/*发票分类*/,
ftaxtypeflagh int null default 1 
/*整单扣税类别*/,
ntaxrateh decimal(28,8) null 
/*整单税率*/,
ccurrencyid nvarchar(20) null 
/*本币币种(本位币)*/,
ngroupexchgrate decimal(28,8) null 
/*集团本位币汇率*/,
nglobalexchgrate decimal(28,8) null 
/*全局本位币汇率*/,
binitial nchar(1) null default 'N' 
/*是否期初发票*/,
creator nvarchar(20) null 
/*创建人*/,
bvirtual nchar(1) null default 'N' 
/*虚拟发票标志*/,
ctrantypeid nvarchar(20) null 
/*发票类型(交易类型)*/,
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
ctradewordid nvarchar(20) null 
/*贸易术语*/,
bopptaxflag nchar(1) null default 'N' 
/*逆向征税*/,
vvatcode nvarchar(40) null 
/*VAT注册码*/,
vvendorvatcode nvarchar(40) null 
/*供应商VAT注册码*/,
finvoicetype int null 
/*发票归属*/,
vadjustreason nvarchar(181) null 
/*调整原因*/,
pk_balatype nvarchar(20) null 
/*结算方式*/,
 constraint pk_po_invoice primary key (pk_invoice),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 采购发票子表 */
create table po_invoice_b (
pk_invoice_b nchar(20) not null 
/*采购发票明细*/,
pk_group nvarchar(20) null 
/*所属集团*/,
pk_org nvarchar(20) null 
/*财务组织(OID)*/,
pk_org_v nvarchar(20) null 
/*财务组织(VID)*/,
pk_apfinanceorg nvarchar(20) null 
/*应付财务组织(OID)*/,
pk_apfinanceorg_v nvarchar(20) null 
/*应付财务组织(VID)*/,
crowno nvarchar(20) null 
/*行号*/,
pk_material nvarchar(20) null 
/*物料(VID)*/,
pk_srcmaterial nvarchar(20) null 
/*物料(OID)*/,
cunitid nvarchar(20) null 
/*主单位*/,
nnum decimal(28,8) null 
/*主数量*/,
norigprice decimal(28,8) null 
/*主无税单价*/,
norigmny decimal(28,8) null 
/*无税金额*/,
norigtaxmny decimal(28,8) null 
/*价税合计*/,
norigtaxprice decimal(28,8) null 
/*主含税单价*/,
ftaxtypeflag int null default 1 
/*扣税类别*/,
ntaxrate decimal(28,8) null 
/*税率*/,
nprice decimal(28,8) null 
/*主本币无税单价*/,
nmny decimal(28,8) null 
/*本币无税金额*/,
ntax decimal(28,8) null 
/*本币税额*/,
ntaxmny decimal(28,8) null 
/*本币价税合计*/,
ntaxprice decimal(28,8) null 
/*主本币含税单价*/,
castunitid nvarchar(20) null 
/*单位*/,
vchangerate nvarchar(60) null 
/*换算率*/,
nastnum decimal(28,8) null 
/*数量*/,
pk_costsubj nvarchar(20) null 
/*收支项目*/,
pk_stordoc nvarchar(20) null 
/*仓库*/,
vbatchcode nvarchar(40) null 
/*批次号*/,
pk_batchcode nvarchar(20) null 
/*批次档案*/,
pk_usedept nvarchar(20) null 
/*使用部门(OID)*/,
pk_usedept_v nvarchar(20) null 
/*使用部门(VID)*/,
naccumsettnum decimal(28,8) null 
/*累计结算主数量*/,
naccumsettmny decimal(28,8) null 
/*累计本币结算金额*/,
nreasonwastenum decimal(28,8) null 
/*合理损耗主数量*/,
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
cprojectid nvarchar(20) null 
/*项目*/,
cproductorid nvarchar(20) null 
/*生产厂商*/,
vmemob nvarchar(181) null 
/*备注*/,
vctcode nvarchar(40) null 
/*合同号*/,
cfirsttypecode nvarchar(20) null 
/*源头单据类型*/,
vfirstcode nvarchar(40) null 
/*源头单据号*/,
cfirstid nvarchar(20) null 
/*源头单据主键*/,
vfirstrowno nvarchar(20) null 
/*源头单据行号*/,
vfirsttrantype nvarchar(20) null 
/*源头交易类型*/,
cfirstbid nvarchar(20) null 
/*源头单据行主键*/,
csourcetypecode nvarchar(20) null 
/*来源单据类型*/,
vsourcecode nvarchar(40) null 
/*来源单据号*/,
csourceid nvarchar(20) null 
/*来源单据主键*/,
csourcebid nvarchar(20) null 
/*来源单据行主键*/,
vsourcerowno nvarchar(20) null 
/*来源单据行号*/,
vsourcetrantype nvarchar(20) null 
/*来源交易类型*/,
pk_order nvarchar(20) null 
/*订单主键*/,
pk_order_b nvarchar(20) null 
/*订单行主键*/,
vordercode nvarchar(40) null 
/*订单号*/,
vordertrantype nvarchar(20) null 
/*订单交易类型*/,
pk_apliabcenter nvarchar(20) null 
/*结算利润中心最新版本*/,
pk_apliabcenter_v nvarchar(20) null 
/*结算利润中心*/,
ngroupmny decimal(28,8) null 
/*集团本币无税金额*/,
ngrouptaxmny decimal(28,8) null 
/*集团本币价税合计*/,
nglobalmny decimal(28,8) null 
/*全局本币无税金额*/,
nglobaltaxmny decimal(28,8) null 
/*全局本币价税合计*/,
nastorigprice decimal(28,8) null 
/*无税单价*/,
nastorigtaxprice decimal(28,8) null 
/*含税单价*/,
nastprice decimal(28,8) null 
/*本币无税单价*/,
nasttaxprice decimal(28,8) null 
/*本币含税单价*/,
frowtype int null default 0 
/*行类型*/,
dbilldate nchar(19) null 
/*发票日期*/,
pk_supplier nvarchar(20) null 
/*供应商*/,
cqualitylevelid nvarchar(20) null 
/*质量等级*/,
dsourcedate nchar(19) null 
/*来源单据日期*/,
pk_invoice nchar(20) not null 
/*采购发票主表_主键*/,
casscustid nvarchar(20) null 
/*客户*/,
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
cprojecttaskid nvarchar(20) null 
/*项目任务*/,
cadjustedinvid nvarchar(20) null 
/*被调整发票主键*/,
cadjustedrowid nvarchar(20) null 
/*被调整发票行主键*/,
vadjedbillcode nvarchar(40) null 
/*被调整发票号*/,
ccontractid nvarchar(20) null 
/*合同主键*/,
ccontractrowid nvarchar(20) null 
/*合同行主键*/,
nadjustorgprice decimal(28,8) null 
/*调整单价*/,
cffileid nvarchar(20) null 
/*特征码*/,
 constraint pk_po_invoice_b primary key (pk_invoice_b),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 付款计划 */
create table po_invoice_settle (
pk_invoice_settle nchar(20) not null 
/*付款计划*/,
pk_group nvarchar(20) null default '~' 
/*所属集团*/,
pk_apfinanceorg nvarchar(20) null default '~' 
/*财务组织[OID]*/,
pk_apfinanceorg_v nvarchar(20) null default '~' 
/*财务组织[VID]*/,
vpaydate nvarchar(20) null 
/*付款期*/,
npayrate decimal(28,8) null 
/*付款比例*/,
bpreflag nchar(1) null 
/*预付款*/,
csettletype nvarchar(20) null default '~' 
/*结算方式*/,
cincomeperiod nvarchar(20) null default '~' 
/*起效日期*/,
feffectdatebill int null 
/*起效日期对应单据*/,
ieffectaddday int null 
/*起效日期延期天数*/,
ipaymentday int null 
/*账期天数*/,
icheckdata int null 
/*固定结账日*/,
feffectmonth int null 
/*生效月*/,
ieffectaddmonth int null 
/*附加月*/,
csettlerowno nvarchar(20) null 
/*行号*/,
vmemo nvarchar(181) null 
/*备注*/,
pk_invoice nchar(20) not null 
/*采购发票主表_主键*/,
npaymny decimal(28,8) null 
/*预计付款金额*/,
dpaydate nchar(19) null 
/*预计付款日期*/,
 constraint pk__invoice_settle primary key (pk_invoice_settle),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

