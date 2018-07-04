package nc.test.pu.pureport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.framework.test.AbstractTestCase;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.pub.BusinessException;

import org.apache.commons.collections.MapUtils;

public class JournalTest extends AbstractTestCase {

  public void testCheck() throws BusinessException {
    // IJournalReport service =
    // NCLocator.getInstance().lookup(IJournalReport.class);
    // SmartContext context = new SmartContext();
    //
    // JournalInfoPara para = new JournalInfoPara();
    // String[] billtypes = {
    // "puorder", "puarrive", "purin", "puinvoice"
    // };
    // para.setBilltypes(billtypes);
    // para.setbShowByMar(true);
    // para.setGroupcondtion(GroupEnum.MARCLASS.strValue());
    // context.setAttribute(PuQueryInfoPara.QUERY_PARA, para);
    // String sql = service.getQuerySql(context);
    // System.out.print(sql);
    List<String> pk_materails = new ArrayList<String>();
    for (int i = 0; i < 100; i++) {
      pk_materails.add("10016010000000002SIJ");
    }

    String pk_stock = "000160100000000003LH";

    String[] fields = {
      "martype"
    };

    Map<String, MaterialStockVO> retMap =
        MaterialPubService.queryMaterialStockInfo(
            pk_materails.toArray(new String[pk_materails.size()]), pk_stock,
            fields);

    MapUtils.debugPrint(System.out, null, retMap);

    // ISaleOrderFor21 service =
    // NCLocator.getInstance().lookup(ISaleOrderFor21.class);
    // UFBoolean ret = M30SOServices.queryIsDirectPO("1002Z83000000000FC60");
    // System.out.print(ret);

  }
}
