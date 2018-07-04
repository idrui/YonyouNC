/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-24 下午03:19:08
 */
package nc.bs.pu.est.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pu.est.entity.EstVO;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.m4201.enumeration.EnumToAPFlag;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;
import nc.vo.pubapp.pattern.pub.MapList;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>货物暂估应付规则(顶层规则)
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-24 下午03:19:08
 */
public abstract class AbstractGoodsEstTOAPRule {

  public void process(EstVO[] vos) {

    if (!SysInitGroupQuery.isAPEnabled()) {
      return;
    }

    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    MapList<String, GoodsEstVO> orgMap = this.splitOrgMap(vos);
    List<GoodsEstVO> estList = new ArrayList<GoodsEstVO>();
    for (Entry<String, List<GoodsEstVO>> entry : orgMap.toMap().entrySet()) {
      String pk_fiorg = entry.getKey();
      List<GoodsEstVO> heads = entry.getValue();
      UFBoolean estAP = PUSysParamUtil.getPO52(pk_fiorg);
      if (ValueUtils.getBoolean(estAP)) {
        this.fillToAPFlag(heads);
        estList.addAll(heads);
      }
      else {
        this.fillNotToAPFlag(heads);
      }
    }
    if (0 == estList.size()) {
      return;
    }
    GoodsEstVO[] gevos =
        new ListToArrayTool<GoodsEstVO>().convertToArray(estList);
    this.estTOAP(gevos);
  }

  private void fillNotToAPFlag(List<GoodsEstVO> heads) {
    for (GoodsEstVO head : heads) {
      head.setFtoapflag((Integer) EnumToAPFlag.NotToAP.value());
    }
  }

  private void fillToAPFlag(List<GoodsEstVO> heads) {
    for (GoodsEstVO head : heads) {
      head.setFtoapflag((Integer) EnumToAPFlag.EstimateToAP.value());
    }
  }

  private MapList<String, GoodsEstVO> splitOrgMap(EstVO[] vos) {
    MapList<String, GoodsEstVO> orgMap = new MapList<String, GoodsEstVO>();
    for (EstVO vo : vos) {
      orgMap.put(vo.getParentVO().getPk_financeorg(), vo.getParentVO());
    }
    return orgMap;
  }

  /**
   * 方法功能描述：实现正确的暂估应付。
   * <p>
   * <b>参数说明</b>
   * 
   * @param gevos
   *          需要暂估应付的货物暂估VO数组
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-8-18 下午03:00:25
   */
  abstract protected void estTOAP(GoodsEstVO[] gevos);

}
