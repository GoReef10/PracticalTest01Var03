package ro.pub.cs.systems.eim.practicaltest01var03

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PracticalTest01Var03SecondaryActivity : AppCompatActivity() {
    private lateinit var correctButton : Button
    private lateinit var incorrectButton : Button
    private lateinit var rezultat2TextView: TextView
    private lateinit var buttonClickListener: ButtonClickListener

    private inner class ButtonClickListener : View.OnClickListener {
        override fun onClick(v: View) {
            when(v.id) {
                R.id.incorrect -> setResult(RESULT_CANCELED)
                R.id.correct -> setResult(RESULT_OK)
            }
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practical_test01_var03_secondary)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        buttonClickListener = ButtonClickListener()
        correctButton = findViewById<Button>(R.id.correct)
        correctButton.setOnClickListener(buttonClickListener)
        incorrectButton = findViewById<Button>(R.id.incorrect)
        incorrectButton.setOnClickListener(buttonClickListener)
        rezultat2TextView = findViewById<EditText>(R.id.rezultat2)
        val intr = intent
        val oper = intent.getStringExtra("oper")
        rezultat2TextView.setText(oper)

    }


}