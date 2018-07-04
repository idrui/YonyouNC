package nc.ui.pu.report.pub.action;

import java.util.ArrayList;
import java.util.List;

import nc.ui.uif2.editor.UserdefQueryParam;
import nc.vo.pub.SuperVO;

public class PuReportDlgInit {
  /**
   * 查询模板自定义项参数
   * 
   * @return
   */
  protected List<UserdefQueryParam> getQueryParams(SuperVO hvo, SuperVO bvo) {
    List<UserdefQueryParam> paramList = new ArrayList<UserdefQueryParam>();
    UserdefQueryParam param = new UserdefQueryParam();
    param.setMdfullname(hvo.getMetaData().getEntityName());
    param.setPrefix("vdef");
    paramList.add(param);
    param = new UserdefQueryParam();
    param.setMdfullname(bvo.getMetaData().getEntityName());
    param.setPrefix("vbdef");
    paramList.add(param);
    return paramList;
  }
}
