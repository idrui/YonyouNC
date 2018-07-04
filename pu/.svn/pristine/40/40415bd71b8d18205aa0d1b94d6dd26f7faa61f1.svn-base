package nc.pubimpl.pu.m422x.ic.m4455;

import nc.bs.pu.m422x.rewrite.ReWrite422XFor4455BP;
import nc.pubitf.pu.m422x.ic.m4455.IReWrite422XFor4455;
import nc.vo.pu.m422x.entity.WriteBack422XVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 物资需求申请提供给出库申请单的回写服务实现类
 * 
 * @since 6.0
 * @version 2010-12-16 下午02:11:02
 * @author wuxla
 */

public class ReWrite422XFor4455Impl implements IReWrite422XFor4455 {

  @Override
  public void backWriteNumFor4455(WriteBack422XVO[] paraVos)
      throws BusinessException {
    try {
      new ReWrite422XFor4455BP().backWriteNumFor4455(paraVos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }
}
