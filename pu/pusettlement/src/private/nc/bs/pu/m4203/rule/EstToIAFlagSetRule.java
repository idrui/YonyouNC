package nc.bs.pu.m4203.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pu.m4201.enumeration.EnumToIAFlag;
import nc.vo.pu.m4203.entity.SubcontinFIItemVO;
import nc.vo.pu.m4203.entity.SubcontinFIVO;
import nc.vo.pub.lang.UFBoolean;

/**
 * 委托加工入的副本传成本标志设置规则<br>
 * 请加工是否影响成本的标志已经填好之后<br>
 * 此标志只可能是nottoia或estimatetoia，如果影响成本则为estimatetoia，否则为nottoia
 * 
 * @since 6.0
 * @version 2011-1-20 下午04:03:26
 * @author zhaoyha
 */
public class EstToIAFlagSetRule implements IRule<SubcontinFIVO> {

  @Override
  public void process(SubcontinFIVO[] vos) {
    boolean ia = SysInitGroupQuery.isIAEnabled();

    for (SubcontinFIVO vo : vos) {
      for (SubcontinFIItemVO item : vo.getChildrenVO()) {
        if (UFBoolean.TRUE.equals(item.getBaffectcost()) && ia) {
          item.setFtoiaflag(EnumToIAFlag.EstimateToIA.toInteger());
        }
        else {
          item.setFtoiaflag(EnumToIAFlag.NotToIA.toInteger());
        }
      }
    }
  }
}
