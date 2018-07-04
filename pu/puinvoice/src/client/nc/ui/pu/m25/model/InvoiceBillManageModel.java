/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-16 ����09:44:30
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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���ڷ�Ʊά���Ĺ���ģ��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-3-16 ����09:44:30
 */
@SuppressWarnings("unchecked")
public class InvoiceBillManageModel extends BillManageModel {

  /**
   * <p>
   * <b>������Ҫ������¹��ܣ�</b>
   * <ul>
   * <li>��������ͨ��Ʊά�������model���ݻ�����Ϣ
   * </ul>
   * <p>
   * <p>
   * 
   * @version 6.0
   * @since 6.0
   * @author zhaoyha
   * @time 2010-3-16 ����09:52:50
   */
  static class NormInvoiceModelContext {
    /** ��ͨ��Ʊά����������� **/
    private List<InvoiceVO> data = new ArrayList<InvoiceVO>();

    /** ��ͨ��Ʊά�������ѡ������λ�� **/
    private int selectInx;

    /** ��ͨ��Ʊά�������ѡ����������λ�� **/
    private Integer[] selectInxes;

    /**
     * NormInvoiceModelContext �Ĺ�����
     * 
     * @param data ģ���е�����
     * @param selectInx ��ǰѡ����
     * @param selectInxes ��ǰѡ���������
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
     * @param data Ҫ���õ� data
     */
    protected void setData(List<InvoiceVO> data) {
      this.data = data;
    }

    /**
     * @param selectInx Ҫ���õ� selectInx
     */
    protected void setSelectInx(int selectInx) {
      this.selectInx = selectInx;
    }

    /**
     * @param selectInxes Ҫ���õ� selectInxes
     */
    protected void setSelectInxes(Integer[] selectInxes) {
      this.selectInxes = selectInxes;
    }
  }

  /** ��ƱUI״̬ **/
  private InvoiceUIState invoiceUIState = InvoiceUIState.NORM_INVC;

  /** ��ͨ��Ʊά��ʱ�����ݻ�����Ϣ **/
  private NormInvoiceModelContext normContext;

  /**
   * ��������������������ͨ��Ʊ��ģ�����ݡ�
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @since 6.0
   * @author zhaoyha
   * @time 2010-7-20 ����02:49:46
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
   * ���������������ָ����ݵ���ͨ��Ʊģ�����ݡ�
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @since 6.0
   * @author zhaoyha
   * @time 2010-7-20 ����02:49:50
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
