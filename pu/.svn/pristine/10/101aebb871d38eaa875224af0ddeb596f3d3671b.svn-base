package nc.pubimpl.pu.m21.dm.m4804;

import nc.bs.pu.m21.writeback.dm.OrderWriteBackFor4804BP;
import nc.pubitf.pu.m21.dm.m4804.IOrderWriteBackFor4804;
import nc.pubitf.pu.m21.dm.m4804.IOrderWriteBackParaFor4804;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 运输单回写服务
 * 
 * @since 6.0
 * @version 2010-12-14 下午05:09:24
 * @author wuxla
 */

public class OrderWriteBackFor4804Impl implements IOrderWriteBackFor4804 {

  @Override
  public void writeBackFor45(IOrderWriteBackParaFor4804[] wbVos)
      throws BusinessException {
    try {
      new OrderWriteBackFor4804BP().writeBackFor4804(wbVos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

}
