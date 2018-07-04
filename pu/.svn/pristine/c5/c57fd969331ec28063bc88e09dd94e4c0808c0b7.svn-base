package nc.bs.pu.m20.quotas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.itf.pu.reference.ic.AtpQueryServices;
import nc.itf.pu.reference.ic.M4DPUServices;
import nc.vo.pu.m20.entity.SupplierQuotaPara;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmbd.vrm.vm.entity.VendorMaterBVO;


/**
 * 根据材料领用量采购
 * 配额按材料领用量的比例进行确定。
 * 
 * @since 6.0
 * @version 2011-4-24 下午04:46:08
 * @author wuxla
 */

public class MatDrawQuotaStrategy extends AbstractQuotaStrategy {

  @Override
  public void quota() {

    Map<String, String[]> paramMap = new HashMap<String, String[]>();
    for (SupplierQuotaPara para : this.getParaList()) {
      String pk_srcmaterial = para.getPk_srcmaterial();
      String pk_material = para.getPk_material();
      List<String> list = new ArrayList<String>();
      for (VendorMaterBVO vendorMaterVO : this.getVendorMaterVOs()) {
        if (pk_srcmaterial.equals(vendorMaterVO.getPk_srcmaterial())) {
          list.add(vendorMaterVO.getPk_supplier());
        }
      }
      if (list.size() > 0) {
        // 需要关联采购页签，所以需要VID
        paramMap.put(pk_material, list.toArray(new String[list.size()]));
      }
    }
    // 库存处理返回OID
    Map<String, UFDouble> outMap = M4DPUServices.getVendorQuotas(paramMap);
    if (null == outMap) {
      return;
    }

    for (SupplierQuotaPara para : this.getParaList()) {
      this.quota(para, outMap);
    }
  }

  private void quota(SupplierQuotaPara para, Map<String, UFDouble> outMap) {
    String pk_srcmaterial = para.getPk_srcmaterial();
    String pk_purchaseorg = para.getPk_purchaseorg();
    List<String> vendorList = new ArrayList<String>();
    List<UFDouble> stuffNumList = new ArrayList<UFDouble>();
    double nstuffnumacount = 0;
    Map<String, VendorMaterBVO> vendorMatMap =
        new HashMap<String, VendorMaterBVO>();
    for (VendorMaterBVO vendorMaterVO : this.getVendorMaterVOs()) {
      if (!pk_purchaseorg.equals(vendorMaterVO.getPk_org())
          || !pk_srcmaterial.equals(vendorMaterVO.getPk_srcmaterial())) {
        continue;
      }
      String vendor = vendorMaterVO.getPk_supplier();
      vendorMatMap.put(vendorMaterVO.getPk_supplier(), vendorMaterVO);
      String key = pk_srcmaterial + "|" + vendor;
      if (!outMap.containsKey(key)) {
        continue;
      }
      UFDouble stuffnum = MathTool.nvl(outMap.get(key));
      vendorList.add(vendor);
      stuffNumList.add(stuffnum);
      nstuffnumacount = nstuffnumacount + stuffnum.doubleValue();
    }

    if (0 == vendorList.size() || 0 == nstuffnumacount) {
      return;
    }

    // 需要注意物料需要勾选供应商辅助属性
    Map<String, UFDouble> vendorStoreDataMap =
        AtpQueryServices.queryVendorStoreDatasByOid(
            vendorList.toArray(new String[vendorList.size()]), pk_srcmaterial);
    double npraynum = para.getNpraynum().doubleValue();
    double nnumcount = 0;
    for (int k = 0; k < vendorList.size(); k++) {
      String vendor = vendorList.get(k);
      VendorMaterBVO vendorMaterVO = vendorMatMap.get(vendor);
      double nstuffnum = MathTool.nvl(stuffNumList.get(k)).doubleValue();
      double nminiordernum =
          MathTool.nvl(vendorMaterVO.getNminiordernum()).doubleValue();
      double norderbatchnum =
          MathTool.nvl(vendorMaterVO.getNorderbatchnum()).doubleValue();
      double nnum = 0;
      if (npraynum - nnumcount <= 0) {
        break;
      }
      if (k < vendorList.size() - 1) {
        if (norderbatchnum == 0) {
          nnum = npraynum * nstuffnum / nstuffnumacount;
        }
        else {
          nnum =
              Math.max(npraynum * nstuffnum / nstuffnumacount, nminiordernum);
          if (nnum % norderbatchnum != 0) {
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
      double doubleNum = nnum;
      // 实际生成的采购数量＝配额数量 - 当前库存数量
      UFDouble vendorstoredata =
          vendorStoreDataMap != null ? vendorStoreDataMap.get(vendor)
              : UFDouble.ZERO_DBL;
      UFDouble dIcNum = MathTool.nvl(vendorstoredata);
      doubleNum = nnum - dIcNum.doubleValue();
      if (doubleNum > 0) {
        para.getSupplierList().add(vendor);
        para.getNquotanumList().add(new UFDouble(doubleNum));
      }
    }

  }
}
