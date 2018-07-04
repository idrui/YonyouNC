package nc.bs.pu.m4203.rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;
import nc.vo.pu.m4203.entity.SubcontinFIItemVO;
import nc.vo.pu.m4203.entity.SubcontinFIVO;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.ArrayUtils;

/**
 * 委外加工入库审批生成副本时关键数据补充
 * 1)委托加工入财务明细.结算财务组织（pk_stockps_b.pk_financeorg）->委托加工入库表体ID.库存组织.所属财务组织（
 * cgeneralbid.pk_org.pk_financeorg）
 * 2)委托加工入财务明细.结算财务组织(pk_stockps_b.pk_financeorg_v)->委托加工入库表体ID.库存组织.所属财务组织.版本主键
 * (
 * cgeneralbid.pk_org.pk_financeorg.pk_vid)
 * 3)委托加工入财务明细.应付财务组织(pk_stockps_b.pk_apfinanceorg)->委托加工入库表体ID.库存组织.所属财务组织(
 * cgeneralbid.pk_org.pk_financeorg)
 * 4)委托加工入财务明细.应付财务组织(pk_stockps_b.pk_apfinanceorg_v)->委托加工入库表体ID.库存组织.所属财务组织.
 * 版本主键(cgeneralbid.pk_org.pk_financeorg.pk_vid)
 * 5)委托加工入财务明细.订单交易类型(pk_stockps_b.vordertrantypecode)->getColValue(bd_billtype,
 * pk_billtypecode,pk_billtypeid,cgeneralbid.cfirsttranstype)
 * 6)委托加工入财务明细.本位币(pk_stockps_b.ccurrencyid)->getcolvalue( org_orgs,pk_currtype
 * , pk_org,cgeneralbid.pk_org.pk_financeorg )
 * 
 * @since 6.0
 * @version 2012-8-8 上午09:30:52
 * @author wuxla
 */
public class SubcontFillInfoRule implements IRule<SubcontinFIVO> {

  @Override
  public void process(SubcontinFIVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    this.setFinanceOrgAndCurrency(vos);
    // VO对照对照的是主键，代码中查询code
    this.setVordertrantypecode(vos);
  }

  private void setFinanceOrgAndCurrency(SubcontinFIVO[] vos) {
    Set<String> orgSet = new HashSet<String>();
    for (SubcontinFIVO vo : vos) {
      orgSet.add(vo.getParentVO().getPk_org());
    }
    String[] stockorgIDs = orgSet.toArray(new String[orgSet.size()]);
    Map<String, String> stockFinanceMap =
        StockOrgPubService.queryFinanceOrgIDByStockOrgID(stockorgIDs);
    String[] financeorgs =
        stockFinanceMap.values().toArray(
            new String[stockFinanceMap.values().size()]);
    Map<String, String> vidMap =
        OrgUnitPubService.getNewVIDSByOrgIDS(financeorgs);
    Map<String, String> currencyMap =
        OrgUnitPubService.queryOrgCurrByPk(financeorgs);
    for (SubcontinFIVO vo : vos) {
      String pk_org = vo.getParentVO().getPk_org();
      String pk_financeorg = stockFinanceMap.get(pk_org);
      String pk_financeorg_v = vidMap.get(pk_financeorg);
      String ccurrencyid = currencyMap.get(pk_financeorg);
      SubcontinFIItemVO[] itemVOs = vo.getChildrenVO();
      for (SubcontinFIItemVO itemVO : itemVOs) {
        itemVO.setPk_financeorg(pk_financeorg);
        itemVO.setPk_financeorg_v(pk_financeorg_v);
        itemVO.setPk_apfinanceorg(pk_financeorg);
        itemVO.setPk_apfinanceorg_v(pk_financeorg_v);
        itemVO.setCcurrencyid(ccurrencyid);
      }
    }
  }

  private void setVordertrantypecode(SubcontinFIVO[] vos) {
    Set<String> set = new HashSet<String>();
    for (SubcontinFIVO vo : vos) {
      SubcontinFIItemVO[] itemVOs = vo.getChildrenVO();
      for (SubcontinFIItemVO itemVO : itemVOs) {
        String vordertrantypecode = itemVO.getVordertrantypecode();
        if (vordertrantypecode != null) {
          set.add(vordertrantypecode);
        }
      }
    }
    if (set.size() == 0) {
      return;
    }
    String[] pks = set.toArray(new String[set.size()]);
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_4203_6.name());
    String cond = builder.buildSQL(" pk_billtypeid", pks);
    SqlBuilder sql = new SqlBuilder();
    sql.append("select pk_billtypeid,pk_billtypecode from bd_billtype ");
    sql.append("where ");
    sql.append(cond);
    DataAccessUtils util = new DataAccessUtils();
    String[][] values = util.query(sql.toString()).toTwoDimensionStringArray();
    Map<String, String> trantypecodemap = new HashMap<String, String>();
    for (String[] value : values) {
      trantypecodemap.put(value[0], value[1]);
    }

    for (SubcontinFIVO vo : vos) {
      SubcontinFIItemVO[] itemVOs = vo.getChildrenVO();
      for (SubcontinFIItemVO itemVO : itemVOs) {
        String cordertrantypeid = itemVO.getVordertrantypecode();
        if (cordertrantypeid != null) {
          String vordertrantypecode = trantypecodemap.get(cordertrantypeid);
          itemVO.setVordertrantypecode(vordertrantypecode);
        }
      }
    }
  }
}
