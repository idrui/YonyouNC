package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * 备注：采购发票的入库单推式保存 单据动作执行中的动态执行类的动态执行类。 创建日期：(2010-5-5)
 * 
 * @author 平台脚本生成
 */
public class N_25_PUSHSAVEVO extends AbstractCompiler2 {
  // private java.util.Hashtable m_methodReturnHas=new java.util.Hashtable();
  // private Hashtable m_keyHas = null;

  /**
   * N_25_PUSHSAVEVO 构造子注解。
   */
  public N_25_PUSHSAVEVO() {
    super();
  }

  /*
   * 备注：平台编写原始脚本
   */
  @Override
  public String getCodeRemark() {
    return "  Object retValue=null;\n  nc.vo.pu.m25.entity.InvoiceVO[] vos=(nc.vo.pu.m25.entity.InvoiceVO[])getVos();\n  nc.pubitf.pu.m25.ic.m45.IInvoicePushSaveFor45 service=nc.bs.framework.common.\n  NCLocator.getInstance().lookup(nc.pubitf.pu.m25.ic.m45.IInvoicePushSaveFor45.class);\n  retValue = service.pushSave(vos);\n  return retValue;\n";
  }

  /*
   * 备注：平台编写规则类 接口执行类
   */
  @Override
  public Object runComClass(PfParameterVO vo) throws BusinessException {
    try {
      super.m_tmpVo = vo;
      nc.vo.pu.m25.entity.InvoiceVO[] retValue = null;
      nc.vo.pu.m25.entity.InvoiceVO[] vos =
          (nc.vo.pu.m25.entity.InvoiceVO[]) this.getVos();
      nc.pubitf.pu.m25.ic.m45.IInvoicePushSaveFor45 service =
          nc.bs.framework.common.NCLocator.getInstance().lookup(
              nc.pubitf.pu.m25.ic.m45.IInvoicePushSaveFor45.class);
      retValue = service.pushSave(vos);
      // 更新流程平台中的发票VO
      if (null == retValue || retValue.length == 0) {
        this.setVo(null);
      }
      else {
        this.setVo(retValue[0]);
      }
      this.setVos(retValue);
      return retValue;
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
