/**
 * $�ļ�˵��$
 * 
 * @author lixyp
 * @version 6.1
 * @see
 * @since 6.1
 * @time 2011-12-27 ����04:27:45
 */
package nc.vo.pu.m20.rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;
import nc.vo.pu.m20.entity.PraybillVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              �빺�����ñ��ұ���
 * @scene
 *        �ƻ�������ʽ�����빺��������������ʽ�����빺��
 * @param ��
 * @since 6.3
 * @version 2014-10-21 ����2:05:12
 * @author yanxm5
 */
public class SetOrgCurrRule implements IRule<PraybillVO> {

  /**
   * ���෽����д
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(PraybillVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    this.setOrgCurr(vos);
  }

  private Map<String, String> getFinanceMap(PraybillVO[] vos) {
    Set<String> orgset = new HashSet<String>();
    for (PraybillVO vo : vos) {
      String pk_org = vo.getHVO().getPk_org();
      if (pk_org != null) {
        orgset.add(pk_org);
      }
    }
    if (orgset.size() == 0) {
      return null;
    }
    String[] orgs = orgset.toArray(new String[orgset.size()]);
    return StockOrgPubService.queryFinanceOrgIDByStockOrgID(orgs);
  }

  private void setOrgCurr(PraybillVO[] vos) {
    Map<String, String> finance = this.getFinanceMap(vos);
    if (finance == null || finance.size() == 0) {
      return;
    }

    Map<String, String> currencyMap = new HashMap<String, String>();
    for (PraybillVO vo : vos) {
      String pk_org = vo.getHVO().getPk_org();
      if (null == pk_org) {
        continue;
      }
      String pk_financeorg = finance.get(pk_org);
      if (pk_financeorg == null) {
        continue;
      }
      String ccurrency = null;
      if (currencyMap.containsKey(pk_financeorg)) {
        ccurrency = currencyMap.get(pk_financeorg);
      }
      else {
        ccurrency = OrgUnitPubService.queryOrgCurrByPk(pk_financeorg);
        currencyMap.put(pk_financeorg, ccurrency);
      }

      vo.getHVO().setCcurrencyid(ccurrency);
    }
  }
}
