/* tablename: 物资需求申请单明细 */
create table po_storereq_b (pk_storereq_b char(20) not null 
/*物资需求申请单明细*/,
pk_group varchar(20) null 
/*集团*/,
pk_org varchar(20) null 
/*库存组织最新版本*/,
pk_org_v varchar(20) null 
/*库存组织*/,
crowno varchar(20) null 
/*行号*/,
pk_material varchar(20) null 
/*物料版本信息*/,
pk_srcmaterial varchar(20) null 
/*物料信息*/,
cunitid varchar(20) null 
/*主单位*/,
nnum decimal(28,8) null 
/*主数量*/,
castunitid varchar(20) null 
/*单位*/,
nastnum decimal(28,8) null 
/*数量*/,
vchangerate varchar(60) null 
/*换算率*/,
ntaxprice decimal(28,8) null 
/*主本币含税单价*/,
ntaxmny decimal(28,8) null 
/*本币价税合计*/,
pk_reqstordoc varchar(20) null 
/*需求仓库*/,
dreqdate varchar(19) null 
/*需求日期*/,
pk_apppsn varchar(20) null 
/*申请人*/,
pk_appdept_v varchar(20) null 
/*申请部门*/,
pk_appdept varchar(20) null 
/*申请部门最新版本*/,
pk_receiveaddress varchar(20) null 
/*收货地址*/,
cdevareaid varchar(20) null 
/*收货地区*/,
cdevaddrid varchar(20) null 
/*收货地点*/,
csourcetypecode varchar(20) null 
/*来源单据类型*/,
csourceid varchar(20) null 
/*来源单据*/,
csourcebid varchar(20) null 
/*来源单据明细*/,
vsourcecode varchar(40) null 
/*来源单据号*/,
vsourcetrantype varchar(20) null 
/*来源交易类型*/,
vsourcerowno varchar(20) null 
/*来源单据行号*/,
cfirsttypecode varchar(20) null 
/*源头单据类型*/,
cfirstid varchar(20) null 
/*源头单据*/,
cfirstbid varchar(20) null 
/*源头单据明细*/,
vfirstcode varchar(40) null 
/*源头单据号*/,
vfirsttrantype varchar(20) null 
/*源头交易类型*/,
vfirstrowno varchar(20) null 
/*源头单据行号*/,
naccuoutnum decimal(28,8) null 
/*累计出库主数量*/,
naccuoutreqnum decimal(28,8) null 
/*累计申请出库主数量*/,
bclose char(1) null default 'N' 
/*是否关闭*/,
pk_batchcode varchar(20) null 
/*批次号主键*/,
vbatchcode varchar(40) null 
/*批次号*/,
cvendorid varchar(20) null 
/*供应商*/,
cproductorid varchar(20) null 
/*生产厂商*/,
cprojectid varchar(20) null 
/*项目*/,
cprojecttaskid varchar(20) null 
/*项目任务*/,
ccostelementid varchar(20) null 
/*成本要素*/,
vfree1 varchar(101) null 
/*自由辅助属性1*/,
vfree2 varchar(101) null 
/*自由辅助属性2*/,
vfree3 varchar(101) null 
/*自由辅助属性3*/,
vfree4 varchar(101) null 
/*自由辅助属性4*/,
vfree5 varchar(101) null 
/*自由辅助属性5*/,
vfree6 varchar(101) null 
/*自由辅助属性6*/,
vfree7 varchar(101) null 
/*自由辅助属性7*/,
vfree8 varchar(101) null 
/*自由辅助属性8*/,
vfree9 varchar(101) null 
/*自由辅助属性9*/,
vfree10 varchar(101) null 
/*自由辅助属性10*/,
vbdef1 varchar(101) null 
/*自定义项1*/,
vbdef2 varchar(101) null 
/*自定义项2*/,
vbdef3 varchar(101) null 
/*自定义项3*/,
vbdef4 varchar(101) null 
/*自定义项4*/,
vbdef5 varchar(101) null 
/*自定义项5*/,
vbdef6 varchar(101) null 
/*自定义项6*/,
vbdef7 varchar(101) null 
/*自定义项7*/,
vbdef8 varchar(101) null 
/*自定义项8*/,
vbdef9 varchar(101) null 
/*自定义项9*/,
vbdef10 varchar(101) null 
/*自定义项10*/,
vbdef11 varchar(101) null 
/*自定义项11*/,
vbdef12 varchar(101) null 
/*自定义项12*/,
vbdef13 varchar(101) null 
/*自定义项13*/,
vbdef14 varchar(101) null 
/*自定义项14*/,
vbdef15 varchar(101) null 
/*自定义项15*/,
vbdef16 varchar(101) null 
/*自定义项16*/,
vbdef17 varchar(101) null 
/*自定义项17*/,
vbdef18 varchar(101) null 
/*自定义项18*/,
vbdef19 varchar(101) null 
/*自定义项19*/,
vbdef20 varchar(101) null 
/*自定义项20*/,
vbmemo varchar(181) null 
/*备注*/,
dbilldate char(19) null 
/*申请日期*/,
ccurrencyid varchar(20) null 
/*本币币种*/,
pk_storereq char(20) not null 
/*物资需求申请单_主键*/,
naccumbuyreqnum decimal(28,8) null 
/*累计请购主数量*/,
pk_reqstoorg_v varchar(20) null 
/*原始需求库存组织*/,
pk_reqstoorg varchar(20) null 
/*原始需求库存组织最新版本*/,
pk_nextbalanceorg_v varchar(20) null 
/*下次平衡库存组织*/,
pk_nextbalanceorg varchar(20) null 
/*下次平衡库存组织最新版本*/,
bendgather char(1) null 
/*已平衡*/,
naccustornum decimal(28,8) null 
/*库存满足主数量*/,
nnetnum decimal(28,8) null 
/*转净需求主数量*/,
csourceid2 varchar(20) null 
/*下游单据*/,
csourcebid2 varchar(20) null 
/*下游单据明细*/,
vsourcecode2 varchar(40) null 
/*下游单据号*/,
vsourcerowno2 varchar(20) null 
/*下游单据行号*/,
csourcetypecode2 varchar(20) null 
/*下游单据类型*/,
vsourcetrantype2 varchar(20) null 
/*下游单据交易类型*/,
cfirstid2 varchar(20) null 
/*调拨订单*/,
cfirstbid2 varchar(20) null 
/*调拨订单明细*/,
vfirstcode2 varchar(40) null 
/*调拨订单号*/,
vfirstrowno2 varchar(20) null 
/*调拨订单行号*/,
cfirsttypecode2 varchar(20) null 
/*调拨单据类型*/,
vfirsttrantype2 varchar(20) null 
/*调拨单据交易类型*/,
tgathertime char(19) null 
/*汇总时间*/,
cgatherpsnid varchar(20) null 
/*汇总人*/,
cgatherid varchar(20) null 
/*汇总ID*/,
naccumminusnum decimal(28,8) null 
/*汇总平衡转请购主数量*/,
cbs varchar(20) null 
/*CBS*/,
 constraint pk_po_storereq_b primary key (pk_storereq_b),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: 物资需求申请单 */
create table po_storereq (pk_storereq char(20) not null 
/*物资需求申请单*/,
pk_group varchar(20) null 
/*集团*/,
pk_org varchar(20) null 
/*库存组织最新版本*/,
pk_org_v varchar(20) null 
/*库存组织*/,
vbillcode varchar(40) null 
/*申请单号*/,
dbilldate char(19) null 
/*申请日期*/,
pk_appdepth varchar(20) null 
/*申请部门最新版本*/,
pk_appdepth_v varchar(20) null 
/*申请部门*/,
pk_apppsnh varchar(20) null 
/*申请人*/,
vtrantypecode varchar(20) null 
/*单据类型编码编码*/,
fbillstatus integer null default 0 
/*单据状态*/,
burgency char(1) null default 'N' 
/*紧急*/,
ntotalastnum decimal(28,8) null 
/*总数量*/,
ntotalorigmny decimal(28,8) null 
/*价税合计*/,
pk_project varchar(20) null 
/*项目*/,
creator varchar(20) null 
/*创建人*/,
creationtime char(19) null 
/*创建时间*/,
billmaker varchar(20) null 
/*制单人*/,
dmakedate char(19) null 
/*制单日期*/,
approver varchar(20) null 
/*审批人*/,
taudittime varchar(19) null 
/*审批日期*/,
modifier varchar(20) null 
/*最后修改人*/,
modifiedtime varchar(19) null 
/*最后修改时间*/,
iprintcount integer null default 0 
/*打印次数*/,
vdef1 varchar(101) null 
/*自定义项1*/,
vdef2 varchar(101) null 
/*自定义项2*/,
vdef3 varchar(101) null 
/*自定义项3*/,
vdef4 varchar(101) null 
/*自定义项4*/,
vdef5 varchar(101) null 
/*自定义项5*/,
vdef6 varchar(101) null 
/*自定义项6*/,
vdef7 varchar(101) null 
/*自定义项7*/,
vdef8 varchar(101) null 
/*自定义项8*/,
vdef9 varchar(101) null 
/*自定义项9*/,
vdef10 varchar(101) null 
/*自定义项10*/,
vdef11 varchar(101) null 
/*自定义项11*/,
vdef12 varchar(101) null 
/*自定义项12*/,
vdef13 varchar(101) null 
/*自定义项13*/,
vdef14 varchar(101) null 
/*自定义项14*/,
vdef15 varchar(101) null 
/*自定义项15*/,
vdef16 varchar(101) null 
/*自定义项16*/,
vdef17 varchar(101) null 
/*自定义项17*/,
vdef18 varchar(101) null 
/*自定义项18*/,
vdef19 varchar(101) null 
/*自定义项19*/,
vdef20 varchar(101) null 
/*自定义项20*/,
vmemo varchar(181) null 
/*备注*/,
freqtypeflag integer null 
/*需求类型*/,
ctrantypeid varchar(20) null 
/*物资需求申请类型*/,
 constraint pk_po_storereq primary key (pk_storereq),
 ts char(19) null,
dr smallint null default 0
)
;

