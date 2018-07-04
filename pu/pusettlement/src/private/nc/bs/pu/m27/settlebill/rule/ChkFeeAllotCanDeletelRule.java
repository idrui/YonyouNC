package nc.bs.pu.m27.settlebill.rule;

import nc.bs.pu.m27.feesettle.util.FeeSettleDataContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.SettleFeeAllotDetailVO;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 
 * @description
 * ����Ӧ�ķ��÷�̯��ϸ�Ƿ��������ȡ������
 * @scene
 * ȡ�����ý���
 * @param
 * feedatactx ���ý�����ʹ�õĺ�̨���ݻ���
 *
 * @since 6.3
 * @version 2014-10-22 ����4:00:53
 * @author zhangshqb
 */
public class ChkFeeAllotCanDeletelRule implements IRule<SettleBillVO> {

  private SettleFeeAllotDetailVO[] allotDetails;

  public ChkFeeAllotCanDeletelRule(FeeSettleDataContext feedatactx) {
    if (feedatactx == null) {
      return;
    }
    this.allotDetails = feedatactx.getBeenSavedAllotDetailArray();
  }

  @Override
  public void process(SettleBillVO[] vos) {
    if ((this.allotDetails == null) || (this.allotDetails.length == 0)) {
      return;
    }
    StringBuilder error = new StringBuilder();
    for (SettleFeeAllotDetailVO detail : this.allotDetails) {
      // ��һ�η��ý��㲢�Ҵ��ں����ķ��ý���
      boolean bfirst = ValueUtils.getBoolean(detail.getBestfirstsettle());
      int timesafter = ValueUtils.getInt(detail.getNtimesafterfirst());
      if (bfirst && (timesafter > 0)) {
        String hid = detail.getPk_settlebill();
        String billcode = this.findSettleBillCode(vos, hid);
        if (error.indexOf(billcode) == -1) {
          error.append("[").append(billcode).append("]");
        }
      }
    }
    if (error.length() > 0) {
      String msg = error.toString() + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0074")/*@res "�ǵ�һ�η��ý��㲢�Ҵ��ں����ķ��ý���,���ܽ���ȡ��������"*/;
      ExceptionUtils.wrappBusinessException(msg);
    }
  }

  private String findSettleBillCode(SettleBillVO[] vos, String hid) {
    if ((vos == null) || (hid == null)) {
      return null;
    }
    for (SettleBillVO vo : vos) {
      if (hid.equals(vo.getParentVO().getPk_settlebill())) {
        return vo.getParentVO().getVbillcode();
      }
    }
    return null;
  }
}