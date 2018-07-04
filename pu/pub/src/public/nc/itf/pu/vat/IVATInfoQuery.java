package nc.itf.pu.vat;

import nc.itf.scmpub.reference.uap.bd.vat.CustSuppVATCodeQueryVO;
import nc.itf.scmpub.reference.uap.bd.vat.OppTaxFlagQueryVO;
import nc.itf.scmpub.reference.uap.bd.vat.OrgVATCodeQueryVO;
import nc.itf.scmpub.reference.uap.bd.vat.VATInfoByTaxcodeQueryVO;
import nc.itf.scmpub.reference.uap.bd.vat.VATInfoQueryVO;
import nc.itf.scmpub.reference.uap.bd.vat.VATInfoVO;
import nc.vo.pub.BusinessException;

/**
 * VAT信息查询接口
 * 
 * @since 6.0
 * @version 2012-2-15 上午09:28:37
 * @author wuxla
 */
public interface IVATInfoQuery {

  /**
   * 查询税码和税率等相关信息
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   * 
   * @param queryVOs VAT信息查询参数VO数组
   * @return 与参数一一对应的VAT税率信息
   * @throws BusinessException
   */
  VATInfoVO[] queryTaxInfo(VATInfoQueryVO[] queryVOs) throws BusinessException;

  /**
   * 查询税码、税率等相关信息以及逆向征税标志
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   * 
   * @param vatqueryVOs VAT信息查询参数VO数组
   * @param opptaxqueryvos 逆向征税标志查询参数VO数组
   * @return
   * @throws BusinessException
   */
  VATInfo queryTaxInfoAndOppTaxFlag(VATInfoQueryVO[] vatqueryVOs,
      OppTaxFlagQueryVO[] opptaxqueryvos,
      CustSuppVATCodeQueryVO[] custSuppVATCodeQueryVOs,
      OrgVATCodeQueryVO[] orgVATCodeQueryVOs) throws BusinessException;

  /**
   * 根据税码、业务日期查询VAT税率信息参数VO数组
   * <p>
   * 使用场景：编辑税码后
   * <ul>
   * <li>
   * </ul>
   * 
   * @param queryVOs
   * @return
   * @throws BusinessException
   */
  VATInfoVO[] queryTaxInfoByTaxcode(VATInfoByTaxcodeQueryVO[] queryVOs)
      throws BusinessException;
}
