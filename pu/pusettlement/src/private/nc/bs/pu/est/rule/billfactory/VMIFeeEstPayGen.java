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

import java.util.ArrayList;
import java.util.List;

import nc.bs.arap.util.IArapBillTypeCons;
import nc.vo.pu.m4202.entity.VmiFIFeeVO;
import nc.vo.pu.m4202.entity.VmiFIHeaderVO;
import nc.vo.pu.m4202.entity.VmiFIVO;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFDouble;
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
public class VMIFeeEstPayGen extends AbstractEstBillGenerator<VmiFIVO> {

  public VMIFeeEstPayGen(VmiFIVO[] srcVos) {
    super(POBillType.VoiConsumeFinance.getCode(), IArapBillTypeCons.C1, srcVos);
  }

  private VmiFIVO convertItemToHead(VmiFIFeeVO fee, VmiFIVO fivo) {
    VmiFIVO cfivo = new VmiFIVO();
    cfivo.setParentVO((CircularlyAccessibleValueObject) fivo.getParentVO()
        .clone());
    this.fillSrcItemInfo(cfivo, fee);
    return cfivo;
  }

  protected void fillSrcItemInfo(VmiFIVO fivo, VmiFIFeeVO fee) {
    VmiFIHeaderVO head = fivo.getParentVO();
    head.setPk_material(fee.getPk_feematerial());// ����
    head.setPk_srcmaterial(fee.getPk_srcfeematerial());
    head.setDtocostapdate(fee.getDestdate());// �ݹ�����
    head.setPk_supplier(fee.getPk_supplier());// ��Ӧ��
    head.setPk_costappsn(fee.getPk_estpsn());// �ݹ���
    // wuxla Ӧ�������䣬��ʹ���ݹ����
    head.setNestmny(fee.getNestmny());// �ݹ����
    head.setNestnum(UFDouble.ZERO_DBL);// �ݹ�����Ϊ0
    head.setPk_estcurrency(fee.getPk_estcurrency());// �ݹ�����
    head.setNestexhgrate(fee.getNestexchgrate());// ����
    head.setNesttaxrate(fee.getNesttaxrate());// ˰��
    head.setFesttaxtype(fee.getFtaxtypeflag());// ��˰��� 2012-06-19 tianft
    head.setNesttotalmny(fee.getNesttotalmny());// ��˰�ϼ�
    head.setNesttaxmny(fee.getNesttaxmny());// ˰��
    head.setNestomny(fee.getNestomny());// ԭ�ҽ��
    head.setNestototalmny(fee.getNestototalmny());// ԭ�Ҽ�˰�ϼ�
    // head.setNestotaxmny(fee.getNestotaxmny());// ԭ��˰��
    head.setNestprice(UFDouble.ZERO_DBL);// ����
    head.setNesttaxprice(UFDouble.ZERO_DBL);// ��˰����
    head.setNestoprice(UFDouble.ZERO_DBL);// ԭ�ҵ���
    head.setNestotaxprice(UFDouble.ZERO_DBL);// ԭ�Һ�˰����
    head.setPk_stockps_b(fee.getPk_stockps_fee());// ��¼�����ݹ���pk
    // 2012-05-09 tianft ���vat���
    head.setCsendcountryid(fee.getCsendcountryid());// ���͹�
    head.setCtaxcountryid(fee.getCtaxcountryid());// ��˰��
    head.setCrececountryid(fee.getCrececountryid());// �ջ���
    head.setFbuysellflag(fee.getFbuysellflag());// ����ó��
    head.setBtriatradeflag(fee.getBtriatradeflag());// ��������
    head.setCesttaxcodeid(fee.getCtaxcodeid());// ˰��
    head.setNestnosubtax(fee.getNnosubtax());// ���ɵֿ�˰��
    head.setNestnosubtaxrate(fee.getNnosubtaxrate());// ���ɵֿ�˰��
    head.setNestcalcostmny(fee.getNcalcostmny());// �Ƴɱ����
    head.setNestcaltaxmny(fee.getNcaltaxmny());// ��˰���
    head.setBopptaxflag(fee.getBopptaxflag());// ������˰
  }

  @Override
  protected void processSrcVO() {
    super.processSrcVO();
    VmiFIVO[] fivos = this.getSrcVos();
    List<VmiFIVO> newFiVos = new ArrayList<VmiFIVO>();
    for (VmiFIVO fivo : fivos) {
      for (VmiFIFeeVO fee : fivo.getChildrenVO()) {
        VmiFIVO newFivo = this.convertItemToHead(fee, fivo);
        newFiVos.add(newFivo);
      }
    }
    this.setSrcVos(newFiVos.toArray(new VmiFIVO[newFiVos.size()]));
  }

}
