package nc.bs.pu.m23.fa.rule;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.aim.EquipToScmService;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;

/**
 * 
 * @description
 * 调用资产提供的删除资产卡片接口
 * @scene
 * 删除资产卡片
 * @param
 * 无
 *
 * @since 6.3
 * @version 2010-5-8 下午04:14:25
 * @author hanbin
 */

public class InvokeDelFAServiceRule implements IRule<ArriveVO> {
  private List<String> crtCardBidList;

  @Override
  public void process(ArriveVO[] voArray) {
    ArriveVO[] vos = this.buildCrtCardVO(voArray);
    this.invoke(vos);
    this.updateBFaFlag(voArray);
  }

  private ArriveVO[] buildCrtCardVO(ArriveVO[] voArray) {
    List<ArriveVO> volist = new ArrayList<ArriveVO>();
    this.crtCardBidList = new ArrayList<String>();
    for (ArriveVO vo : voArray) {
      List<ArriveItemVO> blist = new ArrayList<ArriveItemVO>();
      for (ArriveItemVO item : vo.getBVO()) {
        if (!this.isCanDelCard(item)) {
          continue;
        }
        blist.add(item);
        this.crtCardBidList.add(item.getPk_arriveorder_b());
      }
      if (blist.size() > 0) {
        ArriveVO splitVO = new ArriveVO();
        splitVO.setHVO(vo.getHVO());
        splitVO.setBVO(blist.toArray(new ArriveItemVO[blist.size()]));
        volist.add(splitVO);
      }
    }
    return voArray;
  }

  private void invoke(ArriveVO[] vos) {
    try {
      TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4004040_0", "04004040-0078")/* @res "调用资产提供的删除设备卡片接口" */);
      EquipToScmService.deleteFromArrivalGoods(vos);
      TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4004040_0", "04004040-0079")/* @res "成功调用资产的删除设备卡片接口" */);
    }
    catch (Exception ex) {
      ExceptionUtils.wrappException(ex);
    }
  }

  private boolean isCanDelCard(ArriveItemVO item) {
    return ValueUtils.getBoolean(item.getBfaflag());
  }

  private void updateBFaFlag(ArriveVO[] vos) {
    List<ArriveItemVO> list = new ArrayList<ArriveItemVO>();
    for (ArriveVO vo : vos) {
      for (ArriveItemVO item : vo.getBVO()) {
        if (!this.crtCardBidList.contains(item.getPk_arriveorder_b())) {
          continue;
        }
        item.setBfaflag(UFBoolean.FALSE);
        list.add(item);
      }
    }
    ArriveItemVO[] items = list.toArray(new ArriveItemVO[list.size()]);
    VOUpdate<ArriveItemVO> update = new VOUpdate<ArriveItemVO>();
    update.update(items, new String[] {
      ArriveItemVO.BFAFLAG
    });
  }
}
