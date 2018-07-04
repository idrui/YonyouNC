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
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>紧急放行单保存、删除时回写到货单BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-9-13 下午02:04:07
 */
public class Writeback23ForC005SaveAndDeleteBP {

  public void writebackWhenDelete(Writeback23ForC005Para[] paras) {
    // 查询到货单表体VO
    String[] bidArray = ArrivePublicUtil.getWriteParaBidArray(paras);
    VOQuery<ArriveItemVO> util1 = new VOQuery<ArriveItemVO>(ArriveItemVO.class);
    ArriveItemVO[] oldItems = util1.query(bidArray);

    // 添加执行业务规则
    AroundProcesser<ArriveItemVO> proc =
        new AroundProcesser<ArriveItemVO>(null);
    this.addBeforeRule(proc, paras);
    proc.before(oldItems);

    // 进行持久化
    List<ArriveItemVO> updateitems = new ArrayList<ArriveItemVO>();
    for (ArriveItemVO item : oldItems) {
      if (ValueUtils.getBoolean(item.getBletgostate())) {
        String msg =
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
                "04004040-0111")/* @res "到货单行是紧急放行状态，不允许紧急放行单删除！" */;
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
    // 查询到货单表体VO
    String[] bidArray = ArrivePublicUtil.getWriteParaBidArray(paras);
    VOQuery<ArriveItemVO> util1 = new VOQuery<ArriveItemVO>(ArriveItemVO.class);
    ArriveItemVO[] oldItems = util1.query(bidArray);

    // 添加执行业务规则
    AroundProcesser<ArriveItemVO> proc =
        new AroundProcesser<ArriveItemVO>(null);
    this.addBeforeRule(proc, paras);
    proc.before(oldItems);

    // 进行持久化
    List<ArriveItemVO> updateitems = new ArrayList<ArriveItemVO>();
    for (ArriveItemVO item : oldItems) {
      String bid = item.getPk_arriveorder_b();
      Writeback23ForC005Para para = this.findParaByBid(paras, bid);
      if (ValueUtils.getBoolean(item.getBletgostate())) {
        String msg =
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
                "04004040-0112")/* @res "到货单行是紧急放行状态，不允许紧急放行单进行修改！" */;
        ExceptionUtils.wrappBusinessException(msg);
      }
      // 持久化的紧急放行数量 = 旧的累计紧急放行数量 + 增量累计紧急放行数量
      UFDouble newNum =
          MathTool.add(item.getNaccumletgonum(), para.getDiffNum());
      if (newNum.doubleValue() < 0) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004040_0", "04004040-0113")/*
                                                                     * @res
                                                                     * "回写到货单时，出现累计紧急放行数量小于0！"
                                                                     */);
      }
      // 剩余数量 = 到货主数量 - 累计退货主数量
      UFDouble remainNum =
          MathTool.sub(item.getNnum(), item.getNaccumbacknum());
      if (MathTool.compareTo(newNum, remainNum) > 0) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004040_0", "04004040-0114")/*
                                                                     * @res
                                                                     * "回写到货单时，出现累计紧急放行数量大于未退货的到货主数量！"
                                                                     */);
      }

      // 之前改过一般后来和63一致了，出不来差异盘覆盖补丁环境中的补丁了，特此注释
      item.setBletgostate(UFBoolean.FALSE);
      if (newNum.doubleValue() > 0) {
        item.setPk_passbill(para.getPassHid());
        item.setPk_passbill_b(para.getPassBid());
        item.setVpassbillcode(para.getVpassbillcode());
        item.setCpassbollrowno(para.getCpassbollrowno());
        item.setNaccumletgonum(newNum);
      }
      else {
        // 紧急放行单表体可能会进行删行操作，此时需要情况紧急放行数据
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
    // 检查回写参数
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
