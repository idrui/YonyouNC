package nc.pubimpl.pu.it;

import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pu.m27.StockQueryAdapter;
import nc.pubitf.ic.m4a.IGeneralInServiceForPuFeeSettle;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m27.entity.GeneralInSettleVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pub.BusinessException;
import nc.vo.scmpub.res.billtype.ICBillType;

/**
 * @since 6.31
 * @version 2013-9-16 下午02:13:19
 * @author mengjian
 */
public class StockQueryAdapterForIT extends StockQueryAdapter {

  public StockQueryAdapterForIT(IQueryScheme queryscheme) {
    super(queryscheme);
  }

  /**
   * 根据单据类型决定查询何种入库单据的入口方法
   * 
   * @param queryscheme
   * @param billType
   * @return
   */
  @Override
  public List<StockSettleVO> queryStockByBillType(String billType) {
    // 查询采购入
    if (ICBillType.PurchaseIn.getCode().equals(billType)) {
      return this.queryM45();
    }
    // added by wangzhqf 2014年5月20日13:48:27 查询其他入
    else if (ICBillType.GeneralIn.getCode().equals(billType)) {
      return this.queryM4A();
    }
    return null;
  }

  @Override
  protected Object getPUorITWhere(String field) {
    return " and " + field + " = 'Y' ";
  }

  @Override
  protected GeneralInSettleVO[] queryGeneralInSettleVOs()
      throws BusinessException {
    GeneralInSettleVO[] m4avos;
    // 进口查询其他入库单
    IGeneralInServiceForPuFeeSettle serv =
        NCLocator.getInstance().lookup(IGeneralInServiceForPuFeeSettle.class);
    m4avos =
        serv.queryGeneralInSettleVOsBySchemeFromIT(
            this.getQueryscheme(),
            (Map<String, String>) this.getQueryscheme().get(
                ICBillType.GeneralIn.getCode()));
    return m4avos;
  }

}
