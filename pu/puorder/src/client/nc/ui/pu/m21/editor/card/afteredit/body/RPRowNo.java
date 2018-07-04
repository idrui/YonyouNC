/**
 * $文件说明$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-14 下午02:36:16
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
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货计划行号编辑后事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-14 下午02:36:16
 */
public class RPRowNo implements ICardBodyAfterEditEventListener {

  private OrderReceivePlanModel model;

  private BillCardPanel panel;

  public RPRowNo(OrderReceivePlanModel model) {
    this.model = model;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener#afterEdit(nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent)
   */
  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    this.panel = event.getBillCardPanel();

    // 选中的行ID
    UIRefPane refPaneRowNo =
        (UIRefPane) this.panel.getBodyItem("crowno").getComponent();
    String[] saBId = refPaneRowNo.getRefPKs();
    if (ArrayUtils.isEmpty(saBId)) {
      this.panel.setBodyValueAt(event.getOldValue(), event.getRow(), "crowno");
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0073")/*
                                                                   * @res
                                                                   * "录入的行号不存在"
                                                                   */);
    }
    boolean oldneedCalFlag = this.panel.getBillModel().isNeedCalculate();
    this.panel.getBillModel().setNeedCalculate(false);

    int iVOLen = saBId.length;
    int iBeginRow = event.getRow();

    Object beginRowId =
        this.panel.getBodyValueAt(iBeginRow, OrderReceivePlanVO.PK_ORDER_BB1);

    // 增的行
    int[] iaRow = this.getRowsAfterMultiSelect(iBeginRow, iVOLen);

    // 得到需填充的行并加设置
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

    // 使用model.updateLine地址公式返回错误，暂时这样处理
    // model.insertLines(iBeginRow, voaRP);

    // 设置其他的值
    this.execLoadValue(iaRow, mapItem, voaRP);

    this.panel.getBillModel().execLoadFormula();

    for (int i = 0; i < iaRow.length; ++i) {
      // 设定某行的可编辑性
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
   *          要设置的 model
   */
  public void setModel(OrderReceivePlanModel model) {
    this.model = model;
  }

  /**
   * 方法功能描述：显示到货计划界面
   * <p>
   * <b>参数说明</b>
   * 
   * @param iaRow
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-15 下午04:10:44
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
      // // 行号
      this.panel.setBodyValueAt(itemVO.getCrowno(), iRow, "crowno");
      this.panel.setBodyValueAt(itemVO.getPk_order_b(), iRow,
          OrderReceivePlanVO.PK_ORDER_B);
      //
      // // 使用model.insetLines没有将收货库存组织设置值，暂时这样处理
      // this.panel.setBodyValueAt(itemVO.getPk_arrvstoorg(), iRow,
      // OrderReceivePlanVO.PK_ARRVSTOORG);
      // this.panel.setBodyValueAt(itemVO.getPk_arrvstoorg_v(), iRow,
      // OrderReceivePlanVO.PK_ARRVSTOORG_V);
      // // model.updateLine，地址没有设置
      // this.panel.setBodyValueAt(itemVO.getPk_receiveaddress(), iRow,
      // OrderReceivePlanVO.PK_RECEIVEADDRESS);
      // this.panel.setBodyValueAt(itemVO.getVvenddevaddr(), iRow,
      // OrderReceivePlanVO.VVENDDEVADDR);
      //
      // this.panel.setBodyValueAt(itemVO.getPk_material(), iRow,
      // OrderReceivePlanVO.PK_MATERIAL);
      // this.panel.setBodyValueAt(itemVO.getPk_srcmaterial(), iRow,
      // OrderReceivePlanVO.PK_SRCMATERIAL);

      // 到货计划号
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
   * 方法功能描述：得到某些行的到货计划号的数组,过滤空及重复值
   * <p>
   * <b>参数说明</b>
   * 
   * @param cardPanel
   * @param iBeginRow
   * @param iEndRow
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-17 上午10:00:23
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
   * 方法功能描述：得到界面上某BID所对应的本行应录入的到货计划数量
   * <p>
   * <b>参数说明</b>
   * 
   * @param itemVO
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-14 下午05:47:28
   */
  private UFDouble getResidualRPNum(BillCardPanel cardPanel, OrderItemVO itemVO) {
    if (null == itemVO) {
      return UFDouble.ZERO_DBL;
    }

    UFDouble nnum = itemVO.getNnum();
    if (0 == MathTool.compareTo(nnum, UFDouble.ZERO_DBL)) {
      return UFDouble.ZERO_DBL;
    }

    // 界面已累计收货计划数量
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
    // 增的行
    int[] iaRow = new int[selectedLen];
    for (int i = 0; i < selectedLen; i++) {
      iaRow[i] = iCurRow + i;
    }

    return iaRow;
  }

  /**
   * 方法功能描述：行号的afterEdit，得到到货计划VO
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-14 下午04:26:29
   */
  private OrderReceivePlanVO[] getRPVOsAfterRowNo(CardBodyAfterEditEvent event,
      String[] saBId, Map<Integer, OrderItemVO> rowMap) {
    if (ArrayUtils.isEmpty(saBId)) {
      return null;
    }

    OrderVO orderVO = this.getModel().getOrderVO();
    int length = saBId.length;
    // 相关的表体
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
    // 为多选作准备
    int iSelectRow = event.getRow();
    int iSelect = iSelectRow;

    int iVOLen = voaSelectedItem.length;
    OrderReceivePlanVO[] voaRP = new OrderReceivePlanVO[iVOLen];

    RPRelationCalculate calc = new RPRelationCalculate();
    for (int i = 0; i < iVOLen; ++i) {
      voaRP[i] = new OrderReceivePlanVO();

      // 该行对应表体
      OrderItemVO voItem = voaSelectedItem[i];
      rowMap.put(Integer.valueOf(iSelect), voItem);
      // 采购订单
      voaRP[i].setPk_order(voItem.getPk_order());
      // 采购订单明细_主键
      voaRP[i].setPk_order_b(voItem.getPk_order_b());
      // 采购组织
      voaRP[i].setPk_org(voItem.getPk_org());
      voaRP[i].setPk_org_v(voItem.getPk_org_v());
      // 所属集团
      voaRP[i].setPk_group(voItem.getPk_group());
      // 物料
      voaRP[i].setPk_material(voItem.getPk_material());
      // 物料信息
      voaRP[i].setPk_srcmaterial(voItem.getPk_srcmaterial());
      // 收货库存组织
      voaRP[i].setPk_arrvstoorg(voItem.getPk_arrvstoorg());
      voaRP[i].setPk_arrvstoorg_v(voItem.getPk_arrvstoorg_v());
      voaRP[i].setPk_flowstockorg(voItem.getPk_flowstockorg());
      voaRP[i].setPk_flowstockorg_v(voItem.getPk_flowstockorg_v());
      // 收货仓库
      voaRP[i].setPk_recvstordoc(voItem.getPk_recvstordoc());
      // 本次计划收货数量
      UFDouble dPlanNum =
          this.getResidualRPNum(event.getBillCardPanel(), voItem);
      // 主数量 默认为订单行未生成到货计划的数量
      voaRP[i].setNnum(dPlanNum);
      // 主单位
      voaRP[i].setCunitid(voItem.getCunitid());
      // 单位
      voaRP[i].setCastunitid(voItem.getCastunitid());
      // 报价单位
      voaRP[i].setCqtunitid(voItem.getCqtunitid());
      // 换算率
      voaRP[i].setVchangerate(voItem.getVchangerate());
      // 报价单位换算率
      voaRP[i].setVqtunitrate(voItem.getVqtunitrate());
      // 自由辅助属性
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
      // 批次号主键
      voaRP[i].setPk_batchcode(voItem.getPk_batchcode());
      // 批次号
      voaRP[i].setVbatchcode(voItem.getVbatchcode());
      // 激活
      voaRP[i].setFisactive(voItem.getFisactive());
      // 是否赠品
      voaRP[i].setBlargess(voItem.getBlargess());
      // 收货地址
      voaRP[i].setPk_receiveaddress(voItem.getPk_receiveaddress());
      // 收货地点
      voaRP[i].setCdevaddrid(voItem.getCdevaddrid());
      // 收货地区
      voaRP[i].setCdevareaid(voItem.getCdevareaid());
      // 供应商发货地址
      voaRP[i].setVvenddevaddr(voItem.getVvenddevaddr());
      // 供应商发货地点
      voaRP[i].setCvenddevaddrid(voItem.getCvenddevaddrid());
      // 供应商发货地区
      voaRP[i].setCvenddevareaid(voItem.getCvenddevareaid());
      // by luojw 将所有的累计数量清空
      // // 累计运输数量
      // voaRP[i].setNaccumdevnum(voItem.getNaccumdevnum());
      // // 清空累计执行数量
      // // 累计到货数量
      // voaRP[i].setNaccumarrvnum(voItem.getNaccumarrvnum());
      // // 累计入库数量
      // voaRP[i].setNaccumstorenum(voItem.getNaccumstorenum());
      // // 累计途耗数量
      // voaRP[i].setNaccumwastnum(voItem.getNaccumwastnum());
      // // 累计退货数量
      // voaRP[i].setNbackarrvnum(voItem.getNbackarrvnum());
      // // 累计退库数量
      // voaRP[i].setNbackstorenum(voItem.getNbackstorenum());

      // 行号
      voaRP[i].setCrownobb1(voItem.getCrowno());

      // 如果不是第一行，按照数量进行联动计算。否则直接赋值!
      if (MathTool.compareTo(dPlanNum, voItem.getNnum()) != 0) {
        calc.calculate(voaRP[i], OrderReceivePlanVO.NNUM);
      }
      else {
        // 数量
        voaRP[i].setNastnum(voItem.getNastnum());
        // 报价数量
        voaRP[i].setNqtunitnum(voItem.getNqtunitnum());
      }

      iSelect++;
    }

    // 选中行的到货计划号不变
    voaRP[0].setVbillcode((String) event.getBillCardPanel().getBodyValueAt(
        iSelectRow, OrderReceivePlanVO.VBILLCODE));
    return voaRP;
  }

  /**
   * 方法功能描述：设定某行的可编辑性
   * <p>
   * <b>参数说明</b>
   * 
   * @param panel
   * @param iRow
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-17 上午09:09:55
   */
  private void setBodyCellEditable(int iRow) {
    // 未录入行号时其他项不可录入
    String crowno =
        (String) this.panel.getBodyValueAt(iRow, OrderReceivePlanVO.CROWNOBB1);
    boolean bRowNoExisted = !StringUtil.isEmptyWithTrim(crowno);
    // 收货库存组织，计划到货日期，收货仓库，主数量
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

    // 设定批次号的可编辑性

    // 已关闭的行所有项不可编辑
    // 有后续单据的行只能编辑数量
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

      // 已关闭的行所有项不可编辑
      if (!voItem.getFisactive().equals(EnumActive.ACTIVE.value())) {
        for (int i = 0; i < iItemLen; i++) {
          this.panel.setCellEditable(iRow, itemaBody[i].getKey(), false);
        }
      }
      else {// 有后续单据的行只能编辑数量
        //
      }
    }
  }

  /**
   * 方法功能描述：设置到货计划号
   * <p>
   * <b>参数说明</b>
   * 
   * @param iRow
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-17 上午09:48:17
   */
  private String setPlanCode(BillCardPanel panel, int iRow,
      OrderReceivePlanVO voaRP, String[] saPlanCode) {
    // 所有行
    int iTotalRow = panel.getRowCount();
    if (iTotalRow == 0) {
      return null;
    }

    // 当前行有到货计划号时不再重新取
    String sCode =
        (String) panel.getBodyValueAt(iRow, OrderReceivePlanVO.VBILLCODE);
    if (!StringUtil.isEmptyWithTrim(sCode)) {
      return null;
    }

    // 设置到货计划号
    String headBillCode = this.model.getOrderVO().getHVO().getVbillcode();
    String planCode =
        OrderReceivePlanUtils.getNextPlanCode(saPlanCode, headBillCode);
    panel.setBodyValueAt(planCode, iRow, OrderReceivePlanVO.VBILLCODE);
    voaRP.setVbillcode(planCode);
    return planCode;
  }
}
