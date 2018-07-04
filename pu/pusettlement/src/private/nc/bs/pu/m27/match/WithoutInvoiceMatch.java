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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�޷�Ʊ����
 * <li>֧�ֵ��ۺͽ�����Ϊ��Ľ��㣬�����ݹ�Ӧ����ֻ���ݹ�������Ӧ��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-8-7 ����02:12:29
 */
public class WithoutInvoiceMatch {
  public SettleBillVO[] matchProcess(StockSettleVO[] voaStock) {
    WithoutInvcMatchStockProcRule stockRule =
        new WithoutInvcMatchStockProcRule(voaStock);

    PurchaseInVO[] purVos = stockRule.getPurchaseInVOs();
    SubcontInVO[] subconVos = stockRule.getSubcontInVOs();
    InitialEstVO[] initVos = stockRule.getInitialEstVOs();

    // �������ⷢƱ�����ⷢƱ��Դ�ڲɹ���ⵥ����������ⵥ����Ʊ���Զ��������
    // �Ӷ�ʵ�����޷�Ʊ����Ĺ���
    IVirtualInvoiceMaintain invoice =
        NCLocator.getInstance().lookup(IVirtualInvoiceMaintain.class);
    try {
      InvoiceVO[] invoices = null;
      // �ɲɹ���ⵥ�������ⷢƱ
      if (!ArrayUtils.isEmpty(purVos)) {
        InvoiceVO[] purInvoices = invoice.genByPurchsIn(purVos);
        invoices = (InvoiceVO[]) ArrayUtils.addAll(invoices, purInvoices);
      }
      // ��ί�мӹ���ⵥ�������ⷢƱ
      if (!ArrayUtils.isEmpty(subconVos)) {
        InvoiceVO[] subcontInvoices = invoice.genBySubcontIn(subconVos);
        invoices = (InvoiceVO[]) ArrayUtils.addAll(invoices, subcontInvoices);
      }
      // ���ڳ��ݹ����������ⷢƱ
      if (!ArrayUtils.isEmpty(initVos)) {
        InvoiceVO[] initInvoices = invoice.genByInitEst(initVos);
        invoices = (InvoiceVO[]) ArrayUtils.addAll(invoices, initInvoices);
      }
      // �������ⷢƱVO��ѯ���㵥VO
      return this.querySettleBillVO(invoices);
    }
    catch (BusinessException e) {
      // ��־�쳣
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
  // // ��ѯ��ⵥ
  // PurchaseInVO[] vos = M45PUServices.queryPurchaseIns(map);
  // // �ѿɽ����������õ���ⵥ��
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
            "04004060-0069")/* @res "���ⷢƱ����ʧ�ܣ�" */;
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
  // // �������ݼ�ʵ�� ��ʼ�����ݹ�ϵ�����õ����ݼ�
  // IDataSetForCal data = new VODataSetForCal(itemVO, item);
  // Calculator tool = new Calculator(data, scale);
  // // ��������ʵ�����ڼ����ʱ��������ò����������Ƿ�˰���ȵ�
  // Condition cond = new Condition();// ��������ʵ��
  // // �����Ƿ���б��һ���
  // cond.setIsCalLocalCurr(true);
  // // ���õ����۷�ʽ���ۿ�
  // cond.setIsChgPriceOrDiscount(true);
  // // �����Ƿ�̶���λ������
  // cond.setIsFixNchangerate(true);
  // // �Ƿ�̶����۵�λ������
  // cond.setIsFixNqtunitrate(true);
  // // ���ú�˰���Ȼ�����˰����
  // cond.setIsTaxOrNet(false);
  // // �������� cond Ϊ����ʱ�Ĳ�������
  // tool.calculate(cond, itemKey);
  // }
}
