/**
 * $文件说明$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-4 下午03:54:25
 */
package nc.pubimpl.pu.m20.pu.m29;

import nc.bs.pu.m20.rewrite.pu.ReWrite20For29BP;
import nc.pubitf.pu.m20.pu.m29.IReWrite20For29;
import nc.vo.pu.m20.entity.writeback.M29WriteBackVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>询报价单回写请购单服务实现类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-2-4 下午03:54:25
 */
public class ReWrite20For29Impl implements IReWrite20For29 {

  /**
   * 父类方法重写
   * 
   * @see nc.pubitf.pu.m20.pu.m29.IReWrite20For29#backWriteGenNum(nc.vo.pu.m20.entity.writeback.M29WriteBackVO)
   */
  @Override
  public void backWriteGenNum(M29WriteBackVO[] vos) throws BusinessException {
    try {
      new ReWrite20For29BP().backWriteGenNum(vos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

}
