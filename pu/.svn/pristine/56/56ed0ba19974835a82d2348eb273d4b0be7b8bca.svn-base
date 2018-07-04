package nc.bs.pu.m23.writeback.ic.rule;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m23.ic.m45.IWriteback23For45Para;
import nc.vo.pu.m23.entity.ArriveBbVO;
import nc.vo.pu.m23.entity.ArriveViewVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 
 * @description
 * 更新到货单子子表的累计入库数量
 * @scene
 * 采购入库单回写
 * @param
 * paras ： 采购入库的回写到货单时的参数类
 *
 * @since 6.3
 * @version 2010-1-11 下午06:43:36
 * @author hanbin
 */

public class UpdateBBStoreNumRule implements IRule<ArriveViewVO> {

  // 采购入库的回写到货单时的参数类
  private IWriteback23For45Para[] paras;

  public UpdateBBStoreNumRule(IWriteback23For45Para[] paras) {
    this.paras = paras;
  }

  @Override
  public void process(ArriveViewVO[] vos) {
    // <到货单子子表ID = 回写参数类对象>
    List<String> bbids = this.getDiffBbidFromPara();
    if ((bbids == null) || (bbids.size() == 0)) {
      return;
    }
    VOQuery<ArriveBbVO> query = new VOQuery<ArriveBbVO>(ArriveBbVO.class);
    ArriveBbVO[] bbItems = query.query(bbids.toArray(new String[0]));

    if (ArrayUtils.isEmpty(bbItems) || (bbids.size() != bbItems.length)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0095")/*@res "回写到货单时子子表不存在对应数据，可能出现并发操作！"*/);
    }
    List<ArriveBbVO> updateBBItems = new ArrayList<ArriveBbVO>();
    for (ArriveBbVO bbItem : bbItems) {
      for (IWriteback23For45Para para : this.paras) {
        if (StringUtils.isEmpty(para.getBBID())) {
          continue;
        }
        if (!bbItem.getPk_arriveorder_bb().equals(para.getBBID())) {
          continue;
        }
        // 持久化的入库数量 = 旧的累计入库数量 + 增量累计入库数量
        UFDouble diffnum = para.getDiffNum();
        UFDouble accumStoreNum =
            MathTool.add(bbItem.getNaccumstorenum(), diffnum);
        bbItem.setNaccumstorenum(accumStoreNum);
      }
      bbItem.setStatus(VOStatus.UPDATED);
      updateBBItems.add(bbItem);

      VOUpdate<ArriveBbVO> updateUtil = new VOUpdate<ArriveBbVO>();
      ArriveBbVO[] bbitems = updateBBItems.toArray(new ArriveBbVO[0]);
      String[] names = new String[1];
      names[0] = ArriveBbVO.NACCUMSTORENUM;
      updateUtil.update(bbitems, names);
    }
  }

  private List<String> getDiffBbidFromPara() {
    List<String> bbids = new ArrayList<String>();
    for (IWriteback23For45Para para : this.paras) {
      if (StringUtils.isEmpty(para.getBBID())) {
        continue;
      }
      if (bbids.contains(para.getBBID())) {
        continue;
      }
      bbids.add(para.getBBID());
    }
    return bbids;
  }
}