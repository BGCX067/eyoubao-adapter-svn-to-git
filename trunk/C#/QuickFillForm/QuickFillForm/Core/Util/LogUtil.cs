using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;
using System.Reflection;
using QuickFillForm.Core.Resolver;

namespace QuickFillForm.Core.Util
{
    public class LogUtil
    {
        public static void log(string message)
        {
            if (ConfigResolver.GetInstance().IsDebug())
            {
                FileInfo fileIofo = new FileInfo(Assembly.GetExecutingAssembly().Location);
                string path = fileIofo.Directory.FullName + "\\log.txt";
                StreamWriter writer = new StreamWriter(path, true, Encoding.UTF8);
                writer.WriteLine(message);
                writer.Close();
            }
        }
    }
}
