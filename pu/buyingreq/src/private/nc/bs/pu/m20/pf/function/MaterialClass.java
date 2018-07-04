/**
 * $�ļ�˵��$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-5 ����06:16:34
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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���ϲɹ�����������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-7-5 ����06:16:34
 */
public class MaterialClass {
  /**
   * ����������������Ӧ���ϲɹ�������룬֧��like,<br>
   * �����������������������Ϸ��������ڻ��߰������������ϲɹ�������뼴�ɡ�
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
      // ������ݳ��ֲ�ͬ������ֱ࣬�ӷ��ؿ�
      if (mapbasClsId.size() > 1) {
        return null;
      }
    }

    if (pk_marpuclass == null) {
      return null;
    }

    // ��ѯ����ɹ�����VO
    MarPuClassVO marPuClassVO =
        (MarPuClassVO) new BaseDAO().retrieveByPK(MarPuClassVO.class,
            pk_marpuclass);
    if (marPuClassVO == null) {
      return null;
    }
    return marPuClassVO.getCode();

  }
}
