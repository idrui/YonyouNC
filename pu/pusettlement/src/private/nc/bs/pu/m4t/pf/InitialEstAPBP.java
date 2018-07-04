/**
 * $�ļ�˵��$
 *
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-31 ����01:34:38
 */
package nc.bs.pu.m4t.pf;

import nc.bs.arap.util.IArapBillTypeCons;
import nc.bs.pu.est.plugin.InitialEstPluginPoint;
import nc.bs.pu.m4t.pf.rule.EstApInfoUpdateRule;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.arap.ArapServicesForPUUtil;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.arap.estipayable.AggEstiPayableBillVO;
import nc.vo.pu.est.util.ToApIaDataProcessor;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ڳ��ݹ����ݹ�Ӧ����BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-8-31 ����01:34:38
 */
public class InitialEstAPBP {
  public void estimate(InitialEstVO[] vos) {
    if (!SysInitGroupQuery.isAPEnabled()) {
      return;
    }
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    AroundProcesser<InitialEstVO> prcr =
        new AroundProcesser<InitialEstVO>(InitialEstPluginPoint.EST_AP);
    this.addRule(prcr);
    prcr.before(vos);
    this.estAP(vos);
    this.updateEstInfo(vos);
    prcr.after(vos);
  }

  private void addRule(AroundProcesser<InitialEstVO> prcr) {
    prcr.addBeforeFinalRule(new EstApInfoUpdateRule());// �����ݹ���Ϣ
  }

  private void estAP(InitialEstVO[] vos) {
    ToApIaDataProcessor processor = new ToApIaDataProcessor();
    // ��Ʊ��Ӧ���滻
    processor.fillDataByOrder(vos);
    AggEstiPayableBillVO[] apVos =
        PfServiceScmUtil.executeVOChange(POBillType.InitEstimate.getCode(),
            IArapBillTypeCons.C1, vos);
    // ��ԭ��Ӧ��
    processor.resetData(vos);
    if (ArrayUtils.isEmpty(apVos)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004060_0", "04004060-0090")/*
                                                                   * @res
                                                                   * "���ڳ��ݹ����ݹ�Ӧ�����������ݳ������ش���"
                                                                   */);
    }
    // ��Ӧ��������
    ArapServicesForPUUtil.estPayable(apVos);

  }

  private void updateEstInfo(InitialEstVO[] vos) {
    InitialEstItemVO[] items = AggVOUtil.getCombinItemVOs(vos);
    new VOUpdate<InitialEstItemVO>().update(items, new String[] {
      InitialEstItemVO.BESTIMATEAP
    });
  }
}
