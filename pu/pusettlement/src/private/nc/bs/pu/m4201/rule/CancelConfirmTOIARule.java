/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-8 ����02:40:15
 */
package nc.bs.pu.m4201.rule;

import java.util.Set;

import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pu.pub.util.CirVOUtil;

import nc.itf.pu.reference.ia.IAForEstConfirmServices;
import nc.itf.pu.reference.pcia.PCIAForEstConfirmServices;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�Զ�ȡ��ȷ�Ϻ��ݹ��ɱ�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-6-8 ����02:40:15
 */
public class CancelConfirmTOIARule extends CancelConfirmTOFIRule {

  private String[][] getIDs(PurchaseinFIItemVO[] items) {
    String[] bids = new String[items.length];
    String[] hids = new String[items.length];
    for (int i = 0; i < hids.length; i++) {
      bids[i] = items[i].getPk_stockps_b();
      hids[i] = items[i].getPk_stockps();
    }
    return new String[][] {
      hids, bids
    };
  }

  @Override
  protected void cancelConfirm(PurchaseinFIItemVO[] items) {
    Set<String> hids =
        CirVOUtil.getDistinctFieldSet(items, PurchaseinFIItemVO.PK_STOCKPS);
    Set<String> bids =
        CirVOUtil.getDistinctFieldSet(items, PurchaseinFIItemVO.PK_STOCKPS_B);
    // ������Ʒ����Ҫ��浥���������Բ�������Ʒ�������ia�ṩ����id��
    IAForEstConfirmServices.unConfirm(hids.toArray(new String[hids.size()]),
        bids.toArray(new String[bids.size()]));
    // mengjian by 20141021 �����������Ĵ������ʱ
    // change by wandl ��֧�����ȷ�ϳɱ�����������
    /*if (SysInitGroupQuery.isPCIAEnabled()) {
      PCIAForEstConfirmServices.unConfirm(
          hids.toArray(new String[hids.size()]),
          bids.toArray(new String[bids.size()]));
    }*/
  }

  @Override
  protected void cancelEst(PurchaseinFIItemVO[] items) {
    String[][] ids = this.getIDs(items);
    IAForEstConfirmServices.unEstimate(ids[0], ids[1]);
    // mengjian by 20141021 �����������Ĵ������ʱ
    if (SysInitGroupQuery.isPCIAEnabled()) {
      PCIAForEstConfirmServices.unEstimate(ids[0], ids[1]);
    }
  }

  @Override
  protected int getFIModule() {
    return this.IA;
  }

}
