/* tablename: 采购发票交易类型 */
create table po_invctrantype (pk_invctrantype char(20) not null 
/*采购发票交易类型ID*/,
itoarapmode integer not null 
/*传应付控制*/,
bcheckquality char(1) not null 
/*是否质量合格检查*/,
bconsumepur char(1) not null 
/*是否消耗性采购*/,
bsendpay char(1) not null 
/*结算完毕自动传应付*/,
pk_group char(20) not null 
/*所属集团*/,
vtrantypecode varchar(20) not null 
/*交易类型*/,
ctrantypeid varchar(20) null 
/*交易类型编码*/,
bajust char(1) null 
/*调整发票*/,
 constraint pk_po_invctrantype primary key (pk_invctrantype),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: 采购订单交易类??? */
create table po_potrantype (pk_potrantype char(20) not null 
/*主键*/,
vtrantypecode varchar(20) not null 
/*交易类型*/,
bvmi char(1) null 
/*供应商寄存*/,
bdirect char(1) null 
/*直运采购*/,
bcheckcenpur char(1) null 
/*自制采购订单是否检查采购业务委托关系*/,
boverpay char(1) null 
/*允许超订单付款*/,
breceiveplan char(1) null 
/*进行到货计划安排*/,
iprtopolimit integer null 
/*请购单生成订单限制方式*/,
boutput char(1) null 
/*输出*/,
bconfirm char(1) null 
/*对方确认*/,
bconsign char(1) null 
/*发货环节*/,
bload char(1) null 
/*装运环节*/,
bcustom char(1) null 
/*报关环节*/,
boutcustom char(1) null 
/*出关环节*/,
bconfirmcode char(1) null 
/*对方确认单据号*/,
bconfirmdate char(1) null 
/*对方确认单据日期*/,
bconfirmnum char(1) null 
/*对方确认单据数量*/,
bconsigncode char(1) null 
/*发货单据号*/,
bconsigndate char(1) null 
/*发货单据日期*/,
bconsignnum char(1) null 
/*发货单据数量*/,
bloadcode char(1) null 
/*装运单据号*/,
bloaddate char(1) null 
/*装运单据日期*/,
bcustomcode char(1) null 
/*报关单据号*/,
bcustomdate char(1) null 
/*报关单据日期*/,
boutcustomcode char(1) null 
/*出关单据号*/,
boutcustomdate char(1) null 
/*出关单据日期*/,
barrive char(1) null 
/*到货*/,
bstore char(1) null 
/*入库*/,
pk_group varchar(20) null 
/*集团*/,
ionwayend integer null 
/*订单在途最终状态*/,
ionwaybegin integer null 
/*订单在途起始状态*/,
iapproveaft integer null 
/*审核后置状态*/,
ioutputaft integer null 
/*输出后置状态*/,
iconfirmaft integer null 
/*确认后置状态*/,
iconsignaft integer null 
/*发货后置状态*/,
iloadaft integer null 
/*装运后置状态*/,
icustomaft integer null 
/*报关后置状态*/,
ioutcustomaft integer null 
/*出关后置状态*/,
ctrantypeid varchar(20) null 
/*交易类型编码*/,
 constraint pk_po_potrantype primary key (pk_potrantype),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: 请购单交易类型 */
create table po_buyrtrantype (pk_buyrtrantype char(20) not null 
/*请购单交易类型*/,
pk_group varchar(20) not null 
/*所属集团*/,
vtrantypecode varchar(20) not null 
/*交易类型编码*/,
ctrantypeid char(20) not null 
/*交易类型*/,
bneedarrange char(1) not null 
/*需要请购安排*/,
 constraint pk_po_buyrtrantype primary key (pk_buyrtrantype),
 ts char(19) null,
dr smallint null default 0
)
;

