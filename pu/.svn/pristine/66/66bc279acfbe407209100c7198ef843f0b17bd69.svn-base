package nc.ui.pu.m21.param;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m21.IOrderPayPlanQuery;
import nc.ui.pub.para.ICheckParaFinal;
import nc.vo.pub.BusinessException;
import nc.vo.pub.para.CheckParaVO;
import nc.vo.pub.para.SysInitVO;

/**
 * 付款计划已生成有付款申请单或者付款单则该参数值不允许修改。
 * 
 * @since 6.0
 * @version 2011-4-16 上午09:03:32
 * @author wuxla
 */

public class CheckPO88Para implements ICheckParaFinal {

  @Override
  public CheckParaVO paraBeforeSavingCheck(SysInitVO savingvo,
      SysInitVO[] wholeModuleParas) {
    return this.checkPara(savingvo);
  }

  @Override
  public CheckParaVO paraEditabilityCheck(SysInitVO edittingvo,
      SysInitVO[] wholeModuleParas) {
    return this.checkPara(edittingvo);
  }

  private CheckParaVO checkPara(SysInitVO vo) {

    CheckParaVO checkParaVO = new CheckParaVO();
    IOrderPayPlanQuery service =
        NCLocator.getInstance().lookup(IOrderPayPlanQuery.class);
    try {
      service.checkPO88Para(vo);
    }
    catch (BusinessException e) {
      checkParaVO.setLegal(false);
      checkParaVO.setErrMsg(e.getMessage());
      return checkParaVO;
    }
    return checkParaVO;
  }

}
