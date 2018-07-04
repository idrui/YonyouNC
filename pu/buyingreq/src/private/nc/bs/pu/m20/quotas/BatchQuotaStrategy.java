package nc.bs.pu.m20.quotas;

import nc.vo.pu.m20.entity.SupplierQuotaPara;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmbd.vrm.vm.entity.VendorMaterBVO;

/**
 * 根据批量能力采购
 * 以公司为单位，当请购的数量超过第一个供应商（按供应商存货对应关系中的优先级）的批量能力时，将余下的数量转给第二个供应商，依此类推，直到请购数量分配完成。
 * 当一个存货的请购数量之和大于与此存货有关的供应商的批量之和时，则余下的部份不生成采购订单。
 * 
 * @since 6.0
 * @version 2011-4-24 下午04:44:29
 * @author wuxla
 */

public class BatchQuotaStrategy extends AbstractQuotaStrategy {

  @Override
  public void quota() {
    for (SupplierQuotaPara para : this.getParaList()) {
      this.quota(para);
    }
  }

  private void quota(SupplierQuotaPara para) {
    String pk_purchaseorg = para.getPk_purchaseorg();
    String pk_srcmaterial = para.getPk_srcmaterial();
    UFDouble npraynum = para.getNpraynum();
    for (VendorMaterBVO vendorMaterVO : this.getVendorMaterVOs()) {
      if (!pk_purchaseorg.equals(vendorMaterVO.getPk_org())
          || !pk_srcmaterial.equals(vendorMaterVO.getPk_srcmaterial())) {
        continue;
      }

      if (null == vendorMaterVO.getNpriority()
          || null == vendorMaterVO.getNbatchnum()) {
        continue;
      }

      UFDouble nbatchnum = vendorMaterVO.getNbatchnum();
      if (MathTool.compareTo(npraynum, nbatchnum) <= 0) {
        para.getSupplierList().add(vendorMaterVO.getPk_supplier());
        para.getNquotanumList().add(npraynum);
        break;
      }
      para.getSupplierList().add(vendorMaterVO.getPk_supplier());
      para.getNquotanumList().add(nbatchnum);
      npraynum = MathTool.sub(npraynum, nbatchnum);
    }
  }
}
