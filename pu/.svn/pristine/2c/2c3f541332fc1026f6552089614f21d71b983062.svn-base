package nc.vo.pu.m21.transtype;

import java.util.List;
import java.util.Map;

import nc.vo.pu.m21.enumeration.SourceBillEnum;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.scmf.pu.ordertranstype.entity.OrderTranstypeQueryVO;

public interface IOrderTranstypeStrategy {
  Map<String, List<AggregatedValueObject>> getSplitVOMap(
      AggregatedValueObject[] vos, String[] ctrantypeids);

  OrderTranstypeQueryVO[] getTranstypeQueryVO(AggregatedValueObject[] vos,
      SourceBillEnum sourceEnum);
}
