package nc.ui.pu.m21.action;

import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderPaymentVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.IVOMeta;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.tool.BillIndex;
import nc.vo.scmpub.res.SCMActionCode;

import nc.ui.pu.m21.util.BusiVOGetterUtil;
import nc.ui.pu.pub.action.LinkQueryMppAction;
import nc.ui.scmpub.action.SCMActionInitializer;

/**
 * @since 6.0
 * @version 2011-6-13 下午04:14:51
 * @author wuxla
 */

public class OrderLinkQueryMppAction extends LinkQueryMppAction {

  private static final long serialVersionUID = -1851591592385352873L;

  public OrderLinkQueryMppAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_LINKQUERYMPP);
    // this.setCode("linkMPP");
    // this.setBtnName("联查采购计划");
  }

  private OrderVO getAggVO() {
    OrderVO vo = null;
    if (this.getBillForm().isComponentVisible()) {
      vo = this.getCloseVOFromBillForm();
    }
    else {
      vo = this.getCloseVOFromListView();
    }
    return vo;
  }

  private OrderVO getCloseVOFromBillForm() {
    OrderVO vo = (OrderVO) this.getModel().getSelectedData();
    BillIndex index = new BillIndex(new OrderVO[] {
      vo
    });
    IVOMeta meta = vo.getMetaData().getVOMeta(OrderItemVO.class);
    String bid =
        (String) this.getBillForm().getBillCardPanel()
            .getBodyValueAt(this.getSelectIndex(), OrderItemVO.PK_ORDER_B);
    OrderItemVO item = (OrderItemVO) index.get(meta, bid);

    vo = (OrderVO) vo.clone();
    vo.setBVO(new OrderItemVO[] {
      item
    });
    return vo;
  }

  private OrderVO getCloseVOFromListView() {
    OrderVO vo = (OrderVO) this.getModel().getSelectedData();
    BillIndex index = new BillIndex(new OrderVO[] {
      vo
    });
    IVOMeta meta = vo.getMetaData().getVOMeta(OrderItemVO.class);
    String bid =
        (String) this.getList().getBillListPanel().getBodyBillModel()
            .getValueAt(this.getSelectIndex(), OrderItemVO.PK_ORDER_B);
    OrderItemVO item = (OrderItemVO) index.get(meta, bid);

    vo = (OrderVO) vo.clone();
    vo.setBVO(new OrderItemVO[] {
      item
    });
    return vo;
  }

  @Override
  protected void linkQuery() throws BusinessException {
    OrderVO vo = this.getAggVO();
    // 父类doaction()已经做了判断,这里就不需要了
    // if (!SysInitGroupQuery.isMPPEnabled()) {
    // return;
    // }
    BusiVOGetterUtil.linkQueryMpp(vo);
  }
  
  @Override
  protected boolean linkBefore() {
    String tableCode = null;
    if(this.getBillForm().isComponentVisible()){
      tableCode = this.getBillForm().getBillCardPanel().getBodyPanel().getTableCode();
    }else{
     tableCode = this.getList().getBillListPanel().getChildListPanel().getTableCode();
    }
    if(OrderPaymentVO.TABSNAME.equals(tableCode)){
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004000_0", "04004000-0159")/*
                                                                   * @res
                                                                   * 当前页签是付款协议页签，不可联查付款计划！
                                                                   */);
      return false;
    }
    
    return super.linkBefore();
  }
}
