package nc.ui.pu.m20.editor.card.beforeedit;

import java.util.Map;

import nc.ui.pu.m20.editor.card.beforeedit.header.Plandept;
import nc.ui.pu.m20.editor.card.beforeedit.header.Planpsn;
import nc.ui.pu.m20.editor.card.beforeedit.header.Project;
import nc.ui.pu.m20.editor.card.beforeedit.header.ScType;
import nc.ui.pu.m20.editor.card.beforeedit.header.Trantypecode;
import nc.ui.pu.pub.editor.card.handler.AbstractCardHeadTailBeforeEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.vo.pu.m20.entity.PraybillHeaderVO;

/**
 * <p>
 * <b>请购单表头编辑事件的代理处理类，本类主要完成以下功能：</b>
 * <ul>
 * <li>注册编辑前事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-1-24 下午05:00:09
 */
public class HanderBeforeEditHandler extends
    AbstractCardHeadTailBeforeEditEventHandler {

  @Override
  public void registerEventListener(
      Map<String, ICardHeadTailBeforeEditEventListener> listenerMap) {

    // 请购类型
    listenerMap.put(PraybillHeaderVO.CTRANTYPEID, new Trantypecode());

    // 计划部门
    listenerMap.put(PraybillHeaderVO.PK_PLANDEPT, new Plandept());
    listenerMap.put(PraybillHeaderVO.PK_PLANDEPT_V, new Plandept());

    // 计划员
    listenerMap.put(PraybillHeaderVO.PK_PLANPSN, new Planpsn());

    // 项目
    listenerMap.put(PraybillHeaderVO.CHPROJECTID, new Project());
    // 是否委外
    listenerMap.put(PraybillHeaderVO.BSCTYPE, new ScType());

  }

}
