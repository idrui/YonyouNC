package nc.ui.pu.m25.editor.utils;

import java.util.HashSet;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.calculator.data.BillCardPanelDataSet;
import nc.ui.pubapp.pub.scale.UIScaleUtils;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m25.calculator.InvoiceCalculator;
import nc.vo.pu.m25.calculator.data.InvoiceRelationItemForCal;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pu.pub.util.PubSysParamUtil;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.Calculator;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.scale.ScaleUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>单价金额关系换算
 * </ul>
 * <p>
 */
public class RelationCalculate implements
    IAppEventHandler<CardBodyAfterEditEvent> {

  private class InvoiceBillCardPanelDataSet extends BillCardPanelDataSet {

    private IRelationForItems relaItem = null;

    public InvoiceBillCardPanelDataSet(BillCardPanel cardPanel, int row,
        IRelationForItems item) {
      super(cardPanel, row, item);
      this.relaItem = item;
    }

    /**
     * 父类方法重写
     * 
     * @see nc.ui.pubapp.calculator.data.BillCardPanelDataSet#getBillDate()
     */
    @Override
    public UFDate getBillDate() {
      Object value =
          this.getBillCardPanel().getHeadItem(InvoiceHeaderVO.DBILLDATE)
              .getValueObject();
      return ValueUtils.getUFDate(value);
    }

    @Override
    public String getCastunitid() {
      return (String) this.getBillCardPanel().getBodyValueAt(this.getRow(),
          InvoiceItemVO.CASTUNITID);
    }

    /** 本币币种 */
    @Override
    public String getCcurrencyid() {
      return this.getHeadItemStringValue(InvoiceHeaderVO.CCURRENCYID);
    }

    /** 获得原币币种 */
    @Override
    public String getCorigcurrencyid() {
      return this.getHeadItemStringValue(InvoiceHeaderVO.CORIGCURRENCYID);
    }

    @Override
    public String getCqtunitid() {
      return (String) this.getBillCardPanel().getBodyValueAt(this.getRow(),
          InvoiceItemVO.CASTUNITID);
    }

    /** 表头折本汇率 */
    @Override
    public UFDouble getNexchangerate() {
      Object value =
          this.getBillCardPanel().getHeadItem(InvoiceHeaderVO.NEXCHANGERATE)
              .getValueObject();
      return ValueUtils.getUFDouble(value);
    }

    @Override
    public UFDouble getNglobalexchgrate() {
      /** 全局本位币折本汇率 */
      Object value =
          this.getBillCardPanel().getHeadItem(InvoiceHeaderVO.NGLOBALEXCHGRATE)
              .getValueObject();
      return ValueUtils.getUFDouble(value);
    }

    @Override
    public UFDouble getNgroupexchgrate() {
      /** 集团本位币折本汇率 */
      Object value =
          this.getBillCardPanel().getHeadItem(InvoiceHeaderVO.NGROUPEXCHGRATE)
              .getValueObject();
      return ValueUtils.getUFDouble(value);
    }

    /**
     * 父类方法重写
     * 
     * @see nc.ui.pubapp.calculator.data.BillCardPanelDataSet#hasItemKey(java.lang.String)
     */
    @Override
    public boolean hasItemKey(String key) {
      if (RelationCalculate.this.getKeys() != null
          && RelationCalculate.this.getKeys().contains(key)) {
        return true;
      }
      if (this.relaItem.getNitemdiscountrateKey().equals(key)) {
        return true;
      }
      else if (this.relaItem.getCastunitidKey().equals(key)) {
        return true;
      }
      return super.hasItemKey(key);
    }

    /**
     * 方法功能描述：取表头字段值
     * <p>
     * <b>参数说明</b>
     * 
     * @param itemKey
     * @return <p>
     * @since 6.0
     * @author tianft
     * @time 2010-3-30 下午07:40:59
     */
    private String getHeadItemStringValue(String itemKey) {
      Object value =
          this.getBillCardPanel().getHeadItem(itemKey).getValueObject();
      return ValueUtils.getString(value);
    }
  }

  /** 映射的itemkey，用于hasItemKey() */
  private HashSet<String> keys = null;

  public void calculate(BillCardPanel panel, int[] rows, String itemKey) {
    IRelationForItems item = new InvoiceRelationItemForCal();
    ScaleUtils scale = UIScaleUtils.getScaleUtils();
    for (int row : rows) {
      // currentRow = row;
      // 创建数据集实例 初始化数据关系计算用的数据集
      IDataSetForCal data = new InvoiceBillCardPanelDataSet(panel, row, item);
      Calculator tool = new Calculator(data, scale);//
      // 创建参数实例，在计算的时候用来获得参数条件：是否含税优先等
      Condition cond = new Condition();// 创建参数实例
      // 设置是否进行本币换算
      cond.setIsCalLocalCurr(true);
      String pk_group =
          panel.getHeadItem(InvoiceHeaderVO.PK_GROUP).getValueObject()
              .toString();
      // 获取集团参数
      int globalPara = PubSysParamUtil.getNC002IntValue();
      int groupPara = PubSysParamUtil.getNC001IntValue(pk_group);
      // 是否启用全局本位币
      cond.setGlobalLocalCurrencyEnable(globalPara != PubSysParamUtil.GLOBAL_DISABLE);
      // 全局本位币计算方式
      cond.setOrigCurToGlobalMoney(globalPara == PubSysParamUtil.GLOBAL_ORIG_BASE);
      // 是否启用集团本位币
      cond.setGroupLocalCurrencyEnable(groupPara != PubSysParamUtil.GROUP_DISABLE);
      // 集团本位币计算方式
      cond.setOrigCurToGroupMoney(groupPara == PubSysParamUtil.GROUP_ORIG_BASE);

      // 只有主单价没有单位的单价时，计算的策略
      if (InvoiceItemVO.NASTNUM.equals(itemKey)
          || InvoiceItemVO.NNUM.equals(itemKey)) {
        if (panel.getBodyValueAt(row, InvoiceItemVO.NASTORIGPRICE) == null
            || panel.getBodyValueAt(row, InvoiceItemVO.NASTORIGTAXPRICE) == null) {
          // cond.setCalLocalPior(true); 2012-09-01
          // tianft:注释掉，需v63时候关注一下
          if (InvoiceItemVO.NNUM.equals(itemKey)) {
            cond.setUnitPriorType(Condition.MAIN_PRIOR);
          }
        }
      }

      // 设置调单价方式调单价
      cond.setIsChgPriceOrDiscount(true);
      // 设置是否固定单位换算率
      cond.setIsFixNchangerate(this.IsFixNchangerate(panel, row));
      // 是否固定报价单位换算率
      cond.setIsFixNqtunitrate(cond.getIsFixNchangerate());
      // 设置含税优先还是无税优先
      cond.setIsTaxOrNet(this.IsTaxPrior(panel));
      // 设置是否跨国采购标志
      cond.setInternational(InvoiceCalculator
          .isInternationalPur(new CardEditorHelper(panel)));
      tool.calculate(cond, itemKey);
    }
  }

  /**
   * @return keys
   */
  public HashSet<String> getKeys() {
    if (this.keys == null) {
      this.keys = new HashSet<String>();
      // 发票日期
      this.keys.add(InvoiceHeaderVO.DBILLDATE);
      // 汇率
      this.keys.add(InvoiceHeaderVO.NEXCHANGERATE);

      this.keys.add(InvoiceHeaderVO.NGLOBALEXCHGRATE);
      this.keys.add(InvoiceHeaderVO.NGROUPEXCHGRATE);

    }
    return this.keys;
  }

  @Override
  public void handleAppEvent(CardBodyAfterEditEvent e) {
    String itemKey = e.getKey();
    BillCardPanel panel = e.getBillCardPanel();
    int[] rows = new int[1];
    rows[0] = e.getRow();
    this.calculate(panel, rows, itemKey);
  }

  /**
   * @param keys 要设置的 keys
   */
  public void setKeys(HashSet<String> keys) {
    this.keys = keys;
  }

  /**
   * 方法功能描述：查询是否固定换算率
   * <p>
   * <b>参数说明</b>
   * 
   * @param panel
   * @param row
   * @return <p>
   * @since 6.0
   * @author tianft
   * @time 2010-4-1 下午01:16:33
   */
  private boolean IsFixNchangerate(BillCardPanel panel, int row) {

    CardEditorHelper util = new CardEditorHelper(panel);

    String pk_material =
        util.getBodyStringValue(row, InvoiceItemVO.PK_MATERIAL);
    String castunitid = util.getBodyStringValue(row, InvoiceItemVO.CASTUNITID);

    if (StringUtil.isEmptyWithTrim(pk_material)
        || StringUtil.isEmptyWithTrim(castunitid)) {
      return true;
    }

    if (castunitid.equals(util.getBodyStringValue(row, InvoiceItemVO.CUNITID))) {
      return true;
    }

    boolean isFixedChangeRate = false;

    isFixedChangeRate =
        MaterialPubService.queryIsFixedRateByMaterialAndMeasdoc(pk_material,
            castunitid);
    return isFixedChangeRate;
  }

  private boolean IsTaxPrior(BillCardPanel panel) {
    CardEditorHelper helper = new CardEditorHelper(panel);
    String pk_purchaseorg =
        helper.getHeadStringValue(InvoiceHeaderVO.PK_PURCHASEORG);
    return PUSysParamUtil.getPO28For25(pk_purchaseorg);
  }

}
