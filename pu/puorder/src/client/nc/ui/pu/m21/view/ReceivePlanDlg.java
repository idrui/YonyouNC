/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-13 下午06:24:57
 */
package nc.ui.pu.m21.view;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.ui.ic.batchcode.ref.BatchRefPane;
import nc.ui.pu.m21.service.OrderReceivePlanModel;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.scale.CardPaneScaleProcessor;
import nc.ui.pubapp.uif2app.view.BatchBillTable;
import nc.ui.uif2.AppEvent;
import nc.ui.uif2.model.RowOperationInfo;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.tool.BillIndex;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.PosEnum;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货计划界面
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-13 下午06:24:57
 */
public class ReceivePlanDlg extends BatchBillTable {

  private static final long serialVersionUID = 5807922318495466962L;

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pubapp.uif2app.view.BatchBillTable#getEditingLineVO(int)
   */
  @Override
  public Object getEditingLineVO(int rowIndex) {
    Object obj =
        this.getBillCardPanel().getBillModel()
            .getBodyValueRowVO(rowIndex, OrderReceivePlanVO.class.getName());
    // String pk_receiveaddress =
    // (String) this.getBillCardPanel().getBodyValueAt(rowIndex,
    // OrderReceivePlanVO.PK_RECEIVEADDRESS + IBillItem.ID_SUFFIX);
    // String vvenddevaddr =
    // (String) this.getBillCardPanel().getBodyValueAt(rowIndex,
    // OrderReceivePlanVO.VVENDDEVADDR + IBillItem.ID_SUFFIX);
    // 暂时不支持
    String pk_receiveaddress =
        (String) this.getBillCardPanel().getBodyValueAt(rowIndex,
            OrderReceivePlanVO.PK_RECEIVEADDRESS);
    String vvenddevaddr =
        (String) this.getBillCardPanel().getBodyValueAt(rowIndex,
            OrderReceivePlanVO.VVENDDEVADDR);
    // 因为取不到值，暂时这样处理
    CircularlyAccessibleValueObject vo =
        (CircularlyAccessibleValueObject) this.getModel().getRow(rowIndex);
    if (StringUtil.isEmptyWithTrim(pk_receiveaddress)) {
      pk_receiveaddress = ((OrderReceivePlanVO) vo).getPk_receiveaddress();
      ((OrderReceivePlanVO) obj).setPk_receiveaddress(pk_receiveaddress);
    }
    if (StringUtil.isEmptyWithTrim(vvenddevaddr)) {
      vvenddevaddr = ((OrderReceivePlanVO) vo).getVvenddevaddr();
      ((OrderReceivePlanVO) obj).setVvenddevaddr(vvenddevaddr);
    }
    return obj;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pubapp.uif2app.view.BatchBillTable#initUI()
   */
  @Override
  public void initUI() {
    super.initUI();
    // 设置精度
    this.setScale();
    this.setBatchCodeRef();
  }

  private boolean haveFollowBill(BillCardPanel panel, int row) {
    UFDouble naccumarrvnum =
        (UFDouble) panel.getBodyValueAt(row, OrderReceivePlanVO.NACCUMARRVNUM);
    if (MathTool.compareTo(naccumarrvnum, UFDouble.ZERO_DBL) > 0) {
      return true;
    }
    UFDouble naccumdevnum =
        (UFDouble) panel.getBodyValueAt(row, OrderReceivePlanVO.NACCUMDEVNUM);
    if (MathTool.compareTo(naccumdevnum, UFDouble.ZERO_DBL) > 0) {
      return true;
    }

    UFDouble naccumstorenum =
        (UFDouble) panel.getBodyValueAt(row, OrderReceivePlanVO.NACCUMSTORENUM);
    if (MathTool.compareTo(naccumstorenum, UFDouble.ZERO_DBL) > 0) {
      return true;
    }

    UFDouble naccumwastnum =
        (UFDouble) panel.getBodyValueAt(row, OrderReceivePlanVO.NACCUMWASTNUM);
    if (MathTool.compareTo(naccumwastnum, UFDouble.ZERO_DBL) > 0) {
      return true;
    }

    UFDouble nbackarrvnum =
        (UFDouble) panel.getBodyValueAt(row, OrderReceivePlanVO.NBACKARRVNUM);
    if (MathTool.compareTo(nbackarrvnum, UFDouble.ZERO_DBL) > 0) {
      return true;
    }
    UFDouble nbackstorenum =
        (UFDouble) panel.getBodyValueAt(row, OrderReceivePlanVO.NBACKSTORENUM);
    if (MathTool.compareTo(nbackstorenum, UFDouble.ZERO_DBL) > 0) {
      return true;
    }

    return false;
  }

  private void setBatchCodeRef() {
    BillCardPanel card = this.getBillCardPanel();
    // 初始化表体批次参照
    BatchRefPane pane = new BatchRefPane();
    card.getBodyItem(OrderItemVO.VBATCHCODE).setComponent(pane);
  }

  /**
   * 方法功能描述：设定可编辑性
   * <p>
   * <b>参数说明</b>
   * 
   * @param panel
   * @param iRow
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-3 下午02:43:08
   */
  private void setBodyCellEditable(BillCardPanel panel, int iRow) {
    // 未录入行号时其他项不可录入
    String crowno = (String) panel.getBodyValueAt(iRow, "crowno");
    boolean bRowNoExisted = !StringUtil.isEmptyWithTrim(crowno);

    // 收货库存组织，计划到货日期，收货仓库，主数量，数量，报价数量
    String[] saEnabledKey =
        new String[] {
          OrderReceivePlanVO.PK_ARRVSTOORG, OrderReceivePlanVO.PK_ARRVSTOORG_V,
          OrderReceivePlanVO.DPLANARRVDATE, OrderReceivePlanVO.PK_RECVSTORDOC,
          OrderReceivePlanVO.NNUM, OrderReceivePlanVO.NASTNUM,
          OrderReceivePlanVO.NQTUNITNUM
        };

    int iLen = saEnabledKey.length;
    for (int i = 0; i < iLen; i++) {
      panel.setCellEditable(iRow, saEnabledKey[i], bRowNoExisted
          & panel.getBodyItem(saEnabledKey[i]).isEdit());
    }

    // 设定批次号的可编辑性

    // 已关闭的行所有项不可编辑
    // 有后续单据的行只能编辑数量
    if (bRowNoExisted) {
      String sBId =
          (String) panel.getBodyValueAt(iRow, OrderReceivePlanVO.PK_ORDER_B);

      OrderVO vo = ((OrderReceivePlanModel) this.getModel()).getOrderVO();
      // Map<String, OrderItemVO> map = AggVOUtil.createItemMap(new OrderVO[] {
      // vo
      // });
      // OrderItemVO voItem = map.get(sBId);
      BillIndex index = new BillIndex(new OrderVO[] {
        vo
      });
      IVOMeta meta = vo.getMetaData().getVOMeta(OrderItemVO.class);
      OrderItemVO voItem = (OrderItemVO) index.get(meta, sBId);
      BillItem[] itemaBody = panel.getBodyShowItems();
      int iItemLen = itemaBody.length;

      // 已关闭的行所有项不可编辑
      if (!EnumActive.ACTIVE.value().equals(voItem.getFisactive())) {
        for (int i = 0; i < iItemLen; i++) {
          panel.setCellEditable(iRow, itemaBody[i].getKey(), false);
        }
      }
      else if (this.haveFollowBill(panel, iRow)) {
        // 有后续单据的行只能编辑数量
        for (int i = 0; i < iItemLen; ++i) {
          String key = itemaBody[i].getKey();
          if (OrderReceivePlanVO.NASTNUM.equals(key)
              || OrderReceivePlanVO.NQTUNITNUM.equals(key)
              || OrderReceivePlanVO.NNUM.equals(key)) {
            continue;
          }
          panel.setCellEditable(iRow, itemaBody[i].getKey(), false);
        }
      }
    }
  }

  private void setBodyScale(BillScaleProcessor scale) {
    // 换算率
    String[] changeRates = new String[] {
      OrderReceivePlanVO.VCHANGERATE, OrderReceivePlanVO.VQTUNITRATE
    };

    // 报价单位数量
    String[] quoteNumkeys = new String[] {
      OrderReceivePlanVO.NQTUNITNUM
    };
    // 业务单位数量
    String[] assistNumkeys = new String[] {
      OrderReceivePlanVO.NASTNUM
    };
    // 主数量
    String[] numkeys =
        new String[] {
          OrderReceivePlanVO.NACCUMARRVNUM, OrderReceivePlanVO.NACCUMDEVNUM,
          OrderReceivePlanVO.NACCUMSTORENUM, OrderReceivePlanVO.NACCUMWASTNUM,
          OrderReceivePlanVO.NBACKARRVNUM, OrderReceivePlanVO.NBACKSTORENUM,
          OrderReceivePlanVO.NNUM, OrderReceivePlanVO.NACCUMRECEIVENUM
        };

    // 换算率精度
    scale.setHslCtlInfo(changeRates, PosEnum.body, null);
    // 报价单位数量精度
    scale.setNumCtlInfo(quoteNumkeys, PosEnum.body, null,
        OrderReceivePlanVO.CQTUNITID, PosEnum.body, null);
    // 业务单位数量精度
    scale.setNumCtlInfo(assistNumkeys, PosEnum.body, null,
        OrderReceivePlanVO.CASTUNITID, PosEnum.body, null);
    // 主单位数量精度
    scale.setNumCtlInfo(numkeys, PosEnum.body, null,
        OrderReceivePlanVO.CUNITID, PosEnum.body, null);
    // 进行计算
    scale.process();
  }

  /**
   * 方法功能描述：设置行号
   * <p>
   * <b>参数说明</b>
   * 
   * @param obj
   * @param index
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-19 下午07:11:09
   */
  private void setCrowno(CircularlyAccessibleValueObject obj, int index) {
    if (null == obj) {
      return;
    }

    // 设置行号
    OrderReceivePlanVO rpVO = (OrderReceivePlanVO) obj;
    String pkOrderB = rpVO.getPk_order_b();
    DefaultConstEnum value =
        new DefaultConstEnum(pkOrderB, rpVO.getCrownobb1());
    this.getBillCardPanel().setBodyValueAt(value, index, OrderItemVO.CROWNO);
  }

  private void setScale() {
    String pk_group = InvocationInfoProxy.getInstance().getGroupId();
    this.setBodyScale(new CardPaneScaleProcessor(pk_group, this.billCardPanel));
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.editor.BatchBillTable#onDataUpdate(nc.ui.uif2.AppEvent)
   */
  @Override
  protected void onDataUpdate(AppEvent event) {
    RowOperationInfo info = (RowOperationInfo) event.getContextObject();
    int index = info.getRowIndexes()[0];
    CircularlyAccessibleValueObject obj =
        (CircularlyAccessibleValueObject) this.getModel().getRow(index);
    // getBillCardPanel().getBillModel().setBodyRowVO(obj, index);
    this.getBillCardPanel().getBillModel()
        .setBodyRowObjectByMetaData(obj, index);

    // 等UAP宋杰打开行的loadLoadRealtionItemValue(row)，这个方法目前为private，否则会产生效率问题。
    this.getBillCardPanel().getBillModel().loadLoadRelationItemValue(index);
    this.getBillCardPanel().getBillModel().execLoadFormulaByRow(index);
    // 设置行号
    this.setCrowno(obj, index);
    // 设置精度
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pubapp.uif2app.view.BatchBillTable#onEdit()
   */
  @Override
  protected void onEdit() {
    super.onEdit();
    int rowCount = this.billCardPanel.getRowCount();
    if (0 == rowCount) {
      return;
    }
    for (int i = 0; i < rowCount; ++i) {
      this.setBodyCellEditable(this.billCardPanel, i);
    }
  }

}
