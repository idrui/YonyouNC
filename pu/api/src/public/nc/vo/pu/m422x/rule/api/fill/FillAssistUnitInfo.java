package nc.vo.pu.m422x.rule.api.fill;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.pub.calculate.PuSimpleCalCondition;
import nc.vo.pu.pub.calculate.PuSimpleCalculateDataSet;
import nc.vo.pu.pub.calculate.PuSimpleCalculator;
import nc.vo.pu.pub.calculate.PuSimpleRelationItems;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.scmpub.fill.billfill.IBillValueFill;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 *            物资需求申请保存时，填充表体单位和换算率
 * @scene
 *      物资需求申请保存
 * @param
 * 
 *
 * @since 6.5
 * @version 2015-11-5 下午8:05:12
 * @author luojw
 */
public class FillAssistUnitInfo implements IBillValueFill{

  private ScaleUtils scale = new ScaleUtils(AppContext.getInstance().getPkGroup());
  
  @Override
  public AbstractBill[] fillValue(AbstractBill[] billVOs)
      throws BusinessException {
    String[] materials = this.getMaterials(billVOs);
    if (!ArrayUtils.isEmpty(materials)) {
      Map<String, String> assistunitMap =
          MaterialPubService.queryPuMeasdocIDByPks(materials);
      this.setAssistunit(billVOs, assistunitMap);
    }
    return billVOs;
  }

  private void setAssistunit(AbstractBill[] billVOs,
      Map<String, String> assistunitMap) {
    for(AbstractBill bill : billVOs){
      BillHelper<AbstractBill> billHelper = new BillHelper<AbstractBill>(bill);
      int row = 0;
      for(ISuperVO itemVO : bill.getChildren(StoreReqAppItemVO.class)){
        Object material = itemVO.getAttributeValue(StoreReqAppItemVO.PK_MATERIAL);
        String assistunit = assistunitMap.get(material);
        Object unit = itemVO.getAttributeValue(StoreReqAppItemVO.CUNITID);
        if(itemVO.getAttributeValue(StoreReqAppItemVO.CASTUNITID) == null){
          if (null == assistunit) {
            assistunit = unit.toString();
          }
          itemVO.setAttributeValue(StoreReqAppItemVO.CASTUNITID, assistunit);
        }
        String changeRate = this.scale.adjustHslScale("1/1");
        if(!assistunit.equals(unit)){
          changeRate =
              MaterialPubService.queryMainMeasRateByMaterialAndMeasdoc(
                  material.toString(), assistunit);
        }
        itemVO.setAttributeValue(StoreReqAppItemVO.VCHANGERATE, changeRate);

        PuSimpleCalculator calculator =
            new PuSimpleCalculator(new PuSimpleCalculateDataSet(billHelper, row++,
                new PuSimpleRelationItems()), this.scale);
        PuSimpleCalCondition condition = new PuSimpleCalCondition();
        // vo计算用固定换算率
        condition.setIsfixedChangeRate(true);
        calculator.calculate(condition, StoreReqAppItemVO.NASTNUM);
      }
    }
  }

  private String[] getMaterials(AbstractBill[] billVOs) {
    Set<String> materialSet = new HashSet<String>();
    for(AbstractBill bill : billVOs){
      for(ISuperVO itemVO : bill.getChildren(StoreReqAppItemVO.class)){
        Object material = itemVO.getAttributeValue(StoreReqAppItemVO.PK_MATERIAL);
        if(material != null){
          materialSet.add(material.toString());
        }
      }
    }
    if (materialSet.size() > 0) {
      return materialSet.toArray(new String[materialSet.size()]);
    }
    return null;
  }

}
