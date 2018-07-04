package nc.pubimpl.pu.m20.mmpub.setanalysis;

import nc.pubitf.pu.m20.mmpub.setanalysis.IQueryPrayOrderMapForSa;
import nc.pubitf.pu.m20.mmpub.setanalysis.PrayOrderSaSupplyMapVO;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.pub.constant.PUMDValue;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * ���׷�����ѯ����-�빺���ֶ�ӳ��ʵ����
 * 
 * @since 6.3
 * @version 2012-11-2 ����09:24:24
 * @author fanly3
 */
public class QueryPrayOrderMapForSaImpl implements IQueryPrayOrderMapForSa {

  @Override
  public PrayOrderSaSupplyMapVO getSaSupplyMapVO() throws BusinessException {
    try {
      // ��Դ��
      StringBuilder fromSql = new StringBuilder();
      fromSql
          .append(" po_praybill_b po_praybill_b inner join po_praybill po_praybill ");
      fromSql
          .append(" on po_praybill_b.pk_praybill = po_praybill.pk_praybill ");
      // ��������
      StringBuilder whereSql = new StringBuilder();
      whereSql.append(" po_praybill.dr = 0 and po_praybill_b.dr = 0 ");
      // �йر�ΪFalse
      whereSql.append(" and po_praybill_b.browclose = 'N' ");
      // ���°汾
      whereSql.append(" and po_praybill.bislatest = 'Y' ");
      // ������-�ۼƶ���������������
      whereSql
          .append(" and (po_praybill_b.nnum - isnull(po_praybill_b.naccumulatenum,0)) > 0");

      PrayOrderSaSupplyMapVO mapVo = new PrayOrderSaSupplyMapVO();
      mapVo.setFromSql(fromSql.toString());
      mapVo.setWhereSql(whereSql.toString());
      // �����������͵ı���ֵ
      mapVo.setSupplyTypeCodeValue(POBillType.PrayBill.getCode());
      // �����������͵�����ֵ
      mapVo.setSupplyTypeIdValue(POBillType.PrayBill.getCode());
      // ������ͷ����
      mapVo.setSupplyid("po_praybill." + PraybillHeaderVO.PK_PRAYBILL);
      // ������������
      mapVo.setSupplybid("po_praybill_b." + PraybillItemVO.PK_PRAYBILL_B);
      // ���������к�
      mapVo.setSupplyRowNo("po_praybill_b." + PraybillItemVO.CROWNO);
      // ���������֯=�����֯
      mapVo.setSupplyOrgid("po_praybill_b." + PraybillItemVO.PK_ORG);
      // ���������֯�汾
      mapVo.setSupplyOrgvid("po_praybill_b." + PraybillItemVO.PK_ORG_V);
      // ��������
      mapVo.setMaterialid("po_praybill_b." + PraybillItemVO.PK_SRCMATERIAL);
      // ���ϰ汾����
      mapVo.setMaterialvid("po_praybill_b." + PraybillItemVO.PK_MATERIAL);
      // �������ݺ�
      mapVo.setSupplyCode("po_praybill." + PraybillHeaderVO.VBILLCODE);
      // ��������=��������
      mapVo.setSupplyDate("po_praybill_b." + PraybillItemVO.DREQDATE);
      // ��������
      mapVo.setDbillDate("po_praybill." + PraybillHeaderVO.DBILLDATE);
      // ������
      mapVo.setNnum("po_praybill_b." + PraybillItemVO.NNUM);
      // ִ��������=�ۼƶ�������
      mapVo.setNexenum("po_praybill_b." + PraybillItemVO.NACCUMULATENUM);
      // ����������=������-ִ��������
      mapVo
          .setNsupplynum("po_praybill_b." + PraybillItemVO.NNUM
              + " - isnull(po_praybill_b." + PraybillItemVO.NACCUMULATENUM
              + ", 0)");
      // ��Ӧ��
      mapVo.setVendorid(null);
      // ��������
      mapVo.setProductorid("po_praybill_b." + PraybillItemVO.CPRODUCTORID);
      // ��Ŀ
      mapVo.setProjectid("po_praybill_b." + PraybillItemVO.CPROJECTID);
      // �ͻ�
      mapVo.setCustomerid("po_praybill_b." + PraybillItemVO.CASSCUSTID);
      // ������1-10
      mapVo.setFree1("po_praybill_b." + PraybillItemVO.VFREE1);
      mapVo.setFree2("po_praybill_b." + PraybillItemVO.VFREE2);
      mapVo.setFree3("po_praybill_b." + PraybillItemVO.VFREE3);
      mapVo.setFree4("po_praybill_b." + PraybillItemVO.VFREE4);
      mapVo.setFree5("po_praybill_b." + PraybillItemVO.VFREE5);
      mapVo.setFree6("po_praybill_b." + PraybillItemVO.VFREE6);
      mapVo.setFree7("po_praybill_b." + PraybillItemVO.VFREE7);
      mapVo.setFree8("po_praybill_b." + PraybillItemVO.VFREE8);
      mapVo.setFree9("po_praybill_b." + PraybillItemVO.VFREE9);
      mapVo.setFree10("po_praybill_b." + PraybillItemVO.VFREE10);

      // Դͷ������ID
      mapVo.setFirstBid("po_praybill_b." + PraybillItemVO.CFIRSTBID);
      // Դͷ���ݺ�
      mapVo.setFirstCode("po_praybill_b." + PraybillItemVO.VFIRSTCODE);
      // Դͷ����ID
      mapVo.setFirstId("po_praybill_b." + PraybillItemVO.CFIRSTID);
      // Դͷ�����к�
      mapVo.setFirstRowNo("po_praybill_b." + PraybillItemVO.VFIRSTROWNO);
      // Դͷ��������
      mapVo.setFirstType("po_praybill_b." + PraybillItemVO.CFIRSTTYPECODE);
      // ����״̬
      mapVo.setvBillStatus("po_praybill." + PraybillHeaderVO.FBILLSTATUS);
      // ����״̬ö��Ԫ����id
      mapVo.setvBillStatusEnumID(PUMDValue.PrayBillStatus.value());
      // ��Դ������id
      mapVo.setSrcBid("po_praybill_b." + PraybillItemVO.CSOURCEBID);
      // ��Դ����id
      mapVo.setSrcId("po_praybill_b." + PraybillItemVO.CSOURCEID);
      // ��Դ���ݺ�
      mapVo.setSrcCode("po_praybill_b." + PraybillItemVO.VSOURCECODE);
      // ��Դ�����к�
      mapVo.setSrcRowNo("po_praybill_b." + PraybillItemVO.VSOURCEROWNO);
      // ��Դ��������
      mapVo.setSrcType("po_praybill_b." + PraybillItemVO.CSOURCETYPECODE);
      // ������
      mapVo.setCffileid("po_praybill_b." + PraybillItemVO.CFFILEID);
      // ���ݽ�������
      mapVo.setTransType("po_praybill." + PraybillHeaderVO.CTRANTYPEID);
      // �ͻ�������
      mapVo.setCcustmaterialid(null);

      return mapVo;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }
}
