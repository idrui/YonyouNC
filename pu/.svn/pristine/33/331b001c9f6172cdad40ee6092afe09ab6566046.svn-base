package nc.vo.pu.m23.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.pu.m23.entity.ArriveBbVO;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>用于到货单转单生成入库的查询，对查询出来的表头、表体结合执行表，组合成转单VO
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-6-24 下午02:53:22
 */
public class ArriveTrans45QueryUtil {

  private ArriveBbVO[] bbitems;

  // <到货单行主键，物料的库存页签信息>
  private Map<String, MaterialStockVO> bid_mrlvo_map =
      new HashMap<String, MaterialStockVO>();

  private ArriveItemVO[] bitems;

  private ArriveHeaderVO[] headers;

  public ArriveTrans45QueryUtil(ArriveHeaderVO[] headers,
      ArriveItemVO[] bitems, ArriveBbVO[] bbitems) {
    this.headers = headers;
    this.bitems = bitems;
    this.bbitems = bbitems;
    this.init();
  }

  public ArriveVO[] combine() {
    List<ArriveItemVO> bitemList = new ArrayList<ArriveItemVO>();
    for (ArriveItemVO item : this.bitems) {
      if (!ValueUtils.getBoolean(item.getBletgostate())) {
        // 不是紧急放行状态，清除紧急放行数据
        item.setPk_passbill(null);
        item.setPk_passbill_b(null);
        item.setNaccumletgonum(null);
      }
      // 对于不根据检验结果入库的表体行,不用根据检验明细判断入库
      String bid = item.getPk_arriveorder_b();
      MaterialStockVO mrlVO = this.bid_mrlvo_map.get(bid);
      if (!ValueUtils.getBoolean(mrlVO.getStockbycheck())) {
        // 可入库数量 = 到货主数量 - 累计入库数量 - 累计退货数量
        UFDouble innum = MathTool.sub(item.getNnum(), item.getNaccumstorenum());
        innum = MathTool.sub(innum, item.getNaccumbacknum());
        if (MathTool.greaterThan(innum, UFDouble.ZERO_DBL)) {
          item.setNcanstorenum(innum);
          bitemList.add(item);
        }
        continue;
      }
      // 如果有质检，紧急放行不起作用
      // 紧急放行状态的到货单根据紧急放行数量入库
      if (ValueUtils.getBoolean(item.getBletgostate())
          && MathTool.compareTo(item.getNelignum(), UFDouble.ZERO_DBL) <= 0) {
        // 可入库数量 = 紧急放行数量 - 紧急放行累计入库数量
        UFDouble letgonum = MathTool.nvl(item.getNaccumletgonum());
        UFDouble letgoinnum = MathTool.nvl(item.getNaccumletgoinnum());
        item.setNcanstorenum(letgonum.sub(letgoinnum));
        bitemList.add(item);
        continue;
      }
      // 根据检验结果明细，对表体数据进行拆分
      List<ArriveItemVO> newBitems = this.splitBodyItemByBbItem(item);
      bitemList.addAll(newBitems);
    }
    // 把离散的表头、表体组合成聚合VO
    return this.combineToAgg(bitemList);
  }

  public void setBbitems(ArriveBbVO[] bbitems) {
    this.bbitems = bbitems;
  }

  public void setBitems(ArriveItemVO[] bitems) {
    this.bitems = bitems;
  }

  public void setHeaders(ArriveHeaderVO[] headers) {
    this.headers = headers;
  }

  private ArriveVO[] combineToAgg(List<ArriveItemVO> bitemList) {
    List<ArriveVO> voList = new ArrayList<ArriveVO>();
    for (ArriveHeaderVO head : this.headers) {
      String hid = head.getPk_arriveorder();
      ArriveItemVO[] items = this.findItemVOByHid(hid, bitemList);
      if (ArrayUtils.isEmpty(items)) {
        continue;// 如果没有表体数据，不允许入库
      }
      ArriveVO vo = new ArriveVO();
      vo.setHVO((ArriveHeaderVO) head.clone());
      vo.setBVO(items);
      voList.add(vo);
    }
    return voList.toArray(new ArriveVO[0]);
  }

  private ArriveBbVO[] findBbVOByBid(String bid) {
    // 根据到货单行ID,查找对应的所有的检验结果明细
    if (ArrayUtils.isEmpty(this.bbitems)) {
      return null;
    }
    List<ArriveBbVO> bbitemList = new ArrayList<ArriveBbVO>();
    for (ArriveBbVO bbitem : this.bbitems) {
      if (!bid.equals(bbitem.getPk_arriveorder_b())) {
        continue;
      }
      bbitemList.add(bbitem);
    }
    return bbitemList.toArray(new ArriveBbVO[0]);
  }

  private ArriveItemVO[] findItemVOByHid(String hid,
      List<ArriveItemVO> bitemList) {
    // 根据到货单ID,查找对应的所有的表体数据
    if (bitemList.size() == 0) {
      return null;
    }
    List<ArriveItemVO> itemList = new ArrayList<ArriveItemVO>();
    for (ArriveItemVO item : bitemList) {
      if (!hid.equals(item.getPk_arriveorder())) {
        continue;
      }
      itemList.add(item);
    }
    return itemList.toArray(new ArriveItemVO[0]);
  }

  private void init() {
    // 根据表体的库存组织对于表体数据进行分类
    HashMap<String, ArrayList<ArriveItemVO>> org_bitems =
        CirVOUtil.getFieldVOList(this.bitems, ArriveItemVO.PK_ORG);

    String[] fields = new String[1];
    fields[0] = MaterialStockVO.STOCKBYCHECK;// 根据检验结果入库
    for (Entry<String, ArrayList<ArriveItemVO>> entry : org_bitems.entrySet()) {
      // 查询物料的库存页签信息
      String stock = entry.getKey();
      ArriveItemVO[] items = entry.getValue().toArray(new ArriveItemVO[0]);
      String[] mrlpks =
          CirVOUtil.getDistinctFieldSet(items, ArriveItemVO.PK_MATERIAL)
              .toArray(new String[0]);
      Map<String, MaterialStockVO> map = null;
      map = MaterialPubService.queryMaterialStockInfo(mrlpks, stock, fields);
      if (map == null || map.size() == 0) {
        return;
      }
      // 根据查询出的物料库存信息，构造结构：<到货单行主键，物料的库存页签信息>
      for (ArriveItemVO item : items) {
        String bid = item.getPk_arriveorder_b();
        MaterialStockVO mrlVO = map.get(item.getPk_material());
        if (mrlVO == null) {
          String msg =
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4004040_0", "04004040-0167")/*
                                                * @res
                                                * "查询物料的库存页签属性时，找不到对应的物料信息！"
                                                */;
          ExceptionUtils.wrappBusinessException(msg);
        }
        this.bid_mrlvo_map.put(bid, mrlVO);
      }
    }

  }

  private List<ArriveItemVO> splitBodyItemByBbItem(ArriveItemVO oldItem) {
    List<ArriveItemVO> bitemList = new ArrayList<ArriveItemVO>();
    String bid = oldItem.getPk_arriveorder_b();
    ArriveBbVO[] currBBitems = this.findBbVOByBid(bid);
    if (ArrayUtils.isEmpty(currBBitems)) {

      // bitemList.add(oldItem);

      return bitemList;// 根据检验结果入库的物料必须根据检验结果入库
    }

    for (ArriveBbVO bbitem : currBBitems) {
      ArriveItemVO cloneItem = (ArriveItemVO) oldItem.clone();
      // 到货单子子表主键
      cloneItem.setPk_checkdetail(bbitem.getPk_arriveorder_bb());
      // 数量(不能改，应该还是看原始到货的数量)
      // cloneItem.setNnum(bbitem.getNnum());
      // cloneItem.setNastnum(bbitem.getNastnum());
      // 计算可入库数量
      UFDouble nnum = MathTool.nvl(bbitem.getNnum());
      UFDouble innum = MathTool.nvl(bbitem.getNaccumstorenum());
      cloneItem.setNcanstorenum(nnum.sub(innum));// 同步可入库数量

      // 入库批次
      cloneItem.setPk_batchcode(bbitem.getPk_inbatchcode());
      cloneItem.setVbatchcode(bbitem.getVinbatchcode());
      // 改判
      if (bbitem.getBchanged().booleanValue()) {
        // 改判物料
        cloneItem.setPk_material(bbitem.getPk_chgmaterial());
        cloneItem.setPk_srcmaterial(bbitem.getPk_chgsrcmaterial());
        // 单位、换算率
        cloneItem.setCunitid(bbitem.getCunitid());
        cloneItem.setCastunitid(bbitem.getCastunitid());
        cloneItem.setVchangerate(bbitem.getVchangerate());
        // 改判物料的自由辅助属性
        cloneItem.setVfree1(bbitem.getVchgfree1());
        cloneItem.setVfree2(bbitem.getVchgfree2());
        cloneItem.setVfree3(bbitem.getVchgfree3());
        cloneItem.setVfree4(bbitem.getVchgfree4());
        cloneItem.setVfree5(bbitem.getVchgfree5());
        cloneItem.setVfree6(bbitem.getVchgfree6());
        cloneItem.setVfree7(bbitem.getVchgfree7());
        cloneItem.setVfree8(bbitem.getVchgfree8());
        cloneItem.setVfree9(bbitem.getVchgfree9());
        cloneItem.setVfree10(bbitem.getVchgfree10());
      }
      bitemList.add(cloneItem);
    }
    return bitemList;
  }
}
