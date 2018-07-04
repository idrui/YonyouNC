/**
 * $采购结算时用于匹配界面显示的VO的公共方法$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-17 下午01:16:59
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
 * <b>采购结算时用于匹配界面显示的VO的公共使用方法，如组装MatchMaterialVO，拆分MatchMaterialVO等</b>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-1-17 下午01:16:59
 */
public class MatchMaterialVOUtil {

  /**
   * 方法功能描述：由入库单、发票组装MatchMaterialVO，用于匹配显示。
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author tianft
   * @param stype
   * @time 2010-1-18 下午09:33:31
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
   * 根据发票数据组装MatchMaterialVO。
   * <p>
   * <b>参数说明</b>
   * 
   * @param invoiceSettleVOs - 发票数据（拉平的vo）
   * @return MatchMaterialVO[]
   *         <p>
   * @since 6.0
   * @author tianft
   * @time 2010-1-19 下午01:06:31
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
      // added by wangzhqf 2013年10月14日15:36:11
      matchVO.setPk_order(invoiceSettleVOs[i].getPk_order());
      matchVO.setPk_order_b(invoiceSettleVOs[i].getPk_order_b());
      matchVO.setVordercode(invoiceSettleVOs[i].getVordercode());
      matchVO.setVctcode(invoiceSettleVOs[i].getVctcode());
      // added by wangzhqf 2013年10月14日15:36:11
      matchVO.setPk_material(invoiceSettleVOs[i].getPk_material());
      matchVO.setCcurrencyid(invoiceSettleVOs[i].getCcurrencyid());
      matchVO.setCunitid(invoiceSettleVOs[i].getCcurrencyid());
      matchVO.setPk_srcmaterial(invoiceSettleVOs[i].getPk_srcmaterial());
      matchVO.setCunitid(invoiceSettleVOs[i].getCunitid());// 计量单位
      matchVO.setCproductorid(invoiceSettleVOs[i].getCproductorid()); // 生产厂商
      matchVO.setCprojectid(invoiceSettleVOs[i].getCprojectid()); // 项目
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
      matchVO.setNcansettlemny(invoiceSettleVOs[i].getNcansettlemny());// 未结算金额
      matchVO.setNcansettlenum(invoiceSettleVOs[i].getNcansettlenum());// 未结算数量
      matchVO.setNprice(invoiceSettleVOs[i].getNprice());// 单价，发票计成本单价
      matchVO.setNcurinvoicesettlenum(invoiceSettleVOs[i].getNcansettlenum());// 本次发票结算数量
      // 发票显示
      matchVO.setNcurinvoicesettlemny(invoiceSettleVOs[i].getNcansettlemny());// 本次发票本币结算金额
      matchVO.setNcurseetlemny(invoiceSettleVOs[i].getNcansettlemny());// 本次结算金额
      // 未结算金额
      matchVO.setNreasonwastenum(invoiceSettleVOs[i].getNreasonwastenum());// 合理损耗数量
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
   * 根据入库单数据组装MatchMaterialVO。
   * <p>
   * <b>参数说明</b>
   * 
   * @param stockSettleVOs - 入库单数据（拉平的vo）
   * @return MatchMaterialVO[]
   *         <p>
   * @since 6.0
   * @author tianft
   * @time 2010-1-19 下午12:39:10
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
      // added by wangzhqf 2013年10月14日15:36:11
      matchVO.setPk_order(stockSettleVOs[i].getPk_order());
      matchVO.setPk_order_b(stockSettleVOs[i].getPk_order_b());
      matchVO.setVordercode(stockSettleVOs[i].getVordercode());
      matchVO.setVctcode(stockSettleVOs[i].getVctcode());
      // added by wangzhqf 2013年10月14日15:36:11
      matchVO.setPk_material(stockSettleVOs[i].getPk_material());
      matchVO.setCcurrencyid(stockSettleVOs[i].getCcurrencyid());
      matchVO.setPk_srcmaterial(stockSettleVOs[i].getPk_srcmaterial());

      matchVO.setCunitid(stockSettleVOs[i].getCunitid());// 计量单位

      matchVO.setCproductorid(stockSettleVOs[i].getCproductorid()); // 生产厂商
      matchVO.setCprojectid(stockSettleVOs[i].getCprojectid()); // 项目

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

      matchVO.setNstockinnum(stockSettleVOs[i].getNinnum());// 入库数量
      // 未结算金额( 暂估未冲销金额)
      matchVO.setNcansettlemny(stockSettleVOs[i].getNcansettlemny());
      matchVO.setNcansettlenum(stockSettleVOs[i].getNcansettlenum());// 未结算数量
      if (EnumToIAFlag.EstimateToIA.value().equals(
          stockSettleVOs[i].getFdirtoiatype())) {
        matchVO.setNprice(stockSettleVOs[i].getNestcalcostprice());// 暂估单价
      }
      else if(EnumToIAFlag.ConfirmToIA.value().equals(
          stockSettleVOs[i].getFdirtoiatype())){
        matchVO.setNprice(stockSettleVOs[i].getNcalcostprice());// 成本单价
      }
      else if(EnumToIAFlag.NotToIA.value().equals(
          stockSettleVOs[i].getFdirtoiatype())){
        matchVO.setNprice((UFDouble)stockSettleVOs[i]
            .getAttributeValue(StockSettleVO.NCOSTPRICE));// 入库单的成本价
      }
      matchVO.setNcurstocksettlenum(stockSettleVOs[i].getNcansettlenum());// 本次入库结算数量
      //
      // 默认对应未结算数量
      matchVO.setNnoestsettlenum(stockSettleVOs[i].getNpuresettlenum());// 未暂估已结算数量
      matchVO.setNsettleavgprice(stockSettleVOs[i].getNavgsettleprice());// 结算平均单价
      matchVO.setNnoestsettlemny(stockSettleVOs[i].getNpuresettlemny());// 未暂估已结算金额
      matchVO.setNaccumestnum(stockSettleVOs[i].getNestnum());// 累计暂估数量

      // matchVO.setNaccumestmny(stockSettleVOs[i].getNestmny());// 累计暂估金额
      // wuxla v61
      matchVO.setNaccumestmny(stockSettleVOs[i].getNestcalcostmny());// 累计暂估金额
      matchVO.setNestprice(stockSettleVOs[i].getNestcalcostprice());// 暂估单价
      matchVO.setNestnum(stockSettleVOs[i].getNestnum());// 暂估数量
      // matchVO.setNestmny(stockSettleVOs[i].getNestmny());// 暂估金额
      // wuxla v61
      matchVO.setNestmny(stockSettleVOs[i].getNestcalcostmny());// 暂估金额

      matchVO.setBinvoice(UFBoolean.FALSE);
      matchVO.setBstock(UFBoolean.TRUE);
      matchVO.setStockSettleVO(stockSettleVOs[i]);

      if (EnumSettleType.WITHOUT_INVOICE == stype) {
        // 为无发票结算进行特殊处理
        MatchMaterialVOUtil.procWithoutInvcMatch(matchVO, stockSettleVOs[i]);
      }
      else if (EnumSettleType.FEE == stype) {
        // 为费用结算进行特殊处理
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
    mmVo.setNcurstocksettlenum(null);// 费用结算不需要本次结算数量
  }

  private static void procWithoutInvcMatch(MatchMaterialVO mmVo,
      StockSettleVO ssVo) {
    if (MathTool.isZero(ssVo.getNestcalcostprice())) {
      mmVo.setNprice(ssVo.getNcalcostprice());// 未暂估过取入库单价
    }

    // 计算本次结算金额
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
