package nc.pubimpl.pu.m20.mmpps;

import nc.pubitf.mmpub.sdmanage.ISupplyResult;
import nc.pubitf.pu.m20.mmpps.IPrayBillSupply;
import nc.pubitf.pu.m20.mmpps.PrayBillSupplyMapVO;

/**
 * �빺������ɢ�����ṩ��ץȡ����������Ϣ�ӿ�ʵ����
 * 
 * @since 6.3.1
 * @version 2013-9-9 ����10:36:26
 * @author fanly3
 */
public class PrayBillSupplyImpl implements IPrayBillSupply {

  @Override
  public ISupplyResult getSupplyResult() {
    PrayBillSupplyMapVO mapVo = new PrayBillSupplyMapVO();
    return mapVo;
  }

}
