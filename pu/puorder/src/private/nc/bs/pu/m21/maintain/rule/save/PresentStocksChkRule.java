/**
 * $文件说明$
 *
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-12-29 下午03:58:01
 */
package nc.bs.pu.m21.maintain.rule.save;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.SystemUtils;

import nc.vo.bd.accessor.IBDData;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pu.m21.context.OrderContext;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.exception.AskOverPresentStockException;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.trade.voutils.VOUtil;

import nc.itf.pu.reference.ic.AtpQueryServices;
import nc.itf.scmpub.reference.uap.bd.accesor.MaterialAccessor;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;

import nc.pubitf.pu.m21transtype.IPoTransTypeQuery;

import nc.bs.framework.common.NCLocator;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;

/**
 * @description
 *              <ul>
 *              <li>检查是否超现存量
 *              <li>只检查退货订单；非劳务、折扣物料；直运无入库也不检查
 *              <li>
 *              </ul>
 * @scene
 *        采购订单保存修改
 * @param 无
 * @since 6.3
 * @version 2014-10-22 下午2:57:25
 * @author luojw
 */
public class PresentStocksChkRule implements IRule<OrderVO> {

  private OrderContext ctx;

  public PresentStocksChkRule(OrderContext ctx) {
    this.ctx = ctx;
  }

  @Override
  public void process(OrderVO[] vos) {
    // 如果用户确认过，则不检查
    if (null != this.ctx
        && UFBoolean.TRUE.equals(this.ctx.getOverPresentStockConfirm())) {
      return;
    }
    List<OrderItemVO> checkItemLst = this.getCheckItems(vos);// 得到需要检查的表体数据
    if (CollectionUtils.isEmpty(checkItemLst)) {
      return;
    }
    MapList<String, OrderItemVO> orgMarItemMap =
        this.getOrgMarItemMap(checkItemLst);// 库存组织+物料的MAPList
    Map<String, UFDouble> presentMap = this.getPresentMap(checkItemLst);// 查现存量
    if (null == presentMap) {
      presentMap = new HashMap<String, UFDouble>();
    }
    List<String> overMarLst = new ArrayList<String>();
    for (Entry<String, List<OrderItemVO>> entry : orgMarItemMap.entrySet()) {
      String orgMarKey = entry.getKey();
      UFDouble sumOrderNum = this.getMarNum(entry.getValue());
      UFDouble presentNum = presentMap.get(orgMarKey);
      // 对于没查询到现存量信息的，按0处理，这时负订单是不可以保存的
      if (presentNum == null) {
        presentNum = UFDouble.ZERO_DBL;
      }
      if (MathTool.greaterThan(MathTool.abs(sumOrderNum), presentNum)) {
        overMarLst.add(entry.getValue().get(0).getPk_material());
      }
    }
    this.throwMsgByCheck(overMarLst);// 根据检查结算抛出异常
  }

  private List<OrderVO> filterCheckVO(OrderVO[] vos) {
    List<OrderVO> checkVOLst = new ArrayList<OrderVO>();
    for (OrderVO vo : vos) {
      if (UFBoolean.TRUE.equals(vo.getHVO().getBreturn())) {
        checkVOLst.add(vo);// 只对退货订单检查
      }
    }
    return checkVOLst;
  }

  private List<OrderItemVO> getCheckItems(OrderVO[] vos) {
    List<OrderVO> checkVOLst = this.filterCheckVO(vos);
    if (CollectionUtils.isEmpty(checkVOLst)) {
      return null;
    }
    List<OrderItemVO> checkLst = new ArrayList<OrderItemVO>();
    Map<String, MaterialVO> marInfoMap = this.getMarInfoMap(vos);
    Map<String, PoTransTypeVO> ttcodeMap = this.getTrantypeMap(vos);
    for (OrderVO vo : vos) {
      for (OrderItemVO item : vo.getBVO()) {
        // 劳务、折扣物料； 直运无入库；正订单也不检查
        if (!this.isItemCheck(marInfoMap, ttcodeMap, item, vo.getHVO())) {
          continue;
        }
        checkLst.add(item);
      }
    }
    return checkLst;
  }

  private Map<String, MaterialVO> getMarInfoMap(OrderVO[] vos) {
    String[] mars =
        (String[]) AggVOUtil.getDistinctItemFieldArray(vos,
            OrderItemVO.PK_MATERIAL, String.class);
    Map<String, MaterialVO> marInfoMap =
        MaterialPubService.queryMaterialBaseInfo(mars, new String[] {
          MaterialVO.DISCOUNTFLAG, MaterialVO.FEE
        });
    return marInfoMap;
  }

  private UFDouble getMarNum(List<OrderItemVO> itemLst) {
    UFDouble sum = null;
    for (OrderItemVO item : itemLst) {
      sum = MathTool.add(sum, item.getNnum());
    }
    return sum;
  }

  private MapList<String, OrderItemVO> getOrgMarItemMap(
      List<OrderItemVO> checkItemLst) {
    MapList<String, OrderItemVO> ml = new MapList<String, OrderItemVO>();
    for (OrderItemVO item : checkItemLst) {
      String key = VOUtil.getCombinesKey(item, new String[] {
        OrderItemVO.PK_ARRVSTOORG, OrderItemVO.PK_MATERIAL
      });
      ml.put(key, item);
    }
    return ml;
  }

  private Map<String, UFDouble> getPresentMap(List<OrderItemVO> checkItemLst) {
    OrderItemVO[] checkItems =
        checkItemLst.toArray(new OrderItemVO[checkItemLst.size()]);
    Set<String> orgSet =
        CirVOUtil.getDistinctFieldSet(checkItems, OrderItemVO.PK_ARRVSTOORG);
    Set<String> marSet =
        CirVOUtil.getDistinctFieldSet(checkItems, OrderItemVO.PK_MATERIAL);
    Map<String, UFDouble> map =
        AtpQueryServices.queryInvOnhandnum(
            orgSet.toArray(new String[orgSet.size()]),
            marSet.toArray(new String[marSet.size()]));
    return map;
  }

  private Map<String, PoTransTypeVO> getTrantypeMap(OrderVO[] vos) {
    String[] ttcode =
        (String[]) AggVOUtil.getDistinctHeadFieldArray(vos,
            OrderHeaderVO.VTRANTYPECODE, String.class);
    Map<String, PoTransTypeVO> ttcodeMap = null;
    try {
      ttcodeMap =
          NCLocator
              .getInstance()
              .lookup(IPoTransTypeQuery.class)
              .queryAttrByTypes(
                  ttcode,
                  new String[] {
                    PoTransTypeVO.VTRANTYPECODE, PoTransTypeVO.BDIRECT,
                    PoTransTypeVO.BSTORE
                  });
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return ttcodeMap;
  }

  private boolean isItemCheck(Map<String, MaterialVO> marInfoMap,
      Map<String, PoTransTypeVO> ttcodeMap, OrderItemVO item, OrderHeaderVO head) {
    if (MathTool.compareTo(item.getNnum(), UFDouble.ZERO_DBL) >= 0) {
      return false;
    }
    MaterialVO mvo = marInfoMap.get(item.getPk_material());
    if (null != mvo
        && (UFBoolean.TRUE.equals(mvo.getFee()) || UFBoolean.TRUE.equals(mvo
            .getDiscountflag()))) {
      return false;
    }
    PoTransTypeVO ttVo = ttcodeMap.get(head.getVtrantypecode());
    if (null != ttVo && UFBoolean.TRUE.equals(ttVo.getBdirect())
        && UFBoolean.FALSE.equals(ttVo.getBstore())) {
      return false;
    }
    return true;
  }

  private void throwMsgByCheck(List<String> overMarLst) {
    if (CollectionUtils.isEmpty(overMarLst)) {
      return;
    }
    IBDData[] mars =
        MaterialAccessor.getDocbyPks(overMarLst.toArray(new String[overMarLst
            .size()]));
    StringBuilder msg = new StringBuilder();
    for (IBDData mar : mars) {
      msg.append("[").append(mar.getName().toString()).append("]");
    }
    msg.append(SystemUtils.LINE_SEPARATOR);
    msg.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004030_0", "04004030-0122")/* @res "以上物料退货数量已经超过现存量，是否继续？" */);
    UFBoolean businessCheckMap =
        (UFBoolean) BSContext.getInstance().getSession(
            "AskOverPresentStockCheck");
    if (businessCheckMap == null || businessCheckMap.booleanValue()) {
      ExceptionUtils.wrappException(new AskOverPresentStockException(msg
          .toString()));
    }
  }
}
