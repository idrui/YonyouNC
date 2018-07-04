package nc.itf.pu.reference.uap.bd.material;

import java.util.HashMap;
import java.util.Map;

import nc.vo.bd.material.MaterialConvertVO;
import nc.vo.bd.pub.BDCacheQueryUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 物料采购适配类
 * 
 * @since 6.0
 * @version 2013-9-10 下午07:36:14
 * @author wuxla
 */
public class MaterialPuService {

  /**
   * 通过物料和单位查询换算率vo
   * 
   * @param pk_materials 物料vid主键
   * @param pk_measdocs 单位
   * @return key:物料vid+单位 value：物料换算率vo
   */
  public static Map<String, MaterialConvertVO> queryMaterialConvertVOByMaterialAndMeasdoc(
      String[] pk_materials, String[] pk_measdocs) {
    HashMap<String, Object[]> paramField_paramValues_map =
        new HashMap<String, Object[]>();
    paramField_paramValues_map.put(MaterialConvertVO.PK_MATERIAL, pk_materials);
    paramField_paramValues_map.put(MaterialConvertVO.PK_MEASDOC, pk_measdocs);
    MaterialConvertVO[] materialConvertVOs = null;
    try {
      materialConvertVOs =
          (MaterialConvertVO[]) BDCacheQueryUtil.queryVOs(
              MaterialConvertVO.class, new String[] {
                MaterialConvertVO.MEASRATE, MaterialConvertVO.PK_MEASDOC,
                MaterialConvertVO.FIXEDFLAG, MaterialConvertVO.PK_MATERIAL
              }, paramField_paramValues_map);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    if (materialConvertVOs == null || materialConvertVOs.length == 0) {
      return null;
    }

    Map<String, MaterialConvertVO> map =
        new HashMap<String, MaterialConvertVO>();
    for (MaterialConvertVO vo : materialConvertVOs) {
      if (vo != null) {
        map.put(vo.getPk_material() + vo.getPk_measdoc(), vo);
      }
    }
    return map;
  }
}
