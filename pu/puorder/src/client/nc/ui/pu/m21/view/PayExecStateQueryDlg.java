package nc.ui.pu.m21.view;

import java.awt.Container;

import nc.ui.querytemplate.QueryConditionDLG;
import nc.vo.pub.BusinessException;
import nc.vo.pub.query.QueryConditionVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.querytemplate.TemplateInfo;

import org.apache.commons.lang.StringUtils;

/**
 * @since 6.0
 * @version 2011-3-31 上午08:56:34
 * @author wuxla
 */

public class PayExecStateQueryDlg extends QueryConditionDLG {

  private static final long serialVersionUID = -3461689842593960306L;

  /**
   * @param parent
   * @param ti
   */
  public PayExecStateQueryDlg(Container parent, TemplateInfo ti, String pk_order) {
    super(parent, ti);

    try {
      QueryConditionVO[] conds =
          this.getQryCondEditor().getTotalVO().getConditionVOs();
      for (QueryConditionVO cond : conds) {
        if (cond.getFieldCode().equals("po_order.pk_order")) {
          if (StringUtils.isNotEmpty(cond.getValue())) {
            // 如果模板上设置了默认值，则不处理
            continue;
          }
          cond.setValue(pk_order);
        }
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

}
