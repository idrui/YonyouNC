package nc.pubift.pu.m25.ec;

import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * @since 6.0
 * @version 2011-5-16 下午01:03:36
 * @author wuxla
 */

public class InvoiceMatViewECVO extends AbstractDataView {

  public static class InvoiceMatViewECVOMeta extends DataViewMeta {
    public InvoiceMatViewECVOMeta() {
      this.init();
    }

    private void init() {
      // 发票号、发票日期、票到日期、币种id、物料id、自由辅助属性id、
      // 批次号、单位id、数量、累计结算数量、累计结算金额,扣税类别 ,单价、
      // 价税合计、税率、税额,换算率
      // 项目、生产厂商、客户
      this.add(InvoiceItemVO.class, new String[] {
        InvoiceItemVO.PK_MATERIAL, InvoiceItemVO.VFREE1, InvoiceItemVO.VFREE2,
        InvoiceItemVO.VFREE3, InvoiceItemVO.VFREE4, InvoiceItemVO.VFREE5,
        InvoiceItemVO.VFREE6, InvoiceItemVO.VFREE7, InvoiceItemVO.VFREE8,
        InvoiceItemVO.VFREE9, InvoiceItemVO.VFREE10, InvoiceItemVO.VBATCHCODE,
        InvoiceItemVO.CASTUNITID, InvoiceItemVO.NASTNUM,
        InvoiceItemVO.NACCUMSETTNUM, InvoiceItemVO.NACCUMSETTMNY,
        InvoiceItemVO.FTAXTYPEFLAG, InvoiceItemVO.NTAXPRICE,
        InvoiceItemVO.NORIGTAXMNY, InvoiceItemVO.NTAXRATE,
        InvoiceItemVO.VCHANGERATE, InvoiceItemVO.CPROJECTID,
        InvoiceItemVO.CPRODUCTORID, InvoiceItemVO.CASSCUSTID
      });
      this.add(InvoiceHeaderVO.class, new String[] {
        InvoiceHeaderVO.VBILLCODE, InvoiceHeaderVO.DBILLDATE,
        InvoiceHeaderVO.DARRIVEDATE, InvoiceHeaderVO.CORIGCURRENCYID,
        InvoiceHeaderVO.PK_PURCHASEORG
      });
      this.addRelation(InvoiceItemVO.class, InvoiceItemVO.PK_INVOICE,
          InvoiceHeaderVO.class, InvoiceHeaderVO.PK_INVOICE);
    }
  }

  private static final long serialVersionUID = 9181479655420938350L;

  /** 客户 getter 方法 */
  public String getCasscustid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CASSCUSTID);
  }

  /** 单位 getter 方法 */
  public String getCastunitid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CASTUNITID);
  }

  /** 币种 getter 方法 */
  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.CORIGCURRENCYID);
  }

  /** 生产厂商 getter 方法 */
  public String getCproductorid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CPRODUCTORID);
  }

  /** 项目 getter 方法 */
  public String getCprojectid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CPROJECTID);
  }

  /** 票到日期 getter 方法 */
  public UFDate getDarrivedate() {
    return (UFDate) this.getAttributeValue(InvoiceHeaderVO.DARRIVEDATE);
  }

  /** 发票日期 getter 方法 */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(InvoiceHeaderVO.DBILLDATE);
  }

  /** 扣税类别 getter 方法 */
  public Integer getFtaxtypeflag() {
    return (Integer) this.getAttributeValue(InvoiceItemVO.FTAXTYPEFLAG);
  }

  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance().getDataViewMeta(
        InvoiceMatViewECVOMeta.class);
  }

  /** 累计本币结算金额 getter 方法 */
  public UFDouble getNaccumsettmny() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NACCUMSETTMNY);
  }

  /** 累计结算主数量 getter 方法 */
  public UFDouble getNaccumsettnum() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NACCUMSETTNUM);
  }

  /** 数量 getter 方法 */
  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NASTNUM);
  }

  /** 价税合计 getter 方法 */
  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NORIGTAXMNY);
  }

  /** 主本币含税单价 getter 方法 */
  public UFDouble getNtaxprice() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NTAXPRICE);
  }

  /** 税率 getter 方法 */
  public UFDouble getNtaxrate() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NTAXRATE);
  }

  /** 物料(VID) getter 方法 */
  public String getPk_material() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_MATERIAL);
  }

  /** 采购组织 getter 方法 */
  public String getPk_purchaseorg() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_PURCHASEORG);
  }

  /** 批次号 getter 方法 */
  public String getVbatchcode() {
    return (String) this.getAttributeValue(InvoiceItemVO.VBATCHCODE);
  }

  /** 发票号 getter 方法 */
  public String getVbillcode() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VBILLCODE);
  }

  /** 换算率 getter 方法 */
  public String getVchangerate() {
    return (String) this.getAttributeValue(InvoiceItemVO.VCHANGERATE);
  }

  /** 自由辅助属性1 getter 方法 */
  public String getVfree1() {
    return (String) this.getAttributeValue(InvoiceItemVO.VFREE1);
  }

  /** 自由辅助属性10 getter 方法 */
  public String getVfree10() {
    return (String) this.getAttributeValue(InvoiceItemVO.VFREE10);
  }

  /** 自由辅助属性2 getter 方法 */
  public String getVfree2() {
    return (String) this.getAttributeValue(InvoiceItemVO.VFREE2);
  }

  /** 自由辅助属性3 getter 方法 */
  public String getVfree3() {
    return (String) this.getAttributeValue(InvoiceItemVO.VFREE3);
  }

  /** 自由辅助属性4 getter 方法 */
  public String getVfree4() {
    return (String) this.getAttributeValue(InvoiceItemVO.VFREE4);
  }

  /** 自由辅助属性5 getter 方法 */
  public String getVfree5() {
    return (String) this.getAttributeValue(InvoiceItemVO.VFREE5);
  }

  /** 自由辅助属性6 getter 方法 */
  public String getVfree6() {
    return (String) this.getAttributeValue(InvoiceItemVO.VFREE6);
  }

  /** 自由辅助属性7 getter 方法 */
  public String getVfree7() {
    return (String) this.getAttributeValue(InvoiceItemVO.VFREE7);
  }

  /** 自由辅助属性8 getter 方法 */
  public String getVfree8() {
    return (String) this.getAttributeValue(InvoiceItemVO.VFREE8);
  }

  /** 自由辅助属性9 getter 方法 */
  public String getVfree9() {
    return (String) this.getAttributeValue(InvoiceItemVO.VFREE9);
  }
}
