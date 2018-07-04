package nc.pubimpl.pu.m21.mmpps;

import nc.pubitf.mmpub.sdmanage.ISupplyResult;
import nc.pubitf.pu.m21.mmpps.ArrivePlanSupplyMapVO;
import nc.pubitf.pu.m21.mmpps.IArrivePlanSupply;

/**
 * 到货计划给离散制造提供的抓取供给信息接口实现类
 * 
 * @since 6.36
 * @version 2015-2-10 下午2:04:28
 * @author mengjian
 */
public class ArrivePlanSupplyImpl implements IArrivePlanSupply {

  @Override
  public ISupplyResult getSupplyResult() {
    ArrivePlanSupplyMapVO mapVo = new ArrivePlanSupplyMapVO();
    return mapVo;
  }

}
