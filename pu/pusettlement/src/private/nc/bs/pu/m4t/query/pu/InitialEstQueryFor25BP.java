/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-20 上午08:36:50
 */
package nc.bs.pu.m4t.query.pu;

import java.util.ArrayList;
import java.util.List;

import nc.bs.pu.m4t.query.pu.rule.CanInvoiceNumCalcRule;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pu.m4t.entity.InitialEstViewVO;
import nc.vo.pubapp.pattern.model.tool.BillComposite;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-20 上午08:36:50
 */
public class InitialEstQueryFor25BP {

  public InitialEstVO[] query(String cond) {
    DataAccessUtils utils = new DataAccessUtils();
    String[] bids = utils.query(cond).toOneDimensionStringArray();

    InitialEstViewVO[] views =
        new ViewQuery<InitialEstViewVO>(InitialEstViewVO.class).query(bids);
    if (ArrayUtils.isEmpty(views)) {
      return null;
    }

    List<InitialEstHeaderVO> headList = new ArrayList<InitialEstHeaderVO>();
    List<InitialEstItemVO> itemList = new ArrayList<InitialEstItemVO>();
    for (InitialEstViewVO view : views) {
      headList.add((InitialEstHeaderVO) view.getVO(InitialEstHeaderVO.class));
      itemList.add((InitialEstItemVO) view.getVO(InitialEstItemVO.class));
    }

    BillComposite<InitialEstVO> bc =
        new BillComposite<InitialEstVO>(InitialEstVO.class);
    InitialEstVO tempVO = new InitialEstVO();
    bc.append(tempVO.getMetaData().getParent(),
        headList.toArray(new InitialEstHeaderVO[headList.size()]));
    bc.append(tempVO.getMetaData().getVOMeta(InitialEstItemVO.class),
        itemList.toArray(new InitialEstItemVO[itemList.size()]));
    InitialEstVO[] queryVos = bc.composite();

    AroundProcesser<InitialEstVO> processer =
        new AroundProcesser<InitialEstVO>(null);
    this.addRule(processer);

    queryVos = processer.after(queryVos);
    return queryVos;
  }

  private void addRule(AroundProcesser<InitialEstVO> processer) {
    processer.addAfterRule(new CanInvoiceNumCalcRule());
  }

}
