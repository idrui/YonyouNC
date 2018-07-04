package nc.bs.pu.m23.writeback.ic.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m23.ic.m45.IWriteback23For45Para;
import nc.pubitf.qc.c005.pu.m23.IWriteBackFor23;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveViewVO;
import nc.vo.pu.m23.utils.ArrivePublicUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.qc.c005.pub.rewrite.WriteBackC005Para;

/**
 * 
 * @description
 * 回写紧急放行单的累计入库数量
 * @scene
 * 采购入库单回写
 * @param
 * paraArray ： 采购入库的回写到货单时的参数类
 *
 * @since 6.3
 * @version 2010-9-28 上午11:11:57
 * @author hanbin
 */

public class WriteAccumLetgoInnumRule implements IRule<ArriveViewVO> {

  // //采购入库的回写到货单时的参数类
  private IWriteback23For45Para[] paraArray;

  public WriteAccumLetgoInnumRule(IWriteback23For45Para[] paraArray) {
    this.paraArray = paraArray;
  }

  @Override
  public void process(ArriveViewVO[] views) {
    // <到货单行ID = 回写参数类对象>
    Map<String, IWriteback23For45Para> bidToParaMap =
        new HashMap<String, IWriteback23For45Para>();
    for (IWriteback23For45Para para : this.paraArray) {
      bidToParaMap.put(para.getBID(), para);
    }
    Map<String, MaterialStockVO> bidMrlVO = this.queryMaterialStockInfo(views);
    List<WriteBackC005Para> paras = new ArrayList<WriteBackC005Para>();
    for (ArriveViewVO view : views) {
      // 非紧急放行状态时不用处理
      if (!ValueUtils.getBoolean(view.getBVO().getBletgostate())) {
        continue;
      }

      ArriveItemVO item = view.getBVO();
      // 增量累计入库数量
      UFDouble diffInNum = bidToParaMap.get(item.getPrimaryKey()).getDiffNum();

      // 删除入库单也要回写
      if (MathTool.compareTo(view.getBVO().getNelignum(), UFDouble.ZERO_DBL) > 0) {
        continue;
      }

      WriteBackC005Para writeC005para = new WriteBackC005Para();
      writeC005para.setPk_passbill(item.getPk_passbill());
      writeC005para.setPk_passbill_b(item.getPk_passbill_b());
      // 持久化的紧急放行入库数量 = 旧的累计紧急放行入库数量 + 增量累计入库数量
      UFDouble letgoinnum = MathTool.add(item.getNaccumletgoinnum(), diffInNum);
      int compare = MathTool.compareTo(letgoinnum, item.getNaccumletgonum());
      if (compare < 0) {
        // 增量紧急放行入库数量 = 增量累计入库数量
        writeC005para.setTotalinnum(diffInNum);
        paras.add(writeC005para);
        item.setNaccumletgoinnum(letgoinnum);// 累计紧急放行入库主数量
        continue;
      }
      boolean bstockbycheck = false;
      MaterialStockVO mrlvo = bidMrlVO.get(item.getPk_arriveorder_b());
      if (mrlvo != null) {
        bstockbycheck = ValueUtils.getBoolean(mrlvo.getStockbycheck());
      }
      if (compare > 0 && bstockbycheck) {
        String msg =
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
                "04004040-0096")/* @res "回写到货单时，出现累计入库数量超过紧急放行数量！" */;
        ExceptionUtils.wrappBusinessException(msg);
      }
      // 增量紧急放行入库数量 = 紧急放行数量-旧的累计紧急放行入库数量
      writeC005para.setTotalinnum(MathTool.sub(item.getNaccumletgonum(),
          item.getNaccumletgoinnum()));
      paras.add(writeC005para);

      // 累计紧急放行入库主数量 = 紧急放行数量
      item.setNaccumletgoinnum(item.getNaccumletgonum());
      // item.setBletgostate(UFBoolean.FALSE);
    }
    // 回写紧急放行单
    this.dowriteLetgo(paras);
  }

  private void dowriteLetgo(List<WriteBackC005Para> paras) {
    // 进行回写紧急放行单
    if (paras == null || paras.size() == 0) {
      return;
    }
    WriteBackC005Para[] ps = paras.toArray(new WriteBackC005Para[0]);
    IWriteBackFor23 service =
        NCLocator.getInstance().lookup(IWriteBackFor23.class);
    try {
      service.writeBackStockInNum(ps);
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
  }

  private Map<String, MaterialStockVO> queryMaterialStockInfo(
      ArriveViewVO[] views) {
    // 批量查询到货单表体的物料所对应的库存组织页签的属性（根据检验结果入库、是否免检）
    if (views == null || views.length == 0) {
      return new HashMap<String, MaterialStockVO>();
    }
    ArriveItemVO[] items = new ArriveItemVO[views.length];
    for (int i = 0, len = items.length; i < len; i++) {
      items[i] = views[i].getBVO();
    }
    return ArrivePublicUtil.queryMaterialStockInfo(items);
  }
}
