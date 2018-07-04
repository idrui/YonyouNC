/**
 * $�ļ�˵��$
 * 
 * @author zhyhang
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-15 ����08:01:42
 */
package nc.ui.pu.est.action.body;

import nc.ui.pu.pub.action.PuBodyAddLineAction;
import nc.ui.pub.bill.BillModel;
import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.est.util.EstVODefualtValueUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.bill.BillTempletHeadVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�����������Action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhyhang
 * @time 2010-6-15 ����08:01:42
 */
@SuppressWarnings("serial")
public class EstBodyAddLineAction extends PuBodyAddLineAction {

  private void setDefaultValue(CircularlyAccessibleValueObject vo) {
    AggregatedValueObject aggVo =
        (AggregatedValueObject) this.getModel().getSelectedData();
    if (null == aggVo) {
      return;
    }
    EstVODefualtValueUtil.setFeeEstDefValue((FeeEstVO) vo,
        (GoodsEstVO) aggVo.getParentVO(), this.getModel().getContext()
            .getPk_loginUser());
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.pubapp.uif2app.actions.BodyInsertLineAction#getInsertVO()
   */
  @Override
  protected CircularlyAccessibleValueObject getInsertVO(int index) {
    BillModel bm = this.getCardPanel().getBillModel();
    String bodyClassName =
        bm.getTabvo().getBillMetaDataBusinessEntity().getFullClassName();
    CircularlyAccessibleValueObject vo =
        bm.getBodyValueRowVO(index, bodyClassName);

    this.setDefaultValue(vo);
    return vo;
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.pubapp.uif2app.actions.BodyAddLineAction#onDataInsert()
   */
  @Override
  protected void onDataInsert(int index) {
    BillTempletHeadVO bthead =
        this.getCardPanel().getBillData().getBillTempletVO().getHeadVO();
    String metaclass = bthead.getMetadataclass();
    // �Ƚ�Ԫ���������ÿգ��߷�Ԫ���ݵļ��ط�֧
    bthead.setMetadataclass(null);
    super.onDataInsert(index);
    bthead.setMetadataclass(metaclass);
  }

}
