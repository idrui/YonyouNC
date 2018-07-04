/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-21 ����09:16:31
 */
package nc.vo.pu.m422x.rule;

import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.pub.context.IContext;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-21 ����09:16:31
 */
public class HeadDefaultValue {
  private IContext ctx;

  private IKeyValue keyValue;

  public HeadDefaultValue(IKeyValue keyValue, IContext ctx) {
    this.keyValue = keyValue;
    this.ctx = ctx;
  }

  public void setDefaultValue() {
    // ����
    if (this.keyValue.getHeadValue(StoreReqAppHeaderVO.PK_GROUP) == null) {
      this.keyValue.setHeadValue(StoreReqAppHeaderVO.PK_GROUP,
          this.ctx.getPk_group());
    }
    // �ɹ���֯
    if (this.keyValue.getHeadValue(StoreReqAppHeaderVO.PK_ORG_V) == null) {
      String vid = null;
      if (!StringUtil.isEmptyWithTrim(this.ctx.getPk_org())) {
        vid = OrgUnitPubService.getOrgVid(this.ctx.getPk_org());
      }
      this.keyValue.setHeadValue(StoreReqAppHeaderVO.PK_ORG_V, vid);
    }
    // �ɹ���֯�汾��Ϣ
    if (this.keyValue.getHeadValue(StoreReqAppHeaderVO.PK_ORG) == null) {
      this.keyValue.setHeadValue(StoreReqAppHeaderVO.PK_ORG,
          this.ctx.getPk_org());
    }

    // ��������
    if (this.keyValue.getHeadValue(StoreReqAppHeaderVO.VTRANTYPECODE) == null) {
      this.keyValue.setHeadValue(StoreReqAppHeaderVO.VTRANTYPECODE,
          POBillType.MRBill.getCode());
    }
    // ����
    // UFDate busidate = ClientContext.getInstance().getBusiDate();
    // this.keyValue.setHeadValue(StoreReqAppHeaderVO.DBILLDATE, busidate);
    // ������,���벿��,������֯�༭���¼�StockOrganization����

  }

}
