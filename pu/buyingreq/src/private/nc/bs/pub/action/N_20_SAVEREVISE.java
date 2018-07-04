package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pu.m20.context.PraybillContext;
import nc.vo.pu.m20.pub.PraybillContextUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * 备注：请购单的修订 单据动作执行中的动态执行类的动态执行类。 创建日期：(2010-6-22)
 * 
 * @author 平台脚本生成
 */
public class N_20_SAVEREVISE extends AbstractCompiler2 {

  /**
   * N_20_SAVEREVISE 构造子注解。
   */
  public N_20_SAVEREVISE() {
    super();
  }

  /*
   * 备注：平台编写原始脚本
   */
  @Override
  public String getCodeRemark() {
    return "  // ####本脚本必须含有返回值,返回DLG和PNL的组件不允许有返回值####\n      nc.vo.pu.m20.entity.PraybillVO[] inObject =\n          (nc.vo.pu.m20.entity.PraybillVO[]) this.getVos();\n            PraybillContext[] ptxs = PraybillContextUtil.getPtxs(vo);\n      Object retValue =\n          nc.bs.framework.common.NCLocator.getInstance()\n              .lookup(nc.itf.pu.m20.IPraybillRevise.class)\n              .reviseSave(inObject, ptxs);\n      return retValue;\n";/* -=notranslate=- */
  }

  /*
   * 备注：平台编写规则类 接口执行类
   */
  @Override
  public Object runComClass(PfParameterVO vo) throws BusinessException {
    try {
      super.m_tmpVo = vo;
      // ####本脚本必须含有返回值,返回DLG和PNL的组件不允许有返回值####
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
