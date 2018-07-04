package nc.vo.pu.m21.rule;

import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.context.IContext;
import nc.vo.pu.pub.enumeration.EnumDiscounttaxtype;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.scale.ScaleUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：设置新增或者插入的表体行的默认值</b>
 * <ul>
 * <li>corder_bid，清空
 * <li>iisreplenish，非补货
 * <li>iisactive，激活，非冻结
 * <li>breceiveplan，不走到货计划
 * <li>coperator，设置当前操作员
 * <li>idiscounttaxtype，默认为应税外加
 * <li>ndiscountrate，默认为100
 * <li>vfree，清空
 * <li>vfree1-10，清空
 * <li>vproducenum，清空
 * <li>csourcetypecode，默认取插入前当前行的该值
 * <li>vsourcecode，默认取插入前当前行的该值
 * <li>csourceid，默认取插入前当前行的该值
 * <li>csourcebid，默认取插入前当前行的该值
 * <li>vsourcerowno，默认取插入前当前行的该值
 * <li>vsrctrantypecode，默认取插入前当前行的该值
 * <li>cfirsttypecode，默认取插入前当前行的该值
 * <li>vfirstcode，默认取插入前当前行的该值
 * <li>cfirstid，默认取插入前当前行的该值
 * <li>cfirstbid，默认取插入前当前行的该值
 * <li>vfirstrowno，默认取插入前当前行的该值
 * <li>vfirsttrantype，默认取插入前当前行的该值
 * <li>ccontractrowid，默认取插入前当前行的该值
 * <li>ccontractid，默认取插入前当前行的该值
 * <li>ccontractcode，默认取插入前当前行的该值
 * <li>noriginalcurprice，默认取插入前当前行的该值
 * <li>norgtaxprice，默认取插入前当前行的该值
 * <li>cprojectid，默认取插入前当前行的该值，如果为空，默认从表头携带
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-1-15 下午12:24:14
 */
public class LineDefaultValue {
  private IContext ctx;

  private IKeyValue keyValue;

  public LineDefaultValue(IKeyValue keyValue, IContext ctx) {
    this.keyValue = keyValue;
    this.ctx = ctx;
  }

  /**
   * 方法功能描述：设置表体默认值。
   * <p>
   * <b>参数说明</b>
   * 
   * @param row 要设置默认值的行
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-1-27 上午10:33:05
   */
  public void setDefaultValue(int row) {
    if (this.keyValue.getItemCount() == 0) {
      return;
    }
    // 集团
    if (this.keyValue.getBodyValue(row, OrderItemVO.PK_GROUP) == null) {
      this.keyValue.setBodyValue(row, OrderItemVO.PK_GROUP,
          this.ctx.getPk_group());
    }
    
    // 采购组织
    if (this.keyValue.getBodyValue(row, OrderItemVO.PK_ORG) == null) {
      this.keyValue.setBodyValue(row, OrderItemVO.PK_ORG, this.ctx.getPk_org());  
    }
    // 根据最新设计，去掉行状态
    // 是否存在到货计划
    if (this.keyValue.getBodyValue(row, OrderItemVO.BRECEIVEPLAN) == null) {
      this.keyValue
          .setBodyValue(row, OrderItemVO.BRECEIVEPLAN, UFBoolean.FALSE);
    }
    // 是否赠品
    if (this.keyValue.getBodyValue(row, OrderItemVO.BLARGESS) == null) {
      this.keyValue.setBodyValue(row, OrderItemVO.BLARGESS, UFBoolean.FALSE);
    }
    // 操作员
    if (this.keyValue.getBodyValue(row, OrderItemVO.CHANDLER) == null) {
      this.keyValue.setBodyValue(row, OrderItemVO.CHANDLER,
          this.keyValue.getHeadValue(OrderHeaderVO.BILLMAKER));
    }

    // 换算率
    ScaleUtils scale = new ScaleUtils(AppContext.getInstance().getPkGroup());
    if (this.keyValue.getBodyValue(row, OrderItemVO.VCHANGERATE) == null) {
      this.keyValue.setBodyValue(row, OrderItemVO.VCHANGERATE,
          scale.adjustHslScale("1/1"));
    }
    // 报价单位换算率
    if (this.keyValue.getBodyValue(row, OrderItemVO.VQTUNITRATE) == null) {
      this.keyValue.setBodyValue(row, OrderItemVO.VQTUNITRATE,
          scale.adjustHslScale("1/1"));
    }

    // 折扣
    if (this.keyValue.getBodyValue(row, OrderItemVO.NITEMDISCOUNTRATE) == null) {
      this.keyValue.setBodyValue(row, OrderItemVO.NITEMDISCOUNTRATE,
          new UFDouble(100));
    }
    // 扣税类别
    if (this.keyValue.getBodyValue(row, OrderItemVO.FTAXTYPEFLAG) == null) {
      Object taxTypeFlag =
          this.keyValue.getHeadValue(OrderHeaderVO.FHTAXTYPEFLAG);
      if (taxTypeFlag == null) {
        taxTypeFlag = EnumDiscounttaxtype.TAXOUT;
      }
      this.keyValue.setBodyValue(row, OrderItemVO.FTAXTYPEFLAG, taxTypeFlag);
    }
    // 项目
    if (this.keyValue.getBodyValue(row, OrderItemVO.CPROJECTID) == null) {
      Object project = this.keyValue.getHeadValue(OrderHeaderVO.PK_PROJECT);
      if (project != null) {
        this.keyValue.setBodyValue(row, OrderItemVO.CPROJECTID, project);
      }
    }

    // 供应商发货地址
    if (this.keyValue.getBodyValue(row, OrderItemVO.VVENDDEVADDR) == null) {
      Object address = this.keyValue.getHeadValue(OrderHeaderVO.PK_DELIVERADD);
      if (address != null) {
        this.keyValue.setBodyValue(row, OrderItemVO.VVENDDEVADDR, address);
      }
    }
  }
}
