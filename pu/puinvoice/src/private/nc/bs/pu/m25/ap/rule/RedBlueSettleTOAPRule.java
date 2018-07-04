/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-14 ����11:54:22
 */
package nc.bs.pu.m25.ap.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.rule.IFilterRule;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.env.InvoiceUIToBSEnv;
import nc.vo.pu.m27.query.SettleBillInfo;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * ��������Գ�ķ�Ʊ��Ӧ��
 * @scene
 * ��Ӧ��,����(ƥ��)���Զ���Ӧ��
 * @param
 * sttlInfoMap ��Ʊ�Ľ�����ϸ
 * env �ɹ���Ʊ����ʱǰ̨����̨�Ļ�����Ϣ��һ����ƽ̨������userObj���⴫ 
 * 
 * @since 6.3
 * @version 2014-10-22 ����3:40:55
 * @author zhangshqb
 */
public class RedBlueSettleTOAPRule implements IFilterRule<InvoiceVO> {

  /** ������Ϣ */
  private InvoiceUIToBSEnv env;

  /** ��Ʊ�Ľ�����ϸ--���������Գ��˫����Ʊ **/
  private MapList<String, SettleBillInfo> sttlInfoMap;

  /**
   * RedBlueSettleTOAPRule �Ĺ�����
   * 
   * @param sttlInfoMap
   *          ��Ʊ�Ľ�����ϸ
   */
  public RedBlueSettleTOAPRule(MapList<String, SettleBillInfo> sttlInfoMap,
      InvoiceUIToBSEnv env) {
    this.sttlInfoMap = sttlInfoMap;
    this.env = env;
  }

  @Override
  public InvoiceVO[] process(InvoiceVO[] vos) {
    if (ArrayUtils.isEmpty(vos) || null == this.sttlInfoMap
        || 0 == this.sttlInfoMap.size()) {
      return vos;
    }
    // ������Զ����㴫Ӧ����֮ǰ�Ѿ����˴˹��򣬲���Ҫ������һ�飻�ֶ���Ӧ�������ߴ˹���
    if (this.env != null && this.env.isAutoSettleSendAP()) {
      return vos;
    }
    return this.processRedBlue(vos);
  }

  private InvoiceVO[] filterVOs(InvoiceVO[] vos, Set<String> unSendAPIDs,
      Set<String> unSendAPRushIDs) {
    List<InvoiceVO> filterVos = new ArrayList<InvoiceVO>();
    for (InvoiceVO vo : vos) {
      if (unSendAPIDs.contains(vo.getPrimaryKey())
          || unSendAPRushIDs.contains(vo.getPrimaryKey())) {
        continue;
      }
      filterVos.add(vo);
    }
    return filterVos.toArray(new InvoiceVO[filterVos.size()]);
  }

  private InvoiceVO[] getRushInvoiceVOs(InvoiceVO[] vos, Set<String> rushIDs) {
    List<InvoiceVO> rushVOList = new ArrayList<InvoiceVO>();
    for (InvoiceVO vo : vos) {
      String pk_invoice = vo.getPrimaryKey();
      if (rushIDs.contains(pk_invoice)) {
        rushVOList.add(vo);
      }
    }
    return rushVOList.toArray(new InvoiceVO[rushVOList.size()]);
  }

  /**
   * ������������������������ϸ��ȡֵ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos �����Գ�˫����Ʊ��VO����
   * @param RushABMap ���ڷ��أ�����Գ��Map(A1--B1,B1--A1ʱֻ����A1--B1������������ϸȥ��)
   * @param rushIDs ���ڷ��أ�������䵽�Գ岿�ֵķ�ƱID
   * @param normStlInvcBIDs ���ڷ��أ���������ⵥ����ķ�Ʊ��ID
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-8-4 ����10:21:50
   */
  private void iterateSttleInfo(InvoiceVO[] vos, Map<String, String> RushABMap,
      Set<String> rushIDs, Set<String> normStlInvcBIDs) {
    InvoiceItemVO[] items = AggVOUtil.getCombinItemVOs(vos);
    Set<String> bids =
        CirVOUtil.getDistinctFieldSet(items, InvoiceItemVO.PK_INVOICE_B);
    for (String pk_invoice_b : bids) {
      if (!this.sttlInfoMap.containsKey(pk_invoice_b)) {
        continue;
      }
      List<SettleBillInfo> stlInfo = this.sttlInfoMap.get(pk_invoice_b);
      if (CollectionUtils.isEmpty(stlInfo)) {
        continue;
      }
      for (SettleBillInfo info : stlInfo) {
        String pk_stockps_b = info.getPk_stock_b();
        if (!StringUtil.isEmptyWithTrim(pk_stockps_b)) {
          if (null != normStlInvcBIDs) {
            normStlInvcBIDs.add(pk_invoice_b);
          }
        }
        else {
          String pk_rush_b = info.getPk_rushinvoice_b();
          String pk_rush = info.getPk_rushinvoice();
          String pk_invoice = info.getPk_invoice();
          if (!StringUtil.isEmptyWithTrim(pk_rush_b)
              && !StringUtil.isEmptyWithTrim(pk_rush)
              && (null == rushIDs || !rushIDs.contains(pk_invoice))) {
            if (null != RushABMap) {
              RushABMap.put(pk_invoice_b, pk_rush_b);
            }
            if (null != rushIDs) {
              rushIDs.add(pk_rush);
            }
          }
        }
      }
    }
  }

  /**
   * �Ժ�Գ�ķ�Ʊ���Զ����㴫�����������ַ�ƱID ������������
   * <p>
   * ������Ʊ�Զ�����ʱ���������˫���ķ�Ʊ��������ֵ��ȫ��ȣ�
   * <p>
   * ����������δ����Ӧ��ʱ�� �������������ٴ�Ӧ����������Ҫ��Ӧ��
   * <p>
   * ��Ҳ������������������ͬʱ����������δ��Ӧ���ķ�Ʊ����Ҫ��Ӧ������
   * <p>
   * �����䣺�Ѵ�Ӧ�������Ѳ��������Ĳɹ���ƱҲ�������Գ��Զ����㣩
   * <p>
   * ���������£�
   * <p>
   * <li>������ִ���Ӧ����������δ����Ӧ���������ֹ��˵��������ִ�Ӧ��</li>
   * <li>�������δ��Ӧ���������ִ�Ӧ������ֱ�ӽ����ִ������ܺ���</li>
   * <li>���˫�����Ѿ�����Ӧ������һ���������ٴ�</li>
   * <li>���˫����δ��Ӧ��������������ֵ��ȣ���Ҳ����Ҫ�ٴ�</li>
   * <li>���˫����δ��Ӧ��������������ֵ���ȣ�����Ҫ��</li>
   * 
   * @param vos -- �����˺����Գ��˫����ƱVO�������ٲ�
   */
  private InvoiceVO[] processRedBlue(InvoiceVO[] vos) {
    Map<String, String> rushABMap = new HashMap<String, String>();
    Set<String> rushIDs = new HashSet<String>();
    Set<String> normStlInvcBIDs = new HashSet<String>();
    // ����������ϸ�����ɶԳ��VOmap,�Գ巢ƱBID��set�������ý���ķ�ƱBID��set��
    this.iterateSttleInfo(vos, rushABMap, rushIDs, normStlInvcBIDs);
    if (0 == rushIDs.size()) {
      return vos;
    }
    // �ӷ�ƱVO�в���Գ�ķ�Ʊ
    InvoiceVO[] rushVos = this.getRushInvoiceVOs(vos, rushIDs);
    // �����ǰ��Ʊvo��û�жԳ�vo�������Ϊ��Ʊ�ֹ���Ӧ���������²�vo
    if (ArrayUtils.isEmpty(rushVos)) {
      rushVos =
          new BillQuery<InvoiceVO>(InvoiceVO.class).query(rushIDs
              .toArray(new String[rushIDs.size()]));
    }
    Map<String, InvoiceItemVO> normItemMap = AggVOUtil.createItemMap(vos);
    Map<String, InvoiceItemVO> rushItemMap = AggVOUtil.createItemMap(rushVos);
    Map<String, InvoiceVO> VOMap = AggVOUtil.createVOMap(vos);
    Map<String, InvoiceVO> rushVOMap = AggVOUtil.createVOMap(rushVos);
    // ����Ҫ��Ӧ���ĶԳ�A��ƱID--���vos���Ƴ�
    Set<String> unSendAPIDs = new HashSet<String>();
    // ����Ҫ��Ӧ���ĶԳ�B��ƱID
    Set<String> unSendAPRushIDs = new HashSet<String>();
    for (Entry<String, String> entry : rushABMap.entrySet()) {
      String pk_invoice_b = entry.getKey();
      InvoiceItemVO normItem = normItemMap.get(pk_invoice_b);
      String pk_invoice = normItem.getPk_invoice();
      String pk_rush_b = entry.getValue();
      InvoiceItemVO rushItem = rushItemMap.get(pk_rush_b);
      String pk_rush = rushItem.getPk_invoice();
      UFDouble num = MathTool.nvl(normItem.getNnum());
      UFDouble rushNum = MathTool.nvl(rushItem.getNnum());
      UFBoolean apFlag = VOMap.get(pk_invoice).getParentVO().getBapflag();
      UFBoolean rushApFlag = rushVOMap.get(pk_rush).getParentVO().getBapflag();
      /**
       * ����������δ����Ӧ�� &&
       * ˫���ķ�Ʊ��������ֵ��ȫ��� &&
       * ˫���ķ�Ʊδ����Ĵ˴���������(��������൱���Ѵ�Ӧ��)
       * ��˫������Ӧ��������ش�Ӧ��
       */
      if (!normStlInvcBIDs.contains(pk_invoice_b)
          && !normStlInvcBIDs.contains(pk_rush_b)
          && !ValueUtils.getBoolean(apFlag)
          && !ValueUtils.getBoolean(rushApFlag)
          && num.abs().equals(rushNum.abs())) {
        unSendAPIDs.add(pk_invoice);
        unSendAPRushIDs.add(pk_rush);
      }
      else {
        unSendAPIDs.remove(pk_invoice);
        unSendAPRushIDs.remove(pk_rush);
      }
    }
    return this.filterVOs(vos, unSendAPIDs, unSendAPRushIDs);
  }

}
