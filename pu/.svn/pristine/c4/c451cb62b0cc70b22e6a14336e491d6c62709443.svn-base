package nc.ui.pu.est.action.body;

import nc.ui.pu.pub.action.PuBodyInsertLineAction;
import nc.ui.pub.bill.BillModel;
import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.est.util.EstVODefualtValueUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.bill.BillTempletHeadVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>表体的插入行Action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhyhang
 * @time 2010-6-15 下午05:25:43
 */
@SuppressWarnings("serial")
public class EstBodyInsertLineAction extends PuBodyInsertLineAction {

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
   * 父类方法重写
   * 
   * @see nc.ui.pubapp.uif2app.actions.BodyInsertLineAction#getInsertVO()
   */
  @Override
  protected CircularlyAccessibleValueObject getInsertVO() {
    CircularlyAccessibleValueObject vo = super.getInsertVO();
    if (null == vo) {
      BillModel bm = this.getCardPanel().getBillModel();
      String bodyClassName =
          bm.getTabvo().getBillMetaDataBusinessEntity().getFullClassName();
      vo = bm.getBodyValueRowVO(this.getCurrentSelectIndex(), bodyClassName);
    }
    this.setDefaultValue(vo);
    return vo;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pubapp.uif2app.actions.BodyInsertLineAction#onDataInsert()
   */
  @Override
  protected void onDataInsert() {
    BillTempletHeadVO bthead =
        this.getCardPanel().getBillData().getBillTempletVO().getHeadVO();
    String metaclass = bthead.getMetadataclass();
    // 先将元数据属性置空，走非元数据的加载分支
    bthead.setMetadataclass(null);
    super.onDataInsert();
    bthead.setMetadataclass(metaclass);
  }

}
