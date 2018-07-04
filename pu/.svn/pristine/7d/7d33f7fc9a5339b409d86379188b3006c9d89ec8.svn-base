package nc.bs.pu.m23.writeback.qc.c001rule;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m23.qc.Writeback23ForC001Para;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveViewVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>更新视图VO中的累计报检数量
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-11 下午06:43:36
 */
public class UpdateViewCheckNumRule implements IRule<ArriveViewVO> {

  private Writeback23ForC001Para[] paraArray;

  public UpdateViewCheckNumRule(Writeback23ForC001Para[] paraArray) {
    this.paraArray = paraArray;
  }

  @Override
  public void process(ArriveViewVO[] viewVOArray) {
    // <到货单行ID = 回写参数类对象>
    Map<String, Writeback23ForC001Para> bidToParaMap =
        new HashMap<String, Writeback23ForC001Para>();
    for (Writeback23ForC001Para para : this.paraArray) {
      bidToParaMap.put(para.getBID(), para);
    }

    for (ArriveViewVO view : viewVOArray) {
      ArriveItemVO item = view.getBVO();
      Writeback23ForC001Para para = bidToParaMap.get(item.getPrimaryKey());

      // 增量累计报检数量
      UFDouble diffStoreNum = para.getDiffNum();

      // 持久化的报检数量 = 旧的累计报检数量 + 增量累计报检数量
      UFDouble accumStoreNum =
          MathTool.add(item.getNaccumchecknum(), diffStoreNum);
      item.setNaccumchecknum(accumStoreNum);
    }
  }
}
