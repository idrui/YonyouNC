package nc.ui.pu.m21.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.ui.ml.NCLangRes;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单冻结后打印操作不可用
 * </ul>
 * <p>
 * <p>
 * 
 * @version
 * @since 6.0
 * @author guizhw
 * @time 2011-7-14 下午04:32:19
 */

public class PrintAction extends MetaDataBasedPrintAction {

  private static final long serialVersionUID = 781927959578662819L;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    StringBuilder sb = new StringBuilder();
    OrderVO[] vos = this.getSelectedOrderVOs();
    if (null != vos && 1 < vos.length) {
      OrderVO[] cantPrintVOs = this.getFrozenData(vos);
      for (OrderVO vo : cantPrintVOs) {
        sb.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
            "4004030_0", "04004030-0053", null, new String[] {
              vo.getHVO().getVbillcode()
            })/* @res "采购订单{0}已冻结。\n" */);
      }
      if (0 < cantPrintVOs.length) {
        int num = cantPrintVOs.length;
        ExceptionUtils.wrappBusinessException(NCLangRes.getInstance()
            .getStrByID("4004030_0", "04004030-0221", null, new String[] {
              String.valueOf(num), sb.toString()
            })/* 共有{0}张单据不能打印：\n{1} */);
      }
    }
    super.doAction(e);
  }

  // 获取冻结的数据
  public OrderVO[] getFrozenData(OrderVO[] vos) {
    List<OrderVO> frozenlist = new ArrayList<OrderVO>();
    for (OrderVO vo : vos) {
      if (UFBoolean.TRUE.equals(vo.getHVO().getBfrozen())) {
        frozenlist.add(vo);
      }
    }
    OrderVO[] printVOs = frozenlist.toArray(new OrderVO[frozenlist.size()]);
    return printVOs;
  }

  @Override
  public BillManageModel getModel() {
    return (BillManageModel) super.getModel();
  }

  // 获取当前选中行的vo
  public OrderVO[] getSelectedOrderVOs() {
    Object[] objs = this.getModel().getSelectedOperaDatas();
    if (null == objs || 0 == objs.length) {
      return null;
    }
    OrderVO[] vos = new OrderVO[objs.length];
    System.arraycopy(objs, 0, vos, 0, objs.length);
    return vos;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    if (this.getModel().getAppUiState() == AppUiState.ADD
        || this.getModel().getAppUiState() == AppUiState.EDIT) {
      return false;
    }
    Object[] objs = this.getModel().getSelectedOperaDatas();
    if (null != objs && 1 < objs.length) {
      return true;
    }
    OrderVO vo = (OrderVO) this.getModel().getSelectedData();
    return vo != null && UFBoolean.FALSE.equals(vo.getHVO().getBfrozen());
  }
}
