package nc.ui.pu.m21transtype;

import java.awt.Component;

import javax.swing.JSplitPane;

import nc.ui.pub.ButtonObject;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UISplitPane;
import nc.ui.pub.transtype.EditorContext;
import nc.ui.pub.transtype.ITranstypeEditor;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.Log;

import org.apache.commons.lang.ArrayUtils;

/**
 * 采购订单交易类型扩展属性UI
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>根据context信息，更新编辑器状态和自定义的按钮状态。
 * <li>设置界面扩展交易类型。
 * <li>取得扩展属性对象。
 * </ul>
 * <p>
 * 
 * @author GGR
 * @time 2009-11-26 下午02:39:12
 */
public class PoTransTypeClientUI implements ITranstypeEditor {
  // 交易类型扩展编辑器上下文
  private EditorContext context = null;

  // 主面板
  protected UISplitPane ivjUIContentPane = null;

  // 左边的面板
  protected PoTransTypeLeftPane ivjUILeftPane = null;

  // 右边的面板
  protected PoTransTypeRightPane ivjUIRightPane = null;

  @Override
  public void doAction(EditorContext context1) throws BusinessException {
    this.context = context1;
    if (context1.getEventtype() == EditorContext.TYPE_NEW) {
      this.showTranstypeExtObj(null);
      this.editTranstypeExtObj(null);
    }
    if (EditorContext.TYPE_BROWSE == context1.getEventtype()
        || EditorContext.TYPE_CANCEL == context1.getEventtype()) {

      PoTransTypeVO returnVos = this.queryTranstypeExtProp(context1);
      this.getUIRightPane().updateStatus(returnVos);
      this.showTranstypeExtObj(returnVos);
    }
    if (EditorContext.TYPE_EDIT == context1.getEventtype()) {
      PoTransTypeVO returnVos = this.queryTranstypeExtProp(context1);
      this.editTranstypeExtObj(returnVos);
    }
  }

  @Override
  public void doButtonAction(ButtonObject bo) throws BusinessException {
    // 未使用

  }

  /**
   * 设置编辑时界面扩展交易类型
   * 
   * @param vo 扩展交易类型属性
   */
  public void editTranstypeExtObj(PoTransTypeVO vo) {
    this.setSelectStatus(vo);
    this.getUIRightPane().setEditable();
    this.getUILeftPane().setEditable();
  }

  @Override
  public Component getEditorPane() {
    return this.getUIContentPane();
  }

  @Override
  public ButtonObject[] getExtButtonObjects() {
    // 未使用
    return null;
  }

  @Override
  public Object getTransTypeExtObj(EditorContext context1)
      throws BusinessException {
    int type = context1.getEventtype();
    PoTransTypeVO vo = new PoTransTypeVO();
    String vtrantypecode = context1.getTranstype().getPk_billtypecode();
    vo.setVtrantypecode(vtrantypecode);
    vo.setCtrantypeid(context1.getTranstype().getPk_billtypeid());
    if (type == EditorContext.TYPE_NEW) {
      this.validateData();
      vo.setStatus(VOStatus.NEW);
    }
    else if (type == EditorContext.TYPE_EDIT
        || type == EditorContext.TYPE_CLEAR) {
      if (type == EditorContext.TYPE_EDIT) {
        this.validateData();
        vo.setStatus(VOStatus.UPDATED);
      }
      else {
        vo.setStatus(VOStatus.DELETED);
      }
      PoTransTypeVO[] tempVO = null;
      try {
        tempVO =
            PoTransTypeService.queryTranstypeExtProp(" and "
                + PoTransTypeVO.CTRANTYPEID + "='"
                + context1.getTranstype().getPk_billtypeid() + "'");
      }
      catch (Exception e) {
        return vo;
      }
      // 已经被删除，出现并发
      if (ArrayUtils.isEmpty(tempVO)) {
        // 直接抛异常，见uap nc.ui.pub.transtype.TranstypeManageHandler
        throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004000_0", "04004000-0088")/*
                                                      * @res
                                                      * "出发并发，请重新查询数据！"
                                                      */);
      }
      vo.setPk_potrantype(tempVO[0].getPk_potrantype());
      vo.setAttributeValue("ts", tempVO[0].getAttributeValue("ts"));
    }
    else if (type == EditorContext.TYPE_CANCEL) {
      // 不做处理
    }
    else {
      // 不做处理
    }
    this.getUIRightPane().getSelectStatus(vo);
    this.getUILeftPane().getSelectStatus(vo);
    if (type == EditorContext.TYPE_NEW) {
      // 新增时做一些特殊处理
      this.validateArriveStore(vo);
    }
    return vo;
  }

  /**
   * 设置界面扩展交易类型
   * 
   * @param vo 扩展交易类型属性
   */
  public void showTranstypeExtObj(PoTransTypeVO vo) {
    this.getUIRightPane().setNullStatus();
    this.getUILeftPane().setNullStatus();
    this.setSelectStatus(vo);
  }

  /**
   * 设置界面扩展交易类型状态
   * 
   * @param vo 扩展交易类型属性
   */
  private void setSelectStatus(PoTransTypeVO vo) {
    if (vo != null) {
      this.getUIRightPane().setSelectStatus(vo);
      this.getUILeftPane().setSelectStatus(vo);
      this.getUILeftPane().setStatusChkBoxEnable(vo, this.context);
    }
  }

  private void validateArriveStore(PoTransTypeVO vo) {
    // 如果是新增,请用户确认一下是否应该勾选到货或入库
    if (!UFBoolean.TRUE.equals(vo.getBarrive())
        && !UFBoolean.TRUE.equals(vo.getBstore())) {
      int answer =
          MessageDialog.showYesNoDlg(
              this.getUILeftPane(),
              null,
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4004000_0", "04004000-0000")/*
                                                * @res
                                                * "未设置到货或入库状态，此交易上的订单将无法到货或入库，是否继续？"
                                                */);
      if (UIDialog.ID_NO == answer) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004000_0", "04004000-0001")/*
                                                                     * @res
                                                                     * "请设置到货或入库状态！"
                                                                     */);
      }
    }
  }

  /**
   * 方法功能描述：检查数据是否符合要求。
   * <p>
   * <b>examples:</b>
   * <p>
   * 使用示例
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @author zhaoyha
   * @param vo
   * @time 2009-6-17 下午04:26:37
   */
  private void validateData() {
    int ibegin = this.getUIRightPane().getCbbBegin().getSelectedIndex();
    int iend = this.getUIRightPane().getCbbEnd().getSelectedIndex();
    if (ibegin >= iend && iend > 0) {
      String message =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0",
              "04004000-0002")/* @res "在途开始状态应该在在途结束状态之前！" */;
      ExceptionUtils.wrappBusinessException(message);
    }
  }

  /**
   * 取得主面板
   * 
   * @return 主面板
   */
  protected UISplitPane getUIContentPane() {
    if (this.ivjUIContentPane == null) {
      this.ivjUIContentPane =
          new UISplitPane(JSplitPane.HORIZONTAL_SPLIT, true,
              this.getUIRightPane(), this.getUILeftPane());
      this.ivjUIContentPane.setName("UIContentPane");
      this.ivjUIContentPane.setDividerLocation(380);
      this.getUIRightPane().setNullStatus();
      this.getUILeftPane().setNullStatus();
    }
    return this.ivjUIContentPane;
  }

  /**
   * 取得右边的面板
   * 
   * @return 右面面板
   */
  protected PoTransTypeRightPane getUILeftPane() {
    if (this.ivjUIRightPane == null) {
      this.ivjUIRightPane = new PoTransTypeRightPane(this.ivjUILeftPane);
    }
    return this.ivjUIRightPane;
  }

  /**
   * 取得左边的面板
   * 
   * @return 左面面板
   */
  protected PoTransTypeLeftPane getUIRightPane() {
    if (this.ivjUILeftPane == null) {
      this.ivjUILeftPane = new PoTransTypeLeftPane();
    }
    return this.ivjUILeftPane;
  }

  protected PoTransTypeVO queryTranstypeExtProp(EditorContext context1)
      throws BusinessException {
    if (context1.getTranstype() == null
        || StringUtil.spaceToNull(context1.getTranstype().getPk_billtypecode()) == null) {
      return null;
    }
    PoTransTypeVO[] returnVos = null;
    try {
      returnVos =
          PoTransTypeService.queryTranstypeExtProp(" and "
              + PoTransTypeVO.CTRANTYPEID + "='"
              + context1.getTranstype().getPk_billtypeid() + "'");
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
}
