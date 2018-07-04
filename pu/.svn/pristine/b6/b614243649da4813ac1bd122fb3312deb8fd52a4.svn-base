package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * 备注：期初暂估单的暂估应付动作脚本 单据动作执行中的动态执行类的动态执行类。 创建日期：(2010-3-26)
 * 
 * @author 平台脚本生成
 */
public class N_4T_ESTAP extends AbstractCompiler2 {

  /**
   * N_4T_APPROVE 构造子注解。
   */
  public N_4T_ESTAP() {
    super();
  }

  /*
   * 备注：平台编写原始脚本
   */
  @Override
  public String getCodeRemark() {
    return "  nc.vo.pu.m4t.entity.InitialEstVO[] estVos = null;\n      if ((this.getVos() == null) || (this.getVos().length == 0)) {\n        estVos = new nc.vo.pu.m4t.entity.InitialEstVO[] {\n          (nc.vo.pu.m4t.entity.InitialEstVO) this.getVo()\n        };\n      }\n      else {\n        estVos = (nc.vo.pu.m4t.entity.InitialEstVO[]) this.getVos();\n      }\n      nc.bs.framework.common.NCLocator.getInstance()\n          .lookup(nc.pubitf.pu.m4t.pf.IInitialEstAP.class).estimateAP(estVos);\n      return estVos;\n";
  }

  /*
   * 备注：平台编写规则类 接口执行类
   */
  @Override
  public Object runComClass(PfParameterVO vo) throws BusinessException {
    try {
      super.m_tmpVo = vo;
      // /////////////界面编辑框架中的脚本////////////////////////////////
      nc.vo.pu.m4t.entity.InitialEstVO[] estVos = null;
      if (this.getVos() == null || this.getVos().length == 0) {
        estVos = new nc.vo.pu.m4t.entity.InitialEstVO[] {
          (nc.vo.pu.m4t.entity.InitialEstVO) this.getVo()
        };
      }
      else {
        estVos = (nc.vo.pu.m4t.entity.InitialEstVO[]) this.getVos();
      }
      nc.bs.framework.common.NCLocator.getInstance()
          .lookup(nc.pubitf.pu.m4t.pf.IInitialEstAP.class).estimateAP(estVos);
      return estVos;
      // /////////////界面编辑框架中的脚本////////////////////////////////
    }
    catch (Exception ex) {
      if (ex instanceof BusinessException) {
        throw (BusinessException) ex;
      }
      throw new PFBusinessException(ex.getMessage(), ex);
    }
  }

}
