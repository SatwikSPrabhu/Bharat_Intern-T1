package com.satwiksprabhu.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    Button zero,one,two,three,four,five,six,seven,eight,nine,point,add,subtract,multiply,divide,equal,backspace,ac;
    EditText display;
    TextView udisplay;
    public Double operand1 = null;
    public Double operand2 = null;
    public String operator = null;
    public String fullExpression = "";
    public DecimalFormat decimalFormat;
    StringBuilder sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zero = (Button) findViewById(R.id.Zero);
        one = (Button) findViewById(R.id.One);
        two = (Button) findViewById(R.id.Two);
        three = (Button) findViewById(R.id.Three);
        four = (Button) findViewById(R.id.Four);
        five = (Button) findViewById(R.id.Five);
        six = (Button) findViewById(R.id.Six);
        seven = (Button) findViewById(R.id.Seven);
        eight = (Button) findViewById(R.id.Eight);
        nine = (Button) findViewById(R.id.Nine);
        point = (Button) findViewById(R.id.Point);
        add = (Button) findViewById(R.id.Add);
        subtract = (Button) findViewById(R.id.Subtract);
        multiply = (Button) findViewById(R.id.Multiply);
        divide = (Button) findViewById(R.id.Divide);
        equal = (Button) findViewById(R.id.Equal);
        backspace = (Button) findViewById(R.id.Backspace);
        ac = (Button) findViewById(R.id.Ac);

        display = (EditText) findViewById(R.id.Display);
        display.setKeyListener(null);
        display.setLongClickable(false);
        display.setTextIsSelectable(false);
        display.setImeOptions(EditorInfo.IME_FLAG_NO_EXTRACT_UI);

        udisplay = (TextView) findViewById(R.id.UDisplay);
        udisplay.setKeyListener(null);
        udisplay.setLongClickable(false);
        udisplay.setTextIsSelectable(false);
        udisplay.setImeOptions(EditorInfo.IME_FLAG_NO_EXTRACT_UI);

        sb = new StringBuilder();

        sb = new StringBuilder();
        decimalFormat = new DecimalFormat("#.##########");

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb.append("0");
                fullExpression += "0";
                display.setText(sb);
            }
        });

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb.append("1");
                fullExpression += "1";
                display.setText(sb);
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb.append("2");
                fullExpression += "2";
                display.setText(sb);
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb.append("3");
                fullExpression += "3";
                display.setText(sb);
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb.append("4");
                fullExpression += "4";
                display.setText(sb);
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb.append("5");
                fullExpression += "5";
                display.setText(sb);
            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb.append("6");
                fullExpression += "6";
                display.setText(sb);
            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb.append("7");
                fullExpression += "7";
                display.setText(sb);
            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb.append("8");
                fullExpression += "8";
                display.setText(sb);
            }
        });

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb.append("9");
                fullExpression += "9";
                display.setText(sb);
            }
        });

        point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb.append(".");
                fullExpression += ".";
                display.setText(sb);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendOperator("+");
                fullExpression += "+";
            }
        });

        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendOperator("-");
                fullExpression += "-";
            }
        });

        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendOperator("×");
                fullExpression += "×";
            }
        });

        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendOperator("÷");
                fullExpression += "÷";
            }
        });

        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String expression = display.getText().toString();
                try {
                    expression = expression.replace("÷", "/").replace("×", "*");
                    Expression e = new ExpressionBuilder(expression).build();
                    double result = e.evaluate();
                    display.setText(formatResult(result));
                    udisplay.setText(expression.replace("*", "×").replace("/", "÷"));
                    sb.setLength(0);
                    sb.append(formatResult(result));
                } catch (Exception ex) {
                    display.setText("Error");
                    udisplay.setText("");
                }
            }
        });

        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                    display.setText(sb);
                }
            }
        });

        ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb.setLength(0);
                display.setText("0");
                udisplay.setText("");
                fullExpression = "";
                operand1 = null;
                operand2 = null;
                operator = null;
            }
        });
    }

    private void appendOperator(String operator) {
        if (sb.length() == 0) {
            if (operator.equals("-")) {
                sb.append(operator);
            } else {
                sb.append("0");
                sb.append(operator);
            }
        } else {
            char lastChar = sb.charAt(sb.length() - 1);
            if (lastChar >= '0' && lastChar <= '9') {
                sb.append(operator);
            } else if (lastChar == '.') {
                sb.deleteCharAt(sb.length() - 1);
                sb.append(operator);
            } else if (lastChar == '-' && sb.length() > 1 && sb.charAt(sb.length() - 2) >= '0' && sb.charAt(sb.length() - 2) <= '9') {
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        display.setText(sb);
    }

    private String formatResult(double result) {
        return String.format("%.10f", result).replaceAll("0*$", "").replaceAll("\\.$", "");
    }
}
