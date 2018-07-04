package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * ��ע�������������� ���ݶ���ִ���еĶ�ִ̬����Ķ�ִ̬���ࡣ �������ڣ�(2010-1-19)
 * 
 * @author ƽ̨�ű�����
 */
public class N_23_APPROVE extends AbstractCompiler2 {

  /**
   * N_23_APPROVE ������ע�⡣
   */
  public N_23_APPROVE() {
    super();
  }

  /*
   * ��ע��ƽ̨��дԭʼ�ű�
   */
  @Override
  public String getCodeRemark() {
    return "	Object retValue = null;\n      /*********************** ���õ��������������� �����޸� ******************/\n      nc.vo.pu.m23.entity.ArriveVO[] inObject = (nc.vo.pu.m23.entity.ArriveVO[]) getVos();\n      \n      retValue = nc.bs.framework.common.NCLocator.getInstance().lookup(nc.itf.pu.m23.approve.IArriveApprove.class)\n          .approveArrive(inObject, this);\n      /***********************************************************************/\n      \n      return retValue;\n";/* -=notranslate=- */
  }

  /*
   * ��ע��ƽ̨��д������ �ӿ�ִ����
   */
  @Override
  public Object runComClass(PfParameterVO vo) throws BusinessException {
    try {
      super.m_tmpVo = vo;
      Object retValue = null;
      /*********************** ���õ��������������� �����޸� ******************/
      nc.vo.pu.m23.entity.ArriveVO[] inObject =
        (nc.vo.pu.m23.entity.ArriveVO[]) this.getVos();

      retValue =
        nc.bs.framework.common.NCLocator.getInstance()
        .lookup(nc.itf.pu.m23.approve.IArriveApprove.class)
        .approveArrive(inObject, this);
      /***********************************************************************/

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
