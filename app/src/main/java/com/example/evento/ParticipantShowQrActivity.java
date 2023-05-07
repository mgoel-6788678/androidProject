package com.example.evento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class ParticipantShowQrActivity extends AppCompatActivity {


    ImageView qr_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant_show_qr);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();;

        String Email = mAuth.getCurrentUser().getEmail();

        // QR CODE
        qr_code = findViewById(R.id.qr_code);

        // MultiFormat writer -
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

        // Bit Matrix -
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(Email, BarcodeFormat.QR_CODE, 350, 350);

            // BAR CODE ENCODER -
            BarcodeEncoder encoder = new BarcodeEncoder();

            // BITMAP -
            Bitmap bitmap = encoder.createBitmap(bitMatrix);

            // BIT MAP on Image View -
            qr_code.setImageBitmap(bitmap);

            

        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}