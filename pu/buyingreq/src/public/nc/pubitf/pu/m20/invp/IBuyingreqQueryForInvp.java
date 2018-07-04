package nc.pubitf.pu.m20.invp;

import nc.pubitf.invp.plan.ISupplyResultForInvp;
import nc.vo.pub.BusinessException;

/**
 * 请购单作为库存计划的供给而提供的查询服务
 * 
 * @since 6.0
 * @version 2010-12-14 下午06:18:00
 * @author duy
 */
public interface IBuyingreqQueryForInvp {

  /**
   * 返回请购单的查询SQL封装对象
   * 
   * @param pk_org 库存组织OID
   * @param tempTable
   *          临时表的表名（包含三个字段：pk_material-不会重复的物料VID，dstart-开始时间，dend-结束时间）
   * @return 查询SQL封装对象
   * @throws BusinessException
   */
  ISupplyResultForInvp getSupply(String pk_org, String tempTable)
      throws BusinessException;
}
