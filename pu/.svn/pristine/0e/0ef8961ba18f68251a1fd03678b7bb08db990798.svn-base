package nc.bs.pu.m23.writeback.ic.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m23.entity.ArriveViewVO;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 
 * @description
 * 回写到货单的上游单据(采购订单)的累计入库数量
 * @scene
 * 采购入库单回写
 * @param
 * 无
 *
 * @since 6.3
 * @version 2010-1-11 下午06:43:36
 * @author hanbin
 */

public class ChkBCreateFaCardRule implements IRule<ArriveViewVO> {

  @Override
  public void process(ArriveViewVO[] vos) {
    if ((vos == null) || (vos.length == 0)) {
      return;
    }
    for (ArriveViewVO view : vos) {
      boolean bfaflag = ValueUtils.getBoolean(view.getBVO().getBfaflag());
      if (bfaflag) {
        String msg = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0094")/*@res "存在到货单行已经生成资产卡片，不允许再进行入库！"*/;
        ExceptionUtils.wrappBusinessException(msg);
      }
    }
  }
}