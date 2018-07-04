/**
 * $文件说明$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-21 上午08:40:08
 */
package nc.ui.pu.m21.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.ui.ml.NCLangRes;
import nc.ui.pu.m21.view.GrossProfitUI;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.bd.material.MaterialVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.SCMActionCode;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>毛利预估
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-10-21 上午08:40:08
 */
public class BillGrossProfitAction extends NCAction {

  private static final long serialVersionUID = 7309086668032069284L;

  private ShowUpableBillForm billForm;

  private ShowUpableBillForm billForm_Add;

  private BillManageModel model;

  public BillGrossProfitAction() {
    SCMActionInitializer.initializeAction(this,
        SCMActionCode.SCM_CROSSPROFITRPT);
    // String str = "毛利预估";
    // this.putValue(INCAction.CODE, str);
    // this.setBtnName(str);
    // this.putValue(Action.SHORT_DESCRIPTION, str);
    // this.putValue(Action.ACCELERATOR_KEY,
    // KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK));
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
    if (!this.getBillForm().isComponentVisible()) {
      return;
    }
    OrderVO vo = this.getGrossProfitOrderVO();
    if (null == vo) {
      return;
    }

    GrossProfitUI ui =
        new GrossProfitUI(this.getBillForm(), NCLangRes.getInstance()
            .getStrByID("4004030_0", "04004030-0219")/* 毛利预估 */, vo,true);
    ui.showModal();
  }

  /**
   * @return billForm
   */
  public ShowUpableBillForm getBillForm() {
    return this.billForm;
  }

  public ShowUpableBillForm getBillForm_Add() {
    return this.billForm_Add;
  }

  /**
   * @return model
   */
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

  /**
   * @param model
   *          要设置的 model
   */
  public void setModel(BillManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  private OrderVO getGrossProfitOrderVO() {
    OrderVO vo = null;
    if (AppUiState.NOT_EDIT.equals(this.getModel().getAppUiState())) {
      vo = (OrderVO) this.getModel().getSelectedData();
    }
    else {
      vo =
          (OrderVO) this
              .getBillForm()
              .getBillCardPanel()
              .getBillValueVO(OrderVO.class.getName(),
                  OrderHeaderVO.class.getName(), OrderItemVO.class.getName());
    }
    if (null == vo || ArrayUtils.isEmpty(vo.getBVO())
        || StringUtil.isEmptyWithTrim(vo.getHVO().getCorigcurrencyid())) {
      return null;
    }

    List<String> materialList = new ArrayList<String>();

    for (OrderItemVO itemVO : vo.getBVO()) {
      String pk_material = itemVO.getPk_material();
      if (StringUtil.isEmptyWithTrim(pk_material)) {
        continue;
      }
      if (itemVO.getBlargess().booleanValue()) {
        continue;
      }
      materialList.add(itemVO.getPk_material());
    }

    if (0 == materialList.size()) {
      return null;
    }
    Map<String, MaterialVO> matMap = null;

    matMap =
        MaterialPubService.queryMaterialBaseInfo(
            materialList.toArray(new String[materialList.size()]),
            new String[] {
              MaterialVO.FEE, MaterialVO.DISCOUNTFLAG
            });

    if (null == matMap) {
      return null;
    }

    List<OrderItemVO> itemList = new ArrayList<OrderItemVO>();
    StringBuilder sb = new StringBuilder();
    for (OrderItemVO itemVO : vo.getBVO()) {
      String pk_material = itemVO.getPk_material();
      if (StringUtil.isEmptyWithTrim(pk_material)) {
        continue;
      }
      if (itemVO.getBlargess().booleanValue()) {
        continue;
      }
      MaterialVO matVO = matMap.get(pk_material);
      if (matVO == null || UFBoolean.TRUE.equals(matVO.getFee())
          || UFBoolean.TRUE.equals(matVO.getDiscountflag())) {
        sb.append("[" + itemVO.getCrowno() + "]");
        continue;
      }
      itemList.add(itemVO);
    }

    if (sb.length() > 0) {
      ShowStatusBarMsgUtil.showStatusBarMsg(
          NCLangRes.getInstance().getStrByID("4004030_0", "04004030-0220",
              null, new String[] {
                sb.toString()
              })/* 第{0}行不能进行毛利预估：原因是该行物料可能为费用、折扣属性 */, this.getModel()
              .getContext());
    }

    if (itemList.size() > 0) {
      vo.setBVO(itemList.toArray(new OrderItemVO[itemList.size()]));
      return vo;
    }

    return null;
  }

  @Override
  protected boolean isActionEnable() {
    // if (!this.getBillForm().isComponentVisible()) {
    // return false;
    // }
    return true;
  }
}
