/**
 * $文件说明$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-13 下午01:43:55
 */
package nc.ui.pu.m21.view.rp.refmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import nc.ui.bd.ref.AbstractRefModel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>可以录入到货计划的行号参照
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-13 下午01:43:55
 */
public class PoRPRowNoModel extends AbstractRefModel {

  private static final long serialVersionUID = -3666328695904277199L;

  // 是否得到过表体
  private boolean bEverGetItem = false;

  // 当前单据卡片
  private BillCardPanel panel = null;

  // 有效的行
  private OrderItemVO[] voaPlanItem = null;

  // 订单VO
  private OrderVO voOrder = null;

  /**
   * PoRPRowNoModel 的构造子
   * 
   * @param orderVO订单VO
   */
  public PoRPRowNoModel(OrderVO voOrder, BillCardPanel panel) {
    super();
    this.voOrder = voOrder;
    this.setPanel(panel);
  }

  @Override
  public Vector getData() {
    if (this.getPanel() != null) {
      ((UIRefPane) this.getPanel().getBodyItem("crowno").getComponent())
          .getRefModel().setCacheEnabled(false);
    }

    this.m_vecData = super.getData();

    // 边界处理
    if (null == this.m_vecData || 0 == this.m_vecData.size()) {
      return this.m_vecData;
    }
    if (null == this.getPanel()) {
      return this.m_vecData;
    }

    // 应该过滤的行号
    Set<String> keySet = this.getRownoFinished(this.getPanel());
    if (null == keySet) {
      return this.m_vecData;
    }

    // 过滤（补充处理getWherePart()未处理的累计到货计划安排完成的订单行号过滤）
    int cnt = keySet.size();
    String[] saRow = keySet.toArray(new String[cnt]);
    int size = this.m_vecData.size();
    Vector tempVector = null;
    for (int k = 0; k < cnt; k++) {
      for (int i = size - 1; i >= 0; i--) {
        tempVector = (Vector) this.m_vecData.elementAt(i);
        if (tempVector != null && tempVector.contains(saRow[k])) {
          this.m_vecData.remove(i);
          size--;
          break;
        }
      }
    }

    return this.m_vecData;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.bd.ref.AbstractRefModel#getDefaultFieldCount()
   */
  @Override
  public int getDefaultFieldCount() {
    return 5;
  }

  /**
   * 父类方法重写 显示字段列表
   * 
   * @see nc.ui.bd.ref.AbstractRefModel#getFieldCode()
   */
  @Override
  public String[] getFieldCode() {
    return new String[] {
      "po_order_b.crowno", "bd_material.code", "bd_material.name",
      "bd_material.materialspec", "bd_material.materialtype"
    };
  }

  /**
   * 父类方法重写 显示字段中文名
   * 
   * @see nc.ui.bd.ref.AbstractRefModel#getFieldName()
   */
  @Override
  public String[] getFieldName() {
    return new String[] {
      nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
          "UC000-0003563")/* @res "订单行号" */,
      nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
          "UC000-0002911")/* @res "物料编码" */,
      nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
          "UC000-0002908")/* @res "物料名称" */,
      nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
          "UC000-0003448")/* @res "规格" */,
      nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
          "UC000-0001240")/* @res "型号" */,
    };
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.bd.ref.AbstractRefModel#getHiddenFieldCode()
   */
  @Override
  public String[] getHiddenFieldCode() {
    return new String[] {
      "po_order_b.pk_order_b"
    };
  }

  /**
   * 父类方法重写 Order子句
   * 
   * @see nc.ui.bd.ref.AbstractRefModel#getOrderPart()
   */
  @Override
  public String getOrderPart() {
    return "po_order_b.crowno";
  }

  /**
   * @return panel
   */
  public BillCardPanel getPanel() {
    return this.panel;
  }

  /**
   * 父类方法重写 主键字段名
   * 
   * @see nc.ui.bd.ref.AbstractRefModel#getPkFieldCode()
   */
  @Override
  public String getPkFieldCode() {
    return "po_order_b.pk_order_b";
  }

  @Override
  public String getRefCodeField() {
    return "po_order_b.crowno";
  }

  @Override
  public String getRefNameField() {
    return "bd_material.name";
  }

  /**
   * 父类方法重写 参照标题
   * 
   * @see nc.ui.bd.ref.AbstractRefModel#getRefTitle()
   */
  @Override
  public String getRefTitle() {
    return nc.ui.ml.NCLangRes.getInstance().getStrByID("40040400",
        "1400404000048")/* @res "订单行号参照（可多选）" */;
  }

  /**
   * 父类方法重写 参照数据库表或者视图名
   * 
   * @see nc.ui.bd.ref.AbstractRefModel#getTableName()
   */
  @Override
  public String getTableName() {
    return " po_order_b join bd_material on bd_material.pk_material=po_order_b.pk_material";
  }

  /**
   * 父类方法重写 设置查询条件语句
   * 
   * @see nc.ui.bd.ref.AbstractRefModel#getWherePart()
   */
  @Override
  public String getWherePart() {
    // 可以录制到货计划的体VO数组
    this.voaPlanItem = this.getCouldHavePlanBodyVO();

    if (null == this.voaPlanItem) {
      return "1=0";
    }

    SqlBuilder sql = new SqlBuilder();

    String[] pkOrderB = this.getPKOrderB(this.voaPlanItem);
    sql.append("po_order_b.pk_order_b", pkOrderB);

    // 过滤费用及折扣
    sql.append(" and isnull(bd_material.discountflag,'N')='N' ");
    sql.append(" and isnull(bd_material.fee,'N')='N'");

    // 过滤掉累计到货完成的订单行
    sql.append(" and po_order_b.nnum > coalesce(po_order_b.naccumarrvnum,0) ");
    // 过滤掉累计入库完成的订单行
    sql.append(" and po_order_b.nnum > coalesce(po_order_b.naccumstorenum,0) ");

    return sql.toString();
  }

  /**
   * @param panel
   *          要设置的 panel
   */
  public void setPanel(BillCardPanel panel) {
    this.panel = panel;
  }

  /**
   * 方法功能描述：得到增行时可以选择的行
   * <p>
   * <b>参数说明</b>
   * 
   * @return界面上的VO <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-13 下午01:55:36
   */
  private OrderItemVO[] getCouldHavePlanBodyVO() {
    // 已加载不需再加载
    if (this.bEverGetItem) {
      return this.voaPlanItem;
    }

    // 后续单据VO，到货，入库

    OrderItemVO[] voaAllItem = this.voOrder.getBVO();
    if (ArrayUtils.isEmpty(voaAllItem)) {
      return null;
    }

    List<OrderItemVO> positiveCheck = new ArrayList<OrderItemVO>();
    for (OrderItemVO itemVO : voaAllItem) {
      // 正订单行、未关闭、无到货（或入库单）
      // 有到货计划、或无到货计划且无后续单据
      if (MathTool.compareTo(itemVO.getNastnum(), UFDouble.ZERO_DBL) > 0
          && itemVO.getFisactive().equals(EnumActive.ACTIVE.value())
          && (itemVO.getBreceiveplan().booleanValue() || !itemVO
              .getBreceiveplan().booleanValue()
              && !(MathTool.compareTo(itemVO.getNaccumarrvnum(),
                  UFDouble.ZERO_DBL) > 0 || MathTool.compareTo(
                  itemVO.getNaccumstorenum(), UFDouble.ZERO_DBL) > 0))) {
        positiveCheck.add(itemVO);
      }
    }

    // 已加载
    this.bEverGetItem = true;

    if (0 == positiveCheck.size()) {
      return null;
    }
    ListToArrayTool<OrderItemVO> tool = new ListToArrayTool<OrderItemVO>();
    return tool.convertToArray(positiveCheck);
  }

  /**
   * 方法功能描述：得到主键，不含NULL或空串，并过滤重复值
   * <p>
   * <b>参数说明</b>
   * 
   * @param voaPlanItems
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-18 上午11:11:12
   */
  private String[] getPKOrderB(OrderItemVO[] voaPlanItems) {
    if (ArrayUtils.isEmpty(voaPlanItems)) {
      return null;
    }

    Set<String> set = new HashSet<String>();
    for (OrderItemVO itemVO : voaPlanItems) {
      String pkOrderB = itemVO.getPk_order_b();
      if (!StringUtil.isEmptyWithTrim(pkOrderB)) {
        set.add(pkOrderB);
      }
    }

    if (set.size() > 0) {
      String[] pkOrderBs = set.toArray(new String[0]);
      return pkOrderBs;
    }

    return null;
  }

  /**
   * 方法功能描述：获取已经完全安排的到货计划行号映射
   * <p>
   * <b>参数说明</b>
   * 
   * @param cardPanel
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-13 下午04:32:25
   */
  private Set<String> getRownoFinished(BillCardPanel cardPanel) {
    if (null == cardPanel || null == this.voOrder
        || ArrayUtils.isEmpty(this.voOrder.getBVO())) {
      return null;
    }

    int rowCount = cardPanel.getRowCount();
    if (0 == rowCount) {
      return null;
    }

    Map<String, UFDouble> mapRowNoNum = new HashMap<String, UFDouble>();
    String crowno = null;
    UFDouble nnum = null;
    UFDouble accNum = null;

    // 界面映射｛行号＝累计计划数量}
    for (int i = 0; i < rowCount; ++i) {
      crowno = (String) cardPanel.getBodyValueAt(i, OrderItemVO.CROWNO);
      if (StringUtil.isEmptyWithTrim(crowno)) {
        continue;
      }
      nnum = (UFDouble) cardPanel.getBodyValueAt(i, OrderItemVO.NNUM);
      if (mapRowNoNum.containsKey(crowno)) {
        accNum = MathTool.add(mapRowNoNum.get(crowno), nnum);
      }
      else {
        accNum = nnum;
      }
      mapRowNoNum.put(crowno, accNum);
    }
    if (0 == mapRowNoNum.size()) {
      return null;
    }

    OrderItemVO[] items = this.voOrder.getBVO();
    // 只有已经完全计划的行号
    Set<String> mapFinished = new HashSet<String>();
    for (OrderItemVO item : items) {
      if (null == item) {
        continue;
      }

      crowno = item.getCrowno();
      if (StringUtil.isEmptyWithTrim(crowno)
          || !mapRowNoNum.containsKey(crowno) || mapFinished.contains(crowno)) {
        continue;
      }
      if (MathTool.compareTo(mapRowNoNum.get(crowno), item.getNnum()) >= 0) {
        mapFinished.add(crowno);
      }
    }

    if (mapFinished.size() > 0) {
      return mapFinished;
    }
    return null;
  }

}
