package nc.ui.pu.position.param;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.position.IQueryPosition;
import nc.pubitf.scmf.pu.ordertranstype.pu.IQueryOrdertranstypeForPu;
import nc.ui.pub.para.ICheckParaFinal;
import nc.vo.pub.para.CheckParaVO;
import nc.vo.pub.para.SysInitVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 采购分类或基本分类有值的时候不允许修改。
 * 
 * @since 6.0
 * @version 2011-6-7 下午03:53:13
 * @author liugxa
 */
public class CheckPO85Para implements ICheckParaFinal {

  @Override
  public CheckParaVO paraBeforeSavingCheck(SysInitVO savingvo,
      SysInitVO[] wholeModuleParas) {
    return this.check(savingvo);
  }

  @Override
  public CheckParaVO paraEditabilityCheck(SysInitVO edittingvo,
      SysInitVO[] wholeModuleParas) {
    return this.check(edittingvo);
  }

  private CheckParaVO check(SysInitVO vo) {
    boolean check = false;
    try {
      IQueryPosition position =
          NCLocator.getInstance().lookup(IQueryPosition.class);
      check = position.checkPO85Para(vo);

      if (check) {
        IQueryOrdertranstypeForPu ordertrantype =
            NCLocator.getInstance().lookup(IQueryOrdertranstypeForPu.class);
        check = ordertrantype.checkPO85Para();
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    CheckParaVO checkParaVO = new CheckParaVO();
    if (!check) {
      String message =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004080_0",
              "04004080-0004")/* @res "采购参数分类或基本分类不为空，不允许修改！" */;
      checkParaVO.setLegal(false);
      checkParaVO.setErrMsg(message);
    }
    return checkParaVO;
  }

}
