package com.example.simplearithmeticoperations

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.simplearithmeticoperations.databinding.ActivityMainBinding
import com.example.simplearithmeticoperations.databinding.ContentMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var contentMainBinding: ContentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        setSupportActionBar(activityMainBinding.toolbar)

        contentMainBinding = ContentMainBinding.bind(activityMainBinding.root.findViewById(R.id.nav_host_fragment_content_main))

        contentMainBinding.btnAdd.setOnClickListener {
            performOperation(Operation.ADD)
        }

        contentMainBinding.btnSubtract.setOnClickListener {
            performOperation(Operation.SUBTRACT)
        }

        contentMainBinding.btnMultiply.setOnClickListener {
            performOperation(Operation.MULTIPLY)
        }

        contentMainBinding.btnDivide.setOnClickListener {
            performOperation(Operation.DIVIDE)
        }
    }

    private fun performOperation(operation: Operation) {
        val firstNumber = contentMainBinding.etFirstNumber.text.toString().toDoubleOrNull()
        val secondNumber = contentMainBinding.etSecondNumber.text.toString().toDoubleOrNull()

        if (firstNumber == null || secondNumber == null) {
            contentMainBinding.tvResult.text = "Please enter valid numbers"
            return
        }

        val result = when (operation) {
            Operation.ADD -> firstNumber + secondNumber
            Operation.SUBTRACT -> firstNumber - secondNumber
            Operation.MULTIPLY -> firstNumber * secondNumber
            Operation.DIVIDE -> {
                if (secondNumber != 0.0) {
                    firstNumber / secondNumber
                } else {
                    contentMainBinding.tvResult.text = "Cannot divide by zero"
                    return
                }
            }
        }

        contentMainBinding.tvResult.text = "Result: $result"
    }

    enum class Operation {
        ADD, SUBTRACT, MULTIPLY, DIVIDE
    }
}
