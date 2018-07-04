package nc.bs.pu.est.rule.fee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pu.costfactor.entity.CostfactorItemVO;
import nc.vo.pu.costfactor.entity.CostfactorViewVO;
import nc.vo.pu.est.entity.EstVO;
import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.est.util.EstVOUtil;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>费用暂估信息填充
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhyhang
 * @time 2010-6-19 上午09:15:18
 */
public class FeeEstInfoFillRule {

  public void process(EstVO[] vos) {
    FeeEstVO[] fees = (FeeEstVO[]) EstVOUtil.getFeeEstVOs(vos);
    if (ArrayUtils.isEmpty(fees)) {
      return;
    }
    this.fillCostfactor(fees);
    Map<String, GoodsEstVO> headMap = AggVOUtil.createHeadMap(vos);
    this.setEstFlag(fees, headMap);
  }

  private void fillCostfactor(FeeEstVO[] fees) {
    Map<String, ArrayList<FeeEstVO>> fiOrgMap =
        CirVOUtil.getFieldVOList(fees, FeeEstVO.PK_FINANCEORG);
    for (Entry<String, ArrayList<FeeEstVO>> entry : fiOrgMap.entrySet()) {
      String fiOrg = entry.getKey();
      List<FeeEstVO> fiOrgFeeList = entry.getValue();
      ListToArrayTool<FeeEstVO> tool = new ListToArrayTool<FeeEstVO>();
      Set<String> mPkSet =
          CirVOUtil.getDistinctFieldSet(tool.convertToArray(fiOrgFeeList),
              FeeEstVO.PK_SRCFEEMATERIAL);
      CostfactorViewVO[] cfviews = this.getCostFactor(fiOrg, mPkSet);
      Map<String, ArrayList<CostfactorViewVO>> mpkFeeMap =
          CirVOUtil.getFieldVOList(cfviews, CostfactorItemVO.PK_SRCMATERIAL);
      for (FeeEstVO fee : fiOrgFeeList) {
        String srcpk = fee.getPk_srcfeematerial();
        CostfactorViewVO cfview = mpkFeeMap.get(srcpk).get(0);
        fee.setPk_costfactor(cfview.getPk_costfactor());
        fee.setCostfacotorNum(cfview.getIfactororder().intValue());
      }
    }
  }

  private CostfactorViewVO[] getCostFactor(String fiOrg, Set<String> mPkSet) {
    CostfactorViewVO[] cfviews =
        EstVOUtil.getCostFactor(fiOrg,
            mPkSet.toArray(new String[mPkSet.size()]));
    if (ArrayUtils.isEmpty(cfviews) || mPkSet.size() != cfviews.length) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004060_0", "04004060-0062")/*
                                                                   * @res
                                                                   * "成本要素已被修改，请重新查询，再做暂估操作!"
                                                                   */);
    }
    return cfviews;
  }

  private void setEstFlag(FeeEstVO[] fees, Map<String, GoodsEstVO> headMap) {
    boolean iaEnable = SysInitGroupQuery.isIAEnabled();
    for (FeeEstVO fee : fees) {
      String pk_fiorg = fee.getPk_financeorg();
      UFBoolean estAP = PUSysParamUtil.getPO52(pk_fiorg);
      estAP = ValueUtils.getUFBoolean(estAP);
      fee.setBtoap(estAP);
      String pk_stockps_b = fee.getPk_stockps_b();
      GoodsEstVO head = headMap.get(pk_stockps_b);
      UFBoolean estIA = UFBoolean.FALSE;
      if (null != head) {
        estIA = head.getBaffectcost();
      }
      estIA = ValueUtils.getUFBoolean(estIA);
      // 2012-05-02 tianft 需要根据ia是否启用设置此标志。解决暂估报表查询费用数据不正确问题。
      fee.setBtoia(iaEnable ? estIA : UFBoolean.FALSE);
    }
  }

}
