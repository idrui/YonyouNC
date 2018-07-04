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
 * 齐套分析查询供给-请购单字段映射实现类
 * 
 * @since 6.3
 * @version 2012-11-2 上午09:24:24
 * @author fanly3
 */
public class QueryPrayOrderMapForSaImpl implements IQueryPrayOrderMapForSa {

  @Override
  public PrayOrderSaSupplyMapVO getSaSupplyMapVO() throws BusinessException {
    try {
      // 来源表
      StringBuilder fromSql = new StringBuilder();
      fromSql
          .append(" po_praybill_b po_praybill_b inner join po_praybill po_praybill ");
      fromSql
          .append(" on po_praybill_b.pk_praybill = po_praybill.pk_praybill ");
      // 过滤条件
      StringBuilder whereSql = new StringBuilder();
      whereSql.append(" po_praybill.dr = 0 and po_praybill_b.dr = 0 ");
      // 行关闭为False
      whereSql.append(" and po_praybill_b.browclose = 'N' ");
      // 最新版本
      whereSql.append(" and po_praybill.bislatest = 'Y' ");
      // 主数量-累计订货主数量大于零
      whereSql
          .append(" and (po_praybill_b.nnum - isnull(po_praybill_b.naccumulatenum,0)) > 0");

      PrayOrderSaSupplyMapVO mapVo = new PrayOrderSaSupplyMapVO();
      mapVo.setFromSql(fromSql.toString());
      mapVo.setWhereSql(whereSql.toString());
      // 供给单据类型的编码值
      mapVo.setSupplyTypeCodeValue(POBillType.PrayBill.getCode());
      // 供给单据类型的主键值
      mapVo.setSupplyTypeIdValue(POBillType.PrayBill.getCode());
      // 供给表头主键
      mapVo.setSupplyid("po_praybill." + PraybillHeaderVO.PK_PRAYBILL);
      // 供给表体主键
      mapVo.setSupplybid("po_praybill_b." + PraybillItemVO.PK_PRAYBILL_B);
      // 供给单据行号
      mapVo.setSupplyRowNo("po_praybill_b." + PraybillItemVO.CROWNO);
      // 供给库存组织=库存组织
      mapVo.setSupplyOrgid("po_praybill_b." + PraybillItemVO.PK_ORG);
      // 供给库存组织版本
      mapVo.setSupplyOrgvid("po_praybill_b." + PraybillItemVO.PK_ORG_V);
      // 物料主键
      mapVo.setMaterialid("po_praybill_b." + PraybillItemVO.PK_SRCMATERIAL);
      // 物料版本主键
      mapVo.setMaterialvid("po_praybill_b." + PraybillItemVO.PK_MATERIAL);
      // 供给单据号
      mapVo.setSupplyCode("po_praybill." + PraybillHeaderVO.VBILLCODE);
      // 供给日期=需求日期
      mapVo.setSupplyDate("po_praybill_b." + PraybillItemVO.DREQDATE);
      // 单据日期
      mapVo.setDbillDate("po_praybill." + PraybillHeaderVO.DBILLDATE);
      // 主数量
      mapVo.setNnum("po_praybill_b." + PraybillItemVO.NNUM);
      // 执行主数量=累计订货数量
      mapVo.setNexenum("po_praybill_b." + PraybillItemVO.NACCUMULATENUM);
      // 供给主数量=主数量-执行主数量
      mapVo
          .setNsupplynum("po_praybill_b." + PraybillItemVO.NNUM
              + " - isnull(po_praybill_b." + PraybillItemVO.NACCUMULATENUM
              + ", 0)");
      // 供应商
      mapVo.setVendorid(null);
      // 生产厂商
      mapVo.setProductorid("po_praybill_b." + PraybillItemVO.CPRODUCTORID);
      // 项目
      mapVo.setProjectid("po_praybill_b." + PraybillItemVO.CPROJECTID);
      // 客户
      mapVo.setCustomerid("po_praybill_b." + PraybillItemVO.CASSCUSTID);
      // 自由项1-10
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

      // 源头单据行ID
      mapVo.setFirstBid("po_praybill_b." + PraybillItemVO.CFIRSTBID);
      // 源头单据号
      mapVo.setFirstCode("po_praybill_b." + PraybillItemVO.VFIRSTCODE);
      // 源头单据ID
      mapVo.setFirstId("po_praybill_b." + PraybillItemVO.CFIRSTID);
      // 源头单据行号
      mapVo.setFirstRowNo("po_praybill_b." + PraybillItemVO.VFIRSTROWNO);
      // 源头单据类型
      mapVo.setFirstType("po_praybill_b." + PraybillItemVO.CFIRSTTYPECODE);
      // 单据状态
      mapVo.setvBillStatus("po_praybill." + PraybillHeaderVO.FBILLSTATUS);
      // 单据状态枚举元数据id
      mapVo.setvBillStatusEnumID(PUMDValue.PrayBillStatus.value());
      // 来源单据行id
      mapVo.setSrcBid("po_praybill_b." + PraybillItemVO.CSOURCEBID);
      // 来源单据id
      mapVo.setSrcId("po_praybill_b." + PraybillItemVO.CSOURCEID);
      // 来源单据号
      mapVo.setSrcCode("po_praybill_b." + PraybillItemVO.VSOURCECODE);
      // 来源单据行号
      mapVo.setSrcRowNo("po_praybill_b." + PraybillItemVO.VSOURCEROWNO);
      // 来源单据类型
      mapVo.setSrcType("po_praybill_b." + PraybillItemVO.CSOURCETYPECODE);
      // 特征码
      mapVo.setCffileid("po_praybill_b." + PraybillItemVO.CFFILEID);
      // 单据交易类型
      mapVo.setTransType("po_praybill." + PraybillHeaderVO.CTRANTYPEID);
      // 客户物料码
      mapVo.setCcustmaterialid(null);

      return mapVo;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }
}
