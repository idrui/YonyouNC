/* tablename: 消耗汇总财务 */
create table po_vmifi (pk_stockps_b char(20) not null 
/*消耗汇总财务主标识*/,
pk_stockps char(20) null 
/*消耗汇总财务辅标识*/,
pk_group varchar2(20) null 
/*集团*/,
pk_org varchar2(20) null 
/*公司*/,
pk_org_v varchar2(20) null 
/*公司版本*/,
pk_storeorg_v varchar2(20) null 
/*库存组织版本*/,
pk_storeorg varchar2(20) null 
/*库存组织*/,
pk_financeorg_v varchar2(20) null 
/*结算财务组织版本*/,
pk_financeorg varchar2(20) null 
/*结算财务组织*/,
pk_costregion varchar2(20) null 
/*成本域*/,
pk_srcmaterial varchar2(20) null 
/*物料最新版本*/,
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
/*汇总主数量*/,
ninassistnum number(28,8) null 
/*汇总数量*/,
cprojectid varchar2(20) null 
/*项目*/,
vchangerate varchar2(60) null 
/*换算率*/,
crowno varchar2(20) null 
/*行号*/,
cproductorid varchar2(20) null 
/*生产厂商*/,
cstateid varchar2(20) null 
/*库存状态*/,
cunitid varchar2(20) null 
/*主单位*/,
pk_supplier varchar2(20) null 
/*供应商*/,
ccurrencyid varchar2(20) null 
/*本位币*/,
nnetprice number(28,8) null 
/*主本币无税净价*/,
ntaxnetprice number(28,8) null 
/*主本币含税净价*/,
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
/*本币税额*/,
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
festtaxtype integer default 1 null 
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
naccpreeststtlmny number(28,8) null 
/*暂估前累计结算金额*/,
naccgoodssettlemny number(28,8) null 
/*累计货物结算金额*/,
naccsettlemny number(28,8) null 
/*累计结算金额*/,
naccfeesettlemny number(28,8) null 
/*累计费用结算金额*/,
vbillcode varchar2(40) null 
/*单据号*/,
dbilldate char(19) null 
/*汇总日期*/,
pk_stordoc varchar2(20) null 
/*仓库*/,
billmaker varchar2(20) null 
/*制单人*/,
modifier varchar2(20) null 
/*最后修改人*/,
modifiedtime char(19) null 
/*最后修改时间*/,
creationtime char(19) null 
/*创建时间*/,
creator varchar2(20) null 
/*创建人*/,
baffectcost char(1) null 
/*影响成本标志*/,
bnormpur char(1) null 
/*是否普通采购*/,
vtrantypecode varchar2(20) null 
/*交易类型编码*/,
pk_busitype varchar2(20) null 
/*业务流程*/,
vconsumebillcode varchar2(40) null 
/*消耗单据号*/,
cconsumehid varchar2(20) null 
/*消耗单据表头*/,
approver varchar2(20) null 
/*审批人*/,
taudittime char(19) null 
/*审批时间*/,
csumruleid varchar2(20) null 
/*消耗汇总规则ID*/,
vmimatchpurinrule varchar2(10) null 
/*消耗匹配采购入库的规则*/,
ctrantypeid varchar2(20) null 
/*交易类型*/,
casscustid varchar2(20) null 
/*客户*/,
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
cesttaxcodeid varchar2(20) null 
/*税码*/,
nestcaltaxmny number(28,8) null 
/*计税金额*/,
nestnosubtaxrate number(28,8) null 
/*不可抵扣税率*/,
nestnosubtax number(28,8) null 
/*不可抵扣税额*/,
nestcalcostmny number(28,8) null 
/*计成本金额*/,
bopptaxflag char(1) null 
/*逆向征税*/,
nestcalcostprice number(28,8) null 
/*记成本单价*/,
cffileid varchar2(20) null 
/*特征码*/,
 constraint pk_po_vmifi primary key (pk_stockps_b),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: 消耗汇总费用暂估分摊明细 */
create table po_vmifi_fd (pk_stockps_fd char(20) not null 
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
/*消耗汇总费用暂估明细_主键*/,
 constraint pk_po_vmifi_fd primary key (pk_stockps_fd),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: 消耗汇总费用暂估明细 */
create table po_vmifi_fee (pk_stockps_fee char(20) not null 
/*消耗汇总费用暂估明细*/,
pk_stockps char(20) null 
/*消耗汇总财务头*/,
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
/*暂估费用物料第一次结算的费用发票所在结算单*/,
pk_firstsettle_b varchar2(20) null 
/*暂估费用物料第一次结算的费用发票所在结算单行*/,
pk_stockps_b char(20) not null 
/*消耗汇总财务_主键*/,
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
 constraint pk_po_vmifi_fee primary key (pk_stockps_fee),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

