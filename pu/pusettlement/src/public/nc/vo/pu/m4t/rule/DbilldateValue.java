package nc.vo.pu.m4t.rule;

import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.lang.UFDate;

import org.apache.commons.lang.StringUtils;

/**
 * 入库日期
 * 
 * @since 6.0
 * @version 2010-11-16 上午08:40:38
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
    // 期初日期取值参数INI02,取期初日期的前一天
    UFDate startDate = PUSysParamUtil.getINI02BeforeDate(pk_org);
    if (startDate != null) {
      this.keyValue.setHeadValue(InitialEstHeaderVO.DBILLDATE, startDate);
    }
  }
}
