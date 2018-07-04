package nc.pubimpl.pu.m20.aim.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.bs.pu.m20.maintain.PraybillUpdateBP;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.entity.PraybillViewVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.ArrayUtils;

/**
 * �ʲ���������ȡ������ʱɾ�������빺��
 * 
 * @since 6.5
 * @version 2014-2-14 ����01:59:47
 * @author fanly3
 */
public class Delete20For4A08Action {
  // key=��ͷpk��value=aggvo
  Map<String, PraybillVO> aggMap = new HashMap<String, PraybillVO>();

  // key=��ͷpk��value=aggvo
  Map<String, PraybillVO> origAggMap = new HashMap<String, PraybillVO>();

  public void delete(String[] pk_table, String[] pk_table_bs) {
    if (ArrayUtils.isEmpty(pk_table) || ArrayUtils.isEmpty(pk_table_bs)) {
      return;
    }

    PraybillVO[] vos = this.queryAggVos(pk_table, pk_table_bs);
    if (vos == null || vos.length < 1) {
      return;
    }
    // ���ಢ�ҳ�ʼ��aggMap
    for (PraybillVO vo : vos) {
      PraybillHeaderVO headVo = vo.getHVO();
      this.aggMap.put(headVo.getPk_praybill(), vo);
      if (POEnumBillStatus.FREE.toIntValue()!= headVo.getFbillstatus().intValue()) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004020_0", "04004020-0111"));/*
                                                                       * @res
                                                                       * "���δ��ڷ�����̬���빺�������ʲ��������벻��ȡ��������"
                                                                       */

      }
    }

    // ɾ���빺��
    this.deletePraybills();
  }

  private void deletePraybills() {
    // ���ݱ�ͷ��������ѯ����ԭʼvos
    PraybillVO[] origAggVos = this.queryOrigAggVos();
    if (origAggVos == null || origAggVos.length < 1) {
      return;
    }
    // ��ʼ��ԭʼorigAggMap
    for (PraybillVO vo : origAggVos) {
      this.origAggMap.put(vo.getHVO().getPk_praybill(), vo);
    }

    // ������ɾ���ľۺ�VO
    List<PraybillVO> lineDel = new ArrayList<PraybillVO>();
    // ��������ɾ���ľۺ�VO
    List<PraybillVO> entireDel = new ArrayList<PraybillVO>();
    for (Entry<String, PraybillVO> entry : this.aggMap.entrySet()) {
      String key = entry.getKey();
      int newItemCount = this.aggMap.get(key).getBVO().length;
      int origItemCount = this.origAggMap.get(key).getBVO().length;
      if (newItemCount < origItemCount) {
        // ɾ����
        lineDel.add(this.aggMap.get(key));
      }
      else if (newItemCount == origItemCount) {
        // ����ɾ��
        entireDel.add(this.aggMap.get(key));
      }
    }

    if (!lineDel.isEmpty()) {
      // ��֤���������оۺ�vo��˳��
      PraybillVO[] origLineDel = new PraybillVO[lineDel.size()];
      PraybillVO[] newLineDel = new PraybillVO[lineDel.size()];
      // key=�ۺ�VO����pk��value�洢���Ǹþۺ�vo�ӱ�pk
      MapSet<String, String> mapSet = new MapSet<String, String>();
      for (int i = 0; i < lineDel.size(); i++) {
        String key = lineDel.get(i).getHVO().getPk_praybill();
        origLineDel[i] = this.origAggMap.get(key);
        newLineDel[i] = this.aggMap.get(key);
        PraybillItemVO[] newItemVOs = this.aggMap.get(key).getBVO();
        for (PraybillItemVO itemVo : newItemVOs) {
          mapSet.put(key, itemVo.getPk_praybill_b());
        }
      }
      // �����빺����״̬�Ͳ�ȫ��
      for (int i = 0; i < newLineDel.length; i++) {
        List<PraybillItemVO> newBodyList = new ArrayList<PraybillItemVO>();
        PraybillItemVO[] newBodyItems = newLineDel[i].getBVO();
        for (PraybillItemVO itemVo : newBodyItems) {
          itemVo.setStatus(VOStatus.DELETED);
          newBodyList.add(itemVo);
        }
        String key = newLineDel[i].getHVO().getPk_praybill();
        PraybillItemVO[] origBodyItems = origLineDel[i].getBVO();
        for (PraybillItemVO itemVO : origBodyItems) {
          if (!mapSet.get(key).contains(itemVO.getPk_praybill_b())) {
            PraybillItemVO cloneItem = (PraybillItemVO) itemVO.clone();
            cloneItem.setStatus(VOStatus.UNCHANGED);
            newBodyList.add(cloneItem);
          }
        }
        newLineDel[i].getHVO().setStatus(VOStatus.UPDATED);
        newLineDel[i].setBVO(newBodyList.toArray(new PraybillItemVO[newBodyList
            .size()]));
      }

      new PraybillUpdateBP().update(newLineDel, origLineDel, false);
    }

    if (!entireDel.isEmpty()) {
      PraybillVO[] aggVos = entireDel.toArray(new PraybillVO[entireDel.size()]);
      new PraybillDeleteFor4A08BP().delete(aggVos);
    }
  }

  /**
   * ������Դ����pk����Դ�ӱ�pk,�������Ҫɾ�����빺���ۺ�VO
   * 
   * @param pk_table �ʲ�������������pks
   * @param pk_table_bs �ʲ����������ӱ�pks
   * @return ��Ӧ���빺���ۺ�VO
   */
  private PraybillVO[] queryAggVos(String[] pk_table, String[] pk_table_bs) {
    String[] bids = this.queryPraybillBids(pk_table, pk_table_bs);
    ViewQuery<PraybillViewVO> query =
        new ViewQuery<PraybillViewVO>(PraybillViewVO.class);
    PraybillViewVO[] viewVos = query.query(bids);

    if (ArrayUtils.isEmpty(viewVos)) {
      return null;
    }
    return PraybillViewVO.getPraybillVO(viewVos);
  }

  /**
   * ���ݱ�ͷpk��ѯ���ݿ��ж�Ӧ��ԭʼ�ۺ�vo
   * 
   * @return ԭʼ���빺���ۺ�vo
   */
  private PraybillVO[] queryOrigAggVos() {
    // ���ݱ�ͷ��������ѯ����vo
    BillQuery<PraybillVO> billQuery =
        new BillQuery<PraybillVO>(PraybillVO.class);
    PraybillVO[] origAggVos =
        billQuery.query(this.aggMap.keySet().toArray(
            new String[this.aggMap.keySet().size()]));
    return origAggVos;
  }

  /**
   * ��ѯ�빺���ӱ�pk
   * 
   * @param pk_table �ʲ�������������pk
   * @param pk_table_bs �ʲ����������ӱ�pk
   * @return �빺���ӱ�����
   */
  private String[] queryPraybillBids(String[] pk_table, String[] pk_table_bs) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select ");
    sql.append(PraybillItemVO.PK_PRAYBILL_B);
    sql.append(" from ");
    sql.append(PUEntity.M20_B_TABLE);
    sql.append(" where ");
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_20_16.name());
    sql.append(builder.buildSQL(PUEntity.M20_B_TABLE + "."
        + PraybillItemVO.CSOURCEID, pk_table));
    sql.append(" and ");
    IDExQueryBuilder builder2 =
        new IDExQueryBuilder(PUTempTable.tmp_po_20_17.name());
    sql.append(builder2.buildSQL(PUEntity.M20_B_TABLE + "."
        + PraybillItemVO.CSOURCEBID, pk_table_bs));
    sql.append(" and ");
    sql.append(PUEntity.M20_B_TABLE + ".dr", 0);

    DataAccessUtils utils = new DataAccessUtils();
    String[] bids = utils.query(sql.toString()).toOneDimensionStringArray();
    return bids;
  }
}
