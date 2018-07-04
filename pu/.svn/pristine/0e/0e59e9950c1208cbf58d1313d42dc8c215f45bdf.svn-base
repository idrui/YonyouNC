package nc.pubimpl.pu.m21.et;

import nc.bs.pu.m21.writeback.et.OrderWriteBackForETBP;
import nc.pubitf.pu.m21.et.IOrderWriteBackForET;
import nc.pubitf.pu.m21.et.IOrderWriteBackParaForET;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 出口回写订单实现类
 * 
 * @since 6.31
 * @version 2013-7-31 下午03:22:09
 * @author zhangyhh
 */
public class OrderWriteBackForETImpl implements IOrderWriteBackForET {

  @Override
  public void writeBackForET(IOrderWriteBackParaForET[] wbVos)
      throws BusinessException {
    try {
      new OrderWriteBackForETBP().writeBackForET(wbVos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }
}
