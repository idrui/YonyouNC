/**
 * 
 */
package nc.ui.pu.m20trantype;

import java.awt.BorderLayout;
import java.awt.Component;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.ui.pu.mtrantype.TranTypeService;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pub.bill.BillEditListener;
import nc.ui.pub.transtype.AbstractTranstypeEditor;
import nc.ui.pub.transtype.EditorContext;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m20trantype.entity.BuyrTranTypeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pubapp.pattern.log.Log;

/**
 * 请购安排交易类型扩展属性UI
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version
 * @since
 * @author luojw
 * @time 2014-6-18 下午8:17:09
 */
public class BuyrTranTypeUI extends AbstractTranstypeEditor {

  private BillCardPanel billCardPanel;

  private String pk_group = WorkbenchEnvironment.getInstance().getGroupVO()
      .getPk_group();

  private UIPanel ui = new UIPanel();

  public BuyrTranTypeUI() {
    super();
  }

  /**
   * @return the billCardPanel
   */
  public BillCardPanel getBillCardPanel() {
    return this.billCardPanel;
  }

  /*
   * 父类方法重写
   * @see nc.ui.pub.transtype.ITranstypeEditor#getEditorPane()
   */
  @Override
  public Component getEditorPane() {
    this.initUI();
    return this.ui;
  }

  /*
   * 父类方法重写
   * @see
   * nc.ui.pub.transtype.ITranstypeEditor#getTransTypeExtObj(nc.ui.pub.transtype
   * .EditorContext)
   */
  @Override
  public Object getTransTypeExtObj(EditorContext context1)
      throws BusinessException {
    BuyrTranTypeVO vo =
        (BuyrTranTypeVO) this.billCardPanel.getBillData().getHeaderValueVO(
            BuyrTranTypeVO.class.getName());
    vo.setVtrantypecode(context1.getTranstype().getPk_billtypecode());
    vo.setPk_group(this.pk_group);
    return vo;
  }

  /**
   * @param billCardPanel the billCardPanel to set
   */
  public void setBillCardPanel(BillCardPanel billCardPanel) {
    this.billCardPanel = billCardPanel;
  }

  private void initUI() {
    if (this.billCardPanel != null) {
      return;
    }
    this.billCardPanel = new BillCardPanel();

    this.billCardPanel.loadTemplet("1001Z8PoBuyrTranType");

    this.billCardPanel.setEnabled(false);
    this.billCardPanel.setBorder(null);

    this.billCardPanel.addBillEditListenerHeadTail(new BillEditListener() {

      @Override
      public void afterEdit(BillEditEvent e) {
        if (BuyrTranTypeVO.BNEEDARRANGE.equals(e.getKey())) {
          BuyrTranTypeVO vo =
              (BuyrTranTypeVO) BuyrTranTypeUI.this.getBillCardPanel()
                  .getBillData()
                  .getHeaderValueVO(BuyrTranTypeVO.class.getName());
          BuyrTranTypeUI.this.setEditByBneedprayarrange(vo);
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

  /*
   * 父类方法重写
   * @see nc.ui.pub.transtype.AbstractTranstypeEditor#clearEditorPane()
   */
  @Override
  protected void clearEditorPane() {
    // TODO Auto-generated method stub

  }

  /*
   * 父类方法重写
   * @see nc.ui.pub.transtype.AbstractTranstypeEditor#newTranstypeExtProp()
   */
  @Override
  protected void newTranstypeExtProp() throws BusinessException {
    this.billCardPanel.addNew();
    this.billCardPanel.updateValue();
    this.setEditable(true);
  }

  /*
   * 父类方法重写
   * @see
   * nc.ui.pub.transtype.AbstractTranstypeEditor#queryTranstypeExtProp(nc.ui
   * .pub.transtype.EditorContext)
   */
  @Override
  protected Object queryTranstypeExtProp(EditorContext ec)
      throws BusinessException {
    if (ec.getTranstype() == null
        || StringUtil.spaceToNull(ec.getTranstype().getPk_billtypecode()) == null) {
      return null;
    }
    BuyrTranTypeVO[] returnVos = null;
    try {
      returnVos =
          TranTypeService.queryTranstypeExtProp(BuyrTranTypeVO.class, " and "
              + BuyrTranTypeVO.CTRANTYPEID + "='"
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

  /*
   * 父类方法重写
   * @see nc.ui.pub.transtype.AbstractTranstypeEditor#setEditable(boolean)
   */
  @Override
  protected void setEditable(boolean isEdit) {
    this.billCardPanel.setEnabled(isEdit);
  }

  /*
   * 父类方法重写
   * @see
   * nc.ui.pub.transtype.AbstractTranstypeEditor#showTranstypeExtObj(java.lang
   * .Object)
   */
  @Override
  protected void showTranstypeExtObj(Object obj) throws BusinessException {
    this.billCardPanel.getBillData().setHeaderValueVO(
        (CircularlyAccessibleValueObject) obj);
    BuyrTranTypeVO vo = (BuyrTranTypeVO) obj;
    if (null != vo) {
      this.setToArapModeEditable(vo);
    }
  }

  void setEditByBneedprayarrange(BuyrTranTypeVO vo) {
    /*
     * if (vo.getBneedprayarrange().equals(
     * SendControlMode.MODE_ENTER_STORE_MATCH.value())) {
     * // 入库匹配时，消耗采购不可选
     * this.billCardPanel.getHeadItem(InvcTranTypeVO.BCONSUMEPUR)
     * .setValue(null);
     * this.billCardPanel.getHeadItem(InvcTranTypeVO.BCONSUMEPUR)
     * .setEnabled(false);
     * } else {
     * if (vo.getBneedprayarrange().equals(UFBoolean.TRUE)) {
     * this.billCardPanel.getHeadItem(InvcTranTypeVO.BCHECKQUALITY)
     * .setValue(null);
     * this.billCardPanel.getHeadItem(InvcTranTypeVO.BSENDPAY)
     * .setValue(null);
     * }
     * }
     */
  }

  void setToArapModeEditable(BuyrTranTypeVO vo) {
    // 根据“传应付控制”设置“是否质量合格检查”按钮是否可用
    /*
     * if (vo.getItoarapmode().equals(
     * SendControlMode.MODE_ENTER_STORE_MATCH.value())) {
     * this.billCardPanel.getHeadItem(InvcTranTypeVO.BCHECKQUALITY)
     * .setEnabled(true);
     * } else {
     * // 不控制
     * this.billCardPanel.getHeadItem(InvcTranTypeVO.BCHECKQUALITY)
     * .setEnabled(false);
     * this.billCardPanel.getHeadItem(InvcTranTypeVO.BCONSUMEPUR)
     * .setEnabled(true);
     * }
     */
  }

}
