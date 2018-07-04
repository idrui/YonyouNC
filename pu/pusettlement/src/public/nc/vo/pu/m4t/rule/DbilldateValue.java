package nc.vo.pu.m4t.rule;

import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.lang.UFDate;

import org.apache.commons.lang.StringUtils;

/**
 * �������
 * 
 * @since 6.0
 * @version 2010-11-16 ����08:40:38
 * @author wuxla
 */

public class DbilldateValue {
  private IKeyValue keyValue;

  public DbilldateValue(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  public void setDbilldate() {
    String pk_org =
        (String) this.keyValue.getHeadValue(InitialEstHeaderVO.PK_ORG);
    if (StringUtils.isEmpty(pk_org)) {
      return;
    }
    // �ڳ�����ȡֵ����INI02,ȡ�ڳ����ڵ�ǰһ��
    UFDate startDate = PUSysParamUtil.getINI02BeforeDate(pk_org);
    if (startDate != null) {
      this.keyValue.setHeadValue(InitialEstHeaderVO.DBILLDATE, startDate);
    }
  }
}
