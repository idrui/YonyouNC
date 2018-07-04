/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-20 ����11:22:29
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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ݹ�VO��һЩ����������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-20 ����11:22:29
 */
public class EstVOUtil {

  public static interface ScaleKeyInfo {
    /** �ݹ������ֶ����� **/
    public String getCurrencyKey();

    /** �����ݹ����б��ͷ�������ֶ�����(����) **/
    public String[] getCurrPriceKeys();

    /**
     * �����۱����ʾ��ȿ�����Ϣ<br>
     * 0�������ֶ� 1��ԭ���ֶ� 2�������ֶ� 3�����ʷ�����֯�ֶ�
     * 
     * @return
     */
    public FieldInfo[] getExchgRateKeyInfo();

    /** �ݹ����ҽ���ֶ����� **/
    public String[] getMnyKeys();

    /** �����ݹ����б��ͷ���������ֶ����� **/
    public String[] getNumKeys();

    /** �ݹ�ԭ���ֶ����� **/
    public String getOcurrencyKey();

    /** �����ݹ����б��ͷ�������ֶ�����(ԭ��) **/
    public String[] getOcurrPriceKeys();

    /** �ݹ�ԭ�ҽ���ֶ����� **/
    public String[] getOmnyKeys();

    /** ˰���ֶ� **/
    public String[] getTaxRateKey();

    /** �����ݹ����б��ͷ������λ�ֶ����� **/
    public String getunitKey();
  }

  public static interface StockScaleKeyInfo extends ScaleKeyInfo {

    /** �������ֶ����� */
    public String[] getAstNumKey();

    /** ����λ�ֶ����� */
    public String getAstUnitKey();

    /**
     * ȫ�ֻ��ʾ��ȿ�����Ϣ<br>
     * 0�������ֶ� 1��ԭ���ֶ� 2�������ֶ�
     */
    public FieldInfo[] getGlobalExchgRateKeyInfo();

    /** ȫ�ֱ�λ�ҽ�� */
    public String[] getGlobalmnykeys();

    /**
     * ���Ż��ʾ��ȿ�����Ϣ<br>
     * 0�������ֶ� 1��ԭ���ֶ� 2�������ֶ�
     */
    public FieldInfo[] getGroupExchgRateKeyInfo();

    /** ���ű�λ�ҽ�� */
    public String[] getGroupmnykeys();

    /** ���������ֶ����� */
    public String[] getQtNumKey();

    /** ���۵�λ�ֶ����� */
    public String getQtUnitKey();
  }

  /**
   * �ݹ�vo�ĺϼ���صļ���
   * 
   * <pre>
   * ���ñ��Ҽ�˰�ϼ� = �������з��ñ��Ҽ�˰�ϼƵĺϼơ�
   * ���ñ�����˰��� = �������з��ñ�����˰���ĺϼơ�
   * �ܱ��Ҽ�˰�ϼ� �� =���Ҽ�˰�ϼ�+���ñ��Ҽ�˰�ϼơ�
   * �ܱ�����˰��� = ������˰���+���ñ�����˰��
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

  /** ����Ҫ������ģ���е����ݽ��и��ƣ���֤�쳣�������ƻ�ģ���е�����--<b>ǳ��¡��ֻ��¡�������飨����VO����¡��������¡��ͷ</b> **/
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
   * ����������������ѯ������֯�£�ָ�����ϵĳɱ�Ҫ����Ϣ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param fiOrg ������֯
   * @param mPks ����OID������null
   * @return <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-6-29 ����10:40:43
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

  /** �ɹ���ⵥ�����ݹ�Ҫ���зǿռ��� **/
  public static String[] getFeeEstNotNullKeys() {
    return new String[] {
      FeeEstVO.PK_GROUP, FeeEstVO.PK_FINANCEORG, FeeEstVO.PK_STOCKPS,
      FeeEstVO.PK_STOCKPS_B, FeeEstVO.PK_ESTCURRENCY, FeeEstVO.PK_ESTPSN,
      FeeEstVO.DESTDATE, FeeEstVO.PK_FEEMATERIAL, FeeEstVO.PK_SRCFEEMATERIAL,
      FeeEstVO.PK_LOCALCURRENCY, FeeEstVO.PK_SUPPLIER, FeeEstVO.NESTEXCHGRATE,
      FeeEstVO.NESTMNY, FeeEstVO.NESTTAXMNY, FeeEstVO.NESTTAXMNY,
      FeeEstVO.NESTOTOTALMNY, FeeEstVO.NESTTOTALMNY, FeeEstVO.NCALCOSTMNY,
      FeeEstVO.NCALTAXMNY
    // 2012-05-02 tianft ��Ӽ�˰���ͼƳɱ����
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

  /** �õ��ݹ��ķ���VO **/
  public static CircularlyAccessibleValueObject[] getFeeEstVOs(
      AggregatedValueObject[] estVos) {
    if (ArrayUtils.isEmpty(estVos)) {
      return null;
    }
    return AggVOUtil.getCombinItemVOs(estVos);
  }

  /** �����ݹ���Ҫ���µ��ӱ��ֶ� **/
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
      // ֻ�����Ļ��ܲſ��Ը��£��ɹ��ݹ����ɱ༭
      GoodsEstVO.DTOCOSTAPDATE, GoodsEstVO.PK_COSTAPPSN, GoodsEstVO.FTOAPFLAG,
      GoodsEstVO.FTOIAFLAG, GoodsEstVO.NESTCALCOSTPRICE
    };
  }

  /** �����ݹ�Ҫ���зǿռ����ֶ� **/
  public static String[] getGoodsEstNotNullKeys() {
    // Set<String> keys =
    // new HashSet<String>(Arrays.asList(EstVOUtil
    // .getGoodsEstUpdateModelKeys()));
    // keys.add(GoodsEstVO.PK_SUPPLIER);// ��Ӧ��(������ⵥ����Ϊ��)
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
          // wuxla ȥ��ԭ��˰��GoodsEstVO.NESTOTAXMNY,
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

  /** �õ������ݹ�VO **/
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

  /** �ɹ����ݹ���Ҫ���µ������ֶ� **/
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
   * ���Ļ��ܵ��������ֶξ��ȴ�����Ϣ
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
