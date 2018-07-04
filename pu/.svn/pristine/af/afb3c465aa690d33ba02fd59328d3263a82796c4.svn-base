package nc.ui.pu.m23.action.maintain;

import java.awt.event.ActionEvent;

import nc.itf.uap.pf.busiflow.PfButtonClickContext;
import nc.ui.pu.m23.editor.card.utils.BackReasonEditUtil;
import nc.ui.pu.pub.util.PuVDefFilterUtil;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.uif2app.actions.AbstractReferenceAction;
import nc.ui.pubapp.uif2app.event.OrgChangedEvent;
import nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeFuncUtils;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货单 采购订单退货 按钮处理类Action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-12 下午02:15:12
 */
public class BackFrom21UIAction extends AbstractReferenceAction {

  private static final long serialVersionUID = -1935239317112637960L;

  private AbstractAppModel model = null;

  public BackFrom21UIAction() {
    super();

    this.setSourceBillName(POBillType.Order.getName());
    this.setSourceBillType(POBillType.Order.getCode());

    this.setCode("BackArriveFrom21");
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    PfUtilClient.childButtonClickedNew(this.createPfButtonClickContext());

    if (PfUtilClient.isCloseOK()) {
      AggregatedValueObject[] retObjArray = PfUtilClient.getRetVos();
      if (ArrayUtils.isEmpty(retObjArray)) {
        return;
      }

      ArriveVO[] retVOArray = (ArriveVO[]) retObjArray;
      // 更新表头的是否退货标志
      retVOArray = this.updateBackFlag(retVOArray);

      this.getTransferViewProcessor().processBillTransfer(retVOArray);

      BillCardPanel card =
          this.getTransferViewProcessor().getBillForm().getBillCardPanel();
      // 设置退货理由
      new BackReasonEditUtil(card).setEnabled();

      // 触发编辑事件
      String stockOrg = retVOArray[0].getHVO().getPk_org();
      this.model.fireEvent(new OrgChangedEvent(null, stockOrg));
      new PuVDefFilterUtil()
          .setOrgForDefRef(card, this.getModel().getContext());
    }
  }

  public AbstractAppModel getModel() {
    return this.model;
  }

  public void setModel(AbstractAppModel model) {
    this.model = model;
  }

  private PfButtonClickContext createPfButtonClickContext() {
    PfButtonClickContext context = new PfButtonClickContext();
    context.setParent(this.getModel().getContext().getEntranceUI());
    context.setSrcBillType(this.getSourceBillType());
    context.setPk_group(this.getModel().getContext().getPk_group());
    context.setUserId(this.getModel().getContext().getPk_loginUser());
    // 如果该节点是由交易类型发布的，那么这个参数应该传交易类型，否则传单据类型
    String vtrantype =
        TrantypeFuncUtils.getTrantype(this.getModel().getContext());
    if (StringUtil.isEmptyWithTrim(vtrantype)) {
      context.setCurrBilltype(POBillType.Arrive.getCode());
    }
    else {
      context.setCurrBilltype(vtrantype);
    }
    context.setUserObj("RETURN");
    context.setSrcBillId(null);
    context.setBusiTypes(this.getBusitypes());
    // 上面的参数在原来调用的方法中都有涉及，只不过封成了一个整结构，下面两个参数是新加的参数
    // 上游的交易类型集合
    context.setTransTypes(this.getTranstypes());
    // 标志在交换根据目的交易类型分组时，查找目的交易类型的依据，有三个可设置值：1（根据接口定义）、
    // 2（根据流程配置）、-1（不根据交易类型分组）
    context.setClassifyMode(PfButtonClickContext.ClassifyByBusiflow);
    return context;
  }

  /**
   * 更新退货标志
   * 
   * @param voArray 退货单VO数组
   * @return 退货单VO数组
   */
  private ArriveVO[] updateBackFlag(ArriveVO[] voArray) {
    for (ArriveVO vo : voArray) {
      vo.getHVO().setBisback(UFBoolean.TRUE);
    }
    return voArray;
  }
}
