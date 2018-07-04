package nc.bs.pu.m23.fa.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.ct.uitl.ArrayUtil;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.utils.ArrivePublicUtil;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
/**
 * 
 * @description
 * ����ת������
 * @scene
 * 
 * @param
 * ��
 *
 * @since 6.3
 * @version 2014-10-28 ����2:21:55
 * @author guoyk
 */
public class DealCanStoreNumForFARule implements IRule<ArriveVO> {

  public ArriveVO[] getTransAssetVO(ArriveVO[] vos) {

    List<ArriveItemVO> bvotmplist = new ArrayList<ArriveItemVO>();
    for (ArriveVO vo : vos) {
      for (ArriveItemVO itemVo : vo.getBVO()) {
        bvotmplist.add(itemVo);
      }
    }

    // ��ȡ������������Ե������ӱ�PKΪKey�����������ΪValue��
    Map<String, UFDouble> canStoreNnums = this.getCanStoreNnums(vos);
    Map<String, MaterialStockVO> bidMrlMap =
        ArrivePublicUtil.queryMaterialStockInfo(bvotmplist
            .toArray(new ArriveItemVO[bvotmplist.size()]));
    // Ϊ���е������µ������ӱ��Ͽ����������ֵ��
    for (ArriveVO vo : vos) {
      ArriveItemVO[] itemtransVos = vo.getBVO();
      for (ArriveItemVO itemVO : itemtransVos) {
        MaterialStockVO materialvo =
            bidMrlMap.get(itemVO.getPk_arriveorder_b());
        boolean bstockbycheck =
            ValueUtils.getBoolean(materialvo.getStockbycheck());
        boolean chkfreeflag =
            ValueUtils.getBoolean(materialvo.getChkfreeflag());
        // ������ʼ�����⣬���ҷ���졣
        if (bstockbycheck && !chkfreeflag) {
          // ����ϸ� + ���ϸ����� > 0����ת����Ҫ�ÿ�����������㣬����Ļ������õ���������
          if (MathTool.compareTo(
              MathTool.add(itemVO.getNelignum(), itemVO.getNnotelignum()),
              UFDouble.ZERO_DBL) > 0) {
            // ���ڻ��ڵ������˻��ĳ��������������� - �ۼ��˻��������ڿ��������ʱ��ȡ���������������ȡ�������� - �ۼ��˻�������
            UFDouble canStoreNum =
                canStoreNnums.get(itemVO.getPk_arriveorder_b());
            UFDouble remainNum =
                MathTool.sub(itemVO.getNnum(), itemVO.getNaccumbacknum());
            if (MathTool.greaterThan(remainNum, canStoreNum)) {
              itemVO.setNnum(canStoreNum);
            }
            else {
              itemVO.setNnum(remainNum);
            }
          }
          // �ϸ������Ͳ��ϸ�������Ӳ�����0��˵��û�м��飬�׳��쳣��
          else {
            ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4004040_0", "04004040-0009")/*
                                                                         * @res
                                                                         * "��������"
                                                                         */
                + itemVO.getCrowno()
                + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                    "4004040_0", "04004040-0013")/* @res "���Ϸ���죬���ȼ���" */);
          }
        }
        // ���ڻ��ڵ������˻��ĳ������ۼ��ۼ��˻�����
        else {
          itemVO.setNnum(MathTool.sub(itemVO.getNnum(),
              itemVO.getNaccumbacknum()));
        }
      }
    }
    return vos;
  }

  @Override
  public void process(ArriveVO[] vos) {
    if (ArrayUtil.isEmpty(vos)) {
      return;
    }
    this.getTransAssetVO(vos);
  }

  /**
   * ��ȡ������������ڲ����豸��Ƭ��ʱ�򣬸��ݿ��������������
   * 
   * @param vo ������VO����Ҫ���л�ȡ������PK��
   * @return �����������п���������ļ��� ���Ե������ӱ�PKΪKey�����������ΪValue��
   */
  private Map<String, UFDouble> getCanStoreNnums(ArriveVO[] vos) {
    String[] arriveOrderPks = new String[vos.length];
    for (int i = 0; i < vos.length; i++) {
      arriveOrderPks[i] = vos[i].getHVO().getPk_arriveorder();
    }

    StringBuilder sqlBuilder = new StringBuilder();
    sqlBuilder.append(" select pk_arriveorder_b, sum(nnum) as nnum ");
    sqlBuilder.append(" from po_arriveorder_bb ");
    sqlBuilder.append(" where ");
    sqlBuilder.append(new IDExQueryBuilder(PUTempTable.tmp_po_23_01.name())
        .buildSQL("pk_arriveorder", arriveOrderPks));
    sqlBuilder.append(" and bcanstore = '");
    sqlBuilder.append(UFBoolean.TRUE.toString());
    sqlBuilder.append("' and dr = 0 group by pk_arriveorder_b ");

    Map<String, UFDouble> canStoreNnums = new HashMap<String, UFDouble>();
    IRowSet rowset = new DataAccessUtils().query(sqlBuilder.toString());
    while (rowset.next()) {
      canStoreNnums.put(rowset.getString(0), rowset.getUFDouble(1));
    }
    return canStoreNnums;
  }
}
