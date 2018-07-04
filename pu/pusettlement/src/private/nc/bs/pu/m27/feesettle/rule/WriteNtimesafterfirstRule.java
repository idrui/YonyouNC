package nc.bs.pu.m27.feesettle.rule;

import java.util.List;

import nc.bs.pu.m27.feesettle.util.FeeSettlePrivateUtil;
import nc.bs.pu.m27.feesettle.util.FeeSettleQueryPara;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m27.entity.SettleBillHeaderVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.SettleFeeAllotDetailVO;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * ��д�����̯��ϸ�ĺ����ۼƷ��ý������
 * @scene
 * ȡ�����ý���,���ý���
 * @param
 * bdosettle TRUE:���ý���,FALSE:ȡ������
 *
 * @since 6.3
 * @version 2014-10-22 ����4:03:14
 * @author zhangshqb
 */
public class WriteNtimesafterfirstRule implements IRule<SettleBillVO> {

  // TRUE:���ý���,FALSE:ȡ������
  private boolean bdosettle;

  public WriteNtimesafterfirstRule(boolean bdosettle) {
    this.bdosettle = bdosettle;
  }

  @Override
  public void process(SettleBillVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    // ��ȡ���ý��㵥
    List<SettleBillVO> feevos = FeeSettlePrivateUtil.findNeedFeeSettleVO(vos);
    if (feevos == null || feevos.size() == 0) {
      return;
    }
    // �ӽ��㵥�г�ȡ�����ࡢ�ۿ��෢Ʊ��Ӧ������VID
    FeeSettleQueryPara[] queryParas =
        FeeSettlePrivateUtil.buildFeeSettleQueryPara(feevos);
    if (ArrayUtils.isEmpty(queryParas)) {
      return;
    }
    // �����ѯ�����̯��ϸ����ʱ��
    String tempTname = FeeSettlePrivateUtil.buldTempTable(queryParas);
    String[] stlHIds =
        (String[]) AggVOUtil.getDistinctHeadFieldArray(vos,
            SettleBillHeaderVO.PK_SETTLEBILL, String.class);
    // <��ⵥ����������Ӧ��һ�η��ý�����ϸ�б�>
    this.updateAfterfirsttimeFlag(tempTname, stlHIds);
  }

  private void updateAfterfirsttimeFlag(String tempTname, String[] stlHIds) {
    // ��ѯ���鵥���ӱ�����
    SqlBuilder sql = new SqlBuilder();
    sql.append(" update  po_settle_feeallot ");
    sql.append(" set " + SettleFeeAllotDetailVO.NTIMESAFTERFIRST + "=");
    sql.append(SettleFeeAllotDetailVO.NTIMESAFTERFIRST);
    if (this.bdosettle) {
      sql.append("+1");
    }
    else {
      sql.append("-1");
    }
    sql.append(" where " + SettleFeeAllotDetailVO.PK_SETTLE_FEEALLOT + " in (");
    sql.append(" select bb.pk_settle_feeallot");
    sql.append(" from po_settlebill_b b inner join po_settle_feeallot bb");
    sql.append(" on b.pk_settlebill_b = bb.pk_settlebill_b");
    sql.append(" inner join " + tempTname + " t");
    sql.append(" on b.pk_stock_b = t.id1");
    sql.append(" and bb.pk_srcmaterial = t.id2");
    sql.append(" where b.dr = 0 and bb.dr =0");
    sql.append(" and bestfirstsettle", UFBoolean.TRUE);
    // ���������εĽ��㵥
    String inName = " and b." + SettleBillItemVO.PK_SETTLEBILL;
    // ��Ȼ�����������������VO��������V60���󣬷��ý���ֻ������һ�Ž��㵥�����ﲻ����ʱ��
    sql.appendNot(inName, stlHIds);
    sql.append(")");
    DataAccessUtils utils = new DataAccessUtils();
    utils.update(sql.toString());
  }

}
