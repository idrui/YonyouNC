/* tablename: 自动结算之发票入库单 */
create table po_rule_invoicestock (pk_invoicestock char(20) not null 
/*发票入库单主键*/,
bdeptsame char(1) null 
/*部门相同*/,
bbuyersame char(1) null 
/*采购员相同*/,
bbatchcodesame char(1) null 
/*批次相同*/,
borigpricesame char(1) null 
/*主无税单价相同*/,
bnumsame char(1) null 
/*发票和入库单数量相同*/,
bproductorsame char(1) null 
/*生产厂商相同*/,
bprojectsame char(1) null 
/*项目相同*/,
bfreeitemsame char(1) null 
/*自由辅助属性相同*/,
bfinanceorgsame char(1) null 
/*财务组织相同*/,
bsuppliersame char(1) null 
/*供应商相同*/,
bmaterialsame char(1) null 
/*物料相同*/,
bdetailbidsame char(1) null 
/*来源同一进口明细单行*/,
 constraint pk_le_invoicestock primary key (pk_invoicestock),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: 自动结算之红蓝发票 */
create table po_rule_rbinvoice (pk_rbinvoice char(20) not null 
/*红蓝发票主键*/,
bdeptsame char(1) null 
/*部门相同*/,
bbuyersame char(1) null 
/*采购员相同*/,
bnumabssame char(1) null 
/*红蓝发票数量绝对值相同*/,
bbatchcodesame char(1) null 
/*批次相同*/,
bordersame char(1) null 
/*来源同一订单*/,
bproductorsame char(1) null 
/*生产厂商相同*/,
bprojectsame char(1) null 
/*项目相同*/,
bfreeitemsame char(1) null 
/*自由辅助属性相同*/,
bfinanceorgsame char(1) null 
/*财务组织相同*/,
bsuppliersame char(1) null 
/*供应商相同*/,
bmaterialsame char(1) null 
/*物料相同*/,
binvoicetypesame char(1) null 
/*发票类型相同*/,
bnorigpricesame char(1) null 
/*主无税单价相同*/,
 constraint pk__rule_rbinvoice primary key (pk_rbinvoice),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: 自动结算之红蓝入库单 */
create table po_rule_rbstock (pk_rbstock char(20) not null 
/*红蓝入库单主键*/,
bsuppliersame char(1) null 
/*供应商相同*/,
bdeptsame char(1) null 
/*部门相同*/,
bbuyersame char(1) null 
/*采购员相同*/,
bnumabssame char(1) null 
/*红蓝入库单数量绝对值相同*/,
bbatchcodesame char(1) null 
/*批次相同*/,
bordersame char(1) null 
/*来源同一订单*/,
btrantypesame char(1) null 
/*入库类型相同*/,
borigpricesame char(1) null 
/*主无税净价相同*/,
bproductorsame char(1) null 
/*生产厂商相同*/,
bprojectsame char(1) null 
/*项目相同*/,
bfreeitemsame char(1) null 
/*自由辅助属性相同*/,
bfinanceorgsame char(1) null 
/*财务组织相同*/,
bmaterialsame char(1) null 
/*物料相同*/,
 constraint pk_po_rule_rbstock primary key (pk_rbstock),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

