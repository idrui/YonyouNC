package nc.bs.pu.m4201;

import java.util.ArrayList;
import java.util.List;

import nc.bs.dao.BaseDAO;
import nc.bs.pu.m27.rule.ContranctInfoRedundanceRule;
import nc.bs.pu.m27.rule.FillNormalPurFlagRule;
import nc.bs.pu.m27.rule.FillRowAffectCostFlagRule;
import nc.bs.pu.m27.rule.StockFIDuplicateCheckRule;
import nc.bs.pu.m4201.rule.CalCostPriceRule;
import nc.bs.pu.m4201.rule.ChangeDataForITAfterRule;
import nc.bs.pu.m4201.rule.FillRowAffectPCCostFlagRule;
import nc.bs.pub.pf.PfUtilTools;
import nc.vo.ic.m45.entity.PurchaseInBodyVO;
import nc.vo.ic.m45.entity.PurchaseInVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m4201.entity.PurchaseinFIHeaderVO;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pu.m4201.entity.PurchaseinFIVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>插入库存财务信息的BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-8-27 下午04:46:05
 */
public class StockFinanceInsertBP {
  public void insert(PurchaseInVO[] purchaseinVos) {

    // 过滤掉不需要暂估结算的行
    PurchaseInVO[] vosFiltered = this.filterRow(purchaseinVos);

    if (ArrayUtils.isEmpty(vosFiltered)) {
      return;
    }

    // 执行VO交换，获得库存财务的VO
    PurchaseinFIVO[] voaStock = this.runChangeData(vosFiltered);
    try {
      // 普通采购还是集采标志
      new FillNormalPurFlagRule<PurchaseinFIVO>().process(voaStock);
      // 是否影响成本标志
      new FillRowAffectCostFlagRule<PurchaseinFIVO>().process(voaStock);
      // 是否影响利润中心成本标志
      new FillRowAffectPCCostFlagRule().process(voaStock);
      // 数据检查，放在影响成本标志之后
      new StockFIDuplicateCheckRule<PurchaseinFIVO>().process(voaStock);
      // 冗余合同号
      new ContranctInfoRedundanceRule<PurchaseinFIVO>(
          PurchaseinFIItemVO.VORDERTRANTYPECODE, POBillType.Order)
          .process(voaStock);

      // 入库单记成本单价
      new CalCostPriceRule().process(voaStock);

      // 进口入库签字保存时vo交换后处理规则
      new ChangeDataForITAfterRule(vosFiltered).process(voaStock);

      List<PurchaseinFIHeaderVO> headList =
          new ArrayList<PurchaseinFIHeaderVO>();
      List<PurchaseinFIItemVO> itemList = new ArrayList<PurchaseinFIItemVO>();
      for (PurchaseinFIVO vo : voaStock) {
        headList.add(vo.getParentVO());
        for (PurchaseinFIItemVO itemVO : vo.getChildrenVO()) {
          itemList.add(itemVO);
        }
      }
      PurchaseinFIHeaderVO[] headvos =
          headList.toArray(new PurchaseinFIHeaderVO[headList.size()]);
      PurchaseinFIItemVO[] itemvos =
          itemList.toArray(new PurchaseinFIItemVO[itemList.size()]);
      BaseDAO dao = new BaseDAO();
      dao.insertVOArrayWithPK(headvos);
      dao.insertVOArrayWithPK(itemvos);
      // for (int i = 0; i < voaStock.length; i++) {
      // dao.insertVOWithPK(voaStock[i].getParentVO());
      // dao.insertVOArrayWithPK(voaStock[i].getChildrenVO());
      // }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 方法功能描述：过滤掉VMI、赠品入库单行，采购结算和暂估不处理这些行
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos 入库单VO数组
   * @return 过滤后的入库单VO数组
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-8-27 下午04:58:20
   */
  private PurchaseInVO[] filterRow(PurchaseInVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return vos;
    }
    List<PurchaseInVO> voList = new ArrayList<PurchaseInVO>();
    for (PurchaseInVO vo : vos) {
      PurchaseInVO filterVO = new PurchaseInVO();
      filterVO.setParent(vo.getHead());
      List<PurchaseInBodyVO> bodyList = new ArrayList<PurchaseInBodyVO>();
      PurchaseInBodyVO[] bodys = vo.getBodys();
      if (ArrayUtils.isEmpty(bodys)) {
        continue;
      }
      for (PurchaseInBodyVO body : bodys) {
        if (StringUtil.isEmptyWithTrim(body.getCvmivenderid())
            && UFBoolean.FALSE.equals(body.getFlargess())) { // 非赠品
          bodyList.add(body);
        }
      }
      if (0 == bodyList.size()) {
        continue;
      }
      filterVO.setChildrenVO(bodyList.toArray(new PurchaseInBodyVO[bodyList
          .size()]));
      voList.add(filterVO);
    }
    return voList.toArray(new PurchaseInVO[voList.size()]);
  }

  private PurchaseinFIVO[] runChangeData(PurchaseInVO[] vosFiltered) {
    PurchaseinFIVO[] voaStock = new PurchaseinFIVO[vosFiltered.length];
    try {
      // 此处各单据的交易类型可能不同，因此不能使用统一的交易类型进行交换。
      for (int i = 0; i < vosFiltered.length; i++) {
        voaStock[i] =
            (PurchaseinFIVO) PfUtilTools.runChangeData(
                ICBillType.PurchaseIn.getCode(),
                POBillType.PurchaseInFinance.getCode(), vosFiltered[i]);
        voaStock[i].getParent().setAttributeValue("dr", Integer.valueOf(0));
        for (int j = 0; j < voaStock[i].getChildrenVO().length; j++) {
          // 设置外键
          voaStock[i].getChildrenVO()[j].setPk_stockps(voaStock[i]
              .getParentVO().getPk_stockps());
          voaStock[i].getChildrenVO()[j].setAttributeValue("dr",
              Integer.valueOf(0));
        }
      }
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return voaStock;
  }
}
