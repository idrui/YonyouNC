package nc.vo.pu.m422x.rule;

import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.pub.util.IKeyValue;

/**
 * 设置本币币种
 * 
 * @since 6.0
 * @version 2010-12-2 下午08:52:47
 * @author wuxla
 */

public class CurrencySetter {

  private IKeyValue keyValue;

  public CurrencySetter(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  public void setCurrency(int[] rows) {
    String pk_org =
        (String) this.keyValue.getHeadValue(StoreReqAppHeaderVO.PK_ORG);
    if (null == pk_org) {
      return;
    }

    String ccurrencyid = OrgUnitPubService.queryOrgCurrByPk(pk_org);
    for (int row : rows) {
      this.keyValue.setBodyValue(row, StoreReqAppItemVO.CCURRENCYID,
          ccurrencyid);
    }
  }
}
