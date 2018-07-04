package nc.pubimpl.pu.m20.invp.inv9;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.pu.m20.maintain.PraybillDeleteBP;
import nc.bs.pu.m20.maintain.PraybillUpdateBP;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.pubitf.pu.m20.invp.inv9.IDelete20ForInv9;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.entity.PraybillViewVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 库存计划删除下游请购单单据接口
 * 
 * @author zhangyhh
 * @since 6.30
 * @time 2014-04-29 15:37:23
 */
public class Delete20ForInv9Impl implements IDelete20ForInv9 {

  @Override
  public void deleteBills(String[] pk_prayLine) throws BusinessException {
    try {
      ViewQuery<PraybillViewVO> query =
          new ViewQuery<PraybillViewVO>(PraybillViewVO.class);
      PraybillViewVO[] views = query.query(pk_prayLine);

      if (ArrayUtils.isEmpty(views)) {
        return;
      }

      PraybillVO[] delvos = PraybillViewVO.getPraybillVO(views);
      List<String> pkList = new ArrayList<String>();
      for (PraybillVO vo : delvos) {
        pkList.add(vo.getHVO().getPk_praybill());
      }
      String[] pks = pkList.toArray(new String[pkList.size()]);
      BillQuery<PraybillVO> voquery =
          new BillQuery<PraybillVO>(PraybillVO.class);
      // 和delvos一一对应
      PraybillVO[] origvos = voquery.query(pks);

      List<PraybillVO> updateList = new ArrayList<PraybillVO>();
      List<PraybillVO> updateorgList = new ArrayList<PraybillVO>();
      List<PraybillVO> delList = new ArrayList<PraybillVO>();
      for (int i = 0; i < delvos.length; ++i) {
        PraybillItemVO[] delBodyVOs = delvos[i].getBVO();
        PraybillItemVO[] bodyvos = origvos[i].getBVO();
        if (delBodyVOs.length == bodyvos.length) {
          delList.add(delvos[i]);
        }
        else {
          Map<String, PraybillItemVO> delMap =
              new HashMap<String, PraybillItemVO>();
          for (PraybillItemVO delBodyVO : delBodyVOs) {
            delMap.put(delBodyVO.getPk_praybill_b(), delBodyVO);
          }

          List<PraybillItemVO> newBodyList = new ArrayList<PraybillItemVO>();
          for (int j = 0; j < bodyvos.length; ++j) {
            if (delMap.containsKey(bodyvos[j].getPk_praybill_b())) {
              PraybillItemVO itemvo = delMap.get(bodyvos[j].getPk_praybill_b());
              itemvo.setStatus(VOStatus.DELETED);
              newBodyList.add(itemvo);
            }
            else {
              bodyvos[j].setStatus(VOStatus.UNCHANGED);
              newBodyList.add(bodyvos[j]);
            }
          }

          PraybillItemVO[] newBodyItems =
              newBodyList.toArray(new PraybillItemVO[newBodyList.size()]);
          delvos[i].getHVO().setStatus(VOStatus.UPDATED);
          delvos[i].setBVO(newBodyItems);
          updateList.add(delvos[i]);
          updateorgList.add(origvos[i]);
        }
      }

      if (delList.size() > 0) {
        PraybillVO[] delVOs = delList.toArray(new PraybillVO[delList.size()]);
        new PraybillDeleteBP().delete(delVOs);
      }
      if (updateList.size() > 0) {
        PraybillVO[] updatevos =
            updateList.toArray(new PraybillVO[updateList.size()]);
        PraybillVO[] updateorigvos =
            updateorgList.toArray(new PraybillVO[updateorgList.size()]);

        new PraybillUpdateBP().update(updatevos, updateorigvos, false);
      }

    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }
}
