package nc.pubimpl.pu.m21.mmpps.mpm;

import nc.pubitf.pu.m21.mmpps.mpm.IQueryPurOrderMapForPlanConsole;
import nc.pubitf.pu.m21.mmpps.mpm.PurchaseOrderPlanConSupplyMapVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.constant.PUMDValue;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * �ƻ�Ա����̨��ѯ����-�ɹ������ֶ�ӳ��ʵ����
 * 
 * @since 6.3
 * @version 2012-11-1 ����04:56:52
 * @author fanly3
 */
public class QueryPurOrderMapForPlanConsoleImpl implements
    IQueryPurOrderMapForPlanConsole {

  @Override
  public PurchaseOrderPlanConSupplyMapVO getPlanConSupplyMapVO()
      throws BusinessException {
    try {
      // ��Դ��
      StringBuilder fromSql = new StringBuilder();
      fromSql.append(" po_order_b po_order_b inner join po_order po_order ");
      fromSql.append(" on po_order.pk_order = po_order_b.pk_order ");
      // ��������
      StringBuilder whereSql = new StringBuilder();
      whereSql.append(" po_order.dr = 0 and po_order_b.dr = 0 ");
      // ���°汾
      whereSql.append(" and po_order.bislatest = 'Y' ");
      // �Ǻ���
      whereSql.append(" and po_order.breturn = 'N' ");

      PurchaseOrderPlanConSupplyMapVO mapVo =
          new PurchaseOrderPlanConSupplyMapVO();
      mapVo.setFromSql(fromSql.toString());
      mapVo.setWhereSql(whereSql.toString());
      // �����������͵ı���ֵ
      mapVo.setSupplyTypeCodeValue(POBillType.Order.getCode());
      // �����������͵�����ֵ
      mapVo.setSupplyTypeIdValue(POBillType.Order.getCode());
      // ������ͷ����
      mapVo.setSupplyid("po_order." + OrderHeaderVO.PK_ORDER);
      // ������������
      mapVo.setSupplybid("po_order_b." + OrderItemVO.PK_ORDER_B);
      // ���������к�
      mapVo.setSupplyRowNo("po_order_b." + OrderItemVO.CROWNO);
      // ���������֯=�ջ������֯
      mapVo.setSupplyOrgid("po_order_b." + OrderItemVO.PK_ARRVSTOORG);
      // ���������֯�汾
      mapVo.setSupplyOrgvid("po_order_b." + OrderItemVO.PK_ARRVSTOORG_V);
      // ��������
      mapVo.setMaterialid("po_order_b." + OrderItemVO.PK_SRCMATERIAL);
      // ���ϰ汾����
      mapVo.setMaterialvid("po_order_b." + OrderItemVO.PK_MATERIAL);
      // �������ݺ�
      mapVo.setSupplyCode("po_order." + OrderHeaderVO.VBILLCODE);
      // ��������=�ƻ���������
      mapVo.setSupplyDate("po_order_b." + OrderItemVO.DPLANARRVDATE);
      // ��������
      mapVo.setDbillDate("po_order." + OrderHeaderVO.DBILLDATE);
      // ������
      mapVo.setNnum("po_order_b.nnum ");
      // ִ��������=�ۼ����������
      mapVo.setNexenum("po_order_b." + OrderItemVO.NACCUMSTORENUM);
      // ����������=������-ִ��������
      mapVo.setNsupplynum("po_order_b." + OrderItemVO.NNUM
          + " - isnull(po_order_b." + OrderItemVO.NACCUMSTORENUM + ", 0)");
      // Ԥ��������
      mapVo.setReservatioNnum("po_order_b." + OrderItemVO.NSUPRSNUM);
      // ��Ӧ��
      mapVo.setVendorid("po_order_b." + OrderItemVO.PK_SUPPLIER);
      // ��������
      mapVo.setProductorid("po_order_b." + OrderItemVO.CPRODUCTORID);
      // ��Ŀ
      mapVo.setProjectid("po_order_b." + OrderItemVO.CPROJECTID);
      // �ͻ�
      mapVo.setCustomerid("po_order_b." + OrderItemVO.CASSCUSTID);
      // ������1-10
      mapVo.setFree1("po_order_b." + OrderItemVO.VFREE1);
      mapVo.setFree2("po_order_b." + OrderItemVO.VFREE2);
      mapVo.setFree3("po_order_b." + OrderItemVO.VFREE3);
      mapVo.setFree4("po_order_b." + OrderItemVO.VFREE4);
      mapVo.setFree5("po_order_b." + OrderItemVO.VFREE5);
      mapVo.setFree6("po_order_b." + OrderItemVO.VFREE6);
      mapVo.setFree7("po_order_b." + OrderItemVO.VFREE7);
      mapVo.setFree8("po_order_b." + OrderItemVO.VFREE8);
      mapVo.setFree9("po_order_b." + OrderItemVO.VFREE9);
      mapVo.setFree10("po_order_b." + OrderItemVO.VFREE10);

      // Դͷ������ID
      mapVo.setFirstBid("po_order_b." + OrderItemVO.CFIRSTBID);
      // Դͷ���ݺ�
      mapVo.setFirstCode("po_order_b." + OrderItemVO.VFIRSTCODE);
      // Դͷ����ID
      mapVo.setFirstId("po_order_b." + OrderItemVO.CFIRSTID);
      // Դͷ�����к�
      mapVo.setFirstRowNo("po_order_b." + OrderItemVO.VFIRSTROWNO);
      // Դͷ��������
      mapVo.setFirstType("po_order_b." + OrderItemVO.CFIRSTTYPECODE);
      // ��Դ������id
      mapVo.setSrcBid("po_order_b." + OrderItemVO.CSOURCEBID);
      // ��Դ����id
      mapVo.setSrcId("po_order_b." + OrderItemVO.CSOURCEID);
      // ����״̬
      mapVo.setvBillStatus("po_order." + OrderHeaderVO.FORDERSTATUS);
      // ����״̬ö��Ԫ����id
      mapVo.setvBillStatusEnumID(PUMDValue.OrderStatus.value());
      // ��Դ���ݺ�
      mapVo.setSrcCode("po_order_b." + OrderItemVO.VSOURCECODE);
      // ��Դ�����к�
      mapVo.setSrcRowNo("po_order_b." + OrderItemVO.VSOURCEROWNO);
      // ��Դ��������
      mapVo.setSrcRowNo("po_order_b." + OrderItemVO.CSOURCETYPECODE);

      return mapVo;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }
}
