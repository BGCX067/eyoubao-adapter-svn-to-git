package com.mde.wbms.service;

import java.io.File;
import java.io.IOException;

import com.lowagie.text.DocumentException;

public interface IFileService
{
    void merge(File[] files, String mergedFilePath) throws IOException, DocumentException;
}
