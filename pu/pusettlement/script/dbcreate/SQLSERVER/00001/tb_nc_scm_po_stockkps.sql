/* tablename: 采购入财务头 */
create table po_purchaseinfi (
pk_stockps nchar(20) not null 
/*采购入头ID*/,
pk_group nvarchar(20) null 
/*集团*/,
pk_org nvarchar(20) null 
/*库存组织*/,
pk_org_v nvarchar(20) null 
/*库存组织版本*/,
cbilltypecode nvarchar(20) null 
/*单据类型编码*/,
vtrantypecode nvarchar(20) null 
/*交易类型编码*/,
pk_busitype nvarchar(20) null 
/*业务流程*/,
vbillcode nvarchar(40) null 
/*入库单号*/,
dbilldate nchar(19) null 
/*入库日期*/,
pk_stordoc nvarchar(20) null 
/*仓库*/,
pk_dept nvarchar(20) null 
/*部门原始信息*/,
pk_dept_v nvarchar(20) null 
/*部门*/,
pk_psndoc nvarchar(20) null 
/*业务员*/,
cwhsmanagerid nvarchar(20) null 
/*库管员*/,
billmaker nvarchar(20) null 
/*制单人*/,
vnote nvarchar(181) null 
/*备注*/,
freplenishflag nchar(1) null 
/*退货标志*/,
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
creationtime nchar(19) null 
/*创建时间*/,
creator nvarchar(20) null 
/*创建人*/,
bnormpur nchar(1) null 
/*是否普通采购*/,
bautotofi nchar(1) null default 'N' 
/*自动传财务标志*/,
ctrantypeid nvarchar(20) null 
/*交易类型*/,
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
csendtypeid nvarchar(20) null 
/*运输方式*/,
ctradewordid nvarchar(20) null 
/*贸易术语*/,
bitinbill nchar(1) null 
/*进口入库单*/,
approver nvarchar(20) null 
/*签字人*/,
taudittime nchar(19) null 
/*签字日期*/,
 constraint pk_po_purchaseinfi primary key (pk_stockps),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 暂估费用分摊明细 */
create table po_purchaseinfi_fd (
pk_stockps_fd nchar(20) not null 
/*暂估费用分摊明细*/,
pk_billtype nvarchar(20) null 
/*分摊依据单据类型*/,
pk_distbybill nvarchar(20) null 
/*分摊依据单据*/,
pk_distbybill_b nvarchar(20) null 
/*分摊依据单据行*/,
ndistbynum decimal(28,8) null 
/*分摊依据数量*/,
ndistbymny decimal(28,8) null 
/*分摊依据金额*/,
ndistmny decimal(28,8) null 
/*分摊金额*/,
pk_iasrc_b nvarchar(20) null 
/*传成本标识*/,
pk_stockps_fee nchar(20) not null 
/*采购入费用暂估明细_主键*/,
 constraint pk_purchaseinfi_fd primary key (pk_stockps_fd),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 采购入财务体 */
create table po_purchaseinfi_b (
pk_stockps_b nchar(20) not null 
/*采购入体ID*/,
pk_group nvarchar(20) null 
/*集团*/,
pk_org nvarchar(20) null 
/*库存组织*/,
pk_org_v nvarchar(20) null 
/*库存组织版本*/,
pk_financeorg nvarchar(20) null 
/*结算财务组织*/,
pk_financeorg_v nvarchar(20) null 
/*结算财务组织版本*/,
pk_apfinanceorg nvarchar(20) null 
/*应付财务组织*/,
pk_apfinanceorg_v nvarchar(20) null 
/*应付财务组织版本*/,
pk_costregion nvarchar(20) null 
/*成本域*/,
pk_purchaseorg nvarchar(20) null 
/*采购组织*/,
pk_purchaseorg_v nvarchar(20) null 
/*采购组织版本*/,
pk_apliabcenter nvarchar(20) null 
/*结算利润中心最新版本*/,
pk_apliabcenter_v nvarchar(20) null 
/*结算利润中心*/,
pk_srcmaterial nvarchar(20) null 
/*物料*/,
pk_material nvarchar(20) null 
/*物料编码*/,
pk_batchcode nvarchar(20) null 
/*批次档案*/,
vbatchcode nvarchar(40) null 
/*批次号*/,
dbizdate nchar(19) null 
/*业务日期*/,
castunitid nvarchar(20) null 
/*单位*/,
ninnum decimal(28,8) null 
/*入库主数量*/,
ninassistnum decimal(28,8) null 
/*实入辅数量*/,
blargess nchar(1) null 
/*是否赠品*/,
cprojectid nvarchar(20) null 
/*项目*/,
vchangerate nvarchar(60) null 
/*换算率*/,
csourcetypecode nvarchar(20) null 
/*来源单据类型*/,
vsourcetrantype nvarchar(20) null 
/*来源交易类型*/,
csourceid nvarchar(20) null 
/*来源单据表头*/,
csourcebid nvarchar(20) null 
/*来源单据表体*/,
vsourcecode nvarchar(40) null 
/*来源单据号*/,
vsourcerowno nvarchar(20) null 
/*来源单据行号*/,
cfirsttypecode nvarchar(20) null 
/*源头单据类型*/,
vfirsttrantype nvarchar(20) null 
/*源头单据交易类型*/,
cfirstid nvarchar(20) null 
/*源头单据表头*/,
cfirstbid nvarchar(20) null 
/*源头单据表体*/,
vfirstcode nvarchar(40) null 
/*源头单据号*/,
vfirstrowno nvarchar(20) null 
/*源头单据行号*/,
dfirstbilldate nvarchar(19) null 
/*源头单据制单日期*/,
vnotebody nvarchar(181) null 
/*行备注*/,
creceieveid nvarchar(20) null 
/*收货单位*/,
crowno nvarchar(20) null 
/*行号*/,
drequiredate nvarchar(19) null 
/*需求日期*/,
pk_reqstockorg nvarchar(20) null 
/*需求库存组织*/,
pk_reqstockorg_v nvarchar(20) null 
/*需求库存组织版本*/,
pk_reqstocdoc nvarchar(20) null 
/*需求仓库*/,
vordertrantypecode nvarchar(50) null 
/*订单交易类型*/,
pk_order nvarchar(20) null 
/*订单*/,
pk_order_b nvarchar(20) null 
/*订单明细*/,
vordercode nvarchar(50) null 
/*订单号*/,
cproductorid nvarchar(20) null 
/*生产厂商*/,
cstateid nvarchar(20) null 
/*库存状态*/,
vctcode nvarchar(50) null 
/*采购合同号*/,
cunitid nvarchar(20) null 
/*主单位*/,
pk_supplier nvarchar(20) null 
/*供应商*/,
pk_vmisupplier nvarchar(20) null 
/*寄存供应商*/,
ncostprice decimal(28,8) null 
/*单价*/,
ncostmny decimal(28,8) null 
/*金额*/,
nplannedprice decimal(28,8) null 
/*计划单价*/,
nplannedmny decimal(28,8) null 
/*计划金额*/,
cqtunitid nvarchar(20) null 
/*报价单位*/,
nqtunitnum decimal(28,8) null 
/*报价数量*/,
vqtunitrate nvarchar(60) null 
/*报价单位换算率*/,
corigcurrencyid nvarchar(20) null 
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
ftaxtypeflag int null 
/*入库单扣税类别*/,
norigmny decimal(28,8) null 
/*入库单无税金额*/,
norigtaxmny decimal(28,8) null 
/*入库单价税合计*/,
ccurrencyid nvarchar(20) null 
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
pk_dtransaleid nvarchar(20) null 
/*相应的直运销售订单*/,
pk_dtransalebid nvarchar(20) null 
/*相应的直运销售订单行*/,
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
pk_costappsn nvarchar(20) null 
/*传成本和应付人*/,
dtocostapdate nvarchar(19) null 
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
pk_estcurrency nvarchar(20) null 
/*币种*/,
nestexhgrate decimal(28,8) null 
/*折本汇率*/,
nestomny decimal(28,8) null 
/*无税金额*/,
nestototalmny decimal(28,8) null 
/*价税合计*/,
festtaxtype int null 
/*扣税类别*/,
ftoiaflag int null default 0 
/*传成本标志*/,
ftoapflag int null default 0 
/*传应付标志*/,
bsettlefinish nchar(1) null default 'N' 
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
pk_finishsettle_b nvarchar(20) null 
/*结算完毕的结算单行明细*/,
naccpreeststtlmny decimal(28,8) null 
/*暂估前累计结算金额*/,
baffectcost nchar(1) null 
/*影响成本标志*/,
pk_stockps nchar(20) not null 
/*采购入财务头_主键*/,
casscustid nvarchar(20) null 
/*客户*/,
ctaxcodeid nvarchar(20) null 
/*入库单税码*/,
nnosubtaxrate decimal(28,8) null 
/*入库单不可抵扣税率*/,
nnosubtax decimal(28,8) null 
/*入库单不可抵扣税额*/,
ncaltaxmny decimal(28,8) null 
/*入库单计税金额*/,
ncalcostmny decimal(28,8) null 
/*入库单计成本金额*/,
bopptaxflag nchar(1) null 
/*逆向征税*/,
corigcountryid nvarchar(20) null 
/*入库单原产国*/,
corigareaid nvarchar(20) null 
/*入库单原产地区*/,
cdesticountryid nvarchar(20) null 
/*入库单目的地国*/,
cdestiareaid nvarchar(20) null 
/*入库单目的地地区*/,
cesttaxcodeid nvarchar(20) null 
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
cprojecttaskid nvarchar(20) null 
/*项目任务*/,
naccadjustmny decimal(28,8) null 
/*累计调整结算金额*/,
pk_arrliabcenter_v nvarchar(20) null 
/*收货利润中心*/,
pk_arrliabcenter nvarchar(20) null 
/*收货利润中心最新版本*/,
baffectpccost nchar(1) null 
/*影响利润中心成本*/,
cffileid nvarchar(20) null 
/*特征码*/,
 constraint pk__purchaseinfi_b primary key (pk_stockps_b),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 采购入费用暂估明细 */
create table po_purchaseinfi_fee (
pk_stockps_fee nchar(20) not null 
/*采购入费用暂估明细*/,
pk_stockps nchar(20) null 
/*采购入财务头*/,
pk_group nvarchar(20) null 
/*集团*/,
pk_financeorg nvarchar(20) null 
/*财务组织*/,
pk_supplier nvarchar(20) null 
/*供应商名称*/,
pk_costfactor nvarchar(20) null 
/*成本要素*/,
pk_feematerial nvarchar(20) null 
/*费用项*/,
pk_srcfeematerial nvarchar(20) null 
/*暂估费用物料*/,
pk_estpsn nvarchar(20) null 
/*暂估人*/,
destdate nchar(19) null 
/*暂估日期*/,
nestexchgrate decimal(28,8) null 
/*折本汇率*/,
btoia nchar(1) null default 'N' 
/*传成本标志*/,
btoap nchar(1) null default 'N' 
/*传应付标志*/,
nestmny decimal(28,8) null 
/*本币无税金额*/,
nesttaxmny decimal(28,8) null 
/*本币税额*/,
nesttaxrate decimal(28,8) null 
/*税率*/,
nesttotalmny decimal(28,8) null 
/*本币价税合计*/,
pk_estcurrency nvarchar(20) null 
/*币种*/,
pk_localcurrency nvarchar(20) null 
/*本币*/,
nestomny decimal(28,8) null 
/*原币无税金额*/,
nestototalmny decimal(28,8) null 
/*原币价税合计*/,
pk_firstsettle nvarchar(20) null 
/*暂估费用物料第一次结算的结算单*/,
pk_firstsettle_b nvarchar(20) null 
/*暂估费用物料第一次结算的结算单行*/,
pk_stockps_b nchar(20) not null 
/*采购入财务体_主键*/,
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
ftaxtypeflag int null 
/*扣税类别*/,
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
 constraint pk_urchaseinfi_fee primary key (pk_stockps_fee),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

