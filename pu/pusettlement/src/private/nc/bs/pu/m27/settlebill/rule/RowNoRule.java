package nc.bs.pu.m27.settlebill.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pubapp.util.VORowNoUtils;

/**
 * 
 * @description
 * 结算单补全行号
 * @scene
 * 费用结算,结算完毕保存结算单
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午4:12:34
 * @author zhangshqb
 */
public class RowNoRule implements IRule<SettleBillVO> {

  @Override
  public void process(SettleBillVO[] vos) {
  	/*
  	 * add by wandl
  	 * UAP 填充行号工具类有问题，暂时自己处理
  	 */
  	for (SettleBillVO vo : vos) {
      SettleBillItemVO[] items = vo.getChildrenVO();

      for (int i = 0, len = items.length; i < len; i++) {
        items[i].setCrowno("" + (i + 1) * 10);
      }
    }
    //VORowNoUtils.setVOsRowNoByRule(vos, SettleBillItemVO.CROWNO);
  }

}
