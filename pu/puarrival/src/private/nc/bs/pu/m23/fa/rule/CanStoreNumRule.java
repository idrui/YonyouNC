package nc.bs.pu.m23.fa.rule;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.rule.IFilterRule;
import nc.vo.pu.m23.entity.ArriveBbVO;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.utils.ArriveTrans45QueryUtil;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * 
 * @description
 * 关联孙表，计算可入库数量
 * @scene
 * 到货单审批
 * @param
 * 无
 *
 * @since 6.3
 * @version 2010-12-28 上午08:04:24
 * @author wuxla
 */

public class CanStoreNumRule implements IFilterRule<ArriveVO> {

  @Override
  public ArriveVO[] process(ArriveVO[] vos) {
    List<ArriveHeaderVO> headList = new ArrayList<ArriveHeaderVO>();
    List<ArriveItemVO> itemVOList = new ArrayList<ArriveItemVO>();
    List<String> bidList = new ArrayList<String>();
    for (ArriveVO vo : vos) {
      for (ArriveItemVO itemVO : vo.getBVO()) {
        if (MathTool.compareTo(itemVO.getNnum(), itemVO.getNaccumstorenum()) > 0) {
          itemVOList.add(itemVO);
          bidList.add(itemVO.getPk_arriveorder_b());
        }
      }
      headList.add(vo.getHVO());
    }
    if (0 == itemVOList.size()) {
      return null;
    }

    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_23_13.name());
    String[] bids = bidList.toArray(new String[bidList.size()]);
    // SqlBuilder cond = new SqlBuilder();
    // cond.append(ArriveBbVO.PK_ARRIVEORDER_B, bids);
    String cond = builder.buildSQL(ArriveBbVO.PK_ARRIVEORDER_B, bids);
    VOQuery<ArriveBbVO> query = new VOQuery<ArriveBbVO>(ArriveBbVO.class);
    ArriveBbVO[] bbVOs = query.query(" and " + cond, null);
    ArriveHeaderVO[] headVOs =
        headList.toArray(new ArriveHeaderVO[headList.size()]);
    ArriveItemVO[] itemVOs =
        itemVOList.toArray(new ArriveItemVO[itemVOList.size()]);
    ArriveTrans45QueryUtil tranQueryUtil =
        new ArriveTrans45QueryUtil(headVOs, itemVOs, bbVOs);

    return tranQueryUtil.combine();
  }
}
