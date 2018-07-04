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
 *              请购单弃审预算控制
 * @scene
 *        请购单弃审
 * @param 无
 * @since 6.3
 * @version 2014-10-21 上午10:31:29
 * @author yanxm5
 */

public class UnApproveBudgetCtrlRule implements ICompareRule<PraybillVO> {
  @Override
  public void process(PraybillVO[] vos, PraybillVO[] origVos) {
    // 判断采购计划是否启用
    if (ArrayUtils.isEmpty(vos) || !SysInitGroupQuery.isMPPEnabled()) {
      return;
    }
    // 得到原始VO为审批通过状态，当前VO为非审批通过状态，这样才算取消审批，才影响采购计划
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
   * 判断是否超预算，并且修改预占数
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
    // 调用预算接口，检查和更新预占数
    if (budgets.size() > 0) {
      TbbCtrlServices.noCheckUpdateExe(budgets
          .toArray(new PrayBillBudgetCtlVO[budgets.size()]));
    }

  }

  /**
   * 过滤掉委外标示=ture的请购单
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
