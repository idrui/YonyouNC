package nc.bs.pu.m4201;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.pu.m4201.rule.CancelConfirmTOAPRule;
import nc.bs.pu.m4201.rule.CancelConfirmTOIARule;
import nc.bs.pu.m4201.rule.UnSignEstCheckRule;
import nc.bs.pu.m4201.rule.UnSignSettleCheckRule;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m4201.entity.PurchaseinFIHeaderVO;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pu.m4201.entity.PurchaseinFIVO;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>删除库存财务记录的BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-8-27 下午04:45:50
 */
public class StockFinanceDeleteBP {
  public void delete(String[] cpurchaseinids) {
    PurchaseinFIVO[] vos = this.queryFIVOs(cpurchaseinids);
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    AroundProcesser<PurchaseinFIVO> proccesser =
        new AroundProcesser<PurchaseinFIVO>(null);
    this.addCancelRule(proccesser);
    proccesser.before(vos);
    String[] saBId =
        (String[]) AggVOUtil.getDistinctItemFieldArray(vos,
            PurchaseinFIItemVO.PK_STOCKPS_B, String.class);
    this.realDelete(cpurchaseinids, saBId);
    proccesser.after(vos);
  }

  private void addCancelRule(AroundProcesser<PurchaseinFIVO> prcr) {
    prcr.addBeforeFinalRule(new UnSignEstCheckRule());// 检查是否已经暂估过
    prcr.addBeforeFinalRule(new UnSignSettleCheckRule());// 检查是否已经结算过
    prcr.addBeforeFinalRule(new CancelConfirmTOIARule());// 取消自动传成本
    prcr.addBeforeFinalRule(new CancelConfirmTOAPRule());// 取消自动传应付
  }

  private PurchaseinFIVO[] queryFIVOs(String[] ids) {
    BillQuery<PurchaseinFIVO> bquery =
        new BillQuery<PurchaseinFIVO>(PurchaseinFIVO.class);
    return bquery.query(ids);
  }

  /**
   * 方法功能描述：简要描述本方法的功能。
   * <p>
   * <b>参数说明</b>
   * 
   * @param saHId
   * @throws DAOException <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-6-8 上午08:54:06
   */
  private void realDelete(String[] saHId, String[] saBId) {
    BaseDAO dao = new BaseDAO();
    try {
      dao.deleteByPKs(PurchaseinFIItemVO.class, saBId);
      dao.deleteByPKs(PurchaseinFIHeaderVO.class, saHId);
    }
    catch (DAOException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
  }
}
