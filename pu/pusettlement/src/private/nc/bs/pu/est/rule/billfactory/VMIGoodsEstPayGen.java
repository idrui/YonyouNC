/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-17 ����10:03:56
 */
package nc.bs.pu.est.rule.billfactory;

import nc.bs.arap.util.IArapBillTypeCons;
import nc.vo.pu.m4202.entity.VmiFIVO;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���Ļ����ݹ�Ӧ������������(�����ݹ�)
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-8-17 ����10:03:56
 */
public class VMIGoodsEstPayGen extends AbstractEstBillGenerator<VmiFIVO> {

  public VMIGoodsEstPayGen(VmiFIVO[] srcVos) {
    super(POBillType.VoiConsumeFinance.getCode(), IArapBillTypeCons.C1, srcVos);
  }

}
