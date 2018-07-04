package nc.pubitf.pu.m23.ec;

import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * @since 6.0
 * @version 2011-5-16 下午01:24:24
 * @author wuxla
 */

public class ArriveItemECVO extends AbstractDataView {

  private static final long serialVersionUID = -2990167313132295149L;

  public static class ArriveItemECVOMeta extends DataViewMeta {
    public ArriveItemECVOMeta() {
      init();
    }

    private void init() {
      // 订单号、订单行号、产品名称、规格、型号、描述、批次号、单位名称、到货数
      // 量、合格数量、不合格数量、单价、价税合计、税率、币种名称、累计入库数量,换算率
      // 途耗数量
      // 项目、生产厂商、客户
      // 自由辅助属性、主原币含税单价
      add(ArriveItemVO.class, new String[] {
        ArriveItemVO.VSOURCECODE, ArriveItemVO.VSOURCEROWNO,
        ArriveItemVO.PK_MATERIAL, ArriveItemVO.VBATCHCODE,
        ArriveItemVO.CASTUNITID, ArriveItemVO.NASTNUM, ArriveItemVO.NELIGNUM,
        ArriveItemVO.NNOTELIGNUM, ArriveItemVO.NTAXPRICE,
        ArriveItemVO.NORIGTAXMNY, ArriveItemVO.NTAXRATE,
        ArriveItemVO.CORIGCURRENCYID, ArriveItemVO.NACCUMSTORENUM,
        ArriveItemVO.NWASTNUM, ArriveItemVO.VCHANGERATE,
        ArriveItemVO.CPROJECTID, ArriveItemVO.CPRODUCTORID, ArriveItemVO.CASSCUSTID,
        ArriveItemVO.VFREE1, ArriveItemVO.VFREE2,
        ArriveItemVO.VFREE3, ArriveItemVO.VFREE4, ArriveItemVO.VFREE5,
        ArriveItemVO.VFREE6, ArriveItemVO.VFREE7, ArriveItemVO.VFREE8,
        ArriveItemVO.VFREE9, ArriveItemVO.VFREE10,
        ArriveItemVO.NORIGTAXPRICE
      });
    }
  }

  /** 单位 getter 方法 */
  public String getCastunitid() {
    return (String) this.getAttributeValue(ArriveItemVO.CASTUNITID);
  }

  /** 原币币种 getter 方法 */
  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(ArriveItemVO.CORIGCURRENCYID);
  }

  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance().getDataViewMeta(
        ArriveItemECVOMeta.class);
  }

  /** 累计入库主数量 getter 方法 */
  public UFDouble getNaccumstorenum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NACCUMSTORENUM);
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

  /** 途耗主数量 getter 方法 */
  public UFDouble getNwastnum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NWASTNUM);
  }

  /** 物料VID getter 方法 */
  public String getPk_material() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_MATERIAL);
  }

  /** 批次号编码 getter 方法 */
  public String getVbatchcode() {
    return (String) this.getAttributeValue(ArriveItemVO.VBATCHCODE);
  }

  /** 换算率 getter 方法 */
  public String getVchangerate() {
    return (String) this.getAttributeValue(ArriveItemVO.VCHANGERATE);
  }

  /** 来源单据号 getter 方法 */
  public String getVsourcecode() {
    return (String) this.getAttributeValue(ArriveItemVO.VSOURCECODE);
  }

  /** 来源单据行号 getter 方法 */
  public String getVsourcerowno() {
    return (String) this.getAttributeValue(ArriveItemVO.VSOURCEROWNO);
  }
  
  /** 项目 getter 方法 */
  public String getCprojectid() {
    return (String) this.getAttributeValue(ArriveItemVO.CPROJECTID);
  }
  
  /** 生产厂商 getter 方法 */
  public String getCproductorid() {
    return (String) this.getAttributeValue(ArriveItemVO.CPRODUCTORID);
  }
  
  /** 客户 getter 方法 */
  public String getCasscustid() {
    return (String) this.getAttributeValue(ArriveItemVO.CASSCUSTID);
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
  
  /** 主原币含税单价 getter 方法 */
  public UFDouble getNorigtaxprice() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NORIGTAXPRICE);
  }
  
 

}
