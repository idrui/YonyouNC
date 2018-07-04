package nc.vo.pu.m21.transtype;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.vo.pu.m21.enumeration.SourceBillEnum;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.scmf.pu.ordertranstype.entity.OrderTranstypeQueryVO;
import nc.vo.scmpub.res.billtype.POBillType;

public class SingleHeadStrategy implements IOrderTranstypeStrategy {

  @Override
  public Map<String, List<AggregatedValueObject>> getSplitVOMap(
      AggregatedValueObject[] vos, String[] ctrantypeids) {
    MapList<String, AggregatedValueObject> returnMapList =
        new MapList<String, AggregatedValueObject>();
    int length = vos.length;
    for (int i = 0; i < length; ++i) {
      String ctrantypeid = ctrantypeids[i];
      if (ctrantypeid == null) {
        ctrantypeid = POBillType.Order.getCode();
      }
      returnMapList.put(ctrantypeid, vos[i]);
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
      CircularlyAccessibleValueObject headvo = vo.getParentVO();
      String pk_purorg = (String) headvo.getAttributeValue(srcPurorgField);

      String ctrantypeid = null;
      if (srcTrantypeidField != null) {
        ctrantypeid = (String) headvo.getAttributeValue(srcTrantypeidField);
      }
      String pk_srcmaterial = null;
      if (srcMaterialoidField != null) {
        pk_srcmaterial = (String) headvo.getAttributeValue(srcMaterialoidField);
      }
      String pk_stockorg = null;
      if (srcStockorgField != null) {
        pk_stockorg = (String) headvo.getAttributeValue(srcStockorgField);
      }
      String pk_marbaseclass = null;
      if (srcMaterialBaseClassField != null) {
        pk_marbaseclass =
            (String) headvo.getAttributeValue(srcMaterialBaseClassField);
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
    return list.toArray(new OrderTranstypeQueryVO[list.size()]);
  }
}
