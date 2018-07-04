package nc.pubitf.pu.m21.ec;

import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * @since 6.0
 * @version 2011-5-16 下午12:24:30
 * @author wuxla
 */

public class OrderMatViewECVO extends AbstractDataView {
  public static class OrderMatViewECVOMeta extends DataViewMeta {
    public OrderMatViewECVOMeta() {
      this.init();
    }

    private void init() {
      // 订单号、订单主键、产品主键、自由辅助属性id、批次号、单位id、数
      // 量、含税单价、折扣率(%)、净含税单价、价税合计、税率（%）、税额、
      // 币种id、计划到货日期,换算率
      // 主单位、主数量、报价单位、报检数量、辅单位、辅数量、报价换算率
      // 项目、生产厂商、客户
      this.add(OrderItemVO.class, new String[] {
        OrderItemVO.PK_ORG_V, OrderItemVO.PK_ORG, OrderItemVO.PK_MATERIAL,
        OrderItemVO.VFREE1, OrderItemVO.VFREE2, OrderItemVO.VFREE3,
        OrderItemVO.VFREE4, OrderItemVO.VFREE5, OrderItemVO.VFREE6,
        OrderItemVO.VFREE7, OrderItemVO.VFREE8, OrderItemVO.VFREE9,
        OrderItemVO.VFREE10, OrderItemVO.VBATCHCODE, OrderItemVO.CASTUNITID,
        OrderItemVO.NASTNUM, OrderItemVO.NQTORIGTAXPRICE,
        OrderItemVO.NITEMDISCOUNTRATE, OrderItemVO.NORIGORDERPRICE,
        OrderItemVO.NORIGTAXNETPRICE, OrderItemVO.NORIGTAXMNY,
        OrderItemVO.NTAXRATE, OrderItemVO.NTAX, OrderItemVO.CORIGCURRENCYID,
        OrderItemVO.DPLANARRVDATE, OrderItemVO.VCHANGERATE, OrderItemVO.TS,
        OrderItemVO.CUNITID, OrderItemVO.NNUM, OrderItemVO.CQTUNITID,
        OrderItemVO.NQTUNITNUM, OrderItemVO.VQTUNITRATE,
        OrderItemVO.CPROJECTID, OrderItemVO.CPRODUCTORID,
        OrderItemVO.CASSCUSTID
      });
      this.add(OrderHeaderVO.class, new String[] {
        OrderHeaderVO.VBILLCODE, OrderHeaderVO.PK_ORDER
      });
      this.addRelation(OrderItemVO.class, OrderItemVO.PK_ORDER,
          OrderHeaderVO.class, OrderHeaderVO.PK_ORDER);
    }
  }

  private static final long serialVersionUID = -1278809004891582673L;

  /** 客户 getter 方法 */
  public String getCasscustid() {
    return (String) this.getAttributeValue(OrderItemVO.CASSCUSTID);
  }

  /** 单位 getter 方法 */
  public String getCastunitid() {
    return (String) this.getAttributeValue(OrderItemVO.CASTUNITID);
  }

  /** 币种 getter 方法 */
  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(OrderItemVO.CORIGCURRENCYID);
  }

  /** 生产厂商 getter 方法 */
  public String getCproductorid() {
    return (String) this.getAttributeValue(OrderItemVO.CPRODUCTORID);
  }

  /** 项目 getter 方法 */
  public String getCprojectid() {
    return (String) this.getAttributeValue(OrderItemVO.CPROJECTID);
  }

  /** 报价单位 getter 方法 */
  public String getCqtunitid() {
    return (String) this.getAttributeValue(OrderItemVO.CQTUNITID);
  }

  /** 主单位 getter 方法 */
  public String getCunitid() {
    return (String) this.getAttributeValue(OrderItemVO.CUNITID);
  }

  /** 计划到货日期 getter 方法 */
  public UFDate getDplanarrvdate() {
    return (UFDate) this.getAttributeValue(OrderItemVO.DPLANARRVDATE);
  }

  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance().getDataViewMeta(
        OrderMatViewECVOMeta.class);
  }

  /** 数量 getter 方法 */
  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NASTNUM);
  }

  /** 折扣 getter 方法 */
  public UFDouble getNitemdiscountrate() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NITEMDISCOUNTRATE);
  }

  /** 主数量 getter 方法 */
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NNUM);
  }

  /** 净含税单价 getter 方法 */
  public UFDouble getNorigorderprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NORIGORDERPRICE);
  }

  /** 价税合计 getter 方法 */
  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NORIGTAXMNY);
  }

  /** 主含税净价 getter 方法 */
  public UFDouble getNorigtaxnetprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NORIGTAXNETPRICE);
  }

  /** 含税单价 getter 方法 */
  public UFDouble getNqtorigtaxprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NQTORIGTAXPRICE);
  }

  /** 报价数量 getter 方法 */
  public UFDouble getNqtunitnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NQTUNITNUM);
  }

  /** 税额 getter 方法 */
  public UFDouble getNtax() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NTAX);
  }

  /** 税率 getter 方法 */
  public UFDouble getNtaxrate() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NTAXRATE);
  }

  /** 物料版本信息 getter 方法 */
  public String getPk_material() {
    return (String) this.getAttributeValue(OrderItemVO.PK_MATERIAL);
  }

  /** 采购订单明细 getter 方法 */
  public String getPk_order() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ORDER);
  }

  /** 采购组织版本信息 getter 方法 */
  public String getPk_org() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ORG);
  }

  /** 采购组织 getter 方法 */
  public String getPk_org_v() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ORG_V);
  }

  /** ts getter 方法 */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(OrderItemVO.TS);
  }

  /** 批次号 getter 方法 */
  public String getVbatchcode() {
    return (String) this.getAttributeValue(OrderItemVO.VBATCHCODE);
  }

  /** 订单编号getter 方法 */
  public String getVbillcode() {
    return (String) this.getAttributeValue(OrderHeaderVO.VBILLCODE);
  }

  /** 换算率 getter 方法 */
  public String getVchangerate() {
    return (String) this.getAttributeValue(OrderItemVO.VCHANGERATE);
  }

  /** 自由辅助属性1 getter 方法 */
  public String getVfree1() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE1);
  }

  /** 自由辅助属性10 getter 方法 */
  public String getVfree10() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE10);
  }

  /** 自由辅助属性2 getter 方法 */
  public String getVfree2() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE2);
  }

  /** 自由辅助属性3 getter 方法 */
  public String getVfree3() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE3);
  }

  /** 自由辅助属性4 getter 方法 */
  public String getVfree4() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE4);
  }

  /** 自由辅助属性5 getter 方法 */
  public String getVfree5() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE5);
  }

  /** 自由辅助属性6 getter 方法 */
  public String getVfree6() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE6);
  }

  /** 自由辅助属性7 getter 方法 */
  public String getVfree7() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE7);
  }

  /** 自由辅助属性8 getter 方法 */
  public String getVfree8() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE8);
  }

  /** 自由辅助属性9 getter 方法 */
  public String getVfree9() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE9);
  }

  /** 报价换算率 getter 方法 */
  public String getVqtunitrate() {
    return (String) this.getAttributeValue(OrderItemVO.VQTUNITRATE);
  }

}
