package yhm.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class FileAnalysis {
	private File file;
	public FileAnalysis(File file) {
		this.file = file;
	}
	public HWPFDocument readDocWord() {
		FileInputStream fis = null;
		HWPFDocument document = null;
		if (!file.exists()) return null;
		try {
			fis = new FileInputStream(file);
			document = new HWPFDocument(fis);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeInput(fis);
		}
		return document;
	}
	public XWPFDocument readDocxWord() {
		FileInputStream fis = null;
		XWPFDocument document = null;
		if (!file.exists()) return null;
		try {
			fis = new FileInputStream(file);
			document = new XWPFDocument(fis);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeInput(fis);
		}
		return document;
	}
	public HWPFDocument writeDocWord() {
		FileInputStream fis = null;
		HWPFDocument document = null;
		if (!file.exists()) return null;
		try {
			fis = new FileInputStream(file);
			document = new HWPFDocument(fis);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return document;
	}
	
	public XWPFDocument writeDocxWord(XWPFDocument docx,File newFile) {
		FileOutputStream fos = null;
		XWPFDocument document = null;
		try {
			if (!newFile.getParentFile().exists()) {
				newFile.getParentFile().mkdirs();
			}
			if (!newFile.exists()) {
				newFile.createNewFile();
			}
			fos = new FileOutputStream(newFile);
			docx.write(fos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return document;
	}
	
	
	private void closeInput(InputStream is) {
        if (is != null) {
           try {
              is.close();
           } catch (IOException e) {
              e.printStackTrace();
           }
        }
     }
	private void closeOutput(OutputStream os) {
		if (os != null) {
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
