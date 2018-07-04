/**
 * $文件说明$
 *
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-25 下午06:53:24
 */
package nc.bs.pu.m20.maintain.rule.unapprove;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.enumeration.EnumBillStatue;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @description
 *              请购单弃审检查
 * @scene
 *        请购单弃审
 * @param 无
 * @since 6.3
 * @version 2014-10-21 上午10:28:12
 * @author yanxm5
 */
public class UnApproveChkRule implements IRule<PraybillVO> {

  private String msg;

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(PraybillVO[] vos) {
    if (vos == null || vos.length == 0) {
      return;
    }
    for (PraybillVO VO : vos) {
      if (VO == null) {
        continue;
      }

      if (VO.getHVO().getFbillstatus().intValue() == EnumBillStatue.CLOSE
          .toInt()) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004020_0", "04004020-0037")/*
                                                                     * @res
                                                                     * "已经关闭，不能弃审。"
                                                                     */);
      }

      if (VO.getHVO().getFbillstatus().intValue() == POEnumBillStatus.FREE
          .toInt()) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004020_0", "04004020-0038")/*
                                                                     * @res
                                                                     * "单据状态不正确，不能弃审！"
                                                                     */);
      }

      PraybillItemVO[] bodyVO = VO.getBVO();
      for (PraybillItemVO item : bodyVO) {
        if (this.hasUsed(item)) {
          ExceptionUtils.wrappBusinessException(this.getMsg());
        }
      }
    }

  }

  private String getMsg() {
    return this.msg;
  }

  private boolean hasUsed(PraybillItemVO item) {

    // 是否已经生成合同
    if (this.isIntUPZero(item.getNgenct())) {
      this.msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004020_0",
              "04004020-0039")/* @res "已经生成合同，不能弃审。" */;
      return true;
    }
    // 是否已经生成总括订单
    if (UFBoolean.TRUE.equals(item.getBisgensaorder())) {
      this.msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004020_0",
              "04004020-0107")/* @res "已经生成总括订单，不能弃审。" */;
      return true;
    }
    // 是否已经生成价格审批单
    if (this.isIntUPZero(item.getNpriceauditbill())) {
      this.msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004020_0",
              "04004020-0040")/* @res "已经生成价格审批单，不能弃审。" */;
      return true;
    }
    // 是否已经生成询报价单
    if (this.isIntUPZero(item.getNquotebill())) {
      this.msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004020_0",
              "04004020-0041")/* @res "已经生成询报价单，不能弃审。" */;
      return true;
    }
    // 是否已经生成采购订单，委外订单或者进口合同
    if (this.isUFDoubleUPZero(item.getNaccumulatenum())) {
      this.msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004020_0",
              "04004020-0042")/* @res "已经生成采购订单或者委外订单或者进口合同，不能弃审。" */;
      return true;
    }

    // 是否已经发布到电子商务
    if (UFBoolean.TRUE.equals(item.getBpublishtoec())) {
      this.msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004020_0",
              "04004020-0043")/* @res "已经发布到电子商务，不能弃审。" */;
      return true;
    }

    // 是否行关闭
    if (null != item.getBrowclose() && item.getBrowclose().booleanValue()) {
      this.msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004020_0",
              "04004020-0044")/* @res "已经行关闭，不能弃审。" */;
      return true;
    }
    return false;
  }

  private boolean isIntUPZero(Integer i) {
    if (null != i && i.intValue() > 0) {
      return true;
    }

    return false;
  }

  private boolean isUFDoubleUPZero(UFDouble d) {
    if (null != d && d.doubleValue() > 0d) {
      return true;
    }

    return false;
  }

}
