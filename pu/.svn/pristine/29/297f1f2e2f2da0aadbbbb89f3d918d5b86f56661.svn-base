/* tablename: 物资需求申请单明细 */
create table po_storereq_b (
pk_storereq_b nchar(20) not null 
/*物资需求申请单明细*/,
pk_group nvarchar(20) null 
/*集团*/,
pk_org nvarchar(20) null 
/*库存组织最新版本*/,
pk_org_v nvarchar(20) null 
/*库存组织*/,
crowno nvarchar(20) null 
/*行号*/,
pk_material nvarchar(20) null 
/*物料版本信息*/,
pk_srcmaterial nvarchar(20) null 
/*物料信息*/,
cunitid nvarchar(20) null 
/*主单位*/,
nnum decimal(28,8) null 
/*主数量*/,
castunitid nvarchar(20) null 
/*单位*/,
nastnum decimal(28,8) null 
/*数量*/,
vchangerate nvarchar(60) null 
/*换算率*/,
ntaxprice decimal(28,8) null 
/*主本币含税单价*/,
ntaxmny decimal(28,8) null 
/*本币价税合计*/,
pk_reqstordoc nvarchar(20) null 
/*需求仓库*/,
dreqdate nvarchar(19) null 
/*需求日期*/,
pk_apppsn nvarchar(20) null 
/*申请人*/,
pk_appdept_v nvarchar(20) null 
/*申请部门*/,
pk_appdept nvarchar(20) null 
/*申请部门最新版本*/,
pk_receiveaddress nvarchar(20) null 
/*收货地址*/,
cdevareaid nvarchar(20) null 
/*收货地区*/,
cdevaddrid nvarchar(20) null 
/*收货地点*/,
csourcetypecode nvarchar(20) null 
/*来源单据类型*/,
csourceid nvarchar(20) null 
/*来源单据*/,
csourcebid nvarchar(20) null 
/*来源单据明细*/,
vsourcecode nvarchar(40) null 
/*来源单据号*/,
vsourcetrantype nvarchar(20) null 
/*来源交易类型*/,
vsourcerowno nvarchar(20) null 
/*来源单据行号*/,
cfirsttypecode nvarchar(20) null 
/*源头单据类型*/,
cfirstid nvarchar(20) null 
/*源头单据*/,
cfirstbid nvarchar(20) null 
/*源头单据明细*/,
vfirstcode nvarchar(40) null 
/*源头单据号*/,
vfirsttrantype nvarchar(20) null 
/*源头交易类型*/,
vfirstrowno nvarchar(20) null 
/*源头单据行号*/,
naccuoutnum decimal(28,8) null 
/*累计出库主数量*/,
naccuoutreqnum decimal(28,8) null 
/*累计申请出库主数量*/,
bclose nchar(1) null default 'N' 
/*是否关闭*/,
pk_batchcode nvarchar(20) null 
/*批次号主键*/,
vbatchcode nvarchar(40) null 
/*批次号*/,
cvendorid nvarchar(20) null 
/*供应商*/,
cproductorid nvarchar(20) null 
/*生产厂商*/,
cprojectid nvarchar(20) null 
/*项目*/,
cprojecttaskid nvarchar(20) null 
/*项目任务*/,
ccostelementid nvarchar(20) null 
/*成本要素*/,
vfree1 nvarchar(101) null 
/*自由辅助属性1*/,
vfree2 nvarchar(101) null 
/*自由辅助属性2*/,
vfree3 nvarchar(101) null 
/*自由辅助属性3*/,
vfree4 nvarchar(101) null 
/*自由辅助属性4*/,
vfree5 nvarchar(101) null 
/*自由辅助属性5*/,
vfree6 nvarchar(101) null 
/*自由辅助属性6*/,
vfree7 nvarchar(101) null 
/*自由辅助属性7*/,
vfree8 nvarchar(101) null 
/*自由辅助属性8*/,
vfree9 nvarchar(101) null 
/*自由辅助属性9*/,
vfree10 nvarchar(101) null 
/*自由辅助属性10*/,
vbdef1 nvarchar(101) null 
/*自定义项1*/,
vbdef2 nvarchar(101) null 
/*自定义项2*/,
vbdef3 nvarchar(101) null 
/*自定义项3*/,
vbdef4 nvarchar(101) null 
/*自定义项4*/,
vbdef5 nvarchar(101) null 
/*自定义项5*/,
vbdef6 nvarchar(101) null 
/*自定义项6*/,
vbdef7 nvarchar(101) null 
/*自定义项7*/,
vbdef8 nvarchar(101) null 
/*自定义项8*/,
vbdef9 nvarchar(101) null 
/*自定义项9*/,
vbdef10 nvarchar(101) null 
/*自定义项10*/,
vbdef11 nvarchar(101) null 
/*自定义项11*/,
vbdef12 nvarchar(101) null 
/*自定义项12*/,
vbdef13 nvarchar(101) null 
/*自定义项13*/,
vbdef14 nvarchar(101) null 
/*自定义项14*/,
vbdef15 nvarchar(101) null 
/*自定义项15*/,
vbdef16 nvarchar(101) null 
/*自定义项16*/,
vbdef17 nvarchar(101) null 
/*自定义项17*/,
vbdef18 nvarchar(101) null 
/*自定义项18*/,
vbdef19 nvarchar(101) null 
/*自定义项19*/,
vbdef20 nvarchar(101) null 
/*自定义项20*/,
vbmemo nvarchar(181) null 
/*备注*/,
dbilldate nchar(19) null 
/*申请日期*/,
ccurrencyid nvarchar(20) null 
/*本币币种*/,
pk_storereq nchar(20) not null 
/*物资需求申请单_主键*/,
naccumbuyreqnum decimal(28,8) null 
/*累计请购主数量*/,
pk_reqstoorg_v nvarchar(20) null 
/*原始需求库存组织*/,
pk_reqstoorg nvarchar(20) null 
/*原始需求库存组织最新版本*/,
pk_nextbalanceorg_v nvarchar(20) null 
/*下次平衡库存组织*/,
pk_nextbalanceorg nvarchar(20) null 
/*下次平衡库存组织最新版本*/,
bendgather nchar(1) null 
/*已平衡*/,
naccustornum decimal(28,8) null 
/*库存满足主数量*/,
nnetnum decimal(28,8) null 
/*转净需求主数量*/,
csourceid2 nvarchar(20) null 
/*下游单据*/,
csourcebid2 nvarchar(20) null 
/*下游单据明细*/,
vsourcecode2 nvarchar(40) null 
/*下游单据号*/,
vsourcerowno2 nvarchar(20) null 
/*下游单据行号*/,
csourcetypecode2 nvarchar(20) null 
/*下游单据类型*/,
vsourcetrantype2 nvarchar(20) null 
/*下游单据交易类型*/,
cfirstid2 nvarchar(20) null 
/*调拨订单*/,
cfirstbid2 nvarchar(20) null 
/*调拨订单明细*/,
vfirstcode2 nvarchar(40) null 
/*调拨订单号*/,
vfirstrowno2 nvarchar(20) null 
/*调拨订单行号*/,
cfirsttypecode2 nvarchar(20) null 
/*调拨单据类型*/,
vfirsttrantype2 nvarchar(20) null 
/*调拨单据交易类型*/,
tgathertime nchar(19) null 
/*汇总时间*/,
cgatherpsnid nvarchar(20) null 
/*汇总人*/,
cgatherid nvarchar(20) null 
/*汇总ID*/,
naccumminusnum decimal(28,8) null 
/*汇总平衡转请购主数量*/,
cbs nvarchar(20) null 
/*CBS*/,
 constraint pk_po_storereq_b primary key (pk_storereq_b),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 物资需求申请单 */
create table po_storereq (
pk_storereq nchar(20) not null 
/*物资需求申请单*/,
pk_group nvarchar(20) null 
/*集团*/,
pk_org nvarchar(20) null 
/*库存组织最新版本*/,
pk_org_v nvarchar(20) null 
/*库存组织*/,
vbillcode nvarchar(40) null 
/*申请单号*/,
dbilldate nchar(19) null 
/*申请日期*/,
pk_appdepth nvarchar(20) null 
/*申请部门最新版本*/,
pk_appdepth_v nvarchar(20) null 
/*申请部门*/,
pk_apppsnh nvarchar(20) null 
/*申请人*/,
vtrantypecode nvarchar(20) null 
/*单据类型编码编码*/,
fbillstatus int null default 0 
/*单据状态*/,
burgency nchar(1) null default 'N' 
/*紧急*/,
ntotalastnum decimal(28,8) null 
/*总数量*/,
ntotalorigmny decimal(28,8) null 
/*价税合计*/,
pk_project nvarchar(20) null 
/*项目*/,
creator nvarchar(20) null 
/*创建人*/,
creationtime nchar(19) null 
/*创建时间*/,
billmaker nvarchar(20) null 
/*制单人*/,
dmakedate nchar(19) null 
/*制单日期*/,
approver nvarchar(20) null 
/*审批人*/,
taudittime nvarchar(19) null 
/*审批日期*/,
modifier nvarchar(20) null 
/*最后修改人*/,
modifiedtime nvarchar(19) null 
/*最后修改时间*/,
iprintcount int null default 0 
/*打印次数*/,
vdef1 nvarchar(101) null 
/*自定义项1*/,
vdef2 nvarchar(101) null 
/*自定义项2*/,
vdef3 nvarchar(101) null 
/*自定义项3*/,
vdef4 nvarchar(101) null 
/*自定义项4*/,
vdef5 nvarchar(101) null 
/*自定义项5*/,
vdef6 nvarchar(101) null 
/*自定义项6*/,
vdef7 nvarchar(101) null 
/*自定义项7*/,
vdef8 nvarchar(101) null 
/*自定义项8*/,
vdef9 nvarchar(101) null 
/*自定义项9*/,
vdef10 nvarchar(101) null 
/*自定义项10*/,
vdef11 nvarchar(101) null 
/*自定义项11*/,
vdef12 nvarchar(101) null 
/*自定义项12*/,
vdef13 nvarchar(101) null 
/*自定义项13*/,
vdef14 nvarchar(101) null 
/*自定义项14*/,
vdef15 nvarchar(101) null 
/*自定义项15*/,
vdef16 nvarchar(101) null 
/*自定义项16*/,
vdef17 nvarchar(101) null 
/*自定义项17*/,
vdef18 nvarchar(101) null 
/*自定义项18*/,
vdef19 nvarchar(101) null 
/*自定义项19*/,
vdef20 nvarchar(101) null 
/*自定义项20*/,
vmemo nvarchar(181) null 
/*备注*/,
freqtypeflag int null 
/*需求类型*/,
ctrantypeid nvarchar(20) null 
/*物资需求申请类型*/,
 constraint pk_po_storereq primary key (pk_storereq),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

