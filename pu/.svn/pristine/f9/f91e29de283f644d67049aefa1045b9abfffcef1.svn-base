package nc.vo.pu.m21.transtype;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.vo.pu.m21.enumeration.SourceBillEnum;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pubapp.pattern.pub.Constructor;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.scmf.pu.ordertranstype.entity.OrderTranstypeQueryVO;
import nc.vo.scmpub.res.billtype.POBillType;

public class PurHeadStockHeadMarHeadStrategy implements IOrderTranstypeStrategy {

  @Override
  public Map<String, List<AggregatedValueObject>> getSplitVOMap(
      AggregatedValueObject[] vos, String[] ctrantypeids) {
    MapList<String, AggregatedValueObject> returnMapList =
        new MapList<String, AggregatedValueObject>();
    int length = vos.length;
    int total = 0;
    for (int i = 0; i < length; ++i) {
      CircularlyAccessibleValueObject[] bodys = vos[i].getChildrenVO();
      int bodysize = bodys.length;
      MapList<String, CircularlyAccessibleValueObject> innerMapList =
          new MapList<String, CircularlyAccessibleValueObject>();
      for (int j = 0; j < bodysize; ++j) {
        String ctrantypeid = ctrantypeids[total];
        if (ctrantypeid == null) {
          ctrantypeid = POBillType.Order.getCode();
        }
        innerMapList.put(ctrantypeid, bodys[j]);
        ++total;
      }
      if (innerMapList.size() == 1) {
        String key = innerMapList.keySet().iterator().next();
        returnMapList.put(key, vos[i]);
      }
      else {
        CircularlyAccessibleValueObject parent = vos[i].getParentVO();
        for (Entry<String, List<CircularlyAccessibleValueObject>> entry : innerMapList
            .entrySet()) {
          String key = entry.getKey();
          List<CircularlyAccessibleValueObject> value = entry.getValue();
          CircularlyAccessibleValueObject[] children =
              ArrayUtil.convertArrayType(value
                  .toArray(new CircularlyAccessibleValueObject[value.size()]));
          AggregatedValueObject newVO =
              Constructor.construct(vos[i].getClass());
          newVO.setParentVO(parent);
          newVO.setChildrenVO(children);
          returnMapList.put(key, newVO);
        }

      }
    }

    return returnMapList.toMap();
  }

  @Override
  public OrderTranstypeQueryVO[] getTranstypeQueryVO(
      AggregatedValueObject[] vos, SourceBillEnum sourceEnum) {
    String srcBilltype = sourceEnum.getSrcBilltype();
    String srcMaterialoidField = sourceEnum.getSrcMaterialoidField();
    String srcPurorgField = sourceEnum.getSrcPurorgField();
    String srcStockorgField = sourceEnum.getSrcStockorgField();
    String srcTrantypeidField = sourceEnum.getSrcTrantypeidField();
    String srcMaterialBaseClassField =
        sourceEnum.getSrcMaterialBaseClassField();
    List<OrderTranstypeQueryVO> list = new ArrayList<OrderTranstypeQueryVO>();
    for (AggregatedValueObject vo : vos) {
      String pk_purorg =
          (String) vo.getParentVO().getAttributeValue(srcPurorgField);
      String ctrantypeid = null;
      if (srcTrantypeidField != null) {
        ctrantypeid =
            (String) vo.getParentVO().getAttributeValue(srcTrantypeidField);
      }
      for (CircularlyAccessibleValueObject bodyvo : vo.getChildrenVO()) {
        String pk_srcmaterial = null;
        if (srcMaterialoidField != null) {
          pk_srcmaterial =
              (String) bodyvo.getAttributeValue(srcMaterialoidField);
        }
        String pk_stockorg = null;
        if (srcStockorgField != null) {
          pk_stockorg = (String) bodyvo.getAttributeValue(srcStockorgField);
        }
        String pk_marbaseclass = null;
        if (srcMaterialBaseClassField != null) {
          pk_marbaseclass =
              (String) bodyvo.getAttributeValue(srcMaterialBaseClassField);
        }
        OrderTranstypeQueryVO queryvo = new OrderTranstypeQueryVO();
        queryvo.setPk_purchaseorg(pk_purorg);
        queryvo.setPk_srcmaterial(pk_srcmaterial);
        queryvo.setPk_stockorg(pk_stockorg);
        queryvo.setSrcbilltype(srcBilltype);
        queryvo.setSrctranstype(ctrantypeid);
        queryvo.setPk_marbaseclass(pk_marbaseclass);
        list.add(queryvo);
      }
    }
    return list.toArray(new OrderTranstypeQueryVO[list.size()]);
  }
}
