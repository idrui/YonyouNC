/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-30 下午08:00:02
 */
package nc.bs.pu.m21.writeback.source;

import nc.impl.pubapp.bill.rewrite.BillRewriter;
import nc.vo.ic.m49.entity.BorrowInBodyVO;
import nc.vo.ic.m49.entity.BorrowInHeadVO;
import nc.vo.pu.pub.writeback.IWriteBackAddSRCClazz;
import nc.vo.scmpub.res.billtype.ICBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>回写库存借入单回写工具类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-6-30 下午08:00:02
 */
public class WriteBackAddSRCClazzFor49 implements IWriteBackAddSRCClazz {

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pu.pub.writeback.IWriteBackAddSRCClazz#addSRCClazz(nc.impl.scmpub.bill.rewrite.BillRewriter)
   */
  @Override
  public void addSRCClazz(BillRewriter billRewriter) {
    billRewriter.addSRCHeadClazz(ICBillType.BorrowIn.getCode(),
        BorrowInHeadVO.class);
    billRewriter.addSRCItemClazz(ICBillType.BorrowIn.getCode(),
        BorrowInBodyVO.class);
  }

}
