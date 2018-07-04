/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-24 ����04:27:52
 */
package nc.bs.pu.est.m50;

import nc.bs.pu.est.m45.rule.settle.VMISettledFeeChkRule;
import nc.bs.pu.est.m50.rule.VMIEstimatedFeeChkRule;
import nc.bs.pu.est.m50.rule.VMIFeeEstInfoFillRule;
import nc.bs.pu.est.m50.rule.VMIFeeEstTOAPRule;
import nc.bs.pu.est.m50.rule.VMIFeeEstTOIARule;
import nc.bs.pu.est.m50.rule.VMIGoodsEstNecssFillRule;
import nc.bs.pu.est.m50.rule.VMIGoodsEstTOAPRule;
import nc.bs.pu.est.m50.rule.VMIGoodsEstTOIARule;
import nc.bs.pu.est.plugin.VMIEstPluginPoint;
import nc.bs.pu.est.rule.EstCalCostMnyPriceRule;
import nc.bs.pu.est.rule.FeeEstSettleTOIAChkRule;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.data.vo.VOInsert;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pu.est.entity.m50.VmiEstHeaderVO;
import nc.vo.pu.est.entity.m50.VmiEstVO;
import nc.vo.pu.est.rule.m50.VMIFeeEstNotNullChkRule;
import nc.vo.pu.est.rule.m50.VMIGoodsEstNotNullChkRule;
import nc.vo.pu.est.util.EstVOUtil;
import nc.vo.pu.m4202.entity.VmiFIFeeVO;
import nc.vo.pu.m4202.entity.VmiFIHeaderVO;
import nc.vo.pu.pub.util.AggVOUtil;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���Ļ����ݹ�BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-24 ����04:27:52
 */
public class VMIEstBP {

  /** ��ⵥ�ֹ��ݹ� **/
  public void estimate(VmiEstVO[] vos) {
    AroundProcesser<VmiEstVO> prcr =
        new AroundProcesser<VmiEstVO>(VMIEstPluginPoint.EST_BP);
    this.addRule(prcr);
    prcr.before(vos);
    ViewUpdate<VmiEstHeaderVO> vusrv = new ViewUpdate<VmiEstHeaderVO>();
    vusrv.update(this.getHeaders(vos), VmiFIHeaderVO.class,
        EstVOUtil.getGoodsEstBUpdate());
    VmiFIFeeVO[] items = this.getItems(vos);
    if (!ArrayUtils.isEmpty(items)) {
      VOInsert<VmiFIFeeVO> voinsert = new VOInsert<VmiFIFeeVO>();
      voinsert.insert(items);
    }
    prcr.after(vos);
  }

  /** �����ݹ� **/
  public void feeEstimate(VmiEstVO[] vos, VmiEstVO[] origVos) {
    CompareAroundProcesser<VmiEstVO> prcr =
        new CompareAroundProcesser<VmiEstVO>(VMIEstPluginPoint.FEE_EST_BP);
    this.addFeeEstRule(prcr);
    prcr.before(vos, origVos);
    VmiFIFeeVO[] items = this.getItems(vos);
    VOInsert<VmiFIFeeVO> voinsert = new VOInsert<VmiFIFeeVO>();
    voinsert.insert(items);
    prcr.after(vos, origVos);
  }

  private void addFeeEstRule(CompareAroundProcesser<VmiEstVO> prcr) {
    prcr.addBeforeFinalRule(new VMIFeeEstNotNullChkRule());// ���ݹ����õķǿռ��
    prcr.addBeforeFinalRule(new VMIEstimatedFeeChkRule());// ����Ƿ��Ѿ��з�������й��ݹ�
    prcr.addBeforeFinalRule(new VMISettledFeeChkRule());// ����Ƿ��Ѿ��з�������й�����
    prcr.addBeforeFinalRule(new VMIFeeEstInfoFillRule());// �����ݹ�������Ϣ����
    prcr.addBeforeFinalRule(new FeeEstSettleTOIAChkRule<VmiEstVO>());// ����������Ƿ��Ѿ����ɱ�
    prcr.addAfterFinalRule(new VMIFeeEstTOIARule());// �����ݹ��ɱ���IA
    prcr.addAfterFinalRule(new VMIFeeEstTOAPRule());// �����ݹ�Ӧ����AP
  }

  private void addRule(AroundProcesser<VmiEstVO> prcr) {
    prcr.addBeforeFinalRule(new VMIGoodsEstNotNullChkRule());// ���ݹ�����ķǿ�����
    prcr.addBeforeFinalRule(new VMIFeeEstNotNullChkRule());// ���ݹ����õķǿռ��
    prcr.addBeforeFinalRule(new VMISettledFeeChkRule());// ����Ƿ��Ѿ��з�������й�����
    prcr.addBeforeFinalRule(new VMIGoodsEstNecssFillRule());// �ݹ�ǰ������Ϣ���
    prcr.addBeforeFinalRule(new VMIFeeEstInfoFillRule());// �����ݹ�������Ϣ����

    // wuxla v61 �ǳɱ���������
    prcr.addBeforeFinalRule(new EstCalCostMnyPriceRule<VmiEstVO>());

    prcr.addBeforeFinalRule(new VMIGoodsEstTOIARule());// �����ݹ��ɱ���IA
    prcr.addBeforeFinalRule(new VMIGoodsEstTOAPRule());// �ݹ�Ӧ����AP
    prcr.addAfterFinalRule(new VMIFeeEstTOIARule());// �����ݹ��ɱ���IA
    prcr.addAfterFinalRule(new VMIFeeEstTOAPRule());// �����ݹ�Ӧ����AP

  }

  /** �õ��ݹ�VO��ͷ���飬��Ҫ���µĲɹ�����ͼ **/
  private VmiEstHeaderVO[] getHeaders(VmiEstVO[] vos) {
    VmiEstHeaderVO[] heads = new VmiEstHeaderVO[vos.length];
    for (int i = 0; i < heads.length; i++) {
      heads[i] = vos[i].getParentVO();
    }
    return heads;
  }

  private VmiFIFeeVO[] getItems(VmiEstVO[] vos) {
    VmiFIFeeVO[] items = AggVOUtil.getCombinItemVOs(vos);
    return items;
  }

}
