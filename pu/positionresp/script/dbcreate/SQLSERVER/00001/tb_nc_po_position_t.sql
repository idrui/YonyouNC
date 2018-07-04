/* tablename: 岗位设置附表 */
create table po_position_t (
pk_position_t nchar(20) not null 
/*主键*/,
pk_group nvarchar(20) null 
/*集团*/,
pk_org nvarchar(20) null 
/*组织*/,
marclasscode nvarchar(50) null 
/*物料分类编码*/,
pk_marclass nvarchar(20) null 
/*物料分类*/,
code nvarchar(50) null 
/*岗位编码*/,
cemployeeid nvarchar(20) null 
/*采购员*/,
pk_position_b nvarchar(20) null 
/*岗位设置子表*/,
pk_position nvarchar(20) null 
/*岗位设置*/,
fpositiontype int null 
/*岗位类型*/,
 constraint pk_po_position_t primary key (pk_position_t),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

