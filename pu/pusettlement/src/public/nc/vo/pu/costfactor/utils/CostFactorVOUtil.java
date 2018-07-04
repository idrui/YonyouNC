package nc.vo.pu.costfactor.utils;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pu.costfactor.entity.CostfactorItemVO;
import nc.vo.pu.costfactor.entity.CostfactorVO;

import org.apache.commons.lang.ArrayUtils;

public class CostFactorVOUtil {

  /**
   * 过滤表体为空的成本要素。
   * 按需求意见成本要素表体可能为空，这部分数据不应该查询出来用。
   * 需要注意一点，成本要素自身的查询不需要过滤，提供给结算、暂估等的服务要过滤
   * 
   * @param vos 参考成本vo数组
   * @return 过滤后的参考成本vo
   */
  public static CostfactorVO[] filterVOs(CostfactorVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return vos;
    }
    List<CostfactorVO> filterVOs = new ArrayList<CostfactorVO>();
    for (CostfactorVO vo : vos) {
      if (ArrayUtils.isEmpty(vo.getChildrenVO())) {
        continue;
      }
      filterVOs.add(vo);
    }
    if (filterVOs.size() == 0) {
      return null;
    }
    return filterVOs.toArray(new CostfactorVO[filterVOs.size()]);

  }

  /**
   * 返回某物料在成本要素中的序号
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param factorVOs
   * @param pk_srcmaterial
   * @return 如果在成本要素中，返回该序号；否则返回－1。序号从0开始。
   *         <p>
   * @author wangyf
   * @time 2010-3-11 下午12:52:18
   */
  public static int getSequence(CostfactorVO[] factorVOs, String sPk_srcmaterial) {
    if (factorVOs == null) {
      return -1;
    }

    for (int i = 0; i < factorVOs.length; i++) {
      for (CostfactorItemVO item : factorVOs[i].getChildrenVO()) {
        if (item.getPk_srcmaterial().equals(sPk_srcmaterial)) {
          return factorVOs[i].getParentVO().getIfactororder().intValue() - 1;
        }
      }
    }

    return -1;
  }
}
