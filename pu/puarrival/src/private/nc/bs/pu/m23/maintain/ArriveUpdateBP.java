package nc.bs.pu.m23.maintain;

import nc.bs.pu.m23.maintain.rule.ChkBackArWaitNumRule;
import nc.bs.pu.m23.maintain.rule.ChkBackFor23Rule;
import nc.bs.pu.m23.maintain.rule.ChkBillCodeUniqueRule;
import nc.bs.pu.m23.maintain.rule.ChkCheckNumOnBackRule;
import nc.bs.pu.m23.maintain.rule.ChkMrlInStockOrg;
import nc.bs.pu.m23.maintain.rule.DealFreeChkItemRule;
import nc.bs.pu.m23.maintain.rule.DealLocalMnyRule;
import nc.bs.pu.m23.maintain.rule.ValiVOBeforSaveRule;
import nc.bs.pu.m23.maintain.rule.WriteBackFor23Rule;
import nc.bs.pu.m23.maintain.rule.update.UpdateAfterEventRule;
import nc.bs.pu.m23.maintain.rule.update.UpdateBatchCodeAfterRule;
import nc.bs.pu.m23.maintain.rule.update.UpdateBatchCodeBeforeRule;
import nc.bs.pu.m23.maintain.rule.update.UpdateBeforeEventRule;
import nc.bs.pu.m23.maintain.rule.update.UpdateBillCodeRule;
import nc.bs.pu.m23.maintain.rule.update.UpdateFillDataRule;
import nc.bs.pu.m23.maintain.rule.update.Write21WhenUpdateRule;
import nc.bs.pu.m23.maintain.rule.update.Write61WhenUpdateRule;
import nc.bs.pu.m23.plugin.ArriveBPPlugInPoint;
import nc.impl.pubapp.bd.userdef.UserDefSaveRule;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.env.ArrivalUIToBSEnv;
import nc.vo.pu.m23.rule.ArriveATPUpdateRule;
import nc.vo.pu.pub.enumeration.EnumOperate;
import nc.vo.pu.pub.rule.NewestOrgVersionFillRule;
import nc.vo.pu.pub.rule.TrantypeNotNullCheckRule;
import nc.vo.pu.pub.rule.pf.NoPassUpdateRule;

/**
 * ������������������޸ı��浽����
 * <p>
 * <b>����˵��</b>
 * 
 * @param aggVO���� <p>
 * @since 6.0
 * @author hanbin
 * @time 2010-1-13 ����10:18:08
 */
public class ArriveUpdateBP {
  private ArrivalUIToBSEnv env;

  public ArriveUpdateBP(ArrivalUIToBSEnv env) {
    this.env = env;
  }

  /**
   * ������������������޸ı��浽����
   * <p>
   * <b>����˵��</b>
   * 
   * @param aggVO���� <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-13 ����10:18:08
   */
  public ArriveVO[] updateArrive(ArriveVO[] aggVO, ArriveVO[] originBills) {
    CompareAroundProcesser<ArriveVO> processer =
        new CompareAroundProcesser<ArriveVO>(ArriveBPPlugInPoint.ArriveUpdateBP);

    // ���BP����
    this.addBeforeRule(processer);
    this.addAfterRule(processer);
    processer.before(aggVO, originBills);
    BillUpdate<ArriveVO> bo = new BillUpdate<ArriveVO>();
    ArriveVO[] vos = bo.update(aggVO, originBills);
    processer.after(aggVO, originBills);
    return vos;
  }

  private void addAfterRule(CompareAroundProcesser<ArriveVO> processer) {
    // ��鵥�ݺŵ�Ψһ��
    processer.addAfterRule(new ChkBillCodeUniqueRule());
    // �޸ı�������κŴ���
    processer.addAfterRule(new UpdateBatchCodeAfterRule());
    // ��д��Դ���������ۼ��˻�������
    processer.addAfterRule(new WriteBackFor23Rule(EnumOperate.MODIFY));
    // ��д�ɹ��������ۼƵ���������;������
    processer.addAfterRule(new Write21WhenUpdateRule(this.env));
    // ��дί�ⶩ�����ۼƵ���������;������
    processer.addAfterRule(new Write61WhenUpdateRule(this.env));
    // �˻����������������
    processer.addAfterRule(new ChkBackArWaitNumRule());
    // ��������������
    processer.addAfterRule(new ArriveATPUpdateRule(false));
    // �������º��¼�
    processer.addAfterRule(new UpdateAfterEventRule());
  }

  private void addBeforeRule(CompareAroundProcesser<ArriveVO> processer) {
    // �ڻ��ڵ������˻�ʱ����鱨�졢���鱨��
    processer.addBeforeRule(new ChkCheckNumOnBackRule(this.env));
    // ��齻�������Ƿ�Ϊ��
    processer.addBeforeFinalRule(new TrantypeNotNullCheckRule<ArriveVO>());
    // ��������֯���°�
    processer.addBeforeFinalRule(new NewestOrgVersionFillRule<ArriveVO>(
        ArriveHeaderVO.PK_ARRIVEORDER));
    // �����
    processer.addBeforeRule(new UpdateFillDataRule());
    // ������ͨ��ʱ�޸ĺ��״̬���������������ڵȴ���
    processer.addBeforeFinalRule(new NoPassUpdateRule<ArriveVO>());
    // ����ǰ���VO
    processer.addBeforeRule(new ValiVOBeforSaveRule());
    // �����ҽ��
    processer.addBeforeRule(new DealLocalMnyRule());
    // �������������֯�Ƿ�ƥ��
    processer.addBeforeRule(new ChkMrlInStockOrg());
    // �������������
    processer.addBeforeRule(new DealFreeChkItemRule());
    // �޸ı���ǰ���κŴ���
    processer.addBeforeRule(new UpdateBatchCodeBeforeRule());
    // ���ݺŴ���
    processer.addBeforeRule(new UpdateBillCodeRule());
    // ���ڵ������˻�����ؼ�顣
    processer.addBeforeRule(new ChkBackFor23Rule());
    // �Զ�����У��
    processer.addBeforeRule(new UserDefSaveRule<ArriveVO>(new Class[] {
      ArriveHeaderVO.class, ArriveItemVO.class
    }));
    // ���������ǰ����
    processer.addBeforeRule(new ArriveATPUpdateRule(true));
    // ��������ǰ�¼�
    processer.addAfterRule(new UpdateBeforeEventRule());
  }
}
