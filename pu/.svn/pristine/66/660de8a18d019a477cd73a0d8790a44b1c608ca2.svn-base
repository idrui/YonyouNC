/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-20 上午11:22:29
 */
package nc.vo.pu.est.util;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.costfactor.IFactorOrdByOrgMaterial;
import nc.ui.pub.bill.IBillItem;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.costfactor.entity.CostfactorViewVO;
import nc.vo.pu.est.entity.EstVO;
import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.m4201.entity.PurchaseinFIHeaderVO;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pu.m4202.entity.VmiFIHeaderVO;
import nc.vo.pu.pub.enumeration.PricePriority;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.Constructor;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.FieldInfo;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>暂估VO的一些公共处理类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-20 上午11:22:29
 */
public class EstVOUtil {

  public static interface ScaleKeyInfo {
    /** 暂估本币字段名称 **/
    public String getCurrencyKey();

    /** 货物暂估（列表表头）单价字段名称(本币) **/
    public String[] getCurrPriceKeys();

    /**
     * 返回折本汇率精度控制信息<br>
     * 0－汇率字段 1－原币字段 2－本币字段 3－汇率方案组织字段
     * 
     * @return
     */
    public FieldInfo[] getExchgRateKeyInfo();

    /** 暂估本币金额字段名称 **/
    public String[] getMnyKeys();

    /** 货物暂估（列表表头）主数量字段名称 **/
    public String[] getNumKeys();

    /** 暂估原币字段名称 **/
    public String getOcurrencyKey();

    /** 货物暂估（列表表头）单价字段名称(原币) **/
    public String[] getOcurrPriceKeys();

    /** 暂估原币金额字段名称 **/
    public String[] getOmnyKeys();

    /** 税率字段 **/
    public String[] getTaxRateKey();

    /** 货物暂估（列表表头）主单位字段名称 **/
    public String getunitKey();
  }

  public static interface StockScaleKeyInfo extends ScaleKeyInfo {

    /** 辅数量字段名称 */
    public String[] getAstNumKey();

    /** 辅单位字段名称 */
    public String getAstUnitKey();

    /**
     * 全局汇率精度控制信息<br>
     * 0－汇率字段 1－原币字段 2－本币字段
     */
    public FieldInfo[] getGlobalExchgRateKeyInfo();

    /** 全局本位币金额 */
    public String[] getGlobalmnykeys();

    /**
     * 集团汇率精度控制信息<br>
     * 0－汇率字段 1－原币字段 2－本币字段
     */
    public FieldInfo[] getGroupExchgRateKeyInfo();

    /** 集团本位币金额 */
    public String[] getGroupmnykeys();

    /** 报价数量字段名称 */
    public String[] getQtNumKey();

    /** 报价单位字段名称 */
    public String getQtUnitKey();
  }

  /**
   * 暂估vo的合计相关的计算
   * 
   * <pre>
   * 费用本币价税合计 = 表体所有费用本币价税合计的合计。
   * 费用本币无税金额 = 表体所有费用本币无税金额的合计。
   * 总本币价税合计 否 =本币价税合计+费用本币价税合计。
   * 总本币无税金额 = 本币无税金额+费用本币无税金额。
   * 
   * <pre>
   * @param estVOs
   */
  public static void calculateTotal(EstVO[] estVOs) {
    for (EstVO estVO : estVOs) {
      GoodsEstVO goodEstVO = estVO.getParentVO();
      FeeEstVO[] feeEstVOs = estVO.getChildrenVO();
      UFDouble feeMny = UFDouble.ZERO_DBL;
      UFDouble feeTaxMny = UFDouble.ZERO_DBL;
      if (!ArrayUtils.isEmpty(feeEstVOs)) {
        for (FeeEstVO feevo : feeEstVOs) {
          feeMny = MathTool.add(feeMny, feevo.getNestmny());
          feeTaxMny = MathTool.add(feeTaxMny, feevo.getNesttotalmny());
        }
      }
      goodEstVO.setNfeemny(feeMny);
      goodEstVO.setNfeetaxmny(feeTaxMny);
      goodEstVO.setNtotalmny(MathTool.add(feeMny, goodEstVO.getNestmny()));
      goodEstVO.setNtotaltaxmny(MathTool.add(feeTaxMny,
          goodEstVO.getNesttotalmny()));

    }

  }

  /** 对需要操作的模型中的数据进行复制，保证异常操作不破坏模型中的数据--<b>浅克隆，只克隆表体数组（具体VO不克隆），不克隆表头</b> **/
  public static Object[] getCloneEstData(Object[] data) {
    if (ArrayUtils.isEmpty(data)) {
      return data;
    }
    int length = data.length;
    Object[] cdata = Constructor.declareArray(data[0].getClass(), length);
    for (int i = 0; i < length; i++) {
      AggregatedValueObject cvo =
          (AggregatedValueObject) Constructor.construct(data[0].getClass());
      cdata[i] = cvo;
      AggregatedValueObject vo = (AggregatedValueObject) data[i];
      cvo.setParentVO(vo.getParentVO());
      if (ArrayUtils.isEmpty(vo.getChildrenVO())) {
        cvo.setChildrenVO(null);
      }
      else {
        cvo.setChildrenVO(vo.getChildrenVO().clone());
      }
    }
    return cdata;
  }

  /**
   * 方法功能描述：查询财务组织下，指定物料的成本要素信息。
   * <p>
   * <b>参数说明</b>
   * 
   * @param fiOrg 财务组织
   * @param mPks 物料OID，可以null
   * @return <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-6-29 上午10:40:43
   */
  public static CostfactorViewVO[] getCostFactor(String fiOrg, String[] mPks) {
    CostfactorViewVO[] cfviews = null;
    try {
      IFactorOrdByOrgMaterial cfSrv =
          NCLocator.getInstance().lookup(IFactorOrdByOrgMaterial.class);
      cfviews = cfSrv.queryVOByOrgMaterial(fiOrg, mPks);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return cfviews;
  }

  /** 采购入库单费用暂估要进行非空检查的 **/
  public static String[] getFeeEstNotNullKeys() {
    return new String[] {
      FeeEstVO.PK_GROUP, FeeEstVO.PK_FINANCEORG, FeeEstVO.PK_STOCKPS,
      FeeEstVO.PK_STOCKPS_B, FeeEstVO.PK_ESTCURRENCY, FeeEstVO.PK_ESTPSN,
      FeeEstVO.DESTDATE, FeeEstVO.PK_FEEMATERIAL, FeeEstVO.PK_SRCFEEMATERIAL,
      FeeEstVO.PK_LOCALCURRENCY, FeeEstVO.PK_SUPPLIER, FeeEstVO.NESTEXCHGRATE,
      FeeEstVO.NESTMNY, FeeEstVO.NESTTAXMNY, FeeEstVO.NESTTAXMNY,
      FeeEstVO.NESTOTOTALMNY, FeeEstVO.NESTTOTALMNY, FeeEstVO.NCALCOSTMNY,
      FeeEstVO.NCALTAXMNY
    // 2012-05-02 tianft 添加计税金额和计成本金额
    };
  }

  public static ScaleKeyInfo getFeeEstScaleKeyInfo() {
    return new ScaleKeyInfo() {

      @Override
      public String getCurrencyKey() {
        return FeeEstVO.PK_LOCALCURRENCY;
      }

      @Override
      public String[] getCurrPriceKeys() {
        return null;
      }

      @Override
      public FieldInfo[] getExchgRateKeyInfo() {
        FieldInfo[] fdInfos = new FieldInfo[4];
        fdInfos[0] =
            new FieldInfo(FeeEstVO.NESTEXCHGRATE, IBillItem.BODY, null);
        fdInfos[1] =
            new FieldInfo(FeeEstVO.PK_ESTCURRENCY, IBillItem.BODY, null);
        fdInfos[2] =
            new FieldInfo(FeeEstVO.PK_LOCALCURRENCY, IBillItem.BODY, null);
        fdInfos[3] =
            new FieldInfo(FeeEstVO.PK_FINANCEORG, IBillItem.BODY, null);
        return fdInfos;
      }

      @Override
      public String[] getMnyKeys() {
        return new String[] {
          FeeEstVO.NESTMNY, FeeEstVO.NESTTAXMNY, FeeEstVO.NESTTOTALMNY,
          FeeEstVO.NCALTAXMNY, FeeEstVO.NNOSUBTAX, FeeEstVO.NCALCOSTMNY
        };
      }

      @Override
      public String[] getNumKeys() {
        return null;
      }

      @Override
      public String getOcurrencyKey() {
        return FeeEstVO.PK_ESTCURRENCY;
      }

      @Override
      public String[] getOcurrPriceKeys() {
        return null;
      }

      @Override
      public String[] getOmnyKeys() {
        return new String[] {
          FeeEstVO.NESTOMNY, FeeEstVO.NESTOTOTALMNY
        };
      }

      @Override
      public String[] getTaxRateKey() {
        return new String[] {
          FeeEstVO.NESTTAXRATE, FeeEstVO.NNOSUBTAXRATE
        };
      }

      @Override
      public String getunitKey() {
        return null;
      }

    };
  }

  /** 得到暂估的费用VO **/
  public static CircularlyAccessibleValueObject[] getFeeEstVOs(
      AggregatedValueObject[] estVos) {
    if (ArrayUtils.isEmpty(estVos)) {
      return null;
    }
    return AggVOUtil.getCombinItemVOs(estVos);
  }

  /** 货物暂估需要更新的子表字段 **/
  public static String[] getGoodsEstBUpdate() {
    // Set<String> keys =
    // new HashSet<String>(Arrays.asList(EstVOUtil
    // .getGoodsEstUpdateModelKeys()));
    // keys.add(GoodsEstVO.DTOCOSTAPDATE);
    // keys.add(GoodsEstVO.PK_COSTAPPSN);
    // keys.add(GoodsEstVO.FTOAPFLAG);
    // keys.add(GoodsEstVO.FTOIAFLAG);
    //
    // keys.add(GoodsEstVO.NESTCALCOSTPRICE);
    // return keys.toArray(new String[keys.size()]);
    return new String[] {
      GoodsEstVO.NESTNUM, GoodsEstVO.NESTMNY, GoodsEstVO.NESTEXHGRATE,
      GoodsEstVO.NESTOMNY, GoodsEstVO.NESTOPRICE, GoodsEstVO.NESTOTAXPRICE,
      GoodsEstVO.NESTOTOTALMNY, GoodsEstVO.NESTPRICE, GoodsEstVO.NESTTAXMNY,
      GoodsEstVO.NESTTAXPRICE, GoodsEstVO.NESTTAXRATE, GoodsEstVO.NESTTOTALMNY,
      GoodsEstVO.PK_ESTCURRENCY, GoodsEstVO.FESTTAXTYPE,
      GoodsEstVO.NESTCALCOSTMNY, GoodsEstVO.NESTCALTAXMNY,
      GoodsEstVO.NESTNOSUBTAX, GoodsEstVO.NESTNOSUBTAXRATE,
      GoodsEstVO.CESTTAXCODEID, GoodsEstVO.BOPPTAXFLAG,
      // 只有消耗汇总才可以更新，采购暂估不可编辑
      GoodsEstVO.DTOCOSTAPDATE, GoodsEstVO.PK_COSTAPPSN, GoodsEstVO.FTOAPFLAG,
      GoodsEstVO.FTOIAFLAG, GoodsEstVO.NESTCALCOSTPRICE
    };
  }

  /** 货物暂估要进行非空检查的字段 **/
  public static String[] getGoodsEstNotNullKeys() {
    // Set<String> keys =
    // new HashSet<String>(Arrays.asList(EstVOUtil
    // .getGoodsEstUpdateModelKeys()));
    // keys.add(GoodsEstVO.PK_SUPPLIER);// 供应商(自制入库单可能为空)
    // return keys.toArray(new String[keys.size()]);
    // wuxla V61
    return new String[] {
      GoodsEstVO.NESTNUM, GoodsEstVO.NESTMNY, GoodsEstVO.NESTEXHGRATE,
      GoodsEstVO.NESTOMNY, GoodsEstVO.NESTOPRICE, GoodsEstVO.NESTOTAXPRICE,
      GoodsEstVO.NESTOTOTALMNY, GoodsEstVO.NESTPRICE, GoodsEstVO.NESTTAXMNY,
      GoodsEstVO.NESTTAXPRICE, GoodsEstVO.NESTTAXRATE, GoodsEstVO.NESTTOTALMNY,
      GoodsEstVO.PK_ESTCURRENCY, GoodsEstVO.FESTTAXTYPE,
      GoodsEstVO.PK_SUPPLIER, GoodsEstVO.NESTCALCOSTMNY,
      GoodsEstVO.NESTCALTAXMNY, GoodsEstVO.CESTTAXCODEID
    };
  }

  public static ScaleKeyInfo getGoodsEstScaleKeyInfo() {
    return new ScaleKeyInfo() {

      @Override
      public String getCurrencyKey() {
        return GoodsEstVO.CCURRENCYID;
      }

      @Override
      public String[] getCurrPriceKeys() {
        return null;
      }

      @Override
      public FieldInfo[] getExchgRateKeyInfo() {
        FieldInfo[] fdInfos = new FieldInfo[4];
        fdInfos[0] =
            new FieldInfo(GoodsEstVO.NESTEXHGRATE, IBillItem.HEAD, null);
        fdInfos[1] =
            new FieldInfo(GoodsEstVO.PK_ESTCURRENCY, IBillItem.HEAD, null);
        fdInfos[2] =
            new FieldInfo(GoodsEstVO.CCURRENCYID, IBillItem.HEAD, null);
        fdInfos[3] =
            new FieldInfo(GoodsEstVO.PK_FINANCEORG, IBillItem.HEAD, null);
        return fdInfos;
      }

      @Override
      public String[] getMnyKeys() {
        return new String[] {
          GoodsEstVO.NESTMNY, GoodsEstVO.NESTTAXMNY, GoodsEstVO.NESTTOTALMNY,
          GoodsEstVO.NACCPREESTSTTLMNY, GoodsEstVO.NFEEMNY,
          GoodsEstVO.NFEETAXMNY, GoodsEstVO.NTOTALMNY, GoodsEstVO.NTOTALTAXMNY,
          GoodsEstVO.NESTNOSUBTAX, GoodsEstVO.NESTCALCOSTMNY,
          GoodsEstVO.NESTCALTAXMNY
        };
      }

      @Override
      public String[] getNumKeys() {
        return new String[] {
          GoodsEstVO.NESTNUM, GoodsEstVO.NACCESTCOSTSTTLNUM,
          GoodsEstVO.NACCUMSETTLENUM, GoodsEstVO.NINNUM
        };
      }

      @Override
      public String getOcurrencyKey() {
        return GoodsEstVO.PK_ESTCURRENCY;
      }

      @Override
      public String[] getOcurrPriceKeys() {
        return new String[] {
          GoodsEstVO.NESTPRICE, GoodsEstVO.NESTTAXPRICE, GoodsEstVO.NESTOPRICE,
          GoodsEstVO.NESTOTAXPRICE
        };
      }

      @Override
      public String[] getOmnyKeys() {
        return new String[] {
          // wuxla 去掉原币税额GoodsEstVO.NESTOTAXMNY,
          GoodsEstVO.NESTOMNY, GoodsEstVO.NESTOTOTALMNY
        };
      }

      @Override
      public String[] getTaxRateKey() {
        return new String[] {
          GoodsEstVO.NESTTAXRATE, GoodsEstVO.NESTNOSUBTAXRATE
        };
      }

      @Override
      public String getunitKey() {
        return GoodsEstVO.CUNITID;
      }
    };
  }

  public static String[] getGoodsEstUpdateModelKeys() {
    return new String[] {
      GoodsEstVO.NESTNUM, GoodsEstVO.NESTMNY, GoodsEstVO.NESTEXHGRATE,
      GoodsEstVO.NESTOMNY, GoodsEstVO.NESTOPRICE, GoodsEstVO.NESTOTAXPRICE,
      GoodsEstVO.NESTOTOTALMNY, GoodsEstVO.NESTPRICE, GoodsEstVO.NESTTAXMNY,
      GoodsEstVO.NESTTAXPRICE, GoodsEstVO.NESTTAXRATE, GoodsEstVO.NESTTOTALMNY,
      GoodsEstVO.PK_ESTCURRENCY, GoodsEstVO.FESTTAXTYPE,
      GoodsEstVO.NESTCALCOSTMNY, GoodsEstVO.NESTCALTAXMNY,
      GoodsEstVO.NESTNOSUBTAX, GoodsEstVO.NESTNOSUBTAXRATE,
      GoodsEstVO.CESTTAXCODEID, GoodsEstVO.BOPPTAXFLAG
    };
  }

  /** 得到货物暂估VO **/
  public static GoodsEstVO[] getGoodsEstVos(EstVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }
    int length = vos.length;
    GoodsEstVO[] heads =
        Constructor.declareArray(vos[0].getParentVO().getClass(), length);
    for (int i = 0; i < heads.length; i++) {
      heads[i] = vos[i].getParentVO();
    }
    return heads;
  }

  public static PricePriority getPO28(String pk_purorg) {
    if (StringUtil.isEmptyWithTrim(pk_purorg)) {
      return PricePriority.PRICE_PRIOR_TO_TAXPRICE;
    }
    return PUSysParamUtil.getPO28(pk_purorg);
  }

  /** 采购入暂估需要更新的主表字段 **/
  public static String[] getPurchsInGoodsHUpdate() {
    return new String[] {
      PurchaseinFIHeaderVO.BAUTOTOFI
    };
  }

  public static StockScaleKeyInfo getStockScaleKeyInfo() {
    return new StockScaleKeyInfo() {

      @Override
      public String[] getAstNumKey() {
        return new String[] {
          PurchaseinFIItemVO.NINASSISTNUM
        };
      }

      @Override
      public String getAstUnitKey() {
        return PurchaseinFIItemVO.CASTUNITID;
      }

      @Override
      public String getCurrencyKey() {
        return PurchaseinFIItemVO.CCURRENCYID;
      }

      @Override
      public String[] getCurrPriceKeys() {
        return new String[] {
          PurchaseinFIItemVO.NPRICE, PurchaseinFIItemVO.NQTPRICE,
          PurchaseinFIItemVO.NQTTAXPRICE, PurchaseinFIItemVO.NTAXPRICE,
          PurchaseinFIItemVO.NESTPRICE
        };
      }

      @Override
      public FieldInfo[] getExchgRateKeyInfo() {
        FieldInfo[] fdInfos = new FieldInfo[4];
        fdInfos[0] =
            new FieldInfo(PurchaseinFIItemVO.NCHANGESTDRATE, IBillItem.HEAD,
                null);
        fdInfos[1] =
            new FieldInfo(PurchaseinFIItemVO.CORIGCURRENCYID, IBillItem.HEAD,
                null);
        fdInfos[2] =
            new FieldInfo(PurchaseinFIItemVO.CCURRENCYID, IBillItem.HEAD, null);
        fdInfos[3] =
            new FieldInfo(PurchaseinFIItemVO.PK_FINANCEORG, IBillItem.HEAD,
                null);
        return fdInfos;
      }

      @Override
      public FieldInfo[] getGlobalExchgRateKeyInfo() {
        FieldInfo[] fdInfos = new FieldInfo[3];
        fdInfos[0] =
            new FieldInfo(PurchaseinFIItemVO.NGLOBALEXCHGRATE, IBillItem.HEAD,
                null);
        fdInfos[1] =
            new FieldInfo(PurchaseinFIItemVO.CORIGCURRENCYID, IBillItem.HEAD,
                null);
        fdInfos[2] =
            new FieldInfo(PurchaseinFIItemVO.CCURRENCYID, IBillItem.HEAD, null);
        return fdInfos;
      }

      @Override
      public String[] getGlobalmnykeys() {
        return new String[] {
          PurchaseinFIItemVO.NGLOBALMNY, PurchaseinFIItemVO.NGLOBALTAXMNY
        };
      }

      @Override
      public FieldInfo[] getGroupExchgRateKeyInfo() {
        FieldInfo[] fdInfos = new FieldInfo[3];
        fdInfos[0] =
            new FieldInfo(PurchaseinFIItemVO.NGROUPEXCHGRATE, IBillItem.HEAD,
                null);
        fdInfos[1] =
            new FieldInfo(PurchaseinFIItemVO.CORIGCURRENCYID, IBillItem.HEAD,
                null);
        fdInfos[2] =
            new FieldInfo(PurchaseinFIItemVO.CCURRENCYID, IBillItem.HEAD, null);
        return fdInfos;
      }

      @Override
      public String[] getGroupmnykeys() {
        return new String[] {
          PurchaseinFIItemVO.NGROUPMNY, PurchaseinFIItemVO.NGROUPTAXMNY
        };
      }

      @Override
      public String[] getMnyKeys() {
        return new String[] {
          PurchaseinFIItemVO.NCALCOSTMNY, PurchaseinFIItemVO.NCALTAXMNY,
          PurchaseinFIItemVO.NGROUPMNY, PurchaseinFIItemVO.NGROUPTAXMNY,
          PurchaseinFIItemVO.NMNY, PurchaseinFIItemVO.NNOSUBTAX,
          PurchaseinFIItemVO.NCOSTMNY
        };
      }

      @Override
      public String[] getNumKeys() {
        return new String[] {
          PurchaseinFIItemVO.NINNUM
        };
      }

      @Override
      public String getOcurrencyKey() {
        return PurchaseinFIItemVO.CORIGCURRENCYID;
      }

      @Override
      public String[] getOcurrPriceKeys() {
        return new String[] {
          PurchaseinFIItemVO.NCALCOSTPRICE, PurchaseinFIItemVO.NORIGTAXPRICE,
          PurchaseinFIItemVO.NQTORIGPRICE, PurchaseinFIItemVO.NQTORIGTAXPRICE,
          PurchaseinFIItemVO.NCOSTPRICE
        };
      }

      @Override
      public String[] getOmnyKeys() {
        return new String[] {
          PurchaseinFIItemVO.NORIGMNY
        };
      }

      @Override
      public String[] getQtNumKey() {
        return new String[] {
          PurchaseinFIItemVO.NQTUNITNUM
        };
      }

      @Override
      public String getQtUnitKey() {
        return PurchaseinFIItemVO.CQTUNITID;
      }

      @Override
      public String[] getTaxRateKey() {
        return new String[] {
          PurchaseinFIItemVO.NNOSUBTAXRATE, PurchaseinFIItemVO.NESTTAXRATE,
          PurchaseinFIItemVO.NTAXRATE
        };
      }

      @Override
      public String getunitKey() {
        return PurchaseinFIItemVO.CUNITID;
      }
    };
  }

  /**
   * 消耗汇总单据自身字段精度处理信息
   * 
   * @return
   */
  public static StockScaleKeyInfo getVmiStockScaleKeyInfo() {
    return new StockScaleKeyInfo() {

      @Override
      public String[] getAstNumKey() {
        return new String[] {
          VmiFIHeaderVO.NINASSISTNUM
        };
      }

      @Override
      public String getAstUnitKey() {
        return VmiFIHeaderVO.CASTUNITID;
      }

      @Override
      public String getCurrencyKey() {
        return VmiFIHeaderVO.CCURRENCYID;
      }

      @Override
      public String[] getCurrPriceKeys() {
        return new String[] {
          VmiFIHeaderVO.NNETPRICE, VmiFIHeaderVO.NTAXNETPRICE
        };
      }

      @Override
      public FieldInfo[] getExchgRateKeyInfo() {
        return null;

      }

      @Override
      public FieldInfo[] getGlobalExchgRateKeyInfo() {
        return null;
      }

      @Override
      public String[] getGlobalmnykeys() {
        return null;
      }

      @Override
      public FieldInfo[] getGroupExchgRateKeyInfo() {
        return null;
      }

      @Override
      public String[] getGroupmnykeys() {
        return null;
      }

      @Override
      public String[] getMnyKeys() {
        return null;
      }

      @Override
      public String[] getNumKeys() {
        return new String[] {
          VmiFIHeaderVO.NINNUM
        };
      }

      @Override
      public String getOcurrencyKey() {
        return VmiFIHeaderVO.CCURRENCYID;
      }

      @Override
      public String[] getOcurrPriceKeys() {
        return null;
      }

      @Override
      public String[] getOmnyKeys() {
        return null;
      }

      @Override
      public String[] getQtNumKey() {
        return new String[] {
          VmiFIHeaderVO.NINASSISTNUM
        };
      }

      @Override
      public String getQtUnitKey() {
        return VmiFIHeaderVO.CASTUNITID;
      }

      @Override
      public String[] getTaxRateKey() {
        return null;
      }

      @Override
      public String getunitKey() {
        return VmiFIHeaderVO.CUNITID;
      }
    };
  }
}
