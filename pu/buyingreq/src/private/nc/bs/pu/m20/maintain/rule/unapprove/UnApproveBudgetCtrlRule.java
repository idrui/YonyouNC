package nc.bs.pu.m20.maintain.rule.unapprove;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.itf.mpp.reference.tbb.TbbCtrlServices;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.entity.tbb.PrayBillBudgetCtlVO;
import nc.vo.pu.pub.util.ApproveFlowUtil;
import nc.vo.pu.tbb.BillOperationEnum;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              �빺������Ԥ�����
 * @scene
 *        �빺������
 * @param ��
 * @since 6.3
 * @version 2014-10-21 ����10:31:29
 * @author yanxm5
 */

public class UnApproveBudgetCtrlRule implements ICompareRule<PraybillVO> {
  @Override
  public void process(PraybillVO[] vos, PraybillVO[] origVos) {
    // �жϲɹ��ƻ��Ƿ�����
    if (ArrayUtils.isEmpty(vos) || !SysInitGroupQuery.isMPPEnabled()) {
      return;
    }
    // �õ�ԭʼVOΪ����ͨ��״̬����ǰVOΪ������ͨ��״̬����������ȡ����������Ӱ��ɹ��ƻ�
    PraybillVO[] filterVos =
        (PraybillVO[]) ApproveFlowUtil.filterUnApprovedVO(vos, origVos);
    if (ArrayUtils.isEmpty(filterVos)) {
      return;
    }

    filterVos = this.filterSCTypeVos(filterVos);
    if (ArrayUtils.isEmpty(filterVos)) {
      return;
    }

    this.budgetCtrl(filterVos);
  }

  /**
   * �ж��Ƿ�Ԥ�㣬�����޸�Ԥռ��
   * 
   * @param vos
   */
  private void budgetCtrl(PraybillVO[] vos) {
    List<PrayBillBudgetCtlVO> budgets = new ArrayList<PrayBillBudgetCtlVO>();
    String[] execbilltypes =
        new String[] {
          POBillType.Order.getCode(), POBillType.Arrive.getCode(),
          POBillType.Invoice.getCode(), ICBillType.PurchaseIn.getCode()
        };
    for (PraybillVO vo : vos) {
      for (PraybillItemVO item : vo.getBVO()) {
        for (String exec : execbilltypes) {
          budgets.add(new PrayBillBudgetCtlVO(vo.getHVO(), item,
              BillOperationEnum.UNAPPROVE.getValue(), exec));
        }
      }
    }
    // ����Ԥ��ӿڣ����͸���Ԥռ��
    if (budgets.size() > 0) {
      TbbCtrlServices.noCheckUpdateExe(budgets
          .toArray(new PrayBillBudgetCtlVO[budgets.size()]));
    }

  }

  /**
   * ���˵�ί���ʾ=ture���빺��
   * 
   * @param vos
   * @return
   *         2011-8-11
   *         wangljc
   */
  private PraybillVO[] filterSCTypeVos(PraybillVO[] vos) {
    List<PraybillVO> filterVoLst = new ArrayList<PraybillVO>();
    for (PraybillVO vo : vos) {
      UFBoolean bsctype = vo.getHVO().getBsctype();
      if (!ValueUtils.getBoolean(bsctype)) {
        filterVoLst.add(vo);
      }
    }
    if (CollectionUtils.isEmpty(filterVoLst)) {
      return null;
    }
    return new ListToArrayTool<PraybillVO>().convertToArray(filterVoLst);
  }
}
