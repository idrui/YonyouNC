package nc.pubimpl.pu.m21.mmpps;

import nc.pubitf.mmpub.sdmanage.ISupplyResult;
import nc.pubitf.pu.m21.mmpps.ArrivePlanSupplyMapVO;
import nc.pubitf.pu.m21.mmpps.IArrivePlanSupply;

/**
 * �����ƻ�����ɢ�����ṩ��ץȡ������Ϣ�ӿ�ʵ����
 * 
 * @since 6.36
 * @version 2015-2-10 ����2:04:28
 * @author mengjian
 */
public class ArrivePlanSupplyImpl implements IArrivePlanSupply {

  @Override
  public ISupplyResult getSupplyResult() {
    ArrivePlanSupplyMapVO mapVo = new ArrivePlanSupplyMapVO();
    return mapVo;
  }

}
