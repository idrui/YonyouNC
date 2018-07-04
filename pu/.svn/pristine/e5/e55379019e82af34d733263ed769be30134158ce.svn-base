/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-1 上午09:35:30
 */
package nc.bs.pu.est.rule.pricequery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.TempTableDefine;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.est.rule.IEstPriceQueryInfoProvide;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>暂估询最新结算价
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-6-1 上午09:35:30
 */
public class EstNewestSettlePriceQryStrategy extends
    AbstractEstPriceQueryStrategy {

  /**
   * 方法功能描述：根据查询结果集，生成Map结构的询价结果。
   * <p>
   * <b>参数说明</b>
   * 
   * @param rowset
   *          数据库查询结果集
   * @return Map<供应商+物料,价格>
   *         <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-7-20 上午10:23:10
   */
  private Map<String, UFDouble> createPriceQryMap(IRowSet rowset) {
    Map<String, UFDouble> map = new HashMap<String, UFDouble>();
    while (rowset.next()) {
      String pk_supplier = rowset.getString(0);
      String pk_srcmaterial = rowset.getString(1);
      String key = pk_supplier + pk_srcmaterial;
      UFDouble price = rowset.getUFDouble(2);
      if (!map.containsKey(key)) {
        map.put(key, price);
      }
    }
    return map;
  }

  /**
   * 方法功能描述：拼接查询语句。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_fiorg
   *          财务组织
   * @param suppliers
   *          供应商
   * @param mpks
   *          物料
   * @return 完整的sql语句
   *         <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-7-20 上午10:24:18
   */
  private String getSql(String pk_fiorg, String[] suppliers, String[] mpks) {
    String ttab = new TempTableDefine().get(suppliers, mpks);
    SqlBuilder sql = new SqlBuilder();
    sql.append("select b.pk_supplier,b.pk_srcmaterial,b.nprice from ");
    sql.append("po_settlebill a inner join po_settlebill_b b on ");
    sql.append("a.pk_settlebill=b.pk_settlebill ");
    sql.append(" inner join ");
    sql.append(ttab);// 临时表
    sql.append(" t on b.pk_supplier=t.id1 and b.pk_srcmaterial=t.id2 where ");
    sql.append("a.dr=0 and b.dr=0 and ");
    sql.append("a.pk_org=b.pk_org and ");
    sql.append("b.pk_org", pk_fiorg);
    sql.append(" and ");
    sql.append("a.pk_group=b.pk_group and ");
    sql.append("b.pk_group", BSContext.getInstance().getGroupID());
    sql.append(" and ");
    sql.append("b.nprice is not null ");
    sql.append(" order by a.dbilldate desc,b.ts desc,b.pk_settlebill_b desc");
    return sql.toString();
  }

  /**
   * 方法功能描述：得到询价要使用的供应商和物料信息。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pqinfo
   *          询价信息提供接口
   * @return List(0)供应商数组,List(1)物料OID数组
   *         <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-7-20 上午10:21:53
   */
  private List<String[]> getSupplierMaterial(IEstPriceQueryInfoProvide[] pqinfo) {
    List<String[]> result = new ArrayList<String[]>();
    List<String> suppliers = new ArrayList<String>();
    List<String> materials = new ArrayList<String>();
    for (IEstPriceQueryInfoProvide info : pqinfo) {
      String pk_supplier = info.getBillSupplier();
      String pk_srcmaterial = info.getPk_srcmaterial();
      if (StringUtil.isEmptyWithTrim(pk_supplier)
          || StringUtil.isEmptyWithTrim(pk_srcmaterial)) {
        continue;
      }
      suppliers.add(pk_supplier);
      materials.add(pk_srcmaterial);

    }
    result.add(suppliers.toArray(new String[suppliers.size()]));
    result.add(materials.toArray(new String[materials.size()]));
    return result;
  }

  @Override
  protected void procResltData(EstPriceQryResltData[] resltData,
      IEstPriceQueryInfoProvide[] pqinfo) {
    // 询价前已经根据结算财务组织分单，这里只针对一个财务组织，取第1条数据的财务组织
    String pk_fiorg = pqinfo[0].getPk_fiorg();
    List<String[]> ids = this.getSupplierMaterial(pqinfo);
    String[] suppliers = ids.get(0);
    String[] mpks = ids.get(1);
    if (ArrayUtils.isEmpty(suppliers) || ArrayUtils.isEmpty(mpks)
        || (suppliers.length != mpks.length)) {
      return;
    }
    String sql = this.getSql(pk_fiorg, suppliers, mpks);
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql);
    Map<String, UFDouble> priceQryMap = this.createPriceQryMap(rowset);
    for (int i = 0; i < pqinfo.length; i++) {
      IEstPriceQueryInfoProvide info = pqinfo[i];
      EstPriceQryResltData reslt = resltData[i];
      String pk_supplier = info.getBillSupplier();
      String pk_srcmaterial = info.getPk_srcmaterial();
      String key = pk_supplier + pk_srcmaterial;
      reslt.setPrice(priceQryMap.get(key));
    }
  }
}
