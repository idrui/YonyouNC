/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-2 ����03:03:48
 */
package nc.bs.pu.m21.maintain.rule.save;

import java.util.Set;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pubapp.pattern.pub.MapSet;

/**
 * @description
 *              �ɹ������������п����֯�����ϵ�ƥ����
 * @scene
 *        �ɹ����������޸�
 * @param ��
 * @since 6.3
 * @version 2014-10-22 ����11:26:19
 * @author luojw
 */
public class MaterialInStorckOrgRule implements IRule<OrderVO> {

  /**
   * ���෽����д
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(OrderVO[] vos) {
    MapSet<String, String> mapset = this.getMapSet(vos);

    if (0 == mapset.size()) {
      return;
    }

    Set<String> orgs = mapset.keySet();
    for (String org : orgs) {
      Set<String> set = mapset.get(org);
      MaterialPubService.checkMaterialVisiabilityInStorckOrg(org,
          set.toArray(new String[set.size()]));
    }

  }

  /**
   * ���������������ѱ������ϰ����ջ������֯����
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-8-2 ����03:28:01
   */
  private MapSet<String, String> getMapSet(OrderVO[] vos) {
    MapSet<String, String> mapset = new MapSet<String, String>();

    for (OrderVO vo : vos) {
      OrderItemVO[] itemVOs = vo.getBVO();
      for (OrderItemVO itemVO : itemVOs) {
        String pk_arrvstoreorg = itemVO.getPk_arrvstoorg();
        if (StringUtil.isEmptyWithTrim(itemVO.getPk_arrvstoorg())) {
          continue;
        }

        mapset.put(pk_arrvstoreorg, itemVO.getPk_material());
      }
    }

    return mapset;
  }

}
