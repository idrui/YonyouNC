package nc.bs.pu.m23.writeback.ic.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m23.entity.ArriveViewVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>回写到货单子子表的累计入库数量后，进行入库数量容差检查
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-11 下午06:43:36
 */
public class ChkBBStoreNumRule implements IRule<ArriveViewVO> {

  @Override
  public void process(ArriveViewVO[] vos) {
    // TODO hanbin 补充规则

  }
}
