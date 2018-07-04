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
import nc.vo.mmpac.pmo.pac0002.entity.PMOHeadVO;
import nc.vo.mmpac.pmo.pac0002.entity.PMOItemVO;
import nc.vo.pu.pub.writeback.IWriteBackAddSRCClazz;
import nc.vo.scmpub.res.billtype.MMBillType;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����������д������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-6-30 ����08:00:49
 */
public class WriteBackAddSRCClazzFor55A2 implements IWriteBackAddSRCClazz {

  /**
   * ���෽����д
   * 
   * @see nc.vo.pu.pub.writeback.IWriteBackAddSRCClazz#addSRCClazz(nc.impl.scmpub.bill.rewrite.BillRewriter)
   */
  @Override
  public void addSRCClazz(BillRewriter billRewriter) {
    billRewriter.addSRCHeadClazz(MMBillType.ProduceOrder.getCode(),
        PMOHeadVO.class);
    billRewriter.addSRCItemClazz(MMBillType.ProduceOrder.getCode(),
        PMOItemVO.class);
  }

}
