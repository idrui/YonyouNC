package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * 备注：期初暂估单的审批 单据动作执行中的动态执行类的动态执行类。 创建日期：(2010-9-8)
 * 
 * @author 平台脚本生成
 */
public class N_4T_APPROVE extends AbstractCompiler2 {
  // private Hashtable m_keyHas = null;
  //
  // private java.util.Hashtable m_methodReturnHas = new java.util.Hashtable();

  /**
   * N_4T_APPROVE 构造子注解。
   */
  public N_4T_APPROVE() {
    super();
  }

  /*
   * 备注：平台编写原始脚本
   */
  @Override
  public String getCodeRemark() {
    return "	nc.vo.pu.m4t.entity.InitialEstVO[] vos  =(nc.vo.pu.m4t.entity.InitialEstVO[]) getVos();\n  Object retObj=nc.bs.framework.common.NCLocator.getInstance().lookup(nc.itf.pu.m4t.IInitialEstApprove.class).approve(vos,this);\n  return retObj;\n";
  }

  /*
   * 备注：平台编写规则类 接口执行类
   */
  @Override
  public Object runComClass(PfParameterVO vo) throws BusinessException {
    try {
      super.m_tmpVo = vo;
      nc.vo.pu.m4t.entity.InitialEstVO[] vos =
          (nc.vo.pu.m4t.entity.InitialEstVO[]) this.getVos();
      Object retObj =
          nc.bs.framework.common.NCLocator.getInstance()
              .lookup(nc.itf.pu.m4t.IInitialEstApprove.class)
              .approve(vos, this);
      return retObj;
    }
    catch (Exception ex) {
      if (ex instanceof BusinessException) {
        throw (BusinessException) ex;
      }
      throw new PFBusinessException(ex.getMessage(), ex);
    }
  }

  /*
   * 备注：设置脚本变量的HAS
   */
  // private void setParameter(String key, Object val) {
  // if (this.m_keyHas == null) {
  // this.m_keyHas = new Hashtable();
  // }
  // if (val != null) {
  // this.m_keyHas.put(key, val);
  // }
  // }
}
