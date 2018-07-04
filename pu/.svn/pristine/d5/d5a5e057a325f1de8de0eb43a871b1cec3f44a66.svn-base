package nc.ui.pu.m25.action;

import nc.bs.scmpub.tool.SchemeAppendCondition;
import nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;

/**
 * 采购发票查询按钮类
 * 
 * @since 6.5
 * @version 2015-6-16 下午7:33:54
 * @author luojw
 */
public class InvoiceQueryAction extends DefaultQueryAction{

  private static final long serialVersionUID = -765921389348659919L;

  @Override
  protected void executeQuery(IQueryScheme queryScheme) {
    SchemeAppendCondition condition = new SchemeAppendCondition(queryScheme);
    // 添加过滤查询条件，只查询采购归属为采购发票的数据
    condition.appendHead(InvoiceHeaderVO.FINVOICETYPE,"0");
    super.executeQuery(queryScheme);
  }
  
}
