/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-28 ����03:59:28
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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���乩Ӧ����Ϣ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-28 ����03:59:28
 */
public class CoopSupplierRule implements IRule<OrderVO> {

  /**
   * ���෽����д
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(OrderVO[] vos) {
    for (OrderVO vo : vos) {
      BillHelper<OrderVO> helper = new BillHelper<OrderVO>(vo);

      // ��ù�Ӧ����Ϣ
      SupplierInfo supplier = this.getSupplierInfo(helper);

      // ���ù�Ӧ�̵�Ĭ��ֵ
      SupplierDefaultValue vendorDefaultValue =
          new SupplierDefaultValue(helper);
      vendorDefaultValue.setDefaultValueNotClear(supplier);
    }
  }

  /**
   * ����������������Ӧ����Ϣ��ѯ
   * <p>
   * <b>����˵��</b>
   * 
   * @param keyValue
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-28 ����10:22:24
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
      // ��־�쳣
      ExceptionUtils.wrappException(e);

    }
    return supplier;
  }
}
