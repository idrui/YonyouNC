/* tablename: 采购入财务头 */
create table po_purchaseinfi (pk_stockps char(20) not null 
/*采购入头ID*/,
pk_group varchar2(20) null 
/*集团*/,
pk_org varchar2(20) null 
/*库存组织*/,
pk_org_v varchar2(20) null 
/*库存组织版本*/,
cbilltypecode varchar2(20) null 
/*单据类型编码*/,
vtrantypecode varchar2(20) null 
/*交易类型编码*/,
pk_busitype varchar2(20) null 
/*业务流程*/,
vbillcode varchar2(40) null 
/*入库单号*/,
dbilldate char(19) null 
/*入库日期*/,
pk_stordoc varchar2(20) null 
/*仓库*/,
pk_dept varchar2(20) null 
/*部门原始信息*/,
pk_dept_v varchar2(20) null 
/*部门*/,
pk_psndoc varchar2(20) null 
/*业务员*/,
cwhsmanagerid varchar2(20) null 
/*库管员*/,
billmaker varchar2(20) null 
/*制单人*/,
vnote varchar2(181) null 
/*备注*/,
freplenishflag char(1) null 
/*退货标志*/,
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
creationtime char(19) null 
/*创建时间*/,
creator varchar2(20) null 
/*创建人*/,
bnormpur char(1) null 
/*是否普通采购*/,
bautotofi char(1) default 'N' null 
/*自动传财务标志*/,
ctrantypeid varchar2(20) null 
/*交易类型*/,
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
csendtypeid varchar2(20) null 
/*运输方式*/,
ctradewordid varchar2(20) null 
/*贸易术语*/,
bitinbill char(1) null 
/*进口入库单*/,
approver varchar2(20) null 
/*签字人*/,
taudittime char(19) null 
/*签字日期*/,
 constraint pk_po_purchaseinfi primary key (pk_stockps),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: 暂估费用分摊明细 */
create table po_purchaseinfi_fd (pk_stockps_fd char(20) not null 
/*暂估费用分摊明细*/,
pk_billtype varchar2(20) null 
/*分摊依据单据类型*/,
pk_distbybill varchar2(20) null 
/*分摊依据单据*/,
pk_distbybill_b varchar2(20) null 
/*分摊依据单据行*/,
ndistbynum number(28,8) null 
/*分摊依据数量*/,
ndistbymny number(28,8) null 
/*分摊依据金额*/,
ndistmny number(28,8) null 
/*分摊金额*/,
pk_iasrc_b varchar2(20) null 
/*传成本标识*/,
pk_stockps_fee char(20) not null 
/*采购入费用暂估明细_主键*/,
 constraint pk_purchaseinfi_fd primary key (pk_stockps_fd),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: 采购入财务体 */
create table po_purchaseinfi_b (pk_stockps_b char(20) not null 
/*采购入体ID*/,
pk_group varchar2(20) null 
/*集团*/,
pk_org varchar2(20) null 
/*库存组织*/,
pk_org_v varchar2(20) null 
/*库存组织版本*/,
pk_financeorg varchar2(20) null 
/*结算财务组织*/,
pk_financeorg_v varchar2(20) null 
/*结算财务组织版本*/,
pk_apfinanceorg varchar2(20) null 
/*应付财务组织*/,
pk_apfinanceorg_v varchar2(20) null 
/*应付财务组织版本*/,
pk_costregion varchar2(20) null 
/*成本域*/,
pk_purchaseorg varchar2(20) null 
/*采购组织*/,
pk_purchaseorg_v varchar2(20) null 
/*采购组织版本*/,
pk_apliabcenter varchar2(20) null 
/*结算利润中心最新版本*/,
pk_apliabcenter_v varchar2(20) null 
/*结算利润中心*/,
pk_srcmaterial varchar2(20) null 
/*物料*/,
pk_material varchar2(20) null 
/*物料编码*/,
pk_batchcode varchar2(20) null 
/*批次档案*/,
vbatchcode varchar2(40) null 
/*批次号*/,
dbizdate char(19) null 
/*业务日期*/,
castunitid varchar2(20) null 
/*单位*/,
ninnum number(28,8) null 
/*入库主数量*/,
ninassistnum number(28,8) null 
/*实入辅数量*/,
blargess char(1) null 
/*是否赠品*/,
cprojectid varchar2(20) null 
/*项目*/,
vchangerate varchar2(60) null 
/*换算率*/,
csourcetypecode varchar2(20) null 
/*来源单据类型*/,
vsourcetrantype varchar2(20) null 
/*来源交易类型*/,
csourceid varchar2(20) null 
/*来源单据表头*/,
csourcebid varchar2(20) null 
/*来源单据表体*/,
vsourcecode varchar2(40) null 
/*来源单据号*/,
vsourcerowno varchar2(20) null 
/*来源单据行号*/,
cfirsttypecode varchar2(20) null 
/*源头单据类型*/,
vfirsttrantype varchar2(20) null 
/*源头单据交易类型*/,
cfirstid varchar2(20) null 
/*源头单据表头*/,
cfirstbid varchar2(20) null 
/*源头单据表体*/,
vfirstcode varchar2(40) null 
/*源头单据号*/,
vfirstrowno varchar2(20) null 
/*源头单据行号*/,
dfirstbilldate varchar2(19) null 
/*源头单据制单日期*/,
vnotebody varchar2(181) null 
/*行备注*/,
creceieveid varchar2(20) null 
/*收货单位*/,
crowno varchar2(20) null 
/*行号*/,
drequiredate varchar2(19) null 
/*需求日期*/,
pk_reqstockorg varchar2(20) null 
/*需求库存组织*/,
pk_reqstockorg_v varchar2(20) null 
/*需求库存组织版本*/,
pk_reqstocdoc varchar2(20) null 
/*需求仓库*/,
vordertrantypecode varchar2(50) null 
/*订单交易类型*/,
pk_order varchar2(20) null 
/*订单*/,
pk_order_b varchar2(20) null 
/*订单明细*/,
vordercode varchar2(50) null 
/*订单号*/,
cproductorid varchar2(20) null 
/*生产厂商*/,
cstateid varchar2(20) null 
/*库存状态*/,
vctcode varchar2(50) null 
/*采购合同号*/,
cunitid varchar2(20) null 
/*主单位*/,
pk_supplier varchar2(20) null 
/*供应商*/,
pk_vmisupplier varchar2(20) null 
/*寄存供应商*/,
ncostprice number(28,8) null 
/*单价*/,
ncostmny number(28,8) null 
/*金额*/,
nplannedprice number(28,8) null 
/*计划单价*/,
nplannedmny number(28,8) null 
/*计划金额*/,
cqtunitid varchar2(20) null 
/*报价单位*/,
nqtunitnum number(28,8) null 
/*报价数量*/,
vqtunitrate varchar2(60) null 
/*报价单位换算率*/,
corigcurrencyid varchar2(20) null 
/*原币币种*/,
nqtorigprice number(28,8) null 
/*无税单价*/,
nqtorigtaxprice number(28,8) null 
/*含税单价*/,
norigprice number(28,8) null 
/*入库单主无税单价*/,
norigtaxprice number(28,8) null 
/*入库单主含税单价*/,
nqtorignetprice number(28,8) null 
/*无税净价*/,
nqtorigtaxnetprice number(28,8) null 
/*含税净价*/,
norignetprice number(28,8) null 
/*主无税净价*/,
norigtaxnetprice number(28,8) null 
/*主含税净价*/,
ntaxrate number(28,8) null 
/*入库单税率*/,
ftaxtypeflag integer null 
/*入库单扣税类别*/,
norigmny number(28,8) null 
/*入库单无税金额*/,
norigtaxmny number(28,8) null 
/*入库单价税合计*/,
ccurrencyid varchar2(20) null 
/*本位币*/,
nchangestdrate number(28,8) null 
/*入库单折本汇率*/,
nprice number(28,8) null 
/*主本币无税单价*/,
ntaxprice number(28,8) null 
/*主本币含税单价*/,
nqtprice number(28,8) null 
/*本币无税单价*/,
nqttaxprice number(28,8) null 
/*本币含税单价*/,
nqtnetprice number(28,8) null 
/*本币无税净价*/,
nqttaxnetprice number(28,8) null 
/*本币含税净价*/,
nnetprice number(28,8) null 
/*主本币无税净价*/,
ntaxnetprice number(28,8) null 
/*主本币含税净价*/,
nmny number(28,8) null 
/*入库单本币无税金额*/,
ntaxmny number(28,8) null 
/*入库单本币价税合计*/,
ntax number(28,8) null 
/*入库单本币税额*/,
ngroupexchgrate number(28,8) null 
/*集团本位币汇率*/,
ngroupmny number(28,8) null 
/*集团本币无税金额*/,
ngrouptaxmny number(28,8) null 
/*集团本币价税合计*/,
nglobalexchgrate number(28,8) null 
/*全局本位币汇率*/,
nglobalmny number(28,8) null 
/*全局本币无税金额*/,
nglobaltaxmny number(28,8) null 
/*全局本币价税合计*/,
pk_dtransaleid varchar2(20) null 
/*相应的直运销售订单*/,
pk_dtransalebid varchar2(20) null 
/*相应的直运销售订单行*/,
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
pk_costappsn varchar2(20) null 
/*传成本和应付人*/,
dtocostapdate varchar2(19) null 
/*暂估日期*/,
nestnum number(28,8) default 0 null 
/*暂估主数量*/,
nestprice number(28,8) null 
/*暂估单价*/,
nesttaxrate number(28,8) null 
/*税率*/,
nesttaxprice number(28,8) null 
/*暂估含税单价*/,
nestoprice number(28,8) null 
/*主无税单价*/,
nestotaxprice number(28,8) null 
/*主含税单价*/,
nesttaxmny number(28,8) null 
/*税额*/,
nestmny number(28,8) null 
/*本币无税金额*/,
nesttotalmny number(28,8) null 
/*本币价税合计*/,
pk_estcurrency varchar2(20) null 
/*币种*/,
nestexhgrate number(28,8) null 
/*折本汇率*/,
nestomny number(28,8) null 
/*无税金额*/,
nestototalmny number(28,8) null 
/*价税合计*/,
festtaxtype integer null 
/*扣税类别*/,
ftoiaflag integer default 0 null 
/*传成本标志*/,
ftoapflag integer default 0 null 
/*传应付标志*/,
bsettlefinish char(1) default 'N' null 
/*是否结算完成*/,
naccumsettlenum number(28,8) null 
/*累计结算数量*/,
naccestcoststtlnum number(28,8) null 
/*暂估部分累计结算数量*/,
naccestcostwashmny number(28,8) null 
/*累计回冲暂估成本金额*/,
nacctocostadjstmny number(28,8) null 
/*累计调整确认成本金额*/,
nacctoapadjstotmny number(28,8) null 
/*累计调整确认应付原币价税合计*/,
naccgoodssettlemny number(28,8) null 
/*累计货物结算金额*/,
naccsettlemny number(28,8) null 
/*累计???算金额*/,
naccfeesettlemny number(28,8) null 
/*累计费用结算金额*/,
pk_finishsettle_b varchar2(20) null 
/*结算完毕的结算单行明细*/,
naccpreeststtlmny number(28,8) null 
/*暂估前累计结算金额*/,
baffectcost char(1) null 
/*影响成本标志*/,
pk_stockps char(20) not null 
/*采购入财务头_主键*/,
casscustid varchar2(20) null 
/*客户*/,
ctaxcodeid varchar2(20) null 
/*入库单税码*/,
nnosubtaxrate number(28,8) null 
/*入库单不可抵扣税率*/,
nnosubtax number(28,8) null 
/*入库单不可抵扣税额*/,
ncaltaxmny number(28,8) null 
/*入库单计税金额*/,
ncalcostmny number(28,8) null 
/*入库单计成本金额*/,
bopptaxflag char(1) null 
/*逆向征税*/,
corigcountryid varchar2(20) null 
/*入库单原产国*/,
corigareaid varchar2(20) null 
/*入库单原产地区*/,
cdesticountryid varchar2(20) null 
/*入库单目的地国*/,
cdestiareaid varchar2(20) null 
/*入库单目的地地区*/,
cesttaxcodeid varchar2(20) null 
/*税码*/,
nestnosubtaxrate number(28,8) null 
/*不可抵扣税率*/,
nestcaltaxmny number(28,8) null 
/*计税金额*/,
nestnosubtax number(28,8) null 
/*不可抵扣税额*/,
nestcalcostmny number(28,8) null 
/*计成本金额*/,
nestcalcostprice number(28,8) null 
/*记成本单价*/,
ncalcostprice number(28,8) null 
/*入库单记成本单价*/,
cprojecttaskid varchar2(20) null 
/*项目任务*/,
naccadjustmny number(28,8) null 
/*累计调整结算金额*/,
pk_arrliabcenter_v varchar2(20) null 
/*收货利润中心*/,
pk_arrliabcenter varchar2(20) null 
/*收货利润中心最新版本*/,
baffectpccost char(1) null 
/*影响利润中心成本*/,
cffileid varchar2(20) null 
/*特征码*/,
 constraint pk__purchaseinfi_b primary key (pk_stockps_b),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: 采购入费用暂估明细 */
create table po_purchaseinfi_fee (pk_stockps_fee char(20) not null 
/*采购入费用暂估明细*/,
pk_stockps char(20) null 
/*采购入财务头*/,
pk_group varchar2(20) null 
/*集团*/,
pk_financeorg varchar2(20) null 
/*财务组织*/,
pk_supplier varchar2(20) null 
/*供应商名称*/,
pk_costfactor varchar2(20) null 
/*成本要素*/,
pk_feematerial varchar2(20) null 
/*费用项*/,
pk_srcfeematerial varchar2(20) null 
/*暂估费用物料*/,
pk_estpsn varchar2(20) null 
/*暂估人*/,
destdate char(19) null 
/*暂估日期*/,
nestexchgrate number(28,8) null 
/*折本汇率*/,
btoia char(1) default 'N' null 
/*传成本标志*/,
btoap char(1) default 'N' null 
/*传应付标志*/,
nestmny number(28,8) null 
/*本币无税金额*/,
nesttaxmny number(28,8) null 
/*本币税额*/,
nesttaxrate number(28,8) null 
/*税率*/,
nesttotalmny number(28,8) null 
/*本币价税合计*/,
pk_estcurrency varchar2(20) null 
/*币种*/,
pk_localcurrency varchar2(20) null 
/*本币*/,
nestomny number(28,8) null 
/*原币无税金额*/,
nestototalmny number(28,8) null 
/*原币价税合计*/,
pk_firstsettle varchar2(20) null 
/*暂估费用物料第一次结算的结算单*/,
pk_firstsettle_b varchar2(20) null 
/*暂估费用物料第一次结算的结算单行*/,
pk_stockps_b char(20) not null 
/*采购入财务体_主键*/,
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
ftaxtypeflag integer null 
/*扣税类别*/,
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
 constraint pk_urchaseinfi_fee primary key (pk_stockps_fee),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

