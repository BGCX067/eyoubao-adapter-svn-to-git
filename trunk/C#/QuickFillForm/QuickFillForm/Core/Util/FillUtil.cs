using mshtml;
using System;

namespace QuickFillForm.Core.Util
{
    public class FillUtil
    {
        public static void FillText(HTMLDocument document, string id, string value)
        {
            IHTMLInputElement element = (IHTMLInputElement)document.getElementById(id);

            if (null != element)
            {
                element.value = null == value ? "" : value;
            }
        }

        public static void FillTextByName(HTMLDocument document, string name, string value)
        {
            if (null == name || "".Equals(name))
            {
                return;
            }

            IHTMLElementCollection collection = (IHTMLElementCollection)document.getElementsByName(name);

            if (null != collection && collection.length > 0)
            {
                foreach (IHTMLElement element in collection)
                {
                    if (element is IHTMLInputElement)
                    {
                        ((IHTMLInputElement)element).value = null == value ? "" : value;
                    }
                }
            }
        }

        public static void FillSelect(HTMLDocument document, string id, string value, string converted, bool fireOnchange = false)
        {
            IHTMLSelectElement element = (IHTMLSelectElement)document.getElementById(id);
            FillSelect(element, value, converted, fireOnchange);
        }

        public static void FillSelectByName(HTMLDocument document, string name, string value, string converted, bool fireOnchange = false)
        {
            if (null == name || "".Equals(name))
            {
                return;
            }

            IHTMLElementCollection collection = (IHTMLElementCollection)document.getElementsByName(name);

            if (null != collection && collection.length > 0)
            {
                foreach (IHTMLElement element in collection)
                {
                    if (element is IHTMLSelectElement)
                    {
                        FillSelect((IHTMLSelectElement)element, value, converted, fireOnchange);
                    }
                }
            }
        }

        private static void FillSelect(IHTMLSelectElement element, string value, string converted, bool fireOnchange)
        {
            if (null == element)
            {
                return;
            }

            bool selected = false;
            string oldValue = null == element.value ? "" : element.value;

            // 首先根据文本匹配
            foreach (IHTMLOptionElement option in element.options)
            {
                if (option.text.Equals(value))
                {
                    option.selected = true;
                    selected = true;
                }
            }

            // 然后根据转换后值进行文本匹配
            if (!selected && null != converted)
            {
                foreach (IHTMLOptionElement option in element.options)
                {
                    if (option.text.Equals(converted))
                    {
                        option.selected = true;
                        selected = true;
                    }
                }

                // 最后根据转换后的值匹配
                if (!selected)
                {
                    element.value = converted;
                }
            }

            string newValue = null == element.value ? "" : element.value;

            if (!oldValue.Equals(newValue) && fireOnchange)
            {
                ((IHTMLElement3)element).FireEvent("onchange");
            }
        }

        public static void fillRadio(HTMLDocument document, string name, string value)
        {
            foreach (IHTMLInputElement radio in document.getElementsByName(name))
            {
                if (radio.value.Equals(value))
                {
                    radio.@checked = true;
                }
            }
        }
    }
}
