package nc.bs.pu.m27.feesettle.util;

import nc.vo.pu.est.entity.FeeEstDistVO;
import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pu.m27.entity.SettleFeeAllotDetailVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��¼����ͬһ��ⵥ��ͬһ������ķ��ý����̯��ϸ������ݹ���̯��ϸ�Ĺ�ϵ(һһ��Ӧ)
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-8-27 ����02:04:57
 */
public class FeeSettleEstMapping {
  // �ݹ����÷�̯��ϸ
  private FeeEstDistVO estallotvo;

  // �ݹ�����
  private FeeEstVO feeestvo;

  // ���ý����̯��ϸ
  private SettleFeeAllotDetailVO settleallotvo;

  public FeeSettleEstMapping(FeeEstVO feeestvo, FeeEstDistVO estallotvo,
      SettleFeeAllotDetailVO settleallotvo) {
    super();
    this.feeestvo = feeestvo;
    this.estallotvo = estallotvo;
    this.settleallotvo = settleallotvo;
  }

  public FeeEstDistVO getEstallotvo() {
    return this.estallotvo;
  }

  public FeeEstVO getFeeestvo() {
    return this.feeestvo;
  }

  public SettleFeeAllotDetailVO getSettleallotvo() {
    return this.settleallotvo;
  }

}
