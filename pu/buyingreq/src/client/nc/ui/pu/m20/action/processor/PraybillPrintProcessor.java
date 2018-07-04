/**
 * $�ļ�˵��$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-19 ����11:29:39
 */
package nc.ui.pu.m20.action.processor;

import nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction.IBeforePrintDataProcess;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.rule.PrayBillScaleRule;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pubapp.scale.BillVOScaleProcessor;
import nc.vo.pubapp.scale.TotalValueVOScaleProcessor;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�빺����ӡǰ�����羫�ȵ�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-10-19 ����11:29:39
 */
public class PraybillPrintProcessor implements IBeforePrintDataProcess {

  private AbstractAppModel model = null;

  /**
   * @return model
   */
  public AbstractAppModel getModel() {
    return this.model;
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction.IBeforePrintDataProcess#processData(java.lang.Object[])
   */
  @Override
  public Object[] processData(Object[] datas) {
    // ת��Ϊ�빺��vo
    PraybillVO[] vos = (PraybillVO[]) ArrayUtil.convertArrayType(datas);
    // ���ȴ���
    this.processScale(vos);
    return vos;
  }

  /**
   * @param model
   *          Ҫ���õ� model
   */
  public void setModel(AbstractAppModel model) {
    this.model = model;
  }

  /**
   * ������
   * 
   * @param bills
   */
  private void processScale(PraybillVO[] bills) {
    BillVOScaleProcessor scaleProcessor =
        new BillVOScaleProcessor(this.getModel().getContext().getPk_group(),
            bills);
    TotalValueVOScaleProcessor totalScale =
        new TotalValueVOScaleProcessor(bills);
    new PrayBillScaleRule().setScale(scaleProcessor, totalScale);
  }
}
