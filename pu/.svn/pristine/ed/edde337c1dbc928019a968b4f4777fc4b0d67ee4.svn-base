package nc.pubimpl.pu.m422x.pu.m20;

import nc.bs.pu.m422x.rewrite.ReWrite422XFor20BP;
import nc.pubitf.pu.m422x.pu.m20.IReWrite422xFor20;
import nc.vo.pu.m422x.entity.WriteBack422XVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class ReWrite422xFor20Impl implements IReWrite422xFor20 {

  @Override
  public void backWriteNum(WriteBack422XVO[] paraVos) throws BusinessException {
    try {
      new ReWrite422XFor20BP().backWriteNum(paraVos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

}
