/* tablename: 岗位设置 */
create table po_position (
name4 nvarchar(300) null 
/*岗位名称4*/,
name6 nvarchar(300) null 
/*岗位名称6*/,
name5 nvarchar(300) null 
/*岗位名称5*/,
pk_position nchar(20) not null 
/*岗位设置*/,
name3 nvarchar(300) null 
/*岗位名称3*/,
name2 nvarchar(300) null 
/*岗位名称2*/,
name nvarchar(300) null 
/*岗位名称*/,
pk_group nvarchar(20) null default '~' 
/*集团*/,
pk_org nvarchar(20) null default '~' 
/*组织*/,
code nvarchar(30) null 
/*岗位编码*/,
cemployeeid nvarchar(20) null default '~' 
/*计划员*/,
vmemo nvarchar(50) null 
/*备注*/,
fpositiontype int null 
/*岗位类型*/,
 constraint pk_po_position primary key (pk_position),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 岗位设置子表 */
create table po_position_b (
pk_position_b nchar(20) not null 
/*岗位设置子表*/,
pk_org nvarchar(20) null default '~' 
/*组织*/,
pk_marbasclass nvarchar(20) null default '~' 
/*物料基本分类*/,
marbasclass_code nvarchar(50) null 
/*物料基本分类编码*/,
pk_marpuclass nvarchar(20) null default '~' 
/*物料采购分类*/,
marpuclass_code nvarchar(50) null 
/*物料采购分类编码*/,
pk_material nvarchar(20) null default '~' 
/*物料*/,
material_code nvarchar(50) null 
/*物料编码*/,
fflag int null default 1 
/*应用/排除*/,
pk_position nchar(20) not null 
/*岗位设置_主键*/,
pk_srcmaterial nvarchar(20) null 
/*物料oid*/,
 constraint pk_po_position_b primary key (pk_position_b),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

