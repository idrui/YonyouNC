package nc.bs.pu.m27.feesettle.util;

import java.util.Collection;

import nc.vo.pu.m27.entity.SettleBillHeaderVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���Ļ��ܽ��з��ý���ʱ���������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-8-12 ����01:55:11
 */
public class M50FeeSettleToIAUtil extends M45FeeSettleToIAUtil {

  public M50FeeSettleToIAUtil(SettleBillHeaderVO header,
      Collection<SettleBillItemVO> items) {
    super(header, items);
  }

  @Override
  protected String getSourceTypeForVOChg() {
    // ���Ļ���ʹ�ô˵������ͣ����д�IA�����ݽ���
    return POBillType.VoiConsumeSettleBill.getCode();
  }

}