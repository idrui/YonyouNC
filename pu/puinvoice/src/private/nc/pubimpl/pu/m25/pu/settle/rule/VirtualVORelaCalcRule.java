package nc.pubimpl.pu.m25.pu.settle.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.pub.VORelationCalculate;
import nc.vo.pub.lang.UFDouble;

/**
 * @description
 *              ���ⷢƱVO�������㣬������Ľ��Խ���ٴη����������㣬�Ա�֤��Ʊ���ݵ�������
 * @scene
 *        �������ⷢƱ
 * @param ��
 * @since 6.3
 * @version 2014-10-22 ����3:16:52
 * @author zhangshqb
 */
public class VirtualVORelaCalcRule implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    VORelationCalculate vocalc = new VORelationCalculate();
    for (InvoiceVO invoiceVO : vos) {
      InvoiceHeaderVO head = invoiceVO.getParentVO();
      for (InvoiceItemVO item : invoiceVO.getChildrenVO()) {
        // �޷�Ʊ����ʱ������ݼǳɱ����㱾����˰����ʱ�Ա�����˰�������ʱ�����ٸı�ǳɱ����Ͳ��ɵֿ�˰��
        UFDouble ncalcostmny = item.getNcalcostmny();
        UFDouble nmny = item.getNmny();
        UFDouble nosubtax = ncalcostmny.sub(nmny);
        // wuxla v61
        vocalc.calculate(head, item, InvoiceItemVO.NMNY);
        item.setNcalcostmny(ncalcostmny);
        item.setNnosubtax(nosubtax);
      }
    }
  }

}
