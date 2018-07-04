/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-24 ����03:22:03
 */
package nc.bs.pu.est.m50.rule;

import java.util.ArrayList;
import java.util.List;

import nc.bs.pu.est.rule.billfactory.EstFIBillFactory;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.arap.ArapServicesForPUUtil;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.arap.estipayable.AggEstiPayableBillVO;
import nc.vo.pu.est.entity.m50.VmiEstHeaderVO;
import nc.vo.pu.est.entity.m50.VmiEstVO;
import nc.vo.pu.m4202.entity.VmiFIHeaderVO;
import nc.vo.pu.m4202.entity.VmiFIVO;
import nc.vo.pu.pub.util.ListUtil;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * ���õ����ݹ�Ӧ�����򣬱�����Ϊ�����Ҫʹ�÷����ݹ�ID
 * @scene
 * �ݹ���BP����
 * @param
 * ��
 *
 * @since 6.3
 * @version 2014-10-23 ����9:57:58
 * @author zhangshqb
 */
public class VMIFeeEstTOAPRule implements IRule<VmiEstVO> {

  @Override
  public void process(VmiEstVO[] vos) {
    if (!SysInitGroupQuery.isAPEnabled()) {
      return;
    }
    VmiFIVO[] fivos = this.getFeeEstFiVO(vos);
    if (ArrayUtils.isEmpty(fivos)) {
      return;
    }
    AggEstiPayableBillVO[] payVos =
        (AggEstiPayableBillVO[]) EstFIBillFactory.getVMIFeeEstPayGen(fivos)
            .genBill();
    this.estToAP(payVos);
  }

  private void estToAP(AggEstiPayableBillVO[] payVos) {
    ArapServicesForPUUtil.estPayable(payVos);
  }

  /**
   * �õ��ݹ�����ʱ�Ĳ���ۺ�VO���������ݽ���
   */
  private VmiFIVO[] getFeeEstFiVO(VmiEstVO[] estVos) {

    List<VmiFIVO> fivos = new ArrayList<VmiFIVO>();
    for (VmiEstVO estVo : estVos) {
      if (ArrayUtils.isEmpty(estVo.getChildrenVO())) {
        continue;
      }
      VmiEstHeaderVO gevo = estVo.getParentVO();
      String pk_fiorg = gevo.getPk_financeorg();
      // �����Ч�����⣬����Ҳ���Դӵ�1�����壨���ã�VO��ȡ�ñ�־����Ϊǰ�������Ѿ����ú�
      UFBoolean estAP = PUSysParamUtil.getPO52(pk_fiorg);
      if (!estAP.booleanValue()) {
        continue;
      }
      VmiFIVO fivo = new VmiFIVO();
      VmiFIHeaderVO head = (VmiFIHeaderVO) gevo.getVO(VmiFIHeaderVO.class);
      if (null == head) {
        continue;
      }
      fivo.setParent(head);
      fivo.setChildrenVO(estVo.getChildrenVO());
      fivos.add(fivo);
    }
    if (ListUtil.isEmpty(fivos)) {
      return null;
    }
    return fivos.toArray(new VmiFIVO[fivos.size()]);
  }
}
