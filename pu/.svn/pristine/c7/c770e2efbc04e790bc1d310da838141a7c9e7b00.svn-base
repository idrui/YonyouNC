package nc.pubitf.pu.m25.it;

import nc.vo.ic.m45.entity.PurchaseInVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pub.BusinessException;

public interface IVirtualInvoiceMaintainForIT {

  /**
   * 方法功能描述：根据传入的进口入库单信息生成虚拟发票，用于无发票结算。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos 要生成虚拟发票的采购入库单VO数组
   * @return 生成的虚拟发票
   * @throws BusinessException <p>
   * @since 6.31
   * @author mengjian
   * @time 2013-11-06 上午10:46:59
   */
  InvoiceVO[] genByPurchsIn4IT(PurchaseInVO[] vos) throws BusinessException;

}
