/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-28 下午06:38:52
 */
package nc.bs.pu.m21.query.so;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.TempTable;
import nc.itf.scmpub.reference.uap.bd.supplier.SupplierPubService;
import nc.vo.bd.supplier.SupplierVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.JavaType;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmf.coop.entity.CoopHeaderVO;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.res.billtype.SOBillType;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ObjectUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>销售订单参照协同采购订单查询BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-28 下午06:38:52
 */
public class CoopOrderQueryFor30BP {

  public OrderVO[] coopOrderQueryFor30(String condition, String pk_puorg,
      String pk_saleorg) {
    DataAccessUtils utils = new DataAccessUtils();
    String[] hids = utils.query(condition).toOneDimensionStringArray();
    if (ArrayUtils.isEmpty(hids)) {
      return null;
    }
    BillQuery<OrderVO> query = new BillQuery<OrderVO>(OrderVO.class);
    OrderVO[] vos = query.query(hids);
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }
    vos = this.filterByOrg(vos);
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }

    vos = this.filterByCoopSetup(vos, pk_puorg, pk_saleorg);
    return vos;
  }

  private OrderVO[] filterByCoopSetup(OrderVO[] vos, String pk_puorg,
      String pk_saleorg) {
    Map<String, String> supMap = this.querySupplier(vos);
    if (null == supMap || supMap.size() == 0) {
      return null;
    }
    List<List<Object>> datas = new ArrayList<List<Object>>();
    for (OrderVO vo : vos) {
      OrderHeaderVO head = vo.getHVO();
      String pk_supplier = head.getPk_supplier();
      String dest_psorg = supMap.get(pk_supplier);
      if (null == dest_psorg) {
        continue;
      }
      List<Object> list = new ArrayList<Object>();
      // 主键
      list.add(head.getPk_order());
      // 来源单据类型
      list.add(POBillType.Order.getCode());
      // 来源交易类型
      list.add(head.getCtrantypeid());
      // 来源结算财务组织
      list.add(vo.getBVO()[0].getPk_psfinanceorg());
      // 来源购销组织
      list.add(pk_puorg);
      // 目的单据类型
      list.add(SOBillType.Order.getCode());
      // 目的购销组织
      list.add(pk_saleorg);
      // 目的结算财务组织
      list.add(dest_psorg);
      datas.add(list);
    }

    if (datas.size() == 0) {
      return null;
    }
    String[] columns =
        new String[] {
          "srcbillpk", "srcbilltype", "srctrantypeid", "srcfinacialpk",
          "srcpkorg", "destbilltype", "destpkorg", "destfinacialpk",
        };
    String[] columnTypes =
        new String[] {
          "varchar(20)", "varchar(20)", "varchar(20)", "varchar(20)",
          "varchar(20)", "varchar(20)", "varchar(20)", "varchar(20)"
        };
    JavaType[] javaTypes =
        new JavaType[] {
          JavaType.String, JavaType.String, JavaType.String, JavaType.String,
          JavaType.String, JavaType.String, JavaType.String, JavaType.String
        };
    TempTable tmpTable = new TempTable();
    String tmpTabelName =
        tmpTable.getTempTable("temp_coopsofilter", columns, columnTypes,
            javaTypes, datas);

    SqlBuilder sql = new SqlBuilder();
    sql.append("select b.srcbillpk from ");
    sql.append("scm_coopsetup a,");
    sql.append(tmpTabelName + " b ");
    sql.append(" where b.srcbilltype = a." + CoopHeaderVO.VBILLTYPE_SRC);
    sql.append(" and b.srctrantypeid=a." + CoopHeaderVO.CTRANTYPEID_SRC);
    sql.append(" and b.srcfinacialpk=a." + CoopHeaderVO.PK_FINANCEORG_SRC);
    sql.append(" and b.srcpkorg=a." + CoopHeaderVO.PK_ORG_SRC);
    sql.append(" and b.destbilltype=a." + CoopHeaderVO.VBILLTYPE_DEST);
    sql.append(" and b.destpkorg=a." + CoopHeaderVO.PK_ORG_DEST);
    sql.append(" and b.destfinacialpk=a." + CoopHeaderVO.PK_FINANCEORG_DEST);
    sql.append(" and a.dr", 0);
    DataAccessUtils util = new DataAccessUtils();
    String[] values = util.query(sql.toString()).toOneDimensionStringArray();
    if (ArrayUtils.isEmpty(values)) {
      return null;
    }

    Set<String> set = new HashSet<String>();
    for (String value : values) {
      set.add(value);
    }

    List<OrderVO> filterList = new ArrayList<OrderVO>();
    for (OrderVO vo : vos) {
      if (set.contains(vo.getHVO().getPk_order())) {
        filterList.add(vo);
      }
    }

    if (filterList.size() == 0) {
      return null;
    }

    return filterList.toArray(new OrderVO[filterList.size()]);
  }

  private OrderVO[] filterByOrg(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }
    List<OrderVO> list = new ArrayList<OrderVO>();
    for (OrderVO vo : vos) {
      if (this.isPsOrgSame(vo)) {
        list.add(vo);
      }
    }
    if (list.size() == 0) {
      return null;
    }
    return list.toArray(new OrderVO[list.size()]);

  }

  private boolean isPsOrgSame(OrderVO vo) {
    OrderItemVO[] itemVOs = vo.getBVO();
    if (ArrayUtils.isEmpty(itemVOs)) {
      return false;
    }
    String pk_psfinanceorg = itemVOs[0].getPk_psfinanceorg();
    int size = itemVOs.length;
    for (int i = 1; i < size; ++i) {
      String pk_psorg = itemVOs[i].getPk_psfinanceorg();
      if (!ObjectUtils.equals(pk_psfinanceorg, pk_psorg)) {
        return false;
      }
    }

    return true;
  }

  private Map<String, String> querySupplier(OrderVO[] vos) {
    Set<String> set = new HashSet<String>();
    for (OrderVO vo : vos) {
      set.add(vo.getHVO().getPk_supplier());
    }
    String[] pk_suppliers = set.toArray(new String[set.size()]);
    SupplierVO[] supVOs =
        SupplierPubService.getSupplierVO(pk_suppliers, new String[] {
          SupplierVO.PK_SUPPLIER, SupplierVO.PK_FINANCEORG, SupplierVO.SUPPROP
        });
    if (ArrayUtils.isEmpty(supVOs)) {
      return null;
    }
    Map<String, String> map = new HashMap<String, String>();
    for (SupplierVO supVO : supVOs) {
      Integer supprop = supVO.getSupprop();
      if (supprop != null && supprop.intValue() == 1) {
        map.put(supVO.getPk_supplier(), supVO.getPk_financeorg());
      }
    }
    return map;
  }

}
