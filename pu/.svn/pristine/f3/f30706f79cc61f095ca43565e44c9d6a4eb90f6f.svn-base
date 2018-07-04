package nc.bs.pu.m23.fa.rule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IFilterRule;
import nc.itf.pu.reference.ic.M45PUServices;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 到货单若已生成入库单时，则要求对应的入库单行未转固定资产
 * @scene
 * 
 * @param
 * 无
 *
 * @since 6.3
 * @version 2010-12-23 下午12:40:39
 * @author wuxla
 */

public class FilterByICRule implements IFilterRule<ArriveVO> {

  @Override
  public ArriveVO[] process(ArriveVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }
    Set<String> bidSet = new HashSet<String>();
    for (ArriveVO vo : vos) {
      for (ArriveItemVO itemVO : vo.getBVO()) {
        bidSet.add(itemVO.getPk_arriveorder_b());
      }
    }

    String[] bids = bidSet.toArray(new String[bidSet.size()]);
    // 查询到货单对应的入库单是否已经转固
    Map<String, UFBoolean> fixedAssetMap =
        M45PUServices.queryIsFixedAssetFor23(bids);

    List<ArriveVO> voList = new ArrayList<ArriveVO>();
    StringBuffer assetcrown = new StringBuffer();
    for (ArriveVO vo : vos) {
      List<ArriveItemVO> itemList = new ArrayList<ArriveItemVO>();

      for (ArriveItemVO itemVO : vo.getBVO()) {
        if (UFBoolean.TRUE.equals(fixedAssetMap.get(itemVO
            .getPk_arriveorder_b()))) {
          assetcrown.append("[" + itemVO.getCrowno() + "]");
          continue;
        }
        itemList.add(itemVO);

      }
      if (itemList.size() == 0 && assetcrown.length() > 0) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4004040_0", "04004040-0171", null, new String[] {
              assetcrown.toString()
            })/*无符合条件可转固定资产的到货单行记录 第{0}行到货单记录已经生成转固单 */);
      }
      if (itemList.size() > 0) {
        ArriveItemVO[] itemVOs =
            itemList.toArray(new ArriveItemVO[itemList.size()]);
        vo.setBVO(itemVOs);
        voList.add(vo);
      }
    }

    if (voList.size() > 0) {
      return voList.toArray(new ArriveVO[voList.size()]);
    }
    return null;
  }
}
