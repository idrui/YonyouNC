package nc.vo.pu.m4t.rule;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPCPubService;
import nc.itf.scmpub.reference.uap.bd.stordoc.StordocPubService;
import nc.vo.bd.stordoc.StordocVO;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 
 * @description
 *            �ڳ��ݹ�������ʱ������Ӱ�����Ӱ���������ĳɱ���־
 * @scene
 *      �ڳ��ݹ�������
 * @param
 * 
 *
 * @since 6.5
 * @version 2014-10-23 ����7:12:23
 * @author mengjian
 */
public class InitialEstAffectPCCostFlagRule implements IRule<InitialEstVO> {

  @Override
  public void process(InitialEstVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    // ����Ӱ�����Ӱ���������ĳɱ���־
    this.setAffectPCCost(vos);
  }

  /**
   * ���ݲֿ���Ƿ�ɱ�����������Ӱ��ɱ���־������Ҫ�˹���ĵ��ݣ������㳤��MAP)
   * 
   * @param pk_stordocs
   * @return
   */
  private Map<String, UFBoolean> getEffectByStordoc(String[] pk_stordocs) {
    Map<String, UFBoolean> result = new HashMap<String, UFBoolean>();
    if (null == pk_stordocs || pk_stordocs.length < 1) {
      return result;
    }
    // ��ѯ�ֿ��Ƿ���д���ɱ������־
    StordocVO[] stordocVOs =
        StordocPubService.queryStordocByPks(pk_stordocs, new String[] {
          StordocVO.PK_STORDOC, StordocVO.ISCALCULATEDINVCOST
        });
    if (!ArrayUtils.isEmpty(stordocVOs)) {
      for (StordocVO vo : stordocVOs) {
        result.put(vo.getPk_stordoc(), vo.getIscalculatedinvcost());
      }
    }
    return result;
  }

  private Map<String, UFBoolean> getFlagByMaterial(String[] materials,
      String[] pk_apliabcenters) {
    // ���ϡ���+�������ġ�����á��Ƿ񴫴��������
    MaterialPCPubService pcservice = new MaterialPCPubService();
    return pcservice.queryTransfermarByMarOIDsAndPfcPkOrgs(pk_apliabcenters,
        materials);
  }

  private void setAffectPCCost(InitialEstVO[] vos) {
    Map<String, UFBoolean> stordocMap = new HashMap<String, UFBoolean>();
    String[] pk_stordocs =
        (String[]) AggVOUtil.getDistinctHeadFieldArray(vos,
            InitialEstHeaderVO.PK_STORDOC, String.class);
    stordocMap = this.getEffectByStordoc(pk_stordocs);
    for (InitialEstVO vo : vos) {
      String pk_stordoc =
          (String) vo.getParentVO().getAttributeValue(
              InitialEstHeaderVO.PK_STORDOC);
      if (null == pk_stordoc) {
        continue;
      }
      UFBoolean isStordoc = stordocMap.get(pk_stordoc);
      // �ڳ��ݹ����ֿ⹴ѡӰ��ɱ���
      if (UFBoolean.FALSE.equals(isStordoc)) {
        continue;
      }
      InitialEstItemVO[] bodyvos = vo.getItems();
      Map<String, UFBoolean> materialMap = new HashMap<String, UFBoolean>();
      String[] materials =
          (String[]) AggVOUtil.getDistinctItemFieldArray(vos,
              InitialEstItemVO.PK_SRCMATERIAL, String.class);
      String[] pk_apliabcenters =
          (String[]) AggVOUtil.getDistinctItemFieldArray(vos,
              InitialEstItemVO.PK_APLIABCENTER, String.class);
      materialMap = this.getFlagByMaterial(materials, pk_apliabcenters);
      if (null == materialMap) {
        continue;
      }
      for (InitialEstItemVO bodyvo : bodyvos) {
        String pk_apliabcenter = bodyvo.getPk_apliabcenter();
        String pk_srcmaterial = bodyvo.getPk_srcmaterial();
        // �ڳ��ݹ����������ķǿգ�
        if (StringUtils.isNotBlank(pk_apliabcenter)) {
          // ���ϵ�����������ҳǩ���塰�Ƿ񴫴��������Ϊ�ǣ�
          UFBoolean isPCCost =
              materialMap.get(pk_apliabcenter + pk_srcmaterial);
          bodyvo.setBaffectpccost(isPCCost);
          bodyvo.setStatus(VOStatus.UPDATED);
        }
      }

    }
  }
}
