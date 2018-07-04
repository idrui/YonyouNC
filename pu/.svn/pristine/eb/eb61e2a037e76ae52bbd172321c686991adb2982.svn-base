package nc.bs.pu.m4t.maintain.rule;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.bs.pu.m4t.writeback.pu.source.IInitialEstWriteBackSource;
import nc.bs.pu.m4t.writeback.pu.source.WriteBack21;
import nc.impl.pubapp.bill.rewrite.BillRewriter;
import nc.impl.pubapp.bill.rewrite.ItemKeyMapping;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m4t.entity.InitialEstContext;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 *调用采购订单的回写服务，回写上游单据
 * @scene
 * 期初暂估单删除
 * @param
 * InitialEstContext：context
 *
 * @since 6.3
 * @version 2010-9-8 上午10:36:26
 * @author wuxla
 */
public class WriteBackSourceRule implements ICompareRule<InitialEstVO> {

  private InitialEstContext context;

  public WriteBackSourceRule(InitialEstContext context) {
    this.context = context;
  }

  
  @Override
  public void process(InitialEstVO[] vos, InitialEstVO[] originVOs) {
    this.writebackSource(vos, originVOs);
  }

  private BillRewriter getRWTool(ItemKeyMapping mapping) {
    BillRewriter tool = new BillRewriter(mapping);
    // 采购订单
    tool.addSRCHeadClazz(POBillType.Order.getCode(), OrderHeaderVO.class);
    tool.addSRCItemClazz(POBillType.Order.getCode(), OrderItemVO.class);
    return tool;
  }

  private IInitialEstWriteBackSource getWriteBacker(String billtype) {
    if (POBillType.Order.getCode().equals(billtype)) {
      return new WriteBack21();// 采购订单单回写器
    }
    return null;
  }

  /**
   *回写上游单据
   */
  private void writeback(InitialEstVO[] vos, InitialEstVO[] orgVOs,
      ItemKeyMapping mapping) {
    BillRewriter tool = this.getRWTool(mapping);
    Map<String, List<RewritePara>> rwParaMap = null;
    if (ArrayUtils.isEmpty(vos)) {
      rwParaMap = tool.splitForDelete(orgVOs);
    }
    else if (ArrayUtils.isEmpty(orgVOs)) {
      rwParaMap = tool.splitForInsert(vos);
    }
    else {
      rwParaMap = tool.splitForUpdate(vos, orgVOs);
    }
    for (Entry<String, List<RewritePara>> entry : rwParaMap.entrySet()) {
      String billtype = entry.getKey();
      if (0 == rwParaMap.get(billtype).size()) {
        continue;
      }
      IInitialEstWriteBackSource wbacker = this.getWriteBacker(billtype);
      if (null != wbacker) {
        wbacker.writeback(rwParaMap.get(billtype), this.context);
      }
    }
  }

  /**
   * 设置回写单据的参数
   */
  private void writebackSource(InitialEstVO[] vos, InitialEstVO[] orgVOs) {
    ItemKeyMapping mapping = new ItemKeyMapping();
    mapping.setVsrctypeKey(InitialEstItemVO.CSOURCETYPECODE);
    mapping.setCsrcbidKey(InitialEstItemVO.CSOURCEBID);
    mapping.setCsrcidKey(InitialEstItemVO.CSOURCEID);
    mapping.setSrcTSKey(InitialEstItemVO.SOURCETS);
    mapping.setSrcbTSKey(InitialEstItemVO.SOURCEBTS);
    mapping.setNnumKey(InitialEstItemVO.NNUM);
    this.writeback(vos, orgVOs, mapping);
  }

}
