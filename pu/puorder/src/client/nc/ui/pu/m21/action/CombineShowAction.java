package nc.ui.pu.m21.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.ui.pu.m21.print.OrderCombiningConfigDialog;
import nc.ui.pu.m21.rule.OrderScaleSetter;
import nc.ui.pu.pub.print.IBillScale;
import nc.ui.pu.pub.view.PUBillForm;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.print.combination.ICardPanelInit;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.rule.OrderScaleRule;
import nc.vo.pu.pub.constant.PUQueryConst;
import nc.vo.pu.pub.enumeration.PuNodeCode;
import nc.vo.pu.pub.enumeration.PuNodeKey;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.scale.BillVOScaleProcessor;
import nc.vo.pubapp.scale.TotalValueVOScaleProcessor;
import nc.vo.scmpub.res.SCMActionCode;

/**
 * @since 6.0
 * @version 2011-1-26 下午06:29:26
 * @author wuxla
 */

public class CombineShowAction extends NCAction {

  private static final long serialVersionUID = 7600332555984966036L;

  private BillManageModel model;

  private PUBillForm orderForm;

  public CombineShowAction() {
    // String str = "合并显示";
    // this.putValue(INCAction.CODE, str);
    // this.setBtnName(str);
    // this.putValue(Action.ACCELERATOR_KEY,
    // KeyStroke.getKeyStroke(KeyEvent.VK_X, Event.SHIFT_MASK));
    // this.putValue(Action.SHORT_DESCRIPTION, str + "(Shift+X)");

    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_COMBINEPRINT);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    OrderCombiningConfigDialog combConfigDialog =
        new OrderCombiningConfigDialog(WorkbenchEnvironment.getClientApplet(),
            this.orderForm.getBillCardPanel(), (OrderVO) this.getModel()
                .getSelectedData());
    combConfigDialog.setMaterialClassification(true);
    combConfigDialog.setLoginContext(this.getModel().getContext());
    combConfigDialog.setNodeCode(PuNodeCode.n40040400.code());
    combConfigDialog.setNodeKey(PuNodeKey.n4004040001.code());
    combConfigDialog.setPtMaterialNameItemKey(PUQueryConst.MATERIALNAME);
    combConfigDialog.addGroupColumn(this.getGroupLeftItemKeys() == null ? null
        : this.getGroupLeftItemKeys().toArray(new String[0]), this
        .getGroupRightItemKeys() == null ? null : this.getGroupRightItemKeys()
        .toArray(new String[0]));
    combConfigDialog.addSumColumn(new String[] {
      OrderItemVO.NCALCOSTMNY, // 计成本金额
      OrderItemVO.NCALTAXMNY,// 计税金额
      OrderItemVO.NNOSUBTAX,// 不可抵扣税额
      OrderItemVO.NMNY,// 本币无税金额
      OrderItemVO.NTAXMNY,// 本币价税合计
      OrderItemVO.NGROUPMNY,// 集团本币无税金额
      OrderItemVO.NGROUPTAXMNY,// 集团本币价税合计
      OrderItemVO.NGLOBALMNY,// 全局本币无税金额
      OrderItemVO.NGLOBALTAXMNY
    // 全局本币价税合计
        }, new String[] {
          OrderItemVO.NORIGMNY,// 金额
          OrderItemVO.NTAX,// 税额
          OrderItemVO.NORIGTAXMNY,// 价税合计
          OrderItemVO.NNUM,// 主数量
          OrderItemVO.NASTNUM
        // 数量
        });

    combConfigDialog.addAverageColumn(new String[] {
      OrderItemVO.NORIGMNY,// 金额
      OrderItemVO.NTAX,// 税额
      OrderItemVO.NORIGTAXMNY,// 价税合计
      OrderItemVO.NNUM,// 主数量
      OrderItemVO.NASTNUM,// 数量
      OrderItemVO.NCALCOSTMNY, // 计成本金额
      OrderItemVO.NCALTAXMNY,// 计税金额
      OrderItemVO.NNOSUBTAX,// 不可抵扣税额
      OrderItemVO.NMNY,// 本币无税金额
      OrderItemVO.NTAXMNY,// 本币价税合计
      OrderItemVO.NGROUPMNY,// 集团本币无税金额
      OrderItemVO.NGROUPTAXMNY,// 集团本币价税合计
      OrderItemVO.NGLOBALMNY,// 全局本币无税金额
      OrderItemVO.NGLOBALTAXMNY
    // 全局本币价税合计
        }, new String[] {});

    combConfigDialog.addWeightAverageColumn(new String[] {
      OrderItemVO.NORIGMNY,// 金额
      OrderItemVO.NTAX,// 税额
      OrderItemVO.NORIGTAXMNY,// 价税合计
      OrderItemVO.NNUM,// 主数量
      OrderItemVO.NASTNUM,// 数量
      OrderItemVO.NCALCOSTMNY, // 计成本金额
      OrderItemVO.NCALTAXMNY,// 计税金额
      OrderItemVO.NNOSUBTAX,// 不可抵扣税额
      OrderItemVO.NMNY,// 本币无税金额
      OrderItemVO.NTAXMNY,// 本币价税合计
      OrderItemVO.NGROUPMNY,// 集团本币无税金额
      OrderItemVO.NGROUPTAXMNY,// 集团本币价税合计
      OrderItemVO.NGLOBALMNY,// 全局本币无税金额
      OrderItemVO.NGLOBALTAXMNY
    // 全局本币价税合计
        }, new String[] {
          // 无税单价和 /** 无税净价 */
          OrderItemVO.NQTORIGPRICE, OrderItemVO.NQTORIGNETPRICE
        });
    combConfigDialog.setMaterialPKColumn(OrderItemVO.PK_MATERIAL);
    combConfigDialog.setNumKey(OrderItemVO.NNUM);
    combConfigDialog.setBillTitle(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
        .getStrByID("4004030_0", "04004030-0037")/* @res "采购订单" */);

    // 添加模板处理器
    combConfigDialog.setCardPanelInit(new ICardPanelInit() {
      @Override
      public void intCardPanel(BillCardPanel cardPanel) {
        // 处理精度
        new OrderScaleSetter(CombineShowAction.this.getModel().getContext()
            .getPk_group()).setCardScale(cardPanel);
      }
    });
    combConfigDialog.setBillScale(new IBillScale() {
      @Override
      public void processBillScale(AggregatedValueObject vo) {
        BillVOScaleProcessor scale =
            new BillVOScaleProcessor(CombineShowAction.this.getModel()
                .getContext().getPk_group(), new AggregatedValueObject[] {
              vo
            });
        TotalValueVOScaleProcessor totalScale =
            new TotalValueVOScaleProcessor(new AggregatedValueObject[] {
              vo
            });
        new OrderScaleRule().setScale(scale, totalScale);
      }
    });
    combConfigDialog.showModal();
  }

  public BillManageModel getModel() {
    return this.model;
  }

  public PUBillForm getOrderForm() {
    return this.orderForm;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
    this.model.addAppEventListener(this);
  }

  public void setOrderForm(PUBillForm orderForm) {
    this.orderForm = orderForm;
  }

  /**
   * 分组左侧item
   * 
   * @return
   */
  private List<String> getGroupLeftItemKeys() {
    BillCardPanel cardPanel = this.orderForm.getBillCardPanel();
    BillItem[] showItems = cardPanel.getBodyShowItems();
    List<String> groupLeftItemKeys = new ArrayList<String>();
    if (showItems == null || showItems.length == 0) {
      return null;
    }
    for (int i = 0; i < showItems.length; i++) {
      if (showItems[i].isShow()) {
        groupLeftItemKeys.add(showItems[i].getKey());
      }
    }
    List<String> groupRightItemKeys = this.getGroupRightItemKeys();
    if (groupRightItemKeys != null && groupRightItemKeys.size() > 0) {
      for (String groupRightItemKey : groupRightItemKeys) {
        if (groupLeftItemKeys.contains(groupRightItemKey)) {
          groupLeftItemKeys.remove(groupRightItemKey);
        }
      }
    }
    return groupLeftItemKeys;
  }

  /**
   * 分组右侧item
   * 
   * @return
   */
  private List<String> getGroupRightItemKeys() {
    List<String> list = new ArrayList<String>();
    list.add(OrderItemVO.PK_MATERIAL);
    return list;
  }

  @Override
  protected boolean isActionEnable() {
    if (UIState.ADD == this.model.getUiState()
        || UIState.EDIT == this.model.getUiState()) {
      return false;
    }
    Object[] objs = this.getModel().getSelectedOperaDatas();
    if (null != objs && 1 < objs.length) {
      return true;
    }
    OrderVO vo = (OrderVO) this.getModel().getSelectedData();
    return vo != null && UFBoolean.FALSE.equals(vo.getHVO().getBfrozen())
        && this.model.getUiState() == UIState.NOT_EDIT;

  }

}
