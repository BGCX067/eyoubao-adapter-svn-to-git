using System;
using System.Collections.Generic;
using System.Text;

namespace EYouBaoAdapter.Exception
{
    public class InvalidOrderException : ApplicationException
    {
        public InvalidOrderException(string message) : base(message) { }
    }
}
