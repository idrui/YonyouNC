package nc.ui.pu.uif2;

import nc.ui.pubapp.uif2app.view.TemplateContainer;

import org.apache.commons.lang.StringUtils;

/**
 * 采购组的单据模板加载器
 * 
 * @since 6.0
 * @version 2011-12-8 下午4:24:26
 * @author zhaoyha
 */
public class PUTemplateContainer extends TemplateContainer {

  @Override
  public String getPk_group() {
    String group = super.getPk_group();
    if (StringUtils.isBlank(group)) {
      group = this.getContext().getPk_group();
    }
    return group;
  }

  @Override
  public String getPk_loginuser() {
    String usr = super.getPk_loginuser();
    if (StringUtils.isBlank(usr)) {
      usr = this.getContext().getPk_loginUser();
    }
    return usr;
  }

}
