/**
 * $�ɹ�����ʱ����ƥ�������ʾ��VO�Ĺ�������$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-17 ����01:16:59
 */
package nc.vo.pu.m27.entity;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.enumeration.EnumSettleType;
import nc.vo.pu.m4201.enumeration.EnumToIAFlag;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>�ɹ�����ʱ����ƥ�������ʾ��VO�Ĺ���ʹ�÷���������װMatchMaterialVO�����MatchMaterialVO��</b>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-1-17 ����01:16:59
 */
public class MatchMaterialVOUtil {

  /**
   * ������������������ⵥ����Ʊ��װMatchMaterialVO������ƥ����ʾ��
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author tianft
   * @param stype
   * @time 2010-1-18 ����09:33:31
   */
  public static MatchMaterialVO[] assembleMatchMaterialVO(
      StockSettleVO[] stockSettleVOs, InvoiceSettleVO[] invoiceSettleVOs,
      EnumSettleType stype) {

    if (ArrayUtils.isEmpty(stockSettleVOs)
        && ArrayUtils.isEmpty(invoiceSettleVOs)) {
      return null;
    }
    MatchMaterialVO[] resultVOs = null;
    MatchMaterialVO[] temp = null;
    if (!ArrayUtils.isEmpty(stockSettleVOs)) {
      resultVOs =
          MatchMaterialVOUtil.assembleMatchMaterialVOByStock(stockSettleVOs,
              stype);
    }
    if (!ArrayUtils.isEmpty(invoiceSettleVOs)) {
      temp =
          MatchMaterialVOUtil
              .assembleMatchMaterialVOByInvoice(invoiceSettleVOs);
    }
    resultVOs = (MatchMaterialVO[]) ArrayUtils.addAll(resultVOs, temp);
    if (resultVOs.length == 0) {
      return null;
    }
    return resultVOs;
  }

  /**
   * ���ݷ�Ʊ������װMatchMaterialVO��
   * <p>
   * <b>����˵��</b>
   * 
   * @param invoiceSettleVOs - ��Ʊ���ݣ���ƽ��vo��
   * @return MatchMaterialVO[]
   *         <p>
   * @since 6.0
   * @author tianft
   * @time 2010-1-19 ����01:06:31
   */
  public static MatchMaterialVO[] assembleMatchMaterialVOByInvoice(
      InvoiceSettleVO[] invoiceSettleVOs) {
    if (ArrayUtils.isEmpty(invoiceSettleVOs)) {
      return null;
    }

    List<MatchMaterialVO> resultVOs = new ArrayList<MatchMaterialVO>();
    MatchMaterialVO matchVO = null;

    for (int i = 0; i < invoiceSettleVOs.length; i++) {
      matchVO = new MatchMaterialVO();
      matchVO.setVtrantypecode(invoiceSettleVOs[i].getVtrantypecode());
      matchVO.setCtrantypeid(invoiceSettleVOs[i].getCtrantypeid());
      matchVO.setBillcode(invoiceSettleVOs[i].getVbillcode());
      matchVO.setPk_billid(invoiceSettleVOs[i].getPk_invoice());
      matchVO.setPk_billbid(invoiceSettleVOs[i].getPk_invoice_b());
      // added by wangzhqf 2013��10��14��15:36:11
      matchVO.setPk_order(invoiceSettleVOs[i].getPk_order());
      matchVO.setPk_order_b(invoiceSettleVOs[i].getPk_order_b());
      matchVO.setVordercode(invoiceSettleVOs[i].getVordercode());
      matchVO.setVctcode(invoiceSettleVOs[i].getVctcode());
      // added by wangzhqf 2013��10��14��15:36:11
      matchVO.setPk_material(invoiceSettleVOs[i].getPk_material());
      matchVO.setCcurrencyid(invoiceSettleVOs[i].getCcurrencyid());
      matchVO.setCunitid(invoiceSettleVOs[i].getCcurrencyid());
      matchVO.setPk_srcmaterial(invoiceSettleVOs[i].getPk_srcmaterial());
      matchVO.setCunitid(invoiceSettleVOs[i].getCunitid());// ������λ
      matchVO.setCproductorid(invoiceSettleVOs[i].getCproductorid()); // ��������
      matchVO.setCprojectid(invoiceSettleVOs[i].getCprojectid()); // ��Ŀ
      matchVO.setVfree1(invoiceSettleVOs[i].getVfree1());
      matchVO.setVfree2(invoiceSettleVOs[i].getVfree2());
      matchVO.setVfree3(invoiceSettleVOs[i].getVfree3());
      matchVO.setVfree4(invoiceSettleVOs[i].getVfree4());
      matchVO.setVfree5(invoiceSettleVOs[i].getVfree5());
      matchVO.setVfree6(invoiceSettleVOs[i].getVfree6());
      matchVO.setVfree7(invoiceSettleVOs[i].getVfree7());
      matchVO.setVfree8(invoiceSettleVOs[i].getVfree8());
      matchVO.setVfree9(invoiceSettleVOs[i].getVfree9());
      matchVO.setVfree10(invoiceSettleVOs[i].getVfree10());
      matchVO.setNcansettlemny(invoiceSettleVOs[i].getNcansettlemny());// δ������
      matchVO.setNcansettlenum(invoiceSettleVOs[i].getNcansettlenum());// δ��������
      matchVO.setNprice(invoiceSettleVOs[i].getNprice());// ���ۣ���Ʊ�Ƴɱ�����
      matchVO.setNcurinvoicesettlenum(invoiceSettleVOs[i].getNcansettlenum());// ���η�Ʊ��������
      // ��Ʊ��ʾ
      matchVO.setNcurinvoicesettlemny(invoiceSettleVOs[i].getNcansettlemny());// ���η�Ʊ���ҽ�����
      matchVO.setNcurseetlemny(invoiceSettleVOs[i].getNcansettlemny());// ���ν�����
      // δ������
      matchVO.setNreasonwastenum(invoiceSettleVOs[i].getNreasonwastenum());// �����������
      matchVO.setBinvoice(UFBoolean.TRUE);
      matchVO.setBstock(UFBoolean.FALSE);
      matchVO.setInvoiceSettleVO(invoiceSettleVOs[i]);
      resultVOs.add(matchVO);
    }

    if (resultVOs.size() == 0) {
      return null;
    }

    return resultVOs.toArray(new MatchMaterialVO[resultVOs.size()]);
  }

  /**
   * ������ⵥ������װMatchMaterialVO��
   * <p>
   * <b>����˵��</b>
   * 
   * @param stockSettleVOs - ��ⵥ���ݣ���ƽ��vo��
   * @return MatchMaterialVO[]
   *         <p>
   * @since 6.0
   * @author tianft
   * @time 2010-1-19 ����12:39:10
   */
  public static MatchMaterialVO[] assembleMatchMaterialVOByStock(
      StockSettleVO[] stockSettleVOs, EnumSettleType stype) {
    if (ArrayUtils.isEmpty(stockSettleVOs)) {
      return null;
    }

    List<MatchMaterialVO> resultVOs = new ArrayList<MatchMaterialVO>();
    MatchMaterialVO matchVO = null;

    for (int i = 0; i < stockSettleVOs.length; i++) {
      matchVO = new MatchMaterialVO();
      matchVO.setVtrantypecode(stockSettleVOs[i].getVtrantypecode());
      matchVO.setCtrantypeid(stockSettleVOs[i].getCtrantypeid());
      matchVO.setBillcode(stockSettleVOs[i].getVbillcode());
      matchVO.setPk_billid(stockSettleVOs[i].getPk_stockps());
      matchVO.setPk_billbid(stockSettleVOs[i].getPk_stockps_b());
      // added by wangzhqf 2013��10��14��15:36:11
      matchVO.setPk_order(stockSettleVOs[i].getPk_order());
      matchVO.setPk_order_b(stockSettleVOs[i].getPk_order_b());
      matchVO.setVordercode(stockSettleVOs[i].getVordercode());
      matchVO.setVctcode(stockSettleVOs[i].getVctcode());
      // added by wangzhqf 2013��10��14��15:36:11
      matchVO.setPk_material(stockSettleVOs[i].getPk_material());
      matchVO.setCcurrencyid(stockSettleVOs[i].getCcurrencyid());
      matchVO.setPk_srcmaterial(stockSettleVOs[i].getPk_srcmaterial());

      matchVO.setCunitid(stockSettleVOs[i].getCunitid());// ������λ

      matchVO.setCproductorid(stockSettleVOs[i].getCproductorid()); // ��������
      matchVO.setCprojectid(stockSettleVOs[i].getCprojectid()); // ��Ŀ

      matchVO.setVfree1(stockSettleVOs[i].getVfree1());
      matchVO.setVfree2(stockSettleVOs[i].getVfree2());
      matchVO.setVfree3(stockSettleVOs[i].getVfree3());
      matchVO.setVfree4(stockSettleVOs[i].getVfree4());
      matchVO.setVfree5(stockSettleVOs[i].getVfree5());
      matchVO.setVfree6(stockSettleVOs[i].getVfree6());
      matchVO.setVfree7(stockSettleVOs[i].getVfree7());
      matchVO.setVfree8(stockSettleVOs[i].getVfree8());
      matchVO.setVfree9(stockSettleVOs[i].getVfree9());
      matchVO.setVfree10(stockSettleVOs[i].getVfree10());

      matchVO.setNstockinnum(stockSettleVOs[i].getNinnum());// �������
      // δ������( �ݹ�δ�������)
      matchVO.setNcansettlemny(stockSettleVOs[i].getNcansettlemny());
      matchVO.setNcansettlenum(stockSettleVOs[i].getNcansettlenum());// δ��������
      if (EnumToIAFlag.EstimateToIA.value().equals(
          stockSettleVOs[i].getFdirtoiatype())) {
        matchVO.setNprice(stockSettleVOs[i].getNestcalcostprice());// �ݹ�����
      }
      else if(EnumToIAFlag.ConfirmToIA.value().equals(
          stockSettleVOs[i].getFdirtoiatype())){
        matchVO.setNprice(stockSettleVOs[i].getNcalcostprice());// �ɱ�����
      }
      else if(EnumToIAFlag.NotToIA.value().equals(
          stockSettleVOs[i].getFdirtoiatype())){
        matchVO.setNprice((UFDouble)stockSettleVOs[i]
            .getAttributeValue(StockSettleVO.NCOSTPRICE));// ��ⵥ�ĳɱ���
      }
      matchVO.setNcurstocksettlenum(stockSettleVOs[i].getNcansettlenum());// ��������������
      //
      // Ĭ�϶�Ӧδ��������
      matchVO.setNnoestsettlenum(stockSettleVOs[i].getNpuresettlenum());// δ�ݹ��ѽ�������
      matchVO.setNsettleavgprice(stockSettleVOs[i].getNavgsettleprice());// ����ƽ������
      matchVO.setNnoestsettlemny(stockSettleVOs[i].getNpuresettlemny());// δ�ݹ��ѽ�����
      matchVO.setNaccumestnum(stockSettleVOs[i].getNestnum());// �ۼ��ݹ�����

      // matchVO.setNaccumestmny(stockSettleVOs[i].getNestmny());// �ۼ��ݹ����
      // wuxla v61
      matchVO.setNaccumestmny(stockSettleVOs[i].getNestcalcostmny());// �ۼ��ݹ����
      matchVO.setNestprice(stockSettleVOs[i].getNestcalcostprice());// �ݹ�����
      matchVO.setNestnum(stockSettleVOs[i].getNestnum());// �ݹ�����
      // matchVO.setNestmny(stockSettleVOs[i].getNestmny());// �ݹ����
      // wuxla v61
      matchVO.setNestmny(stockSettleVOs[i].getNestcalcostmny());// �ݹ����

      matchVO.setBinvoice(UFBoolean.FALSE);
      matchVO.setBstock(UFBoolean.TRUE);
      matchVO.setStockSettleVO(stockSettleVOs[i]);

      if (EnumSettleType.WITHOUT_INVOICE == stype) {
        // Ϊ�޷�Ʊ����������⴦��
        MatchMaterialVOUtil.procWithoutInvcMatch(matchVO, stockSettleVOs[i]);
      }
      else if (EnumSettleType.FEE == stype) {
        // Ϊ���ý���������⴦��
        MatchMaterialVOUtil.procFeeMatch(matchVO);
      }

      resultVOs.add(matchVO);
    }

    if (resultVOs.size() == 0) {
      return null;
    }

    return resultVOs.toArray(new MatchMaterialVO[resultVOs.size()]);
  }

  private static void procFeeMatch(MatchMaterialVO mmVo) {
    mmVo.setNcurstocksettlenum(null);// ���ý��㲻��Ҫ���ν�������
  }

  private static void procWithoutInvcMatch(MatchMaterialVO mmVo,
      StockSettleVO ssVo) {
    if (MathTool.isZero(ssVo.getNestcalcostprice())) {
      mmVo.setNprice(ssVo.getNcalcostprice());// δ�ݹ���ȡ��ⵥ��
    }

    // ���㱾�ν�����
    UFDouble settlmny = null;
    if (!MathTool.isZero(mmVo.getNcansettlemny())) {
      settlmny = mmVo.getNcansettlemny();
    }
    else if (MathTool.compareTo(ssVo.getNinnum(), mmVo.getNcurstocksettlenum()) == 0) {
      // wuxla v61
      settlmny = ssVo.getNcalcostmny();
    }
    else {
      settlmny =
          MathTool.nvl(mmVo.getNcurstocksettlenum()).multiply(
              MathTool.nvl(mmVo.getNprice()));
    }
    mmVo.setNcurseetlemny(settlmny);
  }
}
