package nc.ui.pu.m21.rule;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.itf.scmpub.reference.uap.bd.currency.CurrencyInfo;
import nc.ui.pub.bill.BillCardPanel;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.util.PubSysParamUtil;
import nc.vo.scmpub.res.para.NCPara;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>设置币种相关的可编辑性
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-4-22 下午02:19:17
 */
public class EditableByCurrency {
  private BillCardPanel panel;

  public EditableByCurrency(BillCardPanel panel) {
    this.panel = panel;
  }

  public void setEditable(int[] rows) {
    this.panel.getBodyItem("material", OrderItemVO.CCURRENCYID).setEnabled(
        false);
    Object obj =
        this.panel.getHeadTailItem(OrderHeaderVO.CORIGCURRENCYID)
            .getValueObject();
    if (obj == null) {
      return;
    }
    this.setExchangeRateEditable(rows, obj);
    this.setGroupExchgRateEditable(rows, obj);
    this.setGlobalExchgRateEditable(rows, obj);
  }

  private void setExchangeRateEditable(int[] rows, Object obj) {
    boolean editable =
        this.panel.getBodyItem("material", OrderItemVO.NEXCHANGERATE).isEdit();
    for (int row : rows) {
      Object curr = this.panel.getBodyValueAt(row, OrderItemVO.CCURRENCYID);
      if (obj.equals(curr)) {
        this.panel.setCellEditable(row, OrderItemVO.NEXCHANGERATE, false);
      }
      else {
        this.panel.setCellEditable(row, OrderItemVO.NEXCHANGERATE, editable);
      }
    }
  }

  private void setGlobalExchgRateEditable(int[] rows, Object obj) {
    boolean globaleditable =
        this.panel.getBodyItem("material", OrderItemVO.NGLOBALEXCHGRATE)
            .isEdit();
    String nc002 = PubSysParamUtil.getNC002();
    String globaLocalCurrency = CurrencyInfo.getGlobeDefaultCurrtype();
    for (int row : rows) {
      if (NCPara.NC001_CALCULATEBYORIGCURRTYPE.getName().equals(nc002)) {
        if (obj.equals(globaLocalCurrency)) {
          this.panel.setCellEditable(row, OrderItemVO.NGLOBALEXCHGRATE, false);
        }
        else {
          this.panel.setCellEditable(row, OrderItemVO.NGLOBALEXCHGRATE,
              globaleditable);
        }
      }
      else if (NCPara.NC001_CALCULATEBYCURRTYPE.getName().equals(nc002)) {
        Object curr = this.panel.getBodyValueAt(row, OrderItemVO.CCURRENCYID);
        if (curr != null && curr.equals(globaLocalCurrency)) {
          this.panel.setCellEditable(row, OrderItemVO.NGLOBALEXCHGRATE, false);
        }
        else {
          this.panel.setCellEditable(row, OrderItemVO.NGLOBALEXCHGRATE,
              globaleditable);
        }
      }
      else if (NCPara.NC001_NOUSEGLOBALCURRTYPE.getName().equals(nc002)) {
        this.panel.setCellEditable(row, OrderItemVO.NGLOBALEXCHGRATE, false);
      }
    }
  }

  private void setGroupExchgRateEditable(int[] rows, Object obj) {
    boolean groupeditable =
        this.panel.getBodyItem("material",OrderItemVO.NGROUPEXCHGRATE).isEdit();
    String pk_group = InvocationInfoProxy.getInstance().getGroupId();
    String nc001 = PubSysParamUtil.getNC001(pk_group);
    String groupLocalCurrency = CurrencyInfo.getLocalCurrtypeByOrgID(pk_group);
    for (int row : rows) {
      if (NCPara.NC001_CALCULATEBYORIGCURRTYPE.getName().equals(nc001)) {
        if (obj.equals(groupLocalCurrency)) {
          this.panel.setCellEditable(row, OrderItemVO.NGROUPEXCHGRATE, false);
        }
        else {
          this.panel.setCellEditable(row, OrderItemVO.NGROUPEXCHGRATE,
              groupeditable);
        }
      }
      else if (NCPara.NC001_CALCULATEBYCURRTYPE.getName().equals(nc001)) {
        Object curr = this.panel.getBodyValueAt(row, OrderItemVO.CCURRENCYID);
        if (curr != null && curr.equals(groupLocalCurrency)) {
          this.panel.setCellEditable(row, OrderItemVO.NGROUPEXCHGRATE, false);
        }
        else {
          this.panel.setCellEditable(row, OrderItemVO.NGROUPEXCHGRATE,
              groupeditable);
        }
      }
      else if (NCPara.NC001_NOUSEGROUPCURRTYPE.getName().equals(nc001)) {
        this.panel.setCellEditable(row, OrderItemVO.NGROUPEXCHGRATE, false);
      }
    }
  }
}
