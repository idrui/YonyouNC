package nc.pubitf.pu.m23.ec;

import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * @since 6.0
 * @version 2011-5-16 下午12:59:17
 * @author wuxla
 */

public class ArriveMatViewECVO extends AbstractDataView {

  public static class ArriveMatViewECVOMeta extends DataViewMeta {
    public ArriveMatViewECVOMeta() {
      this.init();
    }

    private void init() {
      // 到货单号、产品主键、自由辅助属性id、批次号、单位id、到货数量、
      // 合格数量、不合格数量、单价、价税合计、税率、币种id、换算率
      // 项目、生产厂商、客户
      this.add(ArriveItemVO.class, new String[] {
        ArriveItemVO.PK_MATERIAL, ArriveItemVO.VFREE1, ArriveItemVO.VFREE2,
        ArriveItemVO.VFREE3, ArriveItemVO.VFREE4, ArriveItemVO.VFREE5,
        ArriveItemVO.VFREE6, ArriveItemVO.VFREE7, ArriveItemVO.VFREE8,
        ArriveItemVO.VFREE9, ArriveItemVO.VFREE10, ArriveItemVO.VBATCHCODE,
        ArriveItemVO.CASTUNITID, ArriveItemVO.NASTNUM, ArriveItemVO.NELIGNUM,
        ArriveItemVO.NNOTELIGNUM, ArriveItemVO.NTAXPRICE,
        ArriveItemVO.NORIGTAXMNY, ArriveItemVO.NTAXRATE,
        ArriveItemVO.CORIGCURRENCYID, ArriveItemVO.VCHANGERATE,
        ArriveItemVO.CPROJECTID, ArriveItemVO.CPRODUCTORID,
        ArriveItemVO.CASSCUSTID
      });
      this.add(ArriveHeaderVO.class, new String[] {
        ArriveHeaderVO.VBILLCODE, ArriveHeaderVO.PK_PURCHASEORG,
        ArriveHeaderVO.PK_PURCHASEORG_V
      });
      this.addRelation(ArriveItemVO.class, ArriveItemVO.PK_ARRIVEORDER,
          ArriveHeaderVO.class, ArriveHeaderVO.PK_ARRIVEORDER);
    }
  }

  private static final long serialVersionUID = -5135348723310579394L;

  /** 客户 getter 方法 */
  public String getCasscustid() {
    return (String) this.getAttributeValue(ArriveItemVO.CASSCUSTID);
  }

  /** 单位 getter 方法 */
  public String getCastunitid() {
    return (String) this.getAttributeValue(ArriveItemVO.CASTUNITID);
  }

  /** 原币币种 getter 方法 */
  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(ArriveItemVO.CORIGCURRENCYID);
  }

  /** 生产厂商 getter 方法 */
  public String getCproductorid() {
    return (String) this.getAttributeValue(ArriveItemVO.CPRODUCTORID);
  }

  /** 项目 getter 方法 */
  public String getCprojectid() {
    return (String) this.getAttributeValue(ArriveItemVO.CPROJECTID);
  }

  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance().getDataViewMeta(
        ArriveMatViewECVOMeta.class);
  }

  /** 到货数量 getter 方法 */
  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NASTNUM);
  }

  /** 合格主数量 getter 方法 */
  public UFDouble getNelignum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NELIGNUM);
  }

  /** 不合格主数量 getter 方法 */
  public UFDouble getNnotelignum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NNOTELIGNUM);
  }

  /** 原币含税金额 getter 方法 */
  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NORIGTAXMNY);
  }

  /** 主本币含税单价 getter 方法 */
  public UFDouble getNtaxprice() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NTAXPRICE);
  }

  /** 税率 getter 方法 */
  public UFDouble getNtaxrate() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NTAXRATE);
  }

  /** 物料VID getter 方法 */
  public String getPk_material() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_MATERIAL);
  }

  /** 采购组织 getter 方法 */
  public String getPk_purchaseorg() {
    return (String) this.getAttributeValue(ArriveHeaderVO.PK_PURCHASEORG);
  }

  /** 采购组织版本 getter 方法 */
  public String getPk_purchaseorg_v() {
    return (String) this.getAttributeValue(ArriveHeaderVO.PK_PURCHASEORG_V);
  }

  /** 批次号编码 getter 方法 */
  public String getVbatchcode() {
    return (String) this.getAttributeValue(ArriveItemVO.VBATCHCODE);
  }

  /** 到货单号 getter 方法 */
  public String getVbillcode() {
    return (String) this.getAttributeValue(ArriveHeaderVO.VBILLCODE);
  }

  /** 换算率 getter 方法 */
  public String getVchangerate() {
    return (String) this.getAttributeValue(ArriveItemVO.VCHANGERATE);
  }

  /** 自由辅助属性1 getter 方法 */
  public String getVfree1() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE1);
  }

  /** 自由辅助属性10 getter 方法 */
  public String getVfree10() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE10);
  }

  /** 自由辅助属性2 getter 方法 */
  public String getVfree2() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE2);
  }

  /** 自由辅助属性3 getter 方法 */
  public String getVfree3() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE3);
  }

  /** 自由辅助属性4 getter 方法 */
  public String getVfree4() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE4);
  }

  /** 自由辅助属性5 getter 方法 */
  public String getVfree5() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE5);
  }

  /** 自由辅助属性6 getter 方法 */
  public String getVfree6() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE6);
  }

  /** 自由辅助属性7 getter 方法 */
  public String getVfree7() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE7);
  }

  /** 自由辅助属性8 getter 方法 */
  public String getVfree8() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE8);
  }

  /** 自由辅助属性9 getter 方法 */
  public String getVfree9() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE9);
  }
}
