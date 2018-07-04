package nc.pubitf.pu.m21.ec;

import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * 供货情况
 * 
 * @since 6.0
 * @version 2011-5-8 下午12:35:45
 * @author wuxla
 */

public class SupplyDetailVO extends AbstractDataView {

  public static class SupplyDetailVOMeta extends DataViewMeta {
    public SupplyDetailVOMeta() {
      this.init();
    }

    private void init() {
      // 产品主键、单位id、辅助属性,订单主数量,订单金额（本币）、到货数量、
      // 退货数量、入库数量、退库数量、开票数量,开票金额（本币）,换算率,辅单位
      // 客户
      this.add(OrderItemVO.class, new String[] {
        OrderItemVO.PK_ORG, OrderItemVO.PK_ORG_V, OrderItemVO.PK_MATERIAL,
        OrderItemVO.CUNITID, OrderItemVO.NNUM, OrderItemVO.CPRODUCTORID,
        OrderItemVO.CPROJECTID, OrderItemVO.VFREE1, OrderItemVO.VFREE2,
        OrderItemVO.VFREE3, OrderItemVO.VFREE4, OrderItemVO.VFREE5,
        OrderItemVO.VFREE6, OrderItemVO.VFREE7, OrderItemVO.VFREE8,
        OrderItemVO.VFREE9, OrderItemVO.VFREE10, OrderItemVO.NGLOBALTAXMNY,
        OrderItemVO.NACCUMARRVNUM, OrderItemVO.NBACKARRVNUM,
        OrderItemVO.NACCUMSTORENUM, OrderItemVO.NBACKSTORENUM,
        OrderItemVO.NACCUMINVOICENUM, OrderItemVO.NACCUMINVOICEMNY,
        OrderItemVO.VCHANGERATE, OrderItemVO.CASTUNITID, OrderItemVO.TS,
        OrderItemVO.NORIGTAXMNY, OrderItemVO.NEXCHANGERATE,
        OrderItemVO.CASSCUSTID
      });
    }
  }

  private static final long serialVersionUID = -8675108571289427729L;

  /** 客户 getter 方法 */
  public String getCasscustid() {
    return (String) this.getAttributeValue(OrderItemVO.CASSCUSTID);
  }

  /** 辅单位 getter 方法 */
  public String getCastunitid() {
    return (String) this.getAttributeValue(OrderItemVO.CASTUNITID);
  }

  /** 生产厂商 getter 方法 */
  public String getCproductorid() {
    return (String) this.getAttributeValue(OrderItemVO.CPRODUCTORID);
  }

  /** 项目 getter 方法 */
  public String getCprojectid() {
    return (String) this.getAttributeValue(OrderItemVO.CPROJECTID);
  }

  /** 主单位 getter 方法 */
  public String getCunitid() {
    return (String) this.getAttributeValue(OrderItemVO.CUNITID);
  }

  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance().getDataViewMeta(
        SupplyDetailVOMeta.class);
  }

  /** 累计到货主数量 getter 方法 */
  public UFDouble getNaccumarrvnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NACCUMARRVNUM);
  }

  /** 累计本币开票金额 getter 方法 */
  public UFDouble getNaccuminvoicemny() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NACCUMINVOICEMNY);
  }

  /** 累计开票主数量 getter 方法 */
  public UFDouble getNaccuminvoicenum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NACCUMINVOICENUM);
  }

  /** 累计入库主数量 getter 方法 */
  public UFDouble getNaccumstorenum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NACCUMSTORENUM);
  }

  /** 累计退货主数量 getter 方法 */
  public UFDouble getNbackarrvnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NBACKARRVNUM);
  }

  /** 累计退库主数量 getter 方法 */
  public UFDouble getNbackstorenum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NBACKSTORENUM);
  }

  /** 折本汇率 */
  public UFDouble getNexchangerate() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NEXCHANGERATE);
  }

  /** 全局本币价税合计 getter 方法 */
  public UFDouble getNglobaltaxmny() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NGLOBALTAXMNY);
  }

  /** 主数量 getter 方法 */
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NNUM);
  }

  /** 价税合计 getter 方法 */
  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NORIGTAXMNY);
  }

  /** 物料版本信息 getter 方法 */
  public String getPk_material() {
    return (String) this.getAttributeValue(OrderItemVO.PK_MATERIAL);
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
}
