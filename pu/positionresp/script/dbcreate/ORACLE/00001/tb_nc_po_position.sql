/* tablename: 岗位设置 */
create table po_position (name4 varchar2(300) null 
/*岗位名称4*/,
name6 varchar2(300) null 
/*岗位名称6*/,
name5 varchar2(300) null 
/*岗位名称5*/,
pk_position char(20) not null 
/*岗位设置*/,
name3 varchar2(300) null 
/*岗位名称3*/,
name2 varchar2(300) null 
/*岗位名称2*/,
name varchar2(300) null 
/*岗位名称*/,
pk_group varchar2(20) default '~' null 
/*集团*/,
pk_org varchar2(20) default '~' null 
/*组织*/,
code varchar2(30) null 
/*岗位编码*/,
cemployeeid varchar2(20) default '~' null 
/*计划员*/,
vmemo varchar2(50) null 
/*备注*/,
fpositiontype integer null 
/*岗位类型*/,
 constraint pk_po_position primary key (pk_position),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: 岗位设置子表 */
create table po_position_b (pk_position_b char(20) not null 
/*岗位设置子表*/,
pk_org varchar2(20) default '~' null 
/*组织*/,
pk_marbasclass varchar2(20) default '~' null 
/*物料基本分类*/,
marbasclass_code varchar2(50) null 
/*物料基本分类编码*/,
pk_marpuclass varchar2(20) default '~' null 
/*物料采购分类*/,
marpuclass_code varchar2(50) null 
/*物料采购分类编码*/,
pk_material varchar2(20) default '~' null 
/*物料*/,
material_code varchar2(50) null 
/*物料编码*/,
fflag integer default 1 null 
/*应用/排除*/,
pk_position char(20) not null 
/*岗位设置_主键*/,
pk_srcmaterial varchar2(20) null 
/*物料oid*/,
 constraint pk_po_position_b primary key (pk_position_b),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

