package nc.bs.pu.m20.maintain.rule.approve;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
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
 *              �빺������ʱ��Ԥ����ƹ���
 * @scene
 *        �빺������
 * @param ��
 * @since 6.3
 * @version 2014-10-21 ����9:02:52
 * @author yanxm5
 */

public class ApproveBudgetCtrlRule implements IRule<PraybillVO> {

  @Override
  public void process(PraybillVO[] vos) {
    // �жϲɹ��ƻ��Ƿ�����
    if (ArrayUtils.isEmpty(vos) || !SysInitGroupQuery.isMPPEnabled()) {
      return;
    }
    this.budgetCtrl(vos);
  }

  /**
   * �ж��Ƿ�Ԥ�㣬�����޸�Ԥռ��
   * 
   * @param vos
   */
  private void budgetCtrl(PraybillVO[] vos) {
    // �õ�����ͨ����VO��������ʱ���ܻ��������е�VO����
    PraybillVO[] approveVos =
        (PraybillVO[]) ApproveFlowUtil.filterApprovedVO(vos);
    // ���˵�ί���ʾ=ture���빺��
    PraybillVO[] notSCTypeVos = this.filterSCTypeVos(approveVos);
    if (ArrayUtils.isEmpty(notSCTypeVos)) {
      return;
    }
    List<PrayBillBudgetCtlVO> budgets = new ArrayList<PrayBillBudgetCtlVO>();
    String[] execbilltypes =
        new String[] {
          POBillType.Order.getCode(), POBillType.Arrive.getCode(),
          POBillType.Invoice.getCode(), ICBillType.PurchaseIn.getCode()
        };
    for (PraybillVO vo : approveVos) {
      for (PraybillItemVO item : vo.getBVO()) {
        for (String exec : execbilltypes) {
          budgets.add(new PrayBillBudgetCtlVO(vo.getHVO(), item,
              BillOperationEnum.APPROVE.getValue(), exec));
        }
      }
    }
    if (budgets.size() <= 0) {
      return;
    }
    TbbCtrlServices.getControlInfo(budgets
        .toArray(new PrayBillBudgetCtlVO[budgets.size()]));
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
