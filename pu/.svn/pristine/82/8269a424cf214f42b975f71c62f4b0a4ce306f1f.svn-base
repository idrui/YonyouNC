package nc.vo.pu.pub.rule;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.pub.constant.PUQueryConst;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pu.pub.util.ApprovingUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.scmpub.res.billtype.IBillType;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;

/**
 * 懒加载的公共查询，请后台使用<br>
 * 查询前先将查询方案组织好，将附加条件append进scheme<br>
 * 方法中还会“待审批条件”进行过滤，外围方法可不再处理<br>
 * 
 * @since 6.0
 * @version 2011-6-11 上午09:37:01
 * @author zhaoyha
 */
public class PUBillLazyQuery<E extends AbstractBill> {

  /**
   * 单据类型Class
   */
  private Class<E> billClass;

  /**
   * 单据类型
   */
  private IBillType bt;

  /**
   * @param billClass
   * @param bt
   */
  public PUBillLazyQuery(Class<E> billClass, IBillType bt) {
    super();
    this.billClass = billClass;
    this.bt = bt;
  }

  /**
   * 查询前先将查询方案组织好，将附加条件append进scheme<br>
   * 方法中还会“待审批条件”进行过滤，外围方法可不再处理<br>
   * 
   * @param qs 查询方案
   * @return
   */
  public AbstractBill[] query(IQueryScheme qs) {
    return this.realQuery(qs, null);
  }

  /**
   * 查询前先将查询方案组织好，将附加条件append进scheme<br>
   * 方法中还会“待审批条件”进行过滤，外围方法可不再处理<br>
   * 
   * @param qs 查询方案
   * @param orderBy 以" order by"开头的排序字段
   * @return
   */
  public AbstractBill[] query(IQueryScheme qs, String orderBy) {
    return this.realQuery(qs, orderBy);
  }

  /**
   * 查询前先将查询方案组织好，将附加条件append进scheme<br>
   * 方法中还会“待审批条件”进行过滤，外围方法可不再处理<br>
   * 以单据号排序
   * 
   * @param qs 查询方案
   * @return
   */
  public AbstractBill[] queryOrderByBillcode(IQueryScheme qs) {
    QuerySchemeProcessor qrySchemeProcessor = new QuerySchemeProcessor(qs);
    String mainTableAlias = qrySchemeProcessor.getMainTableAlias();
    SqlBuilder orderBy = new SqlBuilder();
    orderBy
        .append(" order by " + mainTableAlias + "." + PUQueryConst.VBILLCODE);
    return this.realQuery(qs, orderBy.toString());
  }

  public AbstractBill[] queryOrderByBillcode(IQueryScheme qs,
      String approvingCondFieldCode) {
    QuerySchemeProcessor qrySchemeProcessor = new QuerySchemeProcessor(qs);
    String mainTableAlias = qrySchemeProcessor.getMainTableAlias();
    SqlBuilder orderBy = new SqlBuilder();
    orderBy
        .append(" order by " + mainTableAlias + "." + PUQueryConst.VBILLCODE);
    return this.realQuery(qs, orderBy.toString(), approvingCondFieldCode);
  }

  /**
   * 查询前先将查询方案组织好，将附加条件append进scheme<br>
   * 方法中还会“待审批条件”进行过滤，外围方法可不再处理<br>
   * 以单据日期排序
   * 
   * @param qs 查询方案
   * @return
   */
  public AbstractBill[] queryOrderByBilldate(IQueryScheme qs) {
    QuerySchemeProcessor qrySchemeProcessor = new QuerySchemeProcessor(qs);
    String mainTableAlias = qrySchemeProcessor.getMainTableAlias();
    SqlBuilder orderBy = new SqlBuilder();
    orderBy
        .append(" order by " + mainTableAlias + "." + PUQueryConst.DBILLDATE);
    return this.realQuery(qs, orderBy.toString());
  }

  /**
   * 懒加载时刷新表体
   * 
   * @param oldBills
   * @return 刷新后的新单据
   */
  public AbstractBill[] refreshItem(AbstractBill[] oldBills) {
    String[] pks = new String[oldBills.length];
    for (int i = 0; i < pks.length; i++) {
      pks[i] = oldBills[i].getPrimaryKey();
    }
    // 查询VO
    BillQuery<E> query = new BillQuery<E>(this.billClass);
    E[] bills = query.query(pks);
    if (ArrayUtils.isEmpty(bills) || oldBills.length != bills.length
        || this.hasChange(oldBills, bills)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004000_0", "04004000-0088")/*
                                                                   * @res
                                                                   * "出发并发，请重新查询数据！"
                                                                   */);
    }
    return bills;
  }

  private boolean hasChange(AbstractBill[] oldBills, AbstractBill[] bills) {
    Map<String, AbstractBill> oldMap = AggVOUtil.createVOMap(oldBills);
    for (AbstractBill bill : bills) {
      AbstractBill oldBill = oldMap.get(bill.getPrimaryKey());
      if (!bill.getParent().getAttributeValue("ts")
          .equals(oldBill.getParent().getAttributeValue("ts"))) {
        return true;
      }
    }
    return false;
  }

  private AbstractBill[] realQuery(IQueryScheme qs, String orderBy) {
    AbstractBill[] vos =
        new BillLazyQuery<E>(this.billClass).query(qs, orderBy);
    vos = ApprovingUtil.filterForApprove(qs, vos, this.bt.getCode());
    if (ArrayUtils.isEmpty(vos)) {
      return vos;
    }
    List<E> refreshVos = (List<E>) Arrays.asList(vos);
    // 有可能第1条懒加载的数据已经被滤掉，因此要重新刷新第1条数据表体
    this.refreshFirstVO(refreshVos);
    return new ListToArrayTool<E>(this.billClass).convertToArray(refreshVos);
  }

  private AbstractBill[] realQuery(IQueryScheme qs, String orderBy,
      String approvingCondFieldCode) {
    AbstractBill[] vos =
        new BillLazyQuery<E>(this.billClass).query(qs, orderBy);
    vos =
        ApprovingUtil.filterForApprove(qs, vos, this.bt.getCode(),
            approvingCondFieldCode);
    if (ArrayUtils.isEmpty(vos)) {
      return vos;
    }
    List<E> refreshVos = (List<E>) Arrays.asList(vos);
    // 有可能第1条懒加载的数据已经被滤掉，因此要重新刷新第1条数据表体
    this.refreshFirstVO(refreshVos);
    return new ListToArrayTool<E>(this.billClass).convertToArray(refreshVos);
  }

  private void refreshFirstVO(List<E> vos) {
    if (CollectionUtils.isEmpty(vos)
        || !ArrayUtils.isEmpty(vos.get(0).getChildrenVO())
        && null != vos.get(0).getChildrenVO()[0]) {
      return;
    }
    String[] refreshPKs = new String[] {
      vos.get(0).getPrimaryKey()
    };
    BillQuery<E> query = new BillQuery<E>(this.billClass);
    E[] refreshBills = query.query(refreshPKs);
    // 如果此时发生并发，原来的第一条单据被删除，则递归处理
    if (ArrayUtils.isEmpty(refreshBills)) {
      vos.remove(0);
      this.refreshFirstVO(vos);
    }
    vos.set(0, refreshBills[0]);
  }

}
