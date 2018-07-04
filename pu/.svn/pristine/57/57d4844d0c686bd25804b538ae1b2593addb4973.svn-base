package nc.ui.pu.m23.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.ui.pu.m23.rule.ArriveUIScaleRule;
import nc.ui.pu.m23.view.ArriveCardForm;
import nc.ui.pu.pub.print.IBillScale;
import nc.ui.pu.pub.print.PUCombiningConfigDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.print.combination.ICardPanelInit;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.rule.ArriveScaleRule;
import nc.vo.pu.pub.constant.PUQueryConst;
import nc.vo.pu.pub.enumeration.PuNodeCode;
import nc.vo.pu.pub.enumeration.PuNodeKey;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pubapp.scale.BillVOScaleProcessor;
import nc.vo.pubapp.scale.TotalValueVOScaleProcessor;
import nc.vo.scmpub.res.SCMActionCode;

public class CombinePrintUIAction extends NCAction {

  private static final long serialVersionUID = 7831180720611056274L;

  private ArriveCardForm billForm;

  private AbstractAppModel model;

  public CombinePrintUIAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_COMBINEPRINT);
    // String name = "合并显示";
    // this.setBtnName(name);
    // this.putValue(INCAction.CODE, name);
    // this.putValue(Action.ACCELERATOR_KEY,
    // KeyStroke.getKeyStroke(KeyEvent.VK_X, Event.SHIFT_MASK));
    // this.putValue(Action.SHORT_DESCRIPTION, name);
  }

  @SuppressWarnings("restriction")
  @Override
  public void doAction(ActionEvent e) throws Exception {
    PUCombiningConfigDialog combConfigDialog =
        new PUCombiningConfigDialog(WorkbenchEnvironment.getClientApplet(),
            this.billForm.getBillCardPanel(), (ArriveVO) this.getModel()
                .getSelectedData());
    combConfigDialog.setLoginContext(this.getModel().getContext());
    combConfigDialog.setNodeCode(PuNodeCode.n40040800.code());
    combConfigDialog.setMaterialClassification(true);
    combConfigDialog.setNodeKey(PuNodeKey.n4004080001.code());
    combConfigDialog.setPtMaterialNameItemKey(PUQueryConst.MATERIALNAME);
    combConfigDialog.addGroupColumn(this.getGroupLeftItemKeys() == null ? null
        : this.getGroupLeftItemKeys().toArray(new String[0]), this
        .getGroupRightItemKeys() == null ? null : this.getGroupRightItemKeys()
        .toArray(new String[0]));
    combConfigDialog.addSumColumn(new String[] {
      ArriveItemVO.NORIGMNY,// 金额
      ArriveItemVO.NORIGTAXMNY,// 价税合计
      ArriveItemVO.NTAX,// 税额
      ArriveItemVO.NMNY,// 本币无税金额
      ArriveItemVO.NTAXMNY
    // 本币价税合计
        }, new String[] {
          ArriveItemVO.NNUM,// 主数量
          ArriveItemVO.NASTNUM, // 数量
          ArriveItemVO.NELIGNUM,// 合格主数量
          ArriveItemVO.NNOTELIGNUM,// 不合格主数量
          ArriveItemVO.NWASTNUM
        // /** 途耗主数量 */
        });

    combConfigDialog.addAverageColumn(new String[] {
      ArriveItemVO.NNUM,// 主数量
      ArriveItemVO.NASTNUM, // 数量
      ArriveItemVO.NELIGNUM,// 合格主数量
      ArriveItemVO.NNOTELIGNUM,// 不合格主数量
      ArriveItemVO.NWASTNUM,// /** 途耗主数量 */
      ArriveItemVO.NORIGMNY,// 金额
      ArriveItemVO.NORIGTAXMNY,// 价税合计
      ArriveItemVO.NTAX,// 税额
      ArriveItemVO.NMNY,// 本币无税金额
      ArriveItemVO.NTAXMNY
    // 本币价税合计
        }, new String[] {});

    combConfigDialog.addWeightAverageColumn(new String[] {
      ArriveItemVO.NNUM,// 主数量
      ArriveItemVO.NASTNUM, // 数量
      ArriveItemVO.NELIGNUM,// 合格主数量
      ArriveItemVO.NNOTELIGNUM,// 不合格主数量
      ArriveItemVO.NWASTNUM,// /** 途耗主数量 */
      ArriveItemVO.NORIGMNY,// 金额
      ArriveItemVO.NORIGTAXMNY,// 价税合计
      ArriveItemVO.NTAX,// 税额
      ArriveItemVO.NMNY,// 本币无税金额
      ArriveItemVO.NTAXMNY
    // 本币价税合计
        }, new String[] {});
    combConfigDialog.setMaterialPKColumn(ArriveItemVO.PK_MATERIAL);
    combConfigDialog.setNumKey(ArriveItemVO.NNUM);
    combConfigDialog.setBillTitle(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
        .getStrByID("4004040_0", "04004040-0003")/* @res "到货单" */);

    // 添加模板处理器
    combConfigDialog.setCardPanelInit(new ICardPanelInit() {
      @Override
      public void intCardPanel(BillCardPanel cardPanel) {
        // 处理精度
        new ArriveUIScaleRule(CombinePrintUIAction.this.getModel().getContext()
            .getPk_group()).setCardScale(cardPanel);
      }
    });
    combConfigDialog.setBillScale(new IBillScale() {
      @Override
      public void processBillScale(AggregatedValueObject vo) {
        BillVOScaleProcessor scale =
            new BillVOScaleProcessor(CombinePrintUIAction.this.getModel()
                .getContext().getPk_group(), new AggregatedValueObject[] {
              vo
            });
        TotalValueVOScaleProcessor totalScale =
            new TotalValueVOScaleProcessor(new AggregatedValueObject[] {
              vo
            });
        new ArriveScaleRule().setScale(scale, totalScale);
      }
    });
    combConfigDialog.showModal();

  }

  public ArriveCardForm getBillForm() {
    return this.billForm;
  }

  public AbstractAppModel getModel() {
    return this.model;
  }

  public void setBillForm(ArriveCardForm billForm) {
    this.billForm = billForm;
  }

  public void setModel(AbstractAppModel model) {
    this.model = model;
    this.model.addAppEventListener(this);
  }

  /**
   * 分组左侧item
   * 
   * @return
   */
  private List<String> getGroupLeftItemKeys() {
    BillCardPanel cardPanel = this.billForm.getBillCardPanel();
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
    list.add(ArriveItemVO.PK_MATERIAL);
    return list;
  }

  @Override
  protected boolean isActionEnable() {
    return this.model.getUiState() == UIState.NOT_EDIT
        && this.model.getSelectedData() != null;
  }

}
