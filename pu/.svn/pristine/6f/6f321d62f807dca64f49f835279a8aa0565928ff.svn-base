package nc.ui.pu.uif2;

import nc.ui.pubapp.uif2app.AppUiState;

/**
 * 采购组的前台状态常量类
 * 
 * @since 6.0
 * @version 2011-12-1 上午9:51:13
 * @author zhaoyha
 */
public class PUUIState extends AppUiState {

  /** 界面编辑状态-普通编辑 */
  public static final PUUIState EDIT_NORM = new PUUIState(AppUiState.EDIT);

  /** 界面编辑状态-修订编辑 */
  public static final PUUIState EDIT_REVISE = new PUUIState(AppUiState.EDIT);

  /** 界面无状态 */
  public static final PUUIState NOT_EDIT = new PUUIState(AppUiState.NOT_EDIT);

  private AppUiState appUiState = null;

  protected PUUIState(AppUiState appUiState) {
    super(appUiState.getUiState());
    this.appUiState = appUiState;
  }

  public AppUiState getAppUiState() {
    return this.appUiState;
  }

}
