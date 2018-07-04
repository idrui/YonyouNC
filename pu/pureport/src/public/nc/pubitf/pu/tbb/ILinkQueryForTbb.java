package nc.pubitf.pu.tbb;

import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pub.BusinessException;
import nc.vo.tb.obj.NtbParamVO;

/**
 * @since 6.0
 * @version 2011-6-20 上午10:15:52
 * @author wuxla
 */

public interface ILinkQueryForTbb {

  /**
   * 联查请购单
   * 
   * @param param 预算公式
   * @return 请购单
   * @throws BusinessException
   */
  PraybillVO[] linkQuery20ForTbb(NtbParamVO param) throws BusinessException;

  /**
   * 联查采购订单
   * 
   * @param param 预算公式
   * @return 采购订单
   * @throws BusinessException
   */
  OrderVO[] linkQuery21ForTbb(NtbParamVO param) throws BusinessException;

  /**
   * 采购发票
   * 
   * @param param 预算公式
   * @return 采购发票
   * @throws BusinessException
   */
  InvoiceVO[] linkQuery25ForTbb(NtbParamVO param) throws BusinessException;
}
