package nc.pubimpl.pu.m23.ic.m47;

import nc.bs.pu.m23.writeback.ic.m47.Writeback23For47BP;
import nc.pubitf.pu.m23.ic.m47.IWriteback23For47;
import nc.pubitf.pu.m23.ic.m47.IWriteback23For47Para;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 到货单提供给库存的委托加工入库单的回写服务实现类
 * 
 * @since 6.0
 * @version 2010-11-12 下午03:59:42
 * @author hanbin
 */
public class Writeback23For47Impl implements IWriteback23For47 {

  @Override
  public void writebackNum(IWriteback23For47Para[] paraArray)
      throws BusinessException {
    try {
      // 调用BP
      Writeback23For47BP bp = new Writeback23For47BP();
      bp.writeback(paraArray);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }
}
