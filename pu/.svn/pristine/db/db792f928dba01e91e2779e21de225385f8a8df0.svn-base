/* tablename: 岗位设置附表 */
create table po_position_t (pk_position_t char(20) not null 
/*主键*/,
pk_group varchar2(20) null 
/*集团*/,
pk_org varchar2(20) null 
/*组织*/,
marclasscode varchar2(50) null 
/*物料分类编码*/,
pk_marclass varchar2(20) null 
/*物料分类*/,
code varchar2(50) null 
/*岗位编码*/,
cemployeeid varchar2(20) null 
/*采购员*/,
pk_position_b varchar2(20) null 
/*岗位设置子表*/,
pk_position varchar2(20) null 
/*岗位设置*/,
fpositiontype integer null 
/*岗位类型*/,
 constraint pk_po_position_t primary key (pk_position_t),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

