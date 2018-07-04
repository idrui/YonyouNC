/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-10 ����02:07:44
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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���óɱ���
 * <li>�����ͷ���������֯���ͷ�����֯����������֯��ͬ������ݿ����֯+�ֿ��Ӧ�ĳɱ�����ΪĬ��ֵ��
 * <li>�����ͷ�����֯����������֯�����ڱ�����������֯������������֯��Ϊ����������֯��
 * �����֯��Ϊ��������֯ƥ���ڲ���������ж�Ӧ�ĵ����ɱ�����Ϊ������㵥�ݵĳɱ��� ���δ���õ����ɱ�����ȡ���������֯�����ɱ���
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-10 ����02:07:44
 */
public class CostregionSetter {
  private IKeyValue keyValue;

  public CostregionSetter(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * �����������������óɱ���
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-9-10 ����02:09:21
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
   * ����������������ȡ��ͷ�����֯����������֯
   * <p>
   * <b>����˵��</b>
   * 
   * @param pk_stockorg
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-10 ����02:17:29
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
   * �������������������ͷ�����֯����������֯�����ڱ�����������֯������������֯��Ϊ����������֯��
   * �����֯��Ϊ��������֯ƥ���ڲ���������ж�Ӧ�ĵ����ɱ�����Ϊ������㵥�ݵĳɱ���
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-9-10 ����04:51:48
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
   * �������������������ͷ���������֯���ͷ�����֯����������֯��ͬ������ݿ����֯+�ֿ��Ӧ�ĳɱ�����ΪĬ��ֵ
   * <p>
   * <b>����˵��</b>
   * 
   * @param pk_stockorg
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-10 ����02:44:04
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
