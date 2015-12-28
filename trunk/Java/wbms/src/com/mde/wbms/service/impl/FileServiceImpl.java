package com.mde.wbms.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.mde.wbms.service.IFileService;

public class FileServiceImpl implements IFileService
{
    @Override
    public void merge(File[] files, String mergedFilePath) throws IOException, DocumentException
    {
        if (files.length == 0)
        {
            return;
        }
        
        Document document = new Document();
        PdfCopy dest = new PdfCopy(document, new FileOutputStream(mergedFilePath));
        
        try
        {
            document.open();
            
            for (File file : files)
            {
                this.merge(dest, file);
            }
        }
        finally
        {
            document.close();
        }
    }
    
    private void merge(PdfCopy dest, File file) throws IOException, DocumentException
    {
        PdfReader reader = new PdfReader(new FileInputStream(file));
        
        PdfImportedPage page;
        
        for (int i = 0; i < reader.getNumberOfPages(); i++)
        {
            page = dest.getImportedPage(reader, i + 1);
            dest.newPage();
            dest.addPage(page);
        }
    }
}
