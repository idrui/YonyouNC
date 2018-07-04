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
 * @version 2011-5-16 下午01:19:46
 * @author wuxla
 */

public class InvoiceItemECVO extends AbstractDataView {

  public static class InvoiceItemECVOMeta extends DataViewMeta {
    public InvoiceItemECVOMeta() {
      this.init();
    }

    private void init() {
      // 订单号,订单行号, 物料id、自由辅助属性id、批
      // 次号、单位id、数量、价税合计、扣税类别、税率、税额
      // 客户
      this.add(InvoiceItemVO.class, new String[] {
        InvoiceItemVO.VORDERCODE, InvoiceItemVO.VSOURCEROWNO,
        InvoiceItemVO.PK_MATERIAL, InvoiceItemVO.VFREE1, InvoiceItemVO.VFREE2,
        InvoiceItemVO.VFREE3, InvoiceItemVO.VFREE4, InvoiceItemVO.VFREE5,
        InvoiceItemVO.VFREE6, InvoiceItemVO.VFREE7, InvoiceItemVO.VFREE8,
        InvoiceItemVO.VFREE9, InvoiceItemVO.VFREE10, InvoiceItemVO.VBATCHCODE,
        InvoiceItemVO.CASTUNITID, InvoiceItemVO.CUNITID, InvoiceItemVO.NASTNUM,
        InvoiceItemVO.NORIGTAXMNY, InvoiceItemVO.FTAXTYPEFLAG,
        InvoiceItemVO.NTAXRATE, InvoiceItemVO.CPRODUCTORID,
        InvoiceItemVO.CPROJECTID, InvoiceItemVO.CASSCUSTID
      });

      this.add(InvoiceHeaderVO.class, new String[] {
        InvoiceHeaderVO.CORIGCURRENCYID, InvoiceHeaderVO.DARRIVEDATE,
        InvoiceHeaderVO.VBILLCODE, InvoiceHeaderVO.DBILLDATE

      });
      this.addRelation(InvoiceItemVO.class, InvoiceItemVO.PK_INVOICE,
          InvoiceHeaderVO.class, InvoiceHeaderVO.PK_INVOICE);
    }
  }

  private static final long serialVersionUID = 1L;

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

  /** 主单位 getter 方法 */
  public String getCunitid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CUNITID);
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
        InvoiceItemECVOMeta.class);
  }

  /** 数量 getter 方法 */
  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NASTNUM);
  }

  /** 价税合计 getter 方法 */
  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NORIGTAXMNY);
  }

  /** 税率 getter 方法 */
  public UFDouble getNtaxrate() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NTAXRATE);
  }

  /** 物料(VID) getter 方法 */
  public String getPk_material() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_MATERIAL);
  }

  /** 批次号 getter 方法 */
  public String getVbatchcode() {
    return (String) this.getAttributeValue(InvoiceItemVO.VBATCHCODE);
  }

  /** 发票号 getter 方法 */
  public String getVbillcode() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VBILLCODE);
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

  /** 订单号 getter 方法 */
  public String getVordercode() {
    return (String) this.getAttributeValue(InvoiceItemVO.VORDERCODE);
  }

  /** 来源单据行号 getter 方法 */
  public String getVsourcerowno() {
    return (String) this.getAttributeValue(InvoiceItemVO.VSOURCEROWNO);
  }
}
