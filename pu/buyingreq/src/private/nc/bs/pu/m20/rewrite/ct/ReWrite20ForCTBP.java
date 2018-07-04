package nc.bs.pu.m20.rewrite.ct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdateTS;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.writeback.GenNumWriteBackVO;
import nc.vo.pu.m20.enumeration.EnumOperate;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pub.JavaType;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ���ͬ��д�빺��BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-5-18 ����10:00:49
 */
public class ReWrite20ForCTBP {

  /**
   * ����������������д���ɺ�ͬ�������߻�д�빺������"��������������"�ֶΡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   *          <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-18 ����10:53:42
   */
  public void backWriteNum(GenNumWriteBackVO[] vos) {

    if (!SysInitGroupQuery.isCTEnabled()) {
      return;
    }

    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    // ���ں�ͬ��д�빺��
    List<List<Object>> adddata = new ArrayList<List<Object>>();
    List<List<Object>> deldata = new ArrayList<List<Object>>();
    ArrayList<PraybillItemVO> items = new ArrayList<PraybillItemVO>();
    // ��������������д�빺��
    ArrayList<String> saDelPks = new ArrayList<String>();
    // Map<����������pk,���������к�>
    Map<String, String> pk_num_map = new HashMap<String, String>();
    for (GenNumWriteBackVO vo : vos) {
      PraybillItemVO item = new PraybillItemVO();
      item.setPk_praybill_b(vo.getPk_praybill_b());
      // �Ƿ�����������
      if (vo.getIsSaOrder().booleanValue()) {
        if (EnumOperate.ADD == vo.getOperateFlag()) {
          pk_num_map.put(vo.getPk_praybill_b(), vo.getCrowno());
        }
        if (EnumOperate.DELETE == vo.getOperateFlag()) {
          saDelPks.add(item.getPk_praybill_b());
        }
        items.add(item);
      }
      // ��ͨ��ͬ
      else {
        List<Object> rowData = new ArrayList<Object>();
        rowData.add(vo.getPk_praybill_b());
        if (EnumOperate.ADD == vo.getOperateFlag()) {
          items.add(item);
          adddata.add(rowData);
        }
        if (EnumOperate.DELETE == vo.getOperateFlag()) {
          items.add(item);
          deldata.add(rowData);
        }
      }
    }

    if (pk_num_map.size() > 0) {
      this.writeBackisSaOrderAdd(pk_num_map);
      this.upTs(items);
    }
    else if (saDelPks.size() > 0) {
      this.writeBackisSaOrderDel(saDelPks);
      this.upTs(items);
    }
    else {
      this.upAdd(adddata);

      this.upDel(deldata);

      this.upTs(items);
    }

  }

  /**
   * ��д�빺������"��������������"�ֶ�
   * 
   * @param pk_num_map ����������д�빺��,�����������޸ĵ��빺����pks
   */
  private void writeBackisSaOrderAdd(Map<String, String> pk_num_map) {
    // ��ѯ���빺������ԭʼ��VO
    String[] bidArray =
        pk_num_map.keySet().toArray(new String[pk_num_map.keySet().size()]);
    VOQuery<PraybillItemVO> voQuery =
        new VOQuery<PraybillItemVO>(PraybillItemVO.class);
    PraybillItemVO[] oldVOArray = voQuery.query(bidArray);
    // У������Դ�빺���з��Ѿ�������������
    this.checkHasSaOrder(pk_num_map, oldVOArray);
    // У����Դ�빺�����Ƿ��Ѿ������������ε���
    this.checkHasNextOrder(pk_num_map, oldVOArray);
    SqlBuilder sql = new SqlBuilder();
    sql.append("UPDATE po_praybill_b  set ");
    sql.append("bisgensaorder", UFBoolean.TRUE);
    sql.append(" where ");
    // ��ʱ��
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_20_03.name());
    sql.append(builder.buildSQL("pk_praybill_b", bidArray));
    new DataAccessUtils().update(sql.toString());
  }

  /**
   * ��д�빺������"��������������"�ֶ�
   * 
   * @param saDelPks ����������д�빺��,����ɾ�����빺����pks
   */
  private void writeBackisSaOrderDel(ArrayList<String> saDelPks) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("UPDATE po_praybill_b set ");
    sql.append("bisgensaorder", UFBoolean.FALSE);
    sql.append(" where ");
    // ��ʱ��
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_20_14.name());
    sql.append(builder.buildSQL("pk_praybill_b",
        saDelPks.toArray(new String[saDelPks.size()])));
    new DataAccessUtils().update(sql.toString());
  }

  /**
   * �ж���Դ�빺���С������������������Ƿ�Ϊ�ǣ����Ϊ�ǣ��򱨴�XX��������Դ�빺�������ɹ������������������ٴ�����
   * 
   * @param pk_num_map Map<����������pk,�����������к�>
   * @param oldVOArray ����������д�빺����pks��Ӧ�����ݿ��е�ԭʼPraybillItemVO
   */
  private void checkHasSaOrder(Map<String, String> pk_num_map,
      PraybillItemVO[] oldVOArray) {
    // ��¼��Դ�빺���С�����������������Ϊ�ǵ������������к�
    ArrayList<String> rowNums = new ArrayList<String>();
    for (PraybillItemVO bvo : oldVOArray) {
      // �Ѿ�������������
      if (bvo.getBisgensaorder().booleanValue()) {
        rowNums.add(pk_num_map.get(bvo.getPk_praybill_b()));
      }
    }
    if (rowNums.size() > 0) {
      StringBuilder sbMsg = new StringBuilder();
      for (String str : rowNums) {
        sbMsg.append("[");
        sbMsg.append(str);
        sbMsg.append("]");
      }
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004020_0", "04004020-0104", null,
              new String[] {
                sbMsg.toString()
              })/* @res "{0}��������Դ�빺�������ɹ������������������ٴ����ɡ�\n" */);
    }
  }

  /**
   * �ж���Դ�빺�����Ƿ��Ѿ������������ε��ݣ�����Ѿ����ɣ�����ʱ����XX��������Դ�빺�����������ε���
   * 
   * @param pk_num_map Map<����������pk,�����������к�>
   * @param oldVOArray ����������д�빺����pks��Ӧ�����ݿ��е�ԭʼPraybillItemVO
   */
  private void checkHasNextOrder(Map<String, String> pk_num_map,
      PraybillItemVO[] oldVOArray) {
    // ��¼��Դ�빺�����Ѿ������������ε��� �������������к�
    ArrayList<String> rowNums = new ArrayList<String>();
    for (PraybillItemVO bvo : oldVOArray) {
      // �Ѿ����ɼ۸�������,ѯ���۵�,�ɹ���������ί�ⶩ��,��������������
      if (this.isIntUPZero(bvo.getNpriceauditbill())
          || this.isIntUPZero(bvo.getNquotebill())
          || this.isUFDoubleUPZero(bvo.getNaccumulatenum())
          || bvo.getBpublishtoec().booleanValue()) {
        rowNums.add(pk_num_map.get(bvo.getPk_praybill_b()));
      }
    }
    if (rowNums.size() > 0) {
      StringBuilder sbMsg = new StringBuilder();
      for (String str : rowNums) {
        sbMsg.append("[");
        sbMsg.append(str);
        sbMsg.append("]");
      }
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004020_0", "04004020-0105", null,
              new String[] {
                sbMsg.toString()
              })/* @res "{0}��������Դ�빺�����������ε��ݡ�\n" */);
    }
  }

  private boolean isIntUPZero(Integer i) {
    if (null != i && i.intValue() > 0) {
      return true;
    }
    return false;
  }

  private boolean isUFDoubleUPZero(UFDouble d) {
    if (null != d && d.doubleValue() > 0d) {
      return true;
    }
    return false;
  }

  private void upAdd(List<List<Object>> adddata) {
    if (adddata.size() > 0) {
      String addsql =
          "UPDATE po_praybill_b set ngenct = coalesce(ngenct,0) + 1  WHERE pk_praybill_b = ? ";
      new DataAccessUtils().update(addsql, new JavaType[] {
        JavaType.String
      }, adddata);
    }
  }

  private void upDel(List<List<Object>> deldata) {
    if (deldata.size() > 0) {
      String delsql =
          "UPDATE po_praybill_b set ngenct = coalesce(ngenct,0) - 1  WHERE pk_praybill_b = ? ";
      new DataAccessUtils().update(delsql, new JavaType[] {
        JavaType.String
      }, deldata);
    }
  }

  private void upTs(ArrayList<PraybillItemVO> items) {
    if (items.size() == 0) {
      return;
    }

    new VOUpdateTS<PraybillItemVO>().update(items
        .toArray(new PraybillItemVO[items.size()]));
  }
}
