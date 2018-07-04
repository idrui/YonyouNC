package nc.impl.pu.m21transtype.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * ���һ�����������Ƿ��ɾ��<br>
 * �������ֻ�����飬ϵͳԤ�õ�һЩ�������ͣ�21-coop��������ɾ��<br>
 * ���������Ƿ�ʹ�ã�ɾ��ʱƽ̨�Ѿ����ã����ﲻ���ظ����
 *
 * @since 6.0
 * @version 2011-7-18 ����02:10:24
 * @author zhaoyha
 */
public class CanDelChkRule implements IRule<PoTransTypeVO> {

  @Override
  public void process(PoTransTypeVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    for (PoTransTypeVO vo : vos) {
      if (PoTransTypeVO.M21_COOP.equals(vo.getVtrantypecode())) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0","04004000-0052")/*@res "�˽����������ڲ�Ԥ�ã�ֻ��������Эͬ�ɹ�ʱ�����ݽ������֣�����ɾ����Ҳ���鲻�ô˽�����ҵ�����ݣ�"*/);
      }
    }

  }
}