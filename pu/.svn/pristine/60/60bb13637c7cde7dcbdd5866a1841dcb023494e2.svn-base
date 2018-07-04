/**
 * $文件说明$
 *
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-4 下午07:56:05
 */
package nc.ui.pu.m20.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.ui.pu.m20.editor.util.PraybillScaleUtil;
import nc.ui.pu.pub.print.IBillScale;
import nc.ui.pu.pub.print.PUCombiningConfigDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.print.combination.ICardPanelInit;
import nc.ui.pubapp.scale.CardPaneScaleProcessor;
import nc.ui.pubapp.scale.TotalValueScaleProcessor;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.rule.PrayBillScaleRule;
import nc.vo.pu.pub.constant.PUQueryConst;
import nc.vo.pu.pub.enumeration.PuNodeCode;
import nc.vo.pu.pub.enumeration.PuNodeKey;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pubapp.scale.BillVOScaleProcessor;
import nc.vo.pubapp.scale.TotalValueVOScaleProcessor;
import nc.vo.scmpub.res.SCMActionCode;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>合并显示Action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-3-4 下午07:56:05
 */
public class PraybillCombineShowAction extends NCAction {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 6144093435749100115L;

  private ShowUpableBillForm editor;

  private AbstractAppModel model;

  public PraybillCombineShowAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_COMBINEPRINT);
  }

  @SuppressWarnings("restriction")
  @Override
  public void doAction(ActionEvent e) throws Exception {

    PUCombiningConfigDialog combConfigDialog =
        new PUCombiningConfigDialog(WorkbenchEnvironment.getClientApplet(),
            this.editor.getBillCardPanel(), (PraybillVO) this.getModel()
                .getSelectedData());
    combConfigDialog.setMaterialClassification(true);
    combConfigDialog.setLoginContext(this.getModel().getContext());
    combConfigDialog.setNodeCode(PuNodeCode.n40040200.code());
    combConfigDialog.setNodeKey(PuNodeKey.n4004020001.code());
    combConfigDialog.setPtMaterialNameItemKey(PUQueryConst.MATERIALNAME);
    combConfigDialog.addGroupColumn(this.getGroupLeftItemKeys() == null ? null
        : this.getGroupLeftItemKeys().toArray(new String[0]), this
        .getGroupRightItemKeys() == null ? null : this.getGroupRightItemKeys()
        .toArray(new String[0]));
    combConfigDialog.addSumColumn(new String[] {
      PraybillItemVO.NACCUMULATENUM, PraybillItemVO.NASTNUM,
      PraybillItemVO.NGENCT, PraybillItemVO.NNUM, PraybillItemVO.NTAXMNY,
      PraybillItemVO.NTAXPRICE
    }, new String[] {});

    combConfigDialog.addAverageColumn(new String[] {
      PraybillItemVO.NACCUMULATENUM, PraybillItemVO.NASTNUM,
      PraybillItemVO.NGENCT, PraybillItemVO.NNUM, PraybillItemVO.NTAXMNY,
      PraybillItemVO.NTAXPRICE
    }, new String[] {});

    combConfigDialog.addWeightAverageColumn(new String[] {
      PraybillItemVO.NACCUMULATENUM, PraybillItemVO.NASTNUM,
      PraybillItemVO.NGENCT, PraybillItemVO.NNUM, PraybillItemVO.NTAXMNY,
      PraybillItemVO.NTAXPRICE
    }, new String[] {});
    combConfigDialog.setMaterialPKColumn(PraybillItemVO.PK_MATERIAL);
    combConfigDialog.setNumKey(PraybillItemVO.NNUM);
    combConfigDialog.setBillTitle(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
        .getStrByID("4004020_0", "04004020-0008")/* @res "请购单" */);

    // 添加模板处理器
    combConfigDialog.setCardPanelInit(new ICardPanelInit() {
      @Override
      public void intCardPanel(BillCardPanel cardPanel) {
        // 处理精度
        new PraybillScaleUtil().setScale(new CardPaneScaleProcessor(
            PraybillCombineShowAction.this.getModel().getContext()
                .getPk_group(), cardPanel), new TotalValueScaleProcessor(
            cardPanel));
      }
    });
    combConfigDialog.setBillScale(new IBillScale() {
      @Override
      public void processBillScale(AggregatedValueObject vo) {
        BillVOScaleProcessor scale =
            new BillVOScaleProcessor(PraybillCombineShowAction.this.getModel()
                .getContext().getPk_group(), new AggregatedValueObject[] {
              vo
            });
        TotalValueVOScaleProcessor totalScale =
            new TotalValueVOScaleProcessor(new AggregatedValueObject[] {
              vo
            });
        new PrayBillScaleRule().setScale(scale, totalScale);
      }
    });
    combConfigDialog.showModal();
  }

  public ShowUpableBillForm getEditor() {
    return this.editor;
  }

  public AbstractAppModel getModel() {
    return this.model;
  }

  public void setEditor(ShowUpableBillForm editor) {
    this.editor = editor;
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
    BillCardPanel cardPanel = this.editor.getBillCardPanel();
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
    list.add(PraybillItemVO.PK_MATERIAL);
    return list;
  }

  @Override
  protected boolean isActionEnable() {
    return this.model.getUiState() == UIState.NOT_EDIT
        && this.model.getSelectedData() != null;
  }
}
