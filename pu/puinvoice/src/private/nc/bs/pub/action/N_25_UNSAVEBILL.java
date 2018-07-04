package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * 备注：采购发票的收回 单据动作执行中的动态执行类的动态执行类。 创建日期：(2010-10-12)
 * 
 * @author 平台脚本生成
 */
public class N_25_UNSAVEBILL extends AbstractCompiler2 {

  /**
   * N_25_UNSAVEBILL 构造子注解。
   */
  public N_25_UNSAVEBILL() {
    super();
  }

  /*
   * 备注：平台编写原始脚本
   */
  @Override
  public String getCodeRemark() {
    return "	Object retValue = null;\n      nc.vo.pu.m25.entity.InvoiceVO[] vos =\n          (nc.vo.pu.m25.entity.InvoiceVO[]) this.getVos();\n      /************* 该组件为批动作工作流处理开始...不能进行修改 *********************/\n      retValue = NCLocator.getInstance().lookup(nc.itf.pu.m25.IInvoiceApprove.class).unSendapprove(vos, this);\n      /**************************************************************************/\n      /************** 返回结果 *************************************************/\n      return retValue;\n";/* -=notranslate=- */
  }

  /*
   * 备注：平台编写规则类 接口执行类
   */
  @Override
  public Object runComClass(PfParameterVO vo) throws BusinessException {
    try {
      super.m_tmpVo = vo;
      Object retValue = null;
      nc.vo.pu.m25.entity.InvoiceVO[] vos =
        (nc.vo.pu.m25.entity.InvoiceVO[]) this.getVos();
      /************* 该组件为批动作工作流处理开始...不能进行修改 *********************/
      retValue =
        NCLocator.getInstance().lookup(nc.itf.pu.m25.IInvoiceApprove.class)
        .unSendapprove(vos, this);
      /**************************************************************************/
      /************** 返回结果 *************************************************/
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
