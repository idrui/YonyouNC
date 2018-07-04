/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-30 ����08:00:49
 */
package nc.bs.pu.m21.writeback.source;

import nc.impl.pubapp.bill.rewrite.BillRewriter;
import nc.vo.mmpac.dmo.entity.DmoVO;
import nc.vo.pu.pub.writeback.IWriteBackAddSRCClazz;
import nc.vo.scmpub.res.billtype.MMBillType;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����������д������-��ɢ��������
 * </ul>
 * <p>
 * <p>
 * 
 * @since 6.0
 * @version 2012-11-5 ����02:24:05
 * @author liuyand
 */
public class WriteBackAddSRCClazzFor55C2 implements IWriteBackAddSRCClazz {

  /**
   * ���෽����д
   * 
   * @see nc.vo.pu.pub.writeback.IWriteBackAddSRCClazz#addSRCClazz(nc.impl.scmpub.bill.rewrite.BillRewriter)
   */
  @Override
  public void addSRCClazz(BillRewriter billRewriter) {
    billRewriter.addSRCHeadClazz(MMBillType.LsProduceOrder.getCode(),
        DmoVO.class);
  }
}
