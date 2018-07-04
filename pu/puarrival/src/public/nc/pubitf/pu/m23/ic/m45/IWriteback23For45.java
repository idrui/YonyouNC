package nc.pubitf.pu.m23.ic.m45;

import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货单提供给库存的采购入库单的回写累计入库数量的服务接口类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-8 下午04:14:51
 */
public interface IWriteback23For45 {

  /**
   * 方法功能描述：到货单提供给库存的采购入库单的回写累计入库数量的服务接口
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author hanbin
   * @time 2010-1-8 下午04:05:12
   */
  public void writebackNum(IWriteback23For45Para[] paraArray)
      throws BusinessException;
}
