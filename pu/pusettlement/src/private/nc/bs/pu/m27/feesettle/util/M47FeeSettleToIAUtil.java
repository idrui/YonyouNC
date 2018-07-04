package nc.bs.pu.m27.feesettle.util;

import java.util.Collection;

import nc.vo.pu.m27.entity.SettleBillHeaderVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>委外入库单进行费用结算时传存货核算
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-8-12 下午01:54:55
 */
public class M47FeeSettleToIAUtil extends AbstractToIAUtil {

  public M47FeeSettleToIAUtil(SettleBillHeaderVO header,
      Collection<SettleBillItemVO> items) {
    super(header, items);
  }

  @Override
  protected String getSourceTypeForVOChg() {
    // 消耗汇总、委外入库单使用此单据类型，进行传IA的数据交换
    return POBillType.SubContractSettleBill.getCode();
  }

}
