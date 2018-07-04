package nc.bs.pu.m20.query;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.bs.scmpub.util.SCMDataAccessUtils;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.pubitf.pu.m21.pu.m20.IOrderQueryFor20;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m21.entity.m20.QueryParaFor20;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.tool.BillComposite;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;

/**
 * ת����ѯ������
 * 
 * @since 6.0
 * @version 2011-4-15 ����02:17:18
 * @author liuchx
 */
public abstract class AbstractRefQueryBP {

  protected String headtb;

  protected String itemtb;

  protected QuerySchemeProcessor psor;

  public AbstractRefQueryBP(QuerySchemeProcessor psor) {
    this.psor = psor;
    this.headtb = psor.getMainTableAlias();
    this.itemtb =
        psor.getTableAliasOfAttribute(PraybillItemVO.PK_PRAYBILL_B + '.'
            + PraybillItemVO.PK_PRAYBILL_B);
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
   * �빺���ṩ��sql��ѯ���
   * 
   * @return
   */
  public abstract StringBuffer makeGetPKSql();

  /**
   * �����������������ݶ����ṩ�Ĳ�ѯ������ѯ�빺����
   * <p>
   * <b>����˵��</b>
   * 
   * @param sql
   * @return �����ѯ�������빺�����ӱ��Ѿ����ˣ�
   * @since 6.0
   * @author liuchx
   * @time 2010-2-1 ����11:29:27
   */
  public PraybillVO[] queryPrayBills() {
    //DataAccessUtils utils = new DataAccessUtils();
  	/*
  	 * change by wandl
  	 * ת���������Ʋ�ѯ������10000�У�����10000�лᱨ����ʾ��С��ѯ��Χ��
  	 */
  	SCMDataAccessUtils utils = new SCMDataAccessUtils(10000);
    // �Ӽ���
    this.psor.appendCurrentGroup();
    // ��ѯ��ͷPK���ӱ�PK
    StringBuffer sql = this.makeGetPKSql();

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

    PraybillHeaderVO[] headers =
        new VOQuery<PraybillHeaderVO>(PraybillHeaderVO.class).query(headPks
            .toArray(new String[headPks.size()]));

    PraybillItemVO[] items =
        new VOQuery<PraybillItemVO>(PraybillItemVO.class).query(itemPks);

    BillComposite<PraybillVO> bc =
        new BillComposite<PraybillVO>(PraybillVO.class);
    PraybillVO tempVO = new PraybillVO();
    bc.append(tempVO.getMetaData().getParent(), headers);
    bc.append(tempVO.getMetaData().getVOMeta(PraybillItemVO.class), items);
    PraybillVO[] queryResult = bc.composite();
    // �����ѯ���
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
   * @param queryResult
   */
  private QueryParaFor20[] createPara(PraybillVO[] queryResult) {
    List<QueryParaFor20> paraList = new ArrayList<QueryParaFor20>();
    for (PraybillVO vo : queryResult) {
      for (PraybillItemVO item : vo.getBVO()) {

        QueryParaFor20 para = new QueryParaFor20();
        para.setCurrency(vo.getHVO().getCcurrencyid());
        para.setDate(AppContext.getInstance().getBusiDate());
        para.setBsc(vo.getHVO().getBsctype());
        para.setPk_praybill_b(item.getPk_praybill_b());
        para.setPk_srcmaterial(item.getPk_srcmaterial());
        para.setPk_org(item.getPk_purchaseorg());
        // para.setPk_ordertrantype(item.getCordertrantypecode());
        paraList.add(para);
      }
    }
    if (paraList.size() == 0) {
      return null;
    }
    return paraList.toArray(new QueryParaFor20[paraList.size()]);
  }

  /**
   * ���˹�Ӧ����Ч�۸�
   * 
   * @param queryResult
   * @return
   */
  protected PraybillVO[] filterEffectivePrice(PraybillVO[] queryResult) {
    QueryParaFor20[] paras = this.createPara(queryResult);
    if (ArrayUtils.isEmpty(paras)) {
      return queryResult;
    }
    Set<String> filterItemIds = null;
    try {
      filterItemIds =
          NCLocator.getInstance().lookup(IOrderQueryFor20.class)
              .filterItemsByVendorExistPrice(paras);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    if (CollectionUtils.isEmpty(filterItemIds)) {
      return queryResult;
    }
    List<PraybillHeaderVO> headers = new ArrayList<PraybillHeaderVO>();
    List<PraybillItemVO> children = new ArrayList<PraybillItemVO>();
    // ����
    for (PraybillVO bill : queryResult) {

      for (PraybillItemVO child : bill.getBVO()) {

        if (!filterItemIds.contains(child.getPk_praybill_b())) {
          headers.add(bill.getHVO());
          children.add(child);
        }
      }
    }
    BillComposite<PraybillVO> bc =
        new BillComposite<PraybillVO>(PraybillVO.class);
    PraybillVO tempVO = new PraybillVO();
    bc.append(tempVO.getMetaData().getParent(),
        headers.toArray(new PraybillHeaderVO[headers.size()]));
    bc.append(tempVO.getMetaData().getVOMeta(PraybillItemVO.class),
        children.toArray(new PraybillItemVO[children.size()]));
    return bc.composite();
  }

  /**
   * �����ؽ��,�����������������Ը�д
   * 
   * @param queryResult
   * @return
   */
  protected PraybillVO[] processQueryResult(PraybillVO[] queryResult) {
    return queryResult;
  }

}
