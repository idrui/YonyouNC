package nc.bs.pu.m4t.maintain.rule.maintain;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.ct.uitl.ArrayUtil;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pu.pub.util.PUBillCodeUtils;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ObjectUtils;

/**
 * 
 * @description
 * 单据号重复检查
 * @scene
 * 期初暂估单保存
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-31 上午10:15:21
 * @author wuxla
 */

public class InitialEstCodeUniqueRule implements ICompareRule<InitialEstVO> {

  @Override
  public void process(InitialEstVO[] vos, InitialEstVO[] originVOs) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    // 新增时检查
    if (ArrayUtil.isEmpty(originVOs)) {
      this.uniqueCheck(vos);
    }
    else {
      // 修改检查
      // 只有前后单据号不一致时才检查
      List<InitialEstVO> list = new ArrayList<InitialEstVO>();
      for (int i = 0; i < vos.length; ++i) {
        if (!ObjectUtils.equals(vos[i].getHeader().getVbillcode(), originVOs[i]
            .getHeader().getVbillcode())) {
          list.add(vos[i]);
        }
      }

      if (list.size() > 0) {
        this.uniqueCheck(list.toArray(new InitialEstVO[list.size()]));
      }
    }
  }

  private void uniqueCheck(InitialEstVO[] vos) {
    PUBillCodeUtils.getInitialEstCode().checkUnique(vos);
  }

}
