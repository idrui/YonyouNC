package nc.md.gen.pdm.index.test;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import nc.bs.framework.test.AbstractTestCase;
import nc.md.gen.pdm.index.IndexFileManager;
import nc.md.gen.pdm.index.PDMFileManager;
import nc.md.gen.pdm.index.PDMInfo;
import nc.md.gen.pdm.index.TabIndex;
import nc.vo.jcom.io.FileUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.collections.MapUtils;

public class PDMReadTest extends AbstractTestCase {
  // private final static String
  // PDMS_DIR="M:\\zhaoyha_NC_SCM_RES6.0_2_int\\NC6_SCM_VOB\\NC_SCM_RES\\ct";
  // public void testRead() {
  // PDMInfo pdm = PDMFileManager
  // .readPDM("f:/temp/NC_SCM_PO_STOCKKPS.pdm");
  // List<TabIndex> tabIndexList = new ArrayList<TabIndex>();
  // Map<String, TabIndex[]> indexMap=pdm.getIndexes();
  // if(MapUtils.isEmpty(indexMap)) return;
  // for (TabIndex[] tis : indexMap.values())
  // tabIndexList.addAll(Arrays.asList(tis));
  // if (0 < tabIndexList.size())
  // IndexFileManager.saveToIndexFile(tabIndexList
  // .toArray(new TabIndex[tabIndexList.size()]));
  // //System.out.println(indexMap.size());
  // }

  // public void testAddPDMIndexes() {
  // PDMFileManager
  // .addPDMIndexes("C:\\Documents and Settings\\zhaoyha\\×ÀÃæ\\temp\\po_stockps0804.pdm");
  // }

  public void testExtractPDMIndexes() throws Exception {
    File dir = new File("");// PDMS_DIR);
    List<File> pdms = FileUtil.listFiles(dir, new FileFilter() {
      @Override
      public boolean accept(File pathname) {
        try {
          if (pathname.getCanonicalPath().toLowerCase().endsWith(".pdm")) {
            return true;
          }
        }
        catch (IOException e) {
          ExceptionUtils.wrappException(e);
        }
        return false;
      }

    });
    PDMInfo[] pdmInfos = new PDMInfo[pdms.size()];
    for (int i = 0; i < pdms.size(); ++i) {
      File file = pdms.get(i);
      pdmInfos[i] = PDMFileManager.readPDM(file.getCanonicalPath());
    }
    List<TabIndex> tabIndexList = new ArrayList<TabIndex>();
    for (PDMInfo pdm : pdmInfos) {
      Map<String, TabIndex[]> indexMap = pdm.getIndexes();
      if (MapUtils.isEmpty(indexMap)) {
        continue;
      }
      for (TabIndex[] tis : indexMap.values()) {
        tabIndexList.addAll(Arrays.asList(tis));
      }
    }
    if (0 < tabIndexList.size()) {
      IndexFileManager.saveToIndexFile(tabIndexList
          .toArray(new TabIndex[tabIndexList.size()]));
    }
  }

}
