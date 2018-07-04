/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-1 上午09:26:49
 */
package nc.bs.pu.m25.writeback.source;

import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.pubitf.pu.m21.pu.m25.IOrderWriteBackFor25;
import nc.pubitf.pu.m21.pu.m25.IOrderWriteBackParaFor25;
import nc.vo.pu.m25.enumeration.InvoiceUserAnswerType;
import nc.vo.pu.m25.env.InvoiceUIToBSEnv;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>回采购订单
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-2-1 上午09:26:49
 */
public class WriteBack21 implements IInvoiceWriteBackSource {
  private Map<String, UFBoolean> bidFeeMap;

  public WriteBack21(Map<String, UFBoolean> bidFeeMap) {
    this.setBidFeeMap(bidFeeMap);
  }

  /**
   * @return bidFeeMap
   */
  public Map<String, UFBoolean> getBidFeeMap() {
    return this.bidFeeMap;
  }

  /**
   * @param bidFeeMap
   *          要设置的 bidFeeMap
   */
  public void setBidFeeMap(Map<String, UFBoolean> bidFeeMap) {
    this.bidFeeMap = bidFeeMap;
  }

  @Override
  public void writeback(List<RewritePara> rwParas) {
    return;
  }

  @Override
  public void writeback(List<RewritePara> rwParas, InvoiceUIToBSEnv env) {
    try {
      this.getWriteBackService().writeBackFor25(
          this.createWriteBackVO(rwParas, env));
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
  }

  private IOrderWriteBackParaFor25[] createWriteBackVO(
      List<RewritePara> rwParas, final InvoiceUIToBSEnv env) {
    IOrderWriteBackParaFor25[] wbVos =
        new IOrderWriteBackParaFor25[rwParas.size()];
    for (int i = 0; i < rwParas.size(); ++i) {
      final RewritePara para = rwParas.get(i);
      wbVos[i] = new IOrderWriteBackParaFor25() {

        @Override
        public String getBBID() {
          return null;
        }

        @Override
        public String getBID() {
          return para.getCsrcbid();
        }

        @Override
        public UFDouble getDiffMny() {
          return para.getNnum2();
        }

        @Override
        public UFDouble getDiffNum() {
          return para.getNnum();
        }

        @Override
        public String getHID() {
          return para.getCsrcid();
        }

        @Override
        public UFDouble getPrice() {
          return null;
        }

        @Override
        public boolean isClose() {
          return false;
        }

        @Override
        public boolean isDiscard() {
          return false;
        }

        @Override
        public boolean isFee() {
          return WriteBack21.this.getBidFeeMap().get(para.getCbill_bid())
              .booleanValue();
        }

        @Override
        public boolean isNumUserConfirm() {
          return InvoiceUserAnswerType.YES == env.getOverPONum();
        }

        @Override
        public boolean isPriceUserConfirm() {
          return InvoiceUserAnswerType.YES == env.getOverPOPrice();
        }

        @Override
        public boolean isReturn() {
          return false;
        }
      };
    }
    return wbVos;
  }

  private IOrderWriteBackFor25 getWriteBackService() {
    return NCLocator.getInstance().lookup(IOrderWriteBackFor25.class);
  }

}
