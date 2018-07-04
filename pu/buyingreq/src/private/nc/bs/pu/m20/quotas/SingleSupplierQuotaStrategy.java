package nc.bs.pu.m20.quotas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.m21.pu.m20.IOrderQueryFor20;
import nc.vo.bd.material.pu.MaterialPuVO;
import nc.vo.pu.m20.entity.SupplierQuotaPara;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmbd.vrm.vm.entity.VendorMaterBVO;


/**
 * ����Ӧ�̲ɹ�
 * ����ȷ������ڼ䣬�����ڴ��ڼ��ڵķ�������������������������ָ�˹�Ӧ�����ۼ����ɶ�������������Ҫȷ��������ʱ�䷶Χ����
 * �ٰ��չ�ʽ���������������������=�������������+��������/�� �������͵Ĺ�Ӧ���Ǵ˴���ѡ�Ĺ�Ӧ�̡�
 * ������ѡ��Ӧ�̵���������Ϊ��ʱ�������������Ĺ�Ӧ�̡���������ʱ����Ӧ��������ָ���ģ������ڹ�Ӧ��֮�����ƽ�����䡣
 * 
 * @since 6.0
 * @version 2011-4-24 ����04:47:19
 * @author wuxla
 */

public class SingleSupplierQuotaStrategy extends AbstractQuotaStrategy {

  @Override
  public void quota() {
    for (SupplierQuotaPara para : this.getParaList()) {
      this.quota(para);
    }
  }

  private Map<String, UFDouble> getQuotumMap(List<String> supplierList,
      String pk_material, String pk_srcmaterial, String pk_purchaseorg) {
    String[] pk_suppliers =
        supplierList.toArray(new String[supplierList.size()]);
    Map<String, MaterialPuVO> puvomap = this.getPuvoMap();
    MaterialPuVO matpuvo = puvomap.get(pk_material + "-" + pk_purchaseorg);
    if (matpuvo == null) {
      return null;
    }
    UFDate beginDate = matpuvo.getPebegin();
    UFDate endDate = matpuvo.getPeend();
    IOrderQueryFor20 service =
        NCLocator.getInstance().lookup(IOrderQueryFor20.class);
    try {
      return service.getSupplierQuantum(pk_purchaseorg, pk_suppliers,
          pk_srcmaterial, beginDate, endDate);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  private String getSupplier(List<VendorMaterBVO> vendorMaterVOList,
      Map<String, UFDouble> quotumMap) {
    // ��Ӧ��
    List<String> supplierlist = new ArrayList<String>();
    // ������
    List<UFDouble> quotaProportionList = new ArrayList<UFDouble>();
    // ���
    List<UFDouble> quotaList = new ArrayList<UFDouble>();
    List<Integer> priorityList = new ArrayList<Integer>();

    double dquotaproportion = 0;
    for (VendorMaterBVO vo : vendorMaterVOList) {
      String pk_supplier = vo.getPk_supplier();
      UFDouble nquota = vo.getNquota();
      double dquota = nquota.doubleValue();
      UFDouble quantum = quotumMap.get(pk_supplier);
      double dquantum = quantum != null ? quantum.doubleValue() : 0;
      UFDouble nquotabase = vo.getNquotabase();
      double dquotabase = nquotabase == null ? 0 : nquotabase.doubleValue();
      dquotaproportion = (dquantum + dquotabase) / dquota;
      supplierlist.add(pk_supplier);
      quotaProportionList.add(new UFDouble(dquotaproportion));
      quotaList.add(nquota);
      priorityList.add(vo.getNpriority());
    }
    String[] supplier = supplierlist.toArray(new String[supplierlist.size()]);
    UFDouble[] quotaProportion =
        quotaProportionList.toArray(new UFDouble[quotaProportionList.size()]);
    UFDouble[] quota = quotaList.toArray(new UFDouble[quotaList.size()]);
    Integer[] priority = priorityList.toArray(new Integer[priorityList.size()]);

    for (int i = 0; i < quotaProportion.length - 1; i++) {
      for (int j = i + 1; j < quotaProportion.length; j++) {
        if (quotaProportion[i] != null && quotaProportion[j] != null) {
          // ѡ���������͵Ĺ�Ӧ��
          if (quotaProportion[i].doubleValue() < quotaProportion[j]
              .doubleValue()) {
            ArrayUtil.swap(quotaProportion, i, j);
            ArrayUtil.swap(supplier, i, j);
            ArrayUtil.swap(priority, i, j);
            ArrayUtil.swap(quota, i, j);
          }
          else if (quotaProportion[i].doubleValue() == 0
              && quotaProportion[j].doubleValue() == 0) {
            // ��������Ϊ0ʱ��ѡ��������Ĺ�Ӧ��
            if (quota[i].doubleValue() >= quota[j].doubleValue()) {
              ArrayUtil.swap(supplier, i, j);
              ArrayUtil.swap(priority, i, j);
              ArrayUtil.swap(quota, i, j);
            }
          }
          else if (quotaProportion[i].doubleValue() == quotaProportion[j]
              .doubleValue()) {
            // ����������Ҳ�Ϊ0ʱ��ѡ���ȼ��ߵĹ�Ӧ��
            if (priority[i] != null && priority[j] != null
                && priority[i].intValue() <= priority[j].intValue()) {
              ArrayUtil.swap(supplier, i, j);
              ArrayUtil.swap(priority, i, j);
              ArrayUtil.swap(quota, i, j);
              // ���ȼ���Ϊ��ѡ��һ����Ӧ��(��һ��Ϊ����һ����Ϊ����ѡ��Ϊ�յĹ�Ӧ��)
            }
            else if (priority[j] == null) {
              ArrayUtil.swap(supplier, i, j);
              ArrayUtil.swap(priority, i, j);
              ArrayUtil.swap(quota, i, j);
            }
          }
        }
      }
    }

    return supplier[supplier.length - 1];
  }

  private void quota(SupplierQuotaPara para) {
    String pk_purchaseorg = para.getPk_purchaseorg();
    String pk_srcmaterial = para.getPk_srcmaterial();
    List<VendorMaterBVO> vendorMaterVOList = new ArrayList<VendorMaterBVO>();
    List<String> supplierList = new ArrayList<String>();
    for (VendorMaterBVO vendorMaterVO : this.getVendorMaterVOs()) {
      // ����Ϊnull�����Ҵ���0
      if (pk_purchaseorg.equals(vendorMaterVO.getPk_org())
          && pk_srcmaterial.equals(vendorMaterVO.getPk_srcmaterial())
          && vendorMaterVO.getNquota() != null
          && vendorMaterVO.getNquota().compareTo(UFDouble.ZERO_DBL) > 0) {
        vendorMaterVOList.add(vendorMaterVO);
        supplierList.add(vendorMaterVO.getPk_supplier());
      }
    }
    if (0 == supplierList.size()) {
      return;
    }

    String pk_material = para.getPk_material();
    Map<String, UFDouble> quotumMap =
        this.getQuotumMap(supplierList, pk_material, pk_srcmaterial,
            pk_purchaseorg);
    if (null == quotumMap) {
      return;
    }
    String pk_supplier = this.getSupplier(vendorMaterVOList, quotumMap);
    if (null == pk_supplier) {
      return;
    }
    para.getSupplierList().add(pk_supplier);
    // �����������Ϊ�빺����
    UFDouble npraynum = para.getNpraynum();
    para.getNquotanumList().add(npraynum);
  }
}
