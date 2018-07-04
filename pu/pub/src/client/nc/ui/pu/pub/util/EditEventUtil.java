package nc.ui.pu.pub.util;

import java.util.List;

import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pu.pub.util.ArrayUtil;

/**
 * 编辑事件工具类
 * 
 * @since 6.0
 * @version 2012-6-26 下午04:24:36
 * @author wuxla
 */
public class EditEventUtil {

  /**
   * 编辑后获取编辑后的行，如果是编辑单行，返回单行；如果是批操作，返回批改后的行
   * 对于批操作，每行的编辑后都会走该方法。第一行是批开始，最后一行是批结束，中间的行是批复制中。
   * 批改开始和批操作中返回值为null，批改结束才返回批改后的行
   * 所以调用者需要判断返回值是否为null
   * 
   * @param event 编辑后事件
   * @return 批操作的行
   */
  public static int[] getAfterEditRows(CardBodyAfterEditEvent event) {
    // 在编辑后进行批拖拽处理
    int[] rows = null;
    if (CardBodyAfterEditEvent.BATCHCOPYEND == event.getAfterEditEventState()) {
      List<Integer> listrow = event.getAfterEditIndexList();
      rows = ArrayUtil.toPrimitive(listrow);
    }
    else if (CardBodyAfterEditEvent.NOTBATCHCOPY == event
        .getAfterEditEventState()) {
      rows = new int[] {
        event.getRow()
      };
    }
    return rows;
  }
}
