package nc.pubimpl.pu.m422x.invp.inv9;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.pu.m422x.maintain.StoreReqAppDeleteBP;
import nc.bs.pu.m422x.maintain.StoreReqAppSaveBP;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.pubitf.pu.m422x.invp.inv9.IDelete422xForInv9;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 库存计划删除下游物资申请单实现
 * 
 * @author zhangyhh
 * @since 6.30
 * @time 2014-04-29 15:37:23
 */
public class Delete422xForInv9Impl implements IDelete422xForInv9 {

  /**
   * 取消平衡后删除下游物资需求申请单接口
   * 
   * @param pk_reqLine
   * @throws BusinessException
   */
  @Override
  public void deleteReq(String[] pk_reqLine) throws BusinessException {

    try {
      // 查询更新前的物资需求申请数据
      StoreReqAppItemVO[] bodys =
          new VOQuery<StoreReqAppItemVO>(StoreReqAppItemVO.class)
              .query(pk_reqLine);

      if (ArrayUtils.isEmpty(bodys)) {
        return;
      }
      Map<String, Set<String>> headkey = new HashMap<String, Set<String>>();
      for (StoreReqAppItemVO body : bodys) {
        if (!headkey.containsKey(body.getPk_storereq())) {
          headkey.put(body.getPk_storereq(), new HashSet<String>());
        }
        headkey.get(body.getPk_storereq()).add(body.getPk_storereq_b());
      }

      StoreReqAppVO[] bills =
          new BillQuery<StoreReqAppVO>(StoreReqAppVO.class).query(headkey
              .keySet().toArray(new String[headkey.keySet().size()]));

      List<StoreReqAppVO> deletebillVOs = new ArrayList<StoreReqAppVO>();
      List<StoreReqAppVO> updatebillVOs = new ArrayList<StoreReqAppVO>();
      List<StoreReqAppVO> deletelineVOs = new ArrayList<StoreReqAppVO>();
      for (StoreReqAppVO bill : bills) {
        // 整单删除,剩下的删行
        if (bill.getBVO().length == headkey.get(bill.getPrimaryKey()).size()) {
          deletebillVOs.add(bill);
          headkey.remove(bill.getPrimaryKey());
        }
        else {
          deletelineVOs.add(bill);// 删行前数据
          StoreReqAppVO newVO = (StoreReqAppVO) bill.clone();
          newVO.getHVO().setStatus(VOStatus.UPDATED);
          for (StoreReqAppItemVO item : newVO.getBVO()) {
            if (headkey.get(newVO.getPrimaryKey()).contains(
                item.getPk_storereq_b())) {
              item.setStatus(VOStatus.DELETED);
            }
            else {
              item.setStatus(VOStatus.UNCHANGED);
            }
          }
          updatebillVOs.add(newVO);// 删行后数据
        }
      }

      if (deletebillVOs.size() > 0) {
        new StoreReqAppDeleteBP().delete(deletebillVOs
            .toArray(new StoreReqAppVO[deletebillVOs.size()]));
      }
      if (updatebillVOs.size() > 0) {
        new StoreReqAppSaveBP().save(null,
            updatebillVOs.toArray(new StoreReqAppVO[updatebillVOs.size()]),
            deletelineVOs.toArray(new StoreReqAppVO[deletelineVOs.size()]));
      }

    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

  }

}
