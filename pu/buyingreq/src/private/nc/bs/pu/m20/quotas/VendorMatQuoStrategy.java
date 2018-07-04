package nc.bs.pu.m20.quotas;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pu.m20.entity.SupplierQuotaPara;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmbd.vrm.vm.entity.VendorMaterBVO;

/**
 * 根据供货配额采购
 * 设某一存货与供应商对应关系如下：
 * 　 配额比例 最小订货量 最小订货批量
 * 供应商1 9 50 10
 * 供应商2 8 80 20
 * 供应商3 13 40 8
 * 请购量1000
 * 供应商1=int(max（1000*9/（9+8+13），50）/10))*10=300
 * 供应商2：int(max（1000*8/（9+8+13），80）/20))*20=280
 * 供应商3：int(max(1000-供应商1-供应商 2，40)/8))*8=424
 * 合计：1004。
 * 因３个供应商的数量合计1004大于请购量1000，则生成采购订单时第３个供应商的数量会被调整为420。
 * 
 * @since 6.0
 * @version 2011-4-24 下午04:48:31
 * @author wuxla
 */

public class VendorMatQuoStrategy extends AbstractQuotaStrategy {

  @Override
  public void quota() {
    for (SupplierQuotaPara para : this.getParaList()) {
      this.quota(para);
    }
  }

  private void quota(SupplierQuotaPara para) {
    String pk_purchaseorg = para.getPk_purchaseorg();
    String pk_srcmaterial = para.getPk_srcmaterial();
    List<VendorMaterBVO> venMatList = new ArrayList<VendorMaterBVO>();
    // 配额总量
    double nquotacount = 0;
    for (VendorMaterBVO vendorMaterVO : this.getVendorMaterVOs()) {
      if (pk_purchaseorg.equals(vendorMaterVO.getPk_org())
          && pk_srcmaterial.equals(vendorMaterVO.getPk_srcmaterial())) {
        nquotacount =
            nquotacount + MathTool.nvl(vendorMaterVO.getNquota()).doubleValue();
        venMatList.add(vendorMaterVO);
      }
    }

    if (0 == nquotacount) {
      return;
    }

    double npraynum = para.getNpraynum().doubleValue();
    double nnumcount = 0;
    for (int i = 0; i < venMatList.size(); ++i) {
      VendorMaterBVO vendorMatVO = venMatList.get(i);
      // 供货配额
      double nquota = MathTool.nvl(vendorMatVO.getNquota()).doubleValue();
      // 最小订货量
      double nminiordernum =
          MathTool.nvl(vendorMatVO.getNminiordernum()).doubleValue();
      // 订货批量
      double norderbatchnum =
          MathTool.nvl(vendorMatVO.getNorderbatchnum()).doubleValue();
      double nnum = 0;
      if (npraynum - nnumcount <= 0) {
        break;
      }
      if (i < venMatList.size() - 1) {
        if (0 == norderbatchnum) {
          nnum = npraynum * nquota / nquotacount;
        }
        else {
          // 分配数量=max(请购单数量*供货配额/配额总量，最小订货量)
          nnum = Math.max(npraynum * nquota / nquotacount, nminiordernum);
          // 当分配的量比订货批量还大时,需要使用批量来计算
          if (nnum > nminiordernum && nnum % norderbatchnum != 0) {
            nnum = nnum / norderbatchnum;
            nnum = (Math.floor(nnum) + 1) * norderbatchnum;
          }
        }
      }
      else {
        if (norderbatchnum == 0) {
          nnum = npraynum - nnumcount;
        }
        else {
          nnum = Math.max(npraynum - nnumcount, nminiordernum);
          if (nnum % norderbatchnum != 0) {
            nnum = nnum / norderbatchnum;
            nnum = (Math.floor(nnum) + 1) * norderbatchnum;
          }
        }
      }

      nnumcount = nnumcount + nnum;
      if (nnum > 0) {
        para.getSupplierList().add(vendorMatVO.getPk_supplier());
        para.getNquotanumList().add(new UFDouble(nnum));
      }
    }
  }

}
