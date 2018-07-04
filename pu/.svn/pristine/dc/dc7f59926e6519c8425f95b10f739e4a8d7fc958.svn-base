package nc.pubitf.pu.m23.ec;

import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * @since 6.0
 * @version 2011-5-16 下午12:04:00
 * @author wuxla
 */

public class BackArriveOrderViewECVO extends AbstractDataView {
  public static class BackArriveOrderViewECVOMeta extends DataViewMeta {
    public BackArriveOrderViewECVOMeta() {
      this.init();
    }

    private void init() {
      // 订单子表pk、货单号、退货日期、退货原因、物料id、自由辅助属性id、批次号
      // 单位、退货数量、换算率、主单位、主数量
      // 项目、生产厂商、客户
      this.add(ArriveItemVO.class, new String[] {
        ArriveItemVO.PK_ORDER_B, ArriveItemVO.PK_MATERIAL, ArriveItemVO.VFREE1,
        ArriveItemVO.VFREE2, ArriveItemVO.VFREE3, ArriveItemVO.VFREE4,
        ArriveItemVO.VFREE5, ArriveItemVO.VFREE6, ArriveItemVO.VFREE7,
        ArriveItemVO.VFREE8, ArriveItemVO.VFREE9, ArriveItemVO.VFREE10,
        ArriveItemVO.VBATCHCODE, ArriveItemVO.CASTUNITID, ArriveItemVO.NASTNUM,
        ArriveItemVO.VCHANGERATE, ArriveItemVO.CUNITID, ArriveItemVO.NNUM,
        ArriveItemVO.CPROJECTID, ArriveItemVO.CPRODUCTORID, ArriveItemVO.CASSCUSTID 
      });
      this.add(ArriveHeaderVO.class, new String[] {
        ArriveHeaderVO.VBILLCODE, ArriveHeaderVO.DBILLDATE,
        ArriveHeaderVO.VBACKREASON
      });
      this.addRelation(ArriveItemVO.class, ArriveItemVO.PK_ARRIVEORDER,
          ArriveHeaderVO.class, ArriveHeaderVO.PK_ARRIVEORDER);
    }
  }

  private static final long serialVersionUID = -1988703182503021770L;

  /** 单位 getter 方法 */
  public String getCastunitid() {
    return (String) this.getAttributeValue(ArriveItemVO.CASTUNITID);
  }

  /** 主单位 getter 方法 */
  public String getCunitid() {
    return (String) this.getAttributeValue(ArriveItemVO.CUNITID);
  }

  /** 到货日期 getter 方法 */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(ArriveHeaderVO.DBILLDATE);
  }

  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance().getDataViewMeta(
        BackArriveOrderViewECVOMeta.class);
  }

  /** 到货数量 getter 方法 */
  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NASTNUM);
  }

  /** 主数量 getter 方法 */
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NNUM);
  }

  /** 物料VID getter 方法 */
  public String getPk_material() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_MATERIAL);
  }

  /** 订单子表pk getter 方法 */
  public String getPk_order_b() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_ORDER_B);
  }

  /** 退货理由 getter 方法 */
  public String getVbackreason() {
    return (String) this.getAttributeValue(ArriveHeaderVO.VBACKREASON);
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
}
