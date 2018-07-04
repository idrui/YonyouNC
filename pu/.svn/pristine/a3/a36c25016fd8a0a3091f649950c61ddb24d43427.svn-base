-- 功能注册
select * from SM_FUNCREGISTER where FUNCODE = '40042004';

-- 参数注册
select * from SM_PARAMREGISTER where parentid = '1002Z810000000001VXJ'; -- (功能注册的ID)

-- 业务活动
select * from sm_busiactivereg where parent_id = '1002Z810000000001VXJ'; -- (功能注册的ID)

-- 业务活动按钮
select * from sm_busiactive_btn where pk_busiactive in (select pk_busiactive from sm_busiactivereg where parent_id='1002Z810000000001VXJ'); -- (功能注册的ID)

-- 按钮注册
select * from sm_butnregister  where parent_id = '1002Z810000000001VXJ'; -- (功能注册的ID)

-- 菜单注册
select * from sm_menuitemreg where funcode='40042004';

--自语报表发表
select * from iufo_reppublish  where parentid='1002Z810000000001VXJ'; -- (功能注册的ID)

-- 查询模板
select * from pub_query_templet where MODEL_CODE = '4004200401';

-- 查询模板的查询条件
select * from pub_query_condition where pk_templet = '1002Z810000000001VY8'; -- (查询模板ID)

-- 功能节点默认模板
select * from PUB_SYSTEMPLATE where funnode='40042004';

-- 语义模型
select * from BI_SMART_DEF where DEFCODE = 'P40042004';

-- 自由报表设计（格式设计）
select * from iufo_freereport where reportcode='40042004';





