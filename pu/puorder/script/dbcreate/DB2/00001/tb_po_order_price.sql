/* tablename: 采购询价明细 */
create table po_order_price_b (pk_order_b char(20) not null 
/*采购订单明细*/,
pk_order_price char(20) not null 
/*采购询价_主键*/,
 constraint pk_o_order_price_b primary key (pk_order_b),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: 采购询价 */
create table po_order_price (pk_order_price char(20) not null 
/*采购询价*/,
pk_org varchar(20) null default '~' 
/*采购组织版本信息*/,
pk_psfinanceorg varchar(20) null default '~' 
/*结算财务组织(OID)*/,
pk_arrvstoorg varchar(20) null default '~' 
/*收货库存组织(OID)*/,
pk_srcmaterial varchar(20) null default '~' 
/*物料信息*/,
pk_supplier varchar(20) null default '~' 
/*供应商*/,
corigcurrencyid varchar(20) null default '~' 
/*币种*/,
ftaxtypeflag integer null default 1 
/*扣税类别*/,
ntaxrate decimal(28,8) null 
/*税率*/,
cunitid varchar(20) null default '~' 
/*主单位*/,
castunitid varchar(20) null default '~' 
/*单位*/,
cqtunitid varchar(20) null default '~' 
/*报价单位*/,
dbilldate char(19) null 
/*订单日期*/,
modifiedtime char(19) null 
/*最后修改时间*/,
nqtorigtaxnetprc decimal(28,8) null 
/*含税净价*/,
norigtaxnetprice decimal(28,8) null 
/*主含税净价*/,
nqttaxnetprice decimal(28,8) null 
/*本币含税净价*/,
ntaxnetprice decimal(28,8) null 
/*主本币含税净价*/,
nqtnetprice decimal(28,8) null 
/*本币无税净价*/,
nnetprice decimal(28,8) null 
/*主本币无税净价*/,
norignetprice decimal(28,8) null 
/*主无税净价*/,
nqtorignetprice decimal(28,8) null 
/*无税净价*/,
norigtaxprice decimal(28,8) null 
/*主含税单价*/,
ntaxprice decimal(28,8) null 
/*主本币含税单价*/,
nqtorigtaxprice decimal(28,8) null 
/*含税单价*/,
nqttaxprice decimal(28,8) null 
/*本币含税单价*/,
norigprice decimal(28,8) null 
/*主无税单价*/,
nqtorigprice decimal(28,8) null 
/*无税单价*/,
nqtprice decimal(28,8) null 
/*本币无税单价*/,
nprice decimal(28,8) null 
/*主本币无税单价*/,
nnetpricem decimal(28,8) null 
/*主本币无税净最低价*/,
ntaxnetpricem decimal(28,8) null 
/*主本币含税净最低价*/,
norignetpricem decimal(28,8) null 
/*主无税净最低价*/,
norigtaxnetpricem decimal(28,8) null 
/*主含税净最低价*/,
bvalidate char(1) null 
/*是否有效*/,
 constraint pk_po_order_price primary key (pk_order_price),
 ts char(19) null,
dr smallint null default 0
)
;

