/**
 * $文件说明$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-4 上午09:33:46
 */
package nc.pubimpl.pu.m20.pu.m21;

import nc.bs.pu.m20.rewrite.ReWrite20ForOrderBP;
import nc.pubitf.pu.m20.pu.m21.IReWrite20For21;
import nc.vo.pu.m20.entity.writeback.M21WriteBackVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-2-4 上午09:33:46
 */
public class ReWrite20For21Impl implements IReWrite20For21 {

  /**
   * 父类方法重写
   * 
   * @see nc.pubitf.pu.m20.pu.m21.IReWrite20For21#backWriteNum(nc.vo.pu.m20.entity.writeback.M21WriteBackVO[])
   */
  @Override
  public void backWriteNum(M21WriteBackVO[] paraVos) throws BusinessException {
    try {
      new ReWrite20ForOrderBP().backWriteNum(paraVos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

}
