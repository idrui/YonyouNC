/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-11-2 下午01:55:56
 */
package nc.pubimpl.pu.position;

import java.util.List;

import nc.pubimpl.pu.position.action.SplitBorgByPos;
import nc.pubimpl.pu.position.action.SplitByPosFactory;
import nc.pubimpl.pu.position.action.SplitHorgByPos;
import nc.pubimpl.pu.position.action.SplitMMByPos;
import nc.pubitf.pu.position.IPositionForSplit;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购岗/计划岗分单函数实现类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-11-2 下午01:55:56
 */
public class PositionForSplitImpl implements IPositionForSplit {

  @Override
  public List<String> splitBorgByPosition(AggregatedValueObject vo,
      String[] keys, int positiontype) throws BusinessException {
    try {
      SplitBorgByPos splitByPos =
          SplitByPosFactory.getInstance().getForBorg(vo, keys, positiontype);
      return splitByPos.splitByPosition();
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public List<String> splitHorgByPosition(AggregatedValueObject vo,
      String[] keys, int positiontype) throws BusinessException {
    try {
      SplitHorgByPos splitByPos =
          SplitByPosFactory.getInstance().getForHOrg(vo, keys, positiontype);
      return splitByPos.splitByPosition();
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public List<String> splitMMByPosition(AggregatedValueObject vo,
      String[] keys, int positiontype) throws BusinessException {
    try {
      SplitMMByPos splitByPos =
          SplitByPosFactory.getInstance().getForMM(vo, keys, positiontype);
      return splitByPos.splitByPosition();
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

}
