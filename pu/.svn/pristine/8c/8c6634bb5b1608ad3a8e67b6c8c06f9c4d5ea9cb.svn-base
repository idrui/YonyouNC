package nc.pubimpl.pu.m21.ic.m45;

import nc.bs.pu.m21.writeback.ic.OrderWriteBackFor45BP;
import nc.pubitf.pu.m21.ic.m45.IOrderWriteBackFor45;
import nc.pubitf.pu.m21.ic.m45.IOrderWriteBackPara;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class OrderWriteBackFor45Impl implements IOrderWriteBackFor45 {

  @Override
  public void writeBackFor45(IOrderWriteBackPara[] wbVos)
      throws BusinessException {

    try {
      new OrderWriteBackFor45BP().writeback(wbVos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

}
