package nc.bs.pu.m27.feesettle.util;

import java.util.Collection;

import nc.vo.pu.m27.entity.SettleBillHeaderVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * @since 6.36
 * @version 2014-10-25 ����11:52:06
 * @author mengjian
 */
public class M4AFeeSettleToPCIAUtil extends AbstractToPCIAUtil {

  public M4AFeeSettleToPCIAUtil(SettleBillHeaderVO header,
      Collection<SettleBillItemVO> items) {
    super(header, items);
  }

  @Override
  protected String getSourceTypeForVOChg() {
    // ���������ʹ�ô˵������ͣ����д�IA�����ݽ���
    return POBillType.GeneralInSettleBill.getCode();
  }
}
