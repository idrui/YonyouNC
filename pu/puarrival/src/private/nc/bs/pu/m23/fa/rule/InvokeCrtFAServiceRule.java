package nc.bs.pu.m23.fa.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.aim.EquipToScmService;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.utils.ArrivePublicUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 调用资产提供的生成资产卡片接口
 * @scene
 * 生成资产卡片时
 * @param
 * 无
 *
 * @since 6.3
 * @version 2010-5-8 下午04:14:25
 * @author hanbin
 */

public class InvokeCrtFAServiceRule implements IRule<ArriveVO> {
  private List<String> crtCardBidList;

  @Override
  public void process(ArriveVO[] voArray) {
    ArriveVO[] vos = this.buildCrtCardVO(voArray);
    if (ArrayUtils.isEmpty(vos)) {
      TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4004040_0", "04004040-0072")/*
                                        * @res
                                        * "只有资产类物料行且序列号管理物料行且没有入库行，才可以生成设备卡片"
                                        */);
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0073")/*
                                                                   * @res
                                                                   * "没有可以生成设备卡片的表体行"
                                                                   */);
    }
    this.invoke(vos);
    this.updateBFaFlag(voArray);
  }

  private ArriveVO[] buildCrtCardVO(ArriveVO[] voArray) {
    this.crtCardBidList = new ArrayList<String>();
    List<ArriveVO> volist = new ArrayList<ArriveVO>();
    for (ArriveVO vo : voArray) {
      List<ArriveItemVO> blist = new ArrayList<ArriveItemVO>();
      for (ArriveItemVO item : vo.getBVO()) {
        if (!this.isCanCrtCard(item)) {
          continue;
        }
        blist.add(item);
        this.crtCardBidList.add(item.getPk_arriveorder_b());
      }
      if (blist.size() > 0) {
        ArriveVO splitVO = new ArriveVO();
        splitVO.setHVO(vo.getHVO());
        ArriveItemVO[] splitItems = this.splitItem(vo, blist);
        // 2012-06-19 tianft 分单后，存在满足条件的表体行才处理
        if (!ArrayUtils.isEmpty(splitItems)) {
          splitVO.setBVO(splitItems);
          volist.add(splitVO);
        }
      }
    }
    ArriveVO[] retVos = volist.toArray(new ArriveVO[volist.size()]);
    return retVos;
  }

  /**
   * 获取可入库数量，在产生设备卡片的时候，根据可入库数量决定。
   * 
   * @param vo 到货单VO，需要从中获取到货单PK。
   * @return 到货单下所有可入库数量的集合 ，以到货单子表PK为Key，可入库数量为Value。
   */
  private Map<String, UFDouble> getCanStoreNnums(ArriveVO vo) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(" select pk_arriveorder_b, sum(nnum) as nnum ");
    stringBuilder.append(" from po_arriveorder_bb ");
    stringBuilder.append(" where pk_arriveorder = '");
    stringBuilder.append(vo.getHVO().getPk_arriveorder());
    stringBuilder.append("' and bcanstore = '");
    stringBuilder.append(UFBoolean.TRUE.toString());
    stringBuilder.append("' and dr = 0 group by pk_arriveorder_b ");

    Map<String, UFDouble> canStoreNnums = new HashMap<String, UFDouble>();
    IRowSet rowset = new DataAccessUtils().query(stringBuilder.toString());
    while (rowset.next()) {
      canStoreNnums.put(rowset.getString(0), rowset.getUFDouble(1));
    }
    return canStoreNnums;
  }

  private void invoke(ArriveVO[] vos) {
    try {
      TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4004040_0", "04004040-0074")/* @res "调用资产提供的生成设备卡片接口" */);
      EquipToScmService.insertFromArrivalGoods(vos);
      TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4004040_0", "04004040-0075")/* @res "成功调用资产的生成设备卡片接口" */);
    }
    catch (Exception ex) {
      ExceptionUtils.wrappException(ex);
    }
  }

  private boolean isCanCrtCard(ArriveItemVO item) {
    // 过滤掉非资产仓的行
    if (!this.isMrlSerialmana(item)) {
      return false;
    }

    // 过滤掉已入库的行
    if (MathTool.nvl(item.getNaccumstorenum()).doubleValue() > 0) {
      return false;
    }

    return true;
  }

  private boolean isMrlSerialmana(ArriveItemVO item) {
    String[] pk_material = new String[1];
    pk_material[0] = item.getPk_material();
    String pk_org = item.getPk_org();
    String[] stockFields = new String[1];
    stockFields[0] = MaterialStockVO.SERIALMANAFLAG;
    MaterialStockVO[] stockMrls =
        MaterialPubService.queryMaterialStockInfoByPks(pk_material, pk_org,
            stockFields);
    if (ArrayUtils.isEmpty(stockMrls)) {
      String message =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
              "04004040-0076")/* @res "检查物料是否序列号管理时，没有找到对应的信息" */;
      ExceptionUtils.wrappBusinessException(message);
    }
    else if (stockMrls.length > 1) {
      String message =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
              "04004040-0077")/* @res "检查物料是否序列号管理时，找到多个物料" */;
      ExceptionUtils.wrappBusinessException(message);
    }

    return ValueUtils.getBoolean(stockMrls[0].getSerialmanaflag());
  }

  /**
   * modify by liuga
   * cause 做尾差处理（可能会有问题...）
   * 
   * @param blist
   * @return
   */
  private ArriveItemVO[] splitItem(ArriveVO vo, List<ArriveItemVO> blist) {
    List<ArriveItemVO> bsplitlist = new ArrayList<ArriveItemVO>();
    Map<String, UFDouble> canStoreNnums = this.getCanStoreNnums(vo);
    Map<String, MaterialStockVO> bidMrlMap =
        ArrivePublicUtil.queryMaterialStockInfo(vo.getBVO());
    for (ArriveItemVO item : blist) {
      // 得到原币含税金额,设备卡片的含税价格为原币含税价格
      UFDouble ufd = item.getNorigtaxmny();
      // 累加价税合计,以处理尾差
      UFDouble nacctaxmny = UFDouble.ZERO_DBL;
      // 可能存在四舍五入情况，计算出差值
      UFDouble realFaNum = null;
      MaterialStockVO materialvo = bidMrlMap.get(item.getPk_arriveorder_b());
      boolean bstockbycheck =
          ValueUtils.getBoolean(materialvo.getStockbycheck());
      boolean chkfreeflag = ValueUtils.getBoolean(materialvo.getChkfreeflag());
      // 按质检结果入库,非免检物料入库需要校验检验,
      // 如果合格+不合格数量>0,说明是质检物料并根据检验结果入库,那传设备卡片需要用合格数量来算,否则的换,还用到货数量
      if (bstockbycheck
          && !chkfreeflag
          && MathTool.compareTo(
              MathTool.add(item.getNelignum(), item.getNnotelignum()),
              UFDouble.ZERO_DBL) > 0) {
        // 对于基于到货单退货的场景，当到货数量 - 累计退货数量大于可入库数量时，取可入库数量，否则取到货数量 - 累计退货数量。
        UFDouble canStoreNum = canStoreNnums.get(item.getPk_arriveorder_b());
        UFDouble remainNum =
            MathTool.sub(item.getNnum(), item.getNaccumbacknum());
        if (MathTool.greaterThan(remainNum, canStoreNum)) {
          realFaNum = canStoreNum;
        }
        else {
          realFaNum = remainNum;
        }

      }
      else {
        // 对于基于到货单退货的场景，扣减累计退货数量
        realFaNum = MathTool.sub(item.getNnum(), item.getNaccumbacknum());
      }
      // 2012-06-19 tianft 不具备生产资产卡片的条件，执行下个循环
      if (MathTool.isZero(realFaNum)) {
        continue;
      }

      // 如果合格数量<总数量就不用考虑尾差倒挤
      UFDouble arrnnum =
          MathTool.nvl((UFDouble) item.getAttributeValue(ArriveItemVO.NNUM));
      double diff = realFaNum.doubleValue() - realFaNum.intValue();
      for (int k = 0, len = realFaNum.intValue(); k < len; k++) {
        ArriveItemVO copyItem = (ArriveItemVO) item.clone();
        if (diff <= 0 && k == arrnnum.intValue() - 1) {
          // 当差值小于0时，最后的一条的数量为1+差异
          UFDouble nnum = new UFDouble(1 + diff);
          // 均值尾差处理
          copyItem.setAttributeValue(ArriveItemVO.NCANSTORENUM, nnum);
          copyItem.setNorigtaxprice(ufd.sub(nacctaxmny));
        }
        else {
          copyItem.setAttributeValue(ArriveItemVO.NCANSTORENUM,
              UFDouble.ONE_DBL);
          nacctaxmny = MathTool.add(nacctaxmny, copyItem.getNorigtaxprice());
        }
        bsplitlist.add(copyItem);
      }
      if (diff > 0) {
        // 当差值大于0时，增加一条的数量为差异
        ArriveItemVO copyItem = (ArriveItemVO) item.clone();
        copyItem.setAttributeValue(ArriveItemVO.NCANSTORENUM,
            new UFDouble(diff));
        bsplitlist.add(copyItem);
      }
    }
    TimeLog.info(NCLangResOnserver.getInstance().getStrByID("4004040_0",
        "04004040-0177", null, new String[] {
          String.valueOf(bsplitlist.size())
        })/* 可以生成 {0} 条设备卡片 */);
    return bsplitlist.toArray(new ArriveItemVO[bsplitlist.size()]);
  }

  private void updateBFaFlag(ArriveVO[] vos) {
    List<ArriveItemVO> list = new ArrayList<ArriveItemVO>();
    for (ArriveVO vo : vos) {
      for (ArriveItemVO item : vo.getBVO()) {
        if (!this.crtCardBidList.contains(item.getPk_arriveorder_b())) {
          continue;
        }
        item.setBfaflag(UFBoolean.TRUE);
        list.add(item);
      }
    }
    ArriveItemVO[] items = list.toArray(new ArriveItemVO[list.size()]);
    VOUpdate<ArriveItemVO> update = new VOUpdate<ArriveItemVO>();
    update.update(items, new String[] {
      ArriveItemVO.BFAFLAG
    });
  }
}
