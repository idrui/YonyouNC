package nc.test.pu.pub.property;

import java.util.Arrays;
import java.util.List;

import nc.bs.framework.test.AbstractTestCase;
import nc.vo.pu.m25trantype.entity.InvcTranTypeVO;
import nc.vo.pub.BeanHelper;
import nc.vo.uif2.LoginContext;

/**
 * 
 * @since 6.0
 * @version 2011-10-27 ÉÏÎç11:14:58
 * @author zhaoyha
 */

public class PropTest extends AbstractTestCase {

  public void testGetAttributeNames() {
    String[] names = new InvcTranTypeVO().getAttributeNames();
    System.out.println(Arrays.deepToString(names));
    List<String> propLst = BeanHelper.getPropertys(new InvcTranTypeVO());
    System.out.println(propLst);
  }

  public void testProp() {
    LoginContext con = new LoginContext();
    List<String> propLst = BeanHelper.getPropertys(con);
    System.out.print(propLst);
  }

}
