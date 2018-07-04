package nc.pubitf.pu.m21.invp;

import nc.pubitf.invp.plan.ISupplyResultForInvp;
import nc.vo.pub.BusinessException;

/**
 * 采购订单作为库存计划的供给而提供的查询服务
 * 
 * @since 6.0
 * @version 2010-12-14 上午11:42:15
 * @author duy
 */
public interface IOrderQueryForInvp {

  /**
   * 返回采购订单的查询SQL封装对象
   * 
   * @param pk_org 收货库存组织的OID
   * @param tempTable
   *          临时表的表名（包含三个字段：pk_material-不会重复的物料VID，dstart-开始时间，dend-结束时间）
   * @param includeRed 是否包含红字单据，如果为true，则包含，否则不包含
   * @return 查询SQL封装对象
   * @throws BusinessException
   */
  public ISupplyResultForInvp getSupply(String pk_org, String tempTable,
      boolean includeRed) throws BusinessException;
}
