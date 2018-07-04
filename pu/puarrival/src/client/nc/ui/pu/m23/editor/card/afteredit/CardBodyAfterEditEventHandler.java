package nc.ui.pu.m23.editor.card.afteredit;

import java.util.Map;

import nc.ui.pu.m23.editor.card.afteredit.body.AstUnit;
import nc.ui.pu.m23.editor.card.afteredit.body.BatchCode;
import nc.ui.pu.m23.editor.card.afteredit.body.ChangeRate;
import nc.ui.pu.m23.editor.card.afteredit.body.Material;
import nc.ui.pu.m23.editor.card.afteredit.body.Num;
import nc.ui.pu.m23.editor.card.afteredit.body.PresentFlag;
import nc.ui.pu.m23.editor.card.afteredit.body.ProduceDate;
import nc.ui.pu.m23.editor.card.afteredit.body.ReceiveStore;
import nc.ui.pu.m23.editor.card.afteredit.body.RelationCalculate;
import nc.ui.pu.pub.editor.card.afteredit.CProject;
import nc.ui.pu.pub.editor.card.handler.AbstractCardBodyAfterEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.AbstractRelationCalculateListener;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;
import nc.vo.uif2.LoginContext;

/**
 * <p>
 * <b>到货单表体编辑事件的处理类，本类主要完成以下功能：</b>
 * <ul>
 * <li>卡片表体编辑后事件处理类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-24 下午05:00:09
 */
public class CardBodyAfterEditEventHandler extends
    AbstractCardBodyAfterEditEventHandler {

  private RelationCalculate calculate;

  private LoginContext context;

  @Override
  public AbstractRelationCalculateListener getCalculateListener() {
    if (this.calculate == null) {
      this.calculate = new RelationCalculate();
    }
    return this.calculate;
  }

  public LoginContext getContext() {
    return this.context;
  }

  @Override
  public void registerEventListener(
      Map<String, ICardBodyAfterEditEventListener> listenerMap) {

    // 行号
    // listenerMap.put(ArriveItemVO.CROWNO, new RowNo());
    // 物料
    listenerMap.put(ArriveItemVO.PK_MATERIAL, new Material());
    // 单位
    listenerMap.put(ArriveItemVO.CASTUNITID,
        new AstUnit(this.getCalculateListener()));

    // 换算率
    listenerMap.put(ArriveItemVO.VCHANGERATE, new ChangeRate());

    Num num = new Num();
    // 到货、应到、途耗、赠品数量和主数量
    listenerMap.put(ArriveItemVO.NNUM, num);
    listenerMap.put(ArriveItemVO.NASTNUM, num);
    listenerMap.put(ArriveItemVO.NPLANNUM, num);
    listenerMap.put(ArriveItemVO.NPLANASTNUM, num);
    listenerMap.put(ArriveItemVO.NWASTNUM, num);
    listenerMap.put(ArriveItemVO.NWASTASTNUM, num);
    listenerMap.put(ArriveItemVO.NPRESENTNUM, num);
    listenerMap.put(ArriveItemVO.NPRESENTASTNUM, num);

    // 生产日期、保质期天数
    ProduceDate produceDateHandler = new ProduceDate();
    listenerMap.put(ArriveItemVO.DPRODUCEDATE, produceDateHandler);
    listenerMap.put(ArriveItemVO.IVALIDDAY, produceDateHandler);
    // 批次号
    listenerMap.put(ArriveItemVO.VBATCHCODE, new BatchCode());
    // 是否赠品
    listenerMap.put(ArriveItemVO.BPRESENT, new PresentFlag());

    // 收货仓库
    listenerMap.put(ArriveItemVO.PK_RECEIVESTORE, new ReceiveStore());
    // 项目
    listenerMap.put(PuAttrNameEnum.cprojectid.name(), new CProject());
  }

  public void setContext(LoginContext context) {
    this.context = context;
  }
}
