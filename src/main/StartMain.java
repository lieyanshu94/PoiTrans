package main;

import java.io.File;
import java.util.Map;

import yhm.translate.TranslateFile;
import yhm.web.ThreadTrans;

public class StartMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TranslateFile trans = new TranslateFile();
		String pathname = "D://test.docx";
		Map<Integer, String> map = trans.segmentedTranslation(new File(pathname));
		ThreadTrans trans1 = ThreadTrans.creatThreadTrans();
		trans1.setMap(map);
		trans1.transValue();
	}

}
