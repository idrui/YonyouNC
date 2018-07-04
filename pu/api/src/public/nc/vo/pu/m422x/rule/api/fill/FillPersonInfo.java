package nc.vo.pu.m422x.rule.api.fill;

import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.scmpub.fill.billfill.IBillValueFill;

/**
 * 
 * @description
 *            物资需求申请保存时，填充单据的日期
 * @scene
 *      物资需求申请保存
 * @param
 * 
 *
 * @since 6.5
 * @version 2015-11-5 下午8:05:12
 * @author luojw
 */
public class FillPersonInfo implements IBillValueFill{
  
  private String[] personFields = new String[]{StoreReqAppHeaderVO.BILLMAKER,
      StoreReqAppHeaderVO.CREATOR};
  
  @Override
  public AbstractBill[] fillValue(AbstractBill[] billVOs)
      throws BusinessException {
    for(AbstractBill bill : billVOs){
      ISuperVO headVO = bill.getParent();
      String person = AppContext.getInstance().getPkUser();
      for(String personField : this.personFields){
        Object date = headVO.getAttributeValue(personField);
        if (date == null) {
          headVO.setAttributeValue(personField, person);
        }
      }
    }
    return billVOs;
  }

}
