/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-7 ����08:44:25
 */
package nc.vo.pu.m4t.rule;

import nc.itf.scmpub.reference.uap.bd.currency.CurrencyRate;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.uif2.LoginContext;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���ñ�ͷĬ��ֵ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-7 ����08:44:25
 */
public class HeadDefaultValue {
  private LoginContext ctx;

  private IKeyValue keyValue;

  public HeadDefaultValue(IKeyValue keyValue, LoginContext ctx) {
    this.keyValue = keyValue;
    this.ctx = ctx;
  }

  public void setDefaultValue() {
    // ����
    if (this.keyValue.getHeadValue(InitialEstHeaderVO.PK_GROUP) == null) {
      this.keyValue.setHeadValue(InitialEstHeaderVO.PK_GROUP,
          this.ctx.getPk_group());
    }
    // ������֯
    if (this.keyValue.getHeadValue(InitialEstHeaderVO.PK_ORG_V) == null) {
      String vid = null;
      if (!StringUtil.isEmptyWithTrim(this.ctx.getPk_org())) {
        vid = OrgUnitPubService.getOrgVid(this.ctx.getPk_org());
      }
      this.keyValue.setHeadValue(InitialEstHeaderVO.PK_ORG_V, vid);
    }
    // ������֯�汾��Ϣ
    if (this.keyValue.getHeadValue(InitialEstHeaderVO.PK_ORG) == null) {
      this.keyValue.setHeadValue(InitialEstHeaderVO.PK_ORG,
          this.ctx.getPk_org());
    }

    // �ɹ���֯
    this.keyValue.setHeadValue(InitialEstHeaderVO.PK_PURCHASEORG,
        this.keyValue.getHeadValue(InitialEstHeaderVO.PK_ORG));
    this.keyValue.setHeadValue(InitialEstHeaderVO.PK_PURCHASEORG_V,
        this.keyValue.getHeadValue(InitialEstHeaderVO.PK_ORG_V));

    String pk_currtype = null;
    this.keyValue.getHeadValue(InitialEstHeaderVO.PK_PURCHASEORG_V);
    if (this.ctx.getPk_org() != null) {
      pk_currtype = OrgUnitPubService.queryOrgCurrByPk(this.ctx.getPk_org());

    }
    // ��λ��
    if (this.keyValue.getHeadValue(InitialEstHeaderVO.CCURRENCYID) == null
        && pk_currtype != null) {
      this.keyValue.setHeadValue(InitialEstHeaderVO.CCURRENCYID, pk_currtype);
    }

    // ԭ��
    if (this.keyValue.getHeadValue(InitialEstHeaderVO.CORIGCURRENCYID) == null
        && pk_currtype != null) {
      this.keyValue.setHeadValue(InitialEstHeaderVO.CORIGCURRENCYID,
          pk_currtype);
    }

    UFDate startDate = null;
    // �ڳ�����ȡֵ����INI02
    if (!StringUtils.isEmpty(this.ctx.getPk_org())) {
      // ȡ�ڳ����ڵ�ǰһ��
      startDate = PUSysParamUtil.getINI02BeforeDate(this.ctx.getPk_org());
      if (startDate != null) {
        this.keyValue.setHeadValue(InitialEstHeaderVO.DBILLDATE, startDate);
      }

    }

    // ����
    if (this.keyValue.getHeadValue(InitialEstHeaderVO.NEXCHANGERATE) == null
        && this.ctx.getPk_org() != null) {
      String corigcurrencyid =
          (String) this.keyValue
              .getHeadValue(InitialEstHeaderVO.CORIGCURRENCYID);
      String ccurrencyid =
          (String) this.keyValue.getHeadValue(InitialEstHeaderVO.CCURRENCYID);
      UFDouble rate =
          CurrencyRate.getCurrencySellRateByOrg(this.ctx.getPk_org(),
              corigcurrencyid, ccurrencyid, startDate);
      this.keyValue.setHeadValue(InitialEstHeaderVO.NEXCHANGERATE, rate);
    }
  }
}
