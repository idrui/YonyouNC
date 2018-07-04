package nc.bs.pu.m422x.rewrite.rule;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m422x.entity.StoreReqAppViewVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * 
 * @description
 *            出库申请单保存回写物资需求申请单，累计申请出库主数量检查
 * @scene
 *      出库申请单保存
 * @param
 * 
 *
 * @since 6.0
 * @version 2010-12-16 下午03:24:20
 * @author wuxla
 */
public class AccuOutreqNumChkRule implements IRule<StoreReqAppViewVO> {
  @Override
  public void process(StoreReqAppViewVO[] views) {
    StringBuilder sb = new StringBuilder();
    for (StoreReqAppViewVO view : views) {
      if (MathTool.compareTo(view.getNaccuoutreqnum(), UFDouble.ZERO_DBL) < 0) {
        sb.append(NCLangResOnserver.getInstance().getStrByID("4004010_0", "04004010-0018", null, new String[]{view.getVbillcode(),view.getCrowno()})/*物资需求申请单{0}上第{1}行的累计出库申请主数量不能小于零！请检查出库申请数量！\n*/);
        continue;
      }

      if (MathTool.compareTo(view.getNaccuoutreqnum(), view.getNnum()) > 0) {
        sb.append(NCLangResOnserver.getInstance().getStrByID("4004010_0", "04004010-0019", null, new String[]{view.getVbillcode(),view.getCrowno()})/*物资需求申请{0}上第{1}行的累计出库申请主数量超出允许范围！请修改出库申请数量！\n*/);
      }
    }

    if (sb.length() > 0) {
      ExceptionUtils.wrappBusinessException(sb.toString());
    }
  }

}
