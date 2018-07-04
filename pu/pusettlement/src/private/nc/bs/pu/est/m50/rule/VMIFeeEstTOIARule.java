/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-20 下午02:10:02
 */
package nc.bs.pu.est.m50.rule;

import nc.bs.pu.est.rule.AbstractFeeEstTOIARule;
import nc.bs.pu.est.rule.billfactory.EstFIBillFactory;
import nc.bs.pu.est.rule.fee.EstFeeBSDIVRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.pu.reference.ia.IAForEstConfirmServices;
import nc.vo.ia.mi9.entity.I9BillVO;
import nc.vo.pu.est.entity.EstVO;
import nc.vo.pu.est.entity.FeeEstDistVO;
import nc.vo.pu.est.entity.m50.VmiEstVO;
import nc.vo.pu.est.entity.m50.VmiFIFDVO;
import nc.vo.pu.est.util.VMIEstVOUtil;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m4202.entity.VmiFIVO;

/**
 * 
 * @description
 * 消耗汇总费用暂估传存货核算(IA)
 * @scene
 * 暂估的BP操作
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-23 上午9:57:43
 * @author zhangshqb
 */
public class VMIFeeEstTOIARule extends AbstractFeeEstTOIARule implements
    IRule<VmiEstVO> {

  @Override
  public void process(VmiEstVO[] vos) {
    super.process(vos);
  }

  @Override
  protected void estToIA(EstVO[] estVos, FeeEstDistVO[] fdVos) {
    VmiFIVO[] fivos = VMIEstVOUtil.getVmiFIVO((VmiEstVO[]) estVos);
    I9BillVO[] i9s =
        (I9BillVO[]) EstFIBillFactory.getVMIFeeEstI9Gen(fivos,
            (VmiFIFDVO[]) fdVos).genBill();
    IAForEstConfirmServices.VMIFeeEstimate(i9s);
  }

  @Override
  protected EstFeeBSDIVRule<? extends FeeEstDistVO> getFeeDivProcesser() {
    return new EstFeeBSDIVRule<VmiFIFDVO>(VmiFIFDVO.class,
        SettleBillItemVO.PK_VOICONSUME_B);
  }

}
