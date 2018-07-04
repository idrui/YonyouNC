/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-31 下午03:18:12
 */
package nc.bs.pu.est.rule.pricequery;

import java.util.Map;

import nc.impl.pubapp.env.BSContext;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.fi.MaterialFiVO;
import nc.vo.pu.est.rule.IEstPriceQueryInfoProvide;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleObjectFactory;

import org.apache.commons.collections.MapUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>暂估取计划价
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-31 下午03:18:12
 */
public class EstPlanPriceQryStrategy extends AbstractEstPriceQueryStrategy {

  private String[] getMaterial(IEstPriceQueryInfoProvide[] pqinfo) {
    String[] ret = new String[pqinfo.length];
    for (int i = 0; i < ret.length; i++) {
      ret[i] = pqinfo[i].getPk_material();
    }
    return ret;
  }

  @Override
  protected void procResltData(EstPriceQryResltData[] resltData,
      IEstPriceQueryInfoProvide[] pqinfo) {
    String[] mids = this.getMaterial(pqinfo);
    // 前面已经按财务组织分单，这里只取第一条数据的财务组织即可
    String fiOrg = pqinfo[0].getPk_fiorg();
    Map<String, MaterialFiVO> mFiVoMap =
        MaterialPubService.getFIInfo(mids, fiOrg, new String[] {
          MaterialFiVO.PLANPRICE
        });
    if (MapUtils.isEmpty(mFiVoMap)) {
      return;
    }
    for (int i = 0; i < pqinfo.length; i++) {
      IEstPriceQueryInfoProvide info = pqinfo[i];
      String pk_material = info.getPk_material();
      if (!mFiVoMap.containsKey(pk_material)) {
        continue;
      }
      UFDouble price = mFiVoMap.get(pk_material).getPlanprice();
      String pk_group = BSContext.getInstance().getGroupID();
      int digit =
          new ScaleObjectFactory(pk_group).getPriceScaleObject().getDigit(
              info.getorigcurr());
      price = MathTool.nvl(price);
      price = price.add(UFDouble.ZERO_DBL, digit);// 取采购单价精度
      resltData[i].setPrice(price);
    }
  }

}
