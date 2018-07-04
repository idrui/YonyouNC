package nc.impl.pu.m27.merge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.itf.scmpub.reference.uap.org.CostRegionPubService;
import nc.vo.pu.costfactor.entity.CostfactorVO;
import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.ICostfactorDiscountUtil;
import nc.vo.pu.m27.entity.InvoiceSettleVOUtil;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.entity.StockSettleVOUtil;
import nc.vo.pu.m27.enumeration.EnumMatchRowType;
import nc.vo.pu.m27.merge.BillItemCalData;
import nc.vo.pu.m27.merge.MatchMerge;
import nc.vo.pu.m27.merge.rule.MatchMergeReasonWasteNumRule;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pu.m4201.enumeration.EnumToAPFlag;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.scale.ScaleUtils;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������Ŀ1���������֮����
 * <li>������Ŀ2
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
 * @time 2010-3-25 ����03:25:16
 */
public abstract class GoodsMatchMerge extends MatchMerge {

  /**
   * <p>
   * <b>�����Ϸֺõ����ݽṹ��</b>
   * <ul>
   * <li>������Ŀ1
   * <li>������Ŀ2
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
   * @time 2010-3-15 ����03:38:37
   */
  protected class DataClassify {

    /** �ѽ�����ɵķ�ƱLIST */
    private ArrayList<InvoiceSettleVO> m_listFinishedInvoice =
        new ArrayList<InvoiceSettleVO>();

    /** �ѽ�����ɵĿ��LIST */
    private ArrayList<StockSettleVO> m_listFinishedStock =
        new ArrayList<StockSettleVO>();

    /** ������ĸ���Ʊ */
    private ArrayList<InvoiceSettleVO> m_listMinusInvoice =
        new ArrayList<InvoiceSettleVO>();

    /** ������ĸ���� */
    private ArrayList<StockSettleVO> m_listMinusStock =
        new ArrayList<StockSettleVO>();

    /** ԭʼ�ķ�ƱLIST */
    private ArrayList<InvoiceSettleVO> m_listOrigInvoice = null;

    /** ԭʼ�Ŀ��LIST */
    private ArrayList<StockSettleVO> m_listOrigStock = null;

    /** �����������Ʊ */
    private ArrayList<InvoiceSettleVO> m_listPlusInvoice =
        new ArrayList<InvoiceSettleVO>();

    /** ������������ */
    private ArrayList<StockSettleVO> m_listPlusStock =
        new ArrayList<StockSettleVO>();

    /** δ������ɵķ�ƱLIST */
    private ArrayList<InvoiceSettleVO> m_listUnfinishedInvoice =
        new ArrayList<InvoiceSettleVO>();

    /** δ������ɵĿ��LIST */
    private ArrayList<StockSettleVO> m_listUnfinishedStock =
        new ArrayList<StockSettleVO>();

    /** ����ID */
    private String m_Pk_srcMaterial = null;

    /**
     * DataClassify �Ĺ�����
     * 
     * @param listInvoice
     * @param listStock
     */
    DataClassify(ArrayList<InvoiceSettleVO> listInvoice,
        ArrayList<StockSettleVO> listStock) {
      this.m_listOrigInvoice = listInvoice;
      this.m_listOrigStock = listStock;
    }

    /**
     * DataClassify �Ĺ�����
     * 
     * @param listInvoice
     * @param listStock
     */
    DataClassify(InvoiceSettleVO[] voaInvoice, StockSettleVO[] voaStock) {

      if (GoodsMatchMerge.this.getInvoiceVOs() != null) {
        // �Դ��������ݽ��з��ࣺ�����ֱ����
        ArrayList<InvoiceSettleVO> listInvoice =
            new ArrayList<InvoiceSettleVO>();
        for (InvoiceSettleVO invoiceVO : voaInvoice) {
          listInvoice.add(invoiceVO);
        }
        this.m_listOrigInvoice = listInvoice;
      }

      if (GoodsMatchMerge.this.getStockVOs() != null) {
        ArrayList<StockSettleVO> listStock = new ArrayList<StockSettleVO>();
        for (StockSettleVO stockVO : voaStock) {
          listStock.add(stockVO);
        }
        this.m_listOrigStock = listStock;
      }

    }

    /**
     * ��������
     * <p>
     * <b>ʹ��ʾ��:</b>
     * <p>
     * <b>����˵��</b>
     * <p>
     * 
     * @author wangyf
     * @time 2010-2-4 ����04:29:55
     */
    protected void classifyPlusMinus() {
      // ����������
      if (this.m_listOrigInvoice != null) {
        this.m_Pk_srcMaterial =
            this.m_listOrigInvoice.get(0).getPk_srcmaterial();
        InvoiceSettleVO voInvoice = null;
        for (int i = 0; i < this.m_listOrigInvoice.size(); i++) {
          voInvoice = this.m_listOrigInvoice.get(i);
          UFDouble currentSettleNum = voInvoice.getNcurrentsettlenum();
          currentSettleNum = MathTool.nvl(currentSettleNum);
          if (currentSettleNum.compareTo(UFDouble.ZERO_DBL) > 0) {
            this.m_listPlusInvoice.add(voInvoice);
          }
          else {
            this.m_listMinusInvoice.add(voInvoice);
          }
        }
      }

      if (this.m_listOrigStock != null) {
        this.m_Pk_srcMaterial = this.m_listOrigStock.get(0).getPk_srcmaterial();
        StockSettleVO voStock = null;
        for (int i = 0; i < this.m_listOrigStock.size(); i++) {
          voStock = this.m_listOrigStock.get(i);
          UFDouble currentSettleNum = voStock.getNcurrentsettlenum();
          currentSettleNum = MathTool.nvl(currentSettleNum);
          if (currentSettleNum.compareTo(UFDouble.ZERO_DBL) > 0) {
            this.m_listPlusStock.add(voStock);
          }
          else {
            this.m_listMinusStock.add(voStock);
          }
        }
      }
    }

    protected ArrayList<InvoiceSettleVO> getFinishedInvoices() {
      return this.m_listFinishedInvoice;
    }

    protected ArrayList<StockSettleVO> getFinishedStockes() {
      return this.m_listFinishedStock;
    }

    protected ArrayList<InvoiceSettleVO> getMinusInvoices() {
      return this.m_listMinusInvoice;
    }

    protected ArrayList<StockSettleVO> getMinusStockes() {
      return this.m_listMinusStock;
    }

    protected ArrayList<InvoiceSettleVO> getOrigInvoices() {
      return this.m_listOrigInvoice;
    }

    protected ArrayList<StockSettleVO> getOrigStockes() {
      return this.m_listOrigStock;
    }

    protected String getPk_srcmaterial() {
      return this.m_Pk_srcMaterial;
    }

    protected ArrayList<InvoiceSettleVO> getPlusInvoices() {
      return this.m_listPlusInvoice;
    }

    protected ArrayList<StockSettleVO> getPlusStockes() {
      return this.m_listPlusStock;
    }

    protected ArrayList<InvoiceSettleVO> getUnfinishedInvoices() {
      return this.m_listUnfinishedInvoice;
    }

    protected ArrayList<StockSettleVO> getUnfinishedStockes() {
      return this.m_listUnfinishedStock;
    }

    /**
     * ʣ��ĵ���
     * <p>
     * <b>ʹ��ʾ��:</b>
     * <p>
     * <b>����˵��</b>
     * <p>
     * 
     * @author wangyf
     * @time 2010-3-8 ����09:33:10
     */
    protected void sumupResidualBill() {
      // ---------��Ʊ
      if (this.getMinusInvoices() != null) {
        this.m_listUnfinishedInvoice.addAll(this.getMinusInvoices());
      }
      if (this.getPlusInvoices() != null) {
        this.m_listUnfinishedInvoice.addAll(this.getPlusInvoices());
      }

      // ---------��浥��
      if (this.getMinusStockes() != null) {
        this.m_listUnfinishedStock.addAll(this.getMinusStockes());
      }
      if (this.getPlusStockes() != null) {
        this.m_listUnfinishedStock.addAll(this.getPlusStockes());
      }
    }
  }

  protected static final int CombineType_MinusInvoiceStock = 1;

  /** ��ⵥ����Ʊ�ĺϲ����ͣ��������Ǹ��� */
  protected static final int CombineType_PlusInvoiceStock = 0;

  /** ����ͬ���Ϸֳɵļ��� */
  private DataClassify[] m_dataClassify = null;

  public GoodsMatchMerge(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, FeeDiscountSettleVO[] adjustInvcVos,
      SettleEnvironment settleEnv) {
    super(voaInvoice, voaStock, voaFee, voaDiscount, adjustInvcVos, settleEnv);
  }

  public GoodsMatchMerge(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, SettleEnvironment settleEnv) {
    super(voaInvoice, voaStock, voaFee, voaDiscount, settleEnv);
  }

  /**
   * ����ⵥ����Ʊ�ϲ�Ϊ���㵥����
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @param voInvoice ��ƱVO
   * @param voStock ���VO
   * @param bStockLastTimeAndHaveMatchAll �Ƿ�����浥���ҿ�浥�ݱ���ƥ����ɣ��˵���ֻ������������Ч
   * @return <p>
   * @author wangyf
   * @time 2009-7-3 ����11:47:52
   */
  private SettleBillItemVO createItemByInvoiceStock(InvoiceSettleVO voInvoice,
      StockSettleVO voStock, boolean bStockLastTimeAndHaveMatchAll) {

    UFDouble dInvoiceWillSettleNum =
        MathTool.sub(InvoiceSettleVOUtil.getCurrentRealSettleNum(voInvoice),
            voInvoice.getNcurrentaccumsettlenum());
    UFDouble dStockWillSettleNum =
        MathTool.sub(voStock.getNcurrentsettlenum(),
            voStock.getNcurrentaccumsettlenum());
    // ��С������ÿ��VOʣ��������������Ʊ�������һ������ȡ��ⵥ����Ϊ��������
    UFDouble dWillSettleNum = null;
    UFDouble increaseNum = null;
    if (bStockLastTimeAndHaveMatchAll) {
      dWillSettleNum = dStockWillSettleNum;
      increaseNum = MathTool.sub(dInvoiceWillSettleNum, dStockWillSettleNum);// �õ����������
    }
    else {
      dWillSettleNum =
          MathTool.absCompareTo(dStockWillSettleNum, dInvoiceWillSettleNum) > 0 ? dInvoiceWillSettleNum
              : dStockWillSettleNum;
    }
    // ��Ʊ�������������������10����Ʊ2������ʱ���ȫ�������꣬���ֵ��2�����2����Ʊ10����ֵ��2��
    UFDouble dActualInvoiceSettleNum =
        MathTool.absCompareTo(dWillSettleNum, dInvoiceWillSettleNum) < 0 ? dWillSettleNum
            : dInvoiceWillSettleNum;

    // �����������Ҫ����Ĳ���
    BillItemCalData calData =
        this.calItemData(voInvoice, dWillSettleNum, dActualInvoiceSettleNum,
            increaseNum);

    // --------------- �����ѽ���ķ�Ʊ�����VO
    // ���·�Ʊ���ۼƽ����������ܽ���Ʊ���
    // ���ν���ķ�Ʊ�����������������������������Ϊ���ȫ�������ˡ�
    voInvoice.setNcurrentaccumsettlenum(MathTool.add(
        voInvoice.getNcurrentaccumsettlenum(), dActualInvoiceSettleNum));
    voInvoice.setNcurrentaccumtotalsettlemny(MathTool.add(
        voInvoice.getNcurrentaccumtotalsettlemny(), calData.getNmoney()));
    voInvoice.setNcurrentaccuminvoicesettlemny(MathTool.add(
        voInvoice.getNcurrentaccuminvoicesettlemny(), calData.getNgoodsmoney())
        .sub(MathTool.nvl(calData.getNdiscount())));
    // ���¿�棺�ۼƽ�������
    voStock.setNcurrentaccumsettlenum(MathTool.add(
        voStock.getNcurrentaccumsettlenum(), dWillSettleNum));

    // --------------- �γɽ��㵥VO
    SettleBillItemVO voBillItem = new SettleBillItemVO();
    voBillItem
        .setFrowtype((Integer) EnumMatchRowType.StockInvoiceMatch.value());
    // ȡ�Լ�����
    voBillItem.setNsettlenum(dWillSettleNum);
    // ���û����������磬��ֱ��ȡ��Ʊ���ۣ����򣽽��/����
    // ��������δ��������ͬ�����ҵ��۷ǿգ�����ʱ����������
    voBillItem.setNprice(calData.getNprice());
    voBillItem.setNmoney(calData.getNmoney());
    voBillItem.setNgoodsmoney(calData.getNgoodsmoney());
    voBillItem.setNgoodsprice(calData.getNgoodsprice());
    // ������ɱ�Ҫ�ء��ۿ�
    ICostfactorDiscountUtil.setNcostfactor(voBillItem, calData.getNaFactor());
    voBillItem.setNdiscount(calData.getNdiscount());

    // ȡ�Է�Ʊ
    voBillItem.setPk_group(voInvoice.getPk_group());
    voBillItem.setPk_org(voInvoice.getPk_org());
    voBillItem.setVinvoicecode(voInvoice.getVbillcode());
    voBillItem.setPk_invoice(voInvoice.getPk_invoice());
    voBillItem.setPk_invoice_b(voInvoice.getPk_invoice_b());
    voBillItem.setVinvoicetrantype(voInvoice.getCtrantypeid());
    // �����������
    voBillItem.setNreasonalwastnum(calData.getNReasonWasteNum());
    // ������ĵ���Ϊ��Ʊ����,������Ľ��=�����������*������ĵ���
    voBillItem.setNreasonalwastprice(calData.getNReasonWastePrice());
    voBillItem.setNreasonalwastmny(calData.getNReasonWasteMny());
    voBillItem.setPk_invoiceorder(voInvoice.getPk_order());
    voBillItem.setPk_invoiceorder_b(voInvoice.getPk_order_b());
    // mengjian by 20141203��¼��Ʊ����
    voBillItem.setInvoicebilldate(voInvoice.getDbilldate());
    //added by wangzhqf 2015-03-24 15:10 ��¼���ں�ͬ��
    voBillItem.setVitctcode(voStock.getVctcode());

    // ȡ�Կ��
    voBillItem.setPk_costregion(voStock.getPk_costregion());
    voBillItem.setPk_material(voStock.getPk_material());
    voBillItem.setPk_srcmaterial(voStock.getPk_srcmaterial());
    voBillItem.setVstockcode(voStock.getVbillcode());
    voBillItem.setVstockbilltype(voStock.getCbilltypecode());
    voBillItem.setVstocktrantype(voStock.getCtrantypeid());
    voBillItem.setPk_stock(voStock.getPk_stockps());
    voBillItem.setPk_stock_b(voStock.getPk_stockps_b());
    voBillItem.setPk_arrstockorg(voStock.getPk_org());
    voBillItem.setPk_arrstockorg_v(voStock.getPk_org_v());
//    voBillItem.setPk_supplier(voStock.getPk_supplier());
    //�ϲ�ͨ�油����ȡ��Ʊ��Ӧ��NCdp205389141
    voBillItem.setPk_supplier(voInvoice.getPk_supplier());
    voBillItem.setPk_stockorder(voStock.getPk_order());
    voBillItem.setPk_stockorder_b(voStock.getPk_order_b());
    // ���š���Ա
    voBillItem.setPk_dept(voStock.getPk_dept());
    voBillItem.setPk_dept_v(voStock.getPk_dept_v());
    voBillItem.setPk_psndoc(voStock.getPk_psndoc());
    // mengjian by 20141203��¼�������
    voBillItem.setStockbilldate(voStock.getDbilldate());
    return voBillItem;
  }

  /**
   * ���ݲ�����֯ƥ��ɱ���
   * 
   * @param pk_fiorg
   * @return
   */
  private String getCostregionByFiOrg(String pk_fiorg) {
    Map<String, String> fiCostMap =
        CostRegionPubService.getCostRegionMapByFinanceOrgIDS(new String[] {
          pk_fiorg
        });
    return fiCostMap.get(pk_fiorg);

  }

  /**
   * ���ݿ����֯ƥ��ɱ���
   * 
   * @param pk_stockorg
   * @return
   */
  private String getCostregionByStockOrg(String pk_stockorg) {
    Map<String, String> costregionMap =
        CostRegionPubService.getCostRegionMapByStockOrgIDS(new String[] {
          pk_stockorg
        });
    return costregionMap.get(pk_stockorg);
  }

  /**
   * ���ݿ����֯+�ֿ�ƥ��ɱ���
   * 
   * @param pk_stockorg
   * @param pk_stordoc
   * @return
   */
  private String getCostregionByStockOrgAndStordoc(String pk_stockorg,
      String pk_stordoc) {
    Map<String, String> costregionMap =
        CostRegionPubService.queryCostRegionIDSByStockOrgVOSAndStordocVOS(
            new String[] {
              pk_stockorg
            }, new String[] {
              pk_stordoc
            });
    String key = pk_stockorg + "|" + pk_stordoc;
    return costregionMap.get(key);

  }

  // ���������Ķ���ж��ǿ��ǵ��˷����ڶ���ѭ���ڲ�ִ�У�������Ч������

  /**
   * ֱ�������ĳɱ����ѯ
   * 
   * @param pk_fiOrg
   * @param pk_stockorg
   * @param stockdoc
   * @return
   */
  private String getCostRegionForDTrans(String pk_fiOrg, String pk_stockorg,
      String pk_stordoc) {
    // TODO tianft ���ҳɱ���Ĺ��̿���ȡ��������
    String costRegion = null;
    // 1.�Ȱ� �����֯+�ֿ� ƥ��
    if (StringUtils.isNotEmpty(pk_stockorg)
        && StringUtils.isNotEmpty(pk_stordoc)) {
      costRegion =
          this.getCostregionByStockOrgAndStordoc(pk_stockorg, pk_stordoc);
    }
    // 2.�� �����֯ ƥ��
    if (StringUtils.isEmpty(costRegion) && StringUtils.isNotEmpty(pk_stockorg)) {
      costRegion = this.getCostregionByStockOrg(pk_stockorg);
    }
    // 1.��������֯ ƥ��
    if (StringUtils.isEmpty(costRegion)) {
      costRegion = this.getCostregionByFiOrg(pk_fiOrg);
    }

    return costRegion;

  }

  private boolean isLastMatch(List<InvoiceSettleVO> invcLst,
      List<StockSettleVO> stockLst, Integer invcIndx, Integer stockInt) {
    // �����Ʊ����ⵥ���Ѿ������һ�ţ���һ�������һ�ν���
    if (invcIndx.intValue() == invcLst.size() - 1
        && stockInt.intValue() == stockLst.size() - 1) {
      return true;
    }
    InvoiceSettleVO invcVo = invcLst.get(invcIndx.intValue());
    // ���û�����磬ֱ�ӷ���false������Ҫ����
    if (MathTool.isZero(this.getIncreaseNum(invcVo.getPk_srcmaterial()))) {
      return false;
    }
    StockSettleVO stockVo = stockLst.get(stockInt.intValue());
    UFDouble invcWillSettleNum =
        MathTool.sub(InvoiceSettleVOUtil.getCurrentRealSettleNum(invcVo),
            invcVo.getNcurrentaccumsettlenum());
    UFDouble StockWillSettleNum =
        MathTool.sub(stockVo.getNcurrentsettlenum(),
            stockVo.getNcurrentaccumsettlenum());
    if (MathTool.equals(invcWillSettleNum, StockWillSettleNum)) {
      return true; // ������δ���������뷢Ʊδ����������ȣ���Ϊ�����һ�ν���ɣ�Ҳû��Ӱ��
    }
    else if (MathTool.lessThan(StockWillSettleNum, invcWillSettleNum)) {
      return false;// ������ɽ�������С�ڷ�Ʊ�ɽ�����������һ���������һ��
    }
    // �����ⵥ�ɽ����������ڷ�Ʊ�ɽ����������Ϳ�һ�»���û�к����ſⵥƥ��ķ�Ʊ
    // ���û�У���������һ�Σ����оͲ���
    for (int i = invcIndx.intValue() + 1; i < invcLst.size(); ++i) {
      if (this.canMatch(invcLst.get(i), stockVo)) {
        return false;
      }
    }
    return true;
  }

  /**
   * ���㷢Ʊ��������Ҫ����Ĳ���
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @param pkCostRegion �ɱ���
   * @param voInvoice ��Ʊ
   * @param dWillSettleNum ����������
   * @param dActualInvoiceSettleNum �����ķ�Ʊ��������
   * @return <p>
   * @author wangyf
   * @time 2010-2-24 ����03:40:38
   */
  protected BillItemCalData calItemData(InvoiceSettleVO voInvoice,
      UFDouble dWillSettleNum, UFDouble dActualInvoiceSettleNum,
      UFDouble inCreaseNum) {
    // ��Ʊ�ѽ�����ɣ����������ȡ���������������ȡ��Ʊδ��������㵥�ۣ����ν�����/���ν��������õ���
    // ��������£�
    // ---������й����÷�̯ ����㵥�ۣ����ν�����/���ν��������õ������򣽷�Ʊ����
    UFDouble dWillSettlePrice = null;
    UFDouble dWillSettleMoney = null;
    UFDouble[] daWillSettleFactor = new UFDouble[CostfactorVO.MAX_NUM];
    UFDouble dWillSettleDiscount = null;
    UFDouble dWillSettleInvoiceMoney = null;
    // ��ƱҪ�������
    if (MathTool.add(voInvoice.getNcurrentaccumsettlenum(),
        dActualInvoiceSettleNum).compareTo(
        InvoiceSettleVOUtil.getCurrentRealSettleNum(voInvoice)) == 0) {
      // ��������
      dWillSettleMoney =
          MathTool.sub(voInvoice.getNcurrentotalsettlemny(),
              voInvoice.getNcurrentaccumtotalsettlemny());
      // �����Ʊ���й���̯�����������<>��Ʊ�������򵥼���Ҫ����
      if (InvoiceSettleVOUtil.isAllot(voInvoice)
          || dWillSettleNum.compareTo(dActualInvoiceSettleNum) != 0) {
        dWillSettlePrice = dWillSettleMoney.div(dWillSettleNum);
      }
      else {
        dWillSettlePrice = voInvoice.getNprice();
      }
      dWillSettlePrice =
          ScaleUtils.getScaleUtilAtBS().adjustSoPuPriceScale(dWillSettlePrice,
              voInvoice.getCcurrencyid());
      // ������ĳɱ�Ҫ��
      UFDouble dFactorPrice = null;
      UFDouble dAccumSettledFactor = null;

      for (int i = 0; i < daWillSettleFactor.length; i++) {
        if (MathTool.compareTo(
            ICostfactorDiscountUtil.getNcostfactor(voInvoice, i + 1),
            UFDouble.ZERO_DBL) == 0) {
          continue;
        }
        // �ɱ�Ҫ�ص���=�ɱ�Ҫ��/��ƱҪ���������
        dFactorPrice =
            ScaleUtils.getScaleUtilAtBS().adjustSoPuPriceScale(
                ICostfactorDiscountUtil.getNcostfactor(voInvoice, i + 1).div(
                    InvoiceSettleVOUtil.getCurrentRealSettleNum(voInvoice)),
                voInvoice.getCcurrencyid());

        // �ѽ���ĳɱ�Ҫ�ؽ��
        try {
          dAccumSettledFactor =
              ScaleUtils.getScaleUtilAtBS().adjustMnyScale(
                  voInvoice.getNcurrentaccumsettlenum().multiply(dFactorPrice),
                  this.getSettleEnv().getOrgCurr());
        }
        catch (Exception ex) {
          ExceptionUtils.wrappException(ex);
        }
        // ���������һ�εĳɱ�Ҫ�ؽ��
        daWillSettleFactor[i] =
            MathTool.sub(
                ICostfactorDiscountUtil.getNcostfactor(voInvoice, i + 1),
                dAccumSettledFactor);
      }
      // �ۿ�
      if (MathTool.compareTo(voInvoice.getNdiscount(), UFDouble.ZERO_DBL) != 0) {
        // �ۿ۵���
        UFDouble dPrice =
            ScaleUtils.getScaleUtilAtBS().adjustSoPuPriceScale(
                voInvoice.getNdiscount().div(
                    InvoiceSettleVOUtil.getCurrentRealSettleNum(voInvoice)),
                this.getSettleEnv().getOrgCurr());
        // ���������һ�ε��ۿ�
        try {
          dWillSettleDiscount =
              ScaleUtils.getScaleUtilAtBS().adjustMnyScale(
                  dActualInvoiceSettleNum.multiply(dPrice),
                  this.getSettleEnv().getOrgCurr());
        }
        catch (Exception ex) {
          ExceptionUtils.wrappException(ex);
        }
      }

      // ��Ʊ���
      dWillSettleInvoiceMoney =
          MathTool.sub(voInvoice.getNcurrentinvoicesettlemny(),
              voInvoice.getNcurrentaccuminvoicesettlemny());
    }
    // ��Ʊδ�������
    else {
      // ����
      if (InvoiceSettleVOUtil.isAllot(voInvoice)) {
        dWillSettlePrice =
            voInvoice.getNcurrentotalsettlemny().div(
                InvoiceSettleVOUtil.getCurrentRealSettleNum(voInvoice));
      }
      else {
        dWillSettlePrice = voInvoice.getNprice();
      }
      dWillSettlePrice =
          ScaleUtils.getScaleUtilAtBS().adjustSoPuPriceScale(dWillSettlePrice,
              this.getSettleEnv().getOrgCurr());
      // ���
      dWillSettleMoney =
          ScaleUtils.getScaleUtilAtBS().adjustMnyScale(
              dActualInvoiceSettleNum.multiply(dWillSettlePrice),
              this.getSettleEnv().getOrgCurr());
      // ������ĳɱ�Ҫ��
      UFDouble dFactorPrice = null;
      for (int i = 0; i < daWillSettleFactor.length; i++) {
        if (ICostfactorDiscountUtil.getNcostfactor(voInvoice, i + 1) == null) {
          continue;
        }
        // �ɱ�Ҫ�ص���=�ɱ�Ҫ��/��ƱҪ���������
        dFactorPrice =
            ScaleUtils.getScaleUtilAtBS().adjustSoPuPriceScale(
                ICostfactorDiscountUtil.getNcostfactor(voInvoice, i + 1).div(
                    InvoiceSettleVOUtil.getCurrentRealSettleNum(voInvoice)),
                this.getSettleEnv().getOrgCurr());
        // ����Ҫ����ĳɱ�Ҫ�ؽ��
        daWillSettleFactor[i] = dActualInvoiceSettleNum.multiply(dFactorPrice);
      }
      // �ۿ�
      if (voInvoice.getNdiscount() != null) {
        // �ۿ۵���
        UFDouble dPrice =
            ScaleUtils.getScaleUtilAtBS().adjustSoPuPriceScale(
                voInvoice.getNdiscount().div(
                    InvoiceSettleVOUtil.getCurrentRealSettleNum(voInvoice)),
                this.getSettleEnv().getOrgCurr());
        // �ۿ�=����*�ۿ۵���
        try {
          dWillSettleDiscount =
              ScaleUtils.getScaleUtilAtBS().adjustMnyScale(
                  dActualInvoiceSettleNum.multiply(dPrice),
                  this.getSettleEnv().getOrgCurr());
        }
        catch (Exception ex) {
          ExceptionUtils.wrappException(ex);
        }
      }
      // ��Ʊ���
      try {
        dWillSettleInvoiceMoney =
            ScaleUtils.getScaleUtilAtBS().adjustMnyScale(
                dActualInvoiceSettleNum.multiply(voInvoice.getNprice()),
                this.getSettleEnv().getOrgCurr());
      }
      catch (Exception ex) {
        ExceptionUtils.wrappException(ex);
      }
    }

    BillItemCalData calData = new BillItemCalData();
    calData.setNprice(dWillSettlePrice);
    calData.setNmoney(dWillSettleMoney);
    calData.setNgoodsmoney(MathTool.add(dWillSettleInvoiceMoney,
        dWillSettleDiscount));
    // ���û���ۿۣ�����ﵥ�ۣ����ۣ���������
    if (MathTool.compareTo(dWillSettleDiscount, UFDouble.ZERO_DBL) == 0) {
      calData.setNgoodsprice(calData.getNprice());
    }
    else {
      calData.setNgoodsprice(ScaleUtils.getScaleUtilAtBS()
          .adjustSoPuPriceScale(calData.getNgoodsmoney().div(dWillSettleNum),
              this.getSettleEnv().getOrgCurr()));
    }
    // ��Ʊ���ɱ�Ҫ�ء��ۿ�
    calData.setNaFactor(daWillSettleFactor);
    calData.setNdiscount(dWillSettleDiscount);

    // �Ժ�����ģ�������д���
    MatchMergeReasonWasteNumRule reasonRule =
        new MatchMergeReasonWasteNumRule(this.getSettleEnv(), voInvoice,
            calData);
    calData =
        reasonRule.calReasonWasteNum(dActualInvoiceSettleNum, dWillSettleNum,
            inCreaseNum);
    return calData;

  }

  /**
   * �����ⵥ�ͷ�Ʊ�ܷ�ƥ��
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @param voStock ��ⵥ
   * @param voInvoice ��Ʊ
   * @return <p>
   * @author wangyf
   * @time 2010-1-20 ����10:49:51
   */
  protected boolean canMatch(InvoiceSettleVO voInvoice, StockSettleVO voStock) {

    // ĳһ��������ɲ��ٽ���
    if (InvoiceSettleVOUtil.isCurrentSettleFinished(voInvoice)
        || StockSettleVOUtil.isCurrentSettleFinished(voStock)) {
      return false;
    }

    // �Զ����㡢�����Ͻ��㡢ͬ���Ͻ��㹲�úϲ��㷨
    // ----�ɹ���ⵥ�������ݹ�Ӧ��������ȷ��Ӧ����������òɹ���ⵥ���Ѵ�Ӧ���Ĳɹ���Ʊ���н��㣻
    // ----�ɹ���ⵥ��ȷ��Ӧ�����������������ɹ���ⵥ���ֲ�ͬ�Ĳɹ���Ʊ���н��㡣

    // ������״̬�ر�Ӱ���Ʊ������ع���
    // 26. ���ն������߷����ƵĲɹ���ⵥ���ɵĲɹ���Ʊ�������������ɹ���������ⵥ���н��㣻
    // 27. ���ƵĲɹ���Ʊ���߲������ƵĲɹ���ⵥ���ɵĲɹ���Ʊ����������Ĳɹ���ⵥ���н��㡣

    // ��Ʊ�Ƕ����ķ�Ʊ����ֻ��ƥ��˶�������ⵥ
    if (!PubAppTool.isEqual(voInvoice.getPk_order_b(), null)
        && !PubAppTool.isEqual(voInvoice.getPk_order_b(),
            voStock.getPk_order_b())) {
      return false;
    }

    // �ɹ���ⵥ��ȷ��Ӧ�����������������ɹ���ⵥ���ֲ�ͬ�Ĳɹ���Ʊ���н��㡣
    if (EnumToAPFlag.ConfirmToAP.value().equals(voStock.getFdirtoaptype())
        && !PubAppTool.isEqual(voStock.getCorigcurrencyid(),
            voInvoice.getCorigcurrencyid())) {
      return false;
    }

    // �ɹ���ⵥ�������ݹ�Ӧ��������ȷ��Ӧ����������òɹ���ⵥ���Ѵ�Ӧ���Ĳɹ���Ʊ���н��㣻
    if (!EnumToAPFlag.NotToAP.value().equals(voStock.getFdirtoaptype())
        && ValueUtils.getBoolean(voInvoice.getBapflag())) {
      return false;
    }

    return true;
  }

  /**
   * ���ɷ�Ʊ����ITEM
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @param voInvoice
   * @param dWillSettleNum
   * @param rowflag �б�־
   * @return <p>
   * @author wangyf
   * @time 2010-3-24 ����05:01:37
   */
  protected SettleBillItemVO createItemByInvoice(InvoiceSettleVO voInvoice,
      UFDouble dWillSettleNum, EnumMatchRowType rowflag) {

    // �����������Ҫ����Ĳ���
    BillItemCalData calData =
        this.calItemData(voInvoice, dWillSettleNum, dWillSettleNum, null);

    // --------------- �����ѽ���ķ�Ʊ�����VO
    // ���·�Ʊ���ۼƽ����������ܽ���Ʊ���
    // ���ν���ķ�Ʊ�����������������������������Ϊ���ȫ�������ˡ�
    voInvoice.setNcurrentaccumsettlenum(MathTool.add(
        voInvoice.getNcurrentaccumsettlenum(), dWillSettleNum));
    voInvoice.setNcurrentaccumtotalsettlemny(MathTool.add(
        voInvoice.getNcurrentaccumtotalsettlemny(), calData.getNmoney()));
    voInvoice.setNcurrentaccuminvoicesettlemny(MathTool.add(voInvoice
        .getNcurrentaccuminvoicesettlemny(),
        calData.getNgoodsmoney().sub(MathTool.nvl(calData.getNdiscount()))));

    // --------------- �γɽ��㵥VO
    SettleBillItemVO voBillItem = new SettleBillItemVO();
    voBillItem.setFrowtype((Integer) rowflag.value());
    // ȡ�Լ�����
    voBillItem.setNsettlenum(dWillSettleNum);
    // ���û����������磬��ֱ��ȡ��Ʊ���ۣ����򣽽��/����
    // ��������δ��������ͬ�����ҵ��۷ǿգ�����ʱ����������
    voBillItem.setNprice(calData.getNprice());
    voBillItem.setNmoney(calData.getNmoney());
    voBillItem.setNgoodsmoney(calData.getNgoodsmoney());
    voBillItem.setNgoodsprice(calData.getNgoodsprice());
    // ������ɱ�Ҫ�ء��ۿ�
    ICostfactorDiscountUtil.setNcostfactor(voBillItem, calData.getNaFactor());
    voBillItem.setNdiscount(calData.getNdiscount());
    // �����������
    voBillItem.setNreasonalwastnum(calData.getNReasonWasteNum());
    // ������ĵ���Ϊ��Ʊ����,������Ľ��=�����������*������ĵ���
    voBillItem.setNreasonalwastprice(calData.getNReasonWastePrice());
    voBillItem.setNreasonalwastmny(calData.getNReasonWasteMny());

    // ȡ�Է�Ʊ
    voBillItem.setPk_group(voInvoice.getPk_group());
    voBillItem.setPk_org(voInvoice.getPk_org());
    voBillItem.setVinvoicecode(voInvoice.getVbillcode());
    voBillItem.setPk_invoice(voInvoice.getPk_invoice());
    voBillItem.setPk_invoice_b(voInvoice.getPk_invoice_b());
    voBillItem.setVinvoicetrantype(voInvoice.getCtrantypeid());
    // ֱ�������ĳɱ����ȡ
    if (EnumMatchRowType.InvoiceDTransPO.equals(rowflag)) {
      String costregion =
          this.getCostRegionForDTrans(voInvoice.getPk_org(),
              voInvoice.getPk_stockorg(), voInvoice.getPk_stordoc());
      if (StringUtils.isEmpty(costregion)) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004060_0", "04004060-0110")/*
                                                                     * @res
                                                                     * "ֱ���������㣬���ݷ�Ʊ��[�����֯���ֿ⣬������֯]δƥ�䵽�ɱ��򣡷�Ʊ�ţ�"
                                                                     */
            + voInvoice.getVbillcode());
      }
      voBillItem.setPk_costregion(costregion);
      // ֱ�������ʱ�Ĳֿ⸳ֵ
      voBillItem.setPk_stordoc(voInvoice.getPk_stordoc());
    }
    else {
      voBillItem.setPk_costregion(voInvoice.getPk_costregion());
    }
    voBillItem.setPk_material(voInvoice.getPk_material());
    voBillItem.setPk_srcmaterial(voInvoice.getPk_srcmaterial());
    voBillItem.setPk_arrstockorg(voInvoice.getPk_stockorg());
    voBillItem.setPk_arrstockorg_v(voInvoice.getPk_stockorg_v());
    voBillItem.setPk_supplier(voInvoice.getPk_supplier());
    voBillItem.setPk_dept(voInvoice.getPk_dept());
    voBillItem.setPk_dept_v(voInvoice.getPk_dept_v());
    voBillItem.setPk_psndoc(voInvoice.getPk_bizpsn());
    voBillItem.setPk_invoiceorder(voInvoice.getPk_order());
    voBillItem.setPk_invoiceorder_b(voInvoice.getPk_order_b());

    // mengjian by 20141203��¼��Ʊ����
    voBillItem.setInvoicebilldate(voInvoice.getDbilldate());
    //added by wangzhqf 2015-03-24 15:10 ��¼���ں�ͬ��
    voBillItem.setVitctcode(voInvoice.getVctcode());
    return voBillItem;
  }

  protected DataClassify[] getDataClassify() {
    return this.m_dataClassify;
  }

  /**
   * ������ⵥ��ID������������<br>
   * ��������ⵥ����������ʱ���Ὣ���������ŵ����ɽ�����������ⵥ�����һ�ν�������
   * 
   * @param pk_srcmaterial
   * @return
   */
  protected UFDouble getIncreaseNum(String pk_srcmaterial) {
    return null;
  }

  /**
   * ��Ʊ��ⵥ����
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @param paraListInvoice
   * @param paraListStock
   * @param numType �����Ǹ�����
   * @return <p>
   * @author wangyf
   * @time 2010-2-5 ����10:25:00
   */
  protected ArrayList<SettleBillItemVO> mergeInvoiceStock(DataClassify data,
      int numType) {

    ArrayList<InvoiceSettleVO> paraListInvoice =
        GoodsMatchMerge.CombineType_PlusInvoiceStock == numType ? data
            .getPlusInvoices() : data.getMinusInvoices();
    ArrayList<StockSettleVO> paraListStock =
        GoodsMatchMerge.CombineType_PlusInvoiceStock == numType ? data
            .getPlusStockes() : data.getMinusStockes();

    if (paraListInvoice == null || paraListInvoice.size() == 0
        || paraListStock == null || paraListStock.size() == 0) {
      return null;
    }

    boolean bPlusSettle =
        numType == GoodsMatchMerge.CombineType_PlusInvoiceStock;

    // ��Ʊ������ʣ�����������С��������
    InvoiceSettleVOUtil.calResidualSettleNum(paraListInvoice);
    ArrayList<InvoiceSettleVO> listInvoice =
        (ArrayList<InvoiceSettleVO>) this.sortByUFDoubleKey(paraListInvoice,
            InvoiceSettleVO.NRESIDUALSETTLENUM, bPlusSettle);
    ArrayList<StockSettleVO> listStock = paraListStock;

    StockSettleVO voStock = null;
    InvoiceSettleVO voInvoice = null;
    // ������кϲ���Ľ�����
    ArrayList<SettleBillItemVO> listItemVO = new ArrayList<SettleBillItemVO>();
    // �����ʱ�Ľ��㵥
    SettleBillItemVO voTempItem = null;
    HashMap<Integer, Integer> hmapStockFinishIndex =
        new HashMap<Integer, Integer>();

    for (int i = 0; i < listInvoice.size(); i++) {
      // ������е���ⵥ����������ˣ�������ѭ��
      if (hmapStockFinishIndex.size() == listStock.size()) {
        break;
      }
      voInvoice = listInvoice.get(i);

      // ------------1��ʣ�����������С��������
      StockSettleVOUtil.calResidualSettleNum(listStock);
      listStock =
          (ArrayList<StockSettleVO>) this.sortByUFDoubleKey(listStock,
              StockSettleVO.NRESIDUALSETTLENUM, bPlusSettle);

      // ÿ�ζ��ӿ�浥�ݵ�ͷѭ��
      for (int j = 0; j < listStock.size(); j++) {
        voStock = listStock.get(j);

        // �����浥���ѽ����꣬����˵�
        if (StockSettleVOUtil.isCurrentSettleFinished(voStock)) {
          continue;
        }

        // ------------2��ƥ���浥��
        // û��ƥ�䵽��浥��
        if (!this.canMatch(voInvoice, voStock)) {
          continue;
        }

        // ------------3���γɽ��㵥��
        if (bPlusSettle) {
          voTempItem =
              this.createItemByInvoiceStock(voInvoice,
                  voStock,
                  // �����浥�ݡ���Ʊ���������һ���ҿ�浥�ݱ���ƥ�����
                  this.getSettleEnv().isStockHaveToMatchAll()
                      && this.isLastMatch(listInvoice, listStock,
                          Integer.valueOf(i), Integer.valueOf(j)) ? true
                      : false);
        }
        else {
          voTempItem = this.createItemByInvoiceStock(voInvoice, voStock, false);
        }
        listItemVO.add(voTempItem);

        // ------------4���ϲ����������ʣ�࣬��������ѽ�����ɵ��б�
        if (StockSettleVOUtil.isCurrentSettleFinished(voStock)) {
          hmapStockFinishIndex.put(Integer.valueOf(j), null);
        }
        if (InvoiceSettleVOUtil.isCurrentSettleFinished(voInvoice)) {
          // ��Ʊ����ѽ�����ɣ����������һ�ŷ�Ʊ
          break;
        }
      }
    }

    // �����û�����
    paraListInvoice.clear();
    for (int i = 0; i < listInvoice.size(); i++) {
      if (InvoiceSettleVOUtil.isCurrentSettleFinished(listInvoice.get(i))) {
        data.getFinishedInvoices().add(listInvoice.get(i));
      }
      else {
        paraListInvoice.add(listInvoice.get(i));
      }
    }
    paraListStock.clear();
    for (int i = 0; i < listStock.size(); i++) {
      if (StockSettleVOUtil.isCurrentSettleFinished(listStock.get(i))) {
        data.getFinishedStockes().add(listStock.get(i));
      }
      else {
        paraListStock.add(listStock.get(i));
      }
    }

    return listItemVO.size() == 0 ? null : listItemVO;
  }

  /**
   * DataClassify��SET
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @param dataClassify <p>
   * @author wangyf
   * @time 2010-3-26 ����10:01:09
   */
  protected void setDataClassify(DataClassify[] dataClassify) {
    this.m_dataClassify = dataClassify;
  }
}
