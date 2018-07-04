package nc.itf.pu.m21;

import nc.vo.pu.m21.query.currency.CurrencyInfo;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>币种和汇率的查询
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-3-28 上午11:14:09
 */
public interface IOrderCurrencyQuery {
  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @param origCurrencyId 原币币种ID
   * @param date 日期
   * @param currencyInfos 币种和汇率信息（必须携带结算财务组织的ID）
   * @return 补充了组织本位币、折本汇率、集团本位币汇率、组织本位币汇率等信息
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-28 上午11:33:28
   */
  public CurrencyInfo[] queryCurrencyInfo(String origCurrencyId, UFDate date,
      CurrencyInfo[] currencyInfos) throws BusinessException;
}
