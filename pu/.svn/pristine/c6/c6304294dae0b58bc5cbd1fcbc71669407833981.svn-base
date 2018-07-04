/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-6 下午06:54:38
 */
package nc.bs.pu.est.m45;

import java.util.ArrayList;
import java.util.List;

import nc.bs.pu.est.CancelEstBP;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.est.entity.m45.PurchaseInEstHeaderVO;
import nc.vo.pu.m4201.entity.PurchaseinFIHeaderVO;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.ValueUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购入取消暂估的BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-7-6 下午06:54:38
 */
public class PurchsInCancelEstBP extends CancelEstBP {

  public PurchsInCancelEstBP(IPluginPoint plugpt, String[] updateItems,
      Class<? extends SuperVO> fdClazz) {
    super(plugpt, updateItems, fdClazz);
  }

  @Override
  protected void updateHeads(GoodsEstVO[] heads) {
    super.updateHeads(heads);
    List<PurchaseInEstHeaderVO> updateHeads =
        new ArrayList<PurchaseInEstHeaderVO>();
    for (GoodsEstVO head : heads) {
      UFBoolean autofi = ((PurchaseInEstHeaderVO) head).getBautotofi();
      // 非自动暂估，则不处理
      if (!ValueUtils.getBoolean(autofi)) {
        continue;
      }
      ((PurchaseInEstHeaderVO) head).setBautotofi(UFBoolean.FALSE);
      updateHeads.add((PurchaseInEstHeaderVO) head);
    }
    if (0 == updateHeads.size()) {
      return;
    }
    PurchaseInEstHeaderVO[] hs =
        updateHeads.toArray(new PurchaseInEstHeaderVO[updateHeads.size()]);
    ViewUpdate<PurchaseInEstHeaderVO> updater =
        new ViewUpdate<PurchaseInEstHeaderVO>();
    updater.update(hs, PurchaseinFIHeaderVO.class, new String[] {
      PurchaseinFIHeaderVO.BAUTOTOFI
    });
  }

}
