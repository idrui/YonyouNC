package nc.vo.pu.upgrade.v63;

import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.scmpub.upgrade.address.CustSupAddrUpgradeSqlVO;
import nc.vo.scmpub.upgrade.address.CustSupAddrUpgrader;

/**
 * 采购客商地址升级类。
 * 
 * @since 6.3
 * @version 2013-6-3 下午01:08:20
 * @author lixyp
 */
public class PuAddressUpgrader {

  public void doUpgrade() {
    CustSupAddrUpgrader upgrader = new CustSupAddrUpgrader();

    upgrader.addSqlVO(this.getSqlForSupOn21H());
    upgrader.addSqlVO(this.getSqlForSupOn21B());
    upgrader.addSqlVO(this.getSqlForSupOn21BB1());
    upgrader.addSqlVO(this.getSqlForCustOn21B());
    upgrader.addSqlVO(this.getSqlForCustOn21BB1());

    upgrader.doUpgrade();
  }

  private String getBB1Join() {
    return " inner join po_order on po_order.pk_order = po_order_bb1.pk_order ";
  }

  private String getBJoin() {
    return " inner join po_order on po_order.pk_order = po_order_b.pk_order ";
  }

  /**
   * 客户 - 采购订单B表。
   * 
   * @return SQL封装类实例。
   */
  private CustSupAddrUpgradeSqlVO getSqlForCustOn21B() {
    CustSupAddrUpgradeSqlVO vo = new CustSupAddrUpgradeSqlVO();
    vo.setCustSupExp("po_order.pk_recvcustomer");
    vo.setJoinSqlPart(this.getBJoin());
    vo.setGroupExp("po_order.pk_group");
    vo.setRefField(OrderItemVO.PK_RECEIVEADDRESS);
    vo.setRefTable(PUEntity.M21_B_TABLE);
    vo.setRefTablePkField(OrderItemVO.PK_ORDER_B);
    vo.setSameTableFlag(false);
    vo.setType(CustSupAddrUpgradeSqlVO.CUSTOMER);
    vo.setWhereSqlPart(" po_order.dr = 0 ");
    return vo;
  }

  /**
   * 客户 - 采购订单BB1表（到货计划）。
   * 
   * @return SQL封装类实例。
   */
  private CustSupAddrUpgradeSqlVO getSqlForCustOn21BB1() {
    CustSupAddrUpgradeSqlVO vo = new CustSupAddrUpgradeSqlVO();
    vo.setCustSupExp("po_order.pk_recvcustomer");
    vo.setJoinSqlPart(this.getBB1Join());
    vo.setGroupExp("po_order.pk_group");
    vo.setRefField(OrderReceivePlanVO.PK_RECEIVEADDRESS);
    vo.setRefTable(PUEntity.M21_BB1_TABLE);
    vo.setRefTablePkField(OrderReceivePlanVO.PK_ORDER_BB1);
    vo.setSameTableFlag(false);
    vo.setType(CustSupAddrUpgradeSqlVO.CUSTOMER);
    vo.setWhereSqlPart(" po_order.dr = 0 ");
    return vo;
  }

  /**
   * 供应商 - 采购订单B表。
   * 
   * @return SQL封装类实例。
   */
  private CustSupAddrUpgradeSqlVO getSqlForSupOn21B() {
    CustSupAddrUpgradeSqlVO vo = new CustSupAddrUpgradeSqlVO();
    vo.setCustSupExp("po_order.pk_supplier");
    vo.setJoinSqlPart(this.getBJoin());
    vo.setGroupExp("po_order.pk_group");
    vo.setOrgExp("po_order.pk_org");
    vo.setRefField(OrderItemVO.VVENDDEVADDR);
    vo.setRefTable(PUEntity.M21_B_TABLE);
    vo.setRefTablePkField(OrderItemVO.PK_ORDER_B);
    vo.setSameTableFlag(false);
    vo.setType(CustSupAddrUpgradeSqlVO.SUPPLIER);
    vo.setWhereSqlPart(" po_order.dr = 0 ");
    return vo;
  }

  /**
   * 供应商 - 采购订单BB1表（到货计划）。
   * 
   * @return SQL封装类实例。
   */
  private CustSupAddrUpgradeSqlVO getSqlForSupOn21BB1() {
    CustSupAddrUpgradeSqlVO vo = new CustSupAddrUpgradeSqlVO();
    vo.setCustSupExp("po_order.pk_supplier");
    vo.setJoinSqlPart(this.getBB1Join());
    vo.setGroupExp("po_order.pk_group");
    vo.setOrgExp("po_order.pk_org");
    vo.setRefField(OrderReceivePlanVO.VVENDDEVADDR);
    vo.setRefTable(PUEntity.M21_BB1_TABLE);
    vo.setRefTablePkField(OrderReceivePlanVO.PK_ORDER_BB1);
    vo.setSameTableFlag(false);
    vo.setType(CustSupAddrUpgradeSqlVO.SUPPLIER);
    vo.setWhereSqlPart(" po_order.dr = 0 ");
    return vo;
  }

  /**
   * 供应商 - 采购订单H表。
   * 
   * @return SQL封装类实例。
   */
  private CustSupAddrUpgradeSqlVO getSqlForSupOn21H() {
    CustSupAddrUpgradeSqlVO vo = new CustSupAddrUpgradeSqlVO();
    vo.setCustSupExp("po_order.pk_supplier");
    vo.setGroupExp("po_order.pk_group");
    vo.setOrgExp("po_order.pk_org");
    vo.setRefField(OrderHeaderVO.PK_DELIVERADD);
    vo.setRefTable(PUEntity.M21_H_TABLE);
    vo.setRefTablePkField(OrderHeaderVO.PK_ORDER);
    vo.setSameTableFlag(true);
    vo.setType(CustSupAddrUpgradeSqlVO.SUPPLIER);
    return vo;
  }
}
