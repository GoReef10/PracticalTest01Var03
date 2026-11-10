package ro.pub.cs.systems.eim.practicaltest01var03

import android.content.Intent
import android.hardware.camera2.CameraExtensionSession
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PracticalTest01Var03MainActivity : AppCompatActivity() {
    private lateinit var plusButton: Button
    private lateinit var minusButton: Button
    private lateinit var navigateButton: Button
    private lateinit var susEditText: EditText
    private lateinit var josEditText: EditText
    private lateinit var rezTextView: TextView
    private lateinit var buttonClickListener: ButtonClickListener
    private var oper: String = ""
    private var nrSus: Int = 0
    private var nrJos: Int = 0

    private inner class ButtonClickListener : View.OnClickListener {
        override fun onClick(v: View) {
            when(v.id) {
                R.id.plus -> {
                    oper = "${susEditText.text} + ${josEditText.text} = ${susEditText.text.toString().toInt() + josEditText.text.toString().toInt()}"
                    rezTextView.text = oper

                }
                R.id.minus -> {
                    oper = "${susEditText.text} - ${josEditText.text} = ${susEditText.text.toString().toInt() - josEditText.text.toString().toInt()}"
                    rezTextView.text = oper
                }
                R.id.navigate -> {
                    val intent: Intent = Intent(this@PracticalTest01Var03MainActivity, PracticalTest01Var03SecondaryActivity::class.java)
                    intent.putExtra("rez", oper)
                    startActivityForResult(intent, 1)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practical_test01_var03_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        buttonClickListener = ButtonClickListener()
        plusButton = findViewById<Button>(R.id.plus)
        plusButton.setOnClickListener(buttonClickListener)
        minusButton = findViewById<Button>(R.id.minus)
        minusButton.setOnClickListener(buttonClickListener)
        navigateButton = findViewById<Button>(R.id.navigate)
        navigateButton.setOnClickListener(buttonClickListener)

        susEditText = findViewById<EditText>(R.id.text_sus)
        josEditText = findViewById<EditText>(R.id.text_jos)
        rezTextView = findViewById<TextView>(R.id.rezultat)

        if(savedInstanceState != null) {
            nrSus = savedInstanceState.getInt("sus", 0)
            nrJos = savedInstanceState.getInt("jos", 0)
        }
        else {
            nrSus = 0
            nrJos = 0
        }
        susEditText.setText(nrSus.toString())
        josEditText.setText(nrJos.toString())
        rezTextView.setText(oper)
//        val receivedIntent: Intent = intent
//        val nrClicks: Int = receivedIntent.getIntExtra(Constants.NR_CLICKS, 0)
//        nrClicksTextView.text = nrClicks.toString()

    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("sus", susEditText.text.toString().toInt())
        outState.putInt("jos", josEditText.text.toString().toInt())
        outState.putString("oper", rezTextView.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        nrSus = savedInstanceState.getInt("sus", 0)
        nrJos = savedInstanceState.getInt("jos", 0)
        oper = savedInstanceState.getString("oper", "rezultat")
        susEditText.setText(nrSus.toString())
        josEditText.setText(nrJos.toString())
        rezTextView.setText(oper)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1) {
            when(resultCode){
                RESULT_OK -> Toast.makeText(this, "Correct selected, activity returned with result code: $resultCode", Toast.LENGTH_LONG).show()
                RESULT_CANCELED -> Toast.makeText(this, "Incorrect selected, activity returned with result code: $resultCode", Toast.LENGTH_LONG).show()
            }
        }
    }

}