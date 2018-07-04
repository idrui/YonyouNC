/**
 * $�ļ�˵��$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-4 ����02:01:52
 */
package nc.pubimpl.pu.m20.pu.m28;

import nc.bs.pu.m20.rewrite.pu.ReWrite20For28BP;
import nc.pubitf.pu.m20.pu.m28.IReWrite20For28;
import nc.vo.pu.m20.entity.writeback.M28WriteBackVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�۸���������д�빺������ʵ����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-2-4 ����02:01:52
 */
public class ReWrite20For28Impl implements IReWrite20For28 {

  /**
   * ���෽����д
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
