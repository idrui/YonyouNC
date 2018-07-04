/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-13 上午08:28:29
 */
package nc.impl.pu.m21;

import nc.impl.pu.m21.action.OrderReceivePlanSaveAction;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pub.smart.SmartServiceImpl;
import nc.itf.pu.m21.IOrderReceivePlan;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货计划实现类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-13 上午08:28:29
 */
public class OrderReceivePlanImpl extends SmartServiceImpl implements
    IOrderReceivePlan {

  @Override
  public Object[] batchSave(BatchOperateVO batchVO, OrderVO orderVO,
      UFBoolean confirm) throws BusinessException {
    if (null == batchVO) {
      return null;
    }

    try {
      return new OrderReceivePlanSaveAction().batchSave(batchVO, orderVO,
          confirm);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return null;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.m21.IOrderReceivePlan#queryPlanVOsByHId(java.lang.String)
   */
  @Override
  public OrderReceivePlanVO[] queryPlanVOsByHId(String hid)
      throws BusinessException {
    if (StringUtil.isEmptyWithTrim(hid)) {
      return null;
    }

    try {
      VOQuery<OrderReceivePlanVO> query =
          new VOQuery<OrderReceivePlanVO>(OrderReceivePlanVO.class);
      OrderReceivePlanVO[] rpVOs =
          query.query(" and pk_order='" + hid + "' and dr=0 ", null);
      if (ArrayUtils.isEmpty(rpVOs)) {
        return null;
      }

      for (OrderReceivePlanVO rpVO : rpVOs) {
        if (MathTool.compareTo(rpVO.getNaccumarrvnum(), UFDouble.ZERO_DBL) > 0) {
          rpVO.setNaccumreceivenum(rpVO.getNaccumarrvnum());
        }
        else if (MathTool
            .compareTo(rpVO.getNaccumstorenum(), UFDouble.ZERO_DBL) > 0) {
          rpVO.setNaccumreceivenum(rpVO.getNaccumstorenum());
        }
      }
      return rpVOs;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return null;
  }

}
