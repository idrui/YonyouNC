package nc.impl.pu.m24;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pu.m24.action.PricestlDeleteAction;
import nc.impl.pu.m24.action.PricestlInsertAction;
import nc.impl.pu.m24.action.PricestlUpdateAction;
import nc.itf.pu.m24.IPricestlMaintain;
import nc.vo.pu.m24.entity.PricestlVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>价格结算单维护实现类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-7-30 下午02:24:41
 */
public class PricestlMaintainImpl implements IPricestlMaintain {

  @Override
  public void delete(PricestlVO[] vos) throws BusinessException {
    try {
      new PricestlDeleteAction().delete(vos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  @Override
  public PricestlVO[] insert(PricestlVO[] vos) throws BusinessException {
    PricestlVO[] ret = null;
    try {
      PricestlInsertAction action = new PricestlInsertAction();

      ret = action.insert(vos);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return ret;
  }

  @Override
  public PricestlVO[] saveBase(PricestlVO[] vos) throws BusinessException {

    PricestlVO[] savedvos = null;
    try {
      // 筛选出新增、修改的单据
      List<PricestlVO> insertVOList = new ArrayList<PricestlVO>();
      List<PricestlVO> updateVOList = new ArrayList<PricestlVO>();
      for (int i = 0, len = vos.length; i < len; i++) {
        if (StringUtils.isEmpty(vos[i].getPrimaryKey())) {
          insertVOList.add(vos[i]);
        }
        else {
          updateVOList.add(vos[i]);
        }
      }
      if (insertVOList.size() != 0) {
        return this.insert(insertVOList.toArray(new PricestlVO[insertVOList
            .size()]));
      }
      if (updateVOList.size() != 0) {
        return this.update(updateVOList.toArray(new PricestlVO[updateVOList
            .size()]));
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return savedvos;
  }

  @Override
  public PricestlVO[] update(PricestlVO[] vos) throws BusinessException {
    PricestlVO[] ret = null;
    try {
      PricestlUpdateAction action = new PricestlUpdateAction();

      ret = action.update(vos);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return ret;
  }

}
