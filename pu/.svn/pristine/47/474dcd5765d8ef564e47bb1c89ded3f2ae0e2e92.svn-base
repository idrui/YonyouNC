package nc.pubimpl.pu.m422x.api.action;

import nc.bs.pu.m422x.maintain.StoreReqAppDeleteBP;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.vo.pu.m422x.entity.StoreReqAppVO;

/**
 * 
 * @description
 *            物资需求申请单删除
 * @scene
 *      物资需求申请单删除
 * @param
 * 
 *
 * @since 6.5
 * @version 2015-10-26 下午8:05:39
 * @author luojw
 */
public class StoreReqAppAPIDeleteAction {


  public void delete(String[] ids){
    BillQuery<StoreReqAppVO> query = new BillQuery<>(StoreReqAppVO.class);
    StoreReqAppVO[] vos = query.query(ids);

    new StoreReqAppDeleteBP().delete(vos);
  }
}
