package nc.test.pu.mm;

import java.lang.reflect.Method;

import nc.bs.framework.test.AbstractTestCase;
import nc.vo.pu.mmpps.SupplyResultForCalcVO;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * 为制造提供的供给sql片段测试类
 * 
 * @since 6.0
 * @version 2011-12-22 上午11:34:43
 * @author tianft
 */
public abstract class AbstractSupplyForMMTestCase extends AbstractTestCase {

  /**
   * 需要返回实际组装的vo
   * 
   * @return
   */
  public abstract SupplyResultForCalcVO getSupplyResultVO();

  /**
   * 获取供给的sql片段，主要测试传的字段，where条件等是否正确
   * 
   * @return
   */
  public String getSupplySql() {
    SupplyResultForCalcVO rst = this.getSupplyResultVO();
    Method[] methods = rst.getClass().getDeclaredMethods();
    String from = null;
    String where = null;
    SqlBuilder wholeSql = new SqlBuilder();
    wholeSql.append("select ");
    for (Method m : methods) {
      try {
        if (m.getName().startsWith("set")) {
          continue;
        }
        String value = m.invoke(rst, new Object[0]).toString();
        if (m.getName().toUpperCase().contains("WHERE")) {
          where = value;
        }
        else if (m.getName().toUpperCase().contains("FROM")) {
          from = value;
        }
        else {
          wholeSql.append(value);
          wholeSql.append(",");
        }
      }
      catch (Exception e) {
        // ExceptionUtils.wrappException(e);
      }
    }
    wholeSql.append(" 'helllo' ");
    wholeSql.append(" from ");
    wholeSql.append(from);
    wholeSql.append(" where ");
    wholeSql.append(where);
    System.out.println(wholeSql.toString());
    return wholeSql.toString();
  }

}
