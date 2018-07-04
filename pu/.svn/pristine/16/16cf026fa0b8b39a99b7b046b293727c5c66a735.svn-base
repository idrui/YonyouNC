package nc.pubimpl.pu.m21.cmp.m36d1;

import java.util.HashSet;
import java.util.Set;

import nc.pubitf.cmp.apply.BaseMultiBillEditSrcQieruForApply;
import nc.vo.cmp.apply.ApplyBVO;
import nc.vo.cmp.apply.ApplyVO;

import org.apache.commons.collections.CollectionUtils;

public class PUEditForApplyImpl extends BaseMultiBillEditSrcQieruForApply {
  /**
   * 表体预付,表体订单号
   */
  private static final String[] bodyNotEdit = {
    ApplyBVO.ISPREPAY, ApplyBVO.ORDERNO
  };

  /**
   * 付款组织,付款财务组织,供应商,申请付款日期,币种
   */
  private static final String[] headNotEdit = {
    ApplyVO.PK_ORG, ApplyVO.PK_ACCEPTORG, ApplyVO.PK_SUPPLIER,
    ApplyVO.APPLYDATE, ApplyVO.PK_CURRTYPE
  };

  @Override
  public Set<String> getBodyNotEditableFields() {
    Set<String> body = new HashSet<String>();
    CollectionUtils.addAll(body, PUEditForApplyImpl.bodyNotEdit);
    return body;
  }

  @Override
  public Set<String> getHeadNotEditableFields() {
    Set<String> head = new HashSet<String>();
    CollectionUtils.addAll(head, PUEditForApplyImpl.headNotEdit);
    return head;
  }
}
