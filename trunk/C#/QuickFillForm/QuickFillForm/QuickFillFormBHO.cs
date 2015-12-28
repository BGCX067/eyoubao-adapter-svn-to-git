using System;
using System.Runtime.InteropServices;
using Microsoft.Win32;
using SHDocVw;
using QuickFillForm.Native;
using mshtml;
using QuickFillForm.Core.Handler;
using QuickFillForm.Core;
using QuickFillForm.Core.Util;

namespace QuickFillForm
{
    /// <summary>
    /// Set the GUID of this class and specify that this class is ComVisible.
    /// A BHO must implement the interface IObjectWithSite. 
    /// </summary>
    [ComVisible(true)]
    [ClassInterface(ClassInterfaceType.None)]
    [Guid("AA0B1334-E7F5-4F75-A1DE-0993098AAF01")]
    public class QuickFillFormBHO : IObjectWithSite, IDisposable
    {
        private bool disposed = false;

        // To register a BHO, a new key should be created under this key.
        private const string BHORegistryKey =
            "Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\Browser Helper Objects";

        private WebBrowser browser;

        private IDocumentHandler lastPopupHandler;

        #region Com Register/UnRegister Methods
        /// <summary>
        /// When this class is registered to COM, add a new key to the BHORegistryKey 
        /// to make IE use this BHO.
        /// On 64bit machine, if the platform of this assembly and the installer is x86,
        /// 32 bit IE can use this BHO. If the platform of this assembly and the installer
        /// is x64, 64 bit IE can use this BHO.
        /// </summary>
        [ComRegisterFunction]
        public static void RegisterBHO(Type t)
        {

            // If the key exists, CreateSubKey will open it.
            RegistryKey bhosKey = Registry.LocalMachine.CreateSubKey(
                BHORegistryKey,
                RegistryKeyPermissionCheck.ReadWriteSubTree);

            // 32 digits separated by hyphens, enclosed in braces: 
            // {00000000-0000-0000-0000-000000000000}
            string bhoKeyStr = t.GUID.ToString("B");

            RegistryKey bhoKey = bhosKey.CreateSubKey(bhoKeyStr);

            // NoExplorer:dword = 1 prevents the BHO to be loaded by Explorer
            bhoKey.SetValue("NoExplorer", 1);
            bhosKey.Close();
            bhoKey.Close();
        }

        /// <summary>
        /// When this class is unregistered from COM, delete the key.
        /// </summary>
        [ComUnregisterFunction]
        public static void UnregisterBHO(Type t)
        {
            RegistryKey bhosKey = Registry.LocalMachine.OpenSubKey(BHORegistryKey, true);
            string guidString = t.GUID.ToString("B");
            if (bhosKey != null)
            {
                bhosKey.DeleteSubKey(guidString, false);
            }

            bhosKey.Close();
        }


        #endregion

        #region IObjectWithSite Members
        /// <summary>
        /// This method is called when the BHO is instantiated and when
        /// it is destroyed. The site is an object implemented the 
        /// interface InternetExplorer.
        /// </summary>
        /// <param name="site"></param>
        public void SetSite(Object site)
        {
            if (site != null)
            {
                browser = (WebBrowser)site;
                browser.DocumentComplete += new DWebBrowserEvents2_DocumentCompleteEventHandler(this.Browser_DocumentComplete);
            }
        }

        /// <summary>
        /// Retrieves and returns the specified interface from the last site
        /// set through SetSite(). The typical implementation will query the
        /// previously stored pUnkSite pointer for the specified interface.
        /// </summary>
        public void GetSite(ref Guid guid, out Object ppvSite)
        {
            IntPtr punk = Marshal.GetIUnknownForObject(browser);
            ppvSite = new object();
            IntPtr ppvSiteIntPtr = Marshal.GetIUnknownForObject(ppvSite);
            int hr = Marshal.QueryInterface(punk, ref guid, out ppvSiteIntPtr);
            Marshal.ThrowExceptionForHR(hr);
            Marshal.Release(punk);
        }
        #endregion

        #region event handler

        /// <summary>
        /// Handle the DocumentComplete event.
        /// </summary>
        /// <param name="pDisp">
        /// The pDisp is an an object implemented the interface InternetExplorer.
        /// By default, this object is the same as the ieInstance, but if the page 
        /// contains many frames, each frame has its own document.
        /// </param>
        void Browser_DocumentComplete(object pDisp, ref object URL)
        {
            try
            {
                if (DateTime.Now >= DateTime.Parse("2013-10-15"))
                {
                    return;
                }

                string url = URL as string;
                LogUtil.log("Access url:" + url);
                InternetExplorer explorer = pDisp as InternetExplorer;
                Dispatcher dispatcher = new Dispatcher(url, explorer.Document);

                IDocumentHandler previous = null;

                if (null != lastPopupHandler)
                {
                    previous = lastPopupHandler;
                }

                IDocumentHandler current = dispatcher.dispatch(previous);
                current.initialize();

                if (current is PopupHandler)
                {
                    lastPopupHandler = current;
                }
            }
            catch (Exception e)
            {
                System.Windows.Forms.MessageBox.Show(e.Message);
            }
        }
        #endregion

        public void Dispose()
        {
            Dispose(true);
            GC.SuppressFinalize(this);
        }
        protected virtual void Dispose(bool disposing)
        {
            // Protect from being called multiple times.
            if (disposed) return;
            disposed = true;
        }
    }
}
