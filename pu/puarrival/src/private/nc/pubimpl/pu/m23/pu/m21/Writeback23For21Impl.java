package nc.pubimpl.pu.m23.pu.m21;

import nc.bs.pu.m23.writeback.pu.m21.Writeback23For21BP;
import nc.pubitf.pu.m23.pu.m21.IWriteback23For21;
import nc.pubitf.pu.m23.pu.m21.IWriteback23For21Para;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�������ṩ���ɹ����������Ļ�д�ۼƲ��������Ļ�д����ʵ����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-8 ����03:57:12
 */
public class Writeback23For21Impl implements IWriteback23For21 {

  @Override
  public void writebackNum(IWriteback23For21Para[] paraArray)
      throws BusinessException {
    // ����BP
    try {
      Writeback23For21BP bp = new Writeback23For21BP();
      bp.writeback(paraArray);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }
}
