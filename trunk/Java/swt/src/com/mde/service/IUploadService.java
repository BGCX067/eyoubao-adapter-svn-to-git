package com.mde.service;

import java.io.File;

public interface IUploadService
{
    String upload(String filename, File file, String rootPath);
}
