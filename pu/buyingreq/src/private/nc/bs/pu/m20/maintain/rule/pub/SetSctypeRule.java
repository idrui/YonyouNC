/**
 * $文件说明$
 * 
 * @author lixyp
 * @version 6.1
 * @see
 * @since 6.1
 * @time 2011-12-27 下午01:30:35
 */
package nc.bs.pu.m20.maintain.rule.pub;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.IMaterialEnumConst;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.Constructor;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;
import nc.vo.pubapp.pattern.pub.MapList;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>设置是否委外默认值
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.1
 * @since 6.1
 * @author lixyp
 * @time 2011-12-27 下午01:30:35
 */
public class SetSctypeRule {

  private StringBuffer errMsg = new StringBuffer();

  /**
   * 父类方法重写
   * 
   * @return
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  public PraybillVO[] process(PraybillVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }
    return this.setSctype(vos);
  }

  /**
   * 方法功能描述：询是否委外。<br>
   * 外系统生成请购单时该字段默认值规则：根据物料档案中的“物料类型”来取值：<br>
   * 如果物料类型为委外件，则此字段自动勾选，该请购单只能生成委外订单；<br>
   * 如果为其他选项，即此字段不勾选，该请购单只能生成采购订单<br>
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   *          <p>
   * @since 6.1
   * @author lixyp
   * @return
   * @time 2011-12-27 下午02:44:21
   */
  public PraybillVO[] setSctype(PraybillVO[] vos) {
    List<PraybillVO> billList = new ArrayList<PraybillVO>();

    for (PraybillVO bill : vos) {
      // 直运时，不分单
      if (null != bill.getHVO().getBdirecttransit()
          && bill.getHVO().getBdirecttransit().booleanValue()) {
        billList.add(bill);
        continue;
      }
      this.split(bill, billList);
    }
    ListToArrayTool<PraybillVO> tool = new ListToArrayTool<PraybillVO>();
    for (PraybillVO bill : billList) {
      if (null == bill.getHVO().getBsctype()) {
        bill.getHVO().setBsctype(UFBoolean.FALSE);
      }
    }

    return tool.convertToArray(billList);
  }

  /**
   * <p>
   * <b>参数说明<b>
   * 
   * @param item 请购单行记录
   * @param stockInfos
   * @return
   */
  private String getKey(PraybillItemVO item,
      Map<String, MaterialStockVO> stockInfos) {
    if (null == stockInfos || null == stockInfos.get(item.getPk_material())) {
      // IBDData ibd = MaterialAccessor.getDocByPk(item.getPk_material());
      // String sCode = ibd.getCode();
      // this.errMsg.append(NCLangResOnserver.getInstance().getStrByID(
      // "4004020_0", "04004020-0092", null, new String[] {
      // sCode
      // })/* 物料编码：{0}没有分配库存组织，无法按照该页签的物料类型分单，请检查！ */);
      // this.errMsg.append(SystemUtils.LINE_SEPARATOR);
      // 没有分配的话默认采购件
      return "NSC";
    }
    if (IMaterialEnumConst.MATERTYPE_OT.equals(stockInfos.get(
        item.getPk_material()).getMartype())) {
      // (委外件)
      return "SC";
    }
    // (非委外件)
    return "NSC";
  }

  /**
   * 根据库存组织和物料取物料库存组织页签下的物料类型
   * <p>
   * <b>参数说明<b>
   * 
   * @param items 请购单行记录
   * @param vo 请购单vo
   * @return
   */
  private Map<String, MaterialStockVO> getStockInfo(PraybillItemVO[] items,
      PraybillVO vo) {
    Set<String> pks = new HashSet<String>();
    for (int i = 0, len = items.length; i < len; i++) {
      pks.add(items[i].getPk_material());
    }
    return MaterialPubService.queryMaterialStockInfo(
        pks.toArray(new String[pks.size()]), vo.getHVO().getPk_org(),
        new String[] {
          MaterialStockVO.MARTYPE
        });

  }

  private void split(PraybillVO bill, List<PraybillVO> billList) {
    MapList<String, PraybillItemVO> index =
        new MapList<String, PraybillItemVO>();

    PraybillItemVO[] vos = bill.getBVO();
    Map<String, MaterialStockVO> stockInfos = this.getStockInfo(vos, bill);
    for (PraybillItemVO vo : vos) {
      String key = this.getKey(vo, stockInfos);
      index.put(key, vo);
    }
    if (!StringUtils.isBlank(this.errMsg.toString())) {
      ExceptionUtils.wrappBusinessException(this.errMsg.toString());
    }
    if (null == bill.getHVO().getBsctype()) {
      bill.getHVO().setBsctype(UFBoolean.FALSE);
    }
    // (只有一个表体行)
    if (index.size() == 1) {
      billList.add(bill);
      // 如果来自 计划订单：转委外请购单，则不根据物料类型设置委外。
      if (bill.getHVO().getBsctype().equals(UFBoolean.TRUE)) {
        return;
      }
      for (String key : index.keySet()) {
        if ("SC".equals(key)) {
          bill.getHVO().setBsctype(UFBoolean.TRUE);
        }
        else {
          bill.getHVO().setBsctype(UFBoolean.FALSE);
        }
      }
      return;
    }
    ListToArrayTool<PraybillItemVO> tool =
        new ListToArrayTool<PraybillItemVO>();
    // 重新构造分单后的单据
    for (String key : index.keySet()) {
      List<PraybillItemVO> list = index.get(key);
      PraybillItemVO[] children = tool.convertToArray(list);
      // 分成两个单据，表头需要复制一份。
      PraybillHeaderVO header = (PraybillHeaderVO) bill.getHVO().clone();
      // 基于生产的业务，因为生产有 转委外请购单，这时不根据物料类型设置委外件。
      if (header.getBsctype().equals(UFBoolean.TRUE)) {
        key = "SC";
      }
      if ("SC".equals(key)) {
        header.setBsctype(UFBoolean.TRUE);
      }
      else {
        header.setBsctype(UFBoolean.FALSE);
      }
      PraybillVO newBill = Constructor.construct(bill.getClass());
      newBill.setParent(header);
      newBill.setBVO(children);
      billList.add(newBill);
    }
  }
}
