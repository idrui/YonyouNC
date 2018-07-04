/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-19 下午09:14:19
 */
package nc.impl.pu.m422x;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pu.m422x.action.StoreReqAppDeleteAction;
import nc.impl.pu.m422x.action.StoreReqAppInsertAction;
import nc.impl.pu.m422x.action.StoreReqAppUpdateAction;
import nc.itf.pu.m422x.IStoreReqAppMaintain;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-19 下午09:14:19
 */
public class StoreReqAppMaintainImpl implements IStoreReqAppMaintain {

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.m422x.IStoreReqAppMaintain#delete(nc.vo.pu.m422x.entity.StoreReqAppVO[])
   */
  @Override
  public void delete(StoreReqAppVO[] vos) throws BusinessException {
    try {
      new StoreReqAppDeleteAction().delete(vos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.m422x.IStoreReqAppMaintain#save(nc.vo.pu.m422x.entity.StoreReqAppVO[])
   */
  @Override
  public StoreReqAppVO[] save(StoreReqAppVO[] vos) throws BusinessException {
    try {
      StoreReqAppVO[] insertVOs = this.pickupInsertVOs(vos);
      StoreReqAppVO[] updateVOs = this.pickupUpdateVOs(vos);

      if (!ArrayUtils.isEmpty(insertVOs)) {
        return new StoreReqAppInsertAction().insert(insertVOs);
      }

      if (!ArrayUtils.isEmpty(updateVOs)) {
        return new StoreReqAppUpdateAction().update(updateVOs);
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  private StoreReqAppVO[] pickupInsertVOs(StoreReqAppVO[] vos) {
    List<StoreReqAppVO> insertList = new ArrayList<StoreReqAppVO>();

    for (StoreReqAppVO vo : vos) {
      if (VOStatus.NEW==vo.getHVO().getStatus()) {
        insertList.add(vo);
      }
    }

    if (insertList.size() > 0) {
      return insertList.toArray(new StoreReqAppVO[insertList.size()]);
    }
    return null;
  }

  private StoreReqAppVO[] pickupUpdateVOs(StoreReqAppVO[] vos) {
    List<StoreReqAppVO> updateList = new ArrayList<StoreReqAppVO>();

    for (StoreReqAppVO vo : vos) {
      if (VOStatus.NEW!=vo.getHVO().getStatus()) {
        updateList.add(vo);
      }
    }

    if (updateList.size() > 0) {
      return updateList.toArray(new StoreReqAppVO[updateList.size()]);
    }
    return null;
  }
}
