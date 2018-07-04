package nc.bs.pu.m422x.maintain.rule.save;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pu.pub.util.PUBillCodeUtils;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ObjectUtils;

/**
 * 
 * @description
 *            物资需求申请单保存时，单据号唯一性校验
 * @scene
 *       物资需求申请单保存
 * @param
 * 
 *
 * @since 6.0
 * @version 2010-7-26 上午09:57:28
 * @author wuxla
 */
public class BillCodeUniqueRule implements ICompareRule<StoreReqAppVO> {

  @Override
  public void process(StoreReqAppVO[] vos, StoreReqAppVO[] originVOs) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    // 新增时检查
    if (ArrayUtils.isEmpty(originVOs)) {
      this.uniqueCheck(vos);
    }
    else {
      // 修改检查
      // 只有前后单据号不一致时才检查
      List<StoreReqAppVO> list = new ArrayList<StoreReqAppVO>();
      for (int i = 0; i < vos.length; ++i) {
        if (!ObjectUtils.equals(vos[i].getHVO().getVbillcode(), originVOs[i]
            .getHVO().getVbillcode())) {
          list.add(vos[i]);
        }
      }

      if (list.size() > 0) {
        this.uniqueCheck(list.toArray(new StoreReqAppVO[list.size()]));
      }
    }
  }

  private void uniqueCheck(StoreReqAppVO[] vos) {
    PUBillCodeUtils.getStorereqCode().checkUnique(vos);
  }

}
