/**
 * $文件说明$
 * 
 * @author lixyp
 * @version 6.1
 * @see
 * @since 6.1
 * @time 2011-12-21 下午04:46:24
 */
package nc.pubimpl.pu.m21.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.bd.addrdoc.AddrdocPubService;
import nc.itf.scmpub.reference.uap.bd.stordoc.StordocPubService;
import nc.vo.bd.cust.addressdoc.AddressDocVO;
import nc.vo.bd.stordoc.StordocVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>根据仓库档案设置其地址、地点、地区等信息
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.1
 * @since 6.1
 * @author lixyp
 * @time 2011-12-21 下午04:46:24
 */
public class WarehouseValueRule implements IRule<OrderVO> {

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    this.setWarehouseValue(vos);
  }

  /**
   * 方法功能描述：清空地址信息
   * <p>
   * <b>参数说明</b>
   * 
   * @param itemVO 订单行vo
   *          <p>
   * @since 6.1
   * @author lixyp
   * @time 2011-12-22 上午08:50:24
   */
  private void clearAddressInfo(OrderItemVO itemVO) {
    itemVO.setPk_receiveaddress(null);
    itemVO.setCdevareaid(null);
    itemVO.setCdevaddrid(null);
  }

  private AddressDocVO getAddressDoc(String addrid,
      Map<String, AddressDocVO> addressdocMap) {
    if (addressdocMap.containsKey(addrid)) {
      return addressdocMap.get(addrid);
    }

    AddressDocVO addr = AddrdocPubService.queryAddrDocVOByID(addrid);
    addressdocMap.put(addrid, addr);
    return addr;
  }

  /**
   * 方法功能描述：得到地址信息Map
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderVOs 订单vo
   * @return <p>
   * @since 6.1
   * @author lixyp
   * @time 2011-12-22 上午10:11:11
   */
  private Map<String, StordocVO> getStordocMap(OrderVO[] orderVOs) {
    // 收集收货仓库pk
    List<String> warehouseIds = new ArrayList<String>();
    for (OrderVO orderVO : orderVOs) {
      for (OrderItemVO itemVO : orderVO.getBVO()) {
        if (itemVO.getPk_recvstordoc() != null) {
          warehouseIds.add(itemVO.getPk_recvstordoc());
        }
      }
    }

    if (warehouseIds.isEmpty()) {
      return null;
    }

    // 通过接口查询仓库信息。
    StordocVO[] vos =
        StordocPubService.queryStordocInfoByPks(
            warehouseIds.toArray(new String[warehouseIds.size()]),
            new String[] {
              StordocVO.PK_STORDOC, StordocVO.PK_ADDRESS, StordocVO.STORADDR,
              StordocVO.PK_ORG
            });

    // 将仓库信息组织成map结构，value为仓库pk，值为仓库vo。
    Map<String, StordocVO> map = new HashMap<String, StordocVO>();
    for (StordocVO stordocVO : vos) {
      if (stordocVO.getPk_stordoc() != null) {
        map.put(stordocVO.getPk_stordoc(), stordocVO);
      }
    }
    return map;
  }

  private void setWarehouseValue(OrderVO[] orderVOs) {
    Map<String, StordocVO> stordocMap = this.getStordocMap(orderVOs);
    if (stordocMap == null) {
      return;
    }

    Map<String, AddressDocVO> addressdocMap =
        new HashMap<String, AddressDocVO>();
    for (OrderVO orderVO : orderVOs) {
      String ss = orderVO.getHVO().getPk_recvcustomer();
      for (OrderItemVO itemVO : orderVO.getBVO()) {
        String warehouseid = itemVO.getPk_recvstordoc();
        StordocVO store = stordocMap.get(warehouseid);

        if (ss == null) {
          if (store == null) {
            // 收货客户和仓库都为空时，清空地址等信息
            this.clearAddressInfo(itemVO);
            continue;
          }
          // 收货地址
          itemVO.setPk_receiveaddress(store.getStoraddr());
          // 收货地点
          String addrid = store.getPk_address();
          itemVO.setCdevaddrid(addrid);
          if (StringUtil.isEmptyWithTrim(addrid)) {
            continue;
          }
          // 收货地区
          AddressDocVO addr = this.getAddressDoc(addrid, addressdocMap);
          itemVO.setCdevareaid(addr.getPk_areacl());
        }
        //
        // if (null == store || itemVO.getPk_arrvstoorg() != null) {
        // continue;
        // }
        // itemVO.setPk_arrvstoorg(store.getPk_org());
      }
    }
  }
}
