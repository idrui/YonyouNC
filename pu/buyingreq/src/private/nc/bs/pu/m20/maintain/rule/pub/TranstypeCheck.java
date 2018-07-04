package nc.bs.pu.m20.maintain.rule.pub;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m21transtype.IPoTransTypeQuery;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @description
 *              �빺�������ֱ��ҵ��У����嶩�������Ƿ���ڷ�ֱ�˶�������
 * @scene
 *        �빺���������޸�
 * @param ��
 * @since 6.3
 * @version 2014-10-21 ����10:02:59
 * @author yanxm5
 */
public class TranstypeCheck implements IRule<PraybillVO> {

  @Override
  public void process(PraybillVO[] vos) {
    if (null == vos || vos.length == 0) {
      return;
    }

    // ֱ���빺���ı��嶩������
    Set<String> ctranstypes = new HashSet<String>();
    for (PraybillVO vo : vos) {
      PraybillHeaderVO head = vo.getHVO();
      UFBoolean bdirecttransit = head.getBdirecttransit();
      if (bdirecttransit != null && bdirecttransit.booleanValue()) {
        PraybillItemVO[] bodyVos = (PraybillItemVO[]) vo.getChildrenVO();
        for (PraybillItemVO bodyVo : bodyVos) {
          if (bodyVo.getCordertrantypecode() != null) {
            ctranstypes.add(bodyVo.getCordertrantypecode());
          }
        }
      }
    }

    if (ctranstypes.isEmpty()) {
      return;
    }

    boolean isExistNotDirect =
        this.isExistNotDirect(ctranstypes.toArray(new String[ctranstypes.size()]));
    if (isExistNotDirect) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004020_0", "04004020-0108")/*
                                                                   * @res
                                                                   * "�빺����ֱ�ˣ����嶩������ֻ����ֱ�˶������ͣ����飡"
                                                                   */);
    }
  }

  /**
   * �Ƿ���ڷ�ֱ�˵Ķ�������
   * 
   * @param ctrantypeids �빺�����嶩����������
   * @return ������ֻҪ���ڷ�ֱ�˶������ͣ�����true�����򷵻�false
   */
  private boolean isExistNotDirect(String[] ctrantypeids) {
    boolean isExistNotDirect = false;
    IPoTransTypeQuery service =
        NCLocator.getInstance().lookup(IPoTransTypeQuery.class);
    try {
      Map<String, PoTransTypeVO> map = service.queryAttrByIDs(ctrantypeids);
      if (map != null) {
        for (Entry<String, PoTransTypeVO> entry : map.entrySet()) {
          if (entry.getValue() != null
              && UFBoolean.FALSE.equals(entry.getValue().getBdirect())) {
            isExistNotDirect = true;
            break;
          }
        }
      }
      return isExistNotDirect;
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return isExistNotDirect;
  }

}
