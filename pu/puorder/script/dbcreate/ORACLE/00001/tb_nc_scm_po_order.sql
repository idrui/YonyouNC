/* tablename: 采购订单在途状态 */
create table po_order_bb (pk_order_bb char(20) not null 
/*采购订单在途状态*/,
pk_order char(20) null 
/*采购订单*/,
pk_org varchar2(20) default '~' null 
/*采购???织*/,
pk_org_v varchar2(20) default '~' null 
/*采购组织*/,
pk_group varchar2(20) default '~' null 
/*所属集团*/,
fonwaystatus integer null 
/*在途状态*/,
nonwaynum number(28,8) null 
/*在途数量*/,
vbillcode varchar2(40) null 
/*单据编号*/,
dbilldate char(19) null 
/*单据日期*/,
dplanarrvdate varchar2(19) null 
/*计划到货日期*/,
cloadport varchar2(20) default '~' null 
/*装船港口*/,
clandport varchar2(20) default '~' null 
/*到货港口*/,
cshipname varchar2(50) null 
/*船名*/,
cshipline varchar2(20) null 
/*船次号*/,
dplanfreightdate varchar2(19) null 
/*计划到港日期*/,
ccasecode varchar2(30) null 
/*货柜号*/,
ccarrier varchar2(20) default '~' null 
/*承运商*/,
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
vbdef21 varchar2(101) null 
/*自定义项21*/,
vbdef22 varchar2(101) null 
/*自定义项22*/,
vbdef23 varchar2(101) null 
/*自定义项23*/,
vbdef24 varchar2(101) null 
/*自定义项24*/,
vbdef25 varchar2(101) null 
/*自定义项25*/,
vbdef26 varchar2(101) null 
/*自定义项26*/,
vbdef27 varchar2(101) null 
/*自定义项27*/,
vbdef28 varchar2(101) null 
/*自定义项28*/,
vbdef29 varchar2(101) null 
/*自定义项29*/,
vbdef30 varchar2(101) null 
/*自定义项30*/,
vbdef31 varchar2(101) null 
/*自定义项31*/,
vbdef32 varchar2(101) null 
/*自定义项32*/,
vbdef33 varchar2(101) null 
/*自定义项33*/,
vbdef34 varchar2(101) null 
/*自定义项34*/,
vbdef35 varchar2(101) null 
/*自定义项35*/,
vbdef36 varchar2(101) null 
/*自定义项36*/,
vbdef37 varchar2(101) null 
/*自定义项37*/,
vbdef38 varchar2(101) null 
/*自定义项38*/,
vbdef39 varchar2(101) null 
/*自定义项39*/,
vbdef40 varchar2(101) null 
/*自定义项40*/,
vhdef1 varchar2(101) null 
/*表头自定义项1*/,
vhdef2 varchar2(101) null 
/*表头自定义项2*/,
vhdef3 varchar2(101) null 
/*表头自定义项3*/,
vhdef4 varchar2(101) null 
/*表头自定义项4*/,
vhdef5 varchar2(101) null 
/*表头自定义项5*/,
vhdef6 varchar2(101) null 
/*表头自定义项6*/,
vhdef7 varchar2(101) null 
/*表头自定义项7*/,
vhdef8 varchar2(101) null 
/*表头自定义项8*/,
vhdef9 varchar2(101) null 
/*表头自定义项9*/,
vhdef10 varchar2(101) null 
/*表头自定义项10*/,
vhdef11 varchar2(101) null 
/*表头自定义项11*/,
vhdef12 varchar2(101) null 
/*表头自定义项12*/,
vhdef13 varchar2(101) null 
/*表头自定义项13*/,
vhdef14 varchar2(101) null 
/*表头自定义项14*/,
vhdef15 varchar2(101) null 
/*表头自定义项15*/,
vhdef16 varchar2(101) null 
/*表头自定义项16*/,
vhdef17 varchar2(101) null 
/*表头自定义项17*/,
vhdef18 varchar2(101) null 
/*表头自定义项18*/,
vhdef19 varchar2(101) null 
/*表头自定义项19*/,
vhdef20 varchar2(101) null 
/*表头自定义项20*/,
vhdef21 varchar2(101) null 
/*表头自定义项21*/,
vhdef22 varchar2(101) null 
/*表头自定义项22*/,
vhdef23 varchar2(101) null 
/*表头自定义项23*/,
vhdef24 varchar2(101) null 
/*表头自定义项24*/,
vhdef25 varchar2(101) null 
/*表头自定义项25*/,
vhdef26 varchar2(101) null 
/*表头自定义项26*/,
vhdef27 varchar2(101) null 
/*表头自定义项27*/,
vhdef28 varchar2(101) null 
/*表头自定义项28*/,
vhdef29 varchar2(101) null 
/*表头自定义项29*/,
vhdef30 varchar2(101) null 
/*表头自定义项30*/,
vhdef31 varchar2(101) null 
/*表头自定义项31*/,
vhdef32 varchar2(101) null 
/*表头自定义项32*/,
vhdef33 varchar2(101) null 
/*表头自定义项33*/,
vhdef34 varchar2(101) null 
/*表头自定义项34*/,
vhdef35 varchar2(101) null 
/*表头自定义项35*/,
vhdef36 varchar2(101) null 
/*表头自定义项36*/,
vhdef37 varchar2(101) null 
/*表头自定义项37*/,
vhdef38 varchar2(101) null 
/*表头自定义项38*/,
vhdef39 varchar2(101) null 
/*表头自定义项39*/,
vhdef40 varchar2(101) null 
/*表头自定义项40*/,
isoperated char(1) null 
/*是否已操作*/,
vvendororderrow varchar2(30) null 
/*对方订单行号*/,
nmaxhandlenum number(28,8) null 
/*最大可操作数量*/,
pk_order_b char(20) not null 
/*上层单据主键*/,
 constraint pk_po_order_bb primary key (pk_order_bb),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: 采购订单明细 */
create table po_order_b (pk_order_b char(20) not null 
/*采购订单明细*/,
pk_group varchar2(20) default '~' null 
/*所属集团*/,
pk_org varchar2(20) default '~' null 
/*采购组织*/,
pk_org_v varchar2(20) default '~' null 
/*采购组织*/,
pk_reqcorp varchar2(20) default '~' null 
/*需求公司*/,
pk_reqstoorg varchar2(20) default '~' null 
/*需求库存组织*/,
pk_reqstoorg_v varchar2(20) default '~' null 
/*??求??存组织*/,
pk_reqstordoc varchar2(20) default '~' null 
/*需求仓库*/,
pk_arrvstoorg varchar2(20) default '~' null 
/*收货库存组织*/,
pk_arrvstoorg_v varchar2(20) default '~' null 
/*收货库存组织*/,
pk_reqdept varchar2(20) default '~' null 
/*需求部门最新版本*/,
pk_flowstockorg varchar2(20) default '~' null 
/*物流组织*/,
pk_flowstockorg_v varchar2(20) default '~' null 
/*物流组织*/,
crowno varchar2(20) null 
/*行号*/,
pk_material varchar2(20) default '~' not null 
/*物料名称*/,
pk_srcmaterial varchar2(20) default '~' not null 
/*物料信息*/,
cunitid varchar2(20) default '~' null 
/*主单位*/,
nnum number(28,8) null 
/*主数量*/,
castunitid varchar2(20) default '~' null 
/*单位*/,
nastnum number(28,8) null 
/*数量*/,
vchangerate varchar2(60) null 
/*换算率*/,
cqtunitid varchar2(20) default '~' null 
/*报价单位*/,
nqtunitnum number(28,8) null 
/*报价数量*/,
vqtunitrate varchar2(60) null 
/*报价换算率*/,
nqtorigprice number(28,8) null 
/*无税单价*/,
nqtorigtaxprice number(28,8) null 
/*含税单价*/,
nqtorignetprice number(28,8) null 
/*无税净价*/,
nqtorigtaxnetprc number(28,8) null 
/*含税净价*/,
nqtnetprice number(28,8) null 
/*本币无税净价*/,
nqttaxnetprice number(28,8) null 
/*本币含税净价*/,
nitemdiscountrate number(28,8) null 
/*折扣*/,
norigmny number(28,8) null 
/*无税金额*/,
norigtaxmny number(28,8) null 
/*价税合计*/,
nmny number(28,8) null 
/*本币无税金额*/,
ntaxmny number(28,8) null 
/*本币价税合计*/,
ntax number(28,8) null 
/*税额*/,
norigprice number(28,8) null 
/*主无税单价*/,
norigtaxprice number(28,8) null 
/*主含税单价*/,
norignetprice number(28,8) null 
/*主无税净价*/,
norigtaxnetprice number(28,8) null 
/*主含税净价*/,
nnetprice number(28,8) null 
/*主本币无税净价*/,
ntaxnetprice number(28,8) null 
/*主本币含税净价*/,
naccumarrvnum number(28,8) null 
/*累计到货主数量*/,
naccumstorenum number(28,8) null 
/*累计入库主数量*/,
naccuminvoicenum number(28,8) null 
/*累计开票主数量*/,
naccumwastnum number(28,8) null 
/*累计途耗主数量*/,
dplanarrvdate char(19) null 
/*计划到货日期*/,
pk_recvstordoc varchar2(20) default '~' null 
/*收货仓库*/,
pk_receiveaddress varchar2(20) default '~' null 
/*收货地址*/,
dcorrectdate varchar2(19) null 
/*修正日期*/,
chandler varchar2(20) default '~' null 
/*操作员*/,
fisactive integer default 0 null 
/*激活*/,
csourcetypecode varchar2(20) default '~' null 
/*来源单据类型*/,
csourceid varchar2(20) null 
/*来源单据*/,
csourcebid varchar2(20) null 
/*来源单据明细*/,
cfirsttypecode varchar2(20) default '~' null 
/*源头单据类型*/,
cfirstid varchar2(20) null 
/*源头单据*/,
cfirstbid varchar2(20) null 
/*源头单据明细*/,
vbmemo varchar2(181) null 
/*备注*/,
pk_batchcode varchar2(20) null 
/*批次号主键*/,
vbatchcode varchar2(40) null 
/*批次号*/,
nbackarrvnum number(28,8) null 
/*累计退货主数量*/,
nbackstorenum number(28,8) null 
/*累计退库主数量*/,
cqpbaseschemeid varchar2(20) default '~' null 
/*优质优价方案*/,
ccontractrowid varchar2(20) null 
/*合同明细*/,
ccontractid varchar2(20) default '~' null 
/*合同信息*/,
vcontractcode varchar2(40) null 
/*合同号*/,
vpriceauditcode varchar2(30) null 
/*价格审批单号*/,
cpriceauditid varchar2(20) default '~' null 
/*价格审批单*/,
cpriceaudit_bid varchar2(20) null 
/*价格审批单存货明细*/,
cpriceaudit_bb1id varchar2(20) null 
/*价格审批单存货子子表*/,
breceiveplan char(1) default 'N' null 
/*存在到货计划*/,
naccumrpnum number(28,8) null 
/*累计到货计划主数量*/,
blargess char(1) default 'N' null 
/*是否赠品*/,
cvenddevareaid varchar2(20) default '~' null 
/*供应商发货地区*/,
cvenddevaddrid varchar2(20) default '~' null 
/*供应商发货地点*/,
vvenddevaddr varchar2(20) default '~' null 
/*供应商发货地址*/,
cdevareaid varchar2(20) default '~' null 
/*收货地区*/,
cdevaddrid varchar2(20) default '~' null 
/*收货地点*/,
naccumdevnum number(28,8) null 
/*累计运输主数量*/,
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
vvendinventorycode varchar2(30) null 
/*对应物料编码*/,
vvendinventoryname varchar2(60) null 
/*对应物料名称*/,
btransclosed char(1) default 'N' null 
/*是否运输关闭*/,
nfeemny number(28,8) null 
/*费用累计开票金额*/,
pk_psfinanceorg varchar2(20) default '~' null 
/*结算财务组织*/,
pk_psfinanceorg_v varchar2(20) default '~' null 
/*结算财务组织*/,
pk_apfinanceorg varchar2(20) default '~' null 
/*应付组织*/,
pk_apfinanceorg_v varchar2(20) default '~' null 
/*应付组织*/,
pk_apliabcenter varchar2(20) default '~' null 
/*结算利润中心最新版本*/,
pk_apliabcenter_v varchar2(20) default '~' null 
/*结算利润中心*/,
pk_arrliabcenter varchar2(20) default '~' null 
/*收货利润中心最新版本*/,
pk_arrliabcenter_v varchar2(20) default '~' null 
/*收货利润中心*/,
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
bborrowpur char(1) default 'N' null 
/*借入转采购*/,
vfirsttrantype varchar2(20) default '~' null 
/*源头交易类型*/,
vfirstcode varchar2(40) null 
/*源头单据号*/,
vfirstrowno varchar2(20) null 
/*源头单据行号*/,
vsourcetrantype varchar2(20) default '~' null 
/*来源交易类型*/,
vsourcecode varchar2(40) null 
/*来源单据号*/,
vsourcerowno varchar2(20) null 
/*来源单据行号*/,
naccuminvoicemny number(28,8) default 0 null 
/*累计本币开票金额*/,
nacccancelinvmny number(28,8) default 0 null 
/*累计已核销本币开票金额*/,
nweight number(28,8) null 
/*重量*/,
nvolumn number(28,8) null 
/*体积*/,
npacknum number(28,8) null 
/*件数*/,
bstockclose char(1) default 'N' null 
/*入库关闭*/,
binvoiceclose char(1) default 'N' null 
/*开票关闭*/,
barriveclose char(1) default 'N' null 
/*到货关闭*/,
bpayclose char(1) default 'N' null 
/*付款关闭*/,
ftaxtypeflag integer default 1 null 
/*扣税类别*/,
ntaxrate number(28,8) null 
/*税率*/,
ccurrencyid varchar2(20) default '~' null 
/*本币币种*/,
nexchangerate number(28,8) null 
/*折本汇率*/,
ngroupexchgrate number(28,8) null 
/*集团本位币汇率*/,
nglobalexchgrate number(28,8) null 
/*全局本位币汇率*/,
ngroupmny number(28,8) null 
/*集团本币无税金额*/,
ngrouptaxmny number(28,8) null 
/*集团本币价税合计*/,
nglobalmny number(28,8) null 
/*全局本币无税金额*/,
nglobaltaxmny number(28,8) null 
/*全局本币价税合计*/,
cprojectid varchar2(20) default '~' null 
/*项目*/,
cproductorid varchar2(20) default '~' null 
/*生产厂商*/,
dbilldate char(19) null 
/*订单日期*/,
pk_supplier varchar2(20) default '~' null 
/*供应商*/,
corigcurrencyid varchar2(20) default '~' null 
/*币种*/,
cqualitylevelid varchar2(20) default '~' null 
/*质量等级*/,
nsuprsnum number(28,8) null 
/*被预留数量*/,
pk_srcorder_b char(20) null 
/*修订来源订单明细*/,
pk_reqdept_v varchar2(20) default '~' null 
/*需求部门*/,
nqtprice number(28,8) null 
/*本币无税单价*/,
nqttaxprice number(28,8) null 
/*本币含税单价*/,
nprice number(28,8) null 
/*主本币无税单价*/,
ntaxprice number(28,8) null 
/*主本币含税单价*/,
cecbillid varchar2(20) null 
/*电子商务单据*/,
cecbillbid varchar2(20) null 
/*电子商务单据明细*/,
vecbillcode varchar2(40) null 
/*电子商务单据号*/,
cectypecode varchar2(20) default '~' null 
/*电子商务单据类型*/,
casscustid varchar2(20) default '~' null 
/*客户*/,
cprojecttaskid varchar2(20) default '~' null 
/*项目任务*/,
csendcountryid varchar2(20) default '~' null 
/*发货国家/地区*/,
crececountryid varchar2(20) default '~' null 
/*收货国家/地区*/,
ctaxcountryid varchar2(20) default '~' null 
/*报税国家/地区*/,
fbuysellflag integer null 
/*购销类型*/,
btriatradeflag char(1) null 
/*三角贸易*/,
ctaxcodeid varchar2(20) default '~' null 
/*税码*/,
nnosubtaxrate number(28,8) null 
/*不可抵扣税率*/,
nnosubtax number(28,8) null 
/*不可抵扣税额*/,
ncaltaxmny number(28,8) null 
/*计税金额*/,
ncalcostmny number(28,8) null 
/*计成本金额*/,
corigcountryid varchar2(20) default '~' null 
/*原产国*/,
corigareaid varchar2(20) default '~' null 
/*原产地区*/,
cdesticountryid varchar2(20) default '~' null 
/*目的地国*/,
cdestiareaid varchar2(20) default '~' null 
/*目的地地区*/,
pk_discount varchar2(20) default '~' null 
/*折扣规则编码*/,
naccumpickupnum number(28,8) null 
/*累计拣货主数量*/,
cpraybillbid varchar2(20) null 
/*请购单表体主键*/,
cpraybillcode varchar2(40) null 
/*请购单号*/,
cpraybillhid varchar2(20) null 
/*请购单表头主键*/,
cpraybillrowno varchar2(20) null 
/*请购单行号*/,
cpraytypecode varchar2(20) null 
/*请购单单据类型*/,
pk_order char(20) not null 
/*上层单据主键*/,
cffileid varchar2(20) null 
/*特征码*/,
 constraint pk_po_order_b primary key (pk_order_b),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: po_order_b_ec_扩展表 */
create table po_order_b_ec (norigordernum number(28,8) null 
/*原始订单数量*/,
norigorderprice number(28,8) null 
/*原始订单净含税单价*/,
dorigplanarrvdate varchar2(19) null 
/*原始计划到货日期*/,
istorestatus integer null 
/*供应商交货状态*/,
fneedpurtype integer null 
/*应执采购类型*/,
factpurtype integer null 
/*实执采购类型*/,
pk_cenpurule_b varchar2(20) null 
/*集采控制规则子表*/,
pk_schedule varchar2(20) null 
/*排程计划*/,
pk_schedule_b varchar2(20) null 
/*排程计划明细*/,
nsendplannum number(28,8) null 
/*送货计划数量*/,
pk_order_b char(20) not null 
/*扩展表主键*/,
 constraint pk_po_order_b_ec primary key (pk_order_b),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: 付款计划 */
create table po_order_payplan (pk_order_payplan char(20) not null 
/*付款计划*/,
crowno varchar2(20) null 
/*序号*/,
iaccounttermno integer null 
/*账期号*/,
pk_paymentch varchar2(20) null 
/*付款协议账期主键*/,
pk_payterm varchar2(20) default '~' null 
/*付款协议*/,
feffdatetype varchar2(20) default '~' null 
/*起算依据*/,
dbegindate char(19) null 
/*起算日期*/,
iitermdays integer null 
/*账期天数*/,
denddate char(19) null 
/*账期到期日*/,
bpreflag char(1) default 'N' null 
/*预付款*/,
nrate number(28,8) null 
/*比率*/,
norigmny number(28,8) null 
/*原币金额*/,
nmny number(28,8) null 
/*本币金额*/,
corigcurrencyid varchar2(20) default '~' null 
/*币种*/,
ccurrencyid varchar2(20) default '~' null 
/*本币币种*/,
nexchangerate number(28,8) null 
/*折本汇率*/,
naccumpayapporgmny number(28,8) null 
/*累计付款申请金额*/,
naccumpayappmny number(28,8) null 
/*累计付款申请本币金额*/,
naccumpayorgmny number(28,8) null 
/*累计付款金额*/,
naccumpaymny number(28,8) null 
/*累计付款本币金额*/,
isdeposit char(1) null 
/*质保金*/,
pk_financeorg varchar2(20) default '~' null 
/*应付财务组织*/,
pk_financeorg_v varchar2(20) default '~' null 
/*应付财务组织*/,
ntotalorigmny number(28,8) null 
/*总付款金额*/,
ccontractid varchar2(20) default '~' null 
/*合同号*/,
pk_group varchar2(20) default '~' null 
/*集团*/,
pk_order char(20) not null 
/*上层单据主键*/,
 constraint pk_o_order_payplan primary key (pk_order_payplan),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: 到货计划 */
create table po_order_bb1 (pk_order_bb1 varchar2(20) not null 
/*到货计划*/,
pk_group varchar2(20) default '~' null 
/*所属集团*/,
pk_org varchar2(20) default '~' null 
/*采购组织*/,
pk_org_v varchar2(20) default '~' null 
/*采购组织*/,
vbillcode varchar2(40) not null 
/*到货计划号*/,
pk_arrvstoorg varchar2(20) default '~' null 
/*收货库存组织*/,
pk_arrvstoorg_v varchar2(20) default '~' null 
/*收货库存组织*/,
pk_flowstockorg varchar2(20) default '~' null 
/*物流组织*/,
pk_flowstockorg_v varchar2(20) default '~' null 
/*物流组织*/,
pk_material varchar2(20) default '~' null 
/*物料名称*/,
pk_srcmaterial varchar2(20) default '~' null 
/*物料信息*/,
cunitid varchar2(20) default '~' null 
/*主单位*/,
nnum number(28,8) null 
/*主数量*/,
castunitid varchar2(20) default '~' null 
/*单位*/,
nastnum number(28,8) null 
/*数量*/,
vchangerate varchar2(60) null 
/*换算率*/,
cqtunitid varchar2(20) default '~' null 
/*报价单位*/,
nqtunitnum number(28,8) null 
/*报价数量*/,
vqtunitrate varchar2(60) null 
/*报价单位换算率*/,
naccumarrvnum number(28,8) null 
/*累计到货主数量*/,
naccumstorenum number(28,8) null 
/*累计入库主数量*/,
naccumwastnum number(28,8) null 
/*累计途耗主数量*/,
dplanarrvdate char(19) null 
/*计划到货日期*/,
pk_recvstordoc varchar2(20) default '~' null 
/*收货仓库*/,
pk_receiveaddress varchar2(20) default '~' null 
/*收货地址*/,
fisactive integer default 0 null 
/*激活状态*/,
vmemo varchar2(181) null 
/*备注*/,
pk_batchcode varchar2(20) null 
/*批次号主键*/,
vbatchcode varchar2(40) null 
/*批次号*/,
nbackarrvnum number(28,8) null 
/*累计退货主数量*/,
nbackstorenum number(28,8) null 
/*累计退库主数量*/,
blargess char(1) null 
/*是否赠品*/,
cvenddevareaid varchar2(20) default '~' null 
/*供应商发货地区*/,
cvenddevaddrid varchar2(20) default '~' null 
/*供应商发货地点*/,
vvenddevaddr varchar2(20) default '~' null 
/*供应商发货地址*/,
cdevareaid varchar2(20) default '~' null 
/*收货地区*/,
cdevaddrid varchar2(20) default '~' null 
/*收货地点*/,
naccumdevnum number(28,8) null 
/*累计运输主数量*/,
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
btransclosed char(1) null 
/*是否运输关闭*/,
cvendorid varchar2(20) default '~' null 
/*生产厂商*/,
nweight number(28,8) null 
/*重量*/,
nvolumn number(28,8) null 
/*体积*/,
npacknum number(28,8) null 
/*件数*/,
pk_order char(20) null 
/*采购订单*/,
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
vecbillcode varchar2(40) null 
/*EC发货单号*/,
dsenddate varchar2(19) null 
/*发货日期*/,
vehicletype varchar2(100) null 
/*运输工具*/,
vehiclelicense varchar2(100) null 
/*车牌号*/,
vlinkpsn varchar2(100) null 
/*联系人*/,
vlinktype varchar2(100) null 
/*联系方式*/,
pk_order_b char(20) not null 
/*上层单据主键*/,
 constraint pk_po_order_bb1 primary key (pk_order_bb1),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: 付款账期 */
create table po_order_payment (pk_payment char(20) not null 
/*付款账期*/,
showorder smallint not null 
/*付款期*/,
accrate number(16,4) not null 
/*付款比例*/,
prepayment char(1) null 
/*预付款*/,
pk_payperiod varchar2(20) default '~' not null 
/*起效日期*/,
effectdateadddate int(5) default 0 null 
/*起效日期延迟天数*/,
paymentday int(5) default 0 null 
/*账期天数*/,
accountday integer null 
/*出账日*/,
checkdata int(2) null 
/*固定结账日*/,
effectmonth integer null 
/*生效月*/,
effectaddmonth int(5) null 
/*附加月*/,
pk_balatype varchar2(20) default '~' null 
/*结算方式*/,
isdeposit char(1) null 
/*质保金*/,
pk_rate varchar2(20) default '~' null 
/*现金折扣*/,
pk_order char(20) not null 
/*上层单据主键*/,
 constraint pk_o_order_payment primary key (pk_payment),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: 采购订单 */
create table po_order (pk_order char(20) not null 
/*采购订单*/,
pk_group varchar2(20) default '~' null 
/*所属集团*/,
pk_org varchar2(20) default '~' null 
/*采购组织*/,
pk_org_v varchar2(20) default '~' null 
/*采购组织*/,
vbillcode varchar2(40) null 
/*订单编号*/,
dbilldate char(19) null 
/*订单日期*/,
pk_freecust varchar2(20) default '~' null 
/*散户*/,
pk_supplier varchar2(20) default '~' not null 
/*供应商*/,
pk_bankdoc varchar2(20) default '~' null 
/*开户银行*/,
pk_dept varchar2(20) default '~' null 
/*采购部门*/,
cemployeeid varchar2(20) default '~' null 
/*采购员*/,
vtrantypecode varchar2(20) default '~' null 
/*订单类型编码*/,
pk_recvcustomer varchar2(20) default '~' null 
/*收货客户*/,
pk_invcsupllier varchar2(20) default '~' null 
/*开票供应商*/,
pk_deliveradd varchar2(20) default '~' null 
/*供应商发货地址*/,
pk_transporttype varchar2(20) default '~' null 
/*运输方式*/,
pk_payterm varchar2(20) default '~' null 
/*付款协议*/,
billmaker varchar2(20) default '~' null 
/*制单人*/,
dmakedate char(19) null 
/*制单日期*/,
forderstatus integer default 0 null 
/*单据状态*/,
approver varchar2(20) default '~' null 
/*审批人*/,
vmemo varchar2(181) null 
/*备注*/,
ccontracttextpath varchar2(181) null 
/*合同附件*/,
norgprepaylimit number(28,8) null 
/*预付款限额*/,
nversion integer null 
/*版本号*/,
bislatest char(1) default 'Y' null 
/*最新版本*/,
bisreplenish char(1) default 'N' null 
/*补货*/,
breturn char(1) default 'N' null 
/*退货*/,
iprintcount integer default 0 null 
/*打印次数*/,
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
taudittime char(19) null 
/*审批日期*/,
modifiedtime varchar2(19) null 
/*最后修改时间*/,
crevisepsn varchar2(20) default '~' null 
/*修订人*/,
trevisiontime char(19) null 
/*修订日期*/,
bcooptoso char(1) null 
/*已协同生成销售订单*/,
bsocooptome char(1) default 'N' null 
/*由销售订单协同生成*/,
vcoopordercode varchar2(40) null 
/*对方订单号*/,
ntotalastnum number(28,8) null 
/*总数量*/,
ntotalorigmny number(28,8) null 
/*价税合计*/,
modifier varchar2(20) default '~' null 
/*最后修改人*/,
pk_freezepsndoc varchar2(20) default '~' null 
/*冻结人*/,
tfreezetime char(19) null 
/*冻结日期*/,
bfrozen char(1) default 'N' null 
/*冻结*/,
vfrozenreason varchar2(100) null 
/*冻结原因*/,
pk_busitype varchar2(20) default '~' null 
/*业务流程*/,
fhtaxtypeflag integer default 1 null 
/*整单扣税类别*/,
nhtaxrate number(28,8) null 
/*整单税率*/,
corigcurrencyid varchar2(20) default '~' null 
/*币种*/,
brefwhenreturn char(1) default 'N' null 
/*退货/库基于原订单补货*/,
ntotalweight number(28,8) null 
/*总重量*/,
ntotalvolume number(28,8) null 
/*总体积*/,
ntotalpiece number(28,8) null 
/*总件数*/,
bfinalclose char(1) default 'N' null 
/*最终关闭*/,
creator varchar2(20) default '~' null 
/*创建人*/,
pk_project varchar2(20) default '~' null 
/*项目*/,
dclosedate varchar2(19) null 
/*最终关闭日期*/,
pk_dept_v varchar2(20) default '~' null 
/*采购部门*/,
ctrantypeid varchar2(20) default '~' null 
/*订单类型*/,
ctradewordid varchar2(20) default '~' null 
/*贸易术语*/,
pk_balatype varchar2(20) null 
/*结算方式*/,
bdirect char(1) null 
/*直运采购*/,
 constraint pk_po_order primary key (pk_order),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: po_order_ec_扩展表 */
create table po_order_ec (irespstatus integer null 
/*响应状态*/,
vreason varchar2(181) null 
/*拒绝/变更理由*/,
bpublish char(1) default 'N' null 
/*发布*/,
pk_pubpsn varchar2(20) default '~' null 
/*发布人*/,
tpubtime char(19) null 
/*发布日期*/,
pk_resppsn varchar2(20) default '~' null 
/*响应人*/,
tresptime char(19) null 
/*响应日期*/,
breleasedover char(1) null 
/*曾经发布*/,
pk_order char(20) not null 
/*扩展表主键*/,
 constraint pk_po_order_ec primary key (pk_order),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

