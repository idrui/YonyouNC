/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-5 下午06:16:34
 */
package nc.bs.pu.m20.pf.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.dao.BaseDAO;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.marpuclass.MarPuClassVO;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>物料采购分类编码包含
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-7-5 下午06:16:34
 */
public class MaterialClass {
  /**
   * 方法功能描述：对应物料采购分类编码，支持like,<br>
   * 即表体所有行物料所属物料分类编码等于或者包含该设置物料采购分类编码即可。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-7-5 下午06:19:35
   */
  public String getMaterialClassCode(AggregatedValueObject vo)
      throws BusinessException {
    List<String> pks = FunctionUtil.getPkMaterial((PraybillVO) vo);
    if (null == pks) {
      return null;
    }

    String pk_org = ((PraybillVO) vo).getHVO().getPk_org();

    Map<String, MaterialStockVO> materialVos =
        MaterialPubService.queryMaterialStockInfo(
            pks.toArray(new String[pks.size()]), pk_org, new String[] {
              MaterialStockVO.PK_MARPUCLASS
            });

    if (materialVos == null || materialVos.size() == 0) {
      return null;
    }
    int len = pks.size();
    HashMap<String, String> mapbasClsId = new HashMap<String, String>();
    String pk_marpuclass = null;
    for (int i = 0; i < len; i++) {
      MaterialStockVO materialStockVO = materialVos.get(pks.get(i));
      if (materialStockVO == null || materialStockVO.getPk_marpuclass() == null) {
        continue;
      }
      pk_marpuclass = materialStockVO.getPk_marpuclass();
      mapbasClsId.put(pk_marpuclass, "");
      // 如果单据出现不同存货分类，直接返回空
      if (mapbasClsId.size() > 1) {
        return null;
      }
    }

    if (pk_marpuclass == null) {
      return null;
    }

    // 查询存货采购分类VO
    MarPuClassVO marPuClassVO =
        (MarPuClassVO) new BaseDAO().retrieveByPK(MarPuClassVO.class,
            pk_marpuclass);
    if (marPuClassVO == null) {
      return null;
    }
    return marPuClassVO.getCode();

  }
}
