package nc.vo.pu.m21.transtype;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pu.m21.enumeration.SourceBillEnum;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.scmf.pu.ordertranstype.entity.OrderTranstypeQueryVO;

public class PurBodyStockBodyMarBodyStrategy extends
    PurHeadStockHeadMarHeadStrategy {

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
        String pk_purorg = (String) bodyvo.getAttributeValue(srcPurorgField);
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
