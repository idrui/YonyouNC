/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-26 上午10:33:24
 */
package nc.impl.pu.m4t;

import java.util.ArrayList;
import java.util.List;

import nc.bs.pu.m4t.maintain.InitialEstImportSaveBP;
import nc.impl.pu.m4t.action.InitialEstDeleteAction;
import nc.impl.pu.m4t.action.InitialEstInsertAction;
import nc.impl.pu.m4t.action.InitialEstUpdateAction;
import nc.itf.pu.m4t.IInitialEstMaintain;
import nc.vo.pu.m4t.entity.InitialEstContext;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>期初暂估单维护操作实现
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-3-26 上午10:33:24
 */
public class InitialEstMaintainImpl implements IInitialEstMaintain {

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.m4t.IInitialEstMaintain#delete(nc.vo.pu.m4t.entity.InitialEstVO[],
   *      nc.vo.pu.m4t.entity.InitialEstContext)
   */
  @Override
  public void delete(InitialEstVO[] vos, InitialEstContext context)
      throws BusinessException {
    try {
      new InitialEstDeleteAction().delete(vos, context);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

  }

  @Override
  public InitialEstVO[] importSave(InitialEstVO[] vos) throws BusinessException {
    InitialEstImportSaveBP bp = new InitialEstImportSaveBP();
    return bp.save(vos);
  }

  @Override
  public InitialEstVO[] save(InitialEstVO[] vos, InitialEstContext context)
      throws BusinessException {
    try {
      List<InitialEstVO> insertList = new ArrayList<InitialEstVO>();
      List<InitialEstVO> updateList = new ArrayList<InitialEstVO>();
      for (int i = 0; i < vos.length; i++) {
        if (StringUtils.isEmpty(vos[i].getPrimaryKey())) {
          insertList.add(vos[i]);
        }
        else {
          updateList.add(vos[i]);
        }
      }
      if (insertList.size() > 0) {
        return new InitialEstInsertAction().insert(
            insertList.toArray(new InitialEstVO[insertList.size()]), context);
      }
      if (updateList.size() > 0) {
        return new InitialEstUpdateAction().update(
            updateList.toArray(new InitialEstVO[updateList.size()]), context);
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;

  }

}
