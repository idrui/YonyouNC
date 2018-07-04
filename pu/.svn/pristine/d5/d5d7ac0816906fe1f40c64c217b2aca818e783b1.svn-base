package nc.pubitf.pu.m25.dm.m4812;

import nc.vo.dm.m4812.entity.ApInvoiceVO;
import nc.vo.pub.BusinessException;

/**
 * 采购发票给应付运费发票的审批与弃审接口
 * 
 * @since 6.31
 * @version 2014-10-13 下午3:24:05
 * @author zhangshqb
 */
public interface IInvoiceApproveFor4812 {
  /**
   * 应付运费发票审批时调用此接口生成对应的采购发票单
   * 
   * @param vos
   * @throws BusinessException
   */
  public void approve(ApInvoiceVO[] vos) throws BusinessException;

  /**
   * 应付运费发票审批时调用此接口删除对应的采购发票单
   * 
   * @param ids
   * @throws BusinessException
   */
  public void unapprove(String[] ids) throws BusinessException;
}
