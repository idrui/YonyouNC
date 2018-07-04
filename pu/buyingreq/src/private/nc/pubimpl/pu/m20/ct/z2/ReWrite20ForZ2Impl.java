/**
 * $文件说明$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-4 下午04:06:02
 */
package nc.pubimpl.pu.m20.ct.z2;

import nc.bs.pu.m20.rewrite.ct.ReWrite20ForCTBP;
import nc.pubitf.pu.m20.ct.z2.IReWrite20ForZ2;
import nc.vo.pu.m20.entity.writeback.GenNumWriteBackVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购合同回写请购单服务实现类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-2-4 下午04:06:02
 */
public class ReWrite20ForZ2Impl implements IReWrite20ForZ2 {

  /**
   * 父类方法重写
   * 
   * @see nc.pubitf.pu.m20.ct.z2.IReWrite20ForZ2#backWriteGenNum(nc.vo.pu.m20.entity.writeback.M29WriteBackVO)
   */
  @Override
  public void backWriteGenNum(GenNumWriteBackVO[] vo) throws BusinessException {
    try {
      new ReWrite20ForCTBP().backWriteNum(vo);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

  }

}
