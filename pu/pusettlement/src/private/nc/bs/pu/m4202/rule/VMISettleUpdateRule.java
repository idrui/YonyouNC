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
 * ���Ļ��ܽ����Ļ�д
 * @scene
 * ɾ�����㵥,���ý���,������ϱ�����㵥
 * @param
 * wbp ��дʱ���ö���ࣨ����ʱ��ɾ��ʱ��
 *
 * @since 6.3
 * @version 2014-10-22 ����4:09:02
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
