/* tablename: 采购成本要素表体 */
create table po_costfactor_b (pk_costfactor_b char(20) not null 
/*成本要素行ID*/,
pk_material char(20) not null 
/*费用物料ID*/,
pk_srcmaterial varchar2(20) null 
/*物料(OID)*/,
bshow char(1) default 'Y' null 
/*是否显示*/,
ishoworder integer null 
/*显示顺序*/,
pk_accountfactor varchar2(20) default '~' null 
/*对应的核算要素*/,
pk_costfactor char(20) not null 
/*采购成本要素_主键*/,
 constraint pk_po_costfactor_b primary key (pk_costfactor_b),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: 采购成本要素 */
create table po_costfactor (vfactorname varchar2(300) null 
/*成本要素名称*/,
vfactorname6 varchar2(300) null 
/*成本要素名称6*/,
vfactorname5 varchar2(300) null 
/*成本要素名称5*/,
vfactorname4 varchar2(300) null 
/*成本要素名称4*/,
vfactorname3 varchar2(300) null 
/*成本要素名称3*/,
vfactorname2 varchar2(300) null 
/*成本要素名称2*/,
pk_costfactor char(20) not null 
/*成本要素ID*/,
pk_group varchar2(20) default '~' null 
/*所属集团*/,
pk_org varchar2(20) default '~' null 
/*主组织*/,
pk_org_v char(20) null 
/*主组织版本信息*/,
fapportionmode integer default 0 null 
/*分摊方式*/,
bentercost char(1) default 'N' null 
/*是否进入存货成本*/,
ifactororder integer null 
/*成本要素顺序*/,
 constraint pk_po_costfactor primary key (pk_costfactor),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

