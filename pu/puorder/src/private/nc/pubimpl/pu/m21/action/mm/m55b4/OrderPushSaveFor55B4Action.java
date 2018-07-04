/**
 * $�ļ�˵��$
 * 
 * @author lixyp
 * @version 6.1
 * @see
 * @since 6.1
 * @time 2011-12-19 ����08:20:32
 */
package nc.pubimpl.pu.m21.action.mm.m55b4;

import nc.bs.pu.m21.maintain.OrderSaveBP;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubimpl.pu.m21.rule.CheckOrderNotNullRule;
import nc.pubimpl.pu.m21.rule.CurrencyAndExchangeRule;
import nc.pubimpl.pu.m21.rule.NumCalcRule;
import nc.pubimpl.pu.m21.rule.OrganizationValueRule;
import nc.pubimpl.pu.m21.rule.PlanArrDateOrderDateRule;
import nc.pubimpl.pu.m21.rule.PlanArriveDateRule;
import nc.pubimpl.pu.m21.rule.RelationCalculateRule;
import nc.pubimpl.pu.m21.rule.SupplierValueRule;
import nc.pubimpl.pu.m21.rule.TrantypeRule;
import nc.pubimpl.pu.m21.rule.WarehouseValueRule;
import nc.vo.pu.m21.context.OrderContext;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.rule.QuerySourceRule;
import nc.vo.pu.m21.rule.RowNoRule;
import nc.vo.pu.m21.rule.margin.OrderMarginRule;
import nc.vo.pu.m21.rule.vat.OrderVatInfoSrcFillRule;
import nc.vo.scmf.sourcing.entity.SourceReturnVO;
import nc.vo.scmpub.res.billtype.MMBillType;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ƻ�������ʽ���ɲɹ�����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.1
 * @since 6.1
 * @author lixyp
 * @time 2011-12-19 ����08:20:32
 */
public class OrderPushSaveFor55B4Action {

  // lixy 2012.4.28 ���ƻ�������ʽ����Action�Ӵ���̳У����޸Ĵ���ʱ���뿼���Ƿ�ͬ�������ڿ��ƻ�������

  public OrderVO[] pushSave(OrderVO[] orderVOs, SourceReturnVO[] SourceReturnVOs) {
    // ע��ҵ�����
    AroundProcesser<OrderVO> processer = new AroundProcesser<OrderVO>(null);
    this.addBeforeRule(processer, SourceReturnVOs);

    // ����
    processer.before(orderVOs);

    // ���ڿ��ƻ������ɴ˼̳У���β������߼���BeforeRule����������������д��
    this.processMargin(orderVOs);

    OrderSaveBP bp = new OrderSaveBP(new OrderContext());
    OrderVO[] savedVOs = bp.save(orderVOs, null);
    processer.after(savedVOs);
    return savedVOs;
  }

  private void addBeforeRule(AroundProcesser<OrderVO> processer,
      SourceReturnVO[] SourceReturnVOs) {
    // �������
    processer.addBeforeRule(new CheckOrderNotNullRule());
    // ��ȫ�к�
    processer.addBeforeRule(new RowNoRule());
    // ��ȫ������֯
    processer.addBeforeRule(new OrganizationValueRule());
    // ��ȫ��Ӧ����Ϣ
    processer.addBeforeRule(new SupplierValueRule());
    // vat��Ϣ����
    processer.addBeforeRule(new OrderVatInfoSrcFillRule(false));
    // ѰԴ��Ϣ����
    processer.addBeforeRule(new QuerySourceRule(SourceReturnVOs));
    // ��ȫ���ֺͻ���
    processer.addBeforeRule(new CurrencyAndExchangeRule());
    // ��ȫ�ֿ�
    processer.addBeforeRule(new WarehouseValueRule());
    // ��ȫ����
    processer.addBeforeRule(new NumCalcRule());
    // ���۽�����
    processer.addBeforeRule(new RelationCalculateRule());
    // �ƻ���������
    processer.addBeforeRule(new PlanArriveDateRule());
    processer.addBeforeRule(new PlanArrDateOrderDateRule());
    // ��ȫ��������
    processer.addBeforeRule(new TrantypeRule());
  }

  /**
   * ����β�����
   * 
   * @param orderVOs
   */
  protected void processMargin(OrderVO[] orderVOs) {
    new OrderMarginRule(MMBillType.PlanOrder.getCode(), null).process(orderVOs);
  }
}
