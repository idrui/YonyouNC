/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-1 上午09:28:22
 */
package nc.bs.pu.m25.writeback.source;

import java.util.List;

import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.itf.pu.reference.ic.M45PUServices;
import nc.pubitf.ic.m45.m25.IParameter45For25;
import nc.vo.pu.m25.env.InvoiceUIToBSEnv;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>回写采购入库单
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-2-1 上午09:28:22
 */
public class WriteBack45 implements IInvoiceWriteBackSource {

  @Override
  public void writeback(List<RewritePara> rwParas) {
    return;
  }

  @Override
  public void writeback(List<RewritePara> rwParas, InvoiceUIToBSEnv env) {
    M45PUServices.writeback45For25(this.createWriteBack45Para(rwParas));
  }

  private IParameter45For25[] createWriteBack45Para(
      final List<RewritePara> paraList) {
    IParameter45For25[] wbVos = new IParameter45For25[paraList.size()];
    for (int i = 0; i < paraList.size(); ++i) {
      final int idx = i;
      wbVos[i] = new IParameter45For25() {
        @Override
        public String getBid() {
          return paraList.get(idx).getCsrcbid();
        }

        @Override
        public String getHid() {
          return paraList.get(idx).getCsrcid();
        }

        @Override
        public UFDouble getNinvoicenum() {
          return paraList.get(idx).getNnum();
        }
      };
    }
    return wbVos;
  }

}
