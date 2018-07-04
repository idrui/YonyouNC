package nc.pubitf.pu.m21.ec;

import nc.vo.pub.BusinessException;

/**
 * 给电子采购排程结果提供的推式保存接口。
 * 
 * @since 6.0
 * @version 2012-11-2 下午04:05:59
 * @author lixyp
 */
public interface IOrderPushSaveForEC49 {

  /**
   * 排程结果推式保存生成采购订单。
   * 
   * @param orderVOs 订单聚合VO
   * @return 订单聚合VO
   * @throws BusinessException
   */
  PushSaveVO pushSave(PushSaveVO pushSaveVO) throws BusinessException;
}
