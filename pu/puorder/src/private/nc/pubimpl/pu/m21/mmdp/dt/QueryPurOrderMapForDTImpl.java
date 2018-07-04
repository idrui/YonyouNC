package nc.pubimpl.pu.m21.mmdp.dt;

import nc.pubitf.pu.m21.mmdp.dt.IQueryPurOrderMapForDT;
import nc.pubitf.pu.m21.mmdp.dt.PurchaseOrderDTSupplyMapVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.constant.PUMDValue;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * PU-������ٲ�ѯ����-�ɹ������ֶ�ӳ��ʵ����
 * 
 * @since 6.0
 * @version 2012-11-6 ����11:02:40
 * @author liuyand
 */
public class QueryPurOrderMapForDTImpl implements IQueryPurOrderMapForDT {

  @Override
  public PurchaseOrderDTSupplyMapVO getDTSupplyMapVO() throws BusinessException {
    try {
      // ��Դ��
      StringBuilder fromSql = new StringBuilder();
      fromSql.append(" po_order_b po_order_b inner join po_order po_order ");
      fromSql.append(" on po_order_b.pk_order = po_order.pk_order ");
      // ��������
      SqlBuilder whereSql = new SqlBuilder();
      // δɾ��
      whereSql.append(" po_order_b.dr = 0 and po_order.dr = 0 ");
      // �Ǻ��ֵ���
      whereSql.append(" and po_order.breturn", UFBoolean.FALSE);

      PurchaseOrderDTSupplyMapVO mapVo = new PurchaseOrderDTSupplyMapVO();
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
      // ����λ
      mapVo.setCunitid("po_order_b." + OrderItemVO.CUNITID);
      // ������
      mapVo.setNnum("po_order_b." + OrderItemVO.NNUM);
      // ִ��������=�ۼ����������
      mapVo.setNexenum("po_order_b." + OrderItemVO.NACCUMSTORENUM);
      // ����������=������-ִ��������
      mapVo.setNsupplynum("po_order_b." + OrderItemVO.NNUM
          + " - isnull(po_order_b." + OrderItemVO.NACCUMSTORENUM + ", 0)");
      // ������
      mapVo.setVchangerate("po_order_b." + OrderItemVO.VCHANGERATE);
      // ��λ
      mapVo.setCastunitid("po_order_b." + OrderItemVO.CASTUNITID);
      // ����
      mapVo.setNastnum("po_order_b." + OrderItemVO.NASTNUM);
      // Ԥ��������=��Ԥ������
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
      // ����״̬
      mapVo.setvBillStatus("po_order." + OrderHeaderVO.FORDERSTATUS);
      // ����״̬ö��Ԫ����id
      mapVo.setvBillStatusEnumID(PUMDValue.OrderStatus.value());
      // ��Դ������id
      mapVo.setSrcBid("po_order_b." + OrderItemVO.CSOURCEBID);
      // ��Դ����id
      mapVo.setSrcId("po_order_b." + OrderItemVO.CSOURCEID);
      // ��Դ���ݺ�
      mapVo.setSrcCode("po_order_b." + OrderItemVO.VSOURCECODE);
      // ��Դ�����к�
      mapVo.setSrcRowNo("po_order_b." + OrderItemVO.VSOURCEROWNO);
      // ��Դ��������
      mapVo.setSrcType("po_order_b." + OrderItemVO.CSOURCETYPECODE);
      // ���ر�
      mapVo.setBstockclose("po_order_b." + OrderItemVO.BSTOCKCLOSE);

      // ������
      mapVo.setCffileid("po_order_b." + OrderItemVO.CFFILEID);

      return mapVo;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }
}
