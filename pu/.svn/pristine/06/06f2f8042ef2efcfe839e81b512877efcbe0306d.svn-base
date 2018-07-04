package nc.pubitf.pu.position;

import java.util.ArrayList;
import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.para.SysInitVO;

/**
 * 采购岗（计划岗）物料设置为采购的查询服务。
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>过滤掉当前采购员（计划员）没有有权限的物料。
 * <li>根据库存组织＋物料查询采购岗物料设置。
 * </ul>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2009-12-22 下午03:17:44
 */
public interface IQueryPosition {

  /**
   * PO85 采购基础设置物料分类方式 设置进行控制
   * 
   * @param vo
   * @return
   * @throws BusinessException
   */

  boolean checkPO85Para(SysInitVO vo) throws BusinessException;

  /**
   * 过滤掉当前操作员(计划员)没有有权限的物料
   * 
   * @param pk_org 当前组织
   * @param pk_operator 操作员(计划员)
   * @param pk_materials 待过滤的物料
   * @return ArrayList 过滤后操作员(计划员)有权限的物料
   * @throws BusinessException
   */
  ArrayList<String> filterMaterialByPlanner(String pk_org, String pk_operator,
      ArrayList<String> pk_materials) throws BusinessException;

  /**
   * 根据计划岗查询该岗位下所有的物料VID sql
   * 
   * @param pk_org 库存组织
   * @param pk_plan 岗位PK
   * @return 岗位下所有的物料VID sql( select pk_material from .....)
   */
  String getMaterialSqlByPkPlan(String pk_org, String pk_plan);

  /**
   * 取得操作员有权限的存货（采购员）
   * 
   * @param pk_org 组织
   * @return 临时表--InvPosPowerTempTable，表中只有一个字段为pk_material
   * @throws BusinessException
   */
  String getMaterialSqlByPurchaser(String pk_org) throws BusinessException;

  /**
   * 根据库存组织＋物料查询采购岗物料设置。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_org 库存组织PK数组
   * @param pk_material 物料PK数组
   * @return HashMap<pk_org+pk_material,cemployeeid(采购员信息)>
   * @throws BusinessException <p>
   * @author GGR
   * @time 2009-12-22 上午08:53:40
   */
  Map<String, String> getPurchaser(String[] pkOrg, String[] pk_stocks,
      String[] pkMaterial) throws BusinessException;

  /**
   * 根据采购组织、库存组织、物料oid查询采购岗
   * 
   * @param vos 查询参数VO，包括采购组织、物料oid、库存组织主键（VO对照时传到请购单主组织或者采购订单需求库存组织。如果没有不用设置值）
   * @return 查询参数VO 设置采购岗的值
   * @throws BusinessException
   */
  PositionQueryVO[] getPurPosition(PositionQueryVO[] vos)
      throws BusinessException;

  /**
   * 根据当前登录操作员查询其对应的业务员（计划员）所对应的计划岗的物料
   * 
   * @param pk_org 库存组织
   * @return 查询计划岗物料的SQL<br>
   *         岗位下所有的物料VID sql( select pk_material from .....) <br>
   *         如果操作员没有设置业务员，则返回null,如果操作员对应的业务员未设置计划岗，也返回null
   * @throws BusinessException
   */
  String queryPlanMaterialsForUser(String pk_org) throws BusinessException;
}
