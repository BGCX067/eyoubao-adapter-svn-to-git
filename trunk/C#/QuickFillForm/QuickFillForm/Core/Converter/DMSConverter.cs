using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;
using System.Reflection;

namespace QuickFillForm.Core.Converter
{
    public class DMSConverter : IConverter
    {
        private static readonly DMSConverter instance = new DMSConverter();

        private FileMappingConverter proxy;

        private DMSConverter()
        {
            this.initializ();
        }

        public static DMSConverter GetInstance()
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
            string path = fileIofo.Directory.FullName + "\\Config\\dms-mapping.xml";
            this.proxy = new FileMappingConverter(path);
        }
    }
}
