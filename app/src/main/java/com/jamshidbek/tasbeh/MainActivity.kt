package com.jamshidbek.tasbeh

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.material.shadow.ShadowRenderer
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var context:Context

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var sharedPreferences : SharedPreferences = getPreferences(Context.MODE_PRIVATE)
        var editor : SharedPreferences.Editor = sharedPreferences.edit()

        var number = sharedPreferences.getInt("timesRepeated", 0)

        wordNow.text = "Subhanallah"
        timesRepeated.text = number.toString()

        context = this

        btnPress.setOnClickListener{
            number++

            editor.clear()
            editor.putInt("timesRepeated", number)
            editor.apply()
            editor.commit()

            number = sharedPreferences.getInt("timesRepeated", 0)

            timesRepeated.text = number.toString()

            checkNumber(number)
        }

        btnRepeat.setOnClickListener{
            number = 0
            timesRepeated.text = number.toString()

            btnPress.isClickable = true

            checkNumber(number)

            btnRepeat.isVisible = false
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)

    private fun checkNumber(PressCount: Int) {

        if (PressCount == 0){
            wordNow.text = "Subhanallah"
        }

        if (PressCount == 33){
            wordNow.text = "Alhamdulillah"

            val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

            if (Build.VERSION.SDK_INT >= 26) {
                vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                vibrator.vibrate(200)
            }

        } else {
            if (PressCount == 66){
                wordNow.text = "Allahu Akbar"

                val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

                if (Build.VERSION.SDK_INT >= 26) {
                    vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
                } else {
                    vibrator.vibrate(200)
                }

            } else {
                if (PressCount == 99){
                    wordNow.text = "Completed"

                    Toast.makeText(this, "Compeleted!", Toast.LENGTH_SHORT).show()

                    val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

                    btnRepeat.isVisible = true

                    btnPress.isClickable = false

                    if (Build.VERSION.SDK_INT >= 26) {
                        vibrator.vibrate(VibrationEffect.createOneShot(400, VibrationEffect.DEFAULT_AMPLITUDE))
                    } else {
                        vibrator.vibrate(400)
                    }
                }
            }
        }
    }
}