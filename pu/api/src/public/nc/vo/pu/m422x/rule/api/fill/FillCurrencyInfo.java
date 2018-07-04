package nc.vo.pu.m422x.rule.api.fill;

import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.scmpub.fill.billfill.IBillValueFill;

/**
 * 
 * @description
 *            物资需求申请保存时，填充表体币种
 * @scene
 *      物资需求申请保存
 * @param
 * 
 *
 * @since 6.5
 * @version 2015-11-5 下午8:05:12
 * @author luojw
 */
public class FillCurrencyInfo implements IBillValueFill{

  @Override
  public AbstractBill[] fillValue(AbstractBill[] billVOs)
      throws BusinessException {
    for(AbstractBill bill : billVOs){
      ISuperVO headVO = bill.getParent();
      Object pk_org = headVO.getAttributeValue(StoreReqAppHeaderVO.PK_ORG);
      String ccurrencyid = OrgUnitPubService.queryOrgCurrByPk(pk_org.toString());
      for(ISuperVO itemVO : bill.getChildren(StoreReqAppItemVO.class)){
        itemVO.setAttributeValue(StoreReqAppItemVO.CCURRENCYID, ccurrencyid);
      }
    }
    return billVOs;
  }

}