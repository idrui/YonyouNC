/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-10 下午02:07:44
 */
package nc.vo.pu.m4t.rule;

import java.util.Map;

import nc.itf.pu.reference.to.SettleRuleServices;
import nc.itf.scmpub.reference.uap.org.CostRegionPubService;
import nc.itf.scmpub.reference.uap.org.FinanceOrgPubService;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pu.pub.util.ObjectUtil;

import org.apache.commons.lang.ObjectUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>设置成本域
 * <li>如果表头结算财务组织与表头库存组织所属财务组织相同，则根据库存组织+仓库对应的成本域作为默认值；
 * <li>如果表头库存组织所属财务组织不等于表体结算财务组织，则结算财务组织作为调出财务组织、
 * 库存组织作为调入库存组织匹配内部结算规则中对应的调出成本域作为存货核算单据的成本域。 如果未设置调出成本域，则取结算财务组织的主成本域。
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-10 下午02:07:44
 */
public class CostregionSetter {
  private IKeyValue keyValue;

  public CostregionSetter(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * 方法功能描述：设置成本域
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-9-10 下午02:09:21
   */
  public void setCostregion() {
    String pk_org =
        (String) this.keyValue.getHeadValue(InitialEstHeaderVO.PK_ORG);
    String pk_stockorg =
        (String) this.keyValue.getHeadValue(InitialEstHeaderVO.PK_STOCKORG);

    if (StringUtil.isEmptyWithTrim(pk_org)
        || StringUtil.isEmptyWithTrim(pk_stockorg)) {
      return;
    }

    String pk_financeOrg = this.getFinanceOrgID(pk_stockorg);
    if (ObjectUtils.equals(pk_org, pk_financeOrg)) {
      this.setCostRegionWhenSame();
    }
    else {
      this.setCostRegionWhenNotSame();
    }
  }

  /**
   * 方法功能描述：获取表头库存组织所属财务组织
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_stockorg
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-10 下午02:17:29
   */
  private String getFinanceOrgID(String pk_stockorg) {
    Map<String, String> map =
        StockOrgPubService.queryFinanceOrgIDByStockOrgID(new String[] {
          pk_stockorg
        });
    if (map != null) {
      return map.get(pk_stockorg);
    }

    return null;
  }

  private String getVordertrantype() {
    int rowcount = this.keyValue.getItemCount();
    if (0 == rowcount) {
      return null;
    }
    for (int i = 0; i < rowcount; ++i) {
      Object vordertrantype =
          this.keyValue.getBodyValue(i, InitialEstItemVO.VORDERTRANTYPE);
      if (!ObjectUtil.isEmptyWithTrim(vordertrantype)) {
        return (String) vordertrantype;
      }
    }

    return null;
  }

  /**
   * 方法功能描述：如果表头库存组织所属财务组织不等于表体结算财务组织，则结算财务组织作为调出财务组织、
   * 库存组织作为调入库存组织匹配内部结算规则中对应的调出成本域作为存货核算单据的成本域。
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-9-10 下午04:51:48
   */
  private void setCostRegionWhenNotSame() {
    String pk_org =
        (String) this.keyValue.getHeadValue(InitialEstHeaderVO.PK_ORG);
    String pk_stockorg =
        (String) this.keyValue.getHeadValue(InitialEstHeaderVO.PK_STOCKORG);
    String pk_group =
        (String) this.keyValue.getHeadValue(InitialEstHeaderVO.PK_GROUP);
    String vordertrantype = this.getVordertrantype();
    String costRegion =
        SettleRuleServices.getCostRegion(pk_org, pk_stockorg, pk_group,
            vordertrantype);
    if (costRegion == null) {
      costRegion = FinanceOrgPubService.queryMainCostRegion(pk_org);
    }
    this.keyValue.setHeadValue(InitialEstHeaderVO.PK_COSTREGION, costRegion);
  }

  /**
   * 方法功能描述：如果表头结算财务组织与表头库存组织所属财务组织相同，则根据库存组织+仓库对应的成本域作为默认值
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_stockorg
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-10 下午02:44:04
   */
  private void setCostRegionWhenSame() {
    String pk_stordoc =
        (String) this.keyValue.getHeadValue(InitialEstHeaderVO.PK_STORDOC);
    // if (StringUtil.isEmptyWithTrim(pk_stordoc)) {
    // this.keyValue.setHeadValue(InitialEstHeaderVO.PK_COSTREGION, null);
    // return;
    // }

    String pk_stockorg =
        (String) this.keyValue.getHeadValue(InitialEstHeaderVO.PK_STOCKORG);

    Map<String, String> costMap =
        CostRegionPubService.queryCostRegionIDByStockOrgsAndStordocs(
            new String[] {
              pk_stockorg
            }, new String[] {
              pk_stordoc
            });
    String pk_costregion = costMap.get(pk_stockorg + pk_stordoc);
    if (StringUtil.isEmptyWithTrim(pk_costregion)) {
      return;
    }
    this.keyValue.setHeadValue(InitialEstHeaderVO.PK_COSTREGION, pk_costregion);
  }
}
