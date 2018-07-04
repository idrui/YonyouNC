/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-8 ����10:43:55
 */
package nc.bs.pu.m4t.writeback.pu.rule;

import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m4t.pu.m25.IInvoicePullWBPara;
import nc.vo.pu.m4t.entity.InitialEstViewVO;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ۼƿ�Ʊ�������㣺�ۼƿ�Ʊ����=ԭ�ۼƿ�Ʊ����+���ο�Ʊ����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-8 ����10:43:55
 */
public class AccInvoiceNumCalcRule implements IRule<InitialEstViewVO> {

  private IInvoicePullWBPara[] paras;

  public AccInvoiceNumCalcRule(IInvoicePullWBPara[] paras) {
    this.paras = paras;
  }

  /**
   * ���෽����д
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(InitialEstViewVO[] views) {
    Map<String, InitialEstViewVO> viewMap = CirVOUtil.createKeyVOMap(views);
    for (IInvoicePullWBPara para : this.paras) {
      InitialEstViewVO view = viewMap.get(para.getBID());
      UFDouble diffNum = para.getDiffNum();
      UFDouble newAccNum = MathTool.add(view.getNaccinvoicenum(), diffNum);
      view.setNaccinvoicenum(newAccNum);
    }
  }
}
