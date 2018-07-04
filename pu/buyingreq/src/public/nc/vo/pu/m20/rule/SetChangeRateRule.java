package nc.vo.pu.m20.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.util.BillHelper;

import org.apache.commons.lang.ArrayUtils;

/**
 * 设置换算率和是否固定换算率
 * 
 * @since 6.5
 * @version 2014-1-20 下午03:14:53
 * @author fanly3
 */
public class SetChangeRateRule implements IRule<PraybillVO> {

  @Override
  public void process(PraybillVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    for (PraybillVO vo : vos) {
      PraybillItemVO[] itemVOs = vo.getBVO();
      int[] rows = new int[itemVOs.length];
      for (int i = 0; i < itemVOs.length; ++i) {
        rows[i] = i;
      }
      BillHelper<PraybillVO> helper = new BillHelper<PraybillVO>(vo);
      new ChangeRateUtil().setChgRateAndFixedFlag(helper, rows);
    }
  }

}
