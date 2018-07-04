/**
 * $�ļ�˵��$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-17 ����03:43:32
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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��;״̬���������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-8-17 ����03:43:32
 */
public abstract class AbstractOrderOnwayService implements IAppModelService,
    IQueryService {

  private AbstractUIAppModel model = null;

  private Class<? extends IOnwayQuery> onwayQuery;

  private Class<? extends IStatusMaintain> statusMaintain;

  /**
   * ���෽����д
   * 
   * @see nc.ui.uif2.model.IAppModelService#delete(java.lang.Object)
   */
  @Override
  public void delete(Object object) throws Exception {
    // ����״̬ά��
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
   * ��������������ȡ�ñ��β�ѯʱ�Ѵ�����δ����
   * <p>
   * <b>����˵��</b>
   * 
   * @param sqlWhere
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-8-12 ����01:26:59
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
   * ��������������ȡ����;״̬
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-8-17 ����03:47:19
   */
  public abstract int getStatus();

  public Class<? extends IStatusMaintain> getStatusMaintain() {
    return this.statusMaintain;
  }

  /**
   * ���෽����д
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

          // ȷ������
          // UFDouble confirmNum = bvo.getNconfirmnum();
          // �ۼ�����
          // UFDouble naccunum = bvo.getNaccunum();
          // ���η�������
          UFDouble nsendoutnum = bvo.getNsendoutnum();

          // ������
          UFDouble nnum = bvo.getNnum();

          // ���ñ��η������
          if (status == OnwayStatus.STATUS_SENDOUT.toInt()) {
            // bvo.setNsendoutnum(nsendoutnum);
            // ���ñ��η������
            this.setStatusMny(bvo, nnum, nsendoutnum,
                OrderOnwayItemVO.NSTATUSMNY);
          }
          else {
            bvo.setNsendoutnum(null);
            bvo.setNstatusmny(null);
          }

          // �����ѷ������
          // �ѷ�������
          UFDouble nonwaynum = MathTool.nvl(bvo.getNonwaynum());
          // ���ñ��η������
          this.setStatusMny(bvo, nnum, nonwaynum, OrderOnwayItemVO.NONWAYMNY);

          // ���ô�������
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
   * ���෽����д
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
  // // ȷ������
  // // UFDouble confirmNum = bvo.getNconfirmnum();
  // // �ۼ�����
  // // UFDouble naccunum = bvo.getNaccunum();
  // // ���η�������
  // UFDouble nsendoutnum = bvo.getNsendoutnum();
  //
  // // ������
  // UFDouble nnum = bvo.getNnum();
  //
  // // ���ñ��η������
  // if (status == OnwayStatus.STATUS_SENDOUT.toInt()) {
  // // bvo.setNsendoutnum(nsendoutnum);
  // // ���ñ��η������
  // this.setStatusMny(bvo, nnum, nsendoutnum,
  // OrderOnwayItemVO.NSTATUSMNY);
  // }
  // else {
  // bvo.setNsendoutnum(null);
  // bvo.setNstatusmny(null);
  // }
  //
  // // �����ѷ������
  // // �ѷ�������
  // UFDouble nonwaynum = MathTool.nvl(bvo.getNonwaynum());
  // // ���ñ��η������
  // this.setStatusMny(bvo, nnum, nonwaynum, OrderOnwayItemVO.NONWAYMNY);
  //
  // // ���ô�������
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
   * ���෽����д
   * 
   * @see nc.ui.uif2.model.IAppModelService#update(java.lang.Object)
   */
  @Override
  public Object update(Object object) throws Exception {

    // ����״̬ά��
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
   * �����������������ñ��η������
   * <p>
   * <b>����˵��</b>
   * 
   * @param nnum
   *          ������
   * @param num
   *          Ҫ�Ƚϵ�����
   * @param item
   *          Ҫ���õ���
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-8-19 ����04:31:42
   */
  private void setStatusMny(OrderOnwayItemVO bvo, UFDouble nnum, UFDouble num,
      String item) {
    UFDouble nstatusmny = UFDouble.ZERO_DBL;
    // ������η�������=������
    if (num.equals(nnum)) {
      nstatusmny = bvo.getNorigmny();

    }
    // ������η�������������������,���η������=Ҫ�Ƚ�����*����˰����
    else {
      // ȡ������˰����
      UFDouble norignetprice = bvo.getNorignetprice();
      nstatusmny = num.multiply(norignetprice);

    }
    bvo.setAttributeValue(item, nstatusmny);
  }
}
