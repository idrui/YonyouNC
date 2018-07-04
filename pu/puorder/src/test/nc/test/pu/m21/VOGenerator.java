/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2009-12-30 下午03:29:34
 */
package nc.test.pu.m21;

import nc.bs.framework.test.AbstractTestCase;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pubapp.pattern.tool.generate.VOFieldGenerator;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>根据元数据，生成VO方法及常量
 * <li>
 * <li>
 * </ul>
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2009-12-30 下午03:29:34
 */
public class VOGenerator extends AbstractTestCase {

  public void testGenerateVO() {
    VOFieldGenerator bo = new VOFieldGenerator();
    String str = bo.generate(OrderItemVO.class);
    System.out.println(str);
  }

  @Override
  protected String getPort() {
    return "80";
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
  }

  // public void testUFdouble(){
  // UFDouble a=new UFDouble(3.14,3);
  // UFDouble b=new UFDouble(3.14,2);
  // System.out.println(a.add(b));
  // System.out.println(a.add(b).getPower());
  // }

}
