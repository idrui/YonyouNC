/**
 * 
 */
package nc.pubitf.pu.m25.pu.settle;

import nc.vo.ic.m45.entity.PurchaseInVO;
import nc.vo.ic.m47.entity.SubcontInVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>为采购结算提供虚拟发票维护服务
 * <li>用于无发票结算
 * </ul>
 * <p>
 * <p>
 * 
 * @author xiebo
 * @version 6.0
 * @see
 * @since
 * @time 2010-1-26 下午03:35:48
 */
public interface IVirtualInvoiceMaintain {
  /**
   * 方法功能描述：取消结算(无发票)时，删除相应的虚拟发票。
   * <p>
   * <b>参数说明</b>
   * 
   * @param ids 虚拟发票主表ID数组
   * @throws BusinessException <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-4-12 上午10:50:48
   */
  public void delete(String[] ids) throws BusinessException;

  /**
   * 根据传入的期初暂估单信息生成虚拟发票，用于无发票结算
   * 
   * @param vos
   * @return
   * @throws BusinessException
   */
  public InvoiceVO[] genByInitEst(InitialEstVO[] vos) throws BusinessException;

  /**
   * 方法功能描述：根据传入的采购入库单信息生成虚拟发票，用于无发票结算。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos 要生成虚拟发票的采购入库单VO数组
   * @return 生成的虚拟发票
   * @throws BusinessException <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-4-12 上午10:46:59
   */
  public InvoiceVO[] genByPurchsIn(PurchaseInVO[] vos) throws BusinessException;

  /**
   * 根据传入的委托加工入库单信息生成虚拟发票，用于无发票结算
   * 
   * @param vos
   * @return
   * @throws BusinessException
   */
  public InvoiceVO[] genBySubcontIn(SubcontInVO[] vos) throws BusinessException;
}
