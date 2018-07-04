/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-30 下午07:55:39
 */
package nc.bs.pu.m21.writeback.source;

import nc.impl.pubapp.bill.rewrite.BillRewriter;
import nc.vo.invp.po.entity.PoVO;
import nc.vo.pu.pub.writeback.IWriteBackAddSRCClazz;
import nc.vo.scmpub.res.billtype.INVPBillType;

/**
 * 回写库存计划计划订单
 * 
 * @since 6.0
 * @version 2011-12-9 上午11:09:01
 * @author 田锋涛
 */
public class WriteBackAddSRCClazzFor4F implements IWriteBackAddSRCClazz {

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pu.pub.writeback.IWriteBackAddSRCClazz#addSRCClazz()
   */
  @Override
  public void addSRCClazz(BillRewriter billRewriter) {
    billRewriter.addSRCHeadClazz(INVPBillType.PoOrder.getCode(), PoVO.class);
  }

}
