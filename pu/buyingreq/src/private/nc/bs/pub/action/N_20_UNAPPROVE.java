package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * ��ע���빺�������� ���ݶ���ִ���еĶ�ִ̬����Ķ�ִ̬���ࡣ �������ڣ�(2010-2-2)
 * 
 * @author ƽ̨�ű�����
 */
public class N_20_UNAPPROVE extends AbstractCompiler2 {

  /**
   * N_20_UNAPPROVE ������ע�⡣
   */
  public N_20_UNAPPROVE() {
    super();
  }

  /*
   * ��ע��ƽ̨��дԭʼ�ű�
   */
  @Override
  public String getCodeRemark() {
    return "	//####���ű����뺬�з���ֵ,����DLG��PNL������������з���ֵ####\n	Object retObj=null;\nnc.vo.pu.m20.entity.PraybillVO[] inObject = (nc.vo.pu.m20.entity.PraybillVO[]) getVos();\n          Object retValue =nc.bs.framework.common.NCLocator.getInstance().lookup(nc.itf.pu.m20.IPraybillApprove.class).unApprove(inObject,this);\n          return retValue;\n";/* -=notranslate=- */
  }

  /*
   * ��ע��ƽ̨��д������ �ӿ�ִ����
   */
  @Override
  public Object runComClass(PfParameterVO vo) throws BusinessException {
    try {
      super.m_tmpVo = vo;
      // ####���ű����뺬�з���ֵ,����DLG��PNL������������з���ֵ####
      // Object retObj = null;
      nc.vo.pu.m20.entity.PraybillVO[] inObject =
        (nc.vo.pu.m20.entity.PraybillVO[]) this.getVos();
      Object retValue =
        nc.bs.framework.common.NCLocator.getInstance()
        .lookup(nc.itf.pu.m20.IPraybillApprove.class)
        .unApprove(inObject, this);
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
