package nc.ui.pu.service;

import java.awt.Container;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.bill.convertor.BillToViewConvertor;
import nc.pubitf.pu.tbb.ILinkQueryForTbb;
import nc.pubitf.pu.tbb.IPULinkQueryForTbb;
import nc.ui.pu.m20.editor.util.PraybillScaleUtil;
import nc.ui.pu.m21.rule.OrderScaleSetter;
import nc.ui.pu.m25.editor.utils.InvoiceUIScaleProcessor;
import nc.ui.scmf.pub.TBBRelQueryDialog;
import nc.ui.scmf.pub.TBBRelQueryDialog.ITBBQueryScaleProcesser;
import nc.ui.scmf.pub.TBBRelQueryDialog.TBBQueryData;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.entity.PraybillViewVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.entity.InvoiceViewVO;
import nc.vo.pu.pub.enumeration.PuNodeKey;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.tb.dailyexe.NtbLinkQueryData;
import nc.vo.tb.obj.NtbParamVO;

/**
 * @since 6.0
 * @version 2011-6-22 下午03:17:19
 * @author wuxla
 */

public class PULinkQueryForTbbImpl implements IPULinkQueryForTbb {

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
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public void linkQueryForTbb(NtbLinkQueryData data, Container parent)
      throws BusinessException {
    try {
      List<NtbParamVO> paramList = (List<NtbParamVO>) data.getUserObject();
      NtbParamVO param = paramList.get(0);
      String billtype = param.getBill_type();
      ILinkQueryForTbb service =
          NCLocator.getInstance().lookup(ILinkQueryForTbb.class);
      if (POBillType.PrayBill.getCode().equals(billtype)) {
        PraybillVO[] vos = service.linkQuery20ForTbb(param);
        PraybillViewVO[] views =
            new BillToViewConvertor<PraybillVO, PraybillViewVO>(
                PraybillViewVO.class).convert(vos);
        TBBQueryData[] tds =
            new TBBQueryData[] {
              new TBBQueryData(PuNodeKey.n4004020022.code(), views,
                  POBillType.PrayBill.getName())
            };
        TBBRelQueryDialog tqd =
            new TBBRelQueryDialog(parent, tds, nc.vo.ml.NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4004090_0", "04004090-0001")/*
                                                                         * @res
                                                                         * "联查采购数据"
                                                                         */);
        tqd.setScaleProcesser(new QueryScaleProcesser());
        tqd.showModal();
      }
      else if (POBillType.Order.getCode().equals(billtype)) {
        OrderVO[] vos = service.linkQuery21ForTbb(param);
        OrderViewVO[] views =
            new BillToViewConvertor<OrderVO, OrderViewVO>(OrderViewVO.class)
                .convert(vos);
        TBBQueryData[] tds =
            new TBBQueryData[] {
              new TBBQueryData(PuNodeKey.n40040400RL.code(), views,
                  POBillType.Order.getName())
            };
        TBBRelQueryDialog tqd =
            new TBBRelQueryDialog(parent, tds, nc.vo.ml.NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4004090_0", "04004090-0001")/*
                                                                         * @res
                                                                         * "联查采购数据"
                                                                         */);
        tqd.setScaleProcesser(new QueryScaleProcesser());
        tqd.showModal();
      }
      else if (POBillType.Invoice.getCode().equals(billtype)) {
        InvoiceVO[] vos = service.linkQuery25ForTbb(param);
        InvoiceViewVO[] views =
            new BillToViewConvertor<InvoiceVO, InvoiceViewVO>(
                InvoiceViewVO.class).convert(vos);
        TBBQueryData[] tds =
            new TBBQueryData[] {
              new TBBQueryData(PuNodeKey.n40041000DB.code(), views,
                  POBillType.Invoice.getName())
            };
        TBBRelQueryDialog tqd =
            new TBBRelQueryDialog(parent, tds, nc.vo.ml.NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4004090_0", "04004090-0001")/*
                                                                         * @res
                                                                         * "联查采购数据"
                                                                         */);
        tqd.setScaleProcesser(new QueryScaleProcesser());
        tqd.showModal();
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

  }

}
