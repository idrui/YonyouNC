/* tablename: 采购入财务头 */
create table po_purchaseinfi (pk_stockps char(20) not null 
/*采购入头ID*/,
pk_group varchar(20) null 
/*集团*/,
pk_org varchar(20) null 
/*库存组织*/,
pk_org_v varchar(20) null 
/*库存组织版本*/,
cbilltypecode varchar(20) null 
/*单据类型编码*/,
vtrantypecode varchar(20) null 
/*交易类型编码*/,
pk_busitype varchar(20) null 
/*业务流程*/,
vbillcode varchar(40) null 
/*入库单号*/,
dbilldate char(19) null 
/*入库日期*/,
pk_stordoc varchar(20) null 
/*仓库*/,
pk_dept varchar(20) null 
/*部门原始信息*/,
pk_dept_v varchar(20) null 
/*部门*/,
pk_psndoc varchar(20) null 
/*业务员*/,
cwhsmanagerid varchar(20) null 
/*库管员*/,
billmaker varchar(20) null 
/*制单人*/,
vnote varchar(181) null 
/*备注*/,
freplenishflag char(1) null 
/*退货标志*/,
modifier varchar(20) null 
/*最后修改人*/,
modifiedtime char(19) null 
/*最后修改时间*/,
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
creationtime char(19) null 
/*创建时间*/,
creator varchar(20) null 
/*创建人*/,
bnormpur char(1) null 
/*是否普通采购*/,
bautotofi char(1) null default 'N' 
/*自动传财务标志*/,
ctrantypeid varchar(20) null 
/*交易类型*/,
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
csendtypeid varchar(20) null 
/*运输方式*/,
ctradewordid varchar(20) null 
/*贸易术语*/,
bitinbill char(1) null 
/*进口入库单*/,
approver varchar(20) null 
/*签字人*/,
taudittime char(19) null 
/*签字日期*/,
 constraint pk_po_purchaseinfi primary key (pk_stockps),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: 暂估费用分摊明细 */
create table po_purchaseinfi_fd (pk_stockps_fd char(20) not null 
/*暂估费用分摊明细*/,
pk_billtype varchar(20) null 
/*分摊依据单据类型*/,
pk_distbybill varchar(20) null 
/*分摊依据单据*/,
pk_distbybill_b varchar(20) null 
/*分摊依据单据行*/,
ndistbynum decimal(28,8) null 
/*分摊依据数量*/,
ndistbymny decimal(28,8) null 
/*分摊依据金额*/,
ndistmny decimal(28,8) null 
/*分摊金额*/,
pk_iasrc_b varchar(20) null 
/*传成本标识*/,
pk_stockps_fee char(20) not null 
/*采购入费用暂估明细_主键*/,
 constraint pk_purchaseinfi_fd primary key (pk_stockps_fd),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: 采购入财务体 */
create table po_purchaseinfi_b (pk_stockps_b char(20) not null 
/*采购入体ID*/,
pk_group varchar(20) null 
/*集团*/,
pk_org varchar(20) null 
/*库存组织*/,
pk_org_v varchar(20) null 
/*库存组织版本*/,
pk_financeorg varchar(20) null 
/*结算财务组织*/,
pk_financeorg_v varchar(20) null 
/*结算财务组织版本*/,
pk_apfinanceorg varchar(20) null 
/*应付财务组织*/,
pk_apfinanceorg_v varchar(20) null 
/*应付财务组织版本*/,
pk_costregion varchar(20) null 
/*成本域*/,
pk_purchaseorg varchar(20) null 
/*采购组织*/,
pk_purchaseorg_v varchar(20) null 
/*采购组织版本*/,
pk_apliabcenter varchar(20) null 
/*结算利润中心最新版本*/,
pk_apliabcenter_v varchar(20) null 
/*结算利润中心*/,
pk_srcmaterial varchar(20) null 
/*物料*/,
pk_material varchar(20) null 
/*物料编码*/,
pk_batchcode varchar(20) null 
/*批次档案*/,
vbatchcode varchar(40) null 
/*批次号*/,
dbizdate char(19) null 
/*业务日期*/,
castunitid varchar(20) null 
/*单位*/,
ninnum decimal(28,8) null 
/*入库主数量*/,
ninassistnum decimal(28,8) null 
/*实入辅数量*/,
blargess char(1) null 
/*是否赠品*/,
cprojectid varchar(20) null 
/*项目*/,
vchangerate varchar(60) null 
/*换算率*/,
csourcetypecode varchar(20) null 
/*来源单据类型*/,
vsourcetrantype varchar(20) null 
/*来源交易类型*/,
csourceid varchar(20) null 
/*来源单据表头*/,
csourcebid varchar(20) null 
/*来源单据表体*/,
vsourcecode varchar(40) null 
/*来源单据号*/,
vsourcerowno varchar(20) null 
/*来源单据行号*/,
cfirsttypecode varchar(20) null 
/*源头单据类型*/,
vfirsttrantype varchar(20) null 
/*源头单据交易类型*/,
cfirstid varchar(20) null 
/*源头单据表头*/,
cfirstbid varchar(20) null 
/*源头单据表体*/,
vfirstcode varchar(40) null 
/*源头单据号*/,
vfirstrowno varchar(20) null 
/*源头单据行号*/,
dfirstbilldate varchar(19) null 
/*源头单据制单日期*/,
vnotebody varchar(181) null 
/*行备注*/,
creceieveid varchar(20) null 
/*收货单位*/,
crowno varchar(20) null 
/*行号*/,
drequiredate varchar(19) null 
/*需求日期*/,
pk_reqstockorg varchar(20) null 
/*需求库存组织*/,
pk_reqstockorg_v varchar(20) null 
/*需求库存组织版本*/,
pk_reqstocdoc varchar(20) null 
/*需求仓库*/,
vordertrantypecode varchar(50) null 
/*订单交易类型*/,
pk_order varchar(20) null 
/*订单*/,
pk_order_b varchar(20) null 
/*订单明细*/,
vordercode varchar(50) null 
/*订单号*/,
cproductorid varchar(20) null 
/*生产厂商*/,
cstateid varchar(20) null 
/*库存状态*/,
vctcode varchar(50) null 
/*采购合同号*/,
cunitid varchar(20) null 
/*主单位*/,
pk_supplier varchar(20) null 
/*供应商*/,
pk_vmisupplier varchar(20) null 
/*寄存供应商*/,
ncostprice decimal(28,8) null 
/*单价*/,
ncostmny decimal(28,8) null 
/*金额*/,
nplannedprice decimal(28,8) null 
/*计划单价*/,
nplannedmny decimal(28,8) null 
/*计划金额*/,
cqtunitid varchar(20) null 
/*报价单位*/,
nqtunitnum decimal(28,8) null 
/*报价数量*/,
vqtunitrate varchar(60) null 
/*报价单位换算率*/,
corigcurrencyid varchar(20) null 
/*原币币种*/,
nqtorigprice decimal(28,8) null 
/*无税单价*/,
nqtorigtaxprice decimal(28,8) null 
/*含税单价*/,
norigprice decimal(28,8) null 
/*入库单主无税单价*/,
norigtaxprice decimal(28,8) null 
/*入库单主含税单价*/,
nqtorignetprice decimal(28,8) null 
/*无税净价*/,
nqtorigtaxnetprice decimal(28,8) null 
/*含税净价*/,
norignetprice decimal(28,8) null 
/*主无税净价*/,
norigtaxnetprice decimal(28,8) null 
/*主含税净价*/,
ntaxrate decimal(28,8) null 
/*入库单税率*/,
ftaxtypeflag integer null 
/*入库单扣税类别*/,
norigmny decimal(28,8) null 
/*入库单无税金额*/,
norigtaxmny decimal(28,8) null 
/*入库单价税合计*/,
ccurrencyid varchar(20) null 
/*本位币*/,
nchangestdrate decimal(28,8) null 
/*入库单折本汇率*/,
nprice decimal(28,8) null 
/*主本币无税单价*/,
ntaxprice decimal(28,8) null 
/*主本币含税单价*/,
nqtprice decimal(28,8) null 
/*本币无税单价*/,
nqttaxprice decimal(28,8) null 
/*本币含税单价*/,
nqtnetprice decimal(28,8) null 
/*本币无税净价*/,
nqttaxnetprice decimal(28,8) null 
/*本币含税净价*/,
nnetprice decimal(28,8) null 
/*主本币无税净价*/,
ntaxnetprice decimal(28,8) null 
/*主本币含税净价*/,
nmny decimal(28,8) null 
/*入库单本币无税金额*/,
ntaxmny decimal(28,8) null 
/*入库单本币价税合计*/,
ntax decimal(28,8) null 
/*入库单本币税额*/,
ngroupexchgrate decimal(28,8) null 
/*集团本位币汇率*/,
ngroupmny decimal(28,8) null 
/*集团本币无税金额*/,
ngrouptaxmny decimal(28,8) null 
/*集团本币价税合计*/,
nglobalexchgrate decimal(28,8) null 
/*全局本位币汇率*/,
nglobalmny decimal(28,8) null 
/*全局本币无税金额*/,
nglobaltaxmny decimal(28,8) null 
/*全局本币价税合计*/,
pk_dtransaleid varchar(20) null 
/*相应的直运销售订单*/,
pk_dtransalebid varchar(20) null 
/*相应的直运销售订单行*/,
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
pk_costappsn varchar(20) null 
/*传成本和应付人*/,
dtocostapdate varchar(19) null 
/*暂估日期*/,
nestnum decimal(28,8) null default 0 
/*暂估主数量*/,
nestprice decimal(28,8) null 
/*暂估单价*/,
nesttaxrate decimal(28,8) null 
/*税率*/,
nesttaxprice decimal(28,8) null 
/*暂估含税单价*/,
nestoprice decimal(28,8) null 
/*主无税单价*/,
nestotaxprice decimal(28,8) null 
/*主含税单价*/,
nesttaxmny decimal(28,8) null 
/*税额*/,
nestmny decimal(28,8) null 
/*本币无税金额*/,
nesttotalmny decimal(28,8) null 
/*本币价税合计*/,
pk_estcurrency varchar(20) null 
/*币种*/,
nestexhgrate decimal(28,8) null 
/*折本汇率*/,
nestomny decimal(28,8) null 
/*无税金额*/,
nestototalmny decimal(28,8) null 
/*价税合计*/,
festtaxtype integer null 
/*扣税类别*/,
ftoiaflag integer null default 0 
/*传成本标志*/,
ftoapflag integer null default 0 
/*传应付标志*/,
bsettlefinish char(1) null default 'N' 
/*是否结算完成*/,
naccumsettlenum decimal(28,8) null 
/*累计结算数量*/,
naccestcoststtlnum decimal(28,8) null 
/*暂估部分累计结算数量*/,
naccestcostwashmny decimal(28,8) null 
/*累计回冲暂估成本金额*/,
nacctocostadjstmny decimal(28,8) null 
/*累计调整确认成本金额*/,
nacctoapadjstotmny decimal(28,8) null 
/*累计调整确认应付原币价税合计*/,
naccgoodssettlemny decimal(28,8) null 
/*累计货物结算金额*/,
naccsettlemny decimal(28,8) null 
/*累计???算金额*/,
naccfeesettlemny decimal(28,8) null 
/*累计费用结算金额*/,
pk_finishsettle_b varchar(20) null 
/*结算完毕的结算单行明细*/,
naccpreeststtlmny decimal(28,8) null 
/*暂估前累计结算金额*/,
baffectcost char(1) null 
/*影响成本标志*/,
pk_stockps char(20) not null 
/*采购入财务头_主键*/,
casscustid varchar(20) null 
/*客户*/,
ctaxcodeid varchar(20) null 
/*入库单税码*/,
nnosubtaxrate decimal(28,8) null 
/*入库单不可抵扣税率*/,
nnosubtax decimal(28,8) null 
/*入库单不可抵扣税额*/,
ncaltaxmny decimal(28,8) null 
/*入库单计税金额*/,
ncalcostmny decimal(28,8) null 
/*入库单计成本金额*/,
bopptaxflag char(1) null 
/*逆向征税*/,
corigcountryid varchar(20) null 
/*入库单原产国*/,
corigareaid varchar(20) null 
/*入库单原产地区*/,
cdesticountryid varchar(20) null 
/*入库单目的地国*/,
cdestiareaid varchar(20) null 
/*入库单目的地地区*/,
cesttaxcodeid varchar(20) null 
/*税码*/,
nestnosubtaxrate decimal(28,8) null 
/*不可抵扣税率*/,
nestcaltaxmny decimal(28,8) null 
/*计税金额*/,
nestnosubtax decimal(28,8) null 
/*不可抵扣税额*/,
nestcalcostmny decimal(28,8) null 
/*计成本金额*/,
nestcalcostprice decimal(28,8) null 
/*记成本单价*/,
ncalcostprice decimal(28,8) null 
/*入库单记成本单价*/,
cprojecttaskid varchar(20) null 
/*项目任务*/,
naccadjustmny decimal(28,8) null 
/*累计调整结算金额*/,
pk_arrliabcenter_v varchar(20) null 
/*收货利润中心*/,
pk_arrliabcenter varchar(20) null 
/*收货利润中心最新版本*/,
baffectpccost char(1) null 
/*影响利润中心成本*/,
cffileid varchar(20) null 
/*特征码*/,
 constraint pk__purchaseinfi_b primary key (pk_stockps_b),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: 采购入费用暂估明细 */
create table po_purchaseinfi_fee (pk_stockps_fee char(20) not null 
/*采购入费用暂估明细*/,
pk_stockps char(20) null 
/*采购入财务头*/,
pk_group varchar(20) null 
/*集团*/,
pk_financeorg varchar(20) null 
/*财务组织*/,
pk_supplier varchar(20) null 
/*供应商名称*/,
pk_costfactor varchar(20) null 
/*成本要素*/,
pk_feematerial varchar(20) null 
/*费用项*/,
pk_srcfeematerial varchar(20) null 
/*暂估费用物料*/,
pk_estpsn varchar(20) null 
/*暂估人*/,
destdate char(19) null 
/*暂估日期*/,
nestexchgrate decimal(28,8) null 
/*折本汇率*/,
btoia char(1) null default 'N' 
/*传成本标志*/,
btoap char(1) null default 'N' 
/*传应付标志*/,
nestmny decimal(28,8) null 
/*本币无税金额*/,
nesttaxmny decimal(28,8) null 
/*本币税额*/,
nesttaxrate decimal(28,8) null 
/*税率*/,
nesttotalmny decimal(28,8) null 
/*本币价税合计*/,
pk_estcurrency varchar(20) null 
/*币种*/,
pk_localcurrency varchar(20) null 
/*本币*/,
nestomny decimal(28,8) null 
/*原币无税金额*/,
nestototalmny decimal(28,8) null 
/*原币价税合计*/,
pk_firstsettle varchar(20) null 
/*暂估费用物料第一次结算的结算单*/,
pk_firstsettle_b varchar(20) null 
/*暂估费用物料第一次结算的结算单行*/,
pk_stockps_b char(20) not null 
/*采购入财务体_主键*/,
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
ctaxcodeid varchar(20) null 
/*税码*/,
ftaxtypeflag integer null 
/*扣税类别*/,
ncaltaxmny decimal(28,8) null 
/*计税金额*/,
nnosubtaxrate decimal(28,8) null 
/*不可抵扣税率*/,
nnosubtax decimal(28,8) null 
/*不可抵扣税额*/,
ncalcostmny decimal(28,8) null 
/*计成本金额*/,
bopptaxflag char(1) null 
/*逆向征税*/,
 constraint pk_urchaseinfi_fee primary key (pk_stockps_fee),
 ts char(19) null,
dr smallint null default 0
)
;

