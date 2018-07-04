/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-24 下午03:22:03
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
 * 费用单独暂估应付规则，必须作为后规则，要使用费用暂估ID
 * @scene
 * 暂估的BP操作
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-23 上午9:57:58
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
   * 得到暂估费用时的财务聚合VO，用于数据交换
   */
  private VmiFIVO[] getFeeEstFiVO(VmiEstVO[] estVos) {

    List<VmiFIVO> fivos = new ArrayList<VmiFIVO>();
    for (VmiEstVO estVo : estVos) {
      if (ArrayUtils.isEmpty(estVo.getChildrenVO())) {
        continue;
      }
      VmiEstHeaderVO gevo = estVo.getParentVO();
      String pk_fiorg = gevo.getPk_financeorg();
      // 如果有效率问题，这里也可以从第1个表体（费用）VO中取得标志，因为前规则中已经设置好
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
