package nc.vo.pu.m21.listener;

import nc.bs.businessevent.IBusinessEvent;
import nc.bs.businessevent.IBusinessListener;
import nc.bs.businessevent.bd.BDCommonEvent;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.bd.bankaccount.cust.CustBankaccUnionVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * 供应商银行账户删除监听
 * 
 * @since 6.3
 * @version 2013-1-23 下午01:36:22
 * @author lixyp
 */
public class SupplierBankDelListener implements IBusinessListener {

  @Override
  public void doAction(IBusinessEvent event) throws BusinessException {
    BDCommonEvent bdCommonEvent = (BDCommonEvent) event;

    Object[] bankAccVos = bdCommonEvent.getNewObjs();
    String[] pk_banks = new String[bankAccVos.length];
    for (int i = 0; i < bankAccVos.length; i++) {
      pk_banks[i] =
          ((CustBankaccUnionVO) bankAccVos[i]).getCustbankVO()
              .getPk_bankaccsub();
    }
    int refCount = this.getRefBankCount(pk_banks);
    if (refCount > 0) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0364")/*
                                                                   * @res
                                                                   * "该账户已被采购订单引用，无法删除。"
                                                                   */);
    }
  }

  /**
   * 查询关联的订单。
   * 
   * @param pk_banks 银行账户子户pk数组
   * @return 引用数量
   */
  private int getRefBankCount(String[] pk_banks) {
    SqlBuilder builder = new SqlBuilder();
    builder.append("select count(*) from po_order where dr = 0 and ");
    builder.append(OrderHeaderVO.PK_BANKDOC, pk_banks);
    builder.append(" and " + OrderHeaderVO.BISLATEST, UFBoolean.TRUE);

    String[] datas =
        new DataAccessUtils().query(builder.toString())
            .toOneDimensionStringArray();
    if (datas != null && datas.length > 0) {
      return Integer.valueOf(datas[0]).intValue();
    }
    return 0;
  }
}
