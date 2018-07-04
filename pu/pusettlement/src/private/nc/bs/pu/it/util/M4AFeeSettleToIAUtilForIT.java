package nc.bs.pu.it.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.pu.m27.feesettle.util.M4AFeeSettleToIAUtil;
import nc.vo.pu.m27.entity.SettleBillHeaderVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleFeeAllotDetailVO;
import nc.vo.pu.m27.enumeration.EnumMatchRowType;
import nc.vo.pu.pub.constant.PUParaValue;
import nc.vo.pu.pub.util.CirVOUtil;

import org.apache.commons.collections.CollectionUtils;

/**
 * ����������ֱ�Ӵ����������ܲ���ת�뷽ʽ��������
 * 
 * @since 6.31
 * @version 2013-11-20 ����03:05:16
 * @author mengjian
 */
public class M4AFeeSettleToIAUtilForIT extends M4AFeeSettleToIAUtil {

  public M4AFeeSettleToIAUtilForIT(SettleBillHeaderVO header,
      Collection<SettleBillItemVO> items) {
    super(header, items);
  }

  private Map<String, SettleFeeAllotDetailVO> getStlAllotMap(
      Collection<SettleFeeAllotDetailVO> noneEstBbvos) {
    if (CollectionUtils.isEmpty(noneEstBbvos)) {
      return new HashMap<String, SettleFeeAllotDetailVO>();
    }
    return CirVOUtil.createKeyVOMap(noneEstBbvos
        .toArray(new SettleFeeAllotDetailVO[noneEstBbvos.size()]));
  }

  private boolean isAdjustGoods(SettleBillItemVO toIAStlItem) {
    if (EnumMatchRowType.AdjustGoods.value().equals(toIAStlItem.getFrowtype())) {
      return true;
    }
    return false;
  }

  /**
   * ��ԭʼ�Ľ����У�����̯��ϸ���<br>
   * �����ݲ�������̯���ݵĻ��������ݹ���������ɵ�I9��IG�Ľ�������
   * 
   * @param orgItem ԭʼ������
   * @param toI9Lst ��ŵ�I9�Ĳ�ֺ������
   * @param toIgLst ��ŵ�IG�Ĳ�ֺ������
   * @param stlAllotVoLst ��������ϸ
   * @param forceToCost �Ƿ�ǿ�ƽ��ɱ���I9
   */
  @Override
  protected void genToIAAdjBillStlItems(SettleBillItemVO orgItem,
      List<SettleBillItemVO> toI9Lst, List<SettleBillItemVO> toIgLst,
      List<SettleFeeAllotDetailVO> stlAllotVoLst, boolean forceToCost) {
    List<SettleBillItemVO> allToLst =
        this.getToIAStlItemsByAllot(orgItem, stlAllotVoLst);
    Map<String, SettleFeeAllotDetailVO> stlAllotMap =
        this.getStlAllotMap(stlAllotVoLst);
    for (SettleBillItemVO toIAStlItem : allToLst) {
      // �������󣨼��������������������ý����Ӧ�ķ�̯�����Ѿ������ɱ���
      // ���ʽΪ����������棬forceToCost�����ݹ����ķ��ûس�ʱ��ǿ���߳ɱ�
      if (!forceToCost
          && PUParaValue.po13.profit_loss.equals(this.getParaPO13())
          && this.goodsHavenToIA(toIAStlItem, stlAllotMap)
      /* && !this.isAdjustGoods(toIAStlItem) */) {
        toIgLst.add(this.adjustForIGStlItem(toIAStlItem,
            stlAllotMap.get(toIAStlItem.getPk_settle_feeallot()))); // ��IG����һЩ����
      }
      else {
        toI9Lst.add(toIAStlItem);
      }
    }
  }

}
