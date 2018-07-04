package nc.pubimpl.pu.m23.ic.m45;

import nc.bs.pu.m23.writeback.ic.m45.Writeback23For45BP;
import nc.pubitf.pu.m23.ic.m45.IWriteback23For45;
import nc.pubitf.pu.m23.ic.m45.IWriteback23For45Para;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�������ṩ�����Ĳɹ���ⵥ�Ļ�д����ʵ����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-8 ����03:57:12
 */
public class Writeback23For45Impl implements IWriteback23For45 {

  @Override
  public void writebackNum(IWriteback23For45Para[] paraArray)
      throws BusinessException {
    try {
      // ����BP
      Writeback23For45BP bp = new Writeback23For45BP();
      bp.writeback(paraArray);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }
}
