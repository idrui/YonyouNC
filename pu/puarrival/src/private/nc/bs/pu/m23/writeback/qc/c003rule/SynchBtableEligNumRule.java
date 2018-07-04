package nc.bs.pu.m23.writeback.qc.c003rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.uapbd.IMaterialPubService_C;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.pu.m23.entity.ArriveBbVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.JavaType;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>更新子表中合格数量、不合格数量
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-9-28 下午04:35:14
 */
public class SynchBtableEligNumRule implements IRule<ArriveBbVO> {
  private boolean isApprove;

  public SynchBtableEligNumRule(boolean isApprove) {
    this.isApprove = isApprove;
  }

  @Override
  public void process(ArriveBbVO[] paras) {
    if (this.isApprove) {
      this.updateBTableEligNumWhenApp(paras);
    }
    else {
      this.updateBtableEligNumWhenUnApp(paras);
    }
  }

  private void updateArriveItems(List<List<Object>> paraData) {
    StringBuffer sql = new StringBuffer();
    sql.append(" update po_arriveorder_b ");
    sql.append(" set nelignum=(select sum(po_arriveorder_bb.nnum) from po_arriveorder_bb where po_arriveorder_bb.pk_arriveorder_b = po_arriveorder_b.pk_arriveorder_b and po_arriveorder_bb.dr= 0 and po_arriveorder_bb.beligible = 'Y'), ");
    sql.append(" nnotelignum=(select sum(po_arriveorder_bb.nnum) from po_arriveorder_bb where po_arriveorder_bb.pk_arriveorder_b = po_arriveorder_b.pk_arriveorder_b and po_arriveorder_bb.dr= 0 and po_arriveorder_bb.beligible = 'N') ");
    sql.append(" where po_arriveorder_b.dr=0 and pk_arriveorder_b = ? ");
    DataAccessUtils datautil = new DataAccessUtils();
    datautil.update(sql.toString(), new JavaType[] {
      JavaType.String
    }, paraData);
  }

  private void updateBTableEligNumWhenApp(ArriveBbVO[] paras) {
    // 1、按到货单行统计合格数量、不合格数量
    Map<String, UpdateEligNumPara> bid_updatePara_map =
        new HashMap<String, UpdateEligNumPara>();
    for (ArriveBbVO para : paras) {
      if (bid_updatePara_map.get(para.getPk_arriveorder_b()) == null) {
        UpdateEligNumPara updatePara = new UpdateEligNumPara();
        bid_updatePara_map.put(para.getPk_arriveorder_b(), updatePara);
      }
      // 累计合格数量、不合格数量
      bid_updatePara_map.get(para.getPk_arriveorder_b()).updateEligNum(para);
    }

    // 2、查询出需要更新的到货单行VO
    String[] bids = bid_updatePara_map.keySet().toArray(new String[0]);
    VOQuery<ArriveItemVO> util1 = new VOQuery<ArriveItemVO>(ArriveItemVO.class);
    ArriveItemVO[] origItems = util1.query(bids);
    if (origItems.length == 0 || bids.length != origItems.length) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0104")/*
                                                                   * @res
                                                                   * "回写到货单质检结果时，找不到对应的到货单行！"
                                                                   */);
    }

    // 3、持久化更新到货单表体行
    ArriveItemVO[] newItems = new ArriveItemVO[bids.length];
    List<List<Object>> paraData = new ArrayList<List<Object>>();
    for (int i = 0, len = bids.length; i < len; i++) {
      List<Object> rowData = new ArrayList<Object>();
      newItems[i] = (ArriveItemVO) origItems[i].clone();
      String bid = newItems[i].getPk_arriveorder_b();
      // 设置合格数量、不合格数量
      UpdateEligNumPara eligPara = bid_updatePara_map.get(bid);
      UFDouble naccumchknum = origItems[i].getNaccumchecknum();
      UFDouble writeTotalNum = eligPara.getTotanNum();
      if (MathTool.compareTo(writeTotalNum, naccumchknum) > 0) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004040_0", "04004040-0105")/*
                                                                     * @res
                                                                     * "回写到货单质检结果时，出现回写数量大于累计报检数量！"
                                                                     */);
      }

      rowData.add(newItems[i].getPk_arriveorder_b());
      paraData.add(rowData);
    }

    this.updateArriveItems(paraData);
  }

  private void updateBtableEligNumWhenUnApp(ArriveBbVO[] paras) {
    String[] bids =
        (String[]) AggVOUtil.getDistinctFieldArray(paras,
            ArriveBbVO.PK_ARRIVEORDER_B, String.class);

    VOQuery<ArriveItemVO> util2 = new VOQuery<ArriveItemVO>(ArriveItemVO.class);
    ArriveItemVO[] origItems = util2.query(bids);
    if (origItems.length == 0 || bids.length != origItems.length) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0104")/*
                                                                   * @res
                                                                   * "回写到货单质检结果时，找不到对应的到货单行！"
                                                                   */);
    }
    // Map<String, UFBoolean> bidMap = M45PUServices.getMapBysrcBid(bids);
    // if (null == bidMap || bidMap.size() == 0) {
    // return;
    // }

    ArriveItemVO[] newItems = new ArriveItemVO[origItems.length];
    List<List<Object>> paraData = new ArrayList<List<Object>>();
    List<Object> rowData = new ArrayList<Object>();
    for (int i = 0, len = origItems.length; i < len; i++) {
      UFDouble naccumstorenum = MathTool.nvl(origItems[i].getNaccumstorenum());
      UFDouble naccumreplnum = MathTool.nvl(origItems[i].getNaccumreplnum());
      boolean bfaflag =
          origItems[i].getBfaflag() == null ? false : origItems[i].getBfaflag()
              .booleanValue();
      UFDouble nnotelignum = MathTool.nvl(origItems[i].getNnotelignum());
      UFDouble nelignum = MathTool.nvl(origItems[i].getNelignum());
      UFDouble naccumletgoinnum =
          MathTool.nvl(origItems[i].getNaccumletgoinnum());
      String crowno = origItems[i].getCrowno();
      // UFBoolean isPurchaseIn =
      // bidMap.get(origItems[i].getPk_arriveorder_b());
      IMaterialPubService_C service =
          NCLocator.getInstance().lookup(IMaterialPubService_C.class);
      try {
        // 获取物料库存信息
        Map<String, MaterialStockVO> StockInfo =
            service.queryMaterialStockInfoByPks(new String[] {
              origItems[i].getPk_material()
            }, origItems[i].getPk_org(), new String[] {
              MaterialStockVO.STOCKBYCHECK
            });
        MaterialStockVO stockVO = StockInfo.get(origItems[i].getPk_material());
        // v636新需求，当物料不是根据质检结果入库的物料时，不对是否已经入库等条件做控制。
        if (stockVO != null && stockVO.getStockbycheck().booleanValue()) {
          if (MathTool.compareTo(MathTool.add(nelignum, nnotelignum),
              UFDouble.ZERO_DBL) > 0
              && MathTool.compareTo(
                  MathTool.sub(naccumstorenum, naccumletgoinnum),
                  UFDouble.ZERO_DBL) > 0) {
            ExceptionUtils.wrappBusinessException(NCLangResOnserver
                .getInstance().getStrByID("4004040_0", "04004040-0172", null,
                    new String[] {
                      crowno
                    })/* 第{0}行已根据质检报告结果入库，不允许弃审！ */);
          }
          /* 存在入库单实收数量为空或零的行时，质检报告弃审成功 暂时没有找到合理的解决方案，633暂不修改 */
          // // 无紧急放行时如果存在下游入库单则不允许弃审
          // if (MathTool.compareTo(MathTool.add(nelignum, nnotelignum),
          // UFDouble.ZERO_DBL) > 0
          // && UFBoolean.FALSE.equals(origItems[i].getBletgostate())
          // && UFBoolean.TRUE.equals(isPurchaseIn)) {
          // ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          // .getStrByID("4004040_0", "04004040-0172", null, new String[] {
          // crowno
          // })/* 第{0}行已根据质检报告结果入库，不允许弃审！ */);
          // }

          if (naccumreplnum.doubleValue() > 0) {
            ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4004040_0", "04004040-0106")/*
                                                                         * @res
                                                                         * "质检报告对应的到货单行已生成补货订单，不允许弃审！"
                                                                         */);
          }
          if (bfaflag) {
            ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4004040_0", "04004040-0107")/*
                                                                         * @res
                                                                         * "质检报告对应的到货单行已生成资产卡片，不允许弃审！"
                                                                         */);
          }
        }
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
      newItems[i] = (ArriveItemVO) origItems[i].clone();
      rowData.add(newItems[i].getPk_arriveorder_b());
    }
    paraData.add(rowData);
    this.updateArriveItems(paraData);
  }
}

class UpdateEligNumPara {
  // 合格数量
  private UFDouble nelignum = UFDouble.ZERO_DBL;

  // 不合格数量
  private UFDouble nnotelignum = UFDouble.ZERO_DBL;

  public UFDouble getNelignum() {
    return this.nelignum;
  }

  public UFDouble getNnotelignum() {
    return this.nnotelignum;
  }

  public UFDouble getTotanNum() {
    // 合格数量+不合格数量
    return MathTool.add(this.getNelignum(), this.getNnotelignum());
  }

  public void updateEligNum(ArriveBbVO bbVO) {
    if (bbVO.getBeligible().booleanValue()) {
      this.nelignum = this.nelignum.add(bbVO.getNnum());
    }
    else {
      this.nnotelignum = this.nnotelignum.add(bbVO.getNnum());
    }
  }
}
