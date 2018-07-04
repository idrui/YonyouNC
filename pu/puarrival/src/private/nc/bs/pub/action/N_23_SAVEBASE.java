package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * ��ע���������ı��� ���ݶ���ִ���еĶ�ִ̬����Ķ�ִ̬���ࡣ �������ڣ�(2010-2-25)
 * 
 * @author ƽ̨�ű�����
 */
public class N_23_SAVEBASE extends AbstractCompiler2 {
  /**
   * N_23_SAVEBASE ������ע�⡣
   */
  public N_23_SAVEBASE() {
    super();
  }

  /*
   * ��ע��ƽ̨��дԭʼ�ű�
   */
  @Override
  public String getCodeRemark() {
    return "  Object retValue = null;\n      /*********************** ���õ������ı������ �����޸� ******************/\n      nc.vo.pu.m23.entity.ArriveVO[] inObject = (nc.vo.pu.m23.entity.ArriveVO[]) getVos();\n      nc.vo.pu.m23.env.ArrivalUIToBSEnv[] envs =\n          nc.vo.pu.m23.pub.ArrivalEnvExtractUtil.getEnvs(vo);      \n      retValue = nc.bs.framework.common.NCLocator.getInstance().lookup(nc.itf.pu.m23.maintain.IArriveMaintain.class)\n          .saveBase(inObject,envs);\n      /***********************************************************************/\n      \n      return retValue;\n";/* -=notranslate=- */
  }

  /*
   * ��ע��ƽ̨��д������ �ӿ�ִ����
   */
  @Override
  public Object runComClass(PfParameterVO vo) throws BusinessException {
    try {
      super.m_tmpVo = vo;
      Object retValue = null;
      /*********************** ���õ������ı������ �����޸� ******************/
      nc.vo.pu.m23.entity.ArriveVO[] inObject =
        (nc.vo.pu.m23.entity.ArriveVO[]) this.getVos();
      nc.vo.pu.m23.env.ArrivalUIToBSEnv[] envs =
        nc.vo.pu.m23.pub.ArrivalEnvExtractUtil.getEnvs(vo);
      retValue =
        nc.bs.framework.common.NCLocator.getInstance()
        .lookup(nc.itf.pu.m23.maintain.IArriveMaintain.class)
        .saveBase(inObject, envs);
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
