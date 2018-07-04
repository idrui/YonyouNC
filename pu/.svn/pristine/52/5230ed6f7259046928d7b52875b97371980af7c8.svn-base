/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-28 上午09:28:36
 */
package nc.impl.pu.m21.action.rule.revise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.rule.DownFlowCheck;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;
import nc.vo.pubapp.pattern.model.tool.BillIndex;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ObjectUtils;

/**
 * @description
 *              采购订单如有后续单据，表体批次号、需求部门、项目、收货仓库、收货地址、赠品不能修改
 * @scene
 *        采购订单修订
 * @param 无
 * @since 6.3
 * @version 2014-10-22 上午10:34:41
 * @author luojw
 */
public class ItemChangeCheckRule implements ICompareRule<OrderVO> {

  private Map<String, String> checkFieldmap;

  private String[] items;

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.ICompareRule#process(E[], E[])
   */
  @Override
  public void process(OrderVO[] vos, OrderVO[] originVOs) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    // 表体批次号、需求部门、项目、收货仓库、收货地址、赠品、收货库存组织
    this.items =
        new String[] {
          OrderItemVO.VBATCHCODE, OrderItemVO.PK_REQDEPT,
          OrderItemVO.CPROJECTID, OrderItemVO.PK_RECVSTORDOC,
          OrderItemVO.PK_RECEIVEADDRESS, OrderItemVO.BLARGESS,
          OrderItemVO.PK_ARRVSTOORG
        };
    this.checkFieldmap = this.getCheckFieldMap(this.items);

    StringBuilder sb = new StringBuilder();
    // 将VO的表体生成［表体主键，表体］的Maps
    // Map<String, OrderItemVO> originItemMap =
    // AggVOUtil.createItemMap(originVOs);
    BillIndex index = new BillIndex(originVOs);

    for (OrderVO orderVO : vos) {
      // this.changeCheck(orderVO, originItemMap, sb);
      this.changeCheck(orderVO, index, sb);
    }

    if (sb.length() > 0) {
      ExceptionUtils.wrappBusinessException(sb.toString());
    }
  }

  /**
   * 方法功能描述：检查字段是否修改
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderVO
   * @param originItemMap
   * @param sb
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-16 下午04:10:42
   */
  // private void changeCheck(OrderVO orderVO,
  // Map<String, OrderItemVO> originItemMap, StringBuilder sb) {
  // if (null == orderVO || ArrayUtils.isEmpty(orderVO.getBVO())) {
  // return;
  // }
  //
  // DownFlowCheck check = new DownFlowCheck();
  // for (OrderItemVO itemVO : orderVO.getBVO()) {
  // // 判断状态是不是UPDATED
  // if (null == itemVO || itemVO.getStatus() != VOStatus.UPDATED) {
  // continue;
  // }
  //
  // // 得到改变项
  // List<String> changes =
  // this.changeItems(itemVO, originItemMap.get(itemVO.getPk_order_b()));
  // if (changes.isEmpty()) {
  // continue;
  // }
  //
  // // 是否有后续单据
  // if (check.hasDownFlow(itemVO)) {
  // sb.append("订单" + orderVO.getHVO().getVbillcode() + "第"
  // + itemVO.getCrowno() + "行存在后续单据，不能修改如下字段：");
  // for (int j = 0; j < changes.size(); ++j) {
  // if (j > 0) {
  // sb.append("、");
  // }
  // sb.append(this.checkFieldmap.get(changes.get(j)));
  // }
  // }
  // }
  // }
  private void changeCheck(OrderVO orderVO, BillIndex index, StringBuilder sb) {
    if (null == orderVO || ArrayUtils.isEmpty(orderVO.getBVO())) {
      return;
    }

    DownFlowCheck check = new DownFlowCheck();
    IVOMeta meta = orderVO.getMetaData().getVOMeta(OrderItemVO.class);
    for (OrderItemVO itemVO : orderVO.getBVO()) {
      // 判断状态是不是UPDATED
      if (null == itemVO || itemVO.getStatus() != VOStatus.UPDATED) {
        continue;
      }

      // 得到改变项
      List<String> changes =
          this.changeItems(itemVO,
              (OrderItemVO) index.get(meta, itemVO.getPk_order_b()));
      if (changes.isEmpty()) {
        continue;
      }

      // 是否有后续单据
      if (check.hasDownFlow(itemVO)) {
        sb.append(NCLangResOnserver.getInstance().getStrByID("4004030_0",
            "04004030-0286", null, new String[] {
              orderVO.getHVO().getVbillcode(), itemVO.getCrowno()
            })/* 订单{0}第{1}行存在后续单据，不能修改如下字段： */);
        for (int j = 0; j < changes.size(); ++j) {
          if (j > 0) {
            sb.append(", ");
          }
          sb.append(this.checkFieldmap.get(changes.get(j)));
        }
      }
    }
  }

  /**
   * 方法功能描述：得到改变的项
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   * @param originVO
   * @param items
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-3-28 上午10:00:12
   */
  private List<String> changeItems(OrderItemVO vo, OrderItemVO originVO) {
    List<String> changeItems = new ArrayList<String>();
    for (int i = 0; i < this.items.length; ++i) {
      Object value = vo.getAttributeValue(this.items[i]);
      Object originValue = originVO.getAttributeValue(this.items[i]);
      // value修订内容来自前台，空值为“ ”，originValue来自后台，空值为null，需要对两者处理成一致比较
      if (!ObjectUtils.equals(ObjectUtils.toString(value).trim(), ObjectUtils
          .toString(originValue).trim())) {
        changeItems.add(this.items[i]);
      }
    }

    return changeItems;
  }

  /**
   * 方法功能描述：检查项Map[key:字段，value:名称]
   * <p>
   * <b>参数说明</b>
   * 
   * @param checkItems
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-16 下午04:05:09
   */
  private Map<String, String> getCheckFieldMap(String[] checkItems) {
    Map<String, String> map = new HashMap<String, String>();
    IVOMeta vometa = VOMetaFactory.getInstance().getVOMeta(PUEntity.M21_B);
    for (String item : checkItems) {
      map.put(item, vometa.getAttribute(item).toString());
    }
    return map;
  }

}
