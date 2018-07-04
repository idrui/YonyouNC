package nc.vo.pu.upgrade.v61;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.pu.upgrade.IPUUpgradeToV61;
import nc.scmmm.upgrade.scmpub.AbstractUpgradeToV61;
import nc.scmmm.upgrade.scmpub.BillTempletUpgradeUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.Log;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * 采购管理升级处理
 * 从V60升级V61
 * 
 * @since 6.0
 * @version 2012-3-29 上午09:47:26
 * @author wuxla
 */
public class PUUpgradeToV61 extends AbstractUpgradeToV61 {
  private Map<String, String> billtypeUpgradeMap =
      new HashMap<String, String>();

  public PUUpgradeToV61() {
    super();
    this.initMap();
  }

  private void delVOSplititem() {
    SqlBuilder sql = new SqlBuilder();
    sql.append("delete from pub_vosplititem ");
    sql.append(" where ");
    sql.append("billtype", "20");
    sql.append(" and ");
    sql.append("item", "pk_praybill_b.pk_customer");
    new DataAccessUtils().update(sql.toString());
  }

  private void initMap() {
    // v61物资需求申请升级
    this.billtypeUpgradeMap.put(POBillType.MRBill.getCode(),
        "nc.itf.pu.m422x.upgrade.IM422xUpgradeToV61");
    // v61请购单升级
    this.billtypeUpgradeMap.put(POBillType.PrayBill.getCode(),
        "nc.itf.pu.m20.upgrade.IM20UpgradeToV61");
    // v61采购订单升级
    this.billtypeUpgradeMap.put(POBillType.Order.getCode(),
        "nc.itf.pu.m21.upgrade.IM21UpgradeToV61");
    // v61到货单升级
    this.billtypeUpgradeMap.put(POBillType.Arrive.getCode(),
        "nc.itf.pu.m23.upgrade.IM23UpgradeToV61");
    // v61采购发票升级
    this.billtypeUpgradeMap.put(POBillType.Invoice.getCode(),
        "nc.itf.pu.m25.upgrade.IM25UpgradeToV61");
    // v61期初暂估单升级、v61暂估升级、v61结算升级
    this.billtypeUpgradeMap.put(POBillType.SettleBill.getCode(),
        "nc.itf.pu.settle.upgrade.ISettleUpgradeToV61");
  }

  /**
   * 自定义单据模板调整V61删除的字段，否则会显示ERROR
   * 调整物料带税率
   * <p>
   * 使用场景：
   * <ul>
   * <li>V61升级，自定义模板，针对删除的字段
   * <li>调整物料带税率
   * </ul>
   */
  private void selfBilltempletUpgarde() {
    BillTempletUpgradeUtil util = new BillTempletUpgradeUtil();
    List<String> delItem = new ArrayList<String>();
    // 物资需求申请单
    // csourcetypecode2下游单据类型、csourceid2下游单据、csourcebid2下游单据明细、vsourcecode2下游单据单据号
    // vsourcetrantype2下游单据交易类型、vsourcerowno2下游单据行号、cprjcostclass项目成本分类
    delItem.add("pu.po_storereq_b.vsourcecode2");
    delItem.add("pu.po_storereq_b.csourcetypecode2");
    delItem.add("pu.po_storereq_b.vsourcerowno2");
    delItem.add("pu.po_storereq_b.vsourcetrantype2");
    delItem.add("pu.po_storereq_b.csourceid2");
    delItem.add("pu.po_storereq_b.csourcebid2");
    delItem.add("pu.po_storereq_b.cprjcostclass");
    // 请购单体：pk_customer销售客户（需要做升级处理，将客户字段设置为销售客户字段）、pk_reqstor_v需求仓库版本
    delItem.add("pu.po_praybill_b.pk_reqstor_v");
    delItem.add("pu.po_praybill_b.pk_customer");
    // 采购订单：nacccancelpaymny累计已核销本币付款金额、norigtax税额
    delItem.add("pu.po_order_b.nacccancelpaymny");
    delItem.add("pu.po_order_b.norigtax");
    // 采购发票：norigtax税额
    delItem.add("pu.po_invoice_b.norigtax");
    // 期初暂估单：naccwashapnum累计回冲暂估应付数量、norigtax税额
    delItem.add("pu.po_initialest_b.norigtax");
    delItem.add("pu.po_initialest_b.naccwashapnum");
    // 采购入财务体：naccestapwashnum暂估应付的累计冲减数量、nestotaxmny税额、norigtax入库单税额
    // 采购入费用暂估明细：nestotaxmny原币税额
    delItem.add("pu.po_purchaseinfi_b.naccestapwashnum");
    delItem.add("pu.po_purchaseinfi_b.nestotaxmny");
    delItem.add("pu.po_purchaseinfi_b.norigtax");
    delItem.add("pu.po_purchaseinfi_fee.nestotaxmny");
    // 消耗汇总财务表头(单表)VO：naccestapwashnum暂估应付的累计冲减数量、nestotaxmny税额
    // 消耗汇总财务费用暂估：nestotaxmny原币税额
    delItem.add("pu.po_vmifi.naccestapwashnum");
    delItem.add("pu.po_vmifi.nestotaxmny");
    delItem.add("pu.po_vmifi_fee.nestotaxmny");
    String[] metadatapropertys = delItem.toArray(new String[delItem.size()]);
    util.deleteItemByMetadataproperty(metadatapropertys);

    util.removeNtaxrateMetadatarelationOnMaterialField(NCModule.PO.getCode());
  }

  private void updateBilltypeFinderClz() {
    SqlBuilder sql = new SqlBuilder();
    sql.append("update bd_billtype set  ");
    sql.append("datafinderclz", "nc.vo.pu.linkquery.PUMultiSrcBillFinder");
    sql.append(" where ");
    sql.append("parentbilltype", new String[] {
      POBillType.PrayBill.getCode(), POBillType.Order.getCode()
    });
    sql.append(" and pk_group", "<>", "global00000000000000");
    new DataAccessUtils().update(sql.toString());
  }

  @Override
  protected void doAfterUpdateDataFrom60To61() throws BusinessException {
    // 自定义模板调整
    this.selfBilltempletUpgarde();

    // 分单规则调整，删除的规则业务组自己调整
    this.delVOSplititem();

    // 联查调整
    // 对于有修订的单据，如果不重写MultiSrcBillFinder，会将历史版本都联查出来
    // 重写MultiSrcBillFinder，需要注册到bd_billtype的datafinderclz
    this.updateBilltypeFinderClz();

    try {
      for (Entry<String, String> entry : this.billtypeUpgradeMap.entrySet()) {

        String key = entry.getKey();
        String message = "upgrade[" + key + "] start.";/* -=notranslate=- */
        Log.debug(message);

        // 升级
        String value = entry.getValue();
        IPUUpgradeToV61 service =
            (IPUUpgradeToV61) NCLocator.getInstance().lookup(value);
        service.doAfterUpdateDataFrom60To61();

        message = "upgrade[" + key + "] end.";/* -=notranslate=- */
        Log.debug(message);
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

}
