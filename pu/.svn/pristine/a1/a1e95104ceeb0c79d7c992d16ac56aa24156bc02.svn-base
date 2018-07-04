package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * 备注：采购发票的采购发票保存 单据动作执行中的动态执行类的动态执行类。 创建日期：(2009-7-3)
 * 
 * @author 平台脚本生成
 */
public class N_25_SAVEBASE extends AbstractCompiler2 {

  /**
   * N_25_SAVE 构造子注解。
   */
  public N_25_SAVEBASE() {
    super();
  }

  /*
   * 备注：平台编写原始脚本
   */
  @Override
  public String getCodeRemark() {
    return "  /*************从平台取得由该动作传入的入口参数。本方法取得需要的VO。************/\n nc.vo.pu.m25.entity.InvoiceVO[] inObject  =(nc.vo.pu.m25.entity.InvoiceVO[]) getVos ();\n /**************设置参数******************************************************/\n  setParameter(\"INVO\", inObject);\n /**************执行采购发票的保存（更新）方法 ********************************/\n  return (nc.vo.pu.m25.entity.InvoiceVO[])runClass(\"nc.impl.pu.m25.action.InvoiceMaintainImpl\",\"update\",\n        \"&INVO:nc.vo.pu.m25.entity.InvoiceVO\",vo,m_keyHas,m_methodReturnHas);\n";/* -=notranslate=- */
  }

  /*
   * 备注：平台编写规则类 接口执行类
   */
  @Override
  public Object runComClass(PfParameterVO vo) throws BusinessException {

    try {
      super.m_tmpVo = vo;

      /********************** 以下不能修改 *******************************************/
      Object retObj = null;
      nc.vo.pu.m25.entity.InvoiceVO[] vos =
        (nc.vo.pu.m25.entity.InvoiceVO[]) this.getVos();
      nc.vo.pu.m25.env.InvoiceUIToBSEnv[] envs =
        nc.vo.pu.m25.pub.InvoiceEnvExtractUtil.getEnvs(vo);
      retObj =
        NCLocator.getInstance().lookup(nc.itf.pu.m25.IInvoiceMaintain.class)
        .save(vos, envs);
      return retObj;
      /********************** 以上不能修改 *******************************************/
    }
    catch (Exception ex) {
      if (ex instanceof BusinessException) {
        throw (BusinessException) ex;
      }
      throw new PFBusinessException(ex.getMessage(), ex);
    }
  }
}
