package nc.pubitf.pu.m23.ec;

import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * 向供应商门户传递退货信息的视图VO
 * 
 * @since 6.0
 * @version 2012-10-22 下午05:00:20
 * @author lixyp
 */
public class BackArriveViewECVO extends AbstractDataView {

  /**
   * 此视图VO对应元数据
   * 
   * @since 6.0
   * @version 2012-10-22 下午05:00:52
   * @author lixyp
   */
  public static class BackArriveViewECVOMeta extends DataViewMeta {

    public BackArriveViewECVOMeta() {
      this.init();
    }

    private void init() {
      // Header：退货单ID、退货单号、采购公司、采购部门、采购员、退货日期、响应状态、退货理由、退货方式、供应商退货说明
      this.add(ArriveHeaderVO.class, new String[] {
        ArriveHeaderVO.PK_ARRIVEORDER, ArriveHeaderVO.VBILLCODE,
        ArriveHeaderVO.PK_PURCHASEORG, ArriveHeaderVO.PK_DEPT,
        ArriveHeaderVO.PK_PUPSNDOC, ArriveHeaderVO.DBILLDATE,
        ArriveHeaderVO.IRESPSTATUS, ArriveHeaderVO.VBACKREASON,
        ArriveHeaderVO.VSUPBACKREASON
      });

      // Item：订单号（来源单据号）、产品ID、辅助属性、单位ID、退货数量、途耗数量、是否赠品、实际到货日期
      this.add(ArriveItemVO.class, new String[] {
        ArriveItemVO.VSOURCECODE, ArriveItemVO.PK_MATERIAL,
        ArriveItemVO.VFREE1, ArriveItemVO.VFREE2, ArriveItemVO.VFREE3,
        ArriveItemVO.VFREE4, ArriveItemVO.VFREE5, ArriveItemVO.VFREE6,
        ArriveItemVO.VFREE7, ArriveItemVO.VFREE8, ArriveItemVO.VFREE9,
        ArriveItemVO.VFREE10, ArriveItemVO.CASTUNITID, ArriveItemVO.NASTNUM,
        ArriveItemVO.NWASTASTNUM, ArriveItemVO.BPRESENT
      });

      this.addRelation(ArriveHeaderVO.class, ArriveHeaderVO.PK_ARRIVEORDER,
          ArriveItemVO.class, ArriveItemVO.PK_ARRIVEORDER);
    }
  }

  private static final long serialVersionUID = 5315218899131561855L;

  /** 获取是否赠品 */
  public UFBoolean getBpresent() {
    return (UFBoolean) this.getAttributeValue(ArriveItemVO.BPRESENT);
  }

  /** 获取单位ID */
  public String getCastunitid() {
    return (String) this.getAttributeValue(ArriveItemVO.CASTUNITID);
  }

  /** 获取退货日期 */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(ArriveHeaderVO.DBILLDATE);
  }

  /** 获取响应状态 */
  public Integer getIrespstatus() {
    return (Integer) this.getAttributeValue(ArriveHeaderVO.IRESPSTATUS);
  }

  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance().getDataViewMeta(
        BackArriveViewECVOMeta.class);
  }

  /** 获取退货数量 */
  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NASTNUM);
  }

  /** 获取途耗数量 */
  public UFDouble getNwastastnum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NWASTASTNUM);
  }

  /** 获取退货单ID */
  public String getPk_arriveorder() {
    return (String) this.getAttributeValue(ArriveHeaderVO.PK_ARRIVEORDER);
  }

  /** 获取采购部门 */
  public String getPk_dept() {
    return (String) this.getAttributeValue(ArriveHeaderVO.PK_DEPT);
  }

  /** 获取产品ID */
  public String getPk_material() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_MATERIAL);
  }

  /** 获取采购员 */
  public String getPk_pupsndoc() {
    return (String) this.getAttributeValue(ArriveHeaderVO.PK_PUPSNDOC);
  }

  /** 获取采购公司 */
  public String getPk_purchaseorg() {
    return (String) this.getAttributeValue(ArriveHeaderVO.PK_PURCHASEORG);
  }

  /** 获取退货理由 */
  public String getVbackreason() {
    return (String) this.getAttributeValue(ArriveHeaderVO.VBACKREASON);
  }

  /** 获取退货单号 */
  public String getVbillcode() {
    return (String) this.getAttributeValue(ArriveHeaderVO.VBILLCODE);
  }

  /** 获取自由辅助属性1 */
  public String getVfree1() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE1);
  }

  /** 获取自由辅助属性10 */
  public String getVfree10() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE10);
  }

  /** 获取自由辅助属性2 */
  public String getVfree2() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE2);
  }

  /** 获取自由辅助属性3 */
  public String getVfree3() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE3);
  }

  /** 获取自由辅助属性4 */
  public String getVfree4() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE4);
  }

  /** 获取自由辅助属性5 */
  public String getVfree5() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE5);
  }

  /** 获取自由辅助属性6 */
  public String getVfree6() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE6);
  }

  /** 获取自由辅助属性7 */
  public String getVfree7() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE7);
  }

  /** 获取自由辅助属性8 */
  public String getVfree8() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE8);
  }

  /** 获取自由辅助属性9 */
  public String getVfree9() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE9);
  }

  /** 获取来源单据号 */
  public String getVsourcecode() {
    return (String) this.getAttributeValue(ArriveItemVO.VSOURCECODE);
  }

  /** 获取供应商退货说明 */
  public String getVsupbackreason() {
    return (String) this.getAttributeValue(ArriveHeaderVO.VSUPBACKREASON);
  }

}
