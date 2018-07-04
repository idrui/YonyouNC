/**
 * $文件说明$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-13 上午08:24:08
 */
package nc.bs.pu.m21.pf.function;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.pu.MaterialPuVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>业务检查函数：订单价格超最高限价差额，订单价格超最高限价差额=MAX每行（采购订单本币含税净单价—最高限价）
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-5-13 上午08:24:08
 */
public class OverMaxPrice {

  /**
   * 方法功能描述：订单价格超最高限价差额，订单价格超最高限价差额=MAX每行（采购订单本币含税净单价—最高限价）
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderVO
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-14 上午08:25:12
   */
  public UFDouble getOverMaxPrice(AggregatedValueObject vo)
      throws BusinessException {
    if (null == vo) {
      throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004030_0", "04004030-0135")/* @res "检查函数传入参数错误!" */);
    }

    OrderVO orderVO = (OrderVO) vo;
    OrderItemVO[] itemVOs = orderVO.getBVO();
    if (ArrayUtils.isEmpty(itemVOs)) {
      throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004030_0", "04004030-0135")/* @res "检查函数传入参数错误!" */);
    }

    String pk_purchaseorg = orderVO.getHVO().getPk_org();
    List<String> listPkMaterials = new ArrayList<String>();
    for (OrderItemVO itemVO : itemVOs) {
      if (null == itemVO) {
        continue;
      }

      String pkMaterial = itemVO.getPk_material();
      if (pkMaterial != null) {
        listPkMaterials.add(pkMaterial);
      }
    }

    Map<String, MaterialPuVO> map =
        MaterialPubService.queryMaterialPurchaseInfoMap(listPkMaterials,
            pk_purchaseorg, new String[] {
              MaterialPuVO.MAXPRICE
            });

    UFDouble maxPrice = null;
    MaterialPuVO puvo = map.get(itemVOs[0].getPk_material());
    if (puvo != null) {
      maxPrice = puvo.getMaxprice();
    }
    UFDouble overMaxPrice =
        MathTool.sub(itemVOs[0].getNtaxnetprice(), maxPrice);
    for (int i = 1; i < itemVOs.length; ++i) {
      puvo = map.get(itemVOs[i].getPk_material());
      if (null == puvo) {
        continue;
      }
      maxPrice = puvo.getMaxprice();
      if (MathTool.compareTo(maxPrice, UFDouble.ZERO_DBL) <= 0) {
        continue;
      }

      // 采购订单本币含税净单价—最高限价
      UFDouble subMaxPrice =
          MathTool.sub(itemVOs[i].getNtaxnetprice(), maxPrice);
      if (MathTool.compareTo(overMaxPrice, subMaxPrice) < 0) {
        overMaxPrice = subMaxPrice;
      }
    }

    return overMaxPrice;
  }
}
