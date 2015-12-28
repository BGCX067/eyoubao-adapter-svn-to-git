using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QuickFillForm.Core.Converter
{
    public interface IConverter
    {
        string Convert(string name, string value);
    }
}
