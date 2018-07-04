/**
 *
 */
package nc.ui.pu.m25.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.itf.uap.pf.busiflow.PfButtonClickContext;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.util.BusiDateSetter;
import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.uif2app.actions.AbstractReferenceAction;
import nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeFuncUtils;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.uif2.UIState;
import nc.ui.uif2.editor.IBillCardPanelEditor;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pu.pub.util.ListUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.Log;
import nc.vo.pubapp.util.VORowNoUtils;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.res.billtype.SCBillType;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 采购发票参照按钮
 * 
 * @since 6.0
 * @version 2010-11-11 上午10:38:42
 * @author tianft
 */
public abstract class InvoiceRefAddAction extends AbstractReferenceAction {

  private static final long serialVersionUID = -7167526730230052116L;

  // 卡片面板
  private IBillCardPanelEditor editor;

  // model
  private BillManageModel model;

  public InvoiceRefAddAction() {
    super();
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    PfUtilClient.childButtonClickedNew(this.createPfButtonClickContext());
    if (PfUtilClient.isCloseOK()) {
      InvoiceVO[] retvos = (InvoiceVO[]) PfUtilClient.getRetVos();
      if (retvos == null || retvos.length <= 0) {
        return;
      }

      BusiDateSetter busidateSetter = new BusiDateSetter();
      // 单据日期
      busidateSetter.setHeaderBusiDate(retvos, InvoiceHeaderVO.DBILLDATE);
      // 票到日期
      busidateSetter.setHeaderBusiDate(retvos, InvoiceHeaderVO.DARRIVEDATE);
      this.getTransferViewProcessor().processBillTransfer(retvos);
    }
  }

  public IBillCardPanelEditor getEditor() {
    return this.editor;
  }

  /**
   * @return model
   */
  public BillManageModel getModel() {
    return this.model;
  }

  public void setEditor(IBillCardPanelEditor editor) {
    this.editor = editor;
  }

  /**
   * @param model
   *          要设置的 model
   */
  public void setModel(BillManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  /**
   * 修改对模板非空字段异型性的校验为根据OID，避免由于档案版本化之后引入的最新版本不一致问题
   * 目前处理的字段：财务组织
   * 
   * @param notNullKey 非空字段名称列表
   */
  private void changeNotNullKey2OID(String[] notNullKey) {
    if (ArrayUtils.isEmpty(notNullKey)) {
      return;
    }
    for (int i = 0; i < notNullKey.length; i++) {
      if (notNullKey[i] != null && InvoiceItemVO.PK_ORG_V.equals(notNullKey[i])) {
        notNullKey[i] = InvoiceItemVO.PK_ORG;
        break;
      }
    }
  }

  /**
   * 对业务流程的检查,是否有直接来源
   * 
   * @param pk_busitype
   * @return
   */
  private boolean checkBusiType(String pk_busitype, String srcBilltype) {
    if (StringUtils.isEmpty(pk_busitype)) {
      return false;
    }
    String msg = "";
    if (POBillType.Order.getCode().equals(srcBilltype)) {
      msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0118")/*
                               * @res
                               * "发票不是来源于采购订单，不能参照采购订单增行！"
                               */;
    }
    else if (ICBillType.PurchaseIn.getCode().equals(srcBilltype)) {
      msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0119")/*
                               * @res
                               * "发票不是来源于采购入库单，不能参照采购入库单增行！";
                               */;
    }
    else if (ICBillType.SubContinIn.getCode().equals(srcBilltype)) {
      msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0131")/*
                               * @res
                               * "发票不是来源于委托加工入库单，不能参照委托加工入库单增行！";
                               */;
    }
    else if (POBillType.InitEstimate.getCode().equals(srcBilltype)) {
      msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0132")/*
                               * @res
                               * "发票不是来源于期初暂估单，不能参照期初暂估单增行！";
                               */;
    }
    else if (ICBillType.VmiSum.getCode().equals(srcBilltype)) {
      msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0133")/*
                               * @res
                               * "发票不是来源于消耗汇总，不能参照消耗汇总增行！";
                               */;
    }
    else if (SCBillType.Order.getCode().equals(srcBilltype)) {
      msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0130")/*
                               * @res
                               * "发票不是来源于委外订单，不能参照委外订单增行！";
                               */;
    }
    int rowCount = this.getEditor().getBillCardPanel().getRowCount();
    String sourcetypecode = null;
    for (int i = 0; i < rowCount; i++) {
      Object bodyValueAt =
          this.getEditor().getBillCardPanel()
              .getBodyValueAt(i, InvoiceItemVO.CSOURCETYPECODE);
      sourcetypecode = bodyValueAt == null ? null : (String) bodyValueAt;
      if (null != sourcetypecode) {
        break;
      }
    }
    if (null == sourcetypecode || sourcetypecode.equals(srcBilltype)) {
      return true;
    }
    ExceptionUtils.wrappBusinessException(msg);
    return false;
  }

  private PfButtonClickContext createAddRowPfButtonClickContext(
      List<String> busiType) {
    PfButtonClickContext context = new PfButtonClickContext();
    context.setParent(this.getModel().getContext().getEntranceUI());
    context.setSrcBillType(this.getSrcBillTypeCode());
    context.setPk_group(this.getModel().getContext().getPk_group());
    context.setUserId(this.getModel().getContext().getPk_loginUser());
    // 如果该节点是由交易类型发布的，那么这个参数应该传交易类型，否则传单据类型
    String vtrantype =
        TrantypeFuncUtils.getTrantype(this.getModel().getContext());
    if (StringUtil.isEmptyWithTrim(vtrantype)) {
      context.setCurrBilltype(POBillType.Invoice.getCode());
    }
    else {
      context.setCurrBilltype(vtrantype);
    }
    context.setUserObj(null);
    context.setSrcBillId(null);
    context.setBusiTypes(busiType);
    // 上面的参数在原来调用的方法中都有涉及，只不过封成了一个整结构，下面两个参数是新加的参数
    // 上游的交易类型集合,交易类型只要有空的情况就不用set
    if (!ListUtil.isEmpty(this.getTranstypes())
        && !this.getTranstypes().contains("")) {
      context.setTransTypes(this.getTranstypes());
    }
    // 标志在交换根据目的交易类型分组时，查找目的交易类型的依据，有三个可设置值：1（根据接口定义）、
    // 2（根据流程配置）、-1（不根据交易类型分组）
    context.setClassifyMode(PfButtonClickContext.ClassifyByBusiflow);
    return context;
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
      context.setCurrBilltype(POBillType.Invoice.getCode());
    }
    else {
      context.setCurrBilltype(vtrantype);
    }
    context.setUserObj(null);
    context.setSrcBillId(null);
    context.setBusiTypes(this.getBusitypes());
    // 上面的参数在原来调用的方法中都有涉及，只不过封成了一个整结构，下面两个参数是新加的参数
    // 上游的交易类型集合,交易类型只要有空的情况就不用set
    if (!ListUtil.isEmpty(this.getTranstypes())
        && !this.getTranstypes().contains("")) {
      context.setTransTypes(this.getTranstypes());
    }
    // 标志在交换根据目的交易类型分组时，查找目的交易类型的依据，有三个可设置值：1（根据接口定义）、
    // 2（根据流程配置）、-1（不根据交易类型分组）
    context.setClassifyMode(PfButtonClickContext.ClassifyByBusiflow);
    return context;
  }

  /**
   * 参照增行处理
   * 
   * @throws Exception
   */
  protected void doRefAddRowAction() throws Exception {
    // 根据当前流程判断可否操作
    Object curVo = this.getEditor().getValue();
    if (curVo == null) {
      return;
    }
    InvoiceVO invoiceVO = (InvoiceVO) curVo;
    // 业务流程
    String pk_busitype = invoiceVO.getParentVO().getPk_busitype();
    if (StringUtils.isBlank(pk_busitype)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004050_0", "04004050-0005")/*
                                                                   * @res
                                                                   * "自制发票无法参照增行！"
                                                                   */);
    }
    if (!this.checkBusiType(pk_busitype, this.getSrcBillTypeCode())) {
      return;
    }
    List<String> busiType = new ArrayList<String>();
    busiType.add(pk_busitype);
    PfUtilClient.childButtonClickedNew(this
        .createAddRowPfButtonClickContext(busiType));
    if (PfUtilClient.isCloseOK()) {
      InvoiceVO[] retvos = (InvoiceVO[]) PfUtilClient.getRetVos();
      if (ArrayUtils.isEmpty(retvos)) {
        return;
      }
      // 从主数量重新进行计算，多次转单后数量不同
      // InvoiceVOUtil.reCalculateBasedNum(retvos);
      CardEditorHelper helper =
          new CardEditorHelper(this.getEditor().getBillCardPanel());
      // 克隆一份，避免点取消后界面数据不能及时刷新
      InvoiceVO curVO = (InvoiceVO) invoiceVO.clone();
      InvoiceHeaderVO curHeaderVO = curVO.getParentVO();
      // 当前模板表头非空字段key
      String[] notNullKey = helper.getNotNullHeadItemKeys();
      // 修改对模板非空字段异型性的校验为根据OID，避免由于档案版本化之后引入的最新版本不一致问题
      this.changeNotNullKey2OID(notNullKey);
      for (String key : notNullKey) {
        Log.debug(" InvoiceRefAddAction:not null key =" + key);
        Log.debug(" \n");
      }
      // 来源的表头非空字段如果与当前表头非空字段值不一致的话不让增行，不包括日期型字段
      boolean existDifferItem =
          CirVOUtil.existDifferNotNullItems(retvos, curHeaderVO, notNullKey,
              false);
      if (existDifferItem) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004050_0", "04004050-0006")/*
                                                                     * @res
                                                                     * "来源表头数据与当前表头数据不一致，无法参照增行！"
                                                                     */);
      }
      InvoiceItemVO[] combinItemVOs =
          AggVOUtil.getCombinItemVOs(retvos, invoiceVO.getChildrenVO());
      curVO.setChildrenVO(combinItemVOs);
      VORowNoUtils.setVOsRowNoByRule(new InvoiceVO[] {
        curVO
      }, InvoiceItemVO.CROWNO);
      this.getEditor().setValue(curVO);
    }
  }

  protected String getSrcBillTypeCode() {
    return null;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    return UIState.NOT_EDIT == this.getModel().getUiState();
  }

  /**
   * 转单是否根据流程过滤
   * 
   * @return
   */
  protected boolean isBusiness() {
    return true;
  }

}
