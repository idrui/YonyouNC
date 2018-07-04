package nc.bs.pu.m23.writeback.ic.m45;

import java.util.HashSet;
import java.util.Set;

import nc.bs.pu.m23.plugin.ArriveBPPlugInPoint;
import nc.bs.pu.m23.writeback.ic.rule.ChkBCreateFaCardRule;
import nc.bs.pu.m23.writeback.ic.rule.ChkBStoreNumRule;
import nc.bs.pu.m23.writeback.ic.rule.UpdateBBStoreNumRule;
import nc.bs.pu.m23.writeback.ic.rule.UpdateViewStoreNumRule;
import nc.bs.pu.m23.writeback.ic.rule.WriteAccumLetgoInnumRule;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubitf.pu.m23.ic.m45.IWriteback23For45Para;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveViewVO;
import nc.vo.pu.m23.utils.ArrivePublicUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>回写到货单的累计入库数量—采购入库
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-13 上午10:47:37
 */
public class Writeback23For45BP {

  /**
   * 方法功能描述：回写到货单的累计入库数量—采购入库
   * <p>
   * <b>参数说明</b>
   * 
   * @param paraArray回写参数数组
   *          <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-13 上午10:18:08
   */
  public void writeback(IWriteback23For45Para[] paras) {
    if (ArrayUtils.isEmpty(paras)) {
      return;
    }
    // 检查回写到货单的参数
    this.checkWritePara(paras);

    // 查询到货单表体视图VO
    String[] bidArray = ArrivePublicUtil.getWriteParaBidArray(paras);
    ViewQuery<ArriveViewVO> q = new ViewQuery<ArriveViewVO>(ArriveViewVO.class);
    q.setSharedHead(true);
    // 质检报告表体多行可能产生重复数据 ,bidArray排重 modify by wangljc at 2011-11-3 11:25:52
    Set<String> bidSet = new HashSet<String>();
    for (int i = 0; i < bidArray.length; i++) {
      bidSet.add(bidArray[i]);
    }
    ArriveViewVO[] oldViewVOArray =
        q.query(bidSet.toArray(new String[bidSet.size()]));

    // 添加执行业务规则
    ArriveBPPlugInPoint point = ArriveBPPlugInPoint.Writeback23For45BP;
    AroundProcesser<ArriveViewVO> pc = new AroundProcesser<ArriveViewVO>(point);
    this.addBeforeFinalRule(pc, paras);
    this.addAfterRule(pc, paras);

    pc.before(oldViewVOArray);

    // 持久化
    String[] names = new String[3];
    names[0] = ArriveItemVO.NACCUMSTORENUM;
    names[1] = ArriveItemVO.BLETGOSTATE;
    names[2] = ArriveItemVO.NACCUMLETGOINNUM;
    ViewUpdate<ArriveViewVO> bo = new ViewUpdate<ArriveViewVO>();
    oldViewVOArray = bo.update(oldViewVOArray, ArriveItemVO.class, names);

    pc.after(oldViewVOArray);
  }

  private void addAfterRule(AroundProcesser<ArriveViewVO> processer,
      IWriteback23For45Para[] paras) {
    // 货单子表的入库数量容差检查
    processer.addAfterRule(new ChkBStoreNumRule(UFBoolean.valueOf(paras[0]
        .isNumUserConfirm())));
  }

  private void addBeforeFinalRule(AroundProcesser<ArriveViewVO> processer,
      IWriteback23For45Para[] paras) {
    // 检查生成入库单的到货单行是否存在已经生成资产卡片
    processer.addBeforeFinalRule(new ChkBCreateFaCardRule());

    // 更新到货单子子表的累计入库数量
    processer.addBeforeFinalRule(new UpdateBBStoreNumRule(paras));

    // 更新视图VO中的累计入库数量
    processer.addBeforeFinalRule(new UpdateViewStoreNumRule(paras));

    // 回写紧急放行单的累计入库数量
    processer.addBeforeFinalRule(new WriteAccumLetgoInnumRule(paras));

  }

  private void checkWritePara(IWriteback23For45Para[] paras) {
    for (IWriteback23For45Para para : paras) {
      if (StringUtils.isEmpty(para.getHID())) {
        String message =
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
                "04004040-0092")/* @res "回写到货单时参数中的到货单表头主键不允许为空，请检查！" */;
        ExceptionUtils.wrappBusinessException(message);
      }
      if (StringUtils.isEmpty(para.getBID())) {
        String message =
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
                "04004040-0093")/* @res "回写到货单时参数中的到货单表体主键不允许为空，请检查！" */;
        ExceptionUtils.wrappBusinessException(message);
      }
    }
  }
}
