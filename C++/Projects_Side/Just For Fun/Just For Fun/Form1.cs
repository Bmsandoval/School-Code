using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace Just_For_Fun
{
    public partial class Form1 : Form
    {
        const int D_FOUR = 4;
        const int D_SIX = 6;
        const int D_EIGHT = 8;
        const int D_TWELVE = 12;
        const int D_TWENTY = 20;

        private int _custom = 0;
        private int _rollVal = 0;

        Roll Value = new Roll();

        public Form1()
        {
            InitializeComponent();
        }

        private void radioButton1_CheckedChanged(object sender, EventArgs e)
        {

        }

        private void radioButton2_CheckedChanged(object sender, EventArgs e)
        {

        }

        private void radioButton3_CheckedChanged(object sender, EventArgs e)
        {

        }

        private void radioButton4_CheckedChanged(object sender, EventArgs e)
        {

        }

        private void radioButton5_CheckedChanged(object sender, EventArgs e)
        {

        }

        private void radioButton6_CheckedChanged(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (radioButton1.Checked == true)
            {
                _rollVal = Value.rndm(D_FOUR);
                listBox1.Items.Add("D4:\t" + _rollVal.ToString());
            }
            else if (radioButton2.Checked == true)
            {
                _rollVal = Value.rndm(D_SIX);
                listBox1.Items.Add("D6:\t" + _rollVal.ToString());
            }
            else if (radioButton3.Checked == true)
            {
                _rollVal = Value.rndm(D_EIGHT);
                listBox1.Items.Add("D8:\t" + _rollVal.ToString());
            }
            else if (radioButton4.Checked == true)
            {
                _rollVal = Value.rndm(D_TWELVE);
                listBox1.Items.Add("D12:\t" + _rollVal.ToString());
            }
            else if (radioButton5.Checked == true)
            {
                _rollVal = Value.rndm(D_TWENTY);
                listBox1.Items.Add("D20:\t" + _rollVal.ToString());
            }
            else if (radioButton6.Checked == true)
            {
                if (int.TryParse(textBox1.Text, out _custom) == false)
                    MessageBox.Show("You have entered an invalid value");
                else
                {
                    _rollVal = Value.rndm(_custom);
                    listBox1.Items.Add("D" + _custom + ":\t" + _rollVal.ToString());
                }
            }
            else
                MessageBox.Show("Please select a value of dice to roll");
        }

        private void button2_Click(object sender, EventArgs e)
        {
            listBox1.Items.Clear();
        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {
            radioButton6.PerformClick();
        }

        private void listBox1_SelectedIndexChanged(object sender, EventArgs e)
        {

        }


    }

    public class Roll
    {
        public int rndm(int r)
        {
            Random rand = new Random();
            return (rand.Next(1, r + 1)); 
        }    
    }
}
