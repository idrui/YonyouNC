package nc.bs.pu.m20.rewrite.it;

import nc.bs.pu.m20.plugin.PraybillPluginPoint;
import nc.bs.pu.m20.rewrite.pu.rule.AccOrderNumCalcRule;
import nc.bs.pu.m20.rewrite.pu.rule.PrayOrderWriteBackTolerRule;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillViewVO;
import nc.vo.pu.m20.entity.writeback.OrderWriteBackVO;
import nc.vo.pu.m20.pub.PraybillVOUtil;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.ArrayUtils;

/**
 * 进口合同回写请购单BP
 * 
 * @since 6.3.1
 * @version 2013-6-25上午10:26:48
 * @author fanly3
 */
public class ReWrite20For5801BP {
  /**
   * 回写请购单"累计订货数量"
   * 
   * @param backVos 回写VOS
   */
  public void backWriteNum(OrderWriteBackVO[] backVos) {
    // 判断进口管理模块是否启用
    if (!SysInitGroupQuery.isITEnabled() || ArrayUtils.isEmpty(backVos)) {
      return;
    }

    String[] bids = PraybillVOUtil.getInstance().getBidsFromWBVos(backVos);
    // 查询更新前的请购单数据
    PraybillViewVO[] views =
        new ViewQuery<PraybillViewVO>(PraybillViewVO.class).query(bids);
    if (ArrayUtils.isEmpty(views)) {
      return;
    }
    AroundProcesser<PraybillViewVO> processer =
        new AroundProcesser<PraybillViewVO>(PraybillPluginPoint.ITREWRITENUM);

    this.addRule(processer, backVos, views);
    processer.before(views);
    String[] wbNames = new String[] {
      PraybillItemVO.NACCUMULATENUM
    };
    ViewUpdate<PraybillViewVO> bo = new ViewUpdate<PraybillViewVO>();
    views = bo.update(views, PraybillItemVO.class, wbNames);
    processer.after(views);
  }

  private void addRule(AroundProcesser<PraybillViewVO> processer,
      OrderWriteBackVO[] vos, PraybillViewVO[] views) {
    processer.addBeforeRule(new AccOrderNumCalcRule(vos));
    // 容差处理
    String pk_org = views[0].getPk_purchaseorg();
    processer.addAfterRule(new PrayOrderWriteBackTolerRule(
        MaterialVO.INTOLERANCE, PUSysParamUtil.getPO07(pk_org),
        PraybillItemVO.NACCUMULATENUM,
        UFBoolean.valueOf(vos[0].isUserConfirm())));
  }
}
