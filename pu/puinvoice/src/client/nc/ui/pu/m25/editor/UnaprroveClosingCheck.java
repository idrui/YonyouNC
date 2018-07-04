package nc.ui.pu.m25.editor;

import nc.ui.pu.m25.service.ClosingCheckModelService;

/**
 * ºó¶Ë¼ì²éÂß¼­¡£
 * 
 * @since 6.36
 * @time 2015-6-01 ÏÂÎç3:20:04
 * @author luojw
 */
public class UnaprroveClosingCheck extends BackstageClosingCheck{

  @Override
  protected ClosingCheckModelService getService() {
    return new ClosingCheckModelService(ClosingCheckModelService.UNAPPROVE);
  }
}
