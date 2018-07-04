/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-5 ����06:36:10
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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���ȼ��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-5 ����06:36:10
 */
public class PrecisionValidate {
  private ScaleUtils util = ScaleUtils.getScaleUtilAtBS();

  /**
   * �����������������ȼ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param itemVO
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-5 ����06:38:35
   */
  public void precisionValidate(OrderItemVO itemVO) {
    // ������
    this.checkMoneyPrecision(itemVO);
    // ��鵥�۾���
    this.checkPricePrecision(itemVO);
    // �����������
    this.checkNumberPrecision(itemVO);
    // ��黻���ʾ���
    this.checkNchangeRatePrecision(itemVO);
  }

  /**
   * �������������������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param itemVO
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-3 ����01:48:52
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
          })/* ��{0}���������Ȳ���ȷ������ֵ��{1}����ʵ��ֵ��{2} */);
    }
  }

  /**
   * ��������������ȫ�ֽ���
   * <p>
   * <b>����˵��</b>
   * 
   * @param itemVO
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-3 ����02:30:11
   */
  private void checkGlobalMnyPrecision(OrderItemVO itemVO) {
    // ȫ�ֱ�λ�ҽ��
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
            })/* ��{0}��ȫ�ֽ��Ȳ���ȷ������ֵ��{1}����ʵ��ֵ��{2} */);
      }
    }
  }

  /**
   * �����������������Ž���
   * <p>
   * <b>����˵��</b>
   * 
   * @param itemVO
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-3 ����02:30:38
   */
  private void checkGroupMnyPrecison(OrderItemVO itemVO) {
    // ���ű�λ�ҽ��
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
            })/* ��{0}�м��Ž��Ȳ���ȷ������ֵ��{1}����ʵ��ֵ��{2} */);
      }
    }
  }

  /**
   * �����������������ű��ҽ���
   * <p>
   * <b>����˵��</b>
   * 
   * @param itemVO
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-3 ����02:38:13
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
            })/* ��{0}�б��ҽ��Ȳ���ȷ������ֵ��{1}����ʵ��ֵ��{2} */);
      }
    }
  }

  /**
   * ��������������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param itemVO
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-3 ����10:28:41
   */
  private void checkMoneyPrecision(OrderItemVO itemVO) {
    // ԭ�Ҿ���
    this.checkOrgMnyPrecision(itemVO);
    // ���Ҿ���
    this.checkLocalMnyPrecision(itemVO);
    // ȫ�ֽ���
    this.checkGlobalMnyPrecision(itemVO);
    // ���Ž���
    this.checkGroupMnyPrecison(itemVO);
  }

  /**
   * ����������������黻���ʾ���
   * <p>
   * <b>����˵��</b>
   * 
   * @param itemVO
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-3 ����10:30:53
   */
  private void checkNchangeRatePrecision(OrderItemVO itemVO) {
    // ���ۻ�����
    String vqtunitrate = itemVO.getVqtunitrate();
    String value = null;
    if (!StringUtil.isEmptyWithTrim(vqtunitrate)) {
      value = this.util.adjustHslScale(vqtunitrate);
      if (vqtunitrate.equals(value)) {
        ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004030_0", "04004030-0319", null, new String[] {
              itemVO.getCrowno(), value, vqtunitrate
            })/* ��{0}�б��۵�λ�����ʾ��Ȳ���ȷ������ֵ��{1}����ʵ��ֵ��{2} */);
      }
    }

    // ������
    String vchangerate = itemVO.getVchangerate();
    if (!StringUtil.isEmptyWithTrim(vchangerate)) {
      value = this.util.adjustHslScale(vchangerate);
      if (vchangerate.equals(value)) {
        ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004030_0", "04004030-0320", null, new String[] {
              itemVO.getCrowno(), value, vchangerate
            })/* ��{0}�л����ʾ��Ȳ���ȷ������ֵ��{1}����ʵ��ֵ��{2} */);
      }
    }
  }

  /**
   * �������������������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param itemVO
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-3 ����10:25:30
   */
  private void checkNumberPrecision(OrderItemVO itemVO) {
    // �����������
    this.checkAstnumPrecision(itemVO);
    // �������������
    this.checkNumPrecision(itemVO);
    // ��鱨����������
    this.checkQtnumPrecisioin(itemVO);
  }

  /**
   * ���������������������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param itemVO
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-3 ����01:48:43
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
          })/* ��{0}�����������Ȳ���ȷ������ֵ��{1}����ʵ��ֵ��{2} */);
    }
  }

  /**
   * ��������������ԭ�ҽ���
   * <p>
   * <b>����˵��</b>
   * 
   * @param itemVO
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-3 ����02:21:11
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
            })/* ��{0}��ԭ�ҽ��Ȳ���ȷ������ֵ��{1}����ʵ��ֵ��{2} */);
      }
    }
  }

  /**
   * ����������������鵥�۾��ȣ����۵�λ�۸�����λ�۸�
   * <p>
   * <b>����˵��</b>
   * 
   * @param itemVO
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-3 ����10:27:20
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
            })/* ��{0}�е��۾��Ȳ���ȷ������ֵ��{1}����ʵ��ֵ��{2} */);
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
            })/* ��{0}�е��۾��Ȳ���ȷ������ֵ��{1}����ʵ��ֵ��{2} */);
      }
    }
  }

  /**
   * ����������������鱨����������
   * <p>
   * <b>����˵��</b>
   * 
   * @param itemVO
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-3 ����01:48:30
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
              })/* ��{0}�б����������Ȳ���ȷ������ֵ��{1}����ʵ��ֵ��{2} */);
    }
  }

}
