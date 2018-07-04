/* tablename: 自动结算之发票入库单 */
create table po_rule_invoicestock (
pk_invoicestock nchar(20) not null 
/*发票入库单主键*/,
bdeptsame nchar(1) null 
/*部门相同*/,
bbuyersame nchar(1) null 
/*采购员相同*/,
bbatchcodesame nchar(1) null 
/*批次相同*/,
borigpricesame nchar(1) null 
/*主无税单价相同*/,
bnumsame nchar(1) null 
/*发票和入库单数量相同*/,
bproductorsame nchar(1) null 
/*生产厂商相同*/,
bprojectsame nchar(1) null 
/*项目相同*/,
bfreeitemsame nchar(1) null 
/*自由辅助属性相同*/,
bfinanceorgsame nchar(1) null 
/*财务组织相同*/,
bsuppliersame nchar(1) null 
/*供应商相同*/,
bmaterialsame nchar(1) null 
/*物料相同*/,
bdetailbidsame nchar(1) null 
/*来源同一进口明细单行*/,
 constraint pk_le_invoicestock primary key (pk_invoicestock),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 自动结算之红蓝发票 */
create table po_rule_rbinvoice (
pk_rbinvoice nchar(20) not null 
/*红蓝发票主键*/,
bdeptsame nchar(1) null 
/*部门相同*/,
bbuyersame nchar(1) null 
/*采购员相同*/,
bnumabssame nchar(1) null 
/*红蓝发票数量绝对值相同*/,
bbatchcodesame nchar(1) null 
/*批次相同*/,
bordersame nchar(1) null 
/*来源同一订单*/,
bproductorsame nchar(1) null 
/*生产厂商相同*/,
bprojectsame nchar(1) null 
/*项目相同*/,
bfreeitemsame nchar(1) null 
/*自由辅助属性相同*/,
bfinanceorgsame nchar(1) null 
/*财务组织相同*/,
bsuppliersame nchar(1) null 
/*供应商相同*/,
bmaterialsame nchar(1) null 
/*物料相同*/,
binvoicetypesame nchar(1) null 
/*发票类型相同*/,
bnorigpricesame nchar(1) null 
/*主无税单价相同*/,
 constraint pk__rule_rbinvoice primary key (pk_rbinvoice),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 自动结算之红蓝入库单 */
create table po_rule_rbstock (
pk_rbstock nchar(20) not null 
/*红蓝入库单主键*/,
bsuppliersame nchar(1) null 
/*供应商相同*/,
bdeptsame nchar(1) null 
/*部门相同*/,
bbuyersame nchar(1) null 
/*采购员相同*/,
bnumabssame nchar(1) null 
/*红蓝入库单数量绝对值相同*/,
bbatchcodesame nchar(1) null 
/*批次相同*/,
bordersame nchar(1) null 
/*来源同一订单*/,
btrantypesame nchar(1) null 
/*入库类型相同*/,
borigpricesame nchar(1) null 
/*主无税净价相同*/,
bproductorsame nchar(1) null 
/*生产厂商相同*/,
bprojectsame nchar(1) null 
/*项目相同*/,
bfreeitemsame nchar(1) null 
/*自由辅助属性相同*/,
bfinanceorgsame nchar(1) null 
/*财务组织相同*/,
bmaterialsame nchar(1) null 
/*物料相同*/,
 constraint pk_po_rule_rbstock primary key (pk_rbstock),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

