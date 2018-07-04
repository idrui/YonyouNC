package nc.vo.pu.m21.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.IVOMeta;
import nc.vo.pubapp.pattern.model.tool.BillIndex;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * Эͬβ���
 * ��Ϊ�����������ȥ��VO���գ����Բ��߿��������߼����Լ�д
 * ֻ���Эͬ
 * 
 * @since 6.0
 * @version 2011-7-16 ����08:33:30
 * @author wuxla
 */

public class CoopMarginRule implements IRule<OrderVO> {
  private AggregatedValueObject[] srcVOs;

  public CoopMarginRule(AggregatedValueObject[] srcVOs) {
    this.srcVOs = srcVOs;
  }

  @Override
  public void process(OrderVO[] vos) {
    SaleOrderVO[] srcSaleVOs = ArrayUtil.convertArrayType(this.srcVOs);
    BillIndex index = new BillIndex(srcSaleVOs);
    IVOMeta salebmeta =
        new SaleOrderVO().getMetaData().getVOMeta(SaleOrderBVO.class);
    for (OrderVO vo : vos) {
      for (OrderItemVO itemVO : vo.getBVO()) {
        String csourcebid = itemVO.getCsourcebid();
        SaleOrderBVO salebvo = (SaleOrderBVO) index.get(salebmeta, csourcebid);
        this.setQtMargin(itemVO, salebvo);
      }
    }
  }

  private void setQtMargin(OrderItemVO itemVO, SaleOrderBVO salebvo) {
    if (null == salebvo) {
      return;
    }
    itemVO.setNorigmny(salebvo.getNorigmny());
    itemVO.setNorigtaxmny(salebvo.getNorigtaxmny());
    // itemVO.setNorigtax(salebvo.getNorigtax());
  }

}
