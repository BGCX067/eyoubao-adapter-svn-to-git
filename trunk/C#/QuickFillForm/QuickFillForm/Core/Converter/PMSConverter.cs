using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;
using System.Xml.XPath;
using System.Reflection;

namespace QuickFillForm.Core.Converter
{
    /**
     * 公安系统数据转换
     * */
    public class PMSConverter : IConverter
    {
        private static readonly PMSConverter instance = new PMSConverter();

        private FileMappingConverter proxy;

        private PMSConverter()
        {
            this.initializ();
        }

        public static PMSConverter GetInstance()
        {
            return instance;
        }

        public string Convert(string name, string value)
        {
            return this.proxy.Convert(name, value);
        }

        private void initializ()
        {
            FileInfo fileIofo = new FileInfo(Assembly.GetExecutingAssembly().Location);
            string path = fileIofo.Directory.FullName + "\\Config\\pms-mapping.xml";
            this.proxy = new FileMappingConverter(path);
        }
    }
}
