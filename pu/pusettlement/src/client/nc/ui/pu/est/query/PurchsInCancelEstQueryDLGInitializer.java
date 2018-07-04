package nc.ui.pu.est.query;

import java.util.ArrayList;
import java.util.List;

import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.IQueryConditionVODealer;
import nc.vo.pu.est.enumeration.QueryNonMetaFieldCode;
import nc.vo.pub.query.QueryConditionVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * 取消暂估查询初始过滤
 * 
 * @since 6.0
 * @version 2011-1-17 下午03:57:19
 * @author yinfy
 */

public class PurchsInCancelEstQueryDLGInitializer extends
    PurchsInEstQueryDLGInitializer {

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    super.initQueryConditionDLG(condDLGDelegator);
  }

  @Override
  protected void init(QueryConditionDLGDelegator condDLGDelegator) {
    // 去掉查询条件中一些不必要的值
    condDLGDelegator.addQueryCondVODealer(new IQueryConditionVODealer() {
      @Override
      public QueryConditionVO[] deal(QueryConditionVO[] conds) {
        if (ArrayUtils.isEmpty(conds)) {
          return conds;
        }
        List<QueryConditionVO> newConds = new ArrayList<QueryConditionVO>();
        for (QueryConditionVO cond : conds) {
          String fdcode = cond.getFieldCode();
          if (QueryNonMetaFieldCode.festtype.name().equals(fdcode)
              || QueryNonMetaFieldCode.bestfee.name().equals(fdcode)) {
            continue;
          }
          newConds.add(cond);
        }
        return newConds.toArray(new QueryConditionVO[newConds.size()]);
      }
    });
  }
}
