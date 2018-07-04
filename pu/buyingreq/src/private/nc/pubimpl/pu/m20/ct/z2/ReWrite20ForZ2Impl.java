/**
 * $�ļ�˵��$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-4 ����04:06:02
 */
package nc.pubimpl.pu.m20.ct.z2;

import nc.bs.pu.m20.rewrite.ct.ReWrite20ForCTBP;
import nc.pubitf.pu.m20.ct.z2.IReWrite20ForZ2;
import nc.vo.pu.m20.entity.writeback.GenNumWriteBackVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ���ͬ��д�빺������ʵ����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-2-4 ����04:06:02
 */
public class ReWrite20ForZ2Impl implements IReWrite20ForZ2 {

  /**
   * ���෽����д
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
