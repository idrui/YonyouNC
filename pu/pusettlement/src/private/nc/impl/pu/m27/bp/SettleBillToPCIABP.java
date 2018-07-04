package nc.impl.pu.m27.bp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.pu.m27.settlebill.rule.StockInfoUtil;
import nc.itf.pu.reference.pcia.PCIAServices;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.pcia.m4632.po.I2AdjustBackData;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.costfactor.entity.CostfactorVO;
import nc.vo.pu.m27.entity.ICostfactorDiscountUtil;
import nc.vo.pu.m27.entity.SettleBillHeaderVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillItemVOUtil;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.enumeration.EnumMatchRowType;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pu.m4201.enumeration.EnumToIAFlag;
import nc.vo.pu.pub.constant.PUParaValue;
import nc.vo.pu.pub.constant.PUParaValue.po12;
import nc.vo.pu.pub.constant.PUParaValue.po13;
import nc.vo.pu.pub.util.ListUtil;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.bill.SplitBill;
import nc.vo.pubapp.calculator.formula.NumConvertRateFormula;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleObjectFactory;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���㵥���ɱ������д���
 * <li>...
 * </ul>
 * <p>
 * <b>�����ʷ����ѡ����</b>
 * <p>
 * XXX�汾����XXX��֧�֡�
 * <p>
 * <p>
 * 
 * @version ���汾��
 * @since ��һ�汾��
 * @author wangyf
 * @time 2009-7-7 ����11:30:55
 */
public class SettleBillToPCIABP {

  /**
   * <p>
   * <b>������Ҫ������¹��ܣ�</b>
   * <ul>
   * <li>��Ż�����㽫�����͵�IA������
   * <li>
   * <li>...
   * </ul>
   * <p>
   * <b>�����ʷ����ѡ����</b>
   * <p>
   * XXX�汾����XXX��֧�֡�
   * <p>
   * <p>
   * 
   * @version ���汾��
   * @since ��һ�汾��
   * @author wangyf
   * @time 2009-7-9 ����04:48:31
   */
  private class GoodsToIASet {

    // �Դ�����㵥���д���: ��ͬ��ͷ�ĵ�����ϳ�һ�ŵ���
    // ������㵥����Դ����ͷID���ݹ���־�͵�������Ψһ�����

    /** I9�������� */
    private ArrayList<SettleBillItemVO> m_listSettleToAdjustI9 =
        new ArrayList<SettleBillItemVO>();

    /** I2�ɹ���ⵥ */
    private ArrayList<SettleBillItemVO> m_listSettleToI2 =
        new ArrayList<SettleBillItemVO>();

    /** �ݹ��س嵥 */
    private ArrayList<SettleBillItemVO> m_listSettleToRushEst =
        new ArrayList<SettleBillItemVO>();

    /** ���մ���I2�ĵ��� */
    private SettleBillVO[] m_vosFinalI2 = null;

    /** ���մ���I9�ĵ��� */
    private SettleBillVO[] m_vosFinalI9 = null;

    /** �����ݹ��س��ڳ��ݹ��ĵ��� */
    private SettleBillItemVO[] m_vosFinalRushInitestItem = null;

    /** �����ݹ��س�ɹ���ĵ��� */
    private I2AdjustBackData[] m_vosFinalRushPurinData = null;

    public GoodsToIASet() {
      // Ĭ�Ϲ�����
    }

    /***
     * �����ݹ��س�����
     * <p>
     * <b>����˵��</b>
     * 
     * @param voItem <p>
     * @since 6.0
     * @author wangyf
     * @time 2010-6-1 ����02:24:30
     */
    public void addRushEstimateItems(SettleBillItemVO voItem) {
      this.m_listSettleToRushEst.add(voItem);
    }

    /***
     * ���Ӳɹ���
     * <p>
     * <b>ʹ��ʾ��:</b>
     * <p>
     * <b>����˵��</b>
     * 
     * @param voItem <p>
     * @author wangyf
     * @time 2009-7-10 ����01:32:38
     */
    public void addToI2Items(SettleBillItemVO voItem) {
      // ������ν�����Ϊ�㣨���޷�Ʊ������Ϊ�㣩�򲻴����
      if (MathTool.isZero(voItem.getNmoney())) {
        return;
      }
      this.m_listSettleToI2.add(voItem);
    }

    /***
     * ������������
     * <p>
     * <b>����˵��</b>
     * 
     * @param voItem <p>
     * @since 6.0
     * @author wangyf
     * @time 2010-6-1 ����02:31:12
     */
    public void addToI9Items(SettleBillItemVO voItem) {
      this.m_listSettleToAdjustI9.add(voItem);
    }

    /**
     * ��������
     * <p>
     * <b>����˵��</b>
     * <p>
     * 
     * @since 6.0
     * @author wangyf
     * @time 2010-6-3 ����01:12:46
     */
    public void confirmFinalData() {

      this.confirmFinalToI2SettleBills();
      this.confirmFinalToI9SettleBills();
      this.confirmFinalRushEstimateData();

    }

    /***
     * �õ��س��ڳ��ݹ�������
     * <p>
     * <b>ʹ��ʾ��:</b>
     * <p>
     * <b>����˵��</b>
     * 
     * @return <p>
     * @author wangyf
     * @time 2009-7-13 ����10:14:45
     */
    public SettleBillItemVO[] getFinalRushInitestData() {
      return this.m_vosFinalRushInitestItem;
    }

    /***
     * �õ��س�ɹ��������
     * <p>
     * <b>ʹ��ʾ��:</b>
     * <p>
     * <b>����˵��</b>
     * 
     * @return <p>
     * @author wangyf
     * @time 2009-7-13 ����10:14:45
     */
    public I2AdjustBackData[] getFinalRushPurinEstItems() {
      return this.m_vosFinalRushPurinData;
    }

    /**
     * �õ��ɹ��������
     * <p>
     * <b>ʹ��ʾ��:</b>
     * <p>
     * <b>����˵��</b>
     * 
     * @return <p>
     * @author wangyf
     * @time 2009-7-13 ����10:14:45
     */
    public SettleBillVO[] getFinalToI2SettleBills() {
      return this.m_vosFinalI2;
    }

    /***
     * ������������ITEM
     * <p>
     * <b>ʹ��ʾ��:</b>
     * <p>
     * <b>����˵��</b>
     * 
     * @return <p>
     * @author wangyf
     * @time 2010-3-30 ����04:28:47
     */
    public SettleBillVO[] getFinalToI9SettleBills() {
      return this.m_vosFinalI9;
    }

    private void calculateAssistNumForRush(I2AdjustBackData voRush,
        StockSettleVO voStock) {
      UFDouble ninnum = voStock.getNinnum();
      UFDouble nestnum = voStock.getNestnum();
      UFDouble nsettlenum = voRush.getNnum();
      // ����ݹ���������������������һ���ݹ���ɣ��������ݹ��������ڱ��ν�������������һ�λس���ɣ���ֱ��ȡ��⸨����
      if (ninnum.equals(nestnum) && nestnum.equals(nsettlenum)) {
        voRush.setNastnum(voStock.getNinassistnum());
      }
      else {
        // �������������㸨����
        ScaleUtils scale = ScaleUtils.getScaleUtilAtBS();
        String castunitid = voStock.getCastunitid();
        UFDouble nastnum =
            NumConvertRateFormula.calculateAssistNum(nsettlenum,
                voStock.getVchangerate(), castunitid, scale);
        voRush.setNastnum(nastnum);
      }
    }

    /***
     * ����������ITEM���������������������������
     * <p>
     * <b>ʹ��ʾ��:</b>
     * <p>
     * <b>����˵��</b>
     * 
     * @return <p>
     * @author wangyf
     * @time 2010-3-30 ����04:28:47
     */
    private SettleBillVO[] confirmFinalAdjustSettleBills(
        ArrayList<SettleBillItemVO> listOrig) {

      if (ListUtil.isEmpty(listOrig)) {
        return null;
      }

      /*
       * ����������һ�ν��㣺�Ƚϵ�ǰ�Ľ��㵥���Ƿ���ݹ���ȷ�ϵ�����ͬ�������ͬ������Ҫ���ɱ���
       * ������������һ�ν��㣬�ҽ������ʣ����ݹ�����޲��죬���貹�
       */
      SettleBillItemVO voItem = null;
      ArrayList<SettleBillItemVO> listRet = new ArrayList<SettleBillItemVO>();
      UFDouble dMoneyDiff = null;
      for (int i = 0; i < listOrig.size(); i++) {
        voItem = listOrig.get(i);
        // �ݹ�ʱ�Լ��ݹ���ȷ��ʱ��ȷ��
        if (MathTool.compareTo(voItem.getNoppoconfmmoney(), UFDouble.ZERO_DBL) != 0) {
          dMoneyDiff =
              MathTool
                  .sub(voItem.getNgoodsmoney(), voItem.getNoppoconfmmoney());
        }
        // ��������ݹ����Ϊ�㣬����ǳ���ݹ�ҲҪ�����������������ί�����޵����ݹ���
        else if (MathTool.compareTo(voItem.getNclashestmoney(),
            UFDouble.ZERO_DBL) != 0
            || UFBoolean.TRUE.equals(voItem.getBwashest())) {
          dMoneyDiff =
              MathTool.sub(voItem.getNgoodsmoney(), voItem.getNclashestmoney());
        }
        else {// ���û���ݹ���Ҳû��ȷ�ϣ���ȡ������ģ���������IG
          dMoneyDiff = voItem.getNreasonalwastmny();
        }

        if (MathTool.compareTo(dMoneyDiff, UFDouble.ZERO_DBL) != 0) {
          try {
            // ��֯����VO
            SettleBillItemVO voAdjust = (SettleBillItemVO) voItem.clone();
            // ���ò����ȥ�����������ۡ��ɱ�Ҫ�أ������ȫ�����ơ�
            voAdjust.setNmoney(dMoneyDiff);
            // voAdjust.setNsettlenum(null);
            voAdjust.setNprice(null);
            voAdjust.setNgoodsmoney(null);
            voAdjust.setNgoodsprice(null);
            for (int j = 0; j < CostfactorVO.MAX_NUM; j++) {
              ICostfactorDiscountUtil.setNcostfactor(voAdjust, j, null);
            }
            // �������֮����
            listRet.add(voAdjust);
          }
          catch (Exception ex) {
            ExceptionUtils.wrappException(ex);
          }
        }
      }

      if (ListUtil.isEmpty(listRet)) {
        return null;
      }

      // -------------------�ֵ�
      // �ɹ��롢VMI���ڳ��ݹ���ί����
      SettleBillVO voRet = new SettleBillVO();
      voRet.setParentVO((SettleBillHeaderVO) SettleBillToPCIABP.this.getBill()
          .getParentVO().clone());

      SettleBillItemVO[] voaBeforeSplit =
          listRet.toArray(new SettleBillItemVO[listRet.size()]);
      voRet.setChildrenVO(voaBeforeSplit);

      SplitBill<SettleBillVO> split = new SplitBill<SettleBillVO>();
      // ��浥�ݡ��ɱ��򡢹�Ӧ����Ĭ�Ϸֵ�����
      split.appendKey(SettleBillItemVO.PK_COSTREGION);
      split.appendKey(SettleBillItemVO.PK_STOCK);
      split.appendKey(SettleBillItemVO.PK_SUPPLIER);
      SettleBillVO[] voaAfterBill = split.split(new SettleBillVO[] {
        voRet
      });

      return voaAfterBill;

    }

    /***
     * �õ��ݹ��س������
     * <p>
     * <b>ʹ��ʾ��:</b>
     * <p>
     * <b>����˵��</b>
     * 
     * @return <p>
     * @author wangyf
     * @time 2009-7-13 ����10:14:45
     */
    private void confirmFinalRushEstimateData() {

      if (ListUtil.isEmpty(this.m_listSettleToRushEst)) {
        return;
      }

      ArrayList<I2AdjustBackData> listRushPurin =
          new ArrayList<I2AdjustBackData>();
      ArrayList<SettleBillItemVO> listRushInitest =
          new ArrayList<SettleBillItemVO>();

      for (SettleBillItemVO voSettleItem : this.m_listSettleToRushEst) {
        StockSettleVO voStock =
            SettleBillToPCIABP.this.getStockInfo().getStockSettleVO(
                voSettleItem.getPk_stock_b());
        // �õ���浥��VO���ӿ�浥�����ɻس嵥
        if (POBillType.InitEstimate.getCode().equals(
            voSettleItem.getVstockbilltype())) {
          listRushInitest.add(voSettleItem);
        }
        else {
          I2AdjustBackData voRush = new I2AdjustBackData();
          voRush.setVsettlecode(SettleBillToPCIABP.this.getSettleBillVO()
              .getParentVO().getVbillcode());
          voRush.setCsettleid(voSettleItem.getPk_settlebill());
          voRush.setCsettlebid(voSettleItem.getPk_settlebill_b());
          voRush.setVsettlerowno(voSettleItem.getCrowno());
          voRush.setCicid(voSettleItem.getPk_stock());
          voRush.setCicbid(voSettleItem.getPk_stock_b());
          voRush.setNnum(voSettleItem.getNsettlenum());
          // wuxla v61
          // voRush.setNprice(voStock.getNestprice());
          voRush.setNprice(voStock.getNestcalcostprice());
          voRush.setNmny(voSettleItem.getNclashestmoney());
          voRush.setVchangerate(voStock.getVchangerate());

          // by 20141217 mengjian ȡ������Ʊ���ڣ�ȡҵ������
          UFDate date = voSettleItem.getInvoicebilldate();
          if(date == null){
            date = AppContext.getInstance().getBusiDate();
          }
          voRush.setDaccountdate(date);

          this.calculateAssistNumForRush(voRush, voStock);

          // �������֮����
          if (ICBillType.PurchaseIn.getCode().equals(
              voSettleItem.getVstockbilltype())) {
            listRushPurin.add(voRush);
          }
        }
      }

      this.m_vosFinalRushPurinData =
          ListUtil.isEmpty(listRushPurin) ? null : listRushPurin
              .toArray(new I2AdjustBackData[listRushPurin.size()]);
      this.m_vosFinalRushInitestItem =
          ListUtil.isEmpty(listRushInitest) ? null : listRushInitest
              .toArray(new SettleBillItemVO[listRushInitest.size()]);

    }

    /**
     * ���������������õ����մ�I2���ݵĽ��㵥VO����
     * <p>
     * <b>����˵��</b>
     * 
     * @throws BusinessException <p>
     * @since 6.0
     * @author duy
     * @time 2010-8-11 ����02:36:37
     */
    private void confirmFinalToI2SettleBills() {

      if (ListUtil.isEmpty(this.m_listSettleToI2)) {
        return;
      }

      SettleBillVO vo = new SettleBillVO();
      vo.setParentVO((SettleBillHeaderVO) SettleBillToPCIABP.this.getBill()
          .getParentVO().clone());

      SettleBillItemVO[] voaBeforeSplit =
          new SettleBillItemVO[this.m_listSettleToI2.size()];
      for (int i = 0; i < voaBeforeSplit.length; i++) {
        voaBeforeSplit[i] =
            (SettleBillItemVO) this.m_listSettleToI2.get(i).clone();
        // I2����������Ƿ��ú��ۿۣ�������ɵ��������룩
        voaBeforeSplit[i].setNprice(voaBeforeSplit[i].getNgoodsprice());
        voaBeforeSplit[i].setNmoney(voaBeforeSplit[i].getNgoodsmoney());
      }
      vo.setChildrenVO(voaBeforeSplit);

      SplitBill<SettleBillVO> split = new SplitBill<SettleBillVO>();
      // ��浥�ݡ��ɱ�����Ĭ�Ϸֵ�����(���Ը��ǰ��������͡���浥�ݡ���Ӧ�̡��ɱ���ֵ���
      // ������ڼ�ͳɱ���ֵ��Ǵ������
      split.appendKey(SettleBillItemVO.PK_COSTREGION);
      split.appendKey(SettleBillItemVO.PK_STOCK);
      split.appendKey(SettleBillItemVO.PK_SUPPLIER);
      if (EnumMatchRowType.InvoiceDTransPO.toInteger().equals(
          voaBeforeSplit[0].getFrowtype())) {
        split.appendKey(SettleBillItemVO.PK_STORDOC);
      }
      SettleBillVO[] voaAfterBill = split.split(new SettleBillVO[] {
        vo
      });

      this.m_vosFinalI2 = voaAfterBill;
    }

    /***
     * ������������ITEM
     * <p>
     * <b>ʹ��ʾ��:</b>
     * <p>
     * <b>����˵��</b>
     * 
     * @return <p>
     * @author wangyf
     * @time 2010-3-30 ����04:28:47
     */
    private void confirmFinalToI9SettleBills() {
      this.m_vosFinalI9 =
          this.confirmFinalAdjustSettleBills(this.m_listSettleToAdjustI9);
    }

  }

  /** ���㵥 */
  private SettleBillVO m_bill = null;

  /** ���㻷�� */
  private SettleEnvironment m_settleEnv = null;

  /** ��浥����Ϣ�� */
  private StockInfoUtil m_stockInfo = null;

  /** IA�ĵ��ݼ��� */
  private GoodsToIASet m_ToIASet = new GoodsToIASet();

  /** ��ǰ���㵥�������Ƿ񴫴�������ϼ�ֵ�������ԣ� **/
  private Map<String, Map<String, UFBoolean>> orgToIAMarMap = null;

  public SettleBillToPCIABP(final SettleBillVO bill,
      final SettleEnvironment settleEnv) {
    this.m_bill = bill;
    this.m_settleEnv = settleEnv;

  }

  public SettleEnvironment getSettleEnv() {
    return this.m_settleEnv;
  }

  /**
   * ���㵥���ɱ�
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @param voBill ���㵥VO����
   * @throws BusinessException <p>
   * @author wangyf
   * @time 2009-7-7 ����11:33:35
   */
  public void submitToPCIA(StockInfoUtil util) throws BusinessException {

    this.m_stockInfo = util;

    // �Դ��ݵ�PCIA�ı�����з���
    this.classifyItems();

    // �������е����ݣ�û�в���Ĳ���...����֯����������
    this.orginazeFinalSettleVO();

    // ����VOת����תΪIA���ݣ����ݵ�IA
    this.convertAndPassToIA();

  }

  /**
   * ��ȷ�ϳɱ����е��������ɳɱ�������
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @param voBillItem
   * @throws BusinessException <p>
   * @author wangyf
   * @time 2009-7-9 ����04:16:32
   */
  private void adjustConfirmDataCost(SettleBillItemVO voBillItem) {
    this.getToIASet().addToI9Items(voBillItem);
  }

  /**
   * ���ݹ����е��������ɳɱ�������
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @param voBillItem
   * @throws BusinessException <p>
   * @author wangyf
   * @time 2009-7-9 ����04:19:05
   */
  private void adjustEstimateDataCost(SettleBillItemVO voBillItem) {

    this.getToIASet().addToI9Items(voBillItem);

  }

  /**
   * �س����ݵĵ��۴����ߴ������ľ���
   * 
   * @param i2AdjustDatas
   */
  private void adjustI2Price(I2AdjustBackData[] i2AdjustDatas) {
    String pk_group = InvocationInfoProxy.getInstance().getGroupId();
    ScaleObjectFactory sof = new ScaleObjectFactory(pk_group);
    int costdg = sof.getCostPriceScaleObject().getDigit();
    int podg =
        sof.getPriceScaleObject().getDigit(
            this.m_bill.getParentVO().getCcurrencyid());
    if (costdg >= podg) {
      return;
    }
    for (I2AdjustBackData i2 : i2AdjustDatas) {
      UFDouble mny = MathTool.nvl(i2.getNmny());
      UFDouble num = MathTool.nvl(i2.getNnum());
      i2.setNprice(mny.div(num, costdg));
    }
  }

  /**
   * ���ݴ��͵�IA
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @throws BusinessException <p>
   * @author wangyf
   * @time 2009-7-9 ����04:16:13
   */
  private void classifyItems() {

    for (SettleBillItemVO voSettleItem : this.getBill().getChildrenVO()) {
      Integer frowtype = voSettleItem.getFrowtype();
      StockSettleVO voStockItem =
          this.getStockInfo().getStockSettleVO(voSettleItem.getPk_stock_b());
      // �������ⵥ��Ʊƥ�䡢�����Ͻ���֮��ⵥ���ұ���Ӱ��ɱ��Ŵ���
      if ((EnumMatchRowType.StockInvoiceMatch.value().equals(frowtype) || EnumMatchRowType.StockInDiffMatch
          .value().equals(frowtype))
          && null != voStockItem
          && SettleBillItemVOUtil.isSettleToPCIA(voSettleItem, voStockItem)) {
        // ����ǲɹ���
        if (ICBillType.PurchaseIn.getCode().equals(
            voSettleItem.getVstockbilltype())) {

          // ���δ���й��ݹ���ȷ�ϳɱ������ݽ��㵥�������ɴ���ɹ���ⵥ����ֱ�Ӵ��ɱ�
          Integer fdirtoiatype = voStockItem.getFdirtoiatype();
          if (EnumToIAFlag.NotToIA.value().equals(fdirtoiatype)) {
            this.directPurStock(voSettleItem);
          }
          // �����浥�ݽ��й��ݹ��ɱ�
          else if (EnumToIAFlag.EstimateToIA.value().equals(fdirtoiatype)) {
            // ����ǻس壬���ݹ�����׼���س����ݣ����ɻس嵥����ʽ�����ⵥ�����ݹ��س崫�ɱ���
            if (this.getParaEstProcessMode().equals(PUParaValue.po12.rush)) {
              this.rushAndConfirmEstimateData(voSettleItem);
            }
            else {
              // ����ǵ�������ݹ����ݺͽ��㵥����׼���������ݣ����ɴ�����������ɱ������棩�����ݹ�����ɱ���
              if (this.getParaDiffMode().equals(PUParaValue.po13.cost)) {
                this.adjustEstimateDataCost(voSettleItem);
              }
            }
          }
          // �����ȷ�ϳɱ�����������
          else if (EnumToIAFlag.ConfirmToIA.value().equals(fdirtoiatype)) {
            if (this.getParaDiffMode().equals(PUParaValue.po13.cost)) {
              this.adjustConfirmDataCost(voSettleItem);
            }
          }
        }
        // VMI(�Ͳɹ���������Ƶ�ֻ�����ݹ���������ȷ��)�ڳ��ݹ���(һ�����ݹ���ȷ��)
        else if (POBillType.InitEstimate.getCode().equals(
            voSettleItem.getVstockbilltype())) {
          // ���δ���й��ݹ���ȷ�ϳɱ������ݽ��㵥�������ɴ���ɹ���ⵥ����ֱ�Ӵ��ɱ�
          Integer fdirtoiatype = voStockItem.getFdirtoiatype();
          if (EnumToIAFlag.NotToIA.value().equals(fdirtoiatype)) {
            this.directPurStock(voSettleItem);
          }
          else if (EnumToIAFlag.EstimateToIA.value().equals(fdirtoiatype)) {
            // ����ǻس壬���ݹ�����׼���س����ݣ����ɻس嵥����ʽ�����ⵥ�����ݹ��س崫�ɱ���
            if (this.getParaEstProcessMode().equals(PUParaValue.po12.rush)) {
              this.rushAndConfirmEstimateData(voSettleItem);
            }
            else {
              // ����ǵ�������ݹ����ݺͽ��㵥����׼���������ݣ����ɴ�����������ɱ������棩�����ݹ�����ɱ���
              if (this.getParaDiffMode().equals(PUParaValue.po13.cost)) {
                this.adjustEstimateDataCost(voSettleItem);
              }
            }
          }
        }
      }
      // ����ǡ��ɹ���ⵥ�����Գ塱���ݹ�����׼���س����ݣ����ɻس嵥�����س崫�ɱ���
      else if (EnumMatchRowType.StockRush.value().equals(frowtype)) {
        if (null != voStockItem
            && SettleBillItemVOUtil.isSettleToPCIA(voSettleItem, voStockItem)
            && EnumToIAFlag.EstimateToIA.value().equals(
                voStockItem.getFdirtoiatype())) {
          this.rushEstimateData(voSettleItem);
        }
      }
      // ����ǡ����ý���֮��ⵥ�����ɹ��롢�����롢VMI��������ⵥ�����ݽ��㵥����׼���������ݣ����ɴ��������
      // ��ṹ�������˷�֧�߲���
      else if (EnumMatchRowType.StockFeeSettle.value().equals(frowtype)
          && null != voStockItem
          && SettleBillItemVOUtil.isSettleToPCIA(voSettleItem, voStockItem)) {
        if (this.getParaDiffMode().equals(PUParaValue.po13.cost)) {
          this.feeSettleCost(voSettleItem);
        }
      }
      // i) ����ǡ�ֱ������ⵥ֮��Ʊ��
      // ---i. ���ɴ������ɹ���ⵥ������SettleBillToIABP.directToIA()��
      else if (EnumMatchRowType.InvoiceDTransPO.value().equals(frowtype)) {
        this.invoiceDTransToIA(voSettleItem);
      }
    }

  }

  /**
   * ��տ�浥��
   * <p>
   * ʹ�ó�����VO����ʱ��ѯ�������Ϣ�����ڸ��ֶ��ϣ�Ȼ��ִ��VO�����������Ҫ���
   * <ul>
   * <li>
   * </ul>
   * 
   * @param settleBills
   */
  private void clearStockbill(SettleBillVO[] settleBills) {
    for (SettleBillVO settleBill : settleBills) {
      settleBill.getParentVO().setStockbill(null);
    }
  }

  /**
   * ת��ΪIA����
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @author wangyf
   * @time 2009-7-13 ����09:51:37
   */
  private void convertAndPassToIA() throws BusinessException {
    // �س�
    this.convertToRush();
    // �ɹ���
    this.convertToI2();
    // ������
    this.convertToI9();
  }

  /**
   * ת��Ϊ�ɹ���
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @author wangyf
   * @time 2009-7-13 ����10:02:20
   */
  private void convertToI2() throws BusinessException {
    SettleBillVO[] settleBills = this.getToIASet().getFinalToI2SettleBills();
    if (settleBills == null || settleBills.length == 0) {
      return;
    }
    // ִ�������Ľ�I2�����
    this.convertToI2(settleBills);

  }

  private void convertToI2(SettleBillVO[] settleBills) throws BusinessException {
    this.convertToI2(settleBills, false);
  }

  /**
   * �����
   * 
   * @param settleBills
   * @param forM4tRush �Ƿ�Ϊ�ڳ��Ļس���
   * @throws BusinessException
   */
  private void convertToI2(SettleBillVO[] settleBills, boolean forM4tRush)
      throws BusinessException {
    this.whenCrossFinanceOrg(settleBills);
    String pk_group = InvocationInfoProxy.getInstance().getGroupId();
    ScaleObjectFactory sof = new ScaleObjectFactory(pk_group);
    int costdg = sof.getCostPriceScaleObject().getDigit();
    int podg = sof.getPriceScaleObject().getDigit();
    MapList<String, SettleBillVO> mapList = new MapList<String, SettleBillVO>();
    for (int i = 0; i < settleBills.length; ++i) {
      SettleBillItemVO item = settleBills[i].getChildrenVO()[0];
      String settleStockBillType = this.getSettleStockBillType(item);
      mapList.put(settleStockBillType, settleBills[i]);
      SettleBillItemVO[] itemVOs = settleBills[i].getChildrenVO();
      for (SettleBillItemVO itemVO : itemVOs) {
        String nprice = itemVO.getNprice().toString();
        while (nprice.endsWith("0")) {
          nprice = nprice.substring(0, nprice.length() - 1);
        }
        if (nprice.indexOf(".") != -1) {
          String decimail_after =
              nprice.substring(nprice.indexOf(".") + 1, nprice.length());
          podg = decimail_after.length();
        }

        if (costdg >= podg) {
          // ��������һ����У��
          continue;
        }
        UFDouble mny = MathTool.nvl(item.getNmoney());
        UFDouble num = MathTool.nvl(item.getNsettlenum());
        itemVO.setNprice(mny.div(num, costdg));
      }
    }
    // List<I2BillVO> i2Lst = new ArrayList<I2BillVO>();
    for (Entry<String, List<SettleBillVO>> btstlentry : mapList.entrySet()) {
      SettleBillVO[] stlVos =
          btstlentry.getValue().toArray(
              new SettleBillVO[btstlentry.getValue().size()]);
      // I2BillVO[] destI2Vos =
      // (I2BillVO[]) PfUtilTools.runChangeDataAry(btstlentry.getKey(),
      // IABillType.CGRK.getCode(), stlVos);
      // ��ս��㵥�ϵļ������ԣ���浥��
      this.clearStockbill(stlVos);
      // ������Ļ��ܲ����������
      // if
      // (POBillType.VoiConsumeSettleBill.getCode().equals(btstlentry.getKey()))
      // {
      // this.setStockTranTypeForVMI(destI2Vos, settleBills[0].getParentVO()
      // .getPk_org(), AbstractRealHeadVO.CTRANTYPEID);
      // }
      // i2Lst.addAll(Arrays.asList(destI2Vos));

    }
    // I2BillVO[] voaI2 = i2Lst.toArray(new I2BillVO[i2Lst.size()]);

    // // Ϊ�������Ĳɹ���ⵥ���丨����
    // this.fillI2Nastnum(voaI2);

    // EstTOIAUtil.adjustI2Price(voaI2);
    if (forM4tRush) {
      // mengjian by 20141021 �����������Ĵ������ʱ
      if (SysInitGroupQuery.isPCIAEnabled()) {
        PCIAServices.insertI2ForPOQCSettleAdjustBack(settleBills);
      }
    }
    else {
      // ����IA
      // mengjian by 20141021 �����������Ĵ������ʱ
      if (SysInitGroupQuery.isPCIAEnabled()) {
        PCIAServices.settleToI2(settleBills);
      }
    }
  }

  /**
   * ת��Ϊ��������
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @author wangyf
   * @time 2009-7-13 ����10:02:20
   */
  private void convertToI9() throws BusinessException {
    SettleBillVO[] settleBills = this.getToIASet().getFinalToI9SettleBills();
    if (settleBills == null || settleBills.length == 0) {
      return;
    }

    this.whenCrossFinanceOrg(settleBills);

    MapList<String, SettleBillVO> mapList = new MapList<String, SettleBillVO>();
    for (int i = 0; i < settleBills.length; ++i) {
      SettleBillItemVO item = settleBills[i].getChildrenVO()[0];
      String settleStockBillType = this.getSettleStockBillType(item);
      mapList.put(settleStockBillType, settleBills[i]);
    }
    for (Entry<String, List<SettleBillVO>> btstlentry : mapList.entrySet()) {
      SettleBillVO[] stlVos =
          btstlentry.getValue().toArray(
              new SettleBillVO[btstlentry.getValue().size()]);

      // ��ս��㵥�ϵļ������ԣ���浥��
      this.clearStockbill(stlVos);

    }

    // mengjian by 20141021 �����������Ĵ������ʱ
    if (SysInitGroupQuery.isPCIAEnabled()) {
      PCIAServices.settleToI9(settleBills);
    }

  }

  /**
   * ת��Ϊ�ݹ��س嵥
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @author wangyf
   * @time 2009-7-13 ����10:03:37
   */
  private void convertToRush() throws BusinessException {

    // �س�ɹ���
    if (!ArrayUtils.isEmpty(this.getToIASet().getFinalRushPurinEstItems())) {

      I2AdjustBackData[] adjustData =
          this.getToIASet().getFinalRushPurinEstItems();
      // �س����ݵ��۵Ĵ���
      this.adjustI2Price(adjustData);
      // mengjian by 20141021 �����������Ĵ������ʱ
      if (SysInitGroupQuery.isPCIAEnabled()) {
        PCIAServices.settleToI2ForAdjustBack(adjustData);
      }
    }

    // �ڳ��ݹ��Ļس����ݣ�ֻ����VO���գ�������û���ڳ����ݹ����ݣ�
    SettleBillItemVO[] finalRushInitestData =
        this.getToIASet().getFinalRushInitestData();
    if (!ArrayUtils.isEmpty(finalRushInitestData)) {
      SettleBillItemVO[] cloneItems =
          new SettleBillItemVO[finalRushInitestData.length];
      for (int i = 0; i < finalRushInitestData.length; ++i) {
        cloneItems[i] = (SettleBillItemVO) finalRushInitestData[i].clone();
        this.opposeInitEstSettleItem(cloneItems[i]);
      }
      SettleBillVO vo = new SettleBillVO();
      vo.setParent((ISuperVO) this.getBill().getParent().clone());
      vo.setChildrenVO(cloneItems);

      SettleBillVO[] initrushsettlevos =
          this.splitSettleForInitEst(new SettleBillVO[] {
            vo
          });

      // ִ�д����
      this.convertToI2(initrushsettlevos, true);
    }

  }

  /**
   * ֱ�Ӵ��ɱ�
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @param voBillItem
   * @throws BusinessException <p>
   * @author wangyf
   * @time 2009-7-9 ����04:19:50
   */
  private void directPurStock(SettleBillItemVO voBillItem) {
    this.getToIASet().addToI2Items(voBillItem);
  }

  /**
   * ���ý��㴫�ɱ�������
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @param voBillItem
   * @throws BusinessException <p>
   * @author wangyf
   * @time 2009-7-9 ����04:20:01
   */
  private void feeSettleCost(SettleBillItemVO voBillItem) {
    //
  }

  private Map<String, Map<String, UFBoolean>> getOrgToIAMarMap() {
    if (null == this.orgToIAMarMap) {
      List<SettleBillVO> volst = new ArrayList<SettleBillVO>();
      volst.add(this.getBill());
      this.orgToIAMarMap = SettleBillItemVOUtil.getEffectByMaterial(volst);
    }
    return this.orgToIAMarMap;
  }

  /**
   * �õ�����ת�뷽ʽ
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @param sFinanceOrgId
   * @return <p>
   * @author wangyf
   * @time 2010-1-4 ����02:06:32
   */
  private po13 getParaDiffMode() {
    return PUSysParamUtil.getPO13(this.getBill().getParentVO().getPk_org());
  }

  /**
   * �õ��ݹ�������ʽ
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @param sFinanceOrgId
   * @return <p>
   * @author wangyf
   * @time 2010-1-4 ����02:05:49
   */
  private po12 getParaEstProcessMode() {
    return PUSysParamUtil.getPO12(this.getBill().getParentVO().getPk_org());
  }

  private String getSettleStockBillType(SettleBillItemVO item) {
    String settleStockBillType = null;
    String stockBillType = item.getVstockbilltype();
    // ������Դ���ݵõ���ͬ�Ľ��㵥��������
    if (!StringUtil.isEmpty(stockBillType)) {
      if (ICBillType.PurchaseIn.getCode().equals(stockBillType)) {
        settleStockBillType = POBillType.PurchaseInSettleBill.getCode();
      }
      else if (POBillType.InitEstimate.getCode().equals(stockBillType)) {
        settleStockBillType = POBillType.InitialEstSettleBill.getCode();
      }
    }
    else {
      settleStockBillType = POBillType.DTransNoPurchaseInSettleBill.getCode();
    }
    return settleStockBillType;
  }

  /**
   * IA���ݵ�GET����
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @author wangyf
   * @time 2009-7-13 ����09:42:39
   */
  private GoodsToIASet getToIASet() {
    return this.m_ToIASet;
  }

  private void invoiceDTransToIA(SettleBillItemVO item) {
    Map<String, Map<String, UFBoolean>> orgMarMap = this.getOrgToIAMarMap();
    String pk_org = item.getPk_org();
    Map<String, UFBoolean> marMap = orgMarMap.get(pk_org);
    UFBoolean toia = marMap.get(item.getPk_material());
    // ֻ�����ϼ�ֵ�����Ǵ�����ĲŴ���ֱ�����������Ҫ���⴦��һ�£�V61���ǽ���־�ŵ����㵥
    if (!UFBoolean.FALSE.equals(toia)) {
      this.directPurStock(item);
    }
  }

  private void opposeInitEstSettleItem(SettleBillItemVO item) {
    item.setNsettlenum(MathTool.oppose(item.getNsettlenum()));
    item.setNmoney(MathTool.oppose(item.getNclashestmoney()));// ʹ�ûس��ݹ����

    item.setNprice(this.getStockInfo().getStockSettleVO(item.getPk_stock_b())
        .getNestcalcostprice());// �����ݹ�����
  }

  /**
   * �����贫�ݵ�IA����������
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @since 6.0
   * @author wangyf
   * @time 2010-6-3 ����01:09:13
   */
  private void orginazeFinalSettleVO() {
    this.getToIASet().confirmFinalData();
  }

  /**
   * �ݹ��س�
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @param voBillItem
   * @throws BusinessException <p>
   * @author wangyf
   * @time 2009-7-9 ����04:20:40
   */
  private void rushAndConfirmEstimateData(SettleBillItemVO voBillItem) {
    // ����س��ITEM��
    this.getToIASet().addRushEstimateItems(voBillItem);
    // ���봫�ɱ���ITEM��
    this.getToIASet().addToI2Items(voBillItem);
  }

  private void rushEstimateData(SettleBillItemVO voBillItem) {
    // ����س��ITEM
    this.getToIASet().addRushEstimateItems(voBillItem);
  }

  private SettleBillVO[] splitSettleForInitEst(SettleBillVO[] stlVos) {
    SplitBill<SettleBillVO> split = new SplitBill<SettleBillVO>();
    split.appendKey(SettleBillItemVO.PK_STOCK);
    split.appendKey(SettleBillItemVO.PK_COSTREGION);
    split.appendKey(SettleBillItemVO.PK_SUPPLIER);
    return split.split(stlVos);
  }

  /**
   * ������������֯�ͽ��㵥������ջ������֯�����Ĳ�����֯��ͬ�������ڷ��ռ��ᣬ��Ҫ��տ����֯
   * 
   * @param settleBills ������ϵĽ��㵥
   */
  private void whenCrossFinanceOrg(SettleBillVO[] settleBills) {
    for (SettleBillVO settleBill : settleBills) {
      SettleBillItemVO[] items = settleBill.getChildrenVO();
      for (SettleBillItemVO item : items) {
        String pk_stock_b = item.getPk_stock_b();
        if (StringUtils.isNotBlank(pk_stock_b)) {
          SettleBillItemVOUtil.clearCenterPurInfo(item, this.getStockInfo()
              .getStockSettleVO(pk_stock_b));
        }
      }
    }
  }

  SettleBillVO getBill() {
    return this.m_bill;
  }

  SettleBillVO getSettleBillVO() {
    return this.m_bill;
  }

  StockInfoUtil getStockInfo() {
    return this.m_stockInfo;
  }

}
