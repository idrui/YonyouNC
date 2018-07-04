/**
 * $�ļ�˵��$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-14 ����02:36:16
 */
package nc.ui.pu.m21.editor.card.afteredit.body;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import nc.ui.pu.m21.service.OrderReceivePlanModel;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pu.m21.pub.OrderReceivePlanUtils;
import nc.vo.pu.m21.rule.RPRelationCalculate;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.tool.BillIndex;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�����ƻ��кű༭���¼�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-14 ����02:36:16
 */
public class RPRowNo implements ICardBodyAfterEditEventListener {

  private OrderReceivePlanModel model;

  private BillCardPanel panel;

  public RPRowNo(OrderReceivePlanModel model) {
    this.model = model;
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener#afterEdit(nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent)
   */
  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    this.panel = event.getBillCardPanel();

    // ѡ�е���ID
    UIRefPane refPaneRowNo =
        (UIRefPane) this.panel.getBodyItem("crowno").getComponent();
    String[] saBId = refPaneRowNo.getRefPKs();
    if (ArrayUtils.isEmpty(saBId)) {
      this.panel.setBodyValueAt(event.getOldValue(), event.getRow(), "crowno");
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0073")/*
                                                                   * @res
                                                                   * "¼����кŲ�����"
                                                                   */);
    }
    boolean oldneedCalFlag = this.panel.getBillModel().isNeedCalculate();
    this.panel.getBillModel().setNeedCalculate(false);

    int iVOLen = saBId.length;
    int iBeginRow = event.getRow();

    Object beginRowId =
        this.panel.getBodyValueAt(iBeginRow, OrderReceivePlanVO.PK_ORDER_BB1);

    // ������
    int[] iaRow = this.getRowsAfterMultiSelect(iBeginRow, iVOLen);

    // �õ��������в�������
    Map<Integer, OrderItemVO> mapItem = new HashMap<Integer, OrderItemVO>();
    OrderReceivePlanVO[] voaRP = this.getRPVOsAfterRowNo(event, saBId, mapItem);
    if (beginRowId == null) {
      this.model.updateLine(iBeginRow, voaRP[0]);
    }
    else {
      try {
        this.model.delLine(iBeginRow);
      }
      catch (Exception e) {
        ExceptionUtils.wrappException(e);
      }
      this.model.insertLines(iBeginRow, new OrderReceivePlanVO[] {
        voaRP[0]
      });
    }

    if (voaRP.length > 1) {
      OrderReceivePlanVO[] insertRP = new OrderReceivePlanVO[voaRP.length - 1];
      System.arraycopy(voaRP, 1, insertRP, 0, insertRP.length);
      this.model.insertLines(iBeginRow + 1, insertRP);
    }

    // ʹ��model.updateLine��ַ��ʽ���ش�����ʱ��������
    // model.insertLines(iBeginRow, voaRP);

    // ����������ֵ
    this.execLoadValue(iaRow, mapItem, voaRP);

    this.panel.getBillModel().execLoadFormula();

    for (int i = 0; i < iaRow.length; ++i) {
      // �趨ĳ�еĿɱ༭��
      this.setBodyCellEditable(iaRow[i]);
      // this.panel.getBillModel().loadLoadRelationItemValue(iaRow[i],
      // OrderReceivePlanVO.PK_ARRVSTOORG_V);
      // this.panel.getBillModel().loadLoadRelationItemValue(iaRow[i],
      // OrderReceivePlanVO.PK_MATERIAL);
    }
    this.panel.getBillModel().setNeedCalculate(oldneedCalFlag);
  }

  /**
   * @return model
   */
  public OrderReceivePlanModel getModel() {
    return this.model;
  }

  /**
   * @param model
   *          Ҫ���õ� model
   */
  public void setModel(OrderReceivePlanModel model) {
    this.model = model;
  }

  /**
   * ����������������ʾ�����ƻ�����
   * <p>
   * <b>����˵��</b>
   * 
   * @param iaRow
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-15 ����04:10:44
   */
  private void execLoadValue(int[] iaRow, Map<Integer, OrderItemVO> mapItem,
      OrderReceivePlanVO[] voaRP) {
    HashSet<String> tmpCodes = new HashSet<String>();
    if (this.panel.getRowCount() >= 1) {
      tmpCodes =
          this.getArrayNotNull(this.panel, 0, this.panel.getRowCount() - 1);
    }
    for (int i = 0; i < iaRow.length; ++i) {
      int iRow = iaRow[i];
      OrderItemVO itemVO = mapItem.get(Integer.valueOf(iRow));
      if (null == itemVO) {
        continue;
      }
      // // �к�
      this.panel.setBodyValueAt(itemVO.getCrowno(), iRow, "crowno");
      this.panel.setBodyValueAt(itemVO.getPk_order_b(), iRow,
          OrderReceivePlanVO.PK_ORDER_B);
      //
      // // ʹ��model.insetLinesû�н��ջ������֯����ֵ����ʱ��������
      // this.panel.setBodyValueAt(itemVO.getPk_arrvstoorg(), iRow,
      // OrderReceivePlanVO.PK_ARRVSTOORG);
      // this.panel.setBodyValueAt(itemVO.getPk_arrvstoorg_v(), iRow,
      // OrderReceivePlanVO.PK_ARRVSTOORG_V);
      // // model.updateLine����ַû������
      // this.panel.setBodyValueAt(itemVO.getPk_receiveaddress(), iRow,
      // OrderReceivePlanVO.PK_RECEIVEADDRESS);
      // this.panel.setBodyValueAt(itemVO.getVvenddevaddr(), iRow,
      // OrderReceivePlanVO.VVENDDEVADDR);
      //
      // this.panel.setBodyValueAt(itemVO.getPk_material(), iRow,
      // OrderReceivePlanVO.PK_MATERIAL);
      // this.panel.setBodyValueAt(itemVO.getPk_srcmaterial(), iRow,
      // OrderReceivePlanVO.PK_SRCMATERIAL);

      // �����ƻ���
      if (tmpCodes != null) {
        String[] bufCodes = tmpCodes.toArray(new String[0]);
        String plancode =
            this.setPlanCode(this.panel, iRow, voaRP[i], bufCodes);
        if (plancode != null) {
          tmpCodes.add(plancode);
        }
      }

    }
  }

  /**
   * ���������������õ�ĳЩ�еĵ����ƻ��ŵ�����,���˿ռ��ظ�ֵ
   * <p>
   * <b>����˵��</b>
   * 
   * @param cardPanel
   * @param iBeginRow
   * @param iEndRow
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-17 ����10:00:23
   */
  private HashSet<String> getArrayNotNull(BillCardPanel cardPanel,
      int iBeginRow, int iEndRow) {
    if (iBeginRow > iEndRow) {
      return null;
    }

    HashSet<String> set = new HashSet<String>();
    for (int i = iBeginRow; i <= iEndRow; ++i) {
      String oValue =
          (String) cardPanel.getBodyValueAt(i, OrderReceivePlanVO.VBILLCODE);
      if (!StringUtil.isEmptyWithTrim(oValue)) {
        set.add(oValue);
      }
    }

    return set;

  }

  /**
   * ���������������õ�������ĳBID����Ӧ�ı���Ӧ¼��ĵ����ƻ�����
   * <p>
   * <b>����˵��</b>
   * 
   * @param itemVO
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-14 ����05:47:28
   */
  private UFDouble getResidualRPNum(BillCardPanel cardPanel, OrderItemVO itemVO) {
    if (null == itemVO) {
      return UFDouble.ZERO_DBL;
    }

    UFDouble nnum = itemVO.getNnum();
    if (0 == MathTool.compareTo(nnum, UFDouble.ZERO_DBL)) {
      return UFDouble.ZERO_DBL;
    }

    // �������ۼ��ջ��ƻ�����
    UFDouble dUISumRPNumForBId = UFDouble.ZERO_DBL;
    for (int i = 0; i < cardPanel.getRowCount(); ++i) {
      if (itemVO.getPk_order_b().equals(
          cardPanel.getBodyValueAt(i, OrderReceivePlanVO.PK_ORDER_B))) {
        dUISumRPNumForBId =
            MathTool
                .add(dUISumRPNumForBId, (UFDouble) cardPanel.getBodyValueAt(i,
                    OrderReceivePlanVO.NNUM));
      }
    }

    if (MathTool.compareTo(dUISumRPNumForBId, nnum) > 0) {
      return UFDouble.ZERO_DBL;
    }

    return MathTool.sub(nnum, dUISumRPNumForBId);
  }

  private int[] getRowsAfterMultiSelect(int iCurRow, int iSelectedLen) {

    int selectedLen = iSelectedLen <= 1 ? 1 : iSelectedLen;
    // ������
    int[] iaRow = new int[selectedLen];
    for (int i = 0; i < selectedLen; i++) {
      iaRow[i] = iCurRow + i;
    }

    return iaRow;
  }

  /**
   * ���������������кŵ�afterEdit���õ������ƻ�VO
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-14 ����04:26:29
   */
  private OrderReceivePlanVO[] getRPVOsAfterRowNo(CardBodyAfterEditEvent event,
      String[] saBId, Map<Integer, OrderItemVO> rowMap) {
    if (ArrayUtils.isEmpty(saBId)) {
      return null;
    }

    OrderVO orderVO = this.getModel().getOrderVO();
    int length = saBId.length;
    // ��صı���
    OrderItemVO[] voaSelectedItem = new OrderItemVO[length];

    // Map<String, OrderItemVO> itemMap = AggVOUtil.createItemMap(new OrderVO[]
    // {
    // orderVO
    // });
    BillIndex index = new BillIndex(new OrderVO[] {
      orderVO
    });
    IVOMeta meta = orderVO.getMetaData().getVOMeta(OrderItemVO.class);
    for (int i = 0; i < length; ++i) {
      // voaSelectedItem[i] = itemMap.get(saBId[i]);
      voaSelectedItem[i] = (OrderItemVO) index.get(meta, saBId[i]);
    }
    // Ϊ��ѡ��׼��
    int iSelectRow = event.getRow();
    int iSelect = iSelectRow;

    int iVOLen = voaSelectedItem.length;
    OrderReceivePlanVO[] voaRP = new OrderReceivePlanVO[iVOLen];

    RPRelationCalculate calc = new RPRelationCalculate();
    for (int i = 0; i < iVOLen; ++i) {
      voaRP[i] = new OrderReceivePlanVO();

      // ���ж�Ӧ����
      OrderItemVO voItem = voaSelectedItem[i];
      rowMap.put(Integer.valueOf(iSelect), voItem);
      // �ɹ�����
      voaRP[i].setPk_order(voItem.getPk_order());
      // �ɹ�������ϸ_����
      voaRP[i].setPk_order_b(voItem.getPk_order_b());
      // �ɹ���֯
      voaRP[i].setPk_org(voItem.getPk_org());
      voaRP[i].setPk_org_v(voItem.getPk_org_v());
      // ��������
      voaRP[i].setPk_group(voItem.getPk_group());
      // ����
      voaRP[i].setPk_material(voItem.getPk_material());
      // ������Ϣ
      voaRP[i].setPk_srcmaterial(voItem.getPk_srcmaterial());
      // �ջ������֯
      voaRP[i].setPk_arrvstoorg(voItem.getPk_arrvstoorg());
      voaRP[i].setPk_arrvstoorg_v(voItem.getPk_arrvstoorg_v());
      voaRP[i].setPk_flowstockorg(voItem.getPk_flowstockorg());
      voaRP[i].setPk_flowstockorg_v(voItem.getPk_flowstockorg_v());
      // �ջ��ֿ�
      voaRP[i].setPk_recvstordoc(voItem.getPk_recvstordoc());
      // ���μƻ��ջ�����
      UFDouble dPlanNum =
          this.getResidualRPNum(event.getBillCardPanel(), voItem);
      // ������ Ĭ��Ϊ������δ���ɵ����ƻ�������
      voaRP[i].setNnum(dPlanNum);
      // ����λ
      voaRP[i].setCunitid(voItem.getCunitid());
      // ��λ
      voaRP[i].setCastunitid(voItem.getCastunitid());
      // ���۵�λ
      voaRP[i].setCqtunitid(voItem.getCqtunitid());
      // ������
      voaRP[i].setVchangerate(voItem.getVchangerate());
      // ���۵�λ������
      voaRP[i].setVqtunitrate(voItem.getVqtunitrate());
      // ���ɸ�������
      voaRP[i].setVfree1(voItem.getVfree1());
      voaRP[i].setVfree2(voItem.getVfree2());
      voaRP[i].setVfree3(voItem.getVfree3());
      voaRP[i].setVfree4(voItem.getVfree4());
      voaRP[i].setVfree5(voItem.getVfree5());
      voaRP[i].setVfree6(voItem.getVfree6());
      voaRP[i].setVfree7(voItem.getVfree7());
      voaRP[i].setVfree8(voItem.getVfree8());
      voaRP[i].setVfree9(voItem.getVfree9());
      voaRP[i].setVfree10(voItem.getVfree10());
      // ���κ�����
      voaRP[i].setPk_batchcode(voItem.getPk_batchcode());
      // ���κ�
      voaRP[i].setVbatchcode(voItem.getVbatchcode());
      // ����
      voaRP[i].setFisactive(voItem.getFisactive());
      // �Ƿ���Ʒ
      voaRP[i].setBlargess(voItem.getBlargess());
      // �ջ���ַ
      voaRP[i].setPk_receiveaddress(voItem.getPk_receiveaddress());
      // �ջ��ص�
      voaRP[i].setCdevaddrid(voItem.getCdevaddrid());
      // �ջ�����
      voaRP[i].setCdevareaid(voItem.getCdevareaid());
      // ��Ӧ�̷�����ַ
      voaRP[i].setVvenddevaddr(voItem.getVvenddevaddr());
      // ��Ӧ�̷����ص�
      voaRP[i].setCvenddevaddrid(voItem.getCvenddevaddrid());
      // ��Ӧ�̷�������
      voaRP[i].setCvenddevareaid(voItem.getCvenddevareaid());
      // by luojw �����е��ۼ��������
      // // �ۼ���������
      // voaRP[i].setNaccumdevnum(voItem.getNaccumdevnum());
      // // ����ۼ�ִ������
      // // �ۼƵ�������
      // voaRP[i].setNaccumarrvnum(voItem.getNaccumarrvnum());
      // // �ۼ��������
      // voaRP[i].setNaccumstorenum(voItem.getNaccumstorenum());
      // // �ۼ�;������
      // voaRP[i].setNaccumwastnum(voItem.getNaccumwastnum());
      // // �ۼ��˻�����
      // voaRP[i].setNbackarrvnum(voItem.getNbackarrvnum());
      // // �ۼ��˿�����
      // voaRP[i].setNbackstorenum(voItem.getNbackstorenum());

      // �к�
      voaRP[i].setCrownobb1(voItem.getCrowno());

      // ������ǵ�һ�У��������������������㡣����ֱ�Ӹ�ֵ!
      if (MathTool.compareTo(dPlanNum, voItem.getNnum()) != 0) {
        calc.calculate(voaRP[i], OrderReceivePlanVO.NNUM);
      }
      else {
        // ����
        voaRP[i].setNastnum(voItem.getNastnum());
        // ��������
        voaRP[i].setNqtunitnum(voItem.getNqtunitnum());
      }

      iSelect++;
    }

    // ѡ���еĵ����ƻ��Ų���
    voaRP[0].setVbillcode((String) event.getBillCardPanel().getBodyValueAt(
        iSelectRow, OrderReceivePlanVO.VBILLCODE));
    return voaRP;
  }

  /**
   * ���������������趨ĳ�еĿɱ༭��
   * <p>
   * <b>����˵��</b>
   * 
   * @param panel
   * @param iRow
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-17 ����09:09:55
   */
  private void setBodyCellEditable(int iRow) {
    // δ¼���к�ʱ�������¼��
    String crowno =
        (String) this.panel.getBodyValueAt(iRow, OrderReceivePlanVO.CROWNOBB1);
    boolean bRowNoExisted = !StringUtil.isEmptyWithTrim(crowno);
    // �ջ������֯���ƻ��������ڣ��ջ��ֿ⣬������
    String[] saEnabledKey =
        new String[] {
          OrderReceivePlanVO.PK_ARRVSTOORG, OrderReceivePlanVO.DPLANARRVDATE,
          OrderReceivePlanVO.PK_RECVSTORDOC, OrderReceivePlanVO.NNUM
        };

    int iLen = saEnabledKey.length;
    for (int i = 0; i < iLen; i++) {
      this.panel.setCellEditable(iRow, saEnabledKey[i], bRowNoExisted
          & this.panel.getBodyItem(saEnabledKey[i]).isEdit());
    }

    // �趨���κŵĿɱ༭��

    // �ѹرյ���������ɱ༭
    // �к������ݵ���ֻ�ܱ༭����
    if (bRowNoExisted) {
      String sBId =
          (String) this.panel.getBodyValueAt(iRow,
              OrderReceivePlanVO.PK_ORDER_B);
      OrderVO vo = this.model.getOrderVO();
      // Map<String, OrderItemVO> map = AggVOUtil.createItemMap(new OrderVO[] {
      // vo
      // });
      BillIndex index = new BillIndex(new OrderVO[] {
        vo
      });
      IVOMeta meta = vo.getMetaData().getVOMeta(OrderItemVO.class);
      // OrderItemVO voItem = map.get(sBId);
      OrderItemVO voItem = (OrderItemVO) index.get(meta, sBId);
      BillItem[] itemaBody = this.panel.getBodyShowItems();
      int iItemLen = itemaBody.length;

      // �ѹرյ���������ɱ༭
      if (!voItem.getFisactive().equals(EnumActive.ACTIVE.value())) {
        for (int i = 0; i < iItemLen; i++) {
          this.panel.setCellEditable(iRow, itemaBody[i].getKey(), false);
        }
      }
      else {// �к������ݵ���ֻ�ܱ༭����
        //
      }
    }
  }

  /**
   * �����������������õ����ƻ���
   * <p>
   * <b>����˵��</b>
   * 
   * @param iRow
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-17 ����09:48:17
   */
  private String setPlanCode(BillCardPanel panel, int iRow,
      OrderReceivePlanVO voaRP, String[] saPlanCode) {
    // ������
    int iTotalRow = panel.getRowCount();
    if (iTotalRow == 0) {
      return null;
    }

    // ��ǰ���е����ƻ���ʱ��������ȡ
    String sCode =
        (String) panel.getBodyValueAt(iRow, OrderReceivePlanVO.VBILLCODE);
    if (!StringUtil.isEmptyWithTrim(sCode)) {
      return null;
    }

    // ���õ����ƻ���
    String headBillCode = this.model.getOrderVO().getHVO().getVbillcode();
    String planCode =
        OrderReceivePlanUtils.getNextPlanCode(saPlanCode, headBillCode);
    panel.setBodyValueAt(planCode, iRow, OrderReceivePlanVO.VBILLCODE);
    voaRP.setVbillcode(planCode);
    return planCode;
  }
}
