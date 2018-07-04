/**
 * $文件说明$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-4 下午02:01:52
 */
package nc.pubimpl.pu.m20.pu.m28;

import nc.bs.pu.m20.rewrite.pu.ReWrite20For28BP;
import nc.pubitf.pu.m20.pu.m28.IReWrite20For28;
import nc.vo.pu.m20.entity.writeback.M28WriteBackVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>价格审批单回写请购单服务实现类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-2-4 下午02:01:52
 */
public class ReWrite20For28Impl implements IReWrite20For28 {

  /**
   * 父类方法重写
   * 
   * @see nc.pubitf.pu.m20.pu.m28.IReWrite20For28#backWriteGenNum(nc.vo.pu.m20.entity.writeback.M28WriteBackVO)
   */
  @Override
  public void backWriteGenNum(M28WriteBackVO[] vos) throws BusinessException {
    try {
      new ReWrite20For28BP().backWriteGenNum(vos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

  }

}
