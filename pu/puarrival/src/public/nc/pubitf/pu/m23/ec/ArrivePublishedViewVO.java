package nc.pubitf.pu.m23.ec;

import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * @since 6.0
 * @version 2011-5-16 下午01:13:25
 * @author wuxla
 */

public class ArrivePublishedViewVO extends AbstractDataView {

  public static class ArrivePublishedViewVOMeta extends DataViewMeta {
    public ArrivePublishedViewVOMeta() {
      this.init();
    }

    private void init() {
      // 到货单主键,到货单号、到货日期、采购组织id、采购部门id、收货人id、
      // 是否退货、退货理由、备注
      this.add(ArriveHeaderVO.class, new String[] {
        ArriveHeaderVO.PK_ORG, ArriveHeaderVO.PK_ORG_V,
        ArriveHeaderVO.PK_ARRIVEORDER, ArriveHeaderVO.VBILLCODE,
        ArriveHeaderVO.DBILLDATE, ArriveHeaderVO.PK_PURCHASEORG,
        ArriveHeaderVO.PK_PURCHASEORG_V, ArriveHeaderVO.PK_DEPT,
        ArriveHeaderVO.PK_DEPT_V, ArriveHeaderVO.PK_RECEIVEPSNDOC,
        ArriveHeaderVO.BISBACK, ArriveHeaderVO.VBACKREASON,
        ArriveHeaderVO.VMEMO
      });

    }
  }

  private static final long serialVersionUID = -8671305191028684658L;

  /** 退货 getter 方法 */
  public UFBoolean getBisback() {
    return (UFBoolean) this.getAttributeValue(ArriveHeaderVO.BISBACK);
  }

  /** 到货日期 getter 方法 */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(ArriveHeaderVO.DBILLDATE);
  }

  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance().getDataViewMeta(
        ArrivePublishedViewVOMeta.class);

  }

  /** 到货单 getter 方法 */
  public String getPk_arriveorder() {
    return (String) this.getAttributeValue(ArriveHeaderVO.PK_ARRIVEORDER);
  }

  /** 采购部门 getter 方法 */
  public String getPk_dept() {
    return (String) this.getAttributeValue(ArriveHeaderVO.PK_DEPT);
  }

  /** 采购部门版本 getter 方法 */
  public String getPk_dept_v() {
    return (String) this.getAttributeValue(ArriveHeaderVO.PK_DEPT_V);
  }

  /** 采购组织getter 方法 */
  public String getPk_org() {
    return (String) this.getAttributeValue(ArriveHeaderVO.PK_ORG);
  }

  /** 库存组织版本 getter 方法 */
  public String getPk_org_v() {
    return (String) this.getAttributeValue(ArriveHeaderVO.PK_ORG_V);
  }

  /** 采购组织 getter 方法 */
  public String getPk_purchaseorg() {
    return (String) this.getAttributeValue(ArriveHeaderVO.PK_PURCHASEORG);
  }

  /** 采购组织版本 getter 方法 */
  public String getPk_purchaseorg_v() {
    return (String) this.getAttributeValue(ArriveHeaderVO.PK_PURCHASEORG_V);
  }

  /** 收货人 getter 方法 */
  public String getPk_receivepsndoc() {
    return (String) this.getAttributeValue(ArriveHeaderVO.PK_RECEIVEPSNDOC);
  }

  /** 退货理由 getter 方法 */
  public String getVbackreason() {
    return (String) this.getAttributeValue(ArriveHeaderVO.VBACKREASON);
  }

  /** 到货单号 getter 方法 */
  public String getVbillcode() {
    return (String) this.getAttributeValue(ArriveHeaderVO.VBILLCODE);
  }

  /** 备注 getter 方法 */
  public String getVmemo() {
    return (String) this.getAttributeValue(ArriveHeaderVO.VMEMO);
  }

}
