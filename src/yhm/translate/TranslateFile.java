package yhm.translate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import yhm.manager.FileAnalysis;

public class TranslateFile {
	private final Integer MAX_BYTE = 6000;
	private final String DOC = "doc";
	private final String DOCX = "docx";
	private final String TXT = "txt";
	
	public TranslateFile() {
		// TODO Auto-generated constructor stub
	}
	public String readFile(File file) {
		if(!file.exists()&&file.isDirectory()) return null;
		String fileName = file.getName();
		String fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		String fileText = "";
		FileAnalysis analysis = new FileAnalysis(file);
		if (fileSuffix.equals(DOC)) {
//			HWPFDocument doc = analysis.readDocWord();
		} else if (fileSuffix.equals(DOCX)) {
			XWPFDocument docx = analysis.readDocxWord();
			List<XWPFParagraph> paragraphs = docx.getParagraphs();
			for (XWPFParagraph para : paragraphs) {
				List<XWPFRun> runs = para.getRuns();
				for (XWPFRun run : runs) {
					fileText += run.getText(0)+"\n";
				}
			}
		} else if (fileSuffix.equals(TXT)) {
			FileInputStream fis;
			try {
				fis = new FileInputStream(file);
				fileText += fis.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(fileText);
		return fileText;
	}
	public Map<Integer, String> segmentedTranslation(File file) {
		String readFile = this.readFile(file);
		String regex = "\\n";
		Map<Integer, String> transMap = new HashMap<>();
		int keyNum = 1;
		String[] strArr = readFile.split(regex);
		int strBytes = 0;
		String value = new String();
		try {
			for (String str : strArr) {
				strBytes += str.getBytes("utf-8").length;
				if (strBytes > this.MAX_BYTE) {
					transMap.put(keyNum,value);
					value = new String(str+"\n");
					strBytes = 0;
					keyNum++;
				} else {
					value += str + "\n";
				}
			}
			if (value.length() != 0) {
				transMap.put(keyNum,value);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transMap;
	}
}
