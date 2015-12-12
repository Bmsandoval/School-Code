// File Prologue
// Name: Bryan Sandoval
// CS 1400 Section 001
// Project: Lab26
// Date: 4/25/2013 3:08:55 PM
// 
// I declare that the following code was written by me or provided 
// by the instructor for this project. I understand that copying source
// code from any other source constitutes cheating, and that I will receive
// a zero on this project if I am found in violation of this policy.
// ---------------------------------------------------------------------------






using System;
using System.Collections.Generic;
using System.Linq;
using System.Windows.Forms;

namespace Just_For_Fun
{
    static class Program
    {

        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            Application.Run(new Form1());
        }
    }
}
