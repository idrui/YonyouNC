/**
 * $文件说明$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-3 上午08:17:29
 */
package nc.ui.pu.m422x.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.ui.pu.m422x.rule.ScaleSetter;
import nc.ui.pu.pub.print.IBillScale;
import nc.ui.pu.pub.print.PUCombiningConfigDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.print.combination.ICardPanelInit;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pu.m422x.rule.StoreReqScaleRule;
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
 * <li>合并显示
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-8-3 上午08:17:29
 */
public class CombineAction extends NCAction {

  private static final long serialVersionUID = 7072320271168217497L;

  private ShowUpableBillForm editor;

  private AbstractAppModel model;

  public CombineAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_COMBINEPRINT);
    // String name = "合并显示";
    // this.setBtnName(name);
    // this.putValue(INCAction.CODE, name);
    // this.putValue(Action.SHORT_DESCRIPTION, name);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @SuppressWarnings("restriction")
  @Override
  public void doAction(ActionEvent e) throws Exception {
    PUCombiningConfigDialog combConfigDialog =
        new PUCombiningConfigDialog(WorkbenchEnvironment.getClientApplet(),
            this.editor.getBillCardPanel(), (StoreReqAppVO) this.getModel()
                .getSelectedData());
    combConfigDialog.setLoginContext(this.getModel().getContext());
    combConfigDialog.setNodeCode(PuNodeCode.n40040000.code());
    combConfigDialog.setMaterialClassification(true);
    combConfigDialog.setNodeKey(PuNodeKey.n4004000001.code());
    combConfigDialog.setPtMaterialNameItemKey(PUQueryConst.MATERIALNAME);
    combConfigDialog.addGroupColumn(this.getGroupLeftItemKeys() == null ? null
        : this.getGroupLeftItemKeys().toArray(new String[0]), this
        .getGroupRightItemKeys() == null ? null : this.getGroupRightItemKeys()
        .toArray(new String[0]));
    combConfigDialog.addSumColumn(new String[] {}, new String[] {
      StoreReqAppItemVO.NNUM, StoreReqAppItemVO.NASTNUM,
      StoreReqAppItemVO.NTAXMNY, StoreReqAppItemVO.NACCUOUTNUM,
      StoreReqAppItemVO.NACCUOUTREQNUM
    });

    combConfigDialog.addAverageColumn(new String[] {
      StoreReqAppItemVO.NNUM, StoreReqAppItemVO.NASTNUM,
      StoreReqAppItemVO.NTAXMNY, StoreReqAppItemVO.NACCUOUTNUM,
      StoreReqAppItemVO.NACCUOUTREQNUM
    }, new String[] {});

    combConfigDialog.addWeightAverageColumn(new String[] {
      StoreReqAppItemVO.NNUM, StoreReqAppItemVO.NASTNUM,
      StoreReqAppItemVO.NTAXMNY, StoreReqAppItemVO.NACCUOUTNUM,
      StoreReqAppItemVO.NACCUOUTREQNUM
    }, new String[] {});
    combConfigDialog.setMaterialPKColumn(StoreReqAppItemVO.PK_MATERIAL);
    combConfigDialog.setNumKey(StoreReqAppItemVO.NNUM);
    combConfigDialog.setBillTitle(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
        .getStrByID("4004010_0", "04004010-0001")/* @res "物资需求申请单" */);

    // 添加模板处理器
    combConfigDialog.setCardPanelInit(new ICardPanelInit() {
      @Override
      public void intCardPanel(BillCardPanel cardPanel) {
        // 处理精度
        new ScaleSetter(CombineAction.this.getModel().getContext()
            .getPk_group()).setCardScale(cardPanel);
      }
    });
    combConfigDialog.setBillScale(new IBillScale() {
      @Override
      public void processBillScale(AggregatedValueObject vo) {
        BillVOScaleProcessor scale =
            new BillVOScaleProcessor(CombineAction.this.getModel().getContext()
                .getPk_group(), new AggregatedValueObject[] {
              vo
            });
        TotalValueVOScaleProcessor totalScale =
            new TotalValueVOScaleProcessor(new AggregatedValueObject[] {
              vo
            });
        new StoreReqScaleRule().setScale(scale, totalScale);
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
    model.addAppEventListener(this);
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
    list.add(StoreReqAppItemVO.PK_MATERIAL);
    return list;
  }

  @Override
  protected boolean isActionEnable() {
    return this.model.getUiState() == UIState.NOT_EDIT
        && this.model.getSelectedData() != null;
  }
}
