package nc.pubitf.pu.m23.ic.m47;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货单提供给库存的委托加工入库单的查询服务接口类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-8 下午03:57:12
 */
public interface IQuery23For47 {
  /**
   * 方法功能描述：到货单提供给库存的委托加工入库单的查询服务方法
   * <p>
   * <b>参数说明</b>
   * 
   * @param queryScheme
   *          查询scheme
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-8 下午04:00:53
   */
  public ArriveVO[] queryArrive(IQueryScheme queryScheme)
      throws BusinessException;

  /**
   * 方法功能描述：到货单提供给库存的委托加工入库单的查询服务方法
   * <p>
   * <b>参数说明</b>
   * 
   * @param sql
   *          查询语句
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-8 下午04:00:53
   */
  // public ArriveVO[] queryArrive(String whereSql) throws BusinessException;
}
