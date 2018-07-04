package nc.test.pu.invp;

import java.lang.reflect.Method;

import nc.bs.framework.common.NCLocator;
import nc.bs.framework.core.service.IFwLogin;
import nc.bs.framework.test.AbstractTestCase;
import nc.pubitf.invp.plan.IReqResultForInvp;
import nc.pubitf.pu.m422x.invp.IStorereqQueryForInvp;
import nc.vo.pub.BusinessException;

public class ReqResultTestCase extends AbstractTestCase {

  public void testFor422X() throws BusinessException {
    IFwLogin loginService = NCLocator.getInstance().lookup(IFwLogin.class);
    loginService.login("lxy", "1", null);

    IStorereqQueryForInvp service =
        NCLocator.getInstance().lookup(IStorereqQueryForInvp.class);
    IReqResultForInvp result =
        service.getReq("0001D31000000000AOSB", "tempTable");
    this.printSql("sql for 422X:", result);
  }

  private void printSql(String prefix, IReqResultForInvp result) {
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
