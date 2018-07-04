/**
 * $文件说明$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-4 上午10:00:49
 */
package nc.bs.pu.m20.rewrite;

import nc.bs.pu.m20.plugin.PraybillPluginPoint;
import nc.bs.pu.m20.rewrite.pu.rule.AccOrderNumCalcRule;
import nc.bs.pu.m20.rewrite.pu.rule.PrayOrderWriteBackTolerRule;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillViewVO;
import nc.vo.pu.m20.entity.writeback.OrderWriteBackVO;
import nc.vo.pu.m20.pub.PraybillVOUtil;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单、委外订单回写共用BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-2-4 上午10:00:49
 */
public class ReWrite20ForOrderBP {

  /**
   * 方法功能描述：回写数量。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   *          <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-18 上午10:57:46
   */
  public void backWriteNum(OrderWriteBackVO[] vos) {

    String[] bids = PraybillVOUtil.getInstance().getBidsFromWBVos(vos);
    // 查询更新前的请购单数据
    PraybillViewVO[] views =
        new ViewQuery<PraybillViewVO>(PraybillViewVO.class).query(bids);
    if (ArrayUtils.isEmpty(views)) {
      return;
    }
    AroundProcesser<PraybillViewVO> processer =
        new AroundProcesser<PraybillViewVO>(PraybillPluginPoint.REWRITENUM);

    this.addRule(processer, vos, views);
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
    String pk_org = views[0].getPk_purchaseorg();
    processer.addBeforeRule(new AccOrderNumCalcRule(vos));
    processer.addAfterRule(new PrayOrderWriteBackTolerRule(
        MaterialVO.INTOLERANCE, PUSysParamUtil.getPO47(pk_org),
        PraybillItemVO.NACCUMULATENUM,
        UFBoolean.valueOf(vos[0].isUserConfirm())));
  }

}
