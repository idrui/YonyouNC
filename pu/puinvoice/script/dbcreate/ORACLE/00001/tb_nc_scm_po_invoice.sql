/* tablename: 采购发票主表 */
create table po_invoice (pk_invoice char(20) not null 
/*采购发票*/,
pk_group varchar2(20) null 
/*所属集团*/,
pk_org varchar2(20) null 
/*财务组织(OID)*/,
pk_org_v varchar2(20) null 
/*财务组织(VID)*/,
vbillcode varchar2(200) null 
/*发票号*/,
dbilldate char(19) null 
/*发票日期*/,
darrivedate varchar2(19) null 
/*票到日期*/,
vtrantypecode varchar2(20) null 
/*发票类型(交易类型)编码*/,
pk_busitype varchar2(20) null 
/*业务流程*/,
bfee char(1) default 'N' null 
/*费用发票*/,
pk_purchaseorg varchar2(20) null 
/*采购组织(OID)*/,
pk_purchaseorg_v varchar2(20) null 
/*采购组织(VID)*/,
pk_stockorg varchar2(20) null 
/*库存组织(OID)*/,
pk_stockorg_v varchar2(20) null 
/*库存组织(VID)*/,
pk_supplier varchar2(20) null 
/*供应商*/,
pk_bankaccbas varchar2(20) null 
/*银行账户*/,
pk_freecust varchar2(20) null 
/*散户*/,
pk_bizpsn varchar2(20) null 
/*业务员*/,
pk_dept varchar2(20) null 
/*采购部门(OID)*/,
pk_dept_v varchar2(20) null 
/*采购部门(VID)*/,
pk_paytosupplier varchar2(20) null 
/*付款单位*/,
pk_payterm varchar2(20) null 
/*付款协议*/,
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
corigcurrencyid varchar2(20) null 
/*币种*/,
nexchangerate number(28,8) default 1.00 null 
/*汇率*/,
bapflag char(1) default 'N' null 
/*已传应付标志*/,
bfrozen char(1) default 'N' null 
/*冻结*/,
vfrozenreason varchar2(181) null 
/*最后冻结原因*/,
ntotalastnum number(28,8) null 
/*整单数量*/,
ntotalorigmny number(28,8) null 
/*整单价税合计(原币)*/,
vmemo varchar2(181) null 
/*备注*/,
pk_frozenuser varchar2(20) null 
/*冻结人*/,
tfrozentime varchar2(19) null 
/*冻结日期*/,
fbillstatus integer default 0 null 
/*单据状态*/,
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
iprintcount integer default 0 null 
/*打印次数*/,
pk_parentinvoice varchar2(20) null 
/*费用发票对应货物发票*/,
finvoiceclass integer default 0 null 
/*发票分类*/,
ftaxtypeflagh integer default 1 null 
/*整单扣税类别*/,
ntaxrateh number(28,8) null 
/*整单税率*/,
ccurrencyid varchar2(20) null 
/*本币币种(本位币)*/,
ngroupexchgrate number(28,8) null 
/*集团本位币汇率*/,
nglobalexchgrate number(28,8) null 
/*全局本位币汇率*/,
binitial char(1) default 'N' null 
/*是否期初发票*/,
creator varchar2(20) null 
/*创建人*/,
bvirtual char(1) default 'N' null 
/*虚拟发票标志*/,
ctrantypeid varchar2(20) null 
/*发票类型(交易类型)*/,
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
ctradewordid varchar2(20) null 
/*贸易术语*/,
bopptaxflag char(1) default 'N' null 
/*逆向征税*/,
vvatcode varchar2(40) null 
/*VAT注册码*/,
vvendorvatcode varchar2(40) null 
/*供应商VAT注册码*/,
finvoicetype integer null 
/*发票归属*/,
vadjustreason varchar2(181) null 
/*调整原因*/,
pk_balatype varchar2(20) null 
/*结算方式*/,
 constraint pk_po_invoice primary key (pk_invoice),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: 采购发票子表 */
create table po_invoice_b (pk_invoice_b char(20) not null 
/*采购发票明细*/,
pk_group varchar2(20) null 
/*所属集团*/,
pk_org varchar2(20) null 
/*财务组织(OID)*/,
pk_org_v varchar2(20) null 
/*财务组织(VID)*/,
pk_apfinanceorg varchar2(20) null 
/*应付财务组织(OID)*/,
pk_apfinanceorg_v varchar2(20) null 
/*应付财务组织(VID)*/,
crowno varchar2(20) null 
/*行号*/,
pk_material varchar2(20) null 
/*物料(VID)*/,
pk_srcmaterial varchar2(20) null 
/*物料(OID)*/,
cunitid varchar2(20) null 
/*主单位*/,
nnum number(28,8) null 
/*主数量*/,
norigprice number(28,8) null 
/*主无税单价*/,
norigmny number(28,8) null 
/*无税金额*/,
norigtaxmny number(28,8) null 
/*价税合计*/,
norigtaxprice number(28,8) null 
/*主含税单价*/,
ftaxtypeflag integer default 1 null 
/*扣税类别*/,
ntaxrate number(28,8) null 
/*税率*/,
nprice number(28,8) null 
/*主本币无税单价*/,
nmny number(28,8) null 
/*本币无税金额*/,
ntax number(28,8) null 
/*本币税额*/,
ntaxmny number(28,8) null 
/*本币价税合计*/,
ntaxprice number(28,8) null 
/*主本币含税单价*/,
castunitid varchar2(20) null 
/*单位*/,
vchangerate varchar2(60) null 
/*换算率*/,
nastnum number(28,8) null 
/*数量*/,
pk_costsubj varchar2(20) null 
/*收支项目*/,
pk_stordoc varchar2(20) null 
/*仓库*/,
vbatchcode varchar2(40) null 
/*批次号*/,
pk_batchcode varchar2(20) null 
/*批次档案*/,
pk_usedept varchar2(20) null 
/*使用部门(OID)*/,
pk_usedept_v varchar2(20) null 
/*使用部门(VID)*/,
naccumsettnum number(28,8) null 
/*累计结算主数量*/,
naccumsettmny number(28,8) null 
/*累计本币结算金额*/,
nreasonwastenum number(28,8) null 
/*合理损耗主数量*/,
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
cprojectid varchar2(20) null 
/*项目*/,
cproductorid varchar2(20) null 
/*生产厂商*/,
vmemob varchar2(181) null 
/*备注*/,
vctcode varchar2(40) null 
/*合同号*/,
cfirsttypecode varchar2(20) null 
/*源头单据类型*/,
vfirstcode varchar2(40) null 
/*源头单据号*/,
cfirstid varchar2(20) null 
/*源头单据主键*/,
vfirstrowno varchar2(20) null 
/*源头单据行号*/,
vfirsttrantype varchar2(20) null 
/*源头交易类型*/,
cfirstbid varchar2(20) null 
/*源头单据行主键*/,
csourcetypecode varchar2(20) null 
/*来源单据类型*/,
vsourcecode varchar2(40) null 
/*来源单据号*/,
csourceid varchar2(20) null 
/*来源单据主键*/,
csourcebid varchar2(20) null 
/*来源单据行主键*/,
vsourcerowno varchar2(20) null 
/*来源单据行号*/,
vsourcetrantype varchar2(20) null 
/*来源交易类型*/,
pk_order varchar2(20) null 
/*订单主键*/,
pk_order_b varchar2(20) null 
/*订单行主键*/,
vordercode varchar2(40) null 
/*订单号*/,
vordertrantype varchar2(20) null 
/*订单交易类型*/,
pk_apliabcenter varchar2(20) null 
/*结算利润中心最新版本*/,
pk_apliabcenter_v varchar2(20) null 
/*结算利润中心*/,
ngroupmny number(28,8) null 
/*集团本币无税金额*/,
ngrouptaxmny number(28,8) null 
/*集团本币价税合计*/,
nglobalmny number(28,8) null 
/*全局本币无税金额*/,
nglobaltaxmny number(28,8) null 
/*全局本币价税合计*/,
nastorigprice number(28,8) null 
/*无税单价*/,
nastorigtaxprice number(28,8) null 
/*含税单价*/,
nastprice number(28,8) null 
/*本币无税单价*/,
nasttaxprice number(28,8) null 
/*本币含税单价*/,
frowtype integer default 0 null 
/*行类型*/,
dbilldate char(19) null 
/*发票日期*/,
pk_supplier varchar2(20) null 
/*供应商*/,
cqualitylevelid varchar2(20) null 
/*质量等级*/,
dsourcedate char(19) null 
/*来源单据日期*/,
pk_invoice char(20) not null 
/*采购发票主表_主键*/,
casscustid varchar2(20) null 
/*客户*/,
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
cprojecttaskid varchar2(20) null 
/*项目任务*/,
cadjustedinvid varchar2(20) null 
/*被调整发票主键*/,
cadjustedrowid varchar2(20) null 
/*被调整发票行主键*/,
vadjedbillcode varchar2(40) null 
/*被调整发票号*/,
ccontractid varchar2(20) null 
/*合同主键*/,
ccontractrowid varchar2(20) null 
/*合同行主键*/,
nadjustorgprice number(28,8) null 
/*调整单价*/,
cffileid varchar2(20) null 
/*特征码*/,
 constraint pk_po_invoice_b primary key (pk_invoice_b),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: 付款计划 */
create table po_invoice_settle (pk_invoice_settle char(20) not null 
/*付款计划*/,
pk_group varchar2(20) default '~' null 
/*所属集团*/,
pk_apfinanceorg varchar2(20) default '~' null 
/*财务组织[OID]*/,
pk_apfinanceorg_v varchar2(20) default '~' null 
/*财务组织[VID]*/,
vpaydate varchar2(20) null 
/*付款期*/,
npayrate number(28,8) null 
/*付款比例*/,
bpreflag char(1) null 
/*预付款*/,
csettletype varchar2(20) default '~' null 
/*结算方式*/,
cincomeperiod varchar2(20) default '~' null 
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
csettlerowno varchar2(20) null 
/*行号*/,
vmemo varchar2(181) null 
/*备注*/,
pk_invoice char(20) not null 
/*采购发票主表_主键*/,
npaymny number(28,8) null 
/*预计付款金额*/,
dpaydate char(19) null 
/*预计付款日期*/,
 constraint pk__invoice_settle primary key (pk_invoice_settle),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

