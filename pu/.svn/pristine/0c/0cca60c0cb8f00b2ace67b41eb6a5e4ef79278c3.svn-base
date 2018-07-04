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
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>设置表头默认值的工具类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-1-25 下午07:32:09
 */
public class HeadDefaultValue {
  private IContext ctx;

  private IKeyValue keyValue;

  public HeadDefaultValue(IKeyValue keyValue, IContext ctx) {
    this.keyValue = keyValue;
    this.ctx = ctx;
  }

  /**
   * 方法功能描述：设置表头默认值。
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author duy
   * @time 2010-1-27 上午10:33:05
   */
  public void setDefaultValue() {
    // 单据日期，取业务日期（只有新增时才处理）
    if (null == this.keyValue.getHeadValue(OrderHeaderVO.PK_ORDER)) {
      this.keyValue.setHeadValue(OrderHeaderVO.DBILLDATE, AppContext
          .getInstance().getBusiDate());
    }
    // 集团
    if (this.keyValue.getHeadValue(OrderHeaderVO.PK_GROUP) == null) {
      this.keyValue
          .setHeadValue(OrderHeaderVO.PK_GROUP, this.ctx.getPk_group());
    }
    // 采购组织
    if (this.keyValue.getHeadValue(OrderHeaderVO.PK_ORG_V) == null) {
      String vid = null;
      if (!StringUtil.isEmptyWithTrim(this.ctx.getPk_org())) {
        vid = OrgUnitPubService.getOrgVid(this.ctx.getPk_org());
      }
      this.keyValue.setHeadValue(OrderHeaderVO.PK_ORG_V, vid);
    }
    // 采购组织版本信息
    if (this.keyValue.getHeadValue(OrderHeaderVO.PK_ORG) == null) {
      this.keyValue.setHeadValue(OrderHeaderVO.PK_ORG, this.ctx.getPk_org());
    }
    // 版本
    if (this.keyValue.getHeadValue(OrderHeaderVO.NVERSION) == null) {
      this.keyValue.setHeadValue(OrderHeaderVO.NVERSION, Integer.valueOf(1));
    }
    // 扣税类别
    if (this.keyValue.getHeadValue(OrderHeaderVO.FHTAXTYPEFLAG) == null) {
      this.keyValue.setHeadValue(OrderHeaderVO.FHTAXTYPEFLAG,
          EnumDiscounttaxtype.TAXOUT.value());
    }

    // 币种
    if (this.keyValue.getHeadValue(OrderHeaderVO.CORIGCURRENCYID) == null
        && this.ctx.getPk_org() != null) {
      String pk_currtype =
          OrgUnitPubService.queryOrgCurrByPk(this.ctx.getPk_org());
      this.keyValue.setHeadValue(OrderHeaderVO.CORIGCURRENCYID, pk_currtype);
    }
  }
}
