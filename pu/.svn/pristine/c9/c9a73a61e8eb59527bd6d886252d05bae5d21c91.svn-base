/* tablename: 采购询价明细 */
create table po_order_price_b (pk_order_b char(20) not null 
/*采购订单明细*/,
pk_order_price char(20) not null 
/*采购询价_主键*/,
 constraint pk_o_order_price_b primary key (pk_order_b),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: 采购询价 */
create table po_order_price (pk_order_price char(20) not null 
/*采购询价*/,
pk_org varchar2(20) default '~' null 
/*采购组织版本信息*/,
pk_psfinanceorg varchar2(20) default '~' null 
/*结算财务组织(OID)*/,
pk_arrvstoorg varchar2(20) default '~' null 
/*收货库存组织(OID)*/,
pk_srcmaterial varchar2(20) default '~' null 
/*物料信息*/,
pk_supplier varchar2(20) default '~' null 
/*供应商*/,
corigcurrencyid varchar2(20) default '~' null 
/*币种*/,
ftaxtypeflag integer default 1 null 
/*扣税类别*/,
ntaxrate number(28,8) null 
/*税率*/,
cunitid varchar2(20) default '~' null 
/*主单位*/,
castunitid varchar2(20) default '~' null 
/*单位*/,
cqtunitid varchar2(20) default '~' null 
/*报价单位*/,
dbilldate char(19) null 
/*订单日期*/,
modifiedtime char(19) null 
/*最后修改时间*/,
nqtorigtaxnetprc number(28,8) null 
/*含税净价*/,
norigtaxnetprice number(28,8) null 
/*主含税净价*/,
nqttaxnetprice number(28,8) null 
/*本币含税净价*/,
ntaxnetprice number(28,8) null 
/*主本币含税净价*/,
nqtnetprice number(28,8) null 
/*本币无税净价*/,
nnetprice number(28,8) null 
/*主本币无税净价*/,
norignetprice number(28,8) null 
/*主无税净价*/,
nqtorignetprice number(28,8) null 
/*无税净价*/,
norigtaxprice number(28,8) null 
/*主含税单价*/,
ntaxprice number(28,8) null 
/*主本币含税单价*/,
nqtorigtaxprice number(28,8) null 
/*含税单价*/,
nqttaxprice number(28,8) null 
/*本币含税单价*/,
norigprice number(28,8) null 
/*主无税单价*/,
nqtorigprice number(28,8) null 
/*无税单价*/,
nqtprice number(28,8) null 
/*本币无税单价*/,
nprice number(28,8) null 
/*主本币无税单价*/,
nnetpricem number(28,8) null 
/*主本币无税净最低价*/,
ntaxnetpricem number(28,8) null 
/*主本币含税净最低价*/,
norignetpricem number(28,8) null 
/*主无税净最低价*/,
norigtaxnetpricem number(28,8) null 
/*主含税净最低价*/,
bvalidate char(1) null 
/*是否有效*/,
 constraint pk_po_order_price primary key (pk_order_price),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

