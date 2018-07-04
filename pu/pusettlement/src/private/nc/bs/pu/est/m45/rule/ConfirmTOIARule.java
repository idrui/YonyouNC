package nc.bs.pu.est.m45.rule;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.pu.reference.ia.IAForEstConfirmServices;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.ia.mi2.entity.I2BillVO;
import nc.vo.pu.est.util.EstTOIAUtil;
import nc.vo.pu.m4201.entity.PurchaseinFIHeaderVO;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pu.m4201.entity.PurchaseinFIVO;
import nc.vo.pu.m4201.enumeration.EnumToIAFlag;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.scmpub.res.billtype.IABillType;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 采购入库单(45)确认成本规则
 * @scene
 * 自动传成本和应付
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-23 上午9:38:38
 * @author zhangshqb
 */
public class ConfirmTOIARule implements IRule<PurchaseinFIVO> {

  @Override
  public void process(PurchaseinFIVO[] vos) {

    if (!SysInitGroupQuery.isIAEnabled()) {
      return;
    }

    PurchaseinFIVO[] vosFiltered = this.filterUnAffectCost(vos);
    if (ArrayUtils.isEmpty(vosFiltered)) {
      return;// 全部不影响成本
    }
    // 对分收集结的数据，清空库存组织及仓库
    this.clearCenterSettleInfo(vosFiltered);

    I2BillVO[] I2Vos = this.getI2VOs(vosFiltered);

    // 调整精度
    EstTOIAUtil.adjustI2Price(I2Vos);

    this.toIA(I2Vos);
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
        UFBoolean toCost = item.getBaffectcost();
        if (UFBoolean.TRUE.equals(toCost)) {
          itemLst.add(item);
        }
      }
      if (itemLst.size() == 0) {
        continue;
      }
      PurchaseinFIVO newVo = vo;
      if (itemLst.size() != vo.getChildrenVO().length) {
        // 为了不影响流程中的VO结构，这里新建一个
        newVo = new PurchaseinFIVO();
        newVo.setParent(vo.getParent());
        newVo.setChildrenVO(itemLst.toArray(new PurchaseinFIItemVO[itemLst
            .size()]));
      }
      newList.add(newVo);
    }
    return newList.toArray(new PurchaseinFIVO[newList.size()]);
  }

  private I2BillVO[] getI2VOs(PurchaseinFIVO[] vos) {
    return PfServiceScmUtil.executeVOChange(
        POBillType.PurchaseInFinance.getCode(), IABillType.CGRK.getCode(), vos);
  }

  private void toIA(I2BillVO[] vos) {
    IAForEstConfirmServices.confirm(vos);
  }

  private void updateTOIAFlag(PurchaseinFIVO[] vos) {
    for (PurchaseinFIVO vo : vos) {
      for (PurchaseinFIItemVO item : vo.getChildrenVO()) {
        item.setStatus(VOStatus.UPDATED);
        item.setFtoiaflag((Integer) EnumToIAFlag.ConfirmToIA.value());
      }
    }
  }

}
