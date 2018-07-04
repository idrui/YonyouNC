package nc.bs.pu.m25.pf.function;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.itf.scmpub.reference.uap.org.FinanceOrgPubService;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;
import nc.vo.ia.pub.period.AccountPeriod;
import nc.vo.ia.pub.period.Calendar;
import nc.vo.ic.m45.entity.PurchaseInBodyVO;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.scmpub.res.billtype.ICBillType;

/**
 * 发票的会计期间检查： 发票的会计期间不能早于入库单的会计期间
 * <p>
 * <b>本类主要完成以下功能：</b> 如果发票的来源是采购入库单，则进行如下判断。
 * <ul>
 * <li>取发票主组织财务组织的主账簿的会计期间方案，以发票表头的发票日期匹配此会计期间方案得到发票所处的会计期间。</li>
 * <li>取到采购入库单的主组织库存组织所属财务组织，取此财务组织的主账簿的会计期间方案。以采购入库单表体的入库日期匹配此会计期间方案得到入库单的会计期间
 * ，对每个采购入库单行取一个会计期间。</li>
 * <li>循环比较发票会计期间和入库单的会计期间，如果发票的会计期间早于入库单的会计期间，抛错“发票的会计期间不能早于入库单的会计期间，入库单号XXX，
 * 行号YYY。”多个入库单行一起提示。</li>
 * </ul>
 * <p>
 * <p>
 * 
 * @since 6.0
 * @version 2012-8-23 下午01:31:55
 * @author liuyand
 */
public class InvoiceFiscalPeriodUtil {

  /**
   * 获取校验失败的入库单表体VO列表
   * 
   * @param headerVO 采购发票的表头VO
   * @param pkPurInvItemVOMap 采购发票的表体VOMap
   * @return 校验失败的入库单表体VO列表
   */
  public List<PurchaseInBodyVO> checkFiscalPeriod(InvoiceHeaderVO headerVO,
      Map<String, InvoiceItemVO> pkPurInvItemVOMap, boolean isInvGreater) {
    // 取采购入库单表体VO
    VOQuery<PurchaseInBodyVO> query =
        new VOQuery<PurchaseInBodyVO>(PurchaseInBodyVO.class);
    PurchaseInBodyVO[] purchaseInBodyVOs =
        query.query(pkPurInvItemVOMap.keySet().toArray(
            new String[pkPurInvItemVOMap.size()]));

    Map<String, PurchaseInBodyVO> purchaseInBodyPkVOs =
        this.getInvItemPkVos(purchaseInBodyVOs);

    Map<String, String> inBodyBidFinanceOrgMap =
        this.getInBodyBidFinanceOrgMap(purchaseInBodyVOs);

    // 合并需要的
    Set<String> financeOrgSet = new HashSet<String>();
    financeOrgSet.add(headerVO.getPk_org());
    financeOrgSet.addAll(inBodyBidFinanceOrgMap.values());

    Map<String, String> financeOrgMainBookMap =
        this.getFinanceOrgMainBookMap(financeOrgSet
            .toArray(new String[financeOrgSet.size()]));
    AccountPeriod invoicePeriod =
        this.getFirstPeriod(headerVO, financeOrgMainBookMap);
    Map<String, AccountPeriod> purchaseInBodyPeriods =
        this.getFirstPeriod(inBodyBidFinanceOrgMap, purchaseInBodyPkVOs,
            financeOrgMainBookMap);

    return this.checkFiscalPeriod(invoicePeriod, purchaseInBodyPeriods,
        purchaseInBodyPkVOs, isInvGreater);
  }

  /**
   * 获取所有来源单据类型是采购入库单的表体VOMap
   * 
   * @param items 所有的表体VO数组
   * @return 来源单据类型是采购入库单的表体VOMap。key-表体对应的来源单据（采购入库单）表体主键，val-表体VO
   */
  public Map<String, InvoiceItemVO> getInvItemVosGenByPurchanseIn(
      InvoiceItemVO[] itemArr) {
    Map<String, InvoiceItemVO> itemMap = new HashMap<String, InvoiceItemVO>();
    for (InvoiceItemVO item : itemArr) {
      if (ICBillType.PurchaseIn.getCode().equals(item.getCsourcetypecode())) {
        itemMap.put(item.getCsourcebid(), item);
      }
    }
    return itemMap;
  }

  /**
   * 获取校验失败的入库单表体VO列表
   * 
   * @param invoicePeriod 采购发票的会计期间
   * @param purchaseInBodyPeriods 采购入库单表体的会计期间Map
   * @param purchaseInBodyPkVOs 获取采购入库单的表体
   * @param isInvGreater
   *          是否采购发票会计期间应该晚于采购入库单。true-采购发票会计期间应该晚于采购入库单，false-采购发票会计期间应该早于采购入库单
   * @return 校验失败的入库单表体VO列表
   * @return
   */
  private List<PurchaseInBodyVO> checkFiscalPeriod(AccountPeriod invoicePeriod,
      Map<String, AccountPeriod> purchaseInBodyPeriods,
      Map<String, PurchaseInBodyVO> purchaseInBodyPkVOs, boolean isInvGreater) {
    List<PurchaseInBodyVO> errVoList = new ArrayList<PurchaseInBodyVO>();
    for (Map.Entry<String, AccountPeriod> entry : purchaseInBodyPeriods
        .entrySet()) {
      // 应该晚于的，小于时候有问题
      if (isInvGreater && invoicePeriod.compareTo(entry.getValue()) < 0) {
        errVoList.add(purchaseInBodyPkVOs.get(entry.getKey()));
      }
      // 应该早于的，晚于时候有问题
      else if (!isInvGreater && invoicePeriod.compareTo(entry.getValue()) > 0) {
        errVoList.add(purchaseInBodyPkVOs.get(entry.getKey()));
      }
    }
    // 结果排序，按单据号+行号
    Collections.sort(errVoList, new Comparator<PurchaseInBodyVO>() {
      @Override
      public int compare(PurchaseInBodyVO o1, PurchaseInBodyVO o2) {
        if (o1 == null) {
          return -1;
        }
        if (o2 == null) {
          return 1;
        }
        return (o1.getCgeneralhid() + o1.getCrowno()).compareTo((o2
            .getCgeneralhid() + o2.getCrowno()));
      }
    });

    return errVoList;
  }

  /**
   * 获取财务组织主键和主账簿主键的Map
   * 
   * @param financeOrgIDArr 财务组织主键数组
   * @return 财务组织主键和主账簿主键的Map。 key-财务组织主键，val-主账簿主键
   */
  private Map<String, String> getFinanceOrgMainBookMap(String[] financeOrgIDArr) {
    // 财务组织主键和主账簿主键的Map
    Map<String, String> financeMainBookMap =
        FinanceOrgPubService.queryMainBookID(financeOrgIDArr);

    return financeMainBookMap;
  }

  /**
   * 获取采购发票的会计期间
   * -取发票主组织财务组织的主账簿的会计期间方案，以发票表头的发票日期匹配此会计期间方案得到发票所处的会计期间。
   * 
   * @param headVO 采购发票表头VO
   * @param financeOrgMainBookMap 财务组织和主账簿的Map
   * @return 会计期间
   */
  private AccountPeriod getFirstPeriod(InvoiceHeaderVO headVO,
      Map<String, String> financeOrgMainBookMap) {
    String pk_org = headVO.getPk_org();
    Calendar calendar =
        Calendar.getInstance(pk_org, financeOrgMainBookMap.get(pk_org));
    return calendar.getPeriod(headVO.getDbilldate());
  }

  private Map<String, AccountPeriod> getFirstPeriod(
      Map<String, String> inBodyBidFinanceOrgMap,
      Map<String, PurchaseInBodyVO> purchaseInBodyPkVOs,
      Map<String, String> financeOrgMainBookMap) {

    Map<String, AccountPeriod> inBodyBidPeriodMap =
        new HashMap<String, AccountPeriod>();
    for (Map.Entry<String, String> entry : inBodyBidFinanceOrgMap.entrySet()) {
      String cgeneralbid = entry.getKey();
      String pk_org = entry.getValue();
      String pk_book = financeOrgMainBookMap.get(pk_org);
      Calendar calendar = Calendar.getInstance(pk_org, pk_book);
      PurchaseInBodyVO inBodyVO = purchaseInBodyPkVOs.get(cgeneralbid);
      inBodyBidPeriodMap.put(cgeneralbid,
          calendar.getPeriod(inBodyVO.getDbizdate()));
    }
    return inBodyBidPeriodMap;
  }

  /**
   * 获取采购入库单表体的主键和财务组织的Map
   * 
   * @param inBodyVOs 采购入库单表体VO数组
   * @return 采购入库单表体的表体的主键和财务组织的Map。 key-表体的主键， val-财务组织
   */
  private Map<String, String> getInBodyBidFinanceOrgMap(
      PurchaseInBodyVO[] inBodyVOs) {
    Set<String> pk_orgSet = new HashSet<String>();
    for (PurchaseInBodyVO inBodyVO : inBodyVOs) {
      pk_orgSet.add(inBodyVO.getPk_org());
    }
    Map<String, String> stockOrgFinanceOrgMap =
        StockOrgPubService.queryFinanceOrgIDByStockOrgID(pk_orgSet
            .toArray(new String[pk_orgSet.size()]));
    Map<String, String> bidFinanceOrgMap = new HashMap<String, String>();
    for (PurchaseInBodyVO inBodyVO : inBodyVOs) {
      bidFinanceOrgMap.put(inBodyVO.getCgeneralbid(),
          stockOrgFinanceOrgMap.get(inBodyVO.getPk_org()));
    }

    return bidFinanceOrgMap;

  }

  /**
   * 获取采购入库单的表体VOMap。key-表体主键，val-表体VO
   */
  private Map<String, PurchaseInBodyVO> getInvItemPkVos(
      PurchaseInBodyVO[] inBodyVOs) {
    Map<String, PurchaseInBodyVO> map = new HashMap<String, PurchaseInBodyVO>();
    for (PurchaseInBodyVO inBodyVO : inBodyVOs) {
      map.put(inBodyVO.getCgeneralbid(), inBodyVO);
    }
    return map;
  }

}
