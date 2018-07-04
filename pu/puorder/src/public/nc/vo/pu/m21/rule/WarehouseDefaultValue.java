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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���ݲֿ⵵���������ַ���ص㡢��������Ϣ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-4-1 ����07:57:17
 */
public class WarehouseDefaultValue {
  private IKeyValue keyValue;

  public WarehouseDefaultValue(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * �����������������ݲֿ�����������ԣ���ַ���ص㡢�����ȣ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param row
   *          Ҫ���õ���
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-4-2 ����09:11:59
   */
  public void setWarehouseDefaultValue(int row) {
    // ��ѯ�ֿ���Ϣ
    StordocVO store = this.queryStordocInfo(row);

    // ���õ�ַ���ص㡢������
    this.setAddressInfo(row, store);
    // �����ջ������֯
    this.setArrvStoOrg(row, store);
    // ������֯
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
        // �ջ��ͻ��ͲֿⶼΪ��ʱ����յ�ַ����Ϣ
        this.clearAddressInfo(row);
        return;
      }

      // �ջ���ַ
      this.keyValue.setBodyValue(row, OrderItemVO.PK_RECEIVEADDRESS,
          store.getStoraddr());

      // �ջ��ص�
      String addrid = store.getPk_address();
      this.keyValue.setBodyValue(row, OrderItemVO.CDEVADDRID, addrid);

      if (addrid == null) {
        this.keyValue.setBodyValue(row, OrderItemVO.CDEVAREAID, null);
        return;
      }

      // �ջ�����
      AddressDocVO addr = AddrdocPubService.queryAddrDocVOByID(addrid);
      this.keyValue.setBodyValue(row, OrderItemVO.CDEVAREAID,
          addr.getPk_areacl());
    }
  }

  private void setArrvStoOrg(int row, StordocVO store) {
    // �����ջ������֯
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
