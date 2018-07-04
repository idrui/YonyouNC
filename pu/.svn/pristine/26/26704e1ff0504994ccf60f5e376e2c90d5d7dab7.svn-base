package nc.test.pubitf.pu.m21transtype;

import java.lang.reflect.Field;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.framework.test.AbstractTestCase;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.pubitf.pu.m21transtype.IPoTransTypeQuery;
import nc.vo.org.GroupVO;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.collections.MapUtils;

/**
 * 
 * @since 6.0
 * @version 2011-12-28 上午10:54:00
 * @author zhaoyha
 */
public class PoTransTypeQueryTest extends AbstractTestCase {

  public void testGetDirectTransWhereString() {
    try {
      IPoTransTypeQuery q =
          NCLocator.getInstance().lookup(IPoTransTypeQuery.class);
      String sqlslice = q.getDirectTransWhereString("po_order", "ctrantypeid");
      System.out.println(sqlslice);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

  public void testQueryAttrByIDs() {
    try {
      String[] ids =
          {
            "0001Z81000000000I9DM", "0001Z81000000000I9DN",
            "0001Z81000000000I9DO", "0001Z81000000000J1XI"
          };

      IPoTransTypeQuery q =
          NCLocator.getInstance().lookup(IPoTransTypeQuery.class);
      Map<String, PoTransTypeVO> map = q.queryAttrByIDs(ids);
      MapUtils.debugPrint(System.out, null, map);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

  public void testQueryAttrByTypes() {
    try {
      String[] code = {
        "21-04", "21-05", "21-coop", "21-01"
      };
      // String[] atts =
      // {
      // PoTransTypeVO.VTRANTYPECODE, PoTransTypeVO.BARRIVE,
      // PoTransTypeVO.CTRANTYPEID
      // };
      // 设置环境变量，后台服务中只有了集团信息
      Field field = WorkbenchEnvironment.class.getDeclaredField("groupVO");
      field.setAccessible(true);
      GroupVO gvo = (GroupVO) field.get(WorkbenchEnvironment.getInstance());
      if (null == gvo) {
        gvo = new GroupVO();
        field.set(WorkbenchEnvironment.getInstance(), gvo);
      }
      gvo.setPk_group("0001Z81000000000HJY3");
      IPoTransTypeQuery q =
          NCLocator.getInstance().lookup(IPoTransTypeQuery.class);
      Map<String, PoTransTypeVO> map = q.queryAttrByTypes(code, null);// atts);
      MapUtils.debugPrint(System.out, null, map);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

}
