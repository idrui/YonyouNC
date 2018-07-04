/**
 * $文件说明$
 * 
 * @author lixyp
 * @version 6.3
 * @see
 * @since 6.3
 * @time 2013-1-11 上午08:27:57
 */
package nc.vo.pu.m23.rule;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.pub.constant.PUParaValue;
import nc.vo.pu.pub.enumeration.PricePriority;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.calculator.Calculator;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.calculator.data.RelationItemForCal;
import nc.vo.pubapp.calculator.data.VODataSetForCal;
import nc.vo.pubapp.scale.ScaleUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>单价金额关系换算，主要用于由其他单据生成采购订单时的计算。
 * </ul>
 * <p>
 * <p>
 * 
 * @since 6.3
 * @version 2013-1-11 上午08:27:57
 * @author lixyp
 */
public class RelationCalculate {

  private static class ArriveDataSetForCal extends VODataSetForCal {

    private ArriveVO arriveVo;

    public ArriveDataSetForCal(CircularlyAccessibleValueObject currVO,
        IRelationForItems item, ArriveVO arriveVo) {
      super(currVO, item);
      this.arriveVo = arriveVo;
    }

    @Override
    public UFDate getBillDate() {
      return this.arriveVo.getHVO().getDbilldate();
    }

    @Override
    public boolean hasItemKey(String key) {
      if (ArriveHeaderVO.DBILLDATE.equals(key)) {
        return true;
      }
      return super.hasItemKey(key);
    }
  }

  public void calcaulate(ArriveVO arriveVo, String itemKey) {
    IRelationForItems item = this.getIRelationForItem();
    ScaleUtils scaleUtils = new ScaleUtils(arriveVo.getHVO().getPk_group());
    ArriveItemVO[] itemVos = arriveVo.getBVO();

    for (ArriveItemVO itemVo : itemVos) {
      // 创建数据集实例 初始化数据关系计算用的数据集
      IDataSetForCal data = new ArriveDataSetForCal(itemVo, item, arriveVo);
      String pk_purchaseorg = arriveVo.getHVO().getPk_purchaseorg();
      // 创建参数实例，在计算的时候用来获得参数条件：是否含税优先等
      Condition cond = new Condition();
      // 设置是否进行本币换算
      cond.setIsCalLocalCurr(true);
      // 设置调单价方式调折扣
      boolean isChangePrice = this.isChangePrice(pk_purchaseorg);
      cond.setIsChgPriceOrDiscount(isChangePrice);
      // 设置是否固定单位换算率
      cond.setIsFixNchangerate(true);
      // 是否固定报价单位换算率
      cond.setIsFixNqtunitrate(true);
      // 设置含税优先还是无税优先
      boolean isTaxPricePriorToPrice =
          this.isTaxPricePriorToPrice(pk_purchaseorg);
      cond.setIsTaxOrNet(isTaxPricePriorToPrice);
      // 主计量单位优先
      cond.setUnitPriorType(Condition.MAIN_PRIOR);
      Integer fbuysellflag = itemVo.getFbuysellflag();
      // 是否跨国业务（购销类型为出口销售、进口采购时，为跨国业务）
      cond.setInternational(BuySellFlagEnum.IMPORT.value().equals(fbuysellflag)
          || BuySellFlagEnum.OUTPUT.value().equals(fbuysellflag));

      Calculator tool = new Calculator(data, scaleUtils);
      tool.calculate(cond, itemKey);
    }
  }

  private IRelationForItems getIRelationForItem() {
    IRelationForItems item = new RelationItemForCal();
    // 原币含税净价--》到货单：原币含税单价
    item.setNorigtaxnetpriceKey(ArriveItemVO.NORIGTAXPRICE);
    // 原币无税净价--》到货单：原币无税单价
    item.setNorignetpriceKey(ArriveItemVO.NORIGPRICE);
    // 本币含税净价--》到货单：本币含税单价
    item.setNtaxnetpriceKey(ArriveItemVO.NTAXPRICE);
    // 本币无税净价--》到货单：本币无税单价
    item.setNnetpriceKey(ArriveItemVO.NPRICE);
    return item;
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

  private boolean isTaxPricePriorToPrice(String pk_org) {
    boolean flag = true;
    PricePriority pricePriority = PUSysParamUtil.getPO28(pk_org);
    if (!PricePriority.TAXPRICE_PRIOR_TO_PRICE.equals(pricePriority)) {
      flag = false;
    }
    return flag;
  }
}
