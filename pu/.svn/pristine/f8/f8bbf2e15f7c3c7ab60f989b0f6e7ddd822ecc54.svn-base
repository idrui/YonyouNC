/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-22 上午11:33:50
 */
package nc.impl.pu.m20;

import nc.bs.pu.m20.maintain.PraybillRQueryBP;
import nc.impl.pu.m20.action.PraybillRAction;
import nc.itf.pu.m20.IPraybillRevise;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m20.context.PraybillContext;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>请购单修订实现类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-6-22 上午11:33:50
 */
public class PraybillReviseImpl implements IPraybillRevise {

  @Override
  public PraybillVO[] queryHistory(String sql, UFBoolean isLazy)
      throws BusinessException {
    try {
      return new PraybillRQueryBP().queryHistory(sql);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public PraybillVO[] queryPrayBills(IQueryScheme queryScheme)
      throws BusinessException {
    try {
      return new PraybillRQueryBP().query(queryScheme);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public PraybillVO[] reviseSave(PraybillVO[] vos, PraybillContext[] ptxs)
      throws BusinessException {
    PraybillContext ptx =
        ArrayUtils.isEmpty(ptxs) ? new PraybillContext() : ptxs[0];
    try {
      return new PraybillRAction().revise(vos, ptx);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }
}
