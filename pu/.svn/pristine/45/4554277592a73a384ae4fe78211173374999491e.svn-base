package nc.test.pu.invp;

import java.lang.reflect.Method;

import nc.bs.framework.common.NCLocator;
import nc.bs.framework.core.service.IFwLogin;
import nc.bs.framework.test.AbstractTestCase;
import nc.pubitf.invp.plan.ISupplyResultForInvp;
import nc.pubitf.pu.m20.invp.IBuyingreqQueryForInvp;
import nc.pubitf.pu.m21.invp.IOrderQueryForInvp;
import nc.vo.pub.BusinessException;

public class SupplyResultTestCase extends AbstractTestCase {

  public void testFor20() throws BusinessException {
    IFwLogin loginService = NCLocator.getInstance().lookup(IFwLogin.class);
    loginService.login("lxy", "1", null);

    IBuyingreqQueryForInvp service =
        NCLocator.getInstance().lookup(IBuyingreqQueryForInvp.class);
    ISupplyResultForInvp result =
        service.getSupply("0001D31000000000AOSB", "tempTable");
    this.printSql("sql for 20:", result);
  }

  public void testFor21IncludeRed() throws BusinessException {
    IFwLogin loginService = NCLocator.getInstance().lookup(IFwLogin.class);
    loginService.login("lxy", "1", null);

    IOrderQueryForInvp service =
        NCLocator.getInstance().lookup(IOrderQueryForInvp.class);
    ISupplyResultForInvp result =
        service.getSupply("0001D31000000000AOSB", "tempTable", true);
    this.printSql("sql for 21(include red):", result);
  }

  public void testFor21NotIncludeRed() throws BusinessException {
    IFwLogin loginService = NCLocator.getInstance().lookup(IFwLogin.class);
    loginService.login("lxy", "1", null);

    IOrderQueryForInvp service =
        NCLocator.getInstance().lookup(IOrderQueryForInvp.class);
    ISupplyResultForInvp result =
        service.getSupply("0001D31000000000AOSB", "tempTable", false);
    this.printSql("sql for 21(not include red):", result);
  }

  private void printSql(String prefix, ISupplyResultForInvp result) {
    Method[] methods = result.getClass().getDeclaredMethods();
    String methodName = null;
    StringBuffer buf = new StringBuffer();

    buf.append(" select ");
    for (Method m : methods) {
      methodName = m.getName();
      try {
        if (methodName.startsWith("get") && !methodName.equals("getFrom")
            && !methodName.equals("getWhere")) {
          Object value = m.invoke(result, new Object[0]);
          if (value != null) {
            buf.append(value);
            buf.append(",");
          }
          else {
            System.out.print("method '" + m.getName() + "' return null.");
          }
        }
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }
    buf.deleteCharAt(buf.length() - 1);
    buf.append(" from ");
    buf.append(result.getFrom());
    buf.append(" where ");
    buf.append(result.getWhere());

    System.out.println(prefix + buf.toString());
  }
}
