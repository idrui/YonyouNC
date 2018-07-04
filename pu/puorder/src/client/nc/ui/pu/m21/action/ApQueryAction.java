/**
 * $文件说明$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-7 下午02:32:04
 */
package nc.ui.pu.m21.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m21.IOrderLinkBillQuery;
import nc.ui.pu.m21.view.ApQueryDlg;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.ClientContext;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.ApDataVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.scmpub.res.SCMActionCode;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>供应商应付
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-7 下午02:32:04
 */
public class ApQueryAction extends NCAction {

  private static final long serialVersionUID = -1492113860468677150L;

  private ShowUpableBillForm billForm;

  private ShowUpableBillForm billForm_Add;

  private BillManageModel model;

  public ApQueryAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_SUPPLIERAP);
    // this.putValue(Action.ACCELERATOR_KEY,
    // KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.ALT_MASK));
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    ShowUpableBillForm showBillForm = null;
    if (this.billForm != null && this.billForm.isComponentVisible()) {
      showBillForm = this.billForm;
    }
    else if (this.billForm_Add != null
        && this.billForm_Add.isComponentVisible()) {
      showBillForm = this.billForm_Add;
    }
    if (null == showBillForm) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0032")/*
                                                                   * @res
                                                                   * "只在卡片下可用"
                                                                   */);
    }
    BillCardPanel panel = showBillForm.getBillCardPanel();
    // 供应商基本信息ID
    String pk_supplier =
        (String) panel.getHeadItem(OrderHeaderVO.PK_SUPPLIER).getValueObject();
    String pk_org =
        (String) panel.getHeadItem(OrderHeaderVO.PK_ORG).getValueObject();
    if (StringUtil.isEmptyWithTrim(pk_supplier)
        || StringUtil.isEmptyWithTrim(pk_org)) {
      return;
    }

    if (0 == panel.getRowCount()) {
      return;
    }

    // List<ApDataVO> list = new ArrayList<ApDataVO>();
    // for (int i = 0; i < panel.getRowCount(); ++i) {
    // String pk_apfinanceorg =
    // (String) panel.getBodyValueAt(i, OrderItemVO.PK_APFINANCEORG);
    // if (StringUtil.isEmptyWithTrim(pk_apfinanceorg)) {
    // continue;
    // }
    // ApDataVO vo = new ApDataVO();
    // vo.setPk_apfinanceorg(pk_apfinanceorg);
    //
    // String pk_apfinanceorg_v =
    // (String) panel.getBodyValueAt(i, OrderItemVO.PK_APFINANCEORG_V);
    // vo.setPk_apfinanceorg_v(pk_apfinanceorg_v);
    // list.add(vo);
    // }
    //
    // if (0 == list.size()) {
    // return;
    // }

    // ApDataVO[] vos = list.toArray(new ApDataVO[list.size()]);
    IOrderLinkBillQuery service =
        NCLocator.getInstance().lookup(IOrderLinkBillQuery.class);
    ApDataVO[] vos = service.getApData(pk_org, pk_supplier);
    if (!ArrayUtils.isEmpty(vos)) {
      String pk_group = ClientContext.getInstance().getPk_group();
      ScaleUtils scale = new ScaleUtils(pk_group);
      for (ApDataVO vo : vos) {
        String ccurrencyid = vo.getCcurrencyid();
        vo.setNfinanceap(scale.adjustMnyScale(vo.getNfinanceap(), ccurrencyid));
        vo.setNoperationap(scale.adjustMnyScale(vo.getNoperationap(),
            ccurrencyid));
        vo.setNorderap(scale.adjustMnyScale(vo.getNorderap(), ccurrencyid));
        vo.setNorderpaymny(scale.adjustMnyScale(vo.getNorderpaymny(),
            ccurrencyid));
        vo.setNunverifymny(scale.adjustMnyScale(vo.getNunverifymny(),
            ccurrencyid));
      }
    }
    ApQueryDlg apDlg = new ApQueryDlg(showBillForm, vos,true);
    apDlg.setResizable(true);
    apDlg.showModal();
  }

  public BillManageModel getModel() {
    return this.model;
  }

  /**
   * @param billForm
   *          要设置的 billForm
   */
  public void setBillForm(ShowUpableBillForm billForm) {
    this.billForm = billForm;
  }

  public void setBillForm_Add(ShowUpableBillForm billForm_Add) {
    this.billForm_Add = billForm_Add;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
  }

}
