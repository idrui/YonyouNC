package nc.bs.pu.m422x.rewrite;

import java.util.ArrayList;
import java.util.List;

import nc.bs.pu.m422x.rewrite.rule.AccBuyingReqNumCalcRule;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppViewVO;
import nc.vo.pu.m422x.entity.WriteBack422XVO;

import org.apache.commons.lang.ArrayUtils;

public class ReWrite422XFor20BP {

  public void backWriteNum(WriteBack422XVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    String[] bids = this.getBidsFromWBVos(vos);
    // 查询更新前的物资需求申请数据
    StoreReqAppViewVO[] views =
        new ViewQuery<StoreReqAppViewVO>(StoreReqAppViewVO.class).query(bids);

    AroundProcesser<StoreReqAppViewVO> processer =
        new AroundProcesser<StoreReqAppViewVO>(null);
    this.addRule(processer, vos);
    processer.before(views);
    String[] wbNames = new String[] {
      StoreReqAppItemVO.NACCUMBUYREQNUM
    };
    ViewUpdate<StoreReqAppViewVO> bo = new ViewUpdate<StoreReqAppViewVO>();
    views = bo.update(views, StoreReqAppItemVO.class, wbNames);
    processer.after(views);
  }

  private void addRule(AroundProcesser<StoreReqAppViewVO> processer,
      WriteBack422XVO[] vos) {
    // 累计数量计算
    processer.addBeforeRule(new AccBuyingReqNumCalcRule(vos));
    // 累计数量容差校验
    // processer.addBeforeRule(new AccuOutreqNumChkRule());
  }

  private String[] getBidsFromWBVos(WriteBack422XVO[] vos) {
    List<String> retVal = new ArrayList<String>();
    for (WriteBack422XVO vo : vos) {
      retVal.add(vo.getPk_storereq_b());
    }
    return retVal.toArray(new String[retVal.size()]);
  }
}
