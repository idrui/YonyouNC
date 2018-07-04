package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;

/**
 * 备注：采购订单的保存
 * 单据动作执行中的动态执行类的动态执行类。
 * 创建日期：(2010-6-10)
 * 
 * @author 平台脚本生成
 */
public class N_21_SAVEBASE extends AbstractCompiler2 {

  /**
   * N_21_SAVEBASE 构造子注解。
   */
  public N_21_SAVEBASE() {
    super();
  }

  /*
   * 备注：平台编写原始脚本
   */
  @Override
  public String getCodeRemark() {
    return " nc.vo.pu.m21.entity.OrderVO[] inObject =\n          (nc.vo.pu.m21.entity.OrderVO[]) this.getVos();\n      nc.vo.pu.m21.context.OrderContext[] ctxs =\n          nc.vo.pu.m21.pub.OrderContextUtil.getCtxs(vo);\n      nc.vo.pu.m21.context.OrderContext ctx = null;\n      if (ctxs != null) {\n        ctx = ctxs[0];\n      }\n      return nc.bs.framework.common.NCLocator.getInstance()\n          .lookup(nc.itf.pu.m21.IOrderMaintain.class).save(inObject, ctx);\n";
  }

  /*
   * 备注：平台编写规则类 接口执行类
   */
  @Override
  public Object runComClass(PfParameterVO vo) throws BusinessException {
    try {
      super.m_tmpVo = vo;
      nc.vo.pu.m21.entity.OrderVO[] inObject =
          (nc.vo.pu.m21.entity.OrderVO[]) this.getVos();
      nc.vo.pu.m21.context.OrderContext[] ctxs =
          nc.vo.pu.m21.pub.OrderContextUtil.getCtxs(vo);
      nc.vo.pu.m21.context.OrderContext ctx = null;
      if (ctxs != null) {
        ctx = ctxs[0];
      }
      return nc.bs.framework.common.NCLocator.getInstance()
          .lookup(nc.itf.pu.m21.IOrderMaintain.class).save(inObject, ctx);
    }
    catch (Exception ex) {
      nc.vo.pubapp.pattern.exception.ExceptionUtils.marsh(ex);
    }
    return null;
  }

}
