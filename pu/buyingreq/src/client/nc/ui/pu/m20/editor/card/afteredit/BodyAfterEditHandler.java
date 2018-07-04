package nc.ui.pu.m20.editor.card.afteredit;

import java.util.Map;

import nc.ui.pu.m20.editor.card.afteredit.body.Astunitid;
import nc.ui.pu.m20.editor.card.afteredit.body.BatchCode;
import nc.ui.pu.m20.editor.card.afteredit.body.Material;
import nc.ui.pu.m20.editor.card.afteredit.body.Purchaseorg;
import nc.ui.pu.m20.editor.card.afteredit.body.ReqDate;
import nc.ui.pu.m20.editor.card.afteredit.body.ReqStock;
import nc.ui.pu.m20.editor.card.afteredit.body.Suggestsupplier;
import nc.ui.pu.pub.editor.card.afteredit.CProject;
import nc.ui.pu.pub.editor.card.handler.AbstractCardBodyAfterEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.AbstractRelationCalculateListener;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pubapp.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>请购单表体编辑事件的代理处理类，本类主要完成以下功能：</b>
 * <ul>
 * <li>卡片表体编辑后事件处理类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-1-24 下午05:00:09
 */
public class BodyAfterEditHandler extends AbstractCardBodyAfterEditEventHandler {

  

	@Override
  public AbstractRelationCalculateListener getCalculateListener() {
    return new RelationCalculate();
  }

  @Override
  public void registerEventListener(
      Map<String, ICardBodyAfterEditEventListener> listenerMap) {
    // 行号
    // handlerMap.put(PraybillItemVO.CROWNO, new RowNo());

    // 物料
    listenerMap.put(PraybillItemVO.PK_MATERIAL, new Material());

    // 采购组织
    listenerMap.put(PraybillItemVO.PK_PURCHASEORG_V, new Purchaseorg());

    // 需求日期
    listenerMap.put(PraybillItemVO.DREQDATE, new ReqDate());

    // 供应商
    listenerMap.put(PraybillItemVO.PK_SUGGESTSUPPLIER, new Suggestsupplier());

    // // 主数量
    // listenerMap.put(PraybillItemVO.NNUM, new Num());
    //
    // // 数量
    // listenerMap.put(PraybillItemVO.NASTNUM, new Nastnum());

    // 单位
    listenerMap.put(PraybillItemVO.CASTUNITID, new Astunitid());

    // 需求仓库
    listenerMap.put(PraybillItemVO.PK_REQSTOR, new ReqStock());
    // 项目
    listenerMap.put(PuAttrNameEnum.cprojectid.name(), new CProject());
    // 批次号
    listenerMap.put(PraybillItemVO.VBATCHCODE, new BatchCode());   
 
  }

/* （非 Javadoc）
 * @see nc.ui.pu.pub.editor.card.handler.AbstractCardBodyAfterEditEventHandler#handleAppEvent(nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent)
 */
  /**16-12-23增加**/
	@Override
	public void handleAppEvent(CardBodyAfterEditEvent e) {
		// TODO 自动生成的方法存根
		BillCardPanel bcp = (BillCardPanel) e.getBillCardPanel();
		if("pric_req".equals(e.getKey())){
			int rowno=e.getRow();
			bcp.setBodyValueAt("01",rowno,"sts_req");
			UFDouble pric_req = (UFDouble)bcp.getBodyValueAt(rowno, "pric_req");//获取无税单价
			UFDouble nastnum = (UFDouble)bcp.getBodyValueAt(rowno, "nastnum");//获取数量
			UFDouble pric_req_rat= pric_req.multiply(1.17);//含税单价=无税单价*1.17
			bcp.setBodyValueAt(pric_req_rat,rowno,"pric_req_rat");//含税单价
			bcp.setBodyValueAt(pric_req.multiply(nastnum),rowno,"tot_pric_req");//不含税总价
			bcp.setBodyValueAt(pric_req_rat.multiply(nastnum),rowno,"tot_pric_req_rat");//含税总价
			return;
		}		
		super.handleAppEvent(e);
	}
  
  
}
