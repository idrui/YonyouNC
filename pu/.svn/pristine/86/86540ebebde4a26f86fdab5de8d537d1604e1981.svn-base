package nc.pubimpl.pu.m20.mm;

import nc.pubitf.pu.m20.mm.IQueryPrayExec;
import nc.pubitf.scmpub.scmpub.mmpps.calc.ISupplyResultForCalc;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.mmpps.SupplyResultForCalcVO;
import nc.vo.pu.pub.constant.PUMDValue;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>请购单执行数量查询实现类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-6-29 上午09:24:03
 */
public class QueryPrayExecImpl implements IQueryPrayExec {

  @Override
  public ISupplyResultForCalc getSupplyResult() throws BusinessException {
    try {
      SupplyResultForCalcVO supplyResult = new SupplyResultForCalcVO();

      // 主数量表达式
      String nnumExpress =
          "(isnull(" + "po_praybill_b." + PraybillItemVO.NNUM
              + ", 0) - isnull(" + "po_praybill_b."
              + PraybillItemVO.NACCUMULATENUM + ", 0))";

      // 注册from子句（不包含from关键字）
      StringBuilder fromSql = new StringBuilder();
      fromSql.append(" po_praybill_b po_praybill_b ");
      fromSql
          .append(" inner join po_praybill po_praybill on po_praybill.pk_praybill = po_praybill_b.pk_praybill ");
      supplyResult.setFromSql(fromSql.toString());

      // 注册where子句（不包含where关键字）
      StringBuilder whereSql = new StringBuilder();
      whereSql.append(" po_praybill.dr = 0 and po_praybill_b.dr = 0 ");
      whereSql.append(" and po_praybill.bislatest = 'Y' ");
      whereSql.append(" and po_praybill_b.browclose = 'N' ");
      whereSql.append(" and " + nnumExpress + " > 0 ");
      supplyResult.setWhereSql(whereSql.toString());

      // 注册其它字段
      supplyResult.setSupplyTypeCodeValue(POBillType.PrayBill.getCode()); // 请购单单据类型编码
      supplyResult.setSupplyTypeIdValue(POBillType.PrayBill.getCode());// 请购单单据类型编码
      supplyResult.setSupplyid("po_praybill." + PraybillHeaderVO.PK_PRAYBILL);
      supplyResult
          .setSupplybid("po_praybill_b." + PraybillItemVO.PK_PRAYBILL_B);
      supplyResult.setSupplyRowNo("po_praybill_b." + PraybillItemVO.CROWNO);
      supplyResult.setSupplyOrgid("po_praybill_b." + PraybillItemVO.PK_ORG);
      supplyResult.setSupplyOrgvid("po_praybill_b." + PraybillItemVO.PK_ORG_V);
      supplyResult.setMaterialid("po_praybill_b."
          + PraybillItemVO.PK_SRCMATERIAL);
      supplyResult
          .setMaterialvid("po_praybill_b." + PraybillItemVO.PK_MATERIAL);
      supplyResult.setSupplyCode("po_praybill." + PraybillHeaderVO.VBILLCODE);
      supplyResult.setSupplyDate("po_praybill_b." + PraybillItemVO.DREQDATE);// 需求日期
      // 供给数量 = 请购数量 - 累计订货数量
      supplyResult.setNnum(nnumExpress);
      supplyResult.setReservatioNnum(null);// 预留数量
      supplyResult.setVendorid(null);// 供应商
      supplyResult.setProductorid("po_praybill_b."
          + PraybillItemVO.CPRODUCTORID);
      supplyResult.setProjectid("po_praybill_b." + PraybillItemVO.CPROJECTID);
      supplyResult.setCustomerid("po_praybill_b." + PraybillItemVO.CASSCUSTID);
      supplyResult.setFree1("po_praybill_b." + PraybillItemVO.VFREE1);
      supplyResult.setFree10("po_praybill_b." + PraybillItemVO.VFREE10);
      supplyResult.setFree2("po_praybill_b." + PraybillItemVO.VFREE2);
      supplyResult.setFree3("po_praybill_b." + PraybillItemVO.VFREE3);
      supplyResult.setFree4("po_praybill_b." + PraybillItemVO.VFREE4);
      supplyResult.setFree5("po_praybill_b." + PraybillItemVO.VFREE5);
      supplyResult.setFree6("po_praybill_b." + PraybillItemVO.VFREE6);
      supplyResult.setFree7("po_praybill_b." + PraybillItemVO.VFREE7);
      supplyResult.setFree8("po_praybill_b." + PraybillItemVO.VFREE8);
      supplyResult.setFree9("po_praybill_b." + PraybillItemVO.VFREE9);
      // 来源单据行id
      supplyResult.setCsrcbid("po_praybill_b." + PraybillItemVO.CSOURCEBID);
      // 来源单据id
      supplyResult.setCsrcid("po_praybill_b." + PraybillItemVO.CSOURCEID);
      // 单据状态
      supplyResult
          .setFstatusflag("po_praybill." + PraybillHeaderVO.FBILLSTATUS);
      // 单据状态枚举元数据id
      supplyResult.setFstatusflagEnumID(PUMDValue.PrayBillStatus.value());
      // 来源单据号
      supplyResult.setVsrccode("po_praybill_b." + PraybillItemVO.VSOURCECODE);
      // 来源单据行号
      supplyResult.setVsrcrowno("po_praybill_b." + PraybillItemVO.VSOURCEROWNO);
      // 来源单据类型
      supplyResult.setVsrctype("po_praybill_b."
          + PraybillItemVO.CSOURCETYPECODE);
      return supplyResult;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }
}
