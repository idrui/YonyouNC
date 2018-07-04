/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-18 下午03:04:22
 */
package nc.bs.pu.est.m50.rule;

import nc.bs.pu.est.rule.AbstractGoodsEstTOAPRule;
import nc.bs.pu.est.rule.billfactory.EstFIBillFactory;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.arap.ArapServicesForPUUtil;
import nc.vo.arap.estipayable.AggEstiPayableBillVO;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.est.entity.m50.VmiEstHeaderVO;
import nc.vo.pu.est.entity.m50.VmiEstVO;
import nc.vo.pu.est.util.VMIEstVOUtil;
import nc.vo.pu.m4202.entity.VmiFIVO;

/**
 * 
 * @description
 * 消耗汇总暂估货物暂估应付规则
 * @scene
 * 暂估的BP操作
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-23 上午9:57:26
 * @author zhangshqb
 */
public class VMIGoodsEstTOAPRule extends AbstractGoodsEstTOAPRule implements
    IRule<VmiEstVO> {

  @Override
  public void process(VmiEstVO[] vos) {
    super.process(vos);
  }

  private VmiFIVO[] getFiVOs(VmiEstHeaderVO[] heads) {
    VmiFIVO[] fiVos = VMIEstVOUtil.getVmiFIVO(heads);
    return fiVos;
  }

  @Override
  protected void estTOAP(GoodsEstVO[] gevos) {
    VmiFIVO[] fiVos = this.getFiVOs((VmiEstHeaderVO[]) gevos);
    AggEstiPayableBillVO[] payVos =
        (AggEstiPayableBillVO[]) EstFIBillFactory.getVMIGoodsEstPayGen(fiVos)
            .genBill();
    ArapServicesForPUUtil.estPayable(payVos);
  }
}
