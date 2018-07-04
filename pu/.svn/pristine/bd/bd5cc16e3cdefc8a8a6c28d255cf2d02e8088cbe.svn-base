package nc.vo.pu.m21.rule;

import nc.itf.scmpub.reference.uap.bd.addrdoc.AddrdocPubService;
import nc.itf.scmpub.reference.uap.bd.stordoc.StordocPubService;
import nc.vo.bd.cust.addressdoc.AddressDocVO;
import nc.vo.bd.stordoc.StordocVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.util.IKeyValue;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>根据仓库档案设置其地址、地点、地区等信息
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-4-1 下午07:57:17
 */
public class WarehouseDefaultValue {
  private IKeyValue keyValue;

  public WarehouseDefaultValue(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * 方法功能描述：根据仓库设置相关属性（地址、地点、地区等）
   * <p>
   * <b>参数说明</b>
   * 
   * @param row
   *          要设置的行
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-4-2 上午09:11:59
   */
  public void setWarehouseDefaultValue(int row) {
    // 查询仓库信息
    StordocVO store = this.queryStordocInfo(row);

    // 设置地址、地点、地区等
    this.setAddressInfo(row, store);
    // 带出收货库存组织
    this.setArrvStoOrg(row, store);
    // 物流组织
    this.setFlowStockOrg(row, store);
  }

  private void clearAddressInfo(int row) {
    this.keyValue.setBodyValue(row, OrderItemVO.PK_RECEIVEADDRESS, null);
    this.keyValue.setBodyValue(row, OrderItemVO.CDEVAREAID, null);
    this.keyValue.setBodyValue(row, OrderItemVO.CDEVADDRID, null);
  }

  private StordocVO queryStordocInfo(int row) {
    String warehouseid =
        (String) this.keyValue.getBodyValue(row, OrderItemVO.PK_RECVSTORDOC);
    if (StringUtil.isEmptyWithTrim(warehouseid)) {
      return null;
    }
    StordocVO[] vos = null;
    vos = StordocPubService.queryStordocInfoByPks(new String[] {
      warehouseid
    }, new String[] {
      StordocVO.PK_ADDRESS, StordocVO.STORADDR, StordocVO.PK_ORG
    });

    if (vos == null || vos.length == 0) {
      return null;
    }

    return vos[0];
  }

  private void setAddressInfo(int row, StordocVO store) {
    String ss =
        (String) this.keyValue.getHeadValue(OrderHeaderVO.PK_RECVCUSTOMER);
    if (ss == null) {
      if (store == null) {
        // 收货客户和仓库都为空时，清空地址等信息
        this.clearAddressInfo(row);
        return;
      }

      // 收货地址
      this.keyValue.setBodyValue(row, OrderItemVO.PK_RECEIVEADDRESS,
          store.getStoraddr());

      // 收货地点
      String addrid = store.getPk_address();
      this.keyValue.setBodyValue(row, OrderItemVO.CDEVADDRID, addrid);

      if (addrid == null) {
        this.keyValue.setBodyValue(row, OrderItemVO.CDEVAREAID, null);
        return;
      }

      // 收货地区
      AddressDocVO addr = AddrdocPubService.queryAddrDocVOByID(addrid);
      this.keyValue.setBodyValue(row, OrderItemVO.CDEVAREAID,
          addr.getPk_areacl());
    }
  }

  private void setArrvStoOrg(int row, StordocVO store) {
    // 带出收货库存组织
    if (store == null
        || this.keyValue.getBodyValue(row, OrderItemVO.PK_ARRVSTOORG) != null) {
      return;
    }
    this.keyValue.setBodyValue(row, OrderItemVO.PK_ARRVSTOORG,
        store.getPk_org());
  }

  private void setFlowStockOrg(int row, StordocVO store) {
    String pk_org = null == store ? null : store.getPk_org();
    FlowStockOrgValue util = new FlowStockOrgValue(this.keyValue);
    util.setFlowStockOrgByWar(row, pk_org);
  }
}
