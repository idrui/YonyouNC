package nc.vo.pu.m27.rule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m27.ISettleMatch;
import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.merge.MatchMergeData;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ValidationException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>�Զ��������</b>
 * <ul>
 * <li>������Ŀ1
 * <li>������Ŀ2
 * <li>...
 * </ul>
 * <p>
 * <b>�����ʷ����ѡ����</b>
 * <p>
 * XXX�汾����XXX��֧�֡�
 * <p>
 * <p>
 * 
 * @version ���汾��
 * @since ��һ�汾��
 * @author wangyf
 * @time 2010-1-6 ����03:00:23
 */
public class AutoMatchRule extends MatchRule {

  /**
   * ��Ʊ����ⵥ����
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @author wangyf
   * @time 2010-3-8 ����11:49:13
   */
  public static String[] getInvoiceStockFixedRule() {
    // ���� ������֯��ͬ ���� ��Ӧ����ͬ ���� ������ͬ

    return new String[] {
      StockSettleVO.PK_FINANCEORG, StockSettleVO.PK_SRCMATERIAL,
      StockSettleVO.PK_SUPPLIER
    };
  }

  /**
   * ��Ʊ�Գ����
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @author wangyf
   * @time 2010-3-8 ����10:32:30
   */
  public static String[] getRBInvoiceFixedRule() {

    // ���� ������֯��ͬ ���������� ��Ӧ����ͬ
    // ���� ������ͬ ���������� ��Ʊ������ͬ
    // ���� ����˰������ͬ

    return new String[] {
      InvoiceSettleVO.PK_ORG, InvoiceSettleVO.PK_SRCMATERIAL,
      InvoiceSettleVO.PK_SUPPLIER, InvoiceSettleVO.VTRANTYPECODE,
      // ����˰������ͬ �ݱ�Ϊ��ԭ����ͬ������˰������ͬ
      InvoiceSettleVO.NORIGPRICE, InvoiceSettleVO.CORIGCURRENCYID
    };
  }

  /**
   * ������ⵥ����
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @author wangyf
   * @time 2010-3-8 ����10:33:07
   */
  public static String[] getRBStockFixedRule() {
    // ���� ������֯��ͬ ���� ������ͬ

    return new String[] {
      StockSettleVO.PK_FINANCEORG, StockSettleVO.PK_SRCMATERIAL
    };
  }

  @Override
  public void check(MatchMergeData mmData, SettleEnvironment settleEnvironment)
      throws ValidationException {
    //
  }

  @Override
  public void filter(MatchMergeData mmData, SettleEnvironment settleEnvironment) {
    super.filter(mmData, settleEnvironment);
    // ���˵��뵱ǰ���﷢Ʊ����صķ��÷�Ʊ
    this.filterNonRelationFeeDiscount(mmData);
    // ���˵��Ѿ��ݹ��ķ��÷�Ʊ
    this.filterEstAPFee(mmData);
  }

  private void filterEstAPFee(MatchMergeData mmData) {
    if (ArrayUtils.isEmpty(mmData.getStockVos())
        || ArrayUtils.isEmpty(mmData.getFeeInvcVos())) {
      return;
    }
    try {
      MapList<String, String> stockFeeEstApMap =
          NCLocator.getInstance().lookup(ISettleMatch.class)
              .getEstAPFeeMaterial(mmData.getStockVos());
      if (stockFeeEstApMap.size() == 0) {
        return;
      }
      // ���˵��ݹ�Ӧ���ķ��÷�Ʊ
      this.filterEstAPFee(mmData, stockFeeEstApMap);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

  }

  private void filterEstAPFee(MatchMergeData mmData,
      MapList<String, String> stockFeeEstApMap) {
    FeeDiscountSettleVO[] vos = mmData.getFeeInvcVos();
    Set<String> estMarSet = new HashSet<String>();
    for (Entry<String, List<String>> entry : stockFeeEstApMap.entrySet()) {
      estMarSet.addAll(entry.getValue());
    }
    List<FeeDiscountSettleVO> filterLst = new ArrayList<FeeDiscountSettleVO>();
    for (FeeDiscountSettleVO vo : vos) {
      // ����ݹ���Ӧ���ҷ�Ʊ�Ѿ�����Ӧ����ֱ�ӹ��˵�
      if (UFBoolean.TRUE.equals(vo.getBapflag())
          && estMarSet.contains(vo.getPk_material())) {
        continue;
      }
      filterLst.add(vo);
    }
    mmData.setFeeInvcVos(CollectionUtils.isEmpty(filterLst) ? null : filterLst
        .toArray(new FeeDiscountSettleVO[filterLst.size()]));
  }

  private List<FeeDiscountSettleVO> filterNonRelationFeeDiscount(
      FeeDiscountSettleVO[] feeDiscntVos, Set<String> goodsInvcs) {
    List<FeeDiscountSettleVO> filterLst = new ArrayList<FeeDiscountSettleVO>();
    if (!ArrayUtils.isEmpty(feeDiscntVos)) {
      for (FeeDiscountSettleVO feeDiscount : feeDiscntVos) {
        String pk_invoice = feeDiscount.getPk_invoice();
        if (goodsInvcs.contains(pk_invoice)
            || goodsInvcs.contains(feeDiscount.getPk_parentinvoice())) {
          filterLst.add(feeDiscount);
        }
      }
    }
    return filterLst;
  }

  private void filterNonRelationFeeDiscount(MatchMergeData mmData) {
    Set<String> goodsInvcs = null;
    if (ArrayUtils.isEmpty(mmData.getGoodsInvcVos())) {
      goodsInvcs = new HashSet<String>();
    }
    else {
      goodsInvcs =
          CirVOUtil.getDistinctFieldSet(mmData.getGoodsInvcVos(),
              InvoiceSettleVO.PK_INVOICE);
    }
    List<FeeDiscountSettleVO> filterLst =
        this.filterNonRelationFeeDiscount(mmData.getDiscountInvcVos(),
            goodsInvcs);
    mmData.setDiscountInvcVos(CollectionUtils.isEmpty(filterLst) ? null
        : filterLst.toArray(new FeeDiscountSettleVO[filterLst.size()]));
    filterLst =
        this.filterNonRelationFeeDiscount(mmData.getFeeInvcVos(), goodsInvcs);
    mmData.setFeeInvcVos(CollectionUtils.isEmpty(filterLst) ? null : filterLst
        .toArray(new FeeDiscountSettleVO[filterLst.size()]));
  }

}
