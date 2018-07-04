/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-7 下午05:00:55
 */
package nc.ui.pu.m4t.editor.body.after;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.calculator.data.BillCardPanelDataSet;
import nc.ui.pubapp.pub.scale.UIScaleUtils;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.pub.enumeration.PricePriority;
import nc.vo.pu.pub.util.ObjectUtil;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.Calculator;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.calculator.data.RelationItemForCal;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.scale.ScaleUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>单价金额关系计算
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-7 下午05:00:55
 */
public class RelationCalculate implements
    IAppEventHandler<CardBodyAfterEditEvent> {

  private static class InitialEstBillCardPanelDataSet extends
      BillCardPanelDataSet {

    /**
     * InitialEstBillCardPanelDataSet 的构造子
     * 
     * @param cardPanel
     * @param row
     * @param item
     */
    public InitialEstBillCardPanelDataSet(BillCardPanel cardPanel, int row,
        IRelationForItems item) {
      super(cardPanel, row, item);
    }

    /**
     * 父类方法重写
     * 
     * @see nc.ui.pubapp.calculator.data.BillCardPanelDataSet#getBillDate()
     */
    @Override
    public UFDate getBillDate() {
      return (UFDate) this.getBillCardPanel()
          .getHeadItem(InitialEstHeaderVO.DBILLDATE).getValueObject();
    }

    /**
     * 父类方法重写
     * 
     * @see nc.ui.pubapp.calculator.data.BillCardPanelDataSet#getCcurrencyid()
     */
    @Override
    public String getCcurrencyid() {
      return (String) this.getBillCardPanel()
          .getHeadItem(InitialEstHeaderVO.CCURRENCYID).getValueObject();
    }

    /**
     * 父类方法重写
     * 
     * @see nc.ui.pubapp.calculator.data.BillCardPanelDataSet#getCorigcurrencyid()
     */
    @Override
    public String getCorigcurrencyid() {
      return (String) this.getBillCardPanel()
          .getHeadItem(InitialEstHeaderVO.CORIGCURRENCYID).getValueObject();
    }

    /**
     * 父类方法重写
     * 
     * @see nc.ui.pubapp.calculator.data.BillCardPanelDataSet#getNexchangerate()
     */
    @Override
    public UFDouble getNexchangerate() {
      return (UFDouble) this.getBillCardPanel()
          .getHeadItem(InitialEstHeaderVO.NEXCHANGERATE).getValueObject();
    }

    /**
     * 父类方法重写
     * 
     * @see nc.ui.pubapp.calculator.data.BillCardPanelDataSet#hasItemKey(java.lang.String)
     */
    @Override
    public boolean hasItemKey(String key) {
      if (InitialEstHeaderVO.NEXCHANGERATE.equals(key)) {
        return true;
      }
      else if (InitialEstHeaderVO.CORIGCURRENCYID.equals(key)) {
        return true;
      }
      else if (InitialEstHeaderVO.CCURRENCYID.equals(key)) {
        return true;
      }
      else if (InitialEstHeaderVO.DBILLDATE.equals(key)) {
        return true;
      }
      else {
        return super.hasItemKey(key);
      }
    }
  }

  private static class InitialEstRelationItemForCal extends RelationItemForCal {
    public InitialEstRelationItemForCal() {
      // 构造函数
    }

    @Override
    public String getCastunitidKey() {
      return super.getCqtunitidKey();
    }

    @Override
    public String getCqtunitidKey() {
      return super.getCastunitidKey();
    }

    @Override
    public String getNnetpriceKey() {
      return InitialEstItemVO.NPRICE;
    }

    @Override
    public String getNorignetpriceKey() {
      return InitialEstItemVO.NORIGPRICE;
    }

    @Override
    public String getNorigtaxnetpriceKey() {
      return InitialEstItemVO.NORIGTAXPRICE;
    }

    @Override
    public String getNqtnetpriceKey() {
      return InitialEstItemVO.NASTPRICE;
    }

    @Override
    public String getNqtorignetpriceKey() {
      return InitialEstItemVO.NASTORIGPRICE;
    }

    @Override
    public String getNqtorigpriceKey() {
      return InitialEstItemVO.NASTORIGPRICE;
    }

    @Override
    public String getNqtorigtaxnetprcKey() {
      return InitialEstItemVO.NASTORIGTAXPRICE;
    }

    @Override
    public String getNqtorigtaxpriceKey() {
      return InitialEstItemVO.NASTORIGTAXPRICE;
    }

    @Override
    public String getNqtpriceKey() {
      return InitialEstItemVO.NASTPRICE;
    }

    @Override
    public String getNqttaxnetpriceKey() {
      return InitialEstItemVO.NASTTAXPRICE;
    }

    @Override
    public String getNqttaxpriceKey() {
      return InitialEstItemVO.NASTTAXPRICE;
    }

    @Override
    public String getNqtunitnumKey() {
      return InitialEstItemVO.NASTNUM;
    }

    @Override
    public String getNqtunitrateKey() {
      return InitialEstItemVO.VCHANGERATE;
    }

    @Override
    public String getNtaxnetpriceKey() {
      return InitialEstItemVO.NTAXPRICE;
    }
  }

  /**
   * 方法功能描述：计算
   * <p>
   * <b>参数说明</b>
   * 
   * @param panel
   * @param rows
   * @param itemKey
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-9 上午11:48:20
   */
  public void calculate(BillCardPanel panel, int[] rows, String itemKey) {
    Object value =
        panel.getHeadTailItem(InitialEstHeaderVO.DBILLDATE).getValueObject();
    if (ObjectUtil.isEmptyWithTrim(value)) {
      // 数量单价金额公共算法计算时，dbilldate不可为空
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004060_0", "04004060-0047")/*
                                                                   * @res
                                                                   * "入库日期不能为空！"
                                                                   */);
      return;
    }

    IRelationForItems item = new InitialEstRelationItemForCal();
    ScaleUtils scale = UIScaleUtils.getScaleUtils();
    String pk_org =
        (String) panel.getHeadTailItem(InitialEstHeaderVO.PK_PURCHASEORG)
            .getValueObject();
    boolean isTaxPricePriorToPrice = this.isTaxPricePriorToPrice(pk_org);

    for (int row : rows) {
      // 创建数据集实例 初始化数据关系计算用的数据集
      IDataSetForCal data =
          new InitialEstBillCardPanelDataSet(panel, row, item);
      Calculator tool = new Calculator(data, scale);//
      // 创建参数实例，在计算的时候用来获得参数条件：是否含税优先等
      Condition cond = new Condition();// 创建参数实例
      // 设置是否进行本币换算
      cond.setIsCalLocalCurr(true);

      // 只有主单价没有单位的单价时，计算的策略
      if (InitialEstItemVO.NASTNUM.equals(itemKey)
          || InitialEstItemVO.NNUM.equals(itemKey)) {
        if (panel.getBodyValueAt(row, InitialEstItemVO.NASTORIGPRICE) == null
            || panel.getBodyValueAt(row, InitialEstItemVO.NASTORIGTAXPRICE) == null) {
          cond.setCalLocalPior(true);
          if (InitialEstItemVO.NNUM.equals(itemKey)) {
            cond.setUnitPriorType(Condition.MAIN_PRIOR);
          }
        }
      }

      // 设置调单价方式调单价
      cond.setIsChgPriceOrDiscount(true);
      cond.setOrigCurToGlobalMoney(false);
      cond.setOrigCurToGroupMoney(false);
      String material =
          (String) panel.getBodyValueAt(row, InitialEstItemVO.PK_MATERIAL);
      String cunitid =
          (String) panel.getBodyValueAt(row, InitialEstItemVO.CUNITID);
      String castunitid =
          (String) panel.getBodyValueAt(row, InitialEstItemVO.CASTUNITID);
      // 设置是否固定单位换算率
      cond.setIsFixNchangerate(true);
      // 是否固定报价单位换算率
      cond.setIsFixNqtunitrate(this
          .isFixUnitRate(material, cunitid, castunitid));
      // 设置含税优先还是无税优先
      cond.setIsTaxOrNet(isTaxPricePriorToPrice);
      // VAT联动计算，是否跨国业务（购销类型为出口销售、进口采购时，为跨国业务）
      // 因为采购支持进口采购、国内采购。所以不用判断出口销售
      Integer fbuysellflag =
          (Integer) panel.getBodyValueAt(row, InitialEstItemVO.FBUYSELLFLAG);
      cond.setInternational(BuySellFlagEnum.IMPORT.value().equals(fbuysellflag));
      // 两个参数 cond 为计算时的参数条件
      tool.calculate(cond, itemKey);
    }
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pubapp.uif2app.event.IAppEventHandler#handleAppEvent(nc.ui.uif2.AppEvent)
   */
  @Override
  public void handleAppEvent(CardBodyAfterEditEvent e) {
    String itemKey = e.getKey();
    BillCardPanel panel = e.getBillCardPanel();
    int[] rows = new int[1];
    rows[0] = e.getRow();
    this.calculate(panel, rows, itemKey);
  }

  /**
   * 方法功能描述：是否固定换算率
   * <p>
   * <b>参数说明</b>
   * 
   * @param material
   * @param cunitid
   * @param castunitid
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-9 下午01:50:58
   */
  private boolean isFixUnitRate(String material, String cunitid,
      String castunitid) {
    boolean flag = true;
    if (material == null || cunitid == null || castunitid == null) {
      return flag;
    }
    flag =
        MaterialPubService.queryIsFixedRateByMaterialAndMeasdoc(material,
            cunitid, castunitid);
    return flag;
  }

  /**
   * 方法功能描述：价格优先策略
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_org
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-9 下午01:51:21
   */
  private boolean isTaxPricePriorToPrice(String pk_org) {
    boolean flag = true;
    if (null == pk_org) {
      return flag;
    }
    PricePriority pricePriority = PUSysParamUtil.getPO28(pk_org);
    if (!PricePriority.TAXPRICE_PRIOR_TO_PRICE.equals(pricePriority)) {
      flag = false;
    }
    return flag;
  }
}
