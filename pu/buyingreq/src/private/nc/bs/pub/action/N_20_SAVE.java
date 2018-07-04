package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * ��ע���빺�������� ���ݶ���ִ���еĶ�ִ̬����Ķ�ִ̬���ࡣ �������ڣ�(2010-4-26)
 * 
 * @author ƽ̨�ű�����
 */
public class N_20_SAVE extends AbstractCompiler2 {
  // private final java.util.Hashtable m_methodReturnHas = new
  // java.util.Hashtable();
  //
  // private Hashtable m_keyHas = null;

  /**
   * N_20_SAVE ������ע�⡣
   */
  public N_20_SAVE() {
    super();
  }

  /*
   * ��ע��ƽ̨��дԭʼ�ű�
   */
  @Override
  public String getCodeRemark() {
    return "	Object retObj=null;\n	nc.vo.pu.m20.entity.PraybillVO[] inObject = (nc.vo.pu.m20.entity.PraybillVO[]) getVos();\n          Object retValue =nc.bs.framework.common.NCLocator.getInstance().lookup(nc.itf.pu.m20.IPraybillApprove.class).sendapprove(inObject,this);\n          return retValue;\n";
  }

  /*
   * ��ע��ƽ̨��д������ �ӿ�ִ����
   */
  @Override
  public Object runComClass(PfParameterVO vo) throws BusinessException {
    try {
      super.m_tmpVo = vo;

      nc.vo.pu.m20.entity.PraybillVO[] inObject =
          (nc.vo.pu.m20.entity.PraybillVO[]) this.getVos();
      Object retValue =
          nc.bs.framework.common.NCLocator.getInstance()
              .lookup(nc.itf.pu.m20.IPraybillApprove.class)
              .sendapprove(inObject, this);
      return retValue;
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
