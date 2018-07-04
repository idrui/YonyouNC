/* tablename: 采购发票交易类型 */
create table po_invctrantype (
pk_invctrantype nchar(20) not null 
/*采购发票交易类型ID*/,
itoarapmode int not null 
/*传应付控制*/,
bcheckquality nchar(1) not null 
/*是否质量合格检查*/,
bconsumepur nchar(1) not null 
/*是否消耗性采购*/,
bsendpay nchar(1) not null 
/*结算完毕自动传应付*/,
pk_group nchar(20) not null 
/*所属集团*/,
vtrantypecode nvarchar(20) not null 
/*交易类型*/,
ctrantypeid nvarchar(20) null 
/*交易类型编码*/,
bajust nchar(1) null 
/*调整发票*/,
 constraint pk_po_invctrantype primary key (pk_invctrantype),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 采购订单交易类??? */
create table po_potrantype (
pk_potrantype nchar(20) not null 
/*主键*/,
vtrantypecode nvarchar(20) not null 
/*交易类型*/,
bvmi nchar(1) null 
/*供应商寄存*/,
bdirect nchar(1) null 
/*直运采购*/,
bcheckcenpur nchar(1) null 
/*自制采购订单是否检查采购业务委托关系*/,
boverpay nchar(1) null 
/*允许超订单付款*/,
breceiveplan nchar(1) null 
/*进行到货计划安排*/,
iprtopolimit int null 
/*请购单生成订单限制方式*/,
boutput nchar(1) null 
/*输出*/,
bconfirm nchar(1) null 
/*对方确认*/,
bconsign nchar(1) null 
/*发货环节*/,
bload nchar(1) null 
/*装运环节*/,
bcustom nchar(1) null 
/*报关环节*/,
boutcustom nchar(1) null 
/*出关环节*/,
bconfirmcode nchar(1) null 
/*对方确认单据号*/,
bconfirmdate nchar(1) null 
/*对方确认单据日期*/,
bconfirmnum nchar(1) null 
/*对方确认单据数量*/,
bconsigncode nchar(1) null 
/*发货单据号*/,
bconsigndate nchar(1) null 
/*发货单据日期*/,
bconsignnum nchar(1) null 
/*发货单据数量*/,
bloadcode nchar(1) null 
/*装运单据号*/,
bloaddate nchar(1) null 
/*装运单据日期*/,
bcustomcode nchar(1) null 
/*报关单据号*/,
bcustomdate nchar(1) null 
/*报关单据日期*/,
boutcustomcode nchar(1) null 
/*出关单据号*/,
boutcustomdate nchar(1) null 
/*出关单据日期*/,
barrive nchar(1) null 
/*到货*/,
bstore nchar(1) null 
/*入库*/,
pk_group nvarchar(20) null 
/*集团*/,
ionwayend int null 
/*订单在途最终状态*/,
ionwaybegin int null 
/*订单在途起始状态*/,
iapproveaft int null 
/*审核后置状态*/,
ioutputaft int null 
/*输出后置状态*/,
iconfirmaft int null 
/*确认后置状态*/,
iconsignaft int null 
/*发货后置状态*/,
iloadaft int null 
/*装运后置状态*/,
icustomaft int null 
/*报关后置状态*/,
ioutcustomaft int null 
/*出关后置状态*/,
ctrantypeid nvarchar(20) null 
/*交易类型编码*/,
 constraint pk_po_potrantype primary key (pk_potrantype),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 请购单交易类型 */
create table po_buyrtrantype (
pk_buyrtrantype nchar(20) not null 
/*请购单交易类型*/,
pk_group nvarchar(20) not null 
/*所属集团*/,
vtrantypecode nvarchar(20) not null 
/*交易类型编码*/,
ctrantypeid nchar(20) not null 
/*交易类型*/,
bneedarrange nchar(1) not null 
/*需要请购安排*/,
 constraint pk_po_buyrtrantype primary key (pk_buyrtrantype),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

