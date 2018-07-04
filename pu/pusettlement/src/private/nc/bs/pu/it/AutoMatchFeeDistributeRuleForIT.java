package nc.bs.pu.it;

import nc.bs.pu.m27.match.rule.AutoMatchFeeDistributeRule;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.StockSettleVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�Զ�����һ�η�̯���򣨺�̨ʹ��for�����ڣ�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.31
 * @since 6.31
 * @author liangchen1
 * @time 2013-11-11 ����08:44:10
 */

public class AutoMatchFeeDistributeRuleForIT extends AutoMatchFeeDistributeRule {

  public AutoMatchFeeDistributeRuleForIT(StockSettleVO[] ssVos) {
    super(ssVos);
  }

  @Override
  protected void distFeeDiscount(SettleBillVO vo) {
    FirstDistributeUtilForIT util =
        new FirstDistributeUtilForIT(vo, this.ssVos);
    // ���з�̯,�����ͬʱ��¼��StockSettleVO��
    util.distribute();
  }

}
