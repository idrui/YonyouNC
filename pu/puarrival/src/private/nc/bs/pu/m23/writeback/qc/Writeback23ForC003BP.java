package nc.bs.pu.m23.writeback.qc;

import nc.bs.pu.m23.writeback.qc.c003rule.ChkWriteParaForC003Rule;
import nc.bs.pu.m23.writeback.qc.c003rule.SynchBtableEligNumRule;
import nc.bs.pu.m23.writeback.qc.c003rule.WriteC005WhenResultFeedbackRule;
import nc.impl.pubapp.pattern.data.vo.VODelete;
import nc.impl.pubapp.pattern.data.vo.VOInsert;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m23.entity.ArriveBbVO;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ʼ챨���д��������BP��(�������ʼ챨���������ʼ챨��ȡ������)
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-9-29 ����09:17:52
 */
public class Writeback23ForC003BP {

  public void writebackWhenApprove(ArriveBbVO[] paras) {

    // ���ִ��ҵ�����
    AroundProcesser<ArriveBbVO> proc = new AroundProcesser<ArriveBbVO>(null);
    this.addBeforeRule(proc, true);
    this.addAfterRule(proc, true);

    proc.before(paras);

    // 2���������ӱ�����
    VOInsert<ArriveBbVO> util = new VOInsert<ArriveBbVO>();
    util.insert(paras);

    proc.after(paras);
  }

  public void writebackWhenUnApprove(ArriveBbVO[] paras) {

    // ���ִ��ҵ�����
    AroundProcesser<ArriveBbVO> proc = new AroundProcesser<ArriveBbVO>(null);
    this.addBeforeRule(proc, false);
    this.addAfterRule(proc, false);

    proc.before(paras);

    // ����������������ѯ���ӱ�����
    String[] reportids =
        (String[]) AggVOUtil.getDistinctFieldArray(paras,
            ArriveBbVO.PK_QCREPORT, String.class);
    if (ArrayUtils.isEmpty(reportids)) {
      String message =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
              "04004040-0109")/* @res "��д�������ʼ���ʱ���ʼ챨�����������Ϊ�գ�" */;
      ExceptionUtils.wrappBusinessException(message);
    }
    SqlBuilder sqlBld = new SqlBuilder();
    sqlBld.append("select pk_arriveorder_bb from po_arriveorder_bb where ");
    // ��ʱ��
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_23_17.name());
    sqlBld.append(builder.buildSQL(ArriveBbVO.PK_QCREPORT, reportids));
    sqlBld.append(" and dr = 0");

    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sqlBld.toString());
    String[] bbids = rowset.toOneDimensionStringArray();
    if (!ArrayUtils.isEmpty(bbids)) {
      // �������ڸ�������ȫ���������ٵ���飬�����Ҳ������������ӱ��Ӧ�ļ�¼.�������Ѿ�ɾ�����ʴ˴���ɾ��Ҳ����
      // ��Ϊ�ж�bbids��Ϊ��ʱ��ȥɾ
      // String message =
      // nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0110")/*@res
      // "��д�������ʼ���ʱ���Ҳ������������ӱ��Ӧ�ļ�¼�����ֲ�����"*/;
      // ExceptionUtils.wrappBusinessException(message);

      // ɾ����Ӧ�����ӱ�����
      VOQuery<ArriveBbVO> util = new VOQuery<ArriveBbVO>(ArriveBbVO.class);
      ArriveBbVO[] bbItems = util.query(bbids);
      new VODelete<ArriveBbVO>().delete(bbItems);
    }

    proc.after(paras);
  }

  private void addAfterRule(AroundProcesser<ArriveBbVO> proc, boolean isApprove) {
    // �����ӱ��кϸ����������ϸ�����
    proc.addAfterRule(new SynchBtableEligNumRule(isApprove));

    // ����д��ɾ�����������ʼ���ʱ����д�������е����Ƿ����ʼ����ı�־
    proc.addAfterRule(new WriteC005WhenResultFeedbackRule(isApprove));
  }

  private void addBeforeRule(AroundProcesser<ArriveBbVO> proc, boolean isApprove) {
    // ����д����
    proc.addBeforeRule(new ChkWriteParaForC003Rule(isApprove));
  }
}
