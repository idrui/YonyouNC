package nc.bs.pu.m21.alert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.bs.pu.alert.PUAlertConst;
import nc.bs.pu.m21.state.OrderCloseStateUtil;
import nc.bs.pu.m21.state.OrderStateMachine;
import nc.bs.pu.m21.state.bill.OrderFinalCloseState;
import nc.bs.pub.pa.IPreAlertPlugin;
import nc.bs.pub.pa.PreAlertContext;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.pa.PreAlertReturnType;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.page.db.IDDBPage;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.enumeration.EnumBillStatus;
import nc.vo.pu.pub.alert.PuAlertDataSource;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUParaValue;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.pub.util.ObjectUtil;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * 订单定时关闭预警类
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>订单定时关闭预警
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-10-8 下午02:57:22
 */
public class PoCloseBillAlert implements IPreAlertPlugin {
  static class PoCloseBillAlertDataSource extends PuAlertDataSource {
    private static final long serialVersionUID = -6812195347133261469L;

    public PoCloseBillAlertDataSource(String[][] values) {
      super(values);
    }

    @Override
    public String[] getAllDataItemExpress() {
      return new String[] {
        OrderHeaderVO.VBILLCODE
      };
    }
  }

  @Override
  public PreAlertObject executeTask(PreAlertContext context)
      throws BusinessException {
    try {
      return this.getPreAlertObject(context);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  private boolean canClose(OrderVO closeVO) {
    for (OrderItemVO itemVO : closeVO.getBVO()) {
      if (!itemVO.getBarriveclose().booleanValue()) {
        return false;
      }
      if (!itemVO.getBstockclose().booleanValue()) {
        return false;
      }
      if (!itemVO.getBinvoiceclose().booleanValue()) {
        return false;
      }
      if (!itemVO.getBpayclose().booleanValue()) {
        return false;
      }
    }
    return true;
  }

  private String[][] getCloseCodes(String[] pk_orgs, UFDate date) {
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_55.name());
    String orgCond = builder.buildSQL("", pk_orgs);

    String alterDateEnd = date.toString();

    SqlBuilder sql = new SqlBuilder();
    sql.append("select  h." + OrderHeaderVO.PK_ORDER);
    sql.append(" from " + PUEntity.M21_H_TABLE + " h ");
    sql.append(" where ");
    sql.append("h." + OrderHeaderVO.PK_ORG + orgCond);
    sql.append(" and h." + OrderHeaderVO.DBILLDATE, "<=", alterDateEnd);
    sql.append(" and h." + OrderHeaderVO.DR, 0);
    sql.append(" and h." + OrderHeaderVO.BISLATEST, UFBoolean.TRUE);
    sql.append(" and h." + OrderHeaderVO.FORDERSTATUS, new int[] {
      POEnumBillStatus.APPROVE.toInt(), EnumBillStatus.EXPORT.toInt()
    });
    sql.append(" and h." + OrderHeaderVO.BFINALCLOSE, UFBoolean.FALSE);
    sql.append(" and exists (");
    sql.append("select 1 from po_order_b b ");
    sql.append(" where h.pk_order = b.pk_order ");
    sql.append(" and ");
    sql.append(" b." + OrderItemVO.PK_ORG + orgCond);
    sql.append(" and b." + OrderItemVO.DBILLDATE, "<=", alterDateEnd);
    sql.append(" and b." + OrderItemVO.DR, 0);
    sql.append(" and b." + OrderItemVO.BARRIVECLOSE, UFBoolean.TRUE);
    sql.append(" and b." + OrderItemVO.BSTOCKCLOSE, UFBoolean.TRUE);
    sql.append(" and b." + OrderItemVO.BINVOICECLOSE, UFBoolean.TRUE);
    sql.append(" and b." + OrderItemVO.BPAYCLOSE, UFBoolean.TRUE);
    sql.append(")");

    // 定时需要设置为true
    OrderCloseStateUtil.getInstance().setbInstanceClose(true);
    OrderFinalCloseState state = new OrderFinalCloseState(UFBoolean.TRUE);
    Set<String> set = new HashSet<String>();
    LockOperator lock = new LockOperator();
    IDDBPage page = new IDDBPage(sql.toString(), 1000);
    while (page.hasNext()) {
      String[] ids = page.next();
      lock.lock(ids, "PoCloseBillAlert lock failed");/* -=notranslate=- */
      // ViewQuery<OrderCloseVO> query =
      // new ViewQuery<OrderCloseVO>(OrderCloseVO.class);
      // OrderCloseVO[] vos = query.query(bids);
      // OrderVO[] closeVOs = OrderCloseVO.getOrderVO(vos);
      BillQuery<OrderVO> billquery = new BillQuery<OrderVO>(OrderVO.class);
      OrderVO[] closeVOs = billquery.query(ids);
      closeVOs = this.getCloseVO(closeVOs);
      if (closeVOs == null || closeVOs.length == 0) {
        continue;
      }
      // 不需要处理可用量了，因为入库关闭、行关闭时已经处理
      new OrderStateMachine().setState(state, closeVOs);
      for (OrderVO vo : closeVOs) {
        if (UFBoolean.TRUE.equals(vo.getHVO().getBfinalclose())) {
          set.add(vo.getHVO().getVbillcode());
        }
      }
    }

    if (set.size() == 0) {
      return null;
    }
    String[][] codes = new String[set.size()][1];
    String[] tempCodes = set.toArray(new String[set.size()]);
    for (int i = 0; i < tempCodes.length; ++i) {
      codes[i][0] = tempCodes[i];
    }
    return codes;
  }

  private OrderVO[] getCloseVO(OrderVO[] closeVOs) {
    List<OrderVO> list = new ArrayList<OrderVO>();
    for (OrderVO closeVO : closeVOs) {
      if (this.canClose(closeVO)) {
        list.add(closeVO);
      }
    }
    if (list.size() > 0) {
      return list.toArray(new OrderVO[list.size()]);
    }
    return null;
  }

  private String[][] getDatas(PreAlertContext context) {
    Object odays = context.getKeyMap().get(PUAlertConst.DAYS);
    String[] pk_orgs = context.getPk_orgs();

    if (ObjectUtil.isEmptyWithTrim(odays) || pk_orgs == null
        || pk_orgs.length == 0) {
      return null;
    }

    List<String> timingList = new ArrayList<String>();
    for (String pk_org : pk_orgs) {
      if (PUParaValue.po09.timing == PUSysParamUtil.getPO09(pk_org)) {
        timingList.add(pk_org);
      }
    }

    if (timingList.size() == 0) {
      return null;
    }

    UFDate loginDate = AppContext.getInstance().getBusiDate();
    Integer day = Integer.valueOf(odays.toString());
    UFDate date = loginDate.getDateBefore(day.intValue());
    return this.getCloseCodes(
        timingList.toArray(new String[timingList.size()]), date);
  }

  private PoCloseBillAlertDataSource getDataSource(String[][] values) {
    PoCloseBillAlertDataSource datasource =
        new PoCloseBillAlertDataSource(values);
    return datasource;
  }

  private PreAlertObject getPreAlertObject(PreAlertContext context) {
    PreAlertObject retObj = new PreAlertObject();
    String[][] values = this.getDatas(context);
    if (null == values || values.length == 0) {
      retObj.setReturnType(PreAlertReturnType.RETURNNOTHING);
    }
    else {
      retObj.setReturnType(PreAlertReturnType.RETURNDATASOURCE);
      PoCloseBillAlertDataSource datasource = this.getDataSource(values);
      retObj.setReturnObj(datasource);
    }
    return retObj;
  }
}
