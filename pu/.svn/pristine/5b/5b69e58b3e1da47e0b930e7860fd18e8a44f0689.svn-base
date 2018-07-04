/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-21 上午09:16:31
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
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-21 上午09:16:31
 */
public class HeadDefaultValue {
  private IContext ctx;

  private IKeyValue keyValue;

  public HeadDefaultValue(IKeyValue keyValue, IContext ctx) {
    this.keyValue = keyValue;
    this.ctx = ctx;
  }

  public void setDefaultValue() {
    // 集团
    if (this.keyValue.getHeadValue(StoreReqAppHeaderVO.PK_GROUP) == null) {
      this.keyValue.setHeadValue(StoreReqAppHeaderVO.PK_GROUP,
          this.ctx.getPk_group());
    }
    // 采购组织
    if (this.keyValue.getHeadValue(StoreReqAppHeaderVO.PK_ORG_V) == null) {
      String vid = null;
      if (!StringUtil.isEmptyWithTrim(this.ctx.getPk_org())) {
        vid = OrgUnitPubService.getOrgVid(this.ctx.getPk_org());
      }
      this.keyValue.setHeadValue(StoreReqAppHeaderVO.PK_ORG_V, vid);
    }
    // 采购组织版本信息
    if (this.keyValue.getHeadValue(StoreReqAppHeaderVO.PK_ORG) == null) {
      this.keyValue.setHeadValue(StoreReqAppHeaderVO.PK_ORG,
          this.ctx.getPk_org());
    }

    // 单据类型
    if (this.keyValue.getHeadValue(StoreReqAppHeaderVO.VTRANTYPECODE) == null) {
      this.keyValue.setHeadValue(StoreReqAppHeaderVO.VTRANTYPECODE,
          POBillType.MRBill.getCode());
    }
    // 日期
    // UFDate busidate = ClientContext.getInstance().getBusiDate();
    // this.keyValue.setHeadValue(StoreReqAppHeaderVO.DBILLDATE, busidate);
    // 申请人,申请部门,放在组织编辑后事件StockOrganization中了

  }

}
