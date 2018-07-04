package nc.bs.pu.m23.writeback.ic.rule;

import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m23.ic.m45.IWriteback23For45Para;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveViewVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * 
 * @description
 * 更新视图VO中的累计入库数量
 * @scene
 * 采购入库单回写
 * @param
 * paraArray ：采购入库的回写到货单时的参数类
 *
 * @since 6.3
 * @version 2010-1-11 下午06:43:36
 * @author hanbin
 */

public class UpdateViewStoreNumRule implements IRule<ArriveViewVO> {

  // 采购入库的回写到货单时的参数类
  private IWriteback23For45Para[] paraArray;

  public UpdateViewStoreNumRule(IWriteback23For45Para[] paraArray) {
    this.paraArray = paraArray;
  }

  @Override
  public void process(ArriveViewVO[] viewVOArray) {
    // <到货单行ID,回写参数类对象>
    MapList<String, IWriteback23For45Para> bidToParaMap =
        new MapList<String, IWriteback23For45Para>();
    for (IWriteback23For45Para para : this.paraArray) {
      bidToParaMap.put(para.getBID(), para);
    }
    // 更新视图VO中的累计入库数量
    for (ArriveViewVO view : viewVOArray) {
      ArriveItemVO item = view.getBVO();
      List<IWriteback23For45Para> ps = bidToParaMap.get(item.getPrimaryKey());
      for (IWriteback23For45Para para : ps) {
        // 增量累计入库数量
        UFDouble diffStoreNum = para.getDiffNum();
        // 持久化的入库数量 = 旧的累计入库数量 + 增量累计入库数量
        UFDouble accumStoreNum =
            MathTool.add(item.getNaccumstorenum(), diffStoreNum);
        item.setNaccumstorenum(accumStoreNum);
      }
    }
  }
}
