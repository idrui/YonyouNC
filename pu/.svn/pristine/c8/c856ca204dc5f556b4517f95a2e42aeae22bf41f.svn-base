package nc.impl.pu.m21.price;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.fi.MaterialFiVO;
import nc.vo.pu.m21.query.price.OrderPriceQueryDetail;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>物料档案的计划价和参考成本的询价策略的基础类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-5-26 下午04:37:54
 */
public abstract class BaseDocPriceStrategy extends AbstractPriceStrategy {

  @Override
  public void queryPrice() {
    String[] fields = new String[] {
      MaterialFiVO.PK_MATERIAL, MaterialFiVO.PK_ORG, this.getPriceField()
    };
    Map<String, Set<String>> ids = this.getOrgMaterials();
    Set<Entry<String, Set<String>>> orgs = ids.entrySet();
    for (Entry<String, Set<String>> org : orgs) {
      Set<String> materials = org.getValue();
      Map<String, MaterialFiVO> vos =
          MaterialPubService.getFIInfo(
              materials.toArray(new String[materials.size()]), org.getKey(),
              fields);
      this.setPrice(vos);
    }
  }

  private Map<String, Set<String>> getOrgMaterials() {
    Map<String, Set<String>> ids = new HashMap<String, Set<String>>();
    OrderPriceQueryDetail[] details = this.getQueryParameter().getDetail();
    for (OrderPriceQueryDetail detail : details) {
      String org = detail.getFinanceOrg();
      if (org == null || detail.getMaterial() == null) {
        continue;
      }
      Set<String> materials = ids.get(org);
      if (materials == null) {
        materials = new HashSet<String>();
        ids.put(org, materials);
      }
      materials.add(detail.getMaterial());
    }
    return ids;
  }

  private void setPrice(Map<String, MaterialFiVO> mfivos) {
    Map<String, UFDouble> prices = new HashMap<String, UFDouble>();
    Set<Entry<String, MaterialFiVO>> keys = mfivos.entrySet();
    for (Entry<String, MaterialFiVO> key : keys) {
      MaterialFiVO mfivo = key.getValue();
      if (mfivo == null
          || mfivo.getAttributeValue(this.getPriceField()) == null) {
        continue;
      }
      prices.put(mfivo.getPk_org() + "|" + mfivo.getPk_material(),
          (UFDouble) mfivo.getAttributeValue(this.getPriceField()));
    }

    OrderPriceQueryDetail[] details = this.getQueryParameter().getDetail();
    for (OrderPriceQueryDetail detail : details) {
      String org = detail.getFinanceOrg();
      if (org == null || detail.getMaterial() == null) {
        continue;
      }
      UFDouble price = prices.get(org + "|" + detail.getMaterial());
      if (price != null) {
        detail.setPrice(price);
      }
    }
  }

  protected abstract String getPriceField();
}
