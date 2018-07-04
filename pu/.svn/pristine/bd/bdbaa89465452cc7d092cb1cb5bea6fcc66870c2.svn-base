/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-27 下午01:41:32
 */
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.pubitf.pu.m25.pf.IInvoiceSendAP;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购发票传应付组件脚本
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-27 下午01:41:32
 */
public class N_25_SENDAP extends AbstractCompiler2 {

  /**
   * 父类方法重写
   * 
   * @see nc.bs.pub.compiler.AbstractCompiler2#runComClass(nc.vo.pub.compiler.PfParameterVO)
   */
  @Override
  public Object runComClass(PfParameterVO paraVo) throws BusinessException {

    try {
      super.m_tmpVo = paraVo;

      /************* 该组件为批动作工作流处理开始...不能进行修改 *********************/
      Object retValue = null;
      nc.vo.pu.m25.entity.InvoiceVO[] vos = null;
      if ((this.getVos() == null || this.getVos().length == 0)
          && null != this.getVo()) {
        vos = new nc.vo.pu.m25.entity.InvoiceVO[] {
          (nc.vo.pu.m25.entity.InvoiceVO) this.getVo()
        };
      }
      else {
        vos = (nc.vo.pu.m25.entity.InvoiceVO[]) this.getVos();
      }
      nc.vo.pu.m25.env.InvoiceUIToBSEnv env =
          nc.vo.pu.m25.pub.InvoiceEnvExtractUtil.getEnv(paraVo);
      retValue =
          NCLocator.getInstance().lookup(IInvoiceSendAP.class).sendAP(vos, env,
              paraVo);
      return retValue;
      /**************************************************************************/

    }
    catch (Exception ex) {
      if (ex instanceof BusinessException) {
        throw (BusinessException) ex;
      }
      throw new PFBusinessException(ex.getMessage(), ex);
    }

  }

}
