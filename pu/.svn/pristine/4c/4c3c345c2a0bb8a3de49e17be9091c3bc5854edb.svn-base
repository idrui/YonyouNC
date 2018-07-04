/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-1 上午09:45:50
 */
package nc.itf.pu.reference.ic;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.ic.atp.ATPUpdate;
import nc.pubitf.ic.atp.IAtpQuery;
import nc.vo.ic.atp.entity.AtpVO;
import nc.vo.ic.atp.pub.AtpQryParamVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>可用量更新服务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-1 上午09:45:50
 */
public class ATPServices {
  /**
   * 方法功能描述：动作后置调用
   * <p>
   * <b>参数说明</b>
   * 
   * @param cbilltype
   * @param bills
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-1 上午09:50:01
   */
  public static void modifyATPAfter(String cbilltype,
      AggregatedValueObject[] bills) {
    ATPUpdate service = NCLocator.getInstance().lookup(ATPUpdate.class);
    try {
      service.modifyATPAfter(cbilltype, bills);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 方法功能描述：动作前置调用
   * <p>
   * <b>参数说明</b>
   * 
   * @param cbilltype
   * @param bills
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-1 上午09:49:16
   */
  public static AtpVO[] modifyATPBefore(String cbilltype,
      AggregatedValueObject[] bills) {
    ATPUpdate service = NCLocator.getInstance().lookup(ATPUpdate.class);
    try {
      service.abandonATPCheck();
      service.modifyATPBefore(cbilltype, bills);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    return null;
  }

  /**
   * 方法功能描述：查询可用量
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_org
   *          库存组织的OID
   * @param pk_materials
   *          物料的OID数组
   * @param date
   *          日期
   * @return 可用量<物料，可用量>
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-7-24 下午05:00:53
   */
  public static Map<String, UFDouble> queryATP(String pk_org,
      String[] pk_materials, UFDate[] date) {
    Map<String, UFDouble> atps = new HashMap<String, UFDouble>();
    IAtpQuery service = NCLocator.getInstance().lookup(IAtpQuery.class);
    String[] orgs = new String[pk_materials.length];
    for (int i = 0; i < orgs.length; i++) {
      orgs[i] = pk_org;
    }
    try {
      UFDouble[] results = service.queryAtp(orgs, pk_materials, date);
      for (int i = 0; i < pk_materials.length; i++) {
        atps.put(pk_materials[i], results[i]);
      }
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return atps;
  }

  /**
   * 方法功能描述：查询可用量VO
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_calbodyoids
   * @param cmaterialoids
   * @param ddates
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-23 下午04:30:09
   */
  public static Map<String, AtpVO> queryAtpAndOnhandNum(AtpQryParamVO[] paramVOs) {
    Map<String, AtpVO> atps = new HashMap<String, AtpVO>();
    IAtpQuery service = NCLocator.getInstance().lookup(IAtpQuery.class);
    try {
      AtpVO[] results = service.queryAtpVOs(paramVOs);
      for (int i = 0; i < paramVOs.length; i++) {
        atps.put(paramVOs[i].getCmaterialoid(), results[i]);
      }
      // int materLen = cmaterialoids.length;
      // for (int i = 0; i < materLen; i++) {
      // atps.put(cmaterialoids[i], results[i]);
      // }
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }

    return atps;
  }
}
