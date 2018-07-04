package nc.ui.pu.m21.action.orderclose;

import java.awt.event.ActionEvent;

import nc.ui.pu.m21.service.OrderCloseManageModel;
import nc.ui.pu.pub.action.PULinkQueryAction;
import nc.ui.pubapp.uif2app.actions.LinkQueryAction.IBillInfoFactory;
import nc.vo.pu.m21.entity.OrderCloseVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * 采购订单关闭节点的单据联查按钮
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-11-2 下午06:23:26
 */
public class CloseLinkQueryAction extends PULinkQueryAction {

  private static final long serialVersionUID = 7564592536233338169L;

  private IBillInfoFactory bf;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    if (null == ((OrderCloseManageModel) CloseLinkQueryAction.this.getModel())
        .getSelectedData()) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0083")/*
                                                                   * @res
                                                                   * "请选一个订单行！"
                                                                   */);
    }
    super.doAction(e);
  }

  @Override
  public IBillInfoFactory<Object> getBillInfoFactory() {
    if (null == this.bf) {
      this.bf = new IBillInfoFactory() {
        @Override
        public IBillInfo getBillInfo(Object t) {
          return new IBillInfo() {

            @Override
            public String getBillCode() {
              OrderCloseVO vo = this.getSelectedCloseVO();
              return null == vo ? null : vo.getVbillcode();
            }

            @Override
            public String getBillId() {
              OrderCloseVO vo = this.getSelectedCloseVO();
              return null == vo ? null : vo.getPk_order();
            }

            @Override
            public String getBillType() {
              return POBillType.Order.getCode();
            }

            private OrderCloseVO getSelectedCloseVO() {
              return (OrderCloseVO) ((OrderCloseManageModel) CloseLinkQueryAction.this
                  .getModel()).getSelectedData();
            }

          };
        }
      };
    }
    return this.bf;
  }
}
