package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * 备注：期初暂估单的SAVEBASE
 * 单据动作执行中的动态执行类的动态执行类。
 * 创建日期：(2011-3-11)
 * 
 * @author 平台脚本生成
 */
public class N_4T_SAVEBASE extends AbstractCompiler2 {

  /**
   * N_4T_SAVEBASE 构造子注解。
   */
  public N_4T_SAVEBASE() {
    super();
  }

  /*
   * 备注：平台编写原始脚本
   */
  @Override
  public String getCodeRemark() {
    return "	Object retObj = null;\n      nc.vo.pu.m4t.entity.InitialEstVO[] vos =\n          (nc.vo.pu.m4t.entity.InitialEstVO[]) this.getVos();\n      nc.vo.pu.m4t.entity.InitialEstContext[] ctxs =\n          nc.vo.pu.m4t.util.InitialContextUtil.getCtxs(vo);\n      nc.vo.pu.m4t.entity.InitialEstContext ctx =\n          org.apache.commons.lang.ArrayUtils.isEmpty(ctxs) ? new nc.vo.pu.m4t.entity.InitialEstContext()\n              : ctxs[0];\n      retObj =\n          nc.bs.framework.common.NCLocator.getInstance()\n              .lookup(nc.itf.pu.m4t.IInitialEstMaintain.class).save(vos, ctx);\n      return retObj;\n";
  }

  /*
   * 备注：平台编写规则类
   * 接口执行类
   */
  @Override
  public Object runComClass(PfParameterVO vo) throws BusinessException {
    try {
      super.m_tmpVo = vo;
      Object retObj = null;
      nc.vo.pu.m4t.entity.InitialEstVO[] vos =
          (nc.vo.pu.m4t.entity.InitialEstVO[]) this.getVos();

      nc.vo.pu.m4t.entity.InitialEstContext[] ctxs =
          nc.vo.pu.m4t.util.InitialContextUtil.getCtxs(vo);
      nc.vo.pu.m4t.entity.InitialEstContext ctx =
          org.apache.commons.lang.ArrayUtils.isEmpty(ctxs) ? new nc.vo.pu.m4t.entity.InitialEstContext()
              : ctxs[0];
      retObj =
          nc.bs.framework.common.NCLocator.getInstance()
              .lookup(nc.itf.pu.m4t.IInitialEstMaintain.class).save(vos, ctx);

      return retObj;
    }
    catch (Exception ex) {
      if (ex instanceof BusinessException) {
        throw (BusinessException) ex;
      }
      throw new PFBusinessException(ex.getMessage(), ex);
    }
  }

  // /*
  // * 备注：设置脚本变量的HAS
  // */
  // private void setParameter(String key, Object val) {
  // if (this.m_keyHas == null) {
  // this.m_keyHas = new Hashtable();
  // }
  // if (val != null) {
  // this.m_keyHas.put(key, val);
  // }
  // }
}
