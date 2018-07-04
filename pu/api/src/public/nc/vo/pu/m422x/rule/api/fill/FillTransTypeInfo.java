

package nc.vo.pu.m422x.rule.api.fill;

import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.scmpub.fill.billfill.TrantypeIDBillFill;

/**
 * 
 * @description
 *            物资需求申请保存时，填充表头交易类型
 * @scene
 *      物资需求申请保存
 * @param
 * 
 *
 * @since 6.5
 * @version 2015-11-5 下午8:05:12
 * @author luojw
 */
public class FillTransTypeInfo extends TrantypeIDBillFill{

  @Override
  public String getTranTypeIDFieldName() {
    return StoreReqAppHeaderVO.CTRANTYPEID;
  }

  @Override
  public String getTransTypeCodeFieldName() {
    return StoreReqAppHeaderVO.VTRANTYPECODE;
  }


}
