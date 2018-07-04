package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * ��ע���ڳ��ݹ��������� ���ݶ���ִ���еĶ�ִ̬����Ķ�ִ̬���ࡣ �������ڣ�(2010-9-7)
 * 
 * @author ƽ̨�ű�����
 */
public class N_4T_DISCARD extends AbstractCompiler2 {
  // private Hashtable m_keyHas = null;
  //
  // private java.util.Hashtable m_methodReturnHas = new java.util.Hashtable();

  /**
   * N_4T_DISCARD ������ע�⡣
   */
  public N_4T_DISCARD() {
    super();
  }

  /*
   * ��ע��ƽ̨��дԭʼ�ű�
   */
  @Override
  public String getCodeRemark() {
    return "  nc.vo.pu.m4t.entity.InitialEstVO[] vos  =(nc.vo.pu.m4t.entity.InitialEstVO[]) getVos();\n  nc.vo.pu.m4t.entity.InitialEstContext context=(nc.vo.pu.m4t.entity.InitialEstContext)getUserObj();\n  nc.bs.framework.common.NCLocator.getInstance().lookup(nc.itf.pu.m4t.IInitialEstMaintain.class).delete(vos,context);\n  return null;\n";
  }

  /*
   * ��ע��ƽ̨��д������ �ӿ�ִ����
   */
  @Override
  public Object runComClass(PfParameterVO vo) throws BusinessException {
    try {
      super.m_tmpVo = vo;
      nc.vo.pu.m4t.entity.InitialEstVO[] vos =
          (nc.vo.pu.m4t.entity.InitialEstVO[]) this.getVos();
      nc.vo.pu.m4t.entity.InitialEstContext context =
          (nc.vo.pu.m4t.entity.InitialEstContext) this.getUserObj();
      nc.bs.framework.common.NCLocator.getInstance()
          .lookup(nc.itf.pu.m4t.IInitialEstMaintain.class).delete(vos, context);
      return null;
    }
    catch (Exception ex) {
      if (ex instanceof BusinessException) {
        throw (BusinessException) ex;
      }
      throw new PFBusinessException(ex.getMessage(), ex);
    }
  }

  /*
   * ��ע�����ýű�������HAS
   */
  // private void setParameter(String key, Object val) {
  // if (this.m_keyHas == null) {
  // this.m_keyHas = new Hashtable();
  // }
  // if (val != null) {
  // this.m_keyHas.put(key, val);
  // }
  // }
}
