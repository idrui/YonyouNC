package nc.ui.pu.m23.editor.card.beforeedit;

import java.util.Map;

import nc.ui.pu.m23.editor.card.beforeedit.body.AstUnit;
import nc.ui.pu.m23.editor.card.beforeedit.body.BatchCode;
import nc.ui.pu.m23.editor.card.beforeedit.body.ChangeRate;
import nc.ui.pu.m23.editor.card.beforeedit.body.Material;
import nc.ui.pu.m23.editor.card.beforeedit.body.NeverEditBodyItem;
import nc.ui.pu.m23.editor.card.beforeedit.body.NumHandler;
import nc.ui.pu.m23.editor.card.beforeedit.body.PresentFlag;
import nc.ui.pu.m23.editor.card.beforeedit.body.Project;
import nc.ui.pu.m23.editor.card.beforeedit.body.Rack;
import nc.ui.pu.m23.editor.card.beforeedit.body.ReceiveStore;
import nc.ui.pu.m23.editor.card.beforeedit.body.Reporter;
import nc.ui.pu.pub.editor.card.beforeedit.Casscustid;
import nc.ui.pu.pub.editor.card.beforeedit.ProjectTaskId;
import nc.ui.pu.pub.editor.card.handler.AbstractCardBodyBeforeEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.uif2.LoginContext;

/**
 * <p>
 * <b>����������༭�¼��Ĵ����࣬������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����༭ǰ�¼�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-24 ����05:00:09
 */
public class CardBodyBeforeEditEventHandler extends
    AbstractCardBodyBeforeEditEventHandler {

  private LoginContext context;

  public LoginContext getContext() {
    return this.context;
  }

  @Override
  public void registerEventListener(
      Map<String, ICardBodyBeforeEditEventListener> listenerMap) {

    // ����
    listenerMap.put(ArriveItemVO.PK_MATERIAL, new Material());
    // ��λ
    listenerMap.put(ArriveItemVO.CASTUNITID, new AstUnit());
    // ������
    listenerMap.put(ArriveItemVO.CREPORTERID, new Reporter());
    // ��������������������
    NumHandler numHandler = new NumHandler();
    listenerMap.put(ArriveItemVO.NNUM, numHandler);
    listenerMap.put(ArriveItemVO.NASTNUM, numHandler);
    // ������
    listenerMap.put(ArriveItemVO.VCHANGERATE, new ChangeRate());
    // ���κ�
    listenerMap.put(ArriveItemVO.VBATCHCODE, new BatchCode());
    // �Ƿ���Ʒ
    listenerMap.put(ArriveItemVO.BPRESENT, new PresentFlag());
    // �ջ��ֿ�
    listenerMap.put(ArriveItemVO.PK_RECEIVESTORE, new ReceiveStore());

    listenerMap.put(ArriveItemVO.CPROJECTID, new Project());
    // ��λ
    listenerMap.put(ArriveItemVO.PK_RACK, new Rack());

    // ������༭�ֶ�
    NeverEditBodyItem neverEditItem = new NeverEditBodyItem();
    // �Ƿ�̶�������
    listenerMap.put(ArriveItemVO.BFIXEDRATE, neverEditItem);
    // �˻��Ƿ����ԭ��������
    listenerMap.put(ArriveItemVO.BBACKREFORDER, neverEditItem);
    // ��Դ�������Ƿ���Ʒ
    
    listenerMap.put(ArriveItemVO.FPRODUCTCLASS, neverEditItem);
    listenerMap.put(ArriveItemVO.BPRESENTSOURCE, neverEditItem);
    listenerMap.put(ArriveItemVO.NPRESENTASTNUM, neverEditItem);
    listenerMap.put(ArriveItemVO.NPRESENTNUM, neverEditItem);
    listenerMap.put(ArriveItemVO.NTAXRATE, neverEditItem);
    listenerMap.put(ArriveItemVO.PK_REQSTOORG, neverEditItem);
    listenerMap.put(ArriveItemVO.PK_REQSTORE, neverEditItem);
    listenerMap.put(ArriveItemVO.PK_APFINANCEORG, neverEditItem);
    listenerMap.put(ArriveItemVO.PK_APFINANCEORG_V, neverEditItem);
    listenerMap.put(ArriveItemVO.PK_PSFINANCEORG, neverEditItem);
    listenerMap.put(ArriveItemVO.PK_PSFINANCEORG_V, neverEditItem);
    listenerMap.put(ArriveItemVO.DINVALIDDATE, neverEditItem);
    listenerMap.put(ArriveItemVO.NEXCHANGERATE, neverEditItem);
    // ������
    listenerMap.put(ArriveItemVO.CFFILEID, neverEditItem);

    // ��Ŀ����
    listenerMap.put(ArriveItemVO.CPROJECTTASKID, new ProjectTaskId());
    // �ͻ�
    listenerMap.put(ArriveItemVO.CASSCUSTID, new Casscustid());
  }

  public void setContext(LoginContext context) {
    this.context = context;
  }
}
