package nc.ui.pu.m20.action.billref;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.scmf.sourcing.sour4pu.ISourcePUService;
import nc.ui.pubapp.billref.src.RefBillModel;
import nc.ui.pubapp.billref.src.RefContext;
import nc.ui.pubapp.billref.src.action.ISuperAction;
import nc.ui.pubapp.billref.src.view.AggVOToViewVOTransfer;
import nc.ui.uif2.NCAction;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmf.sourcing.entity.SupplierPrice;
import nc.vo.scmf.sourcing.entity.SupplierQueryVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * 确定供应商
 * 
 * @since 6.0
 * @version 2011-9-9 上午10:19:07
 * @author wuxla
 */

public class DefaultSupplierAction extends NCAction implements ISuperAction {
  private static final long serialVersionUID = -5112505381813528308L;

  private RefContext refContext;

  public DefaultSupplierAction() {
    this.setBtnName(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004020_0", "04004020-0097")/* @res "确定供应商" */);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    AggregatedValueObject[] aggVOs =
        this.refContext.getRefBill().getSelectVOs();
    if (ArrayUtils.isEmpty(aggVOs)) {
      return;
    }

    PraybillVO[] vos = ArrayUtil.convertArrayType(aggVOs);
    this.setDefaultSupplier(vos);

    RefBillModel refbillmodel = this.getRefBillModel();
    for (PraybillVO vo : vos) {
      for (PraybillItemVO itemVO : vo.getBVO()) {
        refbillmodel.updateBodyRowVO(itemVO.getPk_praybill(),
            itemVO.getPk_praybill_b(), itemVO);
      }
    }
    if (this.refContext.getRefBill().getRefTabUIContainer().isBillShow()) {
      int row =
          this.refContext.getRefBill().getRefListView().getRefListPanel()
              .getHeadTable().getSelectedRow();
      this.refContext.getRefBill().getRefListView().getRefListPanel()
          .setSelectRow(row);
      this.refContext.getRefBill().getRefListView().getRefListPanel()
          .getBillRowManager().refreshBodyRow();
      this.refContext.getRefBill().getRefListView().getRefListPanel()
          .getBillRowManager().synchronizedBodyRowStatus();
    }
    else {
      AggregatedValueObject[] allvos = this.getRefBillModel().getAllBillVOs();
      CircularlyAccessibleValueObject[] viewVOs =
          AggVOToViewVOTransfer.transfer(allvos, this.getRefContext()
              .getRefInfo().getViewVO().getClass());
      this.refContext
          .getRefBill()
          .getRefSingleListView()
          .getRefSingleTableListPanel()
          .getBillViewValueSetter()
          .setHeaderDatas(
              this.refContext.getRefBill().getRefSingleListView()
                  .getRefSingleTableListPanel(), viewVOs);
    }
    // refbillmodel.fireEvent(new AppEvent(
    // nc.ui.uif2.model.AppEventConst.DATA_REFRESHED));

  }

  @Override
  public RefBillModel getRefBillModel() {
    return this.refContext.getRefBill().getRefBillModel();
  }

  @Override
  public RefContext getRefContext() {
    return this.refContext;
  }

  @Override
  public void setRefBillModel(RefBillModel refBillModel) {
    //
  }

  @Override
  public void setRefContext(RefContext refContext) {
    this.refContext = refContext;
  }

  private SupplierPrice[] getSupplierPrice(SupplierQueryVO[] vos) {
    ISourcePUService service =
        NCLocator.getInstance().lookup(ISourcePUService.class);
    try {
      return service.querySupplierInfo(vos);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  private void setDefaultSupplier(PraybillVO[] vos) {
    List<SupplierQueryVO> queryVOList = new ArrayList<SupplierQueryVO>();
    List<PraybillItemVO> itemList = new ArrayList<PraybillItemVO>();
    for (PraybillVO vo : vos) {
      // 和王姐确认取请购单币种
      // 如果不传币种，默认取所有的。所以不用传币种。
      // String ccurrencyid = vo.getHVO().getCcurrencyid();
      for (PraybillItemVO itemVO : vo.getBVO()) {
        if (null == itemVO.getPk_suggestsupplier()) {
          SupplierQueryVO queryVO =
              new SupplierQueryVO(itemVO.getPk_srcmaterial(),
                  itemVO.getPk_purchaseorg(), UFBoolean.FALSE,
                  itemVO.getCastunitid(), null);
          queryVOList.add(queryVO);
          itemList.add(itemVO);
        }
      }
    }

    int size = itemList.size();
    if (0 == size) {
      return;
    }

    SupplierPrice[] supplierVOs =
        this.getSupplierPrice(queryVOList
            .toArray(new SupplierQueryVO[queryVOList.size()]));

    if (supplierVOs == null || supplierVOs.length == 0
        || supplierVOs.length != size) {
      return;
    }

    for (int i = 0; i < size; ++i) {
      if (supplierVOs[i] != null) {
        String pk_supplier = supplierVOs[i].getPk_supplier();
        PraybillItemVO itemVO = itemList.get(i);
        itemVO.setPk_suggestsupplier(pk_supplier);
      }
    }
  }
}
