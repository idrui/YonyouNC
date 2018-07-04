/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-13 ����03:54:56
 */
package nc.bs.pu.m21.maintain.rule;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.bd.supplier.SupplierPubService;
import nc.vo.bd.supplier.stock.SupStockVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              ��鹩Ӧ���Ƿ��Ѷ���
 * @scene
 *        �ɹ���������
 * @param ��
 * @since 6.3
 * @version 2014-10-20 ����3:31:33
 * @author luojw
 */
public class SupplierFrozeChkRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    for (OrderVO vo : vos) {
      // ���˻���������Ҫ��鹩Ӧ���Ƿ��Ѿ���������
      if (UFBoolean.FALSE.equals(vo.getHVO().getBreturn())) {
        this.checkSupplierFreeze(vo);
      }
    }
  }

  private void checkSupplierFreeze(OrderVO vo) {
    String pk_supplier = vo.getHVO().getPk_supplier();
    String pk_org = vo.getHVO().getPk_org();
    SupStockVO[] suppliers = SupplierPubService.getSupStockVO(new String[] {
      pk_supplier
    }, pk_org, new String[] {
      SupStockVO.ORDERFREEZEFLAG
    });

    if (ArrayUtils.isEmpty(suppliers)) {
      return;
    }

    if (ValueUtils.getBoolean(suppliers[0].getOrderfreezeflag())) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4004030_0", "04004030-0245", null, new String[] {
            vo.getHVO().getVbillcode()
          })/* {0}�Ĺ�Ӧ���Ѿ��������ᣬ���������� */);
    }
  }
}
