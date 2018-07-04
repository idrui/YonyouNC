package nc.bs.pu.m4202;

import nc.bs.pu.m27.AbstractStockFICancelDupBP;
import nc.bs.pu.m4202.rule.EstimatedCheckRule;
import nc.bs.pu.m4202.rule.SettledCheckRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m4202.entity.VmiFIVO;

/**
 * ɾ�����Ļ��ܵĸ���BP
 * 
 * @since 6.0
 * @version 2011-1-20 ����03:19:25
 * @author zhaoyha
 */
public class VMIFICancelDupBP extends AbstractStockFICancelDupBP<VmiFIVO> {

  public VMIFICancelDupBP() {
    super(VmiFIVO.class);
  }

  @Override
  protected void addRule(AroundProcesser<VmiFIVO> prcr) {
    prcr.addBeforeFinalRule(new EstimatedCheckRule());// �ݹ����
    prcr.addBeforeFinalRule(new SettledCheckRule());// ������
  }

}
