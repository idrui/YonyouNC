package nc.ui.pu.est.editor.head;

import nc.ui.pu.est.view.EditorToModelValueSetter;
import nc.ui.pu.est.view.EstEditor;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.list.ListHeadRowChangedEvent;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>列表表头行改变事件监听处理
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-12 上午11:44:09
 */
public class HeadRowChangeHandler implements
    IAppEventHandler<ListHeadRowChangedEvent> {

  private EstEditor editor;

  /**
   * @return editor
   */
  public EstEditor getEditor() {
    return this.editor;
  }

  @Override
  public void handleAppEvent(ListHeadRowChangedEvent e) {
    // 同步model中的数据
    this.synModelBodyValue(e);
  }

  /**
   * @param editor 要设置的 editor
   */
  public void setEditor(EstEditor editor) {
    this.editor = editor;
  }

  /**
   * 方法功能描述：。
   * <p>
   * <b>参数说明</b>
   * 
   * @param e
   *          <p>
   * @since 6.0
   * @author zhyhang
   * @time 2010-6-15 下午02:29:24
   */
  private void synModelBodyValue(ListHeadRowChangedEvent e) {
    int row = e.getOldRow();
    EditorToModelValueSetter setter =
        new EditorToModelValueSetter(this.editor.getBillCardPanel(),
            this.editor.getModel());
    setter.setModelBodyValue(row);
  }

}
