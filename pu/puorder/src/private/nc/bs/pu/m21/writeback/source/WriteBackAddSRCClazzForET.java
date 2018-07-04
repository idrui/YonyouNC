package nc.bs.pu.m21.writeback.source;

import nc.impl.pubapp.bill.rewrite.BillRewriter;
import nc.vo.it.m5801.entity.ContractBVO;
import nc.vo.it.m5801.entity.ContractHVO;
import nc.vo.pu.pub.writeback.IWriteBackAddSRCClazz;
import nc.vo.scmpub.res.billtype.ETBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>出口合同回写工具类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.31
 * @since 6.31
 * @author zhangyhh
 * @time 2013-8-9 下午03:00:49
 */
public class WriteBackAddSRCClazzForET implements IWriteBackAddSRCClazz {
  /**
   * 父类方法重写
   * 
   * @see nc.vo.pu.pub.writeback.IWriteBackAddSRCClazz#addSRCClazz(nc.impl.scmpub.bill.rewrite.BillRewriter)
   */
  @Override
  public void addSRCClazz(BillRewriter billRewriter) {
    billRewriter.addSRCHeadClazz(ETBillType.CONTRACT.getCode(),
        ContractHVO.class);
    billRewriter.addSRCItemClazz(ETBillType.CONTRACT.getCode(),
        ContractBVO.class);
  }
}
