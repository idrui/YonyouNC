package nc.bs.pu.m23.writeback.pu.m21;

import nc.bs.pu.m23.plugin.ArriveBPPlugInPoint;
import nc.bs.pu.m23.writeback.pu.m21.rule.ChkReplNumRule;
import nc.bs.pu.m23.writeback.pu.m21.rule.UpdateViewVOReplNumRule;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubitf.pu.m23.pu.m21.IWriteback23For21Para;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveViewVO;
import nc.vo.pu.m23.utils.ArrivePublicUtil;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>回写到货单的累计补货数量—采购订单
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-13 上午10:47:37
 */
public class Writeback23For21BP {

  /**
   * 方法功能描述：回写到货单的累计补货数量—采购订单
   * <p>
   * <b>参数说明</b>
   * 
   * @param paraArray回写参数数组
   *          <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-13 上午10:18:08
   */
  public void writeback(IWriteback23For21Para[] paraArray) {
    // 查询到货单表体视图VO
    String[] bidArray = ArrivePublicUtil.getWriteParaBidArray(paraArray);
    ViewQuery<ArriveViewVO> queryBO =
        new ViewQuery<ArriveViewVO>(ArriveViewVO.class);
    queryBO.setSharedHead(true);
    ArriveViewVO[] oldViewVOArray = queryBO.query(bidArray);

    // 添加执行业务规则
    AroundProcesser<ArriveViewVO> processer =
        new AroundProcesser<ArriveViewVO>(
            ArriveBPPlugInPoint.Writeback23For21BP);

    this.addAfterRule(processer, paraArray);

    processer.before(oldViewVOArray);

    // 持久化
    String[] names = new String[] {
      ArriveItemVO.NACCUMREPLNUM
    };
    ViewUpdate<ArriveViewVO> bo = new ViewUpdate<ArriveViewVO>();
    oldViewVOArray = bo.update(oldViewVOArray, ArriveItemVO.class, names);

    processer.after(oldViewVOArray);
  }

  private void addAfterRule(AroundProcesser<ArriveViewVO> processer,
      IWriteback23For21Para[] paraArray) {
    // 更新视图VO中的累计补货数量
    processer.addBeforeRule(new UpdateViewVOReplNumRule(paraArray));

    // 本次补货数量存在超出退货单可补货数量范围，规则：补货数量<=退货数量
    processer.addAfterRule(new ChkReplNumRule());
  }
}
