package nc.bs.pu.m4202.rule;

import nc.bs.pu.m27.settlebill.rule.AbstractEstSettleUpdateRule;
import nc.bs.pu.m27.settlebill.rule.WritebackPoint;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.vo.pu.est.entity.m50.VmiEstHeaderVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m4202.entity.VmiFIHeaderVO;

/**
 * 
 * @description
 * 消耗汇总结算后的回写
 * @scene
 * 删除结算单,费用结算,结算完毕保存结算单
 * @param
 * wbp 回写时点的枚举类（保存时、删除时）
 *
 * @since 6.3
 * @version 2014-10-22 下午4:09:02
 * @author zhangshqb
 */
public class VMISettleUpdateRule extends
    AbstractEstSettleUpdateRule<VmiEstHeaderVO> {

  public VMISettleUpdateRule(WritebackPoint wbp) {
    super(wbp, VmiEstHeaderVO.class);
  }

  @Override
  protected String getStockBIDField() {
    return SettleBillItemVO.PK_VOICONSUME_B;
  }

  @Override
  protected void updateDB(VmiEstHeaderVO[] gevos) {

    ViewUpdate<VmiEstHeaderVO> vu = new ViewUpdate<VmiEstHeaderVO>();
    vu.update(gevos, VmiFIHeaderVO.class, new String[] {
      VmiFIHeaderVO.BSETTLEFINISH, VmiFIHeaderVO.NACCUMSETTLENUM,
      VmiFIHeaderVO.NACCESTCOSTWASHMNY, VmiFIHeaderVO.NACCESTCOSTSTTLNUM,
      VmiFIHeaderVO.NACCPREESTSTTLMNY, VmiFIHeaderVO.NACCSETTLEMNY,
      VmiFIHeaderVO.NACCGOODSSETTLEMNY, VmiFIHeaderVO.NACCFEESETTLEMNY
    });
  }
}
