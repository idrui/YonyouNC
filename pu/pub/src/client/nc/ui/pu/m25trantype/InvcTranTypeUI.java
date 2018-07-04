package nc.ui.pu.m25trantype;

import java.awt.BorderLayout;
import java.awt.Component;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pub.bill.BillEditListener;
import nc.ui.pub.transtype.AbstractTranstypeEditor;
import nc.ui.pub.transtype.EditorContext;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m25trantype.entity.InvcTranTypeVO;
import nc.vo.pu.m25trantype.enumeration.SendControlMode;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.log.Log;

/**
 * 采购发票交易类型扩展属性UI
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>实现交易类型扩展属性编辑器抽象类中的方法
 * </ul>
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version v60
 * @since v60
 * @author zhaoyha
 * @time 2009-5-27 下午01:29:49
 */
public class InvcTranTypeUI extends AbstractTranstypeEditor {

  private BillCardPanel billCardPanel;

  private String pk_group = WorkbenchEnvironment.getInstance().getGroupVO()
      .getPk_group();

  private UIPanel ui = new UIPanel();

  /**
   * InvcTranTypeUI 的构造子
   */
  public InvcTranTypeUI() {
    super();
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pub.transtype.AbstractTranstypeEditor#doAction(nc.ui.pub.transtype.EditorContext)
   */
  @Override
  public void doAction(EditorContext ec) throws BusinessException {
    if (EditorContext.TYPE_BROWSE == ec.getEventtype()
        || EditorContext.TYPE_CANCEL == ec.getEventtype()) {
      if (this.isTransTypeChanged(ec)) {
        this.transTypeExtObject = this.queryTranstypeExtProp(ec);
      }
      this.showTranstypeExtObj(this.transTypeExtObject);
      // 不可编辑
      this.setEditable(false);
      this.context = ec;
    }
    // else if(EditorContext.TYPE_EDIT == ec.getEventtype()){
    // if (this.isTransTypeChanged(ec)) {
    // InvcTranTypeVO vo = (InvcTranTypeVO) this.queryTranstypeExtProp(ec);
    // if("25-06".equals(vo.getVtrantypecode())){
    // this.showTranstypeExtObj(vo);
    // // 不可编辑
    // this.setEditable(false);
    // this.context = ec;
    // }else{
    // super.doAction(ec);
    // }
    // }
    // }
    else {
      super.doAction(ec);
    }
  }

  /**
   * @return billCardPanel
   */
  public BillCardPanel getBillCardPanel() {
    return this.billCardPanel;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pub.transtype.ITranstypeEditor#getEditorPane()
   */
  @Override
  public Component getEditorPane() {
    this.initUI();
    return this.ui;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pub.transtype.ITranstypeEditor#getTransTypeExtObj(nc.ui.pub.transtype.EditorContext)
   */
  @Override
  public Object getTransTypeExtObj(EditorContext context1)
      throws BusinessException {
    InvcTranTypeVO vo =
        (InvcTranTypeVO) this.billCardPanel.getBillData().getHeaderValueVO(
            InvcTranTypeVO.class.getName());
    vo.setVtrantypecode(context1.getTranstype().getPk_billtypecode());
    vo.setPk_group(this.pk_group);
    return vo;
  }

  private void initUI() {
    if (this.billCardPanel != null) {
      return;
    }
    this.billCardPanel = new BillCardPanel();

    this.billCardPanel.loadTemplet("1001Z8PoInvcTranType");

    this.billCardPanel.setEnabled(false);
    this.billCardPanel.setBorder(null);

    this.billCardPanel.addBillEditListenerHeadTail(new BillEditListener() {

      @Override
      public void afterEdit(BillEditEvent e) {
        if (InvcTranTypeVO.ITOARAPMODE.equals(e.getKey())) {
          InvcTranTypeVO vo =
              (InvcTranTypeVO) InvcTranTypeUI.this.getBillCardPanel()
                  .getBillData()
                  .getHeaderValueVO(InvcTranTypeVO.class.getName());
          InvcTranTypeUI.this.afterItoarapmode(vo);
        }
        if (InvcTranTypeVO.BCONSUMEPUR.equals(e.getKey())) {
          InvcTranTypeVO vo =
              (InvcTranTypeVO) InvcTranTypeUI.this.getBillCardPanel()
                  .getBillData()
                  .getHeaderValueVO(InvcTranTypeVO.class.getName());
          InvcTranTypeUI.this.setEditByBconsumepur(vo);
        }
        if (InvcTranTypeVO.BCHECKQUALITY.equals(e.getKey())) {
          InvcTranTypeVO vo =
              (InvcTranTypeVO) InvcTranTypeUI.this.getBillCardPanel()
                  .getBillData()
                  .getHeaderValueVO(InvcTranTypeVO.class.getName());
          if (vo.getItoarapmode().equals(SendControlMode.MODE_NO_CTRL.value())) {
            InvcTranTypeUI.this.billCardPanel.getHeadItem(
                InvcTranTypeVO.BCHECKQUALITY).setValue(null);
          }
          InvcTranTypeUI.this.billCardPanel.getHeadItem(
              InvcTranTypeVO.BCONSUMEPUR).setValue(null);
        }
        if (InvcTranTypeVO.BSENDPAY.equals(e.getKey())) {
          InvcTranTypeUI.this.billCardPanel.getHeadItem(
              InvcTranTypeVO.BCONSUMEPUR).setValue(null);
        }

      }

      @Override
      public void bodyRowChange(BillEditEvent e) {
        // 方法未使用

      }

    });
    this.ui.setLayout(new BorderLayout());
    this.ui.add(this.billCardPanel);

  }

  private boolean isTransTypeChanged(EditorContext ec) {
    if (ec.getTranstype() == null) {
      return false;
    }

    return true;

  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pub.transtype.AbstractTranstypeEditor#clearEditorPane()
   */
  @Override
  protected void clearEditorPane() {
    // 方法未使用
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pub.transtype.AbstractTranstypeEditor#newTranstypeExtProp()
   */
  @Override
  protected void newTranstypeExtProp() throws BusinessException {
    this.billCardPanel.addNew();
    this.billCardPanel.updateValue();
    this.setEditable(true);

  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pub.transtype.AbstractTranstypeEditor#queryTranstypeExtProp(nc.ui.pub.transtype.EditorContext)
   */
  @Override
  protected Object queryTranstypeExtProp(EditorContext ec)
      throws BusinessException {
    if (ec.getTranstype() == null
        || StringUtil.spaceToNull(ec.getTranstype().getPk_billtypecode()) == null) {
      return null;
    }
    InvcTranTypeVO[] returnVos = null;
    try {
      returnVos =
          InvcTranTypeService.queryTranstypeExtProp(" and "
              + InvcTranTypeVO.CTRANTYPEID + "='"
              + ec.getTranstype().getPk_billtypeid() + "'");
    }
    catch (Exception e) {
      // 日志异常
      Log.info(e);
      // 按规范抛出异常
      throw new BusinessException(e);
    }
    if (returnVos == null || returnVos.length == 0) {
      return null;
    }
    return returnVos[0];
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pub.transtype.AbstractTranstypeEditor#setEditable(boolean)
   */
  @Override
  protected void setEditable(boolean isEdit) {
    this.billCardPanel.setEnabled(isEdit);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pub.transtype.AbstractTranstypeEditor#showTranstypeExtObj(java.lang.Object)
   */
  @Override
  protected void showTranstypeExtObj(Object obj) throws BusinessException {
    this.billCardPanel.getBillData().setHeaderValueVO(
        (CircularlyAccessibleValueObject) obj);
    InvcTranTypeVO vo = (InvcTranTypeVO) obj;
    if (null != vo) {
      this.setToArapModeEditable(vo);
    }
  }

  void afterItoarapmode(InvcTranTypeVO vo) {

    this.billCardPanel.getHeadItem(InvcTranTypeVO.BCHECKQUALITY).setValue(null);
    this.billCardPanel.getHeadItem(InvcTranTypeVO.BCONSUMEPUR).setValue(null);
    this.billCardPanel.getHeadItem(InvcTranTypeVO.BSENDPAY).setValue(null);

    if (vo.getItoarapmode().equals(
        SendControlMode.MODE_ENTER_STORE_MATCH.value())) {
      this.billCardPanel.getHeadItem(InvcTranTypeVO.BCHECKQUALITY).setEnabled(
          true);
    }
    else {
      // 不控制
      this.billCardPanel.getHeadItem(InvcTranTypeVO.BCHECKQUALITY).setEnabled(
          false);
      this.billCardPanel.getHeadItem(InvcTranTypeVO.BCONSUMEPUR).setEnabled(
          true);
    }

  }

  void setEditByBconsumepur(InvcTranTypeVO vo) {
    if (vo.getItoarapmode().equals(
        SendControlMode.MODE_ENTER_STORE_MATCH.value())) {
      // 入库匹配时，消耗采购不可选
      this.billCardPanel.getHeadItem(InvcTranTypeVO.BCONSUMEPUR).setValue(null);
      this.billCardPanel.getHeadItem(InvcTranTypeVO.BCONSUMEPUR).setEnabled(
          false);
    }
    else {
      if (vo.getBconsumepur().equals(UFBoolean.TRUE)) {
        this.billCardPanel.getHeadItem(InvcTranTypeVO.BCHECKQUALITY).setValue(
            null);
        this.billCardPanel.getHeadItem(InvcTranTypeVO.BSENDPAY).setValue(null);
      }
    }
  }

  /**
   * 方法功能描述：设置界面状态。 <b>参数说明</b>
   * 
   * @param vo <p>
   * @author zhaoyha
   * @time 2009-6-1 下午06:10:43
   */
  void setToArapModeEditable(InvcTranTypeVO vo) {
    // 根据“传应付控制”设置“是否质量合格检查”按钮是否可用
    if (vo.getItoarapmode().equals(
        SendControlMode.MODE_ENTER_STORE_MATCH.value())) {
      this.billCardPanel.getHeadItem(InvcTranTypeVO.BCHECKQUALITY).setEnabled(
          true);
    }
    else {
      // 不控制
      this.billCardPanel.getHeadItem(InvcTranTypeVO.BCHECKQUALITY).setEnabled(
          false);
      this.billCardPanel.getHeadItem(InvcTranTypeVO.BCONSUMEPUR).setEnabled(
          true);
    }
  }

}
