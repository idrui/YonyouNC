/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-12 ����08:54:51
 */
package nc.bs.pu.m21.writeback.source;

import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.pubitf.invp.po.IpoOrderForPu;
import nc.pubitf.invp.po.PoOrderWritebackVO;
import nc.vo.pu.pub.writeback.IWriteBackSource;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * �ɹ�������д���ƻ��ƻ�����
 * 
 * @since 6.0
 * @version 2011-12-9 ����10:59:14
 * @author �����
 */
public class WriteBack4F implements IWriteBackSource {

  /**
   * ���෽����д
   * 
   * @see nc.vo.pu.pub.writeback.IWriteBackSource#writeback(java.util.List)
   */
  @Override
  public void writeback(List<RewritePara> rwParas) {
    try {
      this.getWriteBackService().writebackForPu(this.getWriteBackPara(rwParas));
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * �����д����
   * 
   * @param rwParas
   * @return ���ƻ��ƻ�������д����
   */
  private PoOrderWritebackVO[] getWriteBackPara(List<RewritePara> rwParas) {
    PoOrderWritebackVO[] paras = new PoOrderWritebackVO[rwParas.size()];
    for (int i = 0; i < paras.length; ++i) {
      final RewritePara rwPara = rwParas.get(i);
      paras[i] = new PoOrderWritebackVO();
      paras[i].setBsc(UFBoolean.FALSE);// ί���־
      paras[i].setDiffNum(rwPara.getNnum());// ��������
      paras[i].setPk_po(rwPara.getCsrcid());// ��Դid
    }
    return paras;
  }

  /**
   * ���ƻ��ƻ������ط���
   * 
   * @return
   */
  private IpoOrderForPu getWriteBackService() {
    return NCLocator.getInstance().lookup(IpoOrderForPu.class);
  }

}
