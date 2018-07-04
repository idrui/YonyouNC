package nc.pubitf.pu.m21.et;

import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>出口明细单拣配采购订单回写接口
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.31
 * @since 6.31
 * @author zhangyhh
 * @time 2013-8-1 下午03:48:50
 */
public interface IOrderWriteBackForET {
  /**
   * 回写出口合同
   * @param wbVos
   * @throws BusinessException
   */
  void writeBackForET(IOrderWriteBackParaForET[] wbVos)
      throws BusinessException;
}
