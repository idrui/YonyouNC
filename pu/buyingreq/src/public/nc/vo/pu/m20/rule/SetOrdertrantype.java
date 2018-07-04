/**
 * $�ļ�˵��$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-2 ����04:41:32
 */
package nc.vo.pu.m20.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.pf.PfBillItfDefUtil;
import nc.itf.scmpub.reference.uap.pf.TransTypeMapping;
import nc.pubitf.pu.m21transtype.IPoTransTypeQuery;
import nc.pubitf.scmf.pu.ordertranstype.pu.IQueryOrdertranstypeForPu;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.res.billtype.SCBillType;

/**
 * �����빺����������ϻ��߶�Ӧ�����Ϸ���+�ɹ���֯ƥ�����϶��������������ҵ���Ӧ�Ĳɹ������������ͣ�<br>
 * ����ƥ��ʱ���ϼ����Ϸ��ఴ����ϸ���ȵĹ�����С�<br>
 * ���ƥ�����϶�����������δƥ�䵽��Ӧֵ����ƥ�������ε��ݽӿڹ�ϵ�����ȡĬ��ֵ��<br>
 * �����ͷ��ί�⡱��ѡ����ƥ�������ε��ݽӿڹ�ϵ�����ȡĬ��ֵ�������ֹ��༭ʱֻ�ܲ���ί�ⶩ���Ľ������͡�
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-2-2 ����04:41:32
 */
public class SetOrdertrantype {

  /**
   * �����������������Ҷ������͡�
   * <p>
   * <b>����˵��</b>
   * 
   * @param keyValue <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-18 ����04:28:34
   */
  public void setOrdertrantype(IKeyValue keyValue) {
    int[] rows = this.getRows(keyValue);

    this.setOrdertrantype(keyValue, rows);
  }

  /**
   * �����빺����������ϻ��߶�Ӧ�����Ϸ���+�ɹ���֯ƥ�����϶��������������ҵ���Ӧ�Ĳɹ������������ͣ�<br>
   * ����ƥ��ʱ���ϼ����Ϸ��ఴ����ϸ���ȵĹ�����С�<br>
   * ���ƥ�����϶�����������δƥ�䵽��Ӧֵ����ƥ�������ε��ݽӿڹ�ϵ�����ȡĬ��ֵ��<br>
   * �����ͷ��ί�⡱��ѡ����ƥ�������ε��ݽӿڹ�ϵ�����ȡĬ��ֵ�������ֹ��༭ʱֻ�ܲ���ί�ⶩ���Ľ������͡�
   * <p>
   * <b>����˵��</b>
   * 
   * @param keyValue �빺��
   * @param rows ��Ȼѡ����
   * @since 6.0
   * @author GGR
   * @time 2010-2-2 ����04:43:52
   */
  public void setOrdertrantype(IKeyValue keyValue, int[] rows) {
    Object obsctype = keyValue.getHeadValue(PraybillHeaderVO.BSCTYPE);
    boolean bsctype = false;
    if (obsctype != null && obsctype instanceof UFBoolean) {
      bsctype = ((UFBoolean) obsctype).booleanValue();
    }
    else if (obsctype != null && obsctype instanceof Boolean) {
      bsctype = ((Boolean) obsctype).booleanValue();
    }

    String pk_group = (String) keyValue.getHeadValue(PraybillHeaderVO.PK_GROUP);
    String pk_stockorg =
        (String) keyValue.getHeadValue(PraybillHeaderVO.PK_ORG);

    if (bsctype) {
      // ���ö�������ֻ�ܲ���ί�ⶩ���Ľ�������
      String billType = SCBillType.Order.getCode();

      // ƥ�������ε��ݽӿڹ�ϵ����
      String transtype = this.getDestTransType(billType, pk_group, keyValue);
      for (int i = 0, len = rows.length; i < len; i++) {
        keyValue.setBodyValue(rows[i], PraybillItemVO.CORDERTRANTYPECODE,
            transtype);
      }
    }
    else {
      // ���ö����������Ͳ��չ���
      String billType = POBillType.Order.getCode();

      // ȡ�����϶�����������,��ϸ����
      Map<String, String> transtypes =
          this.getTranstype(pk_group, keyValue, rows);
      // ƥ�������ε��ݽӿڹ�ϵ����
      String transtype = this.getDestTransType(billType, pk_group, keyValue);

      for (int i = 0, len = rows.length; i < len; i++) {
        Object pk_material =
            keyValue.getBodyValue(rows[i], PraybillItemVO.PK_SRCMATERIAL);
        Object pk_org =
            keyValue.getBodyValue(rows[i], PraybillItemVO.PK_PURCHASEORG);
        if (null == pk_material || null == pk_org) {
          continue;
        }
        String pk = pk_org.toString() + pk_stockorg + pk_material.toString();
        // ������϶�������ȡ��ֵ�������ϣ�δȡ��ֵ��ƥ�������ι�ϵ��������
        if (null != transtypes.get(pk)) {
          keyValue.setBodyValue(rows[i], PraybillItemVO.CORDERTRANTYPECODE,
              transtypes.get(pk));
        }
        else {
          keyValue.setBodyValue(rows[i], PraybillItemVO.CORDERTRANTYPECODE,
              transtype);
        }
      }

      // ֱ���빺�������嶩���������Ѱ�������Ƿ�ֱ�˶������ͣ������
      this.procDirectOrderTransType(keyValue, rows);

    }
  }

  /**
   * ƥ�������ε��ݽӿڹ�ϵ������Ҷ������͡�
   * <p>
   * <b>����˵��</b>
   * 
   * @param billType ���ε�������
   * @param pk_group ����
   * @param card
   * @param row
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-14 ����10:40:32
   */
  private String getDestTransType(String billType, String pk_group,
      IKeyValue keyValue) {
    Object prayType = keyValue.getHeadValue(PraybillHeaderVO.CTRANTYPEID);
    Object prayTypeCode = keyValue.getHeadValue(PraybillHeaderVO.VTRANTYPECODE);

    if (null == prayType) {
      return null;
    }
    TransTypeMapping mapping = new TransTypeMapping();
    mapping.setSrcBillType(POBillType.PrayBill.getCode());
    mapping.setSrcTransType(prayType.toString());
    mapping.setSrcTransTypeCode(prayTypeCode.toString());
    mapping.setDestBillType(billType);

    // ȡ�ö�������
    PfBillItfDefUtil.queryTransTypeMapping(pk_group, mapping);

    return mapping.getDestTransType();
  }

  private int[] getRows(IKeyValue keyValue) {
    int[] rows = new int[keyValue.getItemCount()];
    for (int i = 0; i < keyValue.getItemCount(); i++) {
      rows[i] = i;
    }
    return rows;
  }

  /**
   * �������϶����������ò��Ҷ������͡�
   * <p>
   * <b>����˵��</b>
   * 
   * @param pk_group
   * @param card
   * @param rows
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-14 ����10:40:49
   */
  private Map<String, String> getTranstype(String pk_group, IKeyValue keyValue,
      int[] rows) {
    // �����빺����������ϻ��߶�Ӧ�����Ϸ���+�ɹ���֯ƥ�����϶��������������ҵ���Ӧ�Ĳɹ������������ͣ�
    // ����ƥ��ʱ���ϼ����Ϸ��ఴ����ϸ���ȵĹ������

    ArrayList<String> materials = new ArrayList<String>();
    ArrayList<String> orgs = new ArrayList<String>();
    ArrayList<String> stockorgs = new ArrayList<String>();
    String stockorg = (String) keyValue.getHeadValue(PraybillHeaderVO.PK_ORG);
    for (int i = 0, len = rows.length; i < len; i++) {
      Object pk_srcmaterial =
          keyValue.getBodyValue(rows[i], PraybillItemVO.PK_SRCMATERIAL);
      Object pk_org =
          keyValue.getBodyValue(rows[i], PraybillItemVO.PK_PURCHASEORG);
      if (null == pk_srcmaterial || null == pk_org) {
        continue;
      }

      materials.add(pk_srcmaterial.toString());
      orgs.add(pk_org.toString());
      stockorgs.add(stockorg);
    }

    if (orgs.size() == 0) {
      return new HashMap<String, String>();
    }
    IQueryOrdertranstypeForPu service =
        NCLocator.getInstance().lookup(IQueryOrdertranstypeForPu.class);
    return service.getTranstypeIdByOrg(pk_group,
        orgs.toArray(new String[orgs.size()]),
        materials.toArray(new String[materials.size()]),
        stockorgs.toArray(new String[stockorgs.size()]));
  }

  /**
   * ����ֱ���빺��ʱ���嶩�����ͣ����嶩���������Ѱ�������Ƿ�ֱ�˶������ͣ������
   * 
   * @param keyValue �빺��
   * @param rows ��ǰѡ����
   */
  private void procDirectOrderTransType(IKeyValue keyValue, int[] rows) {

    // �����ֱ���빺��
    Object bdirecttransit =
        keyValue.getHeadValue(PraybillHeaderVO.BDIRECTTRANSIT);
    if (bdirecttransit.toString() != null
        && UFBoolean.TRUE.equals(UFBoolean.valueOf(bdirecttransit.toString()))) {
      // �õ���ֵ֮�����ϵĶ������ͼ���
      Set<String> set = new HashSet<String>();
      for (int i = 0, len = rows.length; i < len; i++) {
        String cordertrantypecode =
            (String) keyValue.getBodyValue(rows[i],
                PraybillItemVO.CORDERTRANTYPECODE);
        if (cordertrantypecode != null) {
          set.add(cordertrantypecode);
        }
      }

      if (set.isEmpty()) {
        return;
      }
      IPoTransTypeQuery service =
          NCLocator.getInstance().lookup(IPoTransTypeQuery.class);
      Map<String, PoTransTypeVO> poTransTypeVOMap = null;
      try {
        poTransTypeVOMap =
            service.queryAttrByIDs(set.toArray(new String[set.size()]));
        // �ж�Ѱ�����Ķ��������Ƿ���ֱ�˶������ͣ�������ǣ������
        for (int i = 0, len = rows.length; i < len; i++) {
          String cordertrantypecode =
              (String) keyValue.getBodyValue(rows[i],
                  PraybillItemVO.CORDERTRANTYPECODE);
          if (cordertrantypecode == null) {
            continue;
          }
          if (poTransTypeVOMap.get(cordertrantypecode) != null
              && UFBoolean.TRUE.equals(poTransTypeVOMap.get(cordertrantypecode)
                  .getBdirect())) {
            continue;
          }

          keyValue.setBodyValue(rows[i], PraybillItemVO.CORDERTRANTYPECODE,
              null);
        }

      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }

    }
  }
}
