package nc.pubitf.pu.m21.pu.m25;

import nc.vo.pub.BusinessException;

public interface IOrderWriteBackFor25 {

  /**
   * 方法功能描述：发票单价容差检查
   * <p>
   * <b>参数说明</b>
   * 
   * @param wbVos
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-11 上午08:54:30
   */
  public void invoicePriceChk(IOrderWriteBackParaFor25[] wbVos)
      throws BusinessException;

  public void writeBackFor25(IOrderWriteBackParaFor25[] wbVos)
      throws BusinessException;
}
