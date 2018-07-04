package nc.vo.pu.m20.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.calculator.HslParseUtil;
import nc.vo.pubapp.scale.ScaleUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 通过辅数量，换算率设置主数量
 * 
 * @since 6.5
 * @version 2014-1-20 下午04:13:16
 * @author fanly3
 */
public class SetNnumRule implements IRule<PraybillVO> {

  @Override
  public void process(PraybillVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    this.setNnum(vos);
  }

  private void setNnum(PraybillVO[] vos) {
    ScaleUtils scale = new ScaleUtils(AppContext.getInstance().getPkGroup());
    for (PraybillVO vo : vos) {
      PraybillItemVO[] items = vo.getBVO();
      if (ArrayUtils.isEmpty(items)) {
        continue;
      }
      for (int i = 0, len = items.length; i < len; i++) {
        UFDouble astnum = items[i].getNastnum();
        if (null == astnum) {
          return;
        }

        String vchangerate = items[i].getVchangerate();
        if (vchangerate == null) {
          vchangerate = scale.adjustHslScale("1/1");
        }

        String cunitid = items[i].getCunitid();
        UFDouble nnum =
            scale.adjustNumScale(
                HslParseUtil.hslMultiplyUFDouble(
                    scale.adjustHslScale(vchangerate), items[i].getNastnum()),
                cunitid);
        items[i].setNnum(nnum);
      }
    }
  }

}
