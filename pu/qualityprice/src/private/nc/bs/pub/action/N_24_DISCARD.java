package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * ��ע���۸���㵥������ ���ݶ���ִ���еĶ�ִ̬����Ķ�ִ̬���ࡣ �������ڣ�(2010-7-23)
 * 
 * @author ƽ̨�ű�����
 */
public class N_24_DISCARD extends AbstractCompiler2 {
  // private java.util.Hashtable m_methodReturnHas = new java.util.Hashtable();

  // private Hashtable m_keyHas = null;

  /**
   * N_20_DISCARD ������ע�⡣
   */
  public N_24_DISCARD() {
    super();
  }

  /*
   * ��ע��ƽ̨��дԭʼ�ű�
   */
  @Override
  public String getCodeRemark() {
    return "  nc.vo.pu.m24.entity.PricestlVO[] inObject = (nc.vo.pu.m24.entity.PricestlVO[]) getVos();\n nc.bs.framework.common.NCLocator.getInstance().lookup(nc.itf.pu.m24.IPricestlMaintain.class).delete(inObject);\n";
  }

  /*
   * ��ע��ƽ̨��д������ �ӿ�ִ����
   */
  @Override
  public Object runComClass(PfParameterVO vo) throws BusinessException {
    try {
      super.m_tmpVo = vo;
      nc.vo.pu.m24.entity.PricestlVO[] inObject =
          (nc.vo.pu.m24.entity.PricestlVO[]) this.getVos();
      nc.bs.framework.common.NCLocator.getInstance()
          .lookup(nc.itf.pu.m24.IPricestlMaintain.class).delete(inObject);
      return null;
    }
    catch (Exception ex) {
      if (ex instanceof BusinessException) {
        throw (BusinessException) ex;
      }

      throw new PFBusinessException(ex.getMessage(), ex);

    }
  }

  // /*
  // * ��ע�����ýű�������HAS
  // */
  // private void setParameter(String key, Object val) {
  // if (m_keyHas == null) {
  // m_keyHas = new Hashtable();
  // }
  // if (val != null) {
  // m_keyHas.put(key, val);
  // }
  // }
}
