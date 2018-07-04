/**
 * $文件说明$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-17 下午03:43:32
 */
package nc.ui.pu.m21.service.onway;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m21.IOnwayQuery;
import nc.itf.pu.m21.IStatusMaintain;
import nc.ui.pubapp.ClientContext;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.uif2.model.AbstractUIAppModel;
import nc.ui.uif2.model.IAppModelService;
import nc.vo.pu.m21.entity.OrderOnwayItemVO;
import nc.vo.pu.m21.entity.OrderOnwayVO;
import nc.vo.pu.m21transtype.enumeration.OnwayStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.uif2.LoginContext;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>在途状态服务抽象类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-8-17 下午03:43:32
 */
public abstract class AbstractOrderOnwayService implements IAppModelService,
    IQueryService {

  private AbstractUIAppModel model = null;

  private Class<? extends IOnwayQuery> onwayQuery;

  private Class<? extends IStatusMaintain> statusMaintain;

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.model.IAppModelService#delete(java.lang.Object)
   */
  @Override
  public void delete(Object object) throws Exception {
    // 发货状态维护
    if (object == null) {
      return;
    }
    OrderOnwayVO[] newVOs = null;
    if (object instanceof OrderOnwayVO) {
      newVOs = new OrderOnwayVO[] {
        (OrderOnwayVO) object
      };
    }
    else if (object instanceof OrderOnwayVO[]) {
      newVOs = (OrderOnwayVO[]) object;
    }
    if (null != this.statusMaintain) {
      NCLocator.getInstance().lookup(this.statusMaintain)
          .deleteOnway(newVOs, this.getStatus());
    }
  }

  /**
   * 方法功能描述：取得本次查询时已处理还是未处理
   * <p>
   * <b>参数说明</b>
   * 
   * @param sqlWhere
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-8-12 下午01:26:59
   */
  // public abstract boolean getIsDone(String sqlWhere);

  public AbstractUIAppModel getModel() {
    return this.model;
  }

  public Class<? extends IOnwayQuery> getOnwayQuery() {
    return this.onwayQuery;
  }

  public abstract String getOnwayStatusStr();

  /**
   * 方法功能描述：取得在途状态
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-8-17 下午03:47:19
   */
  public abstract int getStatus();

  public Class<? extends IStatusMaintain> getStatusMaintain() {
    return this.statusMaintain;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.model.IAppModelService#insert(java.lang.Object)
   */
  @Override
  public Object insert(Object object) throws Exception {
    return null;
  }

  @Override
  public Object[] queryByDataVisibilitySetting(LoginContext context)
      throws Exception {
    return null;
  }

  @Override
  public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {
    if (null != this.onwayQuery) {
      boolean isDone = this.getIsDone(queryScheme);
      int status = this.getStatus();
      OrderOnwayVO[] onwayVO =
          NCLocator
              .getInstance()
              .lookup(this.onwayQuery)
              .onwayQuery(queryScheme, this.getOnwayStatusStr(), status, isDone);
      if (null == onwayVO || onwayVO.length == 0) {
        return null;
      }

      for (OrderOnwayVO vo : onwayVO) {

        OrderOnwayItemVO[] bvos = vo.getBVO();

        for (OrderOnwayItemVO bvo : bvos) {

          // 确认数量
          // UFDouble confirmNum = bvo.getNconfirmnum();
          // 累计数量
          // UFDouble naccunum = bvo.getNaccunum();
          // 本次发货数量
          UFDouble nsendoutnum = bvo.getNsendoutnum();

          // 主数量
          UFDouble nnum = bvo.getNnum();

          // 设置本次发货金额
          if (status == OnwayStatus.STATUS_SENDOUT.toInt()) {
            // bvo.setNsendoutnum(nsendoutnum);
            // 设置本次发货金额
            this.setStatusMny(bvo, nnum, nsendoutnum,
                OrderOnwayItemVO.NSTATUSMNY);
          }
          else {
            bvo.setNsendoutnum(null);
            bvo.setNstatusmny(null);
          }

          // 设置已发货金额
          // 已发货数量
          UFDouble nonwaynum = MathTool.nvl(bvo.getNonwaynum());
          // 设置本次发货金额
          this.setStatusMny(bvo, nnum, nonwaynum, OrderOnwayItemVO.NONWAYMNY);

          // 设置处理日期
          if (!isDone) {
            UFDate billDate = ClientContext.getInstance().getBusiDate();
            bvo.setDbilldate(billDate);
          }
        }
      }

      return onwayVO;
    }
    return null;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pubapp.uif2app.model.IQueryService#queryByWhereSql(java.lang.String)
   */
  // @Override
  // public Object[] queryByWhereSql(String whereSql) throws Exception {
  // if (null != this.onwayQuery) {
  // boolean isDone = this.getIsDone(whereSql);
  // int status = this.getStatus();
  // OrderOnwayVO[] onwayVO =
  // NCLocator.getInstance().lookup(this.onwayQuery)
  // .onwayQuery(whereSql, status, isDone);
  //
  // for (OrderOnwayVO vo : onwayVO) {
  //
  // OrderOnwayItemVO[] bvos = vo.getBVO();
  //
  // for (OrderOnwayItemVO bvo : bvos) {
  //
  // // 确认数量
  // // UFDouble confirmNum = bvo.getNconfirmnum();
  // // 累计数量
  // // UFDouble naccunum = bvo.getNaccunum();
  // // 本次发货数量
  // UFDouble nsendoutnum = bvo.getNsendoutnum();
  //
  // // 主数量
  // UFDouble nnum = bvo.getNnum();
  //
  // // 设置本次发货金额
  // if (status == OnwayStatus.STATUS_SENDOUT.toInt()) {
  // // bvo.setNsendoutnum(nsendoutnum);
  // // 设置本次发货金额
  // this.setStatusMny(bvo, nnum, nsendoutnum,
  // OrderOnwayItemVO.NSTATUSMNY);
  // }
  // else {
  // bvo.setNsendoutnum(null);
  // bvo.setNstatusmny(null);
  // }
  //
  // // 设置已发货金额
  // // 已发货数量
  // UFDouble nonwaynum = MathTool.nvl(bvo.getNonwaynum());
  // // 设置本次发货金额
  // this.setStatusMny(bvo, nnum, nonwaynum, OrderOnwayItemVO.NONWAYMNY);
  //
  // // 设置处理日期
  // if (!isDone) {
  // UFDate billDate = ClientContext.getInstance().getBusiDate();
  // bvo.setDbilldate(billDate);
  // }
  // }
  // }
  //
  // return onwayVO;
  // }
  // return null;
  // }

  public void setModel(AbstractUIAppModel model) {
    this.model = model;
  }

  public void setOnwayQuery(Class<? extends IOnwayQuery> onwayQuery) {
    this.onwayQuery = onwayQuery;
  }

  public void setStatusMaintain(Class<? extends IStatusMaintain> statusMaintain) {
    this.statusMaintain = statusMaintain;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.model.IAppModelService#update(java.lang.Object)
   */
  @Override
  public Object update(Object object) throws Exception {

    // 发货状态维护
    if (object == null) {
      return null;
    }
    OrderOnwayVO[] newVOs = null;
    if (object instanceof OrderOnwayVO) {
      newVOs = new OrderOnwayVO[] {
        (OrderOnwayVO) object
      };
    }
    else if (object instanceof OrderOnwayVO[]) {
      newVOs = (OrderOnwayVO[]) object;
    }
    if (null != this.statusMaintain) {
      return NCLocator.getInstance().lookup(this.statusMaintain)
          .updateOnway(newVOs, this.getStatus());
    }
    return null;
  }

  private boolean getIsDone(IQueryScheme queryScheme) {
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    QueryCondition outputCond =
        qrySchemeProcessor.getQueryCondition(this.getOnwayStatusStr());
    Object[] values = outputCond.getValues();
    UFBoolean output = UFBoolean.valueOf((String) values[0]);
    return output.booleanValue();
  }

  /**
   * 方法功能描述：设置本次发货金额
   * <p>
   * <b>参数说明</b>
   * 
   * @param nnum
   *          主数量
   * @param num
   *          要比较的数量
   * @param item
   *          要设置的项
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-8-19 下午04:31:42
   */
  private void setStatusMny(OrderOnwayItemVO bvo, UFDouble nnum, UFDouble num,
      String item) {
    UFDouble nstatusmny = UFDouble.ZERO_DBL;
    // 如果本次发货数量=主数量
    if (num.equals(nnum)) {
      nstatusmny = bvo.getNorigmny();

    }
    // 如果本次发货数量不等于主数量,本次发货金额=要比较数量*主无税净价
    else {
      // 取得主无税净价
      UFDouble norignetprice = bvo.getNorignetprice();
      nstatusmny = num.multiply(norignetprice);

    }
    bvo.setAttributeValue(item, nstatusmny);
  }
}
