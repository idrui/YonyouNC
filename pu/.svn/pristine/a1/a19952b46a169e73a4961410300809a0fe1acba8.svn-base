package nc.pubimpl.pu.m4201.ia;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.pubitf.pu.m4201.ia.IStockpsQueryForIAClosingCheck;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.parameter.ia.QueryParaVO;

/**
 * @ClassName:StockpsQueryForIAClosingCheckImpl
 * @Description:为存货核算提供关账检查接口关于采购入库单的实现类
 * @author liyjp
 * @date 2014-11-24 上午9:36:04
 */
public class StockpsQueryForIAClosingCheckImpl implements
    IStockpsQueryForIAClosingCheck {

  /**
   * @Title:queryUnsettledPFI
   * @Description:存货核算未结算采购入库单查询接口
   * @param: queryParaVO 查询参数VO
   *         包含pk_financeorg财务组织、startData会计期间开始日期、endpData会计期间结束日期
   * @return String[] 采购入库单表头VO的主键数组(Pk_stockps)
   * @throws BusinessException 业务异常
   */
  @Override
  public String[] queryUnsettledPFI(QueryParaVO queryParaVO)
      throws BusinessException {
    if (queryParaVO == null) {
      return null;
    }

    String[] pk_financeorgs = queryParaVO.getPk_financeorgs(); // 财务组织
    UFDate startData = queryParaVO.getStartData(); // 会计期间开始日期
    UFDate endData = queryParaVO.getEndData(); // 会计期间结束日期

    /*
     * 
     * select distinct pk_stockps from po_purchaseinfi_b where dr=0 and dtocostapdate is
     * null and bsettlefinish='N' and dbizdate
     * between '' and '' 
     */

    SqlBuilder sql = new SqlBuilder();
    sql.append("select distinct pk_stockps from po_purchaseinfi_b ");
    sql.append("where dr=0 and dtocostapdate is null and bsettlefinish='N' ");
    sql.append("and " + PurchaseinFIItemVO.PK_FINANCEORG, pk_financeorgs);
    if (startData != null) {
      sql.append(" and dbizdate >= '" + startData);
      if (endData != null) {
        sql.append("' and  dbizdate <= '" + endData);
      }
      else {
        sql.append("' and dbizdate <= '" + startData);
      }
      sql.append("'");
    }
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rs = utils.query(sql.toString());
    return rs.toOneDimensionStringArray();
  }
}
