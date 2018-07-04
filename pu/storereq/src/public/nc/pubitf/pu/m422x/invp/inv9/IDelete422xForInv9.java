package nc.pubitf.pu.m422x.invp.inv9;

import nc.vo.pub.BusinessException;

/**
 * 库存计划删除下游物资申请单接口
 * 
 * @author zhangyhh
 * @since 6.30
 * @time 2014-04-29 15:37:23
 * 
 */
public interface IDelete422xForInv9 {

  /**
   * 取消平衡后删除下游物资需求申请单接口
   * @param pk_reqLine
   * @throws BusinessException
   */
  void deleteReq(String[] pk_reqLine) throws BusinessException;

}
