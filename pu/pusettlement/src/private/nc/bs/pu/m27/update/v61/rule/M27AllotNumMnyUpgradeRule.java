package nc.bs.pu.m27.update.v61.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.pu.m27.settlebill.rule.StockInfoUtil;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.SettleFeeAllotDetailVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m4201.enumeration.EnumToIAFlag;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

public class M27AllotNumMnyUpgradeRule implements IRule<SettleFeeAllotDetailVO> {

  @Override
  public void process(SettleFeeAllotDetailVO[] vos) {
    SettleBillVO[] settlebills = this.getSettleBillVO(vos);
    StockSettleVO[] stocksettlevos = this.queryStockStlVO(settlebills);

    Map<String, StockSettleVO> ssVOMap =
        CirVOUtil.createKeyVOMap(stocksettlevos);
    Map<String, UFDouble[]> stlMap = this.getStlMap(vos);
    for (SettleFeeAllotDetailVO vo : vos) {
      String pk_allotbillbid = vo.getPk_allotbillbid();
      UFDouble adjnum = null;
      UFDouble adjmny = null;
      if (ssVOMap.containsKey(pk_allotbillbid)) {
        StockSettleVO ssVo = ssVOMap.get(pk_allotbillbid);
        if (EnumToIAFlag.ConfirmToIA.value().equals(ssVo.getFdirtoiatype())) {
          // 确认过成本直接使用确认成本的数据
          adjnum = ssVo.getNinnum();
          adjmny = ssVo.getNmny();
        }
        else {
          // 分摊数量 = 累计结算数量－累计暂估部分结算数量+暂估数量
          adjnum =
              MathTool.add(
                  MathTool.sub(ssVo.getNaccumsettlenum(),
                      ssVo.getNaccestcoststtlnum()), ssVo.getNestnum());
          // 分摊金额= 暂估前累计结算金额+暂估本币含税金额(本币含税金额)
          adjmny = MathTool.add(ssVo.getNaccpreeststtlmny(), ssVo.getNestmny());
        }
        if (MathTool.isZero(adjmny)) {
          adjmny = adjnum;
        }
      }
      else if (stlMap.containsKey(pk_allotbillbid)) {
        UFDouble[] values = stlMap.get(pk_allotbillbid);
        adjnum = values[0];
        adjmny = values[1];
      }

      vo.setNallotbillnum(adjnum);
      vo.setNallotbillmny(adjmny);
      vo.setStatus(VOStatus.UPDATED);
    }
  }

  private SettleBillVO[] getSettleBillVO(SettleFeeAllotDetailVO[] vos) {
    Set<String> idSet = new HashSet<String>();
    for (SettleFeeAllotDetailVO vo : vos) {
      idSet.add(vo.getPk_settlebill());
    }
    String[] ids = idSet.toArray(new String[idSet.size()]);
    BillQuery<SettleBillVO> query =
        new BillQuery<SettleBillVO>(SettleBillVO.class);
    return query.query(ids);
  }

  private Map<String, UFDouble[]> getStlMap(SettleFeeAllotDetailVO[] vos) {
    Map<String, UFDouble[]> map = new HashMap<String, UFDouble[]>();
    Set<String> feeallotbidSet = new HashSet<String>();
    for (SettleFeeAllotDetailVO vo : vos) {
      feeallotbidSet.add(vo.getPk_allotbillbid());
    }

    String[] bids = feeallotbidSet.toArray(new String[feeallotbidSet.size()]);
    VOQuery<SettleBillItemVO> query =
        new VOQuery<SettleBillItemVO>(SettleBillItemVO.class);
    SettleBillItemVO[] itemvos = query.query(bids);
    if (ArrayUtils.isEmpty(itemvos)) {
      return map;
    }

    for (SettleBillItemVO item : itemvos) {
      String pk_settlebill_b = item.getPk_settlebill_b();
      UFDouble[] value = new UFDouble[] {
        item.getNsettlenum(), item.getNgoodsmoney()
      };
      map.put(pk_settlebill_b, value);
    }
    return map;
  }

  private StockSettleVO[] queryStockStlVO(SettleBillVO[] vos) {
    StockInfoUtil siUtil = new StockInfoUtil(vos);
    List<StockSettleVO> ssVoLst = new ArrayList<StockSettleVO>();
    for (SettleBillVO vo : vos) {
      for (SettleBillItemVO item : vo.getChildrenVO()) {
        if (StringUtils.isBlank(item.getPk_stock_b())) {
          continue;
        }
        ssVoLst.add(siUtil.getStockSettleVO(item.getPk_stock_b()));
      }
    }
    if (ssVoLst.size() == 0) {
      return new StockSettleVO[0];
    }
    StockSettleVO[] stlVOs = new StockSettleVO[ssVoLst.size()];
    for (int i = 0; i < ssVoLst.size(); i++) {
      stlVOs[i] = ssVoLst.get(i);
    }
    return stlVOs;
    // return new ListToArrayTool<StockSettleVO>().convertToArray(ssVoLst);
  }

}
