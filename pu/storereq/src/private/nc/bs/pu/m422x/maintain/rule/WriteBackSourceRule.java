package nc.bs.pu.m422x.maintain.rule;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.bs.pu.m422x.writeback.source.WriteBack4D14;
import nc.impl.pubapp.bill.rewrite.BillRewriter;
import nc.impl.pubapp.bill.rewrite.ItemKeyMapping;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pu.pub.writeback.IWriteBackSource;
import nc.vo.pub.ISuperVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 *            物资需求申请单回写来源时，对一些规则进行处理
 * @scene
 *      物资需求申请单回写来源
 * @param
 * 
 *
 * @since 6.3
 * @version 2013-8-8 下午07:32:46
 * @author fanly3
 */
public class WriteBackSourceRule implements ICompareRule<StoreReqAppVO> {

  @Override
  public void process(StoreReqAppVO[] vos, StoreReqAppVO[] originVOs) {
    if (vos == null && originVOs == null) {
      return;
    }
    this.writeback(vos, originVOs);
  }

  /**
   * 返回下游回写上游的字段映射类
   * 
   * @return ItemKeyMapping的实例
   */
  private ItemKeyMapping getItemMapping() {
    ItemKeyMapping mapping = new ItemKeyMapping();
    mapping.setVsrctypeKey(StoreReqAppItemVO.CSOURCETYPECODE);
    mapping.setCsrcbidKey(StoreReqAppItemVO.CSOURCEBID);
    mapping.setCsrcidKey(StoreReqAppItemVO.CSOURCEID);
    mapping.setSrcTSKey(StoreReqAppItemVO.SOURCETS);
    mapping.setSrcbTSKey(StoreReqAppItemVO.SOURCEBTS);
    mapping.setNnumKey(StoreReqAppItemVO.NNUM);
    return mapping;
  }

  /**
   * 组织回写参数map
   * 
   * @param vos 物资需求申请单vos
   * @param originVOs 原始物资需求申请单vos
   * @return 回写参数map
   */
  private Map<String, List<RewritePara>> getRewritePara(StoreReqAppVO[] vos,
      StoreReqAppVO[] originVOs) {
    BillRewriter tool = this.getRewriteTool();
    Map<String, List<RewritePara>> rwParaMap = null;
    if (ArrayUtils.isEmpty(vos)) {
      rwParaMap = tool.splitForDelete(originVOs);
    }
    else if (ArrayUtils.isEmpty(originVOs)) {
      rwParaMap = tool.splitForInsert(vos);
    }
    else {
      rwParaMap = tool.splitForUpdate(vos, originVOs);
    }
    return rwParaMap;
  }

  /**
   * 返回回写上游数量的工具类实例
   * 
   * @return BillRewriter的实例
   */
  @SuppressWarnings("unchecked")
  private BillRewriter getRewriteTool() {
    ItemKeyMapping mapping = this.getItemMapping();
    BillRewriter tool = new BillRewriter(mapping);
    if (SysInitGroupQuery.isPIMEnabled()) {
      try {
        // 物资及服务需求单
        tool.addSRCHeadClazz("1001ZP1000000005GEZN",
            (Class<? extends ISuperVO>) Class
                .forName("nc.vo.pbm.materialplan.MaterialPlanHeadVO"));
        tool.addSRCItemClazz("1001ZP1000000005GEZN",
            (Class<? extends ISuperVO>) Class
                .forName("nc.vo.pbm.materialplan.MaterialPlanBodyVO"));
        // tool.addSRCHeadClazz("1001ZP1000000005GEZN",
        // MaterialPlanHeadVO.class);
        // tool.addSRCItemClazz("1001ZP1000000005GEZN",
        // MaterialPlanBodyVO.class);
      }
      catch (ClassNotFoundException e) {
        ExceptionUtils.wrappException(e);
      }
    }
    return tool;
  }

  private IWriteBackSource getWriteBacker(String billType) {
    IWriteBackSource writeBackSource = null;
    if ("1001ZP1000000005GEZN".equals(billType)) {
      writeBackSource = new WriteBack4D14();
    }
    return writeBackSource;
  }

  private void writeback(StoreReqAppVO[] vos, StoreReqAppVO[] originVOs) {
    // 得到回写参数map
    Map<String, List<RewritePara>> rwParaMap =
        this.getRewritePara(vos, originVOs);

    for (Entry<String, List<RewritePara>> entry : rwParaMap.entrySet()) {
      List<RewritePara> list = entry.getValue();
      if (list.size() == 0) {
        continue;
      }
      String billtype = entry.getKey();
      IWriteBackSource wbacker = this.getWriteBacker(billtype);
      // 按上游单据类型执行真正回写
      if (null != wbacker) {
        wbacker.writeback(rwParaMap.get(billtype));
      }
    }
  }
}
