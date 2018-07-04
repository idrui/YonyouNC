/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-28 下午03:59:28
 */
package nc.vo.pu.m21.rule;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.pu.m21.IOrderSupplierQuery;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.query.supplier.SupplierInfo;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>补充供应商信息
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-28 下午03:59:28
 */
public class CoopSupplierRule implements IRule<OrderVO> {

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(OrderVO[] vos) {
    for (OrderVO vo : vos) {
      BillHelper<OrderVO> helper = new BillHelper<OrderVO>(vo);

      // 获得供应商信息
      SupplierInfo supplier = this.getSupplierInfo(helper);

      // 设置供应商的默认值
      SupplierDefaultValue vendorDefaultValue =
          new SupplierDefaultValue(helper);
      vendorDefaultValue.setDefaultValueNotClear(supplier);
    }
  }

  /**
   * 方法功能描述：供应商信息查询
   * <p>
   * <b>参数说明</b>
   * 
   * @param keyValue
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-28 上午10:22:24
   */
  private SupplierInfo getSupplierInfo(IKeyValue keyValue) {
    String pk_supplier =
        (String) keyValue.getHeadValue(OrderHeaderVO.PK_SUPPLIER);
    if (pk_supplier == null) {
      return null;
    }
    String pk_purchaseorg =
        (String) keyValue.getHeadValue(OrderHeaderVO.PK_ORG);
    SupplierInfo supplier = null;
    try {
      supplier =
          NCLocator.getInstance().lookup(IOrderSupplierQuery.class)
              .querySupplier(pk_supplier, pk_purchaseorg);
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);

    }
    return supplier;
  }
}
