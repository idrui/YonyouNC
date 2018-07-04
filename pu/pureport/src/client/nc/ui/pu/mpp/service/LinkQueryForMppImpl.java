package nc.ui.pu.mpp.service;

import java.awt.Container;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.bill.convertor.BillToViewConvertor;
import nc.itf.pu.mpp.IBillQueryByNtbParamForMpp;
import nc.pubitf.pu.mpp.ILinkQueryForMpp;
import nc.ui.ic.m45.deal.PurchaseInBillScaleProcessor;
import nc.ui.pu.m20.editor.util.PraybillScaleUtil;
import nc.ui.pu.m21.rule.OrderScaleSetter;
import nc.ui.pu.m23.rule.ArriveUIScaleRule;
import nc.ui.pu.m25.editor.utils.InvoiceUIScaleProcessor;
import nc.ui.pubapp.scale.ListPaneScaleProcessor;
import nc.ui.pubapp.scale.TotalValueScaleProcessor;
import nc.ui.scmf.pub.TBBRelQueryDialog;
import nc.ui.scmf.pub.TBBRelQueryDialog.ITBBQueryScaleProcesser;
import nc.ui.scmf.pub.TBBRelQueryDialog.TBBQueryData;
import nc.vo.ic.m45.entity.PurchaseInViewVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.entity.PraybillViewVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.entity.ArriveViewVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.entity.InvoiceViewVO;
import nc.vo.pu.pub.enumeration.PuNodeKey;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.AppContext;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.tb.obj.NtbParamVO;

import org.apache.commons.lang.ArrayUtils;

public class LinkQueryForMppImpl implements ILinkQueryForMpp {
  private static class QueryScaleProcesser implements ITBBQueryScaleProcesser {

    @Override
    public void setScale(TBBQueryData tbbQueryData) {
      String pk_group = AppContext.getInstance().getPkGroup();
      if (tbbQueryData.getVos() instanceof OrderViewVO[]) {
        new OrderScaleSetter(pk_group).setSingleTableScale(tbbQueryData
            .getBlp());
      }
      else if (tbbQueryData.getVos() instanceof PraybillViewVO[]) {
        new PraybillScaleUtil().setSingleTableScale(tbbQueryData.getBlp(),
            pk_group);
      }
      else if (tbbQueryData.getVos() instanceof InvoiceViewVO[]) {
        new InvoiceUIScaleProcessor().setSingleTableScale(
            tbbQueryData.getBlp(), pk_group);
      }
      else if (tbbQueryData.getVos() instanceof PurchaseInViewVO[]) {
        PurchaseInBillScaleProcessor processor =
            new PurchaseInBillScaleProcessor();
        processor.setAllHead(UFBoolean.TRUE);
        processor.scaleProcess(new ListPaneScaleProcessor(pk_group,
            tbbQueryData.getBlp()), null, new TotalValueScaleProcessor(
            tbbQueryData.getBlp()));
      }
      else if (tbbQueryData.getVos() instanceof ArriveViewVO[]) {
        new ArriveUIScaleRule(pk_group).setSingleTableScale(tbbQueryData
            .getBlp());
      }
    }
  }

  @Override
  public void execLinkQueryForMpp(NtbParamVO param, Container parent)
      throws BusinessException {
    String[] billtypes = param.getBill_type().split("-");
    String execBilltype = billtypes[0];
    String readyBilltype = billtypes[1];
    IBillQueryByNtbParamForMpp service =
        NCLocator.getInstance().lookup(IBillQueryByNtbParamForMpp.class);
    if (POBillType.Order.getCode().equals(execBilltype)) {
      OrderVO[] vos = service.execLinkQuery21(param, readyBilltype);
      OrderViewVO[] views = null;
      if (!ArrayUtils.isEmpty(vos)) {
        views =
            new BillToViewConvertor<OrderVO, OrderViewVO>(OrderViewVO.class)
                .convert(vos);
      }
      TBBQueryData[] tds =
          new TBBQueryData[] {
            new TBBQueryData(PuNodeKey.n40040400RL.code(), views,
                POBillType.Order.getName())
          };
      TBBRelQueryDialog tqd =
          new TBBRelQueryDialog(parent, tds, nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4003001_0", "04003001-0000")/*
                                                                       * @res
                                                                       * "联查采购数据"
                                                                       */);
      tqd.setScaleProcesser(new QueryScaleProcesser());
      tqd.showModal();
    }
    else if (POBillType.Arrive.getCode().equals(execBilltype)) {
      ArriveVO[] vos = service.execLinkQuery23(param, readyBilltype);
      ArriveViewVO[] views = null;
      if (!ArrayUtils.isEmpty(vos)) {
        views =
            new BillToViewConvertor<ArriveVO, ArriveViewVO>(ArriveViewVO.class)
                .convert(vos);
      }
      // this.open(vos, PuNodeCode.n40040800.code(), parent);
      TBBQueryData[] tds =
          new TBBQueryData[] {
            new TBBQueryData(PuNodeKey.n4004080022.code(), views,
                POBillType.Arrive.getName())
          };
      TBBRelQueryDialog tqd =
          new TBBRelQueryDialog(parent, tds, nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4003001_0", "04003001-0000")/*
                                                                       * @res
                                                                       * "联查采购数据"
                                                                       */);
      tqd.setScaleProcesser(new QueryScaleProcesser());
      tqd.showModal();
    }
    else if (POBillType.Invoice.getCode().equals(execBilltype)) {
      InvoiceVO[] vos = service.execLinkQuery25(param, readyBilltype);
      // this.open(vos, PuNodeCode.n40041000.code(), parent);
      InvoiceViewVO[] views = null;
      if (!ArrayUtils.isEmpty(vos)) {
        views =
            new BillToViewConvertor<InvoiceVO, InvoiceViewVO>(
                InvoiceViewVO.class).convert(vos);
      }
      TBBQueryData[] tds =
          new TBBQueryData[] {
            new TBBQueryData(PuNodeKey.n40041000DB.code(), views,
                POBillType.Invoice.getName())
          };
      TBBRelQueryDialog tqd =
          new TBBRelQueryDialog(parent, tds, nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4003001_0", "04003001-0000")/*
                                                                       * @res
                                                                       * "联查采购数据"
                                                                       */);
      tqd.setScaleProcesser(new QueryScaleProcesser());
      tqd.showModal();
    }
    else if (ICBillType.PurchaseIn.getCode().equals(execBilltype)) {
      PurchaseInViewVO[] views = service.execLinkQuery45(param, readyBilltype);
      TBBQueryData[] tds = new TBBQueryData[] {
        new TBBQueryData("45TO25_L", views, ICBillType.PurchaseIn.getName())
      };
      TBBRelQueryDialog tqd =
          new TBBRelQueryDialog(parent, tds, nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4003001_0", "04003001-0000")/*
                                                                       * @res
                                                                       * "联查采购数据"
                                                                       */);
      tqd.setScaleProcesser(new QueryScaleProcesser());
      tqd.showModal();
    }
  }

  @Override
  public void readyLinkQueryForMpp(NtbParamVO param, Container parent)
      throws BusinessException {
    String[] billtypes = param.getBill_type().split("-");
    String readyBilltype = billtypes[0];
    String execBilltype = billtypes[1];
    IBillQueryByNtbParamForMpp service =
        NCLocator.getInstance().lookup(IBillQueryByNtbParamForMpp.class);
    if (readyBilltype.startsWith(POBillType.PrayBill.getCode())) {
      PraybillVO[] prayVOs =
          service.readyLinkQuery20ForTbb(param, execBilltype);
      OrderVO[] vos = service.readyLinkQuery20CloseForTbb(param, execBilltype);
      PraybillViewVO[] prayViews = null;
      if (!ArrayUtils.isEmpty(prayVOs)) {
        prayViews =
            new BillToViewConvertor<PraybillVO, PraybillViewVO>(
                PraybillViewVO.class).convert(prayVOs);
      }
      OrderViewVO[] orderViews = null;
      if (!ArrayUtils.isEmpty(vos)) {
        orderViews =
            new BillToViewConvertor<OrderVO, OrderViewVO>(OrderViewVO.class)
                .convert(vos);
      }
      TBBQueryData[] tds = new TBBQueryData[2];
      tds[0] =
          new TBBQueryData(PuNodeKey.n4004020022.code(), prayViews,
              POBillType.PrayBill.getName());
      tds[1] =
          new TBBQueryData(PuNodeKey.n40040400RL.code(), orderViews,
              POBillType.Order.getName());
      TBBRelQueryDialog tqd =
          new TBBRelQueryDialog(parent, tds, nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4003001_0", "04003001-0000")/*
                                                                       * @res
                                                                       * "联查采购数据"
                                                                       */);
      tqd.setScaleProcesser(new QueryScaleProcesser());
      tqd.showModal();
    }
    else if (readyBilltype.startsWith(POBillType.Order.getCode())) {
      OrderVO[] vos = service.readyLinkQuery21ForTbb(param, execBilltype);
      OrderViewVO[] orderViews = null;
      if (!ArrayUtils.isEmpty(vos)) {
        orderViews =
            new BillToViewConvertor<OrderVO, OrderViewVO>(OrderViewVO.class)
                .convert(vos);
      }
      TBBQueryData[] tds =
          new TBBQueryData[] {
            new TBBQueryData(PuNodeKey.n40040400RL.code(), orderViews,
                POBillType.Order.getName())
          };
      TBBRelQueryDialog tqd =
          new TBBRelQueryDialog(parent, tds, nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4003001_0", "04003001-0000")/*
                                                                       * @res
                                                                       * "联查采购数据"
                                                                       */);
      tqd.setScaleProcesser(new QueryScaleProcesser());
      tqd.showModal();
    }
  }

}
