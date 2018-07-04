package nc.vo.pu.m422x.rule.api.fill;

import java.util.HashMap;
import java.util.Map;

import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.scmpub.fill.billfill.IBillValueFill;

/**
 * 
 * @description
 *             物资需求申请保存时，如果表头有值，则填充表体
 * @scene
 *       物资需求申请保存
 * @param
 * 
 *
 * @since 6.5
 * @version 2015-10-31 下午3:48:46
 * @author luojw
 */
public class FillDefaultVOInfo implements IBillValueFill{
  
  MapList<String, String> mapList;

	@Override
	public AbstractBill[] fillValue(AbstractBill[] billVOs)
			throws BusinessException {
    for(AbstractBill bill : billVOs){
      ISuperVO headVO = bill.getParent();
      Map<String, Object> valueMap = new HashMap<>();
      for(String headkey : this.getMapList().keySet()){
        Object headValue = headVO.getAttributeValue(headkey);
        if(headValue != null){
          valueMap.put(headkey, headValue);
        }
      }
      if(valueMap.size() == 0){
        continue;
      }
      ISuperVO[] itemVOs = bill.getChildren(StoreReqAppItemVO.class);
      for(ISuperVO itemVO : itemVOs){
        for(Map.Entry<String, Object> entry : valueMap.entrySet()){
          String headkey = entry.getKey();
          Object headVaule = entry.getValue();
          for(String itemkey : this.getMapList().get(headkey)){
            if(itemVO.getAttributeValue(itemkey) == null){
              itemVO.setAttributeValue(itemkey, headVaule);
            }
          }
        }
      }
    }
    return billVOs;
	}
	
	private MapList<String, String> getMapList(){
	  if(this.mapList == null){
	    this.mapList = new MapList<>();
	    this.mapList.put(StoreReqAppHeaderVO.PK_GROUP, StoreReqAppItemVO.PK_GROUP);
	    this.mapList.put(StoreReqAppHeaderVO.PK_ORG, StoreReqAppItemVO.PK_ORG);
	    this.mapList.put(StoreReqAppHeaderVO.PK_ORG_V, StoreReqAppItemVO.PK_ORG_V);
	    this.mapList.put(StoreReqAppHeaderVO.PK_ORG, StoreReqAppItemVO.PK_REQSTOORG);
	    this.mapList.put(StoreReqAppHeaderVO.PK_ORG_V, StoreReqAppItemVO.PK_REQSTOORG_V);
	    this.mapList.put(StoreReqAppHeaderVO.PK_ORG, StoreReqAppItemVO.PK_NEXTBALANCEORG);
	    this.mapList.put(StoreReqAppHeaderVO.PK_ORG_V, StoreReqAppItemVO.PK_NEXTBALANCEORG_V);
	    this.mapList.put(StoreReqAppHeaderVO.PK_APPDEPTH, StoreReqAppItemVO.PK_APPDEPT);
	    this.mapList.put(StoreReqAppHeaderVO.PK_APPDEPTH_V, StoreReqAppItemVO.PK_APPDEPT_V);
	    this.mapList.put(StoreReqAppHeaderVO.PK_APPPSNH, StoreReqAppItemVO.PK_APPPSN);
	    this.mapList.put(StoreReqAppHeaderVO.PK_PROJECT, StoreReqAppItemVO.CPROJECTID);
	  }
	  return this.mapList;
	}
}
