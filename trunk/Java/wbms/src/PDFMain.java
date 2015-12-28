import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;

/*
 * 文件名称：PDFMain.java  下午1:05:25 2013-3-8
 * 版权说明：js.todaysoft Technologies Co., Ltd. Copyright 2010-2017, All rights reserved.
 */

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author  xuxin
 * @version 1.0, 2013-3-8
 * @see     [相关类/方法]
 */
public class PDFMain
{
    public static void main(String[] args)
    {
        String[] files = {"e:\\1.pdf", "e:\\2.pdf"};
        String savepath = "e:\\temp.pdf";
        mergePdfFiles(files, savepath);
        readPDF("e:\\2.pdf");
    } /*  
      15.         * * 合並pdf文件 * * @param files 要合並文件數組(絕對路徑如{ "e:\\1.pdf", "e:\\2.pdf" ,  
      16.         * "e:\\3.pdf"}) * @param newfile  
      17.         * 合並後新產生的文件絕對路徑如e:\\temp.pdf,請自己刪除用過後不再用的文件請 * @return boolean  
      18.         * 產生成功返回true, 否則返回false  
      19.         */
    
    public static boolean mergePdfFiles(String[] files, String newfile)
    {
        boolean retValue = false;
        Document document = null;
        try
        {
            document = new Document(new PdfReader(files[0]).getPageSize(1));
            PdfCopy copy = new PdfCopy(document, new FileOutputStream(newfile));
            document.open();
            for (int i = 0; i < files.length; i++)
            {
                PdfReader reader = new PdfReader(files[i]);
                int n = reader.getNumberOfPages();
                for (int j = 1; j <= n; j++)
                {
                    document.newPage();
                    PdfImportedPage page = copy.getImportedPage(reader, j);
                    copy.addPage(page);
                }
            }
            retValue = true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            document.close();
        }
        return retValue;
    }
    
    public static void readPDF(String fileName)
    {
        File file = new File(fileName);
        FileInputStream in = null;
        try
        {
            in = new FileInputStream(fileName);
            
            // 新建一个PDF解析器对象   
            PDFParser parser = new PDFParser(in);
            // 对PDF文件进行解析   
            parser.parse();
            // 获取解析后得到的PDF文档对象   
            PDDocument pdfdocument = parser.getPDDocument();
            // 新建一个PDF文本剥离器   
            PDFTextStripper stripper = new PDFTextStripper();
            // 从PDF文档对象中剥离文本   
            String result = stripper.getText(pdfdocument);
            System.out.println("PDF文件的文本内容如下：");
            System.out.println(result);
            
        }
        catch (Exception e)
        {
            System.out.println("读取PDF文件" + file.getAbsolutePath() + "生失败！" + e);
            e.printStackTrace();
        }
        finally
        {
            if (in != null)
            {
                try
                {
                    in.close();
                }
                catch (IOException e1)
                {
                }
            }
        }
    }
    
}
