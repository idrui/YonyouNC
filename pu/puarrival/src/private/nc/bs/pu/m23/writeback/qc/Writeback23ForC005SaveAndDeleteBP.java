package nc.bs.pu.m23.writeback.qc;

import java.util.ArrayList;
import java.util.List;

import nc.bs.pu.m23.writeback.qc.c005rule.ChkWriteParaForC005Rule;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubitf.pu.m23.qc.Writeback23ForC005Para;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.utils.ArrivePublicUtil;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�������е����桢ɾ��ʱ��д������BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-9-13 ����02:04:07
 */
public class Writeback23ForC005SaveAndDeleteBP {

  public void writebackWhenDelete(Writeback23ForC005Para[] paras) {
    // ��ѯ����������VO
    String[] bidArray = ArrivePublicUtil.getWriteParaBidArray(paras);
    VOQuery<ArriveItemVO> util1 = new VOQuery<ArriveItemVO>(ArriveItemVO.class);
    ArriveItemVO[] oldItems = util1.query(bidArray);

    // ���ִ��ҵ�����
    AroundProcesser<ArriveItemVO> proc =
        new AroundProcesser<ArriveItemVO>(null);
    this.addBeforeRule(proc, paras);
    proc.before(oldItems);

    // ���г־û�
    List<ArriveItemVO> updateitems = new ArrayList<ArriveItemVO>();
    for (ArriveItemVO item : oldItems) {
      if (ValueUtils.getBoolean(item.getBletgostate())) {
        String msg =
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
                "04004040-0111")/* @res "���������ǽ�������״̬��������������е�ɾ����" */;
        ExceptionUtils.wrappBusinessException(msg);
      }
      item.setBletgostate(UFBoolean.FALSE);
      item.setPk_passbill(null);
      item.setPk_passbill_b(null);
      item.setVpassbillcode(null);
      item.setCpassbollrowno(null);
      item.setNaccumletgonum(null);
      item.setStatus(VOStatus.UPDATED);
      updateitems.add(item);
    }
    String[] names = new String[6];
    names[0] = ArriveItemVO.BLETGOSTATE;
    names[1] = ArriveItemVO.PK_PASSBILL_B;
    names[2] = ArriveItemVO.NACCUMLETGONUM;
    names[3] = ArriveItemVO.PK_PASSBILL;
    names[4] = ArriveItemVO.VPASSBILLCODE;
    names[5] = ArriveItemVO.CPASSBOLLROWNO;

    VOUpdate<ArriveItemVO> updateUtil = new VOUpdate<ArriveItemVO>();
    ArriveItemVO[] news = updateitems.toArray(new ArriveItemVO[0]);
    updateUtil.update(news, names);
    proc.after(oldItems);
  }

  public void writebackWhenSave(Writeback23ForC005Para[] paras) {
    // ��ѯ����������VO
    String[] bidArray = ArrivePublicUtil.getWriteParaBidArray(paras);
    VOQuery<ArriveItemVO> util1 = new VOQuery<ArriveItemVO>(ArriveItemVO.class);
    ArriveItemVO[] oldItems = util1.query(bidArray);

    // ���ִ��ҵ�����
    AroundProcesser<ArriveItemVO> proc =
        new AroundProcesser<ArriveItemVO>(null);
    this.addBeforeRule(proc, paras);
    proc.before(oldItems);

    // ���г־û�
    List<ArriveItemVO> updateitems = new ArrayList<ArriveItemVO>();
    for (ArriveItemVO item : oldItems) {
      String bid = item.getPk_arriveorder_b();
      Writeback23ForC005Para para = this.findParaByBid(paras, bid);
      if (ValueUtils.getBoolean(item.getBletgostate())) {
        String msg =
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
                "04004040-0112")/* @res "���������ǽ�������״̬��������������е������޸ģ�" */;
        ExceptionUtils.wrappBusinessException(msg);
      }
      // �־û��Ľ����������� = �ɵ��ۼƽ����������� + �����ۼƽ�����������
      UFDouble newNum =
          MathTool.add(item.getNaccumletgonum(), para.getDiffNum());
      if (newNum.doubleValue() < 0) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004040_0", "04004040-0113")/*
                                                                     * @res
                                                                     * "��д������ʱ�������ۼƽ�����������С��0��"
                                                                     */);
      }
      // ʣ������ = ���������� - �ۼ��˻�������
      UFDouble remainNum =
          MathTool.sub(item.getNnum(), item.getNaccumbacknum());
      if (MathTool.compareTo(newNum, remainNum) > 0) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004040_0", "04004040-0114")/*
                                                                     * @res
                                                                     * "��д������ʱ�������ۼƽ���������������δ�˻��ĵ�����������"
                                                                     */);
      }

      // ֮ǰ�Ĺ�һ�������63һ���ˣ������������̸��ǲ��������еĲ����ˣ��ش�ע��
      item.setBletgostate(UFBoolean.FALSE);
      if (newNum.doubleValue() > 0) {
        item.setPk_passbill(para.getPassHid());
        item.setPk_passbill_b(para.getPassBid());
        item.setVpassbillcode(para.getVpassbillcode());
        item.setCpassbollrowno(para.getCpassbollrowno());
        item.setNaccumletgonum(newNum);
      }
      else {
        // �������е�������ܻ����ɾ�в�������ʱ��Ҫ���������������
        item.setPk_passbill(null);
        item.setPk_passbill_b(null);
        item.setVpassbillcode(null);
        item.setCpassbollrowno(null);
        item.setNaccumletgonum(null);
      }
      item.setStatus(VOStatus.UPDATED);
      updateitems.add(item);
    }
    String[] names = new String[6];
    names[0] = ArriveItemVO.BLETGOSTATE;
    names[1] = ArriveItemVO.PK_PASSBILL_B;
    names[2] = ArriveItemVO.NACCUMLETGONUM;
    names[3] = ArriveItemVO.PK_PASSBILL;
    names[4] = ArriveItemVO.VPASSBILLCODE;
    names[5] = ArriveItemVO.CPASSBOLLROWNO;

    VOUpdate<ArriveItemVO> updateUtil = new VOUpdate<ArriveItemVO>();
    ArriveItemVO[] news = updateitems.toArray(new ArriveItemVO[0]);
    updateUtil.update(news, names);

    proc.after(oldItems);
  }

  private void addBeforeRule(AroundProcesser<ArriveItemVO> proc,
      Writeback23ForC005Para[] paras) {
    // ����д����
    proc.addBeforeRule(new ChkWriteParaForC005Rule(paras, UFBoolean.TRUE));
  }

  private Writeback23ForC005Para findParaByBid(Writeback23ForC005Para[] paras,
      String bid) {
    for (Writeback23ForC005Para para : paras) {
      if (!para.getBID().equals(bid)) {
        continue;
      }
      return para;
    }
    return null;
  }
}
