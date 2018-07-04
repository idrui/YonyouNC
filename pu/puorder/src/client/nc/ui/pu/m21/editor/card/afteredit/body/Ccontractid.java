/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-29 下午06:28:05
 */
package nc.ui.pu.m21.editor.card.afteredit.body;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.currency.CurrencyRate;
import nc.ui.pu.m21.editor.card.afteredit.RelationCalculate;
import nc.ui.pu.m21.rule.EditableSetter;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.rule.NumValueSetter;
import nc.vo.pu.pub.enumeration.PricePriority;
import nc.vo.pu.pub.util.ObjectUtil;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>合同信息编辑后事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-29 下午06:28:05
 */
public class Ccontractid implements ICardBodyAfterEditEventListener {

  /** 合同面板字段跟采购订单字段的对应map */
  private Map<String, String> ctOrderItemMap;

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener#afterEdit(nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent)
   */
  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    int row = event.getRow();
    BillCardPanel panel = event.getBillCardPanel();
    if (ObjectUtil.isEmptyWithTrim(event.getValue())) {
      this.setValueWhenNull(panel, row);
    }
    else {
      // 见nc.ui.ct.purdaily.ref.CtPuRefModel
      UIRefPane ctPane =
          (UIRefPane) panel.getBodyItem(OrderItemVO.CCONTRACTID).getComponent();
      if (null == ctPane) {
        return;
      }
      // 从合同来的普通字段
      this.setNormalValueFromCt(panel, row, ctPane);

      // 设置报价单位和报价换算率
      this.setQtUnit(ctPane, panel, row);
      // 合同数量
      this.setNumFromCt(ctPane, panel, row);
      // 设置汇率
      this.setnexchangerate(panel, row);
      // 从合同来的单价字段等
      this.setCalValueFromCt(panel, row, ctPane);

    }

    CardEditorHelper editor = new CardEditorHelper(event.getBillCardPanel());
    Integer[] rows = this.getNotNullMaterialRow(editor);
    new EditableSetter(event.getBillCardPanel())
        .setEditableByContract(ArrayUtils.toPrimitive(rows));
  }

  /**
   * 合同面板字段跟采购订单字段的对应map
   * 
   * @return
   */
  private Map<String, String> getCtOrderItemMap() {
    if (this.ctOrderItemMap == null) {
      this.ctOrderItemMap = new HashMap<String, String>();
      this.ctOrderItemMap.put("b.nqtorigprice", OrderItemVO.NQTORIGPRICE);
      this.ctOrderItemMap.put("b.nqtorigtaxprice", OrderItemVO.NQTORIGTAXPRICE);
      this.ctOrderItemMap.put("b.ntaxrate", OrderItemVO.NTAXRATE);
      this.ctOrderItemMap.put("b.nnosubtaxrate", OrderItemVO.NNOSUBTAXRATE);
    }
    return this.ctOrderItemMap;
  }

  private Integer[] getNotNullMaterialRow(CardEditorHelper editor) {
    List<Integer> rowsList = new ArrayList<Integer>();
    for (int i = 0; i < editor.getItemCount(); i++) {
      if (editor.getBodyValue(i, OrderItemVO.PK_MATERIAL) != null) {
        rowsList.add(Integer.valueOf(i));
      }
    }
    Integer[] rows = rowsList.toArray(new Integer[rowsList.size()]);
    return rows;
  }

  /**
   * 从合同面板取值，只要值不同的时候才会覆盖
   * 
   * @param panel
   * @param row
   * @param ctPane
   * @param ctItemKey
   * @return
   */
  private String resetValueFromCt(BillCardPanel panel, int row,
      UIRefPane ctPane, String ctItemKey) {
    Object ctValue = ctPane.getRefModel().getValue(ctItemKey);
    String orderItemKey = this.getCtOrderItemMap().get(ctItemKey);
    if (ctValue != null
        && !ctValue.equals(panel.getBodyValueAt(row, orderItemKey))) {
      panel.setBodyValueAt(ctValue, row, orderItemKey);
    }
    return orderItemKey;
  }

  /**
   * 设置从合同来的单价、税率等字段
   * 
   * @param panel
   * @param row
   * @param ctPane
   */
  private void setCalValueFromCt(BillCardPanel panel, int row, UIRefPane ctPane) {
    String pk_org =
        (String) panel.getHeadItem(OrderItemVO.PK_ORG).getValueObject();
    PricePriority pricePriority = PUSysParamUtil.getPO28(pk_org);
    // 联动计算的key，越往后优先级越高
    String changekey = null;
    changekey = this.resetValueFromCt(panel, row, ctPane, "b.nnosubtaxrate");// 不可抵扣税率
    changekey = this.resetValueFromCt(panel, row, ctPane, "b.ntaxrate");// 税率
    if (PricePriority.TAXPRICE_PRIOR_TO_PRICE.equals(pricePriority)) {
      changekey =
          this.resetValueFromCt(panel, row, ctPane, "b.nqtorigtaxprice");// 含税单价
    }
    else {
      changekey = this.resetValueFromCt(panel, row, ctPane, "b.nqtorigprice");// 无税单价
    }

    if (changekey != null) {
      RelationCalculate rc = new RelationCalculate();
      rc.calculate(panel, new int[] {
        row
      }, changekey);
    }
  }

  /**
   * 折本汇率
   * 
   * @param panel
   * @param row
   */
  private void setnexchangerate(BillCardPanel panel, int row) {
    String ccurrencytype =
        (String) panel.getBodyValueAt(row, OrderItemVO.CORIGCURRENCYID);
    UFDate date =
        (UFDate) panel.getHeadItem(OrderItemVO.DBILLDATE).getValueObject();
    if (StringUtil.isEmptyWithTrim(ccurrencytype)) {
      ccurrencytype =
          (String) panel.getHeadItem(OrderHeaderVO.CORIGCURRENCYID)
              .getValueObject();
    }
    UFDouble rate =
        CurrencyRate.getCurrencyRateByOrg(
            (String) panel.getBodyValueAt(row, OrderItemVO.PK_PSFINANCEORG),
            ccurrencytype,
            (String) panel.getBodyValueAt(row, OrderItemVO.CCURRENCYID), date);
    panel.setBodyValueAt(rate, row, OrderItemVO.NEXCHANGERATE);
  }

  /**
   * 从合同来的普通字段值赋值
   * 
   * @param panel
   * @param row
   * @param ctPane
   */
  private void setNormalValueFromCt(BillCardPanel panel, int row,
      UIRefPane ctPane) {
    nc.ui.bd.ref.AbstractRefModel model = ctPane.getRefModel();
    String ccontractid = (String) model.getValue("b.pk_ct_pu");
    String ccontractrowid = (String) model.getValue("b.pk_ct_pu_b");
    String vcontractcode = (String) model.getValue("a.vbillcode");
    String ccurrencytype = (String) model.getValue("a.corigcurrencyid");
    String cqpbaseschemeid = (String) model.getValue("b.cqpbaseschemeid");// 优质优价方案

    panel.setBodyValueAt(ccontractid, row, OrderItemVO.CCONTRACTID);
    panel.setBodyValueAt(ccontractrowid, row, OrderItemVO.CCONTRACTROWID);
    panel.setBodyValueAt(vcontractcode, row, OrderItemVO.VCONTRACTCODE);
    panel.setBodyValueAt(ccurrencytype, row, OrderItemVO.CORIGCURRENCYID);
    panel.setBodyValueAt(cqpbaseschemeid, row, OrderItemVO.CQPBASESCHEMEID);// 优质优价方案
  }

  /**
   * 取合同数量
   * 
   * @param ctPane
   * @param panel
   * @param row
   */
  private void setNumFromCt(UIRefPane ctPane, BillCardPanel panel, int row) {
    // 已有数量，不处理
    Object norgnum = panel.getBodyValueAt(row, OrderItemVO.NNUM);
    if (norgnum != null) {
      return;
    }

    UFDouble nctnum = (UFDouble) ctPane.getRefModel().getValue("b.nnum");
    UFDouble nctordernum =
        (UFDouble) ctPane.getRefModel().getValue("b.nordnum");
    UFDouble nnum = MathTool.sub(nctnum, nctordernum);
    // 理论上不可能出现此情况，除非逻辑错误
    if (MathTool.greaterThan(nnum, UFDouble.ZERO_DBL)) {
      panel.setBodyValueAt(nnum, row, OrderItemVO.NNUM);
      CardEditorHelper helper = new CardEditorHelper(panel);
      NumValueSetter numvalue = new NumValueSetter(helper);
      numvalue.setNastnum(new int[] {
        row
      });

    }
  }

  private void setQtUnit(UIRefPane ctPane, BillCardPanel panel, int row) {
    String cqtunitid = (String) ctPane.getRefModel().getValue("b.cqtunitid");
    String vqtunitrate =
        (String) ctPane.getRefModel().getValue("b.vqtunitrate");
    if (cqtunitid != null) {
      panel.setBodyValueAt(cqtunitid, row, OrderItemVO.CQTUNITID);
    }
    if (cqtunitid != null && vqtunitrate != null) {
      String oldrate =
          (String) panel.getBodyValueAt(row, OrderItemVO.VQTUNITRATE);
      UFDouble norgnum = (UFDouble) panel.getBodyValueAt(row, OrderItemVO.NNUM);
      panel.setBodyValueAt(vqtunitrate, row, OrderItemVO.VQTUNITRATE);
      if (!vqtunitrate.equals(oldrate) && norgnum != null) {
        CardEditorHelper helper = new CardEditorHelper(panel);
        NumValueSetter numvalue = new NumValueSetter(helper);
        numvalue.setNastnum(new int[] {
          row
        });
      }
    }

  }

  /**
   * 方法功能描述：值为空时设置可编辑性和合同值
   * <p>
   * <b>参数说明</b>
   * 
   * @param panel
   * @param row
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-30 上午11:50:32
   */
  private void setValueWhenNull(BillCardPanel panel, int row) {
    panel.setCellEditable(row, OrderItemVO.CCONTRACTID,
        panel.getBodyItem(OrderItemVO.CCONTRACTID).isEdit());
    // 主无税单价
    panel.setCellEditable(row, OrderItemVO.NORIGPRICE,
        panel.getBodyItem(OrderItemVO.NORIGPRICE).isEdit());
    // 无税单价
    panel.setCellEditable(row, OrderItemVO.NQTORIGPRICE,
        panel.getBodyItem(OrderItemVO.NQTORIGPRICE).isEdit());
    // 主含税单价
    panel.setCellEditable(row, OrderItemVO.NORIGTAXPRICE,
        panel.getBodyItem(OrderItemVO.NORIGTAXPRICE).isEdit());
    // 含税单价
    panel.setCellEditable(row, OrderItemVO.NQTORIGTAXPRICE,
        panel.getBodyItem(OrderItemVO.NQTORIGTAXPRICE).isEdit());
    // 主无税净价
    panel.setCellEditable(row, OrderItemVO.NORIGNETPRICE,
        panel.getBodyItem(OrderItemVO.NORIGNETPRICE).isEdit());
    // 无税净价
    panel.setCellEditable(row, OrderItemVO.NQTORIGNETPRICE,
        panel.getBodyItem(OrderItemVO.NQTORIGNETPRICE).isEdit());
    // 无税金额
    panel.setCellEditable(row, OrderItemVO.NORIGMNY,
        panel.getBodyItem(OrderItemVO.NORIGMNY).isEdit());

    panel.setBodyValueAt(null, row, OrderItemVO.CCONTRACTID);
    panel.setBodyValueAt(null, row, OrderItemVO.CCONTRACTROWID);
    panel.setBodyValueAt(null, row, OrderItemVO.VCONTRACTCODE);
  }
}
