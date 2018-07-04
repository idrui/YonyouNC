/* tablename: 采购订单在途状态 */
create table po_order_bb (
pk_order_bb nchar(20) not null 
/*采购订单在途状态*/,
pk_order nchar(20) null 
/*采购订单*/,
pk_org nvarchar(20) null default '~' 
/*采购???织*/,
pk_org_v nvarchar(20) null default '~' 
/*采购组织*/,
pk_group nvarchar(20) null default '~' 
/*所属集团*/,
fonwaystatus int null 
/*在途状态*/,
nonwaynum decimal(28,8) null 
/*在途数量*/,
vbillcode nvarchar(40) null 
/*单据编号*/,
dbilldate nchar(19) null 
/*单据日期*/,
dplanarrvdate nvarchar(19) null 
/*计划到货日期*/,
cloadport nvarchar(20) null default '~' 
/*装船港口*/,
clandport nvarchar(20) null default '~' 
/*到货港口*/,
cshipname nvarchar(50) null 
/*船名*/,
cshipline nvarchar(20) null 
/*船次号*/,
dplanfreightdate nvarchar(19) null 
/*计划到港日期*/,
ccasecode nvarchar(30) null 
/*货柜号*/,
ccarrier nvarchar(20) null default '~' 
/*承运商*/,
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
vbdef21 nvarchar(101) null 
/*自定义项21*/,
vbdef22 nvarchar(101) null 
/*自定义项22*/,
vbdef23 nvarchar(101) null 
/*自定义项23*/,
vbdef24 nvarchar(101) null 
/*自定义项24*/,
vbdef25 nvarchar(101) null 
/*自定义项25*/,
vbdef26 nvarchar(101) null 
/*自定义项26*/,
vbdef27 nvarchar(101) null 
/*自定义项27*/,
vbdef28 nvarchar(101) null 
/*自定义项28*/,
vbdef29 nvarchar(101) null 
/*自定义项29*/,
vbdef30 nvarchar(101) null 
/*自定义项30*/,
vbdef31 nvarchar(101) null 
/*自定义项31*/,
vbdef32 nvarchar(101) null 
/*自定义项32*/,
vbdef33 nvarchar(101) null 
/*自定义项33*/,
vbdef34 nvarchar(101) null 
/*自定义项34*/,
vbdef35 nvarchar(101) null 
/*自定义项35*/,
vbdef36 nvarchar(101) null 
/*自定义项36*/,
vbdef37 nvarchar(101) null 
/*自定义项37*/,
vbdef38 nvarchar(101) null 
/*自定义项38*/,
vbdef39 nvarchar(101) null 
/*自定义项39*/,
vbdef40 nvarchar(101) null 
/*自定义项40*/,
vhdef1 nvarchar(101) null 
/*表头自定义项1*/,
vhdef2 nvarchar(101) null 
/*表头自定义项2*/,
vhdef3 nvarchar(101) null 
/*表头自定义项3*/,
vhdef4 nvarchar(101) null 
/*表头自定义项4*/,
vhdef5 nvarchar(101) null 
/*表头自定义项5*/,
vhdef6 nvarchar(101) null 
/*表头自定义项6*/,
vhdef7 nvarchar(101) null 
/*表头自定义项7*/,
vhdef8 nvarchar(101) null 
/*表头自定义项8*/,
vhdef9 nvarchar(101) null 
/*表头自定义项9*/,
vhdef10 nvarchar(101) null 
/*表头自定义项10*/,
vhdef11 nvarchar(101) null 
/*表头自定义项11*/,
vhdef12 nvarchar(101) null 
/*表头自定义项12*/,
vhdef13 nvarchar(101) null 
/*表头自定义项13*/,
vhdef14 nvarchar(101) null 
/*表头自定义项14*/,
vhdef15 nvarchar(101) null 
/*表头自定义项15*/,
vhdef16 nvarchar(101) null 
/*表头自定义项16*/,
vhdef17 nvarchar(101) null 
/*表头自定义项17*/,
vhdef18 nvarchar(101) null 
/*表头自定义项18*/,
vhdef19 nvarchar(101) null 
/*表头自定义项19*/,
vhdef20 nvarchar(101) null 
/*表头自定义项20*/,
vhdef21 nvarchar(101) null 
/*表头自定义项21*/,
vhdef22 nvarchar(101) null 
/*表头自定义项22*/,
vhdef23 nvarchar(101) null 
/*表头自定义项23*/,
vhdef24 nvarchar(101) null 
/*表头自定义项24*/,
vhdef25 nvarchar(101) null 
/*表头自定义项25*/,
vhdef26 nvarchar(101) null 
/*表头自定义项26*/,
vhdef27 nvarchar(101) null 
/*表头自定义项27*/,
vhdef28 nvarchar(101) null 
/*表头自定义项28*/,
vhdef29 nvarchar(101) null 
/*表头自定义项29*/,
vhdef30 nvarchar(101) null 
/*表头自定义项30*/,
vhdef31 nvarchar(101) null 
/*表头自定义项31*/,
vhdef32 nvarchar(101) null 
/*表头自定义项32*/,
vhdef33 nvarchar(101) null 
/*表头自定义项33*/,
vhdef34 nvarchar(101) null 
/*表头自定义项34*/,
vhdef35 nvarchar(101) null 
/*表头自定义项35*/,
vhdef36 nvarchar(101) null 
/*表头自定义项36*/,
vhdef37 nvarchar(101) null 
/*表头自定义项37*/,
vhdef38 nvarchar(101) null 
/*表头自定义项38*/,
vhdef39 nvarchar(101) null 
/*表头自定义项39*/,
vhdef40 nvarchar(101) null 
/*表头自定义项40*/,
isoperated nchar(1) null 
/*是否已操作*/,
vvendororderrow nvarchar(30) null 
/*对方订单行号*/,
nmaxhandlenum decimal(28,8) null 
/*最大可操作数量*/,
pk_order_b nchar(20) not null 
/*上层单据主键*/,
 constraint pk_po_order_bb primary key (pk_order_bb),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 采购订单明细 */
create table po_order_b (
pk_order_b nchar(20) not null 
/*采购订单明细*/,
pk_group nvarchar(20) null default '~' 
/*所属集团*/,
pk_org nvarchar(20) null default '~' 
/*采购组织*/,
pk_org_v nvarchar(20) null default '~' 
/*采购组织*/,
pk_reqcorp nvarchar(20) null default '~' 
/*需求公司*/,
pk_reqstoorg nvarchar(20) null default '~' 
/*需求库存组织*/,
pk_reqstoorg_v nvarchar(20) null default '~' 
/*??求??存组织*/,
pk_reqstordoc nvarchar(20) null default '~' 
/*需求仓库*/,
pk_arrvstoorg nvarchar(20) null default '~' 
/*收货库存组织*/,
pk_arrvstoorg_v nvarchar(20) null default '~' 
/*收货库存组织*/,
pk_reqdept nvarchar(20) null default '~' 
/*需求部门最新版本*/,
pk_flowstockorg nvarchar(20) null default '~' 
/*物流组织*/,
pk_flowstockorg_v nvarchar(20) null default '~' 
/*物流组织*/,
crowno nvarchar(20) null 
/*行号*/,
pk_material nvarchar(20) not null default '~' 
/*物料名称*/,
pk_srcmaterial nvarchar(20) not null default '~' 
/*物料信息*/,
cunitid nvarchar(20) null default '~' 
/*主单位*/,
nnum decimal(28,8) null 
/*主数量*/,
castunitid nvarchar(20) null default '~' 
/*单位*/,
nastnum decimal(28,8) null 
/*数量*/,
vchangerate nvarchar(60) null 
/*换算率*/,
cqtunitid nvarchar(20) null default '~' 
/*报价单位*/,
nqtunitnum decimal(28,8) null 
/*报价数量*/,
vqtunitrate nvarchar(60) null 
/*报价换算率*/,
nqtorigprice decimal(28,8) null 
/*无税单价*/,
nqtorigtaxprice decimal(28,8) null 
/*含税单价*/,
nqtorignetprice decimal(28,8) null 
/*无税净价*/,
nqtorigtaxnetprc decimal(28,8) null 
/*含税净价*/,
nqtnetprice decimal(28,8) null 
/*本币无税净价*/,
nqttaxnetprice decimal(28,8) null 
/*本币含税净价*/,
nitemdiscountrate decimal(28,8) null 
/*折扣*/,
norigmny decimal(28,8) null 
/*无税金额*/,
norigtaxmny decimal(28,8) null 
/*价税合计*/,
nmny decimal(28,8) null 
/*本币无税金额*/,
ntaxmny decimal(28,8) null 
/*本币价税合计*/,
ntax decimal(28,8) null 
/*税额*/,
norigprice decimal(28,8) null 
/*主无税单价*/,
norigtaxprice decimal(28,8) null 
/*主含税单价*/,
norignetprice decimal(28,8) null 
/*主无税净价*/,
norigtaxnetprice decimal(28,8) null 
/*主含税净价*/,
nnetprice decimal(28,8) null 
/*主本币无税净价*/,
ntaxnetprice decimal(28,8) null 
/*主本币含税净价*/,
naccumarrvnum decimal(28,8) null 
/*累计到货主数量*/,
naccumstorenum decimal(28,8) null 
/*累计入库主数量*/,
naccuminvoicenum decimal(28,8) null 
/*累计开票主数量*/,
naccumwastnum decimal(28,8) null 
/*累计途耗主数量*/,
dplanarrvdate nchar(19) null 
/*计划到货日期*/,
pk_recvstordoc nvarchar(20) null default '~' 
/*收货仓库*/,
pk_receiveaddress nvarchar(20) null default '~' 
/*收货地址*/,
dcorrectdate nvarchar(19) null 
/*修正日期*/,
chandler nvarchar(20) null default '~' 
/*操作员*/,
fisactive int null default 0 
/*激活*/,
csourcetypecode nvarchar(20) null default '~' 
/*来源单据类型*/,
csourceid nvarchar(20) null 
/*来源单据*/,
csourcebid nvarchar(20) null 
/*来源单据明细*/,
cfirsttypecode nvarchar(20) null default '~' 
/*源头单据类型*/,
cfirstid nvarchar(20) null 
/*源头单据*/,
cfirstbid nvarchar(20) null 
/*源头单据明细*/,
vbmemo nvarchar(181) null 
/*备注*/,
pk_batchcode nvarchar(20) null 
/*批次号主键*/,
vbatchcode nvarchar(40) null 
/*批次号*/,
nbackarrvnum decimal(28,8) null 
/*累计退货主数量*/,
nbackstorenum decimal(28,8) null 
/*累计退库主数量*/,
cqpbaseschemeid nvarchar(20) null default '~' 
/*优质优价方案*/,
ccontractrowid nvarchar(20) null 
/*合同明细*/,
ccontractid nvarchar(20) null default '~' 
/*合同信息*/,
vcontractcode nvarchar(40) null 
/*合同号*/,
vpriceauditcode nvarchar(30) null 
/*价格审批单号*/,
cpriceauditid nvarchar(20) null default '~' 
/*价格审批单*/,
cpriceaudit_bid nvarchar(20) null 
/*价格审批单存货明细*/,
cpriceaudit_bb1id nvarchar(20) null 
/*价格审批单存货子子表*/,
breceiveplan nchar(1) null default 'N' 
/*存在到货计划*/,
naccumrpnum decimal(28,8) null 
/*累计到货计划主数量*/,
blargess nchar(1) null default 'N' 
/*是否赠品*/,
cvenddevareaid nvarchar(20) null default '~' 
/*供应商发货地区*/,
cvenddevaddrid nvarchar(20) null default '~' 
/*供应商发货地点*/,
vvenddevaddr nvarchar(20) null default '~' 
/*供应商发货地址*/,
cdevareaid nvarchar(20) null default '~' 
/*收货地区*/,
cdevaddrid nvarchar(20) null default '~' 
/*收货地点*/,
naccumdevnum decimal(28,8) null 
/*累计运输主数量*/,
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
vvendinventorycode nvarchar(30) null 
/*对应物料编码*/,
vvendinventoryname nvarchar(60) null 
/*对应物料名称*/,
btransclosed nchar(1) null default 'N' 
/*是否运输关闭*/,
nfeemny decimal(28,8) null 
/*费用累计开票金额*/,
pk_psfinanceorg nvarchar(20) null default '~' 
/*结算财务组织*/,
pk_psfinanceorg_v nvarchar(20) null default '~' 
/*结算财务组织*/,
pk_apfinanceorg nvarchar(20) null default '~' 
/*应付组织*/,
pk_apfinanceorg_v nvarchar(20) null default '~' 
/*应付组织*/,
pk_apliabcenter nvarchar(20) null default '~' 
/*结算利润中心最新版本*/,
pk_apliabcenter_v nvarchar(20) null default '~' 
/*结算利润中心*/,
pk_arrliabcenter nvarchar(20) null default '~' 
/*收货利润中心最新版本*/,
pk_arrliabcenter_v nvarchar(20) null default '~' 
/*收货利润中心*/,
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
bborrowpur nchar(1) null default 'N' 
/*借入转采购*/,
vfirsttrantype nvarchar(20) null default '~' 
/*源头交易类型*/,
vfirstcode nvarchar(40) null 
/*源头单据号*/,
vfirstrowno nvarchar(20) null 
/*源头单据行号*/,
vsourcetrantype nvarchar(20) null default '~' 
/*来源交易类型*/,
vsourcecode nvarchar(40) null 
/*来源单据号*/,
vsourcerowno nvarchar(20) null 
/*来源单据行号*/,
naccuminvoicemny decimal(28,8) null default 0 
/*累计本币开票金额*/,
nacccancelinvmny decimal(28,8) null default 0 
/*累计已核销本币开票金额*/,
nweight decimal(28,8) null 
/*重量*/,
nvolumn decimal(28,8) null 
/*体积*/,
npacknum decimal(28,8) null 
/*件数*/,
bstockclose nchar(1) null default 'N' 
/*入库关闭*/,
binvoiceclose nchar(1) null default 'N' 
/*开票关闭*/,
barriveclose nchar(1) null default 'N' 
/*到货关闭*/,
bpayclose nchar(1) null default 'N' 
/*付款关闭*/,
ftaxtypeflag int null default 1 
/*扣税类别*/,
ntaxrate decimal(28,8) null 
/*税率*/,
ccurrencyid nvarchar(20) null default '~' 
/*本币币种*/,
nexchangerate decimal(28,8) null 
/*折本汇率*/,
ngroupexchgrate decimal(28,8) null 
/*集团本位币汇率*/,
nglobalexchgrate decimal(28,8) null 
/*全局本位币汇率*/,
ngroupmny decimal(28,8) null 
/*集团本币无税金额*/,
ngrouptaxmny decimal(28,8) null 
/*集团本币价税合计*/,
nglobalmny decimal(28,8) null 
/*全局本币无税金额*/,
nglobaltaxmny decimal(28,8) null 
/*全局本币价税合计*/,
cprojectid nvarchar(20) null default '~' 
/*项目*/,
cproductorid nvarchar(20) null default '~' 
/*生产厂商*/,
dbilldate nchar(19) null 
/*订单日期*/,
pk_supplier nvarchar(20) null default '~' 
/*供应商*/,
corigcurrencyid nvarchar(20) null default '~' 
/*币种*/,
cqualitylevelid nvarchar(20) null default '~' 
/*质量等级*/,
nsuprsnum decimal(28,8) null 
/*被预留数量*/,
pk_srcorder_b nchar(20) null 
/*修订来源订单明细*/,
pk_reqdept_v nvarchar(20) null default '~' 
/*需求部门*/,
nqtprice decimal(28,8) null 
/*本币无税单价*/,
nqttaxprice decimal(28,8) null 
/*本币含税单价*/,
nprice decimal(28,8) null 
/*主本币无税单价*/,
ntaxprice decimal(28,8) null 
/*主本币含税单价*/,
cecbillid nvarchar(20) null 
/*电子商务单据*/,
cecbillbid nvarchar(20) null 
/*电子商务单据明细*/,
vecbillcode nvarchar(40) null 
/*电子商务单据号*/,
cectypecode nvarchar(20) null default '~' 
/*电子商务单据类型*/,
casscustid nvarchar(20) null default '~' 
/*客户*/,
cprojecttaskid nvarchar(20) null default '~' 
/*项目任务*/,
csendcountryid nvarchar(20) null default '~' 
/*发货国家/地区*/,
crececountryid nvarchar(20) null default '~' 
/*收货国家/地区*/,
ctaxcountryid nvarchar(20) null default '~' 
/*报税国家/地区*/,
fbuysellflag int null 
/*购销类型*/,
btriatradeflag nchar(1) null 
/*三角贸易*/,
ctaxcodeid nvarchar(20) null default '~' 
/*税码*/,
nnosubtaxrate decimal(28,8) null 
/*不可抵扣税率*/,
nnosubtax decimal(28,8) null 
/*不可抵扣税额*/,
ncaltaxmny decimal(28,8) null 
/*计税金额*/,
ncalcostmny decimal(28,8) null 
/*计成本金额*/,
corigcountryid nvarchar(20) null default '~' 
/*原产国*/,
corigareaid nvarchar(20) null default '~' 
/*原产地区*/,
cdesticountryid nvarchar(20) null default '~' 
/*目的地国*/,
cdestiareaid nvarchar(20) null default '~' 
/*目的地地区*/,
pk_discount nvarchar(20) null default '~' 
/*折扣规则编码*/,
naccumpickupnum decimal(28,8) null 
/*累计拣货主数量*/,
cpraybillbid nvarchar(20) null 
/*请购单表体主键*/,
cpraybillcode nvarchar(40) null 
/*请购单号*/,
cpraybillhid nvarchar(20) null 
/*请购单表头主键*/,
cpraybillrowno nvarchar(20) null 
/*请购单行号*/,
cpraytypecode nvarchar(20) null 
/*请购单单据类型*/,
pk_order nchar(20) not null 
/*上层单据主键*/,
cffileid nvarchar(20) null 
/*特征码*/,
 constraint pk_po_order_b primary key (pk_order_b),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: po_order_b_ec_扩展表 */
create table po_order_b_ec (
norigordernum decimal(28,8) null 
/*原始订单数量*/,
norigorderprice decimal(28,8) null 
/*原始订单净含税单价*/,
dorigplanarrvdate nvarchar(19) null 
/*原始计划到货日期*/,
istorestatus int null 
/*供应商交货状态*/,
fneedpurtype int null 
/*应执采购类型*/,
factpurtype int null 
/*实执采购类型*/,
pk_cenpurule_b nvarchar(20) null 
/*集采控制规则子表*/,
pk_schedule nvarchar(20) null 
/*排程计划*/,
pk_schedule_b nvarchar(20) null 
/*排程计划明细*/,
nsendplannum decimal(28,8) null 
/*送货计划数量*/,
pk_order_b nchar(20) not null 
/*扩展表主键*/,
 constraint pk_po_order_b_ec primary key (pk_order_b),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 付款计划 */
create table po_order_payplan (
pk_order_payplan nchar(20) not null 
/*付款计划*/,
crowno nvarchar(20) null 
/*序号*/,
iaccounttermno int null 
/*账期号*/,
pk_paymentch nvarchar(20) null 
/*付款协议账期主键*/,
pk_payterm nvarchar(20) null default '~' 
/*付款协议*/,
feffdatetype nvarchar(20) null default '~' 
/*起算依据*/,
dbegindate nchar(19) null 
/*起算日期*/,
iitermdays int null 
/*账期天数*/,
denddate nchar(19) null 
/*账期到期日*/,
bpreflag nchar(1) null default 'N' 
/*预付款*/,
nrate decimal(28,8) null 
/*比率*/,
norigmny decimal(28,8) null 
/*原币金额*/,
nmny decimal(28,8) null 
/*本币金额*/,
corigcurrencyid nvarchar(20) null default '~' 
/*币种*/,
ccurrencyid nvarchar(20) null default '~' 
/*本币币种*/,
nexchangerate decimal(28,8) null 
/*折本汇率*/,
naccumpayapporgmny decimal(28,8) null 
/*累计付款申请金额*/,
naccumpayappmny decimal(28,8) null 
/*累计付款申请本币金额*/,
naccumpayorgmny decimal(28,8) null 
/*累计付款金额*/,
naccumpaymny decimal(28,8) null 
/*累计付款本币金额*/,
isdeposit nchar(1) null 
/*质保金*/,
pk_financeorg nvarchar(20) null default '~' 
/*应付财务组织*/,
pk_financeorg_v nvarchar(20) null default '~' 
/*应付财务组织*/,
ntotalorigmny decimal(28,8) null 
/*总付款金额*/,
ccontractid nvarchar(20) null default '~' 
/*合同号*/,
pk_group nvarchar(20) null default '~' 
/*集团*/,
pk_order nchar(20) not null 
/*上层单据主键*/,
 constraint pk_o_order_payplan primary key (pk_order_payplan),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 到货计划 */
create table po_order_bb1 (
pk_order_bb1 nvarchar(20) not null 
/*到货计划*/,
pk_group nvarchar(20) null default '~' 
/*所属集团*/,
pk_org nvarchar(20) null default '~' 
/*采购组织*/,
pk_org_v nvarchar(20) null default '~' 
/*采购组织*/,
vbillcode nvarchar(40) not null 
/*到货计划号*/,
pk_arrvstoorg nvarchar(20) null default '~' 
/*收货库存组织*/,
pk_arrvstoorg_v nvarchar(20) null default '~' 
/*收货库存组织*/,
pk_flowstockorg nvarchar(20) null default '~' 
/*物流组织*/,
pk_flowstockorg_v nvarchar(20) null default '~' 
/*物流组织*/,
pk_material nvarchar(20) null default '~' 
/*物料名称*/,
pk_srcmaterial nvarchar(20) null default '~' 
/*物料信息*/,
cunitid nvarchar(20) null default '~' 
/*主单位*/,
nnum decimal(28,8) null 
/*主数量*/,
castunitid nvarchar(20) null default '~' 
/*单位*/,
nastnum decimal(28,8) null 
/*数量*/,
vchangerate nvarchar(60) null 
/*换算率*/,
cqtunitid nvarchar(20) null default '~' 
/*报价单位*/,
nqtunitnum decimal(28,8) null 
/*报价数量*/,
vqtunitrate nvarchar(60) null 
/*报价单位换算率*/,
naccumarrvnum decimal(28,8) null 
/*累计到货主数量*/,
naccumstorenum decimal(28,8) null 
/*累计入库主数量*/,
naccumwastnum decimal(28,8) null 
/*累计途耗主数量*/,
dplanarrvdate nchar(19) null 
/*计划到货日期*/,
pk_recvstordoc nvarchar(20) null default '~' 
/*收货仓库*/,
pk_receiveaddress nvarchar(20) null default '~' 
/*收货地址*/,
fisactive int null default 0 
/*激活状态*/,
vmemo nvarchar(181) null 
/*备注*/,
pk_batchcode nvarchar(20) null 
/*批次号主键*/,
vbatchcode nvarchar(40) null 
/*批次号*/,
nbackarrvnum decimal(28,8) null 
/*累计退货主数量*/,
nbackstorenum decimal(28,8) null 
/*累计退库主数量*/,
blargess nchar(1) null 
/*是否赠品*/,
cvenddevareaid nvarchar(20) null default '~' 
/*供应商发货地区*/,
cvenddevaddrid nvarchar(20) null default '~' 
/*供应商发货地点*/,
vvenddevaddr nvarchar(20) null default '~' 
/*供应商发货地址*/,
cdevareaid nvarchar(20) null default '~' 
/*收货地区*/,
cdevaddrid nvarchar(20) null default '~' 
/*收货地点*/,
naccumdevnum decimal(28,8) null 
/*累计运输主数量*/,
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
btransclosed nchar(1) null 
/*是否运输关闭*/,
cvendorid nvarchar(20) null default '~' 
/*生产厂商*/,
nweight decimal(28,8) null 
/*重量*/,
nvolumn decimal(28,8) null 
/*体积*/,
npacknum decimal(28,8) null 
/*件数*/,
pk_order nchar(20) null 
/*采购订单*/,
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
vecbillcode nvarchar(40) null 
/*EC发货单号*/,
dsenddate nvarchar(19) null 
/*发货日期*/,
vehicletype nvarchar(100) null 
/*运输工具*/,
vehiclelicense nvarchar(100) null 
/*车牌号*/,
vlinkpsn nvarchar(100) null 
/*联系人*/,
vlinktype nvarchar(100) null 
/*联系方式*/,
pk_order_b nchar(20) not null 
/*上层单据主键*/,
 constraint pk_po_order_bb1 primary key (pk_order_bb1),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 付款账期 */
create table po_order_payment (
pk_payment nchar(20) not null 
/*付款账期*/,
showorder smallint not null 
/*付款期*/,
accrate decimal(16,4) not null 
/*付款比例*/,
prepayment nchar(1) null 
/*预付款*/,
pk_payperiod nvarchar(20) not null default '~' 
/*起效日期*/,
effectdateadddate int(5) null default 0 
/*起效日期延迟天数*/,
paymentday int(5) null default 0 
/*账期天数*/,
accountday int null 
/*出账日*/,
checkdata int(2) null 
/*固定结账日*/,
effectmonth int null 
/*生效月*/,
effectaddmonth int(5) null 
/*附加月*/,
pk_balatype nvarchar(20) null default '~' 
/*结算方式*/,
isdeposit nchar(1) null 
/*质保金*/,
pk_rate nvarchar(20) null default '~' 
/*现金折扣*/,
pk_order nchar(20) not null 
/*上层单据主键*/,
 constraint pk_o_order_payment primary key (pk_payment),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 采购订单 */
create table po_order (
pk_order nchar(20) not null 
/*采购订单*/,
pk_group nvarchar(20) null default '~' 
/*所属集团*/,
pk_org nvarchar(20) null default '~' 
/*采购组织*/,
pk_org_v nvarchar(20) null default '~' 
/*采购组织*/,
vbillcode nvarchar(40) null 
/*订单编号*/,
dbilldate nchar(19) null 
/*订单日期*/,
pk_freecust nvarchar(20) null default '~' 
/*散户*/,
pk_supplier nvarchar(20) not null default '~' 
/*供应商*/,
pk_bankdoc nvarchar(20) null default '~' 
/*开户银行*/,
pk_dept nvarchar(20) null default '~' 
/*采购部门*/,
cemployeeid nvarchar(20) null default '~' 
/*采购员*/,
vtrantypecode nvarchar(20) null default '~' 
/*订单类型编码*/,
pk_recvcustomer nvarchar(20) null default '~' 
/*收货客户*/,
pk_invcsupllier nvarchar(20) null default '~' 
/*开票供应商*/,
pk_deliveradd nvarchar(20) null default '~' 
/*供应商发货地址*/,
pk_transporttype nvarchar(20) null default '~' 
/*运输方式*/,
pk_payterm nvarchar(20) null default '~' 
/*付款协议*/,
billmaker nvarchar(20) null default '~' 
/*制单人*/,
dmakedate nchar(19) null 
/*制单日期*/,
forderstatus int null default 0 
/*单据状态*/,
approver nvarchar(20) null default '~' 
/*审批人*/,
vmemo nvarchar(181) null 
/*备注*/,
ccontracttextpath nvarchar(181) null 
/*合同附件*/,
norgprepaylimit decimal(28,8) null 
/*预付款限额*/,
nversion int null 
/*版本号*/,
bislatest nchar(1) null default 'Y' 
/*最新版本*/,
bisreplenish nchar(1) null default 'N' 
/*补货*/,
breturn nchar(1) null default 'N' 
/*退货*/,
iprintcount int null default 0 
/*打印次数*/,
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
taudittime nchar(19) null 
/*审批日期*/,
modifiedtime nvarchar(19) null 
/*最后修改时间*/,
crevisepsn nvarchar(20) null default '~' 
/*修订人*/,
trevisiontime nchar(19) null 
/*修订日期*/,
bcooptoso nchar(1) null 
/*已协同生成销售订单*/,
bsocooptome nchar(1) null default 'N' 
/*由销售订单协同生成*/,
vcoopordercode nvarchar(40) null 
/*对方订单号*/,
ntotalastnum decimal(28,8) null 
/*总数量*/,
ntotalorigmny decimal(28,8) null 
/*价税合计*/,
modifier nvarchar(20) null default '~' 
/*最后修改人*/,
pk_freezepsndoc nvarchar(20) null default '~' 
/*冻结人*/,
tfreezetime nchar(19) null 
/*冻结日期*/,
bfrozen nchar(1) null default 'N' 
/*冻结*/,
vfrozenreason nvarchar(100) null 
/*冻结原因*/,
pk_busitype nvarchar(20) null default '~' 
/*业务流程*/,
fhtaxtypeflag int null default 1 
/*整单扣税类别*/,
nhtaxrate decimal(28,8) null 
/*整单税率*/,
corigcurrencyid nvarchar(20) null default '~' 
/*币种*/,
brefwhenreturn nchar(1) null default 'N' 
/*退货/库基于原订单补货*/,
ntotalweight decimal(28,8) null 
/*总重量*/,
ntotalvolume decimal(28,8) null 
/*总体积*/,
ntotalpiece decimal(28,8) null 
/*总件数*/,
bfinalclose nchar(1) null default 'N' 
/*最终关闭*/,
creator nvarchar(20) null default '~' 
/*创建人*/,
pk_project nvarchar(20) null default '~' 
/*项目*/,
dclosedate nvarchar(19) null 
/*最终关闭日期*/,
pk_dept_v nvarchar(20) null default '~' 
/*采购部门*/,
ctrantypeid nvarchar(20) null default '~' 
/*订单类型*/,
ctradewordid nvarchar(20) null default '~' 
/*贸易术语*/,
pk_balatype nvarchar(20) null 
/*结算方式*/,
bdirect nchar(1) null 
/*直运采购*/,
 constraint pk_po_order primary key (pk_order),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: po_order_ec_扩展表 */
create table po_order_ec (
irespstatus int null 
/*响应状态*/,
vreason nvarchar(181) null 
/*拒绝/变更理由*/,
bpublish nchar(1) null default 'N' 
/*发布*/,
pk_pubpsn nvarchar(20) null default '~' 
/*发布人*/,
tpubtime nchar(19) null 
/*发布日期*/,
pk_resppsn nvarchar(20) null default '~' 
/*响应人*/,
tresptime nchar(19) null 
/*响应日期*/,
breleasedover nchar(1) null 
/*曾经发布*/,
pk_order nchar(20) not null 
/*扩展表主键*/,
 constraint pk_po_order_ec primary key (pk_order),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

