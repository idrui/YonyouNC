package nc.pubitf.pu.m20.aim.m4A08;

import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;

/**
 * 资产配置申请审批时推式保存请购单
 * 
 * @since 6.5
 * @version 2014-2-14 上午10:03:28
 * @author fanly3
 */
public interface IPushSave20For4A08 {
  /**
   * 资产配置申请审批时推式保存请购单
   * 
   * @param praybills 请购单单据VO
   * @throws BusinessException
   */
  void pushSaveBills(PraybillVO[] praybills) throws BusinessException;
}
