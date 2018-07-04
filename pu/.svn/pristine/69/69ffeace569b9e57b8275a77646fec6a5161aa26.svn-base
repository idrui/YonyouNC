package nc.bs.pu.m21.writeback;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m21transtype.IPoTransTypeQuery;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.m21.exception.AskNumException;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUParaValue.ctrltype;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              采购订单回写入库和到货容差控制
 * @scene
 *        采购入库单回写订单
 * @param
 *        boolean isArrive 是否到货
 *        boolean isUserConfirm 用户确认选项
 * @since 6.3
 * @version 2014-10-22 下午3:35:25
 * @author luojw
 */

public class OrderWBStoreArrvTolerRule implements IRule<OrderViewVO> {
  private boolean isArrive;

  private boolean isUserConfirm;

  public OrderWBStoreArrvTolerRule(boolean isArrive, boolean isUserConfirm) {
    this.isArrive = isArrive;
    this.isUserConfirm = isUserConfirm;
  }

  @Override
  public void process(OrderViewVO[] views) {
    ctrltype tolerPara = PUSysParamUtil.getPO40(views[0].getPk_org());
    if (ctrltype.not_control.equals(tolerPara)) {
      return;
    }
    OrderViewVO[] checkViews = views;
    if (!this.isArrive) {
      checkViews = this.getCheckView(views);
    }
    if (ArrayUtils.isEmpty(checkViews)) {
      return;
    }

    List<String> brefList = new ArrayList<String>();
    List<String> norefPosList = new ArrayList<String>();
    for (OrderViewVO view : checkViews) {
      if (UFBoolean.TRUE.equals(view.getBrefwhenreturn())) {
        brefList.add(view.getPk_order_b());
      }
      else if (MathTool.compareTo(view.getNnum(), UFDouble.ZERO_DBL) > 0) {
        norefPosList.add(view.getPk_order_b());
      }
    }

    if (brefList.size() > 0) {
      String[] bids = brefList.toArray(new String[brefList.size()]);
      this.checkRef(bids, tolerPara);
    }

    if (norefPosList.size() > 0) {
      String[] bids = norefPosList.toArray(new String[norefPosList.size()]);
      this.checkNoRef(bids, tolerPara);
    }
  }

  private void checkNoRef(String[] bids, ctrltype tolerPara) {
    this.toleranceCompareWhenNoRef(bids, tolerPara);
  }

  private void checkRef(String[] bids, ctrltype tolerPara) {
    this.toleranceCompareWhenRef(bids, tolerPara);
  }

  private OrderViewVO[] getCheckView(OrderViewVO[] views) {
    Set<String> set = new HashSet<String>();
    for (OrderViewVO view : views) {
      set.add(view.getCtrantypeid());
    }

    String[] cids = set.toArray(new String[set.size()]);
    IPoTransTypeQuery service =
        NCLocator.getInstance().lookup(IPoTransTypeQuery.class);
    Map<String, PoTransTypeVO> map = null;
    try {
      map = service.queryAttrByIDs(cids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    if (null == map) {
      return null;
    }

    List<OrderViewVO> list = new ArrayList<OrderViewVO>();
    for (OrderViewVO view : views) {
      String ctrantypeid = view.getCtrantypeid();
      if (UFBoolean.TRUE.equals(map.get(ctrantypeid).getBarrive())) {
        list.add(view);
      }
    }
    if (list.size() > 0) {
      return list.toArray(new OrderViewVO[list.size()]);
    }
    return null;
  }

  private String[] getCodeOverTolerWhenNoRef(String[] srcBids) {
    // 物料基本信息表
    String mtable = MaterialVO.getDefaultTableName();

    DataAccessUtils utils = new DataAccessUtils();
    IDExQueryBuilder idQueryBuilder =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_09.name());
    String bidsql =
        idQueryBuilder.buildSQL("t." + OrderItemVO.PK_ORDER_B, srcBids);
    String sql =
        "select m.code from " + PUEntity.M21_B_TABLE + " t inner join "
            + mtable + " m on t.pk_material=m.pk_material where (isnull(t."
            + OrderItemVO.NACCUMARRVNUM + ",0)-isnull(t."
            + OrderItemVO.NBACKARRVNUM + ",0))* cast((1+m."
            + MaterialVO.INTOLERANCE
            + "/100.0) as decimal (16, 8)) < isnull(t."
            + OrderItemVO.NACCUMSTORENUM + ",0) and " + bidsql;
    // 存在订单累计入库数量超出订单累计到货数量容差控制的行，请检查
    return utils.query(sql).toOneDimensionStringArray();
  }

  private String[] getCodeOverTolerWhenRef(String[] srcBids) {
    // 物料基本信息表
    String mtable = MaterialVO.getDefaultTableName();

    DataAccessUtils utils = new DataAccessUtils();
    IDExQueryBuilder idQueryBuilder =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_10.name());
    String bidsql =
        idQueryBuilder.buildSQL("t." + OrderItemVO.PK_ORDER_B, srcBids);

    String sql =
        "select m.code from " + PUEntity.M21_B_TABLE + " t inner join "
            + mtable + " m on t.pk_material=m.pk_material where (isnull(t."
            + OrderItemVO.NACCUMARRVNUM + ",0))* cast((1+m."
            + MaterialVO.INTOLERANCE + "/100.0) as decimal (16, 8))< isnull(t."
            + OrderItemVO.NACCUMSTORENUM + ",0) and " + bidsql;
    // 存在订单累计入库数量超出订单累计到货数量容差控制的行，请检查
    return utils.query(sql).toOneDimensionStringArray();
  }

  private void toleranceCompareWhenNoRef(String[] srcBids, ctrltype tolerPara) {

    String[] results = this.getCodeOverTolerWhenNoRef(srcBids);

    if (results != null && results.length > 0) {
      // 严格控制
      if (tolerPara.equals(ctrltype.not_save)) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4004030_0", "04004030-0265", null, new String[] {
              results[0]
            })/* 物料{0}累计入库数量超出订单累计到货数量容差控制，请检查！ */);
      }
      // 提示
      else if (tolerPara.equals(ctrltype.ask) && !this.isUserConfirm) {
        ExceptionUtils.wrappException(new AskNumException(NCLangResOnserver
            .getInstance().getStrByID("4004030_0", "04004030-0266", null,
                new String[] {
                  results[0]
                })/* 物料{0}累计入库数量超出订单累计到货数量容差控制，是否继续！ */));
      }
    }
  }

  private void toleranceCompareWhenRef(String[] srcBids, ctrltype tolerPara) {

    String[] results = this.getCodeOverTolerWhenRef(srcBids);

    if (results != null && results.length > 0) {
      // 严格控制
      if (tolerPara.equals(ctrltype.not_save)) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4004030_0", "04004030-0265", null, new String[] {
              results[0]
            })/* 物料{0}累计入库数量超出订单累计到货数量容差控制，请检查！ */);
      }
      // 提示
      else if (tolerPara.equals(ctrltype.ask) && !this.isUserConfirm) {
        ExceptionUtils.wrappException(new AskNumException(NCLangResOnserver
            .getInstance().getStrByID("4004030_0", "04004030-0266", null,
                new String[] {
                  results[0]
                })/* 物料{0}累计入库数量超出订单累计到货数量容差控制，是否继续！ */));
      }
    }
  }

}
