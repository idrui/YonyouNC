/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-7 ����04:06:14
 */
package nc.bs.pu.est.m45.rule;

import java.util.List;
import java.util.Map.Entry;

import nc.bs.arap.util.ArapFlowUtil;
import nc.bs.arap.util.IArapBillTypeCons;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.arap.payable.AggPayableBillVO;
import nc.vo.arap.payable.PayableBillVO;
import nc.vo.pu.est.util.ToApIaDataProcessor;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pu.m4201.entity.PurchaseinFIVO;
import nc.vo.pu.m4201.enumeration.EnumToAPFlag;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              ��ⵥ(45)�Զ�ȷ��Ӧ������
 * @scene
 *        �Զ����ɱ���Ӧ��
 * @param ��
 * @since 6.3
 * @version 2014-10-23 ����9:38:21
 * @author zhangshqb
 */
public class ConfirmTOAPRule implements IRule<PurchaseinFIVO> {

  @Override
  public void process(PurchaseinFIVO[] vos) {
    if (!SysInitGroupQuery.isAPEnabled()) {
      return;
    }
    AggPayableBillVO[] payVos = this.getEstPayVOs(vos);
    if (ArrayUtils.isEmpty(payVos)) {
      return;
    }
    this.toAPByAction(payVos);// �߱���ű���Ӧ��
    this.updateTOAPFlag(vos);
  }

  private AggPayableBillVO[] getEstPayVOs(PurchaseinFIVO[] vos) {
    ToApIaDataProcessor processor = new ToApIaDataProcessor();
    // ��Ʊ��Ӧ���滻
    processor.fillDataByOrder(vos);
    AggPayableBillVO[] payVos =
        PfServiceScmUtil.exeVOChangeByBillItfDef(
            POBillType.PurchaseInFinance.getCode(), IArapBillTypeCons.F1, vos);
    // ��ԭ��Ӧ��
    processor.resetData(vos);
    return payVos;
  }

  private void toAPByAction(AggPayableBillVO[] payVos) {
    MapList<String, AggPayableBillVO> payMapList = new MapList<>();
    for (AggPayableBillVO payVo : payVos) {
      String pk_org =
          (String) payVo.getParent().getAttributeValue(PayableBillVO.PK_ORG);
      payMapList.put(pk_org, payVo);
    }
    for (Entry<String, List<AggPayableBillVO>> entry : payMapList.entrySet()) {
      String actionCode =
          ArapFlowUtil
              .getCommitActionCode(entry.getKey(), IArapBillTypeCons.F1);
      AggPayableBillVO[] payVO =
          entry.getValue().toArray(new AggPayableBillVO[0]);
      // �����ո����ݱ���ű�
      PfServiceScmUtil.processBatch(actionCode, IArapBillTypeCons.F1, payVO,
          null, null);
    }
  }

  private void updateTOAPFlag(PurchaseinFIVO[] fiVos) {
    for (PurchaseinFIVO fiVo : fiVos) {
      for (PurchaseinFIItemVO item : fiVo.getChildrenVO()) {
        item.setStatus(VOStatus.UPDATED);
        item.setFtoapflag((Integer) EnumToAPFlag.ConfirmToAP.value());
      }
    }
  }

}
