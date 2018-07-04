package nc.pubimpl.pu.m4203.ia;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.pubitf.pu.m4203.ia.IEntrustProcessStorageQueryForIACC;
import nc.vo.pu.m4203.entity.SubcontinFIHeaderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.scmpub.parameter.ia.QueryParaVO;

/**
 * @ClassName:EntrustProcessStorageQueryForIACCImpl
 * @Description:委托加工单为存货核算提供关账检查接口的实现类
 * @author liyjp
 * @date 2014-11-24 上午11:30:34
 */
public class EntrustProcessStorageQueryForIACCImpl implements
    IEntrustProcessStorageQueryForIACC {
  /**
   * @Title:queryUnsettledPFI
   * @Description:存货核算未结算委托加工单查询
   * @param: queryParaVO 查询参数VO
   *         包含pk_financeorg财务组织、startData会计期间开始日期、endpData会计期间结束日期
   * @return String[] 采购入库单表头VO的主键数组（Pk_stockps）
   * @throws BusinessException 业务异常
   */
  @Override
  public String[] queryUnsettledEPS(QueryParaVO queryParaVO)
      throws BusinessException {
    if (queryParaVO == null) {
      return null;
    }
    String pk_financeorg = queryParaVO.getPk_financeorg(); // 财务组织
    UFDate startData = queryParaVO.getStartData(); // 会计期间开始日期
    UFDate endpData = queryParaVO.getEndData(); // 会计期间结束日期

    /*
     * 拼凑sql语句
     * select * from po_subcontinfi where dr=0 and pk_org='0001D11000000000GHDQ'
     * and pk_stockps in(
     * select pk_stockps from po_subcontinfi_b where (dbizdate between
     * '2014-11-06 00:00:00' and '2015-11-06 00:00:00') andr dr=0 and
     * dtocostapdate is null and bsettlefinish='N')
     */
    StringBuffer sb =
        new StringBuffer(" and pk_org='" + pk_financeorg
            + "' and pk_stockps in(");
    sb.append("select pk_stockps from po_subcontinfi_b where dr=0 and "
        + "dtocostapdate is" +
        " null and bsettlefinish='N'");
    if (startData != null) {
      sb.append(" and (dbizdate between '" + startData);
      if (endpData != null) {
        sb.append("' and '" + endpData + "')");
      }
      else {
        sb.append("' and '" + startData + "')");
      }
    }
    sb.append(")");
    /* 查询数据库 获得表头实体 */
    VOQuery<SubcontinFIHeaderVO> voquery =
        new VOQuery<SubcontinFIHeaderVO>(SubcontinFIHeaderVO.class);
    SubcontinFIHeaderVO[] subcontinFIHeaderVO =
        voquery.query(sb.toString(), null);

    /* 构建委托加工单主键数组 */
    String[] ids = new String[subcontinFIHeaderVO.length];
    int count = 0;
    for (SubcontinFIHeaderVO vo : subcontinFIHeaderVO) {
      ids[count] = vo.getPk_stockps();
      count++;
    }

    return ids;
  }
}
