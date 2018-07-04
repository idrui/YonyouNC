/* tablename: 结算单明细 */
create table po_settlebill_b (pk_settlebill_b char(20) not null 
/*结算明细*/,
crowno varchar(20) null 
/*行号*/,
pk_group varchar(20) null 
/*所属集团*/,
pk_org varchar(20) null 
/*财务组织*/,
pk_org_v varchar(20) null 
/*财务组织版本*/,
frowtype integer null 
/*行类型*/,
vinvoicetrantype varchar(20) null 
/*发票交易类型*/,
pk_invoice varchar(20) null 
/*发票*/,
pk_invoice_b varchar(20) null 
/*发票行*/,
vinvoicecode varchar(40) null 
/*发票号*/,
vstockbilltype varchar(20) null 
/*库存单据类型*/,
vstocktrantype varchar(20) null 
/*入库单交易类型*/,
pk_stock varchar(20) null 
/*库存单据*/,
pk_stock_b varchar(20) null 
/*库存单据行*/,
vstockcode varchar(40) null 
/*入库单号/消耗汇总*/,
pk_stockorder varchar(20) null 
/*库存单据的订单头*/,
pk_stockorder_b varchar(20) null 
/*库存单据的订单体*/,
pk_invoiceorder varchar(20) null 
/*发票的订单头*/,
pk_invoiceorder_b varchar(20) null 
/*发票的订单体*/,
pk_dtransaleid varchar(20) null 
/*相应的直运销售订单*/,
pk_dtransalebid varchar(20) null 
/*相应的直运销售订单行*/,
pk_rushstock varchar(20) null 
/*对冲的库存单据*/,
pk_rushstock_b varchar(20) null 
/*对冲的库存单据行*/,
pk_rushinvoice varchar(20) null 
/*对冲的发票*/,
pk_rushinvoice_b varchar(20) null 
/*对冲的发票行*/,
pk_srcmaterial varchar(20) null 
/*物料*/,
pk_material varchar(20) null 
/*物料版本*/,
cunitid varchar(20) null 
/*主单位*/,
nsettlenum decimal(28,8) null 
/*结算数量*/,
nprice decimal(28,8) null 
/*单价*/,
nmoney decimal(28,8) null 
/*金额*/,
ngoodsprice decimal(28,8) null 
/*货物结算单价*/,
ngoodsmoney decimal(28,8) null 
/*货物结算金额*/,
bwashest char(1) null 
/*是否冲销暂估标志*/,
nclashestmoney decimal(28,8) null 
/*冲销暂估金额*/,
noppoconfmmoney decimal(28,8) null 
/*相应的确认成本*/,
noppoconfmapmny decimal(28,8) null 
/*相应的确认应付原币价税合计*/,
nreasonalwastnum decimal(28,8) null 
/*合理损耗数量*/,
nreasonalwastprice decimal(28,8) null 
/*合理损耗单价*/,
nreasonalwastmny decimal(28,8) null 
/*合理损耗金额*/,
pk_arrstockorg varchar(20) null 
/*收货库存组织*/,
pk_arrstockorg_v varchar(20) null 
/*收货库存组织版本*/,
pk_costregion varchar(20) null 
/*成本域*/,
ncostfactor1 decimal(28,8) null 
/*本币成本要素1*/,
ncostfactor2 decimal(28,8) null 
/*本币成本要素2*/,
ncostfactor3 decimal(28,8) null 
/*本币成本要素3*/,
ncostfactor4 decimal(28,8) null 
/*本币成本要素4*/,
ncostfactor5 decimal(28,8) null 
/*本币成本要素5*/,
ncostfactor6 decimal(28,8) null 
/*本币成本要素6*/,
ncostfactor7 decimal(28,8) null 
/*本币成本要素7*/,
ncostfactor8 decimal(28,8) null 
/*本币成本要素8*/,
ndiscount decimal(28,8) null 
/*折扣*/,
pk_supplier varchar(20) null 
/*供应商*/,
pk_dept varchar(20) null 
/*采购部门原始信息*/,
pk_dept_v varchar(20) null 
/*采购部门*/,
pk_psndoc varchar(20) null 
/*业务员*/,
pk_purchasein varchar(20) null 
/*采购入表头ID*/,
pk_purchasein_b varchar(20) null 
/*采购入表体ID*/,
pk_subcontract varchar(20) null 
/*委外入表头ID*/,
pk_subcontract_b varchar(20) null 
/*委外入表体ID*/,
pk_voiconsume varchar(20) null 
/*消耗汇总表头ID*/,
pk_voiconsume_b varchar(20) null 
/*消耗汇总表体ID*/,
pk_initialest varchar(20) null 
/*期初暂估表头ID*/,
pk_initialest_b varchar(20) null 
/*期初暂估表体ID*/,
pk_transin varchar(20) null 
/*调拨入表头ID*/,
pk_transin_b varchar(20) null 
/*调拨入表体ID*/,
pk_generalin varchar(20) null 
/*其他入表头ID*/,
pk_generalin_b varchar(20) null 
/*其他入表体ID*/,
pk_settlebill char(20) not null 
/*结算单头_主键*/,
nadjustmny decimal(28,8) null 
/*调整货物金额*/,
stockbilldate char(19) null 
/*入库日期*/,
invoicebilldate char(19) null 
/*开票日期*/,
vitctcode varchar(40) null 
/*进口合同号*/,
 constraint pk_po_settlebill_b primary key (pk_settlebill_b),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: 结算单头 */
create table po_settlebill (pk_settlebill char(20) not null 
/*结算单*/,
pk_group varchar(20) null 
/*所属集团*/,
pk_org varchar(20) null 
/*财务组织*/,
pk_org_v varchar(20) null 
/*财务组织版本*/,
ccurrencyid varchar(20) null 
/*本币币种*/,
bvirtualsettle char(1) null default 'N' 
/*是否虚拟发票的结算*/,
vbillcode varchar(40) null 
/*结算单号*/,
dbilldate char(19) null 
/*结算日期*/,
btoia char(1) null default 'N' 
/*已传存货*/,
iprintcount integer null default 0 
/*打印次数*/,
billmaker varchar(20) null 
/*制单人*/,
dmakedate char(19) null 
/*制单日期*/,
creationtime char(19) null 
/*创建时间*/,
creator varchar(20) null 
/*创建人*/,
fsettletype integer null 
/*结算单据类型*/,
 constraint pk_po_settlebill primary key (pk_settlebill),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: 结算费用分摊明细 */
create table po_settle_feeallot (pk_settle_feeallot char(20) not null 
/*费用分摊明细*/,
pk_settlebill char(20) null 
/*结算单头*/,
pk_srcmaterial varchar(20) null 
/*费用物料*/,
pk_material varchar(20) null 
/*费用物料版本*/,
vallotbilltype varchar(4) null 
/*费用分摊依据的单据类型*/,
pk_allotbillid varchar(20) null 
/*分摊的单据ID*/,
pk_allotbillbid varchar(20) null 
/*分摊的单据行ID*/,
nallotmoney decimal(28,8) null 
/*费用分摊金额*/,
bestfirstsettle char(1) null 
/*是否暂估费用物料的第一次结算*/,
ntimesafterfirst integer null 
/*第一次结算的后续累计费用结算次数*/,
pk_oppofee_b char(20) null 
/*相应的费用所在的结算行ID*/,
pk_supplier varchar(20) null 
/*供应商*/,
pk_settlebill_b char(20) not null 
/*结算单明细_主键*/,
nallotbillnum decimal(28,8) null 
/*分摊的依据数量*/,
nallotbillmny decimal(28,8) null 
/*分摊的依据金额*/,
 constraint pk_settle_feeallot primary key (pk_settle_feeallot),
 ts char(19) null,
dr smallint null default 0
)
;

