package nc.bs.pu.m422x.query;

import java.util.HashSet;
import java.util.Set;

import nc.bs.scmpub.util.SCMDataAccessUtils;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.tool.BillComposite;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

/**
 * 物资需求申请单转单查询抽象类
 * 
 * @since 6.5
 * @version 2015-6-17 上午11:10:25
 * @author luojw
 */
public abstract class Abstract422XRefQueryBP {

  protected String headtb;

  protected String itemtb;

  protected QuerySchemeProcessor psor;

  public Abstract422XRefQueryBP(IQueryScheme queryScheme) {
    this.psor = new QuerySchemeProcessor(queryScheme);
    this.headtb = this.psor.getMainTableAlias();
    this.itemtb =
        this.psor.getTableAliasOfAttribute(StoreReqAppItemVO.PK_STOREREQ_B + "." 
            + StoreReqAppItemVO.PK_STOREREQ_B);
  }

  public String getHeadtb() {
    return this.headtb;
  }

  public String getItemtb() {
    return this.itemtb;
  }

  public QuerySchemeProcessor getPsor() {
    return this.psor;
  }

  /**
   * 物资需求申请单提供的sql查询语句
   * 
   * @return
   */
  public abstract StringBuilder makeGetPKSql();

  /**
   * 方法功能描述：根据提供的查询条件查询物资需求申请单。
   * <p>
   * <b>参数说明</b>
   * 
   * @param sql
   * @return 满足查询条件的物资需求申请单（子表已经过滤）
   * @since 6.5
   * @author luojw
   * @time 2015-6-17 上午10:29:27
   */
  public StoreReqAppVO[] queryStoreReqVOs() {
  	/*
  	 * change by wandl
  	 * 转单界面限制查询数据量10000行，超过10000行会报错提示缩小查询范围！
  	 */
  	SCMDataAccessUtils utils = new SCMDataAccessUtils(10000);
    // 加集团
    this.psor.appendCurrentGroup();
    // 查询表头PK和子表PK
    StringBuilder sql = this.makeGetPKSql();

    String[][] pks = utils.query(sql.toString()).toTwoDimensionStringArray();
    if (null == pks || pks.length == 0) {
      return null;
    }
    Set<String> headPks = new HashSet<String>();
    String[] itemPks = new String[pks.length];

    for (int i = 0; i < pks.length; i++) {
      headPks.add(pks[i][0]);
      itemPks[i] = pks[i][1];
    }

    StoreReqAppHeaderVO[] headers =
        new VOQuery<StoreReqAppHeaderVO>(StoreReqAppHeaderVO.class).query(headPks
            .toArray(new String[headPks.size()]));

    StoreReqAppItemVO[] items =
        new VOQuery<StoreReqAppItemVO>(StoreReqAppItemVO.class).query(itemPks);

    BillComposite<StoreReqAppVO> bc =
        new BillComposite<StoreReqAppVO>(StoreReqAppVO.class);
    StoreReqAppVO tempVO = new StoreReqAppVO();
    bc.append(tempVO.getMetaData().getParent(), headers);
    bc.append(tempVO.getMetaData().getVOMeta(StoreReqAppItemVO.class), items);
    StoreReqAppVO[] queryResult = bc.composite();
    // 处理查询结果
    return this.processQueryResult(queryResult);
  }

  public void setHeadtb(String headtb) {
    this.headtb = headtb;
  }

  public void setItemtb(String itemtb) {
    this.itemtb = itemtb;
  }

  public void setPsor(QuerySchemeProcessor psor) {
    this.psor = psor;
  }

  /**
   * 处理返回结果,各个子类根据情况可以覆写
   * 
   * @param queryResult
   * @return
   */
  protected StoreReqAppVO[] processQueryResult(StoreReqAppVO[] queryResult) {
    return queryResult;
  }

}
