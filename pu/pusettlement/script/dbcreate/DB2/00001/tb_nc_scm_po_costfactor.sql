/* tablename: 采购成本要素表体 */
create table po_costfactor_b (pk_costfactor_b char(20) not null 
/*成本要素行ID*/,
pk_material char(20) not null 
/*费用物料ID*/,
pk_srcmaterial varchar(20) null 
/*物料(OID)*/,
bshow char(1) null default 'Y' 
/*是否显示*/,
ishoworder integer null 
/*显示顺序*/,
pk_accountfactor varchar(20) null default '~' 
/*对应的核算要素*/,
pk_costfactor char(20) not null 
/*采购成本要素_主键*/,
 constraint pk_po_costfactor_b primary key (pk_costfactor_b),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: 采购成本要素 */
create table po_costfactor (vfactorname varchar(300) null 
/*成本要素名称*/,
vfactorname6 varchar(300) null 
/*成本要素名称6*/,
vfactorname5 varchar(300) null 
/*成本要素名称5*/,
vfactorname4 varchar(300) null 
/*成本要素名称4*/,
vfactorname3 varchar(300) null 
/*成本要素名称3*/,
vfactorname2 varchar(300) null 
/*成本要素名称2*/,
pk_costfactor char(20) not null 
/*成本要素ID*/,
pk_group varchar(20) null default '~' 
/*所属集团*/,
pk_org varchar(20) null default '~' 
/*主组织*/,
pk_org_v char(20) null 
/*主组织版本信息*/,
fapportionmode integer null default 0 
/*分摊方式*/,
bentercost char(1) null default 'N' 
/*是否进入存货成本*/,
ifactororder integer null 
/*成本要素顺序*/,
 constraint pk_po_costfactor primary key (pk_costfactor),
 ts char(19) null,
dr smallint null default 0
)
;

