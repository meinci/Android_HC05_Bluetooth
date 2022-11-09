package com.info.android_bt_hc05_ver1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    BluetoothSocket btSocket = null;
    TextView textView;
    SeekBar seekBarEff1;
    SeekBar seekBarEff2;
    SeekBar seekBarEff3;
    TextView textViewseekBar1;
    TextView textViewseekBar2;
    TextView textViewseekBar3;
    ImageView imageView_BT_connected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView_BT_connected = findViewById(R.id.imageView_BT_icon);
        textView = findViewById(R.id.Id_textView);
        seekBarEff1 = findViewById(R.id.Id_seekBar_Eff1);
        seekBarEff2 = findViewById(R.id.Id_seekBar_Eff2);
        seekBarEff3 = findViewById(R.id.Id_seekBar_Eff3);
        textViewseekBar1 = findViewById(R.id.Id_textView_seekBar1);
        textViewseekBar2 = findViewById(R.id.Id_textView_seekBar2);
        textViewseekBar3 = findViewById(R.id.Id_textView_seekBar3);
        BTconnect();
//        String l_effekt1 = "eff1";




        seekBarEff1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textViewseekBar1.setText("" + i);
                textViewseekBar2.setText("0");
                seekBarEff2.setProgress(0);
                textViewseekBar3.setText("0");
                seekBarEff3.setProgress(0);
                boolean connected = btSocket.isConnected();
                if (connected == true) {
                    System.out.println("CONNECTED!!!");
                    try {
                    /*OutputStream outputStream = btSocket.getOutputStream();
                    String stringValue = Integer.toString(i+100);
                    String neuString1 = stringValue.concat("e1#");
                    System.out.println(neuString1);
                    outputStream.write(neuString1.getBytes());*/
                        OutputStream outputStream = btSocket.getOutputStream();
                        int Value = (i);
                        outputStream.write(Value);
                        outputStream.write(Value);
                        System.out.println(i);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    BTconnect();
                }

                /*InputStream inputStream;
                try {   //***Empfängt 1 Byte***
                    inputStream = btSocket.getInputStream();
                    char a = (char) inputStream.read();
                    System.out.println(a);
                    char charOff = 'A';
                    if (a == charOff && i != 0) {
                        textView.setText("Effekt1");
                    } else {
                        textView.setText("Off");
                        System.out.println("effekt1 Off");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarEff2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textViewseekBar2.setText("" + i);
                textViewseekBar1.setText("0");
                seekBarEff1.setProgress(0);
                textViewseekBar3.setText("0");
                seekBarEff3.setProgress(0);
                try {
                    /*OutputStream outputStream = btSocket.getOutputStream();
                    String stringValue = Integer.toString(i+100);
                    String neuString1 = stringValue.concat("e2#");
                    System.out.println(neuString1);
                    outputStream.write(neuString1.getBytes());*/

                    OutputStream outputStream = btSocket.getOutputStream();
                    int Value = (i+50);
                    outputStream.write(Value);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                /*InputStream inputStream;
                try {   //***Empfängt 1 Byte***
                    inputStream = btSocket.getInputStream();
                    char a = (char) inputStream.read();
                    System.out.println(a);
                    char charOff = 'B';
                    if (a == charOff && i != 0) {
                        textView.setText("Effek 2");

                    } else {
                        textView.setText("Off");
                        System.out.println("effekt2 off");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


        seekBarEff3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textViewseekBar3.setText("" + i);
                textViewseekBar1.setText("0");
                textViewseekBar2.setText("0");
                seekBarEff1.setProgress(0);
                seekBarEff2.setProgress(0);
                try {
                    /*OutputStream outputStream = btSocket.getOutputStream();
                    String stringValue = Integer.toString(i+100);
                    String neuString1 = stringValue.concat("e3#");
                    System.out.println(neuString1);
                    outputStream.write(neuString1.getBytes());*/

                    OutputStream outputStream = btSocket.getOutputStream();
                    int Value = (i+100);
                    outputStream.write(Value);
                } catch (IOException e) {
                    e.printStackTrace();
                }
               /* InputStream inputStream;
                try {   //***Empfängt 1 Byte***
                    inputStream = btSocket.getInputStream();
                    char c = (char) inputStream.read();
                    System.out.println(c);
                    char charOff = 'C';
                    if (c == charOff && i != 0) {
                        textView.setText("Effek 3");

                    } else {
                        textView.setText("Off");
                        System.out.println("effekt3 aus");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

    }


    @SuppressLint("MissingPermission")
    public void BTconnect() {
        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
        BluetoothDevice HC05A = btAdapter.getRemoteDevice("98:D3:32:70:E3:82");
        //BluetoothDevice HC05B = btAdapter.getRemoteDevice("98:D3:32:70:E0:FD");
        try {
            btSocket = HC05A.createRfcommSocketToServiceRecord(MY_UUID);
            btSocket.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            OutputStream outputStream = btSocket.getOutputStream();
            outputStream.write(200);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*InputStream inputStream ;
        try {
            inputStream = btSocket.getInputStream();
            char b = (char) inputStream.read();
        }catch (IOException e){
            e.printStackTrace();
        }*/
    }
}
