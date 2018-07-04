/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-7 ����04:29:41
 */
package nc.bs.pu.est.m45.rule;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

import nc.vo.pu.m4201.entity.PurchaseinFIHeaderVO;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pu.m4201.entity.PurchaseinFIVO;
import nc.vo.pu.m4201.enumeration.EnumToIAFlag;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;

import nc.impl.pubapp.pattern.rule.IRule;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ���ⵥ(45)ȷ�ϳɱ�����
 * </ul>
 * <p>
 * <p>
 * 
 * @since 6.36
 * @version 2014-10-26 ����1:29:38
 * @author mj423
 */
public class ConfirmTOPCIARule implements IRule<PurchaseinFIVO> {

  @Override
  public void process(PurchaseinFIVO[] vos) {

    if (!SysInitGroupQuery.isPCIAEnabled()) {
      return;
    }

    PurchaseinFIVO[] vosFiltered = this.filterUnAffectCost(vos);
    if (ArrayUtils.isEmpty(vosFiltered)) {
      return;// ȫ����Ӱ��ɱ�
    }
    // �Է��ռ�������ݣ���տ����֯���ֿ�
    this.clearCenterSettleInfo(vosFiltered);

    // this.toPCIA(vos);
    this.updateTOIAFlag(vosFiltered);
  }

  private void clearCenterSettleInfo(PurchaseinFIVO[] vosFiltered) {
    String[] names =
        {
          PurchaseinFIHeaderVO.PK_ORG, PurchaseinFIHeaderVO.PK_ORG_V,
          PurchaseinFIHeaderVO.PK_STORDOC
        };

    for (PurchaseinFIVO pfivo : vosFiltered) {
      PurchaseinFIHeaderVO headvo = pfivo.getParentVO();
      UFBoolean normpur = headvo.getBnormpur();
      if (UFBoolean.TRUE.equals(normpur)) {
        continue;
      }

      for (String name : names) {
        headvo.setAttributeValue(name, null);
      }
    }
  }

  private PurchaseinFIVO[] filterUnAffectCost(PurchaseinFIVO[] vos) {
    List<PurchaseinFIVO> newList = new ArrayList<PurchaseinFIVO>();
    for (PurchaseinFIVO vo : vos) {
      List<PurchaseinFIItemVO> itemLst = new ArrayList<PurchaseinFIItemVO>();
      for (PurchaseinFIItemVO item : vo.getChildrenVO()) {
        UFBoolean toCost = item.getBaffectpccost();
        if (UFBoolean.TRUE.equals(toCost)) {
          itemLst.add(item);
        }
      }
      if (itemLst.size() == 0) {
        continue;
      }
      PurchaseinFIVO newVo = vo;
      if (itemLst.size() != vo.getChildrenVO().length) {
        // Ϊ�˲�Ӱ�������е�VO�ṹ�������½�һ��
        newVo = new PurchaseinFIVO();
        newVo.setParent(vo.getParent());
        newVo.setChildrenVO(itemLst.toArray(new PurchaseinFIItemVO[itemLst
            .size()]));
      }
      newList.add(newVo);
    }
    return newList.toArray(new PurchaseinFIVO[newList.size()]);
  }

  // change by wandl �������Ĵ����֧����ⵥȷ�ϳɱ�
  /*private void toPCIA(PurchaseinFIVO[] vos) {
    // mengjian by 20141021 �����������Ĵ������ʱ
    if (SysInitGroupQuery.isPCIAEnabled()) {
      PCIAForEstConfirmServices.confirm(vos);
    }
  }*/

  private void updateTOIAFlag(PurchaseinFIVO[] vos) {
    for (PurchaseinFIVO vo : vos) {
      for (PurchaseinFIItemVO item : vo.getChildrenVO()) {
        item.setStatus(VOStatus.UPDATED);
        item.setFtoiaflag((Integer) EnumToIAFlag.ConfirmToIA.value());
      }
    }
  }

}
