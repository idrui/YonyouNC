package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * ��ע���빺���ı��� ���ݶ���ִ���еĶ�ִ̬����Ķ�ִ̬���ࡣ �������ڣ�(2010-1-27)
 * 
 * @author ƽ̨�ű�����
 */
public class N_20_SAVEBASE extends AbstractCompiler2 {

  /**
   * N_20_SAVEBASE ������ע�⡣
   */
  public N_20_SAVEBASE() {
    super();
  }

  /*
   * ��ע��ƽ̨��дԭʼ�ű�
   */
  @Override
  public String getCodeRemark() {
    return "	//####���ű����뺬�з���ֵ,����DLG��PNL������������з���ֵ####\n  nc.vo.pu.m20.entity.PraybillVO[] inObject  =(nc.vo.pu.m20.entity.PraybillVO[])getVos ();\n  Object retValue=nc.bs.framework.common.NCLocator.getInstance().lookup(nc.itf.pu.m20.IPraybillMaintain.class).insert(inObject);\n  return retValue;\n";/* -=notranslate=- */
  }

  /*
   * ��ע��ƽ̨��д������ �ӿ�ִ����
   */
  @Override
  public Object runComClass(PfParameterVO vo) throws BusinessException {
    try {
      super.m_tmpVo = vo;
      // ####���ű����뺬�з���ֵ,����DLG��PNL������������з���ֵ####
      nc.vo.pu.m20.entity.PraybillVO[] inObject =
        (nc.vo.pu.m20.entity.PraybillVO[]) this.getVos();
      Object retValue =
        nc.bs.framework.common.NCLocator.getInstance()
        .lookup(nc.itf.pu.m20.IPraybillMaintain.class).saveBase(inObject);
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
