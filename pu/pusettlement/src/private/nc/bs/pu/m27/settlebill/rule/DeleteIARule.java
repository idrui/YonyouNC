package nc.bs.pu.m27.settlebill.rule;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.pu.reference.ia.M27IAServices;
import nc.itf.pu.reference.pcia.PCIAServices;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 
 * @description
 * ɾ�����㵥�������㣩ǰɾ���������ĵ���
 * @scene
 * ȡ�����������,ɾ�����㵥
 * @param
 * ��
 *
 * @since 6.3
 * @version 2014-10-22 ����3:59:28
 * @author zhangshqb
 */
public class DeleteIARule implements IRule<SettleBillVO> {

  @Override
  public void process(SettleBillVO[] vos) {
    // δ���ô�����㣬����
    if (!SysInitGroupQuery.isIAEnabled()) {
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
      // ɾ��I2��IA�ɹ���ⵥ������
      M27IAServices.unsettleI2(csrcids, csrcbids);
      // ɾ��I9��IA��������������
      M27IAServices.cancelSettleI9(csrcids, csrcbids);
      // ɾ��IG��IA���������������
      M27IAServices.cancelSettleIG(csrcids, csrcbids);
      // mengjian by 20141021 �����������Ĵ������ʱ
      if (SysInitGroupQuery.isPCIAEnabled()) {
        PCIAServices.unsettleI2(csrcids, csrcbids);
        PCIAServices.cancelSettleI9(csrcids, csrcbids);
      }

    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
  }

}
