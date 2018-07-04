package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * 备注：请购单的送审 单据动作执行中的动态执行类的动态执行类。 创建日期：(2010-4-26)
 * 
 * @author 平台脚本生成
 */
public class N_20_SAVE extends AbstractCompiler2 {
  // private final java.util.Hashtable m_methodReturnHas = new
  // java.util.Hashtable();
  //
  // private Hashtable m_keyHas = null;

  /**
   * N_20_SAVE 构造子注解。
   */
  public N_20_SAVE() {
    super();
  }

  /*
   * 备注：平台编写原始脚本
   */
  @Override
  public String getCodeRemark() {
    return "	Object retObj=null;\n	nc.vo.pu.m20.entity.PraybillVO[] inObject = (nc.vo.pu.m20.entity.PraybillVO[]) getVos();\n          Object retValue =nc.bs.framework.common.NCLocator.getInstance().lookup(nc.itf.pu.m20.IPraybillApprove.class).sendapprove(inObject,this);\n          return retValue;\n";
  }

  /*
   * 备注：平台编写规则类 接口执行类
   */
  @Override
  public Object runComClass(PfParameterVO vo) throws BusinessException {
    try {
      super.m_tmpVo = vo;

      nc.vo.pu.m20.entity.PraybillVO[] inObject =
          (nc.vo.pu.m20.entity.PraybillVO[]) this.getVos();
      Object retValue =
          nc.bs.framework.common.NCLocator.getInstance()
              .lookup(nc.itf.pu.m20.IPraybillApprove.class)
              .sendapprove(inObject, this);
      return retValue;
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
  // if (m_keyHas == null) {
  // m_keyHas = new Hashtable();
  // }
  // if (val != null) {
  // m_keyHas.put(key, val);
  // }
  // }
}
