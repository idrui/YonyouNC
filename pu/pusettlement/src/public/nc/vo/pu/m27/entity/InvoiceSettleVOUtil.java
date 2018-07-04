package nc.vo.pu.m27.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.pubitf.pu.m25.pu.settle.IInvoiceSettleQuery;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.enumeration.InvoiceRowType;
import nc.vo.pu.m25.pub.InvoiceVOUtil;
import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.pub.util.ListUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

public class InvoiceSettleVOUtil {

  /**
   * ����δ���������
   * <p>
   * <b>����˵��</b>
   * 
   * @param invoiceSettleVOs <p>
   * @since 6.0
   * @author tianft
   * @time 2010-1-28 ����08:00:37
   */
  public static void calInvoiceCanSettleValue(InvoiceSettleVO[] invoiceSettleVOs) {
    if (ArrayUtils.isEmpty(invoiceSettleVOs)) {
      return;
    }
    ScaleUtils su = new ScaleUtils(AppContext.getInstance().getPkGroup());
    for (int i = 0; i < invoiceSettleVOs.length; i++) {
      // V61 wuxla ��Ʊ�����ν��������ȡ��Ʊ�ı�����˰������Ϊȡ��Ʊ�ļƳɱ���
      // ����:����Ƴɱ����=������˰��ȡ��Ʊ������˰���ۣ�����ȡ�Ƴɱ����/��Ʊ������
      if (InvoiceRowType.GOODS_ROW == invoiceSettleVOs[i].getFrowtype()
          .intValue()
          && MathTool.compareTo(invoiceSettleVOs[i].getNmny(),
              invoiceSettleVOs[i].getNcalcostmny()) != 0
          && MathTool.compareTo(invoiceSettleVOs[i].getNnum(),
              UFDouble.ZERO_DBL) != 0) {// 2012-04-26
                                        // tianft�����ڻ��﷢Ʊ������Ϊ0��û������ģ����ǵñ�֤��ѯû����
        UFDouble nprice =
            su.adjustSoPuPriceScale(
                invoiceSettleVOs[i].getNcalcostmny().div(
                    invoiceSettleVOs[i].getNnum()),
                invoiceSettleVOs[i].getCorigcurrencyid());
        invoiceSettleVOs[i].setNprice(nprice);
      }
      invoiceSettleVOs[i].setNmny(invoiceSettleVOs[i].getNcalcostmny());

      // δ�������� = ��Ʊ���� - �ۼƽ�������
      invoiceSettleVOs[i]
          .setNcansettlenum(MathTool.sub(invoiceSettleVOs[i].getNnum(),
              invoiceSettleVOs[i].getNaccumsettnum()));
      // δ������ = ��Ʊ��� - �ۼƽ�����
      invoiceSettleVOs[i]
          .setNcansettlemny(MathTool.sub(invoiceSettleVOs[i].getNmny(),
              invoiceSettleVOs[i].getNaccumsettmny()));
      // ���ν������� = δ��������
      invoiceSettleVOs[i].setNcurrentsettlenum(invoiceSettleVOs[i]
          .getNcansettlenum());
      // ���ν����� = δ������
      invoiceSettleVOs[i].setNcurrentinvoicesettlemny(invoiceSettleVOs[i]
          .getNcansettlemny());
      // ���ν����ܽ��
      invoiceSettleVOs[i].setNcurrentotalsettlemny(invoiceSettleVOs[i]
          .getNcansettlemny());
      // �����������(���ۼƽ���ķ�Ʊ�������δ��д������V60ֻ�е�һ�ν���ʱ�Ŵ���ȥ�������)
      if (!MathTool.isZero(invoiceSettleVOs[i].getNaccumsettmny())) {
        invoiceSettleVOs[i].setNreasonwastenum(null);
      }
    }
  }

  /**
   * ���㱾�ν��㻹ʣ�¶���δ���㣬����䷢Ʊ��Nresidualsettlenum
   * <p>
   * <b>����˵��</b>
   * 
   * @param listInvoice <p>
   * @author wangyf
   * @time 2010-3-23 ����02:24:49
   */
  public static void calResidualSettleNum(ArrayList<InvoiceSettleVO> listInvoice) {
    if (ListUtil.isEmpty(listInvoice)) {
      return;
    }
    for (InvoiceSettleVO vo : listInvoice) {
      vo.setNresidualsettlenum(MathTool.sub(
          InvoiceSettleVOUtil.getCurrentRealSettleNum(vo),
          vo.getNcurrentaccumsettlenum()));
    }
  }

  /**
   * �ӷ�ƱVOת���������VO����������ú��ۿ�<br>
   * ���ﲻ�ٵ���̨��ѯ��ֱ��ת����ֱ��ʹ��ȫVO��Ϊ��ͼVO
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @param voOrgInvoice ��ƱVO
   * @return <p>
   * @author wangyf
   * @time 2009-7-2 ����04:43:53
   */
  public static InvoiceSettleVO[] convertFromInvoice(InvoiceVO voOrgInvoice) {
    List<InvoiceSettleVO> listMaterial = new ArrayList<InvoiceSettleVO>();
    for (int i = 0; i < voOrgInvoice.getChildrenVO().length; i++) {
      InvoiceItemVO voItem = voOrgInvoice.getChildrenVO()[i];
      InvoiceHeaderVO voHeader = voOrgInvoice.getParentVO();

      InvoiceSettleVO voWillSettle = null;
      if (voItem.getFrowtype().intValue() == InvoiceRowType.GOODS_ROW) {// ����
        voWillSettle = new InvoiceSettleVO();
        voWillSettle.setVO(voHeader);
        voWillSettle.setVO(voItem);
        listMaterial.add(voWillSettle);
      }

    }
    InvoiceSettleVO[] ssVos =
        listMaterial.toArray(new InvoiceSettleVO[listMaterial.size()]);
    InvoiceSettleVOUtil.calInvoiceCanSettleValue(ssVos);
    return ssVos;
  }

  /**
   * �ֹ����㣬�ӽ����VOת����Ʊ����VO
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @param voaMatch
   * @return <p>
   * @author wangyf
   * @time 2010-1-28 ����09:46:14
   */
  public static InvoiceSettleVO[] convertFromMatchMaterialVO(
      MatchMaterialVO[] voaMatch) {

    if (voaMatch == null || voaMatch.length == 0) {
      return null;
    }

    ArrayList<InvoiceSettleVO> listVO = new ArrayList<InvoiceSettleVO>();
    int iLen = voaMatch.length;
    for (int i = 0; i < iLen; i++) {
      if (ValueUtils.getBoolean(voaMatch[i].getBinvoice())) {
        // �õ�ԭʼ��VO
        InvoiceSettleVO voDest =
            (InvoiceSettleVO) voaMatch[i].getInvoiceSettleVO().clone();

        // ����ӽ���õ�������
        // ���η�Ʊ�������������η�Ʊ����������������
        voDest.setNcurrentsettlenum(voaMatch[i].getNcurinvoicesettlenum());
        voDest.setNcurrentinvoicesettlemny(voaMatch[i]
            .getNcurinvoicesettlemny());
        voDest.setNreasonwastenum(voaMatch[i].getNreasonwastenum());
        // �ۿۡ��ɱ�Ҫ��1-8
        voDest.setNdiscount(voaMatch[i].getNdiscount());
        voDest.setNcostfactor1(voaMatch[i].getNcostfactor1());
        voDest.setNcostfactor2(voaMatch[i].getNcostfactor2());
        voDest.setNcostfactor3(voaMatch[i].getNcostfactor3());
        voDest.setNcostfactor4(voaMatch[i].getNcostfactor4());
        voDest.setNcostfactor5(voaMatch[i].getNcostfactor5());
        voDest.setNcostfactor6(voaMatch[i].getNcostfactor6());
        voDest.setNcostfactor7(voaMatch[i].getNcostfactor7());
        voDest.setNcostfactor8(voaMatch[i].getNcostfactor8());
        // ������� ���add by liangchen1
        voDest.setNadjustmny(voaMatch[i].getNadjustmny());
        // ���ν����ܽ��
        voDest.setNcurrentotalsettlemny(voaMatch[i].getNcurseetlemny());

        listVO.add(voDest);
      }
    }

    if (listVO.size() == 0) {
      return null;
    }

    return listVO.toArray(new InvoiceSettleVO[listVO.size()]);
  }

  /**
   * ����õ�����ʵ�ʿɽ�������
   * <ul>
   * <li>����Ʊ����Ϊ���ν������������η�Ʊ�ϵĺ����������</li><br>
   * <li>����Ʊ����Ϊ���ν�����������V5X�����˻���Ʊ��֧�ֺ�����Ľ���</li>
   * </ul>
   * 
   * @param vo
   * @return ����ʵ�ʿɽ�������
   */
  public static UFDouble getCurrentRealSettleNum(InvoiceSettleVO vo) {

    return MathTool.greaterThan(vo.getNcurrentsettlenum(), UFDouble.ZERO_DBL) ? MathTool
        .sub(vo.getNcurrentsettlenum(), vo.getNreasonwastenum()) : vo
        .getNcurrentsettlenum();
  }

  /**
   * �õ���Ʊ�Ľ����ѯ���ͣ������ڲɹ����㣬�����������Ļ��ܽ���
   * 
   * @param vo
   * @param defaultType ���Ƶ�Ĭ��Ϊ��������
   * @return
   * @see IInvoiceSettleQuery.QRY_TYPE_PO
   * @see IInvoiceSettleQuery.QRY_TYPE_VMI
   */
  public static String getQueryType(InvoiceVO vo, String defaultType) {
    if (InvoiceVOUtil.isSelfMade(vo)) {
      return defaultType;
    }
    for (InvoiceItemVO item : vo.getChildrenVO()) {
      String srcTT = item.getCsourcetypecode();
      if (ICBillType.VmiSum.getCode().equals(srcTT)) {
        return IInvoiceSettleQuery.QRY_TYPE_VMI;
      }
    }
    return IInvoiceSettleQuery.QRY_TYPE_PO;
  }

  /**
   * �÷�Ʊ�Ƿ���й����÷�̯���ۿۣ�0 ���� �ɱ�Ҫ��1-5Ϊ��ʱ��δ���й���̯��
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @param voInvoice
   * @return <p>
   * @author wangyf
   * @time 2010-1-28 ����01:46:47
   */
  public static boolean isAllot(InvoiceSettleVO voInvoice) {
    return MathTool.compareTo(voInvoice.getNdiscount(), UFDouble.ZERO_DBL) != 0
        || MathTool.compareTo(voInvoice.getNcostfactor1(), UFDouble.ZERO_DBL) != 0
        || MathTool.compareTo(voInvoice.getNcostfactor2(), UFDouble.ZERO_DBL) != 0
        || MathTool.compareTo(voInvoice.getNcostfactor3(), UFDouble.ZERO_DBL) != 0
        || MathTool.compareTo(voInvoice.getNcostfactor4(), UFDouble.ZERO_DBL) != 0
        || MathTool.compareTo(voInvoice.getNcostfactor5(), UFDouble.ZERO_DBL) != 0
        // add by liangchen1 ��������
        || MathTool.compareTo(voInvoice.getNadjustmny(), UFDouble.ZERO_DBL) != 0;
  }

  /**
   * ��ǰ���������Ƿ������
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @param voStock
   * @return <p>
   * @author wangyf
   * @time 2010-2-5 ����10:29:19
   */
  public static boolean isCurrentSettleFinished(InvoiceSettleVO voInvoice) {
    if (UFBoolean.TRUE.equals(voInvoice.getBvirtual())
        && MathTool.isZero(voInvoice.getNmny())
        && MathTool.compareTo(voInvoice.getNcurrentaccumsettlenum(),
            voInvoice.getNnum()) != 0) {
      // ���ⷢƱ֧���������
      return false;
    }
    return MathTool.compareTo(voInvoice.getNcurrentotalsettlemny(),
        voInvoice.getNcurrentaccumtotalsettlemny()) == 0;
  }

  /**
   * ��Ʊ�Ƿ�δ��������
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @return true��Ʊ�����˶�����falseδ����������
   *         <p>
   * @author wangyf
   * @time 2010-1-19 ����10:43:35
   */
  public static boolean isOrderRelated(InvoiceSettleVO voSettle) {
    if (voSettle.getPk_order() == null
        || voSettle.getPk_order().trim().length() == 0) {
      return false;
    }

    return true;
  }

  /**
   * ��Ʊ�Ƿ�δ��������
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @return true��Ʊ�����˶�����falseδ����������
   *         <p>
   * @author wangyf
   * @time 2010-1-19 ����10:43:35
   */
  public static boolean isStockRelated(InvoiceSettleVO voSettle) {

    // 45�ɹ��롢47ί���롢4T�ڳ��ݹ���50VMI
    if (!StringUtil.isEmptyWithTrim(voSettle.getCsourcebid())
        && (voSettle.getCsourcetypecode().equals(
            ICBillType.PurchaseIn.getCode())
            || voSettle.getCsourcetypecode()
                .equals(ICBillType.VmiSum.getCode())
            || voSettle.getCsourcetypecode().equals(
                POBillType.InitEstimate.getCode()) || voSettle
            .getCsourcetypecode().equals(ICBillType.SubContinIn.getCode()))) {
      return true;
    }

    return false;
  }

  /**
   * ���������������ѷ����ۿ��෢Ʊ���ݴ�ƥ���������ģ����ͬ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param uiInvoices �����ϵ�����
   * @param modelFees ģ���еķ����෢Ʊ
   * @param modelDiscounts ģ���е��ۿ��෢Ʊ
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-10-3 ����11:18:07
   */
  public static void synchFeeAndDiscounts(FeeDiscountSettleVO[] uiInvoices,
      FeeDiscountSettleVO[] modelFees, FeeDiscountSettleVO[] modelDiscounts) {
    if (ArrayUtils.isEmpty(uiInvoices)) {
      return;
    }

    // ͬ����ǰModel�������ࡢ�ۿ��෢Ʊ�ĵ�ǰ������(����ʱ��ǰ̨���÷�̯ʱ����)
    Map<String, FeeDiscountSettleVO> feeAndDiscounts =
        new HashMap<String, FeeDiscountSettleVO>();
    if (!ArrayUtils.isEmpty(modelFees)) {
      for (FeeDiscountSettleVO modelFee : modelFees) {
        feeAndDiscounts.put(modelFee.getPk_invoice_b(), modelFee);
      }
    }
    if (!ArrayUtils.isEmpty(modelDiscounts)) {
      for (FeeDiscountSettleVO modelDiscount : modelDiscounts) {
        feeAndDiscounts.put(modelDiscount.getPk_invoice_b(), modelDiscount);
      }
    }

    for (FeeDiscountSettleVO uiInvoice : uiInvoices) {
      String bid = uiInvoice.getPk_invoice_b();
      UFDouble currMny = uiInvoice.getNcurrentinvoicesettlemny();
      FeeDiscountSettleVO modelData = feeAndDiscounts.get(bid);
      modelData.setNcurrentinvoicesettlemny(currMny);
    }
  }

  /**
   * ���������������ѷ����ۿ��෢Ʊ���ݴ�ƥ���������ģ����ͬ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param uiInvoices �����ϵ�����
   * @param modelFees ģ���еķ����෢Ʊ
   * @param modelDiscounts ģ���е��ۿ��෢Ʊ
   * @param modelAdjs ģ���еĵ�����Ʊ
   *          <p>
   * @since 6.0
   * @author wangzhqf
   * @time 2013��10��25��8:32:47
   */
  public static void synchFeeDiscountsAdjs(FeeDiscountSettleVO[] uiInvoices,
      FeeDiscountSettleVO[] modelFees, FeeDiscountSettleVO[] modelDiscounts,
      FeeDiscountSettleVO[] modelAdjs) {
    if (ArrayUtils.isEmpty(uiInvoices)) {
      return;
    }

    // ͬ����ǰModel�������ࡢ�ۿ��෢Ʊ��������Ʊ�ĵ�ǰ������(����ʱ��ǰ̨���÷�̯ʱ����)
    Map<String, FeeDiscountSettleVO> feeAndDiscountsAdjs =
        new HashMap<String, FeeDiscountSettleVO>();
    if (!ArrayUtils.isEmpty(modelFees)) {
      for (FeeDiscountSettleVO modelFee : modelFees) {
        feeAndDiscountsAdjs.put(modelFee.getPk_invoice_b(), modelFee);
      }
    }
    if (!ArrayUtils.isEmpty(modelDiscounts)) {
      for (FeeDiscountSettleVO modelDiscount : modelDiscounts) {
        feeAndDiscountsAdjs.put(modelDiscount.getPk_invoice_b(), modelDiscount);
      }
    }
    if (!ArrayUtils.isEmpty(modelAdjs)) {
      for (FeeDiscountSettleVO modelAdj : modelAdjs) {
        feeAndDiscountsAdjs.put(modelAdj.getPk_invoice_b(), modelAdj);
      }
    }

    for (FeeDiscountSettleVO uiInvoice : uiInvoices) {
      String bid = uiInvoice.getPk_invoice_b();
      UFDouble currMny = uiInvoice.getNcurrentinvoicesettlemny();
      FeeDiscountSettleVO modelData = feeAndDiscountsAdjs.get(bid);
      modelData.setNcurrentinvoicesettlemny(currMny);
    }
  }

  /**
   * ���������������ѷ�Ʊ���ݴ�ƥ���������ģ����ͬ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param uiMatchVos �����ϵ�MatchMaterialVO
   * @param modelMatchVos ģ���е�MatchMaterialVO
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-10-3 ����10:37:48
   */
  public static void synchInvoiceOfMatchMaterial(MatchMaterialVO[] uiMatchVos,
      MatchMaterialVO[] modelMatchVos) {
    if (ArrayUtils.isEmpty(uiMatchVos) || ArrayUtils.isEmpty(modelMatchVos)) {
      return;
    }

    Map<String, MatchMaterialVO> matchVoMap =
        new HashMap<String, MatchMaterialVO>();
    for (MatchMaterialVO modelMatchVo : modelMatchVos) {
      if (ValueUtils.getBoolean(modelMatchVo.getBinvoice())) {
        matchVoMap.put(modelMatchVo.getPk_billbid(), modelMatchVo);
      }
    }

    for (MatchMaterialVO uiMatchVo : uiMatchVos) {
      if (!ValueUtils.getBoolean(uiMatchVo.getBinvoice())) {
        continue;
      }
      // �õ�ģ���е�VO
      MatchMaterialVO modelMatchVo = matchVoMap.get(uiMatchVo.getPk_billbid());

      // ��ģ���е�ƥ��VO���õ���ƱVO
      InvoiceSettleVO invoice = modelMatchVo.getInvoiceSettleVO();

      // ����ӽ���õ�������
      // ���η�Ʊ�������������η�Ʊ����������������
      invoice.setNcurrentsettlenum(uiMatchVo.getNcurinvoicesettlenum());
      invoice.setNcurrentinvoicesettlemny(uiMatchVo.getNcurinvoicesettlemny());
      invoice.setNreasonwastenum(uiMatchVo.getNreasonwastenum());
      // �ۿۡ��ɱ�Ҫ��1-8
      invoice.setNdiscount(uiMatchVo.getNdiscount());
      invoice.setNcostfactor1(uiMatchVo.getNcostfactor1());
      invoice.setNcostfactor2(uiMatchVo.getNcostfactor2());
      invoice.setNcostfactor3(uiMatchVo.getNcostfactor3());
      invoice.setNcostfactor4(uiMatchVo.getNcostfactor4());
      invoice.setNcostfactor5(uiMatchVo.getNcostfactor5());
      invoice.setNcostfactor6(uiMatchVo.getNcostfactor6());
      invoice.setNcostfactor7(uiMatchVo.getNcostfactor7());
      invoice.setNcostfactor8(uiMatchVo.getNcostfactor8());
      // ������� add by liangchen1
      invoice.setNadjustmny(uiMatchVo.getNadjustmny());
      // ���ν����ܽ��
      invoice.setNcurrentotalsettlemny(uiMatchVo.getNcurseetlemny());
    }
  }
}
