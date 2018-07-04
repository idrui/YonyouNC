package nc.bs.pu.m23.writeback.pu.m21.rule;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m23.pu.m21.IWriteback23For21Para;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveViewVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * 
 * @description
 * 更新视图VO中的累计补货数量
 * @scene
 * 采购订单补货后回写
 * @param
 * 无
 *
 * @since 6.3
 * @version 2010-1-11 下午06:43:36
 * @author hanbin
 */

public class UpdateViewVOReplNumRule implements IRule<ArriveViewVO> {

  // 采购订单补货的回写到货单时的参数类
  private IWriteback23For21Para[] paraArray;

  public UpdateViewVOReplNumRule(IWriteback23For21Para[] paraArray) {
    this.paraArray = paraArray;
  }

  @Override
  public void process(ArriveViewVO[] viewVOArray) {
    // <到货单行ID = 回写参数类对象>
    Map<String, IWriteback23For21Para> bidToParaMap =
        new HashMap<String, IWriteback23For21Para>();
    for (IWriteback23For21Para para : this.paraArray) {
      bidToParaMap.put(para.getBID(), para);
    }

    for (ArriveViewVO view : viewVOArray) {
      ArriveItemVO item = view.getBVO();
      IWriteback23For21Para para = bidToParaMap.get(item.getPrimaryKey());

      // 增量累计补货数量
      UFDouble diffStoreNum = para.getDiffNum();

      // 持久化的补货数量 = 旧的累计补货数量 + 增量累计补货数量
      UFDouble accumReplNum =
          MathTool.add(item.getNaccumreplnum(), diffStoreNum);
      item.setNaccumreplnum(accumReplNum);
    }
  }
}
