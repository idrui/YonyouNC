/**
 * $文件说明$
 *
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-11 下午02:29:00
 */
package nc.ui.pu.m25.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.ui.pu.m25.editor.utils.InvoiceUIScaleProcessor;
import nc.ui.pu.m25.print.InvoiceCombiningConfigDialog;
import nc.ui.pu.pub.print.IBillScale;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.print.combination.ICardPanelInit;
import nc.ui.pubapp.scale.CardPaneScaleProcessor;
import nc.ui.pubapp.scale.TotalValueScaleProcessor;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.UIState;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.pub.InvoiceScaleProcessor;
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
 * <li>合并显示按钮
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-4-11 下午02:29:00
 */
public class InvoicePrintCombineAction extends InvoiceNormalAction {

  private static final long serialVersionUID = -4075835748710745407L;

  public InvoicePrintCombineAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_COMBINEPRINT);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    //
    InvoiceVO invoiceVO = (InvoiceVO) this.getModel().getSelectedData();
    // 自定义的发票对话框中，将零数量行的数量赋值为零。所以对invoiceVO进行复制
    InvoiceCombiningConfigDialog combConfigDialog =
        new InvoiceCombiningConfigDialog(WorkbenchEnvironment.getClientApplet(),
            this.getEditor().getBillCardPanel(), (InvoiceVO)invoiceVO.clone());
    combConfigDialog.setLoginContext(this.getModel().getContext());
    combConfigDialog.setNodeCode(PuNodeCode.n40041000.code());
    combConfigDialog.setMaterialClassification(true);
    combConfigDialog.setNodeKey(PuNodeKey.n4004100001.code());
    combConfigDialog.setPtMaterialNameItemKey(PUQueryConst.MATERIALNAME);
    combConfigDialog.addGroupColumn(this.getGroupLeftItemKeys() == null ? null
        : this.getGroupLeftItemKeys().toArray(new String[0]), this
        .getGroupRightItemKeys() == null ? null : this.getGroupRightItemKeys()
        .toArray(new String[0]));
    combConfigDialog.addSumColumn(new String[] {
      InvoiceItemVO.NORIGTAXMNY,// 价税合计
      InvoiceItemVO.NCALCOSTMNY, // 计成本金额
      InvoiceItemVO.NCALTAXMNY,// 计税金额
      InvoiceItemVO.NNOSUBTAX,// 不可抵扣税额
      InvoiceItemVO.NTAX,// 税额
      InvoiceItemVO.NMNY,// 本币无税金额
      InvoiceItemVO.NTAXMNY,// 本币价税合计
      InvoiceItemVO.NGROUPMNY,// 集团本币无税金额
      InvoiceItemVO.NGROUPTAXMNY,// 集团本币价税合计
      InvoiceItemVO.NGLOBALMNY,// 全局本币无税金额
      InvoiceItemVO.NGLOBALTAXMNY
    // 全局本币价税合计
        }, new String[] {
          InvoiceItemVO.NNUM,// 主数量
          InvoiceItemVO.NASTNUM,// 数量
          // InvoiceItemVO.NORIGPRICE,// 主无税单价
          InvoiceItemVO.NORIGMNY
        // 金额
        });

    combConfigDialog.addAverageColumn(new String[] {
      InvoiceItemVO.NNUM,// 主数量
      InvoiceItemVO.NASTNUM,// 数量
      // InvoiceItemVO.NORIGPRICE,// 主无税单价
      InvoiceItemVO.NORIGMNY,// 金额
      InvoiceItemVO.NORIGTAXMNY,// 价税合计
      InvoiceItemVO.NCALCOSTMNY, // 计成本金额
      InvoiceItemVO.NCALTAXMNY,// 计税金额
      InvoiceItemVO.NNOSUBTAX,// 不可抵扣税额
      InvoiceItemVO.NTAX,// 税额
      InvoiceItemVO.NMNY,// 本币无税金额
      InvoiceItemVO.NTAXMNY,// 本币价税合计
      InvoiceItemVO.NGROUPMNY,// 集团本币无税金额
      InvoiceItemVO.NGROUPTAXMNY,// 集团本币价税合计
      InvoiceItemVO.NGLOBALMNY,// 全局本币无税金额
      InvoiceItemVO.NGLOBALTAXMNY
    // 全局本币价税合计
        }, new String[] {});

    combConfigDialog.addWeightAverageColumn(new String[] {
      InvoiceItemVO.NNUM,// 主数量
      InvoiceItemVO.NASTNUM,// 数量
      // InvoiceItemVO.NORIGPRICE,// 主无税单价
      InvoiceItemVO.NORIGMNY,// 金额
      InvoiceItemVO.NORIGTAXMNY,// 价税合计
      InvoiceItemVO.NCALCOSTMNY, // 计成本金额
      InvoiceItemVO.NCALTAXMNY,// 计税金额
      InvoiceItemVO.NNOSUBTAX,// 不可抵扣税额
      InvoiceItemVO.NTAX,// 税额
      InvoiceItemVO.NMNY,// 本币无税金额
      InvoiceItemVO.NTAXMNY,// 本币价税合计
      InvoiceItemVO.NGROUPMNY,// 集团本币无税金额
      InvoiceItemVO.NGROUPTAXMNY,// 集团本币价税合计
      InvoiceItemVO.NGLOBALMNY,// 全局本币无税金额
      InvoiceItemVO.NGLOBALTAXMNY
    // 全局本币价税合计
        }, new String[] {
          InvoiceItemVO.NASTORIGPRICE,// 无税单价
          InvoiceItemVO.NASTORIGTAXPRICE,// 含税单价
        });
    combConfigDialog.setMaterialPKColumn(InvoiceItemVO.PK_MATERIAL);
    combConfigDialog.setNumKey(InvoiceItemVO.NNUM);
    combConfigDialog.setBillTitle(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
        .getStrByID("4004050_0", "04004050-0004")/* @res "采购发票" */);
    // 添加模板处理器
    combConfigDialog.setCardPanelInit(new ICardPanelInit() {
      @Override
      public void intCardPanel(BillCardPanel cardPanel) {
        // 处理精度
        new InvoiceUIScaleProcessor().setScale(new CardPaneScaleProcessor(
            InvoicePrintCombineAction.this.getModel().getContext()
                .getPk_group(), cardPanel), new TotalValueScaleProcessor(
            cardPanel));
      }
    });
    combConfigDialog.setBillScale(new IBillScale() {
      @Override
      public void processBillScale(AggregatedValueObject vo) {
        BillVOScaleProcessor scale =
            new BillVOScaleProcessor(InvoicePrintCombineAction.this.getModel()
                .getContext().getPk_group(), new AggregatedValueObject[] {
              vo
            });
        TotalValueVOScaleProcessor totalScale =
            new TotalValueVOScaleProcessor(new AggregatedValueObject[] {
              vo
            });
        new InvoiceScaleProcessor().setScale(scale, totalScale);
      }
    });
    combConfigDialog.showModal();

  }

  /**
   * 分组左侧item
   * 
   * @return
   */
  private List<String> getGroupLeftItemKeys() {
    BillCardPanel cardPanel = this.getEditor().getBillCardPanel();
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
    list.add(InvoiceItemVO.PK_MATERIAL);
    return list;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    BillManageModel model = this.getModel();
    if (model.getData() == null || model.getData().size() == 0
        || model.getSelectedData() == null) {
      return false;
    }
    return model.getUiState() != UIState.EDIT;
  }

}
