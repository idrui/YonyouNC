package nc.bs.pu.est.m45.rule;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.pu.reference.pcia.PCIAForEstConfirmServices;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.est.entity.m45.PurchaseInEstVO;
import nc.vo.pu.est.util.EstVOUtil;
import nc.vo.pu.m4201.entity.PurchaseinFIHeaderVO;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pu.m4201.entity.PurchaseinFIVO;
import nc.vo.pu.m4201.enumeration.EnumToIAFlag;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;
import nc.vo.scmpub.util.CombineViewToAggUtil;
import nc.vo.pu.est.util.EstTOPCIAUtil;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 *            �ɹ���ⵥ�����ݹ������������
 * @scene
 *      �ɹ���ⵥ�����ݹ�
 * @param
 * 
 *
 * @since 6.0
 * @version 2010-8-18 ����01:47:01
 * @author zhaoyha
 */
public class PurchsInGoodsEstTOPCIARule implements IRule<PurchaseInEstVO> {

  @Override
  public void process(PurchaseInEstVO[] vos) {
    if (!SysInitGroupQuery.isPCIAEnabled()) {
      return;
    }
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    // ��䴫�ɱ���־ ��������ʱ���ô˱�־�˴�����Ҫ���÷��������� ҵ�񳡾����������ı��봫���
    // for (PurchaseInEstVO vo : vos) {
    // this.fillToIAFlag(vo);
    // }
    // �õ����ɱ����ݹ�VO
    PurchaseInEstVO[] toIAEstVos = this.getToIAEstVO(vos);
    if (ArrayUtils.isEmpty(toIAEstVos)) {
      return;
    }
    this.sendTOIA(toIAEstVos);// ���������IA
  }

  // private void fillToIAFlag(PurchaseInEstVO vo) {
  // GoodsEstVO head = vo.getParentVO();
  // if (ValueUtils.getBoolean(head.getBaffectpccost())) {
  // head.setFtoiaflag((Integer) EnumToIAFlag.EstimateToIA.value());
  // }
  // else {
  // head.setFtoiaflag((Integer) EnumToIAFlag.NotToIA.value());
  // }
  // }

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
    if (newList.size() > 0) {
      return newList.toArray(new PurchaseinFIVO[newList.size()]);
    }
    return null;
  }

  private PurchaseInEstVO[] getToIAEstVO(PurchaseInEstVO[] vos) {
    List<PurchaseInEstVO> heads = new ArrayList<PurchaseInEstVO>();
    for (int i = 0; i < vos.length; i++) {
      GoodsEstVO head = vos[i].getParentVO();
      if (EnumToIAFlag.EstimateToIA.value().equals(head.getFtoiaflag())) {
        heads.add(vos[i]);
      }
    }
    if (heads.size() == 0) {
      return null;
    }
    return new ListToArrayTool<PurchaseInEstVO>().convertToArray(heads);
  }

  protected void sendTOIA(PurchaseInEstVO[] estVos) {
    GoodsEstVO[] views = EstVOUtil.getGoodsEstVos(estVos);
    CombineViewToAggUtil<PurchaseinFIVO> util =
        new CombineViewToAggUtil<PurchaseinFIVO>(PurchaseinFIVO.class,
            PurchaseinFIHeaderVO.class, PurchaseinFIItemVO.class);
    PurchaseinFIVO[] fiVos =
        util.combineViewToAgg(views, PurchaseinFIHeaderVO.PK_STOCKPS);
    PurchaseinFIVO[] vosFiltered = this.filterUnAffectCost(fiVos);
    if (null == vosFiltered) {
      return;
    }
    //added by wangzhqf �Կ�������.�Ƴɱ����� (pk_stockps_b.nestcalcostprice)���ȴ���
    EstTOPCIAUtil.adjustM4201Price(vosFiltered);
    // mengjian by 20141021 �����������Ĵ������ʱ
    PCIAForEstConfirmServices.estimate(vosFiltered);
    
  }

}
