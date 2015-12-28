using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;
using System.Xml.XPath;

namespace QuickFillForm.Core.Converter
{
    class FileMappingConverter : IConverter
    {
        private Dictionary<string, Dictionary<string, string>> mappings;

        public FileMappingConverter(string path)
        {
            this.initializ(path);
        }

        public string Convert(string name, string value)
        {
            Dictionary<string, string> mapping;
            this.mappings.TryGetValue(name, out mapping);

            // 没有配置转换映射时，返回输入的值
            if (null == mapping)
            {
                return value;
            }

            string converted;
            mapping.TryGetValue(value, out converted);

            // 转换条目的优先级最高
            if (null != converted)
            {
                return converted;
            }

            // 转换默认值优先级次之
            mapping.TryGetValue("DEFAULT_VALUE", out converted);

            if (null != converted)
            {
                return converted;
            }

            return value;
        }

        private void initializ(string path)
        {
            this.mappings = new Dictionary<string, Dictionary<string, string>>();
            StreamReader reader = new StreamReader(path);
            XPathDocument doc = new XPathDocument(reader);
            XPathNavigator nav = doc.CreateNavigator();
            XPathNodeIterator iterator = nav.Select("/mappings/mapping");
            XPathNavigator node;
            while (iterator.MoveNext())
            {
                node = iterator.Current;
                this.mapping(node);
            }
            reader.Close();
        }

        private void mapping(XPathNavigator node)
        {
            string name = node.GetAttribute("name", "");
            Dictionary<string, string> mapping = new Dictionary<string, string>();
            XPathNavigator defaultValueNode = node.SelectSingleNode("default");

            if (null != defaultValueNode)
            {
                mapping.Add("DEFAULT_VALUE", defaultValueNode.InnerXml);
            }

            XPathNodeIterator iterator = node.Select("entry");
            XPathNavigator entryNode;
            while (iterator.MoveNext())
            {
                entryNode = iterator.Current;
                mapping.Add(entryNode.GetAttribute("key", ""), entryNode.GetAttribute("value", ""));
            }
            this.mappings.Add(name, mapping);
        }
    }
}
