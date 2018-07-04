package nc.ui.pu.m20.action.billref;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;

import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.entity.SupplierQuotaPara;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.calculator.HslParseUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleUtils;

import nc.itf.pu.m20.ISupplierQuotas;

import nc.bs.framework.common.NCLocator;

import nc.ui.pubapp.billref.src.RefBillModel;
import nc.ui.pubapp.billref.src.RefContext;
import nc.ui.pubapp.billref.src.VOFakePkControl;
import nc.ui.pubapp.billref.src.action.ISuperAction;
import nc.ui.pubapp.billref.src.view.AggVOToViewVOTransfer;
import nc.ui.uif2.NCAction;

/**
 * 配额分配
 * 
 * @since 6.0
 * @version 2011-4-18 下午05:14:29
 * @author wuxla
 */

public class SupplierQuotasAction extends NCAction implements ISuperAction {

  private static final long serialVersionUID = -3414584581859199049L;

  private RefContext refContext;

  public SupplierQuotasAction() {
    this.setBtnName(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004020_0", "04004020-0000")/* @res "配额分配" */);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    AggregatedValueObject[] aggVOs =
        this.refContext.getRefBill().getSelectVOs();
    if (ArrayUtils.isEmpty(aggVOs)) {
      return;
    }

    PraybillVO[] vos = ArrayUtil.convertArrayType(aggVOs);
    Map<String, SupplierQuotaPara> map = this.getSupplierQuotas(vos);
    if (null == map || 0 == map.size()) {
      return;
    }

    Set<String> set = map.keySet();
    List<PraybillVO> list = new ArrayList<PraybillVO>();
    for (PraybillVO vo : vos) {
      if (this.needQuota(set, vo)) {
        list.add(vo);
      }
    }
    if (list.size() == 0) {
      return;
    }
    RefBillModel refbillmodel = this.refContext.getRefBill().getRefBillModel();

    for (PraybillVO vo : list) {
      this.setQuotaVO(vo, map, refbillmodel);
    }

    for (PraybillVO vo : list) {
      String pk_praybill = vo.getHVO().getPk_praybill();
      for (PraybillItemVO itemVO : vo.getBVO()) {
        refbillmodel.getRowSelectManager().selectBodyRow(pk_praybill,
            itemVO.getPk_praybill_b(), true);
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

    // 更新界面
    // refbillmodel.fireEvent(new AppEvent(AppEventConst.DATA_REFRESHED));
    // this.refContext.getRefBill().getRefListView().getRefListPanel()
    // .getBillRowManager().synchronizedAllRowStatus();
    // this.refContext.getRefBill().getRefSingleListView()
    // .getRefSingleTableListPanel().getBillViewRowManager()
    // .synchronizedAllRowStatus();

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

  private Map<String, SupplierQuotaPara> getSupplierQuotas(PraybillVO[] vos) {
    List<SupplierQuotaPara> list = new ArrayList<SupplierQuotaPara>();
    UFDate date = AppContext.getInstance().getBusiDate();
    for (PraybillVO vo : vos) {
      for (PraybillItemVO itemVO : vo.getBVO()) {
        UFDouble ncanquotanum =
            MathTool.sub(itemVO.getNnum(), itemVO.getNaccumulatenum());
        if (ncanquotanum.compareTo(UFDouble.ZERO_DBL) <= 0) {
          continue;
        }

        itemVO.setNnum(ncanquotanum);
        itemVO.setNaccumulatenum(UFDouble.ZERO_DBL);
        SupplierQuotaPara para = new SupplierQuotaPara();
        para.setDate(date);
        // 配额分配主数量应该是主数量-累计订单数量
        para.setNpraynum(ncanquotanum);

        para.setPk_material(itemVO.getPk_material());
        para.setPk_srcmaterial(itemVO.getPk_srcmaterial());
        para.setPk_praybill_b(itemVO.getPk_praybill_b());
        para.setPk_purchaseorg(itemVO.getPk_purchaseorg());
        para.setPk_praybill(itemVO.getPk_praybill());
        list.add(para);
      }
    }
    if (list.size() == 0) {
      return null;
    }
    ISupplierQuotas service =
        NCLocator.getInstance().lookup(ISupplierQuotas.class);
    try {
      return service.getSupplierQuotas(list);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  private boolean needQuota(Set<String> set, PraybillVO vo) {
    for (PraybillItemVO itemVO : vo.getBVO()) {
      if (set.contains(itemVO.getPk_praybill() + itemVO.getPk_praybill_b())) {
        return true;
      }
    }
    return false;
  }

  private void setNastnum(PraybillItemVO itemVO) {
    String pk_group = itemVO.getPk_group();
    UFDouble nnum = itemVO.getNnum();
    String vchangerate = itemVO.getVchangerate();
    ScaleUtils scale = new ScaleUtils(pk_group);
    UFDouble nastnum = HslParseUtil.hslDivUFDouble(vchangerate, nnum);
    nastnum = scale.adjustNumScale(nastnum, itemVO.getCastunitid());
    itemVO.setNastnum(nastnum);
  }

  private void setQuotaVO(PraybillVO vo, Map<String, SupplierQuotaPara> map,
      RefBillModel refbillmodel) {
    int i = 0;
    List<PraybillItemVO> itemList = new ArrayList<PraybillItemVO>();
    PraybillItemVO[] itemVOs = vo.getBVO();
    for (PraybillItemVO itemVO : itemVOs) {
      String pk_praybill_b = itemVO.getPk_praybill_b();
      SupplierQuotaPara para = map.get(itemVO.getPk_praybill() + pk_praybill_b);
      if (null == para) {
        String pk = VOFakePkControl.FAKE_PK_SUFIX + i;
        itemVO.setPk_praybill_b(pk);
        itemList.add(itemVO);
        i++;
        continue;
      }

      // 分配数量应该是主数量-累计订单数量
      // UFDouble nnum =
      // MathTool.sub(itemVO.getNnum(), itemVO.getNaccumulatenum());
      UFDouble ntotalnum = UFDouble.ZERO_DBL;
      List<String> supplierList = para.getSupplierList();
      List<UFDouble> quotanumList = para.getNquotanumList();
      for (int j = 0; j < supplierList.size(); ++j) {
        PraybillItemVO newItemVO = (PraybillItemVO) itemVO.clone();
        newItemVO.setPk_suggestsupplier(supplierList.get(j));
        // 不管是否是最后一个供应商 都取供应商配额分配数量
        // if (j == supplierList.size() - 1) {
        // newItemVO.setNnum(MathTool.sub(nnum, ntotalnum));
        // }
        // else {
        newItemVO.setNnum(quotanumList.get(j));
        // }
        this.setNastnum(newItemVO);
        String pk = VOFakePkControl.FAKE_PK_SUFIX + i;
        i++;
        newItemVO.setPk_praybill_b(pk);
        itemList.add(newItemVO);
        ntotalnum = MathTool.add(ntotalnum, quotanumList.get(j));
      }
    }
    String pk_praybill = vo.getHVO().getPk_praybill();
    itemVOs = itemList.toArray(new PraybillItemVO[itemList.size()]);
    vo.setBVO(itemVOs);
    refbillmodel.updateBodyRowVOs(pk_praybill, itemVOs);
  }

}
