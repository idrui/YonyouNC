/**
 * $�ļ�˵��$ 
 *
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-5 ����07:39:03
 */
package nc.bs.pu.m20.pf.function;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.scmbd.vrm.vm.IVendorMaterialQuery;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.scmbd.vrm.vm.entity.VendorMaterBVO;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��Ӧ�������ϼ��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-7-5 ����07:39:03
 */
public class SupplierMaterial {
  /**
   * �������������� ��Ӧ�������Ϲ�ϵУ��У��<br>
   * <p>
   * <b>����˵��</b>
   * 
   * @param vo
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-7-5 ����06:19:35
   */
  public UFBoolean checkSupplierMaterial(AggregatedValueObject aggVO)
      throws BusinessException {

    try {
      PraybillVO vo = (PraybillVO) aggVO;
      // ȡ��Ӧ�̹�����
      MapList<String,String> vrmMap = this.getVendorMatVOs(aggVO);

      if (vrmMap==null||vrmMap.toMap().isEmpty()) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004020_0", "04004020-0096"));
        /** @res ���壬�����빩Ӧ�̲�ƥ�䣡 **/
      }
      // ������� <���ϣ���Ӧ��>
      List<String> notMatList = new ArrayList<String>();
      for (PraybillItemVO itemVO : vo.getBVO()) {
        String supplier = itemVO.getPk_suggestsupplier();
        String pk_material = itemVO.getPk_material();
        String pk_purorg = itemVO.getPk_purchaseorg();
        List<String> spList = vrmMap.get(pk_material+pk_purorg);
        if(spList!=null&&supplier!=null&&spList.contains(supplier)){
          continue;
        }
        notMatList.add(pk_material);
      }
      if (CollectionUtils.isEmpty(notMatList)) {
        return UFBoolean.TRUE;
      }
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004020_0", "04004020-0096"));
      /** @res ���壬�����빩Ӧ�̲�ƥ�䣡 **/
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return UFBoolean.TRUE;
  }

  private void getSuppliers(PraybillVO vo, List<String> suppliers) {
    for (PraybillItemVO itemvo : vo.getBVO()) {
      if (StringUtils.isBlank(itemvo.getPk_suggestsupplier())) {
        continue;
      }
      suppliers.add(itemvo.getPk_suggestsupplier());
    }
  }

  /**
   * ȡ����Ӧ�̹�����
   * 
   * @param aggVO
   * @return
   */
  private MapList<String,String> getVendorMatVOs(AggregatedValueObject aggVO) {

    PraybillVO vo = (PraybillVO) aggVO;
    List<String> suppliers = new ArrayList<String>();
    this.getSuppliers(vo, suppliers);
    if (CollectionUtils.isEmpty(suppliers)) {
      return null;
    }
    Set<String> purorgs = new HashSet<String>();
    for (PraybillItemVO item : vo.getBVO()) {
      purorgs.add(item.getPk_purchaseorg());
    }
    List<String> materialList = new ArrayList<String>();
    this.setMaterialList(vo, materialList);
    String[] materialIds =
        materialList.toArray(new String[materialList.size()]);
    IVendorMaterialQuery service =
        NCLocator.getInstance().lookup(IVendorMaterialQuery.class);
    MapList<String,String> vrmMap  = null;
    try {
      vrmMap =
          service.queryByVendorMaterialOrgIds(
              suppliers.toArray(new String[suppliers.size()]), materialIds,
              purorgs.toArray(new String[purorgs.size()]));
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    return vrmMap;
  }

  private void setMaterialList(PraybillVO vo, List<String> materialList) {
    for (PraybillItemVO itemVO : vo.getBVO()) {
      materialList.add(itemVO.getPk_material());
    }
  }
}
