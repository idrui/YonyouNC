package nc.ui.pu.qst.newoaspwh.action;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.INewoaspwhMaintain;
import nc.ui.lm.jk45.oaspjg.ace.handler.ShowSPOpinionAction;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIEditorPane;
import nc.ui.pub.beans.UIScrollPane;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.NCAction;
import nc.vo.lm.oaspjg.AggOaspjgHVO;
import nc.vo.pu.qst.newoaspwh.AggNewoaspwhaHeadVO;
import nc.vo.pub.BusinessException;

public class ShowOpinionAction extends NCAction{

	private ShowUpableBillForm editor;

	private BillManageModel model;
	
	public ShowOpinionAction() {
		super();
		// TODO 自动生成的构造函数存根
		this.setBtnName("查看意见");
		this.setCode("nckyjlist");
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO 自动生成的方法存根
		FileWriter fw = null;
		String filename = null;
		try{
			if(null == this.model.getSelectedData() || null == this.model.getSelectedOperaRows() || this.model.getSelectedOperaRows().length > 1){
				MessageDialog.showHintDlg(this.editor, "提示", "只能选一条数据");
				return;
			}
			File dplace = new File("d:/");
			if(dplace.exists()){
				filename = "d:/" + ShowSPOpinionAction.getSequenceNumber() + ".html";
				File fopinion = new File(filename);
				fw = new FileWriter(fopinion);
				String headcode = "<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=GBK\"></head><body><table border=\"1\">";
				fw.write(headcode);
				String opinions = this.getOpinions();
				if(null != opinions){
					fw.write(opinions);
				}else return;
				fw.write("</table></body>");
			}
		}finally{
			if(null != fw) 
				fw.close();
		}
		if(null != filename){
			UIEditorPane editorPane = new UIEditorPane();
			String path = "file:///"+ filename;  
			editorPane.setEditable(false);     //请把editorPane设置为只读，不然显示就不整齐  
			editorPane.setPage(path);
			UIDialog frame = new UIDialog(this.editor);
			Toolkit kit = Toolkit.getDefaultToolkit();    // 定义工具包
		    Dimension screenSize = kit.getScreenSize();   // 获取屏幕的尺寸
		    int screenWidth = screenSize.width/2;         // 获取屏幕的宽
		    int screenHeight = screenSize.height/2;       // 获取屏幕的高
		    int height = this.editor.getHeight();
		    frame.setLocation(screenWidth/2, screenHeight-height/2);
			frame.setSize(600, 600);
			UIScrollPane scrollPane = new UIScrollPane(editorPane);  
			Container container = frame.getContentPane();  
		    //让editorPane总是填满整个窗体  
		    container.add(scrollPane, BorderLayout.CENTER);
		    frame.setVisible(true);
		}
		
	}

	public ShowUpableBillForm getEditor() {
		return editor;
	}

	public void setEditor(ShowUpableBillForm editor) {
		this.editor = editor;
	}

	public BillManageModel getModel() {
		return model;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
	}
	
	public static String getSequenceNumber(){
		Date d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("MMddhhmmss");
		String str=sdf.format(d);
		String haomiao=String.valueOf(System.nanoTime());
		str=str+haomiao.substring(haomiao.length()-6,haomiao.length());
		return str;
	}
	
	private String getOpinions(){
		String opinions = (String) ((AggNewoaspwhaHeadVO)this.model.getSelectedData()).getParentVO().getAttributeValue("spfah");
		INewoaspwhMaintain operator = NCLocator.getInstance().lookup(
				INewoaspwhMaintain.class);
		try {
			return operator.queryOpinions(opinions);
		} catch (BusinessException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}
}
