package nc.itf.pu.mpp;

import nc.vo.ic.m45.entity.PurchaseInViewVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pub.BusinessException;
import nc.vo.tb.obj.NtbParamVO;

public interface IBillQueryByNtbParamForMpp {
  OrderVO[] execLinkQuery21(NtbParamVO param, String readyBilltype)
      throws BusinessException;

  ArriveVO[] execLinkQuery23(NtbParamVO param, String readyBilltype)
      throws BusinessException;

  InvoiceVO[] execLinkQuery25(NtbParamVO param, String readyBilltype)
      throws BusinessException;

  PurchaseInViewVO[] execLinkQuery45(NtbParamVO param, String readyBilltype)
      throws BusinessException;

  OrderVO[] readyLinkQuery20CloseForTbb(NtbParamVO param, String exebilltype)
      throws BusinessException;

  PraybillVO[] readyLinkQuery20ForTbb(NtbParamVO param, String exebilltype)
      throws BusinessException;

  OrderVO[] readyLinkQuery21ForTbb(NtbParamVO param, String exebilltype)
      throws BusinessException;
}
