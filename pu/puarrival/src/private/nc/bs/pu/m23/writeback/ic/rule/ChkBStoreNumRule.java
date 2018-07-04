package nc.bs.pu.m23.writeback.ic.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.MaterialVO;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.pu.m23.entity.ArriveBbVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveViewVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUParaValue.ctrltype;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.pub.rule.ToleranceCalcRule;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * 
 * @description
 * ��д�������ӱ���ۼ���������󣬽�����������ݲ���
 * @scene
 * �ɹ���ⵥ��д
 * @param
 * ��
 *
 * @since 6.3
 * @version 2010-1-11 ����06:43:36
 * @author hanbin
 */

public class ChkBStoreNumRule extends ToleranceCalcRule implements
    IRule<ArriveViewVO> {

  private UFBoolean isUserConfirm;

  private String numfiled;

  private String table = PUEntity.M23_B_TABLE;

  public ChkBStoreNumRule(UFBoolean isUserConfirm) {
    this.isUserConfirm = isUserConfirm;
  }

  @Override
  public String getBidFiled() {
    return ArriveItemVO.PK_ARRIVEORDER_B;
  }

  @Override
  public String getNumField() {
    return this.numfiled;
  }

  @Override
  public String getTable() {
    return this.table;
  }

  @Override
  public void process(ArriveViewVO[] vos) {
    List<ArriveItemVO> arriveitems = new ArrayList<ArriveItemVO>();
    // �����ݼ��������ı�����
    List<String> notStockByCheckList = new ArrayList<String>();
    // ���ݼ��������,�����ʼ�ı�����
    List<String> stockByCheckList = new ArrayList<String>();
    // ���ݼ��������,δ�������,�������������,�ݲ�������ݲ����,����WriteAccumLetgoInnumRule���ϸ��������
    List<String> letgoList = new ArrayList<String>();
    Map<String, ArriveItemVO> arriveitemmap =
        new HashMap<String, ArriveItemVO>();
    for (ArriveViewVO vo : vos) {
      ArriveItemVO item = vo.getBVO();
      arriveitems.add(item);
      arriveitemmap.put(item.getPk_arriveorder_b(), item);
    }
    Map<String, MaterialStockVO> bid_mrlvo_map =
        this.getMrlvoMap(arriveitems.toArray(new ArriveItemVO[arriveitems
            .size()]));

    for (Entry<String, MaterialStockVO> entry : bid_mrlvo_map.entrySet()) {
      String key = entry.getKey();
      MaterialStockVO mrlVO = entry.getValue();
      if (!ValueUtils.getBoolean(mrlVO.getStockbycheck())) {
        notStockByCheckList.add(key);
      }
      else {
        ArriveItemVO arriveitem = arriveitemmap.get(key);
        if (MathTool
            .compareTo(
                MathTool.add(arriveitem.getNelignum(),
                    arriveitem.getNnotelignum()), UFDouble.ZERO_DBL) == 0) {
          letgoList.add(key);
        }
        else {
          stockByCheckList.add(key);
        }
      }
    }

    ctrltype po40 = PUSysParamUtil.getPO40(vos[0].getPk_purchaseorg());
    // ���Ǹ��ݼ��������ı����д���
    this.setTable(PUEntity.M23_B_TABLE);
    this.setNumfiled(ArriveItemVO.NNUM);
    this.toleranceCompare(ArriveItemVO.NACCUMSTORENUM,
        notStockByCheckList.toArray(new String[notStockByCheckList.size()]),
        MaterialVO.INTOLERANCE, po40, this.isUserConfirm);
    // ���ݼ��������ı����д���
    String[] bids =
        stockByCheckList.toArray(new String[stockByCheckList.size()]);
    // ������ݼ����������ݲ��������(�Ӳ�ѯ,������)
    this.setTableByQcResult(bids);
    this.setNumfiled(ArriveBbVO.NNUM);
    this.toleranceCompare(ArriveItemVO.NACCUMSTORENUM, bids,
        MaterialVO.INTOLERANCE, po40, this.isUserConfirm);
    // ���ݼ��������,δ�������,�������������,�ݲ�������ݲ����,����WriteAccumLetgoInnumRule���ϸ��������
  }

  public void setNumfiled(String numfiled) {
    this.numfiled = numfiled;
  }

  public void setTable(String table) {
    this.table = table;
  }

  private Map<String, MaterialStockVO> getMrlvoMap(ArriveItemVO[] bvolist) {
    Map<String, MaterialStockVO> bid_mrlvo_map =
        new HashMap<String, MaterialStockVO>();
    // ���ݱ���Ŀ����֯���ڱ������ݽ��з���
    HashMap<String, ArrayList<ArriveItemVO>> org_bitems =
        CirVOUtil.getFieldVOList(bvolist, ArriveItemVO.PK_ORG);

    String[] fields = new String[1];
    fields[0] = MaterialStockVO.STOCKBYCHECK;// ���ݼ��������
    for (Entry<String, ArrayList<ArriveItemVO>> entry : org_bitems.entrySet()) {
      // ��ѯ���ϵĿ��ҳǩ��Ϣ
      String stock = entry.getKey();
      ArriveItemVO[] items = entry.getValue().toArray(new ArriveItemVO[0]);
      String[] mrlpks =
          CirVOUtil.getDistinctFieldSet(items, ArriveItemVO.PK_MATERIAL)
              .toArray(new String[0]);
      Map<String, MaterialStockVO> map = null;
      map = MaterialPubService.queryMaterialStockInfo(mrlpks, stock, fields);
      if (map == null || map.size() == 0) {
        return null;
      }
      // ���ݲ�ѯ�������Ͽ����Ϣ������ṹ��<�����������������ϵĿ��ҳǩ��Ϣ>
      for (ArriveItemVO item : items) {
        String bid = item.getPk_arriveorder_b();
        MaterialStockVO mrlVO = map.get(item.getPk_material());
        if (mrlVO == null) {
          String msg =
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4004040_0", "04004040-0167")/*
                                                * @res
                                                * "��ѯ���ϵĿ��ҳǩ����ʱ���Ҳ�����Ӧ��������Ϣ��"
                                                */;
          ExceptionUtils.wrappBusinessException(msg);
        }
        bid_mrlvo_map.put(bid, mrlVO);
      }
    }
    return bid_mrlvo_map;
  }

  private void setTableByQcResult(String[] bids) {
    if (bids.length == 0) {
      return;
    }
    IDExQueryBuilder iq = new IDExQueryBuilder(PUTempTable.tmp_po_23_02.name());
    SqlBuilder sql = new SqlBuilder();
    sql.startParentheses();
    sql.append("select b." + ArriveItemVO.PK_ARRIVEORDER_B);
    sql.append(",b." + ArriveItemVO.PK_MATERIAL);
    // wangljc modify at 2011-11-3 11:23:48
    sql.append(",max(isnull(b." + ArriveItemVO.NACCUMSTORENUM + ",0)) "
        + ArriveItemVO.NACCUMSTORENUM);
    sql.append(",sum(isnull(bb." + ArriveBbVO.NNUM + ",0)) " + ArriveBbVO.NNUM);
    sql.append(" from ");
    sql.append(PUEntity.M23_B_TABLE + " b inner join ");
    sql.append(PUEntity.M23_BB_TABLE + " bb on ");
    sql.append("b." + ArriveItemVO.PK_ARRIVEORDER_B + "=bb.");
    sql.append(ArriveBbVO.PK_ARRIVEORDER_B);
    sql.append(" where b.dr=0 and bb.dr=0 and ");
    sql.append(iq.buildAnotherSQL("b." + ArriveItemVO.PK_ARRIVEORDER_B, bids));
    sql.append(" and bb." + ArriveBbVO.BCANSTORE, UFBoolean.TRUE);
    sql.append(" group by b." + ArriveItemVO.PK_ARRIVEORDER_B);
    sql.append("," + ArriveItemVO.PK_MATERIAL);
    sql.endParentheses();
    this.setTable(sql.toString());
  }
}
