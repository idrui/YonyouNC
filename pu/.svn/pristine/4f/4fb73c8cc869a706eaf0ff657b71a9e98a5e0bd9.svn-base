package nc.bs.pu.m23.fa.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.ct.uitl.ArrayUtil;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.utils.ArrivePublicUtil;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
/**
 * 
 * @description
 * 设置转固数量
 * @scene
 * 
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-28 下午2:21:55
 * @author guoyk
 */
public class DealCanStoreNumForFARule implements IRule<ArriveVO> {

  public ArriveVO[] getTransAssetVO(ArriveVO[] vos) {

    List<ArriveItemVO> bvotmplist = new ArrayList<ArriveItemVO>();
    for (ArriveVO vo : vos) {
      for (ArriveItemVO itemVo : vo.getBVO()) {
        bvotmplist.add(itemVo);
      }
    }

    // 获取可入库数量，以到货单子表PK为Key，可入库数量为Value。
    Map<String, UFDouble> canStoreNnums = this.getCanStoreNnums(vos);
    Map<String, MaterialStockVO> bidMrlMap =
        ArrivePublicUtil.queryMaterialStockInfo(bvotmplist
            .toArray(new ArriveItemVO[bvotmplist.size()]));
    // 为所有到货单下的所有子表赋上可入库数量的值。
    for (ArriveVO vo : vos) {
      ArriveItemVO[] itemtransVos = vo.getBVO();
      for (ArriveItemVO itemVO : itemtransVos) {
        MaterialStockVO materialvo =
            bidMrlMap.get(itemVO.getPk_arriveorder_b());
        boolean bstockbycheck =
            ValueUtils.getBoolean(materialvo.getStockbycheck());
        boolean chkfreeflag =
            ValueUtils.getBoolean(materialvo.getChkfreeflag());
        // 如果按质检结果入库，并且非免检。
        if (bstockbycheck && !chkfreeflag) {
          // 如果合格 + 不合格数量 > 0，那转固需要用可入库数量来算，否则的话，还用到货数量。
          if (MathTool.compareTo(
              MathTool.add(itemVO.getNelignum(), itemVO.getNnotelignum()),
              UFDouble.ZERO_DBL) > 0) {
            // 对于基于到货单退货的场景，当到货数量 - 累计退货数量大于可入库数量时，取可入库数量，否则取到货数量 - 累计退货数量。
            UFDouble canStoreNum =
                canStoreNnums.get(itemVO.getPk_arriveorder_b());
            UFDouble remainNum =
                MathTool.sub(itemVO.getNnum(), itemVO.getNaccumbacknum());
            if (MathTool.greaterThan(remainNum, canStoreNum)) {
              itemVO.setNnum(canStoreNum);
            }
            else {
              itemVO.setNnum(remainNum);
            }
          }
          // 合格数量和不合格数量相加不大于0，说明没有检验，抛出异常。
          else {
            ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4004040_0", "04004040-0009")/*
                                                                         * @res
                                                                         * "到货单行"
                                                                         */
                + itemVO.getCrowno()
                + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                    "4004040_0", "04004040-0013")/* @res "物料非免检，请先检验" */);
          }
        }
        // 对于基于到货单退货的场景，扣减累计退货数量
        else {
          itemVO.setNnum(MathTool.sub(itemVO.getNnum(),
              itemVO.getNaccumbacknum()));
        }
      }
    }
    return vos;
  }

  @Override
  public void process(ArriveVO[] vos) {
    if (ArrayUtil.isEmpty(vos)) {
      return;
    }
    this.getTransAssetVO(vos);
  }

  /**
   * 获取可入库数量，在产生设备卡片的时候，根据可入库数量决定。
   * 
   * @param vo 到货单VO，需要从中获取到货单PK。
   * @return 到货单下所有可入库数量的集合 ，以到货单子表PK为Key，可入库数量为Value。
   */
  private Map<String, UFDouble> getCanStoreNnums(ArriveVO[] vos) {
    String[] arriveOrderPks = new String[vos.length];
    for (int i = 0; i < vos.length; i++) {
      arriveOrderPks[i] = vos[i].getHVO().getPk_arriveorder();
    }

    StringBuilder sqlBuilder = new StringBuilder();
    sqlBuilder.append(" select pk_arriveorder_b, sum(nnum) as nnum ");
    sqlBuilder.append(" from po_arriveorder_bb ");
    sqlBuilder.append(" where ");
    sqlBuilder.append(new IDExQueryBuilder(PUTempTable.tmp_po_23_01.name())
        .buildSQL("pk_arriveorder", arriveOrderPks));
    sqlBuilder.append(" and bcanstore = '");
    sqlBuilder.append(UFBoolean.TRUE.toString());
    sqlBuilder.append("' and dr = 0 group by pk_arriveorder_b ");

    Map<String, UFDouble> canStoreNnums = new HashMap<String, UFDouble>();
    IRowSet rowset = new DataAccessUtils().query(sqlBuilder.toString());
    while (rowset.next()) {
      canStoreNnums.put(rowset.getString(0), rowset.getUFDouble(1));
    }
    return canStoreNnums;
  }
}
