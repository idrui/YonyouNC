package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * ��ע���빺�����ջ� ���ݶ���ִ���еĶ�ִ̬����Ķ�ִ̬���ࡣ �������ڣ�(2010-10-12)
 * 
 * @author ƽ̨�ű�����
 */
public class N_20_UNSAVEBILL extends AbstractCompiler2 {

  /**
   * N_20_UNSAVE ������ע�⡣
   */
  public N_20_UNSAVEBILL() {
    super();
  }

  /*
   * ��ע��ƽ̨��дԭʼ�ű�
   */
  @Override
  public String getCodeRemark() {
    return "	Object retObj=null;\n	nc.vo.pu.m20.entity.PraybillVO[] inObject = (nc.vo.pu.m20.entity.PraybillVO[]) getVos();\n          Object retValue =nc.bs.framework.common.NCLocator.getInstance().lookup(nc.itf.pu.m20.IPraybillApprove.class).unSendapprove(inObject,this);\n          return retValue;\n";
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
              .unSendapprove(inObject, this);
      return retValue;
    }
    catch (Exception ex) {
      if (ex instanceof BusinessException) {
        throw (BusinessException) ex;
      }

      throw new PFBusinessException(ex.getMessage(), ex);

    }
  }

}
