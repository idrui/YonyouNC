package nc.bs.pu.m27.match.rule;

import nc.bs.pu.m27.feesettle.distribute.FirstDistributeUtil;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.util.StockSettleInfoUpdate;
import nc.vo.pu.m4201.enumeration.EnumToIAFlag;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * �Զ����㣬ģ�����󣬽��з��÷�̯�Ĵ������
 * 
 * @since 6.0
 * @version 2011-3-25 ����10:12:19
 * @author zhaoyha
 */
public class AutoMatchFeeDistributeRule {
  // modify by liangchen1 631������ private to protected
  protected StockSettleVO[] ssVos;

  /**
   * �Զ�����ʱ,����ģ����㼰���÷�̯����
   * 
   * @param ssVos ģ�����ǰԭʼ�Ŀ�����VO
   */
  public AutoMatchFeeDistributeRule(StockSettleVO[] ssVos) {
    this.ssVos = ssVos;
  }

  /**
   * ���и���ģ����㵥��Ϣ,���¿��������ݵĽ�����Ϣ
   * 
   * @return
   */
  public StockSettleVO[] process(SettleBillVO[] vos) {
    // ���¿�����VO�Ļ��������Ϣ
    this.updateGoodsSettleInfo(vos);
    for (SettleBillVO vo : vos) {
      this.distFeeDiscount(vo);
    }
    return this.ssVos;
  }

  private boolean isNeedUpdateGoodsSettleInfo() {
    // ������ⵥ�о��ݹ���ȷ�Ϲ�Ҳ����ģ�����
    for (StockSettleVO ssVo : this.ssVos) {
      if (!EnumToIAFlag.ConfirmToIA.toInteger().equals(ssVo.getFdirtoiatype())
          && MathTool.isZero(ssVo.getNestnum())) {
        return true;
      }
    }
    return false;
  }

  private void updateGoodsSettleInfo(SettleBillVO[] vos) {
    if (!this.isNeedUpdateGoodsSettleInfo()) {
      return;
    }
    // ���¿�����VO�еĻ��������Ϣ
    StockSettleInfoUpdate ssUpdate = new StockSettleInfoUpdate(this.ssVos);
    ssUpdate.process(vos);
    // ����һ��Ҫ����,��Ϊupdate�л�clone
    this.ssVos = ssUpdate.getUpdatedSSVO();
  }

  // modify by liangchen1 631������ private to protected
  protected void distFeeDiscount(SettleBillVO vo) {
    FirstDistributeUtil util = new FirstDistributeUtil(vo, this.ssVos);
    // ���з�̯,�����ͬʱ��¼��StockSettleVO��
    util.distribute();
  }

}
