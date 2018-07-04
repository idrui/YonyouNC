package nc.bs.pu.m20.query;

import nc.bs.pu.m422x.query.FilterForPlanPosUtil;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

/**
 * 按照岗位过滤物料
 * 
 * @since 6.0
 * @version 2011-4-15 下午12:48:57
 * @author liuchx
 */
public class FilterForPosUtil {

  public static void filterMaterialByPos(QuerySchemeProcessor processor,
      StringBuffer sql) {
    QueryCondition con =
        processor.getQueryCondition(PraybillHeaderVO.BISPOSITIONINV);
    if (con != null && con.getValues() != null) {
      Object bPosition = con.getValues()[0];
      if (UFBoolean.valueOf(bPosition.toString()).booleanValue()) {
        String inSql = FilterForPosUtil.getFilterSql(processor);
        if (null != inSql) {
          sql.append(inSql);
        }
      }
    }
  }

  /**
   * 过滤物料
   * 
   * @param processor
   * @return
   */
  public static String getFilterSql(QuerySchemeProcessor processor) {
    StringBuffer ret = new StringBuffer();
    QueryCondition orgCon =
        processor.getQueryCondition(PraybillItemVO.PK_PRAYBILL_B + "."
            + PraybillItemVO.PK_PURCHASEORG);
    String inSql = null;
    if (null == orgCon) {
      return null;
    }
    inSql = FilterForPlanPosUtil.filterByPosition(orgCon.getValues(), 1);
    if (null != inSql) {
      String tablealias =
          processor.getTableAliasOfAttribute("pk_praybill_b.pk_org");
      ret.append(" and ");
      ret.append(tablealias + "." + PraybillItemVO.PK_MATERIAL);
      ret.append(" in( ");
      ret.append(inSql);
      ret.append(")");
    }
    return ret.toString();
  }

}
