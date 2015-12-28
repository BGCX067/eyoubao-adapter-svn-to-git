using System;
using System.Collections.Generic;
using System.Text;

namespace EYouBaoAdapter.Exception
{
    public class EYouBaoAccessException : ApplicationException
    {
        public EYouBaoAccessException(string message) : base(message) { }
    }
}
