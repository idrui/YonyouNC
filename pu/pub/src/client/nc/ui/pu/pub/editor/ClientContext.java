package nc.ui.pu.pub.editor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.itf.pu.reference.ct.Z2CTServices;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.ct.entity.CtBusinessVO;
import nc.vo.pu.pub.context.IContext;
import nc.vo.pub.BeanHelper;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.res.NCModule;
import nc.vo.uif2.LoginContext;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>客户端的环境上下文
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-1-31 上午09:42:56
 */
public class ClientContext extends LoginContext implements IContext {
  private static final long serialVersionUID = 2557717182081233235L;

  private Map<String, CtBusinessVO> ctbidBusiMap =
      new HashMap<String, CtBusinessVO>();

  // 是否需要加载关联项，表体增行的时候，需要加载关联项，但多选物料增行的时候则不需要加载，此标志用来标识是否需要加载关联项。
  private boolean needLoadRelationItem = true;

  public LoginContext convertLoginContext() {
    LoginContext context = new LoginContext();
    List<String> prlist = BeanHelper.getPropertys(this);
    for (String pr : prlist) {
      Object value = BeanHelper.getProperty(this, pr);
      BeanHelper.setProperty(context, pr, value);
    }
    return context;
  }

  public CtBusinessVO getCtBusiType(String ctbid) {

    if (!this.ctbidBusiMap.containsKey(ctbid)) {
      try {
        Map<String, CtBusinessVO> map =
            Z2CTServices.queryCtBusinessByPks(new String[] {
              ctbid
            });
        if (null == map) {
          return null;
        }
        this.ctbidBusiMap.putAll(map);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }
    return this.ctbidBusiMap.get(ctbid);
  }

  public boolean isNeedLoadRelationItem() {
    return this.needLoadRelationItem;
  }

  /**
   * 判断某个模块在当前集团是否启用<br>
   * 
   * @param m
   * @return
   */
  public boolean isStart(NCModule m) {
    UFBoolean start = UFBoolean.valueOf(SysInitGroupQuery.isEnable(m));
    return UFBoolean.TRUE.equals(start);
  }

  public void setNeedLoadRelationItem(boolean needLoadRelationItem) {
    this.needLoadRelationItem = needLoadRelationItem;
  }

}
