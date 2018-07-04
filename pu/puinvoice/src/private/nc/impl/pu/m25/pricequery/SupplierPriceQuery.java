package nc.impl.pu.m25.pricequery;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m25.IPriceQuery;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pp.supplierprice.entity.SupplierPrice;
import nc.vo.pp.supplierprice.entity.SupplierPriceQueryVO;
import nc.vo.pp.supplierprice.enumeration.QTSoruceType;
import nc.vo.pu.m25.entity.InvoicePriceQueryVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * ��Ӧ�̼�Ŀ���ѯ��
 * 
 * @since 6.0
 * @version 2011-7-29 ����02:31:49
 * @author �����
 */

public class SupplierPriceQuery implements IPriceQuery {

  public SupplierPriceQuery() {
    //
  }

  @Override
  public void query(InvoicePriceQueryVO[] para) {
    try {
      if (!SysInitGroupQuery.isPPEnabled() || ArrayUtils.isEmpty(para)) {
        return;
      }
      // ת���ɹ�Ӧ�̼�Ŀ���ѯ�۲���
      SupplierPriceQueryVO[] vos = this.getSuppPriceQueryVO(para);
      // ѯ��Ӧ�̼�Ŀ��,���ص��ǵȳ������飬ûѰ���۸����Ϊnull
      // ���ܻ����supplierPrice[0]=null��supplierPrice[1]=null�����
      SupplierPrice[] supplierPrice =
          NCLocator.getInstance()
              .lookup(nc.pubitf.pp.supplierprice.ISupplierPriceQuery.class)
              .query(vos, QTSoruceType.PI);
      if (ArrayUtils.isEmpty(supplierPrice)) {
        return;
      }
      for (int i = 0; i < supplierPrice.length; i++) {
        if (supplierPrice[i] == null) {
          continue;
        }
        // ȡ����λ�ļ۸�
        para[i].setNorigprice(supplierPrice[i].getNorigprice());
        para[i].setNorigtaxprice(supplierPrice[i].getNorigtaxprice());
        // ����˰�ʺͿ�˰���
        para[i].setNtaxrate(supplierPrice[i].getNtaxrate());
        para[i].setFtaxtypeflag(Integer.valueOf(supplierPrice[i]
            .getFtaxtypeflag()));
        para[i].setNNoSubTaxRate(supplierPrice[i].getNnosubtaxrate());
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * ��Ʊѯ�۲��� ��Ӧ�̼�Ŀ��ѯ�۲���ת��
   * 
   * @param para ��Ʊѯ�۲���
   * @return ��Ӧ�̼�Ŀ��ѯ�۲���
   */
  private SupplierPriceQueryVO[] getSuppPriceQueryVO(InvoicePriceQueryVO[] para) {
    SupplierPriceQueryVO[] vos = new SupplierPriceQueryVO[para.length];
    for (int i = 0; i < vos.length; i++) {
      // ѯ�۲������ɹ���֯+��Ӧ��+����+���֣���Ʊ����+��λ
      vos[i] = new SupplierPriceQueryVO();
      vos[i].setCurrency(para[i].getCorigcurrencyid());// ���֣�ԭ�ң�
      vos[i].setDate(para[i].getDbilldate());// ��Ʊ����
      vos[i].setCproductorid(para[i].getCproductorid());// ��������
      vos[i].setPk_org(para[i].getPk_purchaseorg());// �ɹ���֯
      vos[i].setPk_srcmaterial(para[i].getPk_srcmaterial());// ����oid
      vos[i].setPk_supplier(para[i].getPk_supplier());// ��Ӧ��
      vos[i].setCastunitid(para[i].getCastunitid());// ��λ
      vos[i].setCqualitylevelid(para[i].getCqualitylevelid());// �����ȼ�
    }
    return vos;
  }

}
