package nc.vo.pu.m27.rule;

import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.merge.MatchMergeData;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pub.ValidationException;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>ͬ���Ͻ������</b>
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
 * @time 2010-1-4 ����02:55:43
 */
public class SameMaterialMatchRule extends GoodsMatchRule {

  public static String[] getRBInvoiceFixedRule() {
    // ������ͬ����Ӧ����ͬ��������֯��ͬ������˰������ͬ
    return new String[] {
      InvoiceSettleVO.PK_ORG, InvoiceSettleVO.PK_SRCMATERIAL,
      InvoiceSettleVO.PK_SUPPLIER,
      // ����˰������ͬ �ݱ�Ϊ��ԭ����ͬ������˰������ͬ
      InvoiceSettleVO.NORIGPRICE, InvoiceSettleVO.CORIGCURRENCYID
    };
  }

  public static String[] getRBStockFixedRule() {
    // ������ͬ��������֯��ͬ
    return new String[] {
      StockSettleVO.PK_FINANCEORG, StockSettleVO.PK_SRCMATERIAL
    };
  }

  @Override
  public void check(MatchMergeData mmData, SettleEnvironment settleEnvironment)
      throws ValidationException {
    // a) ��Ʊ����Ϊ��
    // b) ��ⵥ����Ϊ��
    // c) �ֹ����㲻֧��ֻ���ۿۡ������෢Ʊ�Ľ���

    if (ArrayUtils.isEmpty(mmData.getGoodsInvcVos())
        && ArrayUtils.isEmpty(mmData.getStockVos())) {
      throw new ValidationException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0166")/*@res "��ѡ����﷢Ʊ����ⵥ�����Ļ��ܵ���"*/);
    }
    if ((!ArrayUtils.isEmpty(mmData.getFeeInvcVos()) || !ArrayUtils
        .isEmpty(mmData.getDiscountInvcVos()))
        && ArrayUtils.isEmpty(mmData.getGoodsInvcVos())) {
      throw new ValidationException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0167")/*@res "ѡ�������ۿ۷�Ʊ�󣬱���ѡ����﷢Ʊ��"*/);
    }
    super.check(mmData, settleEnvironment);
  }

}