package nc.pubimpl.pu.m23.qc;

import nc.bs.pu.m23.writeback.qc.Writeback23ForC003BP;
import nc.pubitf.pu.m23.qc.IWriteback23ForC003;
import nc.vo.pu.m23.entity.ArriveBbVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�������ṩ���ʼ챨��Ļ�д����ӿ�ʵ����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-7-15 ����08:05:18
 */
public class Writeback23ForC003Impl implements IWriteback23ForC003 {

  @Override
  public void delQCResultWhenReportUnApp(ArriveBbVO[] paras)
      throws BusinessException {
    try {
      new Writeback23ForC003BP().writebackWhenUnApprove(paras);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  @Override
  public void saveQCResultWhenReportApp(ArriveBbVO[] paras)
      throws BusinessException {
    try {
      new Writeback23ForC003BP().writebackWhenApprove(paras);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }
}
