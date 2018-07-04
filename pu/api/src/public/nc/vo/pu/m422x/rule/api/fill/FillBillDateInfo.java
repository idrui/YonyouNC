package nc.vo.pu.m422x.rule.api.fill;

import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.scmpub.fill.billfill.IBillValueFill;

/**
 * 
 * @description
 *            �����������뱣��ʱ����䵥�ݵ�����
 * @scene
 *      �����������뱣��
 * @param
 * 
 *
 * @since 6.5
 * @version 2015-11-5 ����8:05:12
 * @author luojw
 */
public class FillBillDateInfo implements IBillValueFill{
  
  private String[] dateFields = new String[]{StoreReqAppHeaderVO.DBILLDATE,
      StoreReqAppHeaderVO.CREATIONTIME, StoreReqAppHeaderVO.DMAKEDATE};
  
  @Override
  public AbstractBill[] fillValue(AbstractBill[] billVOs)
      throws BusinessException {
    for(AbstractBill bill : billVOs){
      ISuperVO headVO = bill.getParent();
      UFDate defaultDate = AppContext.getInstance().getBusiDate();
      for(String dateField : this.dateFields){
        Object date = headVO.getAttributeValue(dateField);
        if (date == null) {
          headVO.setAttributeValue(dateField, defaultDate);
        }
      }
      for(ISuperVO itemVO : bill.getChildren(StoreReqAppItemVO.class)){
        itemVO.setAttributeValue(StoreReqAppItemVO.DREQDATE, defaultDate.asLocalEnd());
      }
    }
    return billVOs;
  }

}
