package nc.pubimpl.pu.m20.mmdp.altc;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.pubitf.pu.m20.mmdp.altc.IQueryPrayOrderMapForALTC;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.MapList;

/**
 * 场景描述 打开单据按钮触发
 * 业务逻辑 由单据主键获取节点和单据主键的对应关系
 * 
 * 2015年7月24日 wangweir 生产V65版本不需要此接口，并且生产没有接口代码此处先注释 Begin
 * end
 * 
 * @since 6.36
 * @version 2015-2-9 下午9:35:51
 * @author mengjian
 */
public class QueryPrayOrderMapForALTCImpl implements IQueryPrayOrderMapForALTC {

  private final String po20Nodekey = "40040200";

  private final String po20reviseNodekey = "40040202";

  public MapList<String, String> queryFuncodeByBillStatus(String[] ids)
      throws BusinessException {
    MapList<String, String> nodekeyMap = new MapList<String, String>();
    if (null == ids) {
      return nodekeyMap;
    }
    VOQuery<PraybillHeaderVO> query =
        new VOQuery<PraybillHeaderVO>(PraybillHeaderVO.class);
    PraybillHeaderVO[] headvos = query.query(ids);
    for (PraybillHeaderVO vo : headvos) {
      if (POEnumBillStatus.APPROVE.toInteger().equals(vo.getFbillstatus())) {
        nodekeyMap.put(this.po20reviseNodekey, vo.getPk_praybill());
      }
      else {
        nodekeyMap.put(this.po20Nodekey, vo.getPk_praybill());
      }
    }
    return nodekeyMap;
  }

}
