/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-9 ����09:07:10
 */
package nc.vo.pu.m21.rule;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.constant.PUParaValue;
import nc.vo.pu.pub.enumeration.PricePriority;
import nc.vo.pu.pub.rule.WeightVolumePieceCalc;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pu.pub.util.PubSysParamUtil;
import nc.vo.pu.scale.PuScaleUtils;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.calculator.Calculator;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.calculator.data.RelationItemForCal;
import nc.vo.pubapp.calculator.data.VODataSetForCal;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.scmpub.res.para.NCPara;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���۽���ϵ���㣬��Ҫ�����������������ɲɹ�����ʱ�ļ��㡣
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-6-9 ����09:07:10
 */
public class RelationCalculate {
  private static class OrderDataSetForCal extends VODataSetForCal {
    private OrderVO orderVO;

    public OrderDataSetForCal(CircularlyAccessibleValueObject currVO,
        IRelationForItems item, OrderVO orderVO) {
      super(currVO, item);
      this.orderVO = orderVO;
    }

    @Override
    public UFDate getBillDate() {
      return this.orderVO.getHVO().getDbilldate();
    }

    /** ���ԭ�ұ��� */
    @Override
    public String getCorigcurrencyid() {
      return this.orderVO.getHVO().getCorigcurrencyid();
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

  public void calculate(OrderVO orderVO, String itemKey) {
    this.calculate(orderVO, itemKey, null);
  }

  /**
   * �������㣬�����������������Ϣ
   * <p>
   * ʹ�ó�����
   * <ul>
   * <li>����������ǰ�Ѿ������������������Ϣ��������������ʱ����Ҫ�����¼���
   * <li>����ת��ʱ���ȸ������������������������Ϣ���㣬֮����������㲻��Ҫ�ټ���
   * </ul>
   * 
   * @param orderVO
   * @param itemKey
   * @param bfixrate
   * @param bdiscount
   */
  public void calculate(OrderVO orderVO, String itemKey, boolean bfixrate,
      boolean bdiscount) {
    this.calculate(orderVO, itemKey, bfixrate, bdiscount, null);
  }

  /**
   * �������㣬��Ҫ���������������Ϣ
   * <p>
   * ʹ�ó�����
   * <ul>
   * <li>����������ʱ���������������Ϣ�������������ı䣬�������ҲҪ�ı䡣
   * <li>ΪЧ�ʿ��ǣ��Ȳ�ѯ������Ϣ������������
   * </ul>
   * 
   * @param orderVO
   * @param itemKey
   * @param bfixrate
   * @param bdiscount
   * @param wvpCalc ���������������
   */
  public void calculate(OrderVO orderVO, String itemKey, boolean bfixrate,
      boolean bdiscount, WeightVolumePieceCalc wvpCalc) {
    IRelationForItems item = new RelationItemForCal();
    ScaleUtils scale = new ScaleUtils(orderVO.getHVO().getPk_group());
    String pkOrg = orderVO.getHVO().getPk_org();
    boolean isChangePrice = false;
    if (!bdiscount) {
      isChangePrice = this.isChangePrice(pkOrg);
    }
    boolean isTaxPricePriorToPrice = this.isTaxPricePriorToPrice(pkOrg);
    String pk_group = orderVO.getHVO().getPk_group();
    boolean groupLobalMnyCal = this.groupLobalMnyCal(pk_group);
    boolean publicLocalMnyCal = this.publicLocalMnyCal();

    boolean isOrigCurToGroupMoney = this.isOrigCurToGroupMoney(pk_group);
    boolean isOrigCurToGlobalMoney = this.isOrigCurToGlobalMoney();

    OrderItemVO[] itemVOs = orderVO.getBVO();
    int[] rows = new int[orderVO.getBVO().length];
    for (int i = 0; i < orderVO.getBVO().length; i++) {
      rows[i] = i;
    }
    for (OrderItemVO itemVO : itemVOs) {
      // �������ݼ�ʵ�� ��ʼ�����ݹ�ϵ�����õ����ݼ�
      IDataSetForCal data = new OrderDataSetForCal(itemVO, item, orderVO);
      Calculator tool = new Calculator(data, scale);
      // ��������ʵ�����ڼ����ʱ��������ò����������Ƿ�˰���ȵ�
      Condition cond = new Condition();// ��������ʵ��
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

      if (bfixrate) {
        // �����Ƿ�̶���λ������
        cond.setIsFixNchangerate(true);
        // �Ƿ�̶����۵�λ������
        cond.setIsFixNqtunitrate(true);
      }
      else {
        String material = itemVO.getPk_material();
        String cunitid = itemVO.getCunitid();
        String castunitid = itemVO.getCastunitid();
        String cqtunitid = itemVO.getCqtunitid();
        // �����Ƿ�̶���λ������
        cond.setIsFixNchangerate(this.isFixUnitRate(material, cunitid,
            castunitid));
        // �Ƿ�̶����۵�λ������
        cond.setIsFixNqtunitrate(this.isFixUnitRate(material, cunitid,
            cqtunitid));
      }

      // ���ú�˰���Ȼ�����˰����
      cond.setIsTaxOrNet(isTaxPricePriorToPrice);
      Integer fbuysellflag = itemVO.getFbuysellflag();
      // �Ƿ���ҵ�񣨹�������Ϊ�������ۡ����ڲɹ�ʱ��Ϊ���ҵ��
      cond.setInternational(BuySellFlagEnum.IMPORT.value().equals(fbuysellflag)
          || BuySellFlagEnum.OUTPUT.value().equals(fbuysellflag));
      // �������� cond Ϊ����ʱ�Ĳ�������
      tool.calculate(cond, itemKey);
    }
    if (null != wvpCalc) {
      wvpCalc.calc(rows);// ���������������������
    }
  }

  /**
   * �˷���ֻ���ڹ̶�������
   * ��������ʱ��Ҫ������Դ�Ļ�����
   * 
   * @param orderVO
   * @param itemKey
   */
  public void calculate(OrderVO orderVO, String itemKey,
      WeightVolumePieceCalc wvpCalc) {
    IRelationForItems item = new RelationItemForCal();
    ScaleUtils scale = new PuScaleUtils(orderVO.getHVO().getPk_group());
    String pkOrg = orderVO.getHVO().getPk_org();
    boolean isChangePrice = this.isChangePrice(pkOrg);
    boolean isTaxPricePriorToPrice = this.isTaxPricePriorToPrice(pkOrg);
    String pk_group = orderVO.getHVO().getPk_group();
    boolean groupLobalMnyCal = this.groupLobalMnyCal(pk_group);
    boolean publicLocalMnyCal = this.publicLocalMnyCal();

    boolean isOrigCurToGroupMoney = this.isOrigCurToGroupMoney(pk_group);
    boolean isOrigCurToGlobalMoney = this.isOrigCurToGlobalMoney();

    OrderItemVO[] itemVOs = orderVO.getBVO();
    int[] rows = new int[orderVO.getBVO().length];
    for (int i = 0; i < orderVO.getBVO().length; i++) {
      rows[i] = i;
    }
    for (OrderItemVO itemVO : itemVOs) {
      // �������ݼ�ʵ�� ��ʼ�����ݹ�ϵ�����õ����ݼ�
      IDataSetForCal data = new OrderDataSetForCal(itemVO, item, orderVO);
      Calculator tool = new Calculator(data, scale);
      // ��������ʵ�����ڼ����ʱ��������ò����������Ƿ�˰���ȵ�
      Condition cond = new Condition();// ��������ʵ��
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
      // �����Ƿ�̶���λ������
      cond.setIsFixNchangerate(true);
      // �Ƿ�̶����۵�λ������
      cond.setIsFixNqtunitrate(true);
      // ���ú�˰���Ȼ�����˰����
      cond.setIsTaxOrNet(isTaxPricePriorToPrice);
      Integer fbuysellflag = itemVO.getFbuysellflag();
      // �Ƿ���ҵ�񣨹�������Ϊ�������ۡ����ڲɹ�ʱ��Ϊ���ҵ��
      cond.setInternational(BuySellFlagEnum.IMPORT.value().equals(fbuysellflag)
          || BuySellFlagEnum.OUTPUT.value().equals(fbuysellflag));
      // �������� cond Ϊ����ʱ�Ĳ�������
      tool.calculate(cond, itemKey);
    }
    if (null != wvpCalc) {
      wvpCalc.calc(rows);// ���������������������
    }
  }

  private boolean groupLobalMnyCal(String pk_group) {
    String nc001 = PubSysParamUtil.getNC001(pk_group);
    if (NCPara.NC001_NOUSEGROUPCURRTYPE.name().equals(nc001)) {
      return false;
    }
    return true;
  }

  private boolean isChangePrice(String pk_org) {
    return PUParaValue.po84.adjust_price == PUSysParamUtil.getPO84(pk_org);
  }

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
    PricePriority pricePriority = PUSysParamUtil.getPO28(pk_org);
    if (!PricePriority.TAXPRICE_PRIOR_TO_PRICE.equals(pricePriority)) {
      flag = false;
    }
    return flag;
  }

  private boolean publicLocalMnyCal() {
    String nc002 = PubSysParamUtil.getNC002();
    if (NCPara.NC002_NOUSEGLOBALCURRTYPE.getName().equals(nc002)) {
      return false;
    }
    return true;
  }

}
