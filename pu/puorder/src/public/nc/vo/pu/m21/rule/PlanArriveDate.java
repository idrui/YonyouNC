package nc.vo.pu.m21.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.plan.MaterialPlanVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����ƻ���������<br/>
 * �ֹ����Ӷ���ʱ���ƻ���������Ĭ��ȡ��������+��ǰ�ڣ������㷨���£�<br/>
 * ��ǰ��=�̶���ǰ�� <br/>
 * {<br/>
 * if ����>��ǰ������<br/>
 * ����ȡ��[������-��ǰ��������*��ǰ��ϵ��/��ǰ������]<br/>
 * else 0<br/>
 * <br/>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-1-15 ����12:19:55
 */
public class PlanArriveDate {
  private IKeyValue keyValue;

  public PlanArriveDate(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * �����������������üƻ��������ڡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param row Ҫ���õ���ID
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-21 ����01:36:13
   */
  public void setPlanArriveDate(int startRow, int endRow) {
    int[] rows = new int[endRow - startRow + 1];
    for (int i = 0; i < rows.length; i++) {
      rows[i] = startRow++;
    }
    this.setPlanArriveDate(rows);
  }

  public void setPlanArriveDate(int[] rows) {
    // �����������Ϊ�գ��򲻽��м���
    Object objBillDate = this.keyValue.getHeadValue(OrderHeaderVO.DBILLDATE);
    if (objBillDate == null) {
      return;
    }

    // ׼������
    Map<String, List<String>> params = this.prepareParams(rows);

    if (params.size() != 0) {
      // ��ѯ�����ϵ����Ĳɹ���ǰ�������Ϣ
      Map<String, Map<String, MaterialPlanVO>> map =
          this.getMateriaPlan(params);

      // �������ϵ����Ĳɹ���ǰ�ڼ���ƻ���������
      this.calculatePlanArriveDate(map, rows);
    }
  }

  /**
   * ���ƻ��������ڸ�Ϊend���ͣ���ֹ�����ڣ�
   */
  // public void setPlanArriveDateAsEnd() {
  // for (int i = 0; i < this.keyValue.getItemCount(); ++i) {
  // UFDate date =
  // (UFDate) this.keyValue.getBodyValue(i, OrderItemVO.DPLANARRVDATE);
  // if (null == date) {
  // continue;
  // }
  //
  // date = date.asLocalEnd();
  // this.keyValue.setBodyValue(i, OrderItemVO.DPLANARRVDATE, date);
  // }
  // }

  private void calculatePlanArriveDate(
      Map<String, Map<String, MaterialPlanVO>> map, int[] rows) {
    // ��������
    Object objBillDate = this.keyValue.getHeadValue(OrderHeaderVO.DBILLDATE);
    UFDate billDate = (UFDate) objBillDate;
    for (int row : rows) {
      Object storeOrgId =
          this.keyValue.getBodyValue(row, OrderItemVO.PK_ARRVSTOORG);
      Map<String, MaterialPlanVO> mpvo = map.get(storeOrgId);
      if (mpvo != null) {
        Object materialId =
            this.keyValue.getBodyValue(row, OrderItemVO.PK_MATERIAL);
        MaterialPlanVO vo = mpvo.get(materialId);
        if (vo != null) {
          this.calculatePlanArriveDate(vo, billDate, row);
        }
        else {
          this.keyValue.setBodyValue(row, OrderItemVO.DPLANARRVDATE, billDate);
        }
      }
      else {
        this.keyValue.setBodyValue(row, OrderItemVO.DPLANARRVDATE, billDate);
      }
    }
  }

  private void calculatePlanArriveDate(MaterialPlanVO vo, UFDate billDate,
      int row) {
    UFDouble nnum =
        (UFDouble) this.keyValue.getBodyValue(row, OrderItemVO.NNUM);
    // ---------------------������ǰ��
    // [0]�̶���ǰ��
    int advanceDay =
        vo.getFixedahead() == null ? 0 : vo.getFixedahead().intValue();
    // [1]��ǰ��ϵ��
    UFDouble advanceCoff =
        vo.getAheadcoff() == null ? UFDouble.ZERO_DBL : vo.getAheadcoff();
    // [2]��ǰ������
    UFDouble advanceBatch =
        vo.getAheadbatch() == null ? UFDouble.ZERO_DBL : vo.getAheadbatch();
    if (nnum.compareTo(advanceBatch) > 0) {
      // ����>��ǰ������
      UFDouble dTemp = nnum.sub(advanceBatch);
      dTemp = dTemp.multiply(advanceCoff);
      if (advanceBatch.compareTo(UFDouble.ZERO_DBL) > 0) {
        dTemp = dTemp.div(advanceBatch);
      }
      // ����ȡ�����������Ϊ���������ж�
      int iInt = dTemp.intValue();
      if (MathTool.compareTo(dTemp, new UFDouble(iInt)) != 0) {
        iInt += 1;
      }
      advanceDay += iInt;
    }
    // ---------------------�õ��ƻ���������
    this.keyValue.setBodyValue(row, OrderItemVO.DPLANARRVDATE,
        billDate.getDateAfter(advanceDay));
  }

  /**
   * ��������������������ϼƻ���Ϣҳǩ�еĹ̶���ǰ�ڡ���ǰ����������ǰ��ϵ����
   * <p>
   * <b>����˵��</b>
   * 
   * @param params ������Ϣ������key-�����֯��PK��value-����VID�ļ��ϣ�
   * @return ���ϼƻ���Ϣҳǩ�������Ϣ��key-�����֯��PK��value-��key-����PK��value-���ϼƻ���Ϣ����
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-21 ����12:36:42
   */
  private Map<String, Map<String, MaterialPlanVO>> getMateriaPlan(
      Map<String, List<String>> params) {
    Map<String, Map<String, MaterialPlanVO>> map =
        new HashMap<String, Map<String, MaterialPlanVO>>();
    Set<Entry<String, List<String>>> storeOrgIds = params.entrySet();
    for (Entry<String, List<String>> storeOrgId : storeOrgIds) {
      String[] materialIds = storeOrgId.getValue().toArray(new String[0]);
      MaterialPlanVO[] vos =
          MaterialPubService.queryMaterialPlanInfoByPks(materialIds,
              storeOrgId.getKey(), new String[] {
                MaterialPlanVO.FIXEDAHEAD, MaterialPlanVO.AHEADBATCH,
                MaterialPlanVO.AHEADCOFF, MaterialPlanVO.PK_ORG,
                MaterialPlanVO.PK_MATERIAL
              });

      if (vos == null) {
        continue;
      }

      Map<String, MaterialPlanVO> vomap = new HashMap<String, MaterialPlanVO>();
      for (MaterialPlanVO vo : vos) {
        vomap.put(vo.getPk_material(), vo);
      }

      map.put(storeOrgId.getKey(), vomap);
    }
    return map;
  }

  private Map<String, List<String>> prepareParams(int[] rows) {
    Map<String, List<String>> map = new HashMap<String, List<String>>();
    for (int row : rows) {
      // ���ԭ�ƻ�����������ֵ���������¼���
      Object planArriveDate =
          this.keyValue.getBodyValue(row, OrderItemVO.DPLANARRVDATE);
      if (planArriveDate != null) {
        continue;
      }

      // �������Ϊ�գ��򲻽��м���
      Object materialId =
          this.keyValue.getBodyValue(row, OrderItemVO.PK_MATERIAL);
      if (materialId == null || ((String) materialId).length() == 0) {
        continue;
      }

      // ����ջ������֯Ϊ�գ��򲻽��м���
      Object storeOrgId =
          this.keyValue.getBodyValue(row, OrderItemVO.PK_ARRVSTOORG);
      if (storeOrgId == null || ((String) storeOrgId).length() == 0) {
        continue;
      }

      // �������Ϊ�գ��򲻽��м���
      Object norderNum = this.keyValue.getBodyValue(row, OrderItemVO.NNUM);
      if (norderNum == null || ((UFDouble) norderNum).equals(UFDouble.ZERO_DBL)) {
        continue;
      }

      // ��֯������map
      if (map.containsKey(storeOrgId)) {
        map.get(storeOrgId).add((String) materialId);
      }
      else {
        List<String> ids = new ArrayList<String>();
        ids.add((String) materialId);
        map.put((String) storeOrgId, ids);
      }
    }
    return map;
  }
}
