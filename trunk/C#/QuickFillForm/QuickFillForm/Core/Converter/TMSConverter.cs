using System.IO;
using System.Xml.XPath;
using System.Reflection;
using System.Collections.Generic;
using System.Windows.Forms;

namespace QuickFillForm.Core.Converter
{
    /**
     * 运管系统数据转换
     * */
    public class TMSConverter : IConverter
    {
        private static readonly TMSConverter instance = new TMSConverter();

        private FileMappingConverter proxy;

        private TMSConverter()
        {
            this.initializ();
        }

        public static TMSConverter GetInstance()
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
            string path = fileIofo.Directory.FullName + "\\Config\\tms-mapping.xml";
            this.proxy = new FileMappingConverter(path);
        }
    }
}
