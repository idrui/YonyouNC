package nc.bs.pu.m27.match;

import nc.bs.framework.common.NCLocator;
import nc.bs.pu.m27.match.rule.WithoutInvcMatchStockProcRule;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.pubitf.pu.m25.pu.settle.IVirtualInvoiceMaintain;
import nc.vo.ic.m45.entity.PurchaseInVO;
import nc.vo.ic.m47.entity.SubcontInVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>无发票结算
 * <li>支持单价和结算金额为零的结算，如有暂估应付，只冲暂估，不传应付
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-8-7 下午02:12:29
 */
public class WithoutInvoiceMatch {
  public SettleBillVO[] matchProcess(StockSettleVO[] voaStock) {
    WithoutInvcMatchStockProcRule stockRule =
        new WithoutInvcMatchStockProcRule(voaStock);

    PurchaseInVO[] purVos = stockRule.getPurchaseInVOs();
    SubcontInVO[] subconVos = stockRule.getSubcontInVOs();
    InitialEstVO[] initVos = stockRule.getInitialEstVOs();

    // 生成虚拟发票，虚拟发票来源于采购入库单，将会走入库单到发票的自动结算过程
    // 从而实现了无发票结算的过程
    IVirtualInvoiceMaintain invoice =
        NCLocator.getInstance().lookup(IVirtualInvoiceMaintain.class);
    try {
      InvoiceVO[] invoices = null;
      // 由采购入库单生成虚拟发票
      if (!ArrayUtils.isEmpty(purVos)) {
        InvoiceVO[] purInvoices = invoice.genByPurchsIn(purVos);
        invoices = (InvoiceVO[]) ArrayUtils.addAll(invoices, purInvoices);
      }
      // 由委托加工入库单生成虚拟发票
      if (!ArrayUtils.isEmpty(subconVos)) {
        InvoiceVO[] subcontInvoices = invoice.genBySubcontIn(subconVos);
        invoices = (InvoiceVO[]) ArrayUtils.addAll(invoices, subcontInvoices);
      }
      // 由期初暂估单生成虚拟发票
      if (!ArrayUtils.isEmpty(initVos)) {
        InvoiceVO[] initInvoices = invoice.genByInitEst(initVos);
        invoices = (InvoiceVO[]) ArrayUtils.addAll(invoices, initInvoices);
      }
      // 根据虚拟发票VO查询结算单VO
      return this.querySettleBillVO(invoices);
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return new SettleBillVO[0];
  }

  // private Map<String, Set<String>> getPurchaseInIds(StockSettleVO[] voaStock)
  // {
  // Map<String, Set<String>> map = new HashMap<String, Set<String>>();
  // for (StockSettleVO vo : voaStock) {
  // String cpurchaseid = vo.getPk_stockps();
  // Set<String> itemIds = map.get(cpurchaseid);
  // if (itemIds == null) {
  // itemIds = new LinkedHashSet<String>();
  // map.put(cpurchaseid, itemIds);
  // }
  //
  // itemIds.add(vo.getPk_stockps_b());
  // }
  // return map;
  // }

  // private PurchaseInVO[] queryPurchaseInVos(StockSettleVO[] voaStock) {
  // Map<String, Set<String>> map = this.getPurchaseInIds(voaStock);
  // // 查询入库单
  // PurchaseInVO[] vos = M45PUServices.queryPurchaseIns(map);
  // // 把可结算数量放置到入库单上
  // Map<String, StockSettleVO> stockMap = new HashMap<String, StockSettleVO>();
  // for (StockSettleVO vo : voaStock) {
  // stockMap.put(vo.getPk_stockps_b(), vo);
  // }
  //
  // for (PurchaseInVO vo : vos) {
  // PurchaseInBodyVO[] items = vo.getBodys();
  // for (PurchaseInBodyVO item : items) {
  // StockSettleVO settle = stockMap.get(item.getCgeneralbid());
  // if (settle != null) {
  // if (settle.getNavgsettleprice() != null) {
  // item.setNnetprice(settle.getNavgsettleprice());
  // this.relationCalculate(item, PurchaseInBodyVO.NNETPRICE);
  // }
  // item.setNnum(settle.getNcurrentsettlenum());
  // this.relationCalculate(item, ICPubMetaNameConst.NNUM);
  // }
  // }
  // }
  // return vos;
  // }

  protected SettleBillVO[] querySettleBillVO(InvoiceVO[] invoices) {
    String err =
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0",
            "04004060-0069")/* @res "虚拟发票生成失败！" */;
    if (invoices == null || invoices.length == 0) {
      ExceptionUtils.wrappBusinessException(err);
      return null;
    }

    String[] invids = AggVOUtil.getPrimaryKeys(invoices);
    IDExQueryBuilder ids =
        new IDExQueryBuilder(PUTempTable.tmp_po_27_03.name());
    SqlBuilder builder = new SqlBuilder();
    builder
        .append("select distinct pk_settlebill from po_settlebill_b where dr = 0 ");
    builder.append("and pk_org", invoices[0].getParentVO().getPk_org());
    builder.append("and " + ids.buildSQL("pk_invoice", invids));
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rs = utils.query(builder.toString());

    String keys[] = rs.toOneDimensionStringArray();
    if (keys == null || keys.length == 0) {
      return null;
    }

    BillQuery<SettleBillVO> query =
        new BillQuery<SettleBillVO>(SettleBillVO.class);
    return query.query(keys);
  }

  // private void relationCalculate(PurchaseInBodyVO itemVO, String itemKey) {
  // IRelationForItems item = new RelationItemForCal();
  // ScaleUtils scale = new ScaleUtils(BSContext.getInstance().getGroupID());
  // // 创建数据集实例 初始化数据关系计算用的数据集
  // IDataSetForCal data = new VODataSetForCal(itemVO, item);
  // Calculator tool = new Calculator(data, scale);
  // // 创建参数实例，在计算的时候用来获得参数条件：是否含税优先等
  // Condition cond = new Condition();// 创建参数实例
  // // 设置是否进行本币换算
  // cond.setIsCalLocalCurr(true);
  // // 设置调单价方式调折扣
  // cond.setIsChgPriceOrDiscount(true);
  // // 设置是否固定单位换算率
  // cond.setIsFixNchangerate(true);
  // // 是否固定报价单位换算率
  // cond.setIsFixNqtunitrate(true);
  // // 设置含税优先还是无税优先
  // cond.setIsTaxOrNet(false);
  // // 两个参数 cond 为计算时的参数条件
  // tool.calculate(cond, itemKey);
  // }
}
