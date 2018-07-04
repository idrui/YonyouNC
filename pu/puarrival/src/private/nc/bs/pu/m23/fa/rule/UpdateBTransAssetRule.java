package nc.bs.pu.m23.fa.rule;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 更新是否转固标志
 * @scene
 * 删除转固单
 * @param
 * 无
 *
 * @since 6.3
 * @version 2010-12-23 下午12:52:06
 * @author wuxla
 */


public class UpdateBTransAssetRule implements IRule<ArriveVO> {
  private UFBoolean btransAsset;

  public UpdateBTransAssetRule(UFBoolean btransAsset) {
    this.btransAsset = btransAsset;
  }

  @Override
  public void process(ArriveVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    List<ArriveItemVO> list = new ArrayList<ArriveItemVO>();
    for (ArriveVO vo : vos) {
      for (ArriveItemVO itemVO : vo.getBVO()) {
        itemVO.setBtransasset(this.btransAsset);
        list.add(itemVO);
      }
    }

    ArriveItemVO[] items = list.toArray(new ArriveItemVO[list.size()]);
    VOUpdate<ArriveItemVO> update = new VOUpdate<ArriveItemVO>();
    update.update(items, new String[] {
      ArriveItemVO.BTRANSASSET
    });
  }

}
