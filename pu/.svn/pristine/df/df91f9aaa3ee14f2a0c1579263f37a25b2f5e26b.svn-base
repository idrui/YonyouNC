package nc.pubimpl.pu.m27;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.pubitf.pu.m27.ISettleBillQueryForIA;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.enumeration.EnumMatchRowType;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.AssertUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>为存货核算提供的查询服务的实现类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-9-8 上午10:52:39
 */
public class SettleBillQueryForIAImpl implements ISettleBillQueryForIA {

  @Override
  public Map<String, UFDouble> queryLatestSettlePrice(String pk_costregion,
      String[] pk_materials) throws BusinessException {
    String err =
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0",
            "04004060-0118")/* @res "采购提供接口提示信息：存货核算传入参数不正确！" */;
    AssertUtils.assertValue(pk_costregion != null && pk_materials != null
        && pk_materials.length > 0, err);

    Map<String, UFDouble> map = new HashMap<String, UFDouble>();
    try {
      // 只查询物料和最新结算价
      String[] fields = {
        SettleBillItemVO.PK_SRCMATERIAL, SettleBillItemVO.NGOODSPRICE
      };
      VOQuery<SettleBillItemVO> query =
          new VOQuery<SettleBillItemVO>(SettleBillItemVO.class, fields);
      // 查询的Where条件
      String whereSql = this.getWhereSql(pk_costregion, pk_materials);
      // 查询出包含了两个字段的VO数组
      SettleBillItemVO[] items = query.queryWithWhereKeyWord(whereSql, null);
      for (SettleBillItemVO item : items) {
        map.put(item.getPk_srcmaterial(), item.getNgoodsprice());
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return map;
  }

  private String getWhereSql(String pk_costregion, String[] pk_materials) {
    IDExQueryBuilder idbuilder =
        new IDExQueryBuilder(PUTempTable.tmp_po_27_12.name());
    String ids =
        idbuilder.buildSQL(SettleBillItemVO.PK_SRCMATERIAL, pk_materials);
    String ids1 =
        idbuilder
            .buildAnotherSQL(SettleBillItemVO.PK_SRCMATERIAL, pk_materials);

    SqlBuilder sb = new SqlBuilder();
    sb.append(" where ");
    sb.append(SettleBillItemVO.PK_COSTREGION, pk_costregion);
    sb.append(" and " + ids);
    sb.append(" and " + SettleBillItemVO.FROWTYPE,
        EnumMatchRowType.StockInvoiceMatch.toInt());
    sb.append(" and coalesce(ngoodsprice, 0) > 0 ");
    sb.append(" and ts in (");
    sb.append("select max(ts) from po_settlebill_b ");
    sb.append(" where ");
    sb.append(SettleBillItemVO.PK_COSTREGION, pk_costregion);
    sb.append(" and " + ids1);
    sb.append(" and " + SettleBillItemVO.FROWTYPE,
        EnumMatchRowType.StockInvoiceMatch.toInt());
    sb.append(" and coalesce(ngoodsprice, 0) > 0 ");
    sb.append(" group by " + SettleBillItemVO.PK_SRCMATERIAL);
    sb.append(" )");
    return sb.toString();
  }
}
