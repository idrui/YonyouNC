/**
 * $�ļ�˵��$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-4 ����11:23:59
 */
package nc.pubimpl.pu.m20.sc.m61;

import nc.bs.pu.m20.rewrite.ReWrite20ForOrderBP;
import nc.pubitf.pu.m20.sc.m61.IReWrite20For61;
import nc.vo.pu.m20.entity.writeback.M61WriteBackVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-2-4 ����11:23:59
 */
public class ReWrite20For61Impl implements IReWrite20For61 {

  /**
   * ���෽����д
   * 
   * @see nc.pubitf.pu.m20.sc.m61.IReWrite20For61#backWriteNum(nc.vo.pu.m20.entity.writeback.M61WriteBackVO[])
   */
  @Override
  public void backWriteNum(M61WriteBackVO[] paraVos) throws BusinessException {
    try {
      new ReWrite20ForOrderBP().backWriteNum(paraVos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

}
