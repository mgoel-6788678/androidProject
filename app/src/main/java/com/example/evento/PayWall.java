package com.example.evento;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dev.shreyaspatil.easyupipayment.EasyUpiPayment;
import dev.shreyaspatil.easyupipayment.exception.AppNotFoundException;
import dev.shreyaspatil.easyupipayment.listener.PaymentStatusListener;
import dev.shreyaspatil.easyupipayment.model.TransactionDetails;

public class PayWall extends AppCompatActivity implements PaymentStatusListener {
    EditText amount;
    EditText Desc;
    Button pay;

    EasyUpiPayment payment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_wall);

        amount = findViewById(R.id.amount);
        Desc = findViewById(R.id.desc);
        pay = findViewById(R.id.pay);

        pay.setOnClickListener(view -> {
            makePayment();
        });
    }

    private void makePayment() {
        try {
            Toast.makeText(this, "Amount: "+amount.getText().toString(), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Description: "+Desc.getText().toString(), Toast.LENGTH_SHORT).show();
            EasyUpiPayment.Builder builder = new EasyUpiPayment.Builder(this)
                    .setPayeeName("Mehul")
                    .setPayeeVpa("8448228525@paytm")
                    .setAmount(amount.getText().toString())
                    .setDescription(Desc.getText().toString())
                    .setTransactionId(String.valueOf(System.currentTimeMillis()))
                    .setTransactionRefId(String.valueOf(System.currentTimeMillis()))
                    .setPayeeMerchantCode("12345");
            payment = builder.build();
            payment.setPaymentStatusListener(this);
            payment.startPayment();
        } catch (AppNotFoundException e) {
            Log.e("Payment error", "No app found");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTransactionCancelled() {
        Toast.makeText(this, "Transaction cancelled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTransactionCompleted(@NonNull TransactionDetails transactionDetails) {
        Toast.makeText(this, "Transaction Completed", Toast.LENGTH_SHORT).show();
        Log.d("Details", transactionDetails.toString());
    }

    @Override
    public void onDestroy() {
        payment.removePaymentStatusListener();
        super.onDestroy();
    }
}