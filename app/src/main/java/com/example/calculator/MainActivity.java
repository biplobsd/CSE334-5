package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btn_1,btn_2,btn_3,btn_4,btn_5,btn_6,btn_7,btn_8,btn_9,btn_0,btn_dot,btn_ac,btn_c,
            btn_div,btn_multiply,btn_minus,
            btn_plus,btn_equals;

    TextView text_small, text_big;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        btn_6 = findViewById(R.id.btn_6);
        btn_7 = findViewById(R.id.btn_7);
        btn_8 = findViewById(R.id.btn_8);
        btn_9 = findViewById(R.id.btn_9);
        btn_0 = findViewById(R.id.btn_0);
        btn_dot = findViewById(R.id.btn_dot);
        btn_ac = findViewById(R.id.btnac);
        btn_c = findViewById(R.id.btn_c);
        btn_div = findViewById(R.id.btn_div);
        btn_multiply = findViewById(R.id.btn_multiply);
        btn_minus = findViewById(R.id.btn_minus);
        btn_plus = findViewById(R.id.btn_plus);
        btn_equals = findViewById(R.id.btn_equals);

        text_big = findViewById(R.id.text_big);
        text_small = findViewById(R.id.text_small);


        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text_big.setText(text_big.getText()+"1");
            }
        });
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text_big.setText(text_big.getText()+"2");
            }
        });
        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text_big.setText(text_big.getText()+"3");
            }
        });
        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text_big.setText(text_big.getText()+"4");
            }
        });
        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text_big.setText(text_big.getText()+"5");
            }
        });
        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text_big.setText(text_big.getText()+"6");
            }
        });
        btn_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text_big.setText(text_big.getText()+"7");
            }
        });
        btn_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text_big.setText(text_big.getText()+"8");
            }
        });
        btn_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text_big.setText(text_big.getText()+"9");
            }
        });
        btn_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text_big.setText(text_big.getText()+"0");
            }
        });
        btn_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text_big.setText(text_big.getText()+".");
            }
        });
        btn_ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text_big.setText(""); text_small.setText("");
            }
        });
        btn_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String val = text_big.getText().toString();
                String val2 = text_small.getText().toString();

                if(!val.equals("") || !val2.equals("")) {
                    val = val.substring(0, val.length() -1);
                    text_big.setText(val);
                    text_small.setText(val);}
            }
        });
        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text_big.setText(text_big.getText()+"+");
            }
        });
        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text_big.setText(text_big.getText()+"-");
            }
        });
        btn_multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text_big.setText(text_big.getText()+"×");
            }
        });
        btn_div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text_big.setText(text_big.getText()+"÷");
            }
        });

        btn_equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String val = text_big.getText().toString();
                String replacedstr = val.replace('÷','/').replace('×', '*');
                double result = eval(replacedstr);
                text_big.setText(String.valueOf(result));
                text_small.setText(val);
            }
        });

    }


    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm();
                    else if (eat('-')) x -= parseTerm();
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor();
                    else if (eat('/')) x /= parseFactor();
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor();
                if (eat('-')) return -parseFactor();

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }
                return x;
            }
        }.parse();
    }
}
