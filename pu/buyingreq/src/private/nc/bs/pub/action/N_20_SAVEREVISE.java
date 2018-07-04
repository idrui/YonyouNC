package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pu.m20.context.PraybillContext;
import nc.vo.pu.m20.pub.PraybillContextUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * ��ע���빺�����޶� ���ݶ���ִ���еĶ�ִ̬����Ķ�ִ̬���ࡣ �������ڣ�(2010-6-22)
 * 
 * @author ƽ̨�ű�����
 */
public class N_20_SAVEREVISE extends AbstractCompiler2 {

  /**
   * N_20_SAVEREVISE ������ע�⡣
   */
  public N_20_SAVEREVISE() {
    super();
  }

  /*
   * ��ע��ƽ̨��дԭʼ�ű�
   */
  @Override
  public String getCodeRemark() {
    return "  // ####���ű����뺬�з���ֵ,����DLG��PNL������������з���ֵ####\n      nc.vo.pu.m20.entity.PraybillVO[] inObject =\n          (nc.vo.pu.m20.entity.PraybillVO[]) this.getVos();\n            PraybillContext[] ptxs = PraybillContextUtil.getPtxs(vo);\n      Object retValue =\n          nc.bs.framework.common.NCLocator.getInstance()\n              .lookup(nc.itf.pu.m20.IPraybillRevise.class)\n              .reviseSave(inObject, ptxs);\n      return retValue;\n";/* -=notranslate=- */
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

      PraybillContext[] ptxs = PraybillContextUtil.getPtxs(vo);
      Object retValue =
        nc.bs.framework.common.NCLocator.getInstance()
        .lookup(nc.itf.pu.m20.IPraybillRevise.class)
        .reviseSave(inObject, ptxs);
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
