/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-8 ����09:56:28
 */
package nc.bs.pu.m4t.writeback.pu;

import nc.bs.pu.m4t.writeback.pu.rule.AccInvoiceNumCalcRule;
import nc.bs.pu.m4t.writeback.pu.rule.AccInvoiceNumChkRule;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubitf.pu.m4t.pu.m25.IInvoicePullWBPara;
import nc.vo.pu.m21.pub.OrderVOUtil;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.m4t.entity.InitialEstViewVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���ɲɹ���Ʊ�Ļ�дBP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-8 ����09:56:28
 */
public class InitialEstInvoicePullWBBP {

  /**
   * ����������������Ʊ��д
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-20 ����02:48:25
   */
  public void writeBack(IInvoicePullWBPara[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    String[] bids = OrderVOUtil.getInsance().getBIDs(vos);
    InitialEstViewVO[] views =
        new ViewQuery<InitialEstViewVO>(InitialEstViewVO.class).query(bids);

    if (ArrayUtils.isEmpty(views)) {
      return;
    }

    AroundProcesser<InitialEstViewVO> processer =
        new AroundProcesser<InitialEstViewVO>(null);
    this.addRule(processer, vos);

    processer.before(views);

    String[] wbNames = new String[] {
      InitialEstItemVO.NACCINVOICENUM
    };
    ViewUpdate<InitialEstViewVO> bo = new ViewUpdate<InitialEstViewVO>();
    views = bo.update(views, InitialEstItemVO.class, wbNames);

    processer.after(views);
  }

  private void addRule(AroundProcesser<InitialEstViewVO> processer,
      IInvoicePullWBPara[] paras) {
    processer.addBeforeRule(new AccInvoiceNumCalcRule(paras));
    processer.addAfterRule(new AccInvoiceNumChkRule());
  }
}
