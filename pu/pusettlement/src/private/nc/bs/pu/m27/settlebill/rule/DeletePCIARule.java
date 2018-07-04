package nc.bs.pu.m27.settlebill.rule;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.pu.reference.pcia.PCIAServices;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 
 * @description
 *            ɾ�����㵥�������㣩ǰ,ɾ���������ĵ���
 * @scene
 *      ɾ�����㵥
 * @param
 * 
 *
 * @since 6.5
 * @version 2014-10-26 ����01:41:09
 * @author mengjian
 */
public class DeletePCIARule implements IRule<SettleBillVO> {

  @Override
  public void process(SettleBillVO[] vos) {
    // δ���ô�����㣬����
    if (!SysInitGroupQuery.isPCIAEnabled()) {
      return;
    }
    List<String> hids = new ArrayList<String>();
    List<String> bids = new ArrayList<String>();

    for (SettleBillVO vo : vos) {
      SettleBillItemVO[] items = vo.getChildrenVO();
      for (SettleBillItemVO item : items) {

        // IAҪ��hids�����bids��������ǵȳ�����
        hids.add(vo.getPrimaryKey());
        bids.add(item.getPk_settlebill_b());
      }
    }

    try {
      String[] csrcids = hids.toArray(new String[hids.size()]);
      String[] csrcbids = bids.toArray(new String[bids.size()]);
      // mengjian by 20141021 �����������Ĵ������ʱ
      PCIAServices.unsettleI2(csrcids, csrcbids);
      PCIAServices.cancelSettleI9(csrcids, csrcbids);

    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
  }

}
