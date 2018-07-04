package nc.vo.pu.m21.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.plan.MaterialPlanVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>计算计划到货日期<br/>
 * 手工增加订单时，计划到货日期默认取订单日期+提前期，具体算法如下：<br/>
 * 提前期=固定提前期 <br/>
 * {<br/>
 * if 数量>提前期批量<br/>
 * 向上取整[（数量-提前期批量）*提前期系数/提前期批量]<br/>
 * else 0<br/>
 * <br/>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-1-15 下午12:19:55
 */
public class PlanArriveDate {
  private IKeyValue keyValue;

  public PlanArriveDate(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * 方法功能描述：设置计划到货日期。
   * <p>
   * <b>参数说明</b>
   * 
   * @param row 要设置的行ID
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-21 下午01:36:13
   */
  public void setPlanArriveDate(int startRow, int endRow) {
    int[] rows = new int[endRow - startRow + 1];
    for (int i = 0; i < rows.length; i++) {
      rows[i] = startRow++;
    }
    this.setPlanArriveDate(rows);
  }

  public void setPlanArriveDate(int[] rows) {
    // 如果单据日期为空，则不进行计算
    Object objBillDate = this.keyValue.getHeadValue(OrderHeaderVO.DBILLDATE);
    if (objBillDate == null) {
      return;
    }

    // 准备参数
    Map<String, List<String>> params = this.prepareParams(rows);

    if (params.size() != 0) {
      // 查询出物料档案的采购提前期相关信息
      Map<String, Map<String, MaterialPlanVO>> map =
          this.getMateriaPlan(params);

      // 根据物料档案的采购提前期计算计划到货日期
      this.calculatePlanArriveDate(map, rows);
    }
  }

  /**
   * 将计划到货日期改为end类型（截止型日期）
   */
  // public void setPlanArriveDateAsEnd() {
  // for (int i = 0; i < this.keyValue.getItemCount(); ++i) {
  // UFDate date =
  // (UFDate) this.keyValue.getBodyValue(i, OrderItemVO.DPLANARRVDATE);
  // if (null == date) {
  // continue;
  // }
  //
  // date = date.asLocalEnd();
  // this.keyValue.setBodyValue(i, OrderItemVO.DPLANARRVDATE, date);
  // }
  // }

  private void calculatePlanArriveDate(
      Map<String, Map<String, MaterialPlanVO>> map, int[] rows) {
    // 单据日期
    Object objBillDate = this.keyValue.getHeadValue(OrderHeaderVO.DBILLDATE);
    UFDate billDate = (UFDate) objBillDate;
    for (int row : rows) {
      Object storeOrgId =
          this.keyValue.getBodyValue(row, OrderItemVO.PK_ARRVSTOORG);
      Map<String, MaterialPlanVO> mpvo = map.get(storeOrgId);
      if (mpvo != null) {
        Object materialId =
            this.keyValue.getBodyValue(row, OrderItemVO.PK_MATERIAL);
        MaterialPlanVO vo = mpvo.get(materialId);
        if (vo != null) {
          this.calculatePlanArriveDate(vo, billDate, row);
        }
        else {
          this.keyValue.setBodyValue(row, OrderItemVO.DPLANARRVDATE, billDate);
        }
      }
      else {
        this.keyValue.setBodyValue(row, OrderItemVO.DPLANARRVDATE, billDate);
      }
    }
  }

  private void calculatePlanArriveDate(MaterialPlanVO vo, UFDate billDate,
      int row) {
    UFDouble nnum =
        (UFDouble) this.keyValue.getBodyValue(row, OrderItemVO.NNUM);
    // ---------------------计算提前期
    // [0]固定提前期
    int advanceDay =
        vo.getFixedahead() == null ? 0 : vo.getFixedahead().intValue();
    // [1]提前期系数
    UFDouble advanceCoff =
        vo.getAheadcoff() == null ? UFDouble.ZERO_DBL : vo.getAheadcoff();
    // [2]提前期批量
    UFDouble advanceBatch =
        vo.getAheadbatch() == null ? UFDouble.ZERO_DBL : vo.getAheadbatch();
    if (nnum.compareTo(advanceBatch) > 0) {
      // 数量>提前期批量
      UFDouble dTemp = nnum.sub(advanceBatch);
      dTemp = dTemp.multiply(advanceCoff);
      if (advanceBatch.compareTo(UFDouble.ZERO_DBL) > 0) {
        dTemp = dTemp.div(advanceBatch);
      }
      // 向上取整，如果正好为整数，需判断
      int iInt = dTemp.intValue();
      if (MathTool.compareTo(dTemp, new UFDouble(iInt)) != 0) {
        iInt += 1;
      }
      advanceDay += iInt;
    }
    // ---------------------得到计划到货日期
    this.keyValue.setBodyValue(row, OrderItemVO.DPLANARRVDATE,
        billDate.getDateAfter(advanceDay));
  }

  /**
   * 方法功能描述：获得物料计划信息页签中的固定提前期、提前期批量、提前期系数。
   * <p>
   * <b>参数说明</b>
   * 
   * @param params 物料信息参数（key-库存组织的PK，value-物料VID的集合）
   * @return 物料计划信息页签的相关信息（key-库存组织的PK，value-（key-物料PK，value-物料计划信息））
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-21 下午12:36:42
   */
  private Map<String, Map<String, MaterialPlanVO>> getMateriaPlan(
      Map<String, List<String>> params) {
    Map<String, Map<String, MaterialPlanVO>> map =
        new HashMap<String, Map<String, MaterialPlanVO>>();
    Set<Entry<String, List<String>>> storeOrgIds = params.entrySet();
    for (Entry<String, List<String>> storeOrgId : storeOrgIds) {
      String[] materialIds = storeOrgId.getValue().toArray(new String[0]);
      MaterialPlanVO[] vos =
          MaterialPubService.queryMaterialPlanInfoByPks(materialIds,
              storeOrgId.getKey(), new String[] {
                MaterialPlanVO.FIXEDAHEAD, MaterialPlanVO.AHEADBATCH,
                MaterialPlanVO.AHEADCOFF, MaterialPlanVO.PK_ORG,
                MaterialPlanVO.PK_MATERIAL
              });

      if (vos == null) {
        continue;
      }

      Map<String, MaterialPlanVO> vomap = new HashMap<String, MaterialPlanVO>();
      for (MaterialPlanVO vo : vos) {
        vomap.put(vo.getPk_material(), vo);
      }

      map.put(storeOrgId.getKey(), vomap);
    }
    return map;
  }

  private Map<String, List<String>> prepareParams(int[] rows) {
    Map<String, List<String>> map = new HashMap<String, List<String>>();
    for (int row : rows) {
      // 如果原计划到货日期有值，则不再重新计算
      Object planArriveDate =
          this.keyValue.getBodyValue(row, OrderItemVO.DPLANARRVDATE);
      if (planArriveDate != null) {
        continue;
      }

      // 如果物料为空，则不进行计算
      Object materialId =
          this.keyValue.getBodyValue(row, OrderItemVO.PK_MATERIAL);
      if (materialId == null || ((String) materialId).length() == 0) {
        continue;
      }

      // 如果收货库存组织为空，则不进行计算
      Object storeOrgId =
          this.keyValue.getBodyValue(row, OrderItemVO.PK_ARRVSTOORG);
      if (storeOrgId == null || ((String) storeOrgId).length() == 0) {
        continue;
      }

      // 如果数量为空，则不进行计算
      Object norderNum = this.keyValue.getBodyValue(row, OrderItemVO.NNUM);
      if (norderNum == null || ((UFDouble) norderNum).equals(UFDouble.ZERO_DBL)) {
        continue;
      }

      // 组织出参数map
      if (map.containsKey(storeOrgId)) {
        map.get(storeOrgId).add((String) materialId);
      }
      else {
        List<String> ids = new ArrayList<String>();
        ids.add((String) materialId);
        map.put((String) storeOrgId, ids);
      }
    }
    return map;
  }
}
