package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * ��ע�������������������
 * ���ݶ���ִ���еĶ�ִ̬����Ķ�ִ̬���ࡣ
 * �������ڣ�(2010-7-20)
 * 
 * @author ƽ̨�ű�����
 */
public class N_422X_SAVE extends AbstractCompiler2 {
  // private Hashtable m_keyHas = null;
  //
  // private java.util.Hashtable m_methodReturnHas = new java.util.Hashtable();

  /**
   * N_422X_SAVE ������ע�⡣
   */
  public N_422X_SAVE() {
    super();
  }

  /*
   * ��ע��ƽ̨��дԭʼ�ű�
   */
  @Override
  public String getCodeRemark() {
    return "	Object retValue = null;\nnc.vo.pu.m422x.entity.StoreReqAppVO[] vos = (nc.vo.pu.m422x.entity.StoreReqAppVO[])getVos();\nretValue = nc.bs.framework.common.NCLocator.getInstance().lookup(nc.itf.pu.m422x.IStoreReqAppApprove.class).sendapprove(vos,this);\nreturn retValue;\n";
  }

  /*
   * ��ע��ƽ̨��д������ �ӿ�ִ����
   */
  @Override
  public Object runComClass(PfParameterVO vo) throws BusinessException {
    try {
      super.m_tmpVo = vo;
      Object retValue = null;
      nc.vo.pu.m422x.entity.StoreReqAppVO[] vos =
          (nc.vo.pu.m422x.entity.StoreReqAppVO[]) this.getVos();
      retValue =
          nc.bs.framework.common.NCLocator.getInstance()
              .lookup(nc.itf.pu.m422x.IStoreReqAppApprove.class)
              .sendapprove(vos, this);
      return retValue;
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
