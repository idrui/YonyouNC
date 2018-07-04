package nc.ui.pu.m20.action;

import java.util.ArrayList;
import java.util.List;

import nc.itf.tb.control.IAccessableBusiVO;
import nc.ui.pu.pub.action.LinkQueryMppAction;
import nc.ui.pu.reference.mpp.MppUIServices;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.entity.tbb.PrayBillBudgetCtlVO;
import nc.vo.pu.tbb.BillOperationEnum;
import nc.vo.pub.BusinessException;
import nc.vo.pub.IVOMeta;
import nc.vo.pubapp.pattern.model.tool.BillIndex;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * @since 6.0
 * @version 2011-6-13 上午11:31:12
 * @author wuxla
 */

public class PraybillLinkQueryMppAction extends LinkQueryMppAction {
  private static final long serialVersionUID = -9174459781806767946L;

  public PraybillLinkQueryMppAction() {
    super();
    // SCMActionInitializer.initializeAction(this,
    // SCMActionCode.PU_LINKQUERYMPP);
    this.setCode("linkMPP");
    this.setBtnName(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004020_0", "04004020-0013")/* @res "联查采购计划" */);
  }

  private PraybillVO getAggVO() {
    PraybillVO vo = null;
    if (this.getBillForm().isComponentVisible()) {
      vo = this.getCloseVOFromBillForm();
    }
    else {
      vo = this.getCloseVOFromListView();
    }
    return vo;
  }

  private IAccessableBusiVO[] getBusiVOFor20(PraybillVO vo) {
    List<PrayBillBudgetCtlVO> budgets = new ArrayList<PrayBillBudgetCtlVO>();
    String[] execbilltypes =
        new String[] {
          POBillType.Order.getCode(), POBillType.Arrive.getCode(),
          POBillType.Invoice.getCode(), ICBillType.PurchaseIn.getCode()
        };
    for (PraybillItemVO item : vo.getBVO()) {
      for (String exec : execbilltypes) {
        budgets.add(new PrayBillBudgetCtlVO(vo.getHVO(), item,
            BillOperationEnum.APPROVE.getValue(), exec));
      }
    }
    return budgets.toArray(new PrayBillBudgetCtlVO[budgets.size()]);
  }

  private PraybillVO getCloseVOFromBillForm() {
    PraybillVO vo = (PraybillVO) this.getModel().getSelectedData();
    BillIndex index = new BillIndex(new PraybillVO[] {
      vo
    });
    IVOMeta meta = vo.getMetaData().getVOMeta(PraybillItemVO.class);
    String bid =
        (String) this
            .getBillForm()
            .getBillCardPanel()
            .getBodyValueAt(this.getSelectIndex(), PraybillItemVO.PK_PRAYBILL_B);
    PraybillItemVO item = (PraybillItemVO) index.get(meta, bid);

    vo = (PraybillVO) vo.clone();
    vo.setBVO(new PraybillItemVO[] {
      item
    });
    return vo;
  }

  private PraybillVO getCloseVOFromListView() {
    PraybillVO vo = (PraybillVO) this.getModel().getSelectedData();
    BillIndex index = new BillIndex(new PraybillVO[] {
      vo
    });
    IVOMeta meta = vo.getMetaData().getVOMeta(PraybillItemVO.class);
    String bid =
        (String) this.getList().getBillListPanel().getBodyBillModel()
            .getValueAt(this.getSelectIndex(), PraybillItemVO.PK_PRAYBILL_B);
    PraybillItemVO item = (PraybillItemVO) index.get(meta, bid);

    vo = (PraybillVO) vo.clone();
    vo.setBVO(new PraybillItemVO[] {
      item
    });
    return vo;
  }

  @Override
  protected void linkQuery() throws BusinessException {
    PraybillVO vo = this.getAggVO();
    IAccessableBusiVO[] busiVOs = this.getBusiVOFor20(vo);
    MppUIServices.linkQueryMpp(busiVOs);
  }

}
