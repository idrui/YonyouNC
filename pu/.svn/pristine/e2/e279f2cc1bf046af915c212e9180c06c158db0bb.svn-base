package nc.pubimpl.pu.m21.pu.m20;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.impl.pu.m21.action.OrderTranTypeAction;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.pubitf.pu.m21.pu.m20.IOrderQueryFor20;
import nc.pubitf.pu.m21transtype.IPoTransTypeQuery;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.m20.QueryParaFor20;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pu.m21transtype.enumeration.CreateorderType;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2011-4-19 下午03:22:09
 * @author 田锋涛
 */

public class OrderQueryFor20Impl implements IOrderQueryFor20 {

  @Override
  public Set<String> filterItemsByOrderTranType(QueryParaFor20[] paras)
      throws BusinessException {
    Set<String> tranTypes = new HashSet<String>();
    try {
      for (QueryParaFor20 para : paras) {
        tranTypes.add(para.getPk_ordertrantype());
      }
      Map<String, PoTransTypeVO> tranTypeMap =
          this.queryOrderTranType(tranTypes.toArray(new String[tranTypes.size()]));
      // 过滤结果
      Set<String> result = new HashSet<String>();
      // 控制类型
      CreateorderType[] types = new CreateorderType[] {
        // 不控制
        CreateorderType.MODE_NO_CTRL,
        // 价格审批单
        CreateorderType.MODE_EXIST_BILL,
        // 供应商有效价格
        CreateorderType.MODE_EXIST_PRICE
      };
      for (CreateorderType type : types) {
        Set<String> tranType = this.filterTranTypeByLimit(tranTypeMap, type);
        QueryParaFor20[] filterParas =
            this.filterParaByTranType(tranType, paras);
        if (filterParas != null) {
          result.addAll(this.filterPrayItemsByContrlType(type, filterParas));
        }
      }
      return result;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * 请购单生成价格审批单的转单过滤。
   * 只有存在供应商有效价格的请购单行才能够查询出来
   * 
   * @param paras
   * @return
   * @throws BusinessException
   */
  @Override
  public Set<String> filterItemsByVendorExistPrice(QueryParaFor20[] paras)
      throws BusinessException {
    try {
      return this.filterPrayItemsByVendorPrice(paras);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public Map<String, UFDouble> getSupplierQuantum(String pk_purchaseorg,
      String[] pk_suppliers, String pk_srcmaterial, UFDate beginDate,
      UFDate endDate) throws BusinessException {
    try {
      IDExQueryBuilder builder =
          new IDExQueryBuilder(PUTempTable.tmp_po_21_27.name());
      String supplierSql =
          builder.buildSQL("b." + OrderItemVO.PK_SUPPLIER, pk_suppliers);
      SqlBuilder sql = new SqlBuilder();
      sql.append("select b." + OrderItemVO.PK_SUPPLIER);
      sql.append(",sum(b." + OrderItemVO.NNUM + ") ");
      sql.append("from " + PUEntity.M21_B_TABLE + " b ");
      sql.append(" where ");
      sql.append(" b." + OrderItemVO.DR, 0);
      sql.append(" and b." + OrderItemVO.FISACTIVE, new int[] {
        EnumActive.ACTIVE.toInt(), EnumActive.CLOSE.toInt()
      });
      sql.append(" and b." + OrderItemVO.PK_ORG, pk_purchaseorg);
      sql.append(" and b." + OrderItemVO.PK_SRCMATERIAL, pk_srcmaterial);
      sql.append(" and " + supplierSql);
      if (beginDate != null) {
        sql.append(" and b." + OrderItemVO.DBILLDATE, " >= ",
            beginDate.toString());
      }
      if (endDate != null) {
        sql.append(" and b." + OrderItemVO.DBILLDATE, " <= ",
            endDate.toString());
      }
      sql.append(" group by b." + OrderItemVO.PK_SUPPLIER);
      DataAccessUtils utils = new DataAccessUtils();
      IRowSet rowset = utils.query(sql.toString());
      String[][] values = rowset.toTwoDimensionStringArray();
      Map<String, UFDouble> map = new HashMap<String, UFDouble>();
      if (ArrayUtils.isEmpty(values)) {
        return map;
      }
      for (String[] value : values) {
        String pk_supplier = value[0];
        String nquotum = value[1];
        if (null == nquotum) {
          map.put(pk_supplier, UFDouble.ZERO_DBL);
        }
        else {
          map.put(pk_supplier, new UFDouble(nquotum));
        }
      }
      return map;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * 根据单据类型过滤参数
   * 
   * @param tranTypes
   * @param paras
   * @return
   */
  private QueryParaFor20[] filterParaByTranType(Set<String> tranTypes,
      QueryParaFor20[] paras) {
    List<QueryParaFor20> paraList = new ArrayList<QueryParaFor20>();
    if (tranTypes.size() == 0) {
      return null;
    }
    for (QueryParaFor20 para : paras) {
      if (tranTypes.contains(para.getPk_ordertrantype())) {
        paraList.add(para);
      }
    }
    if (paraList.size() == 0) {
      return null;
    }
    return paraList.toArray(new QueryParaFor20[paraList.size()]);
  }

  /**
   * 根据控制类型过滤
   * 
   * @param type
   * @param paras
   * @return
   */
  private Set<String> filterPrayItemsByContrlType(CreateorderType type,
      QueryParaFor20[] paras) {
    if (CreateorderType.MODE_NO_CTRL.equals(type)) {
      return this.filterPrayItemsByNoContrl(paras);
    }
    if (CreateorderType.MODE_EXIST_PRICE.equals(type)) {
      return this.filterPrayItemsByVendorPrice(paras);
    }
    return this.filterPrayItemsByM28(paras);
  }

  /**
   * 根据价格审批单过滤
   * 
   * @param paras
   * @return
   */
  private Set<String> filterPrayItemsByM28(QueryParaFor20[] paras) {
    Set<String> pk_praybill_b = new HashSet<String>();
    for (QueryParaFor20 para : paras) {
      pk_praybill_b.add(para.getPk_praybill_b());
    }
    return new OrderTranTypeAction().filterPrayItemsByM28(pk_praybill_b
        .toArray(new String[pk_praybill_b.size()]));

  }

  /**
   * 订单类型不控制，直接返回请购单行id
   * 
   * @param paras
   * @return
   */
  private Set<String> filterPrayItemsByNoContrl(QueryParaFor20[] paras) {
    Set<String> pk_praybill_b = new HashSet<String>();
    for (QueryParaFor20 para : paras) {
      pk_praybill_b.add(para.getPk_praybill_b());
    }
    return pk_praybill_b;
  }

  /**
   * 根据供应商有效价格过滤
   * 
   * @param paras
   * @return
   */
  private Set<String> filterPrayItemsByVendorPrice(QueryParaFor20[] paras) {
    return new OrderTranTypeAction().filterPrayItemsByVendorEffectPrc(paras);

  }

  /**
   * 根据请购单生产订单控制方式分组交易类型
   * 
   * @param tranTypeMap 交易类型
   * @param limitType 请购单生产订单控制方式
   * @return
   */
  private Set<String> filterTranTypeByLimit(
      Map<String, PoTransTypeVO> tranTypeMap, CreateorderType limitType) {
    Set<String> tranTypeSet = new HashSet<String>();
    for (Entry<String, PoTransTypeVO> tranType : tranTypeMap.entrySet()) {
      Integer prtopolimit = tranType.getValue().getIprtopolimit();
      if (limitType.toInteger().equals(prtopolimit)) {
        tranTypeSet.add(tranType.getKey());
      }
    }
    return tranTypeSet;
  }

  /**
   * 查询订单交易类型
   * 
   * @param tranTypes
   * @return
   */
  private Map<String, PoTransTypeVO> queryOrderTranType(String[] tranTypes) {
    try {
      // 查询交易类型属性：请购单生成订单限制方式
      return NCLocator.getInstance().lookup(IPoTransTypeQuery.class)
          .queryAttrByIDs(tranTypes);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;

  }

}
