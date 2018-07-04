package nc.pubimpl.pu.m20.it.m5801;

import nc.bs.pu.m20.rewrite.it.ReWrite20For5801BP;
import nc.pubitf.pu.m20.it.m5801.IReWrite20For5801;
import nc.vo.pu.m20.entity.writeback.M5801WriteBackVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 请购单为进口合同提供的回写服务实现类
 * 
 * @since 6.3.1
 * @version 2013-6-25上午10:35:40
 * @author fanly3
 */
public class ReWrite20For5801Impl implements IReWrite20For5801 {

  @Override
  public void backWriteNum(M5801WriteBackVO[] backVos) throws BusinessException {
    try {
      new ReWrite20For5801BP().backWriteNum(backVos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

}
