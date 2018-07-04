/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-11 ����02:54:14
 */
package nc.ui.pu.pub.action;

import nc.ui.pu.pub.util.BusitypeFilerUtil;
import nc.ui.pubapp.billref.src.action.QueryAction;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.pub.constant.PUQueryConst;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ�ҵ�����̹���
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-10-11 ����02:54:14
 */
public class PUQueryAction extends QueryAction {

  private static final long serialVersionUID = 2903840654809627830L;

  private boolean bizflow = false;

  public boolean isBizflow() {
    return this.bizflow;
  }

  public void setBizflow(boolean bizflow) {
    this.bizflow = bizflow;
  }

  @Override
  protected void executeQuery(IQueryScheme queryScheme) {
    BusitypeFilerUtil.appendWhr(queryScheme, this.getRefContext(),
        this.isBizflow());
    // scheme�����ҵ�����̣���������ѯ��
    queryScheme.put(PUQueryConst.BUSITYPES_QS_KEY, this.getRefContext()
        .getBillReferQuery().getBusitypes());
    // scheme����ӵ�ǰ�������ͻ������ͣ���������ѯ��
    queryScheme.put(PUQueryConst.BILLTYPE_QS_KEY, this.getRefContext()
        .getBillReferQuery().getBillSrcVar().getCurrBillOrTranstype());
    super.executeQuery(queryScheme);
  }

}
