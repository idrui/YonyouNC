package nc.bs.pu.m23.writeback.qc;

import nc.bs.pu.m23.writeback.qc.c005rule.ChkWriteParaForC005Rule;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubitf.pu.m23.qc.Writeback23ForC005Para;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.utils.ArrivePublicUtil;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;

/**
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
 * @author hanbin
 * @time 2010-9-13 ����02:09:59
 */
public class Writeback23ForC005OpenCloseBP {

  public void writebackWhenClose(Writeback23ForC005Para[] paras) {
    // ��ѯ����������VO
    String[] bidArray = ArrivePublicUtil.getWriteParaBidArray(paras);
    VOQuery<ArriveItemVO> util1 = new VOQuery<ArriveItemVO>(ArriveItemVO.class);
    ArriveItemVO[] items = util1.query(bidArray);

    // ���ִ��ҵ�����
    AroundProcesser<ArriveItemVO> proc =
        new AroundProcesser<ArriveItemVO>(null);
    this.addBeforeRule(proc, paras);

    proc.before(items);

    // ���г־û�
    for (ArriveItemVO item : items) {
      item.setBletgostate(UFBoolean.FALSE);
      item.setStatus(VOStatus.UPDATED);
    }
    String[] names = new String[1];
    names[0] = ArriveItemVO.BLETGOSTATE;

    VOUpdate<ArriveItemVO> updateUtil = new VOUpdate<ArriveItemVO>();
    updateUtil.update(items, names);

    proc.after(items);
  }

  public void writebackWhenOpen(Writeback23ForC005Para[] paras) {
    // ��ѯ����������VO
    String[] bidArray = ArrivePublicUtil.getWriteParaBidArray(paras);
    VOQuery<ArriveItemVO> util1 = new VOQuery<ArriveItemVO>(ArriveItemVO.class);
    ArriveItemVO[] items = util1.query(bidArray);

    // ���ִ��ҵ�����
    AroundProcesser<ArriveItemVO> proc =
        new AroundProcesser<ArriveItemVO>(null);
    this.addBeforeRule(proc, paras);

    proc.before(items);

    // ���г־û�
    for (ArriveItemVO item : items) {
      item.setBletgostate(UFBoolean.TRUE);
      item.setStatus(VOStatus.UPDATED);
    }
    String[] names = new String[1];
    names[0] = ArriveItemVO.BLETGOSTATE;

    VOUpdate<ArriveItemVO> updateUtil = new VOUpdate<ArriveItemVO>();
    updateUtil.update(items, names);

    proc.after(items);
  }

  private void addBeforeRule(AroundProcesser<ArriveItemVO> proc,
      Writeback23ForC005Para[] paras) {
    // ����д����
    proc.addBeforeRule(new ChkWriteParaForC005Rule(paras));
  }
}
