/**
 * $�ļ�˵��$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-16 ����04:48:01
 */
package nc.vo.pu.m20.rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import nc.bs.framework.common.RuntimeEnv;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.plan.MaterialPlanVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.pub.AdDaysUtil;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������ǰ�����ñ����е��������ںͽ��鶩������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-4-16 ����04:48:01
 */
public class CalculateBodyDays {

  /**
   * ���鶩�������Ƿ���Ҫ�������ã����ڽ��鶩�������Ѿ���ֵ�����
   */
  private boolean resetSuggestDate = true;

  /**
   * ������
   */
  public CalculateBodyDays() {
    //
  }

  /**
   * ������
   * 
   * @param resetSuggestDate
   */
  public CalculateBodyDays(boolean resetSuggestDate) {
    this.resetSuggestDate = resetSuggestDate;
  }

  /**
   * ȡ�����ϼƻ���Ϣ�е���ǰ����Ϣ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param keyValue
   * @param rows
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-20 ����09:04:41
   */
  public Map<String, MaterialPlanVO> getAdDaysInfo(IKeyValue keyValue,
      int[] rows) {
    HashSet<String> pk_materials = new HashSet<String>();
    for (int i = 0, len = rows.length; i < len; i++) {
      String pk_material =
          (String) keyValue.getBodyValue(rows[i], PraybillItemVO.PK_MATERIAL);
      if (!StringUtil.isEmptyWithTrim(pk_material)) {
        pk_materials.add(pk_material);
      }
    }
    /*
     * add by wandl
     * ����ʲ��������봫���������϶�Ϊ�գ���ȥ��ѯ���ϼƻ���Ϣ�е���ǰ����Ϣ��ֱ�ӷ��ؿ�map
     */
    if(pk_materials.size()<=0){
    	return new HashMap<String, MaterialPlanVO>();
    }
    String pk_org = (String) keyValue.getHeadValue(PraybillHeaderVO.PK_ORG);

    return MaterialPubService.queryMaterialPlanMapInfoByPks(
        pk_materials.toArray(new String[pk_materials.size()]), pk_org,
        new String[] {
          MaterialPlanVO.AHEADBATCH, MaterialPlanVO.AHEADCOFF,
          MaterialPlanVO.FIXEDAHEAD, MaterialPlanVO.PK_MATERIAL
        });
  }

  public boolean isResetSuggestDate() {
    return this.resetSuggestDate;
  }

  /**
   * ������ǰ������ȫ�����������ںͽ��鶩�����ڡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param keyValue <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-16 ����04:51:03
   */
  public void setAdvDays(IKeyValue keyValue) {
    int[] rows = this.getRows(keyValue);
    this.setAdvDays(keyValue, rows);
  }

  /**
   * ������ǰ�������������ںͽ��鶩�����ڡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param keyValue
   * @param rows ��Ҫ�������
   *          <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-16 ����04:51:03
   */
  public void setAdvDays(IKeyValue keyValue, int[] rows) {

    Map<String, MaterialPlanVO> adDaysInfo = this.getAdDaysInfo(keyValue, rows);
    if (null == adDaysInfo || adDaysInfo.size() == 0) {
      this.setComDays(keyValue, rows);
      return;
    }

    this.setAdDays(keyValue, rows, adDaysInfo);

  }

  public void setResetSuggestDate(boolean resetSuggestDate) {
    this.resetSuggestDate = resetSuggestDate;
  }

  private int[] getRows(IKeyValue keyValue) {
    int[] rows = new int[keyValue.getItemCount()];
    for (int i = 0; i < keyValue.getItemCount(); i++) {
      rows[i] = i;
    }
    return rows;
  }

  /**
   * ���ݼƻ���Ϣ������ǰ�����õ��������ںͽ����빺���ڡ�<br>
   * 1) ��������Ϊ��<br>
   * i. ��������=��ǰ���������nDays��<br>
   * ii. ���鶩������=��ǰ����<br>
   * 2) �������ڲ�Ϊ��<br>
   * i. �������ڲ���<br>
   * ii. ���鶩������=����������ǰ�� nDays �� < ��ǰ���ڣ���ǰ���ڣ�����������ǰ�� nDays ��<br>
   * <p>
   * <b>����˵��</b>
   * 
   * @param keyValue
   * @param rows
   * @param adDaysInfo <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-20 ����09:46:05
   */
  private void setAdDays(IKeyValue keyValue, int[] rows,
      Map<String, MaterialPlanVO> adDaysInfo) {
    UFDate billDate = AppContext.getInstance().getBusiDate();
    UFDate billDate_b = billDate;
    UFDate billDate_e = billDate;
    if (!RuntimeEnv.getInstance().isRunningInServer()) {
      billDate_b = billDate_b.asLocalBegin();
      billDate_e = billDate_e.asLocalEnd();
    }

    for (int i = 0, len = rows.length; i < len; i++) {
      String pk_material =
          (String) keyValue.getBodyValue(rows[i], PraybillItemVO.PK_MATERIAL);

      if (!StringUtil.isEmptyWithTrim(pk_material)) {
        MaterialPlanVO vo = adDaysInfo.get(pk_material);
        UFDouble num =
            (UFDouble) keyValue.getBodyValue(rows[i], PraybillItemVO.NNUM);
        if (null == vo || null == num) {
          if (null == keyValue.getBodyValue(rows[i], PraybillItemVO.DREQDATE)) {
            keyValue.setBodyValue(rows[i], PraybillItemVO.DREQDATE, billDate_e);
          }

          if (null == keyValue.getBodyValue(rows[i],
              PraybillItemVO.DSUGGESTDATE)) {
            keyValue.setBodyValue(rows[i], PraybillItemVO.DSUGGESTDATE,
                billDate_b);
          }
        }
        else {
          int nDays =
              AdDaysUtil.getAdDaysArith(num, vo.getFixedahead(),
                  vo.getAheadcoff(), vo.getAheadbatch());

          // ��������Ϊ��
          if (null == keyValue.getBodyValue(rows[i], PraybillItemVO.DREQDATE)) {
            UFDate demdate = billDate.getDateAfter(nDays);
            if (!AdDaysUtil.isDateOverflow(demdate)) {
              if (!RuntimeEnv.getInstance().isRunningInServer()) {
                demdate = demdate.asLocalEnd();
              }
              keyValue.setBodyValue(rows[i], PraybillItemVO.DREQDATE, demdate);
            }
            keyValue.setBodyValue(rows[i], PraybillItemVO.DSUGGESTDATE,
                billDate_b);
          }
          // �������ڲ�Ϊ��
          else {
            // �Ѿ���ֵ�Ҳ���Ҫ��������,������������������ת��ʱ�Ѿ���ֵ�������
            if (null != keyValue.getBodyValue(rows[i],
                PraybillItemVO.DSUGGESTDATE) && !this.isResetSuggestDate()) {
              continue;
            }
            UFDate demdate =
                (UFDate) keyValue
                    .getBodyValue(rows[i], PraybillItemVO.DREQDATE);
            UFDate dsuggestdate = demdate.getDateBefore(nDays);
            if (billDate_b.after(dsuggestdate)) {
              keyValue.setBodyValue(rows[i], PraybillItemVO.DSUGGESTDATE,
                  billDate_b);
            }
            else {
              if (!RuntimeEnv.getInstance().isRunningInServer()) {
                dsuggestdate = dsuggestdate.asLocalBegin();
              }
              keyValue.setBodyValue(rows[i], PraybillItemVO.DSUGGESTDATE,
                  dsuggestdate);
            }
          }

        }
      }
    }
  }

  /**
   * �����������ںͽ��鶩������Ϊ��ǰ���� <b>����˵��</b>
   * 
   * @param keyValue
   * @param rows <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-20 ����09:46:05
   */
  private void setComDays(IKeyValue keyValue, int[] rows) {
    UFDate billDate = AppContext.getInstance().getBusiDate();
    UFDate billDate_e = billDate;
    if (!RuntimeEnv.getInstance().isRunningInServer()) {
      billDate_e = billDate_e.asLocalEnd();
    }
    for (int i = 0, len = rows.length; i < len; i++) {
      if (null == keyValue.getBodyValue(rows[i], PraybillItemVO.DREQDATE)) {
        keyValue.setBodyValue(rows[i], PraybillItemVO.DREQDATE, billDate_e);
      }

      if (null == keyValue.getBodyValue(rows[i], PraybillItemVO.DSUGGESTDATE)) {
        keyValue.setBodyValue(rows[i], PraybillItemVO.DSUGGESTDATE, billDate);
      }
    }
  }
}
