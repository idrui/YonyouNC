package nc.vo.pu.m21.entity;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaAdapter;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

/**
 * 表体和到货计划聚合VO，为批次使用
 * 
 * @since 6.0
 * @version 2011-4-26 上午10:37:01
 * @author wuxla
 */

public class AggItemReceivePlanVO extends AbstractBill {

  private static final long serialVersionUID = -4233856024241001440L;

  @Override
  public IBillMeta getMetaData() {
    AggItemReceivePlanVOMeta meta = new AggItemReceivePlanVOMeta();

    BillMetaAdapter adapter = new BillMetaAdapter();
    adapter.loadDataFromModel(meta);

    return meta;
  }

}
