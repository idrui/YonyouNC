/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-19 上午11:17:08
 */
package nc.vo.pu.m25.entity;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;
import nc.vo.pubapp.pattern.model.tool.BillComposite;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购发票的视图VO
 * <li>包含了InvoiceVO的全部数据
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-19 上午11:17:08
 */
public class InvoiceViewVO extends AbstractDataView {

  /**
   * 
   */
  private static final long serialVersionUID = -8255277006630949054L;

  public static InvoiceVO[] getInvoiceVO(AbstractDataView[] views) {
    if (ArrayUtils.isEmpty(views)) {
      return null;
    }
    List<InvoiceHeaderVO> headers = new ArrayList<InvoiceHeaderVO>();
    List<InvoiceItemVO> items = new ArrayList<InvoiceItemVO>();
    for (AbstractDataView view : views) {
      headers.add((InvoiceHeaderVO) view.getVO(InvoiceHeaderVO.class));
      items.add((InvoiceItemVO) view.getVO(InvoiceItemVO.class));
    }

    BillComposite<InvoiceVO> bc = new BillComposite<InvoiceVO>(InvoiceVO.class);
    InvoiceVO tempVO = new InvoiceVO();
    bc.append(tempVO.getMetaData().getParent(),
        headers.toArray(new InvoiceHeaderVO[headers.size()]));
    bc.append(tempVO.getMetaData().getVOMeta(InvoiceItemVO.class),
        items.toArray(new InvoiceItemVO[items.size()]));
    return bc.composite();
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pubapp.pattern.model.entity.view.AbstractDataView#getMetaData()
   */
  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance().getBillViewMeta(InvoiceVO.class);
  }

  /** 累计本币结算金额 **/
  public UFDouble getNaccumsettmny() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NACCUMSETTMNY);
  }

  /** 累计结算数量 **/
  public UFDouble getNaccumsettnum() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NACCUMSETTNUM);
  }

  /** 累计本币结算金额 **/
  public void setNaccumsettmny(UFDouble naccumsettmny) {
    this.setAttributeValue(InvoiceItemVO.NACCUMSETTMNY, naccumsettmny);
  }

  /** 累计结算数量 **/
  public void setNaccumsettnum(UFDouble naccumsettnum) {
    this.setAttributeValue(InvoiceItemVO.NACCUMSETTNUM, naccumsettnum);
  }

}
