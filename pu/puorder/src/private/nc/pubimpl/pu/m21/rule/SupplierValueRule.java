/**
 * $�ļ�˵��$
 * 
 * @author lixyp
 * @version 6.1
 * @see
 * @since 6.1
 * @time 2011-12-20 ����08:35:33
 */
package nc.pubimpl.pu.m21.rule;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.pu.m21.IOrderSupplierQuery;
import nc.itf.scmpub.reference.uap.bd.accesor.SupplierAccessor;
import nc.vo.bd.accessor.IBDData;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.query.supplier.SupplierInfo;
import nc.vo.pu.m21.rule.SupplierDefaultValue;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���ù�Ӧ�̵�Ĭ��ֵ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.1
 * @since 6.1
 * @author lixyp
 * @time 2011-12-20 ����10:35:33
 */
public class SupplierValueRule implements IRule<OrderVO> {

  /**
   * ���෽����д
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    this.setSupplierValues(vos);
  }

  /**
   * �����������������ù�Ӧ����Ϣ
   * <p>
   * <b>����˵��</b>
   * 
   * @param orderVOs ����vo����
   * @since 6.1
   * @author lixyp
   * @time 2011-12-20 ����01:57:09
   */
  private void setSupplierValues(OrderVO[] orderVOs) {
    IOrderSupplierQuery query =
        NCLocator.getInstance().lookup(IOrderSupplierQuery.class);
    for (OrderVO orderVO : orderVOs) {
      if (null == orderVO) {
        continue;
      }
      OrderHeaderVO headerVO = orderVO.getHVO();
      if (null == headerVO) {
        continue;
      }
      String pk_purchaseorg = headerVO.getPk_org();
      String pk_supplier = headerVO.getPk_supplier();
      if (StringUtil.isEmptyWithTrim(pk_purchaseorg)
          || StringUtil.isEmptyWithTrim(pk_supplier)) {
        continue;
      }
      // ��ù�Ӧ����Ϣ
      SupplierInfo supplier = null;
      try {
        supplier = query.querySupplier(pk_supplier, pk_purchaseorg);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }

      IKeyValue keyValue = new BillHelper<OrderVO>(orderVO);
      SupplierDefaultValue supplierValue = new SupplierDefaultValue(keyValue);
      supplierValue.setDefaultValueNotClear(supplier);

      if (null == orderVO.getHVO().getPk_dept_v()) {
        IBDData data = SupplierAccessor.getDocByPk(pk_supplier);
        String name = data != null ? data.getName().toString() : "";
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4004030_0", "04004030-0296", null, new String[] {
              name
            })/* �����ù�Ӧ��{0}ר�ܲ��� */);
      }
    }
  }
}
