/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-16 上午09:44:30
 */
package nc.ui.pu.m25.model;

import java.util.ArrayList;
import java.util.List;

import nc.ui.pu.m25.view.InvoiceUIState;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>用于发票维护的管理模型
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-3-16 上午09:44:30
 */
@SuppressWarnings("unchecked")
public class InvoiceBillManageModel extends BillManageModel {

  /**
   * <p>
   * <b>本类主要完成以下功能：</b>
   * <ul>
   * <li>描述了普通发票维护界面的model数据环境信息
   * </ul>
   * <p>
   * <p>
   * 
   * @version 6.0
   * @since 6.0
   * @author zhaoyha
   * @time 2010-3-16 上午09:52:50
   */
  static class NormInvoiceModelContext {
    /** 普通发票维护界面的数据 **/
    private List<InvoiceVO> data = new ArrayList<InvoiceVO>();

    /** 普通发票维护界面的选中数据位置 **/
    private int selectInx;

    /** 普通发票维护界面的选中所有数据位置 **/
    private Integer[] selectInxes;

    /**
     * NormInvoiceModelContext 的构造子
     * 
     * @param data 模型中的数据
     * @param selectInx 当前选择行
     * @param selectInxes 当前选择的所有行
     */
    public NormInvoiceModelContext(List<InvoiceVO> data, int selectInx,
        Integer[] selectInxes) {
      this.data.clear();
      this.data.addAll(data);
      this.selectInx = selectInx;
      this.selectInxes = selectInxes;
    }

    /**
     * @return data
     */
    protected List<InvoiceVO> getData() {
      return this.data;
    }

    /**
     * @return selectInx
     */
    protected int getSelectInx() {
      return this.selectInx;
    }

    /**
     * @return selectInxes
     */
    protected Integer[] getSelectInxes() {
      return this.selectInxes;
    }

    /**
     * @param data 要设置的 data
     */
    protected void setData(List<InvoiceVO> data) {
      this.data = data;
    }

    /**
     * @param selectInx 要设置的 selectInx
     */
    protected void setSelectInx(int selectInx) {
      this.selectInx = selectInx;
    }

    /**
     * @param selectInxes 要设置的 selectInxes
     */
    protected void setSelectInxes(Integer[] selectInxes) {
      this.selectInxes = selectInxes;
    }
  }

  /** 发票UI状态 **/
  private InvoiceUIState invoiceUIState = InvoiceUIState.NORM_INVC;

  /** 普通发票维护时的数据环境信息 **/
  private NormInvoiceModelContext normContext;

  /**
   * 方法功能描述：备份普通发票的模型数据。
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author zhaoyha
   * @time 2010-7-20 下午02:49:46
   */
  public void backupNormContext() {
    this.normContext =
        new NormInvoiceModelContext(this.getData(), this.getSelectedRow(),
            this.getSelectedOperaRows());
    this.initModel(null);
  }

  public InvoiceUIState getInvoiceUIState() {
    return this.invoiceUIState;
  }

  public InvoiceVO getNormInvoice() {
    return this.normContext.getData().get(this.normContext.getSelectInx());
  }

  public void restoreAndAppendFeeInvoice() {
    List<InvoiceVO> feeInvoice = this.getData();
    this.normContext.getData().addAll(feeInvoice);
    this.restoreNormContext();
  }

  /**
   * 方法功能描述：恢复备份的普通发票模型数据。
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author zhaoyha
   * @time 2010-7-20 下午02:49:50
   */
  public void restoreNormContext() {
    this.initModel(new ListToArrayTool<InvoiceVO>()
        .convertToArray(this.normContext.getData()));
    int[] selectInxes =
        ArrayUtils.toPrimitive(this.normContext.getSelectInxes());
    if (!ArrayUtils.isEmpty(selectInxes)) {
      this.setSelectedOperaRows(selectInxes);
    }
    this.setSelectedRow(this.normContext.getSelectInx());
    this.normContext = null;
  }

  @Override
  public void setAppUiState(AppUiState appUiState) {
    if (appUiState instanceof InvoiceUIState) {
      this.invoiceUIState = (InvoiceUIState) appUiState;
    }
    super.setAppUiState(appUiState);
  }

}
