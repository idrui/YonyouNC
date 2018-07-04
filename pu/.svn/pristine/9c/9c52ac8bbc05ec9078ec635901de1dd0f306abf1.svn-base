/* tablename: 采购发票主表 */
create table po_invoice (pk_invoice char(20) not null 
/*采购发票*/,
pk_group varchar(20) null 
/*所属集团*/,
pk_org varchar(20) null 
/*财务组织(OID)*/,
pk_org_v varchar(20) null 
/*财务组织(VID)*/,
vbillcode varchar(200) null 
/*发票号*/,
dbilldate char(19) null 
/*发票日期*/,
darrivedate varchar(19) null 
/*票到日期*/,
vtrantypecode varchar(20) null 
/*发票类型(交易类型)编码*/,
pk_busitype varchar(20) null 
/*业务流程*/,
bfee char(1) null default 'N' 
/*费用发票*/,
pk_purchaseorg varchar(20) null 
/*采购组织(OID)*/,
pk_purchaseorg_v varchar(20) null 
/*采购组织(VID)*/,
pk_stockorg varchar(20) null 
/*库存组织(OID)*/,
pk_stockorg_v varchar(20) null 
/*库存组织(VID)*/,
pk_supplier varchar(20) null 
/*供应商*/,
pk_bankaccbas varchar(20) null 
/*银行账户*/,
pk_freecust varchar(20) null 
/*散户*/,
pk_bizpsn varchar(20) null 
/*业务员*/,
pk_dept varchar(20) null 
/*采购部门(OID)*/,
pk_dept_v varchar(20) null 
/*采购部门(VID)*/,
pk_paytosupplier varchar(20) null 
/*付款单位*/,
pk_payterm varchar(20) null 
/*付款协议*/,
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
corigcurrencyid varchar(20) null 
/*币种*/,
nexchangerate decimal(28,8) null default 1.00 
/*汇率*/,
bapflag char(1) null default 'N' 
/*已传应付标志*/,
bfrozen char(1) null default 'N' 
/*冻结*/,
vfrozenreason varchar(181) null 
/*最后冻结原因*/,
ntotalastnum decimal(28,8) null 
/*整单数量*/,
ntotalorigmny decimal(28,8) null 
/*整单价税合计(原币)*/,
vmemo varchar(181) null 
/*备注*/,
pk_frozenuser varchar(20) null 
/*冻结人*/,
tfrozentime varchar(19) null 
/*冻结日期*/,
fbillstatus integer null default 0 
/*单据状态*/,
billmaker varchar(20) null 
/*制单人*/,
dmakedate char(19) null 
/*制单日期*/,
creationtime char(19) null 
/*创建时间*/,
approver varchar(20) null 
/*审批人*/,
taudittime varchar(19) null 
/*审批日期*/,
modifier varchar(20) null 
/*最后修改人*/,
modifiedtime char(19) null 
/*最后修改时间*/,
iprintcount integer null default 0 
/*打印次数*/,
pk_parentinvoice varchar(20) null 
/*费用发票对应货物发票*/,
finvoiceclass integer null default 0 
/*发票分类*/,
ftaxtypeflagh integer null default 1 
/*整单扣税类别*/,
ntaxrateh decimal(28,8) null 
/*整单税率*/,
ccurrencyid varchar(20) null 
/*本币币种(本位币)*/,
ngroupexchgrate decimal(28,8) null 
/*集团本位币汇率*/,
nglobalexchgrate decimal(28,8) null 
/*全局本位币汇率*/,
binitial char(1) null default 'N' 
/*是否期初发票*/,
creator varchar(20) null 
/*创建人*/,
bvirtual char(1) null default 'N' 
/*虚拟发票标志*/,
ctrantypeid varchar(20) null 
/*发票类型(交易类型)*/,
csendcountryid varchar(20) null 
/*发货国家/地区*/,
crececountryid varchar(20) null 
/*收货国家/地区*/,
ctaxcountryid varchar(20) null 
/*报税国家/地区*/,
fbuysellflag integer null 
/*购销类型*/,
btriatradeflag char(1) null 
/*三角贸易*/,
ctradewordid varchar(20) null 
/*贸易术语*/,
bopptaxflag char(1) null default 'N' 
/*逆向征税*/,
vvatcode varchar(40) null 
/*VAT注册码*/,
vvendorvatcode varchar(40) null 
/*供应商VAT注册码*/,
finvoicetype integer null 
/*发票归属*/,
vadjustreason varchar(181) null 
/*调整原因*/,
pk_balatype varchar(20) null 
/*结算方式*/,
 constraint pk_po_invoice primary key (pk_invoice),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: 采购发票子表 */
create table po_invoice_b (pk_invoice_b char(20) not null 
/*采购发票明细*/,
pk_group varchar(20) null 
/*所属集团*/,
pk_org varchar(20) null 
/*财务组织(OID)*/,
pk_org_v varchar(20) null 
/*财务组织(VID)*/,
pk_apfinanceorg varchar(20) null 
/*应付财务组织(OID)*/,
pk_apfinanceorg_v varchar(20) null 
/*应付财务组织(VID)*/,
crowno varchar(20) null 
/*行号*/,
pk_material varchar(20) null 
/*物料(VID)*/,
pk_srcmaterial varchar(20) null 
/*物料(OID)*/,
cunitid varchar(20) null 
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
ftaxtypeflag integer null default 1 
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
castunitid varchar(20) null 
/*单位*/,
vchangerate varchar(60) null 
/*换算率*/,
nastnum decimal(28,8) null 
/*数量*/,
pk_costsubj varchar(20) null 
/*收支项目*/,
pk_stordoc varchar(20) null 
/*仓库*/,
vbatchcode varchar(40) null 
/*批次号*/,
pk_batchcode varchar(20) null 
/*批次档案*/,
pk_usedept varchar(20) null 
/*使用部门(OID)*/,
pk_usedept_v varchar(20) null 
/*使用部门(VID)*/,
naccumsettnum decimal(28,8) null 
/*累计结算主数量*/,
naccumsettmny decimal(28,8) null 
/*累计本币结算金额*/,
nreasonwastenum decimal(28,8) null 
/*合理损耗主数量*/,
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
cprojectid varchar(20) null 
/*项目*/,
cproductorid varchar(20) null 
/*生产厂商*/,
vmemob varchar(181) null 
/*备注*/,
vctcode varchar(40) null 
/*合同号*/,
cfirsttypecode varchar(20) null 
/*源头单据类型*/,
vfirstcode varchar(40) null 
/*源头单据号*/,
cfirstid varchar(20) null 
/*源头单据主键*/,
vfirstrowno varchar(20) null 
/*源头单据行号*/,
vfirsttrantype varchar(20) null 
/*源头交易类型*/,
cfirstbid varchar(20) null 
/*源头单据行主键*/,
csourcetypecode varchar(20) null 
/*来源单据类型*/,
vsourcecode varchar(40) null 
/*来源单据号*/,
csourceid varchar(20) null 
/*来源单据主键*/,
csourcebid varchar(20) null 
/*来源单据行主键*/,
vsourcerowno varchar(20) null 
/*来源单据行号*/,
vsourcetrantype varchar(20) null 
/*来源交易类型*/,
pk_order varchar(20) null 
/*订单主键*/,
pk_order_b varchar(20) null 
/*订单行主键*/,
vordercode varchar(40) null 
/*订单号*/,
vordertrantype varchar(20) null 
/*订单交易类型*/,
pk_apliabcenter varchar(20) null 
/*结算利润中心最新版本*/,
pk_apliabcenter_v varchar(20) null 
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
frowtype integer null default 0 
/*行类型*/,
dbilldate char(19) null 
/*发票日期*/,
pk_supplier varchar(20) null 
/*供应商*/,
cqualitylevelid varchar(20) null 
/*质量等级*/,
dsourcedate char(19) null 
/*来源单据日期*/,
pk_invoice char(20) not null 
/*采购发票主表_主键*/,
casscustid varchar(20) null 
/*客户*/,
ctaxcodeid varchar(20) null 
/*税码*/,
ncaltaxmny decimal(28,8) null 
/*计税金额*/,
nnosubtaxrate decimal(28,8) null 
/*不可抵扣税率*/,
nnosubtax decimal(28,8) null 
/*不可抵扣税额*/,
ncalcostmny decimal(28,8) null 
/*计成本金额*/,
cprojecttaskid varchar(20) null 
/*项目任务*/,
cadjustedinvid varchar(20) null 
/*被调整发票主键*/,
cadjustedrowid varchar(20) null 
/*被调整发票行主键*/,
vadjedbillcode varchar(40) null 
/*被调整发票号*/,
ccontractid varchar(20) null 
/*合同主键*/,
ccontractrowid varchar(20) null 
/*合同行主键*/,
nadjustorgprice decimal(28,8) null 
/*调整单价*/,
cffileid varchar(20) null 
/*特征码*/,
 constraint pk_po_invoice_b primary key (pk_invoice_b),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: 付款计划 */
create table po_invoice_settle (pk_invoice_settle char(20) not null 
/*付款计划*/,
pk_group varchar(20) null default '~' 
/*所属集团*/,
pk_apfinanceorg varchar(20) null default '~' 
/*财务组织[OID]*/,
pk_apfinanceorg_v varchar(20) null default '~' 
/*财务组织[VID]*/,
vpaydate varchar(20) null 
/*付款期*/,
npayrate decimal(28,8) null 
/*付款比例*/,
bpreflag char(1) null 
/*预付款*/,
csettletype varchar(20) null default '~' 
/*结算方式*/,
cincomeperiod varchar(20) null default '~' 
/*起效日期*/,
feffectdatebill integer null 
/*起效日期对应单据*/,
ieffectaddday integer null 
/*起效日期延期天数*/,
ipaymentday integer null 
/*账期天数*/,
icheckdata integer null 
/*固定结账日*/,
feffectmonth integer null 
/*生效月*/,
ieffectaddmonth integer null 
/*附加月*/,
csettlerowno varchar(20) null 
/*行号*/,
vmemo varchar(181) null 
/*备注*/,
pk_invoice char(20) not null 
/*采购发票主表_主键*/,
npaymny decimal(28,8) null 
/*预计付款金额*/,
dpaydate char(19) null 
/*预计付款日期*/,
 constraint pk__invoice_settle primary key (pk_invoice_settle),
 ts char(19) null,
dr smallint null default 0
)
;

