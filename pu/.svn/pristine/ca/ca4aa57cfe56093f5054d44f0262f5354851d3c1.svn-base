package nc.vo.pu.report.queryinfo.arrival;

import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.report.enumeration.PuArrivalGroupEnum;
import nc.vo.pu.report.queryinfo.PuQueryInfoPara;

/**
 * 到货查询自由报表查询参数类
 * 
 * @since 6.0
 * @version 2011-2-22 上午09:59:55
 * @author yinfy
 */

public class PuArrivalQryInfoPara extends PuQueryInfoPara {

  /** 订单日期 */
  public static final String DORDERDATE = "dorderdate";

  /** 计划到货天数 */
  public static final String DPlANDAYS = "dplandays";

  /** 实际到货天数 */
  public static final String DRECEIVEDAYS = "dreceivedays";
  
  /** 采购订单行上的条件 */
  public static final String ORDER_B_COND = "order_b_cond";
  
  /** 到货计划的条件 */
  public static final String ORDER_BB1_COND = "order_bb1_cond";
  
  /** 物料基本分类编码 */
  public static final String BD_MARBASCLASS_CODE = "po_arriveorder_b.pk_material.pk_marbasclass";

  /** 物料编码 */
  public static final String BD_MATERIAL_V_CODE = "po_arriveorder_b.pk_srcmaterial.code";

  /** 物料名称 */
  public static final String BD_MATERIAL_V_NAME = "po_arriveorder_b.pk_srcmaterial.name";

  private static final long serialVersionUID = 4553061134405425021L;

  @Override
  public String[] getGroupKeys() {
    /** 物料 **/
    if (this.getGroupcondtion() != null
        && this.getGroupcondtion().equals(
            PuArrivalGroupEnum.MAR.value().toString())) {

      /**
       * 分组字段：物料
       */
      return new String[] {
        ArriveItemVO.PK_MATERIAL, ArriveHeaderVO.PK_SUPPLIER,
        ArriveItemVO.CUNITID, ArriveItemVO.CASSCUSTID
      };
    }
    /** 明细 **/
    return new String[] {
      ArriveHeaderVO.VBILLCODE, ArriveItemVO.PK_MATERIAL,
      ArriveHeaderVO.PK_SUPPLIER, ArriveItemVO.DPLANRECEIVEDATE,
      ArriveItemVO.DBILLDATE, "dorderdate", ArriveItemVO.CPROJECTID,
      ArriveItemVO.VBATCHCODE
    };
  }

  /**
   * 需要隐藏的字段
   */
  @Override
  public String[] getHideKeys() {
    /** 物料 **/
    if (this.getGroupcondtion() != null
        && this.getGroupcondtion().equals(
            PuArrivalGroupEnum.MAR.value().toString())) {

      /**
       * 隐藏字段：到货单号,供应商,计划到货日期,到货日期,
       * 订单日期,计划到货天数,实际到货天数,项目,批号
       */
      return new String[] {
        ArriveHeaderVO.VBILLCODE, "this.pk_supplier.name",
        ArriveItemVO.DPLANRECEIVEDATE, ArriveHeaderVO.DBILLDATE,
        PuArrivalQryInfoPara.DORDERDATE, PuArrivalQryInfoPara.DPlANDAYS,
        PuArrivalQryInfoPara.DRECEIVEDAYS, "this.cprojectid.project_name",
        ArriveItemVO.VBATCHCODE
      };
    }
    /** 明细 **/
    return new String[] {};
  }

  /**
   * 得到合计字段
   */
  @Override
  public String[] getTotalKeys() {
    /**
     * 合计字段：实到主数量,途耗主数量,赠品主数量,合格主数量,不合格数量,入库主数量
     */
    return new String[] {
      ArriveItemVO.NNUM, ArriveItemVO.NWASTNUM, ArriveItemVO.NPRESENTNUM,
      ArriveItemVO.NELIGNUM, ArriveItemVO.NNOTELIGNUM,
      ArriveItemVO.NACCUMSTORENUM
    };
  }
}
