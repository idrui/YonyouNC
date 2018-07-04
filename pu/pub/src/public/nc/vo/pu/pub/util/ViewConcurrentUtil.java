package nc.vo.pu.pub.util;

import nc.impl.pubapp.pattern.data.view.tool.ViewConcurrentTool;
import nc.vo.pubapp.pattern.model.entity.view.IDataView;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>IDataView的并发控制工具类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-8-13 下午03:58:40
 */
public class ViewConcurrentUtil {
  private static ViewConcurrentUtil instance = new ViewConcurrentUtil();

  private ViewConcurrentUtil() {
    // 缺省构造方法
  }

  public static ViewConcurrentUtil getInstance() {
    return ViewConcurrentUtil.instance;
  }

  public void concurrentCheck(IDataView[] views) {
    if (views == null || views.length == 0) {
      return;
    }
    this.lock(views);
    this.checkTS(views);
  }

  private void checkTS(IDataView[] views) {
    IDataView[] originViews = this.queryOriginViews(views);
    new ViewConcurrentTool().checkTS(views, originViews);
  }

  private void lock(IDataView[] views) {
    new ViewConcurrentTool().lock(views);
  }

  @SuppressWarnings({
    "unchecked", "rawtypes"
  })
  private IDataView[] queryOriginViews(IDataView[] views) {
    String[] pks = new String[views.length];
    for (int i = 0; i < views.length; i++) {
      pks[i] = views[i].getPrimaryKey();
    }

    ViewTsQueryUtil util = new ViewTsQueryUtil(views[0].getClass());
    IDataView[] originViews = util.query(pks);

    return originViews;
  }
}
