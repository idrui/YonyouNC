/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-30 下午07:58:44
 */
package nc.bs.pu.m21.writeback.source;

import nc.impl.pubapp.bill.rewrite.BillRewriter;
import nc.vo.pu.pub.writeback.IWriteBackAddSRCClazz;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>销售订单回写工具类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-6-30 下午07:58:44
 */
public class WriteBackAddSRCClazzFor30 implements IWriteBackAddSRCClazz {

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pu.pub.writeback.IWriteBackAddSRCClazz#addSRCClazz(nc.impl.scmpub.bill.rewrite.BillRewriter)
   */
  @Override
  public void addSRCClazz(BillRewriter billRewriter) {
    billRewriter
        .addSRCHeadClazz(SOBillType.Order.getCode(), SaleOrderHVO.class);
    billRewriter
        .addSRCItemClazz(SOBillType.Order.getCode(), SaleOrderBVO.class);
  }

}
