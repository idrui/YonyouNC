package nc.bs.pu.est.rule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.pu.est.rule.fee.EstFeeBSDIVRule;
import nc.bs.pu.m27.settlebill.SettleBillItemQueryBP;
import nc.impl.pubapp.pattern.data.vo.VOInsert;
import nc.impl.pubapp.pattern.database.DBTool;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pu.est.entity.EstVO;
import nc.vo.pu.est.entity.FeeEstDistVO;
import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.est.util.EstVOUtil;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pu.pub.util.ListUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>费用暂估传成本IA
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-24 下午03:16:41
 */
public abstract class AbstractFeeEstTOIARule {

  public void process(EstVO[] vos) {
    if (!SysInitGroupQuery.isIAEnabled()) {
      return;
    }
    if (ArrayUtils.isEmpty(EstVOUtil.getFeeEstVOs(vos))) {
      return;
    }
    GoodsEstVO[] gevos = EstVOUtil.getGoodsEstVos(vos);
    FeeEstDistVO[] fdVos = this.genFDVO(vos, gevos);
    // 更新传IA时的标识,记到费用分摊明细表中,费用结算时回冲用
    this.updateToIAPk(fdVos, (FeeEstVO[]) EstVOUtil.getFeeEstVOs(vos));
    // 不管影响不影响成本，分摊明细一定要生成，插入到数据库中
    fdVos = this.insertFDVos(fdVos);
    EstVO[] estVos = this.filterEstVO(vos);
    if (ArrayUtils.isEmpty(estVos)) {
      return;
    }
    fdVos = this.filterEstFDVO(estVos, fdVos);
    if (null == fdVos) {
      return;
    }
    // 执行暂估到IA
    this.estToIA(estVos, fdVos);
  }

  private FeeEstDistVO[] filterEstFDVO(EstVO[] estVos, FeeEstDistVO[] fdVos) {
    Set<String> estFeeSet =
        new HashSet<String>(Arrays.asList((String[]) AggVOUtil
            .getDistinctItemFieldArray(estVos, FeeEstVO.PK_STOCKPS_FEE,
                String.class)));
    List<FeeEstDistVO> toIAFdLst = new ArrayList<FeeEstDistVO>();
    for (FeeEstDistVO fdVo : fdVos) {
      if (estFeeSet.contains(fdVo.getPk_stockps_fee())) {
        toIAFdLst.add(fdVo);
      }
    }
    if (0 == toIAFdLst.size()) {
      return null;
    }
    return new ListToArrayTool<FeeEstDistVO>().convertToArray(toIAFdLst);
  }

  private EstVO[] filterEstVO(EstVO[] estVos) {
    List<EstVO> estVoList = new ArrayList<EstVO>();
    for (EstVO estVo : estVos) {
      if (ArrayUtils.isEmpty(estVo.getChildrenVO())) {
        continue;
      }
      GoodsEstVO head = estVo.getParentVO();
      UFBoolean istoia = ValueUtils.getUFBoolean(head.getBaffectcost());
      if (!istoia.booleanValue()) {
        continue;
      }
      estVoList.add(estVo);
    }
    if (ListUtil.isEmpty(estVoList)) {
      return null;
    }
    return new ListToArrayTool<EstVO>().convertToArray(estVoList);
  }

  private FeeEstDistVO[] genFDVO(EstVO[] vos, GoodsEstVO[] gevos) {
    Set<String> bidset =
        CirVOUtil.getDistinctFieldSet(gevos, GoodsEstVO.PK_STOCKPS_B);
    SettleBillItemVO[] settleItems =
        this.getSettleInfo(bidset.toArray(new String[bidset.size()]));
    EstFeeBSDIVRule<? extends FeeEstDistVO> divRule = this.getFeeDivProcesser();
    // 生成费用暂估分摊VO
    FeeEstDistVO[] fdVos = divRule.process(vos, settleItems);
    return fdVos;
  }

  private FeeEstDistVO[] insertFDVos(FeeEstDistVO[] fdVos) {
    VOInsert<FeeEstDistVO> inserter = new VOInsert<FeeEstDistVO>();
    return inserter.insert(fdVos);
  }

  private void updateToIAPk(FeeEstDistVO[] fdVos, FeeEstVO[] feeVos) {
    Map<String, FeeEstVO> feeMap = CirVOUtil.createKeyVOMap(feeVos);
    Map<String, String> map = new HashMap<String, String>();
    for (FeeEstDistVO fdVo : fdVos) {
      // 按分摊依据BID+供应商进行分组,一组的FeeEstDistVO汇总生成一条I9
      String bybid = fdVo.getPk_distbybill_b();
      String pk_fee = fdVo.getPk_stockps_fee();
      String pk_supplier = feeMap.get(pk_fee).getPk_supplier();
      String key = bybid + pk_supplier;
      String pk_iasrc_b = map.get(key);
      if (StringUtils.isBlank(pk_iasrc_b)) {
        pk_iasrc_b = new DBTool().getOIDs(1)[0];
        map.put(key, pk_iasrc_b);
      }
      fdVo.setPk_iasrc_b(pk_iasrc_b);
    }
  }

  /**
   * 方法功能描述：执行暂估到IA的操作
   * <p>
   * <b>参数说明</b>
   * 
   * @param gevos 货物暂估VO
   * @param fees 费用暂估VO
   * @param fdVos 费用暂估分摊VO
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-8-20 下午01:31:41
   */
  abstract protected void estToIA(EstVO[] estVos, FeeEstDistVO[] fdVos);

  /**
   * 方法功能描述：得到费用分摊的处理器
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-8-20 下午01:25:18
   */
  abstract protected EstFeeBSDIVRule<? extends FeeEstDistVO> getFeeDivProcesser();

  /**
   * 方法功能描述：根据入库(消耗汇总)BID得到结算信息
   * <p>
   * <b>参数说明</b>
   * 
   * @param bids
   * @return <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-8-20 下午01:31:12
   */
  protected SettleBillItemVO[] getSettleInfo(String[] bids) {
    SettleBillItemQueryBP bp = new SettleBillItemQueryBP();
    return bp.queryBillItemFromGoodSettle(bids);
  }

}
