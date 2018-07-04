package nc.pubimpl.pu.m4202.ia;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.pubitf.pu.m4202.ia.IConsumeGatherQueryForIAClosingCheck;
import nc.vo.pu.m4202.entity.VmiFIHeaderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.scmpub.parameter.ia.QueryParaVO;

/**
 * @ClassName:ConsumeGatherQueryForIAClosingCheck
 * @Description:消耗汇总单的存货核算关账接口的实现类
 * @author liyjp
 * @date 2014-11-24 下午4:03:35
 */
public class ConsumeGatherQueryForIAClosingCheckImpl implements
    IConsumeGatherQueryForIAClosingCheck {
  /**
   * @Title:queryUnsettledConsumeGather
   * @Description:存货核算未结算消耗汇总单查询接口
   * @param:@param queryParaVO 查询参数VO
   *               包含pk_financeorg财务组织、startData会计期间开始日期、endpData会计期间结束日期
   * @return String[] 消耗汇总财务表头VO的主键[]（Pk_stockps_b）
   * @throws BusinessException 业务异常
   */
  @Override
  public String[] queryUnsettledConsumeGather(QueryParaVO queryParaVO)
      throws BusinessException {

    if (queryParaVO == null) {
      return null;
    }
    String pk_financeorg = queryParaVO.getPk_financeorg(); // 财务组织
    UFDate startData = queryParaVO.getStartData(); // 会计期间开始日期
    UFDate endpData = queryParaVO.getEndData(); // 会计期间结束日期
    /*
     * 组建sql
     * select * from po_vmifi where dr=0 and (dbilldate between 'A' and 'B') and
     * pk_org='' and (dtocostapdate is null) and bsettlefinish='N';
     */
    StringBuffer sb =
        new StringBuffer(" and pk_org='" + pk_financeorg
            + "' and (dtocostapdate is null) and bsettlefinish='N'");
    if (startData != null) {
      sb.append("and (dbilldate between '" + startData);
      if (endpData != null) {
        sb.append("' and '" + endpData + "')");
      }
      else {
        sb.append("' and '" + startData + "')");
      }
    }
    /* 与数据库交互 */
    VOQuery<VmiFIHeaderVO> voquery =
        new VOQuery<VmiFIHeaderVO>(VmiFIHeaderVO.class);
    VmiFIHeaderVO[] vmiFIHeaderVOs = voquery.query(sb.toString(), null);

    /* 构建消耗汇总单主键数组 */
    String[] ids = new String[vmiFIHeaderVOs.length];
    int count = 0;
    for (VmiFIHeaderVO vo : vmiFIHeaderVOs) {
      ids[count] = vo.getPk_stockps_b();
      count++;
    }
    return ids;
  }
}
