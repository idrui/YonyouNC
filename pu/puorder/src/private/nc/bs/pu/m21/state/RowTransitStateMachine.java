package nc.bs.pu.m21.state;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pubapp.pattern.data.IObjectConvert;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.pattern.model.entity.view.IDataView;
import nc.vo.pubapp.pattern.model.tool.VOTool;

/**
 * 
 * @description
 * �е�������״̬������Ҫ����ͬ������״̬����VO��
 * @scene
 * 
 * @param
 * 
 *
 * @since 6.5
 * @version 2015-10-17 ����4:45:04
 * @author luojw
 */
public class RowTransitStateMachine<E extends IDataView, F extends IBill>
    extends TransitStateMachine<E, F> {

  /**
   * ��״̬�����캯��
   * 
   * @param convertor ����ת����
   */
  public RowTransitStateMachine(IObjectConvert<E, F> convertor) {
    super(convertor);
  }

  @Override
  protected void synchronizeData(E[] views, F[] bills) {
    Map<String, E> viewIndex = new HashMap<String, E>();
    for (E view : views) {
      String key = view.getPrimaryKey();
      viewIndex.put(key, view);
    }
    IVOMeta childMeta = bills[0].getMetaData().getChildren()[0];
    for (IBill bill : bills) {
      ISuperVO[] children = bill.getChildren(childMeta);
      for (ISuperVO child : children) {
        String key = child.getPrimaryKey();
        if (viewIndex.containsKey(key)) {
          IDataView view = viewIndex.get(key);
          this.synchronizeData(bill.getParent(), view);
        }
      }
    }
  }

  private void synchronizeData(ISuperVO vo, IDataView view) {
    ISuperVO head = view.getVO(vo.getClass());
    VOTool tool = new VOTool();
    Set<String> set = tool.getDifferentField(vo, head);
    for (String name : set) {
      Object value = vo.getAttributeValue(name);
      head.setAttributeValue(name, value);
    }
  }
}
