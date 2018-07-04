/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-14 ����11:18:15
 */
package nc.bs.pu.m21.query.ic;

import nc.bs.pu.m21.plugin.OrderPluginPoint;
import nc.bs.pu.m21.query.OrderQueryUtil;
import nc.bs.pu.m21.query.ic.rule.CanBackStockNumCalcRule;
import nc.bs.pu.m21.query.ic.rule.StorePrivilegeChkRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>Ϊ�˿ⵥ�ṩ��������ѯ����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-14 ����11:18:15
 */
public class OrderQueryFor45BackBP {
  /**
   * ��������������Ϊ�˿ⵥ���������ṩ��ѯ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param cond
   * @param isLazy
   * @return <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-1-14 ����04:14:32
   */
  public OrderVO[] query(String cond, UFBoolean isLazy) {
    // ����ID��ѯ����VO
    OrderVO[] queryVos = OrderQueryUtil.queryFor45_23(cond, isLazy);
    AroundProcesser<OrderVO> processer =
        new AroundProcesser<OrderVO>(OrderPluginPoint.BACK45);
    this.addRule(processer);
    queryVos = processer.after(queryVos);
    return queryVos;
  }

  private void addRule(AroundProcesser<OrderVO> processer) {
    // ������������
    processer.addAfterRule(new CanBackStockNumCalcRule());
    // �����ԱȨ�޹���
    processer.addAfterRule(new StorePrivilegeChkRule());
  }

  /**
   * ��������������Where����
   * <p>
   * <b>����˵��</b>
   * 
   * @param poOrderB
   * @param poOrderBB1
   * @param potrantype
   * @param bdMaterial
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-9 ����10:58:55
   */
  // private String getWherePart(String poOrderB, String potrantype,
  // String bdMaterial) {
  // StringBuilder sb = new StringBuilder();
  // QueryCondManager manager = QueryCondManager.getInstance();
  // String pubcond =
  // manager.getPubCondFor45_23(poOrderB, potrantype, bdMaterial);
  // sb.append(pubcond);
  //
  // // �˿ⲻ��鵽���ƻ�
  // // �˿ⲻ��鶩����;״̬
  //
  // // �������ͨ���������ۼ�������� > �ۼ��˿�����
  // sb.append(" and (");
  // sb.append("(");
  // sb.append(poOrderB + ".nnum > 0 and coalesce(");
  // sb.append(poOrderB + ".naccumstorenum,0) > coalesce(" + poOrderB
  // + ".nbackstorenum,0)");
  // // ������˻��������򶩵������ľ���ֵ > �ۼ��˿����� + �ۼ��˻�����
  // sb.append(") or (");
  // sb.append(poOrderB + ".nnum < 0 and ");
  // sb.append(poOrderB + ".nnum  + coalesce(" + poOrderB
  // + ".nbackarrvnum,0) + coalesce(" + poOrderB + ".nbackstorenum,0) < 0 ");
  // sb.append(")) ");
  //
  // return sb.toString();
  // }

  /**
   * ������������������SQL���
   * <p>
   * <b>����˵��</b>
   * 
   * @param cond
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-9 ����10:56:39
   */
  // private String getWholeSql(String cond) {
  // // ����
  // FromWhereParseUtil utils = new FromWhereParseUtil(cond);
  // String poOrderB = utils.getTableAlias(PUEntity.M21_B_TABLE);
  // String poOrderBB1 = utils.getTableAlias(PUEntity.M21_BB1_TABLE);
  // String potrantype = utils.getTableAlias(PUEntity.M21_TRANTYPE_TABLE);
  // String bdMaterial = utils.getTableAlias(PUEntity.Material_TABLE);
  //
  // // ����б���ʹ�ñ���������ʹ�ñ���
  // QueryUtil quitls = QueryUtil.getInstance();
  // poOrderB = quitls.getTableName(PUEntity.M21_B_TABLE, poOrderB);
  // poOrderBB1 = quitls.getTableName(PUEntity.M21_BB1_TABLE, poOrderBB1);
  // potrantype = quitls.getTableName(PUEntity.M21_TRANTYPE_TABLE, potrantype);
  // bdMaterial = quitls.getTableName(PUEntity.Material_TABLE, bdMaterial);
  //
  // StringBuilder sb = new StringBuilder();
  // sb.append("select po_order.pk_order," + poOrderB + ".pk_order_b,"
  // + poOrderBB1 + ".pk_order_bb1 ");
  // sb.append(cond);
  // sb.append(" and ");
  // sb.append(this.getWherePart(poOrderB, potrantype, bdMaterial));
  //
  // return sb.toString();
  // }

}
