/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-21 ����04:00:36
 */
package nc.ui.pu.m21.rule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pu.pub.util.ObjectUtil;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��ճ��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-21 ����04:00:36
 */
public class PasteLineRule {
  private AbstractAppModel model = null;

  private BillCardPanel panel;

  public PasteLineRule() {
    // ��
  }

  public PasteLineRule(BillCardPanel panel, AbstractAppModel model) {
    this.panel = panel;
    this.model = model;
  }

  /**
   * ��������������������ͬ
   * <p>
   * <b>����˵��</b>
   * 
   * @param lastPastedRow
   * @param rowlength
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-21 ����04:23:22
   */
  public void cntLink(int lastPastedRow, int rowlength) {
    Set<Integer> set = new HashSet<Integer>();
    for (int line = 0; line < rowlength; line++) {
      int row = lastPastedRow - line;
      Object ccontractrowid =
          this.panel.getBodyValueAt(row, OrderItemVO.CCONTRACTROWID);
      if (!ObjectUtil.isEmptyWithTrim(ccontractrowid)) {
        set.add(Integer.valueOf(row));
      }
    }

    if (0 == set.size()) {
      return;
    }

    Integer[] rows = set.toArray(new Integer[set.size()]);
    ContractLinker linker =
        new ContractLinker(this.panel, this.model.getContext());
    linker.contractLink(rows, false, true);
  }

  /**
   * ����������������Ҫ��յ��ֶ�
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-21 ����04:05:00
   */
  public List<String> getClearItems() {
    String[] keys =
        new String[] {
          OrderItemVO.PK_ORDER_B, OrderItemVO.NACCUMARRVNUM,
          OrderItemVO.NACCUMSTORENUM, OrderItemVO.NACCUMWASTNUM,
          OrderItemVO.NACCUMINVOICENUM, OrderItemVO.NACCUMINVOICEMNY,
          OrderItemVO.NBACKARRVNUM, OrderItemVO.NBACKSTORENUM,
          OrderItemVO.NCONFIRMNUM, OrderItemVO.NCONFIRMMNY,
          OrderItemVO.NACCUMRPNUM, OrderItemVO.NACCCANCELINVMNY,
          OrderItemVO.NACCUMDEVNUM, OrderItemVO.PK_SRCORDER_B,
          OrderItemVO.DCONFIRMDATE, OrderItemVO.DCORRECTDATE,
          OrderItemVO.VVENDORORDERCODE, OrderItemVO.VVENDORORDERROW,
          OrderItemVO.CHANDLER, OrderItemVO.NFEEMNY, OrderItemVO.NSUPRSNUM,
          OrderItemVO.TS, OrderItemVO.FNEEDPURTYPE, OrderItemVO.FACTPURTYPE,
          OrderItemVO.PK_CENPURULE_B, OrderItemVO.NSENDPLANNUM,
          OrderItemVO.PK_SCHEDULE, OrderItemVO.PK_SCHEDULE_B,
          OrderItemVO.NACCUMPICKUPNUM
        };

    List<String> list = new ArrayList<String>();
    for (String key : keys) {
      list.add(key);
    }

    return list;
  }

  /**
   * ������������������Ĭ��ֵ
   * <p>
   * <b>����˵��</b>
   * 
   * @param lastPastedRow
   * @param rowlength
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-21 ����04:22:49
   */
  public void setDefaultValue(int lastPastedRow, int rowlength) {
    for (int line = 0; line < rowlength; line++) {
      int row = lastPastedRow - line;
      // ճ�������ڵ�ǰѡ����֮ǰ�����У������õ�ǰ����ǰ��
      this.panel.getBillModel().setValueAt(EnumActive.ACTIVE.value(), row,
          OrderItemVO.FISACTIVE);
      this.panel.getBillModel().setValueAt(UFBoolean.FALSE, row,
          OrderItemVO.BARRIVECLOSE);
      this.panel.getBillModel().setValueAt(UFBoolean.FALSE, row,
          OrderItemVO.BSTOCKCLOSE);
      this.panel.getBillModel().setValueAt(UFBoolean.FALSE, row,
          OrderItemVO.BINVOICECLOSE);
      this.panel.getBillModel().setValueAt(UFBoolean.FALSE, row,
          OrderItemVO.BPAYCLOSE);
      this.panel.getBillModel().setValueAt(UFBoolean.FALSE, row,
          OrderItemVO.BRECEIVEPLAN);
    }
  }
}
