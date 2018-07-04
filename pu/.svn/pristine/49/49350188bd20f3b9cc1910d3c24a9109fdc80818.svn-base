package nc.pubimpl.pu.m23.ic.m45;

import nc.bs.pu.m23.writeback.ic.m45.Writeback23For45BP;
import nc.pubitf.pu.m23.ic.m45.IWriteback23For45;
import nc.pubitf.pu.m23.ic.m45.IWriteback23For45Para;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货单提供给库存的采购入库单的回写服务实现类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-8 下午03:57:12
 */
public class Writeback23For45Impl implements IWriteback23For45 {

  @Override
  public void writebackNum(IWriteback23For45Para[] paraArray)
      throws BusinessException {
    try {
      // 调用BP
      Writeback23For45BP bp = new Writeback23For45BP();
      bp.writeback(paraArray);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }
}
