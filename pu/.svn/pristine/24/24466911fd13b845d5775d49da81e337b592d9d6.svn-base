/**
 * $文件说明$
 *
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2009-12-30 上午08:58:17
 */
package nc.bs.pu.m21.maintain.rule.save;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.bd.accesor.MaterialAccessor;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPuPubService;
import nc.vo.bd.material.pu.MaterialPuVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;

import org.apache.commons.lang.SystemUtils;

/**
 * @description
 *              是否为必须合同采购物料检查
 * @scene
 *        采购订单保存修改
 * @param 无
 * @since 6.3
 * @version 2014-10-22 下午3:00:26
 * @author luojw
 */
public class CntPurMaterialChkRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    MapList<String, String> orgMarMap = this.getOrgMaterialMap(vos);
    Set<String> cntMatrl = new HashSet<String>();
    for (Entry<String, List<String>> entry : orgMarMap.entrySet()) {
      String pk_org = entry.getKey();
      List<String> mars = entry.getValue();
      Map<String, MaterialPuVO> marPuMap = this.getMarPuInfo(pk_org, mars);
      if (null == marPuMap) {
        continue;
      }
      for (Entry<String, MaterialPuVO> mentry : marPuMap.entrySet()) {
        MaterialPuVO mpuVo = mentry.getValue();
        if (null != mpuVo && UFBoolean.FALSE.equals(mpuVo.getIsnoconallowed())) {
          cntMatrl.add(mentry.getKey());// 不允许无合同采购的物料
        }
      }
    }
    this.check(vos, cntMatrl);// 检查后，抛出异常
  }

  private void check(OrderVO[] vos, Set<String> cntMatrl) {
    StringBuilder errMsg = new StringBuilder();
    for (OrderVO vo : vos) {
      for (OrderItemVO item : vo.getBVO()) {
        if (cntMatrl.contains(item.getPk_material())
            && StringUtil.isEmptyWithTrim(item.getCcontractrowid())) {
          String marName =
              MaterialAccessor.getDocByPk(item.getPk_material()).getName()
                  .toString();
          errMsg
              .append("["
                  + marName
                  + "]"
                  + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                      "4004030_0", "04004030-0117")/*
                                                    * @res
                                                    * "为必须合同采购物料，但未关联合同，请检查！"
                                                    */);
          errMsg.append(SystemUtils.LINE_SEPARATOR);
        }
      }
    }
    if (0 < errMsg.length()) {
      ExceptionUtils.wrappBusinessException(errMsg.toString());
    }
  }

  private Map<String, MaterialPuVO> getMarPuInfo(String pk_org,
      List<String> mars) {
    return MaterialPuPubService.queryMaterialPuVO(
        mars.toArray(new String[mars.size()]), pk_org, new String[] {
          MaterialPuVO.ISNOCONALLOWED
        });
  }

  private MapList<String, String> getOrgMaterialMap(OrderVO[] vos) {
    MapList<String, String> orgMarMap = new MapList<String, String>();
    for (OrderVO vo : vos) {
      for (OrderItemVO item : vo.getBVO()) {
        orgMarMap.put(vo.getHVO().getPk_org(), item.getPk_material());
      }
    }
    return orgMarMap;
  }

}
