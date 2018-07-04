package nc.bs.pu.m21.query;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.price.pub.service.IFindSalePrice;
import nc.itf.scmf.coop.ICoopService;
import nc.vo.price.pub.entity.FindPriceParaVO;
import nc.vo.price.pub.entity.FindPriceResultVO;
import nc.vo.pu.m21.query.price.CoopPriceQueryDetail;
import nc.vo.pu.m21.query.price.CoopPriceQueryParam;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.scmf.coop.entity.CoopHeaderVO;

public class OrderCoopPriceQueryBP {
  private MapList<String, Integer> rowMapList;

  public CoopPriceQueryParam queryCoopPrice(CoopPriceQueryParam param)
      throws BusinessException {

    try {
      // 询协同销售信息
      Map<String, CoopHeaderVO> coopMap = this.getCoopInfo(param);

      // 询价
      Map<Integer, FindPriceResultVO> priceResultMap =
          this.getPrice(param, coopMap);

      // 设置询价结果
      this.setPrice(priceResultMap, param, coopMap);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return param;
  }

  /**
   * 方法功能描述：询协同销售组织信息
   * <p>
   * <b>参数说明</b>
   * 
   * @param param
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-27 下午04:10:30
   */
  private Map<String, CoopHeaderVO> getCoopInfo(CoopPriceQueryParam param)
      throws BusinessException {
    ICoopService coopService =
        NCLocator.getInstance().lookup(ICoopService.class);

    String pk_org = param.getPurchaseOrg();
    String supplyPK = param.getSupplier();
    String tranType = param.getTrasType();
    // 订单结算财务组织
    String[] financialPKs = this.getFinancialPKs(param);

    // 取协同信息
    CoopHeaderVO[] chVO =
        coopService.queryCoopSalePriceInfoForPO(pk_org, supplyPK, tranType,
            financialPKs);

    // 转换为订单结算财务组织-销售组织VOmap
    Map<String, CoopHeaderVO> coopMap = this.getCustomMap(chVO);

    return coopMap;
  }

  /**
   * 转换为订单结算财务组织-销售组织map
   * 
   * @param financialPKs
   * @param coopVOs
   * @return
   */
  private Map<String, CoopHeaderVO> getCustomMap(CoopHeaderVO[] coopVOs) {
    Map<String, CoopHeaderVO> coopMap = new HashMap<String, CoopHeaderVO>();
    for (CoopHeaderVO vo : coopVOs) {
      coopMap.put(vo.getPk_financeorg_src(), vo);
    }
    return coopMap;
  }

  /**
   * 从参数的detail中抽出订单结算组织pk
   * 
   * @param param
   * @return
   */
  private String[] getFinancialPKs(CoopPriceQueryParam param) {
    CoopPriceQueryDetail[] details = param.getDetail();
    Set<String> financialPKSet = new HashSet<String>();
    for (CoopPriceQueryDetail detail : details) {
      financialPKSet.add(detail.getFinancialPK());
    }
    return financialPKSet.toArray(new String[financialPKSet.size()]);
  }

  /**
   * 询价
   * 
   * @param param
   * @param coopMap
   * @return
   * @throws BusinessException
   */
  private Map<Integer, FindPriceResultVO> getPrice(CoopPriceQueryParam param,
      Map<String, CoopHeaderVO> coopMap) throws BusinessException {

    IFindSalePrice priceService =
        NCLocator.getInstance().lookup(IFindSalePrice.class);

    Map<Integer, FindPriceResultVO> resultMap =
        new HashMap<Integer, FindPriceResultVO>();

    // 询价参数VO
    Map<String, List<FindPriceParaVO>> findPriceParasMap =
        this.preparePriceParam(param, coopMap);

    // 对分好组的参数循环询价
    for (Map.Entry<String, List<FindPriceParaVO>> paraMapEntry : findPriceParasMap
        .entrySet()) {
      String financialPk = paraMapEntry.getKey();
      List<FindPriceParaVO> priceParaList = paraMapEntry.getValue();
      FindPriceParaVO[] paraArray =
          priceParaList.toArray(new FindPriceParaVO[priceParaList.size()]);

      FindPriceResultVO[] priceResultVOs =
          priceService.findPrice(paraArray, coopMap.get(financialPk)
              .getPk_org_dest());

      Map<Integer, FindPriceResultVO> priceMap =
          this.getRow_VOMapping(financialPk, priceResultVOs);

      resultMap.putAll(priceMap);
    }

    return resultMap;
  }

  /**
   * 用来记录询价时按销售组织分组后的顺序
   * 
   * @param financialPk
   * @param priceResultVOs
   * @return
   */
  private Map<Integer, FindPriceResultVO> getRow_VOMapping(String financialPk,
      FindPriceResultVO[] priceResultVOs) {
    List<Integer> rowSeq = this.getRowMapList().get(financialPk);
    Map<Integer, FindPriceResultVO> resultMap =
        new HashMap<Integer, FindPriceResultVO>();
    int len = priceResultVOs.length;
    for (int i = 0; i < len; i++) {
      resultMap.put(rowSeq.get(i), priceResultVOs[i]);
    }

    return resultMap;
  }

  private MapList<String, Integer> getRowMapList() {
    if (this.rowMapList == null) {
      return this.rowMapList = new MapList<String, Integer>();
    }
    return this.rowMapList;
  }

  /**
   * 准备询价参数
   * 
   * @param param
   * @param coopMap
   * @return
   */
  private Map<String, List<FindPriceParaVO>> preparePriceParam(
      CoopPriceQueryParam param, Map<String, CoopHeaderVO> coopMap) {

    // 根据销售组织分类
    CoopPriceQueryDetail[] details = param.getDetail();

    MapList<String, FindPriceParaVO> paraMapList =
        new MapList<String, FindPriceParaVO>();

    UFDateTime querydate = null;
    UFDate dbildate = param.getBillDate();
    if (dbildate != null) {
      querydate = new UFDateTime(dbildate.toString());
    }

    for (CoopPriceQueryDetail detail : details) {
      FindPriceParaVO paraVO = new FindPriceParaVO();
      paraVO.setTpricedate(querydate);
      // 物料VID
      paraVO.setPk_material(detail.getPk_material());
      // 收货地区
      paraVO.setPk_areacl(detail.getPk_areacl());
      // 数量
      paraVO.setNnum(detail.getNnum());
      // 币种
      paraVO.setPk_currtype(detail.getPk_currtype());
      // 报价单位
      paraVO.setPk_unit(detail.getPk_unit());
      // 根据订单结算财务组织找客户
      String financialPk = detail.getFinancialPK();
      if (coopMap.get(financialPk) != null) {
        paraVO.setPk_customer(coopMap.get(financialPk).getCustPKForPO());
      }

      // 询价日期
      // group
      paraVO.setPk_group(detail.getPk_group());
      // 质量等级
      // paraVO.setPk_qualitylevel(detail.getPk_qualitylevel());
      paraVO.setVfree1(detail.getVfree1());
      paraVO.setVfree2(detail.getVfree2());
      paraVO.setVfree3(detail.getVfree3());
      paraVO.setVfree4(detail.getVfree4());
      paraVO.setVfree5(detail.getVfree5());
      paraVO.setVfree6(detail.getVfree6());
      paraVO.setVfree7(detail.getVfree7());
      paraVO.setVfree8(detail.getVfree8());
      paraVO.setVfree9(detail.getVfree9());
      paraVO.setVfree10(detail.getVfree10());
      // 根据销售组织分类(相当于根据订单结算财务组织分类)
      paraMapList.put(financialPk, paraVO);

      this.getRowMapList().put(financialPk, Integer.valueOf(detail.getRow()));
    }

    return paraMapList.toMap();
  }

  /**
   * 设置询价结果
   * 
   * @param priceMap
   * @param param
   * @param coopMap
   * @return
   */
  private CoopPriceQueryParam setPrice(
      Map<Integer, FindPriceResultVO> priceMap, CoopPriceQueryParam param,
      Map<String, CoopHeaderVO> coopMap) {
    CoopPriceQueryDetail[] details = param.getDetail();

    for (CoopPriceQueryDetail detail : details) {
      int row = detail.getRow();
      FindPriceResultVO priceResultVO = priceMap.get(Integer.valueOf(row));
      if (priceResultVO != null) {
        detail.setPrice(priceResultVO.getPrice());
        detail.setNetPrice(priceResultVO.getNetPrice());
        detail.setDiscount(priceResultVO.getDiscount());
        detail.setSaleOrg(coopMap.get(detail.getFinancialPK()).getPk_org_src());
      }
    }

    return param;
  }
}
