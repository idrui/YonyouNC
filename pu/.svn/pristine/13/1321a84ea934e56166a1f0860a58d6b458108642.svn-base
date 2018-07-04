/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-4 上午09:26:51
 */
package nc.vo.pu.m25.rule;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.util.ApproveFlowUtil;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.util.AuditInfoUtils;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @description
 * 填充制单时间(创建时间)、修改时间、制单人(修改人)填充
 * @scene
 * 保存的BP
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午3:20:26
 * @author zhangshqb
 */
public class InvoiceAuditInfoFillRule implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    List<InvoiceVO> insertList = new ArrayList<InvoiceVO>();
    List<InvoiceVO> updateList = new ArrayList<InvoiceVO>();
    for (InvoiceVO vo : vos) {
      if (StringUtils.isEmpty(vo.getParentVO().getPk_invoice())) {
        insertList.add(vo);
      }
      else {
        updateList.add(vo);
      }
    }
    if (insertList.size() > 0) {
      InvoiceVO[] insertVos =
          insertList.toArray(new InvoiceVO[insertList.size()]);
      AuditInfoUtils.setAddAuditInfo(insertVos);
      ApproveFlowUtil.setBillMakeInfo(insertVos);
    }
    if (updateList.size() > 0) {
      AuditInfoUtils.setUpdateAuditInfo(updateList
          .toArray(new InvoiceVO[updateList.size()]));
    }
    for (InvoiceVO vo : vos) {
      InvoiceHeaderVO header = vo.getParentVO();
      if (header.getStatus() == VOStatus.UNCHANGED) {
        header.setStatus(VOStatus.UPDATED);
      }
    }
  }

}
