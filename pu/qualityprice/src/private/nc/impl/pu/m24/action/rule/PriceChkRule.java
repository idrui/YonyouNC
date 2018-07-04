package nc.impl.pu.m24.action.rule;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m24.entity.PricestlItemVO;
import nc.vo.pu.m24.entity.PricestlVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              价格结算单价格检查
 * @scene
 *        价格结算单审核
 * @param 无
 * @since 6.3
 * @version 2014-10-22 下午4:14:55
 * @author luojw
 */
public class PriceChkRule implements IRule<PricestlVO> {

  @Override
  public void process(PricestlVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    StringBuilder sb = new StringBuilder();
    for (PricestlVO vo : vos) {
      this.check(sb, vo);
    }

    if (sb.length() > 0) {
      ExceptionUtils.wrappBusinessException(sb.toString());
    }
  }

  private void check(StringBuilder sb, PricestlVO vo) {
    PricestlItemVO[] itemVOs = vo.getBVO();
    if (ArrayUtils.isEmpty(itemVOs)) {
      return;
    }
    List<String> crownoList = new ArrayList<String>();
    for (PricestlItemVO itemVO : itemVOs) {
      UFDouble ncalqualprice = itemVO.getNcalqualprice();
      if (MathTool.compareTo(ncalqualprice, UFDouble.ZERO_DBL) <= 0) {
        crownoList.add(itemVO.getCrowno());
      }
    }

    if (crownoList.size() == 0) {
      return;
    }

    StringBuilder temp = new StringBuilder();
    temp.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004070_2", "2400407001-0048")/* @res "价格结算单号" */);
    temp.append(vo.getHVO().getVbillcode());

    StringBuilder crowNoBuilder = new StringBuilder();
    for (String str : crownoList) {
      crowNoBuilder.append("[");
      crowNoBuilder.append(str);
      crowNoBuilder.append("]");
    }
    temp.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004070_0", "04004070-0015", null, new String[] {
          crowNoBuilder.toString()
        })/* @res "第{0}行优质优价计算结果单价为负数，请修改优质优价方案。" */);
    sb.append(temp.toString() + "\n");
  }

}
