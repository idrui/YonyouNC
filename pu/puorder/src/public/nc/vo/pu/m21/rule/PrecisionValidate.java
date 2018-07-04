/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-5 下午06:36:10
 */
package nc.vo.pu.m21.rule;

import nc.pubitf.uapbd.CurrencyRateUtilHelper;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.scale.ScaleUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>精度检查
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-5 下午06:36:10
 */
public class PrecisionValidate {
  private ScaleUtils util = ScaleUtils.getScaleUtilAtBS();

  /**
   * 方法功能描述：精度检查
   * <p>
   * <b>参数说明</b>
   * 
   * @param itemVO
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-5 下午06:38:35
   */
  public void precisionValidate(OrderItemVO itemVO) {
    // 检查金额精度
    this.checkMoneyPrecision(itemVO);
    // 检查单价精度
    this.checkPricePrecision(itemVO);
    // 检查数量精度
    this.checkNumberPrecision(itemVO);
    // 检查换算率精度
    this.checkNchangeRatePrecision(itemVO);
  }

  /**
   * 方法功能描述：检查数量精度
   * <p>
   * <b>参数说明</b>
   * 
   * @param itemVO
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-3 下午01:48:52
   */
  private void checkAstnumPrecision(OrderItemVO itemVO) {
    UFDouble nastnum = itemVO.getNastnum();
    if (null == nastnum) {
      return;
    }

    UFDouble value = this.util.adjustNumScale(nastnum, itemVO.getCastunitid());
    if (nastnum.compareTo(value) != 0) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004030_0", "04004030-0315", null, new String[] {
            itemVO.getCrowno(), String.valueOf(value), String.valueOf(nastnum)
          })/* 第{0}行数量精度不正确：理论值是{1}，但实际值是{2} */);
    }
  }

  /**
   * 方法功能描述：全局金额精度
   * <p>
   * <b>参数说明</b>
   * 
   * @param itemVO
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-3 下午02:30:11
   */
  private void checkGlobalMnyPrecision(OrderItemVO itemVO) {
    // 全局本位币金额
    String[] globalmnykeys = new String[] {
      OrderItemVO.NGLOBALMNY, OrderItemVO.NGLOBALTAXMNY
    };

    for (String key : globalmnykeys) {
      UFDouble mny = (UFDouble) itemVO.getAttributeValue(key);
      if (null == mny) {
        continue;
      }

      UFDouble value = this.util.adjustGlobalMnyScale(mny);
      if (mny.compareTo(value) != 0) {
        ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004030_0", "04004030-0316", null, new String[] {
              itemVO.getCrowno(), String.valueOf(value), String.valueOf(mny)
            })/* 第{0}行全局金额精度不正确：理论值是{1}，但实际值是{2} */);
      }
    }
  }

  /**
   * 方法功能描述：集团金额精度
   * <p>
   * <b>参数说明</b>
   * 
   * @param itemVO
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-3 下午02:30:38
   */
  private void checkGroupMnyPrecison(OrderItemVO itemVO) {
    // 集团本位币金额
    String[] groupmnykeys = new String[] {
      OrderItemVO.NGROUPMNY, OrderItemVO.NGROUPTAXMNY
    };

    for (String key : groupmnykeys) {
      UFDouble mny = (UFDouble) itemVO.getAttributeValue(key);
      if (null == mny) {
        continue;
      }

      UFDouble value = this.util.adjustGroupMnyScale(mny);
      if (mny.compareTo(value) != 0) {
        ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004030_0", "04004030-0317", null, new String[] {
              itemVO.getCrowno(), String.valueOf(value), String.valueOf(mny)
            })/* 第{0}行集团金额精度不正确：理论值是{1}，但实际值是{2} */);
      }
    }
  }

  /**
   * 方法功能描述：集团本币金额精度
   * <p>
   * <b>参数说明</b>
   * 
   * @param itemVO
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-3 下午02:38:13
   */
  private void checkLocalMnyPrecision(OrderItemVO itemVO) {
    String[] mnykeys = new String[] {
      OrderItemVO.NMNY, OrderItemVO.NTAXMNY
    };

    String currid = itemVO.getCcurrencyid();
    if (null == currid) {
      currid =
          CurrencyRateUtilHelper.getInstance().getLocalCurrtypeByOrgID(
              itemVO.getPk_org());
    }
    for (String key : mnykeys) {
      UFDouble mny = (UFDouble) itemVO.getAttributeValue(key);
      if (null == mny) {
        continue;
      }
      UFDouble value = this.util.adjustMnyScale(mny, currid);
      if (mny.compareTo(value) != 0) {
        ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004030_0", "04004030-0318", null, new String[] {
              itemVO.getCrowno(), String.valueOf(value), String.valueOf(mny)
            })/* 第{0}行本币金额精度不正确：理论值是{1}，但实际值是{2} */);
      }
    }
  }

  /**
   * 方法功能描述：检查金额精度
   * <p>
   * <b>参数说明</b>
   * 
   * @param itemVO
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-3 上午10:28:41
   */
  private void checkMoneyPrecision(OrderItemVO itemVO) {
    // 原币精度
    this.checkOrgMnyPrecision(itemVO);
    // 本币精度
    this.checkLocalMnyPrecision(itemVO);
    // 全局金额精度
    this.checkGlobalMnyPrecision(itemVO);
    // 集团金额精度
    this.checkGroupMnyPrecison(itemVO);
  }

  /**
   * 方法功能描述：检查换算率精度
   * <p>
   * <b>参数说明</b>
   * 
   * @param itemVO
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-3 上午10:30:53
   */
  private void checkNchangeRatePrecision(OrderItemVO itemVO) {
    // 报价换算率
    String vqtunitrate = itemVO.getVqtunitrate();
    String value = null;
    if (!StringUtil.isEmptyWithTrim(vqtunitrate)) {
      value = this.util.adjustHslScale(vqtunitrate);
      if (vqtunitrate.equals(value)) {
        ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004030_0", "04004030-0319", null, new String[] {
              itemVO.getCrowno(), value, vqtunitrate
            })/* 第{0}行报价单位换算率精度不正确：理论值是{1}，但实际值是{2} */);
      }
    }

    // 换算率
    String vchangerate = itemVO.getVchangerate();
    if (!StringUtil.isEmptyWithTrim(vchangerate)) {
      value = this.util.adjustHslScale(vchangerate);
      if (vchangerate.equals(value)) {
        ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004030_0", "04004030-0320", null, new String[] {
              itemVO.getCrowno(), value, vchangerate
            })/* 第{0}行换算率精度不正确：理论值是{1}，但实际值是{2} */);
      }
    }
  }

  /**
   * 方法功能描述：检查数量精度
   * <p>
   * <b>参数说明</b>
   * 
   * @param itemVO
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-3 上午10:25:30
   */
  private void checkNumberPrecision(OrderItemVO itemVO) {
    // 检查数量精度
    this.checkAstnumPrecision(itemVO);
    // 检查主数量精度
    this.checkNumPrecision(itemVO);
    // 检查报价数量精度
    this.checkQtnumPrecisioin(itemVO);
  }

  /**
   * 方法功能描述：检查主数量精度
   * <p>
   * <b>参数说明</b>
   * 
   * @param itemVO
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-3 下午01:48:43
   */
  private void checkNumPrecision(OrderItemVO itemVO) {
    UFDouble nnum = itemVO.getNnum();
    if (null == nnum) {
      return;
    }

    UFDouble value = this.util.adjustNumScale(nnum, itemVO.getCunitid());
    if (nnum.compareTo(value) != 0) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004030_0", "04004030-0321", null, new String[] {
            itemVO.getCrowno(), String.valueOf(value), String.valueOf(nnum)
          })/* 第{0}行主数量精度不正确：理论值是{1}，但实际值是{2} */);
    }
  }

  /**
   * 方法功能描述：原币金额精度
   * <p>
   * <b>参数说明</b>
   * 
   * @param itemVO
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-3 下午02:21:11
   */
  private void checkOrgMnyPrecision(OrderItemVO itemVO) {
    String[] keys = new String[] {
      OrderItemVO.NORIGMNY, OrderItemVO.NORIGTAXMNY
    };

    for (String key : keys) {
      UFDouble orgMny = (UFDouble) itemVO.getAttributeValue(key);
      if (null == orgMny) {
        continue;
      }

      UFDouble value =
          this.util.adjustMnyScale(orgMny, itemVO.getCorigcurrencyid());
      if (orgMny.compareTo(value) != 0) {
        ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004030_0", "04004030-0322", null, new String[] {
              itemVO.getCrowno(), String.valueOf(value), String.valueOf(orgMny)
            })/* 第{0}行原币金额精度不正确：理论值是{1}，但实际值是{2} */);
      }
    }
  }

  /**
   * 方法功能描述：检查单价精度：报价单位价格，主单位价格
   * <p>
   * <b>参数说明</b>
   * 
   * @param itemVO
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-3 上午10:27:20
   */
  private void checkPricePrecision(OrderItemVO itemVO) {
    String[] CurrPricekeys =
        new String[] {
          OrderItemVO.NNETPRICE, OrderItemVO.NQTNETPRICE,
          OrderItemVO.NQTTAXNETPRICE, OrderItemVO.NTAXNETPRICE
        };

    String[] OcrrPricekeys =
        new String[] {
          OrderItemVO.NORIGNETPRICE, OrderItemVO.NORIGPRICE,
          OrderItemVO.NORIGTAXNETPRICE, OrderItemVO.NORIGTAXPRICE,
          OrderItemVO.NQTORIGNETPRICE, OrderItemVO.NQTORIGPRICE,
          OrderItemVO.NQTORIGTAXNETPRC, OrderItemVO.NQTORIGTAXPRICE,
        };

    for (String key : CurrPricekeys) {
      UFDouble price = (UFDouble) itemVO.getAttributeValue(key);
      if (null == price) {
        continue;
      }

      UFDouble value =
          this.util.adjustSoPuPriceScale(price, itemVO.getCcurrencyid());
      if (price.compareTo(value) != 0) {
        ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004030_0", "04004030-0323", null, new String[] {
              itemVO.getCrowno(), String.valueOf(value), String.valueOf(price)
            })/* 第{0}行单价精度不正确：理论值是{1}，但实际值是{2} */);
      }
    }
    for (String key : OcrrPricekeys) {
      UFDouble price = (UFDouble) itemVO.getAttributeValue(key);
      if (null == price) {
        continue;
      }

      UFDouble value =
          this.util.adjustSoPuPriceScale(price, itemVO.getCorigcurrencyid());
      if (price.compareTo(value) != 0) {
        ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004030_0", "04004030-0323", null, new String[] {
              itemVO.getCrowno(), String.valueOf(value), String.valueOf(price)
            })/* 第{0}行单价精度不正确：理论值是{1}，但实际值是{2} */);
      }
    }
  }

  /**
   * 方法功能描述：检查报价数量精度
   * <p>
   * <b>参数说明</b>
   * 
   * @param itemVO
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-3 下午01:48:30
   */
  private void checkQtnumPrecisioin(OrderItemVO itemVO) {
    UFDouble nqtunitnum = itemVO.getNqtunitnum();
    if (null == nqtunitnum) {
      return;
    }

    UFDouble value =
        this.util.adjustNumScale(nqtunitnum, itemVO.getCqtunitid());
    if (nqtunitnum.compareTo(value) != 0) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID(
              "4004030_0",
              "04004030-0324",
              null,
              new String[] {
                itemVO.getCrowno(), String.valueOf(value),
                String.valueOf(nqtunitnum)
              })/* 第{0}行报价数量精度不正确：理论值是{1}，但实际值是{2} */);
    }
  }

}
