/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-18 下午01:47:01
 */
package nc.bs.pu.est.m50.rule;

import nc.bs.pu.est.rule.AbstractGoodsEstTOIARule;
import nc.bs.pu.est.rule.billfactory.EstFIBillFactory;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.pu.reference.ia.IAForEstConfirmServices;
import nc.vo.ia.mi2.entity.I2BillVO;
import nc.vo.pu.est.entity.EstVO;
import nc.vo.pu.est.entity.m50.VmiEstVO;
import nc.vo.pu.est.util.VMIEstVOUtil;
import nc.vo.pu.m4202.entity.VmiFIVO;

/**
 * 
 * @description
 * 消耗汇总货物暂估传IA
 * @scene
 * 暂估的BP操作
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-23 上午9:57:09
 * @author zhangshqb
 */
public class VMIGoodsEstTOIARule extends AbstractGoodsEstTOIARule implements
    IRule<VmiEstVO> {

  @Override
  public void process(VmiEstVO[] vos) {
    super.process(vos);
  }

  @Override
  protected void sendTOIA(EstVO[] estVos) {
    VmiFIVO[] fiVos = VMIEstVOUtil.getVmiFIVO((VmiEstVO[]) estVos);
    I2BillVO[] I2Vos = EstFIBillFactory.getVMIGoodsEstI2Gen(fiVos).genBill();
    IAForEstConfirmServices.VMIEstimate(I2Vos);
  }

}
