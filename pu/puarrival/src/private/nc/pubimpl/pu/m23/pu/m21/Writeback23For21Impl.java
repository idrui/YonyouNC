package nc.pubimpl.pu.m23.pu.m21;

import nc.bs.pu.m23.writeback.pu.m21.Writeback23For21BP;
import nc.pubitf.pu.m23.pu.m21.IWriteback23For21;
import nc.pubitf.pu.m23.pu.m21.IWriteback23For21Para;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货单提供给采购订单补货的回写累计补货数量的回写服务实现类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-8 下午03:57:12
 */
public class Writeback23For21Impl implements IWriteback23For21 {

  @Override
  public void writebackNum(IWriteback23For21Para[] paraArray)
      throws BusinessException {
    // 调用BP
    try {
      Writeback23For21BP bp = new Writeback23For21BP();
      bp.writeback(paraArray);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }
}
