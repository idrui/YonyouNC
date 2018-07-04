package nc.bs.pu.m422x.rewrite;

import java.util.HashSet;
import java.util.Set;

import nc.bs.pu.m422x.plugin.StoreReqAppPluginPoint;
import nc.bs.pu.m422x.rewrite.rule.AccuOutreqNumCalcRule;
import nc.bs.pu.m422x.rewrite.rule.AccuOutreqNumChkRule;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppViewVO;
import nc.vo.pu.m422x.entity.WriteBack422XVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * 出库申请单回写BP
 * 
 * @since 6.0
 * @version 2010-12-16 下午02:15:11
 * @author wuxla
 */

public class ReWrite422XFor4455BP {

  public void backWriteNumFor4455(WriteBack422XVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    String[] bids = this.getBidsFromWBVos(vos);
    // 查询更新前的物资需求申请数据
    StoreReqAppViewVO[] views =
        new ViewQuery<StoreReqAppViewVO>(StoreReqAppViewVO.class).query(bids);
    if (ArrayUtils.isEmpty(views)) {
      return;
    }

    AroundProcesser<StoreReqAppViewVO> processer =
        new AroundProcesser<StoreReqAppViewVO>(
            StoreReqAppPluginPoint.WRITEBACK_4455);

    this.addRule(processer, vos);
    processer.before(views);
    String[] wbNames = new String[] {
      StoreReqAppItemVO.NACCUOUTREQNUM, StoreReqAppItemVO.BCLOSE
    };
    ViewUpdate<StoreReqAppViewVO> bo = new ViewUpdate<StoreReqAppViewVO>();
    views = bo.update(views, StoreReqAppItemVO.class, wbNames);
    processer.after(views);
  }

  private void addRule(AroundProcesser<StoreReqAppViewVO> processer,
      WriteBack422XVO[] vos) {
    // 累计数量计算
    processer.addBeforeRule(new AccuOutreqNumCalcRule(vos));
    // 累计数量容差校验
    processer.addBeforeRule(new AccuOutreqNumChkRule());
  }

  private String[] getBidsFromWBVos(WriteBack422XVO[] vos) {
    Set<String> retVal = new HashSet<String>();
    for (WriteBack422XVO vo : vos) {
      retVal.add(vo.getPk_storereq_b());
    }
    return retVal.toArray(new String[retVal.size()]);
  }

}
