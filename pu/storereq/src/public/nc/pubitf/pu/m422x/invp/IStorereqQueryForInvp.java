package nc.pubitf.pu.m422x.invp;

import nc.pubitf.invp.plan.IReqResultForInvp;
import nc.vo.pub.BusinessException;

/**
 * 物资需求申请单作为库存计划的需求而提供的查询服务
 * 
 * @since 6.0
 * @version 2010-12-14 下午06:18:00
 * @author duy
 */
public interface IStorereqQueryForInvp {

  /**
   * 返回物资需求申请的查询SQL封装对象
   * 
   * @param pk_org 库存组织的OID
   * @param tempTable
   *          临时表的表名（包含三个字段：pk_material-不会重复的物料VID，dstart-开始时间，dend-结束时间）
   * @return 查询SQL封装对象
   * @throws BusinessException
   */
  IReqResultForInvp getReq(String pk_org, String tempTable)
      throws BusinessException;
}
