/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-2 上午10:11:15
 */
package nc.bs.pu.m25.writeback.pu;

import java.util.Map;

import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceViewVO;
import nc.vo.pu.m25.entity.InvoiceWriteBackVO;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购发票为结算提供的回写
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-2-2 上午10:11:15
 */
public class InvoiceSettleWriteBackBP {
  public void writeback(InvoiceWriteBackVO[] wbVos) {

    String[] bids = InvoiceWriteBackVO.getInvoiceBIds(wbVos);
    InvoiceViewVO[] views =
        new ViewQuery<InvoiceViewVO>(InvoiceViewVO.class).query(bids);
    this.calculateNum(views, wbVos);// 计算累计结算数量和金额
    String[] wbNames = new String[] {
      InvoiceItemVO.NACCUMSETTNUM, InvoiceItemVO.NACCUMSETTMNY
    };
    ViewUpdate<InvoiceViewVO> bo = new ViewUpdate<InvoiceViewVO>();
    bo.update(views, InvoiceItemVO.class, wbNames);
  }

  private void calculateNum(InvoiceViewVO[] views, InvoiceWriteBackVO[] vos) {
    Map<String, InvoiceViewVO> viewMap = CirVOUtil.createKeyVOMap(views);
    for (InvoiceWriteBackVO vo : vos) {
      InvoiceViewVO view = viewMap.get(vo.getPk_invoice_b());
      UFDouble diffNum = vo.getDiffNum();
      UFDouble newAccNum = MathTool.add(view.getNaccumsettnum(), diffNum);
      view.setNaccumsettnum(newAccNum);
      UFDouble diffMny = vo.getDiffMny();
      UFDouble newAccMny = MathTool.add(view.getNaccumsettmny(), diffMny);
      view.setNaccumsettmny(newAccMny);
    }
  }
}
