package nc.impl.pu.m23.qc.action.rule;

import java.util.ArrayList;
import java.util.Map;

import nc.bs.pu.m23.writeback.qc.Writeback23ForC003BP;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.qc.c001.pu.ReturnObjectFor23;
import nc.vo.pu.m23.entity.ArriveBbVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.qc.pub.enumeration.StrictLevelEnum;
import nc.vo.qc.pub.util.QCSysParamUtil;

/**
 * 
 * @description
 * 设置到货单的子子表VO，用于存放质检结果数据
 * @scene
 * 
 * @param
 * ReturnObjectFor23 ：rof
 *
 * @since 6.3
 * @version 2011-7-23 下午02:13:47
 * @author wangljc
 */

public class InsertArriveBbVoByStrictLevelRule implements IRule<ArriveVO> {

  private ReturnObjectFor23 rof;

  public InsertArriveBbVoByStrictLevelRule(ReturnObjectFor23 rof) {
    this.rof = rof;
  }

  public ReturnObjectFor23 getRof() {
    return this.rof;
  }

  @Override
  public void process(ArriveVO[] vos) {
    String pk_org = null;
    for (int i = 0; i < vos.length; i++) {
      pk_org = vos[i].getHVO().getPk_org();
      if (!SysInitGroupQuery.isQCEnabled()
          || UFBoolean.FALSE.equals(ValueUtils.getUFBoolean(QCSysParamUtil
              .getINI01(pk_org)))) {
        return;
      }
    }

    if (this.getRof() != null) {
      Map<String, Integer> strictMap =
          this.getRof().getCsourcebid_strictlevel();
      VOQuery<ArriveItemVO> vq = new VOQuery<ArriveItemVO>(ArriveItemVO.class);
      ArriveItemVO[] items =
          vq.query(strictMap.keySet().toArray(
              new String[strictMap.keySet().size()]));

      VOUpdate<ArriveItemVO> updater = new VOUpdate<ArriveItemVO>();
      ArrayList<ArriveBbVO> itemList = new ArrayList<ArriveBbVO>();
      ArrayList<ArriveItemVO> arriveitemList = new ArrayList<ArriveItemVO>();
      for (int i = 0; i < items.length; i++) {
        String hid = items[i].getPk_arriveorder();
        String bid = items[i].getPk_arriveorder_b();
        String pk_group = items[i].getPk_group();
        ArriveBbVO arrivebbvo = new ArriveBbVO();
        arrivebbvo.setPk_arriveorder(hid);
        arrivebbvo.setPk_arriveorder_b(bid);
        arrivebbvo.setBcanstore(UFBoolean.TRUE);
        arrivebbvo.setPk_group(pk_group);
        if (strictMap.containsKey(bid)) {
          if (StrictLevelEnum.FREE.value().equals(strictMap.get(bid))) {
            arrivebbvo.setNnum(items[i].getNnum());
            items[i].setNaccumchecknum(items[i].getNnum());
            arrivebbvo.setBeligible(UFBoolean.TRUE);
            itemList.add(arrivebbvo);
            arriveitemList.add(items[i]);
          }
          else if (StrictLevelEnum.PAUSE.value().equals(strictMap.get(bid))) {
            arrivebbvo.setNnum(UFDouble.ZERO_DBL);
            items[i].setNaccumchecknum(items[i].getNnum());
            arrivebbvo.setBeligible(UFBoolean.FALSE);
            itemList.add(arrivebbvo);
            arriveitemList.add(items[i]);
          }
        }
      }
      if (itemList.size() > 0) {
        ArriveItemVO[] arriveitemvos =
            arriveitemList.toArray(new ArriveItemVO[arriveitemList.size()]);
        updater.update(arriveitemvos, new String[] {
          ArriveItemVO.NACCUMCHECKNUM
        });
        ArriveBbVO[] arrivebbvos =
            itemList.toArray(new ArriveBbVO[itemList.size()]);
        // 不调用Writeback23ForC003BP的方法,因为这儿只是单纯的插入孙表,不需要调其中的rule
        new Writeback23ForC003BP().writebackWhenApprove(arrivebbvos);

      }
    }
  }

  public void setRof(ReturnObjectFor23 rof) {
    this.rof = rof;
  }

}
