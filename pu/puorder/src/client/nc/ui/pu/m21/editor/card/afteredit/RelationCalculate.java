package nc.ui.pu.m21.editor.card.afteredit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.itf.scmpub.reference.uap.para.SysParaInitQuery;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.util.EditEventUtil;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.calculator.data.BillCardPanelDataSet;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.uif2.AppEvent;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.rule.PlanArriveDate;
import nc.vo.pu.m21.rule.ValueCheck;
import nc.vo.pu.pub.constant.PUParaValue;
import nc.vo.pu.pub.enumeration.POParas;
import nc.vo.pu.pub.enumeration.PricePriority;
import nc.vo.pu.pub.rule.WeightVolumePieceCalc;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pu.pub.util.PubSysParamUtil;
import nc.vo.pu.scale.PuScaleUtils;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.Calculator;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.calculator.data.RelationItemForCal;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.scmpub.res.para.NCPara;

import org.apache.commons.collections.CollectionUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���۽���ϵ����
 * </ul>
 * <p>
 * 
 * @param <E> <p>
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-2-1 ����04:28:31
 */
public class RelationCalculate implements IAppEventHandler<AppEvent> {

  private static class OrderBillCardPanelDataSet extends BillCardPanelDataSet {
    public OrderBillCardPanelDataSet(BillCardPanel cardPanel, int row,
        IRelationForItems item) {
      super(cardPanel, row, item);
    }

    @Override
    public UFDate getBillDate() {
      UFDate date =
          (UFDate) this.getBillCardPanel()
              .getHeadTailItem(OrderHeaderVO.DBILLDATE).getValueObject();
      return date;
    }

    /** ���ԭ�ұ��� */
    @Override
    public String getCorigcurrencyid() {
      String value =
          (String) this.getBillCardPanel()
              .getHeadTailItem(OrderHeaderVO.CORIGCURRENCYID).getValueObject();
      return value;
    }

    @Override
    public boolean hasItemKey(String key) {
      if (OrderHeaderVO.CORIGCURRENCYID.equals(key)) {
        return true;
      }
      else if (OrderHeaderVO.DBILLDATE.equals(key)) {
        return true;
      }
      else {
        return super.hasItemKey(key);
      }
    }
  }

  private Set<String> checkNumFields = null;

  /**
   * <p>
   * ʹ�ó�����
   * <ul>
   * <li>
   * </ul>
   * 
   * @param panel
   * @param rows
   * @param itemKey
   */
  public void calculate(BillCardPanel panel, int[] rows, String itemKey) {
    this.calculate(panel, rows, itemKey, null);
  }

  public void calculate(BillCardPanel panel, int[] rows, String itemKey,
      WeightVolumePieceCalc weightVolumePieceCal) {
    /* 2015-3-30 wangweir �رպϼƣ�����Ч�� Begin*/
    panel.getBillModel().setNeedCalculate(false);
    /* 2015-3-30 wangweir End*/
    
    IRelationForItems item = new RelationItemForCal();
    String pk_org =
        (String) panel.getHeadTailItem(OrderHeaderVO.PK_ORG).getValueObject();
    String pk_group =
        (String) panel.getHeadItem(OrderHeaderVO.PK_GROUP).getValueObject();
    boolean isChangePrice = this.isChangePrice(pk_org);
    boolean isTaxPricePriorToPrice = this.isTaxPricePriorToPrice(pk_org);

    boolean groupLobalMnyCal = this.groupLobalMnyCal(pk_group);
    boolean publicLocalMnyCal = this.publicLocalMnyCal();

    boolean isOrigCurToGroupMoney = this.isOrigCurToGroupMoney(pk_group);
    boolean isOrigCurToGlobalMoney = this.isOrigCurToGlobalMoney();
    // mengjian by 20141104 ������ѯ����+������λ�Ƿ��ǹ̶�������
    Map<String, UFBoolean> fixUnitRateAst =
        this.isFixUnitRate(panel, rows, true);
    Map<String, UFBoolean> fixUnitRateQt =
        this.isFixUnitRate(panel, rows, false);
    // ��¼�����仯����
    List<Integer> rowlist = new ArrayList<Integer>();
    CardEditorHelper card = new CardEditorHelper(panel);
    PlanArriveDate pad = new PlanArriveDate(card);

    ScaleUtils scale = new PuScaleUtils(pk_group);
    for (int row : rows) {
      // �������ݼ�ʵ�� ��ʼ�����ݹ�ϵ�����õ����ݼ�
      IDataSetForCal data = new OrderBillCardPanelDataSet(panel, row, item);
      Calculator tool = new Calculator(data, scale);
      // ��������ʵ�����ڼ����ʱ��������ò����������Ƿ�˰���ȵ�
      // ��������ʵ��
      Condition cond = new Condition();
      // �����Ƿ���б��һ���
      cond.setIsCalLocalCurr(true);
      // ���õ����۷�ʽ���ۿ�
      cond.setIsChgPriceOrDiscount(isChangePrice);
      // ȫ�ֱ�λ�Ҽ��㷽ʽ
      cond.setGlobalLocalCurrencyEnable(publicLocalMnyCal);
      // ���ű�λ�Ҽ��㷽ʽ
      cond.setGroupLocalCurrencyEnable(groupLobalMnyCal);

      cond.setOrigCurToGlobalMoney(isOrigCurToGlobalMoney);
      cond.setOrigCurToGroupMoney(isOrigCurToGroupMoney);

      String material =
          (String) panel.getBodyValueAt(row, OrderItemVO.PK_MATERIAL);
      String cunitid = (String) panel.getBodyValueAt(row, OrderItemVO.CUNITID);
      String castunitid =
          (String) panel.getBodyValueAt(row, OrderItemVO.CASTUNITID);
      String cqtunitid =
          (String) panel.getBodyValueAt(row, OrderItemVO.CQTUNITID);
      // mengjian by 20141104
      String keyAst = material + cunitid + castunitid;
      String keyQt = material + cunitid + cqtunitid;
      // �����Ƿ�̶���λ������
      cond.setIsFixNchangerate(null == fixUnitRateAst.get(keyAst) ? false
          : fixUnitRateAst.get(keyAst).booleanValue());
      // �Ƿ�̶����۵�λ������
      cond.setIsFixNqtunitrate(null == fixUnitRateQt.get(keyQt) ? false
          : fixUnitRateQt.get(keyQt).booleanValue());
      // ���ú�˰���Ȼ�����˰����
      cond.setIsTaxOrNet(isTaxPricePriorToPrice);
      Integer fbuysellflag =
          (Integer) panel.getBodyValueAt(row, OrderItemVO.FBUYSELLFLAG);
      // �Ƿ���ҵ�񣨹�������Ϊ�������ۡ����ڲɹ�ʱ��Ϊ���ҵ��
      cond.setInternational(BuySellFlagEnum.IMPORT.value().equals(fbuysellflag)
          || BuySellFlagEnum.OUTPUT.value().equals(fbuysellflag));
      UFDouble oldnnum = (UFDouble) panel.getBodyValueAt(row, OrderItemVO.NNUM);
      // �������� cond Ϊ����ʱ�Ĳ�������
      tool.calculate(cond, itemKey);
      UFDouble newNum = (UFDouble) panel.getBodyValueAt(row, OrderItemVO.NNUM);
      // �������������������
      this.calcWVP(itemKey, weightVolumePieceCal, row, oldnnum, newNum);
      // // ����ƻ���������
      // this.calcPlanDate(pad, itemKey, row, oldnnum, newNum);
      if (!MathTool.equals(oldnnum, newNum)) {
        rowlist.add(Integer.valueOf(row));
      }
    }
    // ����ƻ���������
    this.calcPlanDate(pad, itemKey, rows, ArrayUtil.toPrimitive(rowlist));
    
    /* 2015-3-30 wangweir �رպϼƣ�����Ч�� Begin*/
    panel.getBillModel().setNeedCalculate(true);
    /* 2015-3-30 wangweir End*/
  }

  public void calculate(BillCardPanel panel, int[] rows, String itemKey,
      WeightVolumePieceCalc weightVolumePieceCal, boolean discount) {
    /* 2015-3-30 wangweir �رպϼƣ�����Ч�� Begin*/
    panel.getBillModel().setNeedCalculate(false);
    /* 2015-3-30 wangweir End*/
    
    IRelationForItems item = new RelationItemForCal();
    String pk_org =
        (String) panel.getHeadTailItem(OrderHeaderVO.PK_ORG).getValueObject();
    String pk_group =
        (String) panel.getHeadItem(OrderHeaderVO.PK_GROUP).getValueObject();
    boolean isTaxPricePriorToPrice = this.isTaxPricePriorToPrice(pk_org);

    boolean groupLobalMnyCal = this.groupLobalMnyCal(pk_group);
    boolean publicLocalMnyCal = this.publicLocalMnyCal();

    boolean isOrigCurToGroupMoney = this.isOrigCurToGroupMoney(pk_group);
    boolean isOrigCurToGlobalMoney = this.isOrigCurToGlobalMoney();

    // mengjian by 20141104 ������ѯ����+������λ�Ƿ��ǹ̶�������
    Map<String, UFBoolean> fixUnitRateAst =
        this.isFixUnitRate(panel, rows, true);
    Map<String, UFBoolean> fixUnitRateQt =
        this.isFixUnitRate(panel, rows, false);
    // ��¼�����仯����
    List<Integer> rowlist = new ArrayList<Integer>();
    CardEditorHelper card = new CardEditorHelper(panel);
    PlanArriveDate pad = new PlanArriveDate(card);

    ScaleUtils scale = new PuScaleUtils(pk_group);
    for (int row : rows) {
      // �������ݼ�ʵ�� ��ʼ�����ݹ�ϵ�����õ����ݼ�
      IDataSetForCal data = new OrderBillCardPanelDataSet(panel, row, item);
      Calculator tool = new Calculator(data, scale);
      // ��������ʵ�����ڼ����ʱ��������ò����������Ƿ�˰���ȵ�
      // ��������ʵ��
      Condition cond = new Condition();
      // �����Ƿ���б��һ���
      cond.setIsCalLocalCurr(true);
      // ���õ����۷�ʽ���ۿ�
      cond.setIsChgPriceOrDiscount(discount);
      // ȫ�ֱ�λ�Ҽ��㷽ʽ
      cond.setGlobalLocalCurrencyEnable(publicLocalMnyCal);
      // ���ű�λ�Ҽ��㷽ʽ
      cond.setGroupLocalCurrencyEnable(groupLobalMnyCal);

      cond.setOrigCurToGlobalMoney(isOrigCurToGlobalMoney);
      cond.setOrigCurToGroupMoney(isOrigCurToGroupMoney);

      String material =
          (String) panel.getBodyValueAt(row, OrderItemVO.PK_MATERIAL);
      String cunitid = (String) panel.getBodyValueAt(row, OrderItemVO.CUNITID);
      String castunitid =
          (String) panel.getBodyValueAt(row, OrderItemVO.CASTUNITID);
      String cqtunitid =
          (String) panel.getBodyValueAt(row, OrderItemVO.CQTUNITID);
      // mengjian by 20141104
      String keyAst = material + cunitid + castunitid;
      String keyQt = material + cunitid + cqtunitid;
      // �����Ƿ�̶���λ������
      cond.setIsFixNchangerate(null == fixUnitRateAst.get(keyAst) ? false
          : fixUnitRateAst.get(keyAst).booleanValue());
      // �Ƿ�̶����۵�λ������
      cond.setIsFixNqtunitrate(null == fixUnitRateQt.get(keyQt) ? false
          : fixUnitRateQt.get(keyQt).booleanValue());
      // ���ú�˰���Ȼ�����˰����
      cond.setIsTaxOrNet(isTaxPricePriorToPrice);
      Integer fbuysellflag =
          (Integer) panel.getBodyValueAt(row, OrderItemVO.FBUYSELLFLAG);
      // �Ƿ���ҵ�񣨹�������Ϊ�������ۡ����ڲɹ�ʱ��Ϊ���ҵ��
      cond.setInternational(BuySellFlagEnum.IMPORT.value().equals(fbuysellflag)
          || BuySellFlagEnum.OUTPUT.value().equals(fbuysellflag));
      UFDouble oldnnum = (UFDouble) panel.getBodyValueAt(row, OrderItemVO.NNUM);
      // �������� cond Ϊ����ʱ�Ĳ�������
      tool.calculate(cond, itemKey);
      UFDouble newNum = (UFDouble) panel.getBodyValueAt(row, OrderItemVO.NNUM);
      // �������������������
      this.calcWVP(itemKey, weightVolumePieceCal, row, oldnnum, newNum);

      // // ����ƻ���������
      // this.calcPlanDate(pad, itemKey, row, oldnnum, newNum);
      if (!MathTool.equals(oldnnum, newNum)) {
        rowlist.add(Integer.valueOf(row));
      }
    }
    // ����ƻ���������
    this.calcPlanDate(pad, itemKey, rows, ArrayUtil.toPrimitive(rowlist));
    
    /* 2015-3-30 wangweir �رպϼƣ�����Ч�� Begin*/
    panel.getBillModel().setNeedCalculate(true);
    /* 2015-3-30 wangweir End*/
  }

  @Override
  public void handleAppEvent(AppEvent e) {
    if(!(e instanceof CardBodyAfterEditEvent)){
      return;
    }
    CardBodyAfterEditEvent event = (CardBodyAfterEditEvent) e;
    String itemKey = event.getKey();
    BillCardPanel panel = event.getBillCardPanel();
    // ��ֵ���ֶμ��
    this.numValueCheck(event);
    if (itemKey == null || panel == null) {
      return;
    }

    int[] rows = EditEventUtil.getAfterEditRows(event);
    if (null == rows) {
      return;
    }
    WeightVolumePieceCalc weightVolumePieceCal = null;
    if (OrderItemVO.NNUM.equals(itemKey) || OrderItemVO.NASTNUM.equals(itemKey)
        || OrderItemVO.NQTUNITNUM.equals(itemKey)) {
      weightVolumePieceCal = 
          new WeightVolumePieceCalc(new CardEditorHelper(panel), rows);
    }
    this.calculate(panel, rows, itemKey, weightVolumePieceCal);
  }

  @SuppressWarnings("unused")
  private void calcPlanDate(PlanArriveDate pad, String itemKey, int row,
      UFDouble oldnnum, UFDouble newNum) {
    if (!MathTool.equals(oldnnum, newNum)
        || OrderItemVO.PK_MATERIAL.equals(itemKey)
        || OrderItemVO.PK_ARRVSTOORG_V.equals(itemKey)
        || OrderItemVO.PK_REQSTOORG_V.equals(itemKey)
        || OrderItemVO.NNUM.equals(itemKey)) {// �������༭���Ͼ�ֵ����ȣ������д˷�֧
      pad.setPlanArriveDate(new int[] {
        row
      });
    }
  }

  private void calcPlanDate(PlanArriveDate pad, String itemKey, int[] rows,
      int[] rowlist) {
    if (OrderItemVO.PK_MATERIAL.equals(itemKey)
        || OrderItemVO.PK_ARRVSTOORG_V.equals(itemKey)
        || OrderItemVO.PK_REQSTOORG_V.equals(itemKey)
        || OrderItemVO.NNUM.equals(itemKey)) {// �������༭���Ͼ�ֵ����ȣ������д˷�֧
      pad.setPlanArriveDate(rows);
    }
    else if (null != rowlist && rowlist.length > 0) {
      // �˷�ֻ֧�����������仯�Ľ�����������
      pad.setPlanArriveDate(rowlist);
    }
  }

  private void calcWVP(String itemKey,
      WeightVolumePieceCalc weightVolumePieceCal, int row, UFDouble oldnnum,
      UFDouble newNum) {
    if (null == weightVolumePieceCal) {
      return;
    }
    if (!MathTool.equals(oldnnum, newNum)
        || OrderItemVO.PK_MATERIAL.equals(itemKey)
        || OrderItemVO.NNUM.equals(itemKey)) {// �������༭���Ͼ�ֵ����ȣ������д˷�֧
      weightVolumePieceCal.calc(new int[] {
        row
      });
    }
  }

  private Set<String> getCheckNumFields() {
    if (this.checkNumFields == null) {
      this.checkNumFields = new HashSet<String>();
      this.checkNumFields.add(OrderItemVO.NASTNUM);
      this.checkNumFields.add(OrderItemVO.NNUM);
      this.checkNumFields.add(OrderItemVO.NQTUNITNUM);
      this.checkNumFields.add(OrderItemVO.NTAXRATE);
      this.checkNumFields.add(OrderItemVO.NNOSUBTAXRATE);
      this.checkNumFields.add(OrderItemVO.NQTORIGPRICE);
      this.checkNumFields.add(OrderItemVO.NQTORIGTAXPRICE);
      this.checkNumFields.add(OrderItemVO.NITEMDISCOUNTRATE);
      this.checkNumFields.add(OrderItemVO.NORIGTAXNETPRICE);
      this.checkNumFields.add(OrderItemVO.NORIGPRICE);

    }
    return this.checkNumFields;
  }

  private boolean groupLobalMnyCal(String pk_group) {
    String nc001 = PubSysParamUtil.getNC001(pk_group);
    if (NCPara.NC001_NOUSEGROUPCURRTYPE.getName().equals(nc001)) {
      return false;
    }
    return true;
  }

  private boolean isChangePrice(String pk_org) {
    return PUParaValue.po84.adjust_price == PUSysParamUtil.getPO84(pk_org);
  }

  /**
   * mengjian by 20141104
   * 
   * @param panel
   * @param rows
   * @param isAst �Ǹ���λ���Ǳ��۵�λ
   * @return
   */
  private Map<String, UFBoolean> isFixUnitRate(BillCardPanel panel, int[] rows,
      boolean isAst) {
    Map<String, UFBoolean> map = new HashMap<String, UFBoolean>();
    List<String> pk_materials = new ArrayList<String>();
    List<String> mainunits = new ArrayList<String>();
    List<String> otherunits = new ArrayList<String>();
    for (int row = 0; row < rows.length; row++) {
      String material =
          (String) panel.getBodyValueAt(rows[row], OrderItemVO.PK_MATERIAL);
      String cunitid = (String) panel.getBodyValueAt(row, OrderItemVO.CUNITID);
      String castunitid =
          (String) panel.getBodyValueAt(rows[row], OrderItemVO.CASTUNITID);
      String cqtunitid =
          (String) panel.getBodyValueAt(rows[row], OrderItemVO.CQTUNITID);
      pk_materials.add(material);
      mainunits.add(cunitid);
      if (isAst) {
        otherunits.add(castunitid);
      }
      else {
        otherunits.add(cqtunitid);
      }
    }
    if (CollectionUtils.isEmpty(pk_materials)
        || CollectionUtils.isEmpty(otherunits)) {
      return map;
    }
    String[] materialsstr = pk_materials.toArray(new String[0]);
    String[] mainunitsstr = mainunits.toArray(new String[0]);
    String[] otherunitsstr = otherunits.toArray(new String[0]);

    map =
        MaterialPubService.queryIsFixedRateByMaterialAndMeasdocs(materialsstr,
            otherunitsstr);
    Map<String, UFBoolean> mapRes = new HashMap<String, UFBoolean>();
    int count = pk_materials.size();
    for (int i = 0; i < count; i++) {
      String keyRes = materialsstr[0] + mainunitsstr[0] + otherunitsstr[0];
      String keyTemp = materialsstr[0] + otherunitsstr[0];
      // ����λ����Ҫת���ĵ�λΪ��
      if (StringUtil.isEmpty(mainunitsstr[0])
          || StringUtil.isEmpty(otherunitsstr[0])) {
        mapRes.put(keyRes, UFBoolean.FALSE);
      }
      // ����λ��Ҫת����λ��ͬ
      else if (otherunitsstr[0].equals(mainunitsstr[0])) {
        mapRes.put(keyRes, UFBoolean.TRUE);
      }
      // ����λ��Ҫת����λ��ͬ����ѯ
      else {
        mapRes.put(keyRes, map.get(keyTemp));
      }
    }
    return mapRes;

  }

  @SuppressWarnings("unused")
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

  private boolean isOrigCurToGlobalMoney() {
    String nc002 = PubSysParamUtil.getNC002();
    if (NCPara.NC001_CALCULATEBYORIGCURRTYPE.getName().equals(nc002)) {
      return true;
    }
    return false;
  }

  private boolean isOrigCurToGroupMoney(String pk_group) {
    String nc001 = PubSysParamUtil.getNC001(pk_group);
    if (NCPara.NC001_CALCULATEBYORIGCURRTYPE.getName().equals(nc001)) {
      return true;
    }
    return false;
  }

  private boolean isTaxPricePriorToPrice(String pk_org) {
    boolean flag = true;
    String p = SysParaInitQuery.getParaString(pk_org, POParas.PO28.toString());
    if (!PricePriority.TAXPRICE_PRIOR_TO_PRICE.toString().equals(p)) {
      flag = false;
    }
    return flag;
  }

  /**
   * ��ֵ���ֶμ��
   * 
   * @param event
   */
  private void numValueCheck(CardBodyAfterEditEvent event) {
    if (this.getCheckNumFields().contains(event.getKey())) {
      CardEditorHelper card = new CardEditorHelper(event.getBillCardPanel());
      ValueCheck checker = new ValueCheck(card, (UFDouble) event.getOldValue());
      checker.checkValue(event.getRow(), event.getKey());
    }
  }

  private boolean publicLocalMnyCal() {
    String nc002 = PubSysParamUtil.getNC002();
    if (NCPara.NC001_NOUSEGLOBALCURRTYPE.getName().equals(nc002)) {
      return false;
    }
    return true;
  }
}
