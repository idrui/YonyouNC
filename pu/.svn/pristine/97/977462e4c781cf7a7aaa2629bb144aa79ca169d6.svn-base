/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-24 下午03:14:43
 */
package nc.bs.pu.est.rule;

import java.util.ArrayList;
import java.util.List;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pu.est.entity.EstVO;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.m4201.enumeration.EnumToIAFlag;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>货物暂估传成本IA的父类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-24 下午03:14:43
 */
public abstract class AbstractGoodsEstTOIARule {

  public void process(EstVO[] vos) {
    if (!SysInitGroupQuery.isIAEnabled()) {
      return;
    }
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    // 填充传成本标志
    for (EstVO vo : vos) {
      this.fillToIAFlag(vo);
    }
    // 得到传成本的暂估VO
    EstVO[] toIAEstVos = this.getToIAEstVO(vos);
    if (ArrayUtils.isEmpty(toIAEstVos)) {
      return;
    }
    this.sendTOIA(toIAEstVos);// 传存货核算IA
  }

  private void fillToIAFlag(EstVO vo) {
    GoodsEstVO head = vo.getParentVO();
    if (ValueUtils.getBoolean(head.getBaffectcost())) {
      head.setFtoiaflag((Integer) EnumToIAFlag.EstimateToIA.value());
    }
    else {
      head.setFtoiaflag((Integer) EnumToIAFlag.NotToIA.value());
    }
  }

  private EstVO[] getToIAEstVO(EstVO[] vos) {
    List<EstVO> heads = new ArrayList<EstVO>();
    for (int i = 0; i < vos.length; i++) {
      GoodsEstVO head = vos[i].getParentVO();
      if (EnumToIAFlag.EstimateToIA.value().equals(head.getFtoiaflag())) {
        heads.add(vos[i]);
      }
    }
    if (heads.size() == 0) {
      return null;
    }
    return new ListToArrayTool<EstVO>().convertToArray(heads);
  }

  abstract protected void sendTOIA(EstVO[] estVos);
}
