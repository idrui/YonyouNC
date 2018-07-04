package nc.vo.pu.m21.transtype;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.pub.pf.IDestTranstypeProcess;
import nc.pubitf.scmf.pu.ordertranstype.pu.IQueryOrdertranstypeForPu;
import nc.vo.pu.m21.enumeration.SourceBillEnum;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmf.pu.ordertranstype.entity.OrderTranstypeQueryVO;
import nc.vo.scmpub.res.billtype.POBillType;

public class OrderTranstypeProcessImpl implements IDestTranstypeProcess {
  @Override
  public Map<String, List<AggregatedValueObject>> execute(
      AggregatedValueObject[] src_vos, String src_billtype) {
    SourceBillEnum sourceEnum = this.getSourceBillEnumMap().get(src_billtype);
    IOrderTranstypeStrategy strategy =
        OrderTranstypeStrategyFactory.getTranstypeStrategy(src_billtype);
    if (strategy == null) {
      Map<String, List<AggregatedValueObject>> maplist =
          new HashMap<String, List<AggregatedValueObject>>();
      maplist.put(POBillType.Order.getCode(), Arrays.asList(src_vos));
      return maplist;
    }
    OrderTranstypeQueryVO[] queryvos =
        strategy.getTranstypeQueryVO(src_vos, sourceEnum);
    String[] ctrantypeids = this.getTrantypeIds(queryvos);
    return strategy.getSplitVOMap(src_vos, ctrantypeids);
  }

  private Map<String, SourceBillEnum> getSourceBillEnumMap() {
    Map<String, SourceBillEnum> map = new HashMap<String, SourceBillEnum>();
    for (SourceBillEnum sourceEnum : SourceBillEnum.values()) {
      map.put(sourceEnum.getSrcBilltype(), sourceEnum);
    }
    return map;
  }

  private String[] getTrantypeIds(OrderTranstypeQueryVO[] queryvos) {
    IQueryOrdertranstypeForPu service =
        NCLocator.getInstance().lookup(IQueryOrdertranstypeForPu.class);
    try {
      return service.getTrantypeIdByQueryVO(queryvos);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }
}
