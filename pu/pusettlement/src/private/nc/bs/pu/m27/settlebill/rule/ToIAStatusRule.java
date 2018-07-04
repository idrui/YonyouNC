package nc.bs.pu.m27.settlebill.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pu.m27.entity.SettleBillHeaderVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 
 * @description
 * 传存货或取消传传存货时的结算单状态判断
 * @scene
 * 取消传存货核算,结算完毕保存结算单,传存货核算
 * @param
 * isToIA 是否传成本
 * isCheck 是否检查状态
 * 
 * @since 6.3
 * @version 2014-10-22 下午3:57:19
 * @author zhangshqb
 */
public class ToIAStatusRule implements IRule<SettleBillVO> {
  private boolean isCheck = true;

  private boolean isToIA = true;

  public ToIAStatusRule(boolean isToIA) {
    this.isToIA = isToIA;
  }

  public ToIAStatusRule(boolean isToIA, boolean isCheck) {
    this.isToIA = isToIA;
    this.isCheck = isCheck;
  }

  @Override
  public void process(SettleBillVO[] vos) {
    if (!SysInitGroupQuery.isIAEnabled()) {
      return;
    }
    for (SettleBillVO vo : vos) {
      // 检查结算单是否已经传过应付
      this.checkStatus(vo);
      // 更新是否传应付的标识
      this.changeStatus(vo);
    }
  }

  private void changeStatus(SettleBillVO vo) {
    BillHelper<SettleBillVO> helper = new BillHelper<SettleBillVO>(vo);
    if (this.isToIA) {
      helper.setHeadValue(SettleBillHeaderVO.BTOIA, UFBoolean.TRUE);
    }
    else {
      helper.setHeadValue(SettleBillHeaderVO.BTOIA, UFBoolean.FALSE);
    }
  }

  private void checkStatus(SettleBillVO vo) {
    if (!this.isCheck) {
      return;
    }
    SettleBillHeaderVO header = vo.getParentVO();
    if (this.isToIA && UFBoolean.TRUE.equals(header.getBtoia())) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004060_0", "04004060-0078")/* @res "结算单" */
          + header.getVbillcode()
          + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0",
              "04004060-0079")/* @res "已经传过存货！" */);
    }
    else if (!this.isToIA && UFBoolean.FALSE.equals(header.getBtoia())) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004060_0", "04004060-0078")/* @res "结算单" */
          + header.getVbillcode()
          + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0",
              "04004060-0080")/* @res "没有传过存货！" */);
    }
  }

}
