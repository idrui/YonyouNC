package nc.pubimpl.pu.m21.mmpps;

import nc.pubitf.mmpub.sdmanage.ISupplyResult;
import nc.pubitf.pu.m21.mmpps.IOrderBillSupply;
import nc.pubitf.pu.m21.mmpps.OrderBillSupplyMapVO;

/**
 * �ɹ���������ɢ�����ṩ��ץȡ������Ϣ�ӿ�ʵ����
 * 
 * @since 6.3.1
 * @version 2013-8-2����08:58:14
 * @author fanly3
 */
public class OrderBillSupplyImpl implements IOrderBillSupply {

  @Override
  public ISupplyResult getSupplyResult() {
    OrderBillSupplyMapVO mapVo = new OrderBillSupplyMapVO();
    return mapVo;
  }

}
