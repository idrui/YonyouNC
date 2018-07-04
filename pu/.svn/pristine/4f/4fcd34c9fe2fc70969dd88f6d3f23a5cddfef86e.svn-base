/* tablename: 结算单明细 */
create table po_settlebill_b (pk_settlebill_b char(20) not null 
/*结算明细*/,
crowno varchar2(20) null 
/*行号*/,
pk_group varchar2(20) null 
/*所属集团*/,
pk_org varchar2(20) null 
/*财务组织*/,
pk_org_v varchar2(20) null 
/*财务组织版本*/,
frowtype integer null 
/*行类型*/,
vinvoicetrantype varchar2(20) null 
/*发票交易类型*/,
pk_invoice varchar2(20) null 
/*发票*/,
pk_invoice_b varchar2(20) null 
/*发票行*/,
vinvoicecode varchar2(40) null 
/*发票号*/,
vstockbilltype varchar2(20) null 
/*库存单据类型*/,
vstocktrantype varchar2(20) null 
/*入库单交易类型*/,
pk_stock varchar2(20) null 
/*库存单据*/,
pk_stock_b varchar2(20) null 
/*库存单据行*/,
vstockcode varchar2(40) null 
/*入库单号/消耗汇总*/,
pk_stockorder varchar2(20) null 
/*库存单据的订单头*/,
pk_stockorder_b varchar2(20) null 
/*库存单据的订单体*/,
pk_invoiceorder varchar2(20) null 
/*发票的订单头*/,
pk_invoiceorder_b varchar2(20) null 
/*发票的订单体*/,
pk_dtransaleid varchar2(20) null 
/*相应的直运销售订单*/,
pk_dtransalebid varchar2(20) null 
/*相应的直运销售订单行*/,
pk_rushstock varchar2(20) null 
/*对冲的库存单据*/,
pk_rushstock_b varchar2(20) null 
/*对冲的库存单据行*/,
pk_rushinvoice varchar2(20) null 
/*对冲的发票*/,
pk_rushinvoice_b varchar2(20) null 
/*对冲的发票行*/,
pk_srcmaterial varchar2(20) null 
/*物料*/,
pk_material varchar2(20) null 
/*物料版本*/,
cunitid varchar2(20) null 
/*主单位*/,
nsettlenum number(28,8) null 
/*结算数量*/,
nprice number(28,8) null 
/*单价*/,
nmoney number(28,8) null 
/*金额*/,
ngoodsprice number(28,8) null 
/*货物结算单价*/,
ngoodsmoney number(28,8) null 
/*货物结算金额*/,
bwashest char(1) null 
/*是否冲销暂估标志*/,
nclashestmoney number(28,8) null 
/*冲销暂估金额*/,
noppoconfmmoney number(28,8) null 
/*相应的确认成本*/,
noppoconfmapmny number(28,8) null 
/*相应的确认应付原币价税合计*/,
nreasonalwastnum number(28,8) null 
/*合理损耗数量*/,
nreasonalwastprice number(28,8) null 
/*合理损耗单价*/,
nreasonalwastmny number(28,8) null 
/*合理损耗金额*/,
pk_arrstockorg varchar2(20) null 
/*收货库存组织*/,
pk_arrstockorg_v varchar2(20) null 
/*收货库存组织版本*/,
pk_costregion varchar2(20) null 
/*成本域*/,
ncostfactor1 number(28,8) null 
/*本币成本要素1*/,
ncostfactor2 number(28,8) null 
/*本币成本要素2*/,
ncostfactor3 number(28,8) null 
/*本币成本要素3*/,
ncostfactor4 number(28,8) null 
/*本币成本要素4*/,
ncostfactor5 number(28,8) null 
/*本币成本要素5*/,
ncostfactor6 number(28,8) null 
/*本币成本要素6*/,
ncostfactor7 number(28,8) null 
/*本币成本要素7*/,
ncostfactor8 number(28,8) null 
/*本币成本要素8*/,
ndiscount number(28,8) null 
/*折扣*/,
pk_supplier varchar2(20) null 
/*供应商*/,
pk_dept varchar2(20) null 
/*采购部门原始信息*/,
pk_dept_v varchar2(20) null 
/*采购部门*/,
pk_psndoc varchar2(20) null 
/*业务员*/,
pk_purchasein varchar2(20) null 
/*采购入表头ID*/,
pk_purchasein_b varchar2(20) null 
/*采购入表体ID*/,
pk_subcontract varchar2(20) null 
/*委外入表头ID*/,
pk_subcontract_b varchar2(20) null 
/*委外入表体ID*/,
pk_voiconsume varchar2(20) null 
/*消耗汇总表头ID*/,
pk_voiconsume_b varchar2(20) null 
/*消耗汇总表体ID*/,
pk_initialest varchar2(20) null 
/*期初暂估表头ID*/,
pk_initialest_b varchar2(20) null 
/*期初暂估表体ID*/,
pk_transin varchar2(20) null 
/*调拨入表头ID*/,
pk_transin_b varchar2(20) null 
/*调拨入表体ID*/,
pk_generalin varchar2(20) null 
/*其他入表头ID*/,
pk_generalin_b varchar2(20) null 
/*其他入表体ID*/,
pk_settlebill char(20) not null 
/*结算单头_主键*/,
nadjustmny number(28,8) null 
/*调整货物金额*/,
stockbilldate char(19) null 
/*入库日期*/,
invoicebilldate char(19) null 
/*开票日期*/,
vitctcode varchar2(40) null 
/*进口合同号*/,
 constraint pk_po_settlebill_b primary key (pk_settlebill_b),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: 结算单头 */
create table po_settlebill (pk_settlebill char(20) not null 
/*结算单*/,
pk_group varchar2(20) null 
/*所属集团*/,
pk_org varchar2(20) null 
/*财务组织*/,
pk_org_v varchar2(20) null 
/*财务组织版本*/,
ccurrencyid varchar2(20) null 
/*本币币种*/,
bvirtualsettle char(1) default 'N' null 
/*是否虚拟发票的结算*/,
vbillcode varchar2(40) null 
/*结算单号*/,
dbilldate char(19) null 
/*结算日期*/,
btoia char(1) default 'N' null 
/*已传存货*/,
iprintcount integer default 0 null 
/*打印次数*/,
billmaker varchar2(20) null 
/*制单人*/,
dmakedate char(19) null 
/*制单日期*/,
creationtime char(19) null 
/*创建时间*/,
creator varchar2(20) null 
/*创建人*/,
fsettletype integer null 
/*结算单据类型*/,
 constraint pk_po_settlebill primary key (pk_settlebill),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: 结算费用分摊明细 */
create table po_settle_feeallot (pk_settle_feeallot char(20) not null 
/*费用分摊明细*/,
pk_settlebill char(20) null 
/*结算单头*/,
pk_srcmaterial varchar2(20) null 
/*费用物料*/,
pk_material varchar2(20) null 
/*费用物料版本*/,
vallotbilltype varchar2(4) null 
/*费用分摊依据的单据类型*/,
pk_allotbillid varchar2(20) null 
/*分摊的单据ID*/,
pk_allotbillbid varchar2(20) null 
/*分摊的单据行ID*/,
nallotmoney number(28,8) null 
/*费用分摊金额*/,
bestfirstsettle char(1) null 
/*是否暂估费用物料的第一次结算*/,
ntimesafterfirst integer null 
/*第一次结算的后续累计费用结算次数*/,
pk_oppofee_b char(20) null 
/*相应的费用所在的结算行ID*/,
pk_supplier varchar2(20) null 
/*供应商*/,
pk_settlebill_b char(20) not null 
/*结算单明细_主键*/,
nallotbillnum number(28,8) null 
/*分摊的依据数量*/,
nallotbillmny number(28,8) null 
/*分摊的依据金额*/,
 constraint pk_settle_feeallot primary key (pk_settle_feeallot),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

