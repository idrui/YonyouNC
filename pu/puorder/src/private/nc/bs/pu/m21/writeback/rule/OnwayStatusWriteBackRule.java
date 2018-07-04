/**
 * $�ļ�˵��$
 *
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-15 ����04:48:11
 */
package nc.bs.pu.m21.writeback.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.impl.pu.m21.onway.bp.OnwayBpTools;
import nc.impl.pubapp.pattern.data.vo.VOInsert;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.m21.entity.StatusOnWayItemVO;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.AssertUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.collections.MapUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��������д��;״̬��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-10-15 ����04:48:11
 */
public class OnwayStatusWriteBackRule implements IRule<OrderViewVO> {

  /** δ���� */
  private static final String notOperated = "N";

  /** �Ѳ��� */
  private static final String operated = "Y";

  /** �Ѳ��� */
  private String opreraStr = null;

  /** ״̬ */
  private int status = 0;

  public OnwayStatusWriteBackRule(int status, String opreraStr) {
    this.status = status;
    this.opreraStr = opreraStr;
  }

  /**
   * ���෽����д
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(OrderViewVO[] vos) {
    Map<String, OrderViewVO> viewVOMap = this.getBodyPkMap(vos);

    String[] keyArray =
        viewVOMap.keySet().toArray(new String[viewVOMap.size()]);

    // ��������δ��������
    Map<String, StatusOnWayItemVO> onwayNotOperMap =
        this.queryOnwayStatusVO(keyArray, OnwayStatusWriteBackRule.notOperated,
            this.status);

    // ����δ����������
    this.updateNotOperVO(onwayNotOperMap, viewVOMap);

    // ��ѯ�Ѿ����ڵĲ���������
    Map<String, StatusOnWayItemVO> onwayMap =
        this.queryOnwayStatusVO(keyArray, OnwayStatusWriteBackRule.operated,
            this.status);

    // �����Ѿ���������и��£�û�в���
    Map<String, OrderViewVO> notExistViewBvoMap =
        new HashMap<String, OrderViewVO>();

    if (MapUtils.isNotEmpty(onwayMap)) {
      notExistViewBvoMap = this.updateExistVO(onwayMap, viewVOMap);
    }
    else {
      notExistViewBvoMap.putAll(viewVOMap);
    }

    // ���벻���ڵ���
    if (MapUtils.isNotEmpty(notExistViewBvoMap)) {
      this.insertNotExistVO(notExistViewBvoMap, onwayNotOperMap);
    }
  }

  /**
   * ��������������ȡ�������ӱ�pk
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-15 ����05:04:08
   */
  private Map<String, OrderViewVO> getBodyPkMap(OrderViewVO[] vos) {
    Map<String, OrderViewVO> bvoPkMap = new HashMap<String, OrderViewVO>();

    for (OrderViewVO viewVO : vos) {
      bvoPkMap.put(viewVO.getPk_order_b(), viewVO);
    }

    return bvoPkMap;
  }

  /**
   * ��������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param notExistViewBvoMap
   * @param onwayNotOperMap
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-18 ����10:22:57
   */
  private void insertNotExistVO(Map<String, OrderViewVO> notExistViewBvoMap,
      Map<String, StatusOnWayItemVO> onwayNotOperMap) {

    Set<Map.Entry<String, OrderViewVO>> notExistSet =
        notExistViewBvoMap.entrySet();
    Set<StatusOnWayItemVO> onwaySet = new HashSet<StatusOnWayItemVO>();

    for (Map.Entry<String, OrderViewVO> notExistMapEntry : notExistSet) {
      String pk_order_b = notExistMapEntry.getKey();
      OrderViewVO viewVO = notExistMapEntry.getValue();

      StatusOnWayItemVO onwayVO = onwayNotOperMap.get(pk_order_b);
      StatusOnWayItemVO updateOnwayVO = (StatusOnWayItemVO) onwayVO.clone();

      // �ۼƵ�������
      // updateOnwayVO.setNonwaynum(viewVO.getNaccumarrvnum());
      Object numObj = viewVO.getAttributeValue(this.opreraStr);
      UFDouble num = null;
      if (numObj != null) {
        num = new UFDouble(numObj.toString());
      }
      updateOnwayVO.setNonwaynum(num);

      // �����Ѳ���
      updateOnwayVO.setIsoperated(UFBoolean
          .valueOf(OnwayStatusWriteBackRule.operated));
      onwaySet.add(updateOnwayVO);
    }

    StatusOnWayItemVO[] updateVOs =
        onwaySet.toArray(new StatusOnWayItemVO[onwaySet.size()]);

    VOInsert<StatusOnWayItemVO> voInsert = new VOInsert<StatusOnWayItemVO>();
    voInsert.insert(updateVOs);

  }

  /**
   * ��������������������;״̬VO
   * <p>
   * <b>����˵��</b>
   * 
   * @param bvoPks
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-18 ����08:49:06
   */
  private Map<String, StatusOnWayItemVO> queryOnwayStatusVO(String[] bvoPks,
      String isoperated, int onwayStatus) {
    SqlBuilder sqlbd = new SqlBuilder();
    sqlbd.append(" and ");
    // ��ʱ��
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_33.name());
    sqlbd.append(builder.buildSQL(StatusOnWayItemVO.PK_ORDER_B, bvoPks));
    sqlbd.append(" and ");
    sqlbd.append(StatusOnWayItemVO.ISOPERATED, isoperated);
    sqlbd.append(" and ");
    sqlbd.append(StatusOnWayItemVO.FONWAYSTATUS, onwayStatus);

    VOQuery<StatusOnWayItemVO> voquery =
        new VOQuery<StatusOnWayItemVO>(StatusOnWayItemVO.class);
    StatusOnWayItemVO[] onwayVOs = voquery.query(sqlbd.toString(), null);

    // �����ӱ�pk����map
    return OnwayBpTools.groupKeyMap(onwayVOs, StatusOnWayItemVO.PK_ORDER_B);
  }

  /**
   * �������������������Ѿ����ڵ�VO
   * <p>
   * <b>����˵��</b>
   * 
   * @param onwayMap
   * @param viewVOMap
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-18 ����09:47:25
   */
  private Map<String, OrderViewVO> updateExistVO(
      Map<String, StatusOnWayItemVO> onwayMap,
      Map<String, OrderViewVO> viewVOMap) {

    Set<Map.Entry<String, StatusOnWayItemVO>> onwaySet = onwayMap.entrySet();

    List<StatusOnWayItemVO> onwayList = new ArrayList<StatusOnWayItemVO>();
    Map<String, OrderViewVO> notExistBvoMap =
        new HashMap<String, OrderViewVO>();
    notExistBvoMap.putAll(viewVOMap);

    for (Map.Entry<String, StatusOnWayItemVO> onwayEntrySet : onwaySet) {
      String pk_order_b = onwayEntrySet.getKey();
      StatusOnWayItemVO onwayVO = onwayEntrySet.getValue();
      StatusOnWayItemVO updateOnwayVO = (StatusOnWayItemVO) onwayVO.clone();

      Object numObj =
          viewVOMap.get(pk_order_b).getAttributeValue(this.opreraStr);
      UFDouble num = null;
      if (numObj != null) {
        num = new UFDouble(numObj.toString());
      }
      // �ۼƵ�������
      updateOnwayVO.setNonwaynum(num);
      onwayList.add(updateOnwayVO);

      // �����ڵ�VOȥ��
      notExistBvoMap.remove(pk_order_b);
    }

    VOUpdate<StatusOnWayItemVO> voupdate = new VOUpdate<StatusOnWayItemVO>();
    StatusOnWayItemVO[] updateVOs =
        onwayList.toArray(new StatusOnWayItemVO[onwayList.size()]);

    voupdate.update(updateVOs, new String[] {
      StatusOnWayItemVO.NONWAYNUM
    });

    return notExistBvoMap;
  }

  /**
   * ��������������ά��δ����״̬��VO
   * <p>
   * <b>����˵��</b>
   * 
   * @param onwayNotOperMap
   * @param viewVOMap
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-18 ����10:45:31
   */
  private void updateNotOperVO(Map<String, StatusOnWayItemVO> onwayNotOperMap,
      Map<String, OrderViewVO> viewVOMap) {

    Set<Map.Entry<String, OrderViewVO>> viewVOSet = viewVOMap.entrySet();
    Set<StatusOnWayItemVO> updateOnwaySet = new HashSet<StatusOnWayItemVO>();

    for (Map.Entry<String, OrderViewVO> viewMapEntry : viewVOSet) {
      String pk_order_b = viewMapEntry.getKey();
      OrderViewVO viewVO = viewMapEntry.getValue();

      StatusOnWayItemVO onwayVO = onwayNotOperMap.get(pk_order_b);
      AssertUtils.assertValue(onwayVO != null, nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0150")/*
                                                                   * @res
                                                                   * "������������;״̬û���ߵ�����״̬!"
                                                                   */);

      if (onwayVO != null) {
        StatusOnWayItemVO updateOnwayVO = (StatusOnWayItemVO) onwayVO.clone();
        // ֻ�����ۼƵ�������
        Object numObj = viewVO.getAttributeValue(this.opreraStr);
        UFDouble num = null;
        if (numObj != null) {
          num = new UFDouble(numObj.toString());
        }
        UFDouble onwayNum = updateOnwayVO.getNonwaynum();
        UFDouble notArriveNum = MathTool.sub(onwayNum, num);
        // ���ʵ�ʵ������������������;״̬�ĵ�����������������쳣
        if (MathTool.compareTo(notArriveNum, UFDouble.ZERO_DBL) < 0) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4004030_0", "04004030-0151")/*
                                                                       * @res
                                                                       * "ʵ�ʵĵ���������������ܴ�����;״̬�ĵ������������!"
                                                                       */);
        }

        updateOnwayVO.setNonwaynum(notArriveNum);

        updateOnwaySet.add(updateOnwayVO);
      }
    }

    StatusOnWayItemVO[] updateVOArray =
        updateOnwaySet.toArray(new StatusOnWayItemVO[updateOnwaySet.size()]);
    VOUpdate<StatusOnWayItemVO> voupdate = new VOUpdate<StatusOnWayItemVO>();
    // ֻ������;�����ֶ�
    voupdate.update(updateVOArray, new String[] {
      StatusOnWayItemVO.NONWAYNUM
    });
  }
}
