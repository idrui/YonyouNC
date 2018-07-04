package nc.vo.pu.m21.rule;

import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.pub.context.IContext;
import nc.vo.pu.pub.enumeration.EnumDiscounttaxtype;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pubapp.AppContext;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���ñ�ͷĬ��ֵ�Ĺ�����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-1-25 ����07:32:09
 */
public class HeadDefaultValue {
  private IContext ctx;

  private IKeyValue keyValue;

  public HeadDefaultValue(IKeyValue keyValue, IContext ctx) {
    this.keyValue = keyValue;
    this.ctx = ctx;
  }

  /**
   * �����������������ñ�ͷĬ��ֵ��
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @since 6.0
   * @author duy
   * @time 2010-1-27 ����10:33:05
   */
  public void setDefaultValue() {
    // �������ڣ�ȡҵ�����ڣ�ֻ������ʱ�Ŵ���
    if (null == this.keyValue.getHeadValue(OrderHeaderVO.PK_ORDER)) {
      this.keyValue.setHeadValue(OrderHeaderVO.DBILLDATE, AppContext
          .getInstance().getBusiDate());
    }
    // ����
    if (this.keyValue.getHeadValue(OrderHeaderVO.PK_GROUP) == null) {
      this.keyValue
          .setHeadValue(OrderHeaderVO.PK_GROUP, this.ctx.getPk_group());
    }
    // �ɹ���֯
    if (this.keyValue.getHeadValue(OrderHeaderVO.PK_ORG_V) == null) {
      String vid = null;
      if (!StringUtil.isEmptyWithTrim(this.ctx.getPk_org())) {
        vid = OrgUnitPubService.getOrgVid(this.ctx.getPk_org());
      }
      this.keyValue.setHeadValue(OrderHeaderVO.PK_ORG_V, vid);
    }
    // �ɹ���֯�汾��Ϣ
    if (this.keyValue.getHeadValue(OrderHeaderVO.PK_ORG) == null) {
      this.keyValue.setHeadValue(OrderHeaderVO.PK_ORG, this.ctx.getPk_org());
    }
    // �汾
    if (this.keyValue.getHeadValue(OrderHeaderVO.NVERSION) == null) {
      this.keyValue.setHeadValue(OrderHeaderVO.NVERSION, Integer.valueOf(1));
    }
    // ��˰���
    if (this.keyValue.getHeadValue(OrderHeaderVO.FHTAXTYPEFLAG) == null) {
      this.keyValue.setHeadValue(OrderHeaderVO.FHTAXTYPEFLAG,
          EnumDiscounttaxtype.TAXOUT.value());
    }

    // ����
    if (this.keyValue.getHeadValue(OrderHeaderVO.CORIGCURRENCYID) == null
        && this.ctx.getPk_org() != null) {
      String pk_currtype =
          OrgUnitPubService.queryOrgCurrByPk(this.ctx.getPk_org());
      this.keyValue.setHeadValue(OrderHeaderVO.CORIGCURRENCYID, pk_currtype);
    }
  }
}
